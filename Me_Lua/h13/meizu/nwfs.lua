module("meizu.nwfs", package.seeall)
--network functions

local cjson = require "cjson"
local lfs   = require "lfs"
local bfs   = require "meizu.bfs"
local RC    = require "meizu.r10config"
local dbfs  = require "meizu.dbfs"
local posix = require "posix"

local bind_router          = bfs.bind_router
local data_to_json         = bfs.data_to_json
local exec_cmd_in_sh       = bfs.exec_cmd_in_sh
local exec_reboot          = bfs.exec_reboot
local get_device_SN        = bfs.get_device_SN
local get_device_version   = bfs.get_device_version
local get_https_data       = bfs.get_https_data
local rts_get_access_token = bfs.rts_get_access_token
local set_passwd           = bfs.set_passwd
local silent_upgrade       = bfs.silent_upgrade
local strsplit             = bfs.strsplit
local b64dec               = bfs.b64dec
local b64enc               = bfs.b64enc

local lue                  = require("luci.util").exec

function wifi_network(wifi_device_name)
    local network = require "luci.model.network".init()
    local wifi_net = network:get_wifinet(wifi_device_name)
    if wifi_net then
        local dev = wifi_net:get_device()
        if dev then
            return {
                id         = wifi_device_name,
                name       = wifi_net:shortname(),
                up         = wifi_net:is_up(),
                mode       = wifi_net:active_mode(),
                ssid       = wifi_net:active_ssid(),
                bssid      = wifi_net:active_bssid(),
                encryption = wifi_net:active_encryption(),
                encryption_src = wifi_net:get("encryption"),
                frequency  = wifi_net:frequency(),
                bitrate    = wifi_net:bitrate(),
                ifname     = wifi_net:ifname(),
                assoclist  = wifi_net:assoclist(),
                country    = wifi_net:country(),
                key        = wifi_net:get("key"),
                key1	   = wifi_net:get("key1"),
                hidden     = wifi_net:get("hidden"),
                device     = {
                    up     = dev:is_up(),
                    device = dev:name(),
                    name   = dev:get_i18n()
                }
            }
        end
    end
    return {}
end

function wifi_networks()
    local result = {}
    local network = require "luci.model.network".init()
    local dev
    for _, dev in ipairs(network:get_wifidevs()) do
        local rd = {
            up       = dev:is_up(),
            device   = dev:name(),
            name     = dev:get_i18n(),
            networks = {}
        }
        local wifi_net
        for _, wifi_net in ipairs(dev:get_wifinets()) do
            rd.networks[#rd.networks+1] = {
                name       = wifi_net:shortname(),
                up         = wifi_net:is_up(),
                mode       = wifi_net:active_mode(),
                ssid       = wifi_net:active_ssid(),
                bssid      = wifi_net:active_bssid(),
                encryption = wifi_net:active_encryption(),
                frequency  = wifi_net:frequency(),
                channel    = wifi_net:channel(),
                signal     = wifi_net:signal(),
                quality    = wifi_net:signal_percent(),
                noise      = wifi_net:noise(),
                bitrate    = wifi_net:bitrate(),
                ifname     = wifi_net:ifname(),
                assoclist  = wifi_net:assoclist(),
                country    = wifi_net:country(),
                key	   	   = wifi_net:get("key"),
                key1	   = wifi_net:get("key1"),
                encryption_src = wifi_net:get("encryption"),
                hidden = wifi_net:get("hidden"),
            }
        end
        result[#result+1] = rd
    end
    return result
end

function nw_get_wifi_settings()
    luci.http.prepare_content("application/json")
    local app_version = luci.http.formvalue("appVer")
    if app_version == nil then
        app_version = 0
    end
    local list = get_wifi_settings(app_version)
    luci.http.write(list)
end

function ww_get_wifi_settings(app_version)
    local list = get_wifi_settings(app_version)
    --return data_to_json(list)
    return list
end

function get_wifi_settings(app_version)
    	local network = require "luci.model.network".init()
    	local info_list = {}
    	local wifis = wifi_networks()
    	for i,wifi_net in ipairs(wifis) do
        	local item = {}
        	local index = 1

	    	if wifi_net.device == "mt7628" then
	        	local wifi_net_wl0 = network:get_wifinet('mt7628.network1')
		    	item["ssid"] = wifi_net_wl0:get("ssid")
			if wifi_net_wl0:get("disabled") == "1" then
                		item["status"] = "false"
			else
                		item["status"] = "true"
			end
		end

	    	if wifi_net.device == "mt7610e" then
	        	local wifi_net_wl1 = network:get_wifinet('mt7610e.network1')
			item["ssid"] = wifi_net_wl1:get("ssid")
		    	if wifi_net_wl1:get("disabled") == "1" then
                		item["status"] = "false"
			else
                		item["status"] = "true"
			end
		end

        	local encryption = wifi_net.networks[index].encryption_src
        	local key = wifi_net.networks[index].key
        	if encryption == "wep-open" then
            		key = wifi_net.networks[index].key1
            		if key:len()>4 and key:sub(0,2)=="s:" then
                		key = key:sub(3)
            		end
        	end
        	local name = "wl0";
        	if "rai0" == wifi_net.networks[index].ifname then
        		name = "wl1"
        	end
        	item["name"] = name
		--item["ssid"] = wifi_net.networks[index].ssid
		if key == nil then
	   		key = ""
		end
        	item["password"] = key
        	item["encryption"] = encryption
        	info_list[#wifis+1-i] = item
    	end
    	require "MZLog".log(3, debug.getinfo(1).currentline)
    	require "MZLog".log(3, info_list)
    	if tonumber(app_version) >= 5 then
        	return b64enc(cjson.encode(info_list))
    	else
        	return cjson.encode(info_list)
    	end
end

function get_wifi_ssids()
	local wifis = wifi_networks()
	local ssid1 = ""
	local ssid2 = ""
	for i, wifi_net in ipairs(wifis) do
		if i == 1 then
			ssid1 = wifi_net.networks[1].ssid
		end
		if i == 2 then
			ssid2 = wifi_net.networks[1].ssid
		end
    end

    return ssid1, ssid2
end

function nw_wifi_settings()

    	luci.http.prepare_content("application/json")
    	local data = luci.http.formvalue("data")
    	local app_version = luci.http.formvalue("appVer")
    	if app_version == nil then
        	app_version = 0
    	end
    	local switch_2g = nil
    	local switch_5g = nil
    	local ssid_2g = nil
    	local ssid_5g = nil
    	local pwd_2g = nil
    	local pwd_5g = nil
    	local encry_2g = nil
    	local encry_5g = nil

    	if tonumber(app_version) >= 5 then
        	local data = b64dec(data)
	    	data = cjson.decode(data)

        	for k, v in pairs(data) do
            		if v.name == "wl0" then
        			switch_2g = v.on
        			ssid_2g = v.ssid
        			pwd_2g = v.pwd
        			encry_2g = v.encryption
        			require "MZLog".log(3, debug.getinfo(1).currentline)
        			require "MZLog".log(3, debug.getinfo(1).currentline)
    			elseif v.name == "wl1" then
                		switch_5g = v.on
                		ssid_5g = v.ssid
                		pwd_5g = v.pwd
                		encry_5g = v.encryption
            		end
        	end
    	else
		switch_2g = luci.http.formvalue("on1")
		ssid_2g = luci.http.formvalue("ssid1")
		pwd_2g = luci.http.formvalue("pwd1")
		encry_2g = luci.http.formvalue("encryption1")

		switch_5g = luci.http.formvalue("on2")
		ssid_5g = luci.http.formvalue("ssid2")
		pwd_5g = luci.http.formvalue("pwd2")
		encry_5g = luci.http.formvalue("encryption2")

    	end

	dbfs.init_ssid_table()
	local dbssid = dbfs.fetch_ssid()
	if nil == dbssid or #dbssid == 0 then
		dbfs.add_ssid(ssid_2g, ssid_5g)
	else
		dbfs.update_ssid(ssid_2g, ssid_5g)
	end

    	local res = wifi_settings(switch_2g, ssid_2g, pwd_2g, encry_2g, switch_5g, ssid_5g, pwd_5g, encry_5g)
    	luci.http.write_json(res)
end

function wifi_settings(on1, ssid1, pwd1, encry1, on2, ssid2, pwd2, encry2)
    	local result = {}
	local res = {}
    	local code = 0
    	local code1 = 0
    	local code2 = 0
    	local details = {}
    	local code1 = check_ssid(ssid1, 31)
    	local code2 = check_ssid(ssid2, 31)
    	local succeed1 = false
    	local succeed2 = false
    	if on1 == "false" then
	    	require "MZLog".log(3, debug.getinfo(1).currentline)
        	succeed1 = set_wifi_basic_info(1, nil, nil, nil, on1)
    	else
        	if code1 == 1 then
            		succeed1 = set_wifi_basic_info(1, ssid1, pwd1, encry1, on1)
        	end
    	end

    	if on2 == "false" then
        	succeed2 = set_wifi_basic_info(2, nil, nil, nil, on2)
    	else
        	if code2 == 1 then
            		succeed2 = set_wifi_basic_info(2, ssid2, pwd2, encry2, on2)
        	end
    	end

	if code == 0 and code1 ~= 0 and code2 ~= 0 and succeed1 ~= false and succeed2 ~= false then
		res["result"] = true
            	fork_restart_network()
	else
		res["result"] = false
    	end

	return res
end

function set_wifi_basic_info(wifi_index, ssid, password, encryption, on)

    local network = require "luci.model.network".init()
    if wifi_index == 1 then
	    wifi_net = network:get_wifinet('mt7628.network1')
	    wifi_dev = network:get_wifidev('mt7628')
    end

    if wifi_index == 2 then
	    wifi_net = network:get_wifinet('mt7610e.network1')
	    wifi_dev = network:get_wifidev('mt7610e')
    end

    if wifi_net == nil then
        return false
    end

    if wifi_dev then
        if on == "true" then
            wifi_net:set("disabled", "0")
        elseif on == "false" then
            wifi_net:set("disabled", "1")
        end
    end

    if not is_str_nil(ssid) and check_ssid(ssid) then
        wifi_net:set("ssid",ssid)
    end

    local code = check_wifi_passwd(password,encryption)
    if code == 0 then
        wifi_net:set("encryption",encryption)
        wifi_net:set("key",password)
        if encryption == "none" then
            wifi_net:set("key","")
        elseif encryption == "wep-open" then
            wifi_net:set("key1","s:"..password)
            wifi_net:set("key",1)
        end
    elseif code > 1502 then
        return false
    end

    network:save("wireless")
    network:commit("wireless")
    return true
end

function is_str_nil(str)
    return (str == nil or str == "")
end

function check_ssid(ssid)
    if is_str_nil(ssid) then
        return 0
    end

	return 1
end

function check_wifi_passwd(passwd,encryption)
    if is_str_nil(encryption) or (encryption and encryption ~= "none" and is_str_nil(passwd)) then
        return 1502
    end
    if encryption == "psk" or encryption == "psk2" then
        if  passwd:len() < 8 then
            return 1520
        end
    elseif encryption == "mixed-psk" then
        if  passwd:len()<8 or passwd:len()>63 then
            return 1521
        end
    elseif encryption == "wep-open" then
        if  passwd:len()~=5 and passwd:len()~=13 then
            return 1522
        end
    end
    return 0
end
function fork_smart_wifi_shutdown(wnet, close_time, open_time)
    local close_interval = close_time - os.time()
    local open_interval = open_time - os.time()

    local cmd = string.format("/sbin/wifi down "..wnet.."; sleep 15; /sbin/wifi up "..wnet)                                                                                                 
    --local cmd = string.format("sleep %s; /sbin/wifi down; sleep %s; /sbin/wifi up;", tostring(close_interval), tostring(open_interval))                                                   
    --local cmd = "/sbin/wifi "..switch.." "..wnet  
    exec_cmd_in_sh(cmd)
end
    --[[
    return coroutine.create(function()
        --do close
        while true do
            if os.time() ~= close_time then
                posix.sleep(1)
            else
                wifi_shutdown(wnet)
                table["close"] = true
                luci.http.write_json(table)
                break
            end
        end
        --do restart
        while true do
            if os.time() ~= restart_time then
                posix.sleep(1)
            else
                wifi_reconnect(wnet)
                table["restart"] = true
                luci.http.write_json(table)
                break
            end
        end
    )
    --require "MZLog".log(3, debug.getinfo(1).currentline)
    ]]--


function fork_restart_wifi()
	local cmd = "sleep 1; /sbin/wifi >/dev/null 2>/dev/null;"
    exec_cmd_in_sh(cmd)
end

function fork_restart_network()
	local cmd = "/etc/init.d/network restart"
    exec_cmd_in_sh(cmd)
end

function get_lan_ip()
    local uci = require("luci.model.uci").cursor()
    local lan = uci:get_all("network", "lan")
    return lan.ipaddr
end

--[[
--function:     定时wifi开关
--author：      rh_Jameson
--]]--
--wifi重连 &开关基础函数
local function wifi_reconnect_shutdown(shutdown, wnet)
	local netmd = require "luci.model.network".init()
	local net = netmd:get_wifinet(wnet)
	local dev = net:get_device()
	if dev and net then
		dev:set("disabled", nil)
		net:set("disabled", shutdown and 1 or nil)
		netmd:commit("wireless")

		luci.sys.call("env -i /bin/ubus call network reload >/dev/null 2>/dev/null")

		luci.sys.call("env -i /sbin/wifi reload >/dev/null 2>/dev/null")

		--luci.http.status(200, shutdown and "Shutdown" or "Reconnected")

		return
	end

	--luci.http.status(404, "No such radio")
end
--wifi重连
function wifi_reconnect(wnet)
    wifi_reconnect_shutdown(false, wnet)
end
--wifi关闭
function wifi_shutdown(wnet)
	wifi_reconnect_shutdown(true, wnet)
end


--function:     定时wifi开关shell形式
--author：      rh_Jameson
function smart_wifi_shutdown()
    local wnet = 'mt7628.network1'
    local info = {}

    --get para
    --close_time = luci.http.formvalue("close_time")
    --open_time = luci.http.formvalue("open_time")
    
    --test normal    
    close_time = os.time() + 5
    restart_time = os.time() + 10
    
    --test exception
    --close_time = os.time() - 5
    --restart_time = os.time() - 10

    --para err manage
    if close_time < os.time() or restart_time < close_time then
        info["SUCCESS"] = false
    else
        info["SUCCESS"] = true
    end
    fork_smart_wifi_shutdown(wnet, close_time, restart_time)
    luci.http.write_json(info)  
end


--[[
--function:     定时wifi开关
--author：      rh_Jameson
function smart_wifi_shutdown()
    local wnet = 'mt7628.network1'
    local table = {}
    
    --get para
    --local close_time = luci.http.formvalue("close_time")
    --local open_time = luci.http.formvalue("open_time")
    
    --test normal    
    --local close_time = os.time() + 5
    --local restart_time = os.time() + 10
    
    --test exception
    local close_time = os.time() - 5
    local restart_time = os.time() - 10

    --para err manage
    if close_time < os.time() or restart_time < close_time then
        table["err"] = true
        luci.http.write_json(table)
        return
    end
    --do close
    while true do
        if os.time() ~= close_time then
            posix.sleep(1)
        else
            wifi_shutdown(wnet)
            table["close"] = true
            luci.http.write_json(table)
            break
        end
    end

    --do restart
    while true do
        if os.time() ~= restart_time then
            posix.sleep(1)
        else
            wifi_reconnect(wnet)
            table["restart"] = true
            luci.http.write_json(table)
            break
        end
    end
end

--]]--




function macFormat(mac)
    if mac then
        return string.upper(string.gsub(mac, "-", ":"))
    else
        return ""
    end
end

function getAllWifiConnetDeviceDict()
    local result = {}
    for index = 1,2 do
        local wifilist = getWifiConnectDeviceList(index)
        for _, mactime in pairs(wifilist) do
            local item = {}
            item["wifiIndex"] = index
            item["time"] = mactime["time"]
            item["rx"] = mactime["rx"]
            item["tx"] = mactime["tx"]
            result[macFormat(mactime["mac"])] = item
        end
    end
    return result
end

function getWifiConnectDeviceList(wifiIndex)
	local dlist = {}
	local macfile = nil
    	local uci = require("luci.model.uci").cursor()
    	if tonumber(wifiIndex) == 1 then
    		local disable_2g = uci.get("wireless", "mt7628iface", "disabled")
    		if "1" ~= disable_2g then
	    		local cmd = [[iwpriv ra0 get_mac_table]]
	    		macfile = io.popen(cmd)
    		end
    	elseif tonumber(wifiIndex) == 2 then
        	local disable_5g = uci.get("wireless", "mt7610eiface", "disabled")
    		if "1" ~= disable_5g then
	    		local cmd = [[iwpriv rai0 get_mac_table]]
	    		macfile = io.popen(cmd)
    		end
    	end
    	local tmplinenumber = 0
    	if nil ~= macfile then
    		for line in macfile:lines() do
    			if 0 ~= tmplinenumber and "" ~= line then
    				local item = {}
    				local mactime = strsplit(line, " ")
    				item["mac"] = macFormat(mactime[1])
    				item["time"] = mactime[2]
    				item["rx"] = mactime[3]
    				item["tx"] = mactime[4]
    				table.insert(dlist,item)
    			end
    			tmplinenumber = tmplinenumber + 1
		end
    	end
    	return dlist
end

function getDHCPLists()
    local NixioFs = require("nixio.fs")
    local LuciUci = require("luci.model.uci")
    local uci =  LuciUci.cursor()
    local result = {}
    local leasefile = "/var/dhcp.leases"
    uci:foreach("dhcp", "dnsmasq",
    function(s)
        if s.leasefile and NixioFs.access(s.leasefile) then
            leasefile = s.leasefile
            return false
        end
    end)
    local dhcp = io.open(leasefile, "r")
    if dhcp then
        for line in dhcp:lines() do
            if line then
                local ts, mac, ip, name = line:match("^(%d+) (%S+) (%S+) (%S+)")
                if name == "*" then
                    name = ""
                end
                if ts and mac and ip and name then
                    result[#result+1] = {
                        mac  = macFormat(mac),
                        ip   = ip,
                        name = name,
			sp = 0
                    }
                end
            end
        end
        dhcp:close()
        return result
    else
        return false
    end
end

function getDHCPDict()
    local dhcpDict = {}
    local dhcpList = getDHCPLists()
    for _,value in ipairs(dhcpList) do
        dhcpDict[value.mac] = value
    end
    return dhcpDict
end

function getDHCPIpDicts()
    local dhcpDict = {}
    local dhcpList = getDHCPLists()
    for _,value in ipairs(dhcpList) do
        dhcpDict[value.ip] = value
    end
    return dhcpDict
end

function getDeviceInfoFromDB()
    local result = {}
    local deviceList = dbfs.fetchAllDeviceInfo()
    if #deviceList > 0 then
        for _, device in ipairs(deviceList) do
            result[device.mac] = device
        end
    end
    return result
end

function is_device_online(ip)
	local lu = require("luci.util")
	local cmd = "ping -W 2 -c 1 " .. ip .. " > /dev/null ;echo -n $?"
	local pingresult = lu.exec(cmd)

	local res = nil
	if pingresult == "0" then
		res = true
	else
		res = false

	end

	return res
end

function get_connect_device_list_router()
	local devicelist = {}
	dbfs.init_arp_table()
	local dbarp = dbfs.fetch_all_arp()
	
	local deviceDBDict = getDeviceInfoFromDB()
	local dhcpDeviceDict = getDHCPDict()
	local blacklist0 = dbfs.fetch_all_deny_mac()
	local wifiDeviceDict = getAllWifiConnetDeviceDict()
	
	local cmd = [[cat /proc/net/arp |grep br-lan|awk '{print $1","$4}']]
	local ipmacs = {}
	local devs = lue(cmd)
	if devs ~= "" then
		local ipmacstr = strsplit(devs, '\n')
		ipmacstr[#ipmacstr] = nil
		for k, v in pairs(ipmacstr) do
			local ipmac = strsplit(v, ',')
			ipmacs[string.upper(ipmac[2])] = ipmac
		end
	end
	
	for k, v in pairs(dbarp) do
		local item = {}
		local mac = string.upper(v["mac"])
		if "00:00:00:00:00:00" ~= mac then
			local devflag = false
			local im = ipmacs[mac]
			local wf = v["wifi"]
			if wf == 0 then
				item["type"] = "wire"
			elseif wf == 1 then
				item["type"] = "2.4G"
			elseif wf == 2 then
				item["type"] = "5G"
			end
			if nil ~= im then
				item["ip"] = im[1]
				item["mac"] = string.upper(im[2])
			else
				item["ip"] = v["ip"]
				item["mac"] = mac
			end
			
			local deviceDB = deviceDBDict[mac]
			local dhcpinfo = dhcpDeviceDict[mac]
			if deviceDB ~= nil then
				item["devicename"] = deviceDB.orgname
				if deviceDB.devicename ~= '' then
					item["devicename"] = deviceDB.devicename
				end
			elseif dhcpinfo ~= nil then
				item["devicename"] = dhcpinfo.name
				dbfs.saveDeviceInfo(mac, dhcpinfo.name, "", dhcpinfo.ip)
			else
				item["devicename"] = "unknown"
			end
			
			local isblack = false
			if nil ~= blacklist0 then
				for k, v in pairs(blacklist0) do
					if v.mac == mac then
						isblack = true
					end
				end
			end
			if isblack == false then
				item["enable"] = true
			else
				item["enable"] = false
			end
			
			local wifi = wifiDeviceDict[mac]
			
			item["online"] = false
			if wf == 0 and nil ~= im then
				item["online"] = true
			end
			if wifi ~= nil then
				item["online"] = true
				if wifi.wifiIndex == 1 then
					item["type"] = "2.4G"
				elseif wifi.wifiIndex == 2 then
					item["type"] = "5G"
				end
			end
			if nil == item["type"] or false == item["online"] then
				item["type"] = "unknown"
			end
			table.insert(devicelist,item)
		end
	end
	return devicelist
end

function get_connect_device_list()
	local LuciNetwork = require("luci.model.network").init()
	local lanNetwork = LuciNetwork:get_network("lan")
	local apc = lanNetwork:get_option_value("apclient")
	local devicelist = {}
        if nil == apc or "" == apc then
		--mode router
		devicelist = get_connect_device_list_router()
	else
		--mode apclient
		if "mt7628" == apc then
		
		elseif "mt7610e" == apc then
			
		end
		devicelist = get_connect_device_list_router()
        end
        return devicelist
end

function ww_get_connect_device_list()
    local result = get_connect_device_list()
    return result
end

function nw_get_connect_device_list()
    luci.http.prepare_content("application/json")
    local result = get_connect_device_list()
    luci.http.write_json(result)
end

function nw_set_device_name()
    local mac = luci.http.formvalue("mac")
    local devicename = luci.http.formvalue("devicename")
    set_device_name(mac,devicename)
end

function set_device_name(mac,devicename)
    local code = 0
    local deviceList = {}

    if is_str_nil(mac) or is_str_nil(devicename) then
        code = 1502
    else
        code = save_device_name(mac,devicename)
    end
    return code
end

function save_device_name(mac,name)
    local code = 0
    local code = dbfs.updateDeviceNickname(macFormat(mac),name)
    if code == 0 then
        return true
    else
        return false
    end
end

function set_wan_switch(mac, mode, enable)
	local result = {}
    	local code = false
    	if is_str_nil(mac) then
        	return
    	else
        	mac = macFormat(mac)
        	enable = tonumber(enable)
    	end
    	
    	local dbdenymac = dbfs.fetch_all_deny_mac()
    	local macstr = ""
    	local deny = false
	if enable == 0 then
		if nil == dbdenymac then
			dbfs.add_deny_mac(mac)
			macstr = mac
		else
			if #dbdenymac >= 60 then
				return "black person reach max"
			end
			for _, macaddr in ipairs(dbdenymac) do
				if mac == macaddr.mac then
					return "same black"
				else
					macstr = macstr.." "..macaddr.mac
				end
			end
			dbfs.add_deny_mac(mac)
			macstr = macstr.." "..mac
		end
	else
		if nil == dbdenymac then
			return
		end
		for _, macaddr in ipairs(dbdenymac) do
			if mac == macaddr.mac then
				dbfs.delete_deny_mac(mac)
				if #dbdenymac == 1 then
					deny = true
				end
			else
				macstr = macstr.." "..macaddr.mac
			end
		end
	end
	local cmd = [[wireless-ban.sh ]]
	if deny == true then
		cmd = cmd.."none"
	else
		cmd = cmd.."deny "..macstr
	end
	exec_cmd_in_sh(cmd)
	code = true
	result["result"] = code

	return result
end

function nw_set_wan_switch()
    local result = {}
    local code = false
    local mac = luci.http.formvalue("mac")
    local mode = luci.http.formvalue("mode")
    local enable = luci.http.formvalue("enable")

    code = set_wan_switch(mac,mode,enable)
    result["result"] = code
    luci.http.write_json(result)
end

function getNetConnect(ip)
	local sys = require "luci.sys"
	local res = {}
	res["bytes"] = 0
	local conn = sys.net.conntrack()
	    require "MZLog".log(3, debug.getinfo(1).currentline)
	    require "MZLog".log(3, debug.getinfo(1).currentline)
	    require "MZLog".log(3, conn)
	for _, value in pairs(conn) do
	    if value.src == ip then
		    res["bytes"] = value.bytes + res["bytes"]
		end
	end
	return res
end

function get_net_device()
    local ntm = require "luci.model.network".init()
    local dev
    local devices = { }
    for _, dev in luci.util.vspairs(luci.sys.net.devices()) do
	    if dev ~= "lo" and not ntm:ignore_interface(dev) then
			devices[#devices+1] = dev
	    end
    end
    local curdev = luci.dispatcher.context.requestpath
    curdev = curdev[#curdev] ~= "bandwidth" and curdev[#curdev] or devices[1]
    luci.http.write_json(devices)
end

function nw_check_sys_password()
    local password = luci.http.formvalue("password")
    check_sys_password(password)
end

function check_sys_password(password)
    local result={}
    if not is_str_nil(password) then
        local check = check_sys_pwd(password)
        if check then
           code = true
        else
           code = false
        end
    end
    result["result"] = code
    luci.http.write_json(result)
end

function get_wan_type()
    	local LuciNetwork = require("luci.model.network").init()
    	local lanNetwork = LuciNetwork:get_network("lan")
	local wanNetwork = LuciNetwork:get_network("wan")
   	local wanDetails = {}
	if wanNetwork and lanNetwork then
		local apc = lanNetwork:get_option_value("apclient")
		if nil ~= apc and "" ~= apc then
			wanDetails["type"] = "apclient"
			local uci = require("luci.model.uci").cursor()
			local ssid = uci.get("wireless", apc, "apclissid")
			wanDetails["ssid"] = ssid
		else
        		local wanType = wanNetwork:proto()

	      		if wanType == "static" then
            			wanDetails["ipaddr"] = wanNetwork:get_option_value("ipaddr")
	            		wanDetails["netmask"] = wanNetwork:get_option_value("netmask")
        	    		wanDetails["gateway"] = wanNetwork:get_option_value("gateway")
        		elseif wanType == "pppoe" then
	            		wanDetails["type"] = "pppoe"
		            	wanDetails["pppoeName"] = wanNetwork:get_option_value("username")
        	    		wanDetails["pppoePwd"] = wanNetwork:get_option_value("password")
        		elseif wanType == "dhcp" then
            			wanDetails["type"] = "dhcp"
	            		wanDetails["pppoeName"] = ""
        	    		wanDetails["pppoePwd"] = ""
        		end
		end
		return wanDetails
    	else
        	return nil
    	end
end

function ww_get_wan_type()
    local result = get_wan_type()
    return data_to_json(result)
end

function nw_get_wan_type()
    local result = get_wan_type()
    luci.http.write_json(result)
end
function nw_set_wan_type()
    	local wan_type = luci.http.formvalue("type")
    	if "apclient" == wan_type then
    		local channel  = luci.http.formvalue("channel")
		local ssid     = luci.http.formvalue("ssid")
		local mac      = luci.http.formvalue("mac")
		local sec      = luci.http.formvalue("sec")
		local extch    = luci.http.formvalue("extch")
		local wl_type_val = luci.http.formvalue("aptype")
		local key = luci.http.formvalue("key")
		set_ap_client(channel, ssid, mac, sec, extch, wl_type_val, key)
    	else
	    	local pppoe_name = luci.http.formvalue("pppoeName")
		local pppoe_pwd = luci.http.formvalue("pppoePwd")
		set_wan_type(wan_type, pppoe_name, pppoe_pwd)
	end
end

function set_wan_type(wan_type, pppoe_name, pppoe_pwd)
	local result = {}
        local code = true
        local needRestartWifi = false
        if not is_str_nil(wan_type) then
                local succeed
                if wan_type == "pppoe" and not is_str_nil(pppoe_name) and not is_str_nil(pppoe_pwd) then
                        succeed = setWanPPPoE(pppoe_name, pppoe_pwd)
                elseif wan_type == "dhcp" then
                        succeed = setWanDHCP()
                end
                if not succeed then
                        code = false
                else
			cancelapclient()
                        needRestartWifi = true
                end
        end
        result["result"] = code
        local dbssid = dbfs.fetch_ssid()
	if nil ~= dbssid then
		for k, v in pairs(dbssid) do
			result["ssid"] = v.ssid24
		end
	end
        local macaddrcmd = "eth_mac r wl0"
	local macaddr = lue(macaddrcmd)
	if macaddr ~= "" then
		local ipmacstr = strsplit(macaddr, '\n')
		ipmacstr[#ipmacstr] = nil
		for k, v in pairs(ipmacstr) do
			result["mac"] = v
		end
	end
        luci.http.write_json(result)

        if needRestartWifi then
                cmd = [[sleep 1;apcli_connect.sh restart &]]
                exec_cmd_in_sh(cmd)
                --fork_restart_network()
        end
        luci.http.close()
end

function set_ap_client(channel, ssid, mac, sec, extch, wl_type_val, key)
	local sec_alg = "TKIP"
	if string.find(sec, "AES") then
		sec_alg = "AES"
	end
	if string.find(sec, "WPA2") then
		sec = "WPA2PSK"
	else
		if string.find(sec, "WPA") then
			sec = "WPA1PSK"
		else
			sec = "WEP"
		end
	end
	local wl_type = "ra0"
	if wl_type_val == "5G" then
		wl_type = "rai0"
	end
	local cmd = [[apcli_connect.sh ]]..wl_type.." "..channel.." "..ssid.." "
	cmd = cmd..sec.." "..sec_alg.." "..key
	lue(cmd)
	require "MZLog".log(3, cmd)
	local ret = {}
	ret["result"] = true
        local ssid5,ssid2 = get_wifi_ssids()
        local macaddrcmd = ""
        if wl_type_val == "2.4G" then
		ret["ssid"] = ssid2
		macaddrcmd = "eth_mac r wl0"
	else
		ret["ssid"] = ssid5
		macaddrcmd = "eth_mac r wl1"
	end
	local macaddr = lue(macaddrcmd)
	if macaddr ~= "" then
		local ipmacstr = strsplit(macaddr, '\n')
		ipmacstr[#ipmacstr] = nil
		for k, v in pairs(ipmacstr) do
			ret["mac"] = v
		end
	end
	luci.http.prepare_content("application/json")
	luci.http.write_json(ret)
	local cmd = [[sleep 1;apcli_connect.sh restart &]]
	exec_cmd_in_sh(cmd)
	luci.http.close()
end

function cancelapclient()
	local LuciNetwork = require("luci.model.network").init()
	local lanNetwork = LuciNetwork:get_network("lan")
	local apc = lanNetwork:get_option_value("apclient")
        if nil ~= apc then
        	local dbssid = dbfs.fetch_ssid()
        	local ssid_2g = ""
        	local ssid_5g = ""
        	if nil ~= dbssid then
        		for k, v in pairs(dbssid) do
        			ssid_2g = v.ssid24
        			ssid_5g = v.ssid5
        		end
        	end
                local cmd = [[apcli_connect.sh disable]]
                if "" ~= ssid_2g and "" ~= ssid_5g then
                	cmd = cmd.." "..ssid_2g.." "..ssid_5g
                end
                lue(cmd)
        end
end

function setWanPPPoE(name, password)
    local LuciNetwork = require("luci.model.network").init()
    local uci = require("luci.model.uci").cursor()
    local iface = "wan"
    local ifname = getWanEth()
    local oldconf = uci:get_all("network", "wan") or {}
    local wanrestart = true
    if oldconf.username == name and oldconf.password == password then
        wanrestart = false
    end
    local wanNet = LuciNetwork:del_network(iface)
	local mtuvalue = 1480
    wanNet = LuciNetwork:add_network(
        iface, {
            proto    ="pppoe",
            ifname   = ifname,
            username = name,
            password = password,
            mtu      = mtuvalue
		})
	if wanNet then
        LuciNetwork:save("network")
        LuciNetwork:commit("network")
		if wanrestart then
			wanRestart()
		end
        return true
	else
        return false
    end
end

function setWanDHCP()
    local LuciNetwork = require("luci.model.network").init()
    local uci = require("luci.model.uci").cursor()
    local oldconf = uci:get_all("network", "wan") or {}
    local iface = "wan"
    local ifname = getWanEth()
    local wanrestart = true

    local wanNet = LuciNetwork:del_network(iface)
    if oldconf.proto == "dhcp" then
            wanrestart = false
    end
    local network = {
            proto   = "dhcp",
            ifname  = ifname
        }
    wanNet = LuciNetwork:add_network(iface, network)
    if wanNet then
        LuciNetwork:save("network")
        LuciNetwork:commit("network")
        if wanrestart then
            wanRestart()
        end
        return true
    else
        return false
    end
end

function check_sys_pwd(oldPassword)
    local LuciSys = require("luci.sys")
    return LuciSys.user.checkpasswd("root", oldPassword)
end

function getWanEth()
    local LuciNetwork = require("luci.model.network").init()
    local wanNetwork = LuciNetwork:get_network("wan")
    return wanNetwork:get_option_value("ifname")
end

function wanRestart()
	local LuciUtil = require("luci.util")
	LuciUtil.exec("env -i /sbin/ifup wan")
end

function netspeed_channel(cmd)
	local speed_table = {}
	local speed_file = io.popen(cmd)
	for line in speed_file:lines() do
		table.insert(speed_table, line)
	end
	speed_file:close()
	
	local rx = 0
	local tx = 0
	local speed, tb, rxb, txb
	local n = 0
	for k, v in ipairs(speed_table) do
		speed = strsplit(v, ',')
		if (k == 1) then
			tb  = tonumber(speed[1])
			rxb = tonumber(speed[3])
			txb = tonumber(speed[2])
		else
			n = n + 1
			local tmp
			local td
			tmp = tonumber(speed[1])
			td = tmp - tb
			tb = tmp

			tmp = tonumber(speed[3])
			rx = rx + (tmp - rxb) / td
			rxb = tmp

			tmp = tonumber(speed[2])
			tx = tx + (tmp - txb) / td
			txb = tmp
		end
	end
    	rx = string.format("%6.2f", rx/n)
	tx = string.format("%6.2f", tx/n)
	return rx, tx
end

function real_time_net_speed()
	local res = {}
	--local cmd = [[luci-bwc -i eth0.2|tail -n 5|sed -e 's#.*\[\s*\(.*\)\s*\].*#\1#']]
	local uci = require("luci.model.uci").cursor()
        local apclient = uci.get("network", "lan", "apclient")

	local cmd = nil
	local rx = 0
	local tx = 0
	if nil == apclient then
		cmd = [[luci-bwc -i eth0.2 |tail -n 5 |sed -e 's#.*\[\s*\(.*\)\s*\].*#\1#']]
		rx, tx = netspeed_channel(cmd)		
	else
		cmd = [[luci-bwc -i eth0.1 |tail -n 5 |sed -e 's#.*\[\s*\(.*\)\s*\].*#\1#']]
		rx, tx = netspeed_channel(cmd)
		cmd = [[luci-bwc -i ra0 |tail -n 5 |sed -e 's#.*\[\s*\(.*\)\s*\].*#\1#']]
		local tmprx, tmptx = netspeed_channel(cmd)
		rx = rx + tmprx
		tx = tx + tmptx
		cmd = [[luci-bwc -i rai0 |tail -n 5 |sed -e 's#.*\[\s*\(.*\)\s*\].*#\1#']]
		tmprx, tmptx = netspeed_channel(cmd)
		rx = rx + tmprx
		tx = tx + tmptx
	end

    	res["rx"] = rx
	res["tx"] = tx

    	return res
end

function get_device_details(mac)
    	dbfs.change_maclist_table()
	local item = {}
	mac = string.upper(mac)
	local deviceDBDict = getDeviceInfoFromDB()
	local dhcpDeviceDict = getDHCPDict()
	local blacklist0 = dbfs.fetch_all_deny_mac()
	local wifiDeviceDict = getAllWifiConnetDeviceDict()
	
	local cmd = [[cat /proc/net/arp |grep br-lan|awk '{print $1","$4}']]
	local ipmacs = {}
	local devs = lue(cmd)
	if devs ~= "" then
		local ipmacstr = strsplit(devs, '\n')
		ipmacstr[#ipmacstr] = nil
		for k, v in pairs(ipmacstr) do
			local ipmac = strsplit(v, ',')
			ipmacs[string.upper(ipmac[2])] = ipmac
		end
	end

	item["diskaccess"] = false
	item["speed"] = 0
	item["upload"] = 0
	item["download"] = 0
	item["time"] = 0

	local isblack = false
	if nil ~= blacklist0 then
		for k, v in pairs(blacklist0) do
			if v.mac == mac then
				isblack = true
			end
		end
	end
	if isblack == false then
		item["enable"] = true
	else
		item["enable"] = false
	end

	local dbarp = dbfs.fetch_arp(mac)
	if nil ~= dbarp then
		for k, v in pairs(dbarp) do
			local im = ipmacs[mac]
			local wf = v["wifi"]
			if wf == 0 then
				item["type"] = "wire"
			elseif wf == 1 then
				item["type"] = "2.4G"
			elseif wf == 2 then
				item["type"] = "5G"
			end
			if nil ~= im then
				item["ip"] = im[1]
				item["mac"] = string.upper(im[2])
			else
				item["ip"] = v["ip"]
				item["mac"] = mac
			end
			
			local deviceDB = deviceDBDict[mac]
			local dhcpinfo = dhcpDeviceDict[mac]
			if deviceDB ~= nil then
				item["devicename"] = deviceDB.orgname
				if deviceDB.devicename ~= '' then
					item["devicename"] = deviceDB.devicename
				end
			else
				item["devicename"] = dhcpinfo.name
				dbfs.saveDeviceInfo(mac, dhcpinfo.name, "", dhcpinfo.ip)
			end
			
			local wifi = wifiDeviceDict[mac]
			
			item["online"] = false
			if wf == 0 and nil ~= im then
				item["online"] = true
			end
			if wifi ~= nil then
				item["online"] = true
				local time = wifi.time
				item["time"] = time
				local rx = wifi.rx
				local tx = wifi.tx
				item["upload"] = tx
				item["download"] = rx
				if wifi.wifiIndex == 1 then
					item["type"] = "2.4G"
				elseif wifi.wifiIndex == 2 then
					item["type"] = "5G"
				end
			end
			if nil == item["type"] or false == item["online"] then
				item["type"] = "unknown"
			end
		end
	end

	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, item)
	require "MZLog".log(3, debug.getinfo(1).currentline)

	return item
end

function nw_get_device_details()
	local mac = luci.http.formvalue("mac")
	local res = get_device_details(mac)
	luci.http.write_json(res)
end

function ww_get_device_details(mac)
	local res = get_device_details(mac)
	return res
end

function get_wireless_channel()
	local uci = require("luci.model.uci").cursor()
        local channel = uci.get("wireless", "mt7628", "channel")
        local bw = uci.get("wireless", "mt7628", "bw")
        local ret = {}
        ret["channel"] = channel
        ret["extch"] = bw
        return ret
end

function nw_get_wireless_channel()
        local ret = get_wireless_channel()
        luci.http.write_json(ret)
end

function ww_get_wireless_channel()
        local ret = get_wireless_channel()
        return cjson.encode(ret)
end

function set_wireless_channel(channel, extch)
	local res = false
        local uci = require("luci.model.uci").cursor()
	local c = tonumber(channel)
	
        if c >= 0 and c <= 13 then
		res = uci.set("wireless", "mt7628", "channel", c)
		if extch == "1" or extch == "0" then
                	res = uci.set("wireless", "mt7628", "bw", extch)
                end
        end
        uci.commit("wireless")
	fork_restart_wifi()
        return res
end

function nw_set_wireless_channel(channel, extch)
        local channel = luci.http.formvalue("channel")
        local extch = luci.http.formvalue("extch")
        local res = set_wireless_channel(channel, extch)
        local ret = {}
        ret["result"] = res
        luci.http.write_json(ret)
end

function ww_set_wireless_channel(channel, extch)
        local res = set_wireless_channel(channel, extch)
        local ret = {}
        ret["result"] = res
        return ret
end

function is_wan_connected()
	local lu = require("luci.util")
	local cmd = "ping -W 2 -c 1 www.baidu.com > /dev/null ;echo -n $?"
	local pingresult = lu.exec(cmd)

	local res = {}
	if pingresult == "0" then
		res["result"] = true
	else
		res["result"] = false

	end

	return res
end

function set_bluetooth(id, status)
	require "MZLog".log(3, debug.getinfo(1).currentline)
    local res = {}

	if status == "open" then
	    local cmd = "/root/spi_open" .. id
		lue(cmd)
		res["result"] = "open"
	elseif status == "close" then
	    local cmd = "/root/spi_close" .. id
		lue(cmd)
		res["result"] = "close"
	end
	require "MZLog".log(3, res)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	return res
end

function get_bluetooth_info()
	--local value = luci.http.formvalue("data")
	local value = "010101010101"
	local ret = {}
	require "MZLog".log(3, debug.getinfo(1).currentline)
	local types = string.format("%d", "0x" .. string.sub(value, 1, 2))
	--[[
	if types == "00" then
	end
	]]
	local id = string.format("%d", "0x" .. string.sub(value, 3, 4))
	local status = string.format("%d", "0x" .. string.sub(value, 5, 6))
	local temp = string.format("%d", "0x" .. string.sub(value, 7, 8))
	local rh = string.format("%d", "0x" .. string.sub(value, 9, 10))
	local light = string.format("%d", "0x" .. string.sub(value, 11, 12))

	ret["type"] = types
	ret["id"] = id
	ret["status"] = status
	ret["temp"] = temp
	ret["rh"] = rh
	ret["light"] = light
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, ret)
	return ret
end

function nw_set_bluetooth()
	require "MZLog".log(3, debug.getinfo(1).currentline)
	local id = luci.http.formvalue("id")
	local status = luci.http.formvalue("status")
    local res = set_bluetooth(id, status)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	luci.http.write_json(res)
end

function nw_get_bluetooth_info()
	require "MZLog".log(3, debug.getinfo(1).currentline)
    local res = get_bluetooth_info()
	require "MZLog".log(3, debug.getinfo(1).currentline)
	luci.http.write_json(res)
end

function ww_set_bluetooth()
	require "MZLog".log(3, debug.getinfo(1).currentline)
    local res = set_bluetooth()
	require "MZLog".log(3, debug.getinfo(1).currentline)
	return res
end

function ww_get_bluetooth_info()
	require "MZLog".log(3, debug.getinfo(1).currentline)
    local res = get_bluetooth_info()
	require "MZLog".log(3, debug.getinfo(1).currentline)
	return res
end

------------------------  bluetooth  --------------------

-----------------------  receive data --------------------
function bluetooth_info()
    dbfs.initBluetoothTable()
    local value = luci.http.formvalue("data")
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, value)
    local ret = {}
    local types = string.sub(value, 1, 2)

    if types == "00" then
        local id = string.sub(value, 3, 4)
        local status = string.format("%d", "0x" .. string.sub(value, 5, 6))   
        local temp1 = string.sub(value, 7, 8) 
        local temp2 = string.sub(value, 9, 10)
        local temp = temp2..temp1
        temp = string.format("%d", "0x" .. temp)   
        local rh1 = string.sub(value, 11, 12) 
        local rh2 = string.sub(value, 13, 14)
        local rh = rh2..rh1
        rh = string.format("%d", "0x" .. rh)   
        local light1 = string.sub(value, 15, 16)
        local light2 = string.sub(value, 17, 18) 
        local light = light2..light1
        light = string.format("%d", "0x" .. light)   
        local mac = dbfs.getBluetoothDevice(id)
        local TMP = "/tmp/"..mac.."0"

        ret["mac"] = mac
        ret["id"] = id
        if status == "1" then
            ret["onoff"] = "on"
        else
            ret["onoff"] = "off"
        end
        local timer_id = ""
        local flag = ""
        local start = ""
        local ends = ""
        local fd = io.open(TMP, "r")

        if fd then
            local res = fd:read()
            fd:close()
            res = cjson.decode(res)
            timer_id = res.timerId
            flag = res.flag
            start = res.start
            ends = res.ends
        end

        ret["timerId"] = timer_id
        ret["flag"] = flag
        ret["start"] = start
        ret["ends"] = ends
        ret["temp"] = temp
        ret["hemi"] = rh
        ret["light"] = light
        ret["time"] = os.time()

        local result = data_to_json(ret)
		--[[
        require "MZLog".log(3, debug.getinfo(1).currentline)
        require "MZLog".log(3, id)
        require "MZLog".log(3, result)
        require "MZLog".log(3, ret)
		]]
        local fd = assert(io.open(TMP, "w"))
        fd:write(result)
        fd:close()

    elseif types == "01" then
        local id = string.sub(value, 3, 4)
        local voltage1 = string.sub(value, 5, 6)
        local voltage2 = string.sub(value, 7, 8)  
        local voltage = voltage2..voltage1
        voltage = string.format("%d", "0x" .. voltage)   
        local electricity1 = string.format("%d", "0x" .. string.sub(value, 9, 10))   
        local electricity2 = string.format("%d", "0x" .. string.sub(value, 11, 12))   
        local electricity = electricity2..electricity1
        electricity = string.format("%d", "0x" .. electricity)   
        local power1 = string.sub(value, 13, 14) 
        local power2 = string.sub(value, 15, 16)
        local power = power2..power1
        power = string.format("%d", "0x" .. power)   
        local electric1 = string.sub(value, 17, 18)
        local electric2 = string.sub(value, 19, 20) 
        local electric = electric2..electric1
        electric = string.format("%d", "0x" .. electric)   
        local mac = dbfs.getBluetoothDevice(id)
        local TMP = "/tmp/"..mac.."1"
        local timer_id = ""
        local fd = io.open(TMP, "r")
        if fd then
            local res = fd:read()
            fd:close()
            res = cjson.decode(res)
            timer_id = res.timerId
        end

        ret["timerId"] = timer_id
        ret["mac"] = mac
        ret["id"] = id
        ret["voltage"] = voltage  
        ret["current"] = electricity 
        ret["power"] = power 
        ret["energy"] = electric 

        local result = data_to_json(ret)
        local fd = assert(io.open(TMP, "w"))
        fd:write(result)
        fd:close()
    
    elseif types == "02" then
        require "MZLog".log(3, debug.getinfo(1).currentline)
        local id = string.sub(value, 3, 4)
        local TYPE = string.sub(value, 5, 6)
        local mac1 = string.sub(value, 7, 8)
        local mac2 = string.sub(value, 9, 10)
        local mac3 = string.sub(value, 11, 12)
        local mac4 = string.sub(value, 13, 14)
        local mac5 = string.sub(value, 15, 16)
        local mac6 = string.sub(value, 17, 18)
        local mac = mac6..mac5..mac4..mac3..mac2..mac1

        mac = string.upper(mac)
        local ID = ""
        local res = dbfs.fetchBluetoothDevice(mac)
        require "MZLog".log(3, debug.getinfo(1).currentline)
        require "MZLog".log(3, #res)
        require "MZLog".log(3, res)
        if #res > 0 then
            for k, v in pairs(res) do
                ID = v.id
            end
        end
        if id ~= ID then
            dbfs.updateBluetoothDevice(id, "", "", "", mac)
        end
        local TMP = "/tmp/"..mac.."0"
        local fd = io.open(TMP, "r")

        if fd then
            local res = fd:read()
            fd:close()
            res = cjson.decode(res)
            res["time"] = os.time()
            res = cjson.encode(res)
            local fd = io.open(TMP, "w")
            fd:write(res)
            fd:close()
        end
        require "MZLog".log(3, id)
        require "MZLog".log(3, mac)
        require "MZLog".log(3, debug.getinfo(1).currentline)

    elseif types == "09" then
        local deviceType = string.sub(value, 3, 4)
        local mac1 = string.sub(value, 5, 6)
        local mac2 = string.sub(value, 7, 8)
        local mac3 = string.sub(value, 9, 10)
        local mac4 = string.sub(value, 11, 12)
        local mac5 = string.sub(value, 13, 14)
        local mac6 = string.sub(value, 15, 16)
        local mac = mac6..mac5..mac4..mac3..mac2..mac1
        mac = string.upper(mac)

        local res = dbfs.fetchBluetoothDevice(mac)
        local ret = nil
        local id = nil
        if #res > 0 then
            for k, v in pairs(res) do
                ret = v.mac
                id = v.id
            end
        end
        if id then
            dbfs.updateBluetoothDevice("", "", "", "", mac)
        end
        if ret == nil then
            dbfs.addBluetoothDevice("", mac, "", "", deviceType, "")
            local TMP = "/tmp/"..mac
            local fd = io.open(TMP, "w")
            fd:write(os.time())
            fd:close()
        else
            local TMP = "/tmp/"..mac
            local fd = io.open(TMP, "w")
            fd:write(os.time())
            fd:close()
        end

    elseif types == "04" then
        local data = string.format("%d", "0x" .. string.sub(value, 3, 4))   
        ret["data"] = data

    elseif types == "03" then
        require "MZLog".log(3, debug.getinfo(1).currentline)
        local id = string.sub(value, 3, 4)
        local flag = string.sub(value, 5, 6)   
        local timer_id = string.sub(value, 7, 8)
        local start = string.sub(value, 9, 16)
        local ends = string.sub(value, 17, 24)
        local mac = dbfs.getBluetoothDevice(id)
        local TMP = "/tmp/"..mac.."1"
        local fd = io.open(TMP, "r")

        if fd then
            require "MZLog".log(3, debug.getinfo(1).currentline)
            require "MZLog".log(3, timer_id)
            require "MZLog".log(3, flag)
            local res = fd:read()
            res = cjson.decode(res)
            if timer_id == res["timerId"] then
                local TMP = "/tmp/"..mac.."0"
                local fd = io.open(TMP, "r")
                local res = fd:read()
                res = cjson.decode(res)
                res["flag"] = flag
                res["timerId"] = timer_id
                res["start"] = start
                res["ends"] = ends
                res = cjson.encode(res)
                local fd = io.open(TMP, "w")
                fd:write(res)
                fd:close()
            end
        end

    elseif types == "06" then
        require "MZLog".log(3, debug.getinfo(1).currentline)
        require "MZLog".log(3, debug.getinfo(1).currentline)
        local id = string.sub(value, 3, 4)
        local mac = dbfs.getBluetoothDevice(id)
        local len = tonumber(dbfs.getBleDeviceNameLength(id))
        local str = string.sub(value, 5, len + 4)
        --local res = {}
        --[[
        for i = 1, #str, 2 do
            res[#res + 1] = (tonumber(string.format("%d", "0x"..string.sub(str, i, i+1))))
        end
        ]]
        local device_name = str	
        --[[
		if #res == 1 then
			device_name = string.char(res[1])
		elseif #res == 2 then
			device_name = string.char(res[1], res[2])
		elseif #res == 3 then
			device_name = string.char(res[1], res[2], res[3])
		elseif #res == 4 then
			device_name = string.char(res[1], res[2], res[3], res[4])
		elseif #res == 5 then
			device_name = string.char(res[1], res[2], res[3], res[4], res[5])
		elseif #res == 6 then
			device_name = string.char(res[1], res[2], res[3], res[4], res[5], res[6])
		elseif #res == 7 then
			device_name = string.char(res[1], res[2], res[3], res[4], res[5], res[6], res[7])
		elseif #res == 8 then
			device_name = string.char(res[1], res[2], res[3], res[4], res[5], res[6], res[7], res[8])
		elseif #res == 9 then
			device_name = string.char(res[1], res[2], res[3], res[4], res[5], res[6], res[7], res[8], res[9])
		elseif #res == 10 then
			device_name = string.char(res[1], res[2], res[3], res[4], res[5], res[6], res[7], res[8], res[9], res[10])
		end
	    ]]
        require "MZLog".log(3, debug.getinfo(1).currentline)
        require "MZLog".log(3, str)
        require "MZLog".log(3, device_name)
        dbfs.updateBluetoothDevice(id, "", device_name, len, mac)
        require "MZLog".log(3, debug.getinfo(1).currentline)

    elseif types == "07" then
        local data = string.format("%d", "0x" .. string.sub(value, 3, 4))   
        ret["data"] = data

    elseif types == "0b" then
        local key_ack = string.sub(value, 3, 4)
        local TMP = "/tmp/0b0b"
        local fd = io.open(TMP, "w")
        fd:write(key_ack)
        fd:close()

    elseif types == "0c" then
        local id = string.sub(value, 3, 4)
        local status = string.sub(value, 5, 6)
        local mac = dbfs.getBluetoothDevice(id)
        local TMP = "/tmp/"..mac.."0"
        local fd = io.open(TMP, "r")
        local res = nil
        require "MZLog".log(3, debug.getinfo(1).currentline)

        if fd then
            res = fd:read()
            fd:close()
            res = cjson.decode(res)
            if status == "01" then
                res["onoff"] = "on"
            else
                res["onoff"] = "off"
            end
            res = cjson.encode(res)
            local fd = io.open(TMP, "w")
            fd:write(res)
            fd:close()
        end
        require "MZLog".log(3, res)

    elseif types == "0d" then
        local id = string.sub(value, 3, 4)
        local led_light = string.sub(value, 5, 6)
        local temp1 = string.sub(value, 7, 8) 
        local temp2 = string.sub(value, 9, 10)
        led_light = string.format("%d", "0x" .. led_light)   
        local led_temp = temp2..temp1
        temp = string.format("%d", "0x" .. temp)   

        local TMP = "/tmp/"..mac.."0"
        local fd = io.open(TMP, "r")
        local res = nil
        require "MZLog".log(3, debug.getinfo(1).currentline)

        if fd then
            res = fd:read()
            fd:close()
            res = cjson.decode(res)
			res["led_light"] = led_light
			res["led_temp"] = led_temp
            
			res = cjson.encode(res)
            local fd = io.open(TMP, "w")
            fd:write(res)
            fd:close()
        end

    elseif types == "0e" then
        local id = string.sub(value, 3, 4)
        local wait_time = string.format("%d", "0x" .. string.sub(value, 5, 6))   
        --local mac = dbfs.getBluetoothDevice(id)
        local TMP = "/tmp/0e0e"
        local fd = io.open(TMP, "w")
		fd:write(wait_time)
		fd:close()
		
    elseif types == "10" then
        local mac1 = string.sub(value, 3, 4)
        local mac2 = string.sub(value, 5, 6)
        local mac3 = string.sub(value, 7, 8)
        local mac4 = string.sub(value, 9, 10)
        local mac5 = string.sub(value, 11, 12)
        local mac6 = string.sub(value, 13, 14)
        local mac = mac6..mac5..mac4..mac3..mac2..mac1
        mac = string.upper(mac)

        local res = dbfs.fetchBluetoothDevice(mac)
        if #res == 0 then
            dbfs.addBluetoothDevice("", mac, "0123", "", "", "")
        end
        --[[
        if ret == nil then
            dbfs.addBluetoothDevice("", mac, "", "", deviceType, "")
            local TMP = "/tmp/"..mac
            local fd = io.open(TMP, "w")
            fd:write(os.time())
            fd:close()
        else
            local TMP = "/tmp/"..mac
            local fd = io.open(TMP, "w")
        ]]
	end
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, ret)
    return ret
end

function nw_get_bluetooth_info()
    require "MZLog".log(3, debug.getinfo(1).currentline)
    local res = bluetooth_info()
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_get_bluetooth_info()
    require "MZLog".log(3, debug.getinfo(1).currentline)
    local res = bluetooth_info()
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return res
end

-----------------------  scan_ble_device --------------------
function scan_ble_switch(status)
    local res = {}
    if status == "on" then
        local cmd = "bt_daemon -s ".."16".." 255"  
        lue(cmd)
        require "MZLog".log(3, debug.getinfo(1).currentline)
        require "MZLog".log(3, cmd)
        posix.sleep(1)
        res["result"] = true
    elseif status == "off" then
        local cmd = "bt_daemon -s ".."18".." 255"
        lue(cmd)
        posix.sleep(1)
        res["result"] = false
    end
    return res
end

function nw_scan_ble_switch()
    local status = luci.http.formvalue("status")
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, status)
    local res = scan_ble_switch(status)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_scan_ble_switch(status)
    local res = scan_ble_switch(status)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  get_ble_device_list --------------------
function get_ble_device_list()
    local res = dbfs.fetchAllBluetoothDevice()
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, res)
    local result = {}
    if #res > 0 then
        for k, v in pairs(res) do
            local TMP = "/tmp/"..v.mac
            local fd = io.open(TMP, "r")
            if fd then
                 local time = fd:read()
                fd:close()
                if tonumber(os.time()) - tonumber(time) < 5 then
                    table.insert(result, v)
                end
            end
        end
    end
    return result
end

function nw_get_ble_device_list()
    local res = get_ble_device_list()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    if #res == 0 then
        require "MZLog".log(3, debug.getinfo(1).currentline)
        luci.http.write("[]")
    else
        luci.http.write_json(res)
    end
end

function ww_get_ble_device_list()
    local res = get_ble_device_list()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    if #res == 0 then
        return "[]"
    else
        return cjson.encode(res)
    end
end

-----------------------  add_ble_mesh_device  --------------------
function is_receive_id(mac)
    local id = ""
    local ret = dbfs.fetchBluetoothDevice(mac)
    for k, v in pairs(ret) do
        id = v.id
    end
    return id
end

function add_ble_mesh_device(mac)
    local res = {}
    local id = ""
    local mac1 = string.format("%d", "0x" .. string.sub(mac, 1, 2))   
    local mac2 = string.format("%d", "0x" .. string.sub(mac, 3, 4))   
    local mac3 = string.format("%d", "0x" .. string.sub(mac, 5, 6))   
    local mac4 = string.format("%d", "0x" .. string.sub(mac, 7, 8))   
    local mac5 = string.format("%d", "0x" .. string.sub(mac, 9, 10))   
    local mac6 = string.format("%d", "0x" .. string.sub(mac, 11, 12))   
    local macs = mac6.." "..mac5.." "..mac4.." "..mac3.." "..mac2.." "..mac1

    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, mac)
    local cmd = "bt_daemon -s ".."17 "..macs  
    lue(cmd)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, cmd)

    for i = 1, 20 do
        posix.sleep(1)
        id = is_receive_id(mac)
        if id ~= "" then
            break
        end
    end
    if id ~= "" then
        res["result"] = true
        res["id"] = id
        res["mac"] = mac
    else
        res["result"] = false
        res["mac"] = mac
    end
    return res
end

function nw_add_ble_mesh_device()
    local mac = luci.http.formvalue("mac")
    local res = add_ble_mesh_device(mac)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_add_ble_mesh_device(mac)
    local res = add_ble_mesh_device(mac)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  get_ble_device_detail  --------------------
function get_ble_device_status(mac)
    local TMP1 = "/tmp/"..mac.."0" 
    local TMP2 = "/tmp/"..mac.."1" 
    local fd1 = io.open(TMP1, "r")
    local fd2 = io.open(TMP2, "r")
    if fd1 and fd2 then
        local res1 = fd1:read()
        local res2 = fd2:read()
        fd1:close()
        fd2:close()
        --require "MZLog".log(3, res1)
        --require "MZLog".log(3, res2)
        if res1 ~= nil and res2 ~= nil then
            res1 = cjson.decode(res1)
            res2 = cjson.decode(res2)
            res1["voltage"] = res2.voltage 
            res1["current"] = res2.current
            res1["power"] = res2.power 
            res1["energy"] = res2.energy

            local ret = dbfs.fetchBluetoothDevice(mac)
            local deviceType = nil
            local name = nil
            for k, v in pairs(ret) do
                 deviceType = v.deviceType
                name = v.name
            end

            res1["name"] = name
            res1["type"] = deviceType 
            res1["time"] = nil 
            --require "MZLog".log(3, res1)
            require "MZLog".log(3, debug.getinfo(1).currentline)
        end
        return res1
    else
        return "{}"
    end
end

function nw_get_ble_device_status()
    local mac = luci.http.formvalue("mac")
    local res = get_ble_device_status(mac)
    --require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    if res == "{}" then
        luci.http.write(res)
    else
        luci.http.write_json(res)
    end
end

function ww_get_ble_device_status(mac)
    local res = get_ble_device_status(mac)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    if res == "{}" then
        return res
    else
        return cjson.encode(res)
    end
end

-----------------------  remove_ble_from_mesh  --------------------
function is_remove_ble_from_mesh()
    local res = nil
    local TMP = "/tmp/0e0e" 
    local fd = io.open(TMP, "r")
	if fd then
        local ret = fd:read()
        fd:close()
		if ret ~= "" and ret ~= nil then
    require "MZLog".log(3, debug.getinfo(1).currentline)
			return ret
		else
			return nil 
		end
	else
		return nil
	end
end

function remove_ble_from_mesh(mac)
    local res = {}
    local ret = dbfs.fetchBluetoothDevice(mac)
    local id = nil
    for k, v in pairs(ret) do
        id = v.id
    end
    if id ~= nil and id ~= "" then
        local cmd = "bt_daemon -s ".."3 "..string.format("%d", "0x"..id)   
        lue(cmd)
        local wait_time = nil
        for i = 1, 20 do
            posix.sleep(1)
            wait_time = is_remove_ble_from_mesh(mac)
            if wait_time ~= nil then
                break
            end
        end
        if wait_time then
            res["result"] = true
            res["waitTime"] = wait_time
            res["mac"] = mac
            res["id"] =id
        else
            res["result"] = false
            res["mac"] = mac
            res["id"] = id
        end
    else
        res["result"] = false
        res["mac"] = mac
    end
    return res
end

function nw_remove_ble_from_mesh()
    local mac = luci.http.formvalue("mac")
    local res = remove_ble_from_mesh(mac)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_remove_ble_from_mesh(mac)
    local res = remove_ble_from_mesh(mac)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  get_mesh_device_list --------------------
function is_file_exist(TMP)
    local fd = io.open(TMP, "r")
    if fd then
        return fd
    else
        return false
    end
end

function get_mesh_device_list()
    local result = {}
    dbfs.initBluetoothTable()
    local ret = dbfs.fetchAllBleMeshDevice()
    if #ret > 0 then
        for k, v in pairs(ret) do
            local res = {}
            local TMP = "/tmp/" .. v.mac .."0"
            local fd = nil
            for i = 1, 10 do
                fd = is_file_exist(TMP)
                if fd then
                    break
                else
                    posix.sleep(1)
                end
            end
            if fd then
                local value = fd:read()
                if value ~= nil then
                    value = cjson.decode(value)
                end
                res["mac"] = v.mac
                --res["online"] = true
                res["name"] = v.name
                res["type"] = v.deviceType
                if value["onoff"] == "on" then
                    res["onoff"] = "on"
                else
                    res["onoff"] = "off"
                end

                if tonumber(os.time()) - tonumber(value.time) > 60 then
                    res["online"] = false
                else
                    res["online"] = true
                end

                if res["online"] == false then
                    res = nil
                end
                table.insert(result, res)
                require "MZLog".log(3, debug.getinfo(1).currentline)
            else
                res["mac"] = v.mac
                res["name"] = v.name
                res["type"] = v.deviceType
                res["onoff"] = "off"
                res["online"] = false
                table.insert(result, res)
                require "MZLog".log (3, debug.getinfo(1).currentline)
            end
        end
    end
    return result
end

function nw_get_mesh_device_list()
    local res = get_mesh_device_list()
    --require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    if #res == 0 then
        luci.http.write("[]")
    else
        luci.http.write_json(res)
    end
end

function ww_get_mesh_device_list()
    local res = get_mesh_device_list()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    if #res == 0 then
        return "[]"
    else
        return cjson.encode(res)
    end
end

-----------------------  dismiss_mesh --------------------
function dismiss_mesh()
    local res = {}
    local cmd = "bt_daemon -s ".."3 ".." 255"   
    lue(cmd)
    res["result"] = true
end
function nw_dismiss_mesh()
    local res = dismiss_mesh()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

----------------------- set_mesh_device_attr  --------------------
function is_switch_on(mac)
    local TMP = "/tmp/"..mac.."0"
    local fd = io.open(TMP, "r")
    if fd then
        local res = fd:read()
        fd:close()
        res = cjson.decode(res)
        if res["onoff"] == "on" then
            return true
        else
            return nil
        end
    end
end

function is_switch_off(mac)
    local TMP = "/tmp/"..mac.."0"
    local fd = io.open(TMP, "r")
    if fd then
        local res = fd:read()
        fd:close()
        res = cjson.decode(res)
        if res["onoff"] == "off" then
            return true
        else
            return nil
        end
    end
end

function is_set_name_ok(mac)
    local name = ""
    local ret = dbfs.fetchBluetoothDevice(mac)
    for k, v in pairs(ret) do
        name = v.name
    end
    return name
end

function set_mesh_device_attr(mac, key, value)
    local res = {}
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, mac)
    require "MZLog".log(3, key)
    require "MZLog".log(3, value)
    local ret = dbfs.fetchBluetoothDevice(mac)
    local id = nil
    if #ret > 0 then
        for k, v in pairs(ret) do
            id = v.id
        end
    end

    if id ~= nil then
        if key == "8" and value == "true" then
            local cmd = "bt_daemon -s ".."1 "..string.format("%d", "0x"..id).." 1"   
            lue(cmd)
            local flag = nil
            for i = 1, 10 do
                flag = is_switch_on(mac)    
                if flag then
                    break
                else
                    posix.sleep(1) 
                end
            end
            if flag then
                res["result"] = true
                res["mac"] = mac
                res["key"] = key
                res["onoff"] = " on"
            else
                res["result"] =  false
                res["mac"] = mac
                res["key"] = key
                res["onoff"] = "off"
            end

        elseif key == "8" and value == "false" then
            local cmd = "bt_daemon -s ".."1 "..string.format("%d", "0x"..id).." 0"   
            lue(cmd)
            local flag = nil
            for i = 1, 10 do
                flag = is_switch_off(mac)    
                if flag then
                    break
                else
                    posix.sleep(1)
                end 
            end
            if flag then
                res["result"] = true
                res["mac"] = mac
                res["key"] = key
                res[ "onoff"] = "off"
            else
                res[ "result"] = false
                res["mac"] = mac
                res["key"] = key
                res["onoff"] = "on"
            end

        elseif key == "0" then
            require "MZLog".log(3, debug.getinfo(1).currentline)
            require "MZLog".log(3, value)
            if #value > 20 then
                value = string.sub(value, 1, 20)
            end
            require "MZLog".log(3, debug.getinfo(1).currentline)
            local name = ""
            for i = 1, #value, 2 do
                name = name.." "..string.format("%d", "0x"..string.sub(value, i, i+1))
            end

            require "MZLog".log(3, debug.getinfo(1).currentline)
            require "MZLog".log(3, name)
            local len = #value
            if len > 20 then
                len = 20
            end
            require "MZLog".log(3, len)
            dbfs.updateBluetoothDevice(id, "", "", len, mac)
            require "MZLog".log(3, debug.getinfo(1).currentline)
            local cmd = "bt_daemon -s ".."13 "..string.format("%d", "0x"..id).." "..name   
            lue(cmd)
            require "MZLog".log(3, debug.getinfo(1).currentline)
            require "MZLog".log(3, cmd)

            local name = ""
            for i = 1, 10 do
                 posix.sleep(1)
                name = is_set_name_ok(mac)
                if name ~= "" then
                    break
                end 
            end

            if name ~= "" and name ~= nil then
                 res ["result"] = true
                res["mac"] = mac
                res["key"] = key
            else
                 res[ "result"] = false
                res["mac"] = mac
                res["key"] = key
            end
            require "MZLog".log(3, debug.getinfo(1).currentline)
            require "MZLog".log(3, debug.getinfo(1).currentline)
        end
    else
        res["result"] = false
        res["mac"] = mac
        res["key"] = key
    end
    return res
end

function nw_set_mesh_device_attr()
    local mac = luci.http.formvalue("mac")
    local key = luci.http.formvalue("key")
    local value = luci.http.formvalue("value")
    local res = set_mesh_device_attr(mac, key, value)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_set_mesh_device_attr(mac, key, value)
    local res = set_mesh_device_attr(mac, key, value)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  reboot_mesh_device --------------------
function reboot_mesh_device(mac)
    local res = {}
    local ret = dbfs.fetchBluetoothDevice(mac)
    local id = nil
    for k, v in pairs(ret) do
        id = v.id
    end
    
    local cmd = "bt_daemon -s ".."4 "..string.format("%d", "0x"..id)   
    lue(cmd)
    posix.sleep(2)
    res["result"] = true
end

function nw_reboot_mesh_device()
    local mac = luci.http.formvalue("mac")
    local res = reboot_mesh_device(mac)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_reboot_mesh_device(mac)
    local res = reboot_mesh_device(mac)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  unmesh_all_device --------------------
function unmesh_all_device()
    local res = {}
    local cmd = "bt_daemon -s ".."3 ".."255"
    lue(cmd)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    local wait_time = nil
    for i = 1, 20 do
        wait_time = is_remove_ble_from_mesh()
    require "MZLog".log(3, debug.getinfo(1).currentline)
        if wait_time ~= nil then
            break
		else
            posix.sleep(1)
        end
    end
	if wait_time then
        require "MZLog".log(3, debug.getinfo(1).currentline)
		posix.sleep(5)
		posix.sleep(5)
		posix.sleep(5)
		--posix.sleep(tonumber(wait_time))
        require "MZLog".log(3, debug.getinfo(1).currentline)
        res["result"] = true
	else
        res["result"] = false
    end
    require "MZLog".log(3, debug.getinfo(1).currentline)
	return res 
end

function nw_unmesh_all_device()
    local res = unmesh_all_device()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_unmesh_all_device()
    local res = unmesh_all_device()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  set_mesh_device_timer --------------------
function is_set_timer_ok(mac, timer_id)
    local TMP = "/tmp/"..mac.."0"
    local fd = io.open(TMP, "r")
    if fd then
        local ret = fd:read()
        ret = cjson.decode(ret)
        fd:close()
        if ret["timerId"] == timer_id then
            return true
        else
            return nil
        end
    else
        return nil
    end
end
function set_mesh_device_timer(mac, timer_id, flag, start_time, end_time)
    local res = {}
    local ret = dbfs.fetchBluetoothDevice(mac) 
    local id = nil
    for k ,v in pairs(ret) do
        id = v.id
    end
    local ret = dbfs.getBleTimerId(id, timer_id)
    if ret == "" then
        dbfs.addBleTimer(id, timer_id, flag, start_time, end_time)
    end
    --[[
    local start = start
    local ends = ends
    if string.len(start) == 6 then
        start = "00"..start
    elseif string.len(start) == 5 then
        start = "000"..start
    elseif string.len(start) == 4 then
        start = "0000"..start
    end

    if string.len(ends) == 6 then
        ends = "00"..ends
    elseif string.len(ends) == 5 then
        ends = "000"..ends
    elseif string.len(ends) == 4 then
        ends = "0000"..ends
    end
    require "MZLog".log(3, mac)
    require "MZLog".log(3, timer_id)
    require "MZLog".log(3, flag)
    require "MZLog".log(3, start)
    require "MZLog".log(3, ends)

    local TMP = "/tmp/"..mac.."1"
    local fd = io.open(TMP, "r")
    if fd then
        local res = fd:read()
        res = cjson.decode(res)
        res["timerId"] = timer_id
        res = cjson.encode(res)
        local fd = io.open(TMP, "w")
        fd:write(res)
        fd:close()
    end

    local start1 = string.sub(start, 1, 2)
    local start2 = string.sub(start, 3, 4)
    local start3 = string.sub(start, 5, 6)
    local start4 = string.sub(start, 7, 8)
    local end1 = string.sub(ends, 1, 2)
    local end2 = string.sub(ends, 3, 4)
    local end3 = string.sub(ends, 5, 6)
    local end4 = string.sub(ends, 7, 8)
    if id then
        local start = string.format("%d", "0x"..start1).." "..
                        string.format("%d", "0x"..start2).." "..
                        string.format("%d", "0x"..start3).." "..
                        string.format("%d", "0x"..start4)
        local ends = string.format("%d", "0x"..end1).." "..
                        string.format("%d", "0x"..end2).." "..
                        string.format("%d", "0x"..end3).." "..
                        string.format("%d", "0x"..end4)

        local cmd = "/root/spi_send ".."6 "..string.format("%d", "0x"..id)..
                    " "..string.format("%d", "0x"..flag).." "..
                    string.format("%d", "0x"..timer_id).." "..start.." "..ends
        require "MZLog".log(3, cmd)
        lue(cmd)

        local times = tonumber(os.time()) - 1420041600
        local res = string.format("%x", times)
        local time = ""
        for i = 1, #res, 2 do
            time = time.." "..string.format("%d", "0x"..string.sub(res, i, i+1))
        end
        local cmd = "/root/spi_send ".."7 "..string.format("%d", "0x"..id).." "..time
        lue(cmd)
    end

    local flag = nil
    for i = 1, 10 do
        posix.sleep(1)
        flag = is_set_timer_ok(mac, timer_id)
        if flag then
            break
        end
    end
    if flag then
        res["result"] = true
        res["mac"] = mac
        res["timerId"] = timer_id
    else
        res["result"] = false
        res["mac"] = mac
        res["timerId"] = timer_id
    end
    return res
    ]]
end

function nw_set_mesh_device_timer()
    local mac = luci.http.formvalue("mac")
    local timer_id = luci.http.formvalue("timerId")
    local flag = luci.http.formvalue("flag")
    local start_time = luci.http.formvalue("start")
    local end_time = luci.http.formvalue("ends")
    local timer = luci.http.formvalue("timer")
    local res = set_mesh_device_timer(mac, timer_id, flag, start_time, end_time)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_set_mesh_device_timer(mac, timer_id, flag, start, ends)
    local res = set_mesh_device_timer(mac, timer_id, flag, start, ends)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  del_mesh_device_timer --------------------
function is_del_timer_ok(mac, timer_id)
    local TMP = "/tmp/"..mac.."0"
    local fd = io.open(TMP, r)
    if fd then
        local ret = fd:read()
        ret = cjson.decode(ret)
        fd:close()
        if ret["timerId"] ~= timer_id then
            return true
        else
            return nil
        end
    else
        return nil
    end
end

function del_mesh_device_timer(mac, timer_id)
    local res = {}
    local ret = dbfs.fetchBluetoothDevice(mac) 
    local id = nil
    for k ,v in pairs(ret) do
        id = v.id
    end
    local ret = dbfs.getBleTimerId(id, timer_id)
    if ret ~= "" then
        dbfs.deleteBleTimer(id, timer_id)
    end
    
    --[[
    local cmd = "/root/spi_send ".."10 "..string.format("%d", "0x"..id)..
                " "..string.format("%d", "0x"..timer_id)
    lue(cmd)
    local flag = nil
    for i = 1, 10 do
        posix.sleep(1)
        flag = is_del_timer_ok(mac, timer_id)
        if flag then
            break
        end
    end
    if flag then
        res["result"] = true
        res["mac"] = mac
        res["timerId"] = timer_id
    else
        res["result"] = false
        res["mac"] = mac
        res["timerId"] = timer_id
    end
    return res
    ]]
end

function nw_del_mesh_device_timer()
    local mac = luci.http.formvalue("mac")
    local timer_id = luci.http.formvalue("timerId")
    local res = del_mesh_device_timer(mac, timer_id)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_del_mesh_device_timer(mac, timer_id)
    local res = del_mesh_device_timer(mac, timer_id)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  set_encry_info --------------------
function is_set_key_ok()
    local TMP = "/tmp/0b0b"
    local fd = io.open(TMP, "r")
    require "MZLog".log(3, debug.getinfo(1).currentline)
	if fd then
        local file = fd:read()
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, file)
        fd:close()
		if file == "00" then
			return true
		elseif file == "01" then
    require "MZLog".log(3, debug.getinfo(1).currentline)
			return false
		end
	end
end

function get_ble_device_key()
	local ret = dbfs.fetchBluetoothDeviceKey()
	if #ret > 0 then
		return ret
	else
		return nil
	end
end

function set_mesh_network_pwd(old_key, new_key)
    --local TMP = "/tmp/"..new_key
    --local fd = io.open(TMP, "w")
    --fd:write(new_key)
    --fd:close()
    require "MZLog".log(3, debug.getinfo(1).currentline)
	--"0123"
	--"8888"
    require "MZLog".log(3, old_key)
	local key = new_key
    require "MZLog".log(3, new_key)
	local cmd = "bt_daemon -s 21"
    lue(cmd)
	local ret = nil
	for i = 1, 10 do
	    ret = get_ble_device_key()
		if ret then
			break
		else
            posix.sleep(1) 
		end
	end
	
	if #ret > 0 then
	    for k, v in pairs(ret) do
			if v.key ~= old_key then
			    old_key = v.key
			end
		end
	end
    
	local res = {}
    require "MZLog".log(3, old_key)
    require "MZLog".log(3, new_key)
    local old_key1 = string.sub(old_key, 1, 1)
    local old_key2 = string.sub(old_key, 2, 2)
    local old_key3 = string.sub(old_key, 3, 3)
    local old_key4 = string.sub(old_key, 4, 4)
    local new_key1 = string.sub(new_key, 1, 1)
    local new_key2 = string.sub(new_key, 2, 2)
    local new_key3 = string.sub(new_key, 3, 3)
    local new_key4 = string.sub(new_key, 4, 4)
    require "MZLog".log(3, old_key1)
    require "MZLog".log(3, old_key2)
    require "MZLog".log(3, old_key3)
    require "MZLog".log(3, old_key4)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, debug.getinfo(1).currentline)

	--bt_daemon -s 21 

    local old_key = old_key1.." "..old_key2.." "..old_key3.." "..old_key4
    local new_key = new_key1.." "..new_key2.." "..new_key3.." "..new_key4
    local cmd = "bt_daemon -s ".."9 "..old_key.." "..new_key   
    lue(cmd)
    require "MZLog".log(3, cmd)
    local flag = nil
    for i = 1, 10 do
        flag = is_set_key_ok()
        if flag ~= nil then
            break
		else
            posix.sleep(1) 
        end
    end
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, ret)
	if #ret > 0 then
		for k, v in pairs(ret) do
			local mac = v.mac
	        dbfs.updateBluetoothDevice("", key, "", "", mac)
		end
	end

    if flag then
        res["result"] = true
        res["newKey"] = key
    else
        res["result"] = false
        res["newKey"] = key
    end
	return res
end

function nw_set_mesh_network_pwd()
    local old_key = luci.http.formvalue("oldKey")
    local new_key = luci.http.formvalue("newKey")
    local res = set_mesh_network_pwd(old_key, new_key)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_set_mesh_network_pwd (old_key, new_key)
    local res = set_mesh_network_pwd(old_key, new_key)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

function set_lamp_brightness()
    
end

function nw_set_lamp_brightness()
    local res = set_lamp_brightness()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_set_lamp_brightness()
    local res = set_lamp_brightness()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

------------------------  bluetooth  --------------------

module("meizu.nwfs", package.seeall)
--network functions

local cjson = require "cjson"
local lfs   = require "lfs"
local bfs   = require "meizu.bfs"
local RC    = require "meizu.rtconfig"
local dbfs  = require "meizu.dbfs"

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
local report_router_config = bfs.report_router_config

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

	local deviceModel = RC.deviceModel
	if "R13" == deviceModel then
		if on2 == "false" then
			succeed2 = set_wifi_basic_info(2, nil, nil, nil, on2)
		else
			if code2 == 1 then
				succeed2 = set_wifi_basic_info(2, ssid2, pwd2, encry2, on2)
			end
		end
	else
		code2 = 1
		succeed2 = true
	end

	if code1 ~= 0 and code2 ~= 0 and succeed1 ~= false and succeed2 ~= false then
		res["result"] = true
		fork_restart_wifi()
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

function fork_restart_wifi()
	local FORK_RESTART_WIFI = "sleep 1; /sbin/wifi >/dev/null 2>/dev/null;"
	exec_cmd_in_sh(FORK_RESTART_WIFI)
end

function fork_restart_network()
	local FORK_RESTART_WORK= "/etc/init.d/network restart"
	exec_cmd_in_sh(FORK_RESTART_WORK)
end

function set_wifi_up()
    --local cmd = "/sbin/wifi up".. wnet
    local cmd = "/sbin/wifi up ".. "mt7628"
    exec_cmd_in_sh(cmd)
    luci.http.write_json("true")
end
function set_wifi_down()
    local cmd = "/sbin/wifi down ".."mt7628"
    --local cmd = "/sbin/wifi down"..wnet
    exec_cmd_in_sh(cmd)
    luci.http.write_json("true")
end
--set
function fork_smart_wifi_updown(wnet, close_hour, close_min, open_hour, open_min, week_repeat, time_flag, switch)
    local wifi_status = (switch == tostring(0) and "#" or "")
    if week_repeat == "non-repeat" then
        week_repeat = "0,1,2,3,4,5,6"
        cmd_close = "/usr/sbin/wifi_cron_switch "..close_min.." "..close_hour.." "..week_repeat.. " "..time_flag
                    .." "..wnet.." down"
        cmd_open = "/usr/sbin/wifi_cron_switch "..open_min.." "..open_hour.." "..week_repeat.." "..time_flag
                    .." "..wnet.." up"
    else
        cmd_close = "echo '"..wifi_status..close_min.." "..close_hour.." * * "..week_repeat.." /sbin/wifi down "..wnet.."; #"
            ..close_min..close_hour..open_min..open_hour..week_repeat.."~ down_"..time_flag
            .."' >> /etc/crontabs/root; crontab /etc/crontabs/root;"
        cmd_open = "echo '"..wifi_status..open_min.." "..open_hour.." * * "..week_repeat.." /sbin/wifi up "..wnet.."; #"
            ..close_min..close_hour..open_min..open_hour..week_repeat.."~ up_"..time_flag
            .."' >> /etc/crontabs/root; echo ''>>/etc/crontabs/root;  crontab /etc/crontabs/root;"
    end
    require "MZLog".log(3, cmd_close)
    require "MZLog".log(3, cmd_open)
    exec_cmd_in_sh(cmd_close)
    posix.sleep(1)
    exec_cmd_in_sh(cmd_open)
end
function set_smart_wifi_stop(update_flag)
    local cmd = "sed -i '/"..update_flag.."/d' /etc/crontabs/root; crontab /etc/crontabs/root"
    require "MZLog".log(3, cmd)
    exec_cmd_in_sh(cmd)
end

function set_smart_wifi_off(update_flag)
    local cmd = "sed -i '/"..update_flag.."/s/^/#/' /etc/crontabs/root; crontab /etc/crontabs/root"
    require "MZLog".log(3, cmd)
    exec_cmd_in_sh(cmd)
end

function set_smart_wifi_on(update_flag)
    local cmd = "sed -i '/"..update_flag.."/s/^#//' /etc/crontabs/root; crontab /etc/crontabs/root"
    require "MZLog".log(3, cmd)
    exec_cmd_in_sh(cmd)
end

function get_smart_wifi_info()
    local smart_wifi_info = io.open("/etc/crontabs/root", "r")
    local res = {}
    if smart_wifi_info == nil then
		luci.http.write_json("false")
	    return
    end
    for line in smart_wifi_info:lines() do
        idx,_ = string.find(line, "up_timer")
        if line and idx then
            time_idx_begin = string.find(line, "#", 2) + 1
            time_idx_end = string.find(line, "~") - 1
            local item = {}
            item["close_min"] = string.sub(line, time_idx_begin, time_idx_begin + 1)
            item["close_hour"] = string.sub(line, time_idx_begin + 2, time_idx_begin + 3)
            item["open_min"] = string.sub(line, time_idx_begin + 4, time_idx_begin + 5)
            item["open_hour"] = string.sub(line, time_idx_begin + 6, time_idx_begin + 7)
            local tmp_rep = string.sub(line, time_idx_begin + 8, time_idx_end)
            local repeat_var = "0000000"
	        for i = 1, #tmp_rep, 2 do
                idx = string.sub(tmp_rep, i, i)
                if idx ~= tostring(0) then
                    repeat_var = string.sub(repeat_var, 0, idx - 1) .. tostring(1) .. string.sub(repeat_var, idx + 1, #repeat_var)
                else
                    repeat_var = string.sub(repeat_var, 1, #repeat_var - 1) .. tostring(1)
                end
                require "MZLog".log(3, repeat_var)
            end
            item["repeat"] = repeat_var
            item["switch"] = string.sub(line, 1, 1) == "#" and 0 or 1
            table.insert(res,item)
        end
    end
    require "MZLog".log(3, res)
    smart_wifi_info:close()
    luci.http.write_json(res)
end

function set_smart_wifi_updown()
    local info = {}
    --get para
    wnet = luci.http.formvalue("wnet")
    close_hour = luci.http.formvalue("close_hour")
    close_min = luci.http.formvalue("close_min")
    open_hour = luci.http.formvalue("open_hour")
    open_min = luci.http.formvalue("open_min")
    repeat_var_from_http = luci.http.formvalue("repeat_var")
    func = luci.http.formvalue("func")
    time_flag = luci.http.formvalue("flag")
    switch = luci.http.formvalue("switch")
     --para err manage
    if string.len(repeat_var_from_http) ~= 7 then
        info["res"] = false
        luci.http.write_json(info)
	require "MZLog".log(3, "len error")
        return
    end
    --get repeat
    local week_repeat = ""
    require "MZLog".log(3, repeat_var_from_http)
    for i = 0, #repeat_var_from_http do
        tmp = string.sub(repeat_var_from_http, i, i)
        if tmp == "1" then
            if i == #repeat_var_from_http then
                week_repeat = week_repeat..0
                break
            end
            week_repeat = week_repeat..tostring(i)..","
        end
    end
    if week_repeat == "" then
        week_repeat = "non-repeat"
    end
    --exec
    if func == "set" then
        set_smart_wifi_stop(time_flag)
        posix.sleep(1)
        fork_smart_wifi_updown(wnet, close_hour, close_min, open_hour, open_min, week_repeat, time_flag, switch)
    elseif func == "stop" or func == "update" then
        set_smart_wifi_stop(time_flag)
        if func == "update" then
            local new_flag = luci.http.formvalue("new_flag")
            posix.sleep(1)
            fork_smart_wifi_updown(wnet, close_hour, close_min, open_hour, open_min, week_repeat, new_flag)
        end
    elseif func == "on" then
--        set_smart_wifi_on(time_flag)
          set_smart_wifi_stop(time_flag)
          posix.sleep(1)
          fork_smart_wifi_updown(wnet, close_hour, close_min, open_hour, open_min, week_repeat, time_flag, switch)
    elseif func == "off" then
--        set_smart_wifi_off(time_flag)
          set_smart_wifi_stop(time_flag)
          posix.sleep(1)
          fork_smart_wifi_updown(wnet, close_hour, close_min, open_hour, open_min, week_repeat, time_flag, switch)
    end
    info["res"] = true
    luci.http.write_json(info)
end
function get_lan_ip()
	local uci = require("luci.model.uci").cursor()
	local lan = uci:get_all("network", "lan")
	return lan.ipaddr
end

function macFormat(mac)
	if mac then
		return string.upper(string.gsub(mac, "-", ":"))
	else
		return ""
	end
end

function getAllWifiConnetDeviceDict()
	local result = {}
	local deviceModel = RC.deviceModel
	local networkCount = 2
	if "R13S" == deviceModel then
		networkCount = 1
	end
	for index = 1,networkCount do
		local wifilist = getWifiConnectDeviceList(index)
		for _, mactime in pairs(wifilist) do
			local item = {}
			item["wifiIndex"] = index
			item["time"] = mactime["time"]
			item["rx"] = mactime["rx"]
			item["tx"] = mactime["tx"]
			item["rxspeed"] = mactime["rxspeed"]
			item["txspeed"] = mactime["txspeed"]
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
				item["rxspeed"] = mactime[5]
				item["txspeed"] = mactime[6]
				table.insert(dlist,item)
			end
			tmplinenumber = tmplinenumber + 1
		end
		macfile:close()
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

			local devicename = v["devicename"]
			local orgname = v["orgname"]
			item["devicename"] = orgname
			if devicename ~= '' then
				item["devicename"] = devicename
			end
			if item["devicename"] == '' then
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
	local uci = require("luci.model.uci").cursor()
        local apc = uci.get("network", "lan", "apclient")
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
    	local uci = require("luci.model.uci").cursor()
        local apc = uci.get("network", "lan", "apclient")
	local wanNetwork = LuciNetwork:get_network("wan")
   	local wanDetails = {}
	if wanNetwork then
		if nil ~= apc and "" ~= apc then
			wanDetails["type"] = "apclient"
			local uci = require("luci.model.uci").cursor()
			local ssid = uci.get("wireless", apc, "apclissid")
			wanDetails["ssid"] = ssid
			wanDetails["bssid"] = b64enc(ssid)
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
        local bssid    = luci.http.formvalue("bssid")
        local ssid     = ""
        if nil ~= bssid and "" ~= bssid then
            ssid = b64dec(bssid)
        else
            ssid = luci.http.formvalue("ssid")
        end
        local mac      = luci.http.formvalue("mac")
        local sec      = luci.http.formvalue("sec")
        local extch    = luci.http.formvalue("extch")
        local wl_type_val = luci.http.formvalue("aptype")
        local key = ""
        local bkey = luci.http.formvalue("bkey")
        if nil ~= bkey and "" ~= bkey then
            key = b64dec(bkey)
        else
            key = luci.http.formvalue("key")
        end
        set_ap_client(channel, ssid, mac, sec, extch, wl_type_val, key)
    elseif "dhcp" == wan_type then
	    set_wan_type(wan_type)
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
			result["bssid"] = b64enc(v.ssid24)
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
	cancelapclient()
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
			sec = "NONE"
		end
	end
	local wl_type = "ra0"
	if wl_type_val == "5G" then
		wl_type = "rai0"
	end
	local cmd = [[apcli_connect.sh ]]..wl_type.." "..channel.." 111 "..sec
	if sec ~= "NONE" then
		cmd = cmd.." "..sec_alg.." 222"
	end
	lue(cmd)
	require "MZLog".log(3, cmd)
	local ret = {}
	ret["result"] = true
        local ssid5,ssid2 = get_wifi_ssids()
        local macaddrcmd = ""
	local uci = require("luci.model.uci").cursor()
	if wl_type_val == "2.4G" then
		uci.set("wireless", "mt7628", "apclissid", ssid)
		uci.set("wireless", "mt7628", "apcliwpapsk", key)
		uci.set("wireless", "mt7628iface", "ssid", ssid)
		uci.set("wireless", "mt7628iface", "key", key)
		ret["ssid"] = ssid2
		ret["bssid"] = b64enc(ssid2)
		macaddrcmd = "eth_mac r wl0"
	else
		uci.set("wireless", "mt7610e", "apclissid", ssid)
		uci.set("wireless", "mt7610e", "apcliwpapsk", key)
		uci.set("wireless", "mt7610eiface", "ssid", ssid)
		uci.set("wireless", "mt7610eiface", "key", key)
		ret["ssid"] = ssid5
		ret["bssid"] = b64enc(ssid5)
		macaddrcmd = "eth_mac r wl1"
	end
	uci.commit("wireless")
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
	local apc = uci.get("network", "lan", "apclient")
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
		local cmd = [[apcli_connect.sh disable 111 222]]
		lue(cmd)
		if "" ~= ssid_2g then
			uci.set("wireless", "mt7628iface", "ssid", ssid_2g)
		end
		if "" ~= ssid_5g then
			uci.set("wireless", "mt7610eiface", "ssid", ssid_5g)
		end
		uci.commit("wireless")
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
	return LuciSys.user.checkpasswd("admin", oldPassword)
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
			rxb = tonumber(speed[2])
			txb = tonumber(speed[4])
		else
			n = n + 1
			local tmp
			local td
			tmp = tonumber(speed[1])
			td = tmp - tb
			tb = tmp

			tmp = tonumber(speed[2])
			rx = rx + (tmp - rxb) / td
			rxb = tmp

			tmp = tonumber(speed[4])
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
	else
		if "mt7628" == apclient then
			cmd = [[luci-bwc -i apcli0 |tail -n 5 |sed -e 's#.*\[\s*\(.*\)\s*\].*#\1#']]
		elseif "mt7610e" == apclient then
			cmd = [[luci-bwc -i apclii0 |tail -n 5 |sed -e 's#.*\[\s*\(.*\)\s*\].*#\1#']]
		end
	end

	rx, tx = netspeed_channel(cmd)

    	res["rx"] = rx
	res["tx"] = tx

    	return res
end

function get_device_details(mac)
	local item = {}
	mac = string.upper(mac)
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

	local rtxsl = get_speed_limit(mac)
	item["rxlimit"] = rtxsl["rx"]
	item["txlimit"] = rtxsl["tx"]

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

			local devicename = v["devicename"]
			local orgname = v["orgname"]
			item["devicename"] = orgname
			if devicename ~= '' then
				item["devicename"] = devicename
			end
			if item["devicename"] == '' then
				item["devicename"] = "unknown"
			end

			local wifi = wifiDeviceDict[mac]

			item["online"] = false
			if wf == 0 and nil ~= im then
				item["online"] = true
			end
			if wifi ~= nil then
				item["online"] = true
				item["speed"] = wifi.rxspeed
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

---------------------wireless channel start--------
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
---------------------wireless channel end----------
function get_wireless_bandwidth()
	local res = 0
	local ret = {}
	local uci = require("luci.model.uci").cursor()
	local ht_bsscoexist = uci.get("wireless", "mt7628", "ht_bsscoexist")
	local bw = uci.get("wireless", "mt7628", "bw")
	if ht_bsscoexist == "1" and bw == "1" then
		res = 0
	end
	if ht_bsscoexist == "0" and bw == "0" then
		res = 1
	end
	if ht_bsscoexist == "0" and bw == "1" then
		res = 2
	end
	ret["bandwidth"] = res
	ret["type"] = "2.4G"
	return ret
end

function nw_get_wireless_bandwidth()
	local ret = get_wireless_bandwidth()
	luci.http.write_json(ret)
end

function ww_get_wireless_bandwidth()
	local ret = get_wireless_bandwidth()
	return cjson.encode(ret)
end

function set_wireless_bandwidth(wireless_type, bandwith)
	local res = false
	local uci = require("luci.model.uci").cursor()
	if wireless_type == "2.4G" then
		if bandwith == "0" then --auto
			res = uci.set("wireless", "mt7628", "bw", "1")
			res = uci.set("wireless", "mt7628", "ht_bsscoexist", "1")
		end
		if bandwith == "1" then --20M
			res = uci.set("wireless", "mt7628", "bw", "0")
			res = uci.set("wireless", "mt7628", "ht_bsscoexist", "0")
		end
		if bandwith == "2" then --40M
			res = uci.set("wireless", "mt7628", "bw", "1")
			res = uci.set("wireless", "mt7628", "ht_bsscoexist", "0")
		end
	end
	uci.commit("wireless")
	fork_restart_wifi()
	return res
end

function nw_set_wireless_bandwidth()
	local wireless_type = luci.http.formvalue("type")
	local bandwith = luci.http.formvalue("bandwith")
	local res = set_wireless_bandwidth(wireless_type, bandwith)
	local ret = {}
	ret["result"] = res
	luci.http.write_json(ret)
end

function ww_set_wireless_bandwidth(wireless_type, bandwith)
	local res = set_wireless_bandwidth(wireless_type, bandwith)
	local ret = {}
	ret["result"] = res
	return ret
end

---------------------led start---------------------
function get_led()
	local led_status = is_led_on()
        local ret = {}
        ret["status"] = led_status
        return ret
end

function nw_get_led()
        local ret = get_led()
        luci.http.write_json(ret)
end

function ww_get_led()
        local ret = get_led()
        return cjson.encode(ret)
end

function set_led(status)
	local res = false
	local cmd = [[/etc/init.d/mtkleds ]]
	local leds_status = is_led_on()
	if "on" == status and "off" == leds_status then
		cmd = cmd.."start"
		exec_cmd_in_sh(cmd)
		res = true
	elseif "off" == status and "on" == leds_status then
		cmd = cmd.."stop"
		exec_cmd_in_sh(cmd)
		res = true
	end
        return res
end

function nw_set_led(status)
        local status = luci.http.formvalue("status")
        local res = set_led(status)
        local ret = {}
        ret["result"] = res
        luci.http.write_json(ret)
end

function ww_set_led(status)
        local res = set_led(status)
        local ret = {}
        ret["result"] = res
        return ret
end

function is_led_on()
	local cmd = [[ps | grep mtkleds.sh]]
	local lu = require("luci.util")
	local ledstr = lu.exec(cmd)
	local index = string.find(ledstr, "/usr/bin/mtkleds.sh")
	if nil ~= index then
		return "on"
	else
		return "off"
	end
end
----------------------led end----------------------

----------------------speed limit start------------
function get_speed_limit_dict()
	local dict = {}
	local slfile = io.open("/tmp/speedlimit", "r")
	if nil ~= slfile then
		for line in slfile:lines() do
			local speed = {}
			local sl = strsplit(line, " ")
			local mac = string.lower(sl[1])
			speed["mac"] = mac
			speed["rx"] = sl[2]
			speed["tx"] = sl[3]
			dict[mac] = speed
		end
		slfile:close()
	end
	return dict
end

function dict_to_file(dict)
	local slfile = io.open("/tmp/speedlimit", "w")
	if nil == next(dict) then
		slfile:write("")
		slfile:close()
		return
	end
	local count = 0
	for k,v in pairs(dict) do
		count = count + 1
		if nil ~= v then
			if #dict == count then
				slfile:write(v["mac"].." "..v["rx"].." "..v["tx"])
			else
				slfile:write(v["mac"].." "..v["rx"].." "..v["tx"].."\n")
			end
		end
	end
	slfile:close()
end

function set_speed_limit(mac, rx, tx)
	mac = string.lower(mac)
	local existSpeedLimitDict = get_speed_limit_dict()
	local edit = existSpeedLimitDict[mac]
	rx = tonumber(rx)
	tx = tonumber(tx)
	if nil == edit then
		edit = {}
	end
	if -11 == rx then
		if nil ~= edit["rx"] then
			rx = edit["rx"]
		else
			rx = -1
		end
	end
	if -11 == tx then
		if nil ~= edit["tx"] then
			tx = edit["tx"]
		else
			tx = -1
		end
	end
	edit["mac"] = mac
	edit["rx"] = rx
	edit["tx"] = tx
	if -1 == rx and -1 == tx then
		existSpeedLimitDict[mac] = nil
		dict_to_file(existSpeedLimitDict)
		if nil ~= next(existSpeedLimitDict) then
			lue("netshaping.sh /tmp/speedlimit")
		else
			lue("netshaping.sh")
		end
		return true
	end

	existSpeedLimitDict[mac] = edit
	dict_to_file(existSpeedLimitDict)
	lue("netshaping.sh /tmp/speedlimit")
	return true
end

function nw_set_speed_limit()
	local mac = luci.http.formvalue("mac")
	local rx = luci.http.formvalue("rx")
	local tx = luci.http.formvalue("tx")
        local res = set_speed_limit(mac, rx, tx)
        local ret = {}
        ret["result"] = res
        luci.http.write_json(ret)
end

function ww_set_speed_limit(mac, rx, tx)
        local res = set_speed_limit(mac, rx, tx)
        local ret = {}
        ret["result"] = res
        return ret
end

function get_speed_limit(mac)
	mac = string.lower(mac)
	local existSpeedLimitDict = get_speed_limit_dict()
	local edit = existSpeedLimitDict[mac]
	local rx = -1
	local tx = -1
	if nil ~= edit then
		rx = tonumber(edit["rx"])
		tx = tonumber(edit["tx"])
	end
        local ret = {}
        ret["rx"] = rx
        ret["tx"] = tx
        return ret
end

function nw_get_speed_limit()
	local mac = luci.http.formvalue("mac")
        local ret = get_speed_limit(mac)
        luci.http.write_json(ret)
end

function ww_get_speed_limit(mac)
        local ret = get_speed_limit(mac)
        return cjson.encode(ret)
end

----------------------speed limit end--------------

---------------------kv start---------------------
function get_kv(key)
	local kv = dbfs.fetch_kv(key)
	local value = ""
	if nil ~= kv and #kv > 0 then
		for k,v in pairs(kv) do
			value = v["value"]
		end
	end
        local ret = {}
        ret["v"] = value
        return ret
end

function nw_get_kv()
	local key = luci.http.formvalue("k")
        local ret = get_kv(key)
        luci.http.write_json(ret)
end

function ww_get_kv(key)
        local ret = get_kv(key)
        return ret
end

function set_kv(key, value)
	dbfs.save_kv(key, value)
        return true
end

function nw_set_kv()
        local key = luci.http.formvalue("k")
        local value = luci.http.formvalue("v")
        local res = set_kv(key, value)
        local ret = {}
        ret["result"] = res
        luci.http.write_json(ret)
end

function ww_set_kv(key, value)
        local res = set_kv(key, value)
        local ret = {}
        ret["result"] = res
        return ret
end

----------------------kv end----------------------

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

module("meizu.nwfs", package.seeall)
--network functions

local cjson = require "cjson"
local lfs   = require "lfs"
local bfs   = require "meizu.bfs"
local RC    = require "meizu.r10config"

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

function get_connect_info()
	--usr/lib/lua/luci/controller/admin/network.lua lease_status()
	local rv = { }
	local s = require "luci.tools.status"

	luci.http.prepare_content("application/json")
	rv = s.dhcp_leases()
	for k, v in pairs(rv) do
		v.netswitch = "true"
		v.diskswitch = "true"
		v.netspeed = 0
		v.onnettime = 0
		v.netflow = 0
	end
	luci.http.write_json(rv)
end

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

	    if wifi_net.device == "wl0" then
	        local wifi_net_wl0 = network:get_wifinet('wl0.network1')
		    if wifi_net_wl0:get("disabled") == "1" then
                item["status"] = "false"
			else
                item["status"] = "true"
			end
		end

	    if wifi_net.device == "wl1" then
	        local wifi_net_wl1 = network:get_wifinet('wl1.network1')
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
        item["name"] = wifi_net.networks[index].ifname
        item["ssid"] = wifi_net.networks[index].ssid
	if key == nil then
	   key = ""
	end
        item["password"] = key
        item["encryption"] = encryption
        info_list[#wifis+1-i] = item
    end
    local guestwifi = get_guest_wifi(1)
    if guestwifi then
        table.insert(info_list, guestwifi)
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
    local switch_guest = nil
    local ssid_2g = nil
    local ssid_5g = nil
    local ssid_guest = nil
    local pwd_2g = nil
    local pwd_5g = nil
    local pwd_guest = nil
    local encry_2g = nil
    local encry_5g = nil
    local encry_guest = nil

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
            else
                switch_guest = v.on
                ssid_guest = v.ssid
                pwd_guest = v.pwd
                encry_guest = v.encryption
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

        switch_guest = luci.http.formvalue("on3")
        ssid_guest = luci.http.formvalue("ssid3")
        pwd_guest = luci.http.formvalue("pwd3")
        encry_guest = luci.http.formvalue("encryption3")
    end

    local res = wifi_settings(switch_2g, ssid_2g, pwd_2g, encry_2g, switch_5g, ssid_5g, pwd_5g, encry_5g, switch_guest, ssid_guest, pwd_guest, encry_guest)
    luci.http.write_json(res)
end

function wifi_settings(switch_2g, ssid_2g, pwd_2g, encry_2g, switch_5g, ssid_5g, pwd_5g, encry_5g, switch_guest, ssid_guest, pwd_guest, encry_guest)
    local result = {}
    local res = {}
    local details = {}
    --local code = 0
    local code_2g = 0
    local code_5g = 0
    local code_guest = 0
    local code_2g = check_ssid(ssid_2g, 31)
    local code_5g = check_ssid(ssid_5g, 31)
    local code_guest = check_ssid(ssid_guest, 31)
    local succeed_2g = false
    local succeed_5g = false
    local succeed_guest = false

    if switch_2g == "false" then
        succeed_2g = set_wifi_basic_info(1, nil, nil, nil, switch_2g)
    else
        if code_2g == 1 then
            succeed_2g = set_wifi_basic_info(1, ssid_2g, pwd_2g, encry_2g, switch_2g)
        end
    end

    if switch_5g == "false" then
        succeed_5g = set_wifi_basic_info(2, nil, nil, nil, switch_5g)
    else
        if code_5g == 1 then
            succeed_5g = set_wifi_basic_info(2, ssid_5g, pwd_5g, encry_5g, switch_5g)
        end
    end

    local wifirestart = true
    if switch_guest == "true" then
        switch_guest = 1
        if set_guest(1, ssid_guest, encry_guest, pwd_guest, 1, switch_guest) then
            succeed_guest = true
            --code = 0
        else
            succeed_guest = false
            wifirestart = false
        end
    elseif switch_guest == "false" then
        switch_guest = 0
        if set_guest(1, nil, nil, nil, 0, switch_guest) then
            succeed_guest = true
        end
    else
        return nil
    end

    if code_2g ~= 0 and code_5g ~= 0 and code_guest ~= 0 and succeed_2g ~= false
        and succeed_5g ~= false and succeed_guest ~= false then

        res["result"] = true
        if wifirestart then
            fork_restart_wifi()
        end
    else
        res["result"] = false
    end

	return res
end

function set_wifi_basic_info(wifi_index, ssid, password, encryption, on)

    local network = require "luci.model.network".init()
    if wifi_index == 1 then
	    wifi_net = network:get_wifinet('wl0.network1')
	    wifi_dev = network:get_wifidev('wl0')
    end

    if wifi_index == 2 then
	    wifi_net = network:get_wifinet('wl1.network1')
	    wifi_dev = network:get_wifidev('wl1')
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
	--[[
    if string.match(ssid,"^%w[%w_.-]*%w$") or string.match(ssid, "^%w$") or
	   string.match(ssid, "^%w[%w_.-]*$") or string.match(ssid,"^[%w_.-]*$") or
	   string.match(ssid, "^[%w_.-]*%w$") then
	    return 1
	else
		return 0
	end
	]]
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
	--[[
	if passwd ~= "" then
		local ret = string.match(passwd, "^%w[^&\\%%+]*%w$") or string.match(passwd, "^%w[^&\\%%+]*$")
		if ret == nil or string.find(passwd,'"') then
			return 1523
		end
    end
	]]
    return 0
end

function fork_restart_wifi()
	local FORK_RESTART_WIFI = "sleep 1; /sbin/wifi >/dev/null 2>/dev/null; /etc/init.d/minidlna restart; /etc/init.d/samba restart; /usr/bin/gettraffic flush_wl_dev >/dev/null 2>/dev/null"
    exec_cmd_in_sh(FORK_RESTART_WIFI)
end

function fork_restart_network()
	local FORK_RESTART_WORK= "/etc/init.d/network restart"
    exec_cmd_in_sh(FORK_RESTART_WORK)
end

function fork_smart_wifi_shutdown(wnet, close_time, open_time)
    local close_interval = close_time - os.time()
    local open_interval = open_time - os.time()
    local cmd = string.format("sleep 15; /sbin/wifi down "..wnet.."; sleep 15; /sbin/wifi up "..wnet)

    --local cmd = string.format("sleep %s; /sbin/wifi down; sleep %s; /sbin/wifi up;", tostring(close_interval), tostring(open_interval))                                                   
    --local cmd = "/sbin/wifi "..switch.." "..wnet  
    --local cmd = string.format("sleep %s; /sbin/wifi down; sleep %s; /sbin/wifi up;", tostring(close_interval), tostring(open_interval)) 
    local cmd = string.format("/sbin/wifi down")
	luci.http.status(200, shutdown and "Shutdown" or "Reconnected")
    exec_cmd_in_sh(cmd)
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
    local wnet = 'WL1'
    local info = {}
    local switch = luci.http.formvalue("switch")

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
    fork_smart_wifi_shutdown(switch, wnet, close_time, restart_time)
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

function check_guest_wifi()
    local uci = require("luci.model.uci").cursor()
    local guest = uci:get_all("network", "eth0_3")
    if guest then
        return true
    else
        return false
    end
end

function set_guest(wifi_index, ssid, encryption, key, enabled, open)
    local success = set_guest_wifi(wifi_index, ssid, encryption, key, enabled, open)
    if not success then
        return false
    end
    local networkrestart = true
    if check_guest_wifi() then
        networkrestart = false
    end
    if networkrestart then
        local lanip = get_lan_ip()
        local lan = lanip:gsub(".%d+.%d+$", "")
        local cip = tonumber(lanip:match(".(%d+).%d+$"))
		local nixio = require("nixio")
		local bit = nixio.bit
        cip = bit.bor(bit.band(1, cip + 1), bit.lshift(bit.rshift(cip, 1), 1))
        lanip = lan.."."..tostring(cip)..".1"
        exec_cmd_in_sh("sleep 4; /usr/sbin/guest_ssid_network start "..lanip.." 255.255.255.0 >/dev/null 2>/dev/null")
    else
        fork_restart_wifi()
    end
    return true
end

function set_guest_wifi(wifi_index, ssid, encryption, key, enabled, open)
    local uci = require("luci.model.uci").cursor()
    local wifinetid, ifname
    local enabled = tonumber(enabled) == 1 and 1 or 0
    local open = tonumber(open) == 1 and 1 or 0
    if tonumber(wifi_index) == 1 then
        wifinetid = "wl0iface1"
        ifname = uci:get_all("wireless", "@wifi-iface[1]","ifname")
    else
        return false
    end
    guestwifi = uci:get_all("wireless", wifinetid)
    if guestwifi then
        guestwifi["ifname"] = ifname
        if not is_str_nil(ssid) and check_ssid(ssid) then
            guestwifi["ssid"] = ssid
        end
        if encryption and string.lower(tostring(encryption)) == "none" then
            guestwifi["encryption"] = "none"
            guestwifi["key"] = ""
        end
		if enabled == 1 then
            guestwifi["disabled"] = 0
		elseif enabled == 0 then
            guestwifi["disabled"] = 1
		else
		end

        if open then
            guestwifi["open"] = open
        end
        if encryption and string.lower(tostring(encryption)) ~= "none" and not is_str_nil(key) then
            local check = check_wifi_passwd(key,encryption)
            if check == 0 then
                guestwifi["encryption"] = encryption
                guestwifi["key"] = key
            else
                return false
            end
        end
    else
        if is_str_nil(ssid) or is_str_nil(encryption) then
            return false
        end
        guestwifi = {
            ["ifname"] = ifname,
            ["network"] = "guest",
            ["ssid"] = ssid,
            ["mode"] = "ap",
            ["encryption"] = encryption,
            ["key"] = key,
            ["open"] = open,
            ["enabled"] = enabled
        }
    end
    uci:section("wireless", "wifi-iface", wifinetid, guestwifi)
    uci:commit("wireless")
    return true
end

function get_lan_ip()
    local uci = require("luci.model.uci").cursor()
    local lan = uci:get_all("network", "lan")
    return lan.ipaddr
end

function get_guest_wifi(wifiIndex)
    local uci = require("luci.model.uci").cursor()
    local index = tonumber(wifiIndex)
    local status
    local guestwifi
        guestwifi = uci:get_all("wireless","wl0iface1")
        if guestwifi then
            if guestwifi.disabled == "1" then
		       status = "false"
	        else
		        status = "true"
	        end

            if guestwifi.key == nil then
	            guestwifi.key = "none"
	        end

            return {
                ["name"]      = "guest",
                ["ssid"]        = guestwifi.ssid,
                ["encryption"]  = guestwifi.encryption,
                ["password"]    = guestwifi.key,
                ["status"]      = status,
            }
        end
    return guestwifi
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
    for index = 1,3 do
        local wifilist = getWifiConnectDeviceList(index)
        for _, mac in pairs(wifilist) do
            local item = {}
            item['wifiIndex'] = index
            result[macFormat(mac)] = item
        end
    end
    return result
end

function getWifiConnectDeviceList(wifiIndex)
    local assoclist = {}
    if tonumber(wifiIndex) == 1 then
        assoclist = wifi_network('wl0.network1').assoclist or {}
    elseif tonumber(wifiIndex) == 2 then
        assoclist = wifi_network('wl1.network1').assoclist or {}
    else
        assoclist = wifi_network('wl0.network2').assoclist or {}
    end
    local dlist = {}
        for mac, info in pairs(assoclist) do
            table.insert(dlist, macFormat(mac))
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
    local dbfs = require("meizu.dbfs")
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
	--local cmd = "ping -W 2 -c 1 www.baidu.com > /dev/null ;echo -n $?"
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

function get_connect_device_list()
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, require"socket".gettime()*1000)
	local dbfs = require("meizu.dbfs")
    dbfs.change_maclist_table()
	local LuciUtil = require("luci.util")
	local deviceList = {}
	local dhcpDeviceDict = getDHCPDict()
	local wifiDeviceDict = getAllWifiConnetDeviceDict()
	-- local dhcpDevIpDict = getNetConnect()

	local blacklist0 = getMacfilterList(0)
	if next(blacklist0) ~= nil then
		for _, mac in pairs(blacklist0) do
            local flag = 0
			for key, value in pairs(dhcpDeviceDict) do
 	 		    if  value.mac == mac then
	  				flag = 1
					break
				end
			end
			if flag == 0 then
	   			local res = dbfs.fetchDenyDeviceInfo(mac)
				if #res > 0 then
	   				for _, device in ipairs(res) do
						dhcpDeviceDict[device.mac] = device
					end
				end
		    end
		end
    end

	local cmd = [[cat /proc/net/arp |grep br-lan|awk '{print $1","$4}']]
	local ipmacs = {}
	local devs = lue(cmd)
	if devs ~= "" then
		ipmacs = strsplit(devs, '\n')
	end
	ipmacs[#ipmacs] = nil
	local wire_table = {}
	for k, v in pairs(ipmacs) do
		local ipmac = strsplit(v, ',')
		local ip = ipmac[1]
		local mac = string.upper(ipmac[2])
		local flag = false
	    local res = dbfs.fetch_all_wireless_device()

		if #res > 0 then
			for key, value in pairs(res) do
				if mac == value.mac then
					flag = true
					break
				end
			end
		end
		if flag == false then
			local res = {}
			res.mac = mac
			res.ip = ip
			res.name = ""
			table.insert(wire_table, res)
		end
	end

	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, wire_table)
    for k, v in pairs(wire_table) do
		local flag = false
  	    for key, value in pairs(dhcpDeviceDict) do
		    local mac = macFormat(key)

		    if v.mac == mac then
		        local dhcpinfo = dhcpDeviceDict[mac]
			    v["name"] = dhcpinfo.name
			    dbfs.updateDeviceOrgname(mac, dhcpinfo.name)
				flag = true
			    break
		    end
		end
		if flag == false then
			dhcpDeviceDict[v.mac] = v
		end
	end

	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, debug.getinfo(1).currentline)

	local deviceDBDict = getDeviceInfoFromDB()
	for key, item in pairs(dhcpDeviceDict) do
		local item = {}
		local mac = macFormat(key)
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

		local black0 = blacklist0[mac]
		if black0 ~= nil then
			item["enable"] = false
		else
			item["enable"] = true
		end

		local wifiType = wifiDeviceDict[mac]
		local online = dhcpDeviceDict[mac]
		if wifiType ~= nil then
			if wifiType.wifiIndex == 1 or wifiType.wifiIndex == 3 then
				item["type"] = "2.4G"
			elseif wifiType.wifiIndex == 2 then
				item["type"] = "5G"
			end
		elseif wifiType == nil and online ~= nil and black0 == nil then
            local wireless_res = dbfs.fetch_wireless_device_mac(mac)
			if wireless_res == "" and mac ~= "00:00:00:00:00:00" then
			    item["type"] = "wire"
			else
				item["type"] = "unknown"
			end
		else
			item["type"] = "unknown"
		end

		if online ~= nil and black0 == nil and item["type"] ~= "unknown" then
			item["online"] = true
		else
			item["online"] = false
		end

		item["ip"] = dhcpinfo.ip
		item["mac"] = mac
		-- item["netSpeed"] = tostring(dhcpinfo.ip.rx_bytes or 0)
		deviceList[#deviceList+1] = item
	end
       --[[
       if blacklist0 ~= nil then
           for _, mac in pairs(blacklist0) do
               local item = {}
               local mac = macFormat(mac)
               local deviceDB = deviceDBDict[mac]
               local dhcpdevice = dhcpDeviceDict[mac]
               if dhcpdevice == nil then
                    if deviceDB ~= nil then
                       item["devicename"] = deviceDB.orgname
                       if deviceDB.devicename ~= '' then
                           item["devicename"] = deviceDB.devicename
                       end
                   end
                   local wifiType = wifiDeviceDict[mac]
                   if wifiType ~= nil then
                        if wifiType.wifiIndex == 1 then
                           item["type"] = "2.4G"
                       elseif wifiType.wifiIndex == 2 then
                           item["type"] = "5G"
                       end
                   else
                       item["type"] = "lan"
                   end
                   item["enable"] = false
                   item["online"] = false
                   item["ip"] = "null"
                   item["mac"] = mac
                   --item["diskaccess"] = false
                   -- item["netSpeed"] = tostring(dhcpinfo.ip.rx_bytes or 0)
                   deviceList[#deviceList+1] = item
               end
           end
       end
       if blacklist1 ~= nil then
           for _,mac in pairs(blacklist1) do
               local item = {}
               local mac = macFormat(mac)
               local deviceDB = deviceDBDict[mac]
               local dhcpdevice = dhcpDeviceDict[mac]
               if dhcpdevice == nil then
                     if deviceDB ~= nil then
                       item["devicename"] = deviceDB.orgname
                       if deviceDB.devicename ~= '' then
                           item["devicename"] = deviceDB.devicename
                       end
                   end

                  local wifiType = wifiDeviceDict[mac]
                  if wifiType ~= nil then
                        if wifiType.wifiIndex == 1 then
                           item["type"] = "2.4G"
                       elseif wifiType.wifiIndex == 2 then
                           item["type"] = "5G"
                       end
                   else
                       item["type"] = "lan"
                   end
                   item["enable"] = false
                   item["online"] = false
                   item["ip"] = "null"
                   item["mac"] = mac
                   --item["diskaccess"] = false
                   -- item["netSpeed"] = tostring(dhcpinfo.ip.rx_bytes or 0)
                   deviceList[#deviceList+1] = item
               end
           end
       end
	   ]]
	   --[[
	   local str = 0xffdddddd
	   str = string.format("%u",str)
	   require "MZLog".log(3, debug.getinfo(1).currentline)
	   require "MZLog".log(3, str)
	   require "MZLog".log(3, debug.getinfo(1).currentline)
	   ]]
	require "MZLog".log(3, require"socket".gettime()*1000)
	return deviceList
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
    local dbfs = require("meizu.dbfs")

    if is_str_nil(mac) or is_str_nil(devicename) then
        code = 1502
    else
        code = save_device_name(mac,devicename)
    end
    return code
end

function save_device_name(mac,name)
    local code = 0
    local dbfs = require("meizu.dbfs")
    local code = dbfs.updateDeviceNickname(macFormat(mac),name)
    if code == 0 then
        return true
    else
        return false
    end
end

function getMacfilterList(mode)
    mode = tonumber(mode)
    local maclist = {}
    local dlist = {}
    local uci = require("luci.model.uci").cursor()
    if mode == 0 then
        maclist = uci:get_list("wireless", "wl0iface0", "maclist")
    else
        maclist = uci:get_list("wireless", "wl1iface0", "maclist")
    end
    if #maclist > 0 then
        for _, mac in pairs(maclist) do
            dlist[macFormat(mac)] = mac
        end
    end
	return dlist
end

function set_disk_access(mac, enable)

    local result = {}
    local code = false
    local uci = require("luci.model.uci").cursor()
    local dbfs = require("meizu.dbfs")
    dbfs.initSmbBanTable()
    if is_str_nil(mac) then
		return
	else
		mac = macFormat(mac)
		enable = tonumber(enable)
	end
	if enable == 0 then
		local cmd = "samba_ban add "..mac
	    local smb_ban = "true"
	    exec_cmd_in_sh(cmd)
        dbfs.addSmbBanList(mac, smb_ban)
	    code = false
    else
	    local cmd = "samba_ban remove "..mac
	    exec_cmd_in_sh(cmd)
        dbfs.deleteSmbBanList(mac)
		code = true
	end
	result["result"] = code
	return result
end

function set_wan_switch(mac, mode, enable)
    local result = {}
    local code = false
    local uci = require("luci.model.uci").cursor()
    if is_str_nil(mac) then
        return
    else
        mac = macFormat(mac)
        enable = tonumber(enable)
    end
    uci:set("wireless", "wl0iface0", "macfilter", "deny")
	local maclist = uci:get_list("wireless", "wl0iface0", "maclist")
	if enable == 0 then
		for _, macaddr in ipairs(maclist) do
			if mac == macaddr then
				code = "same black"
				return code
			end
		end
		table.insert(maclist, mac)
		code = true
	else
		local pos = -1
		for i, macaddr in ipairs(maclist) do
			if mac == macaddr then
				pos = i
			end
		end
		if pos >= 0 then
			table.remove(maclist, pos)
			code = true
		else
			code = "same white"
			return code
		end
	end
	if #maclist > 0 then
		uci:set_list("wireless", "wl0iface0", "maclist", maclist)
	else
		uci:delete("wireless", "wl0iface0", "maclist")
	end

	uci:set("wireless", "wl0iface1", "macfilter", "deny")
	local maclist = uci:get_list("wireless", "wl0iface1", "maclist")
	if enable == 0 then
		for _, macaddr in ipairs(maclist) do
			if mac == macaddr then
				code = "same black"
				return code
			end
		end
		table.insert(maclist, mac)
		code = true
	else
		local pos = -1
		for i, macaddr in ipairs(maclist) do
			if mac == macaddr then
				pos = i
			end
		end
		if pos >= 0 then
			table.remove(maclist, pos)
			code = true
		else
			code = "same white"
			return code
		end
	end
	if #maclist > 0 then
		uci:set_list("wireless", "wl0iface1", "maclist", maclist)
	else
		uci:delete("wireless", "wl0iface1", "maclist")
	end

	uci:set("wireless", "wl1iface0", "macfilter", "deny")
	local maclist = uci:get_list("wireless", "wl1iface0", "maclist")
	if enable == 0 then
		for _, macaddr in ipairs(maclist) do
			if mac == macaddr then
				code = "same black"
				return code
			end
		end
		table.insert(maclist, mac)
		code = true
	else
		local pos = -1
		for i, macaddr in ipairs(maclist) do
			if mac == macaddr then
				pos = i
			end
		end
		if pos >= 0 then
			table.remove(maclist, pos)
			code = true
		else
			code = "same white"
			return code
		end
	end
	if #maclist > 0 then
		uci:set_list("wireless", "wl1iface0", "maclist", maclist)
	else
		uci:delete("wireless", "wl1iface0", "maclist")
	end
	uci:commit("wireless")
	fork_restart_wifi()
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

function nw_set_disk_access()
	local result = {}
	local code = false
	local mac = luci.http.formvalue("mac")
	local enable = luci.http.formvalue("enable")
    code = set_disk_access(mac,enable)
	result["enable"] = enable
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
	--[[
	table.sort(conn, function(a,b)
		return(a.src < b.src)
	end)
	local dhcpDevIpDict = getDHCPIpDicts()
	for _,ip in pairs(conn) do
		if dhcpDevIpDict[ip.src] ~= nil then
		dhcpDevIpDict[ip.src].sp = dhcpDevIpDict[ip.src].sp + ip.bytes

		end
	end

  	return dhcpDevIpDict
	]]
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
    local wanNetwork = LuciNetwork:get_network("wan")
    local wanDetails = {}
    if wanNetwork then
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
    local pppoe_name = luci.http.formvalue("pppoeName")
	local pppoe_pwd = luci.http.formvalue("pppoePwd")
    set_wan_type(wan_type, pppoe_name, pppoe_pwd)
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
            needRestartWifi = true
        end
    end
    result["result"] = code
	luci.http.write_json(result)
	if needRestartWifi then
		fork_restart_network()
	end
	luci.http.close()
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

function real_time_net_speed()
	local res = {}
	local cmd = [[luci-bwc -i eth0.2|tail -n 5|sed -e 's#.*\[\s*\(.*\)\s*\].*#\1#']]
	local eth02_speed = io.popen(cmd)
	local speed_table = {}
	for line in eth02_speed:lines() do
		table.insert(speed_table, line)
	end
	eth02_speed:close()
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
    res["rx"] = string.format("%6.2f", rx/n)
	res["tx"] = string.format("%6.2f", tx/n)

    return res
end

function set_smb_switch(smbswitch)
	local result = {}
	local code = false
	local onoff = smbswitch
	if (tonumber)(onoff) == 1 then
		luci.sys.init.enable("samba")
		exec_cmd_in_sh("sleep 1")
		if luci.sys.init.enabled("samba") == true then
			code = true
		else
			code = false
		end
	elseif (tonumber)(onoff) == 0 then
		luci.sys.init.disable("samba")
		exec_cmd_in_sh("sleep 1")
		if luci.sys.init.enabled("samba") == true then
			code = false
		else
			code = true
		end
	end

	result["result"] = code
	return data_to_json(result)

end

function get_smb_switch()
	local smbswitch = {}
	local code = false
	code = luci.sys.init.enabled("samba")
	smbswitch["smbswitch"] = code
	return data_to_json(smbswitch)
end

function nw_set_smbswitch()
    local res = {}
	local smbswitch = luci.http.formvalue("smbswitch")
	res = set_smb_switch(smbswitch)
	luci.http.write(res)
end

function nw_get_smbswitch()
    local res = {}
	res = get_smb_switch()
	luci.http.write(res)
end

function make_get_device_details_cmd(mac, patten)
	local cmd = [[((wl -i wl0 sta_info ]]..mac..[[ 2>/dev/null| ]]
	cmd = cmd..[[grep -E ']]..patten..[[')||]]
	cmd = cmd..[[(wl -i wl1 sta_info ]]..mac..[[ 2>/dev/null| ]]
	cmd = cmd..[[grep -E ']]..patten..[[')||]]
	cmd = cmd..[[(wl -i wl0.1 sta_info ]]..mac..[[ 2>/dev/null| ]]
	cmd = cmd..[[grep -E ']]..patten..[['))]]
	cmd = cmd..[[|sed -e 's/[^0-9]//g'|awk '{printf $1}']]
	return cmd
end

function get_device_details_duration(mac)
	local patten = "in network"
	local cmd = make_get_device_details_cmd(mac, patten)
	local ret = lue(cmd)
	if ret == "" then
		ret = "0"
	end

	return ret
end

function get_device_details_tx(mac)
	local patten = "tx data bytes"
	local cmd = make_get_device_details_cmd(mac, patten)
	local ret = lue(cmd)
	if ret == "" then
		ret = "0"
	end

	return ret
end

function get_device_details_rx(mac)
	local patten = "rx data bytes"
	local cmd = make_get_device_details_cmd(mac, patten)
	local ret = lue(cmd)
	if ret == "" then
		ret = "0"
	end

	return ret
end

function get_device_details(mac)
	local dbfs = require("meizu.dbfs")
    dbfs.change_maclist_table()
	local item = {}
	mac = string.upper(mac)
	local sec = 0
	local tx = 0
	local rx = 0
	local smbBanList = {}
	dbfs.initSmbBanTable()
	smbBanList = dbfs.fetchSmbBanList()
	local flag = 0
	if next(smbBanList) ~= nil then
		for _, value in pairs(smbBanList) do
			if value.mac == mac then
	 			flag = 1
				break
			end
		end
	end

	if flag == 0 then
		item["diskaccess"] = true
	else
		item["diskaccess"] = false
	end

	local blacklist0 = getMacfilterList(0)
	local black0 = blacklist0[mac]
	if black0 ~= nil then
		item["enable"] = false
	else
		item["enable"] = true
	end

	local dhcpDeviceDict = getDHCPDict()
	local res = dbfs.fetch_all_wire_device()
	if #res > 0 then
		for k, v in pairs(res) do
			v["name"] = ""
            for key, value in pairs(dhcpDeviceDict) do
		        local mac = macFormat(key)
			    if v.mac == mac then
		            local dhcpinfo = dhcpDeviceDict[mac]
				    v["name"] = dhcpinfo.name
			        --dbfs.saveDeviceInfo(mac, dhcpinfo.name, "", dhcpinfo.ip)
			        dbfs.updateDeviceOrgname(mac, dhcpinfo.name)
					break
				end
			end
			dhcpDeviceDict[v.mac] = v
		end
	end

	local dhcpinfo = dhcpDeviceDict[mac]
	local online = dhcpDeviceDict[mac]
	local wifiDeviceDict = getAllWifiConnetDeviceDict()
	local wifiType = wifiDeviceDict[mac]
	if wifiType ~= nil then
		sec = get_device_details_duration(mac)
		tx = get_device_details_tx(mac)
		rx = get_device_details_rx(mac)
		if wifiType.wifiIndex == 1 or wifiType.wifiIndex == 3 then
			item["type"] = "2.4G"
		elseif wifiType.wifiIndex == 2 then
			item["type"] = "5G"
		end
	elseif wifiType == nil and black0 == nil then
        local wireless_res = dbfs.fetch_wireless_device_mac(mac)
		if wireless_res == "" and mac ~= "00:00:00:00:00:00" then
			item["type"] = "wire"
            local ret = getNetConnect(dhcpinfo.ip)
			tx = ret.bytes or 0
		else
			item["type"] = "unknown"
		end
	else
		item["type"] = "unknown"
	end

	if black0 == nil and item["type"] ~= "unknown" then
		item["online"] = true
	else
		item["online"] = false
	end
	if dhcpinfo then
		item["ip"] = dhcpinfo.ip
	else
		item["ip"] = ""
	end
	local deviceDBDict = getDeviceInfoFromDB()
	local deviceDB = deviceDBDict[mac]
	item["devicename"] = ""
	if deviceDB ~= nil then
		item["devicename"] = deviceDB.orgname
		if deviceDB.devicename ~= '' then
			item["devicename"] = deviceDB.devicename
		end
	else
		if dhcpinfo then
			dbfs.saveDeviceInfo(mac, dhcpinfo.name, "", dhcpinfo.ip)
		end
	end

	item["mac"] = mac
	item["speed"] = 0
	item["time"] = sec
	item["upload"] = rx
	item["download"] = tx

	return item
end

function nw_get_device_details()
	local mac = luci.http.formvalue("mac")
	local res = get_device_details(mac)
	luci.http.write_json(res)
end

function get_wireless_channel()
	local res = false
	local uci = require("luci.model.uci").cursor()
	local channel = uci.get("wireless", "wl0", "channel")
	local htmode = uci.get("wireless", "wl0", "htmode")
        local extch = 1
        if "auto" == channel then
                channel = "0"
	end
        local mode = string.sub(htmode,3,4)
        if "20" == mode then
                extch = 0
end

	local ret = {}
	ret["channel"] = channel
        ret["extch"] = extch
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
        if "0" == channel then
                res = uci.set("wireless", "wl0", "channel", "auto")
        else
        	local c = tonumber(channel)
        	local tab = "+"
        	local mode = "40"
                if c >= 7 and c <= 13 then
                        tab = "-"
		end
 		if extch == "0" then
 			mode = "20"
		end
		local htmode = "HT"..mode..tab
			res = uci.set("wireless", "wl0", "channel", channel)
                res = uci.set("wireless", "wl0", "htmode", htmode)
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

function ww_get_device_details(mac)
	local res = get_device_details(mac)
	return res
end

function get_tx_power_mode(mode)
	local res = false
    local uci = require("luci.model.uci").cursor()
    local txpower = tonumber(uci.get("wireless", "wl0", "txpower"));
    local mode = "normal"
	if txpower > 15 then
		mode = "enhance"
    --[[
	   [else
	   [    if txpower < 12 then
	   [        mode = "green"
	   [    end
       ]]
	end

	return mode
end

function nw_get_tx_power_mode(mode)
	local mode = get_tx_power_mode()
	local ret = {}
	ret["mode"] = mode
	luci.http.write_json(ret)
end

function ww_get_tx_power_mode(mode)
	local ret = {}
	local mode = get_tx_power_mode(mode)
	ret["mode"] = mode
	return data_to_json(ret)
end

function set_tx_power_mode(mode)
	local res = false
	local uci = require("luci.model.uci").cursor()
	if mode == "normal" then
		res = uci.set("wireless", "wl0", "txpower", "15")
		res = uci.set("wireless", "wl1", "txpower", "15")
	else
		if mode == "enhance" then
			res = uci.set("wireless", "wl0", "txpower", "22")
			res = uci.set("wireless", "wl1", "txpower", "22")
		end
	end
	uci.commit("wireless")

	return res
end

function nw_set_tx_power_mode(mode)
	local mode = luci.http.formvalue("mode")
	local res = set_tx_power_mode(mode)
	local ret = {}
	ret["result"] = res
	luci.http.write_json(ret)
end

function ww_set_tx_power_mode(mode)
	local ret = {}
	local res = set_tx_power_mode(mode)
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

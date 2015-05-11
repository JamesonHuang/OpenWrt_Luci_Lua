module("meizu.arpmon", package.seeall)
--network functions

local cjson = require "cjson"
local lfs   = require "lfs"
local bfs   = require "meizu.bfs"
local dbfs  = require "meizu.dbfs"
local RC    = require "meizu.r10config"
local sipfs = require "meizu.sipfs"
local nwfs  = require "meizu.nwfs"

local lue   = require("luci.util").exec

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

local delete_arp_all_mac   = dbfs.delete_arp_all_mac
local fetch_arp_mac        = dbfs.fetch_arp_mac
local get_dev_nick_name    = dbfs.get_dev_nick_name
local init_arp_table       = dbfs.init_arp_table
local insert_arp_macip     = dbfs.insert_arp_macip

function new_device_notify()

	init_arp_table()
	local ret = {}
	local cmd = [[cat /proc/net/arp |grep br-lan|awk '{print $1","$4}']]
	local ipmacs = {}
	local devs = lue(cmd)
	if devs ~= "" then
		ipmacs = strsplit(devs, '\n')
	end
	local new_devs = {}
	ipmacs[#ipmacs] = nil
	local wifiDeviceDict = nwfs.getAllWifiConnetDeviceDict()
	for k, v in pairs(ipmacs) do
		local ipmac = strsplit(v, ',')
		local ip = ipmac[1]
		local mac = ipmac[2]
		mac = string.upper(mac)
		local wifiType = wifiDeviceDict[mac]
        local data = dbfs.fetch_wireless_device_mac(mac)
		if wifiType ~= nil then
            --local res = dbfs.fetch_wireless_device_mac(mac)
			if data == "" then
                dbfs.insert_wireless_device_mac(mac)
			end
		else
            local ret = dbfs.fetch_wire_device_mac(mac)
            local res = nwfs.is_device_online(ip)
			if res == false and ret == "" and data == "" then
                dbfs.insert_wireless_device_mac(mac)
			elseif res == true and ret == "" then
                dbfs.insert_wire_device_mac(mac, ip)
			end
		end

		if ip ~= "" then
			if fetch_arp_mac(mac) == "" then
				table.insert(new_devs, v)
			end
		end
	end
	delete_arp_all_mac()
	for k, v in pairs(ipmacs) do
		local ipmac = strsplit(v, ',')
		local ip = ipmac[1]
		local mac = ipmac[2]
		if ip ~= "" then
			insert_arp_macip(mac, ip)
		end
	end
	for k, v in pairs(new_devs) do
		local ipmac = strsplit(v, ',')
		local ip = ipmac[1]
		local mac = ipmac[2]
		if ip ~= "" then
			local logtype = 1
			ret["mac_address"] = mac
			local nickname = get_dev_nick_name(mac)
			if nickname and nickname ~= "" then
				ret["name"] = nickname
			else
				ret["name"] = ip
			end
			local res, code, headers, status = sipfs.upload_router_log(data_to_json(ret), logtype)
		end
	end
	luci.http.write_json(ret)
end

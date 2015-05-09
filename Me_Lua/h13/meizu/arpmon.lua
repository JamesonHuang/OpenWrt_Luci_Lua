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
local get_dev_nick_name    = dbfs.get_dev_nick_name
local init_arp_table       = dbfs.init_arp_table
local insert_arp_macip     = dbfs.insert_arp_macip
local fetch_all_arp        = dbfs.fetch_all_arp
local update_arp           = dbfs.update_arp

local getAllWifiConnetDeviceDict = nwfs.getAllWifiConnetDeviceDict

function new_device_notify()

	init_arp_table()
	local ret = {}
	local cmd = [[cat /proc/net/arp |grep br-lan|awk '{print $1","$4}']]
	local ipmacs = {}
	local devs = lue(cmd)
	if devs ~= "" then
		ipmacs = strsplit(devs, '\n')
	end
	ipmacs[#ipmacs] = nil
	local new_devs = {}
	local allarp = fetch_all_arp()
	local wifiDeviceDict = getAllWifiConnetDeviceDict()
	if nil == allarp then
		new_devs = ipmacs
	else
		for k, v in pairs(ipmacs) do
			local ipmac = strsplit(v, ',')
			local ip = ipmac[1]
			local mac = ipmac[2]
			mac = string.upper(mac)
			local isnew = true
			local wf = 0
			for index, value in pairs(allarp) do
				if mac == string.upper(value["mac"]) then
					isnew = false
					wf = value["wifi"]
					break
				end
			end
			if isnew == true then
				table.insert(new_devs,v)
			else
				local wdd = wifiDeviceDict[mac]
				if nil ~= wdd then
					wf = wdd.wifiIndex
				end
				update_arp(mac, ip, wf)
			end
		end
	end
	for k, v in pairs(new_devs) do
		local ipmac = strsplit(v, ',')
		local ip = ipmac[1]
		local mac = string.upper(ipmac[2])
		if ip ~= "" then
			local wifi = 0
			local wdd = wifiDeviceDict[mac]
			if nil ~= wdd then
				wifi = wdd.wifiIndex
			end
			if "00:00:00:00:00:00" ~= mac then
				insert_arp_macip(mac, ip, wifi)
			end
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


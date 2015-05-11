module("meizu.sipfs", package.seeall)
--sip functions

local bfs    = require "meizu.bfs"
local cjson  = require "cjson"
local disk   = require "meizu.disk"
local dlfs   = require "meizu.dlfs"
local nwfs   = require "meizu.nwfs"
local RC     = require "meizu.r10config"
local sipfs  = require "meizu.sipfs"
local upgdfs = require "meizu.upgdfs"
local dbfs   = require "meizu.dbfs"
local btfs   = require "meizu.btfs"

local b64dec                     = bfs.b64dec
local batchfile_checklist        = bfs.batchfile_checklist
local batchfile_compare_upload   = bfs.batchfile_compare_upload
local bind_router                = bfs.bind_router
local data_to_json               = bfs.data_to_json
local exec_cmd_in_sh             = bfs.exec_cmd_in_sh
local exec_reboot                = bfs.exec_reboot
local findInDir                  = bfs.findInDir
local get_device_SN              = bfs.get_device_SN
local get_device_version         = bfs.get_device_version
local get_https_data             = bfs.get_https_data
local factory_reset              = bfs.factory_reset
local rts_get_access_token       = bfs.rts_get_access_token
local set_passwd                 = bfs.set_passwd
local silent_upgrade             = bfs.silent_upgrade
local get_files_list             = bfs.get_files_list

local ww_get_disk_info           = disk.ww_get_disk_info

local get_smb_switch             = nwfs.get_smb_switch
local real_time_net_speed        = nwfs.real_time_net_speed
local set_device_name            = nwfs.set_device_name
local set_disk_access            = nwfs.set_disk_access
local set_smb_switch             = nwfs.set_smb_switch
local set_wan_switch             = nwfs.set_wan_switch
local wifi_settings              = nwfs.wifi_settings
local ww_get_connect_device_list = nwfs.ww_get_connect_device_list
local ww_get_device_details      = nwfs.ww_get_device_details
local ww_get_wifi_settings       = nwfs.ww_get_wifi_settings
local ww_get_tx_power_mode       = nwfs.ww_get_tx_power_mode
local ww_set_tx_power_mode       = nwfs.ww_set_tx_power_mode
local ww_get_wireless_channel    = nwfs.ww_get_wireless_channel
local ww_set_wireless_channel    = nwfs.ww_set_wireless_channel

local ww_scan_ble_switch         = btfs.ww_scan_ble_switch
local ww_add_ble_mesh_device     = btfs.ww_add_ble_mesh_device
local ww_get_ble_device_list     = btfs.ww_get_ble_device_list
local ww_get_ble_device_status   = btfs.ww_get_ble_device_status
local ww_set_mesh_device_attr    = btfs.ww_set_mesh_device_attr
local ww_get_mesh_device_list 	 = btfs.ww_get_mesh_device_list
local ww_unmesh_all_device       = btfs.ww_unmesh_all_device 
local ww_set_mesh_network_pwd    = btfs.ww_set_mesh_network_pwd
local ww_set_lamp_brightness     = btfs.ww_set_lamp_brightness

local check_upgrade              = upgdfs.check_upgrade
local do_upgrade                 = upgdfs.do_upgrade
local local_upgrade              = upgdfs.local_upgrade

local download_task_operate      = dlfs.download_task_operate
local get_active_list            = dlfs.get_active_list
local get_history_list           = dlfs.get_history_list
local get_pause_list             = dlfs.get_pause_list
local remove_download_result     = dlfs.remove_download_result
local download_task_start        = dlfs.download_task_start
local start_download_intranet    = dlfs.start_download_intranet
local ww_thunder_get_bind_code   = dlfs.ww_thunder_get_bind_code

local table_merge                = bfs.table_merge

local delete_access_token        = dbfs.delete_access_token

function sip_get_parameters(commandId)
	local url = "https://router.meizu.com/oauth/router/command/routerRequest?"
	local https = require("ssl.https")
	local access_token = rts_get_access_token()
	local newurl = url.."access_token="..access_token
	newurl = newurl.."&commandId="..commandId
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, url)
	require "MZLog".log(3, debug.getinfo(1).currentline)
    local res, code, headers, status = https.request(newurl)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, code)
	require "MZLog".log(3, status)
	require "MZLog".log(3, res)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	if code == 401 then
                delete_access_token()
                access_token = rts_get_access_token()
		local newurl = url.."access_token="..access_token
		newurl = newurl.."&commandId="..commandId
                res, code, headers, status = https.request(newurl)
        end

	return res
end

function sip_response_uploader(cmd, commandId, data, finishstatus)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, data)
	if data == nil or data == "" then
		return
	end
	local data = data
	if (type(data) == "string") then
		data = require("luci.http").urlencode(data)
	end
	require "MZLog".log(3, debug.getinfo(1).currentline)
	local url="https://router.meizu.com/oauth/router/command/updateResponse"
	local https = require("ssl.https")
	local timemini = os.date("%s")
	local access_token = rts_get_access_token()
	local pd = init_update_resp_pd(access_token, commandId, data, finishstatus, timemini)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, url)
	require "MZLog".log(3, "sip_response_uploader data len:"..string.len(pd))
	require "MZLog".log(3, pd)
	local res, code, headers, status = https.request(url, pd)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, code)
	require "MZLog".log(3, status)
	require "MZLog".log(3, res)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	if code == 401 then
		delete_access_token()
		access_token = rts_get_access_token()
		pd = init_update_resp_pd(access_token, commandId, data, finishstatus, timemini)
		res, code, headers, status = https.request(url, pd)
	end
	return res
end

function init_update_resp_pd(access_token, commandId, data, finishstatus, timemini)
	local pd = "access_token="..access_token
	pd = pd.."&commandId="..commandId
	pd = pd.."&commandResponse="..(data or "")
	local status = 2
	if finishstatus then
		status = finishstatus
	end
	pd = pd.."&status="..status
	pd = pd.."&lastExcuteTime="..timemini
	return pd
end

function download_list_post_process(data, refine_cnt)
	local new_data = {}
	local jsd = cjson.decode(data)
	local nd_msg = {}
	if type(jsd) == "table" then
		local msg = ""
		for k, v in pairs(jsd) do
			if k and k == "message" then
				msg = v
			else
				new_data[k] = v
			end
		end
		if type(msg) == "table" and _G.next(msg) ~= nil then
			local cnt = 0
			for k, v in pairs(msg) do
				if cnt < refine_cnt then
					table.insert(nd_msg, v)
					cnt = cnt + 1
				end
			end
		end
		if _G.next(nd_msg) ~= nil then
			new_data["message"] = nd_msg
		else
			new_data["message"] = "[]"
		end
	end

	return nd_msg
end

function download_task_operate_process(cmd, cmdid)
	local data = sip_get_parameters(cmdid)
	local jsr = cjson.decode(data)
	local value = jsr.value
	local ret = ""
	if (jsr.code) == "200" then
		for k, v in pairs(value) do
			if k == "commandRequest" then
				local jsr = cjson.decode(v)
				local gid = jsr.gid
				ret = download_task_operate(gid, cmd)
			end
		end
	end
	sip_response_uploader(cmd, cmdid, ret)
end

function ww_exec_reboot(cmd, cmdid)
	local ret = {}
	ret["result"] = true
	sip_response_uploader(cmd, cmdid, data_to_json(ret))
	exec_reboot()
end

sip_cmd_process_action = {
	["getFilesList"] = function (cmd, cmdid)
        local data = sip_get_parameters(cmdid)
        require "MZLog".log(3, debug.getinfo(1).currentline)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local path = nil
		local start = nil
		local count = nil
		local IMEI = nil
		for k, v in pairs(value) do
			if k == "commandRequest" then
				require "MZLog".log(3, v)
				local jsr = cjson.decode(v)
				path  = jsr.path
				start  = jsr.start
				count  = jsr.count
				IMEI  = jsr.IMEI
				require "MZLog".log(3, path)
				require "MZLog".log(3, start)
				require "MZLog".log(3, count)
				require "MZLog".log(3, IMEI)
			end
		end
		path = string.gsub(path, "router", "mnt")
		require "MZLog".log(3, path)
        local data = get_files_list(path, start, count, IMEI)
		require "MZLog".log(3, data)
        --luci.http.write(data)
        sip_response_uploader(cmd, cmdid, data)
    end,
	["downloadstart"] = function (cmd, cmdid)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			for k, v in pairs(value) do
				if k == "commandRequest" then
					local url_jsr = cjson.decode(v)
					local url = url_jsr.url
					local dltype = url_jsr.type
					require "MZLog".log(3, url)
					require "MZLog".log(3, dltype)
					ret = download_task_start(url, dltype)
					require "MZLog".log(3, ret)
				end
			end
		end
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["downloadpause"] = function (cmd, cmdid)
		download_task_operate_process(cmd, cmdid)
	end,
	["downloadunpause"] = function (cmd, cmdid)
		download_task_operate_process(cmd, cmdid)
	end,
	["downloadremove"] = function (cmd, cmdid)
		download_task_operate_process(cmd, cmdid)
	end,
	["getPauseList"] = function (cmd, cmdid)
		local data = get_pause_list()
		--data = download_list_post_process(data, 4)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["getActiveList"] = function (cmd, cmdid)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		local data = sip_get_parameters(cmdid)
		require "MZLog".log(3, data)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			require "MZLog".log(3, debug.getinfo(1).currentline)
			for k, v in pairs(value) do
				require "MZLog".log(3, debug.getinfo(1).currentline)
				if k == "commandRequest" then
					local cr_jsr = cjson.decode(v)
					local start = cr_jsr.start
					local count = cr_jsr.count
					ret = get_active_list(start, count)
				end
			end
		end
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["getHistoryList"] = function (cmd, cmdid)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		local data = sip_get_parameters(cmdid)
		require "MZLog".log(3, data)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			require "MZLog".log(3, debug.getinfo(1).currentline)
			for k, v in pairs(value) do
				require "MZLog".log(3, debug.getinfo(1).currentline)
				if k == "commandRequest" then
					local cr_jsr = cjson.decode(v)
					local start = cr_jsr.start
					local count = cr_jsr.count
					ret = get_history_list(start, count)
				end
			end
		end
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["thundergetbindcode"] = function (cmd, cmdid)
		local data = ww_thunder_get_bind_code()
		sip_response_uploader(cmd, cmdid, data)
	end,
	["realtimenetspeed"] = function(cmd, cmdid)
		local data = data_to_json(real_time_net_speed())
		sip_response_uploader(cmd, cmdid, data, 2)
	end,
	["factoryreset"] = function(cmd, cmdid)
		local data = '{'..'status:"reset factory ok."'..'}'
		local data = factory_reset()
		sip_response_uploader(cmd, cmdid, data_to_json(data))
	end,
	["getDeviceList"] = function(cmd, cmdid)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		local data = ww_get_connect_device_list()
		data = data_to_json(data)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		require "MZLog".log(3, data)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["getwifisettings"] = function(cmd, cmdid)
		require "MZLog".log(3, debug.getinfo(1).currentline)
        local data = sip_get_parameters(cmdid)
        require "MZLog".log(3, data)
        local app_version = nil
        local jsr = cjson.decode(data)
        local value = jsr.value
        for k, v in pairs(value) do
            if k == "commandRequest" then
                if #v > 1 then
                    local value = cjson.decode(v)
                    app_version = value.appVer
                end
            end
        end

        if app_version == nil then
            app_version = 0
        end
        require "MZLog".log(3, app_version)
        require "MZLog".log(3, debug.getinfo(1).currentline)
        local data = ww_get_wifi_settings(app_version)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		require "MZLog".log(3, data)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["setwifisettings"] = function(cmd, cmdid)
		require "MZLog".log(3, debug.getinfo(1).currentline)
        local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
        require "MZLog".log(3, debug.getinfo(1).currentline)

	   	local value = jsr.value
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

		for k, v in pairs(value) do
            if k == "commandRequest" then
                require "MZLog".log(3, debug.getinfo(1).currentline)
                require "MZLog".log(3, v)

                require "MZLog".log(3, debug.getinfo(1).currentline)
                local value = cjson.decode(v)
                require "MZLog".log(3, value)
                local base64 = value.base64
                require "MZLog".log(3, debug.getinfo(1).currentline)
                require "MZLog".log(3, base64)
                local app_version = value.appVer
                if app_version == nil then
                    app_version = 0
                end
                require "MZLog".log(3, app_version)

                if tonumber(app_version) >= 5 then
                    require "MZLog".log(3, debug.getinfo(1).currentline)
                    require "MZLog".log(3, base64)
                    v = b64dec(base64)
                    require "MZLog".log(3, debug.getinfo(1).currentline)
                    require "MZLog".log(3, v)
                    local jsr = cjson.decode(v)
                    for key, value in pairs(jsr) do
                        if value.name == "wl0" then
                            switch_2g = value.on
                            ssid_2g = value.ssid
                            pwd_2g = value.pwd
                            encry_2g = value.encryption
                        elseif value.name == "wl1" then
                            switch_5g = value.on
                            ssid_5g = value.ssid
                            pwd_5g = value.pwd
                            encry_5g = value.encryption
                        else
                            switch_guest = value.on
                            ssid_guest = value.ssid
                            pwd_guest = value.pwd
                            encry_guest = value.encryption
                        end
                    end
                else
                    local value = cjson.decode(v)
                    require "MZLog".log(3, debug.getinfo(1).currentline)
                    require "MZLog".log(3, debug.getinfo(1).currentline)
                    require "MZLog".log(3, value)
                    switch_2g = value.on1
                    ssid_2g = value.ssid1
                    pwd_2g = value.pwd1
                    encry_2g = value.encryption1
                    switch_5g = value.on2
                    ssid_5g = value.ssid2
                    pwd_5g = value.pwd2
                    encry_5g = value.encryption2
                    switch_guest = value.on3
                    ssid_guest = value.ssid3
                    pwd_guest = value.pwd3
                    encry_guest = value.encryption3
                end
            end
        end
        local data = wifi_settings(switch_2g, ssid_2g, pwd_2g, encry_2g, switch_5g, ssid_5g, pwd_5g, encry_5g, switch_guest, ssid_guest, pwd_guest, encry_guest)

        require "MZLog".log(3, debug.getinfo(1).currentline)
		data = cjson.encode(data)
		require "MZLog".log(3, data)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["checkRouterUpdate"] = function(cmd, cmdid)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		local data = check_upgrade()
		require "MZLog".log(3, data)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["executeRouterUpdate"] = function(cmd, cmdid)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		local data = do_upgrade()
		if data ~= "" then
			require "MZLog".log(3, data)
			sip_response_uploader(cmd, cmdid, data)
		end
	end,
	["setDeviceName"] = function(cmd, cmdid)
        local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local mac = nil
		local devicename = nil

		for k, v in pairs(value) do
			if k == "commandRequest" then
				local jsr = cjson.decode(v)
				mac = jsr.mac
			    devicename = jsr.devicename
			end
		end

		require "MZLog".log(3, debug.getinfo(1).currentline)
		local data = set_device_name(mac, devicename)
		require "MZLog".log(3, data)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["setWanSwitch"] = function(cmd, cmdid)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local mac = nil
		local mode = nil
		local enable = nil
		local value = jsr.value
		for k, v in pairs(value) do
			if k == "commandRequest" then
				local jsr = cjson.decode(v)
				mac = jsr.mac
				mode = jsr.mode
				enable = jsr.enable
			end
		end
		local data = set_wan_switch(mac, mode, enable)
		data = data_to_json(data)
		require "MZLog".log(3, data)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["setDiskAccess"] = function(cmd, cmdid)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local mac = nil
		local enable = nil
		local value = jsr.value
		for k, v in pairs(value) do
			if k == "commandRequest" then
				local jsr = cjson.decode(v)
				mac = jsr.mac
				enable = jsr.enable
			end
		end
		local data = set_disk_access(mac, enable)
		require "MZLog".log(3, data)
		data = data_to_json(data)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["getSmbSwitch"] = function(cmd, cmdid)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		local data = get_smb_switch()
		require "MZLog".log(3, data)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["setSmbSwitch"] = function(cmd, cmdid)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local smbswitch = nil
		local value = jsr.value

		for k, v in pairs(value) do
			if k == "commandRequest" then
				local  jsr = cjson.decode(v)
				smbswitch = jsr.smbswitch
			end
		end
		local data = set_smb_switch(smbswitch)
		require "MZLog".log(3, data)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["setReboot"] = function(cmd, cmdid)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		ww_exec_reboot(cmd, cmdid)
	end,
	["getdevicedetails"] = function(cmd, cmdid)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			for k, v in pairs(value) do
				if k == "commandRequest" then
					local jsr = cjson.decode(v)
					local mac = jsr.mac
					ret = data_to_json(ww_get_device_details(mac))
				end
			end
		end
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["getDiskInfo"] = function(cmd, cmdid)
		local data = ww_get_disk_info()
		data = data_to_json(data)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["getsysinfo"] = function(cmd, cmdid)
		local data = require "meizu.bfs".sysinfo()
		data = data_to_json(data)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["gettxpowermode"] = function(cmd, cmdid)
		local ret = ww_get_tx_power_mode()
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["settxpowermode"] = function(cmd, cmdid)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			for k, v in pairs(value) do
				if k == "commandRequest" then
					local jsr = cjson.decode(v)
					local mode = jsr.mode
					ret = data_to_json(ww_set_tx_power_mode(mode))
				end
			end
		end
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["getWirelessChannel"] = function(cmd, cmdid)
		local ret = ww_get_wireless_channel()
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["setWirelessChannel"] = function(cmd, cmdid)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			for k, v in pairs(value) do
				if k == "commandRequest" then
					local jsr = cjson.decode(v)
					local channel = jsr.channel
					ret = data_to_json(ww_set_wireless_channel(channel))
				end
			end
		end
	end,

	["scanBleSwitch"] = function(cmd, cmdid)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			for k, v in pairs(value) do
				if k == "commandRequest" then
					local jsr = cjson.decode(v)
					local status = jsr.status
					ret = ww_scan_ble_switch(status)
				end
			end
		end
		require "MZLog".log(3, ret)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["getBleDeviceList"] = function(cmd, cmdid)
		local data = ww_get_ble_device_list()
		require "MZLog".log(3, data)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["addMeshDevice"] = function(cmd, cmdid) 
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			for k, v in pairs(value) do
				if k == "commandRequest" then
					local jsr = cjson.decode(v)
					local mac = jsr.mac
					ret = ww_add_ble_mesh_device(mac)
				end
			end
		end
		require "MZLog".log(3, ret)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["getMeshDeviceDetail"] = function(cmd, cmdid) 
		require "MZLog".log(3, debug.getinfo(1).currentline)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		require "MZLog".log(3, debug.getinfo(1).currentline)
		require "MZLog".log(3, value)		
		if (jsr.code) == "200" then
			for k, v in pairs(value) do
				if k == "commandRequest" then
					local jsr = cjson.decode(v)
					local mac = jsr.mac
					require "MZLog".log(3, debug.getinfo(1).currentline)
					require "MZLog".log(3, mac)
					require "MZLog".log(3, debug.getinfo(1).currentline)
					ret = ww_get_ble_device_status(mac)
				end
			end
		end
		require "MZLog".log(3, ret)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["removeblefrommesh"] = function(cmd, cmdid)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			for k, v in pairs(value) do
				if k == "commandRequest" then
					local jsr = cjson.decode(v)
					local mac = jsr.mac
					ret = ww_remove_ble_from_mesh(mac)
				end
			end
		end
		require "MZLog".log(3, ret)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["getMeshDeviceList"] = function(cmd, cmdid)
		local data = ww_get_mesh_device_list()
		require "MZLog".log(3, data)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["setMeshDeviceAttr"] = function(cmd, cmdid)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			for k, v in pairs(value) do
				if k == "commandRequest" then
					local jsr = cjson.decode(v)
					local mac = jsr.mac
					local key = jsr.key
					local value = jsr.value
					ret = ww_set_mesh_device_attr(mac, key, value)
				end
			end
		end
		require "MZLog".log(3, ret)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["rebootmeshdevice"] = function(cmd, cmdid)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			for k, v in pairs(value) do
				if k == "commandRequest" then
					local jsr = cjson.decode(v)
					local mac = jsr.mac
					ret = ww_reboot_mesh_device(mac)
				end
			end
		end
		require "MZLog".log(3, ret)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["unmeshalldevice"] = function(cmd, cmdid)
		local data = ww_unmesh_all_device()
		require "MZLog".log(3, data)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, data)
	end,
	["setmeshdevicetimer"] = function(cmd, cmdid)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			for k, v in pairs(value) do
				if k == "commandRequest" then
					local jsr = cjson.decode(v)
					local mac = jsr.mac
					local timer_id = jsr.timerId
					local flag = jsr.flag
					local start = jsr.start
					local ends = jsr.ends
					ret = ww_set_mesh_device_timer(mac, timer_id, flag, start, ends)
				end
			end
		end
		require "MZLog".log(3, ret)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["setMeshNetWorkPassword"] = function(cmd, cmdid)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			for k, v in pairs(value) do
				if k == "commandRequest" then
					local jsr = cjson.decode(v)
					local old_key = jsr.oldkey
					local new_key = jsr.newkey
					ret = ww_set_mesh_network_pwd(old_key, new_key)
				end
			end
		end
		require "MZLog".log(3, ret)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["setLampBrightness"] = function(cmd, cmdid)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			for k, v in pairs(value) do
				if k == "commandRequest" then
					local jsr = cjson.decode(v)
					local mac = jsr.mac
					local timer_id = jsr.timerId
					local flag = jsr.flag
					local start = jsr.start
					local ends = jsr.ends
					ret = ww_set_lamp_brightness(mac, timer_id, flag, start, ends)
				end
			end
		end
		require "MZLog".log(3, ret)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, ret)
	end,
	["delmeshdevicetimer"] = function(cmd, cmdid)
		local data = sip_get_parameters(cmdid)
		local jsr = cjson.decode(data)
		local value = jsr.value
		local ret = ""
		if (jsr.code) == "200" then
			for k, v in pairs(value) do
				if k == "commandRequest" then
					local jsr = cjson.decode(v)
					local mac = jsr.mac
					local timer_id = jsr.timerId
					ret = ww_del_mesh_device_timer(mac, timer_id)
				end
			end
		end
		require "MZLog".log(3, ret)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		sip_response_uploader(cmd, cmdid, ret)
	end,
}

--"{ \"size\": \"14.12MB\", \"version\": \"1.0.10\" }"
function OTA_process_action(vs_info)
	require "MZLog".log(3, "get OTA new Version:")
	require "MZLog".log(3, vs_info)
	require "meizu.upgdfs".push_new_version_msg()
end

--sip data format:
--{ "push_event": [ { "appid": "com.meizu.router", "data": { "business": "1", "commandId": "54", "type": "realtimenetspeed" } } ] }
--post d = '{"business":"1","commandId":"53","type":"speed"}'
function sip()
	local ret;
	--local data = '{"business":"1","commandId":"53","type":"speed"}'
	local data = luci.http.formvalue("d")
	require "MZLog".log(3, data)
	if data ~= nil then
		local data = b64dec(data)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		require "MZLog".log(3, data)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		local jsr = cjson.decode(data)
		if jsr.type ~= nil then
			ret = sip_cmd_process_action[jsr.type](jsr.type, jsr.commandId)
		else
			if jsr["com.meizu.router"] ~= nil then
				require "MZLog".log(3, debug.getinfo(1).currentline)
				require "MZLog".log(3, "OTA push message:")
				require "MZLog".log(3, data)
				require "MZLog".log(3, debug.getinfo(1).currentline)
				OTA_process_action(jsr["com.meizu.router"])
			end
		end
	end
	luci.http.write_json("sip done.")
end

function pysip()
	local ret;
	local cmd = luci.http.formvalue("cmd")
	local commandId = luci.http.formvalue("commandId")
	if cmd ~= nil then
		ret = sip_cmd_process_action[cmd](cmd, commandId)
	end
	luci.http.write_json("pysip: "..cmd.." "..commandId.." done.")
	luci.http.close()
end

function upload_router_log(logdata, logtype)
	local https = require("ssl.https")
	local url="https://router.meizu.com/oauth/router/upLog"
	local access_token = rts_get_access_token()
	local pd = init_upload_router_log_pd(access_token, logtype, logdata)
	local res, code, headers, status = https.request(url, pd)
	if code == 401 then
		delete_access_token()
		access_token = rts_get_access_token()
		pd = init_upload_router_log_pd(access_token, logtype, logdata)
		res, code, headers, status = https.request(url, pd)
	end
	return res, code, headers, status
end

function init_upload_router_log_pd(access_token, logtype, logdata)
	local pd = "access_token="..access_token
	if logtype then
		pd = pd.."&type="..logtype
	else
		pd = pd.."&type=".."4"
	end
	pd = pd.."&content="..(logdata or "")
	return pd
end

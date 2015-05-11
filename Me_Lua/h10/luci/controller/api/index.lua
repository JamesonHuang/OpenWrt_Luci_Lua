module("luci.controller.api.index", package.seeall)

local bfs    = require "meizu.bfs"
local cjson  = require "cjson"
local disk   = require "meizu.disk"
local dlfs   = require "meizu.dlfs"
local lfs    = require "lfs"
local lue    = require("luci.util").exec
local nwfs   = require "meizu.nwfs"
local RC     = require "meizu.r10config"
local sipfs  = require "meizu.sipfs"
local upgdfs = require "meizu.upgdfs"
local btfs   = require "meizu.btfs"

b64dec                     = bfs.b64dec
batchfile_checklist        = bfs.batchfile_checklist
batchfile_compare_upload   = bfs.batchfile_compare_upload
bind_router                = bfs.bind_router
data_to_json               = bfs.data_to_json
exec_cmd_in_sh             = bfs.exec_cmd_in_sh
exec_reboot                = bfs.exec_reboot
findInDir                  = bfs.findInDir
get_device_SN              = bfs.get_device_SN
get_device_version         = bfs.get_device_version
get_https_data             = bfs.get_https_data
getFilesList               = bfs.getFilesList
factory_reset              = bfs.factory_reset
rts_get_access_token       = bfs.rts_get_access_token
set_passwd                 = bfs.set_passwd
silent_upgrade             = bfs.silent_upgrade

nw_get_disk_info           = disk.nw_get_disk_info
disk_formatting            = disk.disk_formatting

sip                        = sipfs.sip
pysip                      = sipfs.pysip
upload_router_log          = sipfs.upload_router_log

nw_check_sys_password      = nwfs.nw_check_sys_password
nw_get_connect_device_list = nwfs.nw_get_connect_device_list
nw_get_device_details      = nwfs.nw_get_device_details
nw_get_wan_type            = nwfs.nw_get_wan_type
nw_get_smbswitch           = nwfs.nw_get_smbswitch
nw_get_wifi_settings       = nwfs.nw_get_wifi_settings
nw_set_device_name         = nwfs.nw_set_device_name
nw_set_disk_access         = nwfs.nw_set_disk_access
nw_set_wan_switch          = nwfs.nw_set_wan_switch
nw_set_wan_type            = nwfs.nw_set_wan_type
nw_set_smbswitch           = nwfs.nw_set_smbswitch
nw_wifi_settings           = nwfs.nw_wifi_settings
nw_get_tx_power_mode       = nwfs.nw_get_tx_power_mode
nw_set_tx_power_mode       = nwfs.nw_set_tx_power_mode
nw_get_wireless_channel    = nwfs.nw_get_wireless_channel
nw_set_wireless_channel    = nwfs.nw_set_wireless_channel

get_connect_info           = nwfs.get_connect_info
get_net_device             = nwfs.get_net_device
real_time_net_speed        = nwfs.real_time_net_speed

nw_scan_ble_switch         = btfs.nw_scan_ble_switch         
nw_get_ble_device_list     = btfs.nw_get_ble_device_list
nw_add_ble_mesh_device     = btfs.nw_add_ble_mesh_device
nw_get_ble_device_status   = btfs.nw_get_ble_device_status
nw_get_mesh_device_list    = btfs.nw_get_mesh_device_list
nw_remove_ble_from_mesh    = btfs.nw_remove_ble_from_mesh
nw_dismiss_mesh            = btfs.nw_dismiss_mesh
nw_set_mesh_device_attr    = btfs.nw_set_mesh_device_attr
nw_reboot_mesh_device      = btfs.nw_reboot_mesh_device
nw_unmesh_all_device       = btfs.nw_unmesh_all_device
nw_set_mesh_device_timer   = btfs.nw_set_mesh_device_timer
nw_del_mesh_device_timer   = btfs.nw_del_mesh_device_timer
nw_set_mesh_network_pwd    = btfs.nw_set_mesh_network_pwd
nw_set_lamp_brightness     = btfs.nw_set_lamp_brightness

check_upgrade              = upgdfs.check_upgrade
do_upgrade                 = upgdfs.do_upgrade
local_upgrade              = upgdfs.local_upgrade

nw_download_task_operate   = dlfs.nw_download_task_operate
nw_get_active_list         = dlfs.nw_get_active_list
nw_get_history_list        = dlfs.nw_get_history_list
--nw_get_pause_list          = dlfs.nw_get_pause_list
nw_thunder_get_bind_code   = dlfs.nw_thunder_get_bind_code

nw_download_task_start          = dlfs.nw_download_task_start

function index()
	--nw: abridged for "Nei Wang"; ww abridged for "Wai Wang"
	local root = node()
	if not root.target then
		root.target = alias("api")
		root.index = true
	end
	local page  = node("api")
	page.target = firstchild()
	page.title  = _("api")
	page.order  = 10
	page.index  = true
	page.sysauth = "root"
	page.sysauth_authenticator = "htmlauth"
	--page = entry({"api", "getUserAccessToken"}, call("get_user_access_token"), nil, nil)
	--page.leaf = true
	page = entry({"api", "bindRouter"}, call("bind_router"), nil, nil)
	page.leaf = true
	--page = entry({"api", "unbindRouter"}, call("unbind_router"), nil, nil)
	--page.leaf = true
	--page = entry({"api", "getDeviceAccessToken"}, call("rts_get_access_token"), nil, nil)
	--page.leaf = true

	page = entry({"api", "sip"}, call("sip"), nil, nil)
	page.leaf = true
	page = entry({"api", "pysip"}, call("pysip"), nil, nil)
	page.leaf = true

	page = entry({"api", "getconnectinfo"}, call("get_connect_info"), nil)
	page.leaf = true
	page = entry({"api", "getWifiSettings"}, call("nw_get_wifi_settings"), nil)
	page.leaf = true
	page = entry({"api", "getConnectDeviceList"}, call("nw_get_connect_device_list"), nil)
	page.leaf = true
	page = entry({"api", "getdevicedetails"}, call("nw_get_device_details"), nil)
	page.leaf = true
	page = entry({"api", "getNetDevice"}, call("get_net_device"), nil)
	page.leaf = true
	page = entry({"api", "getWanType"}, call("nw_get_wan_type"), nil)
	page.leaf = true
	page = entry({"api", "realtimenetspeed"}, call("nw_real_time_net_speed"), nil, nil)
	page.leaf = true
	page = entry({"api", "setWanType"}, call("nw_set_wan_type"), nil)
	page.leaf = true
	page = entry({"api", "setDeviceName"}, call("nw_set_device_name"), nil)
	page.leaf = true
	page = entry({"api", "setDiskAccess"}, call("nw_set_disk_access"), nil)
	page.leaf = true
	page = entry({"api", "setWanSwitch"}, call("nw_set_wan_switch"), nil)
	page.leaf = true
	page = entry({"api", "wifiSettings"}, call("nw_wifi_settings"), nil)
	page.leaf = true
	page = entry({"api", "gettxpowermode"}, call("nw_get_tx_power_mode"), nil)
	page.leaf = true
	page = entry({"api", "settxpowermode"}, call("nw_set_tx_power_mode"), nil)
	page.leaf = true
	page = entry({"api", "getWirelessChannel"}, call("nw_get_wireless_channel"), nil)
	page.leaf = true
	page = entry({"api", "setWirelessChannel"}, call("nw_set_wireless_channel"), nil)
	page.leaf = true

	page = entry({"api", "diskinfo"}, call("nw_get_disk_info"), nil)
	page.leaf = true
	page = entry({"api", "diskformat"}, call("disk_formatting"), nil, nil)
	page.leaf = true

	page = entry({"api", "factoryreset"}, call("factory_reset"), nil, nil)
	page.leaf = true
	page = entry({"api", "reboot"}, call("nw_exec_reboot"), nil, nil)
	page.leaf = true
	page = entry({"api", "localupgrade"}, call("local_upgrade"), nil, nil)
	page.leaf = true
	page = entry({"api", "silentupgrade"}, call("silent_upgrade"), nil, nil)
	page.leaf = true

	page = entry({"api", "checkSysPassword"}, call("nw_check_sys_password"), nil)
	page.leaf = true
	page = entry({"api", "setpasswd"}, call("set_passwd"), nil, nil)
	page.leaf = true

	page = entry({"api", "downloadstart"}, call("nw_download_task_start"), nil, nil)
	page.leaf = true
	page = entry({"api", "getActiveList"}, call("nw_get_active_list"), nil, nil)
	page.leaf = true
	page = entry({"api", "getHistoryList"}, call("nw_get_history_list"), nil, nil)
	page.leaf = true
	page = entry({"api", "operateTask"}, call("nw_download_task_operate"), nil, nil)
	page.leaf = true
	page = entry({"api", "getPauseList"}, call("nw_get_pause_list"), nil, nil)
	page.leaf = true
	page = entry({"api", "thunder_get_bind_code"}, call("nw_thunder_get_bind_code"), nil, nil)
	page.leaf = true

	page = entry({"api", "batchFileCompareUpload"}, call("batchfile_compare_upload"), nil, nil)
	page.leaf = true
	page = entry({"api", "batchFileCheckList"}, call("batchfile_checklist"), nil, nil)
	page.leaf = true
	page = entry({"api", "getFilesList"}, call("getFilesList"), nil, nil)
	page.leaf = true
	--page = entry({"api", "setPPPoE"}, call("set_pppoe"), nil)
	--page.leaf = true

	page = entry({"api", "checkupgrade"}, call("nw_check_upgrade"), nil)
	page.leaf = true
	page = entry({"api", "doupgrade"}, call("nw_do_upgrade"), nil)
	page.leaf = true

	page = entry({"api", "setsmbsingleswitch"}, call("setsmbsingleswitch"),nil)
	page.leaf = true
	page = entry({"api", "setsmbswitch"}, call("nw_set_smbswitch"), nil)
	page.leaf = true
	page = entry({"api", "getsmbsingleswitch"}, call("getsmbsingleswitch"),nil)
	page.leaf = true
	page = entry({"api", "getsmbswitch"}, call("nw_get_smbswitch"), nil)
	page.leaf = true
	
	page = entry({"api", "scanBleSwitch"}, call("nw_scan_ble_switch"), nil)
	page.leaf = true
	page = entry({"api", "getBleDeviceList"}, call("nw_get_ble_device_list"), nil)
	page.leaf = true
	page = entry({"api", "addMeshDevice"}, call("nw_add_ble_mesh_device"), nil)
	page.leaf = true
	page = entry({"api", "removeBleFromMesh"}, call("nw_remove_ble_from_mesh"), nil)
	page.leaf = true
	page = entry({"api", "getMeshDeviceDetail"}, call("nw_get_ble_device_status"), nil)
	page.leaf = true
	page = entry({"api", "getMeshDeviceList"}, call("nw_get_mesh_device_list"), nil)
	page.leaf = true
	page = entry({"api", "dismissMesh"}, call("nw_dismiss_mesh"), nil)
	page.leaf = true
	page = entry({"api", "setMeshDeviceAttr"}, call("nw_set_mesh_device_attr"), nil)
	page.leaf = true
	page = entry({"api", "rebootMeshDevice"}, call("nw_reboot_mesh_device"), nil)
	page.leaf = true
	page = entry({"api", "unmeshAllDevice"}, call("nw_unmesh_all_device"), nil)
	page.leaf = true
	page = entry({"api", "setMeshDeviceTimer"}, call("nw_set_mesh_device_timer"), nil)
	page.leaf = true
	page = entry({"api", "delMeshDeviceTimer"}, call("nw_del_mesh_device_timer"), nil)
	page.leaf = true
	page = entry({"api", "setMeshNetWorkPassword"}, call("nw_set_mesh_network_pwd"), nil)
	page.leaf = true
	page = entry({"api", "setLampBrightness"}, call("nw_set_lamp_brightness"), nil)
	page.leaf = true
	
    
end

function nw_check_upgrade()
	local ret = check_upgrade()
	luci.http.write(ret)
end

function nw_do_upgrade()
	local ret = {}
	luci.http.status(200, "upgrading....")
	ret["code"] = 2004
	ret["result"] = "upgrading...."
	luci.http.write(data_to_json(ret))
	do_upgrade()
end

function nw_real_time_net_speed()
	luci.http.prepare_content("application/json")
    local result = real_time_net_speed()
	luci.http.write_json(result)
end

function setsmbsingleswitch()
	--local mac = luci.http.formvalue("mac")
	--generate a white/black list to store this mac.
	--if you want a mac not rw hdd, then write his mac into deny list.
	--if you want a mac rw hdd, write his mac into allow list.
	--modify smb.conf to ban this user's access.
	--restart samba service
end

function setsmbswitch()
	local result = {}
	local code = false
	local onoff = luci.http.formvalue("smbswitch")
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
	luci.http.write_json(result)

end

function getsmbsingleswitch()
--
end

function getsmbswitch()
	local smbswitch = {}
	local code = false
	code = luci.sys.init.enabled("samba")
	smbswitch["smbswitch"] = code
	luci.http.write_json(smbswitch)
end

function nw_exec_reboot()
	local ret = {}
	ret["result"] = true
	luci.http.write_json(ret)
	exec_reboot()
end

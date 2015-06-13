module("luci.controller.api.index", package.seeall)

local bfs    = require "meizu.bfs"
local cjson  = require "cjson"
local lfs    = require "lfs"
local lue    = require("luci.util").exec
local nwfs   = require "meizu.nwfs"
local RC     = require "meizu.r10config"
local sipfs  = require "meizu.sipfs"
local upgdfs = require "meizu.upgdfs"
local btfs   = require "meizu.btfs"

bind_router                = bfs.bind_router
data_to_json               = bfs.data_to_json
exec_cmd_in_sh             = bfs.exec_cmd_in_sh
exec_reboot                = bfs.exec_reboot
factory_reset              = bfs.factory_reset
set_passwd                 = bfs.set_passwd
silent_upgrade             = bfs.silent_upgrade

sip                        = sipfs.sip
pysip                      = sipfs.pysip
upload_router_log          = sipfs.upload_router_log

nw_check_sys_password      = nwfs.nw_check_sys_password
nw_get_connect_device_list = nwfs.nw_get_connect_device_list
nw_get_device_details      = nwfs.nw_get_device_details
nw_get_wan_type            = nwfs.nw_get_wan_type
nw_get_wifi_settings       = nwfs.nw_get_wifi_settings
nw_set_device_name         = nwfs.nw_set_device_name
nw_set_wan_switch          = nwfs.nw_set_wan_switch
nw_set_wan_type            = nwfs.nw_set_wan_type
nw_wifi_settings           = nwfs.nw_wifi_settings
nw_get_wireless_channel    = nwfs.nw_get_wireless_channel
nw_set_wireless_channel    = nwfs.nw_set_wireless_channel
nw_get_led                 = nwfs.nw_get_led
nw_set_led                 = nwfs.nw_set_led
nw_get_speed_limit         = nwfs.nw_get_speed_limit
nw_set_speed_limit         = nwfs.nw_set_speed_limit
set_smart_wifi_updown      = nwfs.set_smart_wifi_updown 
set_wifi_up                = nwfs.set_wifi_up
set_wifi_down              = nwfs.set_wifi_down
set_smart_wifi_update      = nwfs.set_smart_wifi_update
set_smart_wifi_stop        = nwfs.set_smart_wifi_stop


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

get_net_device             = nwfs.get_net_device
real_time_net_speed        = nwfs.real_time_net_speed

check_upgrade              = upgdfs.check_upgrade
do_upgrade                 = upgdfs.do_upgrade
local_upgrade              = upgdfs.local_upgrade

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
	page.sysauth = "admin"
	page.sysauth_authenticator = "htmlauth"

	page = entry({"api", "searchrouter"}, call("nw_search_router"), nil)
	page.leaf = true

	page = entry({"api", "bindRouter"}, call("bind_router"), nil, nil)
	page.leaf = true

	page = entry({"api", "sip"}, call("sip"), nil, nil)
	page.leaf = true
	page = entry({"api", "pysip"}, call("pysip"), nil, nil)
	page.leaf = true

    --rh
	page = entry({"api", "wifiUp"}, call("set_wifi_up"), nil)
	page.leaf = true
	page = entry({"api", "wifiDown"}, call("set_wifi_down"), nil)
	page.leaf = true
	page = entry({"api", "setSmartWifiUpdate"}, call("set_smart_wifi_update"), nil)
	page.leaf = true
	page = entry({"api", "setSmartWifiStop"}, call("set_smart_wifi_stop"), nil)
	page.leaf = true
	page = entry({"api", "setSmartWifiUpdown"}, call("set_smart_wifi_updown"), nil)
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
	page = entry({"api", "setWanSwitch"}, call("nw_set_wan_switch"), nil)
	page.leaf = true
	page = entry({"api", "wifiSettings"}, call("nw_wifi_settings"), nil)
	page.leaf = true
	page = entry({"api", "getWirelessChannel"}, call("nw_get_wireless_channel"), nil)
	page.leaf = true
	page = entry({"api", "setWirelessChannel"}, call("nw_set_wireless_channel"), nil)
	page.leaf = true
	page = entry({"api", "getLed"}, call("nw_get_led"), nil)
	page.leaf = true
	page = entry({"api", "setLed"}, call("nw_set_led"), nil)
	page.leaf = true
	page = entry({"api", "getSpeedLimit"}, call("nw_get_speed_limit"), nil)
	page.leaf = true
	page = entry({"api", "setSpeedLimit"}, call("nw_set_speed_limit"), nil)
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

	page = entry({"api", "doupgrade"}, call("nw_do_upgrade"), nil)
	page.leaf = true
	page = entry({"api", "checkupgrade"}, call("nw_check_upgrade"), nil)
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

function nw_exec_reboot()
	local ret = {}
	ret["result"] = true
	luci.http.write_json(ret)
	exec_reboot()
end

function nw_search_router()
	local wl_type_val = luci.http.formvalue("type")
	local wireless_dev = "ra0"
	local wireless_type = "2.4G"
	if wl_type_val == "2" then
		wireless_type = "5G"
		wireless_dev = "rai0"
	end
	require "MZLog".log(3, wl_type_val)
	require "MZLog".log(3, wireless_type)
	local sub = require "string".sub
	local trim = require "string".trim
	local ssid_table = {}
	local cmd = [[ap_scan.sh ]]..wireless_dev..[[| grep "^[0-9]"]]
	local ssids = io.popen(cmd)
	local ln = 1
	require "MZLog".log(3, debug.getinfo(1).currentline)
	for line in ssids:lines() do
		require "MZLog".log(3, debug.getinfo(1).currentline)
		require "MZLog".log(3, line)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		local assid = {}

		local channel = trim(sub(line, 1,  4))
		local ssid    = trim(sub(line, 5,  37))
		local mac     = trim(sub(line, 38, 54))
		local sec     = trim(sub(line, 58, 79))
		local rss     = trim(sub(line, 81, 82))
		local extch   = trim(sub(line, 98, 103))
		assid["mac"]     = mac
		assid["rss"]     = rss
		assid["sec"]     = sec
		assid["ssid"]    = ssid
		assid["type"]    = wireless_type
		assid["channel"] = channel
		assid["extch"]   = extch

		ssid_table[ln] = assid
		ln = ln + 1
	end
	ssids:close()
	require "MZLog".log(3, debug.getinfo(1).currentline)
	luci.http.write_json(ssid_table)
end

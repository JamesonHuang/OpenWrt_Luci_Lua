local bfs    = require("meizu.bfs")
local dbfs   = require("meizu.dbfs")
local upgdfs = require("meizu.upgdfs")

local get_device_SN        = bfs.get_device_SN
local get_device_version   = bfs.get_device_version
local get_https_data       = bfs.get_https_data
local rts_get_access_token = bfs.rts_get_access_token

local delete_access_token  = dbfs.delete_access_token

function getBasicInfo()
	local sn = get_device_SN()
	local url = "https://router.meizu.com/oauth/router/getBasicInfo?access_token="
	local access_token = rts_get_access_token()
	local newurl = url..access_token.."&device="..sn
	local res, code, headers, status = get_https_data(newurl)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, newurl)
	require "MZLog".log(3, res)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	if code == 401 then
                delete_access_token()
                access_token = rts_get_access_token()
		newurl = url..access_token.."&device="..sn
		res, code, headers, status = get_https_data(newurl)
        end
end

function upRouterPushId()
	local sn = get_device_SN()
	local pushId = sn.."100032"
	local url="https://router.meizu.com/oauth/router/upRouterPushId"
	local access_token = rts_get_access_token()
	local postData = "access_token="..access_token.."&pushId="..pushId
	local res, code, headers, status = get_https_data(url, postData)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, url)
	require "MZLog".log(3, res)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	if code == 401 then
		delete_access_token()
		access_token = rts_get_access_token()
		postData = "access_token="..access_token.."&pushId="..pushId
		res, code, headers, status = get_https_data(url, postData)
        end
end

function report_rom_version()
	local ver = get_device_version()
	local url="https://router.meizu.com/oauth/router/updateStatus"
	local access_token = rts_get_access_token()
	local data = "&name=romversion&value="..ver
	local pd = "access_token="..access_token..data
	local res, code, headers, status = get_https_data(url, pd)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, url)
	require "MZLog".log(3, res)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	if code == 401 then
		delete_access_token()
		access_token = rts_get_access_token()
		pd = "access_token="..access_token..data
		res, code, headers, status = get_https_data(url, pd)
	end
end

function report_uptime()
	local timestamp = os.time()
	local url="https://router.meizu.com/oauth/router/updateStatus"
	local access_token = rts_get_access_token()
	local data = "&name=uptime&value="..timestamp
	local pd = "access_token="..access_token..data
	local res, code, headers, status = get_https_data(url, pd)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, url)
	require "MZLog".log(3, res)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	if code == 401 then
		delete_access_token()
		access_token = rts_get_access_token()
		pd = "access_token="..access_token..data
		res, code, headers, status = get_https_data(url, pd)
	end
end
function check_upgrade_status()
	local lu = require("luci.util")
	local cmd = [[nvram get upgrading|awk '{printf $1}']]
	local ret = lu.exec(cmd)
	if ret == "1" then
		local res, code, headers, status = upgdfs.push_upgrade_finish_msg()
		require "MZLog".log(3, debug.getinfo(1).currentline)
		require "MZLog".log(3, "check_upgrade_status")
		require "MZLog".log(3, res)
		require "MZLog".log(3, code)
		require "MZLog".log(3, headers)
		require "MZLog".log(3, status)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		cmd = [[nvram unset upgrading;nvram commit;]]
		lu.exec(cmd)
	end
	return ret
end

function ping_rts_server()
	local lu = require("luci.util")
	local cmd = "sleep 2;ping -W 2 -c 1 router.meizu.com > /dev/null ;echo -n $?"
	local ret = lu.exec(cmd)
	return ret
end

function ut()
	local lu = require("luci.util")
	local cmd = "uptime"
	local ret = lu.exec(cmd)
	require "MZLog".log(3, ret)
end


ut()

dbfs.init_wireless_device_table()
dbfs.init_wire_device_table()

require "MZLog".log(3, "start ping_rts_server()")
local ret = ping_rts_server()
while ret ~= "0" do
	ret = ping_rts_server()
end
ut()
require "MZLog".log(3, "end ping_rts_server()")
require "MZLog".log(3, "getBasicInfo()")
getBasicInfo()
report_rom_version()
report_uptime()
require "MZLog".log(3, "upRouterPushId()")
upRouterPushId()
ut()
require "MZLog".log(3, "upgdfs.subscribe_auto_upgrade")
local res, code, headers, status = upgdfs.subscribe_auto_upgrade()
ut()
require "MZLog".log(3, res, code, headers, status)
ut()
check_upgrade_status()

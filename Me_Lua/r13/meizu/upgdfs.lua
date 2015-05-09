module("meizu.upgdfs", package.seeall)

local cjson = require "cjson"
local bfs   = require "meizu.bfs"
local sipfs = require "meizu.sipfs"

local b64dec                   = bfs.b64dec
local batchfile_checklist      = bfs.batchfile_checklist
local batchfile_compare_upload = bfs.batchfile_compare_upload
local bind_router              = bfs.bind_router
local cal_str_md5              = bfs.cal_str_md5
local data_to_json             = bfs.data_to_json
local exec_cmd_in_sh           = bfs.exec_cmd_in_sh
local exec_reboot              = bfs.exec_reboot
local findInDir                = bfs.findInDir
local get_device_SN            = bfs.get_device_SN
local get_device_version       = bfs.get_device_version
local get_https_data           = bfs.get_https_data
local rts_get_access_token     = bfs.rts_get_access_token
local set_passwd               = bfs.set_passwd
local silent_upgrade           = bfs.silent_upgrade
local table_merge              = bfs.table_merge

function upgrade_lock()
    return os.execute(RC.upgrade_lock)
end

function upgrade_unlock()
    return os.execute(RC.upgrade_unlock)
end

function push_new_version_msg()
	--type:4  （有新固件更新）
	--msg = "{\"size\": \"14.12MB\", \"version\": \"1.0.10\" }"
	local logtype = 4
	local msg = check_upgrade()
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, "OTA push_new_version_msg:"..data)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	sipfs.upload_router_log(msg, logtype)
end

function push_fw_upgrading_msg(url)
	local logtype = 6
	local msg = {}
	msg["code"] = 2005
	msg["result"] = "upgrading...."
	msg = data_to_json(msg)
	local res, code, headers, status = sipfs.upload_router_log(msg, logtype)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, res)
	require "MZLog".log(3, code)
	require "MZLog".log(3, headers)
	require "MZLog".log(3, status)
	require "MZLog".log(3, debug.getinfo(1).currentline)
end

function push_upgrade_finish_msg()
	--(5) type:5  （固件更新完成）
	local logtype = 5
	local msg = {}
	--"content": "{ \"version\": \"5.0\", \"size\": \"14088999\" }"
	msg["version"] = get_device_version()
	msg["size"] = 0
	msg = data_to_json(msg)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, "push_upgrade_finish_msg:"..msg)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	local res, code, headers, status = sipfs.upload_router_log(msg, logtype)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, res)
	require "MZLog".log(3, code)
	require "MZLog".log(3, headers)
	require "MZLog".log(3, status)
	require "MZLog".log(3, debug.getinfo(1).currentline)
end

function subscribe_auto_upgrade()
	local http = require("socket.http")
	local url = "http://u.meizu.com/api/v1/upgrade/subscribe"
	local serviceCode = "com.meizu.router"
	local pd = "serviceCode="..serviceCode
	local sn = get_device_SN()
	local sipToken = sn.."100032"
	pd = pd.."&sipToken="..sipToken
	local device = sn
	pd = pd.."&device="..device
	local version = get_device_version()
	pd = pd.."&version="..version
	local deviceModel = "R13"
	pd = pd.."&deviceModel="..deviceModel
	local key = "2635881a7ab0593849fe89e685fc56cd"
	local toSignStr = serviceCode..sipToken..version..key
	require "MZLog".log(3, url)
	require "MZLog".log(3, pd)
	pd = pd.."&sign="..cal_str_md5(toSignStr)
	local res, code, headers, status = http.request(url, pd)

	require "MZLog".log(3, res, code, headers, status)
	return res, code, headers, status
end

function gen_check_fw_url_pd()
	local serviceCode = "com.meizu.router"
	local pd = "serviceCode="..serviceCode
	local sn = get_device_SN()
	local device = sn
	pd = pd.."&device="..device
	local deviceModel = "R13"
	pd = pd.."&deviceModel="..deviceModel
	local root = "true"
	pd = pd.."&root="..root
	local version = get_device_version()
	pd = pd.."&version="..version
	local key = "2635881a7ab0593849fe89e685fc56cd"
	local toSignStr = serviceCode..device..deviceModel..root..version..key
	pd = pd.."&sign="..cal_str_md5(toSignStr)

	return pd
end

function check_upgrade()
	local ret = {}
	local http = require("socket.http")
	local url = "http://u.meizu.com/api/v1/upgrade/check/router"
	local pd = gen_check_fw_url_pd()
	local res, code, headers, status = http.request(url, pd)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, pd)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	if res == nil then
		require "MZLog".log(3, "get "..url.." failed!")
		ret["code"] = code;
		return data_to_json(ret)
	else
		return res;
	end
end

function do_upgrade()
	local ret = {}
	local http = require("socket.http")
	local url = "http://u.meizu.com/api/v1/upgrade/check/router"
	local serviceCode = "com.meizu.router"
	local pd = gen_check_fw_url_pd()
	local res, code, headers, status = http.request(url, pd)
	if res == nil then
		require "MZLog".log(3, "do_upgrade get "..url.." failed!")
		ret["result"] = code;
		return data_to_json(ret)
	end
	local data = cjson.decode(res)
	local value = data.value
	local digest = ""
	local filesize = 0
	local upgrade_url = ""
	if (data.code) == 200 then
		for k,v in pairs(value) do
			if k == "size" then
				filesize = v
			end
			if k == "url" then
				upgrade_url = v
			end
			if k == "digest" then
				digest = v
			end
			if k == "version" then
				version = v
			end
		end
	end
	if upgrade_url ~= "" then
		require "MZLog".log(3, upgrade_urogtype)
		push_fw_upgrading_msg(upgrade_url)
		local ota_img = "/tmp/ota.trx"
		local cmd = "wget '"..upgrade_url.."' -O "..ota_img..[[;]]
		cmd = cmd..[[nvram set upgrading=1;nvram commit;]]
		cmd = cmd..[[killall dropbear uhttpd; sleep 1;]]
		--cmd = cmd..[[/sbin/router_reset; sleep 2;]]
		--cmd = cmd..[[/sbin/sysupgrade -n -v ]]..ota_img..[[|tee -a /tmp/ota.log;]]
		cmd = cmd..[[/sbin/sysupgrade -v ]]..ota_img..[[|tee -a /tmp/ota.log;]]
		ret = exec_cmd_in_sh(cmd)
		--to do : add UCI set upgrade flag .
	end
end

function local_upgrade()
	local fd   = nil
	local nixio   = require "nixio"
	local image = "/tmp/ota.trx"
	local touchcmd = "touch "..image
	exec_cmd_in_sh(touchcmd)
	local function image_supported()
		return ( 0 == os.execute(
		". /lib/functions.sh; " ..
		"include /lib/upgrade; " ..
		"platform_check_image %q >/dev/null"
		% image
		))
	end
	luci.http.setfilehandler(
		function(field, chunk, eof)
			if not fd then
				fd = nixio.open(image, "w")
			end
			fd:write(chunk)
			if eof and fd then
				fd:close()
				fd = nil
			end
		end
	)
	local clean = (luci.http.formvalue("clean") == "1") and "-n" or ""
	if image_supported() then
		local lue = require"luci.util".exec
		local cmd = [[nvram set upgrading=1;nvram commit;]]
		lue(cmd)
		luci.http.write("updating")
		exec_cmd_in_sh("killall dropbear uhttpd; sleep 1; /sbin/sysupgrade -v %s %q" %{ clean, image })
		luci.http.write("update finished!")
	else
		luci.http.write("image_supported check failed!")
	end
end

--push_new_version_msg("test")
--subscribe_auto_upgrade()
--local res = check_upgrade()
--print(res)
--do_upgrade()

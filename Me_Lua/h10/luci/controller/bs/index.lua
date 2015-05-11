module("luci.controller.bs.index", package.seeall)


local arpmon = require "meizu.arpmon"
local bfs    = require "meizu.bfs"
local btfs   = require "meizu.btfs"

nw_get_bluetooth_info    = btfs.nw_get_bluetooth_info
new_device_notify        = arpmon.new_device_notify

function index()
    local root = node()
	if not root.target then
		root.target = alias("bs")
		root.index = true
	end
    local page  = node("bs")
    --page.target = firstchild()
    page.title  = _("bs")
    page.order  = 10
    page.index  = true
	page = entry({"bs", "info"}, call("info"), nil, nil)
	page.leaf = true
	page = entry({"bs", "token"}, call("token"), nil, nil)
	page.leaf = true

	page = entry({"bs", "newdevicenotify"}, call("new_device_notify"), nil)
	page.leaf = true

	page = entry({"bs", "devip"}, call("devip"), nil, nil)
	page.leaf = true
	page = entry({"bs", "testip"}, call("testip"), nil, nil)
	page.leaf = true
	page = entry({"bs", "normip"}, call("normip"), nil, nil)
	page.leaf = true

	page = entry({"bs", "apk"}, call("apk"), nil)
	page.leaf = true

    page = entry({"bs", "getToken"}, call("get_token"), nil)
    page.leaf = true
    page = entry({"bs", "sysauth"}, call("sysauth"), nil)
    page.leaf = true

    page = entry({"bs", "getBluetoothInfo"}, call("nw_get_bluetooth_info"), nil)
    page.leaf = true

end

function info()
	luci.http.prepare_content("application/json")
    local result = bfs.sysinfo()
	luci.http.write_json(result)
end

function token()
	luci.http.prepare_content("application/json")
    local httpHandler = require("socket.http")
    local usernm = luci.http.formvalue("username")
    local passwd = luci.http.formvalue("password")
    if usernm == nil and passwd == nil then
	local sauth = require "luci.sauth"
	local token = sauth.noAuthGetToken()
	if token then
		luci.http.write_json(token)
        end
    else
        local ret = luci.sys.user.checkpasswd(usernm, passwd)
        if ret == true then
			local sauth = require "luci.sauth"
			local token = sauth.noAuthGetToken()
			if token then
				luci.http.write_json(token)
			end
        end
	end
end

function show_hosts()
	local lue = require"luci.util".exec
	local cmd = "cat /etc/hosts"
	local ret = lue(cmd)
	luci.http.write(ret)
end

function devip()
	local lue = require"luci.util".exec
	local cmd = "/usr/sbin/mzrts_ips.sh devip"
	local ret = lue(cmd)
	show_hosts()
end

function normip()
	local lue = require"luci.util".exec
	local cmd = "/usr/sbin/mzrts_ips.sh"
	local ret = lue(cmd)
	show_hosts()
end

function testip()
	local lue = require"luci.util".exec
	local cmd = "/usr/sbin/mzrts_ips.sh testip"
	local ret = lue(cmd)
	show_hosts()
end

function apk()
	local fn, fd, block
	local cmd = "ls /www/apk_download/apk/*.apk | awk '{printf $1}'"
	fd = io.popen(cmd)
	fn = fd:read("*l")
	fd:close()
	if fn ~= nil then
		fd = nixio.open(fn, "r")
		luci.http.header('Content-Disposition', 'attachment; filename="%s"' % {nixio.fs.basename(fn)})
		luci.http.prepare_content("application/octet-stream")
		while true do
			block = fd:read(nixio.const.buffersize)
			require "MZLog".log(3, debug.getinfo(1).currentline)
			if (not block) or (#block == 0) then
				require "MZLog".log(3, debug.getinfo(1).currentline)
				break
			else
				luci.http.write(block)
			end
		end
		fd:close()
	end
	luci.http.close()
end

function sysauth()
    local res = {}
    local usernm = luci.http.formvalue("username")
    local passwd = luci.http.formvalue("password")
    res["result"] = luci.sys.user.checkpasswd(usernm, passwd)
    luci.http.write_json(res)
end

function get_token()
    local res = {}
    local sauth = require "luci.sauth"
    local sess = luci.http.getcookie("sysauth")
    sess = sess and sess:match("^[a-f0-9]*$")
    local sdat = sauth.read(sess)
    res["sysauth"] = sess
    res["token"] = sdat.token
    luci.http.write_json(res)
end

module("meizu.disk", package.seeall)

local bfs = require "meizu.bfs"
local exec_cmd_in_sh = bfs.exec_cmd_in_sh
local lue = require("luci.util").exec

function get_disk_space()
	local lu = require("luci.util")
	local cmd = "df -k | grep ' /mnt' | awk '{printf $2}'"
	local disk = lue(cmd)
	if disk ~= "" then
		return disk
	else
		return "0"
	end
end

function get_available_disk()
	local lu = require("luci.util")
	local cmd = "df -k | grep ' /mnt' | awk '{printf $4}'"
	local disk = lue(cmd)
	if disk ~= "" then
		return disk
	else
		return "0"
	end
end

function get_info_imp()
    local ret = {}
	ret["total"] = get_disk_space()
	ret["free"] = get_available_disk()
	return ret;
end

function nw_get_disk_info()
	luci.http.prepare_content("application/json")
    local ret = get_info_imp()
	luci.http.write_json(ret)
end

function ww_get_disk_info()
	local res = {}
	res = get_info_imp()
	return res
end

function disk_formatting()
	local result = {}
	result["result"] = false
	local cmd = [[ls /dev/sda |grep -E "/dev/sd.$" |wc -l|awk '{printf $1}']]
	local res = lue(cmd)

	if res == "1" then
		result["result"] = true
		luci.http.write_json(result)
		local cmd = "hd_part.sh /dev/sda;echo $?"
		exec_cmd_in_sh(cmd)
	else
		result["code"] = res
		luci.http.write_json(result)
	end
end

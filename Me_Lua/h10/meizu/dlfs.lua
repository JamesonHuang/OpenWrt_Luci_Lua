module("meizu.dlfs", package.seeall)
-- download functions

local cjson = require "cjson"
local lfs   = require "lfs"
local bfs   = require "meizu.bfs"
local RC    = require "meizu.r10config"
local nixio = require "nixio"

local b64dec               = bfs.b64dec
local bind_router          = bfs.bind_router
local cal_str_md5          = bfs.cal_str_md5
local data_to_json         = bfs.data_to_json
local exec_cmd_in_sh       = bfs.exec_cmd_in_sh
local exec_reboot          = bfs.exec_reboot
local get_device_SN        = bfs.get_device_SN
local get_device_version   = bfs.get_device_version
local get_https_data       = bfs.get_https_data
local rts_get_access_token = bfs.rts_get_access_token
local set_passwd           = bfs.set_passwd
local silent_upgrade       = bfs.silent_upgrade
local table_merge          = bfs.table_merge

local aria2rpc = "http://127.0.0.1:6800/jsonrpc"
local httpreq  = require("socket.http").request
local lue      = require("luci.util").exec

function aria2_make_req_param(method, gid)
	local p = [[{"jsonrpc":"2.0", "method":"]]
	p = p..method..[[", ]]
	p = p..[["id":1, "params":["]]
	p = p..gid..'"]}'
	p = p..'{"Accept=application/json, text/javascript, */*; q=0.01"}'
	p = p..'{"Accept-Encoding=gzip, deflate"}'
	p = p..'{"Content-Type=application/x-www-form-urlencoded; charset=UTF-8"}'
	p = p..'{"Pragma=no-cache"}'
	p = p..'{"Cache-Control=no-cache"}'

	return p
end

function aria2_make_status_param(method, params)
	local p = [[{"jsonrpc":"2.0", "method":"]]
	p = p..method..[[", ]]
	p = p..[["id":1]]
	if params == "" then
	    p = p..'}'
	else
	    p = p..[[, "params":[]]
		p = p..params..']}'
	end
	p = p..'{"Accept=application/json, text/javascript, */*; q=0.01"}'
	p = p..'{"Accept-Encoding=gzip, deflate"}'
	p = p..'{"Content-Type=application/x-www-form-urlencoded; charset=UTF-8"}'
	p = p..'{"Pragma=no-cache"}'
	p = p..'{"Cache-Control=no-cache"}'

	return p
end

function aria2_success_res(command, gid, status)
    local res = [[{"result":true, "command":"]]..command..[[", "gid":"]]..gid..[[", "message":"]]..status..[["}]]

	return res
end

function aria2_failed_res(command, gid, status)
    local res = [[{"result":flase, "command":"]]..command..[[", "gid":"]]..gid..[[", "message":"]]..status..[["}]]

	return res
end

function aria2_download_task_pause(gid, command)
	local param = aria2_make_req_param("aria2.pause", gid)
	local res, code, headers, status = httpreq(aria2rpc, param)
	local success_res = aria2_success_res(command, gid, status)
	local failed_res = aria2_failed_res(command, gid, status)

	if code == 200 then
		return success_res
	else
		return failed_res
	end
end

function aria2_download_task_unpause(gid, command)
    local param = aria2_make_req_param("aria2.unpause", gid)
	local res, code, headers, status = httpreq(aria2rpc,param)
	local success_res = aria2_success_res(command, gid, status)
	local failed_res = aria2_failed_res(command, gid, status)

	if code == 200 then
		return success_res
	else
		return failed_res
	end
end

function aria2_download_task_remove(gid, command)
    local param = aria2_make_req_param("aria2.removeDownloadResult", gid)
	local res, code, headers, status = httpreq(aria2rpc,param)
    local success_res = aria2_success_res(command, gid, status)
	local failed_res = aria2_failed_res(command, gid, status)

	if code == 200 then
		return success_res
	else
		return failed_res
	end
end

function aria2_get_active_list()
    local result = {}
	local param = aria2_make_status_param("aria2.tellActive", "")
	local res, code, headers, status = httpreq(aria2rpc, param)

    if code == 200 then
	local jsd = cjson.decode(res)
	for key, value in pairs(jsd.result) do
		local data = {status = "", speed = nil, total = nil,
		              completed = nil, gid = "", file = ""}

		data.status = value.status
		data.speed = tonumber(value.downloadSpeed)
		data.total = tonumber(value.totalLength)
		data.completed = tonumber(value.completedLength)
		data.gid = value.gid
		local jsd = cjson.encode(value.files)
		jsd = string.sub(jsd, 2, -2)
		local fn = cjson.decode(jsd)
		data.file = ""
		if string.match(fn.path, ".+/([^/]*%.%w+)$") then
			data.file = string.match(fn.path, ".+/([^/]*%.%w+)$")
		end
		table.insert(result, data)
	end
    end

	return result
end

function aria2_get_pause_list()
	local param = aria2_make_status_param("aria2.tellWaiting", "0, 1000")
	local res, code, headers, status = httpreq(aria2rpc,param)
	local result = {}
	if code == 200 then
		local jsd = cjson.decode(res)

		for key, value in pairs(jsd.result) do
			local data = {status = "", speed = nil, total = nil,
			completed = nil, gid = "", file = ""}
			data.status = value.status
			data.speed = tonumber(value.downloadSpeed)
			data.total = tonumber(value.totalLength)
			data.completed = tonumber(value.completedLength)
			data.gid = value.gid
			local jsd = cjson.encode(value.files)
			jsd = string.sub(jsd, 2, -2)
			local fn = cjson.decode(jsd)
			data.file = ""
			if string.match(fn.path, ".+/([^/]*%.%w+)$") then
				data.file = string.match(fn.path, ".+/([^/]*%.%w+)$")
			end
			table.insert(result, data)
		end
	end

	return result;
end

function aria2_get_history_list()
	local param = aria2_make_status_param("aria2.tellStopped", "0, 1000")
	local result = {}
	local res, code, headers, status = httpreq(aria2rpc, param)
	if code == 200 then
		local jsd = cjson.decode(res)
		for key, value in pairs(jsd.result) do
			local data = {status = "", speed = nil, total = nil,
			completed = nil, gid = "", file = ""}
			data.speed = tonumber(value.downloadSpeed)
			data.total = tonumber(value.totalLength)
			data.completed = tonumber(value.completedLength)
			local jsd = cjson.encode(value.files)
			jsd = string.sub(jsd, 2, -2)
			local fn = cjson.decode(jsd)
			data.status = value.status
			data.gid = value.gid
			data.file = ""
			if string.match(fn.path, ".+/([^/]*%.%w+)$") then
				data.file = string.match(fn.path, ".+/([^/]*%.%w+)$")
			end
			table.insert(result, data)
		end
	end

	return result
end

function aria2_download_task_start(url)
	local param = '[{"jsonrpc":"2.0", "method":"aria2.addUri", "id":"1",'..
	              '"params":[['..'"'..url..'"'.."]]}]"
	local res, code, headers, status = httpreq(aria2rpc, param)
    local jsd = nil
    if code == 200 then
        res = string.sub(res, 2, -2)
        jsd = cjson.decode(res)
    end
	local success_res = '{"result":true, "gid":'..'"'..jsd.result..'"'..
	                    ', "message":'..'"'..status..'"'.."}"
	local failed_res = '{"result":false, "message":'..'"'..status..'"'.."}"

	if code == 200 then
		return success_res
	else
		return failed_res
	end
end

function thunder_clean_list_log()
	if nixio.fs.access("/tmp/tdal.log") then
		nixio.fs.unlink("/tmp/tdal.log")
	end
	if nixio.fs.access("/tmp/tdhl.log") then
		nixio.fs.unlink("/tmp/tdhl.log")
	end
end

function thunder_download_task_pause(gid, command)
	thunder_clean_list_log()
	local success_res = '{"result":true, "command":'..'"'..command..'"'..
	                    ', "gid":'..'"'..gid..'"'..
						', "message":'..'"success"'.."}"
	local cmd = "/usr/bin/xunleiR.py pause "..gid
	local ret = lue(cmd)

	return success_res
end

function download_task_pause(gid, command)
	if string.match(gid, "^tdid_") then
		local tdid = string.sub(gid, 6)
		return thunder_download_task_pause(tdid, command)
	else
		return aria2_download_task_pause(gid, command)
	end
end

function thunder_download_task_unpause(gid, command)
	thunder_clean_list_log()
	local success_res = '{"result":true, "command":'..
	                    '"'..command..'"'..', "gid":'..
						'"'..gid..'"'..', "message":'..'"success"'.."}"
	local cmd = "/usr/bin/xunleiR.py start "..gid
	local ret = lue(cmd)

	return success_res
end

function download_task_unpause(gid, command)
	if string.match(gid, "^tdid_") then
		local tdid = string.sub(gid, 6)
		return thunder_download_task_unpause(tdid, command)
	else
		return aria2_download_task_unpause(gid, command)
	end
end

function thunder_download_task_remove(gid, command)
	thunder_clean_list_log()
	local success_res = '{"result":true, "command":'..
	                    '"'..command..'"'..', "gid":'..
						'"'..gid..'"'..', "message":'..'"success"'.."}"
	--local cmd = "/usr/bin/xunleiR.py remove "..gid
	local cmd = "/usr/bin/xunleiR.py delete "..gid
	local ret = lue(cmd)
	require "MZLog".log(3, ret)

	return success_res
end

function download_task_remove(gid, command)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, gid)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	if string.match(gid, "^tdid_") then
		local tdid = string.sub(gid, 6)
		return thunder_download_task_remove(tdid, command)
	else
		return aria2_download_task_remove(gid, command)
	end
end

function get_pause_list()
	local result = aria2_get_pause_list()
	local ret_msg = ""
	if nil ~= next(result) then
		--result = cjson.encode(result)
		local jsr = data_to_json(result)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		require "MZLog".log(3, jsr)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		ret_msg = '{"result":true, "message":'..jsr.."}"
	else
		ret_msg = '{"result":false, "message":[]}'
	end
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, ret_msg)
	require "MZLog".log(3, debug.getinfo(1).currentline)

	return ret_msg
end

function nw_get_pause_list()
	local data = get_pause_list()
	luci.http.write(data)
end

function thunder_get_bind_code()
	local ret = {}
	local lue = require"luci.util".exec
	local cmd = [[/usr/bin/xunleiR.py sysinfo|awk -F',' '{print $5}']]
	local code = lue(cmd)
	ret = '{"code":'..code..'}'

	return ret
end

function nw_thunder_get_bind_code()
	local data = thunder_get_bind_code()
	luci.http.write(data)
end

function ww_thunder_get_bind_code()
	return thunder_get_bind_code()
end

function thunder_state_code(code)
	local ret = "active"
	if code == 0 or code == 8 or code == 13 or code == 14 or code == 37 then
		ret = "active"
	end
	if code == 9 or code == 10 then
		ret = "paused"
	end
	if code == 11 or code == 15 then
		ret = "complete"
	end

	return ret
end

function thunder_localpath_translate(path)
	if type(path) == "string" then
		path = string.gsub(path, "/opt/xware/mnt/", "/router/Thunder/")
	end
	return path
end

function thunder_get_active_list()
	local cmd = "/usr/bin/xunleiR.py list"
	local ret
	require "MZLog".log(3, debug.getinfo(1).currentline)
	if nixio.fs.access("/tmp/tdal.log") then
		local fp = io.open("/tmp/tdal.log", "r")
	   local data = fp:read("*a")
	   ret = data
	   fp:close()
	else
		ret = lue(cmd)
		local fp = io.open("/tmp/tdal.log", "wb")
		fp:write(ret)
		fp:close()
	end
	local result = {}
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, ret)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	if ret then
		require "MZLog".log(3, debug.getinfo(1).currentline)
		local jsd = cjson.decode(ret)
		local tasks = jsd.tasks
		for k, v in pairs(tasks) do
			local data = {}
			if (_G.next(v) ~= nil) then
				data.speed = v.speed
				data.status = thunder_state_code(v.state)
				data.total = tonumber(v.size)
				data.completed = tonumber(data.total * tonumber(v.progress)/10000)
				data.file = v.name
				data.path = thunder_localpath_translate(v.path)
				data.gid = 'tdid_'..v.id
			end
			table.insert(result, data)
		end
	end

	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, data_to_json(result))
	require "MZLog".log(3, debug.getinfo(1).currentline)
	return result
end

function thunder_get_rlist()
	thunder_clean_list_log()
	local cmd = "/usr/bin/xunleiR.py rlist"
	local ret = lue(cmd)
	local jsd = cjson.decode(ret)
	local tasks = jsd.tasks
	local result = {}
	for k, v in pairs(tasks) do
		local data = {}
		if (_G.next(v) ~= nil) then
			data.speed = v.speed
			data.status = thunder_state_code(v.state)
			data.total = tonumber(v.size)
			data.completed = tonumber(data.total * tonumber(v.progress)/10000)
			data.file = v.name
			data.gid = 'tdid_'..v.id
		end
		table.insert(result, data)
	end

	return result
end

function get_active_list(start, refine_cnt)
	local s = 0
	local cnt
	if start then
		s = start
	end
	local result = {}
	local aria2_alist = aria2_get_active_list()
	local thunder_alist = thunder_get_active_list()
	local aria2_plist = aria2_get_pause_list()
	--local thunder_rlist = thunder_get_rlist()
	if _G.next(aria2_alist) then
		result = table_merge(result, aria2_alist)
	end
	if _G.next(thunder_alist) then
		result = table_merge(result, thunder_alist)
	end
	if _G.next(aria2_plist) then
		result = table_merge(result, aria2_plist)
	end

	local ret_msg = {}
	local total = require("table").getn(result)
	ret_msg["total"] = total
	ret_msg["result"] = false
	local ret = {}
	if _G.next(result) then
		if refine_cnt then
			cnt = refine_cnt
		else
			cnt = total
		end
		local pos = 0
		for k, v in pairs(result) do
			if pos >= s and (pos - s + 1 <= cnt) then
				table.insert(ret, v)
				pos = pos + 1
			end
		end
		ret_msg["result"] = true
		ret_msg["message"] = ret
	else
		ret_msg["message"] = "[]"
	end
	ret_msg["start"] = s
	if ret and type(ret) == "table" and _G.next(ret) then
		ret_msg["count"] = require("table").getn(ret)
	else
		ret_msg["count"] = 0
	end
	ret_msg = data_to_json(ret_msg)
	require "MZLog".log(3, ret_msg)

	return ret_msg
end

function nw_get_active_list()
	local data = get_active_list()
	luci.http.write(data)
end

function thunder_get_history_list()
	local cmd = "/usr/bin/xunleiR.py clist"
	local ret
	if nixio.fs.access("/tmp/tdhl.log") then
		local fp = io.open("/tmp/tdhl.log", "r")
		local data = nixio.fs.readfile("/tmp/tdhl.log")
		ret = data
	else
		ret = lue(cmd)
		local fp = io.open("/tmp/tdhl.log", "wb")
		fp:write(ret)
		fp:close()
	end

	local jsd = cjson.decode(ret)
	local tasks = jsd.tasks
	local result = {}
	for k, v in pairs(tasks) do
		local data = {}
		if (_G.next(v) ~= nil) then
			data.speed = v.speed
			data.total = tonumber(v.size)
			data.completed = v.size
			data.status = thunder_state_code(v.state)
			data.file = v.name
			data.path = thunder_localpath_translate(v.path)
			data.gid = 'tdid_'..v.id
		end
		table.insert(result, data)
	end
	return result
end

function get_history_list(start, refine_cnt)
	local s = 0
	local cnt
	require "MZLog".log(3, debug.getinfo(1).currentline)
	if start then
		s = start
		require "MZLog".log(3, start)
	end
	if refine_cnt then
		require "MZLog".log(3, refine_cnt)
	end
	require "MZLog".log(3, debug.getinfo(1).currentline)
	local result = {}
	local aria2_ret = aria2_get_history_list()
	local thunder_ret = thunder_get_history_list()

	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, data_to_json(aria2_ret))
	require "MZLog".log(3, debug.getinfo(1).currentline)

	if nil ~= _G.next(aria2_ret) then
		table_merge(result, aria2_ret)
	end
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, data_to_json(thunder_ret))
	require "MZLog".log(3, debug.getinfo(1).currentline)
	if _G.next(thunder_ret) then
		table_merge(result, thunder_ret)
	end

	require "MZLog".log(3, debug.getinfo(1).currentline)
	local total = require("table").getn(result)
	local ret_msg = {}
	require "MZLog".log(3, debug.getinfo(1).currentline)
	ret_msg["total"] = total
	ret_msg["result"] = false
	if _G.next(result) then
		require "MZLog".log(3, debug.getinfo(1).currentline)
		local cnt
		if refine_cnt then
			cnt = refine_cnt
		else
			cnt = total
		end
		local ret = {}
		local pos = 0
		for k, v in pairs(result) do
			if pos >= s and (pos - s + 1 <= cnt) then
				table.insert(ret, v)
				pos = pos + 1
			end
		end
		ret_msg["count"] = require("table").getn(ret)
		local jsd = data_to_json(ret)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		require "MZLog".log(3, jsd)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		ret_msg["result"] = true
		--ret_msg["message"] = jsd
		ret_msg["message"] = ret
	else
		ret_msg["message"] = "[]"
	end
	ret_msg["start"] = s
	if ret and type(ret) == "table" and _G.next(ret) then
		ret_msg["count"] = require("table").getn(ret)
	else
		ret_msg["count"] = 0
	end
	require "MZLog".log(3, debug.getinfo(1).currentline)
	ret_msg = data_to_json(ret_msg)
	require "MZLog".log(3, debug.getinfo(1).currentline)
	require "MZLog".log(3, ret_msg)
	require "MZLog".log(3, debug.getinfo(1).currentline)

	return ret_msg
end

function nw_get_history_list()
	local data = get_history_list()
	luci.http.write(data)
end

function thunder_download_task_start(url)
	thunder_clean_list_log()
	local ret = ""
	local cmd = [[/usr/bin/xunleiR.py download ']]..url..[[']]
	require "MZLog".log(3, cmd)
	ret = lue(cmd)
	require "MZLog".log(3, ret)
	local jsr = cjson.decode(ret)
	ret = {}
	ret["message"] = ""
	ret["result"] = false
	if jsr and jsr.rtn then
		require "MZLog".log(3, jsr.rtn)
		ret["rtn"] = jsr.rtn
		if jsr.rtn == 0 or jsr.rtn == 202 then
			ret["result"] = true
		end
	end

	if jsr and jsr.id then
		ret["gid"] = "tdid_"..jsr.id
	else
		ret["gid"] = "tdid_0"
	end

	return data_to_json(ret)
end

function download_task_start(url, dltype)
	require "MZLog".log(3, url)
	require "MZLog".log(3, dltype)
	local res = ""
	if dltype == "thunder" then
		res = thunder_download_task_start(url)
	else
		res = aria2_download_task_start(url)
	end
	require "MZLog".log(3, res)

	return res
end

function nw_download_task_start()
	local url = luci.http.formvalue("url")
	local dltype = luci.http.formvalue("type")
	local res = download_task_start(url, dltype)
	luci.http.write(res)

	return res
end

function download_task_operate(gid, command)
	if command == "downloadpause" then
		return download_task_pause(gid, command)
	elseif command == "downloadunpause" then
		return download_task_unpause(gid, command)
	elseif command == "downloadremove" then
		return download_task_remove(gid, command)
	end
end

function nw_download_task_operate()
	local command = luci.http.formvalue("command")
	local gid = luci.http.formvalue("gid")
	local ret = download_task_operate(gid, command)

	luci.http.write(ret)
end

--[[

Session authentication
(c) 2008 Steven Barth <steven@midlink.org>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

$Id$

]]--

--- LuCI session library.
module("luci.sauth", package.seeall)
require("luci.util")
require("luci.sys")
require("luci.config")
local nixio = require "nixio", require "nixio.util"
local fs = require "nixio.fs"


luci.config.sauth = luci.config.sauth or {}
sessionpath = luci.config.sauth.sessionpath
sessiontime = tonumber(luci.config.sauth.sessiontime) or 15 * 60

--- Prepare session storage by creating the session directory.
function prepare()
	fs.mkdir(sessionpath, 700)
	if not sane() then
		error("Security Exception: Session path is not sane!")
	end
end

local function _read(id)
	local blob = fs.readfile(sessionpath .. "/" .. id)
	return blob
end

local function _write(id, data)
    local tempid = luci.sys.uniqueid(16)
    local tempfile = sessionpath .. "/" .. tempid
    local sessfile = sessionpath .. "/" .. id
    local f = nixio.open(tempfile, "w", 600)
    f:writeall(data)
    f:close()
    fs.rename(tempfile, sessfile)
end

local function _checkid(id)
    return not not (id and #id == 32 and id:match("^[a-fA-F0-9]+$"))
end

--- Write session data to a session file.
-- @param id    Session identifier
-- @param data  Session data table
function write(id, data)
    if not sane() then
        prepare()
    end

    if not _checkid(id) then
        return
    end

    if type(data) ~= "table" then
        return
    end

	data.atime = luci.sys.uptime()

	_write(id, luci.util.get_bytecode(data))
end

--- Read a session and return its content.
-- @param id	Session identifier
-- @return		Session data table or nil if the given id is not found
function read(id)
	if not id or #id == 0 then
		return nil
	end

    if not _checkid(id) then
        return nil
    end

	if not sane(sessionpath .. "/" .. id) then
		return nil
	end

	local blob = _read(id)
	local func = loadstring(blob)
	setfenv(func, {})

    local sess = func()
    if type(sess) ~= "table" then
        return nil
    end

	if sess.atime and sess.atime + sessiontime < luci.sys.uptime() then
		kill(id)
		return nil
	end

	-- refresh atime in session
	write(id, sess)

	return sess
end

--- Check whether Session environment is sane.
-- @return Boolean status
function sane(file)
	return luci.sys.process.info("uid")
			== fs.stat(file or sessionpath, "uid")
		and fs.stat(file or sessionpath, "modestr")
			== (file and "rw-------" or "rwx------")
end

--- Kills a session
-- @param id	Session identifier
function kill(id)
    if not _checkid(id) then
    else
        fs.unlink(sessionpath .. "/" .. id)
    end
end

--- Remove all expired session data files
function reap()
	if sane() then
		local id
		for id in nixio.fs.dir(sessionpath) do
			if _checkid(id) then
				-- reading the session will kill it if it is expired
				read(id)
			end
		end
	end
end

--- Get available session data
function available()
    if sane() then
        local id
        for id in nixio.fs.dir(sessionpath) do
            if _checkid(id) then
                -- reading the session will kill it if it is expired
                local available = read(id)
                if available then
                    return available
                end
            end
        end
    end
    return nil
end

function genAuthToken()
	local id = luci.sys.uniqueid(16)
	local token = luci.sys.uniqueid(16)
	write(id, {
		user="root",
		token=token,
		secret=luci.sys.uniqueid(16)
	})
	return id, read(id)
end

function noAuthGetToken()
	local id
	local data
	local ret = {}
	if sessionpath then
		local ids = nixio.fs.dir(sessionpath)
		require "MZLog".log(3, debug.getinfo(1).currentline)
		if not ids then
			genAuthToken()
		end
		for id in nixio.fs.dir(sessionpath) do
			if id then
				read(id)
			end
		end
		for id in nixio.fs.dir(sessionpath) do
			if id then
				ret["sysauth"] = id
				ret["data"] = read(id)
				return ret
			end
		end
		require "MZLog".log(3, debug.getinfo(1).currentline)
		id, data = genAuthToken()
		ret["sysauth"] = id
		ret["data"] = read(id)
		return ret
	end
end

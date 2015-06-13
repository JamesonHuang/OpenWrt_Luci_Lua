module ("xiaoqiang.util.XQCacheUtil", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")

local LuciUtil = require("luci.util")
local LuciSys = require("luci.sys")
local Nixio = require "nixio"
local NixioFs = require "nixio.fs"

function saveCache(key, data, expire)
    if XQFunction.isStrNil(key) or not data or not expire then
        return false
    end
    local path = "/tmp/"..key
    local info = {}
    info.data = data
    info.atime = LuciSys.uptime()
    info.expire = tostring(expire)
    local cache = Nixio.open(path, "w", 600)
    cache:writeall(LuciUtil.get_bytecode(info))
    cache:close()
    return true
end

function getCache(key)
    if XQFunction.isStrNil(key) then
        return nil
    end
    local path = "/tmp/"..key
    if not NixioFs.access(path) then
        return nil
    end
    local blob = NixioFs.readfile(path)
    local func = loadstring(blob)
    setfenv(func, {})

    local data = func()
    if data.atime and data.expire and tonumber(data.expire) > 0 and data.atime + data.expire < LuciSys.uptime() then
        return nil
    end
    return data.data
end
--[[
Xiaoqiang Preference - Configuration
author : Afei
]]--

local util = require "luci.util"
module("xiaoqiang.XQPreference",package.seeall)

function get(key, defaultValue, config) 
    require "luci.model.uci"
    if not config then
        config = "xiaoqiang"
    end
    local cursor = luci.model.uci.cursor();
    local value = cursor:get(config, "common", key)
    return value or defaultValue;
end

function set(key, value, config)
    require "luci.model.uci"
    if not config then
        config = "xiaoqiang"
    end
    local cursor = luci.model.uci.cursor();
    if value == nil then
        value = ""
    end
    cursor:set(config, "common", key, value)
    cursor:save(config)
    return cursor:commit(config)
end


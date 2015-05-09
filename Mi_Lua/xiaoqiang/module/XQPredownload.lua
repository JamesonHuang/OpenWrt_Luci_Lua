module ("xiaoqiang.module.XQPredownload", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")

function predownloadInfo()
    local uci = require("luci.model.uci").cursor()
    local info = {}
    info["enable"] = os.execute("/etc/init.d/predownload-ota status") == 0 and 1 or 0
    info["priority"] = tonumber(uci:get("otapred", "settings", "priority")) or 0
    return info
end

function reload()
    os.execute("/etc/init.d/predownload-ota restart")
end

function switch(on)
    local uci = require("luci.model.uci").cursor()
    if on then
        return os.execute("/etc/init.d/predownload-ota start") == 0
    else
        return os.execute("/etc/init.d/predownload-ota stop") == 0
    end
end

function setPriority(priority)
    local priority = tonumber(priority) or 0
    if priority == 1 then
        uci:set("otapred", "settings", "priority", "1")
    else
        uci:set("otapred", "settings", "priority", "0")
    end
    uci:commit("otapred")
end
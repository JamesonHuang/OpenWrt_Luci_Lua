module ("xiaoqiang.module.XQAPModule", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")

function enableLanAP()
    local uci = require("luci.model.uci").cursor()
    local ipaddr = uci:get("network", "lan", "ipaddr")
    XQFunction.setNetMode("apmode")
    local newipaddr = uci:get("network", "lan", "ipaddr")
    if newipaddr ~= ipaddr then
        XQFunction.forkExec("sleep 4; ap_mode.sh open")
        return newipaddr
    else
        return nil
    end
end

function disableLanAP()
    local uci = require("luci.model.uci").cursor()
    local ipaddr = uci:get("network", "lan", "ipaddr")
    XQFunction.setNetMode(nil)
    os.execute("ap_mode.sh close")
    XQFunction.forkExec("sleep 4; /etc/init.d/network restart")
    return ipaddr
end
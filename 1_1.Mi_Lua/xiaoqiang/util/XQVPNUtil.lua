module ("xiaoqiang.util.XQVPNUtil", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")

local Network = require("luci.model.network")
local Firewall = require("luci.model.firewall")

-- @param proto pptp/l2tp
-- @param auto  0/1
function setVpn(interface, server, username, password, proto, auto)
    if XQFunction.isStrNil(interface) or XQFunction.isStrNil(server) or XQFunction.isStrNil(username) or XQFunction.isStrNil(password) or XQFunction.isStrNil(proto) then
        return false
    end
    auto = tonumber(auto)
    local autoinit = (auto and auto == 0) and "0" or "1"
    local protocal = string.lower(proto)
    local network = Network.init()
    network:del_network(interface)
    local vpnNetwork = network:add_network(interface, {
        proto = protocal,
        server = server,
        username = username,
        password = password,
        auto = autoinit,
        auth = 'auto'
    })
    if vpnNetwork then
        network:save("network")
        network:commit("network")
        local firewall = Firewall.init()
        local zoneWan = firewall:get_zone("wan")
        zoneWan:add_network(interface)
        firewall:save("firewall")
        firewall:commit("firewall")
        return true
    end
    return false
end

function getVPNInfo(interface)
    local network = Network.init()
    local info = {
        proto = "",
        server = "",
        username = "",
        password = "",
        auto = "1"
    }
    if XQFunction.isStrNil(interface) then
        return info
    end
    local vpn = network:get_network(interface)
    if vpn then
        info.proto = vpn:get_option_value("proto")
        info.server = vpn:get_option_value("server")
        info.username = vpn:get_option_value("username")
        info.password = vpn:get_option_value("password")
        info.auto = vpn:get_option_value("auto")
    end
    return info
end

function vpnSwitch(enable)
    if enable then
        os.execute(XQConfigs.RM_VPNSTATUS_FILE)
        os.execute(XQConfigs.VPN_DISABLE)
        return (os.execute(XQConfigs.VPN_ENABLE) == 0)
    else
        os.execute(XQConfigs.RM_VPNSTATUS_FILE)
        return (os.execute(XQConfigs.VPN_DISABLE) == 0)
    end
end

function vpnStatus()
    local LuciUtil = require("luci.util")
    local status = LuciUtil.exec(XQConfigs.VPN_STATUS)
    if not XQFunction.isStrNil(status) then
        status = LuciUtil.trim(status)
        if XQFunction.isStrNil(status) then
            return nil
        end
        local json = require("json")
        status = json.decode(status)
        if status then
            return status
        end
    end
    return nil
end

module ("xiaoqiang.module.XQGuestWifi", package.seeall)

local bit = require("bit")
local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")
--[[
-- Config network
--- config switch_vlan 'eth0_3'
local SWITCH_VLAN = {
    ["device"] = "eth0",
    ["vlan"] = "3",
    ["ports"] = "5",
    ["xqmodule"] = "guest_wifi"
}

--- config interface 'guest'
local NETWORK_GUEST = {
    ["type"] = "bridge",
    ["ifname"] = "eth0.3",
    ["proto"] = "static",
    ["ipaddr"] = "",
    ["netmask"] = "255.255.255.0",
    ["xqmodule"] = "guest_wifi"
}

-- Config dhcp
--- config dhcp 'guest'
local DHCP_GUEST = {
    ["interface"] = "guest",
    ["leasetime"] = "12h",
    ["dhcp_option_force"] = {
        "43,XIAOMI_ROUTER"
    },
    ["force"] = "1",
    ["start"] = "100",
    ["limit"] = "50",
    ["xqmodule"] = "guest_wifi"
}

-- Config firewall
local GUEST_ZONE = {
    ["name"] = "guest",
    ["network"] = "guest",
    ["input"] = "REJECT",
    ["forward"] = "REJECT",
    ["output"] = "ACCEPT",
    ["xqmodule"] = "guest_wifi"
}

local FORWARDING_GUEST = {
    ["src"] = "guest",
    ["dest"] = "wan",
    ["xqmodule"] = "guest_wifi"
}

local RULE_GUEST1 = {
    ["name"] = "Allow Guest DNS Queries",
    ["src"] = "guest",
    ["dest_port"] = "53",
    ["proto"] = "tcpudp",
    ["target"] = "ACCEPT",
    ["xqmodule"] = "guest_wifi"
}

local RULE_GUEST2 = {
    ["name"] = "Allow Guest DHCP request",
    ["src"] = "guest",
    ["src_port"] = "67-68",
    ["dest_port"] = "67-68",
    ["proto"] = "udp",
    ["target"] = "ACCEPT",
    ["xqmodule"] = "guest_wifi"
}

function _setnetwork()
    local uci = require("luci.model.uci").cursor()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local guest = uci:get_all("network", "eth0_3")
    if not guest then
        local lanip = XQLanWanUtil.getLanIp()
        local lan = lanip:gsub(".%d+.%d+$", "")
        local cip = tonumber(lanip:match(".(%d+).%d+$"))
        if cip > 150 then
            cip = cip - 1
        else
            cip = cip + 1
        end
        NETWORK_GUEST.ipaddr = lan.."."..tostring(cip)..".1"
        uci:section("network", "switch_vlan", "eth0_3", SWITCH_VLAN)
        uci:section("network", "interface", "guest", NETWORK_GUEST)
        uci:commit("network")
        return 1
    end
    return 0
end

function _delnetwork()
    local uci = require("luci.model.uci").cursor()
    uci:delete("network", "eth0_3")
    uci:delete("network", "guest")
    uci:commit("network")
end

function _setdhcp()
    local uci = require("luci.model.uci").cursor()
    local guest = uci:get_all("dhcp", "guest")
    if not guest then
        uci:section("dhcp", "dhcp", "guest", DHCP_GUEST)
        uci:commit("dhcp")
        return 1
    end
    return 0
end

function _deldhcp()
    local uci = require("luci.model.uci").cursor()
    uci:delete("dhcp", "guest")
    uci:commit("dhcp")
end

function _setfirewall()
    local uci = require("luci.model.uci").cursor()
    local guest = uci:get_all("firewall", "guest")
    if not guest then
        local sectionid = uci:add("firewall", "zone")
        uci:section("firewall", "zone", sectionid, GUEST_ZONE)
        uci:section("firewall", "forwarding", "guest", FORWARDING_GUEST)
        local ruleid1 = uci:add("firewall", "rule")
        uci:section("firewall", "rule", ruleid1, RULE_GUEST1)
        local ruleid2 = uci:add("firewall", "rule")
        uci:section("firewall", "rule", ruleid2, RULE_GUEST2)
        uci:commit("firewall")
        return 1
    end
    return 0
end

function _delfirewall()
    local uci = require("luci.model.uci").cursor()
    uci:delete("firewall", "guest")
    uci:foreach("firewall", "zone",
        function(s)
            if s["xqmodule"] == "guest_wifi" then
                uci:delete("firewall", s[".name"])
            end
        end
    )
    uci:foreach("firewall", "rule",
        function(s)
            if s["xqmodule"] == "guest_wifi" then
                uci:delete("firewall", s[".name"])
            end
        end
    )
    uci:commit("firewall")
end
]]--

function _checkGuestWifi()
    local uci = require("luci.model.uci").cursor()
    local guest = uci:get_all("network", "eth0_3")
    if guest then
        return true
    else
        return false
    end
end

function setGuestWifi(wifiIndex, ssid, encryption, key, enabled, open)
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local success = XQWifiUtil.setGuestWifi(wifiIndex, ssid, encryption, key, enabled, open)
    if not success then
        return false
    end
    local networkrestart = true
    if _checkGuestWifi() then
        networkrestart = false
    end
    if networkrestart then
        local lanip = XQLanWanUtil.getLanIp()
        local lan = lanip:gsub(".%d+.%d+$", "")
        local cip = tonumber(lanip:match(".(%d+).%d+$"))
        cip = bit.bor(bit.band(1, cip + 1), bit.lshift(bit.rshift(cip, 1), 1))
        lanip = lan.."."..tostring(cip)..".1"
        XQFunction.forkExec("sleep 4; /usr/sbin/guest_ssid_network start "..lanip.." 255.255.255.0 >/dev/null 2>/dev/null")
    else
        XQFunction.forkRestartWifi()
    end
    return true
end

function delGuestWifi(wifiIndex)
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    XQWifiUtil.delGuestWifi(wifiIndex)
    XQFunction.forkExec("sleep 4; /usr/sbin/guest_ssid_network stop >/dev/null 2>/dev/null")
end
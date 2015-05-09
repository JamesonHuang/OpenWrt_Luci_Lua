module ("xiaoqiang.module.XQDMZModule", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")

DMZ_NVRAM = {
    ["vlan1hwname"] = "et0",
    ["vlan2hwname"] = "et0",
    ["vlan3hwname"] = "et0",
    ["vlan1ports"] = "2 5*",
    ["vlan2ports"] = "4 5",
    ["vlan3ports"] = "0 5*"
}

DMZ_NETWORK_CONFIGS = {
    -- switch_vlan
    ["eth0_1"] = {
        ["device"] = "eth0",
        ["vlan"] = 1,
        ["ports"] = "2 5*"
    },
    ["eth0_3"] = {
        ["device"] = "eth0",
        ["vlan"] = 3,
        ["ports"] = "0 5*"
    },
    -- interface
    ["dmz"] = {
        ["ifname"] = "eth0.3",
        ["proto"] = "static",
        ["ipaddr"] = "",
        ["netmask"] = "255.255.255.0"
    }
}

DMZ_FIREWALL_CONFIGS = {
    -- zone
    ["zonedmz"] = {
        ["name"] = "dmz",
        ["network"] = "dmz",
        ["input"] = "REJECT",
        ["output"] = "ACCEPT",
        ["forward"] = "REJECT"
    },
    -- rule
    ["dmzdns"] = {
        ["src"] = "dmz",
        ["proto"] = "tcpudp",
        ["dest_port"] = 53,
        ["target"] = "ACCEPT"
    },
    ["dmzdhcp"] = {
        ["src"] = "dmz",
        ["proto"] = "udp",
        ["dest_port"] = 67,
        ["target"] = "ACCEPT"
    },
    -- forwarding
    ["dmztowan"] = {
        ["src"] = "dmz",
        ["dest"] = "wan"
    },
    ["lantodmz"] = {
        ["src"] = "lan",
        ["dest"] = "dmz"
    },
    -- redirect
    ["dmz"] = {
        ["src"] = "wan",
        ["proto"] = "all",
        ["target"] = "DNAT",
        ["dest"] = "lan",
        ["dest_ip"] = ""
    }
}

DMZ_DHCP_CONFIGS = {
    -- dhcp
    ["dmz"] = {
        ["interface"] = "dmz",
        ["start"] = 100,
        ["limit"] = 150,
        ["leasetime"] = "12h",
        ["force"] = 1
    }
}

--
-- Event
--
function hookLanIPChangeEvent(ip)
    if XQFunction.isStrNil(ip) then
        return
    end
    local uci = require("luci.model.uci").cursor()
    local lan = ip:gsub(".%d+$","")
    local destip = uci:get("firewall", "dmz","dest_ip")
    if not XQFunction.isStrNil(destip) then
        destip = lan.."."..destip:match(".(%d+)$")
        uci:set("firewall", "dmz", "dest_ip", destip)
        uci:commit("firewall")
    end
end

function unsetDMZ(mode)
    local uci = require("luci.model.uci").cursor()
    if mode == 1 then
        uci:delete("firewall", "zonedmz")
        uci:delete("firewall", "dmzdns")
        uci:delete("firewall", "dmzdhcp")
        uci:delete("firewall", "dmztowan")
        uci:delete("firewall", "lantodmz")
        uci:delete("firewall", "dmz")
        uci:commit("firewall")
        uci:delete("dhcp", "dmz")
        uci:commit("dhcp")
        uci:delete("network", "dmz")
        uci:delete("network", "eth0_3")
        uci:commit("network")
        XQFunction.nvramSet("vlan3hwname", nil)
        XQFunction.nvramSet("vlan3ports", nil)
        XQFunction.nvramSet("vlan2ports", "4 5")
        XQFunction.nvramSet("vlan1ports", "0 2 5*")
        XQFunction.setNetMode(nil)
        XQFunction.nvramCommit()
    elseif mode == 0 then
        uci:delete("firewall", "dmz")
        uci:commit("firewall")
        XQFunction.setNetMode(nil)
        XQFunction.nvramCommit()
    end
end

function _setSimpleDMZ(destip, destmac)
    local uci = require("luci.model.uci").cursor()
    local config = DMZ_FIREWALL_CONFIGS["dmz"]
    local lanip = uci:get("network", "lan", "ipaddr")
    local lanpre = lanip:gsub(".%d+$", "")
    local destpre = destip:gsub(".%d+$", "")
    if lanpre ~= destpre or lanip == destip then
        return 2
    end
    config.dest_ip = destip
    uci:section("firewall", "redirect", "dmz", config)
    uci:commit("firewall")
    if not XQFunction.isStrNil(destmac) then
        local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
        local bind = XQLanWanUtil.addBind(destmac, destip)
        if bind == 0 then
            XQLanWanUtil.saveBindInfo()
        else
            return bind
        end
    end
    return 0
end

function _setComplexDMZ(destip, destmac)
    local uci = require("luci.model.uci").cursor()
    local LuciUtil = require("luci.util")
    local lanip = uci:get("network", "lan", "ipaddr")
    local ipv = LuciUtil.split(destip, ".")
    ipv[4] = 1
    ipv = table.concat(ipv, ".")
    local lanpre = lanip:gsub(".%d+$", "")
    local destpre = destip:gsub(".%d+$", "")
    if lanpre == destpre or lanip == destip then
        return 2
    end
    -- nvram
    for key, value in pairs(DMZ_NVRAM) do
        XQFunction.nvramSet(key, value)
    end
    XQFunction.nvramCommit()
    -- network config
    local eth0_1 = DMZ_NETWORK_CONFIGS["eth0_1"]
    local eth0_3 = DMZ_NETWORK_CONFIGS["eth0_3"]
    local dmz = DMZ_NETWORK_CONFIGS["dmz"]
    dmz.ipaddr = ipv
    uci:section("network", "switch_vlan", "eth0_1", eth0_1)
    uci:section("network", "switch_vlan", "eth0_3", eth0_3)
    uci:section("network", "interface", "dmz", dmz)
    uci:commit("network")
    -- firewall config
    local zonedmz = DMZ_FIREWALL_CONFIGS["zonedmz"]
    local dmzdns = DMZ_FIREWALL_CONFIGS["dmzdns"]
    local dmzdhcp = DMZ_FIREWALL_CONFIGS["dmzdhcp"]
    local dmztowan = DMZ_FIREWALL_CONFIGS["dmztowan"]
    local lantodmz = DMZ_FIREWALL_CONFIGS["lantodmz"]
    local fdmz = DMZ_FIREWALL_CONFIGS["dmz"]
    fdmz.dest_ip = destip
    uci:section("firewall", "zone", "zonedmz", zonedmz)
    uci:section("firewall", "rule", "dmzdns", dmzdns)
    uci:section("firewall", "rule", "dmzdhcp", dmzdhcp)
    uci:section("firewall", "forwarding", "dmztowan", dmztowan)
    uci:section("firewall", "forwarding", "lantodmz", lantodmz)
    uci:section("firewall", "redirect", "dmz", fdmz)
    uci:commit("firewall")
    -- dhcp config
    local dhcp = DMZ_DHCP_CONFIGS["dmz"]
    uci:section("dhcp", "dhcp", "dmz", dhcp)
    -- ip mac bind
    if not XQFunction.isStrNil(destmac) then
        local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
        local bind = XQLanWanUtil.addBind(destmac, destip)
        if bind == 0 then
            XQLanWanUtil.saveBindInfo()
        else
            return bind
        end
    end
    return 0
end

function moduleOn()
    local uci = require("luci.model.uci").cursor()
    local dmzip = uci:get("firewall", "dmz", "dest_ip")
    if dmzip then
        return true
    else
        return false
    end
end

-- status
-- 0:关闭
-- 1:开启
-- 2:冲突(端口转发被开启，DMZ功能就不能开启)
function getDMZInfo()
    local XQPortForward = require("xiaoqiang.module.XQPortForward")
    local uci = require("luci.model.uci").cursor()
    local info = {}
    if XQPortForward.moduleOn() then
        info["status"] = 2
    else
        info["status"] = moduleOn() and 1 or 0
        if info.status == 1 then
            info["ip"] = uci:get("firewall", "dmz", "dest_ip") or ""
        end
    end
    info["lanip"] = uci:get("network", "lan", "ipaddr") or ""
    return info
end

-- mode 0/1 简单/复杂
-- return 0/1/2/3/4 设置成功/IP冲突/MACIP不合法/工作模式不可设置/开启了端口转发,DMZ不能启用
function setDMZ(mode, destip, destmac)
    if XQFunction.isStrNil(destip) then
        return 2
    end
    local XQPortForward = require("xiaoqiang.module.XQPortForward")
    if XQPortForward.moduleOn() then
        return 4
    end
    if mode == 0 then
        XQFunction.setNetMode("dmzsimple")
        return _setSimpleDMZ(destip, destmac)
    elseif mode == 1 then
        XQFunction.setNetMode("dmzmode")
        return _setComplexDMZ(destip, destmac)
    else
        return 3
    end
end

function dmzReload(mode)
    if mode == 0 then
        -- reload services
        os.execute("/etc/init.d/firewall restart")
        XQFunction.forkRestartDnsmasq()
    elseif mode == 1 then
        -- fork reboot
        XQFunction.forkReboot()
    end
end
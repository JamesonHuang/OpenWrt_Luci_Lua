module ("xiaoqiang.module.XQPortForward", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")

--
-- Event
--
function hookLanIPChangeEvent(ip)
    if XQFunction.isStrNil(ip) then
        return
    end
    local uci = require("luci.model.uci").cursor()
    local lan = ip:gsub(".%d+$","")
    uci:foreach("firewall", "redirect",
        function(s)
            local ftype = tonumber(s.ftype)
            if ftype then
                local destip = s.dest_ip
                destip = lan.."."..destip:match(".(%d+)$")
                uci:set("firewall", s[".name"], "dest_ip", destip)
            end
        end
    )
    uci:commit("firewall")
end

-- proto
-- 1: tcp
-- 2: udp
-- 3: tcp and udp
-- default: 1/tcp
function _protoHelper(proto)
    if proto and type(proto) == "number" then
        if proto == 1 then
            return "tcp"
        elseif proto == 2 then
            return "udp"
        elseif proto == 3 then
            return "tcpudp"
        else
            return "tcp"
        end
    end
    if proto and type("proto") == "string" then
        if proto == "tcp" then
            return 1
        elseif proto == "udp" then
            return 2
        elseif proto == "tcpudp" then
            return 3
        else
            return 1
        end
    end
    return nil
end

function _portCheck(port)
    if port and type(port) == "number" and port > 0 then
        return true
    else
        return false
    end
end

function _portRangeOverlap(port1, port2)
    local LuciUtil = require("luci.util")
    if port1 and port2 then
        port1 = tostring(port1)
        port2 = tostring(port2)
        if port1 == port2 then
            return false
        end
        local range1 = {}
        local range2 = {}
        if port1:match("-") then
            local sp = LuciUtil.split(port1, "-")
            range1["f"] = tonumber(sp[1])
            range1["t"] = tonumber(sp[2])
        else
            range1["f"] = tonumber(port1)
            range1["t"] = tonumber(port1)
        end
        if port2:match("-") then
            local sp = LuciUtil.split(port2, "-")
            range2["f"] = tonumber(sp[1])
            range2["t"] = tonumber(sp[2])
        else
            range2["f"] = tonumber(port2)
            range2["t"] = tonumber(port2)
        end
        if (range1.f >= range2.f and range1.f <= range2.t) or
            (range1.t >= range2.f and range1.t <= range2.t) then
            return true
        end
    end
    return false
end

function _portConflictCheck(port)
    local uci = require("luci.model.uci").cursor()
    local result = false
    uci:foreach("firewall", "redirect",
        function(s)
            if _portRangeOverlap(port, s.src_dport) then
                result = true
            end
        end
    )
    return result
end

function moduleOn()
    return #portForwards(0) > 0
end

-- status
-- 0:关闭
-- 1:开启
-- 2:冲突(DMZ功能开启，NAT功能就不能开启)
function portForwardInfo()
    local XQDMZModule = require("xiaoqiang.module.XQDMZModule")
    local info = {}
    if XQDMZModule.moduleOn() then
        info["status"] = 2
    else
        info["status"] = moduleOn() and 1 or 0
    end
    return info
end

-- ftype
-- 0: all
-- 1: port
-- 2: range
function portForwards(ftype)
    local uci = require("luci.model.uci").cursor()
    local portforwards = {}
    local forwardtype = tonumber(ftype) or 0
    uci:foreach("firewall", "redirect",
        function(s)
            local ftype = tonumber(s.ftype)
            if ftype and forwardtype == 0 or forwardtype == ftype then
                local item = {}
                item.name = s.name
                item.destip = s.dest_ip
                item.proto = _protoHelper(s.proto) or 1
                if ftype == 1 then
                    item.srcport = tonumber(s.src_dport)
                    item.destport = s.dest_port
                    item.ftype = 1
                elseif ftype == 2 then
                    item.ftype = 2
                    local portrange = {}
                    local LuciUtil = require("luci.util")
                    local sp = LuciUtil.split(s.src_dport, "-")
                    item.srcport = {
                        ["f"] = tonumber(sp[1]),
                        ["t"] = tonumber(sp[2])
                    }
                end
                table.insert(portforwards, item)
            end
        end
    )
    return portforwards
end

-- return
-- 0: 设置成功
-- 1: 参数不合法
-- 2: 端口冲突(设置的端口和已有端口有重叠)
-- 3: 功能冲突由于DMZ开启
function setPortForward(name, ip, sport, dport, proto)
    if not XQFunction.isStrNil(ip) and _portCheck(tonumber(sport)) and _portCheck(tonumber(dport)) then
        local uci = require("luci.model.uci").cursor()
        if portForwardInfo().status == 2 then
            return 3
        end
        if _portConflictCheck(sport) then
            return 2
        end
        local sname = string.format("wan%srdr", tostring(sport))
        local options = {
            ["src"]       = "wan",
            ["src_dport"] = sport,
            ["proto"]     = _protoHelper(tonumber(proto)) or "tcp",
            ["target"]    = "DNAT",
            ["dest"]      = "lan",
            ["dest_port"] = dport,
            ["dest_ip"]   = ip,
            ["ftype"]     = 1,
            ["name"]      = name or ""
        }
        uci:section("firewall", "redirect", sname, options)
        uci:commit("firewall")
        return 0
    end
    return 1
end

-- return
-- 0: 设置成功
-- 1: 参数不合法
-- 2: 端口冲突(设置的端口和已有端口有重叠)
function setRangePortForward(name, ip, fport, tport, proto)
    if not XQFunction.isStrNil(ip) and _portCheck(tonumber(fport)) and _portCheck(tonumber(tport)) then
        if tonumber(fport) > tonumber(tport) then
            return 1
        end
        local uci = require("luci.model.uci").cursor()
        local sport = tostring(fport).."-"..tostring(tport)
        if portForwardInfo().status == 2 then
            return 3
        end
        if _portConflictCheck(sport) then
            return 2
        end
        local sname = string.format("wan%srdr", tostring(fport))
        local options = {
            ["src"]       = "wan",
            ["src_dport"] = sport,
            ["proto"]     = _protoHelper(tonumber(proto)) or "tcp",
            ["target"]    = "DNAT",
            ["dest"]      = "lan",
            ["dest_ip"]   = ip,
            ["ftype"]     = 2,
            ["name"]      = name or ""
        }
        uci:section("firewall", "redirect", sname, options)
        uci:commit("firewall")
        return 0
    end
    return 1
end

function deletePortForward(port)
    if _portCheck(tonumber(port)) then
        local uci = require("luci.model.uci").cursor()
        local sname = string.format("wan%srdr", tostring(port))
        uci:delete("firewall", sname)
        uci:commit("firewall")
        return true
    end
    return false
end

function deleteAllPortForward()
    local uci = require("luci.model.uci").cursor()
    uci:delete_all("firewall", "redirect",
        function(s)
            if s.ftype then
                return true
            else
                return false
            end
        end
    )
    uci:commit("firewall")
    return true
end

function restart()
    os.execute("/etc/init.d/firewall restart")
end
module ("xiaoqiang.util.XQLanWanUtil", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")

function getDefaultMacAddress()
    local LuciUtil = require("luci.util")
    local mac = LuciUtil.exec(XQConfigs.GET_DEFAULT_MACADDRESS)
    if XQFunction.isStrNil(mac) then
        mac = nil
    else
        mac = LuciUtil.trim(mac):match("(%S-),")
    end
    return string.upper(mac)
end

function getLanLinkList()
    local LuciUtil = require("luci.util")
    local lanLink = {}
    local cmd = "et robord 0x01 0x00"
    for _, line in ipairs(LuciUtil.execl(cmd)) do
        local port,link = line:match('port (%d):(%S+)')
        if link then
            if tonumber(port) == 0 then
                lanLink[1] = link == 'up' and 1 or 0
            end
            if tonumber(port) == 2 then
                lanLink[2] = link == 'up' and 1 or 0
            end
        end
    end
    return lanLink
end

function getLanIp()
    local uci = require("luci.model.uci").cursor()
    local lan = uci:get_all("network", "lan")
    return lan.ipaddr
end

--[[
@return WANLINKSTAT=UP/DOWN
@return LOCALDNSSTAT=UP/DOWN
@return VPNLINKSTAT=UP/DOWN
]]--
function getWanMonitorStat()
    local NixioFs = require("nixio.fs")
    local content = NixioFs.readfile(XQConfigs.WAN_MONITOR_STAT_FILEPATH)
    local status = {}
    if content ~= nil then
        for line in string.gmatch(content, "[^\n]+") do
            key,value = line:match('(%S+)=(%S+)')
            status[key] = value
        end
    end
    return status
end

function getAutoWanType()
    local LuciUtil = require("luci.util")
    local result = LuciUtil.execi("/usr/sbin/wanlinkprobe 1 WAN pppoe dhcp")
    local link,pppoe,dhcp
    if result then
        for line in result do
            if line:match("^LINK=(%S+)") ~= nil then
                link = line:match("^LINK=(%S+)")
            elseif line:match("^PPPOE=(%S+)") ~= nil then
                pppoe = line:match("^PPPOE=(%S+)")
            elseif line:match("^DHCP=(%S+)") ~= nil then
                dhcp = line:match("^DHCP=(%S+)")
            end
        end
    end
    if pppoe == "YES" then
        return 1
    elseif dhcp == "YES" then
        return 2
    elseif link ~= "YES" then
        return 99
    else
        return 0
    end
end

function ubusWanStatus()
    local ubus = require("ubus").connect()
    local wan = ubus:call("network.interface.wan", "status", {})
    local result = {}
    if wan["ipv4-address"] and #wan["ipv4-address"] > 0 then
        result["ipv4"] = wan["ipv4-address"][1]
    else
        result["ipv4"] = {
            ["mask"] = 0,
            ["address"] = ""
        }
    end
    result["dns"] = wan["dns-server"] or {}
    result["proto"] = string.lower(wan.proto or "dhcp")
    result["up"] = wan.up
    result["uptime"] = wan.uptime or 0
    result["pending"] = wan.pending
    result["autostart"] = wan.autostart
    return result
end

function _pppoeStatusCheck()
    local LuciJson = require("json")
    local LuciUtil = require("luci.util")
    local cmd = "lua /usr/sbin/pppoe.lua status"
    local status = LuciUtil.exec(cmd)
    if status then
        status = LuciUtil.trim(status)
        if XQFunction.isStrNil(status) then
            return false
        end
        status = LuciJson.decode(status)
        return status
    else
        return false
    end
end

--- type
--- 1:认证失败（用户密码异常导致）
--- 2:无法连接服务器
--- 3:其他异常（协议不匹配等）
function _pppoeErrorCodeHelper(code)
    local errorA = {
        ["507"] = 1,["691"] = 1,["509"] = 1,["514"] = 1,["520"] = 1,
        ["646"] = 1,["647"] = 1,["648"] = 1,["649"] = 1,["691"] = 1,
        ["646"] = 1
    }
    local errorB = {
        ["516"] = 1,["650"] = 1,["601"] = 1,["510"] = 1,["530"] = 1,
        ["531"] = 1
    }
    local errorC = {
        ["501"] = 1,["502"] = 1,["503"] = 1,["504"] = 1,["505"] = 1,
        ["506"] = 1,["507"] = 1,["508"] = 1,["511"] = 1,["512"] = 1,
        ["515"] = 1,["517"] = 1,["518"] = 1,["519"] = 1
    }
    local errcode = tostring(code)
    if errcode then
        if errorA[errcode] then
            return 1
        end
        if errorB[errcode] then
            return 2
        end
        if errorC[errcode] then
            return 3
        end
        return 1
    end
end

--- status
--- 0:未拨号
--- 1:正在拨号
--- 2:拨号成功
--- 3:正在拨号 但返现拨号错误信息
--- 4:关闭拨号
function getPPPoEStatus()
    local result = {}
    local status = ubusWanStatus()
    if status then
        local LuciNetwork = require("luci.model.network").init()
        local network = LuciNetwork:get_network("wan")
        if status.proto == "pppoe" then
            if status.up then
                result["status"] = 2
            else
                local check = _pppoeStatusCheck()
                if check then
                    if check.process == "down" then
                        result["status"] = 4
                    elseif check.process == "up" then
                        result["status"] = 2
                    elseif check.process == "connecting" then
                        if check.code == nil or check.code == 0 then
                            result["status"] = 1
                        else
                            result["status"] = 3
                            result["errcode"] = check.msg or ""
                            result["errtype"] = _pppoeErrorCodeHelper(tostring(check.code))
                        end
                    end
                else
                    result["status"] = 0
                end
            end
            local configdns = network:get_option_value("dns")
            if not XQFunction.isStrNil(configdns) then
                result["cdns"] = luci.util.split(configdns," ")
            end
            result["pppoename"] = network:get_option_value("username")
            result["password"] = network:get_option_value("password")
            result["peerdns"] = network:get_option_value("peerdns")
        else
            result["status"] = 0
        end
        local device = network:get_interface()
        local ipaddress = device:ipaddrs()
        local ipv4 = {
            ["address"] = "",
            ["mask"] = ""
        }
        if ipaddress and #ipaddress > 0 then
            ipv4["address"] = ipaddress[1]:host():string()
            ipv4["mask"] = ipaddress[1]:mask():string()
        end
        result["ip"] = ipv4
        result["dns"] = status.dns
        result["proto"] = status.proto
        result["gw"] = network:gwaddr() or ""
        return result
    else
        return false
    end
end

function pppoeStop()
    os.execute("lua /usr/sbin/pppoe.lua down")
end

function pppoeStart()
    XQFunction.forkExec("lua /usr/sbin/pppoe.lua up")
end

--[[
@param interface : lan/wan
]]--
function getLanWanInfo(interface)
    if interface ~= "lan" and interface ~= "wan" then
        return false
    end
    local LuciUtil = require("luci.util")
    local LuciNetwork = require("luci.model.network").init()
    local info = {}
    local ipv6Dict = getIPv6Addrs()
    local network = LuciNetwork:get_network(interface)
    if network then
        local device = network:get_interface()
        local ipAddrs = device:ipaddrs()
        local ip6Addrs = device:ip6addrs()
        if interface == "wan" then
            local mtuvalue = network:get_option_value("mtu")
            if XQFunction.isStrNil(mtuvalue) then
                mtuvalue = "1480"
            end
            info["mtu"] = tostring(mtuvalue)
            info["details"] = getWanDetails()
            -- 是否插了网线
            local cmd = 'et robord 0x01 0x00'
            local data = LuciUtil.exec(cmd)
            if not XQFunction.isStrNil(data) then
                local linkStat = string.match(data, 'port 4:([^%s]+)')
                info["link"] = linkStat == 'up' and 1 or 0;
            end
        end
        if device and #ipAddrs > 0 then
            local ipAddress = {}
            for _,ip in ipairs(ipAddrs) do
                ipAddress[#ipAddress+1] = {}
                ipAddress[#ipAddress]["ip"] = ip:host():string()
                ipAddress[#ipAddress]["mask"] = ip:mask():string()
            end
            info["ipv4"] = ipAddress
        end
        if device and #ip6Addrs > 0 then
            local ipAddress = {}
            for _,ip in ipairs(ip6Addrs) do
                ipAddress[#ipAddress+1] = {}
                ipAddress[#ipAddress]["ip"] = ip:host():string()
                ipAddress[#ipAddress]["mask"] = ip:mask():string()
                if ipv6Dict[ip] then
                    ipAddress[#ipAddress]["type"] = ipv6Dict[ip].type
                end
        end
            info["ipv6"] = ipAddress
        end
        info["gateWay"] = network:gwaddr()
        if network:dnsaddrs() then
            info["dnsAddrs"] = network:dnsaddrs()[1] or ""
            info["dnsAddrs1"] = network:dnsaddrs()[2] or ""
        else
            info["dnsAddrs"] = ""
            info["dnsAddrs1"] = ""
        end
        if device and device:mac() ~= "00:00:00:00:00:00" then
            info["mac"] = device:mac()
        end
        if info["mac"] == nil then
            info["mac"] = getWanMac()
        end
        if network:uptime() > 0 then
            info["uptime"] = network:uptime()
        else
            info["uptime"] = 0
        end
        local status = network:status()
        if status=="down" then
            info["status"] = 0
        elseif status=="up" then
            info["status"] = 1
            if info.details and info.details.wanType == "pppoe" then
                wanMonitor = getWanMonitorStat()
                if wanMonitor.WANLINKSTAT ~= "UP" then
                    info["status"] = 0
                end
            end
        elseif status=="connection" then
            info["status"] = 2
        end
    else
        info = false
    end
    return info
end

function getWanEth()
    local LuciNetwork = require("luci.model.network").init()
    local wanNetwork = LuciNetwork:get_network("wan")
    return wanNetwork:get_option_value("ifname")
end

function getWanMac()
    local LuciUtil = require("luci.util")
    local ifconfig = LuciUtil.exec("ifconfig " .. getWanEth())
    if not XQFunction.isStrNil(ifconfig) then
        return ifconfig:match('HWaddr (%S+)') or ""
    else
        return nil
    end
end

--[[
@param interface : lan/wan
]]--
function getLanWanIp(interface)
    if interface ~= "lan" and interface ~= "wan" then
        return false
    end
    local LuciNetwork = require("luci.model.network").init()
    local ipv4 = {}
    local network = LuciNetwork:get_network(interface)
    if network then
        local device = network:get_interface()
        local ipAddrs = device:ipaddrs()
        if device and #ipAddrs > 0 then
            for _,ip in ipairs(ipAddrs) do
                ipv4[#ipv4+1] = {}
                ipv4[#ipv4]["ip"] = ip:host():string()
                ipv4[#ipv4]["mask"] = ip:mask():string()
            end
        end
    end
    return ipv4
end

function checkLanIp(ip)
    local LuciIp = require("luci.ip")
    local ipNl = LuciIp.iptonl(ip)
    if (ipNl >= LuciIp.iptonl("10.0.0.0") and ipNl <= LuciIp.iptonl("10.255.255.255"))
        or (ipNl >= LuciIp.iptonl("172.16.0.0") and ipNl <= LuciIp.iptonl("172.31.255.255"))
        or (ipNl >= LuciIp.iptonl("192.168.0.0") and ipNl <= LuciIp.iptonl("192.168.255.255")) then
        return 0
    else
        return 1527
    end
end

function setLanIp(ip,mask)
    local XQEvent = require("xiaoqiang.XQEvent")
    local LuciNetwork = require("luci.model.network").init()
    local network = LuciNetwork:get_network("lan")
    network:set("ipaddr",ip)
    network:set("netmask",mask)
    LuciNetwork:commit("network")
    LuciNetwork:save("network")
    XQEvent.lanIPChange(ip)
    return true
end

function getIPv6Addrs()
    local LuciIp = require("luci.ip")
    local LuciUtil = require("luci.util")
    local cmd = "ifconfig|grep inet6"
    local ipv6List = LuciUtil.execi(cmd)
    local result = {}
    for line in ipv6List do
        line = luci.util.trim(line)
        local ipv6,mask,ipType = line:match('inet6 addr: ([^%s]+)/([^%s]+)%s+Scope:([^%s]+)')
        if ipv6 then
            ipv6 = LuciIp.IPv6(ipv6,"ffff:ffff:ffff:ffff::")
            ipv6 = ipv6:host():string()
            result[ipv6] = {}
            result[ipv6]['ip'] = ipv6
            result[ipv6]['mask'] = mask
            result[ipv6]['type'] = ipType
        end
    end
    return result
end

function getLanDHCPService()
    local LuciUci = require "luci.model.uci"
    local lanDhcpStatus = {}
    local uciCursor  = LuciUci.cursor()
    local ignore = uciCursor:get("dhcp", "lan", "ignore")
    local leasetime = uciCursor:get("dhcp", "lan", "leasetime")
    if ignore ~= "1" then
        ignore = "0"
    end
    local leasetimeNum,leasetimeUnit = leasetime:match("^(%d+)([^%d]+)")
    lanDhcpStatus["lanIp"] = getLanWanIp("lan")
    lanDhcpStatus["start"] = uciCursor:get("dhcp", "lan", "start")
    lanDhcpStatus["limit"] = uciCursor:get("dhcp", "lan", "limit")
    lanDhcpStatus["leasetime"] = leasetime
    lanDhcpStatus["leasetimeNum"] = leasetimeNum
    lanDhcpStatus["leasetimeUnit"] = leasetimeUnit
    lanDhcpStatus["ignore"] = ignore
    return lanDhcpStatus
end

--[[
Set Lan DHCP, range = start~end
]]--
function setLanDHCPService(startReq,endReq,leasetime,ignore)
    local LuciUci = require("luci.model.uci")
    local LuciUtil = require("luci.util")
    local uciCursor  = LuciUci.cursor()
    if ignore == "1" then
        uciCursor:set("dhcp", "lan", "ignore", tonumber(ignore))
    else
        local limit = tonumber(endReq) - tonumber(startReq) + 1
        if limit < 0 then
            return false
        end
        uciCursor:set("dhcp", "lan", "start", tonumber(startReq))
        uciCursor:set("dhcp", "lan", "limit", tonumber(limit))
        uciCursor:set("dhcp", "lan", "leasetime", leasetime)
        uciCursor:delete("dhcp", "lan", "ignore")
    end
    uciCursor:save("dhcp")
    uciCursor:load("dhcp")
    uciCursor:commit("dhcp")
    uciCursor:load("dhcp")
    LuciUtil.exec("/etc/init.d/dnsmasq restart > /dev/null")
    return true
end

function wanDown()
    local LuciUtil = require("luci.util")
    LuciUtil.exec("env -i /sbin/ifdown wan")
end

function wanRestart()
    local LuciUtil = require("luci.util")
    LuciUtil.exec("env -i /sbin/ifup wan")
    XQFunction.forkExec("/etc/init.d/filetunnel restart")
end

function dnsmsqRestart()
    local LuciUtil = require("luci.util")
    LuciUtil.exec("ubus call network reload; sleep 1; /etc/init.d/dnsmasq restart > /dev/null")
end

--[[
Get wan details, static ip/pppoe/dhcp/mobile
@return {proto="dhcp",ifname=ifname,dns=dns,peerdns=peerdns}
@return {proto="static",ifname=ifname,ipaddr=ipaddr,netmask=netmask,gateway=gateway,dns=dns}
@return {proto="pppoe",ifname=ifname,username=pppoename,password=pppoepasswd,dns=dns,peerdns=peerdns}
]]--
function getWanDetails()
    local LuciNetwork = require("luci.model.network").init()
    local wanNetwork = LuciNetwork:get_network("wan")
    local wanDetails = {}
    if wanNetwork then
        local wanType = wanNetwork:proto()
        if wanType == "mobile" or wanType == "3g" then
            wanType = "mobile"
        elseif wanType == "static" then
            wanDetails["ipaddr"] = wanNetwork:get_option_value("ipaddr")
            wanDetails["netmask"] = wanNetwork:get_option_value("netmask")
            wanDetails["gateway"] = wanNetwork:get_option_value("gateway")
        elseif wanType == "pppoe" then
            wanDetails["username"] = wanNetwork:get_option_value("username")
            wanDetails["password"] = wanNetwork:get_option_value("password")
            wanDetails["peerdns"] = wanNetwork:get_option_value("peerdns")
            wanDetails["service"] = wanNetwork:get_option_value("service")
        elseif wanType == "dhcp" then
            wanDetails["peerdns"] = wanNetwork:get_option_value("peerdns")
        end
        if not XQFunction.isStrNil(wanNetwork:get_option_value("dns")) then
            wanDetails["dns"] = luci.util.split(wanNetwork:get_option_value("dns")," ")
        end
        wanDetails["wanType"] = wanType
        wanDetails["ifname"] = wanNetwork:get_option_value("ifname")
        return wanDetails
    else
        return nil
    end
end

function generateDns(dns1, dns2, peerdns)
    local dns
    if not XQFunction.isStrNil(dns1) and not XQFunction.isStrNil(dns2) then
        dns = {dns1,dns2}
    elseif not XQFunction.isStrNil(dns1) then
        dns = dns1
    elseif not XQFunction.isStrNil(dns2) then
        dns = dns2
    end
    return dns
end

function checkMTU(value)
    local mtu = tonumber(value)
    if mtu and mtu >= 576 and mtu <= 1498 then
        return true
    else
        return false
    end
end

function setWanPPPoE(name, password, dns1, dns2, peerdns, mtu, service)
    local XQPreference = require("xiaoqiang.XQPreference")
    local LuciNetwork = require("luci.model.network").init()
    local uci = require("luci.model.uci").cursor()
    local macaddr = uci:get("network", "wan", "macaddr")

    local iface = "wan"
    local ifname = getWanEth()
    local oldconf = uci:get_all("network", "wan") or {}
    local wanrestart = true
    local dnsrestart = true
    if oldconf.username == name
        and oldconf.password == password
        and tonumber(oldconf.mtu) == tonumber(mtu)
        and ((XQFunction.isStrNil(oldconf.service) and XQFunction.isStrNil(service)) or oldconf.service == service) then
        wanrestart = false
    end
    local dnss = {}
    local odnss = {}
    if oldconf.dns and type(oldconf.dns) == "string" then
        odnss = {oldconf.dns}
    elseif oldconf.dns and type(oldconf.dns) == "table" then
        odnss = oldconf.dns
    end
    if not XQFunction.isStrNil(dns1) then
        table.insert(dnss, dns1)
    end
    if not XQFunction.isStrNil(dns2) then
        table.insert(dnss, dns2)
    end
    if #dnss == #odnss then
        if #dnss == 0 then
            dnsrestart = false
        else
            local odnsd = {}
            local match = 0
            for _, dns in ipairs(odnss) do
                odnsd[dns] = 1
            end
            for _, dns in ipairs(dnss) do
                if odnsd[dns] == 1 then
                    match = match + 1
                end
            end
            if match == #dnss then
                dnsrestart = false
            end
        end
    end

    local wanNet = LuciNetwork:del_network(iface)
    local mtuvalue
    if mtu then
        if checkMTU(mtu) then
            mtuvalue = tonumber(mtu)
        else
            return false
        end
    else
        mtuvalue = 1480
    end
    wanNet = LuciNetwork:add_network(
        iface, {
            proto    ="pppoe",
            ifname   = ifname,
            username = name,
            password = password,
            dns      = generateDns(dns1,dns2,peerdns),
            peerdns  = peerdns,
            macaddr  = macaddr,
            service  = service,
            mtu      = mtuvalue
        })
    if not XQFunction.isStrNil(name) then
        XQPreference.set(XQConfigs.PREF_PPPOE_NAME,name)
        XQFunction.nvramSet("nv_pppoe_name", name)
    end
    if not XQFunction.isStrNil(password) then
        XQPreference.set(XQConfigs.PREF_PPPOE_PASSWORD,password)
        XQFunction.nvramSet("nv_pppoe_pwd", password)
    end
    XQFunction.nvramSet("nv_wan_type", "pppoe")
    XQFunction.nvramCommit()
    if wanNet then
        LuciNetwork:save("network")
        LuciNetwork:commit("network")
        os.execute(XQConfigs.VPN_DISABLE)
        if dnsrestart then
            dnsmsqRestart()
        end
        if wanrestart then
            wanRestart()
        else
            local pppoestatus = getPPPoEStatus()
            if pppoestatus and pppoestatus.status == 4 then
                pppoeStart()
            end
        end
        return true
    else
        return false
    end
end

function checkWanIp(ip)
    local LuciIp = require("luci.ip")
    local ipNl = LuciIp.iptonl(ip)
    if (ipNl >= LuciIp.iptonl("1.0.0.0") and ipNl <= LuciIp.iptonl("126.0.0.0"))
        or (ipNl >= LuciIp.iptonl("128.0.0.0") and ipNl <= LuciIp.iptonl("223.255.255.255")) then
        return 0
    else
        return 1533
    end
end

function setWanStaticOrDHCP(ipType, ip, mask, gw, dns1, dns2, peerdns, mtu)
    local LuciNetwork = require("luci.model.network").init()
    local uci = require("luci.model.uci").cursor()
    local macaddr = uci:get("network", "wan", "macaddr")
    local oldconf = uci:get_all("network", "wan") or {}

    local iface = "wan"
    local ifname = getWanEth()
    local dnsrestart = true
    local wanrestart = true
    local dnss = {}
    local odnss = {}
    if oldconf.dns and type(oldconf.dns) == "string" then
        odnss = {oldconf.dns}
    elseif oldconf.dns and type(oldconf.dns) == "table" then
        odnss = oldconf.dns
    end
    if not XQFunction.isStrNil(dns1) then
        table.insert(dnss, dns1)
    end
    if not XQFunction.isStrNil(dns2) then
        table.insert(dnss, dns2)
    end
    if #dnss == #odnss then
        if #dnss == 0 then
            dnsrestart = false
        else
            local odnsd = {}
            local match = 0
            for _, dns in ipairs(odnss) do
                odnsd[dns] = 1
            end
            for _, dns in ipairs(dnss) do
                if odnsd[dns] == 1 then
                    match = match + 1
                end
            end
            if match == #dnss then
                dnsrestart = false
            end
        end
    end
    local wanNet = LuciNetwork:del_network(iface)
    local dns = generateDns(dns1, dns2, peerdns)
    local mtuvalue
    if mtu then
        mtuvalue = tonumber(mtu)
    end
    if ipType == "dhcp" then
        if oldconf.proto == "dhcp" then
            wanrestart = false
        end
        local network = {
            proto   = "dhcp",
            ifname  = ifname,
            dns     = dns,
            macaddr = macaddr,
            peerdns = peerdns
        }
        if mtu then
            network["mtu"] = mtuvalue
        end
        wanNet = LuciNetwork:add_network(iface, network)
    elseif ipType == "static" then
        if oldconf.proto == "static"
            and oldconf.ipaddr == ip
            and oldconf.netmask == mask
            and oldconf.gateway == gw
            and oldconf.mtu == mtuvalue then
            wanrestart = false
        end
        if not dns then
            dns = gw
        end
        local network = {
            proto   = "static",
            ipaddr  = ip,
            netmask = mask,
            gateway = gw,
            dns     = dns,
            macaddr = macaddr,
            ifname  = ifname
        }
        if mtu then
            network["mtu"] = mtuvalue
        end
        wanNet = LuciNetwork:add_network(iface, network)
    end
    XQFunction.nvramSet("nv_wan_type", ipType)
    XQFunction.nvramCommit()
    if wanNet then
        LuciNetwork:save("network")
        LuciNetwork:commit("network")
        if dnsrestart then
            dnsmsqRestart()
        end
        if wanrestart then
            wanRestart()
        end
        return true
    else
        return false
    end
end

function setWanMac(mac)
    local LuciNetwork = require("luci.model.network").init()
    local LuciDatatypes = require("luci.cbi.datatypes")
    local network = LuciNetwork:get_network("wan")
    local oldMac = network:get_option_value("macaddr")
    local succeed = false
    if oldMac ~= mac then
        if XQFunction.isStrNil(mac) then
            local defaultMac = getDefaultMacAddress() or ""
            network:set("macaddr",defaultMac)
            succeed = true
        elseif LuciDatatypes.macaddr(mac) and mac ~= "ff:ff:ff:ff:ff:ff" and mac ~= "00:00:00:00:00:00" then
            network:set("macaddr",mac)
            succeed = true
        end
    else
        succeed = true
    end
    if succeed then
        LuciNetwork:save("network")
        LuciNetwork:commit("network")
        wanRestart()
    end
    return succeed
end

function _checkIP(ip)
    if XQFunction.isStrNil(ip) then
        return false
    end
    local LuciIp = require("luci.ip")
    local ipNl = LuciIp.iptonl(ip)
    if (ipNl >= LuciIp.iptonl("1.0.0.0") and ipNl <= LuciIp.iptonl("126.0.0.0"))
        or (ipNl >= LuciIp.iptonl("128.0.0.0") and ipNl <= LuciIp.iptonl("223.255.255.255")) then
        return true
    else
        return false
    end
end

function _checkMac(mac)
    if XQFunction.isStrNil(mac) then
        return false
    end
    local LuciDatatypes = require("luci.cbi.datatypes")
    if LuciDatatypes.macaddr(mac) and mac ~= "ff:ff:ff:ff:ff:ff" and mac ~= "00:00:00:00:00:00" then
        return true
    else
        return false
    end
end

function _parseMac(mac)
    if mac then
        return string.lower(string.gsub(mac,"[:-]",""))
    else
        return nil
    end
end

function _parseDhcpLeases()
    local NixioFs = require("nixio.fs")
    local uci =  require("luci.model.uci").cursor()
    local result = {}
    local leasefile = XQConfigs.DHCP_LEASE_FILEPATH
    uci:foreach("dhcp", "dnsmasq",
    function(s)
        if s.leasefile and NixioFs.access(s.leasefile) then
            leasefile = s.leasefile
            return false
        end
    end)
    local dhcp = io.open(leasefile, "r")
    if dhcp then
        for line in dhcp:lines() do
            if line then
                local ts, mac, ip, name = line:match("^(%d+) (%S+) (%S+) (%S+)")
                if name == "*" then
                    name = ""
                end
                if ts and mac and ip and name then
                    result[ip] = {
                        mac  = string.lower(XQFunction.macFormat(mac)),
                        ip   = ip,
                        name = name
                    }
                end
            end
        end
        dhcp:close()
    end
    return result
end

--
-- Event
--
function hookLanIPChangeEvent(ip)
    if XQFunction.isStrNil(ip) then
        return
    end
    local uci = require("luci.model.uci").cursor()
    local lan = ip:gsub(".%d+$","")
    uci:foreach("macbind", "host",
        function(s)
            local ip = s.ip
            ip = lan.."."..ip:match(".(%d+)$")
            uci:set("macbind", s[".name"], "ip", ip)
        end
    )
    uci:foreach("dhcp", "host",
        function(s)
            local ip = s.ip
            ip = lan.."."..ip:match(".(%d+)$")
            uci:set("dhcp", s[".name"], "ip", ip)
        end
    )
    uci:commit("dhcp")
    uci:commit("macbind")
end

--- Tag
--- 0:未添加
--- 1:已添加
--- 2:已绑定
function macBindInfo()
    local uci = require("luci.model.uci").cursor()
    local info = {}
    uci:foreach("macbind", "host",
        function(s)
            local item = {
                ["name"] = s.name,
                ["mac"] = s.mac,
                ["ip"] = s.ip,
                ["tag"] = 1
            }
            info[s.mac] = item
        end
    )
    uci:foreach("dhcp", "host",
        function(s)
            local item = {
                ["name"] = s.name,
                ["mac"] = s.mac,
                ["ip"] = s.ip,
                ["tag"] = 2
            }
            info[s.mac] = item
        end
    )
    return info
end

--- 0:设置成功
--- 1:IP冲突
--- 2:MAC/IP 不合法
function addBind(mac, ip)
    local uci = require("luci.model.uci").cursor()
    if _checkIP(ip) and _checkMac(mac) then
        local dhcp = _parseDhcpLeases()
        mac = string.lower(XQFunction.macFormat(mac))
        local host = dhcp[ip]
        if host and host.mac ~= mac then
            return 1
        end
        local name = _parseMac(mac)
        local options = {
            ["name"] = name,
            ["mac"] = mac,
            ["ip"] = ip
        }
        uci:section("macbind", "host", name, options)
        uci:commit("macbind")
    else
        return 2
    end
    return 0
end

function removeBind(mac)
    local uci = require("luci.model.uci").cursor()
    if _checkMac(mac) then
        local name = _parseMac(mac)
        uci:delete("macbind", name)
        uci:delete("dhcp", name)
        uci:commit("macbind")
        uci:commit("dhcp")
        return true
    else
        return false
    end
end

function unbindAll()
    local uci = require("luci.model.uci").cursor()
    uci:delete_all("dhcp", "host")
    uci:delete_all("macbind", "host")
    uci:commit("dhcp")
    uci:commit("macbind")
end

function saveBindInfo()
    local uci = require("luci.model.uci").cursor()
    uci:delete_all("dhcp", "host")
    uci:foreach("macbind", "host",
        function(s)
            local options = {
                ["name"] = s.name,
                ["mac"] = s.mac,
                ["ip"] = s.ip
            }
            uci:section("dhcp", "host", s.name, options)
        end
    )
    uci:commit("dhcp")
end
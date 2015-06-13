module ("xiaoqiang.util.XQDeviceUtil", package.seeall)

local Json = require("cjson")

local XQConfigs = require("xiaoqiang.common.XQConfigs")
local XQFunction = require("xiaoqiang.common.XQFunction")
local XQEquipment = require("xiaoqiang.XQEquipment")

function getDeviceCompany(mac)
    local companyInfo = { name = "", icon = "" }
    if XQFunction.isStrNil(mac) or string.len(mac) < 8 then
        return companyInfo
    end
    return XQEquipment.identifyDevice(mac, nil)
end

function getDeviceInfoFromDB()
    local XQDBUtil = require("xiaoqiang.util.XQDBUtil")
    local result = {}
    local deviceList = XQDBUtil.fetchAllDeviceInfo()
    if #deviceList > 0 then
        for _,device in ipairs(deviceList) do
            result[device.mac] = device
        end
    end
    return result
end

function saveDeviceName(mac,name)
    local XQDBUtil = require("xiaoqiang.util.XQDBUtil")
    local code = XQDBUtil.updateDeviceNickname(XQFunction.macFormat(mac),name)
    if code == 0 then
        return true
    else
        return false
    end
end

--
--	Get DHCP list
--

function getDHCPList()
    local NixioFs = require("nixio.fs")
    local LuciUci = require("luci.model.uci")
    local uci =  LuciUci.cursor()
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
                    result[#result+1] = {
                        mac  = XQFunction.macFormat(mac),
                        ip   = ip,
                        name = name
                    }
                end
            end
        end
        dhcp:close()
        return result
    else
        return false
    end
end

function getDHCPDict()
    local dhcpDict = {}
    local dhcpList = getDHCPList()
    for _,value in ipairs(dhcpList) do
        dhcpDict[value.mac] = value
    end
    return dhcpDict
end

function getDHCPIpDict()
    local dhcpDict = {}
    local dhcpList = getDHCPList()
    for _,value in ipairs(dhcpList) do
        dhcpDict[value.ip] = value
    end
    return dhcpDict
end

function getMacfilterInfoList()
    local LuciUtil = require("luci.util")
    local macFilterInfo = {}
    local metaData = LuciUtil.execi("/usr/sbin/sysapi macfilter get")
    for filterInfo in metaData do
        filterInfo = filterInfo..";"
        local mac = filterInfo:match('mac=(%S-);') or ""
        local wan = filterInfo:match('wan=(%S-);') or ""
        local lan = filterInfo:match('lan=(%S-);') or ""
        local admin = filterInfo:match('admin=(%S-);') or ""
        local pridisk = filterInfo:match('pridisk=(%S-);') or ""
        local entry = {}
        if (not XQFunction.isStrNil(mac)) then
            entry["mac"] = XQFunction.macFormat(mac)
            entry["wan"] = (string.upper(wan) == "YES" and true or false)
            entry["lan"] = (string.upper(lan) == "YES" and true or false)
            entry["admin"] = (string.upper(admin) == "YES" and true or false)
            entry["pridisk"] = (string.upper(pridisk) == "YES" and true or false)
            table.insert(macFilterInfo, entry)
        end
    end
    return macFilterInfo
end

function getMacfilterInfoDict()
    local macFilterDict = {}
    local macFilterList = getMacfilterInfoList()
    for _,value in ipairs(macFilterList) do
        macFilterDict[value.mac] = value
    end
    return macFilterDict
end

--[[
@param devName : lan/wan，其他情况 DEVNAME = DEV
]]--
function getWanLanNetworkStatistics(devName)
    local LuciUtil = require("luci.util")
    local tracmd = ""
    if devName == "lan" then
        tracmd = "ubus call trafficd lan"
    elseif devName == "wan" then
        tracmd = "ubus call trafficd wan"
    end
    local statistics = {
        ["upload"] = "0",
        ["upspeed"] = "0",
        ["download"] = "0",
        ["downspeed"] = "0",
        ["devname"] = "",
        ["maxuploadspeed"] = "0",
        ["maxdownloadspeed"] = "0"
    }

    local ubusinfo = LuciUtil.exec(tracmd)
    if XQFunction.isStrNil(ubusinfo) then
        return statistics
    end
    local ubusinfo = Json.decode(ubusinfo)
    if devName == "wan" then
        statistics.devname = tostring(ubusinfo.ifname)
        statistics.upload = tostring(ubusinfo.tx_bytes)
        statistics.download = tostring(ubusinfo.rx_bytes)
        statistics.upspeed = tostring(math.floor(ubusinfo.tx_rate or 0))
        statistics.downspeed = tostring(math.floor(ubusinfo.rx_rate or 0))
        statistics.maxuploadspeed = tostring(math.floor(ubusinfo.max_tx_rate or 0))
        statistics.maxdownloadspeed = tostring(math.floor(ubusinfo.max_rx_rate or 0))

        local history = LuciUtil.exec("ubus call trafficd list_wan_rate")
        if not XQFunction.isStrNil(history) then
            historylist = {}
            history = Json.decode(history)
            for _, rate in ipairs(history.rate) do
                if rate then
                    table.insert(historylist, tostring(rate))
                end
            end
            statistics.history = table.concat(historylist, ",")
        end
    else
        statistics.devname = tostring(ubusinfo.ifname)
        statistics.upload = tostring(ubusinfo.rx_bytes)
        statistics.download = tostring(ubusinfo.tx_bytes)
        statistics.upspeed = tostring(math.floor(ubusinfo.rx_rate or 0))
        statistics.downspeed = tostring(math.floor(ubusinfo.tx_rate or 0))
        statistics.maxuploadspeed = tostring(math.floor(ubusinfo.max_rx_rate or 0))
        statistics.maxdownloadspeed = tostring(math.floor(ubusinfo.max_tx_rate or 0))
    end
    return statistics
end

--[[
@param mac=B8:70:F4:27:0C:1B 网卡mac地址
@param upload=14745         主机当前累计上传数据总量（byte）
@param upspeed=54            主机5秒平均上传速度（byte/s）
@param download=25777       主机当前累计下载数据总量（byte）
@param downspeed=120         主机5秒平均下载速度（byte/s）
@param oneline=169           主机在线时长（秒）
@param devname               设备名
@param maxuploadspeed        上传峰值
@param maxdownloadspeed      下载峰值
]]--
function getDevNetStatisticsList()
    local LuciUtil = require("luci.util")
    local statList = {}
    local dhcpNameDict = getDHCPDict()
    local deviceInfoDict = getDeviceInfoFromDB()

    local deviceinfo = LuciUtil.exec("ubus call trafficd hw")
    if XQFunction.isStrNil(deviceinfo) then
        return statList
    else
        deviceinfo = Json.decode(deviceinfo)
    end
    for key, dev in pairs(deviceinfo) do
        if dev then
            local item = {}
            local mac = XQFunction.macFormat(key)
            local name, nickName, oriName
            if dhcpNameDict[mac] then
                oriName = dhcpNameDict[mac].name
            end
            local device = deviceInfoDict[mac]
            if device then
                if XQFunction.isStrNil(oriName) then
                    oriName = device.oName
                end
                nickName = device.nickname
            end
            local company = XQEquipment.identifyDevice(mac, oriName)
            local dtype = company["type"]
            if not XQFunction.isStrNil(nickName) then
                 name = nickName
            end
            if XQFunction.isStrNil(name) and not XQFunction.isStrNil(dtype.n) then
                name = dtype.n
            end
            if XQFunction.isStrNil(name) and not XQFunction.isStrNil(oriName) then
                name = oriName
            end
            if XQFunction.isStrNil(name) and not XQFunction.isStrNil(company.name) then
                name = company.name
            end
            if XQFunction.isStrNil(name) then
                name = mac
            end

            local tx_bytes, tx_rate, rx_bytes, rx_rate, max_tx_rate, max_rx_rate = 0, 0, 0, 0, 0, 0
            local iplist = dev.ip_list
            if #iplist > 0 then
                for _, ip in ipairs(iplist) do
                    tx_bytes = tx_bytes + ip.tx_bytes or 0
                    rx_bytes = rx_bytes + ip.rx_bytes or 0
                    tx_rate = tx_rate + ip.tx_rate or 0
                    rx_rate = rx_rate + ip.rx_rate or 0
                    max_tx_rate = max_tx_rate + ip.max_tx_rate or 0
                    max_rx_rate = max_rx_rate + ip.max_rx_rate or 0
                end
            end
            item["mac"] = mac
            item["upload"] = tostring(tx_bytes)
            item["upspeed"] = tostring(math.floor(tx_rate))
            item["download"] = tostring(rx_bytes)
            item["downspeed"] = tostring(math.floor(rx_rate))
            item["online"] = tostring(dev.online_timer or 0)
            item["devname"] = name
            item["maxuploadspeed"] = tostring(math.floor(max_tx_rate))
            item["maxdownloadspeed"] = tostring(math.floor(max_rx_rate))
            statList[#statList+1] = item
        end
    end
    return statList
end

function getDevNetStatisticsDict()
    local statDict = {}
    local statlist = getDevNetStatisticsList()
    for _, item in ipairs(statlist) do
        if item then
            statDict[item.mac] = item
        end
    end
    return statDict
end

function getConnectDeviceCount()
    local LuciUtil = require("luci.util")
    local count = 0
    local deviceinfo = LuciUtil.exec("ubus call trafficd hw")
    if XQFunction.isStrNil(deviceinfo) then
        return count
    else
        deviceinfo = Json.decode(deviceinfo)
    end
    for _,device in pairs(deviceinfo) do
        if device and device.ip_list and #device.ip_list > 0 then
            local dev = device.ifname
            if not XQFunction.isStrNil(dev) and (tonumber(device.assoc) == 1 or not dev:match("wl")) then
                local tcount = 0
                for _,ip in ipairs(device.ip_list) do
                    if ip.ageing_timer <= 300 then
                        if tcount >= 1 then
                            break
                        end
                        count = count + 1
                        tcount = tcount + 1
                    end
                end
            end
        end
    end
    return count
end

--[[
@return online:      0 (offline) 1 (online)
@return ip:          ip address
@return mac:         mac address
@return type:        wifi/line
@return tag:         1 (normal) 2 (in denylist)
@return port:        1 (2.4G wifi) 2 (5G wifi)
@return name:        name for show
@return origin_name: origin name
@return signal:      wifi signal
@return statistics:
]]--
function getConnectDeviceList()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local XQDBUtil = require("xiaoqiang.util.XQDBUtil")
    local XQEquipment = require("xiaoqiang.XQEquipment")
    local XQLog = require("xiaoqiang.XQLog")

    local LuciUtil = require("luci.util")
    local deviceList = {}

    local deviceinfo = LuciUtil.exec("ubus call trafficd hw")
    if XQFunction.isStrNil(deviceinfo) then
        return deviceList
    else
        deviceinfo = Json.decode(deviceinfo)
    end

    local macFilterDict = getMacfilterInfoDict()
    local dhcpDeviceDict = getDHCPDict()
    local dhcpDevIpDict = getDHCPIpDict()
    local deviceInfoDict = getDeviceInfoFromDB()
    local wifiDeviceDict = XQWifiUtil.getAllWifiConnetDeviceDict()

    for key ,item in pairs(deviceinfo) do
        if item and item.ip_list and #item.ip_list > 0 then
            local devicesignal, devicetype, deviceport
            local dev = item.ifname
            local mac = XQFunction.macFormat(key)

            if not XQFunction.isStrNil(dev) and (tonumber(item.assoc) == 1 or not dev:match("wl")) then
                -- 信号强度
                local signal = wifiDeviceDict[mac]
                if signal and signal.signal then
                    devicesignal = signal.signal
                else
                    devicesignal = ""
                end
                if dev:match("eth") then
                    devicetype = "line"
                    deviceport = 0
                elseif dev == "wl0" then
                    devicetype = "wifi"
                    deviceport = 2
                elseif dev == "wl1" then
                    devicetype = "wifi"
                    deviceport = 1
                elseif dev == "wl1.2" then
                    devicetype = "wifi"
                    deviceport = 3
                end

                -- 访问权限
                local authority = {}
                if (macFilterDict[mac]) then
                    authority["wan"] = macFilterDict[mac]["wan"] and 1 or 0
                    authority["lan"] = macFilterDict[mac]["lan"] and 1 or 0
                    authority["admin"] = macFilterDict[mac]["admin"] and 1 or 0
                    authority["pridisk"] = macFilterDict[mac]["pridisk"] and 1 or 0
                else 
                    authority["wan"] = 1
                    authority["lan"] = 1
                    authority["admin"] = 1
                    -- private disk deny access default
                    authority["pridisk"] = 0
                end

                local deviceInfo = deviceInfoDict[mac]
                local dhcpinfo = dhcpDeviceDict[mac]
                if not deviceInfo and dhcpinfo then
                    XQDBUtil.saveDeviceInfo(mac,dhcpinfo.name,"","","")
                end
                local count = 0
                for _,ip in ipairs(item.ip_list) do
                    if ip.ageing_timer <= 300 then
                        if count >= 1 then
                            break
                        end
                        -- 设备名称
                        local name, originName, nickName, company
                        if dhcpDevIpDict[ip.ip] ~= nil then
                            originName = dhcpDevIpDict[ip.ip].name
                        end

                        if originName and originName:match("^xiaomi%-ir") then -- fix miio model string
                            originName = originName:gsub("%-",".")
                        end

                        if deviceInfo then
                            if XQFunction.isStrNil(originName) then
                                originName = deviceInfo.oName
                            end
                            nickName = deviceInfo.nickname
                        end
                        if not XQFunction.isStrNil(nickName) then
                            name = nickName
                        end
                        company = XQEquipment.identifyDevice(mac, originName)
                        local dtype = company["type"]
                        if XQFunction.isStrNil(name) and not XQFunction.isStrNil(dtype.n) then
                            name = dtype.n
                        end
                        if XQFunction.isStrNil(name) and not XQFunction.isStrNil(originName) then
                            name = originName
                        end
                        if XQFunction.isStrNil(name) and not XQFunction.isStrNil(company.name) then
                            name = company.name
                        end
                        if XQFunction.isStrNil(name) then
                            name = mac
                        end
                        if dtype.c == 3 and XQFunction.isStrNil(nickName) then
                            name = dtype.n
                        end
                        local device = {
                            ["ip"] = ip.ip,
                            ["mac"] = mac,
                            ["online"] = 1,
                            ["type"] = devicetype,
                            ["port"] = deviceport,
                            ["ctype"] = dtype.c,
                            ["ptype"] = dtype.p,
                            ["origin_name"] = originName or "",
                            ["name"] = name,
                            ["company"] = company,
                            ["authority"] = authority
                        }
                        local statistics = {}
                        statistics["dev"] = dev
                        statistics["mac"] = mac
                        statistics["ip"] = ip.ip
                        statistics["upload"] = tostring(ip.tx_bytes or 0)
                        statistics["upspeed"] = tostring(math.floor(ip.tx_rate or 0))
                        statistics["download"] = tostring(ip.rx_bytes or 0)
                        statistics["downspeed"] = tostring(math.floor(ip.rx_rate or 0))
                        statistics["online"] = tostring(item.online_timer or 0)
                        statistics["maxuploadspeed"] = tostring(math.floor(ip.max_tx_rate or 0))
                        statistics["maxdownloadspeed"] = tostring(math.floor(ip.max_rx_rate or 0))
                        device["statistics"] = statistics
                        table.insert(deviceList, device)
                        count = count + 1
                    end
                end
            end
        end
    end
    -- if #deviceList > 0 then
    --     table.sort(deviceList,
    --         function(a, b)
    --             return b.statistics.onlinets < a.statistics.onlinets
    --         end
    --     )
    -- end
    return deviceList
end

function getConDevices(withbrlan)
    local XQDBUtil = require("xiaoqiang.util.XQDBUtil")
    local XQEquipment = require("xiaoqiang.XQEquipment")
    local XQLog = require("xiaoqiang.XQLog")

    local LuciUtil = require("luci.util")
    local deviceList = {}

    local deviceinfo = LuciUtil.exec("ubus call trafficd hw")
    if XQFunction.isStrNil(deviceinfo) then
        return deviceList
    else
        deviceinfo = Json.decode(deviceinfo)
    end

    local dhcpDevIpDict = getDHCPIpDict()
    --local dhcpDeviceDict = getDHCPDict()
    local deviceInfoDict = getDeviceInfoFromDB()

    for key ,item in pairs(deviceinfo) do
        if item and item.ip_list and #item.ip_list > 0 then
            local dev = item.ifname
            local mac = XQFunction.macFormat(key)

            if not XQFunction.isStrNil(dev) and ((not dev:match("wl") and withbrlan) or (dev:match("wl") and tonumber(item.assoc) == 1)) then
                local count = 0
                for _,ip in ipairs(item.ip_list) do
                    if count >= 1 then
                        break
                    end
                    local name, originName, nickName, company
                    if dhcpDevIpDict[ip.ip] ~= nil then
                        originName = dhcpDevIpDict[ip.ip].name
                    end
                    local deviceInfo = deviceInfoDict[mac]
                    if deviceInfo then
                        if XQFunction.isStrNil(originName) then
                            originName = deviceInfo.oName
                        end
                        nickName = deviceInfo.nickname
                    end
                    if not XQFunction.isStrNil(nickName) then
                        name = nickName
                    end
                    company = XQEquipment.identifyDevice(mac, originName)

                    local dtype = company["type"]
                    if XQFunction.isStrNil(name) and not XQFunction.isStrNil(dtype.n) then
                        name = dtype.n
                    end
                    if XQFunction.isStrNil(name) and not XQFunction.isStrNil(originName) then
                        name = originName
                    end
                    if XQFunction.isStrNil(name) and not XQFunction.isStrNil(company.name) then
                        name = company.name
                    end
                    if XQFunction.isStrNil(name) then
                        name = mac
                    end
                    if dtype.c == 3 and XQFunction.isStrNil(nickName) then
                        name = dtype.n
                    end
                    if ip.ageing_timer <= 300 then
                        local device = {
                            ["ip"] = ip.ip,
                            ["mac"] = mac,
                            ["online"] = 1,
                            ["ctype"] = dtype.c,
                            ["ptype"] = dtype.p,
                            ["origin_name"] = originName or "",
                            ["name"] = name,
                            ["company"] = company
                        }
                        local statistics = {}
                        statistics["dev"] = dev
                        statistics["mac"] = mac
                        statistics["ip"] = ip.ip
                        statistics["upload"] = tostring(ip.tx_bytes or 0)
                        statistics["upspeed"] = tostring(math.floor(ip.tx_rate or 0))
                        statistics["download"] = tostring(ip.rx_bytes or 0)
                        statistics["downspeed"] = tostring(math.floor(ip.rx_rate or 0))
                        statistics["online"] = tostring(item.online_timer or 0)
                        statistics["maxuploadspeed"] = tostring(math.floor(ip.max_tx_rate or 0))
                        statistics["maxdownloadspeed"] = tostring(math.floor(ip.max_rx_rate or 0))
                        device["statistics"] = statistics
                        table.insert(deviceList, device)
                        count = count + 1
                    end
                end
            end
        end
    end
    return deviceList
end

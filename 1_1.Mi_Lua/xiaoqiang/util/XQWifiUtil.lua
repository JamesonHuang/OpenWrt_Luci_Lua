module ("xiaoqiang.util.XQWifiUtil", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")

local LuciNetwork = require("luci.model.network")
local LuciUtil = require("luci.util")

local UCI = require("luci.model.uci").cursor()
local WIFI2G = UCI:get("misc", "wireless", "if_2G")
local WIFI5G = UCI:get("misc", "wireless", "if_5G")

local WIFI_DEVS = {
    WIFI2G,
    WIFI5G
}

local WIFI_NETS = {
    WIFI2G..".network1",
    WIFI5G..".network1"
}

function getWifiNames()
    return WIFI_DEVS, WIFI_NETS
end

function _wifiNameForIndex(index)
    return WIFI_NETS[index]
end

function wifiNetworks()
    local result = {}
    local network = LuciNetwork.init()
    local dev
    for _, dev in ipairs(network:get_wifidevs()) do
        local rd = {
            up       = dev:is_up(),
            device   = dev:name(),
            name     = dev:get_i18n(),
            networks = {}
        }
        local wifiNet
        for _, wifiNet in ipairs(dev:get_wifinets()) do
            rd.networks[#rd.networks+1] = {
                name       = wifiNet:shortname(),
                up         = wifiNet:is_up(),
                mode       = wifiNet:active_mode(),
                ssid       = wifiNet:active_ssid(),
                bssid      = wifiNet:active_bssid(),
                encryption = wifiNet:active_encryption(),
                frequency  = wifiNet:frequency(),
                channel    = wifiNet:channel(),
                cchannel   = wifiNet:confchannel(),
                bw         = wifiNet:bw(),
                cbw        = wifiNet:confbw(),
                signal     = wifiNet:signal(),
                quality    = wifiNet:signal_percent(),
                noise      = wifiNet:noise(),
                bitrate    = wifiNet:bitrate(),
                ifname     = wifiNet:ifname(),
                assoclist  = wifiNet:assoclist(),
                country    = wifiNet:country(),
                txpower    = wifiNet:txpower(),
                txpoweroff = wifiNet:txpower_offset(),
                key	   	   = wifiNet:get("key"),
                key1	   = wifiNet:get("key1"),
                encryption_src = wifiNet:get("encryption"),
                hidden = wifiNet:get("hidden"),
                txpwr = wifiNet:txpwr()
            }
        end
        result[#result+1] = rd
    end
    return result
end

function wifiNetwork(wifiDeviceName)
    local network = LuciNetwork.init()
    local wifiNet = network:get_wifinet(wifiDeviceName)
    if wifiNet then
        local dev = wifiNet:get_device()
        if dev then
            return {
                id         = wifiDeviceName,
                name       = wifiNet:shortname(),
                up         = wifiNet:is_up(),
                mode       = wifiNet:active_mode(),
                ssid       = wifiNet:active_ssid(),
                bssid      = wifiNet:active_bssid(),
                encryption = wifiNet:active_encryption(),
                encryption_src = wifiNet:get("encryption"),
                frequency  = wifiNet:frequency(),
                channel    = wifiNet:channel(),
                cchannel   = wifiNet:confchannel(),
                bw         = wifiNet:bw(),
                cbw        = wifiNet:confbw(),
                signal     = wifiNet:signal(),
                quality    = wifiNet:signal_percent(),
                noise      = wifiNet:noise(),
                bitrate    = wifiNet:bitrate(),
                ifname     = wifiNet:ifname(),
                assoclist  = wifiNet:assoclist(),
                country    = wifiNet:country(),
                txpower    = wifiNet:txpower(),
                txpoweroff = wifiNet:txpower_offset(),
                key        = wifiNet:get("key"),
                key1	   = wifiNet:get("key1"),
                hidden     = wifiNet:get("hidden"),
                txpwr = wifiNet:txpwr(),
                device     = {
                    up     = dev:is_up(),
                    device = dev:name(),
                    name   = dev:get_i18n()
                }
            }
        end
    end
    return {}
end

--[[
Get devices conneted to wifi
@param wifiIndex: 1 (2.4G)/ 2 (5G)
@return avaliable channel list
]]--
function getChannels(wifiIndex)
    local stat, iwinfo = pcall(require, "iwinfo")
    local iface = _wifiNameForIndex(wifiIndex)
    local cns
    if stat then
        local t = iwinfo.type(iface or "")
        if iface and t and iwinfo[t] then
            cns = iwinfo[t].freqlist(iface)
        end
    end
    return cns
end

local wifi24 = {
    ["1"] = {["20"] = "1", ["40"] = "1l"},
    ["2"] = {["20"] = "2", ["40"] = "2l"},
    ["3"] = {["20"] = "3", ["40"] = "3l"},
    ["4"] = {["20"] = "4", ["40"] = "4l"},
    ["5"] = {["20"] = "5", ["40"] = "5l"},
    ["6"] = {["20"] = "6", ["40"] = "6l"},
    ["7"] = {["20"] = "7", ["40"] = "7l"},
    ["8"] = {["20"] = "8", ["40"] = "8u"},
    ["9"] = {["20"] = "9", ["40"] = "9u"},
    ["10"] = {["20"] = "10", ["40"] = "10u"},
    ["11"] = {["20"] = "11", ["40"] = "11u"},
    ["12"] = {["20"] = "12", ["40"] = "12u"},
    ["13"] = {["20"] = "13", ["40"] = "13u"}
}

local wifi50 = {
    ["36"] = {["20"] = "36", ["40"] = "36l", ["80"] = "36/80"},
    ["40"] = {["20"] = "40", ["40"] = "40u", ["80"] = "40/80"},
    ["44"] = {["20"] = "44", ["40"] = "44l", ["80"] = "44/80"},
    ["48"] = {["20"] = "48", ["40"] = "48u", ["80"] = "48/80"},
    ["52"] = {["20"] = "52", ["40"] = "52l", ["80"] = "52/80"},
    ["56"] = {["20"] = "56", ["40"] = "56u", ["80"] = "56/80"},
    ["60"] = {["20"] = "60", ["40"] = "60l", ["80"] = "60/80"},
    ["64"] = {["20"] = "64", ["40"] = "64u", ["80"] = "64/80"},
    ["149"] = {["20"] = "149", ["40"] = "149l", ["80"] = "149/80"},
    ["153"] = {["20"] = "153", ["40"] = "153u", ["80"] = "153/80"},
    ["157"] = {["20"] = "157", ["40"] = "157l", ["80"] = "157/80"},
    ["161"] = {["20"] = "161", ["40"] = "161u", ["80"] = "161/80"},
    ["165"] = {["20"] = "165"}
}

function getDefaultWifiChannels(wifiIndex)
    if tonumber(wifiIndex) == 1 then
        return wifi24
    else
        return wifi50
    end
end

--[[
Get devices conneted to wifi
@param wifiIndex: 1 (2.4G)/ 2 (5G)
@return divices list
]]--
function getWifiConnectDeviceList(wifiIndex)
    local wifiUp
    local assoclist = {}
    if tonumber(wifiIndex) == 1 then
        wifiUp = (getWifiStatus(1).up == 1)
        assoclist = wifiNetwork(_wifiNameForIndex(1)).assoclist or {}
    else
        wifiUp = (getWifiStatus(2).up == 1)
        assoclist = wifiNetwork(_wifiNameForIndex(2)).assoclist or {}
    end
    local dlist = {}
    if wifiUp then
        for mac, info in pairs(assoclist) do
            table.insert(dlist, XQFunction.macFormat(mac))
        end
    end
    return dlist
end

function isDeviceWifiConnect(mac,wifiIndex)
    local dict = getWifiConnectDeviceDict(wifiIndex)
    if type(dict) == "table" and #dict>0 then
        return dict[XQFunction.macFormat(mac)] ~= nil
    else
        return false
    end
end

--[[
Get devices conneted to wifi
@param wifiIndex: 1 (2.4G)/ 2 (5G)
@return divices dict{mac:1}
]]--
function getWifiConnectDeviceDict(wifiIndex)
    local wifiUp
    local assoclist = {}
    if tonumber(wifiIndex) == 1 then
        wifiUp = (getWifiStatus(1).up == 1)
        assoclist = wifiNetwork(_wifiNameForIndex(1)).assoclist or {}
    else
        wifiUp = (getWifiStatus(2).up == 1)
        assoclist = wifiNetwork(_wifiNameForIndex(2)).assoclist or {}
    end
    local dict = {}
    if wifiUp then
        for mac, info in pairs(assoclist) do
            if mac then
                dict[XQFunction.macFormat(mac)] = 1
            end
        end
    end
    return dict
end

function _pauseChannel(channel)
    if XQFunction.isStrNil(channel) then
        return ""
    end
    if channel:match("l") then
        return channel:gsub("l","").."(40M)"
    end
    if channel:match("u") then
        return channel:gsub("u","").."(40M)"
    end
    if channel:match("\/80") then
        return channel:gsub("\/80","").."(80M)"
    end
    return channel.."(20M)"
end

function getWifiWorkChannel(wifiIndex)
    local channel = ""
    if tonumber(wifiIndex) == 1 then
        channel = LuciUtil.trim(LuciUtil.exec(XQConfigs.WIFI24_WORK_CHANNEL))
    else
        channel = LuciUtil.trim(LuciUtil.exec(XQConfigs.WIFI50_WORK_CHANNEL))
    end
    return _pauseChannel(channel)
end

--[[
Get device wifiIndex
@param mac: mac address
@return 0 (lan)/1 (2.4G)/ 2 (5G)
]]--
function getDeviceWifiIndex(mac)
    mac = XQFunction.macFormat(mac)
    local wifi1Devices = getWifiConnectDeviceDict(1)
    local wifi2Devices = getWifiConnectDeviceDict(2)
    if wifi1Devices then
        if wifi1Devices[mac] == 1 then
            return 1
        end
    end
    if wifi2Devices then
        if wifi2Devices[mac] == 1 then
            return 2
        end
    end
    return 0
end

function getWifiDeviceSignalDict(wifiIndex)
    local result = {}
    local assoclist = {}
    if not (getWifiStatus(wifiIndex).up == 1) then
        return result
    end
    if wifiIndex == 1 then
        assoclist = wifiNetwork(_wifiNameForIndex(1)).assoclist or {}
    else
        assoclist = wifiNetwork(_wifiNameForIndex(2)).assoclist or {}
    end
    for mac, info in pairs(assoclist) do
        if mac then
            result[XQFunction.macFormat(mac)] = 2*math.abs(tonumber(info.signal)-tonumber(info.noise))
        end
    end
    return result
end

--[[
Get all devices conneted to wifi
@return devices list [{mac,signal,wifiIndex}..]
]]--
function getAllWifiConnetDeviceList()
    local result = {}
    for index = 1,2 do
        local wifiSignal = getWifiDeviceSignalDict(index)
        local wifilist = getWifiConnectDeviceList(index)
        for _, mac in pairs(wifilist) do
            table.insert(result, {
                    ['mac'] = XQFunction.macFormat(mac),
                    ['signal'] = wifiSignal[mac],
                    ['wifiIndex'] = index
                })
        end
    end
    return result
end

--[[
Get all devices conneted to wifi
@return devices dict{mac:{signal,wifiIndex}}
]]--
function getAllWifiConnetDeviceDict()
    local result = {}
    for index = 1,2 do
        local wifiSignal = getWifiDeviceSignalDict(index)
        local wifilist = getWifiConnectDeviceList(index)
        for _, mac in pairs(wifilist) do
            local item = {}
            item['signal'] = wifiSignal[mac]
            item['wifiIndex'] = index
            result[XQFunction.macFormat(mac)] = item
        end
    end
    return result
end

--[[
Get wifi status
@param wifiIndex: 1 (2.4G)/ 2 (5G)
@return dict{ssid,up}
]]--
function getWifiStatus(wifiIndex)
    local wifiNet = wifiNetwork(_wifiNameForIndex(wifiIndex))
    return {
        ['ssid'] = wifiNet["ssid"],
        ['up'] = wifiNet["up"] and 1 or 0
    }
end

function channelHelper(channel)
    local channelInfo = {channel = "", bandwidth = ""}
    if XQFunction.isStrNil(channel) then
        return channelInfo
    end
    if string.find(channel,"l") ~= nil then
        channelInfo["channel"] = channel:match("(%S+)l")
        channelInfo["bandwidth"] = "40"
    elseif string.find(channel,"u") ~= nil then
        channelInfo["channel"] = channel:match("(%S+)u")
        channelInfo["bandwidth"] = "40"
    elseif string.find(channel,"/80") ~= nil then
        channelInfo["channel"] = channel:match("(%S+)/80")
        channelInfo["bandwidth"] = "80"
    else
        channelInfo["channel"] = tostring(channel)
        channelInfo["bandwidth"] = "20"
    end
    local bandList = {}
    if channelInfo.channel then
        local channelList = wifi24[channelInfo.channel] or wifi50[channelInfo.channel]
        if channelList and type(channelList) == "table" then
            for key, v in pairs(channelList) do
                table.insert(bandList, key)
            end
        end
    end
    channelInfo["bandList"] = bandList
    return channelInfo
end

function getBandList(channel)
    local channelInfo = {channel = "", bandwidth = ""}
    if XQFunction.isStrNil(channel) then
        return channelInfo
    end
    local bandList = {}
    local channelList = wifi24[tostring(channel)] or wifi50[tostring(channel)]
    if channelList and type(channelList) == "table" then
        for key, v in pairs(channelList) do
            table.insert(bandList, key)
        end
    end
    channelInfo["bandList"] = bandList
    return channelInfo
end

function _channelFix(channel)
    if XQFunction.isStrNil(channel) then
        return ""
    end
    channel = string.gsub(channel, "l", "")
    channel = string.gsub(channel, "u", "")
    channel = string.gsub(channel, "/80", "")
    return channel
end

function channelFormat(wifiIndex, channel, bandwidth)
    local channelList = {}
    if tonumber(wifiIndex) == 1 then
        channelList = wifi24[tostring(channel)]
    else
        channelList = wifi50[tostring(channel)]
    end
    if channelList and type(channelList) == "table" then
        local channel = channelList[tostring(bandwidth)]
        if not XQFunction.isStrNil(channel) then
            return channel
        end
    end
    return false
end

--[[
Get wifi information
@return dict{status,ifname,device,ssid,encryption,channel,mode,hidden,signal,password}
]]--
function getAllWifiInfo()
    local infoList = {}
    local wifis = wifiNetworks()
    for i,wifiNet in ipairs(wifis) do
        local item = {}
        local index = 1
        if wifiNet["up"] then
            item["status"] = "1"
        else
            item["status"] = "0"
        end
        local encryption = wifiNet.networks[index].encryption_src
        local key = wifiNet.networks[index].key
        if encryption == "wep-open" then
            key = wifiNet.networks[index].key1
            if key:len()>4 and key:sub(0,2)=="s:" then
                key = key:sub(3)
            end
        end
        local channel = wifiNet.networks[index].cchannel
        local channelparseinfo = channelHelper(channel)
        item["ifname"] = wifiNet.networks[index].ifname
        item["device"] = wifiNet.device..".network"..index
        item["ssid"] = wifiNet.networks[index].ssid
        item["channel"] = channelparseinfo.channel
        item["bandwidth"] = wifiNet.networks[index].cbw or channelparseinfo.bandwidth or "0"
        item["channelInfo"] = getBandList(channel)
        item["channelInfo"]["channel"] = wifiNet.networks[index].channel
        item["channelInfo"]["bandwidth"] = wifiNet.networks[index].bw or "20"
        item["mode"] = wifiNet.networks[index].mode
        item["hidden"] = wifiNet.networks[index].hidden or 0
        item["signal"] = wifiNet.networks[index].signal
        item["password"] = key
        item["encryption"] = encryption
        item["txpwr"] = wifiNet.networks[index].txpwr
        infoList[#wifis+1-i] = item
    end
    local guestwifi = getGuestWifi(1)
    if guestwifi then
        table.insert(infoList, guestwifi)
    end
    return infoList
end

function getWifiTxpwr(wifiIndex)
    local network = LuciNetwork.init()
    local wifiNet = network:get_wifinet(_wifiNameForIndex(wifiIndex))
    if wifiNet then
        return tostring(wifiNet:txpwr())
    else
        return nil
    end
end

function getWifiChannel(wifiIndex)
    local network = LuciNetwork.init()
    local wifiNet = network:get_wifinet(_wifiNameForIndex(wifiIndex))
    if wifiNet then
        return tostring(wifiNet:channel())
    else
        return nil
    end
end

function getWifiTxpwrList()
    local txpwrList = {}
    local network = LuciNetwork.init()
    local wifiNet1 = network:get_wifinet(_wifiNameForIndex(1))
    local wifiNet2 = network:get_wifinet(_wifiNameForIndex(2))
    if wifiNet1 then
        table.insert(txpwrList,tostring(wifiNet1:txpwr()))
    end
    if wifiNet2 then
        table.insert(txpwrList,tostring(wifiNet2:txpwr()))
    end
    return txpwrList
end

function getWifiChannelList()
    local channelList = {}
    local network = LuciNetwork.init()
    local wifiNet1 = network:get_wifinet(_wifiNameForIndex(1))
    local wifiNet2 = network:get_wifinet(_wifiNameForIndex(2))
    if wifiNet1 then
        table.insert(channelList,tostring(wifiNet1:channel()))
    end
    if wifiNet2 then
        table.insert(channelList,tostring(wifiNet2:channel()))
    end
    return channelList
end

function getWifiChannelTxpwrList()
    local result = {}
    local network = LuciNetwork.init()
    local wifiNet1 = network:get_wifinet(_wifiNameForIndex(1))
    local wifiNet2 = network:get_wifinet(_wifiNameForIndex(2))
    if wifiNet1 then
        table.insert(result,{
            channel = tostring(wifiNet1:channel()),
            txpwr = tostring(wifiNet1:txpwr())
        })
    else
        table.insert(result,{})
    end
    if wifiNet2 then
        table.insert(result,{
            channel = tostring(wifiNet2:channel()),
            txpwr = tostring(wifiNet2:txpwr())
        })
    else
        table.insert(result,{})
    end
    return result
end

function setWifiChannelTxpwr(channel1,txpwr1,channel2,txpwr2)
    local network = LuciNetwork.init()
    local wifiDev1 = network:get_wifidev(LuciUtil.split(_wifiNameForIndex(1),".")[1])
    local wifiDev2 = network:get_wifidev(LuciUtil.split(_wifiNameForIndex(2),".")[1])
    if wifiDev1 then
        if tonumber(channel1) then
            wifiDev1:set("channel",channel1)
        end
        if not XQFunction.isStrNil(txpwr1) then
            wifiDev1:set("txpwr",txpwr1);
        end
    end
    if wifiDev2 then
        if tonumber(channel2) then
            wifiDev2:set("channel",channel2)
        end
        if not XQFunction.isStrNil(txpwr2) then
            wifiDev2:set("txpwr",txpwr2);
        end
    end
    network:commit("wireless")
    network:save("wireless")
    return true
end

function setWifiTxpwr(txpwr)
    local network = LuciNetwork.init()
    local wifiDev1 = network:get_wifidev(LuciUtil.split(_wifiNameForIndex(1),".")[1])
    local wifiDev2 = network:get_wifidev(LuciUtil.split(_wifiNameForIndex(2),".")[1])
    if wifiDev1 then
        if not XQFunction.isStrNil(txpwr) then
            wifiDev1:set("txpwr",txpwr);
        end
    end
    if wifiDev2 then
        if not XQFunction.isStrNil(txpwr) then
            wifiDev2:set("txpwr",txpwr);
        end
    end
    network:commit("wireless")
    network:save("wireless")
    return true
end

function checkWifiPasswd(passwd,encryption)
    if XQFunction.isStrNil(encryption) or (encryption and encryption ~= "none" and XQFunction.isStrNil(passwd)) then
        return 1502
    end
    if encryption == "psk" or encryption == "psk2" then
        if  passwd:len() < 8 then
            return 1520
        end
    elseif encryption == "mixed-psk" then
        if  passwd:len()<8 or passwd:len()>63 then
            return 1521
        end
    elseif encryption == "wep-open" then
        if  passwd:len()~=5 and passwd:len()~=13 then
            return 1522
        end
    end
    return 0
end

function checkSSID(ssid,length)
    if XQFunction.isStrNil(ssid) then
        return 0
    end
    if string.len(ssid) > tonumber(length) then
        return 1572
    end
    if not XQFunction.checkSSID(ssid) then
        return 1573
    end
    return 0
end

function setWifiBasicInfo(wifiIndex, ssid, password, encryption, channel, txpwr, hidden, on, bandwidth)
    local network = LuciNetwork.init()
    local wifiNet = network:get_wifinet(_wifiNameForIndex(wifiIndex))
    local wifiDev = network:get_wifidev(LuciUtil.split(_wifiNameForIndex(wifiIndex),".")[1])
    if wifiNet == nil then
        return false
    end
    if wifiDev then
        if not XQFunction.isStrNil(channel) then
            wifiDev:set("channel",channel)
            if channel == "0" then
                wifiDev:set("autoch","2")
            else
                wifiDev:set("autoch","0")
            end
        end
        if not XQFunction.isStrNil(bandwidth) then
            wifiDev:set("bw",bandwidth)
        end
        if not XQFunction.isStrNil(txpwr) then
            wifiDev:set("txpwr",txpwr);
        end
        if on == 1 then
            wifiDev:set("disabled", "0")
        elseif on == 0 then
            wifiDev:set("disabled", "1")
        end
    end
    wifiNet:set("disabled", nil)
    if not XQFunction.isStrNil(ssid) and XQFunction.checkSSID(ssid) then
        wifiNet:set("ssid",ssid)
    end
    local code = checkWifiPasswd(password,encryption)
    if code == 0 then
        wifiNet:set("encryption",encryption)
        wifiNet:set("key",password)
        if encryption == "none" then
            wifiNet:set("key","")
        elseif encryption == "wep-open" then
            wifiNet:set("key1","s:"..password)
            wifiNet:set("key",1)
        end
        if wifiIndex == 1 then
            XQFunction.nvramSet("nv_wifi_ssid", ssid)
            XQFunction.nvramSet("nv_wifi_enc", encryption)
            XQFunction.nvramSet("nv_wifi_pwd", password)
            XQFunction.nvramCommit()
        else
            XQFunction.nvramSet("nv_wifi_ssid1", ssid)
            XQFunction.nvramSet("nv_wifi_enc1", encryption)
            XQFunction.nvramSet("nv_wifi_pwd1", password)
            XQFunction.nvramCommit()
        end
    elseif code > 1502 then
        return false
    end
    if hidden == "1" then
        wifiNet:set("hidden","1")
    end
    if hidden == "0" then
        wifiNet:set("hidden","0")
    end
    network:save("wireless")
    network:commit("wireless")
    return true
end

--[[
Turn on wifi
@param wifiIndex: 1 (2.4G)/ 2 (5G)
@return boolean
]]--
function turnWifiOn(wifiIndex)
    local wifiStatus = getWifiStatus(wifiIndex)
    if wifiStatus['up'] == 1 then
        return true
    end
    local network = LuciNetwork.init()
    local wifiNet = network:get_wifinet(_wifiNameForIndex(wifiIndex))
    local dev
    if wifiNet ~= nil then
        dev = wifiNet:get_device()
    end
    if dev and wifiNet then
        dev:set("disabled", "0")
        wifiNet:set("disabled", nil)
        network:commit("wireless")
        XQFunction.forkRestartWifi()
        return true
    end
    return false
end

--[[
Turn off wifi
@param wifiIndex: 1 (2.4G)/ 2 (5G)
@return boolean
]]--
function turnWifiOff(wifiIndex)
    local wifiStatus = getWifiStatus(wifiIndex)
    if wifiStatus['up'] == 0 then
        return true
    end

    local network = LuciNetwork.init()
    local wifiNet = network:get_wifinet(_wifiNameForIndex(wifiIndex))
    local dev
    if wifiNet ~= nil then
        dev = wifiNet:get_device()
    end
    if dev and wifiNet then
        dev:set("disabled", "1")
        wifiNet:set("disabled", nil)
        network:commit("wireless")
        XQFunction.forkRestartWifi()
        return true
    end
    return false
end

function wifiScanList(wifiIndex)
    local LuciSys = require("luci.sys")
    local LuciUtil = require("luci.util")
    local scanList = {}
    local iw = LuciSys.wifi.getiwinfo(_wifiNameForIndex(wifiIndex))
    if iw then
        for i, wifi in ipairs(iw.scanlist or { }) do
            local wifiDev = {}
            local quality = wifi.quality or 0
            local qualityMax = wifi.quality_max or 0
            local wifiSigPercent = 0
            if wifi.bssid and quality > 0 and qualityMax > 0 then
                wifiSigPercent = math.floor((100 / qualityMax) * quality)
            end
            wifi.encryption = wifi.encryption or { }
            wifiDev["ssid"] = wifi.ssid and LuciUtil.pcdata(wifi.ssid) or "hidden"
            wifiDev["bssid"] = wifi.bssid
            wifiDev["mode"] = wifi.mode
            wifiDev["channel"] = wifi.channel
            wifiDev["encryption"] = wifi.encryption
            wifiDev["signal"] = wifi.signal or 0
            wifiDev["signalPercent"] = wifiSigPercent
            wifiDev["quality"] = quality
            wifiDev["qualityMax"] = qualityMax
            table.insert(scanList,wifiDev)
        end
    end
    return scanList
end

function wifiBridgedClientId()
    local LuciNetwork = require("luci.model.network").init()
    local wifiDevs = LuciNetwork:get_wifidevs();
    local clients = {}
    for i, wifiDev in ipairs(wifiDevs) do
        local clientId
        for _, wifiNet in ipairs(wifiDev:get_wifinets()) do
            if wifiNet:active_mode() == "Client" then
                clientId = wifiNet:id()
            end
        end
        if not XQFunction.isStrNil(clientId) then
            table.insert(clients,i,clientId)
        end
    end
    return clients
end

--[[
@param wifiIndex : 1(2.4G) 2(5G)
function getWifiBridgedClient(wifiIndex)
    local LuciNetwork = require("luci.model.network").init()
    local client = {}
    local clientId = wifiBridgedClientId()[wifiIndex]
    if clientId then
        local wifiNet = LuciNetwork:get_wifinet(clientId)
        if wifiNet:get("disabled") == "1" then
            return client
        end
        client["ssid"] = wifiNet:get("ssid")
        client["key"] = wifiNet:get("key")
        client["encryption"] = wifiNet:get("encryption")
        client["channel"] = wifiNet:get("channel")
    end
    return client
end
]]--
--[[
@param wifiIndex : 1(2.4G) 2(5G)
function setWifiBridgedClient(wifiIndex,ssid,encryption,key,channel)
    local LuciNetwork = require("luci.model.network").init()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    -- Set wifi
    local clientId = wifiBridgedClientId()[wifiIndex]
    local wifiDev = LuciNetwork:get_wifidev(LuciUtil.split(_wifiNameForIndex(wifiIndex),".")[1])
    local wlanX = string.format("wlan%d",wifiIndex+1)
    if not wifiDev then
        return false
    end
    if XQFunction.isStrNil(clientId) then
        local network = {ifname = wlanX,ssid = ssid,mode = "sta",encryption = encryption,key = key,network = "",disabled = "0"}
        wifiDev:add_wifinet(network)
    else
        local wifiNet = wifiDev:get_wifinet(clientId)
        wlanX = wifiNet:get("ifname")
        wifiNet:set("ssid",ssid)
        wifiNet:set("key",key)
        wifiNet:set("encryption",encryption)
        wifiNet:set("network","")
        wifiNet:set("disabled","0")
    end
    wifiDev:set("channel",channel)
    -- Set wan
    local wanMac = XQLanWanUtil.getWanMac()
    local wanNetwork = {ifname = wlanX, macaddr = wanMac, proto = "dhcp"}
    LuciNetwork:del_network("wan")
    LuciNetwork:add_network("wan",wanNetwork)
    -- Save and commit
    LuciNetwork:save("wireless")
    LuciNetwork:save("network")
    LuciNetwork:commit("wireless")
    LuciNetwork:commit("network")
end
]]--

--[[
@return 0:close 1:start 2:connect 3:error 4:timeout
]]
function getWifiWpsStatus()
    local LuciUtil = require("luci.util")
    local status = LuciUtil.exec(XQConfigs.GET_WPS_STATUS)
    if not XQFunction.isStrNil(status) then
        status = LuciUtil.trim(status)
        return tonumber(status)
    end
    return 0
end

function getWpsConDevMac()
    local LuciUtil = require("luci.util")
    local mac = LuciUtil.exec(XQConfigs.GET_WPS_CONMAC)
    if mac then
        return XQFunction.macFormat(LuciUtil.trim(mac))
    end
    return nil
end

function stopWps()
    local LuciUtil = require("luci.util")
    LuciUtil.exec(XQConfigs.CLOSE_WPS)
    return
end

function openWifiWps()
    local LuciUtil = require("luci.util")
    local XQPreference = require("xiaoqiang.XQPreference")
    LuciUtil.exec(XQConfigs.OPEN_WPS)
    local timestamp = tostring(os.time())
    XQPreference.set(XQConfigs.PREF_WPS_TIMESTAMP,timestamp)
    return timestamp
end

--[[
    WiFi Bridge
]]--

--local WIFI_LIST_CMD = [[iwpriv wl1 set SiteSurvey=1;iwpriv wl1 get_site_survey | awk '{print $2"|||"$3"|||"$4"|||"$5}']]
--[[
function _parseEncryption(encryption)
    if XQFunction.isStrNil(encryption) then
        return nil
    end
    encryption = string.lower(encryption)
    if encryption:match("none") then
        return "NONE"
    end
    if encryption:match("wpa2psk") then
        return "WPA2PSK"
    end
    if encryption:match("wpapsk") then
        return "WPAPSK"
    end
    if encryption:match("wpa2") then
        return "WPA2"
    end
    if encryption:match("wpa1") then
        return "WPA1"
    end
    return "NONE"
end

function getWifiScanList()
    local wifilist = {}
    local LuciUtil = require("luci.util")
    for _, line in ipairs(LuciUtil.execl(WIFI_LIST_CMD)) do
        local item = LuciUtil.split(line, "|||")
        if tonumber(item[4]) then
            local wifi = {["ssid"] = "", ["encryption"] = "", ["bssid"] = "", ["signal"] = 0}
            local enc = _parseEncryption(item[3])
            if enc then
                wifi.encryption = enc
            end
            wifi.ssid = item[1]
            wifi.bssid = item[2]
            wifi.signal = tonumber(item[4])
            table.insert(wifilist, wifi)
        end
    end
    return wifilist
end
]]--
--[[
    @param wifiIndex:1/2  2.4G/5G
]]
function setWifiBridge(wifiIndex, ssid, password, encryption)
    local LuciUtil = require("luci.util")
    local LuciNetwork = require("luci.model.network").init()
    local wifiDev = LuciNetwork:get_wifidev(LuciUtil.split(_wifiNameForIndex(wifiIndex),".")[1])
    local clients = {}
    local key
    if encryption == "none" then
        key = ""
    else
        key = password
    end
    if wifiDev then
        local clientId
        for _, wifiNet in ipairs(wifiDev:get_wifinets()) do
            if wifiNet:active_mode() == "Client" then
                clientId = wifiNet:id()
            end
        end
        if XQFunction.isStrNil(clientId) then
            local iface = {
                device = wifiDev:name(),
                ifname = "apcli0",
                ssid = ssid,
                mode = "sta",
                encryption = encryption,
                key = key,
                network = "wan",
                disabled = "0"
            }
            wifiDev:add_wifinet(iface)
        else
            local wifiNet = wifiDev:get_wifinet(clientId)
            wifiNet:set("ssid", ssid)
            wifiNet:set("key", key)
            wifiNet:set("encryption", encryption)
        end
        LuciNetwork:save("wireless")
        LuciNetwork:commit("wireless")
    end
end

--- model: 0/1  black/white list
function getWiFiMacfilterList(model)
    local uci = require("luci.model.uci").cursor()
    local config = tonumber(model) == 0 and "wifiblist" or "wifiwlist"
    local maclist = uci:get_list(config, "maclist", "mac")
    return maclist
end

--- model: 0/1  black/white list
--- option: 0/1 add/remove
function editWiFiMacfilterList(model, mac, option)
    if XQFunction.isStrNil(mac) or XQFunction.isStrNil(option) then
        return
    else
        mac = XQFunction.macFormat(mac)
        option = tonumber(option)
    end
    local uci = require("luci.model.uci").cursor()
    local config = tonumber(model) == 0 and "wifiblist" or "wifiwlist"
    local maclist = uci:get_list(config, "maclist", "mac") or {}
    if option == 0 then
        for _, macaddr in ipairs(maclist) do
            if mac == macaddr then
                return
            end
        end
        table.insert(maclist, mac)
    else
        local pos = -1
        for i, macaddr in ipairs(maclist) do
            if mac == macaddr then
                pos = i
            end
        end
        if pos >= 0 then
            table.remove(maclist, pos)
        else
            return
        end
    end
    if #maclist > 32 then
        return 1
    end
    if #maclist > 0 then
        uci:set_list(config, "maclist", "mac", maclist)
    else
        uci:delete(config, "maclist", "mac")
    end
    uci:commit(config)
end

--- model: 0/1  black/white list
function getWiFiMacfilterInfo(model)
    local LuciUtil = require("luci.util")
    local LuciNetwork = require("luci.model.network").init()
    local wifiNet = LuciNetwork:get_wifinet(_wifiNameForIndex(1))
    local info = {
        ["enable"] = 0,
        ["model"] = 0
    }
    if wifiNet then
        local macfilter = wifiNet:get("macfilter")
        if macfilter == "disabled" then
            info["enable"] = 0
            info["model"] = 0
        elseif macfilter == "deny" then
            info["enable"] = 1
            info["model"] = 0
        elseif macfilter == "allow" then
            info["enable"] = 1
            info["model"] = 1
        else
            info["enable"] = 0
            info["model"] = 0
        end
    end
    if model then
        info["maclist"] = getWiFiMacfilterList(model)
    else
        info["maclist"] = getWiFiMacfilterList(info.model)
    end
    return info
end

--- model: 0/1  black/white list
function setWiFiMacfilterModel(enable, model)
    local macfilter
    local maclist
    if enable then
        if tonumber(model) == 1 then
            macfilter = "allow"
            maclist = getWiFiMacfilterList(1)
        else
            macfilter = "deny"
            maclist = getWiFiMacfilterList(0)
        end
    else
        macfilter = "disabled"
    end
    local LuciUtil = require("luci.util")
    local LuciNetwork = require("luci.model.network").init()
    local wifiNet1 = LuciNetwork:get_wifinet(_wifiNameForIndex(1))
    local wifiNet2 = LuciNetwork:get_wifinet(_wifiNameForIndex(2))
    if wifiNet1 and wifiNet2 then
        wifiNet1:set("macfilter", macfilter)
        wifiNet2:set("macfilter", macfilter)
        if maclist and #maclist > 0 then
            wifiNet1:set_list("maclist", maclist)
            wifiNet2:set_list("maclist", maclist)
        else
            wifiNet1:set_list("maclist", nil)
            wifiNet2:set_list("maclist", nil)
        end
        LuciNetwork:save("wireless")
        LuciNetwork:commit("wireless")
    end
end

function getGuestWifi(wifiIndex)
    local uci = require("luci.model.uci").cursor()
    local index = tonumber(wifiIndex)
    local status
    if index then
        status = getWifiStatus(index)
        if index == 1 then
            index = "guest_2G"
        elseif index == 2 then
            index = "guest_5G"
        else
            index = nil
        end
    end
    local guestwifi
    if index and status then
        guestwifi = uci:get_all("wireless", index)
        if guestwifi then
            local enabled
            if status.up == 1 and tonumber(guestwifi.enabled) == 1 then
                enabled = "1"
            else
                enabled = "0"
            end
            return {
                ["ifname"]      = guestwifi.device,
                ["ssid"]        = guestwifi.ssid,
                ["encryption"]  = guestwifi.encryption,
                ["password"]    = guestwifi.key,
                ["status"]      = guestwifi.open,
                ["enabled"]     = enabled
            }
        end
    end
    return guestwifi
end

function setGuestWifi(wifiIndex, ssid, encryption, key, enabled, open)
    local uci = require("luci.model.uci").cursor()
    local wifinetid, ifname
    local enabled = tonumber(enabled) == 1 and 1 or 0
    local open = tonumber(open) == 1 and 1 or 0
    if tonumber(wifiIndex) == 1 then
        wifinetid = "guest_2G"
        ifname = uci:get("misc", "wireless", "ifname_guest_2G")
    elseif tonumber(wifiIndex) == 2 then
        wifinetid = "guest_5G"
    else
        return false
    end
    guestwifi = uci:get_all("wireless", wifinetid)
    if guestwifi then
        guestwifi["ifname"] = ifname
        if not XQFunction.isStrNil(ssid) and XQFunction.checkSSID(ssid) then
            guestwifi["ssid"] = ssid
        end
        if encryption and string.lower(tostring(encryption)) == "none" then
            guestwifi["encryption"] = "none"
            guestwifi["key"] = ""
        end
        if open then
            guestwifi["open"] = open
        end
        if encryption and string.lower(tostring(encryption)) ~= "none" and not XQFunction.isStrNil(key) then
            local check = checkWifiPasswd(key,encryption)
            if check == 0 then
                guestwifi["encryption"] = encryption
                guestwifi["key"] = key
            else
                return false
            end
        end
    else
        if XQFunction.isStrNil(ssid) or XQFunction.isStrNil(encryption) then
            return false
        end
        guestwifi = {
            ["device"] = WIFI_DEVS[wifiIndex],
            ["ifname"] = ifname,
            ["network"] = "guest",
            ["ssid"] = ssid,
            ["mode"] = "ap",
            ["encryption"] = encryption,
            ["key"] = key,
            ["open"] = open,
            ["enabled"] = enabled
        }
    end
    uci:section("wireless", "wifi-iface", wifinetid, guestwifi)
    uci:commit("wireless")
    return true
end

function delGuestWifi(wifiIndex)
    local uci = require("luci.model.uci").cursor()
    local wifinetid
    if tonumber(wifiIndex) == 1 then
        wifinetid = "guest_2G"
    elseif tonumber(wifiIndex) == 2 then
        wifinetid = "guest_5G"
    else
        return false
    end
    uci:delete("wireless", wifinetid)
    uci:commit("wireless")
    return true
end
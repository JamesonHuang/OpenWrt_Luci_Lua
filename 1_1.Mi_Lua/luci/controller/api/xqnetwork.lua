module("luci.controller.api.xqnetwork", package.seeall)

function index()
    local page   = node("api","xqnetwork")
    page.target  = firstchild()
    page.title   = ("")
    page.order   = 200
    page.sysauth = "admin"
    page.sysauth_authenticator = "jsonauth"
    page.index = true
    entry({"api", "xqnetwork"}, firstchild(), (""), 200)
    entry({"api", "xqnetwork", "wifi_status"}, call("getWifiStatus"), (""), 201)
    entry({"api", "xqnetwork", "wifi_detail"}, call("getWifiInfo"), (""), 202)
    entry({"api", "xqnetwork", "wifi_detail_all"}, call("getAllWifiInfo"), (""), 202)
    entry({"api", "xqnetwork", "wifi_connect_devices"}, call("getWifiConDev"), (""), 203)
    entry({"api", "xqnetwork", "wifi_txpwr_channel"}, call("getWifiChTx"), (""), 204)
    -- entry({"api", "xqnetwork", "set_wifi_txpwr_channel"}, call("setWifiChTx"), (""), 205)
    entry({"api", "xqnetwork", "set_wifi_txpwr"}, call("setWifiTxpwr"), (""), 205)
    entry({"api", "xqnetwork", "wifi_up"}, call("turnOnWifi"), (""), 206)
    entry({"api", "xqnetwork", "wifi_down"}, call("shutDownWifi"), (""), 207)
    entry({"api", "xqnetwork", "set_wifi"}, call("setWifi"), (""), 208)
    --entry({"api", "xqnetwork", "get_scan_list"}, call("get_scan_list"), (""), 209, true)
    --entry({"api", "xqnetwork", "wifi_ctl_scan"}, call("wifi_ctl_scan"), (""), 210)
    --entry({"api", "xqnetwork", "get_bridge"}, call("get_bridge"), (""), 211, true)
    --entry({"api", "xqnetwork", "set_bridge"}, call("set_bridge"), (""), 212)
    entry({"api", "xqnetwork", "lan_info"}, call("getLanInfo"), (""), 213)
    entry({"api", "xqnetwork", "wan_info"}, call("getWanInfo"), (""), 214)
    entry({"api", "xqnetwork", "lan_dhcp"}, call("getLanDhcp"), (""), 215)
    entry({"api", "xqnetwork", "wan_down"}, call("wanDown"), (""), 216)
    entry({"api", "xqnetwork", "wan_up"}, call("wanUp"), (""), 217)
    entry({"api", "xqnetwork", "check_wan_type"}, call("getAutoWanType"), (""), 218, 0x08)
    entry({"api", "xqnetwork", "wan_statistics"}, call("getWanStatistics"), (""), 219)
    --
    entry({"api", "xqnetwork", "devices_statistics"}, call("getDevsStatistics"), (""), 220)
    entry({"api", "xqnetwork", "device_statistics"}, call("getDevStatistics"), (""), 221)
    --
    entry({"api", "xqnetwork", "set_lan_ip"}, call("setLanIp"), (""), 222)
    entry({"api", "xqnetwork", "set_wan"}, call("setWan"), (""), 223, 0x08)
    entry({"api", "xqnetwork", "set_lan_dhcp"}, call("setLanDhcp"), (""), 224)
    entry({"api", "xqnetwork", "mac_clone"}, call("setWanMac"), (""), 225)
    entry({"api", "xqnetwork", "set_all_wifi"}, call("setAllWifi"), (""), 226)
    entry({"api", "xqnetwork", "avaliable_channels"}, call("getChannels"), (""), 227)
    -- WiFi macfilter
    entry({"api", "xqnetwork", "wifi_macfilter_info"}, call("getWifiMacfilterInfo"), (""), 228)
    entry({"api", "xqnetwork", "set_wifi_macfilter"}, call("setWifiMacfilter"), (""), 229)
    entry({"api", "xqnetwork", "edit_device"}, call("editDevice"), (""), 230)
    -- Mac bind
    entry({"api", "xqnetwork", "mac_bind"}, call("macBind"), (""), 231)
    entry({"api", "xqnetwork", "mac_unbind"}, call("macUnbind"), (""), 232)
    entry({"api", "xqnetwork", "savebind"}, call("saveBind"), (""), 233)
    entry({"api", "xqnetwork", "unbindall"}, call("unbindAll"), (""), 234)
    entry({"api", "xqnetwork", "macbind_info"}, call("getMacBindInfo"), (""), 235)
    -- PPPoE
    entry({"api", "xqnetwork", "pppoe_status"}, call("pppoeStatus"), (""), 236)
    entry({"api", "xqnetwork", "pppoe_stop"}, call("pppoeStop"), (""), 237)
    entry({"api", "xqnetwork", "pppoe_start"}, call("pppoeStart"), (""), 238)
    -- QoS
    entry({"api", "xqnetwork", "qos_info"}, call("getQosInfo"), (""), 239)
    entry({"api", "xqnetwork", "qos_switch"}, call("qosSwitch"), (""), 240)
    entry({"api", "xqnetwork", "qos_mode"}, call("qosMode"), (""), 241)
    entry({"api", "xqnetwork", "qos_limit"}, call("qosLimit"), (""), 242)
    entry({"api", "xqnetwork", "qos_offlimit"}, call("qosOffLimit"), (""), 243)
    entry({"api", "xqnetwork", "set_band"}, call("setBand"), (""), 244)
    -- NAT
    entry({"api", "xqnetwork", "portforward"}, call("portForward"), (""), 245)
    entry({"api", "xqnetwork", "add_redirect"}, call("addRedirect"), (""), 246)
    entry({"api", "xqnetwork", "add_range_redirect"}, call("addRangeRedirect"), (""), 247)
    entry({"api", "xqnetwork", "delete_redirect"}, call("deleteRedirect"), (""), 248)
    entry({"api", "xqnetwork", "redirect_apply"}, call("redirectApply"), (""), 249)
    -- DMZ
    entry({"api", "xqnetwork", "dmz"}, call("getDMZInfo"), (""), 250)
    entry({"api", "xqnetwork", "set_dmz"}, call("setDMZ"), (""), 251)
    entry({"api", "xqnetwork", "dmz_off"}, call("closeDMZ"), (""), 252)
    entry({"api", "xqnetwork", "dmz_reload"}, call("reloadDMZ"), (""), 252)
    -- DDNS
    entry({"api", "xqnetwork", "ddns"}, call("ddnsStatus"), (""), 253)
    entry({"api", "xqnetwork", "ddns_switch"}, call("ddnsSwitch"), (""), 254)
    entry({"api", "xqnetwork", "add_server"}, call("addServer"), (""), 255)
    entry({"api", "xqnetwork", "del_server"}, call("deleteServer"), (""), 256)
    entry({"api", "xqnetwork", "server_switch"}, call("serverSwitch"), (""), 258)
    entry({"api", "xqnetwork", "ddns_reload"}, call("ddnsReload"), (""), 259)
    entry({"api", "xqnetwork", "ddns_edit"}, call("ddnsEdit"), (""), 260)
    entry({"api", "xqnetwork", "get_server"}, call("getServer"), (""), 261)
end

local LuciHttp = require("luci.http")
local XQErrorUtil = require("xiaoqiang.util.XQErrorUtil")

function getWifiStatus()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local result = {}
    local status = {}
    table.insert(status,XQWifiUtil.getWifiStatus(1))
    table.insert(status,XQWifiUtil.getWifiStatus(2))
    result["code"] = 0
    result["status"] = status
    LuciHttp.write_json(result)
end

function getWifiInfo()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local result = {}
    local code = 0
    local index = tonumber(LuciHttp.formvalue("wifiIndex"))
    if index and index < 3 then
        result["info"] = XQWifiUtil.getAllWifiInfo()[index]
    else
        code = 1523
    end
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
end

function getAllWifiInfo()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local result = {}
    local code = 0
    result["info"] = XQWifiUtil.getAllWifiInfo()
    result["code"] = code
    LuciHttp.write_json(result)
end

function getWifiConDev()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local result = {}
    result["code"] = 0
    result["list"] = XQWifiUtil.getAllWifiConnetDeviceList()
    LuciHttp.write_json(result)
end

function getWifiChTx()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local result = {}
    result["code"] = 0
    result["list"] = XQWifiUtil.getWifiChannelTxpwrList()
    LuciHttp.write_json(result)
end

function setWifiChTx()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local result = {}
    local code = 0
    local channel1 = LuciHttp.formvalue("channel1")
    local txpwr1 = LuciHttp.formvalue("txpwr1")
    local channel2 = LuciHttp.formvalue("channel2")
    local txpwr2 = LuciHttp.formvalue("txpwr2")
    if XQFunction.isStrNil(channel1) and XQFunction.isStrNil(channel2) and XQFunction.isStrNil(txpwr1) and XQFunction.isStrNil(txpwr2) then
        code = 1502
    else
        XQWifiUtil.setWifiChannelTxpwr(channel1,txpwr1,channel2,txpwr2)
    end
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
    if code == 0 then
        LuciHttp.close()
        XQFunction.forkRestartWifi()
    end
end

function setWifiTxpwr()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local result = {}
    local code = 0
    local txpwr = LuciHttp.formvalue("txpwr")
    if XQFunction.isStrNil(txpwr) then
        code = 1502
    else
        XQWifiUtil.setWifiTxpwr(txpwr)
    end
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
    if code == 0 then
        LuciHttp.close()
        XQFunction.forkRestartWifi()
    end
end

function turnOnWifi()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local result = {}
    local code = 0
    local index = tonumber(LuciHttp.formvalue("wifiIndex"))
    if index and index < 3 then
        XQWifiUtil.turnWifiOn(index)
    else
        code = 1523
    end
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
end

function shutDownWifi()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local result = {}
    local code = 0
    local index = tonumber(LuciHttp.formvalue("wifiIndex"))
    if index and index < 3 then
        XQWifiUtil.turnWifiOff(index)
    else
        code = 1523
    end
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
end

function setWifi()
    local XQLog = require("xiaoqiang.XQLog")
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local result = {}
    local code = 0
    local index = tonumber(LuciHttp.formvalue("wifiIndex"))
    local ssid = LuciHttp.formvalue("ssid")
    local password = LuciHttp.formvalue("pwd")
    local encryption = LuciHttp.formvalue("encryption")
    local channel = LuciHttp.formvalue("channel")
    local bandwidth = LuciHttp.formvalue("bandwidth")
    local txpwr = LuciHttp.formvalue("txpwr")
    local hidden = LuciHttp.formvalue("hidden")
    local on = LuciHttp.formvalue("on")
    if on ~= nil then
        on = tonumber(on)
    end
    if channel == "0" then
        bandwidth = "0"
    end
    if index == 1 then
        if channel then
            XQLog.check(0, XQLog.KEY_FUNC_2G_CHANNEL, channel)
        end
        if txpwr then
            XQLog.check(0, XQLog.KEY_FUNC_2G_SIGNAL, txpwr)
        end
    elseif index == 2 then
        if channel then
            XQLog.check(0, XQLog.KEY_FUNC_5G_CHANNEL, channel)
        end
        if txpwr then
            XQLog.check(0, XQLog.KEY_FUNC_5G_SIGNAL, txpwr)
        end
    elseif index == 3 then
        -- todo: Guest wifi
    end
    local wifirestart = true
    code = XQWifiUtil.checkSSID(ssid,31)
    if code == 0 then
        if index == 1 or index == 2 then
            local succeed = XQWifiUtil.setWifiBasicInfo(index, ssid, password, encryption, channel, txpwr, hidden, on, bandwidth)
            if succeed == false then
                code = XQWifiUtil.checkWifiPasswd(password,encryption)
            end
        elseif index == 3 then
            local XQGuestWifi = require("xiaoqiang.module.XQGuestWifi")
            local succeed = XQGuestWifi.setGuestWifi(1, ssid, encryption, password, 1, on)
            if succeed == false then
                code = 1615
            else
                wifirestart = false
            end
        end
    end
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
    if code == 0 then
        LuciHttp.close()
        if wifirestart then
            XQFunction.forkRestartWifi()
        end
    end
end

function setAllWifi()
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local result = {}
    local code = 0
    local code1 = 0
    local code2 = 0
    local details = {}
    local on1 = LuciHttp.formvalue("on1")
    local ssid1 = LuciHttp.formvalue("ssid1")
    local password1 = LuciHttp.formvalue("pwd1")
    local encryption1 = LuciHttp.formvalue("encryption1")
    local channel1 = LuciHttp.formvalue("channel1")
    local txpwr1 = LuciHttp.formvalue("txpwr1")
    local hidden1 = LuciHttp.formvalue("hidden1")
    local bandwidth1 = LuciHttp.formvalue("bandwidth1")

    local on2 = LuciHttp.formvalue("on2")
    local ssid2 = LuciHttp.formvalue("ssid2")
    local password2 = LuciHttp.formvalue("pwd2")
    local encryption2 = LuciHttp.formvalue("encryption2")
    local channel2 = LuciHttp.formvalue("channel2")
    local txpwr2 = LuciHttp.formvalue("txpwr2")
    local hidden2 = LuciHttp.formvalue("hidden2")
    local bandwidth2 = LuciHttp.formvalue("bandwidth2")

    local on3 = LuciHttp.formvalue("on3")
    local ssid3 = LuciHttp.formvalue("ssid3")
    local password3 = LuciHttp.formvalue("pwd3")
    local encryption3 = LuciHttp.formvalue("encryption3")

    if on1 ~= nil then
        on1 = tonumber(on1)
    end
    if on2 ~= nil then
        on2 = tonumber(on2)
    end
    if channel1 == "0" then
        bandwidth1 = "0"
    end
    if channel2 == "0" then
        bandwidth2 = "0"
    end
    local code1 = XQWifiUtil.checkSSID(ssid1,31)
    local code2 = XQWifiUtil.checkSSID(ssid2,31)
    if on1 ~= 0 and not XQFunction.isStrNil(ssid1) then
        XQSysUtil.setRouterName(ssid1)
    end
    local succeed
    if on1 == 0 then
        succeed = XQWifiUtil.setWifiBasicInfo(1, nil, nil, nil, nil, nil, nil, on1, nil)
    else
        code1 = XQWifiUtil.checkSSID(ssid1,31)
        if code1 == 0 then
            succeed = XQWifiUtil.setWifiBasicInfo(1, ssid1, password1, encryption1, channel1, txpwr1, hidden1, on1, bandwidth1)
        else
            code = code1
        end
    end
    if succeed == false then
        local error1 = {}
        code1 = XQWifiUtil.checkWifiPasswd(password1,encryption1)
        error1["code"] = code1
        error1["msg"] = XQErrorUtil.getErrorMessage(code1)
        table.insert(details,error1)
    end
    if on2 == 0 then
        succeed = XQWifiUtil.setWifiBasicInfo(2, nil, nil, nil, nil, nil, nil, on2, nil)
    else
        code2 = XQWifiUtil.checkSSID(ssid2,31)
        if code2 == 0 then
            succeed = XQWifiUtil.setWifiBasicInfo(2, ssid2, password2, encryption2, channel2, txpwr2, hidden2, on2, bandwidth2)
        else
            code = code2
        end
    end
    if succeed == false then
        local error2 = {}
        code2 = XQWifiUtil.checkWifiPasswd(password2,encryption2)
        error2["code"] = code2
        error2["msg"] = XQErrorUtil.getErrorMessage(code2)
        table.insert(details,error2)
    end
    if code1+code2 > 0 and code == 0 then
        code = 1516
    end
    local wifirestart = true
    if on3 then
        local XQGuestWifi = require("xiaoqiang.module.XQGuestWifi")
        if not XQGuestWifi.setGuestWifi(1, ssid3, encryption3, password3, 1, on3) then
            code = 1615
        else
            wifirestart = false
        end
    end
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
       result["errorDetails"] = details
    end
    result["code"] = code
    LuciHttp.write_json(result)
    if code == 0 then
        LuciHttp.close()
        if wifirestart then
            XQFunction.forkRestartWifi()
        end
    end
end

function getLanInfo()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local lan = XQLanWanUtil.getLanWanInfo("lan")
    local linkList = XQLanWanUtil.getLanLinkList()
    local result = {}
    result["code"] = 0
    result["info"] = lan
    result["linkList"] = linkList
    LuciHttp.write_json(result)
end

function getWanInfo()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local wan = XQLanWanUtil.getLanWanInfo("wan")
    local result = {}
    result["code"] = 0
    result["info"] = wan
    LuciHttp.write_json(result)
end

function getWanStatistics()
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local wan = XQDeviceUtil.getWanLanNetworkStatistics("wan")
    local result = {}
    result["code"] = 0
    result["statistics"] = wan
    LuciHttp.write_json(result)
end

function getDevsStatistics()
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local dev = XQDeviceUtil.getDevNetStatisticsList()
    local result = {}
    result["code"] = 0
    result["statistics"] = dev
    LuciHttp.write_json(result)
end

function getDevStatistics()
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local result = {}
    local mac = LuciHttp.formvalue("mac")
    local dict = XQDeviceUtil.getDevNetStatisticsDict()
    local statistics = dict[XQFunction.macFormat(mac)]
    result["code"] = 0
    result["statistics"] = statistics
    LuciHttp.write_json(result)
end

function getAutoWanType()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local XQPreference = require("xiaoqiang.XQPreference")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local result = {}
    local code = 0
    local wanType = XQLanWanUtil.getAutoWanType()
    if wanType == false then
        code = 1524
    else
        result["wanType"] = wanType
        result["pppoeName"] = XQPreference.get(XQConfigs.PREF_PPPOE_NAME, "")
        result["pppoePassword"] = XQPreference.get(XQConfigs.PREF_PPPOE_PASSWORD, "")
    end
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
end

function getLanDhcp()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local result = {}
    local lanDhcp = XQLanWanUtil.getLanDHCPService()
    result["code"] = 0
    result["info"] = lanDhcp
    LuciHttp.write_json(result)
end

function getChannels()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local wifiIndex = tonumber(LuciHttp.formvalue("wifiIndex"))
    local result = {}
    result["code"] = 0
    result["list"] = XQWifiUtil.getDefaultWifiChannels(wifiIndex)
    LuciHttp.write_json(result)
end

function wanDown()
    luci.sys.call("env -i /sbin/ifdown wan")
    local result = {code=0}
    LuciHttp.write_json(result)
end

function wanUp()
    luci.sys.call("env -i /sbin/ifup wan")
    local result = {code=0}
    LuciHttp.write_json(result)
end

function setLanIp()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local LuciDatatypes = require("luci.cbi.datatypes")
    local LuciIp = require("luci.ip")
    local result = {}
    local code = 0
    local ip = LuciHttp.formvalue("ip")
    local mask = "255.255.255.0"
    local wanIp = XQLanWanUtil.getLanWanIp("wan")[1]
    if not LuciDatatypes.ipaddr(ip) then
        code = 1525
    else
        if wanIp then
            local lanIpNl = LuciIp.iptonl(ip)
            local lanMaskNl = LuciIp.iptonl(mask)
            local wanIpNl = LuciIp.iptonl(wanIp.ip)
            local wanMaskNl = LuciIp.iptonl(wanIp.mask)
            if bit.band(lanIpNl,lanMaskNl) == bit.band(wanIpNl,lanMaskNl) or bit.band(lanIpNl,wanMaskNl) == bit.band(wanIpNl,wanMaskNl) then
                code = 1526
            else
                code = XQLanWanUtil.checkLanIp(ip)
            end
        end
    end
    if code == 0 then
        XQLanWanUtil.setLanIp(ip,mask)
    else
        result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
    if code == 0 then
        LuciHttp.close()
        XQFunction.forkReboot()
    end
end

function setWan()
    local XQLog = require("xiaoqiang.XQLog")
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local code = 0
    local result = {}
    local client = LuciHttp.formvalue("client")
    local wanType = LuciHttp.formvalue("wanType")
    local pppoeName = LuciHttp.formvalue("pppoeName")
    local pppoePwd = LuciHttp.formvalue("pppoePwd")
    local staticIp = LuciHttp.formvalue("staticIp")
    local staticMask = LuciHttp.formvalue("staticMask")
    local staticGateway = LuciHttp.formvalue("staticGateway")
    local dns1 = LuciHttp.formvalue("dns1")
    local dns2 = LuciHttp.formvalue("dns2")
    local special = LuciHttp.formvalue("special")
    local peerDns = LuciHttp.formvalue("peerDns")
    local mtu = tonumber(LuciHttp.formvalue("mtu"))
    local service = LuciHttp.formvalue("service")
    if XQFunction.isStrNil(wanType)
        and XQFunction.isStrNil(pppoeName)
        and XQFunction.isStrNil(pppoePwd)
        and XQFunction.isStrNil(staticIp)
        and XQFunction.isStrNil(staticMask)
        and XQFunction.isStrNil(staticGateway)
        and XQFunction.isStrNil(dns1)
        and XQFunction.isStrNil(dns2)
        and XQFunction.isStrNil(special)
        and XQFunction.isStrNil(peerDns) then
            code = 1502
    else
        if wanType == "pppoe" then
            if client == "web" then
                XQLog.check(0, XQLog.KEY_VALUE_NETWORK_PPPOE, 1)
            end
            if XQFunction.isStrNil(pppoeName) or XQFunction.isStrNil(pppoePwd) then
                code = 1528
            else
                if mtu and not XQLanWanUtil.checkMTU(mtu) then
                    code = 1590
                else
                    if not XQLanWanUtil.setWanPPPoE(pppoeName, pppoePwd, dns1, dns2, peerDns, mtu, service) then
                        code = 1529
                    end
                end
            end
        elseif wanType == "dhcp" then
            if client == "web" then
                XQLog.check(0, XQLog.KEY_VALUE_NETWORK_DHCP, 1)
            end
            if not XQLanWanUtil.setWanStaticOrDHCP(wanType, nil, nil, nil, dns1, dns2, peerDns, mtu) then
                code = 1529
            end
        elseif wanType == "static" then
            if client == "web" then
                XQLog.check(0, XQLog.KEY_VALUE_NETWORK_STATIC, 1)
            end
            local LuciDatatypes = require("luci.cbi.datatypes")
            local LuciIp = require("luci.ip")
            if not LuciDatatypes.ipaddr(staticIp) then
                code = 1530
            elseif not XQFunction.checkMask(staticMask) then
                code = 1531
            elseif not LuciDatatypes.ipaddr(staticGateway) then
                code = 1532
            else
                local lanIp = XQLanWanUtil.getLanWanIp("lan")[1]
                local lanIpNl = LuciIp.iptonl(lanIp.ip)
                local lanMaskNl = LuciIp.iptonl(lanIp.mask)
                local wanIpNl = LuciIp.iptonl(staticIp)
                local wanMaskNl = LuciIp.iptonl(staticMask)
                if bit.band(lanIpNl,lanMaskNl) == bit.band(wanIpNl,lanMaskNl) or bit.band(lanIpNl,wanMaskNl) == bit.band(wanIpNl,wanMaskNl) then
                    code = 1526
                else
                    code = XQLanWanUtil.checkWanIp(staticIp)
                    if code == 0 then
                        if not XQLanWanUtil.setWanStaticOrDHCP(wanType, staticIp, staticMask, staticGateway, dns1, dns2, peerDns, mtu) then
                            code = 1529
                        end
                    end
                end
            end
        else
            -- unknown type
        end
    end
    result["code"] = code
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    LuciHttp.write_json(result)
end

function setLanDhcp()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local LuciDatatypes = require("luci.cbi.datatypes")
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local code = 0
    local result = {}
    local startReq = tonumber(LuciHttp.formvalue("start"))
    local endReq = tonumber(LuciHttp.formvalue("end"))
    local leasetime = LuciHttp.formvalue("leasetime")
    local ignore = LuciHttp.formvalue("ignore")
    local num,unit = leasetime:match("^(%d+)(%S+)")
    num = tonumber(num)
    if ignore == "1" then
        XQLanWanUtil.setLanDHCPService(nil,nil,nil,ignore)
    else
        if not LuciDatatypes.uinteger(startReq)
            or not LuciDatatypes.integer(endReq)
            or num == nil
            or unit ~= "h" and unit ~= "m" then
            code = 1537
        else
            if startReq > endReq then
                code = 1534
            elseif  startReq <= 1 or endReq > 254 or endReq <= 1 or endReq >254  then
                code = 1535
            elseif (unit == "h" and (num < 1 or num > 48)) or (unit == "m" and (num < 2 or num > 2880)) then
                code = 1536
            else
                XQLanWanUtil.setLanDHCPService(startReq,endReq,leasetime,ignore)
            end
        end
    end
    result["code"] = code
    if code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    LuciHttp.write_json(result)
end

function setWanMac()
    local XQLog = require("xiaoqiang.XQLog")
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local code = 0
    local result = {}
    local mac = LuciHttp.formvalue("mac")
    local succeed = XQLanWanUtil.setWanMac(mac)
    XQLog.check(0, XQLog.KEY_FUNC_MACCLONE, 1)
    if not succeed then
        code = 1537
        result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
end

function getWifiMacfilterInfo()
    local LuciUtil = require("luci.util")
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local model = tonumber(LuciHttp.formvalue("model"))
    local code = 0
    local result = {}
    local macfilterInfo = XQWifiUtil.getWiFiMacfilterInfo(model)
    local wifiList = XQDeviceUtil.getConDevices(false)
    result["enable"] = macfilterInfo.enable
    result["model"] = macfilterInfo.model
    if macfilterInfo.maclist then
        for _, device in ipairs(wifiList) do
            if LuciUtil.contains(macfilterInfo.maclist, device.mac) then
                device.added = 1
            else
                device.added = 0
            end
        end
    end
    result["code"] = 0
    result["list"] = wifiList
    result["macfilter"] = macfilterInfo.maclist
    LuciHttp.write_json(result)
end

function setWifiMacfilter()
    local XQLog = require("xiaoqiang.XQLog")
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local enable = tonumber(LuciHttp.formvalue("enable")) == 1 and true or false
    local model = tonumber(LuciHttp.formvalue("model"))
    XQLog.check(0, XQLog.KEY_FUNC_WIRELESS_ACCESS, enable and 0 or 1)
    if model and model == 0 then
        XQLog.check(0, XQLog.KEY_FUNC_WIRELESS_BLACK, 1)
    else
        XQLog.check(0, XQLog.KEY_FUNC_WIRELESS_WHITE, 1)
    end
    XQWifiUtil.setWiFiMacfilterModel(enable, model)
    local result = {["code"] = 0}
    XQFunction.forkRestartWifi()
    LuciHttp.write_json(result)
end

function editDevice()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local code = 0
    local result = {}
    local mac = LuciHttp.formvalue("mac")
    local model = tonumber(LuciHttp.formvalue("model"))
    local option = tonumber(LuciHttp.formvalue("option"))
    local success = XQWifiUtil.editWiFiMacfilterList(model, mac, option)
    if success and success == 1 then
        code = 1591
    end
    result["code"] = code
    if code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    LuciHttp.write_json(result)
end

function macBind()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local code = 0
    local result = {}
    local ip = LuciHttp.formvalue("ip")
    local mac = LuciHttp.formvalue("mac")
    local bind = XQLanWanUtil.addBind(mac, ip)
    if bind == 1 then
        code = 1593
    elseif bind == 2 then
        code = 1592
    end
    result["code"] = code
    if code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    LuciHttp.write_json(result)
end

function macUnbind()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local code = 0
    local result = {}
    local mac = LuciHttp.formvalue("mac")
    local unbind = XQLanWanUtil.removeBind(mac)
    if not unbind then
        code = 1594
    end
    result["code"] = code
    if code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    LuciHttp.write_json(result)
end

function saveBind()
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local result = {
        ["code"] = 0
    }
    XQLanWanUtil.saveBindInfo()
    XQFunction.forkRestartDnsmasq()
    LuciHttp.write_json(result)
end

function unbindAll()
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local result = {
        ["code"] = 0
    }
    XQLanWanUtil.unbindAll()
    XQFunction.forkRestartDnsmasq()
    LuciHttp.write_json(result)
end

function getMacBindInfo()
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local result = {
        ["code"] = 0
    }
    local blist = {}
    local bindInfo = XQLanWanUtil.macBindInfo()
    local deviceList = XQDeviceUtil.getConDevices(true) or {}
    for _, device in ipairs(deviceList) do
        local bind = bindInfo[string.lower(device.mac)]
        if bind then
            device["tag"] = bind.tag
        else
            device["tag"] = 0
        end
    end
    for _, host in pairs(bindInfo) do
        table.insert(blist, {
            ["mac"] = string.upper(host.mac),
            ["ip"] = host.ip,
            ["tag"] = host.tag
        })
    end
    result["list"] = blist
    result["devicelist"] = deviceList
    LuciHttp.write_json(result)
end

function pppoeStatus()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local code = 0
    local result = {}
    local status = XQLanWanUtil.getPPPoEStatus()
    if status then
        result = status
        if result.errtype == 1 then
            code = 1603
        elseif result.errtype == 2 then
            code = 1604
        elseif result.errtype == 3 then
            code = 1605
        end
    else
        code = 1602
    end
    if code ~= 0 then
        if code ~= 1602 then
            result["msg"] = string.format("%s(%s)",XQErrorUtil.getErrorMessage(code), tostring(result.errcode))
        else
            result["msg"] = XQErrorUtil.getErrorMessage(code)
        end
    end
    result["code"] = code
    LuciHttp.write_json(result)
end

function pppoeStop()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local result = {
        ["code"] = 0
    }
    XQLanWanUtil.pppoeStop()
    LuciHttp.write_json(result)
end

function pppoeStart()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local result = {
        ["code"] = 0
    }
    XQLanWanUtil.pppoeStart()
    LuciHttp.write_json(result)
end

function getQosInfo()
    local XQQoSUtil = require("xiaoqiang.util.XQQoSUtil")
    local result = {
        ["code"] = 0
    }
    local status = XQQoSUtil.qosStatus()
    result["status"] = status
    if status.on == 1 then
        result["band"] = XQQoSUtil.qosBand()
        result["list"] = XQQoSUtil.qosList()
    end
    LuciHttp.write_json(result)
end

function qosSwitch()
    local XQLog = require("xiaoqiang.XQLog")
    local XQQoSUtil = require("xiaoqiang.util.XQQoSUtil")
    local result = {
        ["code"] = 0
    }
    local on = tonumber(LuciHttp.formvalue("on")) == 1 and true or false
    XQLog.check(0, XQLog.KEY_FUNC_QOS, on and 0 or 1)
    local switch = XQQoSUtil.qosSwitch(on)
    if not switch then
        result.code = 1606
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function qosMode()
    local XQQoSUtil = require("xiaoqiang.util.XQQoSUtil")
    local result = {
        ["code"] = 0
    }
    local auto = tonumber(LuciHttp.formvalue("mode")) == 0 and true or false
    local status = XQQoSUtil.qosStatus()
    local switch
    if status and status.on == 1 then
        switch = XQQoSUtil.qosModeSwitch(auto)
    else
        result.code = 1607
    end
    if not switch and result.code == 0 then
        result.code = 1606
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

-- level : 1/2/3 low/middle/high
-- upload : 1 ~ 100
-- download : 1 ~ 100
function qosLimit()
    local XQQoSUtil = require("xiaoqiang.util.XQQoSUtil")
    local result = {
        ["code"] = 0
    }
    local mac      = LuciHttp.formvalue("mac")
    local upload   = tonumber(LuciHttp.formvalue("upload"))
    local download = tonumber(LuciHttp.formvalue("download"))
    local level    = tonumber(LuciHttp.formvalue("level"))
    local limit
    local status = XQQoSUtil.qosStatus()
    if status and status.on == 1 then
        if mac and upload and download and level then
            limit = XQQoSUtil.qosOnLimit(mac, upload/100, download/100, level, level)
        else
            result.code = 1523
        end
    else
        result.code = 1607
    end
    if not limit and result.code == 0 then
        result.code = 1606
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function qosOffLimit()
    local XQQoSUtil = require("xiaoqiang.util.XQQoSUtil")
    local result = {
        ["code"] = 0
    }
    local mac = LuciHttp.formvalue("mac")
    local status = XQQoSUtil.qosStatus()
    local offlimit
    if status and status.on == 1 then
        offlimit = XQQoSUtil.qosOffLimit(mac)
    else
        result.code = 1607
    end
    if not offlimit and result.code == 0 then
        result.code = 1606
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

-- upload/download M bits/s
function setBand()
    local XQQoSUtil = require("xiaoqiang.util.XQQoSUtil")
    local result = {
        ["code"] = 0
    }
    local upload = tonumber(LuciHttp.formvalue("upload"))
    local download = tonumber(LuciHttp.formvalue("download"))
    local band
    local status = XQQoSUtil.qosStatus()
    if upload and download then
        if status and status.on == 1 then
            band = XQQoSUtil.setQosBand(upload, download)
        else
            result.code = 1607
        end
    else
        result.code = 1523
    end
    if not band and result.code == 0 then
        result.code = 1606
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function portForward()
    local XQPortForward = require("xiaoqiang.module.XQPortForward")
    local result = {
        ["code"] = 0
    }
    local ftype = tonumber(LuciHttp.formvalue("ftype")) or 0
    result["status"] = XQPortForward.portForwardInfo().status
    result["list"] = XQPortForward.portForwards(ftype)
    LuciHttp.write_json(result)
end

function addRedirect()
    local XQLog = require("xiaoqiang.XQLog")
    local XQPortForward = require("xiaoqiang.module.XQPortForward")
    local result = {
        ["code"] = 0
    }
    local ip = LuciHttp.formvalue("ip")
    local name = LuciHttp.formvalue("name")
    local proto = tonumber(LuciHttp.formvalue("proto"))
    local sport = tonumber(LuciHttp.formvalue("sport"))
    local dport = tonumber(LuciHttp.formvalue("dport"))
    local add = XQPortForward.setPortForward(name, ip, sport, dport, proto)
    XQLog.check(0, XQLog.KEY_FUNC_PORTFADD, 1)
    if add == 1 then
        result.code = 1537
    elseif add == 2 then
        result.code = 1608
    elseif add == 3 then
        result.code = 1609
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function addRangeRedirect()
    local XQLog = require("xiaoqiang.XQLog")
    local XQPortForward = require("xiaoqiang.module.XQPortForward")
    local result = {
        ["code"] = 0
    }
    local ip = LuciHttp.formvalue("ip")
    local name = LuciHttp.formvalue("name")
    local proto = tonumber(LuciHttp.formvalue("proto"))
    local fport = tonumber(LuciHttp.formvalue("fport"))
    local tport = tonumber(LuciHttp.formvalue("tport"))
    local add = XQPortForward.setRangePortForward(name, ip, fport, tport, proto)
    XQLog.check(0, XQLog.KEY_FUNC_RANGEFADD, 1)
    if add == 1 then
        result.code = 1537
    elseif add == 2 then
        result.code = 1608
    elseif add == 3 then
        result.code = 1609
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function deleteRedirect()
    local XQPortForward = require("xiaoqiang.module.XQPortForward")
    local result = {
        ["code"] = 0
    }
    local port = tonumber(LuciHttp.formvalue("port")) or 0
    if port == 0 then
        XQPortForward.deleteAllPortForward()
    else
        XQPortForward.deletePortForward(port)
    end
    LuciHttp.write_json(result)
end

function redirectApply()
    local XQLog = require("xiaoqiang.XQLog")
    local XQPortForward = require("xiaoqiang.module.XQPortForward")
    local result = {
        ["code"] = 0
    }
    XQLog.check(0, XQLog.KEY_FUNC_PORTENABLE, 1)
    XQPortForward.restart()
    LuciHttp.write_json(result)
end

function getDMZInfo()
    local XQDMZModule = require("xiaoqiang.module.XQDMZModule")
    local result = {
        ["code"] = 0
    }
    local info = XQDMZModule.getDMZInfo()
    result["status"] = info.status
    result["ip"] = info.ip
    result["lanip"] = info.lanip
    LuciHttp.write_json(result)
end

function setDMZ()
    local XQLog = require("xiaoqiang.XQLog")
    local XQDMZModule = require("xiaoqiang.module.XQDMZModule")
    local result = {
        ["code"] = 0
    }
    local ip = LuciHttp.formvalue("ip")
    local mac = LuciHttp.formvalue("mac")
    local mode = tonumber(LuciHttp.formvalue("mode")) or 0
    local set = XQDMZModule.setDMZ(mode, ip, mac)
    if set == 1 then
        result.code = 1593
    elseif set == 2 then
        result.code = 1592
    elseif set == 3 then
        result.code = 1611
    elseif set == 4 then
        result.code = 1610
    end
    XQLog.check(0, XQLog.KEY_FUNC_DMZ, 0)
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    else
        XQDMZModule.dmzReload(mode)
    end
    LuciHttp.write_json(result)
end

function closeDMZ()
    local XQLog = require("xiaoqiang.XQLog")
    local XQDMZModule = require("xiaoqiang.module.XQDMZModule")
    local result = {
        ["code"] = 0
    }
    local mode = tonumber(LuciHttp.formvalue("mode")) or 0
    XQLog.check(0, XQLog.KEY_FUNC_DMZ, 1)
    XQDMZModule.unsetDMZ(mode)
    LuciHttp.write_json(result)
end

function reloadDMZ()
    local XQDMZModule = require("xiaoqiang.module.XQDMZModule")
    local result = {
        ["code"] = 0
    }
    local mode = tonumber(LuciHttp.formvalue("mode")) or 0
    XQDMZModule.dmzReload(mode)
    LuciHttp.write_json(result)
end

function ddnsStatus()
    local XQDDNS = require("xiaoqiang.module.XQDDNS")
    local result = {
        ["code"] = 0
    }
    local ddns = XQDDNS.ddnsInfo()
    result["on"] = ddns.on
    result["list"] = ddns.list
    LuciHttp.write_json(result)
end

function ddnsSwitch()
    local XQDDNS = require("xiaoqiang.module.XQDDNS")
    local result = {
        ["code"] = 0
    }
    local on = tonumber(LuciHttp.formvalue("on")) == 1 and true or false
    XQDDNS.ddnsSwitch(on)
    LuciHttp.write_json(result)
end

function addServer()
    local XQDDNS = require("xiaoqiang.module.XQDDNS")
    local result = {
        ["code"] = 0
    }
    local id = tonumber(LuciHttp.formvalue("id"))
    local enable = tonumber(LuciHttp.formvalue("enable")) == 1 and 1 or 0
    local domain = LuciHttp.formvalue("domain") or ""
    local username = LuciHttp.formvalue("username") or ""
    local password = LuciHttp.formvalue("password") or ""
    local checkinterval = tonumber(LuciHttp.formvalue("checkinterval"))
    local forceinterval = tonumber(LuciHttp.formvalue("forceinterval"))
    if not id or not checkinterval or not forceinterval then
        result.code = 1612
    elseif checkinterval <= 0 or forceinterval <= 0 then
        result.code = 1523
    else
        local add = XQDDNS.setDdns(id, enable, username, password, checkinterval, forceinterval, domain)
        if not add then
            result.code = 1606
        end
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function deleteServer()
    local XQDDNS = require("xiaoqiang.module.XQDDNS")
    local result = {
        ["code"] = 0
    }
    local id = tonumber(LuciHttp.formvalue("id"))
    if not id then
        result.code = 1612
    else
        local delete = XQDDNS.deleteDdns(id)
        if not delete then
            result.code = 1606
        end
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function serverSwitch()
    local XQDDNS = require("xiaoqiang.module.XQDDNS")
    local result = {
        ["code"] = 0
    }
    local id = tonumber(LuciHttp.formvalue("id"))
    local on = tonumber(LuciHttp.formvalue("on")) == 1 and true or false
    if not id then
        result.code = 1612
    else
        local switch = XQDDNS.ddnsServerSwitch(id, on)
        if not switch then
            result.code = 1606
        end
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function ddnsReload()
    local XQDDNS = require("xiaoqiang.module.XQDDNS")
    local result = {
        ["code"] = 0
    }
    if not XQDDNS.reload() then
        result.code = 1606
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function getServer()
    local XQDDNS = require("xiaoqiang.module.XQDDNS")
    local result = {}
    local id = tonumber(LuciHttp.formvalue("id"))
    local get = XQDDNS.getDdns(id)
    if get then
        result = get
        result["code"] = 0
    else
        result["code"] = 1614
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function ddnsEdit()
    local XQDDNS = require("xiaoqiang.module.XQDDNS")
    local result = {
        ["code"] = 0
    }
    local id = tonumber(LuciHttp.formvalue("id"))
    local enable = tonumber(LuciHttp.formvalue("enable")) == 1 and 1 or 0
    local domain = LuciHttp.formvalue("domain")
    local username = LuciHttp.formvalue("username")
    local password = LuciHttp.formvalue("password")
    local checkinterval = tonumber(LuciHttp.formvalue("checkinterval"))
    local forceinterval = tonumber(LuciHttp.formvalue("forceinterval"))
    local edit = XQDDNS.editDdns(id, enable, username, password, checkinterval, forceinterval, domain)
    if not edit then
        result.code = 1606
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

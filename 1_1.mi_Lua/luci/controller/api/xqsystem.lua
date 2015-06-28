module("luci.controller.api.xqsystem", package.seeall)

function index()
    local page   = node("api","xqsystem")
    page.target  = firstchild()
    page.title   = ("")
    page.order   = 100
    page.sysauth = "admin"
    page.sysauth_authenticator = "jsonauth"
    page.index = true
    entry({"api", "xqsystem"}, firstchild(), (""), 100)
    entry({"api", "xqsystem", "login"}, call("actionLogin"), (""), 109, 0x08)
    entry({"api", "xqsystem", "init_info"}, call("getInitInfo"), (""), 101, 0x09)
    entry({"api", "xqsystem", "fac_info"}, call("getFacInfo"), (""), 101, 0x09)
    entry({"api", "xqsystem", "token"}, call("getToken"), (""), 103, 0x08)
    entry({"api", "xqsystem", "set_inited"}, call("setInited"), (""), 103, 0x08)
    entry({"api", "xqsystem", "system_info"}, call("getSysInfo"), (""), 104, 0x01)
    entry({"api", "xqsystem", "set_name_password"}, call("setPassword"), (""), 105)
    entry({"api", "xqsystem", "check_rom_update"}, call("checkRomUpdate"), (""), 106)
    entry({"api", "xqsystem", "lan_wan"}, call("getLanWanSta"), (""), 106)
    -- for Web only
    entry({"api", "xqsystem", "flash_rom"}, call("flashRom"), (""), 108)

    -- deprecated
    entry({"api", "xqsystem", "router_name"}, call("getRouterName"), (""), 110)

    entry({"api", "xqsystem", "device_list"}, call("getDeviceList"), (""), 112)
    entry({"api", "xqsystem", "set_device_nickname"}, call("setDeviceNickName"), (""), 113)
    entry({"api", "xqsystem", "internet_connect"}, call("isInternetConnect"), (""), 114)
    entry({"api", "xqsystem", "upload_rom"}, call("uploadRom"), (""), 115)
    entry({"api", "xqsystem", "get_languages"}, call("getLangList"), (""), 118, 0x01)
    entry({"api", "xqsystem", "get_main_language"}, call("getMainLang"), (""), 119, 0x01)
    entry({"api", "xqsystem", "set_language"}, call("setLang"), (""), 120)

    entry({"api", "xqsystem", "upload_log"}, call("uploadLogFile"), (""), 124)
    entry({"api", "xqsystem", "backup_config"}, call("uploadConfigFile"), (""), 125)
    entry({"api", "xqsystem", "config_recovery"}, call("configRecovery"), (""), 126)
    entry({"api", "xqsystem", "router_init"}, call("setRouter"), (""), 126, 0x08)
    entry({"api", "xqsystem", "information"}, call("getAllInfo"), (""), 127)
    entry({"api", "xqsystem", "status"}, call("getStatusInfo"), (""), 128)
    entry({"api", "xqsystem", "count"}, call("getConDevCount"), (""), 129)
    entry({"api", "xqsystem", "reboot"}, call("reboot"), (""), 130)
    entry({"api", "xqsystem", "reset"}, call("reset"), (""), 131)
    entry({"api", "xqsystem", "passport_bind_info"}, call("getPassportBindInfo"), (""), 132, 0x01)
    entry({"api", "xqsystem", "set_passport_bound"}, call("setPassportBound"), (""), 133, 0x08)
    entry({"api", "xqsystem", "get_sys_avg_load"}, call("getSysAvgLoad"), (""), 134)
    entry({"api", "xqsystem", "set_mac_filter"}, call("setMacFilter"), (""), 135)
    entry({"api", "xqsystem", "renew_token"}, call("renewToken"), (""), 136)
    entry({"api", "xqsystem", "remove_passport_info"}, call("removePassportBindInfo"), (""), 137)
    entry({"api", "xqsystem", "upgrade_rom"}, call("upgradeRom"), (""), 138)
    entry({"api", "xqsystem", "wps"}, call("openWps"), (""), 139, 0x08)
    entry({"api", "xqsystem", "wps_status"}, call("getWpsStatus"), (""), 140)
    entry({"api", "xqsystem", "stop_nginx"}, call("stopNginx"), (""), 141)
    entry({"api", "xqsystem", "check_router_name_pending"}, call("checkRouterNamePending"), (""), 142)
    entry({"api", "xqsystem", "clear_router_name_pending"}, call("clearRouterNamePending"), (""), 143)
    entry({"api", "xqsystem", "web_url"}, call("redirectUrl"), (""), 144)
    entry({"api", "xqsystem", "start_nginx"}, call("startNginx"), (""), 145)
    entry({"api", "xqsystem", "nginx"}, call("nginxCacheStatus"), (""), 146)
    entry({"api", "xqsystem", "flash_status"}, call("flashStatus"), (""), 147, 0x01)
    entry({"api", "xqsystem", "upgrade_status"}, call("upgradeStatus"), (""), 148, 0x0d)
    entry({"api", "xqsystem", "create_sandbox"}, call("createSandbox"), (""), 149)
    entry({"api", "xqsystem", "is_sandbox_created"}, call("isSandboxCreated"), (""), 150)
    entry({"api", "xqsystem", "mount_things"}, call("mountThings"), (""), 151)
    entry({"api", "xqsystem", "umount_things"}, call("umountThings"), (""), 152)
    entry({"api", "xqsystem", "are_things_mounted"}, call("areThingsMounted"), (""), 153)
    entry({"api", "xqsystem", "start_dropbear"}, call("startDropbear"), (""), 154)
    entry({"api", "xqsystem", "stop_dropbear"}, call("stopDropbear"), (""), 155)
    entry({"api", "xqsystem", "is_dropbear_started"}, call("isDropbearStarted"), (""), 156)
    entry({"api", "xqsystem", "main_status_for_app"}, call("mainStatusForApp"), (""), 157)
    entry({"api", "xqsystem", "mode"}, call("getMacfilterMode"), (""), 158)
    entry({"api", "xqsystem", "set_mode"}, call("setMacfilterMode"), (""), 159)
    entry({"api", "xqsystem", "cancel"}, call("cancelUpgrade"), (""), 160, 0x0d)
    entry({"api", "xqsystem", "shutdown"}, call("shutdown"), (""), 161)
    entry({"api", "xqsystem", "upnp"}, call("upnpList"), (""), 162)
    entry({"api", "xqsystem", "upnp_switch"}, call("upnpSwitch"), (""), 163)
    entry({"api", "xqsystem", "app_limit"}, call("appLimit"), (""), 164)
    entry({"api", "xqsystem", "app_limit_switch"}, call("appLimitSwitch"), (""), 165)
    entry({"api", "xqsystem", "set_app_limit"}, call("setAppLimit"), (""), 166)
    entry({"api", "xqsystem", "vpn"}, call("vpnInfo"), (""), 167)
    entry({"api", "xqsystem", "vpn_status"}, call("vpnStatus"), (""), 168)
    entry({"api", "xqsystem", "vpn_switch"}, call("vpnSwitch"), (""), 169)
    entry({"api", "xqsystem", "set_vpn"}, call("setVpn"), (""), 170)
    entry({"api", "xqsystem", "device_mac"}, call("getDeviceMacaddr"), (""), 171, 0x01)
    entry({"api", "xqsystem", "wps_cancel"}, call("stopWps"), (""), 172)
    entry({"api", "xqsystem", "detection_ts"}, call("getDetectionTimestamp"), (""), 173)
    entry({"api", "xqsystem", "wifi_log"}, call("getWifiLog"), (""), 174)
    entry({"api", "xqsystem", "sys_recovery"}, call("sysRecovery"), (""), 175)
    entry({"api", "xqsystem", "smart_shutdown"}, call("smartShutdown"), (""), 177)

    -- include zigbee dongle device only
    entry({"api", "xqsystem", "device_list_zigbee"}, call("getDeviceListZigbee"), (""), 176)
    -- Noflushd
    entry({"api", "xqsystem", "noflushd"}, call("getNofStatus"), (""), 178)
    entry({"api", "xqsystem", "nof_switch"}, call("nofSwitch"), (""), 179)
    entry({"api", "xqsystem", "pred_status"}, call("predownloadInfo"), (""), 180)
    entry({"api", "xqsystem", "pred_switch"}, call("predownloadSwitch"), (""), 181)
end

local LuciHttp = require("luci.http")
local XQConfigs = require("xiaoqiang.common.XQConfigs")
local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
local XQErrorUtil = require("xiaoqiang.util.XQErrorUtil")

function getInitInfo()
    local XQCountryCode = require("xiaoqiang.XQCountryCode")
    local XQNetUtil = require("xiaoqiang.util.XQNetUtil")
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local monitor = XQLanWanUtil.getWanMonitorStat()
    local connect = 0
    if monitor.WANLINKSTAT == "UP" then
        connect = 1
    end
    local result = {}
    result["code"] = 0
    result["connect"] = connect
    result["inited"] = XQSysUtil.getInitInfo() and 1 or 0
    result["bound"] = XQSysUtil.getPassportBindInfo() and 1 or 0
    result["id"] = XQNetUtil.getSN()
    result["routerId"] = XQNetUtil.getDeviceId()
    result["hardware"] = XQSysUtil.getHardware()
    result["romversion"] = XQSysUtil.getRomVersion()
    result["modules"] = XQSysUtil.getModulesList()
    result["language"] = XQSysUtil.getLang()
    result["countrycode"] = XQCountryCode.getCurrentCountryCode()
    LuciHttp.write_json(result)
end

function getFacInfo()
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    LuciHttp.write_json(XQSysUtil.facInfo())
end

function actionLogin()
    local result = {}
    local init = tonumber(LuciHttp.formvalue("init"))
    result["code"] = 0
    if init and init == 1 then
        result["url"] = luci.dispatcher.build_url("web", "init", "guide")
    else
        result["url"] = luci.dispatcher.build_url("web", "home")
    end
    LuciHttp.write_json(result)
end

function getToken()
    local XQNetUtil = require("xiaoqiang.util.XQNetUtil")
    local sid = LuciHttp.formvalue("sid")
    local result = {}
    result["code"] = 0
    result["token"] = luci.dispatcher.context.urltoken.stok
    result["id"] = XQNetUtil.getSN()
    result["name"] = XQSysUtil.getRouterName()
    LuciHttp.write_json(result)
end

function renewToken()
    local sauth = require "luci.sauth"
    local result = {}
    local session = sauth.available()
    if session then
        result["token"] = session.token
    else
        local token = luci.sys.uniqueid(16)
        sauth.write(token, {
            user="admin",
            token=token,
            ltype="1",
            secret=luci.sys.uniqueid(16)
        })
        result["token"] = token
    end
    result["code"] = 0
   LuciHttp.write_json(result)
end

function setInited()
    local XQLog = require("xiaoqiang.XQLog")
    local client = LuciHttp.formvalue("client")
    if client == "ios" then
        XQLog.check(0, XQLog.KEY_GEL_INIT_IOS, 1)
    elseif client == "android" then
        XQLog.check(0, XQLog.KEY_GEL_INIT_ANDROID, 1)
    elseif client == "other" then
        XQLog.check(0, XQLog.KEY_GEL_INIT_OTHER, 1)
    end
    local result = {}
    local inited = XQSysUtil.setInited()
    if not inited then
        result["code"] = 1501
        result["msg"] = XQErrorUtil.getErrorMessage(1501)
    else
        result["code"] = 0
    end
    LuciHttp.write_json(result)
end

function getLanWanSta()
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local result = {}
    result["code"] = 0
    result["lan"] = XQDeviceUtil.getWanLanNetworkStatistics("lan")
    result["wan"] = XQDeviceUtil.getWanLanNetworkStatistics("wan")
    LuciHttp.write_json(result)
end

function getPassportBindInfo()
    local result = {}
    local bind = XQSysUtil.getPassportBindInfo()
    result["code"] = 0
    if bind then
        result["bound"] = 1
        result["uuid"] = bind
    else
        result["bound"] = 0
    end
    LuciHttp.write_json(result)
end

function setPassportBound()
    local uuid = LuciHttp.formvalue("uuid")
    local result = {}
    local inited = XQSysUtil.setPassportBound(true,uuid)
    if not inited then
        result["code"] = 1501
        result["msg"] = XQErrorUtil.getErrorMessage(1501)
    else
        result["code"] = 0
    end
    LuciHttp.write_json(result)
end

function removePassportBindInfo()
    local uuid = LuciHttp.formvalue("uuid")
    local result = {}
    XQSysUtil.setPassportBound(false,uuid)
    result["code"] = 0
    LuciHttp.write_json(result)
end

function getSysInfo()
    local result = {}
    result["code"] = 0
    result["upTime"] = XQSysUtil.getSysUptime()
    result["routerName"] = XQSysUtil.getRouterName()
    result["romVersion"] = XQSysUtil.getRomVersion()
    result["romChannel"] = XQSysUtil.getChannel()
    result["hardware"] = XQSysUtil.getHardware()
    LuciHttp.write_json(result)
end

function getAllInfo()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local result = {}
    local monitor = XQLanWanUtil.getWanMonitorStat()
    local connect = 0
    if monitor.WANLINKSTAT == "UP" then
        connect = 1
    end
    result["connect"] = connect
    result["wifi"] = XQWifiUtil.getAllWifiInfo()
    result["wan"] = XQLanWanUtil.getLanWanInfo("wan")
    result["lan"] = XQLanWanUtil.getLanWanInfo("lan")
    result["code"] = 0
    result.wifi[1].channel = XQWifiUtil.getWifiWorkChannel(1)
    result.wifi[2].channel = XQWifiUtil.getWifiWorkChannel(2)
    LuciHttp.write_json(result)
end

function getStatusInfo()
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local result = {}
    local monitor = XQLanWanUtil.getWanMonitorStat()
    if monitor.WANLINKSTAT == "UP" then
        result["connect"] = 1
    end
    if monitor.VPNLINKSTAT == "UP" then
        result["vpn"] = 1
    end
    local wifiConCount = {}
    table.insert(wifiConCount,#XQWifiUtil.getWifiConnectDeviceList(1))
    table.insert(wifiConCount,#XQWifiUtil.getWifiConnectDeviceList(2))
    local statList = XQDeviceUtil.getDevNetStatisticsList()
    if #statList > 0 then
        table.sort(statList, function(a, b) return tonumber(a.download) > tonumber(b.download) end)
    end
    if #statList > XQConfigs.DEVICE_STATISTICS_LIST_LIMIT then
        local item = {}
        item["mac"] = ""
        item["ip"] = ""
        for i=1,#statList - XQConfigs.DEVICE_STATISTICS_LIST_LIMIT + 1 do
            local deleteElement = table.remove(statList, XQConfigs.DEVICE_STATISTICS_LIST_LIMIT)
            item["onlinets"] = deleteElement.onlinets
            item["activets"] = deleteElement.activets
            item["upload"] = tonumber(deleteElement.upload) + tonumber(item.upload or 0)
            item["upspeed"] = tonumber(deleteElement.upspeed) + tonumber(item.upspeed or 0)
            item["download"] = tonumber(deleteElement.download) + tonumber(item.download or 0)
            item["downspeed"] = tonumber(deleteElement.downspeed) + tonumber(item.downspeed or 0)
            item["online"] = deleteElement.online
            item["idle"] = deleteElement.idle
            item["devname"] = "Others"
            item["initail"] = deleteElement.initail
            item["maxuploadspeed"] = deleteElement.maxuploadspeed
            item["maxdownloadspeed"] = deleteElement.maxdownloadspeed
        end
        table.insert(statList,item)
    end

    result["lanLink"] = XQLanWanUtil.getLanLinkList()
    result["count"] = XQDeviceUtil.getConnectDeviceCount()
    result["upTime"] = XQSysUtil.getSysUptime()
    result["wifiCount"] = wifiConCount
    result["wanStatistics"] = XQDeviceUtil.getWanLanNetworkStatistics("wan")
    result["devStatistics"] = statList
    result["code"] = 0
    LuciHttp.write_json(result)
end

function getConDevCount()
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local result = {}
    result["code"]= 0
    result["count"] = XQDeviceUtil.getConnectDeviceCount()
    LuciHttp.write_json(result)
end

function _savePassword(nonce, oldpwd, newpwd)
    local XQSecureUtil = require("xiaoqiang.util.XQSecureUtil")
    local code = 0
    local mac = luci.dispatcher.getremotemac()
    local checkNonce = XQSecureUtil.checkNonce(nonce, mac)
    if checkNonce then
        local check = XQSecureUtil.checkUser("admin", nonce, oldpwd)
        if check then
            if XQSecureUtil.saveCiphertextPwd("admin", newpwd) then
                code = 0
            else
                code = 1553
            end
        else
            code = 1552
        end
    else
        code = 1582
    end
    return code
end

function setPassword()
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local result = {}
    local code
    local nonce = LuciHttp.formvalue("nonce")
    local oldPassword = LuciHttp.formvalue("oldPwd")
    local newPassword = LuciHttp.formvalue("newPwd")
    if XQFunction.isStrNil(oldPassword) or XQFunction.isStrNil(newPassword) then
        code = 1502
    else
        if nonce then
            code = _savePassword(nonce, oldPassword, newPassword)
        else
            local XQSecureUtil = require("xiaoqiang.util.XQSecureUtil")
            local check = XQSysUtil.checkSysPassword(oldPassword) or XQSecureUtil.checkPlaintextPwd("admin", oldPassword)
            if check then
                local setPwd = XQSysUtil.setSysPassword(newPassword)
                if setPwd then
                    code = 0
                else
                    code = 1553
                end
            else
                code = 1552
            end
        end
    end
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
end

function checkRomUpdate()
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local XQNetUtil = require("xiaoqiang.util.XQNetUtil")
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local result = {}
    local status = {status = 0, percent = 0}
    local code = 0
    local check = XQNetUtil.checkUpgrade()
    local upgrade = XQSysUtil.checkUpgradeStatus()
    if check == false then
        code = 1504
    else
        code = 0
        result = check
    end
    result["status"] = status
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
end

-- 直接执行升级脚本
function upgradeRom()
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local XQSecureUtil = require("xiaoqiang.util.XQSecureUtil")

    local url = LuciHttp.formvalue("url")
    local filesize = tostring(LuciHttp.formvalue("filesize"))
    local hash = tostring(LuciHttp.formvalue("hash"))

    local result = {}
    local code = 0
    if XQSysUtil.checkBeenUpgraded() then
        code = 1577
    elseif XQSysUtil.isUpgrading() then
        code = 1568
    elseif not XQSecureUtil.cmdSafeCheck(url) or not XQSecureUtil.cmdSafeCheck(filesize) or not XQSecureUtil.cmdSafeCheck(hash) then
        code = 1523
    end
    result["code"] = code
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    LuciHttp.write_json(result)
    if code == 0 then
        XQFunction.sysLock()
        if url and filesize and hash then
            XQFunction.forkExec(string.format("/usr/sbin/crontab_rom.sh '%s' '%s' '%s'", url, hash, filesize))
        else
            XQFunction.forkExec("/usr/sbin/crontab_rom.sh")
        end
    end
end

function cancelUpgrade()
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local code = 0
    local result = {}
    local succeed = XQSysUtil.cancelUpgrade()
    if not succeed then
        code = 1579
        result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
end

function flashRom()
    local LuciFs = require("luci.fs")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local XQPreference = require("xiaoqiang.XQPreference")
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local LuciUtil = require("luci.util")
    local custom = tonumber(LuciHttp.formvalue("custom") or 0)
    local result = {}
    local code = 0
    local filePath = XQConfigs.ROM_CACHE_FILEPATH
    if custom == 1 then
        filePath = XQConfigs.CROM_DISK_CACHE_FILEPATH
    end
    local flashStatus = XQSysUtil.getFlashStatus()
    if flashStatus == 1 then
        code = 1560
    elseif flashStatus == 2 then
        code = 1577
    elseif not LuciFs.access(filePath) then
        code = 1507
    elseif not XQSysUtil.verifyImage(filePath) then
        code = 1554
    end
    XQFunction.ledFlashAlert(false)
    if code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
    if code == 0 then
        LuciHttp.close()
        XQFunction.sysLock()
        XQFunction.forkFlashRomFile(filePath)
    end
end

function flashStatus()
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local result = {}
    result["code"] = 0
    result["status"] = XQSysUtil.getFlashStatus()
    LuciHttp.write_json(result)
end

function upgradeStatus()
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local result = {}
    result["code"] = 0
    result["status"] = XQSysUtil.checkUpgradeStatus()
    if result.status == 3 then
        local LuciFs = require("luci.fs")
        local XQConfigs = require("xiaoqiang.common.XQConfigs")
        local XQPreference = require("xiaoqiang.XQPreference")
        local XQDownloadUtil = require("xiaoqiang.util.XQDownloadUtil")
        local downloadId = XQPreference.get(XQConfigs.PREF_ROM_DOWNLOAD_ID, nil)
        if downloadId then
            result["percent"] = XQDownloadUtil.downloadPercent(downloadId)
        else
            result["percent"] = 0
        end
    elseif result.status == 5 then
        result["percent"] = 100
    end
    LuciHttp.write_json(result)
end

function getRouterName()
    local result = {}
    result["code"] = 0
    result["routerName"] = XQSysUtil.getRouterName()
    LuciHttp.write_json(result)
end

function setRouterName()
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local routerName = LuciHttp.xqformvalue("routerName")
    local result = {}
    local code = 0
    if XQFunction.isStrNil(routerName) then
        code = 1502
    else
        local newName = XQSysUtil.setRouterName(routerName)
        if newName == false then
            code = 1503
        else
            result["routerName"] = newName
        end
    end
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
end

function setRouter()
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local result = {}
    local code = 0
    local msg = {}
    local needRestartWifi = false
    local nonce = LuciHttp.formvalue("nonce")
    local newPwd = LuciHttp.formvalue("newPwd")
    local oldPwd = LuciHttp.formvalue("oldPwd")
    local wifiPwd = LuciHttp.formvalue("wifiPwd")
    local wifi24Ssid = LuciHttp.formvalue("wifi24Ssid")
    local wifi50Ssid = LuciHttp.formvalue("wifi50Ssid")
    local wanType = LuciHttp.formvalue("wanType")
    local pppoeName = LuciHttp.formvalue("pppoeName")
    local pppoePwd = LuciHttp.formvalue("pppoePwd")

    XQFunction.nvramSet("Router_unconfigured", "0")
    XQFunction.nvramCommit()

    local checkssid = XQWifiUtil.checkSSID(wifi24Ssid,28)
    if not XQFunction.isStrNil(wifi24Ssid) and checkssid == 0 then
        XQSysUtil.setRouterName(wifi24Ssid)
    end
    if not XQFunction.isStrNil(newPwd) and not XQFunction.isStrNil(oldPwd) then
        if nonce then
            code = _savePassword(nonce, oldPwd, newPwd)
        else
            local check = XQSysUtil.checkSysPassword(oldPwd)
            if check then
                local succeed = XQSysUtil.setSysPassword(newPwd)
                if not succeed then
                    code = 1515
                end
            else
                code = 1552
            end
        end
        if code ~= 0 then
            table.insert(msg,XQErrorUtil.getErrorMessage(code))
        end
    end
    if not XQFunction.isStrNil(wanType) then
        local succeed
        if wanType == "pppoe" and not XQFunction.isStrNil(pppoeName) and not XQFunction.isStrNil(pppoePwd) then
            succeed = XQLanWanUtil.setWanPPPoE(pppoeName,pppoePwd)
        elseif wanType == "dhcp" then
            succeed = XQLanWanUtil.setWanStaticOrDHCP(wanType)
        end
        if not succeed then
            code = 1518
            table.insert(msg,XQErrorUtil.getErrorMessage(code))
        else
            needRestartWifi = true
        end
    end
    if not XQFunction.isStrNil(wifiPwd) and checkssid == 0 then
        local succeed1 = XQWifiUtil.setWifiBasicInfo(1, wifi24Ssid, wifiPwd, "mixed-psk", nil, nil, 0)
        local succeed2 = XQWifiUtil.setWifiBasicInfo(2, wifi50Ssid, wifiPwd, "mixed-psk", nil, nil, 0)
        if succeed1 or succeed2 then
            needRestartWifi = true
        end
        if not succeed1 or not succeed2 then
            code = XQWifiUtil.checkWifiPasswd(wifiPwd, "mixed-psk")
            table.insert(msg,XQErrorUtil.getErrorMessage(code))
        end
    end
    if checkssid ~= 0 then
        code = checkssid
    end
    if code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(1519)
        result["errorDetails"] = msg
    end
    XQSysUtil.setSPwd()
    XQSysUtil.setInited()
    result["code"] = code
    LuciHttp.write_json(result)
    if needRestartWifi then
        LuciHttp.close()
        XQFunction.forkRestartWifi()
    end
end

function getDeviceList()
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local result = {}
    result["code"] = 0
    result["mac"] = luci.dispatcher.getremotemac()
    result["list"] = XQDeviceUtil.getConnectDeviceList()
    LuciHttp.write_json(result)
end
 
function getDeviceListZigbee()
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local XQZigbeeUtil = require("xiaoqiang.util.XQZigbeeUtil")
    local result = {}
    result["code"] = 0
    result["mac"] = luci.dispatcher.getremotemac()
    local list = {}
    -- add zigbee device
    XQZigbeeUtil.append_yeelink_list(list)
    result["list"] = list
    LuciHttp.write_json(result)
end

function isInternetConnect()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local result = {}
    local monitor = XQLanWanUtil.getWanMonitorStat()
    local connect = 0
    if monitor.WANLINKSTAT == "UP" then
        connect = 1
    end
    result["code"] = 0
    result["connect"] = connect
    LuciHttp.write_json(result)
end

function setDeviceNickName()
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local LuciDatatypes = require("luci.cbi.datatypes")
    local result = {}
    local code = 0
    local mac = LuciHttp.formvalue("mac")
    local nickName = LuciHttp.formvalue("name")
    if XQFunction.isStrNil(mac) or XQFunction.isStrNil(nickName) then
        code = 1502
    -- allow none ip device to set nick name (zigbee device) 
    -- elseif not LuciDatatypes.macaddr(mac) then
    --     code = 1508
    else
        XQDeviceUtil.saveDeviceName(mac,nickName)
    end
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
end


function _prepare()
    local FS = require("nixio.fs")
    FS.mkdir(XQConfigs.USERDISK_UPLOAD_DIR, 777)
    if not _sane() then
        error("Upload Rom Exception: /userdisk/upload path is not sane!")
    end
end

function _sane()
    local FS = require("nixio.fs")
    local LuciSys = require("luci.sys")
    return LuciSys.process.info("uid")
            == FS.stat(XQConfigs.USERDISK_UPLOAD_DIR, "uid")
end

function uploadRom()
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local LuciSys = require("luci.sys")
    local LuciFs = require("luci.fs")

    local fp
    local code = 0
    if not _sane() then
        _prepare()
    end
    local tmpfile = XQConfigs.USERDISK_UPLOAD_DIR..LuciSys.uniqueid(16)
    local fileSize = tonumber(LuciHttp.getenv("CONTENT_LENGTH"))
    local canupload = XQSysUtil.checkDiskSpace(fileSize)
    LuciHttp.setfilehandler(
    function(meta, chunk, eof)
        if canupload then
            if not fp then
                if meta and meta.name == "image" then
                    fp = io.open(tmpfile, "w")
                end
            end
            if chunk then
                fp:write(chunk)
            end
            if eof then
                fp:close()
                if LuciFs.access(XQConfigs.CROM_DISK_CACHE_FILEPATH) then
                    LuciFs.unlink(XQConfigs.CROM_DISK_CACHE_FILEPATH)
                end
                LuciFs.rename(tmpfile, XQConfigs.CROM_DISK_CACHE_FILEPATH)
            end
        else
            code = 1578
        end
    end)
    if LuciHttp.formvalue("image") and fp then
        code = 0
    else
        if code == 0 then
            XQLog.log(6, "upload failed, file not exist: "..tostring(filepath))
            code = 1509
        end
    end
    local result = {}
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
end

function getLangList()
    local result = {}
    result["code"] = 0
    result["list"] = XQSysUtil.getLangList()
    LuciHttp.write_json(result)
end

function getMainLang()
    local result = {}
    result["code"] = 0
    result["lang"] = XQSysUtil.getLang()
    LuciHttp.write_json(result)
end

function setLang()
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local code = 0
    local result = {}
    local lang = LuciHttp.formvalue("language")
    if XQFunction.isStrNil(lang) then
        code = 1502
    end
    local succeed = XQSysUtil.setLang()
    if not succeed then
        code = 1511
    end
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    result["code"] = code
    LuciHttp.write_json(result)
end

function uploadLogFile()
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local XQNetUtil = require("xiaoqiang.util.XQNetUtil")
    local LuciUtil = require("luci.util")
    local code = 0
    local result = {}
    LuciUtil.exec("/usr/sbin/log_collection.sh")
    local succeed = XQNetUtil.uploadLogFile(XQConfigs.LOG_ZIP_FILEPATH,"B")
    if not succeed then
        code = 1512
    end
    result["code"] = code
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    LuciUtil.exec("rm "..XQConfigs.LOG_ZIP_FILEPATH)
    LuciHttp.write_json(result)
end

function uploadConfigFile()
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local XQNetUtil = require("xiaoqiang.util.XQNetUtil")
    local LuciUtil = require("luci.util")
    local code = 0
    local result = {}
    LuciUtil.exec("/usr/sbin/config_collection.sh")
    local succeed = XQNetUtil.uploadConfigFile(XQConfigs.CONFIG_ZIP_FILEPATH)
    if not succeed then
        code = 1512
    end
    result["code"] = code
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    LuciUtil.exec("rm "..XQConfigs.CONFIG_ZIP_FILEPATH)
    LuciHttp.write_json(result)
end

function configRecovery()
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local XQNetUtil = require("xiaoqiang.util.XQNetUtil")
    local LuciUtil = require("luci.util")
    local code = 0
    local result = {}
    local succeed = XQNetUtil.getConfigFile(XQConfigs.CONFIG_ZIP_FILEPATH)
    if not succeed then
        code = 1513
    else
        LuciUtil.exec("/usr/bin/unzip -o "..XQConfigs.CONFIG_ZIP_FILEPATH.." -d //")
    end
    result["code"] = code
    if code ~= 0 then
       result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    LuciUtil.exec("/bin/rm "..XQConfigs.CONFIG_ZIP_FILEPATH)
    LuciHttp.write_json(result)
end

function reboot()
    local XQLog = require("xiaoqiang.XQLog")
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local client = LuciHttp.formvalue("client")
    local lanIp = XQLanWanUtil.getLanWanIp("lan")
    local result = {}
    if client == "web" then
        XQLog.check(0, XQLog.KEY_REBOOT, 1)
    end
    result["code"] = 0
    result["lanIp"] = lanIp
    LuciHttp.write_json(result)
    LuciHttp.close()
    XQFunction.forkReboot()
end

function reset()
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local XQFunction = require("xiaoqiang.common.XQFunction")
	local LuciUtil = require("luci.util")
    local LuciJson = require("json")
    local format = tonumber(LuciHttp.formvalue("format") or 0)
    local code = 0
    local result = {}
    if format == 1 then
        local formatResult = XQFunction.thrift_tunnel_to_datacenter([[{"api":28}]])
        if formatResult then
            if formatResult.code == 0 then
                code = 0
            else
                code = 1558
            end
        else
            code = 1559
        end
    end
    result["code"] = code
    if code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
    LuciHttp.close()
    if result.code == 0 then
        -- reset smart controller database and config
        XQFunction.thrift_tunnel_to_smarthome_controller([[{"command":"reset_scenes"}]])
        -- set restore default and reboot
        XQFunction.forkResetAll()
    end
end

function getSysAvgLoad()
    local LuciUtil = require("luci.util")
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    XQSysUtil.setDetectionTimestamp()
    local result = {}
    result["code"] = 0
    local avg = LuciUtil.exec("/usr/sbin/sysapi system_info get cpuload")
    result["loadavg"] = tonumber(avg)
    result["processCount"] = tonumber(LuciUtil.exec("cat /proc/cpuinfo | grep -c 'processor'"))
    LuciHttp.write_json(result)
    LuciHttp.close()
end

function setMacFilter()
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local LuciUtil = require("luci.util")
    local LuciDatatypes = require("luci.cbi.datatypes")
    local result = {}
    local code = 0
    local mac = LuciHttp.formvalue("mac")
    local wan = LuciHttp.formvalue("wan")
    local lan = LuciHttp.formvalue("lan")
    local admin = LuciHttp.formvalue("admin")
    local pridisk = LuciHttp.formvalue("pridisk")

    if not XQFunction.isStrNil(mac) and LuciDatatypes.macaddr(mac) then
        if wan then
            wan = tonumber(wan) == 1 and "1" or "0"
        end
        if lan then
            lan = tonumber(lan) == 1 and "1" or "0"
        end
        if admin then
            admin = tonumber(admin) == 1 and "1" or "0"
        end
        if pridisk then
            pridisk = tonumber(pridisk) == 1 and "1" or "0"
        end
        XQSysUtil.setMacFilter(mac,lan,wan,admin,pridisk)
    else
        code = 1508
    end
    result["code"] = code
    if code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(code)
    end
    LuciHttp.write_json(result)
end

function openWps()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local result = {}
    result["code"] = 0
    result["timestamp"] = XQWifiUtil.openWifiWps()
    LuciHttp.write_json(result)
end

function stopWps()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    XQWifiUtil.stopWps()
    local result = {}
    result["code"] = 0
    LuciHttp.write_json(result)
end

function _checkConnection(mac, try)
    local cmac
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    if not mac then
        cmac = XQWifiUtil.getWpsConDevMac()
    else
        cmac = mac
    end
    if XQWifiUtil.isDeviceWifiConnect(cmac, 1) or XQWifiUtil.isDeviceWifiConnect(cmac, 2) then
        return cmac
    else
        if try > 0 then
            os.execute("sleep 3")
            _checkConnection(cmac, try - 1)
        end
    end
    return false
end

function getWpsStatus()
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local XQPreference = require("xiaoqiang.XQPreference")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local result = {}
    local status = XQWifiUtil.getWifiWpsStatus()
    if status == 2 then
        local device = {}
        local mac = XQWifiUtil.getWpsConDevMac()
        if mac then
            if XQWifiUtil.isDeviceWifiConnect(mac, 1) or XQWifiUtil.isDeviceWifiConnect(mac, 2) then
                device["mac"] = mac
                device["company"] = XQDeviceUtil.getDeviceCompany(mac)
            else
                local cmac = _checkConnection(mac, 2)
                if cmac then
                    device["mac"] = cmac
                    device["company"] = XQDeviceUtil.getDeviceCompany(cmac)
                    result["device"] = device
                else
                    status = 9
                end
            end
        else
            local cmac = _checkConnection(mac, 2)
            if cmac then
                device["mac"] = cmac
                device["company"] = XQDeviceUtil.getDeviceCompany(cmac)
                result["device"] = device
            else
                status = 9
            end
        end
    end
    if status >= 3 and status <= 7 then
        status = 3
    end
    result["code"] = 0
    result["status"] = status
    result["startTime"] = XQPreference.get(XQConfigs.PREF_WPS_TIMESTAMP,"")
    result["currentTime"] = tostring(os.time())
    LuciHttp.write_json(result)
end

function createSandbox()
    local LuciUtil = require("luci.util")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local result = {}
    result["code"] = 0
    LuciUtil.exec(XQConfigs.LAMP_CREATE_SANDBOX)
    LuciHttp.write_json(result)
end

function mountThings()
    local LuciUtil = require("luci.util")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local result = {}
    result["code"] = 0
    LuciUtil.exec(XQConfigs.LAMP_MOUNT_THINGS)
    LuciHttp.write_json(result)
end

function umountThings()
    local LuciUtil = require("luci.util")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local result = {}
    result["code"] = 0
    LuciUtil.exec(XQConfigs.LAMP_UMOUNT_THINGS)
    LuciHttp.write_json(result)
end

function startDropbear()
    local LuciUtil = require("luci.util")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local result = {}
    result["code"] = 0
    LuciUtil.exec(XQConfigs.LAMP_START_DROPBEAR)
    LuciHttp.write_json(result)
end

function stopDropbear()
    local LuciUtil = require("luci.util")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local result = {}
    result["code"] = 0
    LuciUtil.exec(XQConfigs.LAMP_STOP_DROPBEAR)
    LuciHttp.write_json(result)
end

function isSandboxCreated()
    local LuciUtil = require("luci.util")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local result = {}
    result["code"] = 0
    result["isSandboxCreated"] = (0 == tonumber(os.execute(XQConfigs.LAMP_IS_SANDBOX_CREATED)))
    LuciHttp.write_json(result)
end

function areThingsMounted()
    local LuciUtil = require("luci.util")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local result = {}
    result["code"] = 0
    result["areThingsMounted"] = (0 == tonumber(os.execute(XQConfigs.LAMP_ARE_THINGS_MOUNTED)))
    LuciHttp.write_json(result)
end

function isDropbearStarted()
    local LuciUtil = require("luci.util")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local result = {}
    result["code"] = 0
    result["isDropbearStarted"] = (0 == tonumber(os.execute(XQConfigs.LAMP_IS_DROPBEAR_STARTED)))
    LuciHttp.write_json(result)
end

function stopNginx()
    local LuciUtil = require("luci.util")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local result = {}
    result["code"] = 0
    LuciUtil.exec(XQConfigs.NGINX_CACHE_STOP)
    LuciHttp.write_json(result)
end

function startNginx()
    local LuciUtil = require("luci.util")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local result = {}
    result["code"] = 0
    LuciUtil.exec(XQConfigs.NGINX_CACHE_START)
    LuciHttp.write_json(result)
end

function nginxCacheStatus()
    local LuciUtil = require("luci.util")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local result = {}
    result["code"] = 0
    result["status"] = 1
    local status = LuciUtil.exec(XQConfigs.NGINX_CACHE_STATUS)
    if status then
        result["status"] = LuciUtil.trim(status) == "NGINX_CACHE=off" and 0 or 1
    end
    LuciHttp.write_json(result)
end

function checkRouterNamePending()
	local XQConfigs = require("xiaoqiang.common.XQConfigs")
	local cmd = XQConfigs.THRIFT_TO_MQTT_GET_DEVICEID
	local LuciUtil = require("luci.util")
    local result = {}
    result["code"] = 0
    result['pending'] = XQSysUtil.getRouterNamePending()
    result["routerId"] = LuciUtil.exec(cmd)
    result['routerName'] = XQSysUtil.getRouterName()
    LuciHttp.write_json(result)
end

function clearRouterNamePending()
    XQSysUtil.setRouterNamePending('0')
    local result = {}
    result["code"] = 0
    LuciHttp.write_json(result)
end

function redirectUrl()
    local XQSecureUtil = require("xiaoqiang.util.XQSecureUtil")
    local cookieValue = LuciHttp.getcookie("psp")
    local result = {}
    result["code"] = 0
    if cookieValue then
        local loginType = cookieValue:match("|||(%S)|||")
        result["redirectUrl"] = "http://miwifi.com/cgi-bin/luci/web/home?redirectKey="..XQSecureUtil.generateRedirectKey(loginType)
    else
        result["redirectUrl"] = "http://miwifi.com/cgi-bin/luci/web/home?redirectKey="..XQSecureUtil.generateRedirectKey(2)
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function mainStatusForApp()
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local XQZigbeeUtil = require("xiaoqiang.util.XQZigbeeUtil")
    local result = {}
    local lan = XQDeviceUtil.getWanLanNetworkStatistics("lan")
    local wan = XQDeviceUtil.getWanLanNetworkStatistics("wan")
    local count = XQFunction.thrift_tunnel_to_smarthome_controller([[{"command":"get_scene_count"}]])
    if count and count.code == 0 then
        result["smartSceneCount"] = count.count
    else
        result["smartSceneCount"] = 0
    end
    -- userdisk
    local disk = XQFunction.thrift_tunnel_to_datacenter([[{"api":26}]])
    if disk and disk.code == 0 then
        result["useableSpace"] = math.floor(tonumber(disk.free) / 1024)
    else
        result["useableSpace"] = 0
    end
    -- plugin
    local plugin = XQFunction.thrift_tunnel_to_datacenter([[{"api":601}]])
    if plugin and plugin.code == 0 then
        result["installedPluginCount"] = #plugin.data
    else
        result["installedPluginCount"] = 0
    end
    -- downloading
    local downloads = 0
    local downloading = 0
    local download = XQFunction.thrift_tunnel_to_datacenter([[{"api":503}]])
    if download and download.code == 0 then
        table.foreach(download.uncompletedList,
            function(i,v)
                downloads = downloads + 1
                if v.downloadStatus == 1 then
                    downloading = downloading + 1
                end
            end
        )
    end
    -- zigbee
    local zigbeecount = XQZigbeeUtil.get_zigbee_count();
    result["code"] = 0
    result["connectDeviceCount"] = zigbeecount + XQDeviceUtil.getConnectDeviceCount()


    result["upTime"] = XQSysUtil.getSysUptime()
    result["maxWanSpeed"] = tonumber(wan.maxdownloadspeed)
    result["maxLanSpeed"] = tonumber(lan.maxdownloadspeed)
    result["wanSpeed"] = tonumber(wan.downspeed)
    result["lanSpeed"] = tonumber(lan.downspeed)
    result["hasDownloading"] = downloading > 0 and 1 or 0
    result["downloadingCount"] = downloads
    LuciHttp.write_json(result)
end

--[[
    filter : lan/wan/admin
]]--
function getMacfilterMode()
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local code = 0
    local result = {}

    local filter = LuciHttp.formvalue("filter") or "lan"
    local mode = XQSysUtil.getMacfilterMode(filter)
    if mode then
        result["mode"] = mode
    else
        code = 1574
    end
    result["code"] = code
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

--[[
    filter : lan/wan/admin
    mode : 0/1 (whitelist/blacklist)
]]--
function setMacfilterMode()
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local code = 0
    local result = {}

    local filter = LuciHttp.formvalue("filter") or "lan"
    local mode = tonumber(LuciHttp.formvalue("mode") or 0)
    local setMode = XQSysUtil.setMacfilterMode(filter,mode)
    if not setMode then
        code = 1575
    end
    result["code"] = code
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function shutdown()
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local result = {}
    result["code"] = 0
    LuciHttp.write_json(result)
    LuciHttp.close()
    XQFunction.forkShutdown()
end

function upnpList()
    local XQUPnPUtil = require("xiaoqiang.util.XQUPnPUtil")
    local result = {}
    result["code"] = 0
    result["status"] = XQUPnPUtil.getUPnPStatus() and 1 or 0
    local upnp = XQUPnPUtil.getUPnPList()
    if upnp then
        result["list"] = upnp
    else
        result["list"] = {}
    end
    LuciHttp.write_json(result)
end

function upnpSwitch()
    local XQLog = require("xiaoqiang.XQLog")
    local XQUPnPUtil = require("xiaoqiang.util.XQUPnPUtil")
    local switch = tonumber(LuciHttp.formvalue("switch") or 1)
    local result = {}
    XQLog.check(0, XQLog.KEY_FUNC_UPNP, switch == 1 and 0 or 1)
    XQUPnPUtil.switchUPnP(switch == 1)
    result["code"] = 0
    LuciHttp.write_json(result)
end

function appLimit()
    local XQQoSUtil = require("xiaoqiang.util.XQQoSUtil")
    local info = XQQoSUtil.appInfo()
    info.code = 0
    LuciHttp.write_json(info)
end

function appLimitSwitch()
    local XQLog = require("xiaoqiang.XQLog")
    local XQQoSUtil = require("xiaoqiang.util.XQQoSUtil")
    local switch = tonumber(LuciHttp.formvalue("switch") or 1)
    local result = {}
    XQLog.check(0, XQLog.KEY_FUNC_APPQOS, switch == 1 and 0 or 1)
    XQQoSUtil.appSpeedlimitSwitch(switch == 1)
    result["code"] = 0
    LuciHttp.write_json(result)
end

function setAppLimit()
    local XQQoSUtil = require("xiaoqiang.util.XQQoSUtil")
    local result = {}
    local xlmaxdownload = LuciHttp.formvalue("xlmaxdownload")
    local xlmaxupload = LuciHttp.formvalue("xlmaxupload")
    local kpmaxdownload = LuciHttp.formvalue("kpmaxdownload")
    local kpmaxupload = LuciHttp.formvalue("kpmaxupload")
    XQQoSUtil.setXunlei(xlmaxdownload, xlmaxupload)
    XQQoSUtil.setKuaipan(kpmaxdownload, kpmaxupload)
    XQQoSUtil.reload()
    result["code"] = 0
    LuciHttp.write_json(result)
end

function vpnInfo()
    local XQVPNUtil = require("xiaoqiang.util.XQVPNUtil")
    local result = XQVPNUtil.getVPNInfo("vpn")
    result["code"] = 0
    LuciHttp.write_json(result)
end

function setVpn()
    local XQLog = require("xiaoqiang.XQLog")
    local XQVPNUtil = require("xiaoqiang.util.XQVPNUtil")
    local code = 0
    local result = {}
    local server = LuciHttp.formvalue("server")
    local username = LuciHttp.formvalue("username")
    local password = LuciHttp.formvalue("password")
    local proto = LuciHttp.formvalue("proto")
    local auto = LuciHttp.formvalue("auto")
    local set = XQVPNUtil.setVpn("vpn", server, username, password, proto, auto)
    if proto and string.upper(proto) == "PPTP" then
        XQLog.check(0, XQLog.KEY_FUNC_PPTP, 0)
    elseif proto and string.upper(proto) == "L2TP" then
        XQLog.check(0, XQLog.KEY_FUNC_L2TP, 0)
    end
    if set then
        code = 0
    else
        code = 1583
    end
    result["code"] = code
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function _vpnErrorCodeHelper(code)
    local errorA = {
        ["507"] = 1,["691"] = 1,["509"] = 1,["514"] = 1,["520"] = 1,
        ["646"] = 1,["647"] = 1,["648"] = 1,["649"] = 1,["691"] = 1,
        ["646"] = 1
    }
    local errorB = {
        ["516"] = 1,["650"] = 1,["601"] = 1,["510"] = 1
    }
    local errorC = {
        ["501"] = 1,["502"] = 1,["503"] = 1,["504"] = 1,["505"] = 1,
        ["506"] = 1,["507"] = 1,["508"] = 1,["511"] = 1,["512"] = 1,
        ["515"] = 1,["517"] = 1,["518"] = 1,["519"] = 1
    }
    local errcode = tostring(code)
    if errcode then
        if errorA[errcode] then
            return 1584
        end
        if errorB[errcode] then
            return 1585
        end
        if errorC[errcode] then
            return 1586
        end
        return 1584
    end
end

-- status: 0 connected 1 connecting 2 failed 3 close 4 none
function vpnStatus()
    local XQVPNUtil = require("xiaoqiang.util.XQVPNUtil")
    local status = XQVPNUtil.vpnStatus()
    local result = {}
    if status then
        local up = status.up
        local autostart = status.autostart
        local uptime = tonumber(status.uptime)
        local stat = status.stat
        if up then
            result["status"] = 0
            result["uptime"] = uptime
        else
            if autostart then
                if stat and stat.code ~= 0 then
                    result["status"] = 2
                    result["uptime"] = 0
                    result["errcode"] = stat.code
                    result["errmsg"] = XQErrorUtil.getErrorMessage(_vpnErrorCodeHelper(stat.code)).." "..tostring(stat.code)
                else
                    result["status"] = 1
                    result["uptime"] = 0
                end
            else
                result["status"] = 3
                result["uptime"] = 0
            end
        end
    else
        result["status"] = 4
        result["uptime"] = 0
    end
    result["code"] = 0
    LuciHttp.write_json(result)
end

function vpnSwitch()
    local XQVPNUtil = require("xiaoqiang.util.XQVPNUtil")
    local conn = tonumber(LuciHttp.formvalue("conn"))
    local result = {}
    if conn and conn == 1 then
        XQVPNUtil.vpnSwitch(true)
    else
        XQVPNUtil.vpnSwitch(false)
    end
    result["code"] = 0
    LuciHttp.write_json(result)
end

function getDeviceMacaddr()
    local remoteaddr = luci.http.getenv("REMOTE_ADDR") or ""
    local result = {}
    local code = 0
    if remoteaddr ~= "127.0.0.1" then
        result["mac"] = luci.dispatcher.getremotemac()
    else
        code = 1587
    end
    result["code"] = code
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function getDetectionTimestamp()
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local result = {}
    result["code"] = 0
    result["timestamp"] = XQSysUtil.getDetectionTimestamp()
    result["currentTime"] = tostring(os.time())
    LuciHttp.write_json(result)
end

function getWifiLog()
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local result = {}
    XQSysUtil.getWifiLog()
    result["code"] = 0
    LuciHttp.write_json(result)
end

function sysRecovery()
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    local ssid = LuciHttp.formvalue("ssid")
    local enc = LuciHttp.formvalue("enc")
    local key = LuciHttp.formvalue("pwd")
    local wanType = LuciHttp.formvalue("wanType")
    local pppoeName = LuciHttp.formvalue("pppoeName")
    local pppoePwd = LuciHttp.formvalue("pppoePwd")
    if ssid then
        XQWifiUtil.setWifiBasicInfo(1, ssid, key, enc, nil, nil, 0)
        XQWifiUtil.setWifiBasicInfo(2, ssid.."_5G", key, enc, nil, nil, 0)
    end
    XQFunction.forkRestartWifi()
    if wanType == "pppoe" then
        XQLanWanUtil.setWanPPPoE(pppoeName,pppoePwd,nil,nil,nil)
    elseif wanType == "dhcp" then
        XQLanWanUtil.setWanStaticOrDHCP(wanType,nil,nil,nil,nil,nil,nil)
    end
    local result = {}
    result["code"] = 0
    LuciHttp.write_json(result)
end
--flag定时wifi开关
function smartShutdown()
    local XQFunction = require("xiaoqiang.common.XQFunction")
    local result = {}
    local code = 0
    local delay1 = LuciHttp.formvalue("delay1")
    local delay2 = LuciHttp.formvalue("delay2")
    if delay1 and delay2 then
        XQFunction.forkShutdownAndRebootWithDelay(delay1, delay2)
    else
        code = 1502
    end
    result["code"] = code
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function getNofStatus()
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local status = tonumber(XQSysUtil.noflushdStatus())
    if status == 0 then
        status = 1
    else
        status = 0
    end
    local result = {
        ["code"] = 0,
        ["status"] = status
    }
    LuciHttp.write_json(result)
end

function nofSwitch()
    local XQLog = require("xiaoqiang.XQLog")
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local switch = tonumber(LuciHttp.formvalue("switch")) or 0
    local result = {}
    local success = XQSysUtil.noflushdSwitch(switch == 1 and true or false)
    XQLog.check(0, XQLog.KEY_FUNC_NOFLUSHED, switch == 1 and 0 or 1)
    if switch == 1 then
        XQLog.check(0, XQLog.KEY_DISKSLEEP_OPEN, 1)
    else
        XQLog.check(0, XQLog.KEY_DISKSLEEP_CLOSE, 1)
    end
    if success then
        result["code"] = 0
    else
        result["code"] = 1606
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

function predownloadInfo()
    local Predownload = require("xiaoqiang.module.XQPredownload")
    local result = {}
    local info = Predownload.predownloadInfo()
    result["code"] = 0
    result["status"] = info.enable
    result["priority"] = info.priority
    LuciHttp.write_json(result)
end

function predownloadSwitch()
    local Predownload = require("xiaoqiang.module.XQPredownload")
    local switch = tonumber(LuciHttp.formvalue("switch")) or 0
    local result = {}
    local success = Predownload.switch(switch == 1 and true or false)
    if success then
        result["code"] = 0
    else
        result["code"] = 1606
    end
    if result.code ~= 0 then
        result["msg"] = XQErrorUtil.getErrorMessage(result.code)
    end
    LuciHttp.write_json(result)
end

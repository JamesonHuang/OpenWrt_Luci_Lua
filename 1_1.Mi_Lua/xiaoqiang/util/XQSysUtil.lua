module ("xiaoqiang.util.XQSysUtil", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")

function getInitInfo()
    local initted = require("xiaoqiang.XQPreference").get(XQConfigs.PREF_IS_INITED)
    if initted then
        return true
    else
        return false
    end
end

function setInited()
    require("xiaoqiang.XQPreference").set(XQConfigs.PREF_IS_INITED, "YES")
    local LuciUtil = require("luci.util")
    LuciUtil.exec("/usr/sbin/sysapi webinitrdr set off")
    return true
end

function setSPwd()
    local LuciUtil = require("luci.util")
    local genpwd = LuciUtil.exec("mkxqimage -I")
    if genpwd then
        local LuciSys = require("luci.sys")
        genpwd = LuciUtil.trim(genpwd)
        LuciSys.user.setpasswd("root", genpwd)
    end
end

function getChangeLog()
    local LuciFs  = require("luci.fs")
    local LuciUtil = require("luci.util")
    if LuciFs.access(XQConfigs.XQ_CHANGELOG_FILEPATH) then
        return LuciUtil.exec("cat "..XQConfigs.XQ_CHANGELOG_FILEPATH)
    end
    return ""
end

function getMiscHardwareInfo()
    local uci = require("luci.model.uci").cursor()
    local result = {}
    result["bbs"] = tostring(uci:get("misc", "hardware", "bbs"))
    result["verify"] = tostring(uci:get("misc", "hardware", "verify"))
    result["gpio"] = tonumber(uci:get("misc", "hardware", "gpio")) == 1 and 1 or 0
    result["recovery"] = tonumber(uci:get("misc", "hardware", "recovery")) == 1 and 1 or 0
    result["flashpermission"] = tonumber(uci:get("misc", "hardware", "flash_per")) == 1 and 1 or 0
    return result
end

function getPassportBindInfo()
    local XQPreference = require("xiaoqiang.XQPreference")
    local initted = XQPreference.get(XQConfigs.PREF_IS_PASSPORT_BOUND)
    local bindUUID = XQPreference.get(XQConfigs.PREF_PASSPORT_BOUND_UUID, "")
    if not XQFunction.isStrNil(initted) and initted == "YES" and not XQFunction.isStrNil(bindUUID) then
        return bindUUID
    else
        return false
    end
end

function setPassportBound(bind,uuid)
    local XQPreference = require("xiaoqiang.XQPreference")
    local XQDBUtil = require("xiaoqiang.util.XQDBUtil")
    if bind then
        if not XQFunction.isStrNil(uuid) then
            XQPreference.set(XQConfigs.PREF_PASSPORT_BOUND_UUID,uuid)
        end
        XQPreference.set(XQConfigs.PREF_IS_PASSPORT_BOUND, "YES")
        XQPreference.set(XQConfigs.PREF_TIMESTAMP, "0")
    else
        if not XQFunction.isStrNil(uuid) then
            XQPreference.set(XQConfigs.PREF_PASSPORT_BOUND_UUID,"")
        end
        XQPreference.set(XQConfigs.PREF_IS_PASSPORT_BOUND, "NO")
        XQPreference.set(XQConfigs.PREF_BOUND_USERINFO, "")
    end
    return true
end

function getSysUptime()
    local LuciUtil = require("luci.util")
    local catUptime = "cat /proc/uptime"
    local data = LuciUtil.exec(catUptime)
    if data == nil then
        return 0
    else
        local t1,t2 = data:match("^(%S+) (%S+)")
        return LuciUtil.trim(t1)
    end
end

function getConfigInfo()
    local LuciUtil = require("luci.util")
    return LuciUtil.exec("cat /etc/config/*")
end

function getRouterName()
    return require("xiaoqiang.XQPreference").get(XQConfigs.PREF_ROUTER_NAME, "")
end

function setRouterName(routerName)
    local XQNetUtil = require("xiaoqiang.util.XQNetUtil")
    local XQLog = require("xiaoqiang.XQLog")
    local oldRouterName = getRouterName()
    if routerName and oldRouterName ~= routerName then
        require("xiaoqiang.XQPreference").set(XQConfigs.PREF_ROUTER_NAME, routerName)
        setRouterNamePending('1')
        return true
    else
        return false
    end
end

function getRouterNamePending()
    return require("xiaoqiang.XQPreference").get(XQConfigs.PREF_ROUTER_NAME_PENDING, '0')
end

function setRouterNamePending(pending)
    return require("xiaoqiang.XQPreference").set(XQConfigs.PREF_ROUTER_NAME_PENDING, pending)
end

function getBindUUID()
    return require("xiaoqiang.XQPreference").get(XQConfigs.PREF_PASSPORT_BOUND_UUID, "")
end

function setBindUUID(uuid)
    return require("xiaoqiang.XQPreference").set(XQConfigs.PREF_PASSPORT_BOUND_UUID, uuid)
end

function setBindUserInfo(userInfo)
    local LuciJson = require("json")
    local XQPreference = require("xiaoqiang.XQPreference")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local XQCryptoUtil = require("xiaoqiang.util.XQCryptoUtil")
    if userInfo and type(userInfo) == "table" then
        local userInfoStr = LuciJson.encode(userInfo)
        XQPreference.set(XQConfigs.PREF_BOUND_USERINFO,XQCryptoUtil.binaryBase64Enc(userInfoStr))
    end
end

function getBindUserInfo()
    local LuciJson = require("json")
    local XQPreference = require("xiaoqiang.XQPreference")
    local XQConfigs = require("xiaoqiang.common.XQConfigs")
    local XQCryptoUtil = require("xiaoqiang.util.XQCryptoUtil")
    local infoStr = XQPreference.get(XQConfigs.PREF_BOUND_USERINFO,nil)
    if infoStr and infoStr ~= "" then
        infoStr = XQCryptoUtil.binaryBase64Dec(infoStr)
        if infoStr then
            return LuciJson.decode(infoStr)
        end
    else
        return nil
    end
end

function getRomVersion()
    local LuciUtil = require("luci.util")
    local romVersion = LuciUtil.exec(XQConfigs.XQ_ROM_VERSION)
    if XQFunction.isStrNil(romVersion) then
        romVersion = ""
    end
    return LuciUtil.trim(romVersion)
end

function getChannel()
    local LuciUtil = require("luci.util")
    local channel = LuciUtil.exec(XQConfigs.XQ_CHANNEL)
    if XQFunction.isStrNil(channel) then
        channel = ""
    end
    return LuciUtil.trim(channel)
end

-- From GPIO
function getHardwareVersion()
    local h = XQFunction.getGpioValue(14)
    local m = XQFunction.getGpioValue(13)
    local l = XQFunction.getGpioValue(12)
    local offset = h * 4 + m * 2 + l
    local char = string.char(65+offset)
    return "Ver."..char
end

function getHardwareGPIO()
    local LuciUtil = require("luci.util")
    local hardware = LuciUtil.exec(XQConfigs.XQ_HARDWARE)
    if XQFunction.isStrNil(hardware) then
        hardware = ""
    else
        hardware = LuciUtil.trim(hardware)
    end
    local misc = getMiscHardwareInfo()
    if misc.gpio == 1 then
        return getHardwareVersion()
    end
    return hardware
end

function getHardware()
    local LuciUtil = require("luci.util")
    local hardware = LuciUtil.exec(XQConfigs.XQ_HARDWARE)
    if XQFunction.isStrNil(hardware) then
        hardware = ""
    else
        hardware = LuciUtil.trim(hardware)
    end
    return hardware
end

function getCFEVersion()
    local LuciUtil = require("luci.util")
    local cfe = LuciUtil.exec(XQConfigs.XQ_CFE_VERSION)
    if XQFunction.isStrNil(cfe) then
        cfe = ""
    end
    return LuciUtil.trim(cfe)
end

function getKernelVersion()
    local LuciUtil = require("luci.util")
    local kernel = LuciUtil.exec(XQConfigs.XQ_KERNEL_VERSION)
    if XQFunction.isStrNil(kernel) then
        kernel = ""
    end
    return LuciUtil.trim(kernel)
end

function getRamFsVersion()
    local LuciUtil = require("luci.util")
    local ramFs = LuciUtil.exec(XQConfigs.XQ_RAMFS_VERSION)
    if XQFunction.isStrNil(ramFs) then
        ramFs = ""
    end
    return LuciUtil.trim(ramFs)
end

function getSqaFsVersion()
    local LuciUtil = require("luci.util")
    local sqaFs = LuciUtil.exec(XQConfigs.XQ_SQAFS_VERSION)
    if XQFunction.isStrNil(sqaFs) then
        sqaFs = ""
    end
    return LuciUtil.trim(sqaFs)
end

function getRootFsVersion()
    local LuciUtil = require("luci.util")
    local rootFs = LuciUtil.exec(XQConfigs.XQ_ROOTFS_VERSION)
    if XQFunction.isStrNil(rootFs) then
        rootFs = ""
    end
    return LuciUtil.trim(rootFs)
end

function getLangList()
    local LuciUtil = require("luci.util")
    local LuciConfig = require("luci.config")
    local langs = {}
    for k, v in LuciUtil.kspairs(LuciConfig.languages) do
        if type(v)=="string" and k:sub(1, 1) ~= "." then
            local lang = {}
            lang['lang'] = k
            lang['name'] = v
            table.insert(langs,lang)
        end
    end
    return langs
end

function getLang()
    local LuciConfig = require("luci.config")
    return LuciConfig.main.lang
end

function setLang(lang)
    local LuciUtil = require("luci.util")
    local LuciUci = require("luci.model.uci")
    local LuciConfig = require("luci.config")
    for k, v in LuciUtil.kspairs(LuciConfig.languages) do
        if type(v) == "string" and k:sub(1, 1) ~= "." then
            if lang == k or lang == "auto" then
                local cursor = LuciUci.cursor()
                if lang=="auto" then
                    cursor:set("luci", "main" , "lang" , "auto")
                else
                    cursor:set("luci", "main" , "lang" , k)
                end
                cursor:commit("luci")
                cursor:save("luci")
                return true
            end
        end
    end
    return false
end

function setSysPasswordDefault()
    local LuciSys = require("luci.sys")
    local XQSecureUtil = require("xiaoqiang.util.XQSecureUtil")
    XQSecureUtil.savePlaintextPwd("admin", "admin")
end

function checkSysPassword(oldPassword)
    local LuciSys = require("luci.sys")
    return LuciSys.user.checkpasswd("root", oldPassword)
end

function setSysPassword(newPassword)
    local LuciSys = require("luci.sys")
    local XQSecureUtil = require("xiaoqiang.util.XQSecureUtil")
    check = LuciSys.user.setpasswd("root", newPassword)
    XQSecureUtil.savePlaintextPwd("admin", newPassword)
    if check == 0 then
        return true
    else
        local LuciUtil = require("luci.util")
        LuciUtil.exec("rm /etc/passwd+")
    end
    return false
end

function cutImage(filePath)
    if not filePath then
        return false
    end
    local code = os.execute(XQConfigs.XQ_CUT_IMAGE..filePath)
    if 0 == code or 127 == code then
        return true
    else
        return false
    end
end

function verifyImage(filePath)
    if not filePath then
        return false
    end
    if 0 == os.execute(XQConfigs.XQ_VERIFY_IMAGE..filePath) then
        return true
    else
        return false
    end
end

function getSysInfo()
    local LuciSys = require("luci.sys")
    local LuciUtil = require("luci.util")
    local sysInfo = {}
    local processor = LuciUtil.execl("cat /proc/cpuinfo | grep processor")
    local platform, model, memtotal, memcached, membuffers, memfree, bogomips = LuciSys.sysinfo()
    if #processor > 0 then
        sysInfo["core"] = #processor
    else
        sysInfo["core"] = 1
    end
    local chippkg = LuciUtil.exec(XQConfigs.GET_CPU_CHIPPKG)
    if chippkg then
        chippkg = tonumber(LuciUtil.trim(chippkg))
        if chippkg == 0 then
            sysInfo["hz"] = "1GHz"
        else
            sysInfo["hz"] = "800MHz"
        end
    else
        sysInfo["hz"] = XQFunction.hzFormat(tonumber(bogomips)*500000)
    end
    sysInfo["system"] = platform
    sysInfo["memTotal"] = string.format("%0.2f M",memtotal/1024)
    sysInfo["memFree"] = string.format("%0.2f M",memfree/1024)
    return sysInfo
end

function setMacFilter(mac,lan,wan,admin,pridisk)
    if not XQFunction.isStrNil(mac) then
        local cmd = "/usr/sbin/sysapi macfilter set mac="..mac
        if wan then
            cmd = cmd.." wan="..(wan == "1" and "yes" or "no")
        end
        if lan then
            cmd = cmd.." lan="..(lan == "1" and "yes" or "no")
        end
        if admin then
            cmd = cmd.." admin="..(admin == "1" and "yes" or "no")
        end
        if pridisk then
            cmd = cmd.." pridisk="..(pridisk == "1" and "yes" or "no")
        end
        if os.execute(cmd..";".."/usr/sbin/sysapi macfilter commit") == 0 then
            return true
        end
    end
    return false
end

function getDiskSpace()
    local LuciUtil = require("luci.util")
    local disk = LuciUtil.exec(XQConfigs.DISK_SPACE)
    if disk and tonumber(LuciUtil.trim(disk)) then
        disk = tonumber(LuciUtil.trim(disk))
        return XQFunction.byteFormat(disk*1024)
    else
        return "Cannot find userdisk"
    end
end

function getAvailableMemery()
    local LuciUtil = require("luci.util")
    local memery = LuciUtil.exec(XQConfigs.AVAILABLE_MEMERY)
    if memery and tonumber(LuciUtil.trim(memery)) then
        return tonumber(LuciUtil.trim(memery))
    else
        return false
    end
end

function getAvailableDisk()
    local LuciUtil = require("luci.util")
    local disk = LuciUtil.exec(XQConfigs.AVAILABLE_DISK)
    if disk and tonumber(LuciUtil.trim(disk)) then
        return tonumber(LuciUtil.trim(disk))
    else
        return false
    end
end

function checkDiskSpace(byte)
    local disk = getAvailableDisk()
    if disk then
        if disk - byte/1024 > 10240 then
            return true
        end
    end
    return false
end

function checkTmpSpace(byte)
    local tmp = getAvailableMemery()
    if tmp then
        if tmp - byte/1024 > 10240 then
            return true
        end
    end
    return false
end

function updateUpgradeStatus(status)
    local status = tostring(status)
    os.execute("echo "..status.." > "..XQConfigs.UPGRADE_LOCK_FILE)
end

function getUpgradeStatus()
    local LuciUtil = require("luci.util")
    local status = tonumber(LuciUtil.exec(XQConfigs.UPGRADE_STATUS))
    if status then
        return status
    else
        return 0
    end
end

function checkBeenUpgraded()
    local LuciUtil = require("luci.util")
    local otaFlag = tonumber(LuciUtil.trim(LuciUtil.exec("nvram get flag_ota_reboot")))
    if otaFlag == 1 then
        return true
    else
        return false
    end
end

--[[
    0 : 没有flash
    1 : 正在执行flash
    2 : flash成功 需要重启
    3 : flash失败
]]--
function getFlashStatus()
    local LuciFs = require("luci.fs")
    if checkBeenUpgraded() then
        return 2
    end
    local check = os.execute(XQConfigs.FLASH_EXECUTION_CHECK)
    if check ~= 0 then
        return 1
    end
    if not LuciFs.access(XQConfigs.FLASH_PID_TMP) then
        return 0
    else
        return 3
    end
end

function checkExecStatus(checkCmd)
    local LuciUtil = require("luci.util")
    local check = LuciUtil.exec(checkCmd)
    if check then
        check = tonumber(LuciUtil.trim(check))
        if check > 0 then
            return 1
        end
    end
    return 0
end

--[[
    0 : 没有upgrade
    1 : 检查升级
    2 : 检查tmp 磁盘是否有空间下载
    3 : 下载升级包
    4 : 检测升级包
    5 : 刷写升级包
    6 : 没有检测到更新
    7 : 没有磁盘空间
    8 : 下载失败
    9 : 升级包校验失败
    10 : 刷写失败
    11 : 升级成功
    12 : 手动升级在刷写升级包
]]--
function checkUpgradeStatus()
    local LuciFs = require("luci.fs")
    if checkBeenUpgraded() then
        return 11
    end
    local status = getUpgradeStatus()
    if checkExecStatus(XQConfigs.CRONTAB_ROM_CHECK) == 1 then
        if status == 0 then
            return 1
        else
            return status
        end
    end
    local checkFlash = os.execute(XQConfigs.FLASH_EXECUTION_CHECK)
    if checkFlash ~= 0 then
        if checkExecStatus(XQConfigs.CROM_FLASH_CHECK) == 1 then
            return 12
        else
            return 5
        end
    end
    local flashStatus = getFlashStatus()
    local execute = LuciFs.access(XQConfigs.CRONTAB_PID_TMP)
    if execute then
        if status == 0 then
            if flashStatus == 2 then
                return 11
            elseif flashStatus == 3 then
                return 10
            end
        end
        return status
    else
        if flashStatus == 2 then
            return 11
        elseif flashStatus == 3 then
            return 10
        end
    end
    return 0
end

function isUpgrading()
    local status = checkUpgradeStatus()
    if status == 1 or status == 2 or status == 3 or status == 4 or status == 5 or status == 12 then
        return true
    else
        return false
    end
end

function cancelUpgrade()
    local LuciUtil = require("luci.util")
    local XQPreference = require("xiaoqiang.XQPreference")
    local XQDownloadUtil = require("xiaoqiang.util.XQDownloadUtil")
    local checkFlash = os.execute(XQConfigs.FLASH_EXECUTION_CHECK)
    if checkFlash ~= 0 then
        return false
    end
    local pid = LuciUtil.exec(XQConfigs.UPGRADE_PID)
    local luapid = LuciUtil.exec(XQConfigs.UPGRADE_LUA_PID)
    if not XQFunction.isStrNil(pid) then
        pid = LuciUtil.trim(pid)
        os.execute("kill "..pid)
        if not XQFunction.isStrNil(luapid) then
            os.execute("kill "..LuciUtil.trim(luapid))
        end
        XQDownloadUtil.cancelDownload(XQPreference.get(XQConfigs.PREF_ROM_DOWNLOAD_ID, ""))
        XQFunction.sysUnlock()
        return true
    else
        return false
    end
end

--[[
    Temp < 50, 属于正常
    50 < Temp < 64, 风扇可能工作不正常
    Temp > 64, 不正常风扇或温度传感器坏了
]]--
function getCpuTemperature()
    local LuciUtil = require("luci.util")
    local temperature = LuciUtil.exec(XQConfigs.CPU_TEMPERATURE)
    if not XQFunction.isStrNil(temperature) then
        temperature = temperature:match('Temperature: (%S+)')
        if temperature then
            temperature = tonumber(LuciUtil.trim(temperature))
            return temperature
        end
    end
    return 0
end

--[[
    simple : 0/1/2 (正常模式,时间长上传log/简单模式,时间短,不上传log/简单模式,时间短,上传log)
]]--
function getNetworkDetectInfo(simple,target)
    local LuciUtil = require("luci.util")
    local LuciJson = require("json")
    local XQSecureUtil = require("xiaoqiang.util.XQSecureUtil")
    local network = {}
    local targetUrl = (target == nil or not XQSecureUtil.cmdSafeCheck(target)) and "http://www.baidu.com" or target
    if targetUrl and targetUrl:match("http://") == nil and targetUrl:match("https://") == nil then
        targetUrl = "http://"..targetUrl
    end
    local result
    if tonumber(simple) == 1 then
        result = LuciUtil.exec(XQConfigs.SIMPLE_NETWORK_NOLOG_DETECT.."'"..targetUrl.."'")
    elseif tonumber(simple) == 2 then
        result = LuciUtil.exec(XQConfigs.SIMPLE_NETWORK_DETECT.."'"..targetUrl.."'")
    else
        result = LuciUtil.exec(XQConfigs.FULL_NETWORK_DETECT.."'"..targetUrl.."'")
    end
    if result then
        result = LuciJson.decode(LuciUtil.trim(result))
        if result and type(result) == "table" then
            local checkInfo = result.CHECKINFO
            if checkInfo and type(checkInfo) == "table" then
                network["wanLink"] = checkInfo.wanlink == "up" and 1 or 0
                network["wanType"] = checkInfo.wanprotocal or ""
                network["pingLost"] = checkInfo.ping:match("(%S+)%%")
                network["gw"] = checkInfo.gw:match("(%S+)%%")
                network["dns"] = checkInfo.dns == "ok" and 1 or 0
                network["tracer"] = checkInfo.tracer == "ok" and 1 or 0
                network["memory"] = tonumber(checkInfo.memory)*100
                network["cpu"] = tonumber(checkInfo.cpu)
                network["disk"] = checkInfo.disk
                network["tcp"] = checkInfo.tcp
                network["http"] = checkInfo.http
                network["ip"] = checkInfo.ip
                return network
            end
        end
    end
    return nil
end

function checkSystemStatus()
    local LuciUtil = require("luci.util")
    local status = {}
    status["cpu"] = tonumber(LuciUtil.trim(LuciUtil.exec(XQConfigs.CPU_LOAD_AVG))) or 0
    status["mem"] = tonumber(LuciUtil.trim(LuciUtil.exec(XQConfigs.MEMERY_USAGE))) or 0
    status["link"] = string.upper(LuciUtil.trim(LuciUtil.exec(XQConfigs.WAN_LINK))) == "UP"
    status["wan"] = true --tonumber(LuciUtil.trim(LuciUtil.exec(XQConfigs.WAN_UP))) > 0
    status["tmp"] = getCpuTemperature()
    return status
end

--[[
    lan: samba
    wan: internet
    admin: root
    return 0/1 (whitelist/blacklist)
]]--
function getMacfilterMode(filter)
    local LuciUtil = require("luci.util")
    local getMode = XQConfigs.GET_LAN_MODE
    if filter == "wan" then
        getMode = XQConfigs.GET_WAN_MODE
    elseif filter == "admin" then
        getMode = XQConfigs.GET_ADMIN_MODE
    end
    local macMode = LuciUtil.exec(getMode)
    if macMode then
        macMode = LuciUtil.trim(macMode)
        if macMode == "whitelist" then
            return 0
        else
            return 1
        end
    end
    return false
end

--[[
    filter : lan/wan/admin
    mode : 0/1 (whitelist/blacklist)
]]--
function setMacfilterMode(filter,mode)
    local LuciUtil = require("luci.util")
    local setMode
    if filter == "lan" then
        if tonumber(mode) == 0 then
            setMode = XQConfigs.SET_LAN_WHITELIST
        else
            setMode = XQConfigs.SET_LAN_BLACKLIST
        end
    elseif filter == "wan" then
        if tonumber(mode) == 0 then
            setMode = XQConfigs.SET_WAN_WHITELIST
        else
            setMode = XQConfigs.SET_WAN_BLACKLIST
        end
    elseif filter == "admin" then
        if tonumber(mode) == 0 then
            setMode = XQConfigs.SET_ADMIN_WHITELIST
        else
            setMode = XQConfigs.SET_ADMIN_BLACKLIST
        end
    end
    if setMode and os.execute(setMode) == 0 then
        return true
    else
        return false
    end
end

function getDetectionTimestamp()
    local XQPreference = require("xiaoqiang.XQPreference")
    return tonumber(XQPreference.get(XQConfigs.PREF_TIMESTAMP, "0"))
end

function setDetectionTimestamp()
    local XQPreference = require("xiaoqiang.XQPreference")
    XQPreference.set(XQConfigs.PREF_TIMESTAMP, tostring(os.time()))
end

function getWifiLog()
    os.execute(XQConfigs.WIFI_LOG_COLLECTION)
end

function getNvramConfigs()
    local configs = {}
    configs["wifi_ssid"] = XQFunction.nvramGet("nv_wifi_ssid", "")
    configs["wifi_enc"] = XQFunction.nvramGet("nv_wifi_enc", "")
    configs["wifi_pwd"] = XQFunction.nvramGet("nv_wifi_pwd", "")
    configs["rom_ver"] = XQFunction.nvramGet("nv_rom_ver", "")
    configs["rom_channel"] = XQFunction.nvramGet("nv_rom_channel", "")
    configs["hardware"] = XQFunction.nvramGet("nv_hardware", "")
    configs["uboot"] = XQFunction.nvramGet("nv_uboot", "")
    configs["linux"] = XQFunction.nvramGet("nv_linux", "")
    configs["ramfs"] = XQFunction.nvramGet("nv_ramfs", "")
    configs["sqafs"] = XQFunction.nvramGet("nv_sqafs", "")
    configs["rootfs"] = XQFunction.nvramGet("nv_rootfs", "")
    configs["sys_pwd"] = XQFunction.nvramGet("nv_sys_pwd", "")
    configs["wan_type"] = XQFunction.nvramGet("nv_wan_type", "")
    configs["pppoe_name"] = XQFunction.nvramGet("nv_pppoe_name", "")
    configs["pppoe_pwd"] = XQFunction.nvramGet("nv_pppoe_pwd", "")
    return configs
end

function noflushdStatus()
    return os.execute("/etc/init.d/noflushd status")
end

function noflushdSwitch(on)
    if on then
        return os.execute("/etc/init.d/noflushd on") == 0
    else
        return os.execute("killall -s 10 noflushd ; /etc/init.d/noflushd off") == 0
    end
end

function getModulesList()
    local uci = require("luci.model.uci").cursor()
    local result = {}
    local modules = uci:get_all("module", "common")
    for key, value in pairs(modules) do
        if key and value and not key:match("%.") then
            result[key] = value
        end
    end
    if _G.next(result) == nil then
        return nil
    else
        return result
    end
end

function facInfo()
    local LuciUtil = require("luci.util")
    local fac = {}
    fac["version"] = getRomVersion()
    fac["init"] = getInitInfo()
    fac["ssh"] = tonumber(XQFunction.nvramGet("ssh_en", 0)) == 1 and true or false
    fac["uart"] = tonumber(XQFunction.nvramGet("uart_en", 0)) == 1 and true or false
    fac["telnet"] = tonumber(XQFunction.nvramGet("telnet_en", 0)) == 1 and true or false
    fac["facmode"] = tonumber(LuciUtil.exec("cat /proc/xiaoqiang/ft_mode 2>/dev/null")) == 1 and true or false
    local start = tonumber(LuciUtil.exec("fdisk -lu | grep /dev/sda4 | awk {'print $2'}"))
    if start then
        start = math.mod(start ,8) == 0 and true or false
    else
        start = false
    end
    fac["4kblock"] = start
    return fac
end
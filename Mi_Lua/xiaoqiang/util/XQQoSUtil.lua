module ("xiaoqiang.util.XQQoSUtil", package.seeall)

local cursor = require("luci.model.uci").cursor()

local XQConfigs = require("xiaoqiang.common.XQConfigs")
local XQFunction = require("xiaoqiang.common.XQFunction")

function _application()
    local cursor = luci.model.uci.cursor()
    local config = cursor:get_all("app-tc","config")
    local xunlei = cursor:get_all("app-tc","xunlei")
    local kuaipan = cursor:get_all("app-tc","kuaipan")
    local application = {}
    if config then
        application.enable = config.enable
    end
    if xunlei then
        application.xunlei = xunlei
    end
    if kuaipan then
        application.kuaipan = kuaipan
    end
    return application
end

function _set(section, option, value)
    cursor:set("app-tc", section, option, value)
end

function _apply()
    cursor:save("app-tc")
    cursor:commit("app-tc")
end

function _appSpeedlimit(app, maxdownload, maxupload)
    if maxdownload then
        _set(app, "max_download_speed", tostring(maxdownload))
    end
    if maxupload then
        _set(app, "max_upload_speed", tostring(maxupload))
    end
    _apply()
end

function appSpeedlimitSwitch(enable)
    local cmd = enable and XQConfigs.QOS_APPSL_ENABLE or XQConfigs.QOS_APPSL_DISABLE
    local value = enable and "1" or "0"
    _set("config", "enable", value)
    _apply()
    return (os.execute(cmd) == 0)
end

function appInfo()
    local json = require("json")
    local LuciUtil = require("luci.util")
    local info = {}
    local xunlei = {}
    local kuaipan = {}
    local application = _application()

    local xlcspeed = XQFunction.thrift_tunnel_to_datacenter([[{"api":45,"appCode":1}]])
    local kpcspeed = XQFunction.thrift_tunnel_to_datacenter([[{"api":45,"appCode":0}]])

    if xlcspeed and xlcspeed.code == 0 then
        xunlei.download = tonumber(xlcspeed.downloadSpeed)
        xunlei.upload = tonumber(xlcspeed.uploadSpeed)
    else
        xunlei.download = 0
        xunlei.upload = 0
    end
    if kpcspeed and kpcspeed.code == 0 then
        kuaipan.download = tonumber(kpcspeed.downloadSpeed)
        kuaipan.upload = tonumber(kpcspeed.uploadSpeed)
    else
        kuaipan.download = 0
        kuaipan.upload = 0
    end
    info.enable = application.enable
    xunlei.enable = application.xunlei.enable
    xunlei.maxdownload = tonumber(application.xunlei.max_download_speed)
    xunlei.maxupload = tonumber(application.xunlei.max_upload_speed)

    kuaipan.enable = application.kuaipan.enable
    kuaipan.maxdownload = tonumber(application.kuaipan.max_download_speed)
    kuaipan.maxupload = tonumber(application.kuaipan.max_upload_speed)
    info.xunlei = xunlei
    info.kuaipan = kuaipan
    return info
end

function setXunlei(maxdownload, maxupload)
    _appSpeedlimit("xunlei", maxdownload, maxupload)
end

function setKuaipan(maxdownload, maxupload)
    _appSpeedlimit("kuaipan", maxdownload, maxupload)
end

function reload()
    os.execute(XQConfigs.QOS_APPSL_RELOAD)
end

--
-- smart qos
--
-- return KB
function _bitFormat(bits)
    if XQFunction.isStrNil(bits) then
        return 0
    end
    if type(bits) == "number" then
        return tonumber(string.format("%0.2f", bits/8000))
    end
    if bits:match("Gbit") then
        return tonumber(bits:match("(%S+)Gbit"))*125000
    elseif bits:match("Mbit") then
        return tonumber(bits:match("(%S+)Mbit"))*125
    elseif bits:match("Kbit") then
        return tonumber(string.format("%0.2f",tonumber(bits:match("(%S+)Kbit"))/8))
    elseif bits:match("bit") then
        return tonumber(string.format("%0.2f",tonumber(bits:match("(%S+)bit"))/8000))
    else
        return 0
    end
end

function _weightHelper(level)
    if level == 1 then
        return 0.25
    elseif level == 2 then
        return 0.5
    elseif level == 3 then
        return 0.75
    else
        return 0.1
    end
end

-- 0/1/2/3  unset/low/middel/high
function _levelHelper(weight)
    if weight == 0 then
        return 2
    elseif weight > 0 and weight <= 0.25 then
        return 1
    elseif weight > 0.25 and weight <= 0.5 then
        return 2
    elseif weight > 0.5 then
        return 3
    end
    return 0
end

function qosSwitch(on)
    if on then
        return os.execute("/etc/init.d/miqos on") == 0
    else
        return os.execute("/etc/init.d/miqos off") == 0
    end
end

function qosModeSwitch(auto)
    if auto then
        return os.execute("/etc/init.d/miqos auto on") == 0
    else
        return os.execute("/etc/init.d/miqos auto off") == 0
    end
end

function qosRestart()
    return os.execute("/etc/init.d/miqos restart")
end

-- on   : 0/1 close/open
-- mode : 0/1 auto/manual
function qosStatus()
    local uci = require("luci.model.uci").cursor()
    local status = {}
    if os.execute("/etc/init.d/miqos status") == 0 then
        status["on"] = 1
    else
        status["on"] = 0
    end
    local auto = uci:get("miqos", "settings", "qos_auto")
    if auto and tonumber(auto) == 1 then
        status["mode"] = 0
    else
        status["mode"] = 1
    end
    return status
end

-- M bits/s
function qosBand()
    local MiQos = require("sysapi.miqos")
    local result = {
        ["download"] = 0,
        ["upload"] = 0
    }
    local band = MiQos.cmd("show_band")
    if band and band.status == 0 and band.data then
        result.download = tonumber(string.format("%0.2f", band.data.downlink/1000))
        result.upload = tonumber(string.format("%0.2f", band.data.uplink/1000))
    end
    return result
end

-- M bits/s
function setQosBand(upload, download)
    local MiQos = require("sysapi.miqos")
    if upload and download then
        local upload = tostring(math.floor(1000*upload))
        local download = tostring(math.floor(1000*download))
        local setband = MiQos.cmd(string.format("change_band %s %s", upload, download))
        if setband and setband.status == 0 then
            return true
        end
    end
    return false
end

function qosList()
    local LuciUtil = require("luci.util")
    local MiQos = require("sysapi.miqos")
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local result = {}
    local devicedict = {}
    local devicelist = XQDeviceUtil.getConDevices(true)
    local qoslist = MiQos.cmd("show_limit")
    local band = qosBand()
    if devicelist and type(devicelist) == "table" and #devicelist > 0 then
        for _, item in ipairs(devicelist) do
            devicedict[item.ip] = item
        end
    end
    if devicedict and qoslist and qoslist.status == 0 and qoslist.data then
        for ip, value in pairs(qoslist.data) do
            local device = devicedict[ip]
            if device then
                device = LuciUtil.clone(device, true)
                device.ip = ip
                local qos = {}
                qos["downmax"] = _bitFormat(value.DOWN.max)
                qos["downmin"] = _bitFormat(value.DOWN.min)
                local dpercent
                local level
                if band.download > 0 then
                    dpercent = 100 * (tonumber(value.DOWN.max_per) or 1)
                    level = _levelHelper(tonumber(value.DOWN.min_per) or 0)
                else
                    level = 2
                    dpercent = 100
                end
                qos["maxdownper"] = dpercent
                qos["upmax"] = _bitFormat(value.UP.max)
                qos["upmin"] = _bitFormat(value.UP.min)
                local upercent
                if band.upload > 0 then
                    upercent = 100 * (tonumber(value.UP.max_per) or 1)
                else
                    upercent = 100
                end
                qos["level"] = level
                qos["upmaxper"] = upercent
                device["qos"] = qos
                table.insert(result, device)
            end
        end
    end
    return result
end

-- maxup/maxdown (0, 1]
-- upweight/downweight (low, middle, high) 
function qosOnLimit(mac, maxup, maxdown, upweight, downweight)
    local MiQos = require("sysapi.miqos")
    if mac and maxup and maxdown and upweight and downweight then
        local maxup = tonumber(maxup)
        local maxdown = tonumber(maxdown)
        if maxup > 1 then
            maxup = 1
        elseif maxup <= 0 then
            maxup = 0.1
        end
        if maxdown > 1 then
            maxdown = 1
        elseif maxdown <= 0 then
            maxdown = 0.1
        end
        local upweight = tostring(_weightHelper(upweight))
        local downweight = tostring(_weightHelper(downweight))
        local mac = XQFunction.macFormat(mac)
        local limit = MiQos.cmd(string.format("on_limit %s %s %s %s %s", mac, maxup, maxdown, upweight, downweight))
        if limit and limit.status == 0 then
            return true
        end
    end
    return false
end

-- if mac = nil then clear all limits
function qosOffLimit(mac)
    local MiQos = require("sysapi.miqos")
    local offlimit
    if not XQFunction.isStrNil(mac) then
        offlimit = MiQos.cmd(string.format("off_limit %s", XQFunction.macFormat(mac)))
    else
        offlimit = MiQos.cmd(string.format("off_limit"))
    end
    if offlimit and offlimit.status == 0 then
        return true
    else
        return false
    end
end

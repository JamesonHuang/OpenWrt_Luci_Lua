module ("xiaoqiang.util.XQSDKUtil", package.seeall)

local XQLog = require("xiaoqiang.XQLog")
local XQPreference = require("xiaoqiang.XQPreference")

local XQConfigs = require("xiaoqiang.common.XQConfigs")
local XQFunction = require("xiaoqiang.common.XQFunction")
local XQCryptoUtil = require("xiaoqiang.util.XQCryptoUtil")

CONFIG_MACFILTER = "sdkfilter"

function _permissionFilter(mac)
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local dhcpDict = XQDeviceUtil.getDHCPDict()
    local dhcpInfo = dhcpDict[mac]
    if dhcpInfo and dhcpInfo.name and string.lower(dhcpInfo.name):match("^mitv") then
        XQPreference.set(_formatMac(mac), "1", CONFIG_MACFILTER)
        XQLog.log(6, "SDK filter. Empower mac:"..mac..". MiTV")
        return true
    else
        XQLog.log(6, "SDK filter. Check failed!"..mac..".dhcp info:",dhcpInfo)
        return false
    end
end

function _formatMac(mac)
    if XQFunction.isStrNil(mac) then
        return nil
    end
    return mac:gsub(":","")
end

function checkPermission(mac)
    if XQFunction.isStrNil(mac) then
        return false
    else
        mac = XQFunction.macFormat(mac)
    end
    local permission = XQPreference.get(_formatMac(mac), nil, CONFIG_MACFILTER)
    if permission then
        if permission == "1" then
            XQLog.log(6, "SDK filter. mac:"..mac.." OK!")
            return true
        else
            XQLog.log(6, "SDK filter. mac:"..mac.." not allowed")
            return false
        end
    else
        return _permissionFilter(mac)
    end
    return false
end

function setPermission(mac, permission)
    if XQFunction.isStrNil(mac) then
        return false
    else
        mac = XQFunction.macFormat(mac)
    end
    local permission = permission and "1" or "0"
    XQPreference.set(_formatMac(mac), permission, CONFIG_MACFILTER)
end
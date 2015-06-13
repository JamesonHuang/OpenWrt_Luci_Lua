module ("xiaoqiang.XQPushHelper", package.seeall)

local bit = require("bit")
local Json = require("json")

local XQLog = require("xiaoqiang.XQLog")
local XQPreference = require("xiaoqiang.XQPreference")
local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")
local XQSysUtil = require("xiaoqiang.util.XQSysUtil")

WIFI_CLEAR = false

PUSH_DEFAULT_MESSAGE_TITLE = "新消息"
PUSH_DEFAULT_MESSAGE_DESCRIPTION = "您有一条新消息"

PUSH_MESSAGE_TITLE = {
    "系统升级",
    "备注设备上线",
    "陌生设备上线",
    "所有WiFi设备离线",
    "下载完成",
    "智能场景",
    "网络检测",
    "加速相关",
    "%s有更新，请升级！"
}

PUSH_MESSAGE_DESCRIPTION = {
    "路由器已经升级到最新版",
    "备注设备上线",
    "陌生设备上线",
    "所有WiFi设备离线",
    "全部下载任务已经完成",
    "智能场景已经完成",
    "网络检测已经完成",
    "加速提醒",
    "发现新版本%s（%s）"
}

function _formatStr(str)
    local str = string.gsub(str,"\"","\\\"")
    str = string.gsub(str, ";", "\\;")
    str = string.gsub(str, "&", "\\&")
    return str:gsub(" ","")
end

function _parserFlag(flag)
    local result = {
        ["f"] = false,
        ["p"] = true
    }
    local flag = tonumber(flag)
    if flag then
        if bit.band(flag, 0x01) == 0x01 then
            result.p = true
        else
            result.p = false
        end
        if bit.band(flag, 0x02) == 0x02 then
            result.f = true
        else
            result.f = false
        end
    end
    return result
end

function _parserPushType(ptype)
    local flag = "0x01"
    local ptype = tostring(ptype)
    if ptype then
        flag = XQPreference.get(ptype, "0x01", "push")
    end
    return _parserFlag(flag)
end

function _doPush(payload, title, description, ptype)
    if not payload or not title or not description then
        return
    end
    payload = _formatStr(payload)
    local pushtype = "1"
    if ptype then
        pushtype = tostring(ptype)
    end
    os.execute(string.format("pushClient %s %s %s %s", payload, title, description, pushtype))
end

function _hookSysUpgraded()
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local ver = XQSysUtil.getRomVersion()
    local res = _parserPushType(1)
    local payload = {
        ["type"] = 1,
        ["feed"] = res.f,
        ["push"] = res.p,
        ["ver"] = ver
    }
    _doPush(Json.encode(payload), PUSH_MESSAGE_TITLE[1], PUSH_MESSAGE_DESCRIPTION[1])
end

function _hookWifiConnect(mac)
    if XQFunction.isStrNil(mac) then
        return
    else
        mac = XQFunction.macFormat(mac)
    end
    local uci = require("luci.model.uci").cursor()
    local mackey = mac:gsub(":", "")
    local history = uci:get("devicelist", "history", mackey)
    if not history then
        uci:set("devicelist", "history", mackey, 1)
        uci:commit("devicelist")
    end

    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local dhcpInfo = XQDeviceUtil.getDHCPDict()[mac] or {}

    if not history and XQFunction.isStrNil(dhcpInfo.name) then
        os.execute("sleep 5")
        dhcpInfo = XQDeviceUtil.getDHCPDict()[mac] or {}
    end

    if not history and not XQFunction.isStrNil(dhcpInfo.name) then
        local dhcpname = string.lower(dhcpInfo.name)
        if dhcpname:match("^miwifi%-r1c") then
            local payload = {
                ["type"] = 23,
                ["name"] = "小米路由器mini"
            }
            _doPush(Json.encode(payload), "中继成功", "中继成功")
            return
        elseif dhcpname:match("^miwifi%-r1d") or dhcpname:match("^miwifi%-r2d") then
            local payload = {
                ["type"] = 23,
                ["name"] = "小米路由器"
            }
            _doPush(Json.encode(payload), "中继成功", "中继成功")
            return
        end
    end

    if not history then
        local XQEquipment = require("xiaoqiang.XQEquipment")
        local XQDBUtil = require("xiaoqiang.util.XQDBUtil")
        local device = XQDBUtil.fetchDeviceInfo(mac)
        if device and XQFunction.isStrNil(device.nickname) then
            local res = _parserPushType(3)
            local name = dhcpInfo.name
            if name and string.lower(name):match("android-%S+") and #name > 12 then
                name = name:sub(1, 12)
            end
            if XQFunction.isStrNil(name) then
                name = mac
            else
                local iden = XQEquipment.identifyDevice(mac, name)
                if (iden["type"].c == 2 and iden["type"].p == 6)
                    or (iden["type"].c == 3 and iden["type"].p == 2)
                    or (iden["type"].c == 3 and iden["type"].p == 7) then
                    return
                end
                if iden["type"].c ~= 0 then
                    name = iden["type"].n
                end
            end
            local payload = {
                ["type"] = 3,
                ["feed"] = res.f,
                ["push"] = res.p,
                ["mac"] = mac,
                ["name"] = name
            }
            _doPush(Json.encode(payload), PUSH_MESSAGE_TITLE[3], PUSH_MESSAGE_DESCRIPTION[3])
            XQDBUtil.saveDeviceInfo(mac,dhcpInfo.name,"","","")
            XQLog.log(6, "New Device Connect.", dhcpInfo)
        end
    end
    --[[
    WIFI_CLEAR = false
    local XQDBUtil = require("xiaoqiang.util.XQDBUtil")
    local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
    local device = XQDBUtil.fetchDeviceInfo(mac)
    local dhcpInfo = XQDeviceUtil.getDHCPDict()[mac] or {}
    if device then
        if device.mac and not XQFunction.isStrNil(device.nickname) then
            local res = _parserPushType(2)
            local payload = {
                ["type"] = 2,
                ["feed"] = res.f,
                ["push"] = res.p,
                ["mac"] = mac,
                ["name"] = device.nickname
            }
            _doPush(Json.encode(payload), PUSH_MESSAGE_TITLE[2], PUSH_MESSAGE_DESCRIPTION[2])
            XQLog.log(6, "Special Device Connect.", device)
        elseif device.mac then
            XQLog.log(6, "Device Connect.", device)
        else
            local res = _parserPushType(3)
            local name = dhcpInfo.name
            if name and string.lower(name):match("android-%S+") and #name > 12 then
                name = name:sub(1, 12)
            end
            if XQFunction.isStrNil(name) then
                name = mac
            end
            local payload = {
                ["type"] = 3,
                ["feed"] = res.f,
                ["push"] = res.p,
                ["mac"] = mac,
                ["name"] = name
            }
            XQDBUtil.saveDeviceInfo(mac,dhcpInfo.name,"","","")
            _doPush(Json.encode(payload), PUSH_MESSAGE_TITLE[3], PUSH_MESSAGE_DESCRIPTION[3])
            XQLog.log(6, "New Device Connect.", dhcpInfo)
        end
    end
    ]]--
end

function _hookWifiDisconnect(mac)
    if XQFunction.isStrNil(mac) then
        return
    else
        mac = XQFunction.macFormat(mac)
    end
    XQLog.log(6, "Device Disconnet:"..mac)
    local XQWifiUtil = require("xiaoqiang.util.XQWifiUtil")
    local count = #XQWifiUtil.getWifiConnectDeviceList(1) + #XQWifiUtil.getWifiConnectDeviceList(2)
    if count == 0 and not WIFI_CLEAR then
        local res = _parserPushType(4)
        local payload = {
            ["type"] = 4,
            ["feed"] = res.f,
            ["push"] = res.p
        }
        _doPush(Json.encode(payload), PUSH_MESSAGE_TITLE[4], PUSH_MESSAGE_DESCRIPTION[4])
        XQLog.log(6, "WiFi clear")
        WIFI_CLEAR = true
    end
end

function _hookAllDownloadFinished()
    XQLog.log(6, "All download finished")
    local res = _parserPushType(5)
    local payload = {
        ["type"] = 5,
        ["feed"] = res.f,
        ["push"] = res.p
    }
    _doPush(Json.encode(payload), PUSH_MESSAGE_TITLE[5], PUSH_MESSAGE_DESCRIPTION[5])
end

function _hookIntelligentScene(name,actions)
    local sname = name
    if XQFunction.isStrNil(sname) then
        sname = ""
    end
    XQLog.log(6, "Intelligent Scene:"..name.." finished!")
    local res = _parserPushType(6)
    local payload = {
        ["type"] = 6,
        ["feed"] = res.f,
        ["push"] = res.p,
        ["name"] = name,
        ["actions"] = actions
    }
    _doPush(Json.encode(payload), PUSH_MESSAGE_TITLE[6], PUSH_MESSAGE_DESCRIPTION[6])
end

function _hookDetectFinished(lan, wan)
    if lan and wan then
        XQLog.log(6, "network detect finished!")
        local res = _parserPushType(7)
        local payload = {
            ["type"] = 7,
            ["feed"] = res.f,
            ["push"] = res.p,
            ["lan"] = lan,
            ["wan"] = wan
        }
        _doPush(Json.encode(payload), PUSH_MESSAGE_TITLE[7], PUSH_MESSAGE_DESCRIPTION[7])
    end
end

function _hookCachecenterEvent(hitcount, timesaver)
    if hitcount and timesaver then
        XQLog.log(6, "cachecenter event!")
        local res = _parserPushType(13)
        local payload = {
            ["type"] = 13,
            ["feed"] = res.f,
            ["push"] = res.p,
            ["hitcount"] = hitcount,
            ["timesaver"] = timesaver
        }
        _doPush(Json.encode(payload), PUSH_MESSAGE_TITLE[8], PUSH_MESSAGE_DESCRIPTION[8])
    end
end

function _hookDownloadEvent(count)
    if tonumber(count) then
        XQLog.log(6, "download event!")
        local payload = {
            ["type"] = 17,
            ["count"] = tonumber(count)
        }
        _doPush(Json.encode(payload), "下载完成", "下载完成")
    end
end

function _hookUploadEvent(count)
    if tonumber(count) then
        XQLog.log(6, "upload event!")
        local payload = {
            ["type"] = 18,
            ["count"] = tonumber(count)
        }
        _doPush(Json.encode(payload), "上传完成", "上传完成")
    end
end

function _hookADFilterEvent(page, all)
    if tonumber(page) and tonumber(all) then
        XQLog.log(6, "upload event!")
        local payload = {
            ["type"] = 19,
            ["page"] = tonumber(page),
            ["all"] = tonumber(all)
        }
        _doPush(Json.encode(payload), "广告过滤", "广告过滤")
    end
end

function _hookDefault(data)
    XQLog.log(6, "Unknown Feed")
    local res = _parserPushType(999)
    local payload = {
        ["type"] = 999,
        ["feed"] = res.f,
        ["push"] = res.p,
        ["data"] = data
    }
    _doPush(Json.encode(payload), PUSH_DEFAULT_MESSAGE_TITLE, PUSH_DEFAULT_MESSAGE_DESCRIPTION)
end

function _hookNewRomVersionDetected(version)
    XQLog.log(6, "New ROM version detected")
    local res = _parserPushType(8)
    local routerName = XQPreference.get(XQConfigs.PREF_ROUTER_NAME, "")
    local _romChannel = XQSysUtil.getChannel()
    local payload = {
        ["type"] = 14,
        ["feed"] = res.f,
        ["push"] = res.p,
        ["name"] = routerName,
        ["version"] = version,
        ["channel"] = _romChannel
    }
    local title = string.format(PUSH_MESSAGE_TITLE[9], routerName)
    local romChannel = "开发版"
    if _romChannel == "current" then
        romChannel = "内测版"
    end
    if _romChannel == "release" then
        romChannel = "稳定版"
    end
    local description = string.format(PUSH_MESSAGE_DESCRIPTION[9], version, romChannel)
    _doPush(Json.encode(payload), title, description)
end

function push_request_lua(payload)
    local ptype = tonumber(payload.type)
    if ptype == 1 then
        _hookWifiConnect(payload.data.mac)
    elseif ptype == 2 then
        _hookWifiDisconnect(payload.data.mac)
    elseif ptype == 3 then
        _hookSysUpgraded()
    elseif ptype == 4 then
        _hookAllDownloadFinished()
    elseif ptype == 5 then
        _hookIntelligentScene(payload.data.name,payload.data.list)
    elseif ptype == 6 then
        _hookDetectFinished(payload.data.lan, payload.data.wan)
    elseif ptype == 7 then
        _hookCachecenterEvent(payload.data.hit_count, payload.data.timesaver)
    elseif ptype == 8 then
        _hookNewRomVersionDetected(payload.data.version)
    elseif ptype == 9 then
        _hookDownloadEvent(payload.data.count)
    elseif ptype == 10 then
        _hookUploadEvent(payload.data.count)
    elseif ptype == 11 then
        _hookADFilterEvent(payload.data.filter_page, payload.data.filter_all)
    else
        _hookDefault(payload.data)
    end
    return true
end

--
-- type:{1,2,3...}
-- data:{...}
--
function push_request(payload)
    if XQFunction.isStrNil(payload) then
        return false
    end
    XQLog.log(6,"Push request:",payload)
    local payload = Json.decode(payload)
    return push_request_lua(payload)
end
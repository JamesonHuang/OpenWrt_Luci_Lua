module ("xiaoqiang.XQEquipment", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")
local LuciUtil = require("luci.util")

--[[
    1,1  Broadlink Smart Control
    1,2  Broadlink Smart Switch
    2,1  Antscam
    3,4  MiTV
    3,5  MiBox
    3,7  Infrared
    3,8  MiWiFi-R1D
    3,9  MiWiFi-R1CM
    3,10 MiWiFi-R1CQ
    3,11 MiWiFi-Tiny
]]--
local RULES = {
    ["B4430D"] = {
        {
            ["from"] = "300000",
            ["to"] = "3FFFFF",
            ["company"] = "Broadlink Pty Ltd",
            ["icon"] = "device_list_intelligent.png",
            ["type"] = { ["c"] = 1, ["p"] = 1, ["n"] = "智能红外" },
            ["priority"] = 2
        },
        {
            ["from"] = "100000",
            ["to"] = "1FFFFF",
            ["company"] = "Broadlink Pty Ltd",
            ["icon"] = "device_list_intelligent_plugin.png",
            ["type"] = { ["c"] = 1, ["p"] = 2, ["n"] = "智能插座" },
            ["priority"] = 2
        }
    }
}

local NAME_RULES = {
    {
        ["rule"] = "^mitv",
        ["company"] = "Xiaomi",
        ["icon"] = "device_mitv.png",
        ["type"] = { ["c"] = 3, ["p"] = 4, ["n"] = "小米电视" },
        ["priority"] = 1
    },
    {
        ["rule"] = "^mibox",
        ["company"] = "Xiaomi",
        ["icon"] = "device_mibox.png",
        ["type"] = { ["c"] = 3, ["p"] = 5, ["n"] = "小米盒子" },
        ["priority"] = 1
    },
    {
        ["rule"] = "^miwifi%-r1d",
        ["company"] = "Xiaomi",
        ["icon"] = "device_miwifi_r1d.png",
        ["type"] = { ["c"] = 3, ["p"] = 8, ["n"] = "小米路由器" },
        ["priority"] = 1
    },
    {
        ["rule"] = "^miwifi%-r1cm",
        ["company"] = "Xiaomi",
        ["icon"] = "device_miwifi_r1c.png",
        ["type"] = { ["c"] = 3, ["p"] = 9, ["n"] = "小米路由器mini" },
        ["priority"] = 1
    },
    {
        ["rule"] = "^miwifi%-r1cq",
        ["company"] = "Xiaomi",
        ["icon"] = "device_miwifi_r1c.png",
        ["type"] = { ["c"] = 3, ["p"] = 10, ["n"] = "小米路由器mini2" },
        ["priority"] = 1
    },
    {
        ["rule"] = "^miwifi%-tiny",
        ["company"] = "Xiaomi",
        ["icon"] = "device_mirouter_wifi.png",
        ["type"] = { ["c"] = 3, ["p"] = 11, ["n"] = "小米随身WiFi" },
        ["priority"] = 1
    },
    {
        ["rule"] = "^broadlink_sp2",
        ["company"] = "Broadlink Pty Ltd",
        ["icon"] = "device_list_intelligent_plugin.png",
        ["type"] = { ["c"] = 1, ["p"] = 2, ["n"] = "智能插座" },
        ["priority"] = 1
    },
    {
        ["rule"] = "^broadlink_rm2",
        ["company"] = "Broadlink Pty Ltd",
        ["icon"] = "device_list_intelligent.png",
        ["type"] = { ["c"] = 1, ["p"] = 1, ["n"] = "智能红外" },
        ["priority"] = 1
    },
    {
        ["rule"] = "^antscam",
        ["company"] = "云蚁",
        ["icon"] = "device_list_intelligent_camera.png",
        ["type"] = { ["c"] = 2, ["p"] = 6, ["n"] = "小蚁智能摄像机" },
        ["priority"] = 1
    },
    {
        ["rule"] = "^xiaomi%.ir",
        ["company"] = "Xiaomi",
        ["icon"] = "device_list_lq.png",
        ["type"] = { ["c"] = 3, ["p"] = 7, ["n"] = "智能红外" },
        ["priority"] = 1
    },
    {
        ["rule"] = "chuangmi%-plug",
        ["company"] = "Chuangmi",
        ["icon"] = "device_list_intelligent_plugin.png",
        ["type"] = { ["c"] = 3, ["p"] = 2, ["n"] = "智能插座" },
        ["priority"] = 1
    }
}

function identifyDevice(mac, dhcpname)
    local maciden
    local dhcpiden
    if not XQFunction.isStrNil(mac) then
        mac = XQFunction.macFormat(mac)
        local key = string.upper(string.sub(string.gsub(mac,":",""),1,6))
        local tmac = mac:gsub(":","")
        local rules = RULES[key]
        if rules and type(rules) == "table" then
            tmac = LuciUtil.split(tmac,key)[2]
            if tmac then
                tmac = tonumber(tmac,16)
                for _, rule in ipairs(rules) do
                    local from = tonumber(rule.from,16)
                    local to = tonumber(rule.to,16)
                    if tmac >= from and tmac <= to then
                        maciden = {}
                        local dtype = {["c"] = 0, ["p"] = 0, ["n"] = ""}
                        local priority = 2
                        if rule["type"] then
                            dtype = rule["type"]
                        end
                        if rule.priority then
                            priority = tonumber(rule.priority)
                        end
                        maciden["name"] = rule.company
                        maciden["icon"] = rule.icon
                        maciden["type"] = dtype
                        maciden["priority"] = priority
                    end
                end
            end
        else
            local NixioFs = require("nixio.fs")
            if not NixioFs.access(XQConfigs.OUI_FILEPATH) then
                LuciUtil.exec("unzip -d /tmp".." "..XQConfigs.OUI_ZIP_FILEPATH)
            end
            if NixioFs.access(XQConfigs.OUI_FILEPATH) then
                key = string.upper(string.sub(string.gsub(mac,":","-"),1,8))
                local line = LuciUtil.trim(LuciUtil.exec("sed -n '/"..key.."/p' "..XQConfigs.OUI_FILEPATH))
                if not XQFunction.isStrNil(line) then
                    local company = LuciUtil.trim(LuciUtil.split(line,key)[2])
                    local icon = company:match("ICON:(%S+)")
                    maciden = {}
                    if icon then
                        maciden["name"] = company:match("(.+)ICON:%S+") or ""
                        maciden["icon"] = icon
                    else
                        maciden["name"] = company
                        maciden["icon"] = ""
                    end
                    maciden["type"] = {["c"] = 0, ["p"] = 0, ["n"] = ""}
                    maciden["priority"] = 2
                end
            end
        end
    end
    if not XQFunction.isStrNil(dhcpname) then
        dhcpname = string.lower(dhcpname)
        for _, rule in ipairs(NAME_RULES) do
            if dhcpname:match(rule.rule) then
                local dtype = {["c"] = 0, ["p"] = 0, ["n"] = ""}
                local priority = 2
                dhcpiden = {}
                if rule["type"] then
                    dtype = rule["type"]
                end
                if rule.priority then
                    priority = tonumber(rule.priority)
                end
                dhcpiden["name"] = rule.company
                dhcpiden["icon"] = rule.icon
                dhcpiden["type"] = dtype
                dhcpiden["priority"] = priority
                break
            end
        end
    end
    if maciden and dhcpiden then
        if maciden.priority < dhcpiden.priority then
            return maciden
        else
            return dhcpiden
        end
    elseif not maciden and not dhcpiden then
        return {["name"] = "", ["icon"] = "", ["type"] = {["c"] = 0, ["p"] = 0, ["n"] = ""}, ["priority"] = 2}
    else
        return maciden or dhcpiden
    end
end

module ("xiaoqiang.util.XQZigbeeUtil", package.seeall)

local JSON = require("luci.json")
local XQLog = require("xiaoqiang.XQLog")
local XQConfigs = require("xiaoqiang.common.XQConfigs")
local XQFunction = require("xiaoqiang.common.XQFunction")
local XQDeviceUtil = require("xiaoqiang.util.XQDeviceUtil")


function request_yeelink(payload)
    local XQCryptoUtil = require("xiaoqiang.util.XQCryptoUtil")
    local payload1 = XQCryptoUtil.binaryBase64Enc(payload)
    local cmd = XQConfigs.THRIFT_TUNNEL_TO_MIIO % payload1
    local LuciUtil = require("luci.util")
    return JSON.decode(LuciUtil.exec(cmd))
end

function get_zigbee_count()
    local device_list = request_yeelink("{\"command\":\"device_list\"}")
    if device_list == nil or device_list.list == nil then 
        return 0
    end
    return #device_list.list
end

function append_yeelink_list(list)
    local device_list = request_yeelink("{\"command\":\"device_list\"}")
    if device_list == nil or device_list.list == nil or list == nil then 
    	return
    end
    for _,item in ipairs(device_list.list) do
    	local it = {}
    	it.mac = item.mac
    	it.type = "zigbee"
    	it.ctype = 4 -- yeelink
    	it.ptype = 3 -- light
    	it.online = 0

    	it.origin_name = item.type
    	it.origin_info = item

		local company = {}
    	if item.type == "light" then
    		it.name = "智能灯泡"
    		company.icon = "device_list_intelligent_lamp.png"
    		company.name = "Yeelink"
    	end
    	it.company = company



    	local deviceInfoDict = XQDeviceUtil.getDeviceInfoFromDB()
    	local deviceInfo = deviceInfoDict[it.mac]
    	if deviceInfo ~= nil and not XQFunction.isStrNil(deviceInfo.nickname) then 
    		it.name = deviceInfoDict[it.mac].nickname
    	end

    	if not deviceInfo then
    	 	local XQDBUtil = require("xiaoqiang.util.XQDBUtil")
            XQDBUtil.saveDeviceInfo(it.mac,it.origin_name,"","","")
        end

    	table.insert(list,it)
    end
end
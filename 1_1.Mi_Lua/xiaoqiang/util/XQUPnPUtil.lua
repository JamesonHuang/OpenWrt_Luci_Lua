module ("xiaoqiang.util.XQUPnPUtil", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")

local LuciUtil = require("luci.util")

function getUPnPStatus()
    if os.execute(XQConfigs.UPNP_STATUS) == 0 then
        return true
    else
        return false
    end
end

function switchUPnP(enable)
    if enable then
        return os.execute(XQConfigs.UPNP_ENABLE)
    else
        return os.execute(XQConfigs.UPNP_DISABLE)
    end
end

function getUPnPList()
    if getUPnPStatus() then
        local upnpLease = LuciUtil.exec(XQConfigs.UPNP_LEASE_FILE)
        if upnpLease then
            upnpLease = LuciUtil.trim(upnpLease)
            local upnpFile = io.open(upnpLease,"r")
            if upnpFile then
                local upnpList = {}
                for line in upnpFile:lines() do
                    if not XQFunction.isStrNil(line) then
                        local item = {}
                        local info = LuciUtil.split(line,":")
                        if #info == 6 then
                            item["protocol"] = info[1]
                            item["rport"] = info[2]
                            item["ip"] = info[3]
                            item["cport"] = info[4]
                            item["time"] = info[5]
                            if info[6] == "(null)" then
                                item["name"] = "未知程序"
                            else
                                item["name"] = info[6]
                            end
                            table.insert(upnpList,item)
                        end
                    end
                end
                upnpFile:close()
                return upnpList
            end
        end
    end
    return nil
end
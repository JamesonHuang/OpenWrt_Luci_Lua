module ("xiaoqiang.util.XQHttpUtil", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")
local XQLog = require("xiaoqiang.XQLog")

local Http = require("socket.http")
local Https = require("ssl.https")
local Ltn12 = require("luci.ltn12")

function httpGetRequest(url, paramStr, cookies)
    local header = {}
    local cookieStr
    local httpHandler
    if cookies and type(cookies) == "table" then
        cookieStr = ""
        for key,value in pairs(cookies) do
            cookieStr = cookieStr..key.."="..value..";path=/;domain=.xiaomi.com;"
        end
        header["Cookie"] = cookieStr
    end
    if url:match("^https://") then
        httpHandler = Https
    else
        httpHandler = Http
    end

    local result = {
        code = "",
        headers = "",
        status = "",
        res = ""
    }
    local res, code, headers, status
    if XQFunction.isStrNil(cookieStr) then
        if XQFunction.isStrNil(paramStr) then
            res, code, headers, status = httpHandler.request(url)
        else
            res, code, headers, status = httpHandler.request(url, paramStr)
        end
    else
        if not XQFunction.isStrNil(paramStr) then
            local tmpUrl = url..paramStr
            if tmpUrl:match("?") then
                url = tmpUrl
            else
                url = url.."?"..paramStr
            end
        end
        local t = {}
        res, code, headers, status = httpHandler.request{
            url = url,
            sink = Ltn12.sink.table(t),
            headers = header
        }
        res = table.concat(t)
    end
    result.code = code or ""
    result.headers = headers or ""
    result.status = status or ""
    result.res = res or ""
    XQLog.log(7,result)
    return result
end

function httpPostRequest(url, paramStr, cookies)
    local header = {}
    local cookieStr
    local httpHandler
    if cookies and type(cookies) == "table" then
        cookieStr = ""
        for key,value in pairs(cookies) do
            cookieStr = cookieStr..key.."="..value..";path=/;domain=.xiaomi.com;"
        end
        header["Cookie"] = cookieStr
    end
    header["Content-type"] = "application/x-www-form-urlencoded"
    header["Content-length"] = string.len(paramStr)

    if url:match("^https://") then
        httpHandler = Https
    else
        httpHandler = Http
    end

    local result = {
        code = "",
        headers = "",
        status = "",
        res = ""
    }
    local t = {}
    local res, code, headers, status = httpHandler.request{
        url = url,
        method = "POST",
        source = Ltn12.source.string(paramStr),
        sink = Ltn12.sink.table(t),
        headers = header
    }
    res = table.concat(t)
    result.code = code or ""
    result.headers = headers or ""
    result.status = status or ""
    result.res = res or ""
    XQLog.log(7,result)
    return result
end


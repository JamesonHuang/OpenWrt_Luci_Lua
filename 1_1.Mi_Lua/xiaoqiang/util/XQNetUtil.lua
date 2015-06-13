module ("xiaoqiang.util.XQNetUtil", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")

local LuciProtocol = require("luci.http.protocol")
local XQHttpUtil = require("xiaoqiang.util.XQHttpUtil")

local DEFAULT_TOKEN = "8007236f-a2d6-4847-ac83-c49395ad6d65"
local V3_TOKEN

function getToken()
    return V3_TOKEN
end

function getMacAddr()
    local XQLanWanUtil = require("xiaoqiang.util.XQLanWanUtil")
    return XQLanWanUtil.getDefaultMacAddress()
end

function getSN()
    local LuciUtil = require("luci.util")
    local sn = LuciUtil.exec(XQConfigs.GET_NVRAM_SN)
    if XQFunction.isStrNil(sn) then
        return nil
    else
        sn = LuciUtil.trim(sn)
    end
    return sn
end

function getUserAgent()
    local sn = getSN() or ""
    return "miwifi-"..sn
end

function cryptUrl(serverUrl, subUrl, params, salt)
    local XQCryptoUtil = require("xiaoqiang.util.XQCryptoUtil")
    if serverUrl == nil or params == nil then
        return nil
    end
    local time = XQFunction.getTime()
    table.insert(params,{"time",time})
    table.sort(params, function(a, b) return a[1] < b[1] end)
    local str = ""
    table.foreach(params, function(k, v) str = str..v[1].."="..v[2].."&" end)
    if salt ~= nil and salt ~= "" then
        str = str .. salt
    end
    local md5 = XQCryptoUtil.md5Base64Str(str)
    local token = getToken()
    local isV2 = string.find(serverUrl..subUrl,"/v2/") ~= nil or string.find(serverUrl..subUrl,"/rs/") ~= nil
    if XQFunction.isStrNil(token) or isV2 then
        token = DEFAULT_TOKEN
    end

    local url = ""
    if string.find(serverUrl..subUrl,"?") == nil then
        url = serverUrl..subUrl.."?s="..md5.."&time="..time.."&token="..LuciProtocol.urlencode(token)
    else
        url = serverUrl..subUrl.."&s="..md5.."&time="..time.."&token="..LuciProtocol.urlencode(token)
    end

    return url
end

--
-- HBase
--
local HBASE_LOG_UPLOAD_URL = "https://data.gorouter.info/xiaoqiang_log/"
local HBASE_CONFIG_UPLOAD_URL = "https://data.gorouter.info/xiaoqiang_config/"
local SUB_KEY = "false-row-key"
local CURL_CMD = "curl -k -i -f -X PUT %s%s -H \"Content-Type: application/json\" --data @%s 2>/dev/null"
local CONFIG_KEY = "Qzo="

local LOG_TYPE = {
    ["M"] = "TTo=",
    ["B"] = "Qjo=",
    ["X"] = "WDo=",
    ["Y"] = "WTo=",
    ["Z"] = "Wjo="
}

--[[
    M:Network detection log
    B:System log
    X:Not use
    Y:Not use
    Z:Not use
]]--
function uploadLogFile(logfile,logtype)
    local Mime = require("mime")
    local LuciJson = require("json")
    local uuid = getMacAddr()
    if uuid == nil then
        return false
    end
    local logKey = LOG_TYPE[logtype]
    if XQFunction.isStrNil(logKey) then
        return false
    end
    local filebase64str = luci.util.exec("/usr/bin/base64 "..logfile)
    local timestamp = string.format("%012d",os.time())
    local key = Mime.b64(uuid.."-"..timestamp)
    local blob = {["column"]=logKey, ["$"]=string.gsub(filebase64str,"\n","")}
    local cell = {blob}
    local item = {["key"]=key,["Cell"]=cell}
    local data = {["Row"]=item}
    local dataStr = LuciJson.encode(data)
    local logJsonFile = io.open(XQConfigs.XQ_LOG_JSON_FILEPATH,"w")
    if logJsonFile then
        logJsonFile:write(dataStr)
        logJsonFile:close()
    end
    local command = string.format(CURL_CMD,HBASE_LOG_UPLOAD_URL,SUB_KEY,XQConfigs.XQ_LOG_JSON_FILEPATH)
    local result = luci.util.exec(command)
    if result == nil or result == "" then
        return false
    else
        if string.find(result,"OK") ~= nil then
            return true
        else
            return false
        end
    end
end

function uploadConfigFile(configfile)
    local Mime = require("mime")
    local LuciJson = require("json")
    local uuid = getMacAddr()
    if uuid == nil then
        return false
    end
    local filebase64str = luci.util.exec("/usr/bin/base64 "..configfile)
    local key = Mime.b64(uuid)
    local config = {["column"]=CONFIG_KEY, ["$"]=string.gsub(filebase64str,"\n","")}
    local cell = {config}
    local item = {["key"]=key,["Cell"]=cell}
    local data = {["Row"]=item}
    local dataStr = LuciJson.encode(data)
    local configJsonFile = io.open(XQConfigs.XQ_CONFIG_JSON_FILEPATH,"w")
    if configJsonFile then
        configJsonFile:write(dataStr)
        configJsonFile:close()
    end
    local command = string.format(CURL_CMD,HBASE_CONFIG_UPLOAD_URL,SUB_KEY,XQConfigs.XQ_CONFIG_JSON_FILEPATH)
    local result = luci.util.exec(command)
    if result == nil or result == "" then
        return false
    else
        if string.find(result,"OK") ~= nil then
            return true
        else
            return false
        end
    end
end

--[[
@param configfile: save path
unzip: invalid option -- t
]]--
function getConfigFile(configfile)
    local Xml = require("slaxdom")
    local SSLHttps = require("ssl.https")
    local XQCryptoUtil = require("xiaoqiang.util.XQCryptoUtil")
    local uuid = getMacAddr()
    if uuid == nil then
        return false
    end
    local url = HBASE_CONFIG_UPLOAD_URL..uuid
    local xmlstr = SSLHttps.request(url)
    if xmlstr == nil or xmlstr == "" then
        return false
    end
    if string.find(xmlstr,"html") ~= nil then
        return false
    end
    local obj = Xml:dom(xmlstr)
    local arr = obj.root.el[1].el
    if #arr == 0 then
        return false
    end
    local record = arr[1].kids[1].value
    local decstr = XQCryptoUtil.binaryBase64Dec(record)
    fd = io.open(configfile, "w")
    fd:write(decstr)
    fd:close()
    local check = luci.util.exec("unzip -o "..configfile)
    luci.util.exec("rm -rf /tmp/etc/")
    if string.find(check,"invalid") ~= nil or string.find(check,"inflate error") ~= nil then
        return false
    end
    return true
end

--
-- Xiaomi V3
--
local ACCOUNT_DOMAIN_ONLINE = "https://account.xiaomi.com/"
local ACCOUNT_DOMAIN_STAGING = "http://account.preview.n.xiaomi.net/"
local PASSPORT_LOGIN_PWD_URL = "pass/serviceLoginAuth"
local PASSPORT_LOGIN_PASSTOKEN_URL = "pass/serviceLogin?sid=xiaoqiang"

function xiaomiLogin(username,password)
    local LuciJson = require("json")
    local XQLog = require("xiaoqiang.XQLog")
    local XQDBUtil = require("xiaoqiang.util.XQDBUtil")
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local XQCryptoUtil = require("xiaoqiang.util.XQCryptoUtil")
    local userId,nonce,passToken,ssecurity,psecurity,location,domain
    --
    -- step1 get passtoken security and nonce /https
    --
    local params = {
        {"user", username},
        {"hash", string.upper(password)},
        {"sid", "xiaoqiang"},
        {"deviceId", getSN() or ""}
    }
    local url
    if XQConfigs.SERVER_CONFIG == 1 then
        url = ACCOUNT_DOMAIN_STAGING..PASSPORT_LOGIN_PWD_URL
    else
        url = ACCOUNT_DOMAIN_ONLINE..PASSPORT_LOGIN_PWD_URL
    end
    local data = ""
    table.foreach(params, function(k, v) data = data..v[1].."="..v[2].."&" end)
    local step1 = XQHttpUtil.httpGetRequest(url,string.sub(data,1,-2))
    if step1.code == 302 then
        local extensionPragma = step1.headers["extension-pragma"]
        local setCookie = step1.headers["set-cookie"]
        local extensionJson = LuciJson.decode(extensionPragma)

        location = step1.headers["location"]
        userId = setCookie:match('userId=(%d+);')
        passToken = setCookie:match('passToken=(%S+);')
        domain = setCookie:match('domain=(%S+);')
        nonce = extensionPragma:match('%S+\"nonce\":(%d+),%S+')
        ssecurity = extensionJson["ssecurity"]
        psecurity = extensionJson["psecurity"]
        XQLog.log(7,"XiaomiLogin Step1 Succeed:",step1)

        --
        --  step2 get xiaoqiang server token
        --
        local tobeSign = "nonce="..nonce.."&"..ssecurity
        local sign = XQCryptoUtil.binaryBase64Enc(XQCryptoUtil.sha1Binary(tobeSign))
        local queryStr = LuciProtocol.xq_urlencode_params({
            ["uuid"] = userId,
            ["clientSign"] = sign
        })
        local step2url = location.."&"..queryStr
        local step2 = XQHttpUtil.httpGetRequest(step2url)
        local stoken
        if step2.code == 200 and type(step2.headers) == "table" then
            local xqcookie = step2.headers["set-cookie"]
            if xqcookie then
                stoken = xqcookie:match('serviceToken=(%S+);')
            end
        elseif step2.code == 401 then
            XQLog.log(3,"XiaomiLogin Step2 401 Failed:", step2url, step2)
            return {code = 2}
        end
        local sid = ssecurity
        if not XQFunction.isStrNil(userId) and
            not XQFunction.isStrNil(passToken) and
            not XQFunction.isStrNil(stoken) and
            not XQFunction.isStrNil(sid) and
            not XQFunction.isStrNil(ssecurity) then
            XQLog.log(7,"XiaomiLogin Step2 succeed:"..userId)
            XQDBUtil.savePassport(userId,passToken,stoken,sid,ssecurity)
            local result = {
                code = 0,
                uuid = userId,
                token = passToken,
                stoken = stoken,
                sid = sid,
                ssecurity = ssecurity
            }
            return result
        else
            XQLog.log(3,"XiaomiLogin Step2 Failed:",{
                step2url = step2url,
                userId = userId or "",
                passToken = passToken or "",
                ssecurity = ssecurity or ""
            })
            return {code = 2}
        end
    elseif step1.code == 200 then
        XQLog.log(3,"XiaomiLogin Step1 Username/Password Error:", params, step1)
        return {code = 1}
    else
        XQLog.log(3,"XiaomiLogin Step1 Service Unreachable:", params, step1)
        return {code = 3}
    end
end

function getPassport(uuid)
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local XQDBUtil = require("xiaoqiang.util.XQDBUtil")
    if XQFunction.isStrNil(uuid) then
        uuid = XQSysUtil.getBindUUID()
    end
    if XQFunction.isStrNil(uuid) then
        return false
    end
    local passport = XQDBUtil.fetchPassport(uuid)[1]
    if not passport then
        return false
    end
    if XQFunction.isStrNil(passport.token) then
        return false
    end
    V3_TOKEN = passport.token
    return passport
end

function generateOrigIconUrl(iconUrl)
    if XQFunction.isStrNil(iconUrl) then
        return ""
    else
        return string.gsub(iconUrl,".jpg","_150.jpg")
    end
end

local GET_USER_INFO = "http://api.account.xiaomi.com/pass/usersCard?ids="
function getUserInfo(uuid)
    local LuciJson = require("json")
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    if XQFunction.isStrNil(uuid) then
        uuid = XQSysUtil.getBindUUID()
        if XQFunction.isStrNil(uuid) then
            return false
        end
    end
    local result = XQHttpUtil.httpGetRequest(GET_USER_INFO..uuid)
    if result.code ~= 200 then
        return false
    end
    local resultJson = LuciJson.decode(result.res)
    if resultJson then
        if string.upper(resultJson.result) == "OK" then
            local data = resultJson.data.list
            if data[1] then
                local userInfo = {}
                userInfo["aliasNick"] = data[1].aliasNick or ""
                userInfo["miliaoNick"] = data[1].miliaoNick or ""
                userInfo["userId"] = data[1].userId or ""
                userInfo["miliaoIcon"] = data[1].miliaoIcon or ""
                userInfo["miliaoIconOrig"] = generateOrigIconUrl(userInfo.miliaoIcon)
                XQSysUtil.setBindUserInfo(userInfo)
                return userInfo
            end
        end
    end
    return false
end

--
-- XiaoQiang
--
local XIAOQIANG_SERVER = XQConfigs.SERVER_CONFIG_ONLINE_URL
if XQConfigs.SERVER_CONFIG == 1 then
    XIAOQIANG_SERVER = XQConfigs.SERVER_CONFIG_STAGING_URL
elseif XQConfigs.SERVER_CONFIG == 2 then
    XIAOQIANG_SERVER = XQConfigs.SERVER_CONFIG_PREVIEW_URL
end
-- GET
local XIAOQIANG_UPGRADE = "/rs/grayupgrade"
local XIAOQIANG_RECOVERY_UPGRADE = "/rs/grayupgrade/recovery"
local XIAOQIANG_DEVICELIST = "/s/admin/deviceList"
local XIAOQIANG_ADMINLIST = "/s/device/adminList"
-- POST
local XIAOQIANG_REGISTER = "/s/register"
local XIAOQIANG_PROMOTE = "/s/admin/promote"
local XIAOQIANG_DISMISS = "/s/admin/dismiss"
local XIAOQIANG_PLUGIN_ENABLE = "/s/plugin/enable"
local XIAOQIANG_PLUGIN_DISABLE = "/s/plugin/disable"
local XIAOQIANG_PLUGIN_LIST = "/s/plugin/list"
local XIAOQIANG_PLUGIN_DETAIL = "/s/plugin/detail"
local XIAOQIANG_UPDATE_DEVICENAME = "/s/device/name"

function getDeviceId()
    local LuciUtil = require("luci.util")
    local deviceId = LuciUtil.exec(XQConfigs.XQ_DEVICE_ID)
    if XQFunction.isStrNil(deviceId) then
        deviceId = ""
    end
    return LuciUtil.trim(deviceId)
end

function checkUpgrade()
    local LuciJson = require("json")
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local XQPreference = require("xiaoqiang.XQPreference")
    local XQCryptoUtil = require("xiaoqiang.util.XQCryptoUtil")
    local misc = XQSysUtil.getMiscHardwareInfo()
    local isrecovery = misc.recovery == 1 and true or false
    local params = {}
    if isrecovery then
        local configs = XQSysUtil.getNvramConfigs()
        params = {
            {"deviceID", ""},
            {"rom", configs.rom_ver},
            {"hardware", XQSysUtil.getHardware()},
            {"cfe", configs.uboot},
            {"linux", configs.linux},
            {"ramfs", configs.ramfs},
            {"sqafs", configs.sqafs},
            {"rootfs", configs.rootfs},
            {"channel", configs.rom_channel},
            {"serialNumber", XQFunction.nvramGet("SN", "")}
        }
    else
        params = {
            {"deviceID", getDeviceId()},
            {"rom", XQSysUtil.getRomVersion()},
            {"hardware", XQSysUtil.getHardware()},
            {"cfe", XQSysUtil.getCFEVersion()},
            {"linux", XQSysUtil.getKernelVersion()},
            {"ramfs", XQSysUtil.getRamFsVersion()},
            {"sqafs", XQSysUtil.getSqaFsVersion()},
            {"rootfs", XQSysUtil.getRootFsVersion()},
            {"channel", XQSysUtil.getChannel()},
            {"serialNumber", XQFunction.thrift_to_mqtt_get_sn()}
        }
    end
    local query = {}
    table.foreach(params, function(k, v) query[v[1]] = v[2] end)
    local queryString = LuciProtocol.urlencode_params(query)
    local subUrl = (isrecovery and XIAOQIANG_RECOVERY_UPGRADE or XIAOQIANG_UPGRADE).."?"..queryString
    local requestUrl = cryptUrl(XIAOQIANG_SERVER, subUrl, params, DEFAULT_TOKEN)
    local response = XQHttpUtil.httpGetRequest(requestUrl)
    if response.code ~= 200 then
        return false
    end
    local resultjson = LuciJson.decode(response.res)
    if not resultjson then
        return false
    end
    if tonumber(resultjson["code"]) == 0 then
        local result = {}
        if resultjson.data and resultjson.data.link then
            local changeLog = XQFunction.parseEnter2br(luci.util.trim(resultjson.data.description))
            local weight = tonumber(resultjson.data.weight)
            result["needUpdate"] = 1
            result["downloadUrl"] = resultjson.data.link
            result["fullHash"] = resultjson.data.hash
            result["fileSize"] = resultjson.data.size
            result["version"] = resultjson.data.toVersion
            result["weight"] = weight or 1
            result["changelogUrl"] = resultjson.data.changelogUrl
            result["changeLog"] = changeLog
        else
            result["needUpdate"] = 0
            result["version"] = XQSysUtil.getRomVersion()
            result["changeLog"] = XQFunction.parseEnter2br(XQSysUtil.getChangeLog())
        end
        return result
    else
        return false
    end
end

function generateSignature(method,url,params,security)
    local XQCryptoUtil = require("xiaoqiang.util.XQCryptoUtil")
    local str = ""
    if params and #params > 0 then
        table.sort(params, function(a, b) return a[1] < b[1] end)
        table.foreach(params, function(k, v) str = str..v[1].."="..v[2].."&" end)
    end
    str = str..security
    if not XQFunction.isStrNil(url) then
        str = url.."&"..str
    end
    if not XQFunction.isStrNil(method) then
        str = string.upper(method).."&"..str
    end
    return XQCryptoUtil.hash4SHA1(str)
end

function doRequest(method,url,param,uuid)
    local LuciJson = require("json")
    local XQCrypto = require("xqcrypto")
    local LuciUtil = require("luci.util")
    local XQLog = require("xiaoqiang.XQLog")
    local XQCryptoUtil = require("xiaoqiang.util.XQCryptoUtil")
    local passport = getPassport(uuid)
    method = string.upper(method)
    if not passport then
        XQLog.log(3,"XQRequest: Passport missing "..url)
        return false
    end
    local nonce = XQCrypto.generateNonce()
    local cookies = {}
    local encrypt = {}
    local plain = {}
    local params = {}
    cookies["userId"] = passport.uuid
    cookies["serviceToken"] = passport.stoken
    local sessionSecurity = XQCrypto.generateSessionSecurity(nonce,passport.ssecurity)
    local paramStr = ""
    if param and type(param) == "table" then
        table.sort(param, function(a, b) return a[1] < b[1] end)
        for _,item in ipairs(param) do
            table.insert(plain,item)
        end
    else
        param = {}
    end
    local rc4HashPre = generateSignature(method,url,plain,sessionSecurity)
    table.insert(param,{"rc4_hash__",rc4HashPre})
    table.sort(param, function(a, b) return a[1] < b[1] end)
    for _,item in ipairs(param) do
        paramStr = paramStr..item[2]..";;"
    end
    local paramEncStr = XQCrypto.encryptParams(sessionSecurity,string.sub(paramStr,1,-3))
    local paramsEnc = LuciUtil.split(paramEncStr,";;")
    if paramsEnc and #paramsEnc > 0 then
        for i,item in ipairs(param) do
            table.insert(encrypt,{item[1],paramsEnc[i]})
            table.insert(params,{item[1],paramsEnc[i]})
        end
    end
    local rc4Hash = ""
    local signature = generateSignature(method,url,encrypt,sessionSecurity)
    for _,item in ipairs(encrypt) do
        if item[1] == "rc4_hash__" then
            rc4Hash = item[2]
        end
    end
    table.insert(params,{"signature",signature})
    table.insert(params,{"_nonce",nonce})
    table.insert(params,{"rc4_hash__",rc4Hash})
    local query = {}
    table.foreach(params, function(k, v) query[v[1]] = v[2] end)
    local queryString = LuciProtocol.xq_urlencode_params(query)
    local response
    if method == "GET" then
        response = XQHttpUtil.httpGetRequest(XIAOQIANG_SERVER..url, queryString, cookies)
    elseif method == "POST" then
        response = XQHttpUtil.httpPostRequest(XIAOQIANG_SERVER..url, queryString, cookies)
    end
    if response.code == 200 then
        local jsonResult = XQCrypto.decryptResult(sessionSecurity,response.res)
        if not XQFunction.isStrNil(jsonResult) then
            jsonResult = string.gsub(jsonResult,"u201c","\"")
            jsonResult = string.gsub(jsonResult,"u201d","\"")
            XQLog.log(7,"XQRequest succeed:"..jsonResult)
            return LuciJson.decode(jsonResult)
        end
    elseif response.code == 401 then
        XQLog.log(3,"XQRequest 401:"..XIAOQIANG_SERVER..url,"QueryString:"..queryString,params)
        return {code = 401}
    end
    XQLog.log(3,"XQRequest Failed:"..XIAOQIANG_SERVER..url,"QueryString:"..queryString,params)
    return false
end

function routerRegister(uuid)
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local param = {}
    local d = XQFunction.thrift_to_mqtt_identify_device()
    if XQFunction.isStrNil(d) then
        return false
    end
    local deviceName = XQSysUtil.getRouterName()
    table.insert(param,{"d",d})
    table.insert(param,{"deviceName",deviceName})
    local result = doRequest("POST",XIAOQIANG_REGISTER,param,uuid)
    return result
end

function promoteAccount(uuid,account)
    local param = {}
    local deviceID = getDeviceId()
    if XQFunction.isStrNil(deviceID) or XQFunction.isStrNil(account) then
        return false
    end
    table.insert(param,{"account",account})
    table.insert(param,{"deviceID",deviceID})
    local result = doRequest("POST",XIAOQIANG_PROMOTE,param,uuid)
    return result
end

function dismissAccount(uuid,account)
    local param = {}
    local deviceID = getDeviceId()
    if XQFunction.isStrNil(deviceID) or XQFunction.isStrNil(account) then
        return false
    end
    table.insert(param,{"account",account})
    table.insert(param,{"deviceID",deviceID})
    local result = doRequest("POST",XIAOQIANG_DISMISS,param,uuid)
    return result
end

function getAdminList(uuid)
    local param = {}
    local deviceID = getDeviceId()
    if XQFunction.isStrNil(deviceID) then
        return false
    end
    table.insert(param,{"deviceID",deviceID})
    local result = doRequest("GET",XIAOQIANG_ADMINLIST,param,uuid)
    return result
end

function getUserDeviceList(uuid)
    local result = doRequest("GET",XIAOQIANG_DEVICELIST,nil,uuid)
    return result
end

function pluginEnable(uuid,pluginId)
    local param = {}
    local deviceID = getDeviceId()
    if XQFunction.isStrNil(deviceID) or XQFunction.isStrNil(pluginId) then
        return false
    end
    table.insert(param,{"deviceID",deviceID})
    table.insert(param,{"pluginID",pluginId})
    local result = doRequest("POST",XIAOQIANG_PLUGIN_ENABLE,param,uuid)
    return result
end

function pluginDisable(uuid,pluginId)
    local param = {}
    local deviceID = getDeviceId()
    if XQFunction.isStrNil(deviceID) or XQFunction.isStrNil(pluginId) then
        return false
    end
    table.insert(param,{"deviceID",deviceID})
    table.insert(param,{"pluginID",pluginId})
    local result = doRequest("POST",XIAOQIANG_PLUGIN_DISABLE,param,uuid)
    return result
end

function pluginList(uuid)
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local param = {}
    local deviceID = getDeviceId()
    local romVersion = XQSysUtil.getRomVersion()
    local hardwareNum = XQSysUtil.getHardware()
    if XQFunction.isStrNil(deviceID) then
        return false
    end
    table.insert(param,{"deviceID",deviceID})
    table.insert(param,{"rom",romVersion})
    table.insert(param,{"hardware",hardwareNum})
    local result = doRequest("GET",XIAOQIANG_PLUGIN_LIST,param,uuid)
    return result
end

function pluginDetail(uuid,pluginId)
    local param = {}
    local deviceID = getDeviceId()
    if XQFunction.isStrNil(deviceID) or XQFunction.isStrNil(pluginId) then
        return false
    end
    table.insert(param,{"pluginID",pluginId})
    local result = doRequest("GET",XIAOQIANG_PLUGIN_DETAIL,param,uuid)
    return result
end

function updateDeviceName(uuid,deviceName)
    local param = {}
    local deviceID = getDeviceId()
    if XQFunction.isStrNil(deviceID) or XQFunction.isStrNil(deviceName) then
        return false
    end
    table.insert(param,{"deviceID",deviceID})
    table.insert(param,{"deviceName",deviceName})
    local result = doRequest("POST",XIAOQIANG_UPDATE_DEVICENAME,param,uuid)
    return result
end


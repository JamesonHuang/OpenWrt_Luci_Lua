module ("xiaoqiang.module.XQDDNS", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")

--- noip noip.com
--- oray 花生壳
local SERVICES = {
    ["noip"] = {
        ["service_name"] = "no-ip.com",
        ["ip_url"] = "http://[USERNAME]:[PASSWORD]@dynupdate.no-ip.com/nic/update?hostname=[DOMAIN]&myip=[IP]"
    },
    ["oray"] = {
        ["service_name"] = "oray.com",
        ["ip_url"] = "http://[USERNAME]:[PASSWORD]@ddns.oray.com:80/ph/update?hostname=[DOMAIN]&myip=[IP]"
    },
    ["pubyun"] = {
        ["service_name"] = "3322.org",
        ["ip_url"] = "http://[USERNAME]:[PASSWORD]@members.3322.net/dyndns/update?hostname=[DOMAIN]&myip=[IP]"
    },
    ["dyndnsorg"] = {
        ["service_name"] = "dyndns.org",
        ["ip_url"] = "http://[USERNAME]:[PASSWORD]@members.dyndns.org/nic/update?wildcard=ON&hostname=[DOMAIN]&myip=[IP]"
    },
    ["dyndnsfr"] = {
        ["service_name"] = "dyndns.fr",
        ["ip_url"] = "http://[DOMAIN]:[PASSWORD]@dyndns.dyndns.fr/update.php?hostname=[DOMAIN]&myip=[IP]"
    },
    ["dyndnspro"] = {
        ["service_name"] = "dyndnspro.com",
        ["ip_url"] = "http://[DOMAIN]:[PASSWORD]@dyndns.dyndnspro.com/update.php?hostname=[DOMAIN]&myip=[IP]"
    },
    ["dynamicdomain"] = {
        ["service_name"] = "dynamicdomain.net",
        ["ip_url"] = "http://[DOMAIN]:[PASSWORD]@dyndns.dynamicdomain.net/update.php?hostname=[DOMAIN]&myip=[IP]"
    },
    ["dyndnsit"] = {
        ["service_name"] = "dyndns.it",
        ["ip_url"] = "http://[USERNAME]:[PASSWORD]@dyndns.it/nic/update?hostname=[DOMAIN]&myip=[IP]"
    },
    ["duckdns"] = {
        ["service_name"] = "duckdns.org",
        ["ip_url"] = "http://www.duckdns.org/update?domains=[DOMAIN]&token=[PASSWORD]&ip=[IP]"
    },
    ["systemns"] = {
        ["service_name"] = "system-ns.com",
        ["ip_url"] = "http://system-ns.com/api?type=dynamic&domain=[DOMAIN]&command=set&token=[PASSWORD]&ip=[IP]"
    }
}

--- id
--- 1: noip
--- 2: oray 花生壳
--- 3: 公云
--- ...
local MAP = {
    "noip",
    "oray",
    "pubyun",
    "dyndnsorg",
    "dyndnsfr",
    "dyndnspro",
    "dynamicdomain",
    "dyndnsit",
    "duckdns",
    "systemns"
}

function _serverId(server)
    for id, ser in ipairs(MAP) do
        if ser == server then
            return id
        end
    end
    return false
end

function _ddnsRestart()
    return os.execute("/usr/sbin/ddnsd reload") == 0
end

--- server: noip/oray
--- enable: 0/1
function _saveConfig(server, enable, username, password, checkinterval, forceinterval, domain)
    local uci = require("luci.model.uci").cursor()
    local service = SERVICES[server]
    if service and username and password and domain and checkinterval and forceinterval then
        uci:foreach("ddns", "service",
            function(s)
                if s[".name"] ~= server and tonumber(s.enabled) == 1 then
                    uci:set("ddns", s[".name"], "enabled", 0)
                end
            end
        )
        local section = {
            ["enabled"] = enable,
            ["interface"] = "wan",
            ["service_name"] = service.service_name,
            ["force_interval"] = forceinterval,
            ["force_unit"] = "hours",
            ["check_interval"] = checkinterval,
            ["check_unit"] = "minutes",
            ["username"] = username,
            ["password"] = password,
            ["ip_source"] = "network",
            ["ip_url"] = service.ip_url,
            ["domain"] = domain
        }
        uci:section("ddns", "service", server, section)
        uci:commit("ddns")
        return true
    end
    return false
end

function _ddnsServerSwitch(server, enable)
    local uci = require("luci.model.uci").cursor()
    if XQFunction.isStrNil(server) then
        return false
    end
    uci:foreach("ddns", "service",
        function(s)
            if s[".name"] ~= server then
                if enable == 1 then
                    uci:set("ddns", s[".name"], "enabled", 0)
                end
            else
                uci:set("ddns", s[".name"], "enabled", enable)
            end
        end
    )
    uci:commit("ddns")
    if enable == 1 then
        return _ddnsRestart()
    else
        return true
    end
end

function ddnsInfo()
    local LuciJson = require("cjson")
    local LuciUtil = require("luci.util")
    local result = {
        ["on"] = 0,
        ["list"] = {}
    }
    local status = LuciUtil.exec("/usr/sbin/ddnsd status")
    if not XQFunction.isStrNil(status) then
        status = LuciJson.decode(status)
        if status.daemon == "on" then
            result.on = 1
        end
        for key, value in pairs(status) do
            if key ~= "deamon" then
                local id = _serverId(key)
                if id then
                    value.enabled = tonumber(value.enabled)
                    value.id = id
                    value.servicename = SERVICES[key].service_name
                    table.insert(result.list, value)
                end
            end
        end
    end
    return result
end

function ddnsSwitch(on)
    if on then
        os.execute("/usr/sbin/ddnsd start")
    else
        os.execute("/usr/sbin/ddnsd stop")
    end
end

function getDdns(id)
    if not tonumber(id) then
        return false
    end
    local uci = require("luci.model.uci").cursor()
    local server = MAP[tonumber(id)]
    local result = {}
    local ddns = uci:get_all("ddns", server)
    if ddns then
        result["username"] = ddns.username or ""
        result["password"] = ddns.password or ""
        result["forceinterval"] = tonumber(ddns.force_interval) or 0
        result["checkinterval"] = tonumber(ddns.check_interval) or 0
        result["domain"] = ddns.domain or ""
        result["enabled"] = tonumber(ddns.enabled) or 0
        return result
    end
    return false
end

function setDdns(id, enable, username, password, checkinterval, forceinterval, domain)
    if not tonumber(id) then
        return false
    end
    local server = MAP[tonumber(id)]
    if XQFunction.isStrNil(username)
        or XQFunction.isStrNil(password)
        or XQFunction.isStrNil(domain)
        or XQFunction.isStrNil(server) then
        return false
    end
    checkinterval = tonumber(checkinterval)
    forceinterval = tonumber(forceinterval)
    if not checkinterval or not forceinterval then
        return false
    end
    local denable = enable == 1 and 1 or 0
    if _saveConfig(server, denable, username, password, checkinterval, forceinterval, domain) then
        return _ddnsRestart()
    end
    return false
end

function editDdns(id, enable, username, password, checkinterval, forceinterval, domain)
    if not tonumber(id) then
        return false
    end
    local uci = require("luci.model.uci").cursor()
    local server = MAP[tonumber(id)]
    local ddns = uci:get_all("ddns", server)
    if ddns then
        if not XQFunction.isStrNil(username) and username ~= ddns.username then
            uci:set("ddns", server, "username", username)
        end
        if not XQFunction.isStrNil(password) and password ~= ddns.password then
            uci:set("ddns", server, "password", password)
        end
        if not XQFunction.isStrNil(domain) and domain ~= ddns.domain then
            uci:set("ddns", server, "domain", domain)
        end
        if tonumber(checkinterval) and tonumber(checkinterval) ~= tonumber(ddns.check_interval) then
            uci:set("ddns", server, "checkinterval", checkinterval)
        end
        if tonumber(forceinterval) and tonumber(forceinterval) ~= tonumber(ddns.force_interval) then
            uci:set("ddns", server, "forceinterval", forceinterval)
        end
        local enabled = enable == 1 and 1 or 0
        if enabled ~= tonumber(ddns.enabled) then
            uci:set("ddns", server, "enabled", enabled)
        end
        uci:commit("ddns")
        if enabled ~= 0 or ddns.enabled ~= 0 then
            _ddnsRestart()
        end
        return true
    end
    return false
end

function deleteDdns(id)
    if not tonumber(id) then
        return false
    end
    local server = MAP[tonumber(id)]
    if XQFunction.isStrNil(server) then
        return false
    end
    local uci = require("luci.model.uci").cursor()
    uci:delete("ddns", server)
    uci:commit("ddns")
    return true
end

--- id (1/2/3...)
--- on (true/false)
function ddnsServerSwitch(id, on)
    if id then
        return _ddnsServerSwitch(MAP[id], on and 1 or 0)
    end
    return false
end

function reload()
    return _ddnsRestart()
end
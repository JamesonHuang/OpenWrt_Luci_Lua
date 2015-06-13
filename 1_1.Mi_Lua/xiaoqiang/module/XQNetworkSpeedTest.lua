module ("xiaoqiang.module.XQNetworkSpeedTest", package.seeall)

local LuciFs = require("luci.fs")
local LuciSys = require("luci.sys")
local LuciUtil = require("luci.util")

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")

local DIR = "/tmp/"
-- Kbyte
local POST_FILESIZE = 512
-- Number of requests to perform
local REQUEST_TIMES = 40
-- Number of multiple requests to make at a time
local REQUEST_NUM = 4

local TIMELIMITE = 5
local TIMESTEP = 1
local AB_CMD = "/usr/bin/ab"
local DD_CMD = "/bin/dd"

local POST_URL = "http://netsp.master.qq.com/cgi-bin/netspeed"

function execl(command, times)
    local io = require("io")
    local pp   = io.popen(command)
    local line = ""
    local data = {}
    if times < 1 then
        return nil
    end
    while true do
        line = pp:read()
        if not XQFunction.isStrNil(line) then
            local speed = tonumber(line:match("tx:(%S+)"))
            if speed > 0 then
                table.insert(data, speed)
            else
                break
            end
        else
            break
        end
    end
    pp:close()
    if #data > 2 then
        return data[#data]
    else
        return execl(command, times - 1)
    end
end

function uploadSpeedTest()
    local postfile = DIR..LuciSys.uniqueid(8)..".dat"
    local pfcmd = string.format("%s if=/dev/zero of=%s bs=1k count=%d >/dev/null 2>&1", DD_CMD, postfile, POST_FILESIZE)
    os.execute(pfcmd)
    if postfile and LuciFs.access(postfile) then
        local cmd = string.format("%s -N -s %d -M %d -n %d -c %d -T 'multipart/form-data' -p %s '%s'",
            AB_CMD, TIMESTEP, TIMELIMITE, REQUEST_TIMES, REQUEST_NUM, postfile, POST_URL)
        local speed = execl(cmd, 2)
        if postfile and LuciFs.access(postfile) then
            LuciFs.unlink(postfile)
        end
        if speed then
            return speed
        else
            return nil
        end
    else
        local XQLog = require("xiaoqiang.XQLog")
        XQLog.log(6, "create postfile error")
        return nil
    end
end

function downloadSpeedTest()
    local result = {}
    local cmd = "/usr/bin/speedtest"
    for _, line in ipairs(LuciUtil.execl(cmd)) do
        if not XQFunction.isStrNil(line) then
            table.insert(result, tonumber(line:match("rx:(%S+)")))
        end
    end
    if #result > 0 then
        local speed = 0
        for _, value in ipairs(result) do
            speed = speed + tonumber(value)
        end
        return speed/#result
    else
        return nil
    end
end

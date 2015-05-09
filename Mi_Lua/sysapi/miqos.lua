#!/usr/bin/lua


-- local px =  require "Posix"
local socket= require 'socket'
local json= require 'json'

local cfg_host='127.0.0.1'
local cfg_port='1035'

module("sysapi.miqos", package.seeall)

function cmd(action)
    local con=assert(socket.connect(cfg_host,cfg_port))
    local s,err = con:send(action..'\n')
    -- logger(7,'send act: ' .. action)
    if err then
        logger(7, 'establish conn failed.')
        return json.decode('{"status":-1}')
    end

    local data=''
    while true do
        local line, err = con:receive()
        if not line then
            if err == 'closed' then
                con:close()
                return json.decode(data)
            else
                logger(7,'receive err: ' .. err)
                con:close()
                return json.decode('{"status":-1}')
            end
        else
            --logger(7,'receive line: ' .. line)
            data = data .. line
        end
    end
    con:close()
    return json.decode('{"status":-1}')
end

--[[
px.openlog("miqos","np",LOG_USER)

function logger(loglevel,msg)
    px.syslog(loglevel,msg)
end

function print_r(root,ind)
    local indent="    " .. ind

    for k,v in pairs(root) do
            if(type(v) == "table") then
                    print(indent .. k .. " = {")
                    print_r(v,indent)
                    print(indent .. "}")
            else
                    print(indent .. k .. "=" .. v)
            end
    end

end

function main()
    local data=''
    for i,v in ipairs(arg) do
        data = data .. ' ' .. v
    end
    
    local str=act(data)
    if str then
        print("{")
        print_r(json.decode(str),"")
        print("}")
    end
end

main()
--]]



local base = _G
local string = require("string")
local M = require "posix"

function M.timeradd (x,y)
  local sec, usec = 0, 0
  if x.sec then sec = sec + x.sec end
  if y.sec then sec = sec + y.sec end
  if x.usec then usec = usec + x.usec end
  if y.usec then usec = usec + y.usec end
  if usec > 1000000 then
    sec = sec + 1
    usec = usec - 1000000
  end

  return { sec = sec, usec = usec }
end


function M.timercmp (x, y)
  local x = { sec = x.sec or 0, usec = x.usec or 0 }
  local y = { sec = y.sec or 0, usec = y.usec or 0 }
  if x.sec ~= y.sec then
    return x.sec - y.sec
  else
    return x.usec - y.usec
  end
end


function M.timersub (x,y)
  local sec, usec = 0, 0
  if x.sec then sec = x.sec end
  if y.sec then sec = sec - y.sec end
  if x.usec then usec = x.usec end
  if y.usec then usec = usec - y.usec end
  if usec < 0 then
    sec = sec - 1
    usec = usec + 1000000
  end

  return { sec = sec, usec = usec }
end

function M.timesleep (x)
    local sec, nsec = 0, 0
    y = M.gettimeofday();
    if( M.timercmp(x, y) > 0 ) then
        sec = x.sec - y.sec
        nsec = (x.usec - y.usec) * 1000
        if nsec < 0 then
            sec = sec - 1
            nsec = nsec + 1000000000
        end
        M.nanosleep(sec, nsec)
    end
end

function M.strsplit(str, delim, maxNb)
    -- Eliminate bad cases...
    if string.find(str, delim) == nil then
        return { str }
    end
    if maxNb == nil or maxNb < 1 then
        maxNb = 0    -- No limit
    end
    local result = {}
    local pat = "(.-)" .. delim .. "()"
    local nb = 0
    local lastPos
    for part, pos in string.gfind(str, pat) do
        nb = nb + 1
        result[nb] = part
        lastPos = pos
        if nb == maxNb then break end
    end
    -- Handle the last field
    if nb ~= maxNb then
        result[nb + 1] = string.sub(str, lastPos)
    end
    return result
end

function M.var_dump(data, max_level, prefix)
    if type(prefix) ~= "string" then
        prefix = ""
    end
    if type(data) ~= "table" then
        print(prefix .. tostring(data))
    else
        print(data)
        if max_level ~= 0 then
            local prefix_next = prefix .. "    "
            print(prefix .. "{")
            for k,v in pairs(data) do
                io.stdout:write(prefix_next .. k .. " = ")
                if type(v) ~= "table" or (type(max_level) == "number" and max_level <= 1) then
                    print(v)
                else
                    if max_level == nil then
                        M.var_dump(v, nil, prefix_next)
                    else
                        M.var_dump(v, max_level - 1, prefix_next)
                    end
                end
            end
            print(prefix .. "}")
        end
    end
end


return M

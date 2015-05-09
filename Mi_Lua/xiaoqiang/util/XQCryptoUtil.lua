module ("xiaoqiang.util.XQCryptoUtil", package.seeall)

local b = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'

--base64 encoding
function binaryBase64Enc(data)
    return ((data:gsub('.', function(x)
        local r,b='',x:byte()
        for i=8,1,-1 do r=r..(b%2^i-b%2^(i-1)>0 and '1' or '0') end
        return r;
    end)..'0000'):gsub('%d%d%d?%d?%d?%d?', function(x)
        if (#x < 6) then return '' end
        local c=0
        for i=1,6 do c=c+(x:sub(i,i)=='1' and 2^(6-i) or 0) end
        return b:sub(c+1,c+1)
    end)..({ '', '==', '=' })[#data%3+1])
end

--base64 decoding
function binaryBase64Dec(data)
    data = string.gsub(data, '[^'..b..'=]', '')
    return (data:gsub('.', function(x)
        if (x == '=') then return '' end
        local r,f='',(b:find(x)-1)
        for i=6,1,-1 do r=r..(f%2^i-f%2^(i-1)>0 and '1' or '0') end
        return r;
    end):gsub('%d%d%d?%d?%d?%d?%d?%d?', function(x)
        if (#x ~= 8) then return '' end
        local c=0
        for i=1,8 do c=c+(x:sub(i,i)=='1' and 2^(8-i) or 0) end
        return string.char(c)
    end))
end

function md5File(file)
    local LuciUtil = require("luci.util")
    return LuciUtil.trim(LuciUtil.exec("/usr/bin/md5sum %s|/usr/bin/cut -d' ' -f1" % file))
end

function md5Str(str)
    local LuciUtil = require("luci.util")
    return LuciUtil.trim(LuciUtil.exec("/bin/echo -n %s|/usr/bin/md5sum|/usr/bin/cut -d' ' -f1" % str))
end

function sha256Str(str)
    local LuciUtil = require("luci.util")
    return LuciUtil.trim(LuciUtil.exec("/bin/echo -n %s|/usr/bin/sha256sum|/usr/bin/cut -d' ' -f1" % str))
end

function md5Base64Str(str)
    local Mime = require("mime")
    return md5Str(Mime.b64(str))
end

-- returns a hex string
function sha1(message)
    local SHA1 = require("sha1")
    return SHA1.sha1(message)
end

-- returns raw bytes
function sha1Binary(message)
    local SHA1 = require("sha1")
    return SHA1.sha1_binary(message)
end

function hash4SHA1(str)
    return binaryBase64Enc(sha1Binary(str))
end

function binToHex(s)
    return (s:gsub('(.)', function(c)
        return string.format('%02x', string.byte(c))
    end))
end

function hextobin(s)
    return (s:gsub('(%x%x)', function(hex)
        return string.char(tonumber(hex, 16))
    end))
end

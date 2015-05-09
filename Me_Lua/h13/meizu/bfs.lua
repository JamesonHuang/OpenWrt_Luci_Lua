module("meizu.bfs", package.seeall)
--API base functions

local cjson = require "cjson"
local dbfs  = require "meizu.dbfs"

function cal_str_md5(str)
	local md5 = ""
	local cmd = [[/bin/echo -n ']]..str
	cmd = cmd..[['|/usr/bin/md5sum|/usr/bin/cut -d" " -f1]]
	local fd = io.popen(cmd)
	local ln = fd:read("*l")
	if ln ~= nil then
		md5 = ln
	end
	fd:close()
	return md5
end

function strsplit(str, delim, maxNb)
    local result = {}
	if delim == nil then
		delim = '\n'
	end
    if string.find(str, delim) == nil then
        return { str }
    end
    if maxNb == nil or maxNb < 1 then
        maxNb = 0
    end
    local pat = "(.-)" .. delim .. "()"
    local nb = 0
    local lastPos
    for part, pos in string.gfind(str, pat) do
        nb = nb + 1
        result[nb] = part
        lastPos = pos
        if nb == maxNb then break end
    end
    if nb ~= maxNb then
        result[nb + 1] = string.sub(str, lastPos)
    end

    return result
end

function data_to_json(x)
	local buf = ""
	if  x == nil then
		return ""
	elseif  x == "" then
		return '""'
	elseif type(x) == "table" then
		local k, v
		if type(next(x)) == "number" then
			buf = buf.."[ "
			for k, v in ipairs(x) do
				buf = buf..data_to_json(v)
				if next(x, k) then
					buf = buf..", "
				end
			end
			buf = buf.." ]"
		else
			buf = buf.."{ "
			for k, v in pairs(x) do
				buf = buf..string.format("%q: " % k)
				buf = buf..data_to_json(v)
				if next(x, k) then
					buf = buf..", "
				end
			end
			buf = buf.." }"
		end
	elseif type(x) == "number" or type(x) == "boolean" then
		if (x ~= x) then
			buf = buf.."Number.NaN"
		else
			buf = buf..tostring(x)
		end
	else
		buf = buf..string.format('"%s"' % tostring(x):gsub('[%z\1-\31]', function(c) return '\\u%04x' % c:byte(1) end))
	end
	return buf
end

function exec_cmd_in_sh(command)
	local nio = require("nixio")
	require "MZLog".log(3, command)
    local pid = nio.fork()
    if pid > 0 then
        return
    elseif pid == 0 then
        nio.chdir("/")
        local null = nio.open("/dev/null", "w+")
        if null then
            nio.dup(null, nio.stderr)
            nio.dup(null, nio.stdout)
            nio.dup(null, nio.stdin)
            if null:fileno() > 2 then
                null:close()
            end
        end
        nio.exec("/bin/sh", "-c", command)
    end
end

--local get_https_data = function(url, data) return require("ssl.https").request(url, data) end
--return res, code, headers, status
function get_https_data(url, data)
	if data ~= nil then
		return require("ssl.https").request(url, data)
	else
		return require("ssl.https").request(url)
	end
end

function decodeURI(s)
	local s = string.gsub(s, '%%(%x%x)', function(h) return string.char(tonumber(h, 16)) end)
	return s
end

function encodeURI(s)
	local s = string.gsub(s, "([^%w%.%- ])", function(c) return string.format("%%%02X", string.byte(c)) end)
	return string.gsub(s, " ", "+")
end

function b64enc(data)
	local b='ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'
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

function b64dec(data)
	local b='ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'
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

function exec_reboot()
	--luci.sys.reboot()
    exec_cmd_in_sh("reboot")
end

function factory_reset()
	--exec_cmd_in_sh("killall dropbear uhttpd; sleep 1; mtd erase nvram; mtd -r erase rootfs_data")
	local result = {}
	result["result"] = true
	luci.http.write_json(result)
	exec_cmd_in_sh("killall dropbear uhttpd; sleep 1; /usr/bin/router_reset;/sbin/reboot -f")
end

function set_passwd()
	local result = {}
	local p1 = luci.http.formvalue("pwd1")
	local p2 = luci.http.formvalue("pwd2")
	local stat = nil
	if p1 ~= nil and luci.sys.user.checkpasswd("root", p1) then
		if p2 ~= nil then
			stat = luci.sys.user.setpasswd("root", p2)
			result["result"] = true
			luci.http.write_json(result)
		end
	end
	result["result"] = false
	luci.http.write_json(result)
end

function rts_get_access_token()
	local dbfs = require "meizu.dbfs"
	dbfs.init_access_token_table()
	local dbdata = dbfs.fetch_access_token()
	local token
	if #dbdata > 0 then
		for _, data in ipairs(dbdata) do
			local tokenFromdb = data.token
			local expireTimeFromdb = data.expireTime
			local nowTime = os.time()
			if expireTimeFromdb < nowTime then
				require "MZLog".log(3, "expireTime expire")
				local httpsData = https_get_access_token()
				token = httpsData.access_token
				dbfs.update_access_token(tokenFromdb, token, httpsData.expires_in + os.time() - 10)
			else
				require "MZLog".log(3, "token from db")
				token = tokenFromdb
			end
		end
	else
		local httpsData = https_get_access_token()
		token = httpsData.access_token
		require "MZLog".log(3, "token from https")
		dbfs.add_access_token(token, httpsData.expires_in + os.time() - 10)
	end
	return token
end

function https_get_access_token()
	local url = "https://api.meizu.com/oauth/token?scope=router_trust&device="
	local suffix = "&password=&grant_type=device_only&client_id=gkzyJzq4RPoaov3BamqsJgg&client_secret=yh9bdKurxxotCjrEvJOiumk2mrzhcyej"
	local SN = get_device_SN()
	url = url..SN..suffix
	local res, code, headers, status = get_https_data(url)
	local data = cjson.decode(res)
	return data
end

function get_user_access_token()
    local res, code, headers, status
    local url = 'https://api.meizu.com/oauth/token?grant_type=password&client_id=gkzyJzq4RPoaov3BamqsJgg&client_secret=yh9bdKurxxotCjrEvJOiumk2mrzhcyej&username=appadmin@flyme.cn&password=appadmin111&scope=router_trust'
    local res, code, headers, status = get_https_data(url)
	local data = cjson.decode(res)
	return data.access_token
end

function init_bind_router_body(access_token)
	local body = "access_token="
	local uat = luci.http.formvalue("uat")
	body = body..access_token
	body = body.."&user_access_token="
	body = body..uat
	return body
end

function bind_router()
	local url = "https://router.meizu.com/oauth/router/bindRouter"
	local access_token = rts_get_access_token()
	local body = init_bind_router_body(access_token)
	local https = require("ssl.https")
	local res, code, headers, status = https.request(url, body)
	if code == 401 then
                delete_access_token()
                access_token = rts_get_access_token()
		body = init_bind_router_body(access_token)
                res, code, headers, status = https.request(url, body)
        end
	luci.http.write(res)
end

function unbind_router()
	local url = 'https://router.meizu.com/oauth/user/unbind?access_token='
	local https = require("ssl.https")
	local uat = luci.http.formvalue("uat")
	url = url..uat
	url = url..'&device='..get_device_SN()
    local res, code, headers, status = https.request(url)
	luci.http.write(res)
end

function get_device_SN()
	local sn = "R13WZLCZ7AC1001"
	local fd = io.popen("nvram get sn")
	if fd then
		local ln = fd:read("*l")
		if ln ~= nil then
			sn = ln
	    end
		fd:close()
	end
	return sn
end

function get_device_version()
	local device_version = "1.0.0"
    --[[
	   [local pcall, dofile = pcall, dofile
	   [if pcall(dofile, "/etc/openwrt_release") then
	   [    if DISTRIB_RELEASE ~= nil then
	   [        device_version = DISTRIB_RELEASE
	   [    end
	   [end
       ]]
	local lu = require("luci.util")
	local cmd = [[cat /etc/openwrt_version|awk '{printf $1}']]
	local v = lu.exec(cmd)
	if v ~= nil then
		device_version = v
	end
	return device_version
end

function silent_upgrade()
	local fd   = nil
	local image = "/tmp/firmware.img"
	local touchcmd = "touch "..image
	exec_cmd_in_sh(touchcmd)
	local function image_supported()
		return ( 0 == os.execute(
		". /lib/functions.sh; " ..
		"include /lib/upgrade; " ..
		"platform_check_image %q >/dev/null"
		% image
		))
	end
	if luci.http.formvalue("act") == "update" then
		luci.http.write("act == update")
	end
	if image_supported() then
		luci.http.write("updating")
		exec_cmd_in_sh("killall dropbear uhttpd; sleep 1; /sbin/sysupgrade -v %q" %{ image })
		luci.http.write("update finished!")
	else
		luci.http.write("image_supported check failed!")
	end
end

function table_merge(t1, t2)
	if (type(t1) == "table") and (type(t2) == "table") then
		for k, v in pairs(t2) do
			if (type(v) == "table") and (type(t1[k] or false) == "table") then
				table_merge(t1[k], t2[k])
			else
				t1[k] = v
			end
		end
	end
    return t1
end

function sysinfo()
	local LuciNetwork = require("luci.model.network").init()
    	local lanNetwork = LuciNetwork:get_network("lan")
	local wanNetwork = LuciNetwork:get_network("wan")
	local wanType = "dhcp"
	if wanNetwork and lanNetwork then
		local apc = lanNetwork:get_option_value("apclient")
		if nil ~= apc and "" ~= apc then
			wanType = "apclient"
		else
        		wanType = wanNetwork:proto()
        	end
        end
	local lue = require("luci.util").exec
    	local ret = {}
    	ret["wanType"] = wanType
	ret["romversion"] = get_device_version()
	ret["SN"] = get_device_SN()
	ret["deviceModel"] = "R13"
	ret["routername"] = "mzrt"..get_device_SN()
    	local ssid1, ssid2 = require "meizu.nwfs".get_wifi_ssids()
	ret["ssid1"] = ssid2
	ret["ssid2"] = ssid1
	local cmd = [[df /mnt|grep -q sda;echo -n $?]]
	ret["diskstatus"] = lue(cmd)
	cmd = [[echo -n $(ifconfig br-lan |grep HWaddr|sed 's/.*HWaddr //' | sed 's/\ .*//')]]
	ret["brlanmac"] = lue(cmd)
	cmd = [[echo -n $(ifconfig br-lan |grep 'inet addr'|awk -F':' '{print $2}' |awk '{printf $1}')]]
	ret["brlanip"] = lue(cmd)

	return ret
end


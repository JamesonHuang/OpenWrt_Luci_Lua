module ("xiaoqiang.util.XQMitvUtil", package.seeall)

local JSON = require("luci.json")
local LuciProtocol = require("luci.http.protocol")
local urlencode = LuciProtocol.urlencode
local XQLog = require("xiaoqiang.XQLog")

function DoExec(cmd)
	local LuciUtil = require("luci.util")
	XQLog.log(7,cmd)
	local s = LuciUtil.exec(cmd)
	-- XQLog.log(7,s)
	return s
end

--[[
local JSON = require("json")
function urlencode(str)
  if (str) then
    str = string.gsub (str, "\n", "\r\n")
    str = string.gsub (str, "([^%w %-%_%.%~])",
        function (c) return string.format ("%%%02X", string.byte(c)) end)
    str = string.gsub (str, " ", "+")
  end
  return str	
end

function DoExec(cmd)
	-- print(cmd)
	local f = assert(io.popen(cmd, 'r'))
 	local s = assert(f:read('*a'))
 	f:close()
 	return s
end
]]


-- 
-- mitv control api wrapper
-- zhangyanlu@xiaomi.com
-- 

local Errorm1 = "{ \"code\" : -1 , \"msg\" : \"api not exists\" }";  
local Error3 = "{ \"code\" : 3 , \"msg\" : \"parameter format error\" }";
local Error4 = "{ \"code\" : 4 , \"msg\" : \"mitv api result error\" }";

function  request(payload)
	-- payload example :  {  "mac|ip" : "", "command" : "keyevent", "keycode" : "left"  } 
	if payload == nil then
		return Error3
	end
	
	local params = JSON.decode(payload)
	if params == nil then
		return Error3
	end
	local ip = params.ip
	if ip == nil then
		if params.mac == nil then
			return Error3
		end
		-- get ip from mac
		local DeviceUtil = require("xiaoqiang.util.XQDeviceUtil")
		local devices = DeviceUtil.getDHCPDict()
		local item = devices[params.mac]
		if item == nil then
			return Error3
		end
		ip = item.ip
	end
	if not string.match(ip,"^%d+%.%d+%.%d+%.%d+$") then
		return Error3
	end 

	if params.command == "isalive" then
		return isalive(ip)
	elseif params.command == "keyevent" then
		return control(ip,params.keycode)
	elseif params.command == "video_playurl" then
		return playVideoByUrl(ip,params.url)
	elseif params.command == "video_playmediaid" then
		return playVideoByMediaid(ip,params.mediaid,params.ci)
	elseif params.command == "music_playurl" then
		return playMusicByUrl(ip,params.url)
	elseif params.command == "photo_playurl" then
		return playPhotoByUrl(ip,params.url)
	else
		return Errorm1
	end
	
end

-- mitv api returns status=0 , miwifi apis uses code=0
function castMitvResult(str)
	if str == nil or str == "" then
		return Error4
	end
	local res = JSON.decode(str)
	res.code = res.status
	return JSON.encode(res)
end

-- test if mitv is alive
-- use small timeout
function isalive(ip)
	local cmd = "curl -s -k --connect-timeout 1 \"http://%s:6095/request?action=isalive\""
	local result = DoExec(string.format(cmd,ip))

	return castMitvResult(result)
end

-- control mitv
-- keycode enter, back, up, down, left, right, menu, home, voluemup, volumedown, power
-- aware of bash injection
function control(ip,keycode)
	if not string.match(keycode,"^%a+$") then
		return Error3
	end 
	local cmd = "curl -s -k \"http://%s:6095/controller?action=keyevent&keycode=%s\""
	local result = DoExec(string.format(cmd,ip,keycode))
	return castMitvResult(result)
end

function playVideoByUrl(ip,url)
	local cmd = "curl -s -k \"http://%s:6095/video?action=play&url=%s&clientname=miwifi\""
	local result = DoExec(string.format(cmd,ip,urlencode(url)))
	return castMitvResult(result)
end

function playVideoByMediaid(ip,mediaid,ci)
	if not string.match(mediaid,"^%d+$") or not string.match(ci,"^%d+$") then
		return Error3
	end 
	local cmd = "curl -s -k \"http://%s:6095/video?action=play&mediaid=%s&ci=%s&prefersource=1&clientname=miwifi\""
	local result = DoExec(string.format(cmd,ip,mediaid,ci))
	return castMitvResult(result)
end

function playMusicByUrl(ip,url)
	local cmd = "curl -s -k \"http://%s:6095/music?action=play&url=%s&clientname=miwifi\""
	local result = DoExec(string.format(cmd,ip,urlencode(url)))
	return castMitvResult(result)
end


function playPhotoByUrl(ip,url)
	local cmd = "curl -s -k \"http://%s:6095/photo?action=play&url=%s&clientname=miwifi\""
	local result = DoExec(string.format(cmd,ip,urlencode(url)))
	return castMitvResult(result)
end


-- print(request())
-- print(request(""))
-- print(request("{ \"ip\" : \"192.168.32.109\" , \"command\" : \"isalive\"}"))
-- print(request("{ \"ip\" : \"192.168.32.109\" , \"command\" : \"keyevent\" , \"keycode\" : \"& sudo ls\"}"))
-- print(request("{ \"ip\" : \"192.168.32.109\" , \"command\" : \"keyevent\" , \"keycode\" : \"right\"}"))

-- print(request("{ \"ip\" : \"192.168.32.109\" , \"command\" : \"keyevent\" , \"keycode\" : \"back\"}"))
-- print(request("{ \"ip\" : \"192.168.32.109\" , \"command\" : \"video_playurl\" , \"url\" : \"http://wifi.io/video.mp4\"}"))

-- print(request("{ \"ip\" : \"192.168.32.109\" , \"command\" : \"video_playmediaid\" , \"mediaid\" : \"87931\" , \"ci\" : \"1\" }"))

-- print(request("{ \"ip\" : \"192.168.32.109\" , \"command\" : \"music_playurl\" , \"url\" : \"http://yinyueshiting.baidu.com/data2/music/108215801/14385500158400128.mp3?xcode=191fb7d6bc00415844edb509f9a331dfa888a58d2b9e82c6\"}"))
-- print(request("{ \"ip\" : \"192.168.32.109\" , \"command\" : \"photo_playurl\" , \"url\" : \"http://img03.mifile.cn/webfile/images/hd/2014040802/www/ac_12.jpg\"}"))
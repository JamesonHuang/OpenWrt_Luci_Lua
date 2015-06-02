==============================第一版=============================
--function:     定时wifi开关
--author：      rh_Jameson
function smart_wifi_shutdown()
    local wnet = 'mt7628.network1'
    local table = {}
    
    --get para
    --local close_time = luci.http.formvalue("close_time")
    --local open_time = luci.http.formvalue("open_time")
    
    --test normal    
    --local close_time = os.time() + 5
    --local restart_time = os.time() + 10
    
    --test exception
    local close_time = os.time() - 5
    local restart_time = os.time() - 10

    --para err manage
    if close_time < os.time() or restart_time < close_time then
        table["err"] = true
        luci.http.write_json(table)
        return
    end
    --do close
    while true do
        if os.time() ~= close_time then
            posix.sleep(1)
        else
            wifi_shutdown(wnet)
            table["close"] = true
            luci.http.write_json(table)
            break
        end
    end

    --do restart
    while true do
        if os.time() ~= restart_time then
            posix.sleep(1)
        else
            wifi_reconnect(wnet)
            table["restart"] = true
            luci.http.write_json(table)
            break
        end
    end
end

============================第二版================================


-----------------------api/index.lua------------------------------
set_smart_wifi_updown      = nwfs.set_smart_wifi_updown 
set_wifi_up                = nwfs.set_wifi_up
set_wifi_down              = nwfs.set_wifi_down
set_smart_wifi_update      = nwfs.set_smart_wifi_update
set_smart_wifi_stop        = nwfs.set_smart_wifi_stop

--rh
page = entry({"api", "wifiUp"}, call("set_wifi_up"), nil)
page.leaf = true
page = entry({"api", "wifiDown"}, call("set_wifi_down"), nil)
page.leaf = true
page = entry({"api", "setSmartWifiUpdate"}, call("set_smart_wifi_update"), nil)
page.leaf = true
page = entry({"api", "setSmartWifiStop"}, call("set_smart_wifi_stop"), nil)
page.leaf = true
page = entry({"api", "setSmartWifiUpdown"}, call("set_smart_wifi_updown"), nil)
page.leaf = true




------------------------meizu/nwfs.lua----------------------------
function set_wifi_up()
    --local cmd = "/sbin/wifi up".. wnet
    local cmd = "/sbin/wifi up ".. "mt7628"
    exec_cmd_in_sh(cmd)
    luci.http.write_json("true")
end

function set_wifi_down()
    local cmd = "/sbin/wifi down ".."mt7628"
    --local cmd = "/sbin/wifi down"..wnet
    exec_cmd_in_sh(cmd)
    luci.http.write_json("true")
end

function set_smart_wifi_stop()
    local pid = luci.http.formvalue("pid")
    --exception manage
    if(close_time == nil or open_time == nil) then
        luci.http.write_json("smart_wifi not set")
        return
    end
    if(pid == nil) then
        luci.http.write_json("pid err")
    end

    --到点关闭前
    if os.time() < close_time then
        local cmd = "kill "..pid
    --到点关闭后，到点启动前
    elseif os.time() >= close_time and os.time() <= open_time then
        local cmd = "kill "..pid.."; /sbin/wifi up"
    --到点启动后
    else
        luci.http.write_json("smart wifi has finished，can not stop！")
        return
    end    
    exec_cmd_in_sh(cmd)
    luci.http.write_json("true")
end

function set_smart_wifi_update()
    local pid = luci.http.formvalue("pid")

    --exception manage
    if(close_time == nil or open_time == nil) then
        luci.http.write_json("smart_wifi not set")
        return
    end
    if(pid == nil) then
        luci.http.write_json("pid err")
    end

    --到点关闭前
    if os.time() < close_time then
        local cmd = "kill "..pid
    --到点关闭后，到点启动前
    elseif os.time() >= close_time and os.time() <= open_time then
        local cmd = "kill "..pid.."; /sbin/wifi up"
    --到点启动后
    end     
    exec_cmd_in_sh(cmd)
    
    --get para
    --close_time = os.time(lucimZhttp.formvalue("close_time"))
    --open_time = os.time(luci.http.formvalue("open_time"))
    
    --test normal    
    close_time = os.time() + 5
    restart_time = os.time() + 10
    
    --test exception
    --close_time = os.time() - 5
    --restart_time = os.time() - 10
    
    set_smart_wifi_updown(close_time, restart_time)
end


function fork_smart_wifi_updown( wnet, close_time, open_time)
    local close_interval = close_time - os.time()
    local open_interval = open_time - os.time()
    local cmd = string.format("sleep 15;/sbin/wifi down "..wnet.."; sleep 15; /sbin/wifi up "..wnet)
    
    return exec_cmd_in_sh(cmd)
    --exec_cmd_in_sh(cmd)
end


--function:     定时wifi开关shell形式
--author：      rh_Jameson
function set_smart_wifi_updown()
    local wnet = 'mt7628'
    local info = {}

    --get para
    --close_time = lucimZhttp.formvalue("close_time")
    --open_time = luci.http.formvalue("open_time")
    
    --test normal    
    close_time = os.time() + 5
    restart_time = os.time() + 10
    
    --test exception
    --close_time = os.time() - 5
    --restart_time = os.time() - 10

    --para err manage
    if close_time < os.time() or restart_time < close_time then
        info["SUCCESS"] = false
    else
        info["SUCCESS"] = true
    end
    
    local pid = fork_smart_wifi_updown(wnet, close_time, restart_time)
    info["PID"] = pid
    luci.http.write_json(info)  
end
--重载
function set_smart_wifi_updown(close_time,restart_time)
    
    local wnet = 'mt7628'
    local info = {}
    
    --para err manage
    if close_time < os.time() or restart_time < close_time then
        info["SUCCESS"] = false
    else
        info["SUCCESS"] = true
    end
    
    local pid = fork_smart_wifi_updown(wnet, close_time, restart_time)
    info["PID"] = pid
    luci.http.write_json(info)  
end


-------------------------------bfs.lua--line_92-------------------------------
function exec_cmd_in_sh(command)
	local nio = require("nixio")
	require "MZLog".log(3, command)
    local pid = nio.fork()
    if pid > 0 then
        return pid
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


-------------------------UTC-CODE-字符串形式UTC时间转时间戳------------------------
local lue = require("luci.util").exec
local t = lue([[date -d "2010-10-10 10:10:10" +%s]])
print(tonumber(t))


local lue = require("luci.util").exec


--local t = lue('date -d "2010-10-10 10:10:10" +%s')

local t = lue([[date -d "2010-10-10 10:10:10" +%s]])

print(tonumber(t))
print(t)


----------------------------Lua-多线程回调函数实现---------------------------------
--回调
function fork_exec_in_lua(func)                                
        local nio = require("nixio")                                                                  
--      require "MZLog".log(3, command)        
    local pid = nio.fork()                     
    if pid > 0 then                           
        print(pid)                            
        return                 
    elseif pid == 0 then                                                                              
        func()                                                                                        
    end                   
end


--主调
local bfs = require("meizu.bfs")
function test()
    local i = 1
    while(i < 10) do
        print(i)
        i = i + 1
    end
end

bfs.fork_exec_in_lua(test)



----------------------------Lua-函数不可以重载-----------------------------------
--test
function fun()
    print("11111")
end

function fun(str)
    print(str)
end

fun()
fun("2222")
fun()


--result
nil
2222
nil

----------------------------crontabs & crond------------------------------------

crontab -u //设定某个用户的cron服务，一般root用户在执行这个命令的时候需要此参数 
crontab -l //列出某个用户cron服务的详细内容 
crontab -r //删除没个用户的cron服务 
crontab -e //编辑某个用户的cron服务

基本用法:
1. crontab -l 
列出当前的crontab任务
2. crontab -d 
删除当前的crontab任务
3. crontab -e (solaris5.8上面是 crontab -r) 
编辑一个crontab任务,ctrl_D结束


4. crontab filename 
以filename做为crontab的任务列表文件并载入

/*以下是多启动一个进程进行服务*/
crond start //启动服务

crond stop //关闭服务

crond restart //重启服务

crond reload //重新载入配置

--openwrt设置/etc/crontabs/root:
* * * * * echo "hello" >> /cron.txt

--每次做修改时使用crontab /etc/crondtabs/root 
--crontabs/root文件下写的定时任务是多线程运行，不按顺序执行


Sed命令：
sed -n '/hello/p' cron.txt  查询hello关键字，打印相应行

sed  '/hello/d' cron.txt 删除含hello的行，输出到屏幕（原文件没删除）

sed -i '/hello/d' cron.txt 直接修改原文件



--[[non-repeat任务]]--
* * * * * echo "flag non-repeat">> /cron.txt; sed -i '/sed/d' /etc/crontabs/root

--[[repeat任务]]--
--添加定时任务exam
echo "* * * * * date >>/cron.txt">>/etc/crontabs/root;flag >/dev/null 2>/

--删除定时任务exam
sed -i '/hello/d' /etc/crontabs/root

--修改定时任务exam

==================================第三版-TEST================================

echo '* * * * non-repeat /sbin/wifi down mt7628; sed -i "/* * * * non-repeat/d" etc/crontabs/root' >> /etc/crontabs/root; crontab /etc/crontabs/root;

* * * * * /sbin/wifi down mt7628; sed -i '/sed/d' etc/crontabs/root; crontab /etc/crontabs/root;
* * * * * date >>/cron.txt; sed -i '/sed/d' /etc/crontabs/root


--non-repeat任务：
echo "$1 $2 * * $3 /sbin/wifi down mt7628;
	sleep 4; 
	sed -i '/$1$2$3/d' /etc/crontabs/root; 
	crontab /etc/crontab/root; 
	$1$2$3 >/dev/null 2>/dev/null " >> /etc/crontabs/root; crontab /etc/crontabs/root; 

bin cron.txt * * dev /sbin/wifi down mt7628;sleep 4; sed -i '/bincron.txtdev/d' /etc/crontabs/root; crontab /etc/crontab/root; bincron.txtdev >/dev/null 2>/dev/null   



--non-repeate任务：
 if week_repeat == "non-repeat" then                                                                                                           
        week_repeat = "0,1,2,3,4,5,6"                                                                                                             
        close_min = "40"                                                                                                                          
        Segmentation fault                                                                                                                        
        cmd = "/sbin/smart@OpenWrt:/usr/lib/lua/meizu# e_hour.." "..week_repeat                                                                   
    else                                                                                                                                          
        cmd = "echo '"..close_min.." "..close_hour.." * * "..week_repeat.." /sbin/wifi down "..wnet.."' >> /etc/crontabs/root; crontab /etc/cronta
                ..close_min..close_hour..open_hour..open_min..week_repeat.." >/dev/null 2>/dev/null"                                              
        --cmd = "echo '"..open_min.." "..open_hour.." * * "..week_repeat.." /sbin/wifi up "..wnet.."' >> /etc/crontabs/root; crontab /etc/crontabs
    end                                                                                                                                           
    luci.http.write_json(cmd)                                                                                                                     
    exec_cmd_in_sh(cmd)                                                                                                                           
    luci.http.write_json(true) 



if week_repeat == "non-repeat" then
        week_repeat = "*"
        cmd_close = "/sbin/smart_wifi "..close_hour.." "..close_min.." "..week_repeat 
        --cmd_open = "echo '"..close_min.." "..close_hour.." * * "..week_repeat.." /sbin/wifi down "..wnet
        --            .."; sed -i '/"..close_min..close_hour..open_hour..open_min..week_repeat
        --            .."/d' etc/crontabs/root >> /etc/crontabs/root; crontab /etc/crontabs/root; "
        --            ..close_min..close_hour..open_hour..open_min..week_repeat.." >/dev/null 2>/dev/null" 
    else
        cmd_close = "echo '"..close_min.." "..close_hour.." * * "..week_repeat.." /sbin/wifi down "..wnet
                    .."' >> /etc/crontabs/root; crontab /etc/crontabs/root; "
                    ..close_min..close_hour..open_hour..open_min..week_repeat.." >/dev/null 2>/dev/null"
        
        --cmd_open = "echo '"..open_min.." "..open_hour.." * * "..week_repeat.." /sbin/wifi up "..wnet.."' >> /etc/crontabs/root; crontab /etc/crontabs/root; "..close_min..close_hour..open_hour..open_min..week_repeat.." >/dev/null 2>/dev/null"
    end
    
    luci.http.write_json(cmd_close)
    --luci.http.write_json(cmd_open)
    
    exec_cmd_in_sh(cmd_close)
    --exec_cmd_in_sh(cmd_open)
    
    luci.http.write_json(true)  


===============================================================第三版-succeed====================================================

-------------------------./package/base-files/files/sbin/smart_wifi----------------------------------

#!/bin/sh
echo "" >> /etc/crontabs/root;
echo "$1 $2 * * $3 /sbin/wifi down mt7628;sleep 4; sed -i '/$1$2$3/d' /etc/crontabs/root; crontab /etc/crontab/root; $1$2$3 >/dev/null 2>/dev/null " >> /etc/crontabs/root; crontab /etc/crontabs/root; 
echo "" >> /etc/crontabs/root;


------------------------------------------------nwfs.lua code-------------------------------------

------------------------------------------------set smart wifi------------------------------------
function fork_smart_wifi_updown(wnet, close_hour, close_min, open_hour, open_min, week_repeat)
    if week_repeat == "non-repeat" then               
        week_repeat = "0,1,2,3,4,5,6"       --"1,2,3,4,5,6,0 is error,可能会误删repeat的数据"
        cmd_close = "/sbin/smart_wifi "..close_min.." "..close_hour.." "..week_repeat
        cmd_open = "/sbin/smart_wifi "..open_min.." "..open_hour.." "..week_repeat
    else
        cmd_close = "echo '"..close_min.." "..close_hour.." * * "..week_repeat.." /sbin/wifi down "..wnet..";"
            ..close_min..close_hour..open_min..open_hour..week_repeat
            ..">/dev/null 2>/dev/null' >> /etc/crontabs/root; crontab /etc/crontabs/root;"
        
        cmd_open = "echo '"..open_min.." "..open_hour.." * * "..week_repeat.." /sbin/wifi up "..wnet..";"
            ..close_min..close_hour..open_min..open_hour..week_repeat
            ..">/dev/null 2>/dev/null' >> /etc/crontabs/root; echo '' >> /etc/crontabs/root; crontab /etc/crontabs/root;"
    end                                               
    luci.http.write_json(cmd_close)
    luci.http.write_json(cmd_open)

    exec_cmd_in_sh(cmd_close)
    posix.sleep(1)
    exec_cmd_in_sh(cmd_open)                               
end

-----------------------------------------------stop smart wifi------------------------------------
function set_smart_wifi_stop(update_flag)
    local cmd = "sed -i '/"..update_flag.."/d' /etc/crontabs/root; crontab /etc/crontabs/root"
    exec_cmd_in_sh(cmd)
    luci.http.write_json(cmd)
end



--------------------------------------------update smart wifi-------------------------------------
--(stop + set) smartwifi




----------------------------------------------smart wifi api--------------------------------------
--function:     定时wifi开关shell形式
--author：      rh_Jameson
function set_smart_wifi_updown()

    --get para
    --wnet = luci.http.formvalue("wnet")
    --close_hour = luci.http.formvalue("close_hour")
    --close_min = luci.http.formvalue("close_min")
    --open_hour = luci.http.formvalue("open_hour")
    --open_min = luci.http.formvalue("open_min")
    --repeat_var_from_http = luci.http.formvalue("repeat_var")
    --func = luci.http.formvalue("func")

    --test    
    local wnet = 'mt7628'
    local close_hour = "07"
    local close_min = "25"
    local open_hour = "07"
    local open_min = "26"
    local repeat_var_from_http = "1111111"
    local func = luci.http.formvalue("func")
     --para err manage
    if string.len(repeat_var_from_http) ~= 7 then
        luci.http.write_json("len error")
        return
    end
    
    --get repeat
    local week_repeat = ""
    for i = 0, #repeat_var_from_http do
        tmp = string.sub(repeat_var_from_http, i, i)
        if tmp == "1" then
            if i == #repeat_var_from_http then
                week_repeat = week_repeat..0
                break
            end
            week_repeat = week_repeat..tostring(i)..","
        end
    end
    if week_repeat == "" then
        week_repeat = "non-repeat"
    end
    --exec
    if func == "set" then
        fork_smart_wifi_updown(wnet, close_hour, close_min, open_hour, open_min, week_repeat)
    elseif func == "stop" or func == "update" then
        local update_flag = luci.http.formvalue("flag")
        set_smart_wifi_stop(update_flag)
        
        if func == "update" then
            posix.sleep(1)
            fork_smart_wifi_updown(wnet, close_hour, close_min, open_hour, open_min, week_repeat)
        end
    end
end



-----------------------api/index.lua------------------------------
set_smart_wifi_updown      = nwfs.set_smart_wifi_updown 
set_wifi_up                = nwfs.set_wifi_up
set_wifi_down              = nwfs.set_wifi_down
set_smart_wifi_update      = nwfs.set_smart_wifi_update
set_smart_wifi_stop        = nwfs.set_smart_wifi_stop

--rh
page = entry({"api", "wifiUp"}, call("set_wifi_up"), nil)
page.leaf = true
page = entry({"api", "wifiDown"}, call("set_wifi_down"), nil)
page.leaf = true
page = entry({"api", "setSmartWifiUpdate"}, call("set_smart_wifi_update"), nil)
page.leaf = true
page = entry({"api", "setSmartWifiStop"}, call("set_smart_wifi_stop"), nil)
page.leaf = true
page = entry({"api", "setSmartWifiUpdown"}, call("set_smart_wifi_updown"), nil)
page.leaf = true





-----------------------第三版阉割代码--------------------------------

off功能：
sed -n '/hello/p' /etc/crontabs/root >> /tmp/wifi_cron_off.txt; sed -i '/hello/d' /etc/crontabs/root 

on功能：
sed -n '/timer0/p' /tmp/wifi_cron_off.txt  >> /etc/crontabs/root; sed -i '/timer0/d' /tmp/wifi_cron_off.txt 



get match：
11 08 * * 1,2,3,4,5,6,0 /sbin/wifi down mt7628; #110812081,2,3,4,5,6,0; up_timer0










cmd_close = "echo '"..close_min.." "..close_hour.." * * "..week_repeat.." /sbin/wifi down "..wnet.."; #"
            ..close_min..close_hour..open_min..open_hour..week_repeat.."~ down_"..time_flag
            .."' >> /etc/crontabs/root; crontab /etc/crontabs/root;"
cmd_open = "echo '"..open_min.." "..open_hour.." * * "..week_repeat.." /sbin/wifi up "..wnet.."; #"
   	    ..close_min..close_hour..open_min..open_hour..week_repeat.."~ up_"..time_flag
    	    .."' >> /etc/crontabs/root; echo ''>>/etc/crontabs/root;  crontab /etc/crontabs/root;"

cmd_close = "echo '"..close_min.." "..close_hour.." * * "..week_repeat.." /sbin/wifi down "..wnet.."; #"
            ..close_min..close_hour..open_min..open_hour..week_repeat
            .."' >> /etc/crontabs/root; crontab /etc/crontabs/root;"
        cmd_open = "echo '"..open_min.." "..open_hour.." * * "..week_repeat.." /sbin/wifi up "..wnet.."; #"
            ..close_min..close_hour..open_min..open_hour..week_repeat
            .."' >> /etc/crontabs/root; echo ''>>/etc/crontabs/root;  crontab /etc/crontabs/root;"



http://192.168.232.1/cgi-bin/luci/api/setSmartWifiUpdown?func=set;flag=timer0
http://192.168.232.1/cgi-bin/luci/api/setSmartWifiUpdown?func=stop;flag=timer0
192.168.232.1/cgi-bin/luci/api/setSmartWifiUpdown?func=update;flag=timer0;new_flag=timer0

--get para
    wnet = luci.http.formvalue("wnet")
    close_hour = luci.http.formvalue("close_hour")
    close_min = luci.http.formvalue("close_min")
    open_hour = luci.http.formvalue("open_hour")
    open_min = luci.http.formvalue("open_min")
    repeat_var_from_http = luci.http.formvalue("repeat_var")
    func = luci.http.formvalue("func")
    flag = luci.http.formvalue("flag")

--test normal
    local wnet = 'mt7628'
    local close_hour = "08"
    local close_min = "11"
    local open_hour = "08"
    local open_min = "12"
    local repeat_var_from_http = "1111111"
    local func = luci.http.formvalue("func")
    local time_flag = luci.http.formvalue("flag")
--update
local new_flag = luci.http.formvalue("new_flag")

设置的参数：
fork_smart_wifi_updown(wnet, close_hour, close_min, open_hour, open_min, week_repeat, time_flag)

修改的参数：
192.168.232.1/cgi-bin/luci/api/setSmartWifiUpdown?func=update;flag=timer0;new_flag=timer0



on & off只要flag
http://192.168.232.1/cgi-bin/luci/api/setSmartWifiOff?flag=timer0

http://192.168.232.1/cgi-bin/luci/api/setSmartWifiOn?flag=timer0

---------------------------------------------------------------------------------------
update:

http://192.168.232.1/cgi-bin/luci/api/setSmartWifiUpdown?func=update;flag=timer1;new_flag=timer0


stop:
http://192.168.232.1/cgi-bin/luci/api/setSmartWifiUpdown?func=stop;flag=timer2

set:

192.168.232.1/cgi-bin/luci/api/setSmartWifiUpdown?func=set;flag=timer2

on:

http://192.168.232.1/cgi-bin/luci/api/setSmartWifiUpdown?func=on;flag=timer1

off:
http://192.168.232.1/cgi-bin/luci/api/setSmartWifiUpdown?func=off;flag=timer1

get:
http://192.168.232.1/cgi-bin/luci/api/getSmartWifiInfo








------------------------------------第三版update-------------------------------------------------
--set
function fork_smart_wifi_updown(wnet, close_hour, close_min, open_hour, open_min, week_repeat, time_flag)
    if week_repeat == "non-repeat" then
        week_repeat = "0,1,2,3,4,5,6"
        cmd_close = "/usr/sbin/wifi_cron_switch "..close_min.." "..close_hour.." "..week_repeat.. " "..time_flag
                    .." "..wnet.." down"
        cmd_open = "/usr/sbin/wifi_cron_switch "..open_min.." "..open_hour.." "..week_repeat.." "..time_flag
                    .." "..wnet.." up"
    else
        cmd_close = "echo '"..close_min.." "..close_hour.." * * "..week_repeat.." /sbin/wifi down "..wnet.."; #"
            ..close_min..close_hour..open_min..open_hour..week_repeat.."~ down_"..time_flag
            .."' >> /etc/crontabs/root; crontab /etc/crontabs/root;"
        cmd_open = "echo '"..open_min.." "..open_hour.." * * "..week_repeat.." /sbin/wifi up "..wnet.."; #"
            ..close_min..close_hour..open_min..open_hour..week_repeat.."~ up_"..time_flag
            .."' >> /etc/crontabs/root; echo ''>>/etc/crontabs/root;  crontab /etc/crontabs/root;"
    end
    luci.http.write_json(cmd_close)
    luci.http.write_json(cmd_open)
    require "MZLog".log(3, cmd_close)
    require "MZLog".log(3, cmd_open)
    exec_cmd_in_sh(cmd_close)
    posix.sleep(1)
    exec_cmd_in_sh(cmd_open)
end
--stop(del)
function set_smart_wifi_stop(update_flag)
    local cmd = "sed -i '/"..update_flag.."/d' /etc/crontabs/root; crontab /etc/crontabs/root"
    require "MZLog".log(3, cmd)
    exec_cmd_in_sh(cmd)
    luci.http.write_json(cmd)
end

--off
function set_smart_wifi_off(update_flag)
    --local update_flag = luci.http.formvalue("flag")
    local cmd = "sed -n '/"..update_flag.."/p' /etc/crontabs/root >> /tmp/wifi_cron_off.txt; sed -i '/"
                ..update_flag.."/d' /etc/crontabs/root; crontab /etc/crontabs/root"
    require "MZLog".log(3, cmd)
    exec_cmd_in_sh(cmd)
    luci.http.write_json(cmd)
end

--on
function set_smart_wifi_on(update_flag)
    --local update_flag = luci.http.formvalue("flag")
    local cmd = "sed -n '/"..update_flag.."/p' /tmp/wifi_cron_off.txt  >> /etc/crontabs/root; sed -i '/"
                ..update_flag.."/d' /tmp/wifi_cron_off.txt; crontab /etc/crontabs/root"
	
    require "MZLog".log(3, cmd)
    exec_cmd_in_sh(cmd)
    luci.http.write_json(cmd)
end

--get
function get_smart_wifi_info()
    --exec_cmd_in_sh("echo '' >> /etc/crontabs/root", "r") 
    local smart_wifi_info = io.open("/etc/crontabs/root", "r")
    local res = {}
    if smart_wifi_info == nil then
		luci.http.write_json("false")
	    return
    end 
    for line in smart_wifi_info:lines() do
        idx,_ = string.find(line, "up_timer")
        if line and idx then
            time_idx_begin = string.find(line, "#") + 1
            time_idx_end = string.find(line, "~") - 1
            local item = {}
            item["close_min"] = string.sub(line, time_idx_begin, time_idx_begin + 1)
            item["close_hour"] = string.sub(line, time_idx_begin + 2, time_idx_begin + 3)
            item["open_min"] = string.sub(line, time_idx_begin + 4, time_idx_begin + 5)
            item["open_hour"] = string.sub(line, time_idx_begin + 6, time_idx_begin + 7)
            item["repeat"] = string.sub(line, time_idx_begin + 8, time_idx_end)
            item["switch"] = 1
            table.insert(res,item)
        end
    end
    
    exec_cmd_in_sh("echo '' >> /tmp/wifi_cron_off.txt") 
    smart_wifi_info = io.input("/tmp/wifi_cron_off.txt")
    
    for line in smart_wifi_info:lines() do
        idx,_ = string.find(line, "up_timer")
        if line and idx then
                time_idx_begin = string.find(line, "#") + 1
                time_idx_end = string.find(lind, "~") - 1
                local item = {}
                item["close_min"] = string.sub(line, time_idx_begin, time_idx_begin + 1)
                item["close_hour"] = string.sub(line, time_idx_begin + 2, time_idx_begin + 3)
                item["open_min"] = string.sub(line, time_idx_begin + 4, time_idx_begin + 5)
                item["open_hour"] = string.sub(line, time_idx_begin + 6, time_idx_begin + 7)
                item["repeat"] = string.sub(line, time_idx_begin + 8, time_idx_end)
                item["switch"] = 0
                table.insert(res,item)
        end
    end

    require "MZLog".log(3, res)
    smart_wifi_info:close()
    luci.http.write_json(res)
end


--function:     定时wifi开关shell形式
--author：      rh_Jameson
function set_smart_wifi_updown()
    --get para
    --wnet = luci.http.formvalue("wnet")
    --close_hour = luci.http.formvalue("close_hour")
    --close_min = luci.http.formvalue("close_min")
    --open_hour = luci.http.formvalue("open_hour")
    --open_min = luci.http.formvalue("open_min")
    --repeat_var_from_http = luci.http.formvalue("repeat_var")
    --func = luci.http.formvalue("func")
    --flag = luci.http.formvalue("flag")
    
    --test normal
    local wnet = 'mt7628'
    local close_hour = "08"
    local close_min = "11"
    local open_hour = "08"
    local open_min = "12"
    local repeat_var_from_http = "1111111"
    local func = luci.http.formvalue("func")
    local time_flag = luci.http.formvalue("flag")
     
    --para err manage
    if string.len(repeat_var_from_http) ~= 7 then
        luci.http.write_json("len error")
        return
    end
    --get repeat
    local week_repeat = ""
    for i = 0, #repeat_var_from_http do
        tmp = string.sub(repeat_var_from_http, i, i)
        if tmp == "1" then
            if i == #repeat_var_from_http then
                week_repeat = week_repeat..0
                break
            end
            week_repeat = week_repeat..tostring(i)..","
        end
    end
    if week_repeat == "" then
        week_repeat = "non-repeat"
    end

    --exec
    if func == "set" then
        fork_smart_wifi_updown(wnet, close_hour, close_min, open_hour, open_min, week_repeat, time_flag)
    elseif func == "stop" or func == "update" then
        set_smart_wifi_stop(time_flag)
        if func == "update" then
            local new_flag = luci.http.formvalue("new_flag")
            posix.sleep(1)
            fork_smart_wifi_updown(wnet, close_hour, close_min, open_hour, open_min, week_repeat, new_flag)
        end
    elseif func == "on" then
        set_smart_wifi_on(time_flag)
    elseif func == "off" then
        set_smart_wifi_off(time_flag)
    end
end





















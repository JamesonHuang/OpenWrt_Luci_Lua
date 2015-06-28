--set
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

--stop
function set_smart_wifi_stop(update_flag)
    local cmd = "sed -i '/"..update_flag.."/d' /etc/crontabs/root; crontab /etc/crontabs/root"
    exec_cmd_in_sh(cmd)
    luci.http.write_json(cmd)
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


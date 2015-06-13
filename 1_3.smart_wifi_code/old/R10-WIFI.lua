                                                                                                                                                                                               
function wifi_reconnect_shutdown(shutdown, wnet)                                                                                                                                                
        local netmd = require "luci.model.network".init()                                                                                                                                       
        local net = netmd:get_wifinet(wnet)                                                                                                                                                     
        local dev = net:get_device()                                                                                                                                                            
        if dev and net then                                                                                                                                                                     
                dev:set("disabled", nil)                                                                                                                                                        
                net:set("disabled", shutdown and 1 or nil)                                                                                                                                      
                netmd:commit("wireless")                                                                                                                                                        
                                                                                                                                                                                                
                luci.sys.call("env -i /bin/ubus call network reload >/dev/null 2>/dev/null")                                                                                                    
                                                                                                                                                                                                
                luci.sys.call("env -i /sbin/wifi reload >/dev/null 2>/dev/null")                                                                                                                
                                                                                                                                                                                                
                --luci.http.status(200, shutdown and "Shutdown" or "Reconnected")                                                                                                               
                                                                                                                                                                                                
                return                                                                                                                                                                          
        end                                                                                                                                                                                     
                                                                                                                                                                                                
        --luci.http.status(404, "No such radio")                                                                                                                                                
end                                                                                                                                                                                             
                                                                                                                                                                                                
function wifi_reconnect(wnet)                                                                                                                                                                   
        wifi_reconnect_shutdown(false, wnet)                                                                                                                                                    
end                                                                                                                                                                                             
                                                                                                                                                                                                
function wifi_shutdown(wnet)                                                                                                                                                                    
        wifi_reconnect_shutdown(true, wnet)                                                                                                                                                     
end                                                                                                                                                                                             
                                                                                                                                                                                                
                                                                                                                                                                                                
                                                                                                                                                                                                
function fork_smart_wifi_shutdown(switch, wnet, close_time, open_time)                                                                                                                          
        local close_interval = close_time - os.time()                                                                                                                                           
        local open_interval = open_time - os.time()                                                                                                                                             
                                                                                                                                                                                                
                                                                                                                                                                                                
        local cmd = string.format("/sbin/wifi down "..wnet.."; sleep 15; /sbin/wifi up "..wnet)                                                                                                 
        require "MZLog".log(3, cmd)                                                                                                                                                             
        --local cmd = string.format("sleep %s; /sbin/wifi down; sleep %s; /sbin/wifi up;", tostring(close_interval), tostring(open_interval))                                                   
        --local cmd = "/sbin/wifi "..switch.." "..wnet                                                                                                                                          
                                                                                                                                                                                                
        exec_cmd_in_sh(cmd)                                                                                                                                                                     
end 

                                                                                                                                                                                                
                                                                                                                                                                                                
function smart_wifi_shutdown()                                                                                                                                                                  
        local wnet = "wl1"                                                                                                                                                                      
        local info = {}                                                                                                                                                                         
        local switch = luci.http.formvalue("switch")                                                                                                                                            
        --get para                                                                                                                                                                              
        --close_time = luci.http.formvalue("close_time")                                                                                                                                        
        --open_time = luci.http.formvalue("open_time")                                                                                                                                          
                                                                                                                                                                                                
        --test normal                                                                                                                                                                           
        close_time = os.time() + 2                                                                                                                                                              
        restart_time = os.time() + 4                                                                                                                                                            
                                                                                                                                                                                                
        --test exception                                                                                                                                                                        
        --close_time = os.time() - 5                                                                                                                                                            
        --restart_time = os.time() - 10                                                                                                                                                         
                                                                                                                                                                                                
        --para err manage                                                                                                                                                                       
        if close_time < os.time() or restart_time < close_time then                                                                                                                             
                info["SUCCESS"] = false                                                                                                                                                              
        else                                                                                                                                                                                    
                info["SUCCESS"] = true                                                                                                                                                             
        end                                                                                                                                                                                     
        fork_smart_wifi_shutdown(switch, wnet, close_time, restart_time)                                                                                                                        
        luci.http.write_json(info)                                                                                                                                                              
                                                                                                                                                                                                
end    

module ("xiaoqiang.XQLog", package.seeall)

local posix = require("posix")

--[[
    0    mergency: system is unusable
    1    Alert: action must be taken immediately
    2    Critical: critical conditions
    3    Error: error conditions
    4    Warning: warning conditions
    5    Notice: normal but significant condition
    6    Informational: informational messages
    7    Debug: debug-level messages
]]--
function log(...)
    local priority = arg[1]
    if priority and tonumber(priority) and tonumber(priority) >= 0 and tonumber(priority) <= 7 then
        local util = require("luci.util")
        posix.openlog("luci","np",LOG_USER)
        for i = 2, arg.n do
            posix.syslog(priority, util.serialize_data(arg[i]))
        end
        posix.closelog()
    end
end

--[[
    ctype:
        0 none
        1 instant
]]--

KEY_GEL_USE = "gel_use"
KEY_REBOOT = "gel_restart_soft_count"
KEY_DETECT_ERROR = "network_detect_error"

KEY_VALUE_NETWORK_PPPOE = "network_method_pppoe"
KEY_VALUE_NETWORK_DHCP = "network_method_dhcp"
KEY_VALUE_NETWORK_STATIC = "network_method_static"

KEY_GEL_INIT_ANDROID = "gel_init_android"
KEY_GEL_INIT_IOS = "gel_init_ios"
KEY_GEL_INIT_OTHER = "gel_init_other"

KEY_DISKSLEEP_OPEN = "disk_sleep_open"
KEY_DISKSLEEP_CLOSE = "disk_sleep_close"

KEY_FUNC_PPTP = "function_pptp_web"
KEY_FUNC_L2TP = "function_l2tp_web"
KEY_FUNC_APPQOS = "function_appqos"
KEY_FUNC_MACCLONE = "function_clone"
KEY_FUNC_QOS = "function_qos"
KEY_FUNC_UPNP = "function_upnp"
KEY_FUNC_DMZ = "function_dmz"
KEY_FUNC_PLUGIN = "function_plugin"
KEY_FUNC_PORTFADD = "function_port_forwarding_add"
KEY_FUNC_RANGEFADD = "function_range_forwarding_add"
KEY_FUNC_PORTENABLE = "function_port_forwarding_active"
KEY_FUNC_WIRELESS_ACCESS = "function_wireless_access"
KEY_FUNC_WIRELESS_BLACK = "function_wireless_access_blacklist"
KEY_FUNC_WIRELESS_WHITE = "function_wireless_access_whitelist"
KEY_FUNC_2G_CHANNEL = "function_channel_2g"
KEY_FUNC_5G_CHANNEL = "function_channel_5g"
KEY_FUNC_2G_SIGNAL = "function_channel_2g_signal"
KEY_FUNC_5G_SIGNAL = "function_channel_5g_signal"
KEY_FUNC_NOFLUSHED = "function_hdd_hibernation"

function check(ctype, key, value)
    local statPoints
    if ctype == 0 then
        statPoints = "stat_points_none"
    else
        statPoints = "stat_points_instant"
    end
    posix.openlog("web","np",LOG_USER)
    posix.syslog(6, statPoints.." "..key.."="..tostring(value))
    posix.closelog()
end

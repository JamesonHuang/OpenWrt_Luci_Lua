module ("xiaoqiang.common.XQConfigs", package.seeall)

-- SERVER_CONFIG :
--    0 : online
--    1 : staging
--    2 : preview
SERVER_CONFIG = 0
SERVER_CONFIG_ONLINE_URL = "http://api.gorouter.info"
SERVER_CONFIG_STAGING_URL = "http://api.staging.gorouter.info"
SERVER_CONFIG_PREVIEW_URL = "http://api.preview.gorouter.info"

PASSPORT_CONFIG_ONLINE_URL = "https://account.xiaomi.com/pass/serviceLogin"
PASSPORT_CONFIG_PREVIEW_URL = "http://account.preview.n.xiaomi.net/pass/serviceLogin"
XQ_SERVER_ONLINE_STS_URL = "https://www.gorouter.info/sts"
XQ_SERVER_STAGING_STS_URL = "https://www.staging.gorouter.info/sts"
PASSPORT_LOGOUT_ONLINE_URL = "https://account.xiaomi.com/pass/logout"
PASSPORT_LOGOUT_PREVIEW_URL = "http://account.preview.n.xiaomi.net/pass/logout"
XQ_SERVER_ONLINE_API_URL = "https://www.gorouter.info"
XQ_SERVER_STAGING_API_URL = "https://www.staging.gorouter.info"

-- Statistics
ARP_LIST_UI_FILEPATH = "/tmp/activate.arp.list.ui"
NIC_LIST_UI_FILEPATH = "/tmp/activate.nic.list.ui"

-- Config/log file
CONFIG_ZIP_FILEPATH = "/tmp/config.zip"
LOG_ZIP_FILEPATH = "/tmp/log.zip"
PPP_LOG_FILEPATH = "/var/log/ppp.log"

PREF_IS_INITED = "INITTED"
PREF_IS_PASSPORT_BOUND = "PASSPORT_BOUND"
PREF_ROUTER_NAME = "ROUTER_NAME"
PREF_WAN_SPEED_HISTORY = "WAN_SPEED_HISTORY"
PREF_PASSPORT_BOUND_UUID = "PASSPORT_UUID"
PREF_UPGRADE_INFO = "UPGRADE_INFO"
PREF_WPS_TIMESTAMP = "WPS_TIMESTAMP"
PREF_ROUTER_NAME_PENDING = "ROUTER_NAME_PENDING"
PREF_BOUND_USERINFO = "BOUND_USER_INFO"
PREF_ROM_FULLSIZE = "ROM_FULLSIZE"
PREF_PPPOE_NAME = "PPPOE_NAME"
PREF_PPPOE_PASSWORD = "PPPOE_PASSWORD"
PREF_ROM_DOWNLOAD_URL = "ROM_DOWNLOAD_URL"
PREF_ROM_UPLOAD_URL = "ROM_UPLOAD_URL"
PREF_PAUSED_IDS = "PAUSED_IDS"
PREF_TIMESTAMP = "TIMESTAMP"
PREF_ROM_DOWNLOAD_ID = "ROM_DOWNLOAD_ID"

FORK_RESTART_WIFI = "sleep 4; /sbin/wifi >/dev/null 2>/dev/null; /etc/init.d/minidlna restart; /etc/init.d/samba restart; /usr/bin/gettraffic flush_wl_dev >/dev/null 2>/dev/null"
FORK_RESET_ALL = "env -i sleep 4 && nvram set restore_defaults=1 && nvram commit && reboot & >/dev/null 2>/dev/null"
FORK_RESTART_ROUTER = "/usr/sbin/phyhelper stop;sleep 4; reboot"
FORK_SHUTDOWN_ROUTER = "sleep 4; /usr/sbin/uhbn 3"
FORK_RESTART_DNSMASQ = "sleep 2; /etc/init.d/dnsmasq restart"
RESTART_MAC_FILTER = "/bin/sh /etc/firewall.macfilter"

-- Device name file
DEVICE_NAMES_FILE = "/etc/app/device_names"

-- DHCP lease file
DHCP_LEASE_FILEPATH = "/var/dhcp.leases"
-- DHCP deny list file
DHCP_DENYLIST_FILEPATH = "/etc/config/firewall.mac.list"

-- Wan status
WAN_MONITOR_STAT_FILEPATH = "/tmp/wan.monitor.stat"

-- Rom Version
XQ_ROM_VERSION_FILEPATH = "/usr/share/xiaoqiang/xiaoqiang_version"
-- Log file
XQ_LOG_JSON_FILEPATH = "/tmp/log.json"
XQ_CONFIG_JSON_FILEPATH = "/tmp/config.json"
-- Wifi Passport Error
XQ_WIFIPWDERROR_FILEPATH = "/tmp/wifi_error_xxxx"
-- Change log
XQ_CHANGELOG_FILEPATH = "/usr/share/xiaoqiang/changelog"

-- Download Rom file
ROM_CACHE_FILEPATH = "/tmp/rom.bin"
ROM_DISK_CACHE_FILEPATH = "/userdisk/rom.bin"
-- Upload Rom file
CROM_CACHE_FILEPATH = "/tmp/customrom.bin"
CROM_DISK_CACHE_FILEPATH = "/userdisk/upload/customrom.bin"
USERDISK_UPLOAD_DIR = "/userdisk/upload/"
-- Download Rom dir
USERDISK_DOWNLOAD_DIR = "/userdisk/download/"

-- Download Uboot file
UBOOT_CACHE_FILEPATH = "/tmp/uboot.bin"

-- OUI File: from http://standards.ieee.org/develop/regauth/oui/public.html
OUI_ZIP_FILEPATH = "/usr/share/xiaoqiang/oui.zip"
OUI_FILEPATH = "/tmp/oui"

-- Version Info
XQ_ROM_VERSION = "uci get /usr/share/xiaoqiang/xiaoqiang_version.version.ROM"
XQ_CHANNEL = "uci get /usr/share/xiaoqiang/xiaoqiang_version.version.CHANNEL"
XQ_HARDWARE = "uci get /usr/share/xiaoqiang/xiaoqiang_version.version.HARDWARE"
XQ_CFE_VERSION = "uci get /usr/share/xiaoqiang/xiaoqiang_version.version.UBOOT"
XQ_KERNEL_VERSION = "uci get /usr/share/xiaoqiang/xiaoqiang_version.version.LINUX"
XQ_RAMFS_VERSION = "uci get /usr/share/xiaoqiang/xiaoqiang_version.version.RAMFS"
XQ_SQAFS_VERSION = "uci get /usr/share/xiaoqiang/xiaoqiang_version.version.SQAFS"
XQ_ROOTFS_VERSION = "uci get /usr/share/xiaoqiang/xiaoqiang_version.version.ROOTFS"
XQ_DEVICE_ID = "uci get /etc/config/messaging.deviceInfo.DEVICE_ID"

XQ_CUT_IMAGE = "cd /tmp;multipartcutter -v -R -f "
XQ_VERIFY_IMAGE = "cd /tmp;mkxqimage -x "

OPEN_WPS = "wps pbc"
GET_WPS_STATUS = "wps status"
GET_WPS_CONMAC = "wps stamac"
CLOSE_WPS = "wps stop"
GET_DEFAULT_MACADDRESS = "getmac"
GET_NVRAM_SN = "nvram get SN"
GET_BDATA_SN = "bdata get SN"
NVRAM_SET_UPGRADED = "nvram set flag_upgrade_push=1; nvram commit"

NGINX_CACHE_START = "/usr/sbin/sysapi TRAFFIC_CTL set NGINX_CACHE=on"
NGINX_CACHE_STOP = "/usr/sbin/sysapi TRAFFIC_CTL set NGINX_CACHE=off"
NGINX_CACHE_STATUS = "/usr/sbin/sysapi TRAFFIC_CTL get NGINX_CACHE"

SET_LAN_BLACKLIST = "/usr/sbin/sysapi macfilter set lanmode=blacklist"
SET_LAN_WHITELIST = "/usr/sbin/sysapi macfilter set lanmode=whitelist"
SET_WAN_BLACKLIST = "/usr/sbin/sysapi macfilter set wanmode=blacklist"
SET_WAN_WHITELIST = "/usr/sbin/sysapi macfilter set wanmode=whitelist"
SET_ADMIN_BLACKLIST = "/usr/sbin/sysapi macfilter set admin=blacklist"
SET_ADMIN_WHITELIST = "/usr/sbin/sysapi macfilter set admin=whitelist"
GET_LAN_MODE = "/usr/sbin/sysapi macfilter get lanmode"
GET_WAN_MODE = "/usr/sbin/sysapi macfilter get wanmode"
GET_ADMIN_MODE = "/usr/sbin/sysapi macfilter get adminmode"

LAMP_CREATE_SANDBOX = "/opt/lampmanager/create_sandbox.sh"
LAMP_IS_SANDBOX_CREATED = "/opt/lampmanager/is_sandbox_created.sh"
LAMP_MOUNT_THINGS = "/opt/lampmanager/mount_things.sh"
LAMP_UMOUNT_THINGS = "/opt/lampmanager/unmount_things.sh"
LAMP_ARE_THINGS_MOUNTED = "/opt/lampmanager/are_things_mounted.sh"
LAMP_START_DROPBEAR = "/opt/lampmanager/start_dropbear.sh"
LAMP_STOP_DROPBEAR = "/opt/lampmanager/stop_dropbear.sh"
LAMP_IS_DROPBEAR_STARTED = "/opt/lampmanager/is_dropbear_started.sh"

CPU_TEMPERATURE = "/usr/sbin/readtmp"
SIMPLE_NETWORK_DETECT = "/usr/sbin/networkdt -s "
SIMPLE_NETWORK_NOLOG_DETECT = "/usr/sbin/networkdt -s -n "
FULL_NETWORK_DETECT = "/usr/sbin/networkdt "
WIFI_CHANNEL_24 = "wl -i wl1 chanspecs"
WIFI_CHANNEL_50 = "wl -i wl0 chanspecs"
WIFI24_WORK_CHANNEL = "wl -i wl1 chanspec | awk '{print $1}'"
WIFI50_WORK_CHANNEL = "wl -i wl0 chanspec | awk '{print $1}'"

GET_WAN_DEV = "ip route list 0/0 | grep -v tap | grep -v metric | awk '{print $5}'"
FLASH_EXECUTION_CHECK = "/bin/flash_check.sh"
FLASH_PID_TMP = "/tmp/pid_xxxx"
CRONTAB_PID_TMP = "/tmp/crontab_pid_xxxx"

CRONTAB_ROM_CHECK = [[ps w | grep crontab_rom.sh | grep -v "grep" | wc -l]]
CROM_FLASH_CHECK = [[ps w | grep flash | grep customrom.bin | grep -v "grep" | wc -l]]
DROM_FLASH_CHECK = [[ps w | grep flash | grep rom.bin | grep -v "grep" | wc -l]]
REBOOT_CHECK = [[ps w | grep reboot | grep -v "grep" | wc -l]]

UPGRADE_LOCK_FILE = "/tmp/upgrade_lock"
UPGRADE_STATUS = "cat "..UPGRADE_LOCK_FILE
UPGRADE_LOCK = "/bin/touch "..UPGRADE_LOCK_FILE
UPGRADE_UNLOCK = "/bin/rm "..UPGRADE_LOCK_FILE
UPGRADE_LOCK_CHECK = "/bin/ls -l "..UPGRADE_LOCK_FILE
UPGRADE_PID = "cat "..CRONTAB_PID_TMP
UPGRADE_LUA_PID = [[ps w | grep checkupgrade.lua | grep -v "grep" | awk '{print $1}']]

GET_CPU_CHIPPKG = "cat /proc/cpuinfo | grep b_chippkg | awk '{print $3}'"

DOWNLOAD_RESOURCE_CHECK = "wget -t3 -T10 --spider "

-- 1K-blocks
AVAILABLE_MEMERY = [[df -k | grep /tmp$ | awk '{print $4}']]
AVAILABLE_DISK = [[df -k | grep /userdisk$ | awk '{print $4}']]
DISK_SPACE = [[df -k | grep /userdisk$ | awk '{print $2}']]

DEVICE_STATISTICS_LIST_LIMIT = 10

-- CPU Avg
CPU_LOAD_AVG = "/usr/sbin/getstat.lua|cut -d'%' -f1"
MEMERY_USAGE = "free 2>/dev/null|awk '/Mem/{print substr($3/$2,0,4)}'"
WAN_LINK = "et robord 0x01 0x00 2>/dev/null|awk -F':' '/port 4/{print$2}'"
WAN_UP = "cat /tmp/wan.monitor.stat | grep WANLINKSTAT=UP | wc -l"

-- Update led flash alert
UPDATE_LED_FLASH_ALERT_ENABLE = "updateledfliker"
UPDATE_LED_FLASH_ALERT_DISABLE = "killupdateled"

VPN_ENABLE = "/usr/sbin/vpn.lua up"
VPN_DISABLE = "/usr/sbin/vpn.lua down"
VPN_STATUS = "/usr/sbin/vpn.lua status"
RM_VPNSTATUS_FILE = "/bin/rm /tmp/vpn.stat.msg.last >/dev/null 2>/dev/null"

GPIO_VALUE = "gpio %s | awk -F': <' '{print$2}'| awk -F'>' '{print$1}'"

-- UPnP
UPNP_STATUS = "/etc/init.d/miniupnpd enabled"
UPNP_ENABLE = "/etc/init.d/miniupnpd enable ; /etc/init.d/miniupnpd start"
UPNP_DISABLE = "/etc/init.d/miniupnpd stop ; /etc/init.d/miniupnpd disable"
UPNP_LEASE_FILE = "uci get upnpd.config.upnp_lease_file"

-- QoS
QOS_APPSL_ENABLE = "/etc/init.d/app-tc.d on"
QOS_APPSL_DISABLE = "/etc/init.d/app-tc.d off"
QOS_APPSL_RELOAD = "/etc/init.d/app-tc.d restart"

UPGRADE_INFO_CACHE = "upgrade_info_cache"
UPGRADE_INFO_EXPIRE = 600

THRIFT_TUNNEL_TO_DATACENTER = "thrifttunnel 0 '%s'"
THRIFT_TUNNEL_TO_SMARTHOME = "thrifttunnel 1 '%s'"
THRIFT_TUNNEL_TO_SMARTHOME_CONTROLLER = "thrifttunnel 2 '%s'"
THRIFT_TO_MQTT_IDENTIFY_DEVICE = "thrifttunnel 3 ''"
THRIFT_TO_MQTT_GET_SN = "thrifttunnel 4 ''"
THRIFT_TO_MQTT_GET_DEVICEID = "thrifttunnel 5 ''"
THRIFT_TUNNEL_TO_MIIO = "thrifttunnel 6 '%s'"
THRIFT_TUNNEL_TO_YEELINK = "thrifttunnel 7 '%s'"

TUNNEL_TOOL = "/opt/filetunnel/tunneltool --payload '%s'"

WIIF_LOG_TMP_FILEPATH = "/tmp/wifi.log"
WIFI_LOG_COLLECTION = "/sbin/wifi_analyze.sh >> "..WIIF_LOG_TMP_FILEPATH

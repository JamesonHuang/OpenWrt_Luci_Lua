module("luci.controller.web.index", package.seeall)

function index()
    local root = node()
    if not root.target then
        root.target = alias("web")
        root.index = true
    end
    local page   = node("web")
    page.target  = firstchild()
    page.title   = _("")
    page.order   = 10
    page.sysauth = "admin"
    page.mediaurlbase = "/xiaoqiang/web"
    page.sysauth_authenticator = "htmlauth"
    page.index = true
    entry({"web"}, template("web/index"), _("路由器状态"), 10, 0x08)
    entry({"web", "home"}, template("web/index"), _("路由器状态"), 70, 0x08)
    entry({"web", "manager"}, template("web/manager"), _("终端管理"), 71)
    --entry({"web", "plugin"}, template("web/plugin"), _("插件管理"), 72)
    --entry({"web", "plugin", "kuaipan"}, template("web/plugins/kuaipan"), _("插件管理_快盘"), 72)
    --entry({"web", "plugin", "guest"}, template("web/plugins/guest"), _("插件管理_访客"), 72)
    entry({"web", "logout"}, call("action_logout"), 11, 0x09)
    entry({"web", "init"}, template("web/init/hello"), _("初始化引导"), 190, 0x09)
    entry({"web", "init", "hello"}, template("web/init/hello"), _("欢迎界面"), 198, 0x09)   --不需要登录
    entry({"web", "init", "agreement"}, template("web/init/agreement"), _("用户协议"), 198, 0x09)   --不需要登录
    entry({"web", "init", "guide"}, template("web/init/guide"), _("引导模式"), 190, 0x08)

    entry({"web", "netset"}, template("web/netset"), _("路由设置"), 73)
    entry({"web", "sysset"}, template("web/sysset"), _("路由设置"), 73)
    entry({"web", "sysset", "passport"}, template("web/setting/passport"), _("路由器权限"), 18)
    entry({"web", "sysset", "reboot"}, template("web/setting/reboot"), _("重启路由器"), 73)
    entry({"web", "sysset", "reset"}, template("web/setting/reset"), _("恢复出厂设置"), 73)

    entry({"web", "netset", "wifi"}, template("web/setting/wifi_set"), _("WIFI网络设置"), 20)
    entry({"web", "netset", "wifi_mini"}, template("web/setting/wifi_set_mini"), _("WIFI网络快捷设置"), 20)
    entry({"web", "netset", "wifi_pro"}, template("web/setting/wifi_set_pro"), _("WIFI网络高级设置"), 60)
    entry({"web", "netset", "wifi_txpwr"}, template("web/setting/wifi_txpwr"), _("WIFI强度设置"), 60)
    entry({"web", "netset", "wifi_filter"}, template("web/setting/wifi_filter"), _("WIFI访问控制"), 60)

    entry({"web", "netset" ,"net_wan"}, template("web/setting/net_wan"), _("网络设置WAN"), 20)
    entry({"web", "netset", "net_lan"}, template("web/setting/net_lan"), _("网络设置LAN"), 30)
    entry({"web", "netset", "mac"}, template("web/setting/net_setup_mac"), _("mac 设置"), 40)
    entry({"web", "netset", "ipmacband"}, template("web/setting/net_ipmacband"), _("mac 设置"), 40)
    entry({"web", "sysset", "qos_pro"}, template("web/setting/qos_pro"), _("QoS 设置"), 40)


    entry({"web", "sysset", "upgrade"}, template("web/setting/upgrade"), _("路由器固件升级"), 198, 0x01)
    entry({"web", "sysset", "upgrade_manual"}, template("web/setting/upgrade_manual", _("路由器手动升级"), 200))
    entry({"web", "sysset", "log"}, template("web/setting/log", _("上传日志"), 201))
    --entry({"web", "sysset", "upload_config"}, template("web/setting/upload_config"), _("上传配置信息"), 202)

    --entry({"web", "setting", "sys_psp"}, template("web/setting/sys_psp"), _("管理小米账号"), 73)
    entry({"web", "sysset", "sys_status"}, template("web/setting/sys_status"), _("系统状态"), 73)

    entry({"web", "sysset", "diskformat"}, template("web/setting/diskformat"), _("格式化小强盘"), 202)
    entry({"web", "sysset", "nginx"}, template("web/setting/nginx"), _("关闭NGINX"), 203)
    entry({"web", "sysset", "upnp"}, template("web/setting/upnp"), _("upnp"), 204)
    -- entry({"web", "sysset", "lamp"}, template("web/setting/lamp"), _("LAMP Settings"), 204)
    entry({"web", "sysset", "qos"}, template("web/setting/qos"), _("应用限速"), 204)
    entry({"web", "sysset", "vpn"}, template("web/setting/vpn"), _("VPN"), 204)

    entry({"web", "sysset", "developer"}, template("web/setting/developer"), _("开发者选项"), 205)
    entry({"web", "sysset", "dmz"}, template("web/setting/dmz"), _("DMZ"), 205)
    entry({"web", "sysset", "ddns"}, template("web/setting/ddns"), _("DDNS"), 204)
    entry({"web", "sysset", "nat"}, template("web/setting/nat"), _("端口转发"), 206)
    entry({"web", "sysset", "noflushd"}, template("web/setting/noflushd"), _("磁盘休眠"), 207)
    --entry({"web", "sysset", "predownload"}, template("web/setting/predownload"), _("预下载"), 208)

    entry({"web", "detecte"}, template("web/netdetection"), _("网络检测"), 74, 0x01)
    entry({"web", "detecte_pro"}, template("web/urldetection"), _("网络高级检测"), 75, 0x01)
    entry({"web", "xmaccount"}, template("web/xmaccount"), _("小米帐号验证"), 75, 0x01)
    -- entry({"web", "safeurl"}, call("action_safeurl"), _(""), 75, 0x09)

    entry({"web", "webinitrdr"}, template("web/init/webinitrdr"), _("劫持页面"), 300, 0x09)   --不需要登录
end
function action_logout()
    local dsp = require "luci.dispatcher"
    local sauth = require "luci.sauth"
    if dsp.context.authsession then
        sauth.kill(dsp.context.authsession)
        dsp.context.urltoken.stok = nil
    end
    luci.http.header("Set-Cookie", "sysauth=; path=" .. dsp.build_url())
    luci.http.header("Set-Cookie", "autologin_v2=;expires=-1;path=/;")
    luci.http.redirect(luci.dispatcher.build_url())
end

function action_safeurl()

    local safeUrl = luci.http.formvalue("safeurl")
    require("luci.template")
    luci.template.render("web/safeurl", {safeurl=safeUrl})

end


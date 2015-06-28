module("luci.controller.mobile.index", package.seeall)
function index()
    local root = node()
    if not root.target then
        root.target = alias("mobile")
        root.index = true
    end
    local page   = node("mobile")
    page.target  = firstchild()
    page.title   = _("")
    page.order   = 110
    page.sysauth = "admin"
    page.mediaurlbase = "/xiaoqiang/mobile"
    page.sysauth_authenticator = "htmlauth_moblie"
    page.index = true
    entry({"mobile"}, template("mobile/home"), _("首页"), 1, 0x08)
    entry({"mobile", "logout"}, call("action_logout"), 2, 0x09)
    entry({"mobile", "hello"}, template("mobile/init/hello"), _("初始化欢迎界面"), 3, 0x09)
    entry({"mobile", "agreement"}, template("mobile/init/agreement"), _("查看协议"), 4, 0x09)
    entry({"mobile", "guide"}, template("mobile/init/guide"), _("初始化引导"), 5, 0x08)
end

function action_logout()
    local dsp = require "luci.dispatcher"
    local sauth = require "luci.sauth"
    if dsp.context.authsession then
        sauth.kill(dsp.context.authsession)
        dsp.context.urltoken.stok = nil
    end
    luci.http.header("Set-Cookie", "sysauth=; path=" .. dsp.build_url())
    luci.http.redirect(luci.dispatcher.build_url().."/mobile")
end

module("luci.controller.dispatch.index", package.seeall)

function index()
    local root = node()
    if not root.target then
        root.target = alias("dispatch")
        root.index = true
    end
    local page   = node("dispatch")
    page.target  = firstchild()
    page.title   = _("")
    page.order   = 1
    page.sysauth = "admin"
    page.mediaurlbase = "/xiaoqiang/dispatch"
    page.sysauth_authenticator = "htmlauth"
    page.index = true
    entry({"dispatch"}, template("index"), _("跳转"), 1, 0x09)
end
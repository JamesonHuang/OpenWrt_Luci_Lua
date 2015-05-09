module("luci.controller.service.index", package.seeall)
function index()
    local page   = node("service")
    page.target  = firstchild()
    page.title   = _("")
    page.order   = nil
    page.sysauth = "admin"
    page.sysauth_authenticator = "jsonauth"
    page.index = true
end

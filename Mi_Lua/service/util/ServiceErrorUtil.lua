module ("service.util.ServiceErrorUtil", package.seeall)

function getErrorMessage(errorCode)
    local errorList = {}

    errorList[0] = _("")
    errorList[1] = _("parameter missing")
    errorList[2] = _("Parameter empty")
    errorList[3] = _("Parameter format error")
    errorList[5] = _("invalid app id")
    
    errorList[1056] = _("invalid device id")
    errorList[1057] = _("resource is not ready")

    errorList[1559] = _("datacenter error")

    errorList[2010] = _("datacenter error")

    if (errorList[errorCode] == nil) then
        return translate(_("未知错误"))
    else
        return translate(errorList[errorCode])
    end
end

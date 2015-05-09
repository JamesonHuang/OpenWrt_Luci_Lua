module ("MZLog", package.seeall)

function log(...)
	local posix = require("posix")
	local util = require("luci.util")
	local priority = arg[1]
	if priority and tonumber(priority) then
		posix.openlog("luci", "nep", LOG_USER)
		for i = 2, arg.n do
			posix.syslog(priority, util.serialize_data(arg[i]))
		end
		posix.closelog()
	end
end

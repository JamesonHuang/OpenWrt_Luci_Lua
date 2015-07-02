local posix  = require "Posix"

function test_timeout()
	--posix.sleep(5)
    local ret = {}
	ret["result"] = true
	luci.http.write_json(ret)
end



-- called by trafficd from c
-- yubo@xiaomi.com
-- 2014-09-05

local dev
local equ
local dbDict
local dhcpDict


function get_hostname_init()
	dev = require("xiaoqiang.util.XQDeviceUtil")
	equ = require("xiaoqiang.XQEquipment")
	dbDict = dev.getDeviceInfoFromDB()
	dhcpDict = dev.getDHCPDict()
end

function get_hostname(mac)
	local hostname
	if dbDict[mac] and dbDict[mac]['nickname'] ~= '' then
		hostname = dbDict[mac]['nickname']
	else
		local dhcpname = dhcpDict[mac] and dhcpDict[mac]['name'] or ''
		if dhcpname == '' then
			local t = equ.identifyDevice(mac, '')
			hostname = t.name
		else
			local t = equ.identifyDevice(mac, dhcpname)
			if t.type.p + t.type.c > 0 then
				hostname = t.name
			else
				hostname = dhcpname
			end
		end
	end
	return hostname == '' and mac or hostname
end

function get_wan_dev_name()
	local ubus = require ("ubus")
	local conn = ubus.connect()
	if not conn then
		elog("Failed to connect to ubusd")
	end
	local status = conn:call("network.interface.wan", "status",{})
	conn:close()
	return (status.l3_device and status.l3_device) or status.device
end

function get_lan_dev_name()
	local ubus = require ("ubus")
	local conn = ubus.connect()
	if not conn then
		elog("Failed to connect to ubusd")
	end
	local status = conn:call("network.interface.lan", "status",{})
	conn:close()
	return (status.l3_device and status.l3_device) or status.device
end

function trafficd_lua_done()
	os.execute("killall -q -s 10 noflushd");
end

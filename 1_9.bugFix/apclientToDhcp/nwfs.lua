
--nwfs.lua  & fix first version
function cancelapclient()
		if "" ~= ssid_2g then
			uci.set("wireless", "mt7628iface", "ssid", ssid_2g)
		else
            cmd = "eth_mac r wl0"
	        local macaddr = lue(cmd)
			uci.set("wireless", "mt7628iface", "ssid", "Meizu-R13-"..string.sub(macaddr, -4, -1))
        end
		if "" ~= ssid_5g then
			uci.set("wireless", "mt7610eiface", "ssid", ssid_5g)
        else
            cmd = "eth_mac r wl1"
	        local macaddr = lue(cmd)
			uci.set("wireless", "mt7610eiface", "ssid", "Meizu-R13-5G-"..string.sub(macaddr, -4, -1))
		end
		uci.commit("wireless")
end

--nwfs.lua  & fix second version
function cancelapclient()
	local uci = require("luci.model.uci").cursor()
	local apc = uci.get("network", "lan", "apclient")
	if nil ~= apc then
		local dbssid = dbfs.fetch_ssid()
		local ssid_2g = ""
		local ssid_5g = ""
		if nil ~= dbssid then
			for k, v in pairs(dbssid) do
				ssid_2g = v.ssid24
				ssid_5g = v.ssid5
			end
		end
	--local cmd = [[apcli_connect.sh disable 111 222]]
	local macaddr_2g = lue("eth_mac r wl0")
    local macaddr_5g = lue("eth_mac r wl1")
	local cmd = "apcli_connect.sh disable Meizu-R13-"..string.sub(macaddr_2g, -6, -5)..string.sub(macaddr_2g, -3, -2)..
                    " Meizu-R13-5G-"..string.sub(macaddr_5g, -6, -5)..string.sub(macaddr_5g, -3, -2)
    require "MZLog".log(3, cmd)
    lue(cmd)
    if "" ~= ssid_2g then
		uci.set("wireless", "mt7628iface", "ssid", ssid_2g)
	end
	if "" ~= ssid_5g then
		uci.set("wireless", "mt7610eiface", "ssid", ssid_5g)
	end
	uci.commit("wireless")
	end
end



--dbfs
function fetch_ssid()
    r, msg = pcall(function() db:rows(sqlStr) end)
    if r == false then
	    require "MZLog".log(3, "r false")
        db:close()
	    return result
    end
end


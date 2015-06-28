local pcall, dofile, _G = pcall, dofile, _G

module "luci.version"

if pcall(dofile, "/etc/openwrt_release") and _G.DISTRIB_DESCRIPTION then
	distname    = ""
	distversion = _G.DISTRIB_DESCRIPTION
else
	distname    = "OpenWrt Firmware"
	distversion = "Attitude Adjustment (r40348)"
end

luciname    = "LuCI 0.11.1 Release"
luciversion = "0.11.1"

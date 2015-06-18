module("meizu.dbfs", package.seeall)

local sqlite3 = require("lsqlite3")
local r13db = "/etc/r13db"

function database_busy()
	return true
end

function updateDeviceNickname(mac, nickname)
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("update arp set devicename = '%s' where mac = '%s'", nickname, mac)
	db:exec(sqlStr)
	return db:close()
end

function init_arp_table()
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("create table if not exists arp(mac varchar(18), ip varchar(16), wifi integer, devicename varchar(100), orgname varchar(100))")
	db:exec(sqlStr)
	return db:close()
end

function fetch_all_arp()
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("select * from arp")
	local result = {}
	for row in db:rows(sqlStr) do
		if row then
			table.insert(result,{
				["mac"]   = row[1],
				["ip"] = row[2],
				["wifi"] = row[3],
				["devicename"] = row[4],
				["orgname"] = row[5]
			})
		end
	end
	db:close()
	return result
end

function fetch_arp(mac)
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("select * from arp where mac = '%s' limit 1", mac)
	local result = {}
	for row in db:rows(sqlStr) do
		if row then
			table.insert(result,{
				["mac"]   = row[1],
				["ip"] = row[2],
				["wifi"] = row[3],
				["devicename"] = row[4],
				["orgname"] = row[5]
			})
		end
	end
	db:close()
	return result
end

function insert_arp_macip(mac, ip, wifi, devicename, orgname)
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("insert into arp values('%s', '%s', %d, '%s', '%s')", mac, ip, wifi, devicename, orgname)
	db:exec(sqlStr)
	return db:close()
end

function delete_arp_all_mac()
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("delete from arp")
	db:exec(sqlStr)
	return db:close()
end

function update_arp(mac, ip, wifi)
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("update arp set ip = '%s', wifi = %d where mac = '%s'", ip, wifi, mac)
	db:exec(sqlStr)
	return db:close()
end

function init_access_token_table()
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("create table if not exists accessTokenTable(token varchar(100), expireTime bigint)")
	db:exec(sqlStr)
	return db:close()
end

function add_access_token(token, expireTime)
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("insert into accessTokenTable values('%s', %d)", token, expireTime)
	db:exec(sqlStr)
	return db:close()
end

function fetch_access_token()
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("select * from accessTokenTable")
	local result = {}
	for row in db:rows(sqlStr) do
		if row then
			table.insert(result,{
				["token"]   = row[1],
				["expireTime"] = row[2]
			})
		end
	end
	db:close()
	return result
end

function update_access_token(oldToken, newToken, expireTime)
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("update accessTokenTable set token = '%s', expireTime = %d where token = '%s'", newToken, expireTime, oldToken)
	db:exec(sqlStr)
	return db:close()
end

function delete_access_token()
        local db = sqlite3.open(r13db)
        local sqlStr = string.format("delete from accessTokenTable")
        db:exec(sqlStr)
        return db:close()
end

function init_kv_table()
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("create table if not exists kv(k varchar(50) primary key, v varchar(50))")
	db:exec(sqlStr)
	return db:close()
end

function save_kv(key, value)
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("insert or replace into kv values('%s', '%s')", key, value)
	db:exec(sqlStr)
	return db:close()
end

function fetch_kv(key)
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("select v from kv where k='%s' limit 1", key)
	local result = {}
	for row in db:rows(sqlStr) do
		if row then
			table.insert(result,{
				["value"]   = row[1],
			})
		end
	end
	db:close()
	return result
end

--------

function init_deny_mac_table()
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("create table if not exists denymac(mac varchar(50))")
	db:exec(sqlStr)
	return db:close()
end

function add_deny_mac(mac)
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("insert into denymac values('%s')", mac)
	db:exec(sqlStr)
	return db:close()
end

function fetch_all_deny_mac()
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("select * from denymac")
	local result = {}
	for row in db:rows(sqlStr) do
		if row then
			table.insert(result,{
				["mac"]   = row[1]
			})
		end
	end
	db:close()
	return result
end

function delete_deny_mac(mac)
        local db = sqlite3.open(r13db)
        local sqlStr = string.format("delete from denymac where mac = '%s'", mac)
        db:exec(sqlStr)
        return db:close()
end

function init_ssid_table()
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("create table if not exists ssid(ssid24 varchar(50), ssid5 varchar(50))")
	db:exec(sqlStr)
	return db:close()
end

function add_ssid(ssid24, ssid5)
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("insert into ssid values('%s', '%s')", ssid24, ssid5)
	db:exec(sqlStr)
	return db:close()
end

function fetch_ssid()
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("select ssid24,ssid5 from ssid limit 1")
	local result = {}
	
	require "MZLog".log(3, "rowstest")
	r, msg = pcall(function() db:rows(sqlStr) end)
	if r == false then
		require "MZLog".log(3, "r false")
		return result
	end
	require "MZLog".log(3, "rowstest2")
	
	for row in db:rows(sqlStr) do
		require "MZLog".log(3, "row test")
		if row then
			table.insert(result,{
				["ssid24"]   = row[1],
				["ssid5"]   = row[2]
			})
			require "MZLog".log(3, "table insert in")
		end
		require "MZLog".log(3, "table insert out")
	end

	require "MZLog".log(3, "res test")
	require "MZLog".log(3, result)
	require "MZLog".log(3, "res test")
	db:close()
	return result
end

function update_ssid(ssid24, ssid5)
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("update ssid set ssid24 = '%s', ssid5 = '%s'", ssid24, ssid5)
	db:exec(sqlStr)
	return db:close()
end

function initBluetoothTable()
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("create table if not exists  blemeshtable(id varchar(100), mac varchar(100), key varchar(100), name varchar(100), deviceType varchar(100), len varchar(100))")
	db:exec(sqlStr)
	return db:close()
end

function addBluetoothDevice(id, mac, key, name, deviceType, len)
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("insert into blemeshtable values('%s', '%s', '%s', '%s', '%s', '%s')", id, mac, key, name, deviceType, len)
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function fetchAllBluetoothDevice()
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("select * from blemeshtable")
	db:busy_handler(database_busy)
	local result = {}
	for row in db:rows(sqlStr) do
		if row then
			table.insert(result,{
				["mac"] = row[2],
				["deviceType"] = row[5]
			})
		end
	end
	db:close()
	return result
end

function deleteBluetoothDevice(mac)
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("delete from blemeshtable where mac = '%s'", mac)
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function updateBluetoothDevice(id, key, name, len, mac)
    local db = sqlite3.open(r13db)
    local sqlStr = string.format("update blemeshtable set id = '%s', key = '%s', name = '%s', len = '%s' where mac = '%s'", id, key, name, len, mac)
	db:busy_handler(database_busy)
    db:exec(sqlStr)
    return db:close()
end

function fetchBluetoothDevice(mac)
    local db = sqlite3.open(r13db)
    local sqlStr = string.format("select * from blemeshtable where mac = '%s'", mac)
	db:busy_handler(database_busy)
    local result = {}
	for row in db:rows(sqlStr) do
        if row then
			table.insert(result,{
	   			["id"]  = row[1],
			    ["mac"] = row[2],
				["deviceType"] = row[5],
				["name"] = row[4]
			})
        end
    end
	db:close()
    return result
end

function fetchBluetoothDeviceKey()
    local db = sqlite3.open(r13db)
    local sqlStr = string.format("select * from blemeshtable where key != ''")
	db:busy_handler(database_busy)
    local result = {}
	for row in db:rows(sqlStr) do
        if row then
			table.insert(result,{
			    ["mac"] = row[2],
				["key"] = row[3]
			})
        end
    end
	db:close()
    return result
end

function getBluetoothDeviceMac()
    local db = sqlite3.open(r13db)
    local sqlStr = string.format("select * from blemeshtable where deviceType == 'FF'")
	db:busy_handler(database_busy)
    local result = ""
	for row in db:rows(sqlStr) do
        if row then
            result = row[2]
        end
    end
	db:close()
    return result
end

function getBluetoothDevice(id)
    local db = sqlite3.open(r13db)
    local sqlStr = string.format("select * from blemeshtable where id = '%s'", id)
	db:busy_handler(database_busy)
    local result = ""
	for row in db:rows(sqlStr) do
        if row then
            result = row[2]
        end
    end
	db:close()
    return result
end

function fetchAllBleMeshDevice()
	local db = sqlite3.open(r13db)
	local sqlStr = string.format("select * from blemeshtable where id !=''")
	db:busy_handler(database_busy)
	local result = {}
	for row in db:rows(sqlStr) do
		if row then
			table.insert(result,{
	  			["mac"] = row[2],
				["deviceType"] = row[5],
				["name"] = row[4]
			})
		end
	end
	db:close()
	return result
end

function getBleDeviceNameLength(id)
    local db = sqlite3.open(r13db)
    local sqlStr = string.format("select * from blemeshtable where id = '%s'", id)
	db:busy_handler(database_busy)
    local result = ""
	for row in db:rows(sqlStr) do
        if row then
            result = row[6]
        end
    end
	db:close()
    return result
end

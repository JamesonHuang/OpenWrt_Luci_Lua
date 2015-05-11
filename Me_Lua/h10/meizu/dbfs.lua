module ("meizu.dbfs", package.seeall)

local sqlite3 = require("lsqlite3")
local r10db = "/etc/r10db"

function database_busy()
	return true
end

function updateDeviceNickname(mac, nickname)
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("update maclist set devicename = '%s' where mac = '%s'", nickname, mac)
    db:busy_handler(database_busy)
    db:exec(sqlStr)
    return db:close()
end

function get_dev_nick_name(mac)
    local db = sqlite3.open(r10db)
	local sqlStr = string.format("select devicename, orgname from maclist where mac like '%s'", mac)
	db:busy_handler(database_busy)
	local nickname = ""
	for row in db:rows(sqlStr) do
		if row[1] ~= "" then
			nickname = row[1]
		else
			if row[2] ~= "" then
				nickname = row[2]
			end
		end
    end
	db:close()
    return nickname
end

function initSmbBanTable()
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("create table if not exists SmbBanTable(mac varchar(100), smb_ban varchar(100))")
	db:exec(sqlStr)
	return db:close()
end

function addSmbBanList(mac, smb_ban)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("insert into SmbBanTable values('%s', '%s')", mac, smb_ban)
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function fetchSmbBanList()
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("select * from SmbBanTable where smb_ban = 'true'")
    db:busy_handler(database_busy)
    local result = {}
    for row in db:rows(sqlStr) do
        if row then
            table.insert(result,{
                 ["mac"] = row[1]
            })
        end
    end
    db:close()
    return result
end

function deleteSmbBanList(mac)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("delete from SmbBanTable where mac = '%s' and smb_ban = 'true'", mac)
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function fetchDenyDeviceInfo(mac)
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("select * from maclist where mac = '%s'", mac)
    db:busy_handler(database_busy)
    local result = {}
    for row in db:rows(sqlStr) do
        if row then
            table.insert(result,{
                ["mac"] = row[1],
                ["orgname"] = row[2],
                ["devicename"] = row[3],
                ["ip"] = row[4]
            })
        end
    end
    db:close()
    return result
end

function change_maclist_table()
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("ALTER TABLE maclist ADD COLUMN ip varchar(100)")
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function fetchAllDeviceInfo()
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("select * from maclist")
    db:busy_handler(database_busy)
    local result = {}
    for row in db:rows(sqlStr) do
        if row then
            table.insert(result,{
                 ["mac"] = row[1],
                ["orgname"] = row[2],
                ["devicename"] = row[3]
            })
        end
    end
    db:close()
    return result
end

function updateDeviceOrgname(mac, orgname)
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("update maclist set orgname = '%s' where mac = '%s'", orgname, mac)
    db:busy_handler(database_busy)
    db:exec(sqlStr)
    return db:close()
end

function saveDeviceInfo(mac, orgname, devicename, deviceip)
    local db = sqlite3.open(r10db)
    local fetch = string.format("select * from maclist where mac = '%s'", mac)
    db:busy_handler(database_busy)
    local exist = false
    for row in db:rows(fetch) do
        if row then
            exist = true
        end
    end
    local sqlStr
    if not exist then
        sqlStr = string.format("insert into maclist values('%s','%s','%s', '%s')", mac, orgname, devicename, deviceip)
    else
        sqlStr = string.format("update maclist set mac = '%s', orgname = '%s', devicemame = '%s', ip = '%s' where mac = '%s'", mac, orgname, devicename, deviceip, mac)
    end
    db:exec(sqlStr)
    return db:close()
end

function initBatchFileTable()
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("create table if not exists batchFilesTables(hashCode varchar(100), IMEI varchar(100))")
	db:exec(sqlStr)
	return db:close()
end

function fetchAllFilesIndex(ID, path)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("select * from FilesListTable where ID = '%s' and path = '%s'", ID, path)
	db:busy_handler(database_busy)
	local result = {}
	for row in db:rows(sqlStr) do
		if row then
			table.insert(result,{
				["name"]   = row[3]
			})
		end
	end
	db:close()
	return result
end

function fetchAllFilesList(ID, start, fetchEnd, path)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("select * from FilesListTable where ID = '%s' and path = '%s' ORDER BY isfile, name limit '%s' offset '%s' ", ID, path,  fetchEnd, start)
	db:busy_handler(database_busy)
	local result = {}
	for row in db:rows(sqlStr) do
		if row then
			table.insert(result,{
				["name"]   = row[3],
				["isfile"] = row[4],
				["size"]   = row[5],
				["time"]   = row[6]
			})
		end
	end
	db:close()
	return result
end



function addBatchFile(hashCode, IMEI)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("insert into batchFilesTables values('%s', '%s')", hashCode, IMEI)
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function fetchAllBatchFile(IMEI)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("select * from batchFilesTables where IMEI = '%s'",IMEI )
	db:busy_handler(database_busy)
	local result = {}
	for row in db:rows(sqlStr) do
		if row then
			table.insert(result,{
				["hashCode"] = row[1]
			})
		end
	end
	db:close()
	return result
end

function fetchBatchFileEndFlag(flag, IMEI)
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("select * from batchFilesTables where hashCode = '%s' and IMEI = '%s'", flag, IMEI)
    db:busy_handler(database_busy)
    local result = {}
    for row in db:rows(sqlStr) do
        if row then
            result = {
                ["hashCode"] = row[1]
            }
        end
    end
    db:close()
    return result
end

function deleteBatchFileEndFlag(flag, IMEI)
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("delete from batchFilesTables where hashCode = '%s' and IMEI = '%s'", flag, IMEI)
    db:busy_handler(database_busy)
    db:exec(sqlStr)
    return db:close()
end

function deleteBatchFile(IMEI)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("delete from batchFilesTables where IMEI = '%s'", IMEI)
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function init_arp_table()
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("create table if not exists arp(mac varchar(18), ip varchar(16))")
	db:exec(sqlStr)
	return db:close()
end

function fetch_arp_mac(mac)
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("select * from arp where mac like '%s'", mac)
    db:busy_handler(database_busy)
    local result = ""
	for row in db:rows(sqlStr) do
        if row then
            result = row[1]
        end
    end
	db:close()
    return result
end

function insert_arp_macip(mac, ip)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("delete from ")
	sqlStr = string.format("insert into arp values('%s', '%s')", mac, ip)
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function delete_arp_all_mac()
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("delete from arp")
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function init_wireless_device_table()
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("create table if not exists wireless_table(mac varchar(100))")
	db:exec(sqlStr)
	return db:close()
end

function fetch_wireless_device_mac(mac)
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("select * from wireless_table where mac = '%s'", mac)
    db:busy_handler(database_busy)
    local result = ""
	for row in db:rows(sqlStr) do
        if row then
            result = row[1]
        end
    end
	db:close()
    return result
end

function fetch_all_wireless_device()
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("select * from wireless_table")
    db:busy_handler(database_busy)
    local result = {}
    for row in db:rows(sqlStr) do
        if row then
            table.insert(result,{
                  ["mac"] = row[1]
            })
        end
    end
    db:close()
    return result
end

function insert_wireless_device_mac(mac)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("insert into wireless_table values('%s')", mac)
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function init_wire_device_table()
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("create table if not exists wire_table(mac varchar(100), ip varchar(100))")
	db:exec(sqlStr)
	return db:close()
end

function fetch_wire_device_mac(mac)
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("select * from wire_table where mac = '%s'", mac)
    db:busy_handler(database_busy)
    local result = ""
	for row in db:rows(sqlStr) do
        if row then
            result = row[1]
        end
    end
	db:close()
    return result
end

function fetch_all_wire_device()
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("select * from wire_table")
    db:busy_handler(database_busy)
    local result = {}
    for row in db:rows(sqlStr) do
        if row then
            table.insert(result,{
                 ["mac"] = row[1],
                ["ip"] = row[2]
            })
        end
    end
    db:close()
    return result
end

function insert_wire_device_mac(mac, ip)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("insert into wire_table values('%s','%s')", mac, ip)
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function update_wire_device_ip(mac, ip)
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("update wire_table set ip = '%s' where mac = '%s'", ip, mac)
    db:busy_handler(database_busy)
    db:exec(sqlStr)
    return db:close()
end

function delete_wire__device(mac)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("delete from wire_table where mac = '%s'", mac)
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function init_access_token_table()
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("create table if not exists accessTokenTable(token varchar(100), expireTime bigint)")
	db:exec(sqlStr)
	return db:close()
end

function add_access_token(token, expireTime)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("insert into accessTokenTable values('%s', %d)", token, expireTime)
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function fetch_access_token()
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("select * from accessTokenTable")
	db:busy_handler(database_busy)
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
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("update accessTokenTable set token = '%s', expireTime = %d where token = '%s'", newToken, expireTime, oldToken)
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function delete_access_token()
        local db = sqlite3.open(r10db)
        local sqlStr = string.format("delete from accessTokenTable")
        db:busy_handler(database_busy)
        db:exec(sqlStr)
        return db:close()
end

function initBluetoothTable()
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("create table if not exists  blemeshtable(id varchar(100), mac varchar(100), key varchar(100), name varchar(100), deviceType varchar(100), len varchar(100))")
	db:exec(sqlStr)
	return db:close()
end

function addBluetoothDevice(id, mac, key, name, deviceType, len)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("insert into blemeshtable values('%s', '%s', '%s', '%s', '%s', '%s')", id, mac, key, name, deviceType, len)
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function fetchAllBluetoothDevice()
	local db = sqlite3.open(r10db)
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
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("delete from blemeshtable where mac = '%s'", mac)
	db:busy_handler(database_busy)
	db:exec(sqlStr)
	return db:close()
end

function updateBluetoothDevice(id, key, name, len, mac)
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("update blemeshtable set id = '%s', key = '%s', name = '%s', len = '%s' where mac = '%s'", id, key, name, len, mac)
	db:busy_handler(database_busy)
    db:exec(sqlStr)
    return db:close()
end

function fetchBluetoothDevice(mac)
    local db = sqlite3.open(r10db)
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
    local db = sqlite3.open(r10db)
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

function getBluetoothDevice(id)
    local db = sqlite3.open(r10db)
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
	local db = sqlite3.open(r10db)
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
    local db = sqlite3.open(r10db)
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
--[[
function initBleTimerTable()
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("create table if not exists  bletimertable(id varchar(100), timer_id varchar(100), flag varchar(100), start_time varchar(100), end_time varchar(100))")
	db:exec(sqlStr)
	return db:close()
end

function addBleTimer(id, timer_id, flag, start_time, end_time)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("insert into bletimertable values('%s', '%s', '%s', '%s', '%s')", id, timer_id, flag, start_time, end_time)
	db:exec(sqlStr)
	return db:close()
end

function deleteBleTimer(id, timer_id)
	local db = sqlite3.open(r10db)
	local sqlStr = string.format("delete from bletimertable where id = '%s' and timer_id = '%s'", id, timer_id)
	db:exec(sqlStr)
	return db:close()
end

function getBleTimerId(id, timer_id)
    local db = sqlite3.open(r10db)
    local sqlStr = string.format("select * from bletimertable where id = '%s' and timer = '%s'", id, timer_id)
    local result = ""
	for row in db:rows(sqlStr) do
        if row then
            result = row[2]
        end
    end
	db:close()
    return result
end
]]
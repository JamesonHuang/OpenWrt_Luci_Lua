module ("xiaoqiang.util.XQDBUtil", package.seeall)

local SQLite3 = require("lsqlite3")
local XQ_DB = "/etc/xqDb"

--
-- |TABLE| USER_INFO(UUID,NAME,ICONURL)
-- |TABLE| PASSPORT_INFO(UUID,TOKEN,STOKEN,SID,SSECURITY)
-- |TABLE| DEVICE_INFO(MAC,ONAME,NICKNAME,COMPANY,OWNNERID)
--

function savePassport(uuid,token,stoken,sid,ssecurity)
    local db = SQLite3.open(XQ_DB)
    local fetch = string.format("select * from PASSPORT_INFO where UUID = '%s'",uuid)
    local exist = false
    for row in db:rows(fetch) do
        if row then
            exist = true
        end
    end
    local sqlStr
    if not exist then
        sqlStr = string.format("insert into PASSPORT_INFO values('%s','%s','%s','%s','%s')",uuid,token,stoken,sid,ssecurity)
    else
        sqlStr = string.format("update PASSPORT_INFO set UUID = '%s', TOKEN = '%s', STOKEN = '%s', SID = '%s', SSECURITY = '%s' where UUID = '%s'",uuid,token,stoken,sid,ssecurity,uuid)
    end
    db:exec(sqlStr)
    return db:close()
end

function fetchPassport(uuid)
    local db = SQLite3.open(XQ_DB)
    local sqlStr = string.format("select * from PASSPORT_INFO where UUID = '%s'",uuid)
    local result = {}
    for row in db:rows(sqlStr) do
        if row then
            table.insert(result,{
                ["uuid"] = row[1],
                ["token"] = row[2],
                ["stoken"] = row[3],
                ["sid"] = row[4],
                ["ssecurity"] = row[5]
            })
        end
    end
    db:close()
    return result
end

function fetchAllPassport()
    local db = SQLite3.open(XQ_DB)
    local sqlStr = "select * from PASSPORT_INFO"
    local result = {}
    for row in db:rows(sqlStr) do
        if row then
            table.insert(result,{
                ["uuid"] = row[1],
                ["token"] = row[2],
                ["stoken"] = row[3],
                ["sid"] = row[4],
                ["ssecurity"] = row[5]
            })
        end
    end
    db:close()
    return result
end

function deletePassport(uuid)
    local db = SQLite3.open(XQ_DB)
    local sqlStr = string.format("delete from PASSPORT_INFO where UUID = '%s'",uuid)
    db:exec(sqlStr)
    return db:close()
end

function saveUserInfo(uuid,name,iconUrl)
    local db = SQLite3.open(XQ_DB)
    local fetch = string.format("select * from USER_INFO where UUID = '%s'",uuid)
    local exist = false
    for row in db:rows(fetch) do
        if row then
            exist = true
        end
    end
    local sqlStr
    if not exist then
        sqlStr = string.format("insert into USER_INFO values('%s','%s','%s')",uuid,name,iconUrl)
    else
        sqlStr = string.format("update USER_INFO set UUID = '%s', NAME = '%s', ICONURL = '%s' where UUID = '%s'",uuid,name,iconUrl,uuid)
    end
    db:exec(sqlStr)
    return db:close()
end

function fetchUserInfo(uuid)
    local db = SQLite3.open(XQ_DB)
    local sqlStr = string.format("select * from USER_INFO where UUID = '%s'",uuid)
    local result = {}
    for row in db:rows(sqlStr) do
        if row then
            table.insert(result,{
                ["uuid"] = row[1],
                ["name"] = row[2],
                ["iconUrl"] = row[3]
            })
        end
    end
    db:close()
    return result
end

function fetchAllUserInfo()
    local db = SQLite3.open(XQ_DB)
    local sqlStr = string.format("select * from USER_INFO")
    local result = {}
    for row in db:rows(sqlStr) do
        if row then
            table.insert(result,{
                ["uuid"] = row[1],
                ["name"] = row[2],
                ["iconUrl"] = row[3]
            })
        end
    end
    db:close()
    return result
end

function deleteUserInfo(uuid)
    local db = SQLite3.open(XQ_DB)
    local sqlStr = string.format("delete from USER_INFO where UUID = '%s'",uuid)
    db:exec(sqlStr)
    return db:close()
end

function saveDeviceInfo(mac,oName,nickname,company,ownnerId)
    local db = SQLite3.open(XQ_DB)
    local fetch = string.format("select * from DEVICE_INFO where MAC = '%s'",mac)
    local exist = false
    for row in db:rows(fetch) do
        if row then
            exist = true
        end
    end
    local sqlStr
    if not exist then
        sqlStr = string.format("insert into DEVICE_INFO values('%s','%s','%s','%s','%s')",mac,oName,nickname,company,ownnerId)
    else
        sqlStr = string.format("update DEVICE_INFO set MAC = '%s', ONAME = '%s', NICKNAME = '%s', COMPANY = '%s', OWNNERID = '%s' where MAC = '%s'",mac,oName,nickname,company,ownnerId,mac)
    end
    db:exec(sqlStr)
    return db:close()
end

function updateDeviceNickname(mac,nickname)
    local db = SQLite3.open(XQ_DB)
    local sqlStr = string.format("update DEVICE_INFO set NICKNAME = '%s' where MAC = '%s'",nickname,mac)
    db:exec(sqlStr)
    return db:close()
end

function updateDeviceOwnnerId(mac,ownnerId)
    local db = SQLite3.open(XQ_DB)
    local sqlStr = string.format("update DEVICE_INFO set OWNNERID = '%s' where MAC = '%s'",ownnerId,mac)
    db:exec(sqlStr)
    return db:close()
end

function updateDeviceCompany(mac,company)
    local db = SQLite3.open(XQ_DB)
    local sqlStr = string.format("update DEVICE_INFO set COMPANY = '%s' where MAC = '%s'",company,mac)
    db:exec(sqlStr)
    return db:close()
end

function fetchDeviceInfo(mac)
    local db = SQLite3.open(XQ_DB)
    local sqlStr = string.format("select * from DEVICE_INFO where MAC = '%s'",mac)
    local result = {}
    for row in db:rows(sqlStr) do
        if row then
            result = {
                ["mac"] = row[1],
                ["oName"] = row[2],
                ["nickname"] = row[3],
                ["company"] = row[4],
                ["ownnerId"] = row[5]
            }
        end
    end
    db:close()
    return result
end

function fetchAllDeviceInfo()
    local db = SQLite3.open(XQ_DB)
    local sqlStr = string.format("select * from DEVICE_INFO")
    local result = {}
    for row in db:rows(sqlStr) do
        if row then
            table.insert(result,{
                ["mac"] = row[1],
                ["oName"] = row[2],
                ["nickname"] = row[3],
                ["company"] = row[4],
                ["ownnerId"] = row[5]
            })
        end
    end
    db:close()
    return result
end

function deleteDeviceInfo(mac)
    local db = SQLite3.open(XQ_DB)
    local sqlStr = string.format("delete from DEVICE_INFO where MAC = '%s'",mac)
    db:exec(sqlStr)
    return db:close()
end

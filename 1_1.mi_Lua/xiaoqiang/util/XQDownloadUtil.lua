module ("xiaoqiang.util.XQDownloadUtil", package.seeall)

local XQFunction = require("xiaoqiang.common.XQFunction")
local XQConfigs = require("xiaoqiang.common.XQConfigs")
local XQPreference = require("xiaoqiang.XQPreference")

local LuciJson = require("json")
local LuciUtil = require("luci.util")
local XQLog = require("xiaoqiang.XQLog")
local XQCryptoUtil = require("xiaoqiang.util.XQCryptoUtil")

local TMPFILEPATH = "/tmp/rom.bin"
local UDISKFILEPATH = "/userdisk/rom.bin"

PREF_DOWNLOAD_TYPE = "DOWNLOAD_TYPE"
PREF_DOWNLOAD_FILE_PATH = "DOWNLOAD_FILE_PATH"

local WGET_PIDS = [[ps w | grep wget | awk '{print $1}']]

local WGET_FILESIZE_FILEDIR = "/tmp/"
local WGET_CHECK_FILESIZE = "wget -t3 -T10 --spider '%s' -o %s"
local GET_WGET_FILESIZE = "cat %s | grep Length | awk '{print $2}'"

function wgetCheckDownloadFilesize(url)
    if XQFunction.isStrNil(url) then
        return nil
    end
    local LuciFs = require("luci.fs")
    local LuciSys = require("luci.sys")
    local random = LuciSys.uniqueid(8)
    local filepath = WGET_FILESIZE_FILEDIR..random
    local checkcmd = string.format(WGET_CHECK_FILESIZE, url, filepath)
    if os.execute(checkcmd) then
        local getcmd = string.format(GET_WGET_FILESIZE, filepath)
        local size = tonumber(LuciUtil.exec(getcmd))
        LuciFs.unlink(filepath)
        if size then
            return size
        end
    end
    LuciFs.unlink(filepath)
    return nil
end

function _checkResource(downloadUrl)
    if XQFunction.isStrNil(downloadUrl) then
        return false
    end
    local check = os.execute(XQConfigs.DOWNLOAD_RESOURCE_CHECK..downloadUrl)
    if check ~= 0 then
        return false
    end
    return true
end

function _wgetDownload(downloadUrl)
    if XQFunction.isStrNil(downloadUrl) then
        return false
    end
    if not _checkResource(downloadUrl) then
        XQLog.log(6, "Wget --spider : Bad url "..downloadUrl)
        return false
    end
    XQPreference.set(PREF_DOWNLOAD_TYPE, 2)
    local XQSysUtil = require("xiaoqiang.util.XQSysUtil")
    local LuciFs = require("luci.fs")
    local filePath
    local filesize = XQPreference.get(XQConfigs.PREF_ROM_FULLSIZE, nil)
    if filesize then
        filesize = tonumber(filesize)
        if XQSysUtil.checkDiskSpace(filesize) then
            filePath = UDISKFILEPATH
        elseif XQSysUtil.checkTmpSpace(filesize) then
            filePath = TMPFILEPATH
        else
            return false
        end
    else
        return false
    end
    XQPreference.set(PREF_DOWNLOAD_FILE_PATH, filePath)
    if LuciFs.access(filePath) then
        LuciFs.unlink(filePath)
    end
    local download = "wget -t3 -T30 '"..downloadUrl.."' -O "..filePath
    LuciUtil.exec(download)
    return XQCryptoUtil.md5File(filePath), filePath
end

function _pauseDownload(ids)
    if XQFunction.isStrNil(ids) then
        XQPreference.set(XQConfigs.PREF_PAUSED_IDS, "")
        return
    end
    XQPreference.set(XQConfigs.PREF_PAUSED_IDS, ids)
    local payload = {
        ["api"] = 505,
        ["idList"] = ids
    }
    XQFunction.thrift_tunnel_to_datacenter(LuciJson.encode(payload))
end

function _resumeDownload(ids)
    if XQFunction.isStrNil(ids) then
        return
    end
    local payload = {
        ["api"] = 506,
        ["idList"] = ids
    }
    XQFunction.thrift_tunnel_to_datacenter(LuciJson.encode(payload))
end

function _deleteDownload(ids)
    if XQFunction.isStrNil(ids) then
        return
    end
    local payload = {
        ["api"] = 507,
        ["idList"] = ids,
        ["deletefile"] = true
    }
    XQFunction.thrift_tunnel_to_datacenter(LuciJson.encode(payload))
end

--[[
    DownloadStatusNone,
    Downloading = 1,
    DownloadPause = 2,
    DownloadCompleted = 4,
    DownloadStoped = 8,
    DownloadStatusFailed = 16,
    DownloadNotStart = 32
]]--
function _xunleiDownload(downloadUrl, priority)
    local priority = priority or 1
    XQPreference.set(PREF_DOWNLOAD_TYPE, 1)
    local payload = {
        ["api"] = 504,
        ["url"] = downloadUrl,
        ["type"] = 1,
        ["redownload"] = 0,
        ["hidden"] = true,
        ["path"] = XQConfigs.USERDISK_DOWNLOAD_DIR,
        ["dupId"] = ""
    }
    local ids = {}
    local clist = {}
    if priority == 1 then
        local dolist = XQFunction.thrift_tunnel_to_datacenter([[{"api":503,"hidden":true}]])
        if dolist and dolist.code == 0 then
            table.foreach(dolist.uncompletedList,
                function(i,v)
                    if v.downloadStatus == 1 or v.downloadStatus == 32 then
                        table.insert(ids, v.id)
                    end
                end
            )
            clist = dolist.completedList
        else
            XQLog.log(6, "api 503 failed, will switch to wget")
            return false, false, 0
        end
    end
    ids = table.concat(ids, ";")
    _pauseDownload(ids)

    local download = XQFunction.thrift_tunnel_to_datacenter(LuciJson.encode(payload))
    if not download then
        return false, false, 0
    end
    if download and download.code ~= 2010 and download.code ~= 0 then
        XQLog.log(6, "Xunlei download failed!")
        return false, false, 0
    end
    if download.code == 2010 then
        local LuciFs = require("luci.fs")
        local localFileName
        for _, item in ipairs(clist) do
            if item.id == download.info.id then
                localFileName = item.localFileName
            end
        end
        if not XQFunction.isStrNil(localFileName) and LuciFs.access(localFileName) then
            download.code = 0
            XQLog.log(6, "File exist (predownload hit)")
        else
            XQLog.log(6, "Retry !!!")
            payload.dupId = download.info.id
            payload.redownload = 1
            download = XQFunction.thrift_tunnel_to_datacenter(LuciJson.encode(payload))
        end
    end
    local dId
    if not download then
        return false, false, 0
    end
    if download and download.code ~= 0 then
        return false, nil, 0
    else
        dId = download.info.id
    end
    XQPreference.set(XQConfigs.PREF_ROM_DOWNLOAD_ID, dId)
    local nodata = 0
    local nomatch = 0
    local lastsize = 0
    while true do
        local match = 0
        os.execute("sleep 3")
        local dlist = XQFunction.thrift_tunnel_to_datacenter([[{"api":503,"hidden":true}]])
        if dlist and dlist.code == 0 then
            local completedList = dlist.completedList
            local uncompletedList = dlist.uncompletedList
            table.foreach(uncompletedList, function(i,v) table.insert(completedList, v) end)
            for _,item in ipairs(completedList) do
                if (dId and item.id == dId) or (not dId and item.address == downloadUrl) then
                    match = 1
                    if not dId then
                        dId = item.id
                        XQPreference.set(XQConfigs.PREF_ROM_DOWNLOAD_ID, dId)
                    end
                    if lastsize == item.fileDownloadedSize then
                        nodata = nodata + 1
                    else
                        lastsize = item.fileDownloadedSize
                        nodata = 0
                    end
                    if item.datacenterErrorCode ~= 0 then
                        _resumeDownload(ids)
                        _deleteDownload(dId)
                        return false, nil, lastsize
                    elseif item.downloadStatus == 4 then
                        _resumeDownload(ids)
                        return XQCryptoUtil.md5File(item.localFileName), item.localFileName, item.fileDownloadedSize
                    elseif item.downloadStatus == 16 then
                        _resumeDownload(ids)
                        _deleteDownload(dId)
                        return false, nil, lastsize
                    elseif nodata > 60 then
                        _resumeDownload(ids)
                        _deleteDownload(dId)
                        XQLog.log(6, "xunlei download timeout, will switch to wget")
                        return false, nil, item.fileDownloadedSize
                    end
                end
            end
        end
        if match == 0 then
            nomatch = nomatch + 1
        end
        if nomatch > 60 then
            _resumeDownload(ids)
            _deleteDownload(dId)
            XQLog.log(6, "xunlei download error, will switch to wget")
            return false, nil, lastsize
        end
    end
end

function syncDownload(downloadUrl, priority, smart)
    if XQFunction.isStrNil(downloadUrl) then
        return false
    end
    local smart = smart == nil and true or smart
    XQLog.log(6, "Xunlei download start...")
    local md5, filepath, size = _xunleiDownload(downloadUrl, priority)
    if md5 and filepath then
        return md5, filepath
    end
    if smart and md5 == false and size and size == 0 then
        XQLog.log(6, "Xunlei download failed, start wget...")
        md5 , filepath = _wgetDownload(downloadUrl)
        if md5 then
            XQLog.log(6, "Wget finished")
            return md5, filepath
        else
            XQLog.log(6, "Wget failed")
        end
    end
    return false
end

function _xunleiDownloadPercent(downloadId)
    local dlist = XQFunction.thrift_tunnel_to_datacenter([[{"api":503,"hidden":true}]])
    if dlist and dlist.code == 0 then
        local completedList = dlist.completedList
        local uncompletedList = dlist.uncompletedList
        table.foreach(uncompletedList, function(i,v) table.insert(completedList, v) end)
        for _,item in ipairs(completedList) do
            if item.id == downloadId then
                if item.downloadStatus == 4 then
                    return 100
                elseif item.downloadStatus == 16 then
                    return 0
                else
                    if item.fileTotalSize <= 0 then
                        return 0
                    end
                    local percent = 100*item.fileDownloadedSize/item.fileTotalSize
                    if percent > 100 then
                        return 100
                    else
                        return math.floor(percent)
                    end
                end
            end
        end
        return 0
    else
        return 0
    end
end

function _wgetDownloadPercent(downloadId)
    local filepath = XQPreference.get(PREF_DOWNLOAD_FILE_PATH, nil)
    local filesize = tonumber(XQPreference.get(XQConfigs.PREF_ROM_FULLSIZE, nil))
    if filepath and filesize and filesize > 0 then
        local LuciFs  = require("luci.fs")
        local NixioFs = require("nixio.fs")
        local percent
        if NixioFs.access(filepath) then
            local size = math.modf(LuciFs.stat(filepath).size)
            percent = math.modf(size/filesize*100)
            if percent < 1 and percent > 0 then
                percent = 1
            elseif percent > 100 then
                percent = 100
            end
        end
        return percent
    end
    return 0
end

function downloadPercent(downloadId)
    local dtype = tonumber(XQPreference.get(PREF_DOWNLOAD_TYPE), nil)
    if dtype then
        if dtype == 1 then
            return _xunleiDownloadPercent(downloadId)
        elseif dtype == 2 then
            return _wgetDownloadPercent(downloadId)
        end
    end
    return 0
end

function _cancelXunleiDownload(downloadId)
    _resumeDownload(XQPreference.get(XQConfigs.PREF_PAUSED_IDS, ""))
    if XQFunction.isStrNil(downloadId) then
        return false
    else
        local payload = {
            ["api"] = 507,
            ["idList"] = downloadId,
            ["deletefile"] = true
        }
        local result = XQFunction.thrift_tunnel_to_datacenter(LuciJson.encode(payload))
        if result and result.code == 0 then
            return true
        else
            return false
        end
    end
end

function _cancelWgetDownload(downloadId)
    for _, wgetPid in ipairs(LuciUtil.execl(WGET_PIDS)) do
        if wgetPid then
            os.execute("kill "..LuciUtil.trim(wgetPid))
        end
    end
    local LuciFs = require("luci.fs")
    local filePath = XQPreference.get(PREF_DOWNLOAD_FILE_PATH, nil)
    if filePath and LuciFs.access(filePath) then
        LuciFs.unlink(filePath)
    end
    return true
end

function cancelDownload(downloadUrl)
    local dtype = tonumber(XQPreference.get(PREF_DOWNLOAD_TYPE), nil)
    if dtype then
        if dtype == 1 then
            return _cancelXunleiDownload(downloadUrl)
        elseif dtype == 2 then
            return _cancelWgetDownload(downloadUrl)
        end
    end
    return false
end

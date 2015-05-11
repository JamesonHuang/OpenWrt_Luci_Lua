module("meizu.btfs", package.seeall)
--network functions

local cjson = require "cjson"
local lfs   = require "lfs"
local bfs   = require "meizu.bfs"
local RC    = require "meizu.r10config"
local dbfs  = require("meizu.dbfs")
local posix = require("Posix")

local bind_router          = bfs.bind_router
local data_to_json         = bfs.data_to_json
local exec_cmd_in_sh       = bfs.exec_cmd_in_sh
local exec_reboot          = bfs.exec_reboot
local get_device_SN        = bfs.get_device_SN
local get_device_version   = bfs.get_device_version
local get_https_data       = bfs.get_https_data
local rts_get_access_token = bfs.rts_get_access_token
local set_passwd           = bfs.set_passwd
local silent_upgrade       = bfs.silent_upgrade
local strsplit             = bfs.strsplit
local b64dec               = bfs.b64dec
local b64enc               = bfs.b64enc

local lue                  = require("luci.util").exec

------------------------  bluetooth  --------------------

-----------------------  receive data --------------------
function bluetooth_info()
    dbfs.initBluetoothTable()
    local value = luci.http.formvalue("data")
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, value)
    local ret = {}
    local types = string.sub(value, 1, 2)

    if types == "00" then
        local id = string.sub(value, 3, 4)
        local status = string.format("%d", "0x" .. string.sub(value, 5, 6))   
        local temp1 = string.sub(value, 7, 8) 
        local temp2 = string.sub(value, 9, 10)
        local temp = temp2..temp1
        temp = string.format("%d", "0x" .. temp)   
        local rh1 = string.sub(value, 11, 12) 
        local rh2 = string.sub(value, 13, 14)
        local rh = rh2..rh1
        rh = string.format("%d", "0x" .. rh)   
        local light1 = string.sub(value, 15, 16)
        local light2 = string.sub(value, 17, 18) 
        local light = light2..light1
        light = string.format("%d", "0x" .. light)   
        local mac = dbfs.getBluetoothDevice(id)
        local TMP = "/tmp/"..mac.."0"

        ret["mac"] = mac
        ret["id"] = id
        if status == "1" then
            ret["onoff"] = "on"
        else
            ret["onoff"] = "off"
        end
        local timer_id = ""
        local flag = ""
        local start = ""
        local ends = ""
        local fd = io.open(TMP, "r")

        if fd then
            local res = fd:read()
            fd:close()
            res = cjson.decode(res)
            timer_id = res.timerId
            flag = res.flag
            start = res.start
            ends = res.ends
        end

        ret["timerId"] = timer_id
        ret["flag"] = flag
        ret["start"] = start
        ret["ends"] = ends
        ret["temp"] = temp
        ret["hemi"] = rh
        ret["light"] = light
        ret["time"] = os.time()

        local result = data_to_json(ret)
		--[[
        require "MZLog".log(3, debug.getinfo(1).currentline)
        require "MZLog".log(3, id)
        require "MZLog".log(3, result)
        require "MZLog".log(3, ret)
		]]
        local fd = assert(io.open(TMP, "w"))
        fd:write(result)
        fd:close()

    elseif types == "01" then
        local id = string.sub(value, 3, 4)
        local voltage1 = string.sub(value, 5, 6)
        local voltage2 = string.sub(value, 7, 8)  
        local voltage = voltage2..voltage1
        voltage = string.format("%d", "0x" .. voltage)   
        local electricity1 = string.format("%d", "0x" .. string.sub(value, 9, 10))   
        local electricity2 = string.format("%d", "0x" .. string.sub(value, 11, 12))   
        local electricity = electricity2..electricity1
        electricity = string.format("%d", "0x" .. electricity)   
        local power1 = string.sub(value, 13, 14) 
        local power2 = string.sub(value, 15, 16)
        local power = power2..power1
        power = string.format("%d", "0x" .. power)   
        local electric1 = string.sub(value, 17, 18)
        local electric2 = string.sub(value, 19, 20) 
        local electric = electric2..electric1
        electric = string.format("%d", "0x" .. electric)   
        local mac = dbfs.getBluetoothDevice(id)
        local TMP = "/tmp/"..mac.."1"
        local timer_id = ""
        local fd = io.open(TMP, "r")
        if fd then
            local res = fd:read()
            fd:close()
            res = cjson.decode(res)
            timer_id = res.timerId
        end

        ret["timerId"] = timer_id
        ret["mac"] = mac
        ret["id"] = id
        ret["voltage"] = voltage  
        ret["current"] = electricity 
        ret["power"] = power 
        ret["energy"] = electric 

        local result = data_to_json(ret)
        local fd = assert(io.open(TMP, "w"))
        fd:write(result)
        fd:close()
    
    elseif types == "02" then
        require "MZLog".log(3, debug.getinfo(1).currentline)
        local id = string.sub(value, 3, 4)
        local TYPE = string.sub(value, 5, 6)
        local mac1 = string.sub(value, 7, 8)
        local mac2 = string.sub(value, 9, 10)
        local mac3 = string.sub(value, 11, 12)
        local mac4 = string.sub(value, 13, 14)
        local mac5 = string.sub(value, 15, 16)
        local mac6 = string.sub(value, 17, 18)
        local mac = mac6..mac5..mac4..mac3..mac2..mac1

        mac = string.upper(mac)
        local ID = ""
        local res = dbfs.fetchBluetoothDevice(mac)
        require "MZLog".log(3, debug.getinfo(1).currentline)
        require "MZLog".log(3, #res)
        require "MZLog".log(3, res)
        if #res > 0 then
            for k, v in pairs(res) do
                ID = v.id
            end
        end
        if id ~= ID then
            dbfs.updateBluetoothDevice(id, "", "", "", mac)
        end
        local TMP = "/tmp/"..mac.."0"
        local fd = io.open(TMP, "r")

        if fd then
            local res = fd:read()
            fd:close()
            res = cjson.decode(res)
            res["time"] = os.time()
            res = cjson.encode(res)
            local fd = io.open(TMP, "w")
            fd:write(res)
            fd:close()
        end
        require "MZLog".log(3, id)
        require "MZLog".log(3, mac)
        require "MZLog".log(3, debug.getinfo(1).currentline)

    elseif types == "09" then
        local deviceType = string.sub(value, 3, 4)
        local mac1 = string.sub(value, 5, 6)
        local mac2 = string.sub(value, 7, 8)
        local mac3 = string.sub(value, 9, 10)
        local mac4 = string.sub(value, 11, 12)
        local mac5 = string.sub(value, 13, 14)
        local mac6 = string.sub(value, 15, 16)
        local mac = mac6..mac5..mac4..mac3..mac2..mac1
        mac = string.upper(mac)

        local res = dbfs.fetchBluetoothDevice(mac)
        local ret = nil
        local id = nil
        if #res > 0 then
            for k, v in pairs(res) do
                ret = v.mac
                id = v.id
            end
        end
        if id then
            dbfs.updateBluetoothDevice("", "", "", "", mac)
        end
        if ret == nil then
            dbfs.addBluetoothDevice("", mac, "", "", deviceType, "")
            local TMP = "/tmp/"..mac
            local fd = io.open(TMP, "w")
            fd:write(os.time())
            fd:close()
        else
            local TMP = "/tmp/"..mac
            local fd = io.open(TMP, "w")
            fd:write(os.time())
            fd:close()
        end

    elseif types == "04" then
        local data = string.format("%d", "0x" .. string.sub(value, 3, 4))   
        ret["data"] = data

    elseif types == "03" then
        require "MZLog".log(3, debug.getinfo(1).currentline)
        local id = string.sub(value, 3, 4)
        local flag = string.sub(value, 5, 6)   
        local timer_id = string.sub(value, 7, 8)
        local start = string.sub(value, 9, 16)
        local ends = string.sub(value, 17, 24)
        local mac = dbfs.getBluetoothDevice(id)
        local TMP = "/tmp/"..mac.."1"
        local fd = io.open(TMP, "r")

        if fd then
            require "MZLog".log(3, debug.getinfo(1).currentline)
            require "MZLog".log(3, timer_id)
            require "MZLog".log(3, flag)
            local res = fd:read()
            res = cjson.decode(res)
            if timer_id == res["timerId"] then
                local TMP = "/tmp/"..mac.."0"
                local fd = io.open(TMP, "r")
                local res = fd:read()
                res = cjson.decode(res)
                res["flag"] = flag
                res["timerId"] = timer_id
                res["start"] = start
                res["ends"] = ends
                res = cjson.encode(res)
                local fd = io.open(TMP, "w")
                fd:write(res)
                fd:close()
            end
        end

    elseif types == "06" then
        require "MZLog".log(3, debug.getinfo(1).currentline)
        require "MZLog".log(3, debug.getinfo(1).currentline)
        local id = string.sub(value, 3, 4)
        local mac = dbfs.getBluetoothDevice(id)
        local len = tonumber(dbfs.getBleDeviceNameLength(id))
        local str = string.sub(value, 5, len + 4)
        --local res = {}
        --[[
        for i = 1, #str, 2 do
            res[#res + 1] = (tonumber(string.format("%d", "0x"..string.sub(str, i, i+1))))
        end
        ]]
        local device_name = str	
        --[[
		if #res == 1 then
			device_name = string.char(res[1])
		elseif #res == 2 then
			device_name = string.char(res[1], res[2])
		elseif #res == 3 then
			device_name = string.char(res[1], res[2], res[3])
		elseif #res == 4 then
			device_name = string.char(res[1], res[2], res[3], res[4])
		elseif #res == 5 then
			device_name = string.char(res[1], res[2], res[3], res[4], res[5])
		elseif #res == 6 then
			device_name = string.char(res[1], res[2], res[3], res[4], res[5], res[6])
		elseif #res == 7 then
			device_name = string.char(res[1], res[2], res[3], res[4], res[5], res[6], res[7])
		elseif #res == 8 then
			device_name = string.char(res[1], res[2], res[3], res[4], res[5], res[6], res[7], res[8])
		elseif #res == 9 then
			device_name = string.char(res[1], res[2], res[3], res[4], res[5], res[6], res[7], res[8], res[9])
		elseif #res == 10 then
			device_name = string.char(res[1], res[2], res[3], res[4], res[5], res[6], res[7], res[8], res[9], res[10])
		end
	    ]]
        require "MZLog".log(3, debug.getinfo(1).currentline)
        require "MZLog".log(3, str)
        require "MZLog".log(3, device_name)
        dbfs.updateBluetoothDevice(id, "", device_name, len, mac)
        require "MZLog".log(3, debug.getinfo(1).currentline)

    elseif types == "07" then
        local data = string.format("%d", "0x" .. string.sub(value, 3, 4))   
        ret["data"] = data

    elseif types == "0b" then
        local key_ack = string.sub(value, 3, 4)
        local TMP = "/tmp/0b0b"
        local fd = io.open(TMP, "w")
        fd:write(key_ack)
        fd:close()

    elseif types == "0c" then
        local id = string.sub(value, 3, 4)
        local status = string.sub(value, 5, 6)
        local mac = dbfs.getBluetoothDevice(id)
        local TMP = "/tmp/"..mac.."0"
        local fd = io.open(TMP, "r")
        local res = nil
        require "MZLog".log(3, debug.getinfo(1).currentline)

        if fd then
            res = fd:read()
            fd:close()
            res = cjson.decode(res)
            if status == "01" then
                res["onoff"] = "on"
            else
                res["onoff"] = "off"
            end
            res = cjson.encode(res)
            local fd = io.open(TMP, "w")
            fd:write(res)
            fd:close()
        end
        require "MZLog".log(3, res)

    elseif types == "0d" then
        local id = string.sub(value, 3, 4)
        local led_light = string.sub(value, 5, 6)
        local temp1 = string.sub(value, 7, 8) 
        local temp2 = string.sub(value, 9, 10)
        led_light = string.format("%d", "0x" .. led_light)   
        local led_temp = temp2..temp1
        temp = string.format("%d", "0x" .. temp)   

        local TMP = "/tmp/"..mac.."0"
        local fd = io.open(TMP, "r")
        local res = nil
        require "MZLog".log(3, debug.getinfo(1).currentline)

        if fd then
            res = fd:read()
            fd:close()
            res = cjson.decode(res)
			res["led_light"] = led_light
			res["led_temp"] = led_temp
            
			res = cjson.encode(res)
            local fd = io.open(TMP, "w")
            fd:write(res)
            fd:close()
        end

    elseif types == "0e" then
        local id = string.sub(value, 3, 4)
        local wait_time = string.format("%d", "0x" .. string.sub(value, 5, 6))   
        --local mac = dbfs.getBluetoothDevice(id)
        local TMP = "/tmp/0e0e"
        local fd = io.open(TMP, "w")
        fd:write(wait_time)
        fd:close()
		
    elseif types == "10" then
        local mac1 = string.sub(value, 3, 4)
        local mac2 = string.sub(value, 5, 6)
        local mac3 = string.sub(value, 7, 8)
        local mac4 = string.sub(value, 9, 10)
        local mac5 = string.sub(value, 11, 12)
        local mac6 = string.sub(value, 13, 14)
        local mac = mac6..mac5..mac4..mac3..mac2..mac1
        mac = string.upper(mac)

        local res = dbfs.fetchBluetoothDevice(mac)
        if #res == 0 then
            dbfs.addBluetoothDevice("", mac, "0123", "", "", "")
        end
        --[[
        if ret == nil then
            dbfs.addBluetoothDevice("", mac, "", "", deviceType, "")
            local TMP = "/tmp/"..mac
            local fd = io.open(TMP, "w")
            fd:write(os.time())
            fd:close()
        else
            local TMP = "/tmp/"..mac
            local fd = io.open(TMP, "w")
        ]]
	end
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, ret)
    return ret
end

function nw_get_bluetooth_info()
    require "MZLog".log(3, debug.getinfo(1).currentline)
    local res = bluetooth_info()
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_get_bluetooth_info()
    require "MZLog".log(3, debug.getinfo(1).currentline)
    local res = bluetooth_info()
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return res
end

-----------------------  scan_ble_device --------------------
function scan_ble_switch(status)
    local res = {}
    if status == "on" then
        local cmd = "bt_daemon -s ".."16".." 255"  
        lue(cmd)
        require "MZLog".log(3, debug.getinfo(1).currentline)
        require "MZLog".log(3, cmd)
        posix.sleep(1)
        res["result"] = true
    elseif status == "off" then
        local cmd = "bt_daemon -s ".."18".." 255"
        lue(cmd)
        posix.sleep(1)
        res["result"] = false
    end
    return res
end

function nw_scan_ble_switch()
    local status = luci.http.formvalue("status")
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, status)
    local res = scan_ble_switch(status)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_scan_ble_switch(status)
    local res = scan_ble_switch(status)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  get_ble_device_list --------------------
function get_ble_device_list()
    local res = dbfs.fetchAllBluetoothDevice()
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, res)
    local result = {}
    if #res > 0 then
        for k, v in pairs(res) do
            local TMP = "/tmp/"..v.mac
            local fd = io.open(TMP, "r")
            if fd then
                 local time = fd:read()
                fd:close()
                if tonumber(os.time()) - tonumber(time) < 5 then
                    table.insert(result, v)
                end
            end
        end
    end
    return result
end

function nw_get_ble_device_list()
    local res = get_ble_device_list()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    if #res == 0 then
        require "MZLog".log(3, debug.getinfo(1).currentline)
        luci.http.write("[]")
    else
        luci.http.write_json(res)
    end
end

function ww_get_ble_device_list()
    local res = get_ble_device_list()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    if #res == 0 then
        return "[]"
    else
        return cjson.encode(res)
    end
end

-----------------------  add_ble_mesh_device  --------------------
function is_receive_id(mac)
    local id = ""
    local ret = dbfs.fetchBluetoothDevice(mac)
    for k, v in pairs(ret) do
        id = v.id
    end
    return id
end

function add_ble_mesh_device(mac)
    local res = {}
    local id = ""
    local mac1 = string.format("%d", "0x" .. string.sub(mac, 1, 2))   
    local mac2 = string.format("%d", "0x" .. string.sub(mac, 3, 4))   
    local mac3 = string.format("%d", "0x" .. string.sub(mac, 5, 6))   
    local mac4 = string.format("%d", "0x" .. string.sub(mac, 7, 8))   
    local mac5 = string.format("%d", "0x" .. string.sub(mac, 9, 10))   
    local mac6 = string.format("%d", "0x" .. string.sub(mac, 11, 12))   
    local macs = mac6.." "..mac5.." "..mac4.." "..mac3.." "..mac2.." "..mac1

    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, mac)
    local cmd = "bt_daemon -s ".."17 "..macs  
    lue(cmd)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, cmd)

    for i = 1, 20 do
        posix.sleep(1)
        id = is_receive_id(mac)
        if id ~= "" then
            break
        end
    end
    if id ~= "" then
        res["result"] = true
        res["id"] = id
        res["mac"] = mac
    else
        res["result"] = false
        res["mac"] = mac
    end
    return res
end

function nw_add_ble_mesh_device()
    local mac = luci.http.formvalue("mac")
    local res = add_ble_mesh_device(mac)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_add_ble_mesh_device(mac)
    local res = add_ble_mesh_device(mac)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  get_ble_device_detail  --------------------
function get_ble_device_status(mac)
    local TMP1 = "/tmp/"..mac.."0" 
    local TMP2 = "/tmp/"..mac.."1" 
    local fd1 = io.open(TMP1, "r")
    local fd2 = io.open(TMP2, "r")
    if fd1 and fd2 then
        local res1 = fd1:read()
        local res2 = fd2:read()
        fd1:close()
        fd2:close()
        --require "MZLog".log(3, res1)
        --require "MZLog".log(3, res2)
        if res1 ~= nil and res2 ~= nil then
            res1 = cjson.decode(res1)
            res2 = cjson.decode(res2)
            res1["voltage"] = res2.voltage 
            res1["current"] = res2.current
            res1["power"] = res2.power 
            res1["energy"] = res2.energy

            local ret = dbfs.fetchBluetoothDevice(mac)
            local deviceType = nil
            local name = nil
            for k, v in pairs(ret) do
                 deviceType = v.deviceType
                name = v.name
            end

            res1["name"] = name
            res1["type"] = deviceType 
            res1["time"] = nil 
            --require "MZLog".log(3, res1)
            require "MZLog".log(3, debug.getinfo(1).currentline)
        end
        if res1 == "" then
            res1 = "{}"
        end
        return res1
    else
        return "{}"
    end
end

function nw_get_ble_device_status()
    local mac = luci.http.formvalue("mac")
    local res = get_ble_device_status(mac)
    --require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    if res == "{}" then
	    require "MZLog".log(3, debug.getinfo(1).currentline)
		require "MZLog".log(3, res)
        luci.http.write(res)
    else
	    require "MZLog".log(3, debug.getinfo(1).currentline)
		require "MZLog".log(3, res)
        luci.http.write_json(res)
    end
end

function ww_get_ble_device_status(mac)
    local res = get_ble_device_status(mac)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    if res == "{}" then
        return res
    else
        return cjson.encode(res)
    end
end

-----------------------  remove_ble_from_mesh  --------------------
function is_remove_ble_from_mesh()
    local res = nil
    local TMP = "/tmp/0e0e" 
    local fd = io.open(TMP, "r")
    if fd then
        local ret = fd:read()
        fd:close()
        if ret ~= "" and ret ~= nil then
            return ret
        else
            return nil 
        end
    else
        return nil
    end
end

function remove_ble_from_mesh(mac)
    local res = {}
    local ret = dbfs.fetchBluetoothDevice(mac)
    local id = nil
    for k, v in pairs(ret) do
        id = v.id
    end
    if id ~= nil and id ~= "" then
        local cmd = "bt_daemon -s ".."3 "..string.format("%d", "0x"..id)   
        lue(cmd)
        local wait_time = nil
        for i = 1, 20 do
            posix.sleep(1)
            wait_time = is_remove_ble_from_mesh(mac)
            if wait_time ~= nil then
                break
            end
        end
        if wait_time then
            res["result"] = true
            res["waitTime"] = wait_time
            res["mac"] = mac
            res["id"] =id
        else
            res["result"] = false
            res["mac"] = mac
            res["id"] = id
        end
    else
        res["result"] = false
        res["mac"] = mac
    end
    return res
end

function nw_remove_ble_from_mesh()
    local mac = luci.http.formvalue("mac")
    local res = remove_ble_from_mesh(mac)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_remove_ble_from_mesh(mac)
    local res = remove_ble_from_mesh(mac)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  get_mesh_device_list --------------------
function is_file_exist(TMP)
    local fd = io.open(TMP, "r")
    if fd then
        return fd
    else
        require "MZLog".log(3, debug.getinfo(1).currentline)
        return false
    end
end

function get_mesh_device_list()
    local result = {}
    local ret = dbfs.fetchAllBleMeshDevice()
    if #ret > 0 then
        for k, v in pairs(ret) do
            local res = {}
            local TMP = "/tmp/" .. v.mac .."0"
            local fd = nil
            fd = is_file_exist(TMP)
			--[[
            for i = 1, 5 do
                fd = is_file_exist(TMP)
                if fd then
                    break
                else
                    posix.sleep(1)
                end
            end
			]]
            if fd then
                local value = fd:read()
                if value ~= nil then
                    value = cjson.decode(value)
                end
                res["mac"] = v.mac
                --res["online"] = true
                res["name"] = v.name
                res["type"] = v.deviceType
                if value["onoff"] == "on" then
                    res["onoff"] = "on"
                else
                    res["onoff"] = "off"
                end

                if tonumber(os.time()) - tonumber(value.time) > 60 then
                    res["online"] = false
                else
                    res["online"] = true
                end

                if res["online"] == false then
                    res = nil
                end
                table.insert(result, res)
                require "MZLog".log(3, debug.getinfo(1).currentline)
				
            else
                res["mac"] = v.mac
                res["name"] = v.name
                res["type"] = v.deviceType
                res["onoff"] = "off"
                res["online"] = false
                table.insert(result, res)
                require "MZLog".log (3, debug.getinfo(1).currentline)
            end
        end
    end
    return result
end

function nw_get_mesh_device_list()
    local res = get_mesh_device_list()
    --require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    if #res == 0 then
        luci.http.write("[]")
    else
        luci.http.write_json(res)
    end
end

function ww_get_mesh_device_list()
    local res = get_mesh_device_list()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    if #res == 0 then
        return "[]"
    else
        return cjson.encode(res)
    end
end

-----------------------  dismiss_mesh --------------------
function dismiss_mesh()
    local res = {}
    local cmd = "bt_daemon -s ".."3 ".." 255"   
    lue(cmd)
    res["result"] = true
end
function nw_dismiss_mesh()
    local res = dismiss_mesh()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

----------------------- set_mesh_device_attr  --------------------
function is_switch_on(mac)
    local TMP = "/tmp/"..mac.."0"
    local fd = io.open(TMP, "r")
    if fd then
        local res = fd:read()
        fd:close()
        res = cjson.decode(res)
        if res["onoff"] == "on" then
            return true
        else
            return nil
        end
    end
end

function is_switch_off(mac)
    local TMP = "/tmp/"..mac.."0"
    local fd = io.open(TMP, "r")
    if fd then
        local res = fd:read()
        fd:close()
        res = cjson.decode(res)
        if res["onoff"] == "off" then
            return true
        else
            return nil
        end
    end
end

function is_set_name_ok(mac)
    local name = ""
    local ret = dbfs.fetchBluetoothDevice(mac)
    for k, v in pairs(ret) do
        name = v.name
    end
    return name
end

function set_mesh_device_attr(mac, key, value)
    local res = {}
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, mac)
    require "MZLog".log(3, key)
    require "MZLog".log(3, value)
	
	local fd = io.open("/tmp/meshDeviceFlag", "r")
    if fd then
        local time = tonumber(fd:read())
        if os.time() - time < 50 then
            local res = {}
            res["result"] = true
            return res
        end
    end
	
    local ret = dbfs.fetchBluetoothDevice(mac)
    local id = nil
    if #ret > 0 then
        for k, v in pairs(ret) do
            id = v.id
        end
    end
	--[[
    local fd1 = io.open("/tmp/tmp", "a")
	fd1:write(key)
	fd1:write(value)
	]]
    

    if id ~= nil then
        --local cmd = "touch /tmp/flag"
        --lue(cmd)
		local fd = io.open("/tmp/meshDeviceFlag", "w")
		fd:write(os.time())
		fd:close()

        if key == "8" and value == "true" then
            local cmd = "bt_daemon -s ".."1 "..string.format("%d", "0x"..id).." 1"   
            lue(cmd)
            local flag = nil
            for i = 1, 10 do
                flag = is_switch_on(mac)    
                if flag then
                    break
                else
                    posix.sleep(1) 
                end
            end
            if flag then
                res["result"] = true
                res["mac"] = mac
                res["key"] = key
                res["onoff"] = " on"
            else
                res["result"] =  false
                res["mac"] = mac
                res["key"] = key
                res["onoff"] = "off"
            end

        elseif key == "8" and value == "false" then
            local cmd = "bt_daemon -s ".."1 "..string.format("%d", "0x"..id).." 0"   
            lue(cmd)
            local flag = nil
            for i = 1, 10 do
                flag = is_switch_off(mac)    
                if flag then
                    break
                else
                    posix.sleep(1)
                end 
            end
            if flag then
                res["result"] = true
                res["mac"] = mac
                res["key"] = key
                res[ "onoff"] = "off"
            else
                res[ "result"] = false
                res["mac"] = mac
                res["key"] = key
                res["onoff"] = "on"
            end

        elseif key == "0" then
            require "MZLog".log(3, debug.getinfo(1).currentline)
            require "MZLog".log(3, value)
            if #value > 20 then
                value = string.sub(value, 1, 20)
            end
            require "MZLog".log(3, debug.getinfo(1).currentline)
            local name = ""
            for i = 1, #value, 2 do
                name = name.." "..string.format("%d", "0x"..string.sub(value, i, i+1))
            end

            require "MZLog".log(3, debug.getinfo(1).currentline)
            require "MZLog".log(3, name)
            local len = #value
            if len > 20 then
                len = 20
            end
            require "MZLog".log(3, len)
            dbfs.updateBluetoothDevice(id, "", "", len, mac)
            require "MZLog".log(3, debug.getinfo(1).currentline)
            local cmd = "bt_daemon -s ".."13 "..string.format("%d", "0x"..id).." "..name   
            lue(cmd)
            require "MZLog".log(3, debug.getinfo(1).currentline)
            require "MZLog".log(3, cmd)

            local name = ""
            for i = 1, 10 do
                 posix.sleep(1)
                name = is_set_name_ok(mac)
                if name ~= "" then
                    break
                end 
            end

            if name ~= "" and name ~= nil then
                 res ["result"] = true
                res["mac"] = mac
                res["key"] = key
            else
                 res[ "result"] = false
                res["mac"] = mac
                res["key"] = key
            end
            require "MZLog".log(3, debug.getinfo(1).currentline)
            require "MZLog".log(3, debug.getinfo(1).currentline)
        end
    else
        res["result"] = false
        res["mac"] = mac
        res["key"] = key
    end
	--[[
	fd1:write(cjson.encode(res))
	fd1:write("\n")
	fd1:close()
	]]
    return res
end

function nw_set_mesh_device_attr()
    local mac = luci.http.formvalue("mac")
    local key = luci.http.formvalue("key")
    local value = luci.http.formvalue("value")
    local res = set_mesh_device_attr(mac, key, value)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    local cmd = "rm /tmp/meshDeviceFlag"
    lue(cmd)
	
    luci.http.write_json(res)
end

function ww_set_mesh_device_attr(mac, key, value)
    local res = set_mesh_device_attr(mac, key, value)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    local cmd = "rm /tmp/meshDeviceFlag"
    lue(cmd)
    return cjson.encode(res)
end

-----------------------  reboot_mesh_device --------------------
function reboot_mesh_device(mac)
    local res = {}
    local ret = dbfs.fetchBluetoothDevice(mac)
    local id = nil
    for k, v in pairs(ret) do
        id = v.id
    end
    
    local cmd = "bt_daemon -s ".."4 "..string.format("%d", "0x"..id)   
    lue(cmd)
    posix.sleep(2)
    res["result"] = true
end

function nw_reboot_mesh_device()
    local mac = luci.http.formvalue("mac")
    local res = reboot_mesh_device(mac)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_reboot_mesh_device(mac)
    local res = reboot_mesh_device(mac)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  unmesh_all_device --------------------
function unmesh_all_device()
    local res = {}
    local cmd = "bt_daemon -s ".."3 ".."255"
    lue(cmd)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    local wait_time = nil
    for i = 1, 20 do
        wait_time = is_remove_ble_from_mesh()
        if wait_time ~= nil then
            break
		else
            posix.sleep(1)
        end
    end
    if wait_time then
        require "MZLog".log(3, debug.getinfo(1).currentline)
        require "MZLog".log(3, debug.getinfo(1).currentline)
        res["result"] = true
	else
        res["result"] = false
    end
    require "MZLog".log(3, debug.getinfo(1).currentline)
	return res 
end

function nw_unmesh_all_device()
    local res = unmesh_all_device()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_unmesh_all_device()
    local res = unmesh_all_device()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  set_mesh_device_timer --------------------
function is_set_timer_ok(mac, timer_id)
    local TMP = "/tmp/"..mac.."0"
    local fd = io.open(TMP, "r")
    if fd then
        local ret = fd:read()
        ret = cjson.decode(ret)
        fd:close()
        if ret["timerId"] == timer_id then
            return true
        else
            return nil
        end
    else
        return nil
    end
end
function set_mesh_device_timer(mac, timer_id, flag, start_time, end_time)
    local res = {}
    local ret = dbfs.fetchBluetoothDevice(mac) 
    local id = nil
    for k ,v in pairs(ret) do
        id = v.id
    end
    local ret = dbfs.getBleTimerId(id, timer_id)
    if ret == "" then
        dbfs.addBleTimer(id, timer_id, flag, start_time, end_time)
    end
    --[[
    local start = start
    local ends = ends
    if string.len(start) == 6 then
        start = "00"..start
    elseif string.len(start) == 5 then
        start = "000"..start
    elseif string.len(start) == 4 then
        start = "0000"..start
    end

    if string.len(ends) == 6 then
        ends = "00"..ends
    elseif string.len(ends) == 5 then
        ends = "000"..ends
    elseif string.len(ends) == 4 then
        ends = "0000"..ends
    end
    require "MZLog".log(3, mac)
    require "MZLog".log(3, timer_id)
    require "MZLog".log(3, flag)
    require "MZLog".log(3, start)
    require "MZLog".log(3, ends)

    local TMP = "/tmp/"..mac.."1"
    local fd = io.open(TMP, "r")
    if fd then
        local res = fd:read()
        res = cjson.decode(res)
        res["timerId"] = timer_id
        res = cjson.encode(res)
        local fd = io.open(TMP, "w")
        fd:write(res)
        fd:close()
    end

    local start1 = string.sub(start, 1, 2)
    local start2 = string.sub(start, 3, 4)
    local start3 = string.sub(start, 5, 6)
    local start4 = string.sub(start, 7, 8)
    local end1 = string.sub(ends, 1, 2)
    local end2 = string.sub(ends, 3, 4)
    local end3 = string.sub(ends, 5, 6)
    local end4 = string.sub(ends, 7, 8)
    if id then
        local start = string.format("%d", "0x"..start1).." "..
                        string.format("%d", "0x"..start2).." "..
                        string.format("%d", "0x"..start3).." "..
                        string.format("%d", "0x"..start4)
        local ends = string.format("%d", "0x"..end1).." "..
                        string.format("%d", "0x"..end2).." "..
                        string.format("%d", "0x"..end3).." "..
                        string.format("%d", "0x"..end4)

        local cmd = "/root/spi_send ".."6 "..string.format("%d", "0x"..id)..
                    " "..string.format("%d", "0x"..flag).." "..
                    string.format("%d", "0x"..timer_id).." "..start.." "..ends
        require "MZLog".log(3, cmd)
        lue(cmd)

        local times = tonumber(os.time()) - 1420041600
        local res = string.format("%x", times)
        local time = ""
        for i = 1, #res, 2 do
            time = time.." "..string.format("%d", "0x"..string.sub(res, i, i+1))
        end
        local cmd = "/root/spi_send ".."7 "..string.format("%d", "0x"..id).." "..time
        lue(cmd)
    end

    local flag = nil
    for i = 1, 10 do
        posix.sleep(1)
        flag = is_set_timer_ok(mac, timer_id)
        if flag then
            break
        end
    end
    if flag then
        res["result"] = true
        res["mac"] = mac
        res["timerId"] = timer_id
    else
        res["result"] = false
        res["mac"] = mac
        res["timerId"] = timer_id
    end
    return res
    ]]
end

function nw_set_mesh_device_timer()
    local mac = luci.http.formvalue("mac")
    local timer_id = luci.http.formvalue("timerId")
    local flag = luci.http.formvalue("flag")
    local start_time = luci.http.formvalue("start")
    local end_time = luci.http.formvalue("ends")
    local timer = luci.http.formvalue("timer")
    local res = set_mesh_device_timer(mac, timer_id, flag, start_time, end_time)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_set_mesh_device_timer(mac, timer_id, flag, start, ends)
    local res = set_mesh_device_timer(mac, timer_id, flag, start, ends)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  del_mesh_device_timer --------------------
function is_del_timer_ok(mac, timer_id)
    local TMP = "/tmp/"..mac.."0"
    local fd = io.open(TMP, r)
    if fd then
        local ret = fd:read()
        ret = cjson.decode(ret)
        fd:close()
        if ret["timerId"] ~= timer_id then
            return true
        else
            return nil
        end
    else
        return nil
    end
end

function del_mesh_device_timer(mac, timer_id)
    local res = {}
    local ret = dbfs.fetchBluetoothDevice(mac) 
    local id = nil
    for k ,v in pairs(ret) do
        id = v.id
    end
    local ret = dbfs.getBleTimerId(id, timer_id)
    if ret ~= "" then
        dbfs.deleteBleTimer(id, timer_id)
    end
    
    --[[
    local cmd = "/root/spi_send ".."10 "..string.format("%d", "0x"..id)..
                " "..string.format("%d", "0x"..timer_id)
    lue(cmd)
    local flag = nil
    for i = 1, 10 do
        posix.sleep(1)
        flag = is_del_timer_ok(mac, timer_id)
        if flag then
            break
        end
    end
    if flag then
        res["result"] = true
        res["mac"] = mac
        res["timerId"] = timer_id
    else
        res["result"] = false
        res["mac"] = mac
        res["timerId"] = timer_id
    end
    return res
    ]]
end

function nw_del_mesh_device_timer()
    local mac = luci.http.formvalue("mac")
    local timer_id = luci.http.formvalue("timerId")
    local res = del_mesh_device_timer(mac, timer_id)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_del_mesh_device_timer(mac, timer_id)
    local res = del_mesh_device_timer(mac, timer_id)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

-----------------------  set_encry_info --------------------
function is_set_key_ok()
    local TMP = "/tmp/0b0b"
    local fd = io.open(TMP, "r")
    require "MZLog".log(3, debug.getinfo(1).currentline)
	if fd then
        local file = fd:read()
        fd:close()
        if file == "00" then
            local cmd = "rm /tmp/0b0b"
            lue(cmd)
            return true
        elseif file == "01" then
            return false
        end
    end
end

function get_ble_device_key()
    local ret = dbfs.fetchBluetoothDeviceKey()
    if #ret > 0 then
        return ret
    else
        return nil
    end
end

function set_mesh_network_pwd(old_key, new_key)
    --local TMP = "/tmp/"..new_key
    --local fd = io.open(TMP, "w")
    --fd:write(new_key)
    --fd:close()
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, old_key)
	local key = new_key
    require "MZLog".log(3, new_key)
	local cmd = "bt_daemon -s 21"
    lue(cmd)
    local ret = nil
    for i = 1, 10 do
        ret = get_ble_device_key()
        if ret then
            break
        else
            posix.sleep(1) 
        end
    end
	
    if #ret > 0 then
        for k, v in pairs(ret) do
            if v.key ~= old_key then
                old_key = v.key
            end
        end
    end
    
    local res = {}
    require "MZLog".log(3, old_key)
    require "MZLog".log(3, new_key)
    local old_key1 = string.sub(old_key, 1, 1)
    local old_key2 = string.sub(old_key, 2, 2)
    local old_key3 = string.sub(old_key, 3, 3)
    local old_key4 = string.sub(old_key, 4, 4)
    local new_key1 = string.sub(new_key, 1, 1)
    local new_key2 = string.sub(new_key, 2, 2)
    local new_key3 = string.sub(new_key, 3, 3)
    local new_key4 = string.sub(new_key, 4, 4)
    require "MZLog".log(3, old_key1)
    require "MZLog".log(3, old_key2)
    require "MZLog".log(3, old_key3)
    require "MZLog".log(3, old_key4)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, debug.getinfo(1).currentline)

	--bt_daemon -s 21 

    local old_key = old_key1.." "..old_key2.." "..old_key3.." "..old_key4
    local new_key = new_key1.." "..new_key2.." "..new_key3.." "..new_key4
    local cmd = "bt_daemon -s ".."9 "..old_key.." "..new_key   
    lue(cmd)
    require "MZLog".log(3, cmd)
    local flag = nil
    for i = 1, 10 do
        flag = is_set_key_ok()
        if flag ~= nil then
            break
		else
            posix.sleep(1) 
        end
    end
    require "MZLog".log(3, debug.getinfo(1).currentline)
    require "MZLog".log(3, ret)
    if flag then
        if #ret > 0 then
            for k, v in pairs(ret) do
                local mac = v.mac
                dbfs.updateBluetoothDevice("", key, "", "", mac)
            end
        end	
    end

    if flag then
        res["result"] = true
        res["newKey"] = key
    else
        res["result"] = false
        res["newKey"] = key
    end
	return res
end

function nw_set_mesh_network_pwd()
    local old_key = luci.http.formvalue("oldKey")
    local new_key = luci.http.formvalue("newKey")
    local res = set_mesh_network_pwd(old_key, new_key)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_set_mesh_network_pwd (old_key, new_key)
    local res = set_mesh_network_pwd(old_key, new_key)
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    return cjson.encode(res)
end

function set_lamp_brightness()
    
end

function nw_set_lamp_brightness()
    local res = set_lamp_brightness()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

function ww_set_lamp_brightness()
    local res = set_lamp_brightness()
    require "MZLog".log(3, res)
    require "MZLog".log(3, debug.getinfo(1).currentline)
    luci.http.write_json(res)
end

------------------------  bluetooth  --------------------

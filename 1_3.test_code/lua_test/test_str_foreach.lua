
---------------------Lua遍历字符串的每个字符-----------------------
local s = "hello hi, again"  
print(string.len(s))


--[[----failed,expected table, not a string
for i, v in ipairs(s) do
    print(v)
end
]]--


--[[success！
for i = 0, #s do
    print(string.sub(s, i, i))
end
]]--


--------------------------wifi repeat代码--------------------------
local repeat_var_from_http = "1111111"
if string.len(repeat_var_from_http) ~= 7 then
    print("len error")
    return
end
local week_repeat = ""
for i = 1, #repeat_var_from_http do
    tmp = string.sub(repeat_var_from_http, i, i)
    if tmp == "1" then
        if(i == #repeat_var_from_http) then
            week_repeat = week_repeat..0
            break
        end
        week_repeat = week_repeat..tostring(i)..","
    end
end
print(week_repeat)

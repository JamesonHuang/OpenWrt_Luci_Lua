local val = "1,2,3,4,5,6,0"
--local val = "1,2,3,4,5,6,0"
--local val = "1,2,3,4,5,6,0"
--local val = "1,3,5"
local repeat_var = "0000000"
for i = 1, #val, 2 do
    idx = string.sub(val, i, i)
    if idx ~= tostring(0) then
        repeat_var = string.sub(repeat_var, 0, idx - 1) .. tostring(1) .. string.sub(repeat_var, idx + 1, #repeat_var)
    else
        repeat_var = string.sub(repeat_var, 1, #repeat_var - 1) .. tostring(1) 
    end
    print(repeat_var)
end

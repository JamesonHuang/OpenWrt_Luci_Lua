--[[print("lua")
tmp2 = os.date("!*t")
print(tmp2)
tmp = os.date("*t")
print(os.date())
print(tmp)
local x = os.time()
print(x)
]]--
print(os.date(now))
print(os.date("%x"))
print(os.date("%M"))
print(os.date("*t", now))  --table
print(os.date("!*t", now)) --UTC

--字符串形式
local tmp = os.date(now)
print(tmp)
--转table
local tmp2 = os.date("!*t", tmp)

print( os.time(tmp2))

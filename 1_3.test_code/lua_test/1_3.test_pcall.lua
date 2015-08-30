
function testPcall()
    rint("error")   
end

local r, msg = pcall(function()
                      testPcall()
                end)
if r == false then
    print("error msg:"..msg)
    print("end")
end

--                    rint("error")


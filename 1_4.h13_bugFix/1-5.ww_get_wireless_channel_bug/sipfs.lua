diff --git a/package/meizu/binary/files/dev_lua/usr/lib/lua/meizu/sipfs.lua b/package/meizu/binary/files/dev_lua/usr/lib/lua/meizu/sipfs.lua
index f5c7659..2aef8ae 100644
--- a/package/meizu/binary/files/dev_lua/usr/lib/lua/meizu/sipfs.lua
+++ b/package/meizu/binary/files/dev_lua/usr/lib/lua/meizu/sipfs.lua
@@ -431,7 +431,19 @@ sip_cmd_process_action = {
                sip_response_uploader(cmd, cmdid, data)
        end,
        ["getWirelessChannel"] = function(cmd, cmdid)
-               local ret = ww_get_wireless_channel()
+        local data = sip_get_parameters(cmdid)
+               local jsr = cjson.decode(data)
+               local value = jsr.value
+               local ret = ""
+               if (jsr.code) == "200" then
+                       for k, v in pairs(value) do
+                               if k == "commandRequest" then
+                                       local jsr = cjson.decode(v)
+                                       local wl_type = jsr.type
+                    ret = ww_get_wireless_channel(wl_type)
+                               end
+                       end
+               end
                sip_response_uploader(cmd, cmdid, ret)
        end,
        ["setWirelessChannel"] = function(cmd, cmdid)


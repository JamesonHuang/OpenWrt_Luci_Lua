// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import java.util.*;
import org.json.*;

// Referenced classes of package com.splunk.mint:
//            Properties, ExtraData, EnumActionType

abstract class BaseDTO
{

    public BaseDTO(EnumActionType enumactiontype, HashMap hashmap)
    {
        timestampMilis = Long.valueOf(System.currentTimeMillis());
        sdkVersion = "4.1";
        platform = "Android";
        type = enumactiontype;
        apiKey = Properties.API_KEY;
        StringBuilder stringbuilder = new StringBuilder();
        String s;
        if(Properties.PHONE_BRAND != null)
            s = (new StringBuilder()).append(Properties.PHONE_BRAND).append(" ").toString();
        else
            s = "";
        device = stringbuilder.append(s).append(Properties.PHONE_MODEL).toString();
        osVersion = Properties.OS_VERSION;
        appVersionCode = Properties.APP_VERSIONCODE;
        appVersionName = Properties.APP_VERSIONNAME;
        packageName = Properties.APP_PACKAGE;
        locale = Properties.LOCALE;
        rooted = Boolean.valueOf(Properties.HAS_ROOT);
        uuid = Properties.UID;
        userIdentifier = Properties.userIdentifier;
        carrier = Properties.CARRIER;
        remoteIP = "{%#@@#%}";
        connection = Properties.CONNECTION;
        state = Properties.STATE;
        extraData = Properties.extraData;
        screenOrientation = Properties.SCREEN_ORIENTATION;
        customData = hashmap;
    }

    public JSONObject getBasicDataFixtureJson()
    {
        JSONObject jsonobject = new JSONObject();
        JSONObject jsonobject1;
        Iterator iterator2;
        jsonobject.put("sdkVersion", sdkVersion);
        jsonobject.put("apiKey", apiKey);
        jsonobject.put("platform", platform);
        jsonobject.put("device", device);
        jsonobject.put("osVersion", osVersion);
        jsonobject.put("locale", locale);
        jsonobject.put("uuid", uuid);
        jsonobject.put("userIdentifier", userIdentifier);
        jsonobject.put("carrier", carrier);
        jsonobject.put("remoteIP", remoteIP);
        jsonobject.put("appVersionCode", appVersionCode);
        jsonobject.put("appVersionName", appVersionName);
        jsonobject.put("packageName", packageName);
        jsonobject.put("connection", connection);
        jsonobject.put("state", state);
        jsonobject.put("screenOrientation", screenOrientation);
        jsonobject1 = new JSONObject();
        if(extraData == null || extraData.isEmpty())
            break MISSING_BLOCK_LABEL_310;
        iterator2 = extraData.entrySet().iterator();
_L3:
        java.util.Map.Entry entry1;
        if(!iterator2.hasNext())
            break MISSING_BLOCK_LABEL_310;
        entry1 = (java.util.Map.Entry)iterator2.next();
        if(entry1.getValue() != null) goto _L2; else goto _L1
_L1:
        jsonobject1.put((String)entry1.getKey(), "null");
          goto _L3
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
_L4:
        return jsonobject;
_L2:
        jsonobject1.put((String)entry1.getKey(), entry1.getValue());
          goto _L3
        if(customData != null && !customData.isEmpty())
        {
            for(Iterator iterator1 = customData.entrySet().iterator(); iterator1.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
                if(entry.getValue() == null)
                    jsonobject1.put((String)entry.getKey(), "null");
                else
                    jsonobject1.put((String)entry.getKey(), entry.getValue());
            }

        }
        jsonobject.put("extraData", jsonobject1);
        JSONArray jsonarray = new JSONArray();
        if(Properties.transactions != null)
        {
            for(Iterator iterator = Properties.transactions.iterator(); iterator.hasNext(); jsonarray.put((String)iterator.next()));
        }
        jsonobject.put("transactions", jsonarray);
          goto _L4
    }

    protected static final String UNKNOWN = "NA";
    protected String apiKey;
    protected String appVersionCode;
    protected String appVersionName;
    protected String carrier;
    protected String connection;
    protected HashMap customData;
    protected String device;
    protected ExtraData extraData;
    protected String locale;
    protected String osVersion;
    protected String packageName;
    protected String platform;
    protected String remoteIP;
    protected Boolean rooted;
    protected String screenOrientation;
    protected String sdkVersion;
    protected String state;
    protected Long timestampMilis;
    protected EnumActionType type;
    protected String userIdentifier;
    protected String uuid;
}

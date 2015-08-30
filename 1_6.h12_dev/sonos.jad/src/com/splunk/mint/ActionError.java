// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.content.Context;
import java.util.HashMap;
import org.json.*;

// Referenced classes of package com.splunk.mint:
//            BaseDTO, InterfaceDataType, EnumExceptionType, Properties, 
//            StacktraceHash, Utils, BreadcrumbsLimited, DataSaver, 
//            NetSender, EnumStateStatus, EnumActionType

class ActionError extends BaseDTO
    implements InterfaceDataType
{

    public ActionError(EnumActionType enumactiontype, String s, EnumExceptionType enumexceptiontype, HashMap hashmap)
    {
        super(enumactiontype, hashmap);
        memSysTotal = null;
        memSysAvailable = null;
        stacktrace = s;
        HashMap hashmap1;
        android.app.ActivityManager.MemoryInfo memoryinfo;
        Runtime runtime;
        if(enumexceptiontype == EnumExceptionType.HANDLED)
            handled = Boolean.valueOf(true);
        else
            handled = Boolean.valueOf(false);
        hashmap1 = StacktraceHash.manipulateStacktrace(Properties.APP_PACKAGE, s);
        klass = (String)hashmap1.get("klass");
        message = (String)hashmap1.get("message");
        errorHash = (String)hashmap1.get("errorHash");
        where = (String)hashmap1.get("where");
        gpsStatus = Properties.IS_GPS_ON;
        msFromStart = Utils.getMilisFromStart();
        memoryinfo = new android.app.ActivityManager.MemoryInfo();
        runtime = Runtime.getRuntime();
        if(!handled.booleanValue())
        {
            HashMap hashmap2 = Utils.getMemoryInfo();
            memSysTotal = (String)hashmap2.get("memTotal");
            memSysAvailable = (String)hashmap2.get("memFree");
        }
        memSysThreshold = String.valueOf((double)memoryinfo.threshold / 1048576D);
        memSysLow = String.valueOf(memoryinfo.lowMemory);
        memAppMax = String.valueOf((double)runtime.maxMemory() / 1048576D);
        memAppAvailable = String.valueOf((double)runtime.freeMemory() / 1048576D);
        memAppTotal = String.valueOf((double)runtime.totalMemory() / 1048576D);
        breadcrumbs = Properties.breadcrumbs.getList();
    }

    public ActionError(EnumActionType enumactiontype, String s, String s1, String s2, String s3, HashMap hashmap, EnumExceptionType enumexceptiontype)
    {
        super(enumactiontype, hashmap);
        memSysTotal = null;
        memSysAvailable = null;
        stacktrace = s3;
        android.app.ActivityManager.MemoryInfo memoryinfo;
        Runtime runtime;
        if(enumexceptiontype == EnumExceptionType.HANDLED)
            handled = Boolean.valueOf(true);
        else
            handled = Boolean.valueOf(false);
        klass = s1;
        message = s;
        errorHash = StacktraceHash.getMD5ForJavascriptError(s3);
        where = (new StringBuilder()).append("line: ").append(s2).toString();
        gpsStatus = Properties.IS_GPS_ON;
        msFromStart = Utils.getMilisFromStart();
        memoryinfo = new android.app.ActivityManager.MemoryInfo();
        runtime = Runtime.getRuntime();
        if(!handled.booleanValue())
        {
            HashMap hashmap1 = Utils.getMemoryInfo();
            memSysTotal = (String)hashmap1.get("memTotal");
            memSysAvailable = (String)hashmap1.get("memFree");
        }
        memSysThreshold = String.valueOf((double)memoryinfo.threshold / 1048576D);
        memSysLow = String.valueOf(memoryinfo.lowMemory);
        memAppMax = String.valueOf((double)runtime.maxMemory() / 1048576D);
        memAppAvailable = String.valueOf((double)runtime.freeMemory() / 1048576D);
        memAppTotal = String.valueOf((double)runtime.totalMemory() / 1048576D);
        breadcrumbs = Properties.breadcrumbs.getList();
    }

    protected final String getErrorHash()
    {
        return errorHash;
    }

    public void save(DataSaver datasaver)
    {
        (new DataSaver()).save(toJsonLine());
    }

    public void send(Context context, NetSender netsender, boolean flag)
    {
        netsender.send(toJsonLine(), flag);
    }

    public void send(NetSender netsender, boolean flag)
    {
        netsender.send(toJsonLine(), flag);
    }

    public String toJsonLine()
    {
        JSONObject jsonobject = getBasicDataFixtureJson();
        jsonobject.put("stacktrace", stacktrace);
        jsonobject.put("handled", handled);
        jsonobject.put("klass", klass);
        jsonobject.put("message", message);
        jsonobject.put("errorHash", errorHash);
        jsonobject.put("where", where);
        jsonobject.put("rooted", rooted);
        jsonobject.put("gpsStatus", gpsStatus.toString());
        jsonobject.put("msFromStart", msFromStart);
        if(breadcrumbs != null && breadcrumbs.length() > 0)
            jsonobject.put("breadcrumbs", breadcrumbs);
        jsonobject.put("memSysLow", memSysLow);
        if(!handled.booleanValue())
        {
            jsonobject.put("memSysTotal", memSysTotal);
            jsonobject.put("memSysAvailable", memSysAvailable);
        }
        jsonobject.put("memSysThreshold", memSysThreshold);
        jsonobject.put("memAppMax", memAppMax);
        jsonobject.put("memAppAvailable", memAppAvailable);
        jsonobject.put("memAppTotal", memAppTotal);
        if(!Properties.SEND_LOG) goto _L2; else goto _L1
_L1:
        jsonobject.put("log", Utils.readLogs());
_L4:
        return (new StringBuilder()).append(jsonobject.toString()).append(Properties.getSeparator(EnumActionType.error)).toString();
_L2:
        try
        {
            jsonobject.put("log", "NA");
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private JSONArray breadcrumbs;
    private String errorHash;
    private EnumStateStatus gpsStatus;
    private Boolean handled;
    private String klass;
    private String memAppAvailable;
    private String memAppMax;
    private String memAppTotal;
    private String memSysAvailable;
    private String memSysLow;
    private String memSysThreshold;
    private String memSysTotal;
    private String message;
    private String msFromStart;
    private String stacktrace;
    private String where;
}

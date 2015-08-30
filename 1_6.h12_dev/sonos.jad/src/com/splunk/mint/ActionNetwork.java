// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.content.Context;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.splunk.mint:
//            BaseDTO, InterfaceDataType, EnumActionType, DataSaver, 
//            NetSender, Properties

public class ActionNetwork extends BaseDTO
    implements InterfaceDataType
{

    public ActionNetwork(EnumActionType enumactiontype, String s, HashMap hashmap)
    {
        super(enumactiontype, hashmap);
        url = "";
        latency = Long.valueOf(0L);
        statusCode = Integer.valueOf(0);
        responseLength = Long.valueOf(0L);
        requestLength = Long.valueOf(0L);
        failed = Boolean.valueOf(true);
        exception = "NA";
        protocol = "NA";
        url = s;
    }

    public static final void logNetwork(String s, long l, long l1, String s1, int i, long l2, long l3, String s2, HashMap hashmap)
    {
        ActionNetwork actionnetwork = new ActionNetwork(EnumActionType.network, s, hashmap);
        actionnetwork.latency = Long.valueOf(l1 - l);
        actionnetwork.statusCode = Integer.valueOf(i);
        actionnetwork.responseLength = Long.valueOf(l3);
        actionnetwork.requestLength = Long.valueOf(l2);
        if(actionnetwork.statusCode.intValue() >= 200 && actionnetwork.statusCode.intValue() < 400)
            actionnetwork.failed = Boolean.valueOf(false);
        else
            actionnetwork.failed = Boolean.valueOf(true);
        actionnetwork.exception = s2;
        actionnetwork.protocol = s1;
        actionnetwork.save(new DataSaver());
    }

    private static final String stripHttpFromUrl(String s)
    {
        if(s == null) goto _L2; else goto _L1
_L1:
        if(!s.toLowerCase().startsWith("http://")) goto _L4; else goto _L3
_L3:
        s = s.replaceFirst("(?i)http://", "");
_L2:
        return s;
_L4:
        if(s.toLowerCase().startsWith("https://"))
            s = s.replaceFirst("(?i)https://", "");
        if(true) goto _L2; else goto _L5
_L5:
    }

    public volatile JSONObject getBasicDataFixtureJson()
    {
        return super.getBasicDataFixtureJson();
    }

    public void save(DataSaver datasaver)
    {
        datasaver.save(toJsonLine());
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
        jsonobject.put("url", stripHttpFromUrl(url));
        jsonobject.put("latency", latency);
        jsonobject.put("statusCode", statusCode);
        jsonobject.put("responseLength", responseLength);
        jsonobject.put("requestLength", requestLength);
        jsonobject.put("failed", failed);
        jsonobject.put("protocol", protocol);
        if(exception == null || exception.length() <= 0) goto _L2; else goto _L1
_L1:
        jsonobject.put("exception", exception);
_L4:
        return (new StringBuilder()).append(jsonobject.toString()).append(Properties.getSeparator(EnumActionType.network)).toString();
_L2:
        try
        {
            jsonobject.put("exception", "NA");
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private String exception;
    private Boolean failed;
    private Long latency;
    private String protocol;
    private Long requestLength;
    private Long responseLength;
    private Integer statusCode;
    private String url;
}

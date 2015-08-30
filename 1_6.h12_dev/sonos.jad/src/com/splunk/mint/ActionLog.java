// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.splunk.mint:
//            BaseDTO, InterfaceDataType, EnumActionType, Utils, 
//            DataSaver, Logger, NetSender, Properties, 
//            MintLogLevel

class ActionLog extends BaseDTO
    implements InterfaceDataType
{

    public ActionLog(EnumActionType enumactiontype, String s, Integer integer)
    {
        super(enumactiontype, null);
        eventName = "";
        eventLevel = Integer.valueOf(2);
        eventName = s;
        eventLevel = integer;
    }

    public static final ActionLog createLog(String s, MintLogLevel mintloglevel)
    {
        return new ActionLog(EnumActionType.log, s, Integer.valueOf(Utils.convertLoggingLevelToInt(mintloglevel)));
    }

    public void save(DataSaver datasaver)
    {
        if(eventLevel != null)
        {
            if(eventLevel.intValue() >= Properties.RemoteSettingsProps.logLevel.intValue())
                datasaver.save(toJsonLine());
            else
                Logger.logInfo("Logs's level is lower than the minimum level from Remote Settings, log will not be saved");
        } else
        {
            datasaver.save(toJsonLine());
        }
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
        try
        {
            jsonobject.put("log_name", eventName);
            jsonobject.put("level", eventLevel);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        return (new StringBuilder()).append(jsonobject.toString()).append(Properties.getSeparator(type)).toString();
    }

    public Integer eventLevel;
    public String eventName;
}

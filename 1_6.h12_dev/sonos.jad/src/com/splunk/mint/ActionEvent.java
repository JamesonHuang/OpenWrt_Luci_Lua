// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.content.Context;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.splunk.mint:
//            BaseDTO, InterfaceDataType, EnumActionType, Utils, 
//            MintLogLevel, Properties, DataSaver, Logger, 
//            MintUrls, NetSender, NetSenderResponse, RemoteSettings

class ActionEvent extends BaseDTO
    implements InterfaceDataType
{

    public ActionEvent(EnumActionType enumactiontype, String s, Integer integer, HashMap hashmap)
    {
        super(enumactiontype, hashmap);
        eventName = "";
        eventLevel = null;
        duration = -1L;
        session_id = "";
        eventName = s;
        eventLevel = integer;
        if(enumactiontype != EnumActionType.ping) goto _L2; else goto _L1
_L1:
        session_id = Utils.getRandomSessionNumber();
        savedSessionID = session_id;
_L4:
        return;
_L2:
        if(enumactiontype == EnumActionType.gnip)
            session_id = savedSessionID;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final ActionEvent createEvent(String s)
    {
        return new ActionEvent(EnumActionType.event, s, Integer.valueOf(Utils.convertLoggingLevelToInt(MintLogLevel.Verbose)), null);
    }

    public static final ActionEvent createEvent(String s, MintLogLevel mintloglevel, HashMap hashmap)
    {
        return new ActionEvent(EnumActionType.event, s, Integer.valueOf(Utils.convertLoggingLevelToInt(mintloglevel)), hashmap);
    }

    public static final ActionEvent createGnip()
    {
        ActionEvent actionevent = new ActionEvent(EnumActionType.gnip, null, null, null);
        actionevent.duration = actionevent.timestampMilis.longValue() - Properties.lastPingTime;
        return actionevent;
    }

    public static final ActionEvent createPing()
    {
        ActionEvent actionevent = new ActionEvent(EnumActionType.ping, null, null, null);
        Properties.lastPingTime = actionevent.timestampMilis.longValue();
        return actionevent;
    }

    public void save(DataSaver datasaver)
    {
        if(eventLevel != null)
        {
            if(eventLevel.intValue() >= Properties.RemoteSettingsProps.eventLevel.intValue())
                datasaver.save(toJsonLine());
            else
                Logger.logInfo("Event's level is lower than the minimum level from Remote Settings, event will not be saved");
        } else
        {
            datasaver.save(toJsonLine());
        }
    }

    public void send(Context context, NetSender netsender, boolean flag)
    {
        if(type.equals(EnumActionType.ping))
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(MintUrls.getURL(0, 1));
            stringbuilder.append("?hash=");
            stringbuilder.append(Properties.RemoteSettingsProps.hashCode);
            RemoteSettingsData remotesettingsdata = RemoteSettings.convertJsonToRemoteSettings(netsender.sendBlocking(stringbuilder.toString(), toJsonLine(), flag).getServerResponse());
            if(remotesettingsdata != null)
                RemoteSettings.saveAndLoadRemoteSettings(context, remotesettingsdata);
        } else
        {
            netsender.send(toJsonLine(), flag);
        }
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
            if(duration != -1L)
                jsonobject.put("ses_duration", duration);
            if(eventName != null)
                jsonobject.put("event_name", eventName);
            if(eventLevel != null)
                jsonobject.put("level", eventLevel);
            if(type != EnumActionType.event)
                jsonobject.put("session_id", session_id);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        return (new StringBuilder()).append(jsonobject.toString()).append(Properties.getSeparator(type)).toString();
    }

    protected static String savedSessionID = "";
    protected long duration;
    protected Integer eventLevel;
    protected String eventName;
    protected String session_id;

}

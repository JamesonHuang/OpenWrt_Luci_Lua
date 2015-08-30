// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.splunk.mint:
//            RemoteSettingsData, Logger

class RemoteSettings
{

    RemoteSettings()
    {
    }

    protected static final RemoteSettingsData convertJsonToRemoteSettings(String s)
    {
        if(s != null && s.length() >= 1) goto _L2; else goto _L1
_L1:
        RemoteSettingsData remotesettingsdata = null;
_L4:
        return remotesettingsdata;
_L2:
        remotesettingsdata = new RemoteSettingsData();
        try
        {
            JSONObject jsonobject = (new JSONObject(s)).optJSONObject("remSetVer1");
            if(jsonobject != null)
            {
                remotesettingsdata.logLevel = Integer.valueOf(jsonobject.optInt("logLevel"));
                remotesettingsdata.eventLevel = Integer.valueOf(jsonobject.getInt("eventLevel"));
                remotesettingsdata.netMonitoring = Boolean.valueOf(jsonobject.optBoolean("netMonitoring"));
                remotesettingsdata.sessionTime = Integer.valueOf(jsonobject.optInt("sessionTime"));
                remotesettingsdata.devSettings = jsonobject.optJSONObject("devSettings").toString();
                remotesettingsdata.hashCode = jsonobject.optString("hash");
            }
        }
        catch(JSONException jsonexception)
        {
            Logger.logError("Could not convert json to remote data");
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected static final RemoteSettingsData loadRemoteSettings(Context context)
    {
        RemoteSettingsData remotesettingsdata = new RemoteSettingsData();
        SharedPreferences sharedpreferences = context.getSharedPreferences("REMOTESETTINGSSETTINGS", 0);
        if(sharedpreferences == null)
        {
            remotesettingsdata = null;
        } else
        {
            remotesettingsdata.logLevel = Integer.valueOf(sharedpreferences.getInt("logLevel", Properties.RemoteSettingsProps.logLevel.intValue()));
            remotesettingsdata.eventLevel = Integer.valueOf(sharedpreferences.getInt("eventLevel", Properties.RemoteSettingsProps.eventLevel.intValue()));
            remotesettingsdata.netMonitoring = Boolean.valueOf(sharedpreferences.getBoolean("netMonitoring", Properties.RemoteSettingsProps.netMonitoringEnabled.booleanValue()));
            remotesettingsdata.sessionTime = Integer.valueOf(sharedpreferences.getInt("sessionTime", Properties.RemoteSettingsProps.sessionTime.intValue()));
            remotesettingsdata.devSettings = sharedpreferences.getString("devSettings", Properties.RemoteSettingsProps.devSettings.toString());
            remotesettingsdata.hashCode = sharedpreferences.getString("hashCode", Properties.RemoteSettingsProps.hashCode);
        }
        return remotesettingsdata;
    }

    protected static final boolean saveAndLoadRemoteSettings(Context context, RemoteSettingsData remotesettingsdata)
    {
        boolean flag;
        SharedPreferences sharedpreferences;
        flag = false;
        sharedpreferences = context.getSharedPreferences("REMOTESETTINGSSETTINGS", 0);
        if(sharedpreferences != null) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        if(editor != null)
        {
            if(remotesettingsdata.logLevel != null && remotesettingsdata.logLevel.intValue() > 0)
            {
                editor.putInt("logLevel", remotesettingsdata.logLevel.intValue());
                Properties.RemoteSettingsProps.logLevel = remotesettingsdata.logLevel;
            }
            if(remotesettingsdata.eventLevel != null && remotesettingsdata.eventLevel.intValue() > 0)
            {
                editor.putInt("eventLevel", remotesettingsdata.eventLevel.intValue());
                Properties.RemoteSettingsProps.eventLevel = remotesettingsdata.eventLevel;
            }
            if(remotesettingsdata.netMonitoring != null)
            {
                editor.putBoolean("netMonitoring", remotesettingsdata.netMonitoring.booleanValue());
                Properties.RemoteSettingsProps.netMonitoringEnabled = remotesettingsdata.netMonitoring;
            }
            if(remotesettingsdata.sessionTime != null && remotesettingsdata.sessionTime.intValue() > 0)
            {
                editor.putInt("sessionTime", remotesettingsdata.sessionTime.intValue());
                Properties.RemoteSettingsProps.sessionTime = remotesettingsdata.sessionTime;
            }
            if(remotesettingsdata.devSettings != null)
            {
                editor.putString("devSettings", remotesettingsdata.devSettings);
                try
                {
                    Properties.RemoteSettingsProps.devSettings = new JSONObject(remotesettingsdata.devSettings);
                }
                catch(JSONException jsonexception)
                {
                    jsonexception.printStackTrace();
                }
            }
            if(remotesettingsdata.hashCode != null && remotesettingsdata.hashCode.length() > 1)
            {
                editor.putString("hashCode", remotesettingsdata.hashCode);
                Properties.RemoteSettingsProps.hashCode = remotesettingsdata.hashCode;
            }
            flag = editor.commit();
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private static final String DEVSETTINGS = "devSettings";
    private static final String EVENTLEVEL = "eventLevel";
    private static final String HASHCODE = "hashCode";
    private static final String LOGLEVEL = "logLevel";
    private static final String NETWORKMONITORING = "netMonitoring";
    private static final String REMOTESETTINGS_API = "1";
    private static final String REMOTESETTINGS_NAME = "remSetVer";
    private static final String SESSIONTIME = "sessionTime";
    private static final String SHARED_PREFERENSES_NAME = "REMOTESETTINGSSETTINGS";
}

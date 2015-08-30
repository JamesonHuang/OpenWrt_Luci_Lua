// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.util;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.ConfigurationWatchList;
import ch.qos.logback.core.status.*;
import java.io.PrintStream;
import java.net.URL;

public class ConfigurationWatchListUtil
{

    private ConfigurationWatchListUtil()
    {
    }

    static void addInfo(Context context, String s)
    {
        addStatus(context, new InfoStatus(s, origin));
    }

    static void addStatus(Context context, Status status)
    {
        if(context != null) goto _L2; else goto _L1
_L1:
        System.out.println((new StringBuilder()).append("Null context in ").append(ch/qos/logback/core/joran/spi/ConfigurationWatchList.getName()).toString());
_L4:
        return;
_L2:
        StatusManager statusmanager = context.getStatusManager();
        if(statusmanager != null)
            statusmanager.add(status);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void addToWatchList(Context context, URL url)
    {
        ConfigurationWatchList configurationwatchlist = getConfigurationWatchList(context);
        if(configurationwatchlist == null)
        {
            addWarn(context, (new StringBuilder()).append("Null ConfigurationWatchList. Cannot add ").append(url).toString());
        } else
        {
            addInfo(context, (new StringBuilder()).append("Adding [").append(url).append("] to configuration watch list.").toString());
            configurationwatchlist.addToWatchList(url);
        }
    }

    static void addWarn(Context context, String s)
    {
        addStatus(context, new WarnStatus(s, origin));
    }

    public static ConfigurationWatchList getConfigurationWatchList(Context context)
    {
        ConfigurationWatchList configurationwatchlist;
        if(context == null)
            configurationwatchlist = null;
        else
            configurationwatchlist = (ConfigurationWatchList)context.getObject("CONFIGURATION_WATCH_LIST");
        return configurationwatchlist;
    }

    public static URL getMainWatchURL(Context context)
    {
        ConfigurationWatchList configurationwatchlist = getConfigurationWatchList(context);
        URL url;
        if(configurationwatchlist == null)
            url = null;
        else
            url = configurationwatchlist.getMainURL();
        return url;
    }

    public static void setConfigurationWatchListResetFlag(Context context, boolean flag)
    {
        context.putObject("CONFIGURATION_WATCH_LIST_RESET", Boolean.valueOf(flag));
    }

    public static void setMainWatchURL(Context context, URL url)
    {
        if(context != null)
        {
            ConfigurationWatchList configurationwatchlist = getConfigurationWatchList(context);
            if(configurationwatchlist == null)
            {
                configurationwatchlist = new ConfigurationWatchList();
                configurationwatchlist.setContext(context);
                context.putObject("CONFIGURATION_WATCH_LIST", configurationwatchlist);
            } else
            {
                configurationwatchlist.clear();
            }
            setConfigurationWatchListResetFlag(context, true);
            configurationwatchlist.setMainURL(url);
        }
    }

    public static boolean wasConfigurationWatchListReset(Context context)
    {
        boolean flag;
        if(context == null)
        {
            flag = false;
        } else
        {
            Object obj = context.getObject("CONFIGURATION_WATCH_LIST_RESET");
            if(obj == null)
                flag = false;
            else
                flag = ((Boolean)obj).booleanValue();
        }
        return flag;
    }

    static final ConfigurationWatchListUtil origin = new ConfigurationWatchListUtil();

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.network;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.*;
import android.telephony.TelephonyManager;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.network.ethernetiface.EthernetIfaceManager;
import com.sonos.acr.network.netiface.wifiifacebasic.WifiBasicNetIfaceManager;
import com.sonos.acr.util.SLog;
import java.util.*;

// Referenced classes of package com.sonos.acr.network:
//            NetIfaceListener, NetIfaceManager

public class SonosNetworkManager
    implements NetIfaceListener
{
    public static interface ConnectionListener
    {

        public abstract void onConnectionStatusChange(SonosNetworkManager sonosnetworkmanager);
    }


    public SonosNetworkManager(SonosApplication sonosapplication)
    {
        networkListeners = new ArrayList();
        connectivityManager = (ConnectivityManager)sonosapplication.getSystemService("connectivity");
        context = sonosapplication;
        SLog.d("SonosNetworkManager", "Starting NetworkManager");
    }

    public String getNetworkSSID()
    {
        WifiManager wifimanager = (WifiManager)context.getSystemService("wifi");
        if(wifimanager == null) goto _L2; else goto _L1
_L1:
        WifiInfo wifiinfo = wifimanager.getConnectionInfo();
        if(wifiinfo == null) goto _L2; else goto _L3
_L3:
        String s;
        s = wifiinfo.getSSID();
        if(s == null)
            s = "";
_L5:
        return s;
_L2:
        s = "";
        if(true) goto _L5; else goto _L4
_L4:
    }

    public String getWiFiBSSID()
    {
        WifiInfo wifiinfo = ((WifiManager)context.getSystemService("wifi")).getConnectionInfo();
        String s;
        if(wifiinfo != null)
            s = wifiinfo.getBSSID();
        else
            s = "";
        return s;
    }

    public String getWiFiNetworkName()
    {
        WifiInfo wifiinfo = ((WifiManager)context.getSystemService("wifi")).getConnectionInfo();
        String s;
        if(wifiinfo != null)
            s = wifiinfo.getSSID();
        else
            s = "";
        return s;
    }

    public int getWifiNetworkSecurity()
    {
        byte byte0;
        WifiManager wifimanager;
        WifiInfo wifiinfo;
        byte0 = 4;
        wifimanager = (WifiManager)context.getSystemService("wifi");
        wifiinfo = wifimanager.getConnectionInfo();
        if(wifiinfo != null) goto _L2; else goto _L1
_L1:
        return byte0;
_L2:
        String s = wifiinfo.getSSID().replaceAll("^\"|\"$", "");
        Iterator iterator = wifimanager.getScanResults().iterator();
        ScanResult scanresult;
        do
        {
            if(!iterator.hasNext())
                continue; /* Loop/switch isn't completed */
            scanresult = (ScanResult)iterator.next();
        } while(!scanresult.SSID.equals(s));
        if(scanresult.capabilities.contains("WEP"))
            byte0 = 2;
        else
        if(scanresult.capabilities.contains("PSK"))
            byte0 = 0;
        else
        if(scanresult.capabilities.contains("EAP"))
            byte0 = 1;
        else
            byte0 = 3;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean hasMobileConnection()
    {
        NetworkInfo anetworkinfo[];
        int i;
        int j;
        anetworkinfo = connectivityManager.getAllNetworkInfo();
        if(anetworkinfo == null || anetworkinfo.length <= 0)
            break MISSING_BLOCK_LABEL_69;
        i = anetworkinfo.length;
        j = 0;
_L3:
        NetworkInfo networkinfo;
        if(j >= i)
            break MISSING_BLOCK_LABEL_69;
        networkinfo = anetworkinfo[j];
        if(!networkinfo.isConnected() || !networkinfo.getTypeName().toLowerCase().contains("mobile")) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        j++;
          goto _L3
        flag = false;
          goto _L4
    }

    public boolean hasMobileConnectionAbility()
    {
        boolean flag;
        TelephonyManager telephonymanager;
        flag = false;
        telephonymanager = (TelephonyManager)context.getSystemService("phone");
        if(telephonymanager != null && telephonymanager.getNetworkType() != 0) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        NetworkInfo anetworkinfo[];
        int i;
        int j;
        NetworkInfo networkinfo = connectivityManager.getNetworkInfo(0);
        if(networkinfo == null || networkinfo.getState() == android.net.NetworkInfo.State.UNKNOWN)
            continue; /* Loop/switch isn't completed */
        anetworkinfo = connectivityManager.getAllNetworkInfo();
        if(anetworkinfo == null || anetworkinfo.length <= 0)
            continue; /* Loop/switch isn't completed */
        i = anetworkinfo.length;
        j = 0;
_L4:
        if(j < i)
        {
label0:
            {
                NetworkInfo networkinfo1 = anetworkinfo[j];
                if(!networkinfo1.getTypeName().toLowerCase().contains("mobile") || networkinfo1.getState() == android.net.NetworkInfo.State.UNKNOWN)
                    break label0;
                flag = true;
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
        j++;
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    public boolean hasNoInternetConnection(boolean flag)
    {
        NetworkInfo anetworkinfo[];
        int i;
        int j;
        anetworkinfo = connectivityManager.getAllNetworkInfo();
        if(anetworkinfo == null || anetworkinfo.length <= 0)
            break MISSING_BLOCK_LABEL_67;
        i = anetworkinfo.length;
        j = 0;
_L3:
        NetworkInfo networkinfo;
        if(j >= i)
            break MISSING_BLOCK_LABEL_67;
        networkinfo = anetworkinfo[j];
        if(!networkinfo.isConnected() && (!networkinfo.isConnectedOrConnecting() || !flag)) goto _L2; else goto _L1
_L1:
        boolean flag1 = false;
_L4:
        return flag1;
_L2:
        j++;
          goto _L3
        flag1 = true;
          goto _L4
    }

    public boolean hasWifiConnection()
    {
        boolean flag;
        NetworkInfo anetworkinfo[];
        int i;
        int j;
        flag = true;
        anetworkinfo = connectivityManager.getAllNetworkInfo();
        if(anetworkinfo == null || anetworkinfo.length <= 0)
            break MISSING_BLOCK_LABEL_62;
        i = anetworkinfo.length;
        j = 0;
_L3:
        NetworkInfo networkinfo;
        if(j >= i)
            break MISSING_BLOCK_LABEL_62;
        networkinfo = anetworkinfo[j];
        if(!networkinfo.isConnected() || networkinfo.getType() != flag) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        j++;
          goto _L3
        flag = false;
          goto _L1
    }

    public boolean isRunning()
    {
        boolean flag;
        if(connectors.size() != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isWifiAvailable()
    {
        Iterator iterator = connectors.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        if(!((NetIfaceManager)iterator.next()).isAvailable()) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        return flag;
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public boolean isWifiConnected()
    {
        Iterator iterator = connectors.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        if(!((NetIfaceManager)iterator.next()).isConnected()) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        return flag;
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void onConnectionStatusChange(NetIfaceManager netifacemanager, NetIfaceManager.ConnectionStatus connectionstatus)
    {
        SLog.d("SonosNetworkManager", (new StringBuilder()).append("onConnectionStatusChange( ").append(netifacemanager.getClass().getSimpleName()).append(" ,").append(connectionstatus).append(") wifiConnected: ").append(netifacemanager.isConnected()).toString());
        if(connectionstatus != NetIfaceManager.ConnectionStatus.DISABLED) goto _L2; else goto _L1
_L1:
        netifacemanager.stopMonitoring();
_L4:
        for(Iterator iterator = networkListeners.iterator(); iterator.hasNext(); ((ConnectionListener)iterator.next()).onConnectionStatusChange(this));
        break; /* Loop/switch isn't completed */
_L2:
        if(connectionstatus == NetIfaceManager.ConnectionStatus.ENABLED)
            netifacemanager.startMonitoring();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onFactoryReset()
    {
        for(Iterator iterator = connectors.iterator(); iterator.hasNext(); ((NetIfaceManager)iterator.next()).onFactoryReset());
    }

    public void start()
    {
        if(connectors.size() == 0)
        {
            connectors.add(new WifiBasicNetIfaceManager(context, this));
            connectors.add(new EthernetIfaceManager(context, this));
        }
        Iterator iterator = connectors.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            NetIfaceManager netifacemanager = (NetIfaceManager)iterator.next();
            if(netifacemanager.isEnabled())
                netifacemanager.startMonitoring();
        } while(true);
    }

    public void stop()
    {
        for(Iterator iterator = connectors.iterator(); iterator.hasNext(); ((NetIfaceManager)iterator.next()).stopMonitoring());
        connectors.clear();
    }

    public void subscribe(ConnectionListener connectionlistener)
    {
        if(!networkListeners.contains(connectionlistener))
            networkListeners.add(connectionlistener);
    }

    public void unsubscribe(ConnectionListener connectionlistener)
    {
        networkListeners.remove(connectionlistener);
    }

    public static final String ENABLE_SONOSNET_FEATURE = "sonosnetFeature";
    public static final String LOG_TAG = "SonosNetworkManager";
    public static final int SECURITY_EAP = 1;
    public static final int SECURITY_NONE = 3;
    public static final int SECURITY_PSK = 0;
    public static final int SECURITY_UNKNOWN = 4;
    public static final int SECURITY_WEP = 2;
    protected ConnectivityManager connectivityManager;
    final ArrayList connectors = new ArrayList(3);
    protected SonosApplication context;
    private ArrayList networkListeners;
}

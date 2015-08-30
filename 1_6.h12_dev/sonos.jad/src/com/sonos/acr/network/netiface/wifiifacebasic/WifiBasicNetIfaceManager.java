// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.network.netiface.wifiifacebasic;

import android.content.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.network.NetIfaceListener;
import com.sonos.acr.network.NetIfaceManager;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.sclib.sinks.HouseholdEventSink;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.SCINetstartListenerSwigBase;
import com.sonos.sclib.SCISystem;
import java.util.*;

public class WifiBasicNetIfaceManager extends NetIfaceManager
    implements com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener
{

    public WifiBasicNetIfaceManager(Context context, NetIfaceListener netifacelistener)
    {
        super(context, netifacelistener);
        wifi = (WifiManager)context.getSystemService("wifi");
        if(wifi != null)
            wifiLock = wifi.createWifiLock(1, "SonosNetWifiLock");
    }

    private int getHighestPriority()
    {
        int i = 0;
        List list = wifi.getConfiguredNetworks();
        if(list != null)
        {
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
                i = Math.max(i, ((WifiConfiguration)iterator.next()).priority);

        }
        return i;
    }

    private boolean isConnectionMobile(NetworkInfo networkinfo)
    {
        networkinfo.getType();
        JVM INSTR tableswitch 0 5: default 44
    //                   0 48
    //                   1 44
    //                   2 48
    //                   3 48
    //                   4 48
    //                   5 48;
           goto _L1 _L2 _L1 _L2 _L2 _L2 _L2
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        SLog.e("WifiBasicNetIfaceManager", "Network info reporting a Mobile connection!");
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean joinNetwork(String s, String s1)
    {
        String s2 = (new StringBuilder()).append("\"").append(s).append("\"").toString();
        List list = wifi.getConfiguredNetworks();
        if(list != null)
        {
            Iterator iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                WifiConfiguration wificonfiguration1 = (WifiConfiguration)iterator.next();
                if(wificonfiguration1.SSID != null && wificonfiguration1.SSID.contains(s) && !wifi.removeNetwork(wificonfiguration1.networkId))
                    SLog.e("WifiBasicNetIfaceManager", (new StringBuilder()).append("Unable to remove network:").append(wificonfiguration1.networkId).toString());
            } while(true);
        }
        WifiConfiguration wificonfiguration = new WifiConfiguration();
        wificonfiguration.SSID = s2;
        wificonfiguration.hiddenSSID = true;
        wificonfiguration.priority = 1 + getHighestPriority();
        if(wificonfiguration.priority < 0)
            wificonfiguration.priority = 0x7fffffff;
        int i;
        if(s1 != null)
        {
            wificonfiguration.allowedKeyManagement.set(1);
            wificonfiguration.preSharedKey = (new StringBuilder()).append("\"").append(s1).append("\"").toString();
        } else
        {
            wificonfiguration.allowedKeyManagement.set(0);
            wificonfiguration.allowedPairwiseCiphers.set(0);
        }
        i = wifi.addNetwork(wificonfiguration);
        SLog.i("WifiBasicNetIfaceManager", (new StringBuilder()).append("addNetwork returned ").append(i).toString());
        if(i != -1 && wifi.enableNetwork(i, false))
        {
            SLog.d("WifiBasicNetIfaceManager", "Enabling network SUCCESS!");
            if(!wifi.saveConfiguration())
                SLog.e("WifiBasicNetIfaceManager", "Unable to save config after add");
        } else
        {
            SLog.e("WifiBasicNetIfaceManager", "Enabling network failed");
        }
        return true;
    }

    private boolean saveWifiConfiguration()
    {
        boolean flag;
        if(wifiLock != null)
        {
            wifiLock.acquire();
            flag = wifi.saveConfiguration();
            wifiLock.release();
        } else
        {
            flag = false;
        }
        return flag;
    }

    private boolean updateNetwork(String s, String s1)
    {
        if(s == null) goto _L2; else goto _L1
_L1:
        WifiConfiguration wificonfiguration = findNetworkWithSSID(s);
        if(wificonfiguration == null) goto _L2; else goto _L3
_L3:
        if(wificonfiguration.preSharedKey == null) goto _L5; else goto _L4
_L4:
        boolean flag = true;
_L7:
        return flag;
_L5:
        wifi.removeNetwork(wificonfiguration.networkId);
_L2:
        flag = joinNetwork(s, s1);
        if(true) goto _L7; else goto _L6
_L6:
    }

    private void updateSonosnet(boolean flag)
    {
        if(flag)
        {
            Household household = LibraryUtils.getHousehold();
            if(household != null)
            {
                String s = household.getID();
                if(s != null && s.length() > 0)
                    (new AsyncTask() {

                        protected volatile Object doInBackground(Object aobj[])
                        {
                            return doInBackground((Void[])aobj);
                        }

                        protected transient Void doInBackground(Void avoid[])
                        {
                            passPhrase = sciSystem.blockingFetchSonosNetInfo();
                            return null;
                        }

                        protected volatile void onPostExecute(Object obj)
                        {
                            onPostExecute((Void)obj);
                        }

                        protected void onPostExecute(Void void1)
                        {
                            if(passPhrase != null && passPhrase.length() != 0)
                                updateNetwork(hhid, passPhrase);
                            super.onPostExecute(void1);
                        }

                        private String passPhrase;
                        final WifiBasicNetIfaceManager this$0;
                        final String val$hhid;
                        final SCISystem val$sciSystem;

            
            {
                this$0 = WifiBasicNetIfaceManager.this;
                sciSystem = scisystem;
                hhid = s;
                super();
                passPhrase = null;
            }
                    }
).execute(new Void[0]);
            }
        } else
        {
            clearSavedSonosHHIDs();
        }
    }

    public void clearSavedSonosHHIDs()
    {
        List list = wifi.getConfiguredNetworks();
        if(list != null)
        {
            Iterator iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                WifiConfiguration wificonfiguration = (WifiConfiguration)iterator.next();
                if(wificonfiguration.SSID != null && isSonosSSID(wificonfiguration.SSID, wificonfiguration.BSSID))
                {
                    wifi.removeNetwork(wificonfiguration.networkId);
                    saveWifiConfiguration();
                }
            } while(true);
        }
    }

    WifiConfiguration findNetworkWithSSID(String s)
    {
        String s1;
        List list;
        s1 = (new StringBuilder()).append("\"").append(s).append("\"").toString();
        list = wifi.getConfiguredNetworks();
        if(list == null) goto _L2; else goto _L1
_L1:
        Iterator iterator = list.iterator();
_L5:
        if(!iterator.hasNext()) goto _L2; else goto _L3
_L3:
        WifiConfiguration wificonfiguration = (WifiConfiguration)iterator.next();
        if(wificonfiguration == null || wificonfiguration.SSID == null || !s1.contentEquals(wificonfiguration.SSID)) goto _L5; else goto _L4
_L4:
        return wificonfiguration;
_L2:
        wificonfiguration = null;
        if(true) goto _L4; else goto _L6
_L6:
    }

    public boolean isAvailable()
    {
        boolean flag;
        if(isEnabled() && wifi.isWifiEnabled())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isConnected()
    {
        boolean flag;
        if(networkInfo != null && !isConnectionMobile(networkInfo) && networkInfo.isConnected())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isEnabled()
    {
        boolean flag;
        if(wifi != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isSonosSSID(String s, String s1)
    {
        boolean flag;
        flag = false;
        break MISSING_BLOCK_LABEL_2;
        if(s != null && s.length() >= 30 && (s.startsWith("HHID_") || s.startsWith("sonos_") || s.startsWith("Sonos_") || s.startsWith("\"HHID_") || s.startsWith("\"sonos_") || s.startsWith("\"Sonos_")) && (s1 == null || s1.toLowerCase().startsWith("00:0e:58")))
            flag = true;
        return flag;
    }

    public void onFactoryReset()
    {
        super.onFactoryReset();
        if(wifi != null)
            clearSavedSonosHHIDs();
        SonosApplication.getInstance().getSCLibManager().suspendNetworking();
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        updateSonosnet(LibraryUtils.getSystem().isSonosNetAllowed());
    }

    protected void onStartMonitoring()
    {
        appCtx.registerReceiver(wifiConnectivityReceiver, new IntentFilter("android.net.wifi.STATE_CHANGE"));
        appCtx.registerReceiver(wifiConnectivityReceiver, new IntentFilter("android.net.wifi.supplicant.CONNECTION_CHANGE"));
        networkInfo = connectivityManager.getNetworkInfo(1);
        SCISystem scisystem = LibraryUtils.getSystem();
        if(scisystem != null)
            scisystem.setNetstartListener(netstartListener);
        HouseholdEventSink.getInstance().addListener(this);
    }

    protected void onStopMonitoring()
    {
        appCtx.unregisterReceiver(wifiConnectivityReceiver);
        SCISystem scisystem = LibraryUtils.getSystem();
        if(scisystem != null)
            scisystem.setNetstartListener(null);
        HouseholdEventSink.getInstance().removeListener(this);
    }

    protected static final String LOG_TAG = "WifiBasicNetIfaceManager";
    public static final String SONOS_VENDOR_PREFIX = "00:0e:58";
    final SCINetstartListenerSwigBase netstartListener = new SCINetstartListenerSwigBase() {

        public void onDeviceDiscoveryWaiting()
        {
        }

        public void onJoinComplete()
        {
        }

        public void onJoinFail()
        {
        }

        public void onNetParamsAcquired(String s, String s1, int i)
        {
        }

        public void onSonosnetOptionChange(boolean flag)
        {
            updateSonosnet(flag);
        }

        public void onSonosnetPasswordReset()
        {
            clearSavedSonosHHIDs();
            SonosApplication.getInstance().getSCLibManager().rebindNetworkSockets();
        }

        final WifiBasicNetIfaceManager this$0;

            
            {
                this$0 = WifiBasicNetIfaceManager.this;
                super();
            }
    }
;
    protected NetworkInfo networkInfo;
    protected WifiManager wifi;
    private final BroadcastReceiver wifiConnectivityReceiver = new BroadcastReceiver() {

        public void onReceive(Context context1, Intent intent)
        {
            String s;
            SLog.w("WifiBasicNetIfaceManager", (new StringBuilder()).append("Received Intent: ").append(intent.getAction()).toString());
            s = intent.getAction();
            if(!s.equals("android.net.wifi.STATE_CHANGE"))
                break MISSING_BLOCK_LABEL_180;
            networkInfo = (NetworkInfo)intent.getParcelableExtra("networkInfo");
            class _cls4
            {

                static final int $SwitchMap$android$net$NetworkInfo$State[];

                static 
                {
                    $SwitchMap$android$net$NetworkInfo$State = new int[android.net.NetworkInfo.State.values().length];
                    NoSuchFieldError nosuchfielderror3;
                    try
                    {
                        $SwitchMap$android$net$NetworkInfo$State[android.net.NetworkInfo.State.CONNECTED.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$android$net$NetworkInfo$State[android.net.NetworkInfo.State.SUSPENDED.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$android$net$NetworkInfo$State[android.net.NetworkInfo.State.DISCONNECTING.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror2) { }
                    $SwitchMap$android$net$NetworkInfo$State[android.net.NetworkInfo.State.DISCONNECTED.ordinal()] = 4;
_L2:
                    return;
                    nosuchfielderror3;
                    if(true) goto _L2; else goto _L1
_L1:
                }
            }

            _cls4..SwitchMap.android.net.NetworkInfo.State[networkInfo.getState().ordinal()];
            JVM INSTR tableswitch 1 4: default 104
        //                       1 131
        //                       2 141
        //                       3 141
        //                       4 141;
               goto _L1 _L2 _L3 _L3 _L3
_L1:
            if(networkInfo.getState().equals(android.net.NetworkInfo.State.CONNECTED))
                onConnectionEstablished();
            else
            if(networkInfo.getState().equals(android.net.NetworkInfo.State.DISCONNECTING))
                onConnectionTerminated();
_L4:
            return;
_L2:
            onConnectionEstablished();
              goto _L1
_L3:
            onConnectionTerminated();
              goto _L1
            if(s.equals("android.net.wifi.supplicant.CONNECTION_CHANGE"))
            {
                networkInfo = (NetworkInfo)intent.getParcelableExtra("networkInfo");
                if(intent.getBooleanExtra("connected", false))
                    onConnectionAvailable();
                else
                    onConnectionUnAvailable();
            }
              goto _L4
        }

        final WifiBasicNetIfaceManager this$0;

            
            {
                this$0 = WifiBasicNetIfaceManager.this;
                super();
            }
    }
;
    protected android.net.wifi.WifiManager.WifiLock wifiLock;


}

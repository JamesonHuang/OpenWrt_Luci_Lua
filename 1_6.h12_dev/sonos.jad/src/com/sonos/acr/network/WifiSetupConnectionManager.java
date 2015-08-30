// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.network;

import android.content.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.*;
import android.os.Build;
import android.os.Handler;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.StringUtils;
import com.sonos.sclib.*;
import java.util.*;

// Referenced classes of package com.sonos.acr.network:
//            SonosNetworkManager

public class WifiSetupConnectionManager extends SCIWifiSetupDelegate
{
    private class WifiConnectivityReceiver extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            String s;
            s = intent.getAction();
            SLog.d("WifiSetupConnectionManager", (new StringBuilder()).append("Received Intent: ").append(s).toString());
            if(!"android.net.wifi.SCAN_RESULTS".equals(s)) goto _L2; else goto _L1
_L1:
            if(wifiState == WifiSetupState.SEARCHING_SONOS)
                onScanListReady();
_L4:
            return;
_L2:
            NetworkInfo networkinfo = (NetworkInfo)intent.getParcelableExtra("networkInfo");
            WifiInfo wifiinfo = (WifiInfo)intent.getParcelableExtra("wifiInfo");
            if(wifiinfo == null && wifi != null)
                wifiinfo = wifi.getConnectionInfo();
            SLog.d("WifiSetupConnectionManager", (new StringBuilder()).append("Wifi State Changed: \n\t Wifi Info: ").append(wifiinfo).append("\n\t Network Info: ").append(networkinfo).toString());
            class _cls2
            {

                static final int $SwitchMap$android$net$NetworkInfo$State[];

                static 
                {
                    $SwitchMap$android$net$NetworkInfo$State = new int[android.net.NetworkInfo.State.values().length];
                    NoSuchFieldError nosuchfielderror1;
                    try
                    {
                        $SwitchMap$android$net$NetworkInfo$State[android.net.NetworkInfo.State.CONNECTED.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    $SwitchMap$android$net$NetworkInfo$State[android.net.NetworkInfo.State.DISCONNECTED.ordinal()] = 2;
_L2:
                    return;
                    nosuchfielderror1;
                    if(true) goto _L2; else goto _L1
_L1:
                }
            }

            switch(_cls2..SwitchMap.android.net.NetworkInfo.State[networkinfo.getState().ordinal()])
            {
            case 1: // '\001'
                if(wifiinfo != null && wifiState == WifiSetupState.JOINING_SONOS)
                    connectedToSSID(wifiinfo.getSSID());
                break;

            case 2: // '\002'
                if(wifiState == WifiSetupState.CONNECTED_TO_SONOS)
                {
                    if(callback != null)
                    {
                        SLog.e("WifiSetupConnectionManager", "Looks like the Android watchdog disconnected us, report failure");
                        callback.setSSIDResultExtended("", SCIWifiConnectionResult.WIFI_RESULT_WATCHDOG);
                    }
                    if(connectionListener != null && connectionListener.onUnexpectedSonosDisconnect())
                    {
                        SLog.e("WifiSetupConnectionManager", "Prematurely ended Connection to sonos, report failure");
                        stopConnecting();
                        stopMonitoring();
                        setWifiState(WifiSetupState.IDLE);
                    }
                }
                break;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        final WifiSetupConnectionManager this$0;

        private WifiConnectivityReceiver()
        {
            this$0 = WifiSetupConnectionManager.this;
            super();
        }

    }

    static final class WifiSetupState extends Enum
    {

        public static WifiSetupState valueOf(String s)
        {
            return (WifiSetupState)Enum.valueOf(com/sonos/acr/network/WifiSetupConnectionManager$WifiSetupState, s);
        }

        public static WifiSetupState[] values()
        {
            return (WifiSetupState[])$VALUES.clone();
        }

        private static final WifiSetupState $VALUES[];
        public static final WifiSetupState CONNECTED_TO_SONOS;
        public static final WifiSetupState IDLE;
        public static final WifiSetupState JOINING_SONOS;
        public static final WifiSetupState SEARCHING_SONOS;

        static 
        {
            IDLE = new WifiSetupState("IDLE", 0);
            SEARCHING_SONOS = new WifiSetupState("SEARCHING_SONOS", 1);
            JOINING_SONOS = new WifiSetupState("JOINING_SONOS", 2);
            CONNECTED_TO_SONOS = new WifiSetupState("CONNECTED_TO_SONOS", 3);
            WifiSetupState awifisetupstate[] = new WifiSetupState[4];
            awifisetupstate[0] = IDLE;
            awifisetupstate[1] = SEARCHING_SONOS;
            awifisetupstate[2] = JOINING_SONOS;
            awifisetupstate[3] = CONNECTED_TO_SONOS;
            $VALUES = awifisetupstate;
        }

        private WifiSetupState(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface WifiConnectionListener
    {

        public abstract boolean onUnexpectedSonosDisconnect();
    }


    public WifiSetupConnectionManager(Context context)
    {
        timerOp = new Runnable() {

            public void run()
            {
                SLog.e("WifiSetupConnectionManager", "Timed out waiting for SONOS SSID, reporting failure");
                reportConnectFailure();
            }

            final WifiSetupConnectionManager this$0;

            
            {
                this$0 = WifiSetupConnectionManager.this;
                super();
            }
        }
;
        isHoneycomb = android.os.Build.VERSION.RELEASE.startsWith("3.");
        appCtx = context;
        supportWifiSetup = false;
        if(android.os.Build.VERSION.SDK_INT >= 14 || android.os.Build.VERSION.SDK_INT >= 9 && android.os.Build.VERSION.SDK_INT < 11)
            supportWifiSetup = true;
        else
            SLog.e("WifiSetupConnectionManager", "Not supporting WifiSetup, Old/Bad Version");
        if(Build.MANUFACTURER.equals("Amazon") && Build.MODEL.equals("Kindle Fire"))
        {
            SLog.e("WifiSetupConnectionManager", "Not supporting WifiSetup, Kindle Device has BAD Wifi");
            supportWifiSetup = false;
        }
        wifi = (WifiManager)appCtx.getSystemService("wifi");
        connectivityManager = (ConnectivityManager)appCtx.getSystemService("connectivity");
        if(wifi != null)
        {
            wifiLock = wifi.createWifiLock(1, "SonosWifiSetupWifiLock");
            wifiLock.setReferenceCounted(true);
        }
        setWifiState(WifiSetupState.IDLE);
        previousSSID = "";
        enabledNetworks = new ArrayList();
    }

    private void connectedToSSID(String s)
    {
        String s1 = getCleanSSIDString(s);
        SLog.d("WifiSetupConnectionManager", (new StringBuilder()).append("connectedToSSID: ").append(s1).append(", waitForSSID: ").append(waitForSSID).toString());
        if(waitForSSID != null && StringUtils.sonosStringCompare(s1, waitForSSID) == 0)
        {
            SLog.i("WifiSetupConnectionManager", "We are connected to the Sonos AP! Calling back to SCLib.");
            callback.setSSIDResultExtended(s1, SCIWifiConnectionResult.WIFI_RESULT_SUCCESS);
            stopConnecting();
            waitForSSID = null;
            restoreNetworks();
            setWifiState(WifiSetupState.CONNECTED_TO_SONOS);
        }
    }

    private String getCleanSSIDString(String s)
    {
        return s.replaceAll("(^\")|(\"$)", "");
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

    private boolean isAlreadyConnected(String s)
    {
        networkInfo = connectivityManager.getNetworkInfo(1);
        boolean flag;
        if(networkInfo != null && networkInfo.isConnected() && wifi.getWifiState() == 3)
            flag = getCleanSSIDString(wifi.getConnectionInfo().getSSID()).equals(getCleanSSIDString(s));
        else
            flag = false;
        return flag;
    }

    private void joinNetwork(String s, String s1)
    {
        boolean flag1;
        boolean flag = false;
        if(wifiLock != null)
            wifiLock.acquire();
        SLog.d("WifiSetupConnectionManager", (new StringBuilder()).append("Attempting to join the network ").append(s).toString());
        flag1 = true;
        String s2 = (new StringBuilder()).append("\"").append(s).append("\"").toString();
        WifiConfiguration wificonfiguration = new WifiConfiguration();
        wificonfiguration.status = 2;
        wificonfiguration.SSID = s2;
        wificonfiguration.hiddenSSID = false;
        wificonfiguration.priority = 1 + getHighestPriority();
        if(wificonfiguration.priority < 0)
            wificonfiguration.priority = 0x7fffffff;
        WifiInfo wifiinfo;
        int i;
        WifiInfo wifiinfo1;
        WifiManager wifimanager;
        if(StringUtils.isEmptyOrNull(passkey))
        {
            wificonfiguration.allowedKeyManagement.set(0);
            wificonfiguration.allowedPairwiseCiphers.set(0);
        } else
        {
            wificonfiguration.allowedKeyManagement.set(1);
            wificonfiguration.preSharedKey = (new StringBuilder()).append("\"").append(s1).append("\"").toString();
        }
        wifiinfo = wifi.getConnectionInfo();
        if(wifiinfo != null && previousSSID.length() == 0)
            previousSSID = wifiinfo.getSSID();
        wifi.disconnect();
        i = wifi.addNetwork(wificonfiguration);
        SLog.d("WifiSetupConnectionManager", (new StringBuilder()).append("addNetwork returned ").append(i).toString());
        if(i == -1)
            break MISSING_BLOCK_LABEL_416;
        wifimanager = wifi;
        if(!isHoneycomb)
            flag = true;
        if(!wifimanager.enableNetwork(i, flag))
            break MISSING_BLOCK_LABEL_416;
        SLog.d("WifiSetupConnectionManager", "Enabling network SUCCESS!");
        if(!wifi.saveConfiguration())
        {
            SLog.e("WifiSetupConnectionManager", "Unable to save config after add");
            flag1 = false;
        } else
        {
            SLog.d("WifiSetupConnectionManager", "Successfully added the Sonos AP network. Bouncing wifi.");
        }
_L1:
        wifi.reconnect();
        if(flag1)
        {
            wifiinfo1 = wifi.getConnectionInfo();
            if(wifiinfo1 != null && s.equals(wifiinfo1.getSSID()))
            {
                callback.setSSIDResult(s, true);
                stopConnecting();
            } else
            {
                waitForSSID = s;
                setWifiState(WifiSetupState.JOINING_SONOS);
            }
        } else
        {
            SLog.e("WifiSetupConnectionManager", "We couldn't add the SONOS Network to the network list, reporting a failure.");
            reportConnectFailure();
        }
        if(wifiLock != null)
            wifiLock.release();
        return;
        SLog.e("WifiSetupConnectionManager", "Enabling network failed");
        flag1 = false;
          goto _L1
    }

    private void onScanListReady()
    {
        if(callback != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        SLog.d("WifiSetupConnectionManager", "Scan list ready, looking for Sonos Setup AP");
        if(StringUtils.isEmptyOrNull(SSIDRegExStr))
            continue; /* Loop/switch isn't completed */
        String s;
        Iterator iterator;
        if(SSIDRegExStr.indexOf('.') != -1 || SSIDRegExStr.indexOf('*') != -1)
            s = (new StringBuilder()).append("(.*)").append(SSIDRegExStr).append("(.*)").toString();
        else
            s = SSIDRegExStr;
        iterator = wifi.getScanResults().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ScanResult scanresult = (ScanResult)iterator.next();
            if(!scanresult.SSID.matches(s))
                continue;
            SLog.d("WifiSetupConnectionManager", (new StringBuilder()).append("Found SSID matching Sonos Setup AP: ").append(scanresult.SSID).toString());
            if(!isHoneycomb)
                setEnabledNetworks();
            if(isAlreadyConnected(scanresult.SSID))
            {
                SLog.d("WifiSetupConnectionManager", "We are already connected to the Sonos AP!");
                setWifiState(WifiSetupState.CONNECTED_TO_SONOS);
                callback.setSSIDResult(scanresult.SSID, true);
                stopConnecting();
                restoreNetworks();
                continue; /* Loop/switch isn't completed */
            }
            joinNetwork(scanresult.SSID, passkey);
            break;
        } while(true);
        wifi.startScan();
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void restoreNetworks()
    {
        if(!isHoneycomb)
        {
            SLog.d("WifiSetupConnectionManager", "Restoring wifi network states");
            WifiConfiguration wificonfiguration;
            for(Iterator iterator = enabledNetworks.iterator(); iterator.hasNext(); wifi.enableNetwork(wificonfiguration.networkId, false))
                wificonfiguration = (WifiConfiguration)iterator.next();

            saveWifiConfiguration();
        }
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

    private void setEnabledNetworks()
    {
        SLog.d("WifiSetupConnectionManager", "Saving wifi network states");
        Iterator iterator = wifi.getConfiguredNetworks().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            WifiConfiguration wificonfiguration = (WifiConfiguration)iterator.next();
            if(wificonfiguration.status != 1 && !wificonfiguration.SSID.matches(SSIDRegExStr))
                enabledNetworks.add(wificonfiguration);
        } while(true);
    }

    private void setWifiState(WifiSetupState wifisetupstate)
    {
        SLog.i("WifiSetupConnectionManager", (new StringBuilder()).append("Transitioning From State: ").append(wifiState).append(" to state: ").append(wifisetupstate).toString());
        wifiState = wifisetupstate;
    }

    public void cancelSSIDJoin(String s)
    {
        SLog.d("WifiSetupConnectionManager", (new StringBuilder()).append("cancelSSIDJoin called SSID: ").append(s).toString());
    }

    public void clearListener()
    {
        SLog.d("WifiSetupConnectionManager", "clearListener ");
        stopConnecting();
        callback = null;
    }

    public String getCurrentBSSID()
    {
        String s = SonosApplication.getInstance().getNetworkStatusMonitor().getWiFiBSSID();
        String s1;
        if(s == null)
            s1 = "";
        else
            s1 = s.replaceAll(":", "");
        return s1;
    }

    public String getCurrentSSID()
    {
        String s = SonosApplication.getInstance().getNetworkStatusMonitor().getWiFiNetworkName();
        String s1;
        if(s == null)
            s1 = "";
        else
            s1 = s.replaceAll("^\"|\"$", "");
        return s1;
    }

    public void getWifiInfo(SCIPropertyBag scipropertybag)
    {
        SLog.d("WifiSetupConnectionManager", "SCLIB Requesting UI Info");
        scipropertybag.setStrProp(sclibConstants.SCI_WIFI_SSID, getCurrentSSID());
        scipropertybag.setStrProp(sclibConstants.SCI_WIFI_BSSID, getCurrentBSSID());
        int i = SonosApplication.getInstance().getNetworkStatusMonitor().getWifiNetworkSecurity();
        String s = sclibConstants.SCI_WIFI_OPEN;
        boolean flag;
        if(i == 3)
            flag = true;
        else
            flag = false;
        scipropertybag.setBoolProp(s, flag);
    }

    public boolean isWifiConnected()
    {
        return supportWifiSetup;
    }

    public void removeSSID(String s)
    {
        SLog.d("WifiSetupConnectionManager", (new StringBuilder()).append("removeSSID called SSID: ").append(s).toString());
        List list = wifi.getConfiguredNetworks();
        String s1 = (new StringBuilder()).append("\"").append(s).append("\"").toString();
        if(list != null)
        {
            SLog.d("WifiSetupConnectionManager", "Removing (or disabling) the Sonos AP network, enabling previous one");
            wifi.disconnect();
            Iterator iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                WifiConfiguration wificonfiguration = (WifiConfiguration)iterator.next();
                if(wificonfiguration.SSID != null)
                    if(s1.equals(wificonfiguration.SSID))
                        wifi.removeNetwork(wificonfiguration.networkId);
                    else
                    if(previousSSID.equals(wificonfiguration.SSID))
                        wifi.enableNetwork(wificonfiguration.networkId, true);
            } while(true);
            saveWifiConfiguration();
            previousSSID = "";
            wifi.reconnect();
            setWifiState(WifiSetupState.IDLE);
            stopMonitoring();
        }
    }

    protected void reportConnectFailure()
    {
        if(wifiState != WifiSetupState.IDLE && callback != null)
            callback.setSSIDResult("", false);
        stopConnecting();
        removeSSID(waitForSSID);
        waitForSSID = null;
    }

    public void setConnectionListener(WifiConnectionListener wificonnectionlistener)
    {
        connectionListener = wificonnectionlistener;
    }

    public void setSSID(String s, String s1, long l, SCIWifiListener sciwifilistener)
    {
        long l1 = l * 2000L;
        SLog.d("WifiSetupConnectionManager", (new StringBuilder()).append("Starting WifiSetupManager. Looking for SSIDs matching ").append(s).append(" and timeout is ").append(l1).toString());
        if(wifiState != WifiSetupState.IDLE && wifiState != WifiSetupState.CONNECTED_TO_SONOS)
        {
            sciwifilistener.setSSIDResult("", false);
            SLog.e("WifiSetupConnectionManager", "Wifistate is not idle, cancelling!");
        } else
        {
            startMonitoring();
            callback = sciwifilistener;
            SSIDRegExStr = s;
            passkey = s1;
            setWifiState(WifiSetupState.SEARCHING_SONOS);
            handler.removeCallbacks(timerOp);
            handler.postDelayed(timerOp, l1);
            wifi.startScan();
        }
    }

    protected void startMonitoring()
    {
        if(wifiConnectivityReceiver == null)
        {
            wifiConnectivityReceiver = new WifiConnectivityReceiver();
            SLog.i("WifiSetupConnectionManager", "Starting monitor");
            IntentFilter intentfilter = new IntentFilter("android.net.wifi.SCAN_RESULTS");
            intentfilter.addAction("android.net.wifi.STATE_CHANGE");
            appCtx.registerReceiver(wifiConnectivityReceiver, intentfilter);
        }
    }

    public void stopConnecting()
    {
        handler.removeCallbacks(timerOp);
        passkey = null;
        restoreNetworks();
    }

    protected void stopMonitoring()
    {
        if(wifiConnectivityReceiver != null)
        {
            SLog.i("WifiSetupConnectionManager", "Stopping monitor");
            appCtx.unregisterReceiver(wifiConnectivityReceiver);
            wifiConnectivityReceiver = null;
        }
    }

    protected static final String LOG_TAG = "WifiSetupConnectionManager";
    protected String SSIDRegExStr;
    public Context appCtx;
    private SCIWifiListener callback;
    public WifiConnectionListener connectionListener;
    public ConnectivityManager connectivityManager;
    private List enabledNetworks;
    private final Handler handler = new Handler();
    private boolean isHoneycomb;
    protected NetworkInfo networkInfo;
    protected String passkey;
    protected String previousSSID;
    protected boolean supportWifiSetup;
    protected Runnable timerOp;
    protected String waitForSSID;
    protected WifiManager wifi;
    private BroadcastReceiver wifiConnectivityReceiver;
    protected android.net.wifi.WifiManager.WifiLock wifiLock;
    protected WifiSetupState wifiState;




}

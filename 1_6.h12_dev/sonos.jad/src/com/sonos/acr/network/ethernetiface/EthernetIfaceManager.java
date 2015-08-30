// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.network.ethernetiface;

import android.content.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.sonos.acr.network.NetIfaceListener;
import com.sonos.acr.network.NetIfaceManager;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.util.*;
import com.sonos.sclib.SCIDealerMode;

public class EthernetIfaceManager extends NetIfaceManager
    implements com.sonos.acr.util.ShellCommand.CommandCallback
{

    public EthernetIfaceManager(Context context, NetIfaceListener netifacelistener)
    {
        super(context, netifacelistener);
        executor = new PeriodicExecutor(0x493e0L) {

            public void execute()
            {
                SCLibManager sclibmanager = LibraryUtils.getSCLibManager();
                if(sclibmanager != null)
                {
                    SCIDealerMode scidealermode = sclibmanager.getDealerMode();
                    NetworkInfo networkinfo = connectivityManager.getNetworkInfo(9);
                    if(scidealermode != null && scidealermode.isDealerLock() && networkinfo != null && !networkinfo.isAvailable())
                        (new ShellCommand()).runAndNotify("su -c \"netcfg eth0 down; netcfg eth0 up\"", EthernetIfaceManager.this);
                }
            }

            final EthernetIfaceManager this$0;

            
            {
                this$0 = EthernetIfaceManager.this;
                super(l);
            }
        }
;
    }

    public boolean isAvailable()
    {
        NetworkInfo networkinfo = connectivityManager.getNetworkInfo(9);
        boolean flag;
        if(networkinfo != null && (networkinfo.isConnected() || networkinfo.isAvailable()))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isConnected()
    {
        NetworkInfo networkinfo = connectivityManager.getNetworkInfo(9);
        boolean flag;
        if(networkinfo != null && networkinfo.isConnected())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isEnabled()
    {
        return true;
    }

    public void onComplete(ShellCommand shellcommand, com.sonos.acr.util.ShellCommand.CommandResult commandresult)
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Restart ethernet command finished: ").append(commandresult).toString());
    }

    protected void onStartMonitoring()
    {
        appCtx.registerReceiver(connectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        executor.start();
    }

    protected void onStopMonitoring()
    {
        appCtx.unregisterReceiver(connectivityReceiver);
        executor.stop();
    }

    public static final String LOG_TAG = com/sonos/acr/network/ethernetiface/EthernetIfaceManager.getSimpleName();
    public static final int TYPE_ETHERNET = 9;
    private final BroadcastReceiver connectivityReceiver = new BroadcastReceiver() {

        public void onReceive(Context context1, Intent intent)
        {
            SLog.i(EthernetIfaceManager.LOG_TAG, (new StringBuilder()).append("Received Intent: ").append(intent.getAction()).toString());
            NetworkInfo networkinfo;
            if("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()))
            {
                networkinfo = connectivityManager.getNetworkInfo(9);
                break MISSING_BLOCK_LABEL_53;
            }
            do
            {
                do
                    return;
                while(networkinfo == null || networkinfo.getType() != 9);
                class _cls3
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

                switch(_cls3..SwitchMap.android.net.NetworkInfo.State[networkinfo.getState().ordinal()])
                {
                case 1: // '\001'
                    onConnectionEstablished();
                    break;

                case 2: // '\002'
                case 3: // '\003'
                case 4: // '\004'
                    onConnectionTerminated();
                    break;
                }
            } while(true);
        }

        final EthernetIfaceManager this$0;

            
            {
                this$0 = EthernetIfaceManager.this;
                super();
            }
    }
;
    PeriodicExecutor executor;

}

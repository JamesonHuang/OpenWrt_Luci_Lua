// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.network;

import android.content.Context;
import android.net.ConnectivityManager;

// Referenced classes of package com.sonos.acr.network:
//            NetIfaceListener

public abstract class NetIfaceManager
{
    static final class ConnectionStatus extends Enum
    {

        public static ConnectionStatus valueOf(String s)
        {
            return (ConnectionStatus)Enum.valueOf(com/sonos/acr/network/NetIfaceManager$ConnectionStatus, s);
        }

        public static ConnectionStatus[] values()
        {
            return (ConnectionStatus[])$VALUES.clone();
        }

        private static final ConnectionStatus $VALUES[];
        public static final ConnectionStatus AVAILABLE;
        public static final ConnectionStatus DISABLED;
        public static final ConnectionStatus ENABLED;
        public static final ConnectionStatus ESTABLISHED;
        public static final ConnectionStatus FAILURE;
        public static final ConnectionStatus TERMINATED;
        public static final ConnectionStatus UNAVAILABLE;

        static 
        {
            AVAILABLE = new ConnectionStatus("AVAILABLE", 0);
            UNAVAILABLE = new ConnectionStatus("UNAVAILABLE", 1);
            ESTABLISHED = new ConnectionStatus("ESTABLISHED", 2);
            FAILURE = new ConnectionStatus("FAILURE", 3);
            TERMINATED = new ConnectionStatus("TERMINATED", 4);
            ENABLED = new ConnectionStatus("ENABLED", 5);
            DISABLED = new ConnectionStatus("DISABLED", 6);
            ConnectionStatus aconnectionstatus[] = new ConnectionStatus[7];
            aconnectionstatus[0] = AVAILABLE;
            aconnectionstatus[1] = UNAVAILABLE;
            aconnectionstatus[2] = ESTABLISHED;
            aconnectionstatus[3] = FAILURE;
            aconnectionstatus[4] = TERMINATED;
            aconnectionstatus[5] = ENABLED;
            aconnectionstatus[6] = DISABLED;
            $VALUES = aconnectionstatus;
        }

        private ConnectionStatus(String s, int i)
        {
            super(s, i);
        }
    }


    protected NetIfaceManager(Context context, NetIfaceListener netifacelistener)
    {
        currentlyMonitoring = false;
        appCtx = context.getApplicationContext();
        connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        ncListener = netifacelistener;
    }

    public abstract boolean isAvailable();

    public abstract boolean isConnected();

    public abstract boolean isEnabled();

    public void onConnectionAvailable()
    {
        ncListener.onConnectionStatusChange(this, ConnectionStatus.AVAILABLE);
    }

    public void onConnectionEstablished()
    {
        ncListener.onConnectionStatusChange(this, ConnectionStatus.ESTABLISHED);
    }

    public void onConnectionFailure()
    {
        ncListener.onConnectionStatusChange(this, ConnectionStatus.FAILURE);
    }

    public void onConnectionTerminated()
    {
        ncListener.onConnectionStatusChange(this, ConnectionStatus.TERMINATED);
    }

    public void onConnectionUnAvailable()
    {
        ncListener.onConnectionStatusChange(this, ConnectionStatus.UNAVAILABLE);
    }

    public void onConnectorDisabled()
    {
        ncListener.onConnectionStatusChange(this, ConnectionStatus.DISABLED);
    }

    public void onConnectorEnabled()
    {
        ncListener.onConnectionStatusChange(this, ConnectionStatus.ENABLED);
    }

    public void onFactoryReset()
    {
    }

    protected abstract void onStartMonitoring();

    protected abstract void onStopMonitoring();

    final void startMonitoring()
    {
        if(!currentlyMonitoring)
        {
            onStartMonitoring();
            currentlyMonitoring = true;
        }
    }

    final void stopMonitoring()
    {
        if(currentlyMonitoring)
        {
            onStopMonitoring();
            currentlyMonitoring = false;
        }
    }

    public Context appCtx;
    public ConnectivityManager connectivityManager;
    boolean currentlyMonitoring;
    public NetIfaceListener ncListener;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.application;

import android.os.Handler;
import com.sonos.acr.network.SonosNetworkManager;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.sclib.SonosListener;
import com.sonos.acr.sclib.sinks.HouseholdEventSink;
import com.sonos.acr.sclib.wrappers.Household;

// Referenced classes of package com.sonos.acr.application:
//            SonosApplication

public class NetworkSearchStateManager
    implements com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener
{
    private static final class NetworkSearchState extends Enum
    {

        public static NetworkSearchState valueOf(String s)
        {
            return (NetworkSearchState)Enum.valueOf(com/sonos/acr/application/NetworkSearchStateManager$NetworkSearchState, s);
        }

        public static NetworkSearchState[] values()
        {
            return (NetworkSearchState[])$VALUES.clone();
        }

        private static final NetworkSearchState $VALUES[];
        public static final NetworkSearchState SEARCHING;
        public static final NetworkSearchState STOPPED;
        public static final NetworkSearchState TIMER_FINISHED;

        static 
        {
            STOPPED = new NetworkSearchState("STOPPED", 0);
            SEARCHING = new NetworkSearchState("SEARCHING", 1);
            TIMER_FINISHED = new NetworkSearchState("TIMER_FINISHED", 2);
            NetworkSearchState anetworksearchstate[] = new NetworkSearchState[3];
            anetworksearchstate[0] = STOPPED;
            anetworksearchstate[1] = SEARCHING;
            anetworksearchstate[2] = TIMER_FINISHED;
            $VALUES = anetworksearchstate;
        }

        private NetworkSearchState(String s, int i)
        {
            super(s, i);
        }
    }


    public NetworkSearchStateManager()
    {
        hasHousehold = false;
    }

    public boolean hasHouseholdSearchTimedOut()
    {
        boolean flag;
        if(currentNetworkSearchState == NetworkSearchState.TIMER_FINISHED)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        boolean flag;
        if(SonosApplication.getInstance().getListener().getState() != com.sonos.acr.sclib.SonosListener.HouseholdState.ConnectedNoZones || SonosApplication.getInstance().getListener().getSubState() != com.sonos.acr.sclib.SonosListener.HouseholdSubState.NoDevices)
            flag = true;
        else
            flag = false;
        hasHousehold = flag;
        if(SonosApplication.getInstance().getSCLibManager() != null && hasHousehold)
            stopConnectionSearchTimer();
    }

    public void resumeNetworkingIfNeeded()
    {
        if(currentNetworkSearchState == NetworkSearchState.TIMER_FINISHED)
        {
            if(SonosApplication.getInstance().getSCLibManager() != null && SonosApplication.getInstance().getNetworkStatusMonitor().isWifiConnected())
                SonosApplication.getInstance().getSCLibManager().resumeNetworking();
        } else
        {
            stopConnectionSearchTimer();
        }
    }

    public void start()
    {
        currentNetworkSearchState = NetworkSearchState.STOPPED;
        HouseholdEventSink.getInstance().addListener(this);
    }

    public void startConnectionSearchTimer()
    {
        if(currentNetworkSearchState == NetworkSearchState.SEARCHING)
            stopConnectionSearchTimer();
        if(SonosApplication.getInstance().getSCLibManager() != null && !hasHousehold)
        {
            currentNetworkSearchState = NetworkSearchState.SEARCHING;
            connectionSearchRunnable = new Runnable() {

                public void run()
                {
                    if(!hasHousehold)
                    {
                        SonosApplication.getInstance().getSCLibManager().suspendNetworking();
                        currentNetworkSearchState = NetworkSearchState.TIMER_FINISHED;
                    } else
                    {
                        currentNetworkSearchState = NetworkSearchState.STOPPED;
                    }
                }

                final NetworkSearchStateManager this$0;

            
            {
                this$0 = NetworkSearchStateManager.this;
                super();
            }
            }
;
            SonosApplication.getInstance().getHandler().postDelayed(connectionSearchRunnable, 0x1d4c0L);
        }
    }

    public void stop()
    {
        HouseholdEventSink.getInstance().removeListener(this);
    }

    public void stopConnectionSearchTimer()
    {
        SonosApplication.getInstance().getHandler().removeCallbacks(connectionSearchRunnable);
        currentNetworkSearchState = NetworkSearchState.STOPPED;
    }

    public void suspendNetworkingIfNeeded()
    {
        if(!hasHousehold && SonosApplication.getInstance().getNetworkStatusMonitor().isWifiConnected())
            startConnectionSearchTimer();
    }

    Runnable connectionSearchRunnable;
    NetworkSearchState currentNetworkSearchState;
    boolean hasHousehold;
}

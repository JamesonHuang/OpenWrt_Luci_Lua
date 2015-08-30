// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.phone;

import android.content.*;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;
import java.util.*;

public class PhoneReceiver extends BroadcastReceiver
    implements com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener
{

    public PhoneReceiver()
    {
        pendingZoneGroupPauseOperations = new HashSet();
    }

    private void addPausedZoneGroup(String s)
    {
        if(s != null)
        {
            String s1 = prefs.getString("PhoneReceiverPausedGroups", "");
            if(!s1.contains(s))
            {
                SLog.d("PhoneReceiver", (new StringBuilder()).append("Adding Pause for: ").append(s).toString());
                String s2 = (new StringBuilder()).append(s1).append(DEL).append(s).toString();
                android.content.SharedPreferences.Editor editor = prefs.edit();
                editor.putString("PhoneReceiverPausedGroups", s2);
                editor.commit();
            }
        }
    }

    private void deletePausedGroups()
    {
        SLog.d("PhoneReceiver", "Deleting Pauseed Groups");
        android.content.SharedPreferences.Editor editor = prefs.edit();
        editor.remove("PhoneReceiverPausedGroups");
        editor.commit();
    }

    private void doPause(Household household)
    {
        SLog.d("PhoneReceiver", "Going to try to pause stuff");
        ArrayList arraylist;
        Iterator iterator;
        if("all".equals(prefs.getString("PhoneReceiverPauseType", DbgProp.get("callPauseType", "none"))))
            arraylist = household.getZoneGroups(com.sonos.sclib.SCIHousehold.ZGFilterOpt.FLT_ZG_COMPATIBLE);
        else
            arraylist = new ArrayList();
        iterator = arraylist.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ZoneGroup zonegroup = (ZoneGroup)iterator.next();
            final String zgid = zonegroup.getID();
            if(!pendingZoneGroupPauseOperations.contains(zgid))
            {
                SCINowPlayingTransport scinowplayingtransport = zonegroup.nowPlaying.getTransport();
                SLog.d("PhoneReceiver", (new StringBuilder()).append("NowPlaying State for pause: ").append(scinowplayingtransport.getPlaybackState()).append(" zoneGroup: ").append(zonegroup.getID()).toString());
                SCNPPlaybackState scnpplaybackstate = scinowplayingtransport.getPlaybackState();
                if(scnpplaybackstate == SCNPPlaybackState.SC_NP_PLAYBACK_PLAYING || scnpplaybackstate == SCNPPlaybackState.SC_NP_PLAYBACK_UNKNOWN)
                {
                    pendingZoneGroupPauseOperations.add(zgid);
                    SCIOp sciop = scinowplayingtransport.createPauseOp();
                    if(sciop != null)
                        sciop._start(new SCIOpCBSwigBase() {

                            public void _operationComplete(long l, int i)
                            {
                                SLog.d("PhoneReceiver", (new StringBuilder()).append("Pause Operation Complete: ").append(l).append(" ec: ").append(i).toString());
                                if(i == 0)
                                    addPausedZoneGroup(zgid);
                                pendingZoneGroupPauseOperations.remove(zgid);
                            }

                            final PhoneReceiver this$0;
                            final String val$zgid;

            
            {
                this$0 = PhoneReceiver.this;
                zgid = s;
                super();
            }
                        }
);
                }
            }
        } while(true);
    }

    private void doPlay(Household household)
    {
        String s = prefs.getString("PhoneReceiverPausedGroups", "");
        SLog.d("PhoneReceiver", (new StringBuilder()).append("Currently Paused Groups: ").append(s).toString());
        String as[] = s.split((new StringBuilder()).append("\\").append(DEL).toString());
        int i = as.length;
        int j = 0;
        while(j < i) 
        {
            String s1 = as[j];
            SLog.d("PhoneReceiver", (new StringBuilder()).append("Going to try to resume: ").append(s1).toString());
            ZoneGroup zonegroup = household.lookupZoneGroup(s1);
            if(zonegroup != null)
                zonegroup.nowPlaying.getTransport().createPlayOp()._start(new SCIOpCBSwigBase() {

                    public void _operationComplete(long l, int k)
                    {
                        SLog.d("PhoneReceiver", (new StringBuilder()).append("Play op returned: ").append(k).toString());
                    }

                    final PhoneReceiver this$0;

            
            {
                this$0 = PhoneReceiver.this;
                super();
            }
                }
);
            else
                SLog.d("PhoneReceiver", "Now playing is null");
            j++;
        }
        deletePausedGroups();
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent != com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged) goto _L2; else goto _L1
_L1:
        TelephonyManager telephonymanager;
        SLog.d("PhoneReceiver", (new StringBuilder()).append("Received Event!!! ").append(prefs.getString("PhoneReceiverPausedGroups", null)).toString());
        telephonymanager = (TelephonyManager)SonosApplication.getInstance().getSystemService("phone");
        if(!pendingZoneGroupPauseOperations.isEmpty()) goto _L2; else goto _L3
_L3:
        if(telephonymanager.getCallState() != 2 || prefs.getString("PhoneReceiverPausedGroups", null) != null) goto _L5; else goto _L4
_L4:
        doPause(household);
_L2:
        return;
_L5:
        if(telephonymanager.getCallState() == 0 && prefs.getString("PhoneReceiverPausedGroups", null) != null)
            doPlay(household);
        if(true) goto _L2; else goto _L6
_L6:
    }

    public void onReceive(Context context, Intent intent)
    {
        Bundle bundle = intent.getExtras();
        if(bundle == null || !prefs.getBoolean("PhoneReceiverPaused", true)) goto _L2; else goto _L1
_L1:
        String s;
        s = bundle.getString("state");
        SLog.w("PhoneReceiver", s);
        if(!s.equals(TelephonyManager.EXTRA_STATE_RINGING)) goto _L4; else goto _L3
_L3:
        String s1 = bundle.getString("incoming_number");
        SLog.w("PhoneReceiver", (new StringBuilder()).append("").append(s1).toString());
_L2:
        return;
_L4:
        Household household = LibraryUtils.getHousehold();
        if(household != null)
            if(s.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
                doPause(household);
            else
            if(s.equals(TelephonyManager.EXTRA_STATE_IDLE))
                doPlay(household);
        if(true) goto _L2; else goto _L5
_L5:
    }

    private static String DEL = "|";
    public static final String LOG_TAG = "PhoneReceiver";
    public static final String PAUSE_ALL_ZONES = "all";
    public static final String PAUSE_CURRENT_ZONE = "current";
    public static final String PAUSE_NEAREST_ZONE = "nearest";
    public static final String PAUSE_NONE = "none";
    public static final String PHONE_RECEIVER_PAUSED_GROUPS = "PhoneReceiverPausedGroups";
    HashSet pendingZoneGroupPauseOperations;
    final SharedPreferences prefs = LibraryUtils.getSharedPreferences();


}

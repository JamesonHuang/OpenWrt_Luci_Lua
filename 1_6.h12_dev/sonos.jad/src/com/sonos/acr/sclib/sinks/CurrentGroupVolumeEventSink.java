// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.sinks;

import com.sonos.acr.sclib.wrappers.GroupVolume;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;

// Referenced classes of package com.sonos.acr.sclib.sinks:
//            GroupVolumeEventSink

public class CurrentGroupVolumeEventSink extends GroupVolumeEventSink
    implements HouseholdEventSink.HouseholdListener
{

    public CurrentGroupVolumeEventSink()
    {
    }

    public static CurrentGroupVolumeEventSink getInstance()
    {
        if(instance == null)
            instance = new CurrentGroupVolumeEventSink();
        return instance;
    }

    protected GroupVolume createGroupVolume()
    {
        ZoneGroup zonegroup = LibraryUtils.getCurrentZoneGroup();
        GroupVolume groupvolume;
        if(zonegroup != null)
        {
            groupvolume = zonegroup.getGroupVolume();
        } else
        {
            SLog.e(LOG_TAG, "Error Subscribing: Current ZoneGroup was null");
            groupvolume = null;
        }
        return groupvolume;
    }

    private static CurrentGroupVolumeEventSink instance;
}

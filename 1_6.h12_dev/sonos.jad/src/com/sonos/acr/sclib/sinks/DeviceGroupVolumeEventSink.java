// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.sinks;

import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.*;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.sclib.sinks:
//            GroupVolumeEventSink

public class DeviceGroupVolumeEventSink extends GroupVolumeEventSink
    implements HouseholdEventSink.HouseholdListener
{

    private DeviceGroupVolumeEventSink(String s)
    {
        zoneGroupId = s;
    }

    public static DeviceGroupVolumeEventSink getInstance(String s)
    {
        DeviceGroupVolumeEventSink devicegroupvolumeeventsink = (DeviceGroupVolumeEventSink)groupVolumes.get(s);
        if(devicegroupvolumeeventsink == null)
        {
            devicegroupvolumeeventsink = new DeviceGroupVolumeEventSink(s);
            groupVolumes.put(s, devicegroupvolumeeventsink);
        }
        return devicegroupvolumeeventsink;
    }

    public static void unsubscribeAll(GroupVolumeEventSink.GroupVolumeListener groupvolumelistener)
    {
        for(Iterator iterator = groupVolumes.values().iterator(); iterator.hasNext(); ((DeviceGroupVolumeEventSink)iterator.next()).removeListener(groupvolumelistener));
    }

    protected GroupVolume createGroupVolume()
    {
        Household household = LibraryUtils.getHousehold();
        if(household == null) goto _L2; else goto _L1
_L1:
        ZoneGroup zonegroup = household.lookupZoneGroup(zoneGroupId);
        if(zonegroup == null) goto _L4; else goto _L3
_L3:
        GroupVolume groupvolume = zonegroup.getGroupVolume();
_L5:
        return groupvolume;
_L4:
        SLog.e(LOG_TAG, (new StringBuilder()).append("Error Subscribing: ZoneGroup: ").append(zoneGroupId).append(" des not exist.").toString());
_L6:
        groupvolume = null;
        if(true) goto _L5; else goto _L2
_L2:
        SLog.e(LOG_TAG, (new StringBuilder()).append("Error subscribing to ZoneGroup: ").append(zoneGroupId).append(" Household is null").toString());
          goto _L6
    }

    private static WeakMap groupVolumes = new WeakMap();
    private final String zoneGroupId;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.wrappers;

import com.sonos.acr.sclib.EnumeratorAdapter;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.sclib.wrappers:
//            DeviceVolume, ZoneGroup

public class GroupVolume
{

    public GroupVolume(SCIGroupVolume scigroupvolume)
    {
        sciGroupVolume = scigroupvolume;
    }

    public void beginContinuousVolumeAdjustments()
    {
        sciGroupVolume.prepareForGroupVolumeAdjustments();
    }

    public void endContinuousVolumeAdjustments()
    {
    }

    public DeviceVolume getDeviceVolume(String s)
    {
        SCIDeviceVolume scidevicevolume = sciGroupVolume.getDeviceVolume(s);
        DeviceVolume devicevolume;
        if(scidevicevolume == null)
            devicevolume = null;
        else
            devicevolume = new DeviceVolume(sciGroupVolume, scidevicevolume);
        return devicevolume;
    }

    public ArrayList getDeviceVolumes()
    {
        SCIEnumerator scienumerator = sciGroupVolume.getDeviceVolumes();
        deviceVolumes = new ArrayList(scienumerator.count());
        SCIDeviceVolume scidevicevolume;
        for(Iterator iterator = (new EnumeratorAdapter(scienumerator, sclibConstants.SCIDEVICEVOLUME_INTERFACE)).iterator(); iterator.hasNext(); deviceVolumes.add(new DeviceVolume(sciGroupVolume, scidevicevolume)))
            scidevicevolume = (SCIDeviceVolume)iterator.next();

        return deviceVolumes;
    }

    public String getGroupID()
    {
        return sciGroupVolume.getGroupID();
    }

    public DeviceVolume getGroupVolume()
    {
        return new DeviceVolume(sciGroupVolume, sciGroupVolume.getMasterDeviceVolume());
    }

    public SCIDeviceVolume getMasterDeviceVolume()
    {
        return sciGroupVolume.getMasterDeviceVolume();
    }

    public ZoneGroup getZoneGroup()
    {
        SCIZoneGroup scizonegroup = (SCIZoneGroup)LibraryUtils.cast(sciGroupVolume, com/sonos/sclib/SCIZoneGroup);
        ZoneGroup zonegroup;
        if(scizonegroup != null)
            zonegroup = new ZoneGroup(scizonegroup);
        else
            zonegroup = null;
        return zonegroup;
    }

    public boolean isValid()
    {
        return sciGroupVolume.isValid();
    }

    public boolean isVolumeAdjustable()
    {
        return sciGroupVolume.isGroupVolumeAdjustable();
    }

    public void subscribe(SCIEventSinkSwigBase scieventsinkswigbase)
    {
        sciGroupVolume.subscribe(scieventsinkswigbase);
    }

    public void unsubscribe(SCIEventSinkSwigBase scieventsinkswigbase)
    {
        sciGroupVolume.unsubscribe(scieventsinkswigbase);
    }

    public static final String GROUP_VOLUME_DEVICE_ID = "";
    ArrayList deviceVolumes;
    protected SCIGroupVolume sciGroupVolume;
}

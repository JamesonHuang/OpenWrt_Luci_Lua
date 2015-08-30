// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.wrappers;

import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.StringUtils;
import com.sonos.sclib.*;
import java.util.Comparator;

public class ZoneDevice
{
    public static class DeviceSortByTitleComparator
        implements Comparator
    {

        public int compare(ZoneDevice zonedevice, ZoneDevice zonedevice1)
        {
            return StringUtils.getSonosStringComparator().compare(zonedevice.getTitle(), zonedevice1.getTitle());
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((ZoneDevice)obj, (ZoneDevice)obj1);
        }

        public DeviceSortByTitleComparator()
        {
        }
    }


    public ZoneDevice(SCIDevice scidevice)
    {
        sciDevice = scidevice;
    }

    public SCIOpConnectionManagerGetProtocolInfo createGetProtocolInfoOp()
    {
        return sciDevice.createGetProtocolInfoOp();
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(this == obj)
            flag = true;
        else
        if(obj == null || getClass() != obj.getClass())
        {
            flag = false;
        } else
        {
            ZoneDevice zonedevice = (ZoneDevice)obj;
            flag = sciDevice.getID().equals(zonedevice.sciDevice.getID());
        }
        return flag;
    }

    public SCIDeviceAutoplay getDeviceAutoplay()
    {
        return (SCIDeviceAutoplay)LibraryUtils.cast(sciDevice, com/sonos/sclib/SCIDeviceAutoplay);
    }

    public com.sonos.sclib.SCIDevice.DeviceModel getDeviceModel()
    {
        return sciDevice.getDeviceModel();
    }

    public String getDeviceModelDisplayString()
    {
        return sciDevice.getDeviceModelDisplayString();
    }

    public SCIDeviceMusicEqualization getEQ()
    {
        return (SCIDeviceMusicEqualization)LibraryUtils.cast(sciDevice, com/sonos/sclib/SCIDeviceMusicEqualization);
    }

    public String getIconUri()
    {
        return sciDevice.getIconURI();
    }

    public String getId()
    {
        return sciDevice.getID();
    }

    public SCIDeviceLineOut getLineOut()
    {
        return (SCIDeviceLineOut)LibraryUtils.cast(sciDevice, com/sonos/sclib/SCIDeviceLineOut);
    }

    public SCIVersion getSoftwareVersion()
    {
        return sciDevice.getSoftwareVersion();
    }

    public String getSoftwareVersionStr()
    {
        return sciDevice.getSoftwareVersionStr();
    }

    public String getTitle()
    {
        return sciDevice.getTitle();
    }

    public int hashCode()
    {
        return sciDevice.getID().hashCode();
    }

    public boolean isConfigured()
    {
        return sciDevice.isConfigured();
    }

    public String toString()
    {
        return sciDevice.getTitle();
    }

    SCIDevice sciDevice;
}

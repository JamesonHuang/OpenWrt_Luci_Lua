// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.wrappers;

import android.content.Context;
import android.content.res.Resources;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SonosQueuePlaybackPolicy;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.sclib.wrappers:
//            NowPlaying, ZoneDevice, GroupVolume

public class ZoneGroup
{

    public ZoneGroup(SCINowPlaying scinowplaying)
    {
        sciZoneGroup = (SCIZoneGroup)LibraryUtils.cast(scinowplaying, com/sonos/sclib/SCIZoneGroup);
        nowPlaying = new NowPlaying(scinowplaying);
    }

    public ZoneGroup(SCIZoneGroup scizonegroup)
    {
        nowPlaying = new NowPlaying((SCINowPlaying)LibraryUtils.cast(scizonegroup, com/sonos/sclib/SCINowPlaying));
        sciZoneGroup = scizonegroup;
    }

    public SCIOpAttachPrivateQueue createAttachPrivateQueueOp(String s)
    {
        return sciZoneGroup.createAttachPrivateQueueOp(s);
    }

    public SCIOpNewPrivateQueue createNewPrivateQueueOp(String s, String s1, SonosQueuePlaybackPolicy sonosqueueplaybackpolicy)
    {
        SCIZoneGroup scizonegroup = sciZoneGroup;
        String s2;
        if(sonosqueueplaybackpolicy == null)
            s2 = "";
        else
            s2 = sonosqueueplaybackpolicy.toString();
        return scizonegroup.createNewPrivateQueueOp(s, s1, s2);
    }

    public String createSimpleZoneGroupTitle()
    {
        ArrayList arraylist = getDevices();
        int i = arraylist.size();
        String s;
        if(i > 0)
        {
            if(i == 1)
                s = ((ZoneDevice)arraylist.get(0)).getTitle();
            else
                s = (new StringBuilder()).append(((ZoneDevice)arraylist.get(0)).getTitle()).append(" + ").append(i - 1).toString();
        } else
        {
            s = "";
        }
        return s;
    }

    public String createZoneGroupTitle(int i, boolean flag)
    {
        return createZoneGroupTitle(i, flag, null);
    }

    public String createZoneGroupTitle(int i, boolean flag, String s)
    {
        ArrayList arraylist = getDevices();
        String s1;
        if(arraylist != null)
        {
            StringBuilder stringbuilder = new StringBuilder();
            int j = arraylist.size();
            int k = Math.min(j, i);
            int l = 0;
            ZoneDevice zonedevice = getDevice(s);
            if(zonedevice != null)
            {
                stringbuilder.append(zonedevice.getTitle());
                if(1 < k)
                    stringbuilder.append(" + ");
                l = 0 + 1;
            }
            for(; l < k; l++)
            {
                ZoneDevice zonedevice1 = (ZoneDevice)arraylist.get(l);
                if(zonedevice == zonedevice1)
                    continue;
                stringbuilder.append(zonedevice1.getTitle());
                if(l + 1 < k)
                    stringbuilder.append(" + ");
            }

            if(flag && j > i)
                stringbuilder.append(" + ").append(j - i);
            s1 = stringbuilder.toString();
        } else
        {
            s1 = null;
        }
        return s1;
    }

    public ZoneDevice getDevice(String s)
    {
        if(s == null) goto _L2; else goto _L1
_L1:
        SCIDevice scidevice = sciZoneGroup.getDevice(s);
        if(scidevice == null) goto _L2; else goto _L3
_L3:
        ZoneDevice zonedevice = new ZoneDevice(scidevice);
_L5:
        return zonedevice;
_L2:
        zonedevice = null;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public ArrayList getDevices()
    {
        if(zoneDevices == null)
        {
            SCIEnumerator scienumerator = sciZoneGroup.getDevices();
            zoneDevices = new ArrayList(scienumerator.count());
            scienumerator.reset();
            for(; scienumerator.moveNext(); zoneDevices.add(new ZoneDevice((SCIDevice)scienumerator.getCurrent(sclibConstants.SCIDEVICE_INTERFACE))));
        }
        return zoneDevices;
    }

    public GroupVolume getGroupVolume()
    {
        SCIGroupVolume scigroupvolume = (SCIGroupVolume)LibraryUtils.cast(sciZoneGroup, com/sonos/sclib/SCIGroupVolume);
        GroupVolume groupvolume;
        if(scigroupvolume != null)
            groupvolume = new GroupVolume(scigroupvolume);
        else
            groupvolume = null;
        return groupvolume;
    }

    public String getID()
    {
        return sciZoneGroup.getID();
    }

    public ZoneDevice getMasterDevice()
    {
        ZoneDevice zonedevice;
        if(sciZoneGroup.getMasterDevice() == null)
            zonedevice = null;
        else
            zonedevice = new ZoneDevice(sciZoneGroup.getMasterDevice());
        return zonedevice;
    }

    public SCIPlayQueue getPlayQueue()
    {
        return (SCIPlayQueue)LibraryUtils.cast(sciZoneGroup, com/sonos/sclib/SCIPlayQueue);
    }

    public String getSortName()
    {
        SCIDevice scidevice = sciZoneGroup.getMasterDevice();
        String s;
        if(scidevice != null)
            s = scidevice.getTitle();
        else
            s = "";
        return s;
    }

    public String getZoneMenuStatusText(Context context)
    {
        if(isCompatible()) goto _L2; else goto _L1
_L1:
        String s;
        Iterator iterator1 = getDevices().iterator();
        if(iterator1.hasNext())
        {
            ZoneDevice zonedevice1 = (ZoneDevice)iterator1.next();
            Resources resources1 = context.getResources();
            Object aobj1[] = new Object[1];
            aobj1[0] = zonedevice1.getTitle().toUpperCase();
            s = resources1.getString(0x7f0c012c, aobj1);
        } else
        {
            s = "";
        }
_L4:
        return s;
_L2:
        if(isUnbondedSUB())
        {
            s = context.getResources().getString(0x7f0c012b);
            continue; /* Loop/switch isn't completed */
        }
        if(isUnconfigured())
        {
            for(Iterator iterator = getDevices().iterator(); iterator.hasNext();)
            {
                ZoneDevice zonedevice = (ZoneDevice)iterator.next();
                if(!zonedevice.isConfigured())
                {
                    Resources resources = context.getResources();
                    Object aobj[] = new Object[1];
                    aobj[0] = zonedevice.getDeviceModelDisplayString().toUpperCase();
                    s = resources.getString(0x7f0c012a, aobj);
                    continue; /* Loop/switch isn't completed */
                }
            }

            s = "";
            continue; /* Loop/switch isn't completed */
        }
        s = nowPlaying.getSingleLineMetaData();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int hashCode()
    {
        return sciZoneGroup.getID().hashCode();
    }

    public boolean isCompatible()
    {
        return sciZoneGroup.isCompatible();
    }

    public boolean isCompatibleAndVisible()
    {
        return sciZoneGroup.isCompatibleAndVisible();
    }

    public boolean isCurrent()
    {
        ZoneGroup zonegroup = LibraryUtils.getCurrentZoneGroup();
        boolean flag;
        if(zonegroup != null && zonegroup.getID().equals(getID()))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isUnbondedSUB()
    {
        return sciZoneGroup.isUnbondedSUB();
    }

    public boolean isUnconfigured()
    {
        return sciZoneGroup.isUnconfigured();
    }

    public String toString()
    {
        return sciZoneGroup.getID();
    }

    public static final String PLUS_SPACE = " + ";
    public static final String ZONEGROUP_ID = "ZONEGROUP_ID";
    public final NowPlaying nowPlaying;
    final SCIZoneGroup sciZoneGroup;
    ArrayList zoneDevices;
}

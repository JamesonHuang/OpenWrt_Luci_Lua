// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import java.util.HashMap;
import java.util.Map;

class SonosHouseholdZoneGroupState
{

    protected SonosHouseholdZoneGroupState()
    {
        groups = new HashMap();
    }

    protected SonosHouseholdZoneGroupState(SonosHouseholdZoneGroupState sonoshouseholdzonegroupstate)
    {
        groups = new HashMap();
        groups = new HashMap(sonoshouseholdzonegroupstate.groups);
    }

    public void addGroup(String s, String s1)
    {
        groups.put(s, s1);
    }

    public String masterIdForGroup(String s)
    {
        String s1;
        if(!groups.containsKey(s))
            s1 = null;
        else
            s1 = (String)groups.get(s);
        return s1;
    }

    private Map groups;
}

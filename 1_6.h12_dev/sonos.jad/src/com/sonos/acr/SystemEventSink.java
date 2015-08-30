// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr;

import com.sonos.acr.sclib.EventSinkBase;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;

public abstract class SystemEventSink extends EventSinkBase
{

    public SystemEventSink()
    {
    }

    public void dispatchEvent(SCIObj sciobj, String s)
    {
        SCISystem scisystem = (SCISystem)LibraryUtils.cast(sciobj, com/sonos/sclib/SCISystem);
        if(scisystem != null && s.equals(sclibConstants.SCISYSTEM_ONOPRUNNINGCOUNTCHANGED_EVENT))
            onOpRunningCountChanged(scisystem);
    }

    public abstract void onOpRunningCountChanged(SCISystem scisystem);

    protected String[] supportedEvents()
    {
        String as[] = new String[1];
        as[0] = sclibConstants.SCISYSTEM_ONOPRUNNINGCOUNTCHANGED_EVENT;
        return as;
    }
}

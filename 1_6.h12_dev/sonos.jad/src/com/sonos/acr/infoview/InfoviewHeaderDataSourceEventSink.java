// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.infoview;

import com.sonos.acr.sclib.EventSinkBase;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;

public abstract class InfoviewHeaderDataSourceEventSink extends EventSinkBase
{

    public InfoviewHeaderDataSourceEventSink()
    {
    }

    public void dispatchEvent(SCIObj sciobj, String s)
    {
        SCIInfoViewHeaderDataSource sciinfoviewheaderdatasource = (SCIInfoViewHeaderDataSource)LibraryUtils.cast(sciobj, com/sonos/sclib/SCIInfoViewHeaderDataSource);
        if(s.equals(sclibConstants.SCIINFOVIEWHEADERDATASOURCE_ONCHANGED_EVENT))
            onInfoviewHeaderChanged(sciinfoviewheaderdatasource);
    }

    public abstract void onInfoviewHeaderChanged(SCIInfoViewHeaderDataSource sciinfoviewheaderdatasource);

    protected String[] supportedEvents()
    {
        String as[] = new String[1];
        as[0] = sclibConstants.SCIINFOVIEWHEADERDATASOURCE_ONCHANGED_EVENT;
        return as;
    }
}

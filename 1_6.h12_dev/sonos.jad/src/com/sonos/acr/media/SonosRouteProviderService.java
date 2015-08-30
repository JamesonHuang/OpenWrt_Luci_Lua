// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import android.support.v7.media.MediaRouteProvider;
import android.support.v7.media.MediaRouteProviderService;

// Referenced classes of package com.sonos.acr.media:
//            SonosRouteProvider

public class SonosRouteProviderService extends MediaRouteProviderService
{

    public SonosRouteProviderService()
    {
    }

    public MediaRouteProvider onCreateMediaRouteProvider()
    {
        return SonosRouteProvider.getInstance(this);
    }
}

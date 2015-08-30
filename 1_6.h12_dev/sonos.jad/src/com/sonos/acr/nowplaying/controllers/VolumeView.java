// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying.controllers;

import com.sonos.acr.sclib.sinks.VolumeViewListener;

// Referenced classes of package com.sonos.acr.nowplaying.controllers:
//            VolumeViewController

public interface VolumeView
    extends VolumeViewListener, com.sonos.acr.sclib.sinks.GroupVolumeEventSink.GroupVolumeListener
{

    public abstract void setVolumeViewController(VolumeViewController volumeviewcontroller);
}

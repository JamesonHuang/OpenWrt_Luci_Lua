// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.sinks;

import com.sonos.acr.sclib.wrappers.GroupVolume;

public interface VolumeViewListener
{

    public abstract void onUserVolumeEvent(GroupVolume groupvolume, String s, com.sonos.acr.nowplaying.controllers.VolumeViewController.UserEventType usereventtype);
}

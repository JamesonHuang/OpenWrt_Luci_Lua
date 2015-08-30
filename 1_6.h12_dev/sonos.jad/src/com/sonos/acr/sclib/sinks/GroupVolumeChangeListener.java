// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.sinks;


public interface GroupVolumeChangeListener
{

    public abstract void onAbsoluteVolumeSeekChange(String s, String s1, int i);

    public abstract void onMuteTouch(String s, String s1);

    public abstract void onRelativeVolumeSeekChange(String s, String s1, int i);

    public abstract void onStartTrackingTouch(String s, String s1);

    public abstract void onStopTrackingTouch(String s, String s1);

    public abstract void toggleMute(String s, String s1);
}

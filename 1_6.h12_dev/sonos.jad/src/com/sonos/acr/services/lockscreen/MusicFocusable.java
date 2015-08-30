// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.lockscreen;


public interface MusicFocusable
{

    public abstract void onGainedAudioFocus();

    public abstract void onLostAudioFocus(boolean flag);
}

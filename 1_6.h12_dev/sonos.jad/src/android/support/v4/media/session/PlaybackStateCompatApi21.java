// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.media.session.PlaybackState;

class PlaybackStateCompatApi21
{

    PlaybackStateCompatApi21()
    {
    }

    public static long getActions(Object obj)
    {
        return ((PlaybackState)obj).getActions();
    }

    public static long getBufferedPosition(Object obj)
    {
        return ((PlaybackState)obj).getBufferedPosition();
    }

    public static CharSequence getErrorMessage(Object obj)
    {
        return ((PlaybackState)obj).getErrorMessage();
    }

    public static long getLastPositionUpdateTime(Object obj)
    {
        return ((PlaybackState)obj).getLastPositionUpdateTime();
    }

    public static float getPlaybackSpeed(Object obj)
    {
        return ((PlaybackState)obj).getPlaybackSpeed();
    }

    public static long getPosition(Object obj)
    {
        return ((PlaybackState)obj).getPosition();
    }

    public static int getState(Object obj)
    {
        return ((PlaybackState)obj).getState();
    }

    public static Object newInstance(int i, long l, long l1, float f, long l2, 
            CharSequence charsequence, long l3)
    {
        android.media.session.PlaybackState.Builder builder = new android.media.session.PlaybackState.Builder();
        builder.setState(i, l, f, l3);
        builder.setBufferedPosition(l1);
        builder.setActions(l2);
        builder.setErrorMessage(charsequence);
        return builder.build();
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;

public class MediaSessionCompatApi8
{

    public MediaSessionCompatApi8()
    {
    }

    public static void registerMediaButtonEventReceiver(Context context, ComponentName componentname)
    {
        ((AudioManager)context.getSystemService("audio")).registerMediaButtonEventReceiver(componentname);
    }

    public static void unregisterMediaButtonEventReceiver(Context context, ComponentName componentname)
    {
        ((AudioManager)context.getSystemService("audio")).unregisterMediaButtonEventReceiver(componentname);
    }
}

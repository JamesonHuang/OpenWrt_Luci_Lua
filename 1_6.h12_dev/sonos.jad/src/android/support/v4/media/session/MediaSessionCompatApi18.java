// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.media.AudioManager;
import android.media.RemoteControlClient;
import android.os.SystemClock;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompatApi14

public class MediaSessionCompatApi18
{
    static class OnPlaybackPositionUpdateListener
        implements android.media.RemoteControlClient.OnPlaybackPositionUpdateListener
    {

        public void onPlaybackPositionUpdate(long l)
        {
            mCallback.onSeekTo(l);
        }

        protected final MediaSessionCompatApi14.Callback mCallback;

        public OnPlaybackPositionUpdateListener(MediaSessionCompatApi14.Callback callback)
        {
            mCallback = callback;
        }
    }


    public MediaSessionCompatApi18()
    {
    }

    public static Object createPlaybackPositionUpdateListener(MediaSessionCompatApi14.Callback callback)
    {
        return new OnPlaybackPositionUpdateListener(callback);
    }

    public static void registerMediaButtonEventReceiver(Context context, PendingIntent pendingintent)
    {
        ((AudioManager)context.getSystemService("audio")).registerMediaButtonEventReceiver(pendingintent);
    }

    public static void setOnPlaybackPositionUpdateListener(Object obj, Object obj1)
    {
        ((RemoteControlClient)obj).setPlaybackPositionUpdateListener((android.media.RemoteControlClient.OnPlaybackPositionUpdateListener)obj1);
    }

    public static void setState(Object obj, int i, long l, float f, long l1)
    {
        long l2 = SystemClock.elapsedRealtime();
        if(i == 3 && l > 0L)
        {
            long l3 = 0L;
            if(l1 > 0L)
            {
                l3 = l2 - l1;
                if(f > 0.0F && f != 1.0F)
                    l3 = (long)(f * (float)l3);
            }
            l += l3;
        }
        int j = MediaSessionCompatApi14.getRccStateFromState(i);
        ((RemoteControlClient)obj).setPlaybackState(j, l, f);
    }

    public static void unregisterMediaButtonEventReceiver(Context context, PendingIntent pendingintent)
    {
        ((AudioManager)context.getSystemService("audio")).unregisterMediaButtonEventReceiver(pendingintent);
    }
}

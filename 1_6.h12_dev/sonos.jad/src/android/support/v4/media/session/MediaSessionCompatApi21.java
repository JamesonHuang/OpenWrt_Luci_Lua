// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.*;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.*;
import java.util.*;

class MediaSessionCompatApi21
{
    static class QueueItem
    {

        public static Object createItem(Object obj, long l)
        {
            return new android.media.session.MediaSession.QueueItem((MediaDescription)obj, l);
        }

        public static Object getDescription(Object obj)
        {
            return ((android.media.session.MediaSession.QueueItem)obj).getDescription();
        }

        public static long getQueueId(Object obj)
        {
            return ((android.media.session.MediaSession.QueueItem)obj).getQueueId();
        }

        QueueItem()
        {
        }
    }

    static class CallbackProxy extends android.media.session.MediaSession.Callback
    {

        public void onCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
            mCallback.onCommand(s, bundle, resultreceiver);
        }

        public void onFastForward()
        {
            mCallback.onFastForward();
        }

        public boolean onMediaButtonEvent(Intent intent)
        {
            return mCallback.onMediaButtonEvent(intent);
        }

        public void onPause()
        {
            mCallback.onPause();
        }

        public void onPlay()
        {
            mCallback.onPlay();
        }

        public void onRewind()
        {
            mCallback.onRewind();
        }

        public void onSeekTo(long l)
        {
            mCallback.onSeekTo(l);
        }

        public void onSetRating(Rating rating)
        {
            mCallback.onSetRating(rating);
        }

        public void onSkipToNext()
        {
            mCallback.onSkipToNext();
        }

        public void onSkipToPrevious()
        {
            mCallback.onSkipToPrevious();
        }

        public void onStop()
        {
            mCallback.onStop();
        }

        protected final Callback mCallback;

        public CallbackProxy(Callback callback)
        {
            mCallback = callback;
        }
    }

    public static interface Callback
    {

        public abstract void onCommand(String s, Bundle bundle, ResultReceiver resultreceiver);

        public abstract void onCustomAction(String s, Bundle bundle);

        public abstract void onFastForward();

        public abstract boolean onMediaButtonEvent(Intent intent);

        public abstract void onPause();

        public abstract void onPlay();

        public abstract void onPlayFromMediaId(String s, Bundle bundle);

        public abstract void onPlayFromSearch(String s, Bundle bundle);

        public abstract void onRewind();

        public abstract void onSeekTo(long l);

        public abstract void onSetRating(Object obj);

        public abstract void onSkipToNext();

        public abstract void onSkipToPrevious();

        public abstract void onSkipToQueueItem(long l);

        public abstract void onStop();
    }


    MediaSessionCompatApi21()
    {
    }

    public static Object createCallback(Callback callback)
    {
        return new CallbackProxy(callback);
    }

    public static Object createSession(Context context, String s)
    {
        return new MediaSession(context, s);
    }

    public static Parcelable getSessionToken(Object obj)
    {
        return ((MediaSession)obj).getSessionToken();
    }

    public static boolean isActive(Object obj)
    {
        return ((MediaSession)obj).isActive();
    }

    public static void release(Object obj)
    {
        ((MediaSession)obj).release();
    }

    public static void sendSessionEvent(Object obj, String s, Bundle bundle)
    {
        ((MediaSession)obj).sendSessionEvent(s, bundle);
    }

    public static void setActive(Object obj, boolean flag)
    {
        ((MediaSession)obj).setActive(flag);
    }

    public static void setCallback(Object obj, Object obj1, Handler handler)
    {
        ((MediaSession)obj).setCallback((android.media.session.MediaSession.Callback)obj1, handler);
    }

    public static void setExtras(Object obj, Bundle bundle)
    {
        ((MediaSession)obj).setExtras(bundle);
    }

    public static void setFlags(Object obj, int i)
    {
        ((MediaSession)obj).setFlags(i);
    }

    public static void setMediaButtonReceiver(Object obj, PendingIntent pendingintent)
    {
        ((MediaSession)obj).setMediaButtonReceiver(pendingintent);
    }

    public static void setMetadata(Object obj, Object obj1)
    {
        ((MediaSession)obj).setMetadata((MediaMetadata)obj1);
    }

    public static void setPlaybackState(Object obj, Object obj1)
    {
        ((MediaSession)obj).setPlaybackState((PlaybackState)obj1);
    }

    public static void setPlaybackToLocal(Object obj, int i)
    {
        android.media.AudioAttributes.Builder builder = new android.media.AudioAttributes.Builder();
        builder.setLegacyStreamType(i);
        ((MediaSession)obj).setPlaybackToLocal(builder.build());
    }

    public static void setPlaybackToRemote(Object obj, Object obj1)
    {
        ((MediaSession)obj).setPlaybackToRemote((VolumeProvider)obj1);
    }

    public static void setQueue(Object obj, List list)
    {
        if(list == null)
        {
            ((MediaSession)obj).setQueue(null);
        } else
        {
            ArrayList arraylist = new ArrayList();
            for(Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add((android.media.session.MediaSession.QueueItem)iterator.next()));
            ((MediaSession)obj).setQueue(arraylist);
        }
    }

    public static void setQueueTitle(Object obj, CharSequence charsequence)
    {
        ((MediaSession)obj).setQueueTitle(charsequence);
    }

    public static void setSessionActivity(Object obj, PendingIntent pendingintent)
    {
        ((MediaSession)obj).setSessionActivity(pendingintent);
    }

    public static Object verifySession(Object obj)
    {
        if(obj instanceof MediaSession)
            return obj;
        else
            throw new IllegalArgumentException("mediaSession is not a valid MediaSession object");
    }

    public static Object verifyToken(Object obj)
    {
        if(obj instanceof android.media.session.MediaSession.Token)
            return obj;
        else
            throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
    }
}

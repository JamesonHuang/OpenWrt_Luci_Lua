// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.media.*;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.*;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.List;

class MediaControllerCompatApi21
{
    static class CallbackProxy extends android.media.session.MediaController.Callback
    {

        public void onMetadataChanged(MediaMetadata mediametadata)
        {
            mCallback.onMetadataChanged(mediametadata);
        }

        public void onPlaybackStateChanged(PlaybackState playbackstate)
        {
            mCallback.onPlaybackStateChanged(playbackstate);
        }

        public void onSessionDestroyed()
        {
            mCallback.onSessionDestroyed();
        }

        public void onSessionEvent(String s, Bundle bundle)
        {
            mCallback.onSessionEvent(s, bundle);
        }

        protected final Callback mCallback;

        public CallbackProxy(Callback callback)
        {
            mCallback = callback;
        }
    }

    public static interface Callback
    {

        public abstract void onMetadataChanged(Object obj);

        public abstract void onPlaybackStateChanged(Object obj);

        public abstract void onSessionDestroyed();

        public abstract void onSessionEvent(String s, Bundle bundle);
    }

    public static class PlaybackInfo
    {

        public static AudioAttributes getAudioAttributes(Object obj)
        {
            return ((android.media.session.MediaController.PlaybackInfo)obj).getAudioAttributes();
        }

        public static int getCurrentVolume(Object obj)
        {
            return ((android.media.session.MediaController.PlaybackInfo)obj).getCurrentVolume();
        }

        public static int getLegacyAudioStream(Object obj)
        {
            return toLegacyStreamType(getAudioAttributes(obj));
        }

        public static int getMaxVolume(Object obj)
        {
            return ((android.media.session.MediaController.PlaybackInfo)obj).getMaxVolume();
        }

        public static int getPlaybackType(Object obj)
        {
            return ((android.media.session.MediaController.PlaybackInfo)obj).getPlaybackType();
        }

        public static int getVolumeControl(Object obj)
        {
            return ((android.media.session.MediaController.PlaybackInfo)obj).getVolumeControl();
        }

        private static int toLegacyStreamType(AudioAttributes audioattributes)
        {
            byte byte0 = 3;
            if((1 & audioattributes.getFlags()) != 1) goto _L2; else goto _L1
_L1:
            byte0 = 7;
_L4:
            return byte0;
_L2:
            if((4 & audioattributes.getFlags()) != 4)
                break; /* Loop/switch isn't completed */
            byte0 = 6;
            if(true) goto _L4; else goto _L3
_L3:
            switch(audioattributes.getUsage())
            {
            case 2: // '\002'
                byte0 = 0;
                break;

            case 13: // '\r'
                byte0 = 1;
                break;

            case 3: // '\003'
                byte0 = 8;
                break;

            case 4: // '\004'
                byte0 = 4;
                break;

            case 6: // '\006'
                byte0 = 2;
                break;

            case 5: // '\005'
            case 7: // '\007'
            case 8: // '\b'
            case 9: // '\t'
            case 10: // '\n'
                byte0 = 5;
                break;
            }
            if(true) goto _L4; else goto _L5
_L5:
        }

        private static final int FLAG_SCO = 4;
        private static final int STREAM_BLUETOOTH_SCO = 6;
        private static final int STREAM_SYSTEM_ENFORCED = 7;

        public PlaybackInfo()
        {
        }
    }

    public static class TransportControls
    {

        public static void fastForward(Object obj)
        {
            ((android.media.session.MediaController.TransportControls)obj).fastForward();
        }

        public static void pause(Object obj)
        {
            ((android.media.session.MediaController.TransportControls)obj).pause();
        }

        public static void play(Object obj)
        {
            ((android.media.session.MediaController.TransportControls)obj).play();
        }

        public static void playFromMediaId(Object obj, String s, Bundle bundle)
        {
            ((android.media.session.MediaController.TransportControls)obj).playFromMediaId(s, bundle);
        }

        public static void playFromSearch(Object obj, String s, Bundle bundle)
        {
            ((android.media.session.MediaController.TransportControls)obj).playFromSearch(s, bundle);
        }

        public static void rewind(Object obj)
        {
            ((android.media.session.MediaController.TransportControls)obj).rewind();
        }

        public static void seekTo(Object obj, long l)
        {
            ((android.media.session.MediaController.TransportControls)obj).seekTo(l);
        }

        public static void sendCustomAction(Object obj, String s, Bundle bundle)
        {
            ((android.media.session.MediaController.TransportControls)obj).sendCustomAction(s, bundle);
        }

        public static void setRating(Object obj, Object obj1)
        {
            ((android.media.session.MediaController.TransportControls)obj).setRating((Rating)obj1);
        }

        public static void skipToNext(Object obj)
        {
            ((android.media.session.MediaController.TransportControls)obj).skipToNext();
        }

        public static void skipToPrevious(Object obj)
        {
            ((android.media.session.MediaController.TransportControls)obj).skipToPrevious();
        }

        public static void skipToQueueItem(Object obj, long l)
        {
            ((android.media.session.MediaController.TransportControls)obj).skipToQueueItem(l);
        }

        public static void stop(Object obj)
        {
            ((android.media.session.MediaController.TransportControls)obj).stop();
        }

        public TransportControls()
        {
        }
    }


    MediaControllerCompatApi21()
    {
    }

    public static void adjustVolume(Object obj, int i, int j)
    {
        ((MediaController)obj).adjustVolume(i, j);
    }

    public static Object createCallback(Callback callback)
    {
        return new CallbackProxy(callback);
    }

    public static boolean dispatchMediaButtonEvent(Object obj, KeyEvent keyevent)
    {
        return ((MediaController)obj).dispatchMediaButtonEvent(keyevent);
    }

    public static Object fromToken(Context context, Object obj)
    {
        return new MediaController(context, (android.media.session.MediaSession.Token)obj);
    }

    public static Bundle getExtras(Object obj)
    {
        return ((MediaController)obj).getExtras();
    }

    public static long getFlags(Object obj)
    {
        return ((MediaController)obj).getFlags();
    }

    public static Object getMetadata(Object obj)
    {
        return ((MediaController)obj).getMetadata();
    }

    public static String getPackageName(Object obj)
    {
        return ((MediaController)obj).getPackageName();
    }

    public static Object getPlaybackInfo(Object obj)
    {
        return ((MediaController)obj).getPlaybackInfo();
    }

    public static Object getPlaybackState(Object obj)
    {
        return ((MediaController)obj).getPlaybackState();
    }

    public static List getQueue(Object obj)
    {
        List list = ((MediaController)obj).getQueue();
        Object obj1;
        if(list == null)
            obj1 = null;
        else
            obj1 = new ArrayList(list);
        return ((List) (obj1));
    }

    public static CharSequence getQueueTitle(Object obj)
    {
        return ((MediaController)obj).getQueueTitle();
    }

    public static int getRatingType(Object obj)
    {
        return ((MediaController)obj).getRatingType();
    }

    public static PendingIntent getSessionActivity(Object obj)
    {
        return ((MediaController)obj).getSessionActivity();
    }

    public static Object getTransportControls(Object obj)
    {
        return ((MediaController)obj).getTransportControls();
    }

    public static void registerCallback(Object obj, Object obj1, Handler handler)
    {
        ((MediaController)obj).registerCallback((android.media.session.MediaController.Callback)obj1, handler);
    }

    public static void sendCommand(Object obj, String s, Bundle bundle, ResultReceiver resultreceiver)
    {
        ((MediaController)obj).sendCommand(s, bundle, resultreceiver);
    }

    public static void setVolumeTo(Object obj, int i, int j)
    {
        ((MediaController)obj).setVolumeTo(i, j);
    }

    public static void unregisterCallback(Object obj, Object obj1)
    {
        ((MediaController)obj).unregisterCallback((android.media.session.MediaController.Callback)obj1);
    }
}

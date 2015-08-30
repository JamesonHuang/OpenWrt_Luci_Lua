// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.os.*;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import java.util.*;

// Referenced classes of package android.support.v4.media.session:
//            MediaSessionCompat, PlaybackStateCompat, MediaControllerCompatApi21, IMediaSession, 
//            ParcelableVolumeInfo, IMediaControllerCallback

public final class MediaControllerCompat
{
    static class TransportControlsApi21 extends TransportControls
    {

        public void fastForward()
        {
            MediaControllerCompatApi21.TransportControls.fastForward(mControlsObj);
        }

        public void pause()
        {
            MediaControllerCompatApi21.TransportControls.pause(mControlsObj);
        }

        public void play()
        {
            MediaControllerCompatApi21.TransportControls.play(mControlsObj);
        }

        public void playFromMediaId(String s, Bundle bundle)
        {
            MediaControllerCompatApi21.TransportControls.playFromMediaId(mControlsObj, s, bundle);
        }

        public void playFromSearch(String s, Bundle bundle)
        {
            MediaControllerCompatApi21.TransportControls.playFromSearch(mControlsObj, s, bundle);
        }

        public void rewind()
        {
            MediaControllerCompatApi21.TransportControls.rewind(mControlsObj);
        }

        public void seekTo(long l)
        {
            MediaControllerCompatApi21.TransportControls.seekTo(mControlsObj, l);
        }

        public void sendCustomAction(PlaybackStateCompat.CustomAction customaction, Bundle bundle)
        {
            MediaControllerCompatApi21.TransportControls.sendCustomAction(mControlsObj, customaction.getAction(), bundle);
        }

        public void sendCustomAction(String s, Bundle bundle)
        {
            MediaControllerCompatApi21.TransportControls.sendCustomAction(mControlsObj, s, bundle);
        }

        public void setRating(RatingCompat ratingcompat)
        {
            Object obj = mControlsObj;
            Object obj1;
            if(ratingcompat != null)
                obj1 = ratingcompat.getRating();
            else
                obj1 = null;
            MediaControllerCompatApi21.TransportControls.setRating(obj, obj1);
        }

        public void skipToNext()
        {
            MediaControllerCompatApi21.TransportControls.skipToNext(mControlsObj);
        }

        public void skipToPrevious()
        {
            MediaControllerCompatApi21.TransportControls.skipToPrevious(mControlsObj);
        }

        public void skipToQueueItem(long l)
        {
            MediaControllerCompatApi21.TransportControls.skipToQueueItem(mControlsObj, l);
        }

        public void stop()
        {
            MediaControllerCompatApi21.TransportControls.stop(mControlsObj);
        }

        private final Object mControlsObj;

        public TransportControlsApi21(Object obj)
        {
            mControlsObj = obj;
        }
    }

    static class MediaControllerImplApi21
        implements MediaControllerImpl
    {

        public void adjustVolume(int i, int j)
        {
            MediaControllerCompatApi21.adjustVolume(mControllerObj, i, j);
        }

        public boolean dispatchMediaButtonEvent(KeyEvent keyevent)
        {
            return MediaControllerCompatApi21.dispatchMediaButtonEvent(mControllerObj, keyevent);
        }

        public Bundle getExtras()
        {
            return MediaControllerCompatApi21.getExtras(mControllerObj);
        }

        public long getFlags()
        {
            return MediaControllerCompatApi21.getFlags(mControllerObj);
        }

        public Object getMediaController()
        {
            return mControllerObj;
        }

        public MediaMetadataCompat getMetadata()
        {
            Object obj = MediaControllerCompatApi21.getMetadata(mControllerObj);
            MediaMetadataCompat mediametadatacompat;
            if(obj != null)
                mediametadatacompat = MediaMetadataCompat.fromMediaMetadata(obj);
            else
                mediametadatacompat = null;
            return mediametadatacompat;
        }

        public String getPackageName()
        {
            return MediaControllerCompatApi21.getPackageName(mControllerObj);
        }

        public PlaybackInfo getPlaybackInfo()
        {
            Object obj = MediaControllerCompatApi21.getPlaybackInfo(mControllerObj);
            PlaybackInfo playbackinfo;
            if(obj != null)
                playbackinfo = new PlaybackInfo(MediaControllerCompatApi21.PlaybackInfo.getPlaybackType(obj), MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(obj), MediaControllerCompatApi21.PlaybackInfo.getVolumeControl(obj), MediaControllerCompatApi21.PlaybackInfo.getMaxVolume(obj), MediaControllerCompatApi21.PlaybackInfo.getCurrentVolume(obj));
            else
                playbackinfo = null;
            return playbackinfo;
        }

        public PlaybackStateCompat getPlaybackState()
        {
            Object obj = MediaControllerCompatApi21.getPlaybackState(mControllerObj);
            PlaybackStateCompat playbackstatecompat;
            if(obj != null)
                playbackstatecompat = PlaybackStateCompat.fromPlaybackState(obj);
            else
                playbackstatecompat = null;
            return playbackstatecompat;
        }

        public List getQueue()
        {
            List list = MediaControllerCompatApi21.getQueue(mControllerObj);
            Object obj;
            if(list == null)
            {
                obj = null;
            } else
            {
                obj = new ArrayList();
                Iterator iterator = list.iterator();
                while(iterator.hasNext()) 
                    ((List) (obj)).add(MediaSessionCompat.QueueItem.obtain(iterator.next()));
            }
            return ((List) (obj));
        }

        public CharSequence getQueueTitle()
        {
            return MediaControllerCompatApi21.getQueueTitle(mControllerObj);
        }

        public int getRatingType()
        {
            return MediaControllerCompatApi21.getRatingType(mControllerObj);
        }

        public PendingIntent getSessionActivity()
        {
            return MediaControllerCompatApi21.getSessionActivity(mControllerObj);
        }

        public TransportControls getTransportControls()
        {
            Object obj = MediaControllerCompatApi21.getTransportControls(mControllerObj);
            TransportControlsApi21 transportcontrolsapi21;
            if(obj != null)
                transportcontrolsapi21 = new TransportControlsApi21(obj);
            else
                transportcontrolsapi21 = null;
            return transportcontrolsapi21;
        }

        public void registerCallback(Callback callback, Handler handler)
        {
            MediaControllerCompatApi21.registerCallback(mControllerObj, callback.mCallbackObj, handler);
        }

        public void sendCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
            MediaControllerCompatApi21.sendCommand(mControllerObj, s, bundle, resultreceiver);
        }

        public void setVolumeTo(int i, int j)
        {
            MediaControllerCompatApi21.setVolumeTo(mControllerObj, i, j);
        }

        public void unregisterCallback(Callback callback)
        {
            MediaControllerCompatApi21.unregisterCallback(mControllerObj, callback.mCallbackObj);
        }

        private final Object mControllerObj;

        public MediaControllerImplApi21(Context context, MediaSessionCompat.Token token)
            throws RemoteException
        {
            mControllerObj = MediaControllerCompatApi21.fromToken(context, token.getToken());
            if(mControllerObj == null)
                throw new RemoteException();
            else
                return;
        }

        public MediaControllerImplApi21(Context context, MediaSessionCompat mediasessioncompat)
        {
            mControllerObj = MediaControllerCompatApi21.fromToken(context, mediasessioncompat.getSessionToken().getToken());
        }
    }

    static class TransportControlsBase extends TransportControls
    {

        public void fastForward()
        {
            mBinder.fastForward();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in fastForward. ").append(remoteexception).toString());
              goto _L1
        }

        public void pause()
        {
            mBinder.pause();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in pause. ").append(remoteexception).toString());
              goto _L1
        }

        public void play()
        {
            mBinder.play();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in play. ").append(remoteexception).toString());
              goto _L1
        }

        public void playFromMediaId(String s, Bundle bundle)
        {
            mBinder.playFromMediaId(s, bundle);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in playFromMediaId. ").append(remoteexception).toString());
              goto _L1
        }

        public void playFromSearch(String s, Bundle bundle)
        {
            mBinder.playFromSearch(s, bundle);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in playFromSearch. ").append(remoteexception).toString());
              goto _L1
        }

        public void rewind()
        {
            mBinder.rewind();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in rewind. ").append(remoteexception).toString());
              goto _L1
        }

        public void seekTo(long l)
        {
            mBinder.seekTo(l);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in seekTo. ").append(remoteexception).toString());
              goto _L1
        }

        public void sendCustomAction(PlaybackStateCompat.CustomAction customaction, Bundle bundle)
        {
            sendCustomAction(customaction.getAction(), bundle);
        }

        public void sendCustomAction(String s, Bundle bundle)
        {
            mBinder.sendCustomAction(s, bundle);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in sendCustomAction. ").append(remoteexception).toString());
              goto _L1
        }

        public void setRating(RatingCompat ratingcompat)
        {
            mBinder.rate(ratingcompat);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in setRating. ").append(remoteexception).toString());
              goto _L1
        }

        public void skipToNext()
        {
            mBinder.next();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in skipToNext. ").append(remoteexception).toString());
              goto _L1
        }

        public void skipToPrevious()
        {
            mBinder.previous();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in skipToPrevious. ").append(remoteexception).toString());
              goto _L1
        }

        public void skipToQueueItem(long l)
        {
            mBinder.skipToQueueItem(l);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in skipToQueueItem. ").append(remoteexception).toString());
              goto _L1
        }

        public void stop()
        {
            mBinder.stop();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in stop. ").append(remoteexception).toString());
              goto _L1
        }

        private IMediaSession mBinder;

        public TransportControlsBase(IMediaSession imediasession)
        {
            mBinder = imediasession;
        }
    }

    static class MediaControllerImplBase
        implements MediaControllerImpl
    {

        public void adjustVolume(int i, int j)
        {
            mBinder.adjustVolume(i, j, null);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in adjustVolume. ").append(remoteexception).toString());
              goto _L1
        }

        public boolean dispatchMediaButtonEvent(KeyEvent keyevent)
        {
            if(keyevent == null)
                throw new IllegalArgumentException("event may not be null.");
            try
            {
                mBinder.sendMediaButton(keyevent);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in dispatchMediaButtonEvent. ").append(remoteexception).toString());
            }
            return false;
        }

        public Bundle getExtras()
        {
            Bundle bundle1 = mBinder.getExtras();
            Bundle bundle = bundle1;
_L2:
            return bundle;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getExtras. ").append(remoteexception).toString());
            bundle = null;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public long getFlags()
        {
            long l1 = mBinder.getFlags();
            long l = l1;
_L2:
            return l;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getFlags. ").append(remoteexception).toString());
            l = 0L;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public Object getMediaController()
        {
            return null;
        }

        public MediaMetadataCompat getMetadata()
        {
            MediaMetadataCompat mediametadatacompat1 = mBinder.getMetadata();
            MediaMetadataCompat mediametadatacompat = mediametadatacompat1;
_L2:
            return mediametadatacompat;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getMetadata. ").append(remoteexception).toString());
            mediametadatacompat = null;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public String getPackageName()
        {
            String s1 = mBinder.getPackageName();
            String s = s1;
_L2:
            return s;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getPackageName. ").append(remoteexception).toString());
            s = null;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public PlaybackInfo getPlaybackInfo()
        {
            PlaybackInfo playbackinfo;
            try
            {
                ParcelableVolumeInfo parcelablevolumeinfo = mBinder.getVolumeAttributes();
                playbackinfo = new PlaybackInfo(parcelablevolumeinfo.volumeType, parcelablevolumeinfo.audioStream, parcelablevolumeinfo.controlType, parcelablevolumeinfo.maxVolume, parcelablevolumeinfo.currentVolume);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getPlaybackInfo. ").append(remoteexception).toString());
                playbackinfo = null;
            }
            return playbackinfo;
        }

        public PlaybackStateCompat getPlaybackState()
        {
            PlaybackStateCompat playbackstatecompat1 = mBinder.getPlaybackState();
            PlaybackStateCompat playbackstatecompat = playbackstatecompat1;
_L2:
            return playbackstatecompat;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getPlaybackState. ").append(remoteexception).toString());
            playbackstatecompat = null;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public List getQueue()
        {
            List list1 = mBinder.getQueue();
            List list = list1;
_L2:
            return list;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getQueue. ").append(remoteexception).toString());
            list = null;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public CharSequence getQueueTitle()
        {
            CharSequence charsequence1 = mBinder.getQueueTitle();
            CharSequence charsequence = charsequence1;
_L2:
            return charsequence;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getQueueTitle. ").append(remoteexception).toString());
            charsequence = null;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public int getRatingType()
        {
            int j = mBinder.getRatingType();
            int i = j;
_L2:
            return i;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getRatingType. ").append(remoteexception).toString());
            i = 0;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public PendingIntent getSessionActivity()
        {
            PendingIntent pendingintent1 = mBinder.getLaunchPendingIntent();
            PendingIntent pendingintent = pendingintent1;
_L2:
            return pendingintent;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in getSessionActivity. ").append(remoteexception).toString());
            pendingintent = null;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public TransportControls getTransportControls()
        {
            if(mTransportControls == null)
                mTransportControls = new TransportControlsBase(mBinder);
            return mTransportControls;
        }

        public void registerCallback(Callback callback, Handler handler)
        {
            if(callback == null)
                throw new IllegalArgumentException("callback may not be null.");
            mBinder.asBinder().linkToDeath(callback, 0);
            mBinder.registerCallbackListener((IMediaControllerCallback)callback.mCallbackObj);
            callback.setHandler(handler);
            callback.mRegistered = true;
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in registerCallback. ").append(remoteexception).toString());
            callback.onSessionDestroyed();
              goto _L1
        }

        public void sendCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
            mBinder.sendCommand(s, bundle, new MediaSessionCompat.ResultReceiverWrapper(resultreceiver));
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in sendCommand. ").append(remoteexception).toString());
              goto _L1
        }

        public void setVolumeTo(int i, int j)
        {
            mBinder.setVolumeTo(i, j, null);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in setVolumeTo. ").append(remoteexception).toString());
              goto _L1
        }

        public void unregisterCallback(Callback callback)
        {
            if(callback == null)
                throw new IllegalArgumentException("callback may not be null.");
            mBinder.unregisterCallbackListener((IMediaControllerCallback)callback.mCallbackObj);
            mBinder.asBinder().unlinkToDeath(callback, 0);
            callback.mRegistered = false;
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.e("MediaControllerCompat", (new StringBuilder()).append("Dead object in unregisterCallback. ").append(remoteexception).toString());
              goto _L1
        }

        private IMediaSession mBinder;
        private MediaSessionCompat.Token mToken;
        private TransportControls mTransportControls;

        public MediaControllerImplBase(MediaSessionCompat.Token token)
        {
            mToken = token;
            mBinder = IMediaSession.Stub.asInterface((IBinder)token.getToken());
        }
    }

    static interface MediaControllerImpl
    {

        public abstract void adjustVolume(int i, int j);

        public abstract boolean dispatchMediaButtonEvent(KeyEvent keyevent);

        public abstract Bundle getExtras();

        public abstract long getFlags();

        public abstract Object getMediaController();

        public abstract MediaMetadataCompat getMetadata();

        public abstract String getPackageName();

        public abstract PlaybackInfo getPlaybackInfo();

        public abstract PlaybackStateCompat getPlaybackState();

        public abstract List getQueue();

        public abstract CharSequence getQueueTitle();

        public abstract int getRatingType();

        public abstract PendingIntent getSessionActivity();

        public abstract TransportControls getTransportControls();

        public abstract void registerCallback(Callback callback, Handler handler);

        public abstract void sendCommand(String s, Bundle bundle, ResultReceiver resultreceiver);

        public abstract void setVolumeTo(int i, int j);

        public abstract void unregisterCallback(Callback callback);
    }

    public static final class PlaybackInfo
    {

        public int getAudioStream()
        {
            return mAudioStream;
        }

        public int getCurrentVolume()
        {
            return mCurrentVolume;
        }

        public int getMaxVolume()
        {
            return mMaxVolume;
        }

        public int getPlaybackType()
        {
            return mPlaybackType;
        }

        public int getVolumeControl()
        {
            return mVolumeControl;
        }

        public static final int PLAYBACK_TYPE_LOCAL = 1;
        public static final int PLAYBACK_TYPE_REMOTE = 2;
        private final int mAudioStream;
        private final int mCurrentVolume;
        private final int mMaxVolume;
        private final int mPlaybackType;
        private final int mVolumeControl;

        PlaybackInfo(int i, int j, int k, int l, int i1)
        {
            mPlaybackType = i;
            mAudioStream = j;
            mVolumeControl = k;
            mMaxVolume = l;
            mCurrentVolume = i1;
        }
    }

    public static abstract class TransportControls
    {

        public abstract void fastForward();

        public abstract void pause();

        public abstract void play();

        public abstract void playFromMediaId(String s, Bundle bundle);

        public abstract void playFromSearch(String s, Bundle bundle);

        public abstract void rewind();

        public abstract void seekTo(long l);

        public abstract void sendCustomAction(PlaybackStateCompat.CustomAction customaction, Bundle bundle);

        public abstract void sendCustomAction(String s, Bundle bundle);

        public abstract void setRating(RatingCompat ratingcompat);

        public abstract void skipToNext();

        public abstract void skipToPrevious();

        public abstract void skipToQueueItem(long l);

        public abstract void stop();

        TransportControls()
        {
        }
    }

    public static abstract class Callback
        implements android.os.IBinder.DeathRecipient
    {
        private class MessageHandler extends Handler
        {

            public void handleMessage(Message message)
            {
                if(mRegistered) goto _L2; else goto _L1
_L1:
                return;
_L2:
                switch(message.what)
                {
                case 1: // '\001'
                    onSessionEvent((String)message.obj, message.getData());
                    break;

                case 2: // '\002'
                    onPlaybackStateChanged((PlaybackStateCompat)message.obj);
                    break;

                case 3: // '\003'
                    onMetadataChanged((MediaMetadataCompat)message.obj);
                    break;

                case 5: // '\005'
                    onQueueChanged((List)message.obj);
                    break;

                case 6: // '\006'
                    onQueueTitleChanged((CharSequence)message.obj);
                    break;

                case 7: // '\007'
                    onExtrasChanged((Bundle)message.obj);
                    break;

                case 4: // '\004'
                    onAudioInfoChanged((PlaybackInfo)message.obj);
                    break;

                case 8: // '\b'
                    onSessionDestroyed();
                    break;
                }
                if(true) goto _L1; else goto _L3
_L3:
            }

            public void post(int i, Object obj, Bundle bundle)
            {
                obtainMessage(i, obj).sendToTarget();
            }

            private static final int MSG_DESTROYED = 8;
            private static final int MSG_EVENT = 1;
            private static final int MSG_UPDATE_EXTRAS = 7;
            private static final int MSG_UPDATE_METADATA = 3;
            private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
            private static final int MSG_UPDATE_QUEUE = 5;
            private static final int MSG_UPDATE_QUEUE_TITLE = 6;
            private static final int MSG_UPDATE_VOLUME = 4;
            final Callback this$0;

            public MessageHandler(Looper looper)
            {
                this$0 = Callback.this;
                super(looper);
            }
        }

        private class StubCompat extends IMediaControllerCallback.Stub
        {

            public void onEvent(String s, Bundle bundle)
                throws RemoteException
            {
                mHandler.post(1, s, bundle);
            }

            public void onExtrasChanged(Bundle bundle)
                throws RemoteException
            {
                mHandler.post(7, bundle, null);
            }

            public void onMetadataChanged(MediaMetadataCompat mediametadatacompat)
                throws RemoteException
            {
                mHandler.post(3, mediametadatacompat, null);
            }

            public void onPlaybackStateChanged(PlaybackStateCompat playbackstatecompat)
                throws RemoteException
            {
                mHandler.post(2, playbackstatecompat, null);
            }

            public void onQueueChanged(List list)
                throws RemoteException
            {
                mHandler.post(5, list, null);
            }

            public void onQueueTitleChanged(CharSequence charsequence)
                throws RemoteException
            {
                mHandler.post(6, charsequence, null);
            }

            public void onSessionDestroyed()
                throws RemoteException
            {
                mHandler.post(8, null, null);
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo parcelablevolumeinfo)
                throws RemoteException
            {
                PlaybackInfo playbackinfo = null;
                if(parcelablevolumeinfo != null)
                    playbackinfo = new PlaybackInfo(parcelablevolumeinfo.volumeType, parcelablevolumeinfo.audioStream, parcelablevolumeinfo.controlType, parcelablevolumeinfo.maxVolume, parcelablevolumeinfo.currentVolume);
                mHandler.post(4, playbackinfo, null);
            }

            final Callback this$0;

            private StubCompat()
            {
                this$0 = Callback.this;
                super();
            }

        }

        private class StubApi21
            implements MediaControllerCompatApi21.Callback
        {

            public void onMetadataChanged(Object obj)
            {
                Callback.this.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(obj));
            }

            public void onPlaybackStateChanged(Object obj)
            {
                Callback.this.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(obj));
            }

            public void onSessionDestroyed()
            {
                Callback.this.onSessionDestroyed();
            }

            public void onSessionEvent(String s, Bundle bundle)
            {
                Callback.this.onSessionEvent(s, bundle);
            }

            final Callback this$0;

            private StubApi21()
            {
                this$0 = Callback.this;
                super();
            }

        }


        private void setHandler(Handler handler)
        {
            mHandler = new MessageHandler(handler.getLooper());
        }

        public void binderDied()
        {
            onSessionDestroyed();
        }

        public void onAudioInfoChanged(PlaybackInfo playbackinfo)
        {
        }

        public void onExtrasChanged(Bundle bundle)
        {
        }

        public void onMetadataChanged(MediaMetadataCompat mediametadatacompat)
        {
        }

        public void onPlaybackStateChanged(PlaybackStateCompat playbackstatecompat)
        {
        }

        public void onQueueChanged(List list)
        {
        }

        public void onQueueTitleChanged(CharSequence charsequence)
        {
        }

        public void onSessionDestroyed()
        {
        }

        public void onSessionEvent(String s, Bundle bundle)
        {
        }

        private final Object mCallbackObj;
        private MessageHandler mHandler;
        private boolean mRegistered;




/*
        static boolean access$302(Callback callback, boolean flag)
        {
            callback.mRegistered = flag;
            return flag;
        }

*/



        public Callback()
        {
            mRegistered = false;
            if(android.os.Build.VERSION.SDK_INT >= 21)
                mCallbackObj = MediaControllerCompatApi21.createCallback(new StubApi21());
            else
                mCallbackObj = new StubCompat();
        }
    }


    public MediaControllerCompat(Context context, MediaSessionCompat.Token token)
        throws RemoteException
    {
        if(token == null)
            throw new IllegalArgumentException("sessionToken must not be null");
        mToken = token;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            mImpl = new MediaControllerImplApi21(context, token);
        else
            mImpl = new MediaControllerImplBase(mToken);
    }

    public MediaControllerCompat(Context context, MediaSessionCompat mediasessioncompat)
    {
        if(mediasessioncompat == null)
            throw new IllegalArgumentException("session must not be null");
        mToken = mediasessioncompat.getSessionToken();
        if(android.os.Build.VERSION.SDK_INT >= 21)
            mImpl = new MediaControllerImplApi21(context, mediasessioncompat);
        else
            mImpl = new MediaControllerImplBase(mToken);
    }

    public void adjustVolume(int i, int j)
    {
        mImpl.adjustVolume(i, j);
    }

    public boolean dispatchMediaButtonEvent(KeyEvent keyevent)
    {
        if(keyevent == null)
            throw new IllegalArgumentException("KeyEvent may not be null");
        else
            return mImpl.dispatchMediaButtonEvent(keyevent);
    }

    public Bundle getExtras()
    {
        return mImpl.getExtras();
    }

    public long getFlags()
    {
        return mImpl.getFlags();
    }

    public Object getMediaController()
    {
        return mImpl.getMediaController();
    }

    public MediaMetadataCompat getMetadata()
    {
        return mImpl.getMetadata();
    }

    public String getPackageName()
    {
        return mImpl.getPackageName();
    }

    public PlaybackInfo getPlaybackInfo()
    {
        return mImpl.getPlaybackInfo();
    }

    public PlaybackStateCompat getPlaybackState()
    {
        return mImpl.getPlaybackState();
    }

    public List getQueue()
    {
        return mImpl.getQueue();
    }

    public CharSequence getQueueTitle()
    {
        return mImpl.getQueueTitle();
    }

    public int getRatingType()
    {
        return mImpl.getRatingType();
    }

    public PendingIntent getSessionActivity()
    {
        return mImpl.getSessionActivity();
    }

    public MediaSessionCompat.Token getSessionToken()
    {
        return mToken;
    }

    public TransportControls getTransportControls()
    {
        return mImpl.getTransportControls();
    }

    public void registerCallback(Callback callback)
    {
        registerCallback(callback, null);
    }

    public void registerCallback(Callback callback, Handler handler)
    {
        if(callback == null)
            throw new IllegalArgumentException("callback cannot be null");
        if(handler == null)
            handler = new Handler();
        mImpl.registerCallback(callback, handler);
    }

    public void sendCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
    {
        if(TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("command cannot be null or empty");
        } else
        {
            mImpl.sendCommand(s, bundle, resultreceiver);
            return;
        }
    }

    public void setVolumeTo(int i, int j)
    {
        mImpl.setVolumeTo(i, j);
    }

    public void unregisterCallback(Callback callback)
    {
        if(callback == null)
        {
            throw new IllegalArgumentException("callback cannot be null");
        } else
        {
            mImpl.unregisterCallback(callback);
            return;
        }
    }

    private static final String TAG = "MediaControllerCompat";
    private final MediaControllerImpl mImpl;
    private final MediaSessionCompat.Token mToken;
}

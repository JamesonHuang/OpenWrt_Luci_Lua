// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.support.v4.view.KeyEventCompat;
import android.view.*;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.media:
//            TransportController, TransportMediatorJellybeanMR2, TransportStateListener, TransportPerformer, 
//            TransportMediatorCallback

public class TransportMediator extends TransportController
{

    public TransportMediator(Activity activity, TransportPerformer transportperformer)
    {
        this(activity, null, transportperformer);
    }

    private TransportMediator(Activity activity, View view, TransportPerformer transportperformer)
    {
        mListeners = new ArrayList();
        mTransportKeyCallback = new TransportMediatorCallback() {

            public long getPlaybackPosition()
            {
                return mCallbacks.onGetCurrentPosition();
            }

            public void handleAudioFocusChange(int i)
            {
                mCallbacks.onAudioFocusChange(i);
            }

            public void handleKey(KeyEvent keyevent)
            {
                keyevent.dispatch(mKeyEventCallback);
            }

            public void playbackPositionUpdate(long l)
            {
                mCallbacks.onSeekTo(l);
            }

            final TransportMediator this$0;

            
            {
                this$0 = TransportMediator.this;
                super();
            }
        }
;
        mKeyEventCallback = new android.view.KeyEvent.Callback() {

            public boolean onKeyDown(int i, KeyEvent keyevent)
            {
                boolean flag;
                if(TransportMediator.isMediaKey(i))
                    flag = mCallbacks.onMediaButtonDown(i, keyevent);
                else
                    flag = false;
                return flag;
            }

            public boolean onKeyLongPress(int i, KeyEvent keyevent)
            {
                return false;
            }

            public boolean onKeyMultiple(int i, int j, KeyEvent keyevent)
            {
                return false;
            }

            public boolean onKeyUp(int i, KeyEvent keyevent)
            {
                boolean flag;
                if(TransportMediator.isMediaKey(i))
                    flag = mCallbacks.onMediaButtonUp(i, keyevent);
                else
                    flag = false;
                return flag;
            }

            final TransportMediator this$0;

            
            {
                this$0 = TransportMediator.this;
                super();
            }
        }
;
        Object obj;
        if(activity != null)
            obj = activity;
        else
            obj = view.getContext();
        mContext = ((Context) (obj));
        mCallbacks = transportperformer;
        mAudioManager = (AudioManager)mContext.getSystemService("audio");
        if(activity != null)
            view = activity.getWindow().getDecorView();
        mView = view;
        mDispatcherState = KeyEventCompat.getKeyDispatcherState(mView);
        if(android.os.Build.VERSION.SDK_INT >= 18)
            mController = new TransportMediatorJellybeanMR2(mContext, mAudioManager, mView, mTransportKeyCallback);
        else
            mController = null;
    }

    public TransportMediator(View view, TransportPerformer transportperformer)
    {
        this(null, view, transportperformer);
    }

    private TransportStateListener[] getListeners()
    {
        TransportStateListener atransportstatelistener[];
        if(mListeners.size() <= 0)
        {
            atransportstatelistener = null;
        } else
        {
            atransportstatelistener = new TransportStateListener[mListeners.size()];
            mListeners.toArray(atransportstatelistener);
        }
        return atransportstatelistener;
    }

    static boolean isMediaKey(int i)
    {
        i;
        JVM INSTR lookupswitch 11: default 100
    //                   79: 104
    //                   85: 104
    //                   86: 104
    //                   87: 104
    //                   88: 104
    //                   89: 104
    //                   90: 104
    //                   91: 104
    //                   126: 104
    //                   127: 104
    //                   130: 104;
           goto _L1 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2 _L2
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void pushControllerState()
    {
        if(mController != null)
            mController.refreshState(mCallbacks.onIsPlaying(), mCallbacks.onGetCurrentPosition(), mCallbacks.onGetTransportControlFlags());
    }

    private void reportPlayingChanged()
    {
        TransportStateListener atransportstatelistener[] = getListeners();
        if(atransportstatelistener != null)
        {
            int i = atransportstatelistener.length;
            for(int j = 0; j < i; j++)
                atransportstatelistener[j].onPlayingChanged(this);

        }
    }

    private void reportTransportControlsChanged()
    {
        TransportStateListener atransportstatelistener[] = getListeners();
        if(atransportstatelistener != null)
        {
            int i = atransportstatelistener.length;
            for(int j = 0; j < i; j++)
                atransportstatelistener[j].onTransportControlsChanged(this);

        }
    }

    public void destroy()
    {
        mController.destroy();
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        return KeyEventCompat.dispatch(keyevent, mKeyEventCallback, mDispatcherState, this);
    }

    public int getBufferPercentage()
    {
        return mCallbacks.onGetBufferPercentage();
    }

    public long getCurrentPosition()
    {
        return mCallbacks.onGetCurrentPosition();
    }

    public long getDuration()
    {
        return mCallbacks.onGetDuration();
    }

    public Object getRemoteControlClient()
    {
        Object obj;
        if(mController != null)
            obj = mController.getRemoteControlClient();
        else
            obj = null;
        return obj;
    }

    public int getTransportControlFlags()
    {
        return mCallbacks.onGetTransportControlFlags();
    }

    public boolean isPlaying()
    {
        return mCallbacks.onIsPlaying();
    }

    public void pausePlaying()
    {
        if(mController != null)
            mController.pausePlaying();
        mCallbacks.onPause();
        pushControllerState();
        reportPlayingChanged();
    }

    public void refreshState()
    {
        pushControllerState();
        reportPlayingChanged();
        reportTransportControlsChanged();
    }

    public void registerStateListener(TransportStateListener transportstatelistener)
    {
        mListeners.add(transportstatelistener);
    }

    public void seekTo(long l)
    {
        mCallbacks.onSeekTo(l);
    }

    public void startPlaying()
    {
        if(mController != null)
            mController.startPlaying();
        mCallbacks.onStart();
        pushControllerState();
        reportPlayingChanged();
    }

    public void stopPlaying()
    {
        if(mController != null)
            mController.stopPlaying();
        mCallbacks.onStop();
        pushControllerState();
        reportPlayingChanged();
    }

    public void unregisterStateListener(TransportStateListener transportstatelistener)
    {
        mListeners.remove(transportstatelistener);
    }

    public static final int FLAG_KEY_MEDIA_FAST_FORWARD = 64;
    public static final int FLAG_KEY_MEDIA_NEXT = 128;
    public static final int FLAG_KEY_MEDIA_PAUSE = 16;
    public static final int FLAG_KEY_MEDIA_PLAY = 4;
    public static final int FLAG_KEY_MEDIA_PLAY_PAUSE = 8;
    public static final int FLAG_KEY_MEDIA_PREVIOUS = 1;
    public static final int FLAG_KEY_MEDIA_REWIND = 2;
    public static final int FLAG_KEY_MEDIA_STOP = 32;
    public static final int KEYCODE_MEDIA_PAUSE = 127;
    public static final int KEYCODE_MEDIA_PLAY = 126;
    public static final int KEYCODE_MEDIA_RECORD = 130;
    final AudioManager mAudioManager;
    final TransportPerformer mCallbacks;
    final Context mContext;
    final TransportMediatorJellybeanMR2 mController;
    final Object mDispatcherState;
    final android.view.KeyEvent.Callback mKeyEventCallback;
    final ArrayList mListeners;
    final TransportMediatorCallback mTransportKeyCallback;
    final View mView;
}

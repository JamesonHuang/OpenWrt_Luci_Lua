// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.os.SystemClock;
import android.view.KeyEvent;

public abstract class TransportPerformer
{

    public TransportPerformer()
    {
    }

    public void onAudioFocusChange(int i)
    {
        int j = 0;
        i;
        JVM INSTR tableswitch -1 -1: default 20
    //                   -1 65;
           goto _L1 _L2
_L1:
        if(j != 0)
        {
            long l = SystemClock.uptimeMillis();
            onMediaButtonDown(j, new KeyEvent(l, l, 0, j, 0));
            onMediaButtonUp(j, new KeyEvent(l, l, 1, j, 0));
        }
        return;
_L2:
        j = 127;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public int onGetBufferPercentage()
    {
        return 100;
    }

    public abstract long onGetCurrentPosition();

    public abstract long onGetDuration();

    public int onGetTransportControlFlags()
    {
        return 60;
    }

    public abstract boolean onIsPlaying();

    public boolean onMediaButtonDown(int i, KeyEvent keyevent)
    {
        i;
        JVM INSTR lookupswitch 5: default 52
    //                   79: 75
    //                   85: 75
    //                   86: 68
    //                   126: 54
    //                   127: 61;
           goto _L1 _L2 _L2 _L3 _L4 _L5
_L1:
        return true;
_L4:
        onStart();
        continue; /* Loop/switch isn't completed */
_L5:
        onPause();
        continue; /* Loop/switch isn't completed */
_L3:
        onStop();
        continue; /* Loop/switch isn't completed */
_L2:
        if(onIsPlaying())
            onPause();
        else
            onStart();
        if(true) goto _L1; else goto _L6
_L6:
    }

    public boolean onMediaButtonUp(int i, KeyEvent keyevent)
    {
        return true;
    }

    public abstract void onPause();

    public abstract void onSeekTo(long l);

    public abstract void onStart();

    public abstract void onStop();

    static final int AUDIOFOCUS_GAIN = 1;
    static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
    static final int AUDIOFOCUS_LOSS = -1;
    static final int AUDIOFOCUS_LOSS_TRANSIENT = -2;
    static final int AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK = -3;
}

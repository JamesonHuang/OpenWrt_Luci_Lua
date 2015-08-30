// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.lockscreen;

import android.content.Context;
import android.media.AudioManager;

// Referenced classes of package com.sonos.acr.services.lockscreen:
//            MusicFocusable

public class AudioFocusHelper
    implements android.media.AudioManager.OnAudioFocusChangeListener
{

    public AudioFocusHelper(Context context, MusicFocusable musicfocusable)
    {
        audioManager = (AudioManager)context.getSystemService("audio");
        mFocusable = musicfocusable;
    }

    public boolean abandonFocus()
    {
        boolean flag = true;
        if(flag != audioManager.abandonAudioFocus(this))
            flag = false;
        return flag;
    }

    public void onAudioFocusChange(int i)
    {
        if(mFocusable != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        switch(i)
        {
        case -3: 
            mFocusable.onLostAudioFocus(true);
            break;

        case 1: // '\001'
            mFocusable.onGainedAudioFocus();
            break;

        case -2: 
        case -1: 
            mFocusable.onLostAudioFocus(false);
            break;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean requestFocus()
    {
        boolean flag = true;
        if(flag != audioManager.requestAudioFocus(this, 3, flag))
            flag = false;
        return flag;
    }

    AudioManager audioManager;
    MusicFocusable mFocusable;
}

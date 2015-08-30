// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying.views;

import android.view.View;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.sclib.SCINowPlayingTransport;

public interface TransportView
{
    public static interface TransportSeekListener
    {

        public abstract void onSeekEnded();

        public abstract void onSeekStarted();
    }

    public static interface TransportEventListener
    {

        public abstract void onStartTrackingTouch(View view);

        public abstract void onStopTrackingTouch(View view, long l);

        public abstract void onTransportButtonClicked(View view, TransportButtonType transportbuttontype);

        public abstract void onTransportButtonHeld(View view, TransportButtonType transportbuttontype);

        public abstract void onTransportButtonPressed(View view, TransportButtonType transportbuttontype);

        public abstract void onTransportButtonReleased(View view, TransportButtonType transportbuttontype);
    }

    public static final class TransportButtonType extends Enum
    {

        public static TransportButtonType valueOf(String s)
        {
            return (TransportButtonType)Enum.valueOf(com/sonos/acr/nowplaying/views/TransportView$TransportButtonType, s);
        }

        public static TransportButtonType[] values()
        {
            return (TransportButtonType[])$VALUES.clone();
        }

        private static final TransportButtonType $VALUES[];
        public static final TransportButtonType crossfade;
        public static final TransportButtonType elapsedTime;
        public static final TransportButtonType fwd;
        public static final TransportButtonType more;
        public static final TransportButtonType nightmode;
        public static final TransportButtonType play;
        public static final TransportButtonType remainingTime;
        public static final TransportButtonType repeat;
        public static final TransportButtonType rwd;
        public static final TransportButtonType shuffle;
        public static final TransportButtonType sleeptimer;
        public static final TransportButtonType speechenh;
        public static final TransportButtonType vote0;
        public static final TransportButtonType vote1;

        static 
        {
            play = new TransportButtonType("play", 0);
            fwd = new TransportButtonType("fwd", 1);
            rwd = new TransportButtonType("rwd", 2);
            shuffle = new TransportButtonType("shuffle", 3);
            crossfade = new TransportButtonType("crossfade", 4);
            vote0 = new TransportButtonType("vote0", 5);
            vote1 = new TransportButtonType("vote1", 6);
            repeat = new TransportButtonType("repeat", 7);
            nightmode = new TransportButtonType("nightmode", 8);
            speechenh = new TransportButtonType("speechenh", 9);
            elapsedTime = new TransportButtonType("elapsedTime", 10);
            remainingTime = new TransportButtonType("remainingTime", 11);
            sleeptimer = new TransportButtonType("sleeptimer", 12);
            more = new TransportButtonType("more", 13);
            TransportButtonType atransportbuttontype[] = new TransportButtonType[14];
            atransportbuttontype[0] = play;
            atransportbuttontype[1] = fwd;
            atransportbuttontype[2] = rwd;
            atransportbuttontype[3] = shuffle;
            atransportbuttontype[4] = crossfade;
            atransportbuttontype[5] = vote0;
            atransportbuttontype[6] = vote1;
            atransportbuttontype[7] = repeat;
            atransportbuttontype[8] = nightmode;
            atransportbuttontype[9] = speechenh;
            atransportbuttontype[10] = elapsedTime;
            atransportbuttontype[11] = remainingTime;
            atransportbuttontype[12] = sleeptimer;
            atransportbuttontype[13] = more;
            $VALUES = atransportbuttontype;
        }

        private TransportButtonType(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract void onProgressChange(SCINowPlayingTransport scinowplayingtransport, long l, boolean flag);

    public abstract void setTransportViewController(TransportEventListener transporteventlistener);

    public abstract void updateView(NowPlaying nowplaying);
}

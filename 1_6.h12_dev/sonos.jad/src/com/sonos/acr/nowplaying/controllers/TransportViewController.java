// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying.controllers;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.nowplaying.views.TransportView;
import com.sonos.acr.sclib.wrappers.NoOpCallBack;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.uibusymanager.UiBusyManager;
import com.sonos.acr.util.*;
import com.sonos.acr.view.*;
import com.sonos.sclib.*;
import java.util.Iterator;

public class TransportViewController
    implements com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayingListener, com.sonos.acr.nowplaying.views.TransportView.TransportEventListener
{
    private class TransportButtonListener
        implements android.view.View.OnClickListener, android.view.View.OnTouchListener, Runnable
    {

        public void onClick(View view)
        {
            if(!isPressed)
                onTransportButtonClicked(view, getButtonType(view));
            isPressed = false;
        }

        public boolean onTouch(View view, MotionEvent motionevent)
        {
            boolean flag = (new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())).contains(view.getLeft() + (int)motionevent.getX(), view.getTop() + (int)motionevent.getY());
            motionevent.getAction();
            JVM INSTR tableswitch 0 2: default 76
        //                       0 78
        //                       1 114
        //                       2 141;
               goto _L1 _L2 _L3 _L4
_L1:
            return false;
_L2:
            currentButtonPressed = view;
            handler.postDelayed(this, 1000L);
            onTransportButtonPressed(view, getButtonType(view));
            continue; /* Loop/switch isn't completed */
_L3:
            handler.removeCallbacks(this);
            onTransportButtonReleased(view, getButtonType(view));
            continue; /* Loop/switch isn't completed */
_L4:
            if(!flag)
            {
                handler.removeCallbacks(this);
                onTransportButtonReleased(view, getButtonType(view));
            }
            if(true) goto _L1; else goto _L5
_L5:
        }

        public void run()
        {
            if(currentButtonPressed != null && currentButtonPressed.isClickable())
            {
                onTransportButtonHeld(null, getButtonType(currentButtonPressed));
                isPressed = true;
                currentButtonPressed = null;
            }
        }

        private View currentButtonPressed;
        private Handler handler;
        private boolean isPressed;
        final TransportViewController this$0;

        private TransportButtonListener()
        {
            this$0 = TransportViewController.this;
            super();
            handler = new Handler();
            isPressed = false;
        }

    }

    private class PrevNextTransportCallBack extends SCIOpCBSwigBase
    {

        public void _operationComplete(long l, int i)
        {
            if(i != 0 && nowPlaying != null)
            {
                String as[] = new String[1];
                if(i == 800)
                {
                    nowPlaying.getTransport().getErrorString(SCNPTransportErrorID.SC_NP_ERR_SKIP_LIMIT.swigValue(), as);
                    SonosToast.popupDialog(as[0], null);
                }
                for(Iterator iterator = transportViews.iterator(); iterator.hasNext(); ((TransportView)iterator.next()).updateView(nowPlaying));
            }
        }

        final TransportViewController this$0;

        private PrevNextTransportCallBack()
        {
            this$0 = TransportViewController.this;
            super();
        }

    }

    private class PlayPauseTransportCallback extends SCIOpCBSwigBase
    {

        public void _operationComplete(long l, int i)
        {
            if(i != 0 && nowPlaying != null)
            {
                Object aobj[] = new Object[1];
                SCINowPlayingTransport scinowplayingtransport = nowPlaying.getTransport();
                Iterator iterator;
                if(scinowplayingtransport.getTrackURI(aobj) == SCRet.SC_RET_OK)
                {
                    Object aobj1[] = new Object[1];
                    scinowplayingtransport.getErrorStringFromOpResultAndURI(i, (String)aobj[0], aobj1);
                    SonosToast.popupDialog((String)aobj1[0], null);
                } else
                {
                    SLog.d(TransportViewController.LOG_TAG, "Return type is not ok");
                }
                for(iterator = transportViews.iterator(); iterator.hasNext(); ((TransportView)iterator.next()).updateView(nowPlaying));
            }
        }

        final TransportViewController this$0;

        private PlayPauseTransportCallback()
        {
            this$0 = TransportViewController.this;
            super();
        }

    }

    private class PeriodicProgressUpdater extends PeriodicExecutor
    {

        public void execute()
        {
            if(!currentSeekOperation.inProgress() && nowPlaying != null && !suppressProgressUpdates)
            {
                for(Iterator iterator = transportViews.iterator(); iterator.hasNext(); ((TransportView)iterator.next()).onProgressChange(nowPlaying.getTransport(), getElapsedTime(), true));
                updateProgressViews();
            }
        }

        public void onStart()
        {
            super.onStart();
            if(periodicSeek == null)
                getPositionInfoOperation.start();
        }

        public void onStop()
        {
            getPositionInfoOperation.cancel();
        }

        final TransportViewController this$0;

        private PeriodicProgressUpdater()
        {
            this$0 = TransportViewController.this;
            super(500L, -1L, 0L);
        }

    }

    private class GetPositionInfoOperation extends SCIOpCBSwigBase
    {

        public void _operationComplete(long l, int i)
        {
            if(i == 0)
            {
                hasRealPositionUpdated = true;
                setCurrentTrackPosition(1000L * getPositionInfoOperation.getTrackPositionInSecs());
            }
            getPositionInfoOperation = null;
        }

        public void cancel()
        {
            if(getPositionInfoOperation != null)
            {
                getPositionInfoOperation._cancel();
                getPositionInfoOperation = null;
            }
        }

        public void start()
        {
            if(getPositionInfoOperation == null && nowPlaying != null)
            {
                getPositionInfoOperation = nowPlaying.getTransport().createGetTrackPositionInfoOp();
                if(getPositionInfoOperation != null)
                    getPositionInfoOperation._start(this);
            }
        }

        private SCIOpGetTrackPositionInfo getPositionInfoOperation;
        final TransportViewController this$0;

        private GetPositionInfoOperation()
        {
            this$0 = TransportViewController.this;
            super();
        }

    }

    private class CurrentSeekOperation extends SCIOpCBSwigBase
    {

        public void _operationComplete(long l, int i)
        {
            currentSeekOperation = null;
            startProgress();
        }

        public void cancel()
        {
            if(currentSeekOperation != null)
            {
                currentSeekOperation._cancel();
                currentSeekOperation = null;
            }
        }

        public boolean inProgress()
        {
            boolean flag;
            if(currentSeekOperation != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void start(long l)
        {
            cancel();
            if(nowPlaying == null) goto _L2; else goto _L1
_L1:
            currentSeekOperation = nowPlaying.getTransport().createSeekOp(sclibConstants.SCINOWPLAYINGTRANSPORT_SEEK_RELATIVE_TIME, TimeUtils.toTime(l));
            if(currentSeekOperation == null) goto _L2; else goto _L3
_L3:
            currentSeekOperation._start(this);
            setCurrentTrackPosition(l);
_L5:
            return;
_L2:
            startProgress();
            if(true) goto _L5; else goto _L4
_L4:
        }

        private SCIOp currentSeekOperation;
        final TransportViewController this$0;

        private CurrentSeekOperation()
        {
            this$0 = TransportViewController.this;
            super();
        }

    }

    public static interface ShuffleButtonClickListener
    {

        public abstract void onShuffleClicked();
    }


    public TransportViewController(Context context1)
    {
        currentTrackPositionRecorded = -1L;
        suppressProgressUpdates = false;
        hasRealPositionUpdated = true;
        wasPlaying = false;
        context = context1;
    }

    private long calculateSeekDuration(long l, boolean flag)
    {
        long l1 = System.currentTimeMillis() - l;
        long l2;
        int i;
        if(l1 > 9000L)
            l2 = 0x2bf20L;
        else
        if(l1 > 6000L)
            l2 = 30000L;
        else
        if(l1 > 3000L)
            l2 = 5000L;
        else
            l2 = 2000L;
        if(flag)
            i = -1;
        else
            i = 1;
        return l2 * (long)i;
    }

    private void executeTransportButtonBusyOperation(SCIOp sciop, SCIOpCBSwigBase sciopcbswigbase)
    {
        if(sciop != null)
            (new UiBusyManager((SonosActivity)context, sciop, sciopcbswigbase)).start();
        else
            SLog.e(LOG_TAG, "Unable to execute Busy operation since the op was null");
    }

    private com.sonos.acr.nowplaying.views.TransportView.TransportButtonType getButtonType(View view)
    {
        return com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.valueOf((new StringBuilder()).append("").append(view.getTag()).toString().split(":")[1]);
    }

    private void setCurrentTrackPosition(long l)
    {
        SLog.d(LOG_TAG, (new StringBuilder()).append("Setting Track Position: ").append(l).toString());
        currentTrackPositionRecorded = l;
        currentTrackPositionRecordedAt = System.currentTimeMillis();
        if(nowPlaying != null)
        {
            if(!suppressProgressUpdates && !currentSeekOperation.inProgress())
            {
                for(Iterator iterator = transportViews.iterator(); iterator.hasNext(); ((TransportView)iterator.next()).onProgressChange(nowPlaying.getTransport(), currentTrackPositionRecorded, false));
            }
            updateProgressViews();
        }
    }

    private void updateButton(NowPlaying nowplaying, View view)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = false;
        if(view == null || nowplaying == null) goto _L2; else goto _L1
_L1:
        com.sonos.acr.nowplaying.views.TransportView.TransportButtonType transportbuttontype = com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.valueOf((new StringBuilder()).append("").append(view.getTag()).toString().split(":")[flag]);
        class _cls2
        {

            static final int $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType[];
            static final int $SwitchMap$com$sonos$sclib$SCINowPlayingTransport$SCPlayPauseDisplayState[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCINowPlayingTransport$SCPlayPauseDisplayState = new int[com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState.values().length];
                NoSuchFieldError nosuchfielderror15;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCINowPlayingTransport$SCPlayPauseDisplayState[com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState.SC_PLAYPAUSE_DISPLAYSTATE_PAUSE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCINowPlayingTransport$SCPlayPauseDisplayState[com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState.SC_PLAYPAUSE_DISPLAYSTATE_PLAY.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCINowPlayingTransport$SCPlayPauseDisplayState[com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState.SC_PLAYPAUSE_DISPLAYSTATE_STOP.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType = new int[com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.values().length];
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType[com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.fwd.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType[com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.rwd.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType[com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.play.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType[com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.shuffle.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType[com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.repeat.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType[com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.crossfade.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType[com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.nightmode.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType[com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.speechenh.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType[com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.vote0.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror11) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType[com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.vote1.ordinal()] = 10;
                }
                catch(NoSuchFieldError nosuchfielderror12) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType[com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.more.ordinal()] = 11;
                }
                catch(NoSuchFieldError nosuchfielderror13) { }
                try
                {
                    $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType[com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.elapsedTime.ordinal()] = 12;
                }
                catch(NoSuchFieldError nosuchfielderror14) { }
                $SwitchMap$com$sonos$acr$nowplaying$views$TransportView$TransportButtonType[com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.remainingTime.ordinal()] = 13;
_L2:
                return;
                nosuchfielderror15;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls2..SwitchMap.com.sonos.acr.nowplaying.views.TransportView.TransportButtonType[transportbuttontype.ordinal()];
        JVM INSTR tableswitch 1 13: default 124
    //                   1 183
    //                   2 137
    //                   3 125
    //                   4 223
    //                   5 245
    //                   6 267
    //                   7 289
    //                   8 340
    //                   9 391
    //                   10 391
    //                   11 422
    //                   12 405
    //                   13 414;
           goto _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L11 _L12 _L13 _L14
_L2:
        return;
_L5:
        updatePlayButton(nowplaying.getTransport(), view);
        continue; /* Loop/switch isn't completed */
_L4:
        if(nowplaying.getTransport().isPreviousTrackEnabled() || nowplaying.getTransport().isRewindEnabled((int)Math.ceil(getElapsedTime() / 1000L)))
            flag1 = flag;
        view.setEnabled(flag1);
        continue; /* Loop/switch isn't completed */
_L3:
        if(nowplaying.getTransport().isNextTrackEnabled() || nowplaying.getTransport().isFastForwardEnabled(getElapsedTime() / 1000L))
            flag1 = flag;
        view.setEnabled(flag1);
        continue; /* Loop/switch isn't completed */
_L6:
        updateView(view, nowplaying.getTransport().isShuffleEnabled(), nowplaying.getTransport().getShuffleMode());
        continue; /* Loop/switch isn't completed */
_L7:
        updateView(view, nowplaying.getTransport().isRepeatEnabled(), nowplaying.getTransport().getRepeatMode());
        continue; /* Loop/switch isn't completed */
_L8:
        updateCrossfade(view, nowplaying.getTransport().isCrossfadeEnabled(), nowplaying.getTransport().getCrossfadeMode());
        continue; /* Loop/switch isn't completed */
_L9:
        if(nowplaying.getSourceType() != SCNPSourceType.SC_NP_TYPE_HT_AUDIO_SOURCE)
            flag = false;
        updateView(view, flag, nowplaying.getNightMode());
        if(nowplaying.getSourceType() != SCNPSourceType.SC_NP_TYPE_HT_AUDIO_SOURCE)
            flag1 = 8;
        view.setVisibility(flag1);
        continue; /* Loop/switch isn't completed */
_L10:
        if(nowplaying.getSourceType() != SCNPSourceType.SC_NP_TYPE_HT_AUDIO_SOURCE)
            flag = false;
        updateView(view, flag, nowplaying.getTVDialogEnhancement());
        if(nowplaying.getSourceType() != SCNPSourceType.SC_NP_TYPE_HT_AUDIO_SOURCE)
            flag1 = 8;
        view.setVisibility(flag1);
        continue; /* Loop/switch isn't completed */
_L11:
        updateVotingButton(nowplaying.getRatings(), view, transportbuttontype);
        continue; /* Loop/switch isn't completed */
_L13:
        updateElapsedTime(nowplaying, view);
        continue; /* Loop/switch isn't completed */
_L14:
        updateRemainingTime(view);
        continue; /* Loop/switch isn't completed */
_L12:
        if(!(view instanceof LinearLayout))
        {
            if(!nowplaying.hasMusic())
                flag1 = 4;
            view.setVisibility(flag1);
        }
        if(true) goto _L2; else goto _L15
_L15:
    }

    private void updateCrossfade(View view, boolean flag, boolean flag1)
    {
        if(view instanceof SonosButton)
        {
            String s = (new StringBuilder()).append(view.getResources().getString(0x7f0c0003)).append(" (").toString();
            String s1;
            String s2;
            if(flag1)
                s1 = (new StringBuilder()).append(s).append(view.getResources().getString(0x7f0c000b)).toString();
            else
                s1 = (new StringBuilder()).append(s).append(view.getResources().getString(0x7f0c000a)).toString();
            s2 = (new StringBuilder()).append(s1).append(")").toString();
            ((SonosButton)view).setText(s2);
        }
    }

    private void updateElapsedTime(NowPlaying nowplaying, View view)
    {
        boolean flag = true;
        if(!(view instanceof TextView)) goto _L2; else goto _L1
_L1:
        ((TextView)view).setText(TimeUtils.toShortTime(getElapsedTime()));
        int i;
        if(nowplaying.hasMusic() && nowplaying.isProgressBarEnabled())
            i = 0;
        else
            i = 4;
        view.setVisibility(i);
_L4:
        return;
_L2:
        if(view instanceof SonosProgressBar)
        {
            SonosProgressBar sonosprogressbar = (SonosProgressBar)view;
            SCINowPlayingTransport scinowplayingtransport = nowplaying.getTransport();
            boolean flag1 = scinowplayingtransport.isTrackPositionInfoAvailable();
            boolean flag2 = scinowplayingtransport.isSeekEnabled();
            sonosprogressbar.setProgressable(flag1);
            boolean flag3;
            if(flag1 && flag2)
                flag3 = flag;
            else
                flag3 = false;
            sonosprogressbar.setIsUserInteractable(flag3);
            sonosprogressbar.setMax(scinowplayingtransport.getCurrentTrackDuration());
            sonosprogressbar.setProgress((int)getElapsedTime());
            if(!flag1 || !flag2)
                flag = false;
            view.setEnabled(flag);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void updatePlayButton(SCINowPlayingTransport scinowplayingtransport, View view)
    {
        com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState scplaypausedisplaystate;
        updateView(view, scinowplayingtransport.isPlayPauseEnabled(), false);
        scplaypausedisplaystate = scinowplayingtransport.getPlayPauseDisplayState();
        _cls2..SwitchMap.com.sonos.sclib.SCINowPlayingTransport.SCPlayPauseDisplayState[scplaypausedisplaystate.ordinal()];
        JVM INSTR tableswitch 1 2: default 44
    //                   1 85
    //                   2 98;
           goto _L1 _L2 _L3
_L1:
        int ai[];
        int i;
        ai = STATE_STOPPED_SET;
        i = 0x7f0c0019;
_L5:
        view.setContentDescription(context.getString(i));
        if(view instanceof ImageView)
            ((ImageView)view).setImageState(ai, true);
        return;
_L2:
        ai = STATE_PAUSED_SET;
        i = 0x7f0c000c;
        continue; /* Loop/switch isn't completed */
_L3:
        ai = STATE_PLAYING_SET;
        i = 0x7f0c000d;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void updateProgressViews()
    {
        Iterator iterator = transportButtons.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            View view = (View)iterator.next();
            if(view == null)
                continue;
            com.sonos.acr.nowplaying.views.TransportView.TransportButtonType transportbuttontype = com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.valueOf((new StringBuilder()).append("").append(view.getTag()).toString().split(":")[1]);
            switch(_cls2..SwitchMap.com.sonos.acr.nowplaying.views.TransportView.TransportButtonType[transportbuttontype.ordinal()])
            {
            case 1: // '\001'
            case 2: // '\002'
            case 12: // '\f'
            case 13: // '\r'
                updateButton(nowPlaying, view);
                break;
            }
        } while(true);
    }

    private void updateRemainingTime(View view)
    {
        if(view instanceof TextView)
        {
            ((TextView)view).setText(TimeUtils.toShortTime(getRemainingTime(), true));
            int i;
            if(nowPlaying.hasMusic() && nowPlaying.isProgressBarEnabled())
                i = 0;
            else
                i = 4;
            view.setVisibility(i);
        }
    }

    private void updateView(View view, boolean flag, boolean flag1)
    {
        view.setEnabled(flag);
        view.setSelected(flag1);
    }

    private void updateVotingButton(SCINowPlayingRatings scinowplayingratings, View view, com.sonos.acr.nowplaying.views.TransportView.TransportButtonType transportbuttontype)
    {
        int i;
        if(transportbuttontype == com.sonos.acr.nowplaying.views.TransportView.TransportButtonType.vote0)
            i = 0;
        else
            i = 1;
        if(scinowplayingratings == null) goto _L2; else goto _L1
_L1:
        if(scinowplayingratings.numberOfRatings() != 1) goto _L4; else goto _L3
_L3:
        if(i != 0) goto _L6; else goto _L5
_L5:
        view.setVisibility(4);
_L2:
        return;
_L6:
        if(!scinowplayingratings.isRatingVisible(0))
        {
            view.setVisibility(4);
        } else
        {
            view.setVisibility(0);
            if(view instanceof RemoteImageView)
                ((RemoteImageView)view).setImageURI(scinowplayingratings.getRatingImageURL(0), scinowplayingratings.getRatingImageType(0));
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(!scinowplayingratings.isRatingVisible(i))
        {
            view.setVisibility(8);
        } else
        {
            view.setVisibility(0);
            if(view instanceof RemoteImageView)
                ((RemoteImageView)view).setImageURI(scinowplayingratings.getRatingImageURL(i), scinowplayingratings.getRatingImageType(i));
        }
        if(true) goto _L2; else goto _L7
_L7:
    }

    public void addView(TransportView transportview)
    {
        if(transportview != null)
        {
            transportview.setTransportViewController(this);
            transportViews.add(transportview);
            if(nowPlaying != null)
                transportview.updateView(nowPlaying);
        }
    }

    public void clear()
    {
        for(Iterator iterator = transportViews.iterator(); iterator.hasNext(); ((TransportView)iterator.next()).setTransportViewController(null));
        transportViews.clear();
    }

    public long getElapsedTime()
    {
        long l = -1L;
        if(currentTrackPositionRecorded != l && nowPlaying != null)
            if(nowPlaying.isPlaying() && hasRealPositionUpdated || !nowPlaying.isPlaying() && !hasRealPositionUpdated)
                l = currentTrackPositionRecorded + (System.currentTimeMillis() - currentTrackPositionRecordedAt);
            else
                l = currentTrackPositionRecorded;
        return l;
    }

    public long getRemainingTime()
    {
        return (long)nowPlaying.getTransport().getCurrentTrackDuration() - getElapsedTime();
    }

    public void ignoreView(View view)
    {
        if(transportButtons.remove(view))
        {
            view.setOnTouchListener(null);
            view.setOnClickListener(null);
        }
    }

    public void observeView(View view)
    {
        transportButtons.add(view);
        if(view.isClickable())
            view.setOnClickListener(transportButtonListener);
        view.setOnTouchListener(transportButtonListener);
        updateView(view);
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent nowplayevent)
    {
        if(nowplayevent == com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent.OnMusicChanged && wasPlaying != nowplaying.isPlaying())
            hasRealPositionUpdated = false;
        wasPlaying = nowplaying.isPlaying();
        updateProgressView(nowplaying);
        Iterator iterator = transportButtons.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            View view = (View)iterator.next();
            if(view != null)
                updateButton(nowplaying, view);
        } while(true);
    }

    protected void onStartSeek()
    {
        Iterator iterator = transportViews.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            TransportView transportview = (TransportView)iterator.next();
            if(transportview instanceof com.sonos.acr.nowplaying.views.TransportView.TransportSeekListener)
                ((com.sonos.acr.nowplaying.views.TransportView.TransportSeekListener)transportview).onSeekStarted();
        } while(true);
    }

    public void onStartTrackingTouch(View view)
    {
        suppressProgressUpdates = true;
        currentSeekOperation.cancel();
        getPositionInfoOperation.cancel();
        if(periodicProgressUpdater != null)
            periodicProgressUpdater.stop();
    }

    protected void onStopSeek()
    {
        Iterator iterator = transportViews.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            TransportView transportview = (TransportView)iterator.next();
            if(transportview instanceof com.sonos.acr.nowplaying.views.TransportView.TransportSeekListener)
                ((com.sonos.acr.nowplaying.views.TransportView.TransportSeekListener)transportview).onSeekEnded();
        } while(true);
    }

    public void onStopTrackingTouch(View view, long l)
    {
        suppressProgressUpdates = false;
        currentSeekOperation.start(l);
    }

    public void onTransportButtonClicked(View view, com.sonos.acr.nowplaying.views.TransportView.TransportButtonType transportbuttontype)
    {
        boolean flag = true;
        if(!view.isShown() || nowPlaying == null) goto _L2; else goto _L1
_L1:
        SCINowPlayingTransport scinowplayingtransport = nowPlaying.getTransport();
        _cls2..SwitchMap.com.sonos.acr.nowplaying.views.TransportView.TransportButtonType[transportbuttontype.ordinal()];
        JVM INSTR tableswitch 1 11: default 92
    //                   1 170
    //                   2 114
    //                   3 93
    //                   4 191
    //                   5 237
    //                   6 267
    //                   7 297
    //                   8 327
    //                   9 357
    //                   10 384
    //                   11 441;
           goto _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L2:
        return;
_L5:
        executeTransportButtonBusyOperation(scinowplayingtransport.createTogglePlayPauseOp(), new PlayPauseTransportCallback());
        continue; /* Loop/switch isn't completed */
_L4:
        long l = getElapsedTime();
        SCIOp sciop;
        if(l != -1L)
            sciop = scinowplayingtransport.createRewindToStartOrPrevTrackOp(l / 1000L);
        else
            sciop = scinowplayingtransport.createPrevTrackOp();
        executeTransportButtonBusyOperation(sciop, new PrevNextTransportCallBack());
        continue; /* Loop/switch isn't completed */
_L3:
        executeTransportButtonBusyOperation(scinowplayingtransport.createNextTrackOp(), new PrevNextTransportCallBack());
        continue; /* Loop/switch isn't completed */
_L6:
        if(scinowplayingtransport.getShuffleMode())
            flag = false;
        executeTransportButtonBusyOperation(scinowplayingtransport.createSetShuffleModeOp(flag), completionCallback);
        if(shuffleListener != null)
            shuffleListener.onShuffleClicked();
        continue; /* Loop/switch isn't completed */
_L7:
        if(scinowplayingtransport.getRepeatMode())
            flag = false;
        executeTransportButtonBusyOperation(scinowplayingtransport.createSetRepeatModeOp(flag), completionCallback);
        continue; /* Loop/switch isn't completed */
_L8:
        if(scinowplayingtransport.getCrossfadeMode())
            flag = false;
        executeTransportButtonBusyOperation(scinowplayingtransport.createSetCrossfadeModeOp(flag), completionCallback);
        continue; /* Loop/switch isn't completed */
_L9:
        NowPlaying nowplaying1 = nowPlaying;
        if(nowPlaying.getNightMode())
            flag = false;
        nowplaying1.setNightMode(flag);
        continue; /* Loop/switch isn't completed */
_L10:
        NowPlaying nowplaying = nowPlaying;
        if(nowPlaying.getTVDialogEnhancement())
            flag = false;
        nowplaying.setTVDialogEnhancement(flag);
        continue; /* Loop/switch isn't completed */
_L11:
        SCIActionContext sciactioncontext1 = nowPlaying.getRatings().getActionForRating(0);
        if(sciactioncontext1 != null)
            sciactioncontext1.perform();
        continue; /* Loop/switch isn't completed */
_L12:
        SCIActionContext sciactioncontext;
        if(nowPlaying.getRatings().numberOfRatings() > flag)
            sciactioncontext = nowPlaying.getRatings().getActionForRating(flag);
        else
            sciactioncontext = nowPlaying.getRatings().getActionForRating(0);
        if(sciactioncontext != null)
            sciactioncontext.perform();
        continue; /* Loop/switch isn't completed */
_L13:
        ((SonosActivity)context).showInfo(view);
        if(true) goto _L2; else goto _L14
_L14:
    }

    public void onTransportButtonHeld(View view, com.sonos.acr.nowplaying.views.TransportView.TransportButtonType transportbuttontype)
    {
        if(nowPlaying == null) goto _L2; else goto _L1
_L1:
        SCINowPlayingTransport scinowplayingtransport = nowPlaying.getTransport();
        if(!scinowplayingtransport.isSeekEnabled()) goto _L4; else goto _L3
_L3:
        _cls2..SwitchMap.com.sonos.acr.nowplaying.views.TransportView.TransportButtonType[transportbuttontype.ordinal()];
        JVM INSTR tableswitch 1 2: default 52
    //                   1 53
    //                   2 61;
           goto _L2 _L5 _L6
_L2:
        return;
_L5:
        startSeek(false);
        continue; /* Loop/switch isn't completed */
_L6:
        startSeek(true);
        continue; /* Loop/switch isn't completed */
_L4:
        String as[];
        as = new String[1];
        switch(_cls2..SwitchMap.com.sonos.acr.nowplaying.views.TransportView.TransportButtonType[transportbuttontype.ordinal()])
        {
        default:
            break;

        case 1: // '\001'
            break; /* Loop/switch isn't completed */

        case 2: // '\002'
            break;
        }
        break MISSING_BLOCK_LABEL_140;
_L8:
        if(StringUtils.isNotEmptyOrNull(as[0]))
            SonosToast.popupDialog(as[0], null);
        if(true) goto _L2; else goto _L7
_L7:
        scinowplayingtransport.getErrorString(SCNPTransportErrorID.SC_NP_ERR_SEEK_FORWARD.swigValue(), as);
          goto _L8
        scinowplayingtransport.getErrorString(SCNPTransportErrorID.SC_NP_ERR_SEEK_BACK.swigValue(), as);
          goto _L8
    }

    public void onTransportButtonPressed(View view, com.sonos.acr.nowplaying.views.TransportView.TransportButtonType transportbuttontype)
    {
    }

    public void onTransportButtonReleased(View view, com.sonos.acr.nowplaying.views.TransportView.TransportButtonType transportbuttontype)
    {
        if(periodicSeek != null)
        {
            periodicSeek.stop();
            periodicSeek = null;
        }
    }

    public void removeView(TransportView transportview)
    {
        transportViews.remove(transportview);
        transportview.setTransportViewController(null);
    }

    protected void startProgress()
    {
        if(periodicProgressUpdater != null)
            periodicProgressUpdater.start();
    }

    public void startSeek(boolean flag)
    {
        if(periodicSeek == null)
        {
            periodicSeek = new PeriodicExecutor(flag) {

                public void execute()
                {
                    long l = calculateSeekDuration(getStartTime(), reverse);
                    long l1 = getElapsedTime();
                    long l2 = nowPlaying.getTransport().getCurrentTrackDuration();
                    long l3 = Math.min(Math.max(l1 + l, 0L), l2);
                    setCurrentTrackPosition(l3);
                }

                public void onStart()
                {
                    stopProgress();
                    onStartSeek();
                }

                public void onStop()
                {
                    currentSeekOperation.start(getElapsedTime());
                    onStopSeek();
                    startProgress();
                }

                final TransportViewController this$0;
                final boolean val$reverse;

            
            {
                this$0 = TransportViewController.this;
                reverse = flag;
                super(final_l);
            }
            }
;
            periodicSeek.start();
        }
    }

    protected void stopProgress()
    {
        if(periodicProgressUpdater != null)
            periodicProgressUpdater.stop();
    }

    public void subscribe()
    {
        if(periodicProgressUpdater == null)
            periodicProgressUpdater = new PeriodicProgressUpdater();
    }

    public void unsubscribe()
    {
        if(periodicProgressUpdater != null)
        {
            periodicProgressUpdater.stop();
            periodicProgressUpdater = null;
        }
    }

    public void updateProgressView(NowPlaying nowplaying)
    {
        nowPlaying = nowplaying;
        if(nowplaying != null)
        {
            Iterator iterator;
            if(nowplaying.isPlaying() && nowplaying.isProgressBarEnabled())
            {
                startProgress();
            } else
            {
                stopProgress();
                setCurrentTrackPosition(getElapsedTime());
            }
            for(iterator = transportViews.iterator(); iterator.hasNext(); ((TransportView)iterator.next()).updateView(nowplaying));
        }
        getPositionInfoOperation.start();
    }

    public void updateView(View view)
    {
        if(nowPlaying != null)
            updateButton(nowPlaying, view);
    }

    protected static final int DELAY_MILLIS = 1000;
    public static final String LOG_TAG = com/sonos/acr/nowplaying/controllers/TransportViewController.getSimpleName();
    protected static int STATE_PAUSED_SET[];
    protected static int STATE_PLAYING_SET[];
    protected static int STATE_STOPPED_SET[];
    protected final SCIOpCBSwigBase completionCallback = new NoOpCallBack();
    final Context context;
    protected final CurrentSeekOperation currentSeekOperation = new CurrentSeekOperation();
    private long currentTrackPositionRecorded;
    private long currentTrackPositionRecordedAt;
    protected final GetPositionInfoOperation getPositionInfoOperation = new GetPositionInfoOperation();
    boolean hasRealPositionUpdated;
    NowPlaying nowPlaying;
    private PeriodicExecutor periodicProgressUpdater;
    protected PeriodicExecutor periodicSeek;
    public ShuffleButtonClickListener shuffleListener;
    boolean suppressProgressUpdates;
    private final TransportButtonListener transportButtonListener = new TransportButtonListener();
    private final WeakHashSet transportButtons = new WeakHashSet();
    private final WeakHashSet transportViews = new WeakHashSet();
    boolean wasPlaying;

    static 
    {
        int ai[] = new int[1];
        ai[0] = 0x7f01002d;
        STATE_PLAYING_SET = ai;
        int ai1[] = new int[1];
        ai1[0] = 0x7f01002e;
        STATE_STOPPED_SET = ai1;
        int ai2[] = new int[1];
        ai2[0] = 0x7f01002f;
        STATE_PAUSED_SET = ai2;
    }





}

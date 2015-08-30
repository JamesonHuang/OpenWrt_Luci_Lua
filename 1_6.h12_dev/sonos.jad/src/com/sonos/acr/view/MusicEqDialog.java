// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.sonos.acr.sclib.wrappers.NoOpCallBack;
import com.sonos.acr.sclib.wrappers.ZoneDevice;
import com.sonos.sclib.*;

public class MusicEqDialog extends AlertDialog
    implements android.widget.SeekBar.OnSeekBarChangeListener
{
    class MusicEQEventSink extends SCIEventSinkSwigBase
    {

        public void dispatchEvent(SCIObj sciobj, String s)
        {
            if(!(sciobj instanceof SCIDeviceMusicEqualization) || musicEQView == null) goto _L2; else goto _L1
_L1:
            SCIDeviceMusicEqualization scidevicemusicequalization = (SCIDeviceMusicEqualization)sciobj;
            if(!s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONBASSLEVELCHANGED_EVENT)) goto _L4; else goto _L3
_L3:
            bassSlider.setProgress(scidevicemusicequalization.getBassLevel() + SCIDeviceMusicEqualization.MAX_BASS_LEVEL);
_L2:
            return;
_L4:
            if(s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONBALANCELEVELCHANGED_EVENT))
                balanceSlider.setProgress(scidevicemusicequalization.getLeftRightBalanceLevel() + SCIDeviceMusicEqualization.MAX_BALANCE_LEVEL);
            else
            if(s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONTREBLELEVELCHANGED_EVENT))
                trebleSlider.setProgress(scidevicemusicequalization.getTrebleLevel() + SCIDeviceMusicEqualization.MAX_TREBLE_LEVEL);
            else
            if(s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONLOUDNESSCHANGED_EVENT))
                loudnessToggleButton.setChecked(scidevicemusicequalization.getLoudnessBoost());
            if(true) goto _L2; else goto _L5
_L5:
        }

        final MusicEqDialog this$0;

        MusicEQEventSink()
        {
            this$0 = MusicEqDialog.this;
            super();
        }
    }

    public static interface ViewCompletedListener
    {

        public abstract void onViewCompleted();
    }


    public MusicEqDialog(Context context)
    {
        super(context);
        musicEQEventSink = new MusicEQEventSink();
        musicEQView = LayoutInflater.from(context).inflate(0x7f03006f, null);
    }

    private void showSlidersView(ZoneDevice zonedevice)
    {
        musicEqualization = zonedevice.getEQ();
        balanceSlider = (SeekBar)musicEQView.findViewById(0x7f0a0071);
        bassSlider = (SeekBar)musicEQView.findViewById(0x7f0a006f);
        trebleSlider = (SeekBar)musicEQView.findViewById(0x7f0a0070);
        loudnessToggleButton = (ToggleButton)musicEQView.findViewById(0x7f0a006b);
        balanceNegativeText = musicEQView.findViewById(0x7f0a006e);
        balancePositiveText = musicEQView.findViewById(0x7f0a0075);
        balanceText = musicEQView.findViewById(0x7f0a0069);
        balanceSlider.setMax(2 * SCIDeviceMusicEqualization.MAX_BALANCE_LEVEL);
        bassSlider.setMax(2 * SCIDeviceMusicEqualization.MAX_BASS_LEVEL);
        trebleSlider.setMax(2 * SCIDeviceMusicEqualization.MAX_TREBLE_LEVEL);
        balanceSlider.setOnSeekBarChangeListener(this);
        bassSlider.setOnSeekBarChangeListener(this);
        trebleSlider.setOnSeekBarChangeListener(this);
        loudnessToggleButton.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                musicEqualization.createSetLoudnessBoostOp(flag)._start(new NoOpCallBack());
            }

            final MusicEqDialog this$0;

            
            {
                this$0 = MusicEqDialog.this;
                super();
            }
        }
);
        musicEQView.findViewById(0x7f0a0072).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                musicEqualization.createResetAllOp()._start(new NoOpCallBack());
            }

            final MusicEqDialog this$0;

            
            {
                this$0 = MusicEqDialog.this;
                super();
            }
        }
);
        if(musicEQView.findViewById(0x7f0a0172) == null)
        {
            String s = getContext().getResources().getText(0x7f0c0083).toString();
            Object aobj[] = new Object[1];
            aobj[0] = zonedevice.getTitle();
            setTitle(String.format(s, aobj));
        } else
        {
            setTitle(musicEqualization.getRecommendedTitle());
            ((TextView)musicEQView.findViewById(0x7f0a0172)).setText(zonedevice.getTitle());
        }
        setView(musicEQView);
        setButton(-3, getContext().getResources().getText(0x7f0c0050), (android.content.DialogInterface.OnClickListener)null);
        show();
    }

    public void dismiss()
    {
        musicEqualization.unsubscribe(musicEQEventSink);
        super.dismiss();
    }

    public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
    {
    }

    public void onStartTrackingTouch(SeekBar seekbar)
    {
    }

    public void onStopTrackingTouch(SeekBar seekbar)
    {
        SCIOp sciop = null;
        if(seekbar != balanceSlider) goto _L2; else goto _L1
_L1:
        sciop = musicEqualization.createSetLeftRightBalanceLevelOp(seekbar.getProgress() - SCIDeviceMusicEqualization.MAX_BALANCE_LEVEL);
_L4:
        if(sciop != null)
            sciop._start(new NoOpCallBack());
        return;
_L2:
        if(seekbar == bassSlider)
            sciop = musicEqualization.createSetBassLevelOp(seekbar.getProgress() - SCIDeviceMusicEqualization.MAX_BASS_LEVEL);
        else
        if(seekbar == trebleSlider)
            sciop = musicEqualization.createSetTrebleLevelOp(seekbar.getProgress() - SCIDeviceMusicEqualization.MAX_TREBLE_LEVEL);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void show()
    {
        int i;
        if(musicEqualization.shouldShowBalance())
            i = 0;
        else
            i = 8;
        balanceText.setVisibility(i);
        balanceNegativeText.setVisibility(i);
        balancePositiveText.setVisibility(i);
        balanceSlider.setVisibility(i);
        if(balanceSlider.getParent() instanceof RelativeLayout)
            ((RelativeLayout)balanceSlider.getParent()).setVisibility(i);
        super.show();
        musicEqualization.subscribe(musicEQEventSink);
    }

    public void showEqView(ZoneDevice zonedevice)
    {
        showEqView(zonedevice, null);
    }

    public boolean showEqView(final ZoneDevice device, final ViewCompletedListener listener)
    {
        SCIDeviceLineOut scidevicelineout = device.getLineOut();
        boolean flag;
        if(scidevicelineout == null)
        {
            flag = false;
        } else
        {
            final SCIOpRenderingControlGetOutputFixed op = scidevicelineout.createGetLineOutFixed();
            op._start(new SCIOpCBSwigBase() {

                public void _operationComplete(long l, int i)
                {
                    if(!op.getCurrentFixed())
                        showSlidersView(device);
                    else
                        (new android.app.AlertDialog.Builder(getContext())).setMessage(0x7f0c007f).setCancelable(true).setNegativeButton(0x104000a, null).show();
                    if(listener != null)
                        listener.onViewCompleted();
                }

                final MusicEqDialog this$0;
                final ZoneDevice val$device;
                final ViewCompletedListener val$listener;
                final SCIOpRenderingControlGetOutputFixed val$op;

            
            {
                this$0 = MusicEqDialog.this;
                op = scioprenderingcontrolgetoutputfixed;
                device = zonedevice;
                listener = viewcompletedlistener;
                super();
            }
            }
);
            flag = true;
        }
        return flag;
    }

    private View balanceNegativeText;
    private View balancePositiveText;
    private SeekBar balanceSlider;
    private View balanceText;
    private SeekBar bassSlider;
    private ToggleButton loudnessToggleButton;
    private MusicEQEventSink musicEQEventSink;
    private View musicEQView;
    private SCIDeviceMusicEqualization musicEqualization;
    private SeekBar trebleSlider;







}

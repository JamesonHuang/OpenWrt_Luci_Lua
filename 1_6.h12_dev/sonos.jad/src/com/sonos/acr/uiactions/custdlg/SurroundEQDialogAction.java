// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions.custdlg;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.sclib.wrappers.ZoneDevice;
import com.sonos.acr.uiactions.DisplayCustomControlAction;
import com.sonos.acr.view.SonosButton;
import com.sonos.sclib.*;

public class SurroundEQDialogAction extends DisplayCustomControlAction
{
    class MusicEQEventSink extends SCIEventSinkSwigBase
    {

        public void dispatchEvent(SCIObj sciobj, String s)
        {
            if(sciobj instanceof SCIDeviceMusicEqualization)
            {
                SCIDeviceMusicEqualization scidevicemusicequalization = (SCIDeviceMusicEqualization)sciobj;
                if(s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONSURROUNDLEVELCHANGED_EVENT))
                    m_gainSlider.setProgress(scidevicemusicequalization.getSurroundLevel() - SCIDeviceMusicEqualization.MIN_SURROUND_LEVEL);
                if(s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONSURROUNDENABLEDCHANGED_EVENT))
                    m_enabledToggleButton.setChecked(scidevicemusicequalization.getSurroundEnabled());
                if(s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONSURROUNDMODECHANGED_EVENT))
                {
                    SonosButton sonosbutton = m_modeButton;
                    Resources resources = currentContext.getResources();
                    int i;
                    if(m_musicEq.getSurroundMode() != 0)
                        i = 0x7f0c00fd;
                    else
                        i = 0x7f0c00fb;
                    sonosbutton.setText(resources.getString(i));
                }
            }
        }

        public boolean listenToEventType(String s)
        {
            boolean flag;
            if(s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONSURROUNDLEVELCHANGED_EVENT) || s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONSURROUNDENABLEDCHANGED_EVENT) || s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONSURROUNDMODECHANGED_EVENT))
                flag = true;
            else
                flag = false;
            return flag;
        }

        final SurroundEQDialogAction this$0;

        MusicEQEventSink()
        {
            this$0 = SurroundEQDialogAction.this;
            super();
        }
    }


    public SurroundEQDialogAction(SonosActivity sonosactivity)
    {
        super(sonosactivity);
        m_musicEQEventSink = new MusicEQEventSink();
        m_musicEQView = LayoutInflater.from(sonosactivity).inflate(0x7f030071, null);
        m_enabledToggleButton = (ToggleButton)m_musicEQView.findViewById(0x7f0a0177);
        m_gainSlider = (SeekBar)m_musicEQView.findViewById(0x7f0a0179);
        m_modeButton = (SonosButton)m_musicEQView.findViewById(0x7f0a0181);
        m_resetButton = (SonosButton)m_musicEQView.findViewById(0x7f0a0072);
        m_gainSlider.setMax(SCIDeviceMusicEqualization.MAX_SURROUND_LEVEL - SCIDeviceMusicEqualization.MIN_SURROUND_LEVEL);
        m_gainSlider.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
            {
                if(flag)
                    m_musicEq.setSurroundLevel(seekbar.getProgress() + SCIDeviceMusicEqualization.MIN_SURROUND_LEVEL);
            }

            public void onStartTrackingTouch(SeekBar seekbar)
            {
            }

            public void onStopTrackingTouch(SeekBar seekbar)
            {
            }

            final SurroundEQDialogAction this$0;

            
            {
                this$0 = SurroundEQDialogAction.this;
                super();
            }
        }
);
        m_enabledToggleButton.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                m_musicEq.setSurroundEnabled(flag);
            }

            final SurroundEQDialogAction this$0;

            
            {
                this$0 = SurroundEQDialogAction.this;
                super();
            }
        }
);
        m_modeButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                showSurroundModePopup();
            }

            final SurroundEQDialogAction this$0;

            
            {
                this$0 = SurroundEQDialogAction.this;
                super();
            }
        }
);
        m_resetButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                m_musicEq.resetSurround();
            }

            final SurroundEQDialogAction this$0;

            
            {
                this$0 = SurroundEQDialogAction.this;
                super();
            }
        }
);
    }

    private void showEQDialog(ZoneDevice zonedevice)
    {
        m_musicEq = zonedevice.getEQ();
        m_alertDialogBuilder.setView(m_musicEQView);
        m_alertDialog = m_alertDialogBuilder.setTitle(0x7f0c00ff).setPositiveButton(0x7f0c0050, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                terminate(dialoginterface);
            }

            final SurroundEQDialogAction this$0;

            
            {
                this$0 = SurroundEQDialogAction.this;
                super();
            }
        }
).create();
        m_alertDialog.show();
        m_musicEq.subscribe(m_musicEQEventSink);
    }

    private void showSurroundModePopup()
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(currentContext);
        builder.setTitle(0x7f0c00fe);
        View view = currentContext.getLayoutInflater().inflate(0x7f030072, null);
        ((SonosButton)view.findViewById(0x7f0a0183)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                m_musicEq.setSurroundMode(0);
                m_dialog.dismiss();
                m_dialog = null;
            }

            final SurroundEQDialogAction this$0;

            
            {
                this$0 = SurroundEQDialogAction.this;
                super();
            }
        }
);
        ((SonosButton)view.findViewById(0x7f0a0184)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                m_musicEq.setSurroundMode(1);
                m_dialog.dismiss();
                m_dialog = null;
            }

            final SurroundEQDialogAction this$0;

            
            {
                this$0 = SurroundEQDialogAction.this;
                super();
            }
        }
);
        builder.setView(view);
        builder.setNegativeButton(0x7f0c0037, null);
        m_dialog = builder.create();
        m_dialog.show();
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        String s = sciactioncontext.getPropertyBag().getStrProp(sclibConstants.SCACTN_STRPROP_DEVICEID);
        showEQDialog(currentContext.getHousehold().lookupDevice(s));
        return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
    }

    protected void terminate(DialogInterface dialoginterface)
    {
        super.terminate(dialoginterface);
        if(m_musicEq != null)
            m_musicEq.unsubscribe(m_musicEQEventSink);
        m_musicEq = null;
    }

    private AlertDialog m_dialog;
    private ToggleButton m_enabledToggleButton;
    private SeekBar m_gainSlider;
    private SonosButton m_modeButton;
    private MusicEQEventSink m_musicEQEventSink;
    private View m_musicEQView;
    private SCIDeviceMusicEqualization m_musicEq;
    private SonosButton m_resetButton;





/*
    static AlertDialog access$202(SurroundEQDialogAction surroundeqdialogaction, AlertDialog alertdialog)
    {
        surroundeqdialogaction.m_dialog = alertdialog;
        return alertdialog;
    }

*/



}

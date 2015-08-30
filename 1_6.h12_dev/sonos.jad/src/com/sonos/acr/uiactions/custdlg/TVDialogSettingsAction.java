// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions.custdlg;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.sclib.wrappers.ZoneDevice;
import com.sonos.acr.uiactions.DisplayCustomControlAction;
import com.sonos.acr.util.SLog;
import com.sonos.acr.view.SonosButton;
import com.sonos.sclib.*;

public class TVDialogSettingsAction extends DisplayCustomControlAction
{
    class MusicEQEventSink extends SCIEventSinkSwigBase
    {

        public void dispatchEvent(SCIObj sciobj, String s)
        {
            if(sciobj instanceof SCIDeviceMusicEqualization)
            {
                SCIDeviceMusicEqualization scidevicemusicequalization = (SCIDeviceMusicEqualization)sciobj;
                if(s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONTVAUDIODELAYCHANGED_EVENT))
                    m_audioDelaySlider.setProgress(scidevicemusicequalization.getTVAudioDelay());
            }
        }

        public boolean listenToEventType(String s)
        {
            return s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONTVAUDIODELAYCHANGED_EVENT);
        }

        final TVDialogSettingsAction this$0;

        MusicEQEventSink()
        {
            this$0 = TVDialogSettingsAction.this;
            super();
        }
    }


    public TVDialogSettingsAction(SonosActivity sonosactivity)
    {
        super(sonosactivity);
        m_musicEQEventSink = new MusicEQEventSink();
        tvDialogSettingsView = LayoutInflater.from(sonosactivity).inflate(0x7f030073, null);
        m_audioDelaySlider = (SeekBar)tvDialogSettingsView.findViewById(0x7f0a0187);
        m_resetButton = (SonosButton)tvDialogSettingsView.findViewById(0x7f0a0072);
        m_audioDelaySlider.setMax(SCIDeviceMusicEqualization.MAX_TV_AUDIO_DELAY - SCIDeviceMusicEqualization.MIN_TV_AUDIO_DELAY);
        m_audioDelaySlider.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
            {
            }

            public void onStartTrackingTouch(SeekBar seekbar)
            {
            }

            public void onStopTrackingTouch(SeekBar seekbar)
            {
                int i = seekbar.getProgress() + SCIDeviceMusicEqualization.MIN_TV_AUDIO_DELAY;
                m_musicEq.setTVAudioDelay(i);
            }

            final TVDialogSettingsAction this$0;

            
            {
                this$0 = TVDialogSettingsAction.this;
                super();
            }
        }
);
        m_resetButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                m_musicEq.resetTVDialog();
            }

            final TVDialogSettingsAction this$0;

            
            {
                this$0 = TVDialogSettingsAction.this;
                super();
            }
        }
);
    }

    private void showTVDialogSettings(ZoneDevice zonedevice)
    {
        SLog.d(LOG_TAG, (new StringBuilder()).append("SCIDevice: ").append(zonedevice).toString());
        m_musicEq = zonedevice.getEQ();
        m_alertDialogBuilder.setView(tvDialogSettingsView);
        m_alertDialog = m_alertDialogBuilder.setTitle(0x7f0c0106).setPositiveButton(0x7f0c0050, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                terminate(dialoginterface);
            }

            final TVDialogSettingsAction this$0;

            
            {
                this$0 = TVDialogSettingsAction.this;
                super();
            }
        }
).create();
        m_alertDialog.show();
        m_musicEq.subscribe(m_musicEQEventSink);
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        SCIPropertyBag scipropertybag = sciactioncontext.getPropertyBag();
        if(scipropertybag.doesPropExist(sclibConstants.SCACTN_STRPROP_DEVICEID))
            SLog.d(LOG_TAG, (new StringBuilder()).append("DeviceID = ").append(scipropertybag.getStrProp(sclibConstants.SCACTN_STRPROP_DEVICEID)).toString());
        String s = scipropertybag.getStrProp(sclibConstants.SCACTN_STRPROP_DEVICEID);
        showTVDialogSettings(currentContext.getHousehold().lookupDevice(s));
        return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
    }

    protected void terminate(DialogInterface dialoginterface)
    {
        super.terminate(dialoginterface);
        if(m_musicEq != null)
            m_musicEq.unsubscribe(m_musicEQEventSink);
        m_musicEq = null;
    }

    private SeekBar m_audioDelaySlider;
    private MusicEQEventSink m_musicEQEventSink;
    private SCIDeviceMusicEqualization m_musicEq;
    private SonosButton m_resetButton;
    private View tvDialogSettingsView;


}

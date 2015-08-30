// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions.custdlg;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.sclib.wrappers.ZoneDevice;
import com.sonos.acr.uiactions.DisplayCustomControlAction;
import com.sonos.acr.view.SonosButton;
import com.sonos.sclib.*;

public class SUBEQDialogAction extends DisplayCustomControlAction
{
    class MusicEQEventSink extends SCIEventSinkSwigBase
    {

        public void dispatchEvent(SCIObj sciobj, String s)
        {
            if(!(sciobj instanceof SCIDeviceMusicEqualization)) goto _L2; else goto _L1
_L1:
            SCIDeviceMusicEqualization scidevicemusicequalization = (SCIDeviceMusicEqualization)sciobj;
            if(!s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONSUBGAINCHANGED_EVENT)) goto _L4; else goto _L3
_L3:
            m_gainSlider.setProgress(scidevicemusicequalization.getSubGain() + SCIDeviceMusicEqualization.MAX_SUB_GAIN);
_L2:
            return;
_L4:
            if(s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONSUBPOLARITYCHANGED_EVENT))
                m_polarityToggleButton.setChecked(scidevicemusicequalization.getSubPolarity());
            else
            if(s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONSUBENABLEDCHANGED_EVENT))
                m_enabledToggleButton.setChecked(scidevicemusicequalization.getSubEnabled());
            else
            if(s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONCROSSOVERCHANGED_EVENT))
            {
                int i = m_musicEq.getCrossoverIndex();
                m_speakerSizeSpinner.setSelection(i);
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        public boolean listenToEventType(String s)
        {
            boolean flag;
            if(s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONSUBGAINCHANGED_EVENT) || s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONSUBPOLARITYCHANGED_EVENT) || s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONSUBENABLEDCHANGED_EVENT) || s.equals(sclibConstants.SCIDEVICEMUSICEQUALIZATION_ONCROSSOVERCHANGED_EVENT))
                flag = true;
            else
                flag = false;
            return flag;
        }

        final SUBEQDialogAction this$0;

        MusicEQEventSink()
        {
            this$0 = SUBEQDialogAction.this;
            super();
        }
    }


    public SUBEQDialogAction(SonosActivity sonosactivity)
    {
        super(sonosactivity);
        m_musicEQEventSink = new MusicEQEventSink();
        m_musicEQView = LayoutInflater.from(sonosactivity).inflate(0x7f030070, null);
        m_gainSlider = (SeekBar)m_musicEQView.findViewById(0x7f0a0179);
        m_enabledToggleButton = (ToggleButton)m_musicEQView.findViewById(0x7f0a0177);
        m_polarityToggleButton = (ToggleButton)m_musicEQView.findViewById(0x7f0a017c);
        m_resetButton = (SonosButton)m_musicEQView.findViewById(0x7f0a0072);
        m_speakerSizeLabel = (TextView)m_musicEQView.findViewById(0x7f0a017d);
        m_speakerSizeSpinner = (Spinner)m_musicEQView.findViewById(0x7f0a017e);
        m_gainSlider.setMax(2 * SCIDeviceMusicEqualization.MAX_SUB_GAIN);
        m_gainSlider.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
            {
                if(flag)
                    m_musicEq.setSubGain(seekbar.getProgress() - SCIDeviceMusicEqualization.MAX_SUB_GAIN);
            }

            public void onStartTrackingTouch(SeekBar seekbar)
            {
            }

            public void onStopTrackingTouch(SeekBar seekbar)
            {
            }

            final SUBEQDialogAction this$0;

            
            {
                this$0 = SUBEQDialogAction.this;
                super();
            }
        }
);
        m_enabledToggleButton.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                m_musicEq.setSubEnabled(flag);
            }

            final SUBEQDialogAction this$0;

            
            {
                this$0 = SUBEQDialogAction.this;
                super();
            }
        }
);
        m_polarityToggleButton.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                m_musicEq.setSubPolarity(flag);
            }

            final SUBEQDialogAction this$0;

            
            {
                this$0 = SUBEQDialogAction.this;
                super();
            }
        }
);
        m_resetButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                m_musicEq.resetSubEq();
            }

            final SUBEQDialogAction this$0;

            
            {
                this$0 = SUBEQDialogAction.this;
                super();
            }
        }
);
    }

    private void showEQDialog(ZoneDevice zonedevice)
    {
        m_musicEq = zonedevice.getEQ();
        Spinner spinner = m_speakerSizeSpinner;
        if(m_musicEq.shouldShowCrossoverAdjust())
        {
            spinner.setVisibility(0);
            m_speakerSizeLabel.setVisibility(0);
            SCIStringArray scistringarray = m_musicEq.getCrossoverIndexLabels();
            int i = (int)scistringarray.size();
            String as[] = new String[i];
            for(int j = 0; j < i; j++)
                as[j] = scistringarray.getAt(j);

            ArrayAdapter arrayadapter = new ArrayAdapter(currentContext, 0x1090008, as);
            arrayadapter.setDropDownViewResource(0x1090009);
            spinner.setAdapter(arrayadapter);
            int k = m_musicEq.getCrossoverIndex();
            if(k != -1)
                spinner.setSelection(k);
            spinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView adapterview, View view, int l, long l1)
                {
                    m_musicEq.setCrossoverIndex(l);
                }

                public void onNothingSelected(AdapterView adapterview)
                {
                }

                final SUBEQDialogAction this$0;

            
            {
                this$0 = SUBEQDialogAction.this;
                super();
            }
            }
);
        }
        m_alertDialogBuilder.setView(m_musicEQView);
        m_alertDialog = m_alertDialogBuilder.setTitle(0x7f0c00f1).setPositiveButton(0x7f0c0050, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int l)
            {
                terminate(dialoginterface);
            }

            final SUBEQDialogAction this$0;

            
            {
                this$0 = SUBEQDialogAction.this;
                super();
            }
        }
).create();
        m_alertDialog.show();
        m_musicEq.subscribe(m_musicEQEventSink);
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

    private ToggleButton m_enabledToggleButton;
    private SeekBar m_gainSlider;
    private MusicEQEventSink m_musicEQEventSink;
    private View m_musicEQView;
    private SCIDeviceMusicEqualization m_musicEq;
    private ToggleButton m_polarityToggleButton;
    private SonosButton m_resetButton;
    private TextView m_speakerSizeLabel;
    private Spinner m_speakerSizeSpinner;





}

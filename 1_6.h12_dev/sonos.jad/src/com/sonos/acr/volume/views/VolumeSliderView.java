// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.volume.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.*;
import android.widget.*;
import com.sonos.acr.nowplaying.controllers.VolumeView;
import com.sonos.acr.nowplaying.controllers.VolumeViewController;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import com.sonos.acr.view.SonosSeekBar;
import com.sonos.sclib.SCIDeviceOutputMode;

public class VolumeSliderView extends FrameLayout
    implements com.sonos.acr.view.SonosSeekBar.SonosSeekBarListener, android.view.View.OnClickListener, VolumeView
{

    public VolumeSliderView(Context context)
    {
        this(context, null);
    }

    public VolumeSliderView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public VolumeSliderView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        int ai[] = new int[2];
        ai[0] = 0;
        ai[1] = 0;
        activeStates = ai;
        isMuted = false;
        deviceId = "";
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.VolumeSliderView);
        volumeBarLayoutId = typedarray.getResourceId(0, getDefaultLayoutId());
        typedarray.recycle();
        refreshLayout();
    }

    private int getStateMapping(SCIDeviceOutputMode scideviceoutputmode)
    {
        if(scideviceoutputmode == null) goto _L2; else goto _L1
_L1:
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCIDeviceOutputMode[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCIDeviceOutputMode = new int[SCIDeviceOutputMode.values().length];
                NoSuchFieldError nosuchfielderror3;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDeviceOutputMode[SCIDeviceOutputMode.SCI_DEVICEVOLUME_OUTPUT_FIXED.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDeviceOutputMode[SCIDeviceOutputMode.SCI_DEVICEVOLUME_OUTPUT_HEADPHONES.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIDeviceOutputMode[SCIDeviceOutputMode.SCI_DEVICEVOLUME_OUTPUT_UNKNOWN.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                $SwitchMap$com$sonos$sclib$SCIDeviceOutputMode[SCIDeviceOutputMode.SCI_DEVICEVOLUME_OUTPUT_DEFAULT.ordinal()] = 4;
_L2:
                return;
                nosuchfielderror3;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCIDeviceOutputMode[scideviceoutputmode.ordinal()];
        JVM INSTR tableswitch 1 3: default 40
    //                   1 45
    //                   2 51
    //                   3 57;
           goto _L2 _L3 _L4 _L5
_L2:
        int i = 0x7f010067;
_L7:
        return i;
_L3:
        i = 0x7f010065;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 0x7f010064;
        continue; /* Loop/switch isn't completed */
_L5:
        i = 0x7f010066;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private void updateAccessibilityStrings(DeviceVolume devicevolume)
    {
        SonosSeekBar sonosseekbar = volumeSeekBar;
        String s = getResources().getString(0x7f0c001b);
        Object aobj[] = new Object[2];
        aobj[0] = devicevolume.getDeviceName();
        aobj[1] = Integer.valueOf(devicevolume.getVolume());
        sonosseekbar.setContentDescription(String.format(s, aobj));
        if(devicevolume.isMuted())
        {
            ImageView imageview1 = muteButton;
            String s2 = getResources().getString(0x7f0c001c);
            Object aobj2[] = new Object[1];
            aobj2[0] = devicevolume.getDeviceName();
            imageview1.setContentDescription(String.format(s2, aobj2));
        } else
        {
            ImageView imageview = muteButton;
            String s1 = getResources().getString(0x7f0c001a);
            Object aobj1[] = new Object[1];
            aobj1[0] = devicevolume.getDeviceName();
            imageview.setContentDescription(String.format(s1, aobj1));
        }
    }

    private void updateDrawableState(SCIDeviceOutputMode scideviceoutputmode, boolean flag)
    {
        int i = 0;
        if(outputMode != scideviceoutputmode || isMuted != flag)
        {
            outputMode = scideviceoutputmode;
            isMuted = flag;
            activeStates[i] = getStateMapping(outputMode);
            int ai[] = activeStates;
            if(isMuted)
                i = 0x7f010063;
            ai[1] = i;
            refreshDrawableState();
            refreshDrawableState();
        }
    }

    private void updateView(DeviceVolume devicevolume)
    {
        if(devicevolume != null)
        {
            volumeSeekBar.setIsUserInteractable(true);
            muteButton.setEnabled(true);
            volumeSeekBar.setProgress(devicevolume.getVolume());
            updateVolumeLabel(devicevolume);
            updateVolumeText(devicevolume);
            updateVisibility(devicevolume);
            updateAccessibilityStrings(devicevolume);
        } else
        {
            volumeSeekBar.setIsUserInteractable(false);
            muteButton.setEnabled(false);
            volumeSeekBar.setProgress(20);
        }
        if(zoneGroupName != null)
        {
            ZoneGroup zonegroup = LibraryUtils.getCurrentZoneGroup();
            TextView textview = zoneGroupName;
            String s;
            if(zonegroup != null)
                s = zonegroup.createSimpleZoneGroupTitle();
            else
                s = "";
            textview.setText(s);
        }
    }

    private void updateVisibility(DeviceVolume devicevolume)
    {
        if(devicevolume == null) goto _L2; else goto _L1
_L1:
        com.sonos.acr.sclib.wrappers.DeviceVolume.VolumeMode volumemode;
        updateDrawableState(devicevolume.outputMode(), devicevolume.isMuted());
        volumemode = devicevolume.getSliderMode();
        if(volumemode != com.sonos.acr.sclib.wrappers.DeviceVolume.VolumeMode.FIXED) goto _L4; else goto _L3
_L3:
        volumeSeekBar.setEnabled(false);
        if(volumeText != null)
        {
            volumeText.setText(0x7f0c010d);
            volumeText.setVisibility(0);
        }
        if(volumeTextContainer != null)
            volumeTextContainer.setVisibility(0);
        volumeSeekBar.setVisibility(4);
_L2:
        return;
_L4:
        if(volumemode == com.sonos.acr.sclib.wrappers.DeviceVolume.VolumeMode.INDIVIDUAL_ONLY)
        {
            volumeSeekBar.setEnabled(false);
            if(volumeText != null)
            {
                volumeText.setText(0x7f0c010c);
                volumeText.setVisibility(0);
            }
            if(volumeTextContainer != null)
                volumeTextContainer.setVisibility(0);
            volumeSeekBar.setVisibility(4);
        } else
        if(volumemode == com.sonos.acr.sclib.wrappers.DeviceVolume.VolumeMode.DEFAULT)
        {
            volumeSeekBar.setEnabled(true);
            if(volumeText != null)
                volumeText.setVisibility(4);
            if(volumeTextContainer != null)
                volumeTextContainer.setVisibility(8);
            volumeSeekBar.setVisibility(0);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void updateVolumeLabel(DeviceVolume devicevolume)
    {
        if(volumeLabel != null)
            volumeLabel.setText(devicevolume.getDeviceName());
    }

    private void updateVolumeText(DeviceVolume devicevolume)
    {
        if(devicevolume == null) goto _L2; else goto _L1
_L1:
        com.sonos.acr.sclib.wrappers.DeviceVolume.VolumeMode volumemode = devicevolume.getSliderMode();
        if(volumemode != com.sonos.acr.sclib.wrappers.DeviceVolume.VolumeMode.FIXED) goto _L4; else goto _L3
_L3:
        setEnabled(false);
        volumeText.setText(0x7f0c010d);
        volumeText.setVisibility(0);
_L2:
        return;
_L4:
        if(volumemode == com.sonos.acr.sclib.wrappers.DeviceVolume.VolumeMode.INDIVIDUAL_ONLY)
        {
            setEnabled(false);
            volumeText.setText(0x7f0c010c);
            volumeText.setVisibility(0);
        } else
        if(volumemode == com.sonos.acr.sclib.wrappers.DeviceVolume.VolumeMode.DEFAULT)
        {
            setEnabled(true);
            volumeText.setVisibility(4);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void dispatchSetPressed(boolean flag)
    {
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        muteButton.setImageState(activeStates, true);
        volumeSeekBar.setAdditionalStates(activeStates);
    }

    protected int getDefaultLayoutId()
    {
        return 0x7f0300a2;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void onClick(View view)
    {
        if(volumeViewController != null && view.equals(muteButton))
            volumeViewController.toggleMute(deviceId);
    }

    public int[] onCreateDrawableState(int i)
    {
        int ai[];
        if(activeStates != null)
        {
            ai = super.onCreateDrawableState(i + activeStates.length);
            mergeDrawableStates(ai, activeStates);
        } else
        {
            ai = super.onCreateDrawableState(i);
        }
        return ai;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        requestFocus();
        if(volumeViewController != null && willHandleMotionEvent(motionevent))
            volumeViewController.onUserInteraction(deviceId);
        return super.onInterceptTouchEvent(motionevent);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        SLog.e(LOG_TAG, "OnKeyDown");
        boolean flag;
        if((i == 24 || i == 25) && volumeViewController != null)
            flag = volumeViewController.onVolumeKeyDown(i, deviceId);
        else
            flag = super.onKeyDown(i, keyevent);
        return flag;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        SLog.e(LOG_TAG, "OnKeyUp");
        boolean flag;
        if((i == 24 || i == 25) && volumeViewController != null)
            flag = volumeViewController.onVolumeKeyUp(i, deviceId);
        else
            flag = super.onKeyUp(i, keyevent);
        return flag;
    }

    public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
    {
        if(volumeViewController != null && flag)
            volumeViewController.onAbsoluteVolumeSeekChange(deviceId, i);
    }

    public int onSonosSeekBarTappedToDecrease(SonosSeekBar sonosseekbar)
    {
        int i;
        if(volumeViewController != null)
        {
            volumeViewController.onRelativeVolumeSeekChange(deviceId, -1);
            i = 1;
        } else
        {
            i = 0;
        }
        return i;
    }

    public int onSonosSeekBarTappedToIncrease(SonosSeekBar sonosseekbar)
    {
        int i = 1;
        if(volumeViewController != null)
            volumeViewController.onRelativeVolumeSeekChange(deviceId, i);
        else
            i = 0;
        return i;
    }

    public void onStartTrackingTouch(SeekBar seekbar)
    {
        if(volumeViewController != null)
            volumeViewController.onStartTrackingTouch(deviceId);
    }

    public void onStopTrackingTouch(SeekBar seekbar)
    {
        if(volumeViewController != null)
            volumeViewController.onStopTrackingTouch(deviceId);
    }

    public void onUserVolumeEvent(GroupVolume groupvolume, String s, com.sonos.acr.nowplaying.controllers.VolumeViewController.UserEventType usereventtype)
    {
        updateView(groupvolume.getDeviceVolume(deviceId));
    }

    public void onVolumeEvent(GroupVolume groupvolume, com.sonos.acr.sclib.sinks.GroupVolumeEventSink.VolumeEvent volumeevent)
    {
        updateView(groupvolume.getDeviceVolume(deviceId));
    }

    protected void refreshLayout()
    {
        removeAllViews();
        LayoutInflater.from(getContext()).inflate(volumeBarLayoutId, this, true);
        volumeSeekBar = (SonosSeekBar)findViewById(0x7f0a01db);
        muteButton = (ImageView)findViewById(0x7f0a01da);
        volumeText = (TextView)findViewById(0x7f0a01dd);
        volumeLabel = (TextView)findViewById(0x7f0a01de);
        zoneGroupName = (TextView)findViewById(0x7f0a00d0);
        volumeTextContainer = (ViewGroup)findViewById(0x7f0a01dc);
        volumeSeekBar.setOnSonosSeekBarChangeListener(this);
        muteButton.setOnClickListener(this);
    }

    public void setDeviceId(String s)
    {
        deviceId = s;
    }

    public void setVolumeViewController(VolumeViewController volumeviewcontroller)
    {
        volumeViewController = volumeviewcontroller;
    }

    protected boolean willHandleMotionEvent(MotionEvent motionevent)
    {
        return true;
    }

    public static final String LOG_TAG = com/sonos/acr/volume/views/VolumeSliderView.getSimpleName();
    private static final int nominalVolumeForNoDevice = 20;
    protected final int activeStates[];
    private String deviceId;
    protected boolean isMuted;
    protected ImageView muteButton;
    protected SCIDeviceOutputMode outputMode;
    private int volumeBarLayoutId;
    protected TextView volumeLabel;
    private SonosSeekBar volumeSeekBar;
    protected TextView volumeText;
    protected ViewGroup volumeTextContainer;
    private VolumeViewController volumeViewController;
    protected TextView zoneGroupName;

}

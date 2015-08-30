// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.zonemenu;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.*;
import android.widget.*;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.nowplaying.controllers.HouseholdController;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.StringUtils;
import com.sonos.acr.view.*;
import com.sonos.sclib.SCINowPlayingTransport;
import com.sonos.sclib.SCNPPlaybackState;
import java.util.ArrayList;

public class RoomsMenuCell extends RelativeLayout
    implements android.view.View.OnClickListener, android.view.View.OnLongClickListener
{
    public static interface ActionListener
    {

        public abstract void onMainButtonLongPress(RoomsMenuCell roomsmenucell);

        public abstract void onMainButtonPress(RoomsMenuCell roomsmenucell);

        public abstract void onZoneGroupButtonPress(RoomsMenuCell roomsmenucell);
    }


    public RoomsMenuCell(Context context)
    {
        super(context);
        isCurrent = false;
        isCompatibleAndVisible = true;
        initialize(context);
    }

    public RoomsMenuCell(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        isCurrent = false;
        isCompatibleAndVisible = true;
        initialize(context);
    }

    public RoomsMenuCell(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        isCurrent = false;
        isCompatibleAndVisible = true;
        initialize(context);
    }

    private void updateDeviceViews(ZoneGroup zonegroup)
    {
        ArrayList arraylist = zonegroup.getDevices();
        int i = arraylist.size();
        int j = 0;
        while(j < zoneNames.length) 
        {
            TextView textview = zoneNames[j];
            if(j < i)
            {
                ZoneDevice zonedevice = (ZoneDevice)arraylist.get(j);
                textview.setVisibility(0);
                if(j == -1 + zoneNames.length && j < i - 1)
                    textview.setText((new StringBuilder()).append(zonedevice.getTitle()).append(" + ").append(i - 1 - j).toString());
                else
                    textview.setText(zonedevice.getTitle());
            } else
            {
                textview.setVisibility(8);
            }
            j++;
        }
        refreshDrawableState();
    }

    public void initialize(Context context)
    {
        LayoutInflater.from(context).inflate(0x7f030062, this, true);
        zmMetaDataText1 = (TextView)findViewById(0x7f0a0154);
        zmMetaDataText2 = (TextView)findViewById(0x7f0a0155);
        errorText = (SonosTextView)findViewById(0x7f0a0156);
        albumArt = (RemoteImageView)findViewById(0x7f0a002c);
        updateView = (ImageView)findViewById(0x7f0a0153);
        firstLinePlayIndicator = (PlayIndicatorView)findViewById(0x7f0a0143);
        secondLinePlayIndicator = (PlayIndicatorView)findViewById(0x7f0a0145);
        metadataContainer = (ViewGroup)findViewById(0x7f0a0141);
        middleLayout = (LinearLayout)findViewById(0x7f0a014e);
        bottomLayout = findViewById(0x7f0a0152);
        groupingButtonFrame = (FrameLayout)findViewById(0x7f0a014d);
        TextView atextview[] = new TextView[4];
        atextview[0] = (TextView)findViewById(0x7f0a014c);
        atextview[1] = (TextView)findViewById(0x7f0a014f);
        atextview[2] = (TextView)findViewById(0x7f0a0150);
        atextview[3] = (TextView)findViewById(0x7f0a0151);
        zoneNames = atextview;
        groupingButtonFrame.setOnClickListener(this);
        setClickable(true);
        setFocusable(true);
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    public void onClick(View view)
    {
        SLog.e("RoomsMenuCell", (new StringBuilder()).append("On Click: ").append(view).toString());
        if(actionListener == null) goto _L2; else goto _L1
_L1:
        if(view != groupingButtonFrame) goto _L4; else goto _L3
_L3:
        actionListener.onZoneGroupButtonPress(this);
_L2:
        return;
_L4:
        if(view == this)
            actionListener.onMainButtonPress(this);
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected int[] onCreateDrawableState(int i)
    {
        int ai[] = super.onCreateDrawableState(i + 1);
        if(isCurrent)
            mergeDrawableStates(ai, STATE_CURRENT);
        return ai;
    }

    public boolean onLongClick(View view)
    {
        actionListener.onMainButtonLongPress(this);
        return true;
    }

    public void setActionListener(ActionListener actionlistener)
    {
        actionListener = actionlistener;
    }

    public void setCurrent(boolean flag)
    {
        if(isCurrent != flag)
        {
            isCurrent = flag;
            drawableStateChanged();
            groupingButtonFrame.setSelected(flag);
        }
    }

    public void setZoneGroup(ZoneGroup zonegroup)
    {
        zoneGroupId = zonegroup.getID();
        updateZoneGroupView(zonegroup);
    }

    public void updateNowPlayingView(NowPlaying nowplaying)
    {
        boolean flag1;
        boolean flag2;
        String as[] = nowplaying.getDoubleLineMetaData();
        TextView textview = zmMetaDataText1;
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCNPPlaybackState[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCNPPlaybackState = new int[SCNPPlaybackState.values().length];
                NoSuchFieldError nosuchfielderror2;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_PAUSED.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_STOPPED.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$com$sonos$sclib$SCNPPlaybackState[SCNPPlaybackState.SC_NP_PLAYBACK_PLAYING.ordinal()] = 3;
_L2:
                return;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        String s;
        TextView textview1;
        String s1;
        boolean flag;
        com.sonos.acr.nowplaying.controllers.PlayIndicatorController playindicatorcontroller;
        SCNPPlaybackState scnpplaybackstate;
        if(as != null)
            s = as[0];
        else
            s = "";
        textview.setText(s);
        textview1 = zmMetaDataText2;
        if(as != null)
            s1 = as[1];
        else
            s1 = "";
        textview1.setText(s1);
        albumArt.setSmallImageFromNowPlaying(nowplaying);
        flag = nowplaying.hasMusic();
        if(!nowplaying.getZoneGroup().isCompatible())
        {
            albumArt.setVisibility(8);
            updateView.setVisibility(0);
            String s4 = "";
            if(nowplaying.getZoneGroup().getDevices().size() > 0)
                s4 = ((ZoneDevice)nowplaying.getZoneGroup().getDevices().get(0)).getTitle();
            SonosTextView sonostextview1 = errorText;
            Resources resources = getResources();
            Object aobj1[] = new Object[1];
            aobj1[0] = s4.toUpperCase();
            sonostextview1.setText(resources.getString(0x7f0c012c, aobj1));
            zmMetaDataText1.setVisibility(8);
            zmMetaDataText2.setVisibility(8);
            bottomLayout.setVisibility(0);
            errorText.setVisibility(0);
            middleLayout.setVisibility(8);
            zoneNames[0].setVisibility(8);
            groupingButtonFrame.setVisibility(8);
        } else
        if(nowplaying.getZoneGroup().isUnconfigured())
        {
            albumArt.setVisibility(8);
            updateView.setVisibility(0);
            String s2 = "";
            if(nowplaying.getZoneGroup().getDevices().size() > 0)
                s2 = ((ZoneDevice)nowplaying.getZoneGroup().getDevices().get(0)).getDeviceModelDisplayString();
            SonosTextView sonostextview = errorText;
            String s3 = getResources().getString(0x7f0c012a);
            Object aobj[] = new Object[1];
            aobj[0] = s2.toUpperCase();
            sonostextview.setText(String.format(s3, aobj));
            zmMetaDataText1.setVisibility(8);
            zmMetaDataText2.setVisibility(8);
            errorText.setVisibility(0);
            middleLayout.setVisibility(8);
            zoneNames[0].setVisibility(8);
            bottomLayout.setVisibility(0);
            groupingButtonFrame.setVisibility(8);
        } else
        if(nowplaying.getZoneGroup().isUnbondedSUB())
        {
            albumArt.setVisibility(8);
            updateView.setVisibility(0);
            errorText.setText(getResources().getString(0x7f0c012b));
            zmMetaDataText1.setVisibility(8);
            zmMetaDataText2.setVisibility(8);
            errorText.setVisibility(0);
            middleLayout.setVisibility(8);
            zoneNames[0].setVisibility(8);
            bottomLayout.setVisibility(0);
            groupingButtonFrame.setVisibility(8);
        } else
        {
            RemoteImageView remoteimageview = albumArt;
            int i;
            TextView textview2;
            int j;
            TextView textview3;
            int k;
            View view;
            int l;
            if(flag)
                i = 0;
            else
                i = 8;
            remoteimageview.setVisibility(i);
            textview2 = zmMetaDataText1;
            if(flag)
                j = 0;
            else
                j = 8;
            textview2.setVisibility(j);
            textview3 = zmMetaDataText2;
            if(flag)
                k = 0;
            else
                k = 8;
            textview3.setVisibility(k);
            view = bottomLayout;
            if(flag)
                l = 0;
            else
                l = 8;
            view.setVisibility(l);
            updateView.setVisibility(8);
            errorText.setVisibility(8);
            middleLayout.setVisibility(0);
            zoneNames[0].setVisibility(0);
            groupingButtonFrame.setVisibility(0);
        }
        playindicatorcontroller = ((SonosActivity)getContext()).getHouseholdController().getPlayIndicatorController(nowplaying.getZoneGroup().getID());
        firstLinePlayIndicator.setController(playindicatorcontroller);
        secondLinePlayIndicator.setController(playindicatorcontroller);
        scnpplaybackstate = nowplaying.getTransport().getPlaybackState();
        flag1 = StringUtils.isNotEmptyOrNull(zmMetaDataText2.getText());
        if(StringUtils.isNotEmptyOrNull(zmMetaDataText1.getText()) && zmMetaDataText1.getVisibility() == 0)
            flag2 = true;
        else
            flag2 = false;
        switch(_cls1..SwitchMap.com.sonos.sclib.SCNPPlaybackState[scnpplaybackstate.ordinal()])
        {
        default:
            firstLinePlayIndicator.setVisibility(8);
            secondLinePlayIndicator.setVisibility(8);
            break;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            break MISSING_BLOCK_LABEL_850;
        }
_L1:
        PlayIndicatorView playindicatorview;
        byte byte0;
        PlayIndicatorView playindicatorview1;
        int i1;
        if(flag1)
            zmMetaDataText2.setVisibility(0);
        else
            zmMetaDataText2.setVisibility(8);
        return;
        playindicatorview = firstLinePlayIndicator;
        if(flag2)
        {
            if(flag1)
                byte0 = 4;
            else
                byte0 = 0;
        } else
        {
            byte0 = 8;
        }
        playindicatorview.setVisibility(byte0);
        playindicatorview1 = secondLinePlayIndicator;
        if(flag1)
            i1 = 0;
        else
            i1 = 8;
        playindicatorview1.setVisibility(i1);
          goto _L1
    }

    public void updateZoneGroupView(ZoneGroup zonegroup)
    {
        isCompatibleAndVisible = zonegroup.isCompatibleAndVisible();
        if(isCompatibleAndVisible)
            groupingButtonFrame.setVisibility(0);
        else
            groupingButtonFrame.setVisibility(8);
        updateDeviceViews(zonegroup);
        updateNowPlayingView(zonegroup.nowPlaying);
    }

    public static final int STATE_CURRENT[];
    protected ActionListener actionListener;
    protected RemoteImageView albumArt;
    protected View bottomLayout;
    protected SonosTextView errorText;
    protected PlayIndicatorView firstLinePlayIndicator;
    protected FrameLayout groupingButtonFrame;
    private boolean isCompatibleAndVisible;
    protected boolean isCurrent;
    protected ViewGroup metadataContainer;
    protected LinearLayout middleLayout;
    protected PlayIndicatorView secondLinePlayIndicator;
    protected ImageView updateView;
    protected TextView zmMetaDataText1;
    protected TextView zmMetaDataText2;
    protected String zoneGroupId;
    protected TextView zoneNames[];

    static 
    {
        int ai[] = new int[1];
        ai[0] = 0x7f010035;
        STATE_CURRENT = ai;
    }
}

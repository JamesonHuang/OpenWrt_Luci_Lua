// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying.views;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.SonosFragment;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.nowplaying.MoreMenuOverlayFragment;
import com.sonos.acr.nowplaying.SleepDialogHandler;
import com.sonos.acr.nowplaying.controllers.HouseholdController;
import com.sonos.acr.nowplaying.controllers.ZoneGroupController;
import com.sonos.acr.util.StringUtils;

public class SleepTimerFragment extends SonosFragment
    implements com.sonos.acr.nowplaying.SleepDialogHandler.SleepTimerListener
{

    public SleepTimerFragment()
    {
        super(0x7f0d0021);
    }

    private void onClick(int i)
    {
        com.sonos.acr.nowplaying.SleepDialogHandler.SleepTime sleeptime = com.sonos.acr.nowplaying.SleepDialogHandler.SleepTime.values()[i];
        handler.setNewSleepTime(sleeptime.sleepTime, getSonosActivity());
        ((MoreMenuOverlayFragment)getParentFragment()).dismiss();
    }

    private void updateSleepHeader()
    {
        if(sleepHeader != null)
        {
            Resources resources = SonosApplication.getInstance().getResources();
            SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
            spannablestringbuilder.append(new SpannableString(resources.getString(0x7f0c0017)));
            String s = handler.getTimeRemainingText();
            if(StringUtils.isNotEmptyOrNull(s))
            {
                SpannableString spannablestring = new SpannableString((new StringBuilder()).append(" (").append(s).append(")").toString());
                int i = resources.getColor(0x7f08001f);
                if(s.matches(resources.getString(0x7f0c00c1)))
                    i = getResources().getColor(0x7f080024);
                spannablestring.setSpan(new ForegroundColorSpan(i), 0, spannablestring.length(), 0);
                spannablestringbuilder.append(spannablestring);
            }
            sleepHeader.setText(spannablestringbuilder, android.widget.TextView.BufferType.SPANNABLE);
        }
    }

    protected void onActiveChanged(boolean flag)
    {
        super.onActiveChanged(flag);
        if(handler != null)
        {
            if(flag)
            {
                handler.registerSleepTimerListener(this);
                updateSleepHeader();
            } else
            {
                handler.unregisterSleepTimerListener(this);
            }
        } else
        {
            Log.e("SleepTimerFragment", "onActiveChanged called with no handler!");
        }
    }

    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        handler = getSonosActivity().getHouseholdController().getCurrentZoneGroupController().getSleepTimerHandler();
        handler.registerSleepTimerListener(this);
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view;
        ArrayAdapter arrayadapter;
        mainView = layoutinflater.inflate(0x7f030097, viewgroup, false);
        sleepHeader = (TextView)mainView.findViewById(0x7f0a01b2);
        view = mainView.findViewById(0x7f0a01b3);
        arrayadapter = new ArrayAdapter(SonosApplication.getInstance().getBaseContext(), 0x7f030003, com.sonos.acr.nowplaying.SleepDialogHandler.SleepTime.labels(mainView.getResources()));
        if(!(view instanceof ListView)) goto _L2; else goto _L1
_L1:
        ListView listview = (ListView)view;
        listview.setAdapter(arrayadapter);
        listview.setChoiceMode(1);
        listview.setItemsCanFocus(false);
        listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view2, int i, long l)
            {
                onClick(i);
            }

            final SleepTimerFragment this$0;

            
            {
                this$0 = SleepTimerFragment.this;
                super();
            }
        }
);
_L4:
        return mainView;
_L2:
        if(view instanceof LinearLayout)
        {
            LinearLayout linearlayout = (LinearLayout)view;
            final int position = 0;
            while(position < arrayadapter.getCount()) 
            {
                View view1 = arrayadapter.getView(position, null, linearlayout);
                view1.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view2)
                    {
                        SleepTimerFragment.this.onClick(position);
                    }

                    final SleepTimerFragment this$0;
                    final int val$position;

            
            {
                this$0 = SleepTimerFragment.this;
                position = i;
                super();
            }
                }
);
                linearlayout.addView(view1);
                position++;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onDetach()
    {
        if(handler != null)
        {
            handler.unregisterSleepTimerListener(this);
            handler = null;
        }
        super.onDetach();
    }

    public void onSleepTimerChanged(boolean flag)
    {
        updateSleepHeader();
    }

    private static final String LOG_TAG = com/sonos/acr/nowplaying/views/SleepTimerFragment.getSimpleName();
    protected SleepDialogHandler handler;
    protected View mainView;
    private TextView sleepHeader;


}

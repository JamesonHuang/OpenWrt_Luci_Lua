// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.nowplaying;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.view.SonosPopupWindow;

public class SwitcherPopupWindow extends SonosPopupWindow
{

    public SwitcherPopupWindow(SonosActivity sonosactivity)
    {
        super(((LayoutInflater)sonosactivity.getSystemService("layout_inflater")).inflate(0x7f03009f, null, false), -2, -2);
        activity = sonosactivity;
        setBackgroundDrawable(new ColorDrawable(0));
        setOutsideTouchable(true);
        setTouchable(true);
        ((TextView)getContentView().findViewById(0x7f0a01d3)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                dismiss();
            }

            final SwitcherPopupWindow this$0;

            
            {
                this$0 = SwitcherPopupWindow.this;
                super();
            }
        }
);
        ((TextView)getContentView().findViewById(0x7f0a01d2)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                activity.showNowPlaying();
                dismiss();
            }

            final SwitcherPopupWindow this$0;

            
            {
                this$0 = SwitcherPopupWindow.this;
                super();
            }
        }
);
        ((TextView)getContentView().findViewById(0x7f0a01d4)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                activity.showRooms();
                dismiss();
            }

            final SwitcherPopupWindow this$0;

            
            {
                this$0 = SwitcherPopupWindow.this;
                super();
            }
        }
);
        ((FrameLayout)getContentView().findViewById(0x7f0a01d1)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                dismiss();
            }

            final SwitcherPopupWindow this$0;

            
            {
                this$0 = SwitcherPopupWindow.this;
                super();
            }
        }
);
    }

    protected int dpToPx(int i)
    {
        return (int)(0.5F + activity.getResources().getDisplayMetrics().density * (float)i);
    }

    public void showAsDropDown(View view)
    {
        super.showAsDropDown(view, 0, dpToPx(-40));
    }

    protected SonosActivity activity;
}

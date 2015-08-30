// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.*;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.uibusymanager.BusyUIProgressBar;

// Referenced classes of package com.sonos.acr.view:
//            SonosImageView, SonosButton, SonosViewFlipper

public class HeaderBarView extends RelativeLayout
    implements SonosViewFlipper.FlipAnimationListener
{

    public HeaderBarView(Context context)
    {
        this(context, null);
    }

    public HeaderBarView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f010041);
    }

    public HeaderBarView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        LayoutInflater.from(context).inflate(getLayoutId(), this, true);
        titleViewFlipper = (TextSwitcher)findViewById(0x7f0a009a);
        titleViewFlipper.setMeasureAllChildren(true);
        headerBarLeftButton = (SonosImageView)findViewById(0x7f0a0098);
        headerBarRightButton = (SonosImageView)findViewById(0x7f0a0099);
        titleViewFlipper.setAnimateFirstView(false);
        busyIndicator = (BusyUIProgressBar)findViewById(0x7f0a0035);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.HeaderBarView);
        titleViewFlipper.setCurrentText(typedarray.getString(1));
        typedarray.recycle();
        setClickable(true);
    }

    public void clearButton()
    {
    }

    public void clearText()
    {
        titleViewFlipper.reset();
        titleViewFlipper.setText("");
    }

    public SonosButton getButton()
    {
        return null;
    }

    protected int getLayoutId()
    {
        return 0x7f030023;
    }

    public SonosImageView getLeftButton()
    {
        return headerBarLeftButton;
    }

    public SonosImageView getRightButton()
    {
        return headerBarRightButton;
    }

    public String getText()
    {
        return ((TextView)titleViewFlipper.getCurrentView()).getText().toString();
    }

    protected void initHeaderBarButton(SonosImageView sonosimageview, int i, int j, android.view.View.OnClickListener onclicklistener)
    {
        sonosimageview.setOnClickListener(onclicklistener);
        sonosimageview.setImageResource(i);
        sonosimageview.setContentDescription(getResources().getString(j));
        sonosimageview.setVisibility(0);
        sonosimageview.setBackgroundColor(getResources().getColor(0x7f08001d));
    }

    public boolean isBusyIndicatorEnabled()
    {
        return busyIndicator.isBusyIndicatorEnabled();
    }

    public void onAnimationEnd(SonosViewFlipper sonosviewflipper, Animation animation, View view, View view1)
    {
    }

    public void onAnimationStart(SonosViewFlipper sonosviewflipper, Animation animation, View view, View view1)
    {
    }

    public void setBusyIndicatorEnabled(boolean flag)
    {
    }

    public void setButton(int i, android.view.View.OnClickListener onclicklistener)
    {
        setButton(getResources().getString(i), onclicklistener);
    }

    public void setButton(String s, android.view.View.OnClickListener onclicklistener)
    {
    }

    public void setLeftButton(int i, int j, android.view.View.OnClickListener onclicklistener)
    {
        initHeaderBarButton(headerBarLeftButton, i, j, onclicklistener);
    }

    public void setLeftButtonVisibility(int i)
    {
        if(headerBarLeftButton != null)
            headerBarLeftButton.setVisibility(i);
    }

    public void setRightButton(int i, int j, android.view.View.OnClickListener onclicklistener)
    {
        initHeaderBarButton(headerBarRightButton, i, j, onclicklistener);
    }

    public void setText(int i)
    {
        setText(((CharSequence) (getResources().getString(i))));
    }

    public void setText(final CharSequence text)
    {
        SonosApplication.getInstance().getHandler().post(new Runnable() {

            public void run()
            {
                String s = getText();
                if(s == null || text == null || !s.equals(text.toString()))
                    titleViewFlipper.setText(text);
            }

            final HeaderBarView this$0;
            final CharSequence val$text;

            
            {
                this$0 = HeaderBarView.this;
                text = charsequence;
                super();
            }
        }
);
    }

    private static final String LOG_TAG = com/sonos/acr/view/HeaderBarView.getSimpleName();
    protected BusyUIProgressBar busyIndicator;
    protected SonosImageView headerBarLeftButton;
    protected SonosImageView headerBarRightButton;
    protected android.view.View.OnClickListener mButtonListener;
    public TextSwitcher titleViewFlipper;

}

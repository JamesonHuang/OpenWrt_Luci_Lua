// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.*;
import android.widget.FrameLayout;
import com.sonos.acr.nowplaying.SonosHomeActivity;
import com.sonos.acr.util.SonosTimer;

public class PopOverWindow extends FrameLayout
{
    public static final class Alignment extends Enum
    {

        public static Alignment valueOf(String s)
        {
            return (Alignment)Enum.valueOf(com/sonos/acr/view/PopOverWindow$Alignment, s);
        }

        public static Alignment[] values()
        {
            return (Alignment[])$VALUES.clone();
        }

        private static final Alignment $VALUES[];
        public static final Alignment BELOW;
        public static final Alignment BELOW_RIGHT;

        static 
        {
            BELOW = new Alignment("BELOW", 0);
            BELOW_RIGHT = new Alignment("BELOW_RIGHT", 1);
            Alignment aalignment[] = new Alignment[2];
            aalignment[0] = BELOW;
            aalignment[1] = BELOW_RIGHT;
            $VALUES = aalignment;
        }

        private Alignment(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface PopOverListener
    {

        public abstract void onDismiss(PopOverWindow popoverwindow);

        public abstract void onShow(PopOverWindow popoverwindow);
    }


    public PopOverWindow(Context context)
    {
        super(context);
        timeout = -1L;
        int ai[] = new int[2];
        ai[0] = 0;
        ai[1] = 0;
        locationInWindow = ai;
        windowManager = (WindowManager)context.getSystemService("window");
        setFocusableInTouchMode(true);
    }

    protected void cancelTimeout()
    {
        timeoutDismiss.cancel();
    }

    public void dismiss()
    {
        onDismiss();
        timeoutDismiss.cancel();
        if(popOverListener != null)
            popOverListener.onDismiss(this);
        if(getParent() != null)
            windowManager.removeView(this);
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        resetTimeout();
        Rect rect = new Rect();
        if(contentView != null)
            contentView.getHitRect(rect);
        boolean flag;
        if(rect.contains((int)motionevent.getX(), (int)motionevent.getY()))
            flag = super.dispatchTouchEvent(motionevent);
        else
        if(onTouchOutside(motionevent) || super.dispatchTouchEvent(motionevent))
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected android.view.WindowManager.LayoutParams generateLayoutParams()
    {
        int ai[];
        android.view.WindowManager.LayoutParams layoutparams;
        ai = new int[2];
        ai[0] = 0;
        ai[1] = 0;
        targetView.getLocationInWindow(ai);
        measure(-2, -2);
        layoutparams = new android.view.WindowManager.LayoutParams();
        layoutparams.gravity = 179;
        class _cls2
        {

            static final int $SwitchMap$com$sonos$acr$view$PopOverWindow$Alignment[];

            static 
            {
                $SwitchMap$com$sonos$acr$view$PopOverWindow$Alignment = new int[Alignment.values().length];
                NoSuchFieldError nosuchfielderror1;
                try
                {
                    $SwitchMap$com$sonos$acr$view$PopOverWindow$Alignment[Alignment.BELOW_RIGHT.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                $SwitchMap$com$sonos$acr$view$PopOverWindow$Alignment[Alignment.BELOW.ordinal()] = 2;
_L2:
                return;
                nosuchfielderror1;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls2..SwitchMap.com.sonos.acr.view.PopOverWindow.Alignment[alignment.ordinal()];
        JVM INSTR tableswitch 1 1: default 72
    //                   1 161;
           goto _L1 _L2
_L1:
        layoutparams.x = (ai[0] + targetView.getWidth() / 2) - getMeasuredWidth() / 2;
        layoutparams.y = (ai[1] + targetView.getHeight()) - (int)Math.ceil(beakHeight);
_L4:
        layoutparams.height = -2;
        layoutparams.width = -2;
        layoutparams.flags = 128;
        layoutparams.type = 1000;
        layoutparams.windowAnimations = 0x1030004;
        layoutparams.format = -3;
        return layoutparams;
_L2:
        layoutparams.x = (ai[0] + targetView.getWidth()) - getMeasuredWidth();
        layoutparams.y = (ai[1] + targetView.getHeight()) - (int)Math.ceil(beakHeight);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean isShowing()
    {
        boolean flag;
        if(getParent() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        getLocationInWindow(locationInWindow);
        resetTimeout();
    }

    protected void onDismiss()
    {
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4 && keyevent.getAction() == 0)
        {
            dismiss();
            flag = true;
        } else
        if(getContext() instanceof SonosHomeActivity)
            flag = ((SonosHomeActivity)getContext()).onKeyDown(i, keyevent);
        else
            flag = super.onKeyDown(i, keyevent);
        return flag;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(getContext() instanceof SonosHomeActivity)
            flag = ((SonosHomeActivity)getContext()).onKeyUp(i, keyevent);
        else
            flag = super.onKeyUp(i, keyevent);
        return flag;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(flag)
            setLayoutParams(generateLayoutParams());
    }

    protected void onShow()
    {
    }

    public boolean onTouchOutside(MotionEvent motionevent)
    {
        return false;
    }

    protected void resetTimeout()
    {
        cancelTimeout();
        if(isShowing() && timeout > 0L)
            timeoutDismiss.start(timeout);
    }

    public void setContentView(View view)
    {
        contentView = view;
        removeAllViews();
        addView(view);
    }

    public void setPopOverListener(PopOverListener popoverlistener)
    {
        popOverListener = popoverlistener;
    }

    public void setTimeout(long l)
    {
        timeout = l;
        resetTimeout();
    }

    public void show(View view)
    {
        show(view, Alignment.BELOW);
    }

    public void show(View view, Alignment alignment1)
    {
        onShow();
        targetView = view;
        alignment = alignment1;
        if(alignment1 == Alignment.BELOW_RIGHT)
            setBackgroundResource(0x7f0200a7);
        else
            setBackgroundResource(0x7f0200a6);
        if(popOverListener != null)
            popOverListener.onShow(this);
        try
        {
            windowManager.addView(this, generateLayoutParams());
        }
        catch(IllegalStateException illegalstateexception) { }
        requestFocus();
    }

    public static final String LOG_TAG = com/sonos/acr/view/PopOverWindow.getSimpleName();
    protected Alignment alignment;
    final float beakHeight = getContext().getResources().getDimension(0x7f09000a);
    protected View contentView;
    public final int locationInWindow[];
    PopOverListener popOverListener;
    protected View targetView;
    long timeout;
    final SonosTimer timeoutDismiss = new SonosTimer() {

        public void onTimerFired()
        {
            dismiss();
        }

        final PopOverWindow this$0;

            
            {
                this$0 = PopOverWindow.this;
                super();
            }
    }
;
    protected WindowManager windowManager;

}

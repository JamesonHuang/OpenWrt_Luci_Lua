// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

// Referenced classes of package com.sonos.acr.view:
//            DetachCatchViewFlipper

public class SonosViewFlipper extends DetachCatchViewFlipper
    implements android.view.animation.Animation.AnimationListener
{
    public static interface FlipAnimationListener
    {

        public abstract void onAnimationEnd(SonosViewFlipper sonosviewflipper, Animation animation, View view, View view1);

        public abstract void onAnimationStart(SonosViewFlipper sonosviewflipper, Animation animation, View view, View view1);
    }


    public SonosViewFlipper(Context context)
    {
        super(context);
        m_animListener = null;
        m_animListenerHolder = null;
        m_animationCount = 0;
        m_currentView = null;
        m_previousView = null;
    }

    public SonosViewFlipper(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        m_animListener = null;
        m_animListenerHolder = null;
        m_animationCount = 0;
        m_currentView = null;
        m_previousView = null;
    }

    public FlipAnimationListener getFlipAnimationListener()
    {
        FlipAnimationListener flipanimationlistener;
        if(m_animListenerHolder != null)
            flipanimationlistener = m_animListenerHolder;
        else
            flipanimationlistener = m_animListener;
        return flipanimationlistener;
    }

    public void onAnimationEnd(Animation animation)
    {
        if(m_animationCount > 0)
        {
            int i = -1 + m_animationCount;
            m_animationCount = i;
            if(i == 0 && m_animListener != null)
                m_animListener.onAnimationEnd(this, animation, m_currentView, m_previousView);
        }
    }

    public void onAnimationRepeat(Animation animation)
    {
    }

    public void onAnimationStart(Animation animation)
    {
        if(m_animListener != null && m_animationCount == 0)
            m_animListener.onAnimationStart(this, animation, m_currentView, m_previousView);
        m_animationCount = 1 + m_animationCount;
    }

    public void setFlipAnimationListener(FlipAnimationListener flipanimationlistener)
    {
        if(isFlipping())
            m_animListenerHolder = flipanimationlistener;
        else
            m_animListener = flipanimationlistener;
    }

    public void setInAnimation(Animation animation)
    {
        if(getInAnimation() != null)
            getInAnimation().setAnimationListener(null);
        super.setInAnimation(animation);
        if(animation != null)
            animation.setAnimationListener(this);
    }

    public void setOutAnimation(Animation animation)
    {
        if(getOutAnimation() != null)
            getOutAnimation().setAnimationListener(null);
        super.setOutAnimation(animation);
        if(animation != null)
            animation.setAnimationListener(this);
    }

    public void showNext()
    {
        m_previousView = getCurrentView();
        super.showNext();
        m_currentView = getCurrentView();
    }

    public void showPrevious()
    {
        m_currentView = getCurrentView();
        super.showPrevious();
        m_previousView = getCurrentView();
    }

    protected FlipAnimationListener m_animListener;
    protected FlipAnimationListener m_animListenerHolder;
    protected int m_animationCount;
    protected View m_currentView;
    protected View m_previousView;
}

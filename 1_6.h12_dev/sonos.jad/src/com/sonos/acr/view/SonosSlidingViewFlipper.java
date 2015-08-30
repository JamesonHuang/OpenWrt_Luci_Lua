// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

// Referenced classes of package com.sonos.acr.view:
//            SonosViewFlipper

public class SonosSlidingViewFlipper extends SonosViewFlipper
{

    public SonosSlidingViewFlipper(Context context)
    {
        super(context);
        m_nextInAnimation = AnimationUtils.loadAnimation(context, 0x7f040013);
        m_nextOutAnimation = AnimationUtils.loadAnimation(context, 0x7f040014);
        m_prevInAnimation = AnimationUtils.loadAnimation(context, 0x7f040012);
        m_prevOutAnimation = AnimationUtils.loadAnimation(context, 0x7f040015);
    }

    public SonosSlidingViewFlipper(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        m_nextInAnimation = AnimationUtils.loadAnimation(context, 0x7f040013);
        m_nextOutAnimation = AnimationUtils.loadAnimation(context, 0x7f040014);
        m_prevInAnimation = AnimationUtils.loadAnimation(context, 0x7f040012);
        m_prevOutAnimation = AnimationUtils.loadAnimation(context, 0x7f040015);
    }

    public void showNext()
    {
        setInAnimation(m_nextInAnimation);
        setOutAnimation(m_nextOutAnimation);
        super.showNext();
    }

    public void showPrevious()
    {
        setInAnimation(m_prevInAnimation);
        setOutAnimation(m_prevOutAnimation);
        super.showPrevious();
    }

    private Animation m_nextInAnimation;
    private Animation m_nextOutAnimation;
    private Animation m_prevInAnimation;
    private Animation m_prevOutAnimation;
}

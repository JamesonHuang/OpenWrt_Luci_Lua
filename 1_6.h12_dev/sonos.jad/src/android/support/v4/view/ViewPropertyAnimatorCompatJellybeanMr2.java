// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

class ViewPropertyAnimatorCompatJellybeanMr2
{

    ViewPropertyAnimatorCompatJellybeanMr2()
    {
    }

    public static Interpolator getInterpolator(View view)
    {
        return (Interpolator)view.animate().getInterpolator();
    }
}

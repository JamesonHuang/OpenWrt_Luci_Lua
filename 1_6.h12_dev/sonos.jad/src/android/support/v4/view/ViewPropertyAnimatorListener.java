// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.View;

public interface ViewPropertyAnimatorListener
{

    public abstract void onAnimationCancel(View view);

    public abstract void onAnimationEnd(View view);

    public abstract void onAnimationStart(View view);
}

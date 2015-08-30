// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.support.v4.view.ViewPager;

public interface PageIndicator
    extends android.support.v4.view.ViewPager.OnPageChangeListener
{

    public abstract void notifyDataSetChanged();

    public abstract void setCurrentItem(int i);

    public abstract void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener);

    public abstract void setViewPager(ViewPager viewpager);

    public abstract void setViewPager(ViewPager viewpager, int i);
}

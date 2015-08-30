// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import com.sonos.acr.util.WeakHashSet;
import java.util.Iterator;

public class SonosViewPager extends ViewPager
{
    static interface OnAdapterChangeListener
    {

        public abstract void onAdapterChanged(PagerAdapter pageradapter, PagerAdapter pageradapter1);
    }


    public SonosViewPager(Context context)
    {
        super(context);
        pageChangeListeners = new WeakHashSet();
        internalListener = new android.support.v4.view.ViewPager.OnPageChangeListener() {

            public void onPageScrollStateChanged(int i)
            {
                for(Iterator iterator = pageChangeListeners.iterator(); iterator.hasNext(); ((android.support.v4.view.ViewPager.OnPageChangeListener)iterator.next()).onPageScrollStateChanged(i));
            }

            public void onPageScrolled(int i, float f, int j)
            {
                for(Iterator iterator = pageChangeListeners.iterator(); iterator.hasNext(); ((android.support.v4.view.ViewPager.OnPageChangeListener)iterator.next()).onPageScrolled(i, f, j));
            }

            public void onPageSelected(int i)
            {
                for(Iterator iterator = pageChangeListeners.iterator(); iterator.hasNext(); ((android.support.v4.view.ViewPager.OnPageChangeListener)iterator.next()).onPageSelected(i));
            }

            final SonosViewPager this$0;

            
            {
                this$0 = SonosViewPager.this;
                super();
            }
        }
;
    }

    public SonosViewPager(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        pageChangeListeners = new WeakHashSet();
        internalListener = new _cls1();
        setOnPageChangeListener(internalListener);
    }

    public void addOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        pageChangeListeners.add(onpagechangelistener);
    }

    public void removeOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        pageChangeListeners.remove(onpagechangelistener);
    }

    public void setAdapter(PagerAdapter pageradapter)
    {
        PagerAdapter pageradapter1 = getAdapter();
        super.setAdapter(pageradapter);
        if(onAdapterChangeListener != null && pageradapter1 != pageradapter)
            onAdapterChangeListener.onAdapterChanged(pageradapter1, pageradapter);
    }

    public void setOnAdapterChangeListener(OnAdapterChangeListener onadapterchangelistener)
    {
        onAdapterChangeListener = onadapterchangelistener;
    }

    public void setOnPageChangeListener(android.support.v4.view.ViewPager.OnPageChangeListener onpagechangelistener)
    {
        if(onpagechangelistener != internalListener)
        {
            throw new RuntimeException("Illegal call with SonosViewPager, please use AddOnPageChangeListener instead");
        } else
        {
            super.setOnPageChangeListener(onpagechangelistener);
            return;
        }
    }

    private final android.support.v4.view.ViewPager.OnPageChangeListener internalListener;
    protected OnAdapterChangeListener onAdapterChangeListener;
    private final WeakHashSet pageChangeListeners;

}

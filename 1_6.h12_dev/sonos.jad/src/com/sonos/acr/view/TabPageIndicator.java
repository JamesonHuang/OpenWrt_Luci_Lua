// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.*;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.sonos.acr.util.SLog;
import java.lang.ref.WeakReference;

// Referenced classes of package com.sonos.acr.view:
//            DividerLinearLayout, SonosViewPager, SonosTextView

public class TabPageIndicator extends HorizontalScrollView
{
    private class TabView extends SonosTextView
    {

        public int getIndex()
        {
            return mIndex;
        }

        private int mIndex;
        final TabPageIndicator this$0;


/*
        static int access$202(TabView tabview, int i)
        {
            tabview.mIndex = i;
            return i;
        }

*/

        public TabView(Context context)
        {
            this$0 = TabPageIndicator.this;
            super(context, null, 0x7f01005b);
            setEllipsize(null);
        }
    }

    private class PageListener extends DataSetObserver
        implements android.support.v4.view.ViewPager.OnPageChangeListener, SonosViewPager.OnAdapterChangeListener
    {

        public void onAdapterChanged(PagerAdapter pageradapter, PagerAdapter pageradapter1)
        {
            updateAdapter(pageradapter, pageradapter1);
        }

        public void onChanged()
        {
            SLog.e(TabPageIndicator.LOG_TAG, "Adapter Changed!");
            int i = viewPager.getCurrentItem();
            updateText(i, viewPager.getAdapter());
            float f;
            if(lastKnownPositionOffset >= 0.0F)
                f = lastKnownPositionOffset;
            else
                f = 0.0F;
            updateScrollPosition(i, 0.0F, true);
            updateIndicatorPosition(i, f);
        }

        public void onPageScrollStateChanged(int i)
        {
            pagerScrollState = i;
        }

        public void onPageScrolled(int i, float f, int j)
        {
            lastKnownPositionOffset = f;
            updateScrollPosition(i, f, false);
            updateIndicatorPosition(i, f);
        }

        public void onPageSelected(int i)
        {
            float f = 0.0F;
            setCurrentItem(i);
            if(pagerScrollState == 0)
            {
                if(lastKnownPositionOffset >= 0.0F)
                    f = lastKnownPositionOffset;
                updateScrollPosition(viewPager.getCurrentItem(), f, true);
            }
        }

        private int pagerScrollState;
        final TabPageIndicator this$0;

        private PageListener()
        {
            this$0 = TabPageIndicator.this;
            super();
            pagerScrollState = 0;
        }

    }


    public TabPageIndicator(Context context)
    {
        this(context, null);
    }

    public TabPageIndicator(Context context, AttributeSet attributeset)
    {
        super(context, attributeset, 0);
        pageListener = new PageListener();
        indicatorHeight = 10F;
        currentPageIndex = -1;
        lastKnownPositionOffset = -1F;
        tabClickListener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                TabView tabview = (TabView)view;
                viewPager.getCurrentItem();
                int i = tabview.getIndex();
                viewPager.setCurrentItem(i);
            }

            final TabPageIndicator this$0;

            
            {
                this$0 = TabPageIndicator.this;
                super();
            }
        }
;
        paint = new Paint();
        indicatorRect = new RectF();
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.TabPageIndicator);
        indicatorColor = typedarray.getColor(2, 0xff0000ff);
        indicatorHeight = typedarray.getDimension(1, context.getResources().getDimension(0x7f090068));
        indicatorOffset = typedarray.getDimension(0, context.getResources().getDimension(0x7f090069));
        setFillViewport(true);
        setEnabled(true);
        typedarray.recycle();
        setHorizontalScrollBarEnabled(false);
        tabLayout = new DividerLinearLayout(context, null, 0x7f01005b);
        tabLayout.setPadding(0, 0, 0, 0);
        addView(tabLayout, new android.view.ViewGroup.LayoutParams(-2, -2));
    }

    private void addTab(int i, CharSequence charsequence, int j)
    {
        TabView tabview = new TabView(getContext());
        tabview.mIndex = i;
        tabview.setFocusable(true);
        tabview.setOnClickListener(tabClickListener);
        tabview.setText(charsequence);
        if(j != 0)
            tabview.setCompoundDrawablesWithIntrinsicBounds(j, 0, 0, 0);
        tabLayout.addView(tabview, new android.widget.LinearLayout.LayoutParams(-2, -1, 1.0F));
    }

    private void updateIndicatorPosition(int i, float f)
    {
        if(i < tabLayout.getChildCount())
        {
            View view = tabLayout.getChildAt(i);
            View view1 = tabLayout.getChildAt(i + 1);
            float f1 = view.getWidth();
            float f2;
            float f3;
            float f4;
            int j;
            float f5;
            int k;
            if(view1 != null)
                f2 = view1.getWidth();
            else
                f2 = f1;
            f3 = f1 + f * (f2 - f1);
            f4 = 0 + view.getLeft();
            if(view1 != null)
                j = view1.getLeft();
            else
                j = tabLayout.getWidth();
            f5 = f4 + f * ((float)(j + 0) - f4);
            k = view.getBottom();
            indicatorRect.set(f5, (float)k - indicatorHeight, f5 + f3, k);
            invalidate();
        }
    }

    private void updateScrollPosition(int i, float f, boolean flag)
    {
        if(i < tabLayout.getChildCount())
        {
            View view = tabLayout.getChildAt(i);
            View view1 = tabLayout.getChildAt(i + 1);
            if(tabLayout.getWidth() > getWidth())
            {
                float f1 = view.getLeft();
                int j;
                if(view1 != null)
                    j = view1.getLeft();
                else
                    j = tabLayout.getWidth();
                scrollTo((int)((f1 + f * ((float)j - f1)) - indicatorOffset), getScrollY());
            }
            invalidate();
        }
    }

    private void updateText(int i, PagerAdapter pageradapter)
    {
        tabLayout.removeAllViews();
        int j;
        int k;
        if(pageradapter != null)
            j = pageradapter.getCount();
        else
            j = 0;
        for(k = 0; k < j; k++)
        {
            CharSequence charsequence = pageradapter.getPageTitle(k);
            if(charsequence == null)
                charsequence = EMPTY_TITLE;
            addTab(k, charsequence, 0);
        }

        if(i > j)
            i = j - 1;
        setCurrentItem(i);
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        paint.setColor(indicatorColor);
        canvas.drawRect(indicatorRect, paint);
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(tabSelector != null)
            removeCallbacks(tabSelector);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        updateIndicatorPosition(viewPager.getCurrentItem(), 0.0F);
        if(flag)
            updateScrollPosition(currentPageIndex, 0.0F, false);
    }

    public void onMeasure(int i, int j)
    {
        boolean flag;
        int k;
        int l;
        if(android.view.View.MeasureSpec.getMode(i) == 0x40000000)
            flag = true;
        else
            flag = false;
        setFillViewport(flag);
        k = getMeasuredWidth();
        super.onMeasure(i, j);
        l = getMeasuredWidth();
        if(flag && k != l)
            setCurrentItem(currentPageIndex);
    }

    public void setCurrentItem(int i)
    {
        if(viewPager == null)
            throw new IllegalStateException("ViewPager has not been bound.");
        currentPageIndex = i;
        int j = tabLayout.getChildCount();
        int k = 0;
        while(k < j) 
        {
            View view = tabLayout.getChildAt(k);
            boolean flag;
            if(k == i)
                flag = true;
            else
                flag = false;
            view.setSelected(flag);
            k++;
        }
    }

    public void setPager(SonosViewPager sonosviewpager)
    {
        viewPager = sonosviewpager;
        PagerAdapter pageradapter = sonosviewpager.getAdapter();
        viewPager.addOnPageChangeListener(pageListener);
        viewPager.setOnAdapterChangeListener(pageListener);
        PagerAdapter pageradapter1;
        if(currentAdapter != null)
            pageradapter1 = (PagerAdapter)currentAdapter.get();
        else
            pageradapter1 = null;
        updateAdapter(pageradapter1, pageradapter);
    }

    void updateAdapter(PagerAdapter pageradapter, PagerAdapter pageradapter1)
    {
        if(pageradapter != null)
        {
            pageradapter.unregisterDataSetObserver(pageListener);
            currentAdapter = null;
        }
        if(pageradapter1 != null)
        {
            pageradapter1.registerDataSetObserver(pageListener);
            currentAdapter = new WeakReference(pageradapter1);
        }
        if(viewPager != null)
        {
            currentPageIndex = -1;
            lastKnownPositionOffset = -1F;
            updateText(viewPager.getCurrentItem(), pageradapter1);
            requestLayout();
        }
    }

    private static final CharSequence EMPTY_TITLE = "";
    private static final String LOG_TAG = com/sonos/acr/view/TabPageIndicator.getSimpleName();
    private WeakReference currentAdapter;
    private int currentPageIndex;
    private int indicatorColor;
    private float indicatorHeight;
    private float indicatorOffset;
    private RectF indicatorRect;
    private float lastKnownPositionOffset;
    private final PageListener pageListener;
    private Paint paint;
    private final android.view.View.OnClickListener tabClickListener;
    private final LinearLayout tabLayout;
    private Runnable tabSelector;
    private SonosViewPager viewPager;





/*
    static float access$302(TabPageIndicator tabpageindicator, float f)
    {
        tabpageindicator.lastKnownPositionOffset = f;
        return f;
    }

*/




}

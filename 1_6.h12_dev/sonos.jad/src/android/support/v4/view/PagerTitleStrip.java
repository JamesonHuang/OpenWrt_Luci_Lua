// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.content.Context;
import android.content.res.*;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.TextView;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v4.view:
//            ViewPager, PagerAdapter, PagerTitleStripIcs

public class PagerTitleStrip extends ViewGroup
    implements ViewPager.Decor
{
    private class PageListener extends DataSetObserver
        implements ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener
    {

        public void onAdapterChanged(PagerAdapter pageradapter, PagerAdapter pageradapter1)
        {
            updateAdapter(pageradapter, pageradapter1);
        }

        public void onChanged()
        {
            float f = 0.0F;
            updateText(mPager.getCurrentItem(), mPager.getAdapter());
            if(mLastKnownPositionOffset >= 0.0F)
                f = mLastKnownPositionOffset;
            updateTextPositions(mPager.getCurrentItem(), f, true);
        }

        public void onPageScrollStateChanged(int i)
        {
            mScrollState = i;
        }

        public void onPageScrolled(int i, float f, int j)
        {
            if(f > 0.5F)
                i++;
            updateTextPositions(i, f, false);
        }

        public void onPageSelected(int i)
        {
            float f = 0.0F;
            if(mScrollState == 0)
            {
                updateText(mPager.getCurrentItem(), mPager.getAdapter());
                if(mLastKnownPositionOffset >= 0.0F)
                    f = mLastKnownPositionOffset;
                updateTextPositions(mPager.getCurrentItem(), f, true);
            }
        }

        private int mScrollState;
        final PagerTitleStrip this$0;

        private PageListener()
        {
            this$0 = PagerTitleStrip.this;
            super();
        }

    }

    static class PagerTitleStripImplIcs
        implements PagerTitleStripImpl
    {

        public void setSingleLineAllCaps(TextView textview)
        {
            PagerTitleStripIcs.setSingleLineAllCaps(textview);
        }

        PagerTitleStripImplIcs()
        {
        }
    }

    static class PagerTitleStripImplBase
        implements PagerTitleStripImpl
    {

        public void setSingleLineAllCaps(TextView textview)
        {
            textview.setSingleLine();
        }

        PagerTitleStripImplBase()
        {
        }
    }

    static interface PagerTitleStripImpl
    {

        public abstract void setSingleLineAllCaps(TextView textview);
    }


    public PagerTitleStrip(Context context)
    {
        this(context, null);
    }

    public PagerTitleStrip(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mLastKnownCurrentPage = -1;
        mLastKnownPositionOffset = -1F;
        mPageListener = new PageListener();
        TextView textview = new TextView(context);
        mPrevText = textview;
        addView(textview);
        TextView textview1 = new TextView(context);
        mCurrText = textview1;
        addView(textview1);
        TextView textview2 = new TextView(context);
        mNextText = textview2;
        addView(textview2);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, ATTRS);
        int i = typedarray.getResourceId(0, 0);
        if(i != 0)
        {
            mPrevText.setTextAppearance(context, i);
            mCurrText.setTextAppearance(context, i);
            mNextText.setTextAppearance(context, i);
        }
        int j = typedarray.getDimensionPixelSize(1, 0);
        if(j != 0)
            setTextSize(0, j);
        if(typedarray.hasValue(2))
        {
            int k = typedarray.getColor(2, 0);
            mPrevText.setTextColor(k);
            mCurrText.setTextColor(k);
            mNextText.setTextColor(k);
        }
        mGravity = typedarray.getInteger(3, 80);
        typedarray.recycle();
        mTextColor = mCurrText.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6F);
        mPrevText.setEllipsize(android.text.TextUtils.TruncateAt.END);
        mCurrText.setEllipsize(android.text.TextUtils.TruncateAt.END);
        mNextText.setEllipsize(android.text.TextUtils.TruncateAt.END);
        boolean flag = false;
        if(i != 0)
        {
            TypedArray typedarray1 = context.obtainStyledAttributes(i, TEXT_ATTRS);
            flag = typedarray1.getBoolean(0, false);
            typedarray1.recycle();
        }
        if(flag)
        {
            setSingleLineAllCaps(mPrevText);
            setSingleLineAllCaps(mCurrText);
            setSingleLineAllCaps(mNextText);
        } else
        {
            mPrevText.setSingleLine();
            mCurrText.setSingleLine();
            mNextText.setSingleLine();
        }
        mScaledTextSpacing = (int)(16F * context.getResources().getDisplayMetrics().density);
    }

    private static void setSingleLineAllCaps(TextView textview)
    {
        IMPL.setSingleLineAllCaps(textview);
    }

    int getMinHeight()
    {
        int i = 0;
        Drawable drawable = getBackground();
        if(drawable != null)
            i = drawable.getIntrinsicHeight();
        return i;
    }

    public int getTextSpacing()
    {
        return mScaledTextSpacing;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        android.view.ViewParent viewparent = getParent();
        if(!(viewparent instanceof ViewPager))
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        ViewPager viewpager = (ViewPager)viewparent;
        PagerAdapter pageradapter = viewpager.getAdapter();
        viewpager.setInternalPageChangeListener(mPageListener);
        viewpager.setOnAdapterChangeListener(mPageListener);
        mPager = viewpager;
        PagerAdapter pageradapter1;
        if(mWatchingAdapter != null)
            pageradapter1 = (PagerAdapter)mWatchingAdapter.get();
        else
            pageradapter1 = null;
        updateAdapter(pageradapter1, pageradapter);
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mPager != null)
        {
            updateAdapter(mPager.getAdapter(), null);
            mPager.setInternalPageChangeListener(null);
            mPager.setOnAdapterChangeListener(null);
            mPager = null;
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        float f = 0.0F;
        if(mPager != null)
        {
            if(mLastKnownPositionOffset >= 0.0F)
                f = mLastKnownPositionOffset;
            updateTextPositions(mLastKnownCurrentPage, f, true);
        }
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(i);
        int l = android.view.View.MeasureSpec.getMode(j);
        int i1 = android.view.View.MeasureSpec.getSize(i);
        int j1 = android.view.View.MeasureSpec.getSize(j);
        if(k != 0x40000000)
            throw new IllegalStateException("Must measure with an exact width");
        int k1 = getMinHeight();
        int l1 = getPaddingTop() + getPaddingBottom();
        int i2 = j1 - l1;
        int j2 = android.view.View.MeasureSpec.makeMeasureSpec((int)(0.8F * (float)i1), 0x80000000);
        int k2 = android.view.View.MeasureSpec.makeMeasureSpec(i2, 0x80000000);
        mPrevText.measure(j2, k2);
        mCurrText.measure(j2, k2);
        mNextText.measure(j2, k2);
        if(l == 0x40000000)
            setMeasuredDimension(i1, j1);
        else
            setMeasuredDimension(i1, Math.max(k1, l1 + mCurrText.getMeasuredHeight()));
    }

    public void requestLayout()
    {
        if(!mUpdatingText)
            super.requestLayout();
    }

    public void setGravity(int i)
    {
        mGravity = i;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float f)
    {
        mNonPrimaryAlpha = 0xff & (int)(255F * f);
        int i = mNonPrimaryAlpha << 24 | 0xffffff & mTextColor;
        mPrevText.setTextColor(i);
        mNextText.setTextColor(i);
    }

    public void setTextColor(int i)
    {
        mTextColor = i;
        mCurrText.setTextColor(i);
        int j = mNonPrimaryAlpha << 24 | 0xffffff & mTextColor;
        mPrevText.setTextColor(j);
        mNextText.setTextColor(j);
    }

    public void setTextSize(int i, float f)
    {
        mPrevText.setTextSize(i, f);
        mCurrText.setTextSize(i, f);
        mNextText.setTextSize(i, f);
    }

    public void setTextSpacing(int i)
    {
        mScaledTextSpacing = i;
        requestLayout();
    }

    void updateAdapter(PagerAdapter pageradapter, PagerAdapter pageradapter1)
    {
        if(pageradapter != null)
        {
            pageradapter.unregisterDataSetObserver(mPageListener);
            mWatchingAdapter = null;
        }
        if(pageradapter1 != null)
        {
            pageradapter1.registerDataSetObserver(mPageListener);
            mWatchingAdapter = new WeakReference(pageradapter1);
        }
        if(mPager != null)
        {
            mLastKnownCurrentPage = -1;
            mLastKnownPositionOffset = -1F;
            updateText(mPager.getCurrentItem(), pageradapter1);
            requestLayout();
        }
    }

    void updateText(int i, PagerAdapter pageradapter)
    {
        int j;
        CharSequence charsequence;
        TextView textview;
        CharSequence charsequence1;
        CharSequence charsequence2;
        int k;
        int l;
        int i1;
        int j1;
        if(pageradapter != null)
            j = pageradapter.getCount();
        else
            j = 0;
        mUpdatingText = true;
        charsequence = null;
        if(i >= 1 && pageradapter != null)
            charsequence = pageradapter.getPageTitle(i - 1);
        mPrevText.setText(charsequence);
        textview = mCurrText;
        if(pageradapter != null && i < j)
            charsequence1 = pageradapter.getPageTitle(i);
        else
            charsequence1 = null;
        textview.setText(charsequence1);
        charsequence2 = null;
        if(i + 1 < j && pageradapter != null)
            charsequence2 = pageradapter.getPageTitle(i + 1);
        mNextText.setText(charsequence2);
        k = getWidth() - getPaddingLeft() - getPaddingRight();
        l = getHeight() - getPaddingTop() - getPaddingBottom();
        i1 = android.view.View.MeasureSpec.makeMeasureSpec((int)(0.8F * (float)k), 0x80000000);
        j1 = android.view.View.MeasureSpec.makeMeasureSpec(l, 0x80000000);
        mPrevText.measure(i1, j1);
        mCurrText.measure(i1, j1);
        mNextText.measure(i1, j1);
        mLastKnownCurrentPage = i;
        if(!mUpdatingPositions)
            updateTextPositions(i, mLastKnownPositionOffset, false);
        mUpdatingText = false;
    }

    void updateTextPositions(int i, float f, boolean flag)
    {
        if(i == mLastKnownCurrentPage) goto _L2; else goto _L1
_L1:
        updateText(i, mPager.getAdapter());
_L7:
        int j;
        int l;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int k3;
        int l3;
        int i5;
        int j5;
        int k5;
        int k6;
        mUpdatingPositions = true;
        j = mPrevText.getMeasuredWidth();
        int k = mCurrText.getMeasuredWidth();
        l = mNextText.getMeasuredWidth();
        int i1 = k / 2;
        j1 = getWidth();
        k1 = getHeight();
        l1 = getPaddingLeft();
        i2 = getPaddingRight();
        j2 = getPaddingTop();
        k2 = getPaddingBottom();
        int l2 = l1 + i1;
        int i3 = i2 + i1;
        int j3 = j1 - l2 - i3;
        float f1 = f + 0.5F;
        if(f1 > 1.0F)
            f1--;
        k3 = j1 - i3 - (int)(f1 * (float)j3) - k / 2;
        l3 = k3 + k;
        int i4 = mPrevText.getBaseline();
        int j4 = mCurrText.getBaseline();
        int k4 = mNextText.getBaseline();
        int l4 = Math.max(Math.max(i4, j4), k4);
        i5 = l4 - i4;
        j5 = l4 - j4;
        k5 = l4 - k4;
        int l5 = i5 + mPrevText.getMeasuredHeight();
        int i6 = j5 + mCurrText.getMeasuredHeight();
        int j6 = k5 + mNextText.getMeasuredHeight();
        k6 = Math.max(Math.max(l5, i6), j6);
        0x70 & mGravity;
        JVM INSTR lookupswitch 2: default 312
    //                   16: 504
    //                   80: 543;
           goto _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_543;
_L3:
        int i7;
        int j7;
        int k7;
        i7 = j2 + i5;
        j7 = j2 + j5;
        k7 = j2 + k5;
_L8:
        TextView textview = mCurrText;
        int l7 = j7 + mCurrText.getMeasuredHeight();
        textview.layout(k3, j7, l3, l7);
        int i8 = Math.min(l1, k3 - mScaledTextSpacing - j);
        TextView textview1 = mPrevText;
        int j8 = i8 + j;
        int k8 = i7 + mPrevText.getMeasuredHeight();
        textview1.layout(i8, i7, j8, k8);
        int l8 = Math.max(j1 - i2 - l, l3 + mScaledTextSpacing);
        TextView textview2 = mNextText;
        int i9 = l8 + l;
        int j9 = k7 + mNextText.getMeasuredHeight();
        textview2.layout(l8, k7, i9, j9);
        mLastKnownPositionOffset = f;
        mUpdatingPositions = false;
_L6:
        return;
_L2:
        if(flag || f != mLastKnownPositionOffset) goto _L7; else goto _L6
_L4:
        int k9 = (k1 - j2 - k2 - k6) / 2;
        i7 = k9 + i5;
        j7 = k9 + j5;
        k7 = k9 + k5;
          goto _L8
        int l6 = k1 - k2 - k6;
        i7 = l6 + i5;
        j7 = l6 + j5;
        k7 = l6 + k5;
          goto _L8
    }

    private static final int ATTRS[];
    private static final PagerTitleStripImpl IMPL;
    private static final float SIDE_ALPHA = 0.6F;
    private static final String TAG = "PagerTitleStrip";
    private static final int TEXT_ATTRS[];
    private static final int TEXT_SPACING = 16;
    TextView mCurrText;
    private int mGravity;
    private int mLastKnownCurrentPage;
    private float mLastKnownPositionOffset;
    TextView mNextText;
    private int mNonPrimaryAlpha;
    private final PageListener mPageListener;
    ViewPager mPager;
    TextView mPrevText;
    private int mScaledTextSpacing;
    int mTextColor;
    private boolean mUpdatingPositions;
    private boolean mUpdatingText;
    private WeakReference mWatchingAdapter;

    static 
    {
        int ai[] = new int[4];
        ai[0] = 0x1010034;
        ai[1] = 0x1010095;
        ai[2] = 0x1010098;
        ai[3] = 0x10100af;
        ATTRS = ai;
        int ai1[] = new int[1];
        ai1[0] = 0x101038c;
        TEXT_ATTRS = ai1;
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new PagerTitleStripImplIcs();
        else
            IMPL = new PagerTitleStripImplBase();
    }

}

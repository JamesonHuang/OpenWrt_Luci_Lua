// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.sonos.acr.browse.indexers.BrowseSection;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.sclib.SCIBrowseItem;
import java.util.*;

public class SonosGridLayout extends ViewGroup
{
    public static interface SonosGridAdapter
        extends IDataSourceAdapter
    {

        public abstract View getHeaderView(int i, View view, ViewGroup viewgroup);
    }


    public SonosGridLayout(Context context)
    {
        this(context, null);
    }

    public SonosGridLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SonosGridLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mNumColumns = -1;
        mHorizontalSpacing = 0;
        mVerticalSpacing = 0;
        mStretchMode = 2;
        mGravity = 0x800003;
        adapterDataSetObserver = new DataSetObserver() {

            public void onChanged()
            {
                refreshData();
            }

            final SonosGridLayout this$0;

            
            {
                this$0 = SonosGridLayout.this;
                super();
            }
        }
;
        managedViews = new HashMap();
        currentViews = new ArrayList();
        sectionHeaderViews = new ArrayList();
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.sonos.acr.R.styleable.SonosGridLayout, i, 0);
        setHorizontalSpacing(typedarray.getDimensionPixelOffset(0, 0));
        setVerticalSpacing(typedarray.getDimensionPixelOffset(1, 0));
        int j = typedarray.getInt(2, 2);
        if(j >= 0)
            setStretchMode(j);
        int k = typedarray.getDimensionPixelOffset(3, -1);
        if(k > 0)
            setColumnWidth(k);
        setNumColumns(typedarray.getInt(4, 1));
        typedarray.recycle();
    }

    private BrowseSection[] createSections()
    {
        BrowseSection abrowsesection[] = mAdapter.getSections();
        if(abrowsesection != null && abrowsesection.length != 0) goto _L2; else goto _L1
_L1:
        abrowsesection = new BrowseSection[1];
        abrowsesection[0] = new BrowseSection(0, "");
_L4:
        return abrowsesection;
_L2:
        if(abrowsesection[0].getStartPosition() != 0)
        {
            BrowseSection abrowsesection1[] = new BrowseSection[1 + abrowsesection.length];
            abrowsesection1[0] = new BrowseSection(0, "");
            System.arraycopy(abrowsesection, 0, abrowsesection1, 1, abrowsesection.length);
            abrowsesection = abrowsesection1;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean determineColumns(int i)
    {
        int j;
        int l;
        int i1;
        j = mRequestedHorizontalSpacing;
        int k = mStretchMode;
        l = mRequestedColumnWidth;
        boolean flag = false;
        if(mRequestedNumColumns == -1)
        {
            if(l > 0)
                mNumColumns = (i + j) / (l + j);
            else
                mNumColumns = 2;
        } else
        {
            mNumColumns = mRequestedNumColumns;
        }
        if(mNumColumns <= 0)
            mNumColumns = 1;
        k;
        JVM INSTR tableswitch 0 0: default 76
    //                   0 154;
           goto _L1 _L2
_L1:
        i1 = i - l * mNumColumns - j * (-1 + mNumColumns);
        if(i1 < 0)
            flag = true;
        k;
        JVM INSTR tableswitch 1 3: default 132
    //                   1 190
    //                   2 168
    //                   3 234;
           goto _L3 _L4 _L5 _L6
_L3:
        return flag;
_L2:
        mColumnWidth = l;
        mHorizontalSpacing = j;
        continue; /* Loop/switch isn't completed */
_L5:
        mColumnWidth = l + i1 / mNumColumns;
        mHorizontalSpacing = j;
        continue; /* Loop/switch isn't completed */
_L4:
        mColumnWidth = l;
        if(mNumColumns > 1)
            mHorizontalSpacing = j + i1 / (-1 + mNumColumns);
        else
            mHorizontalSpacing = j + i1;
        continue; /* Loop/switch isn't completed */
_L6:
        mColumnWidth = l;
        if(mNumColumns > 1)
            mHorizontalSpacing = j + i1 / (1 + mNumColumns);
        else
            mHorizontalSpacing = j + i1;
        if(true) goto _L3; else goto _L7
_L7:
    }

    private void reconcileViews()
    {
        removeAllViews();
        for(Iterator iterator = currentViews.iterator(); iterator.hasNext(); addView((View)iterator.next()));
        Iterator iterator1 = sectionHeaderViews.iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            View view = (View)iterator1.next();
            if(view != null)
                addView(view);
        } while(true);
        requestLayout();
    }

    private void refreshData()
    {
        ArrayList arraylist = new ArrayList();
        HashMap hashmap = new HashMap();
        sections = createSections();
        sectionHeaderViews.clear();
        for(int i = 0; i < sections.length; i++)
            sectionHeaderViews.add(mAdapter.getHeaderView(i, null, this));

        if(mAdapter != null)
        {
            itemCount = mAdapter.getCount();
            for(int j = 0; j < itemCount; j++)
            {
                String s = ((SCIBrowseItem)mAdapter.getItem(j)).getSCUri();
                View view = (View)managedViews.remove(s);
                View view1 = mAdapter.getView(j, view, this);
                arraylist.add(view1);
                hashmap.put(s, view1);
            }

            managedViews = hashmap;
            currentViews = arraylist;
        }
        reconcileViews();
    }

    public int getColumnWidth()
    {
        return mColumnWidth;
    }

    public int getGravity()
    {
        return mGravity;
    }

    public int getHorizontalSpacing()
    {
        return mHorizontalSpacing;
    }

    public int getNumColumns()
    {
        return mNumColumns;
    }

    public int getRequestedColumnWidth()
    {
        return mRequestedColumnWidth;
    }

    public int getRequestedHorizontalSpacing()
    {
        return mRequestedHorizontalSpacing;
    }

    public int getStretchMode()
    {
        return mStretchMode;
    }

    public int getVerticalSpacing()
    {
        return mVerticalSpacing;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        if(mCellWidth > 0)
        {
            int i1 = getPaddingRight();
            int j1 = i1;
            int k1 = getPaddingTop();
            for(int l1 = 0; l1 < sections.length; l1++)
            {
                int i2;
                int j2;
                int k2;
                View view;
                int l2;
                if(l1 + 1 < sections.length)
                    i2 = sections[l1 + 1].getStartPosition();
                else
                    i2 = itemCount;
                j2 = sections[l1].getStartPosition();
                k2 = 0;
                view = (View)sectionHeaderViews.get(l1);
                if(view != null)
                {
                    k2 = view.getMeasuredHeight();
                    view.layout(0, k1, getWidth(), k1 + k2);
                    k1 += k2;
                }
                l2 = j2;
                while(l2 < i2) 
                {
                    View view1 = (View)currentViews.get(l2);
                    int i3 = view1.getMeasuredWidth();
                    k2 = view1.getMeasuredHeight();
                    view1.layout(j1, k1, j1 + i3, k1 + k2);
                    if(l2 + 1 < i2)
                        if((1 + (l2 - j2)) % mNumColumns == 0)
                        {
                            j1 = i1;
                            k1 += k2 + mVerticalSpacing;
                        } else
                        {
                            j1 += i3 + mHorizontalSpacing;
                        }
                    l2++;
                }
                j1 = i1;
                k1 += k2 + mVerticalSpacing;
            }

        }
    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(i);
        int l = android.view.View.MeasureSpec.getMode(j);
        int i1 = android.view.View.MeasureSpec.getSize(i);
        int j1 = android.view.View.MeasureSpec.getSize(j);
        int k1;
        if(k == 0)
        {
            int i3;
            double d;
            int k3;
            if(mColumnWidth > 0)
                k3 = mColumnWidth + getPaddingLeft() + getPaddingRight();
            else
                k3 = getPaddingLeft() + getPaddingRight();
            i1 = k3 + getVerticalScrollbarWidth();
        }
        determineColumns(i1 - getPaddingLeft() - getPaddingRight());
        k1 = 0;
        if(mAdapter != null)
        {
            i3 = 0;
            while(i3 < sections.length) 
            {
                int j3;
                if(i3 + 1 < sections.length)
                    j3 = sections[i3 + 1].getStartPosition();
                else
                    j3 = itemCount;
                d = j3 - sections[i3].getStartPosition();
                k1 = (int)((double)k1 + Math.ceil(d / (double)mNumColumns));
                i3++;
            }
        }
        int l1 = android.view.View.MeasureSpec.makeMeasureSpec(mColumnWidth, 0x80000000);
        int i2 = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        mCellWidth = 0;
        mCellHeight = 0;
        for(Iterator iterator = currentViews.iterator(); iterator.hasNext();)
        {
            View view1 = (View)iterator.next();
            view1.measure(l1, i2);
            mCellHeight = Math.max(view1.getMeasuredHeight(), mCellHeight);
            mCellWidth = Math.max(view1.getMeasuredWidth(), mCellWidth);
        }

        int j2 = 0;
        int k2 = 0;
        Iterator iterator1 = sectionHeaderViews.iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            View view = (View)iterator1.next();
            if(view != null)
            {
                view.measure(android.view.View.MeasureSpec.makeMeasureSpec(i1, 0x80000000), android.view.View.MeasureSpec.makeMeasureSpec(view.getLayoutParams().height, 0x40000000));
                j2 += view.getMeasuredHeight();
                k2++;
            }
        } while(true);
        if(l == 0x80000000 || l == 0)
        {
            int l2 = j2 + (getPaddingTop() + getPaddingBottom()) + k1 * mCellHeight;
            if(k1 > 1)
                l2 += mVerticalSpacing * (k1 - 1);
            if(l == 0x80000000 && l2 >= j1)
                l2 = j1;
            j1 = l2;
        }
        if(k == 0x80000000 && mRequestedNumColumns != -1 && mRequestedNumColumns * mColumnWidth + (-1 + mRequestedNumColumns) * mHorizontalSpacing + getPaddingLeft() + getPaddingRight() > i1)
            i1 |= 0x1000000;
        setMeasuredDimension(i1, j1);
    }

    public void setAdapter(SonosGridAdapter sonosgridadapter)
    {
        if(mAdapter != null)
        {
            removeAllViews();
            mAdapter.unregisterDataSetObserver(adapterDataSetObserver);
        }
        mAdapter = sonosgridadapter;
        if(mAdapter != null)
            mAdapter.registerDataSetObserver(adapterDataSetObserver);
        refreshData();
    }

    public void setColumnWidth(int i)
    {
        if(i != mRequestedColumnWidth)
        {
            mRequestedColumnWidth = i;
            requestLayout();
        }
    }

    public void setGravity(int i)
    {
        if(mGravity != i)
        {
            mGravity = i;
            requestLayout();
        }
    }

    public void setHorizontalSpacing(int i)
    {
        if(i != mRequestedHorizontalSpacing)
        {
            mRequestedHorizontalSpacing = i;
            requestLayout();
        }
    }

    public void setNumColumns(int i)
    {
        if(i != mRequestedNumColumns)
        {
            mRequestedNumColumns = i;
            requestLayout();
        }
    }

    public void setStretchMode(int i)
    {
        if(i != mStretchMode)
        {
            mStretchMode = i;
            requestLayout();
        }
    }

    public void setVerticalSpacing(int i)
    {
        if(i != mVerticalSpacing)
        {
            mVerticalSpacing = i;
            requestLayout();
        }
    }

    public static final int AUTO_FIT = -1;
    private static final String LOG_TAG = com/sonos/acr/browse/v2/view/SonosGridLayout.getSimpleName();
    public static final int NO_STRETCH = 0;
    public static final int STRETCH_COLUMN_WIDTH = 2;
    public static final int STRETCH_SPACING = 1;
    public static final int STRETCH_SPACING_UNIFORM = 3;
    private final DataSetObserver adapterDataSetObserver;
    private ArrayList currentViews;
    private int itemCount;
    SonosGridAdapter mAdapter;
    int mCellHeight;
    int mCellWidth;
    private int mColumnWidth;
    private int mGravity;
    private int mHorizontalSpacing;
    private int mNumColumns;
    private int mRequestedColumnWidth;
    private int mRequestedHorizontalSpacing;
    private int mRequestedNumColumns;
    private int mStretchMode;
    private int mVerticalSpacing;
    private HashMap managedViews;
    ArrayList sectionHeaderViews;
    private BrowseSection sections[];


}

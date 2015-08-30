// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.emilsjolander.components.stickylistheaders;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import java.util.LinkedList;
import java.util.List;

// Referenced classes of package com.emilsjolander.components.stickylistheaders:
//            StickyListHeadersAdapter, WrapperView, CheckableWrapperView

class AdapterWrapper extends BaseAdapter
    implements StickyListHeadersAdapter
{
    public static interface OnHeaderClickListener
    {

        public abstract void onHeaderClick(View view, int i, long l);
    }


    AdapterWrapper(Context context, StickyListHeadersAdapter stickylistheadersadapter)
    {
        mDataSetObserver = new DataSetObserver() {

            public void onChanged()
            {
                notifyDataSetChanged();
            }

            public void onInvalidated()
            {
                mHeaderCache.clear();
                notifyDataSetInvalidated();
            }

            final AdapterWrapper this$0;

            
            {
                this$0 = AdapterWrapper.this;
                super();
            }
        }
;
        mContext = context;
        mDelegate = stickylistheadersadapter;
        stickylistheadersadapter.registerDataSetObserver(mDataSetObserver);
    }

    private View configureHeader(WrapperView wrapperview, final int position)
    {
        View view;
        View view1;
        if(wrapperview.mHeader == null)
            view = popHeader();
        else
            view = wrapperview.mHeader;
        view1 = mDelegate.getHeaderView(position, view, wrapperview);
        if(view1 == null)
        {
            throw new NullPointerException("Header view must not be null.");
        } else
        {
            view1.setClickable(true);
            view1.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view2)
                {
                    if(mOnHeaderClickListener != null)
                    {
                        long l = mDelegate.getHeaderId(position);
                        mOnHeaderClickListener.onHeaderClick(view2, position, l);
                    }
                }

                final AdapterWrapper this$0;
                final int val$position;

            
            {
                this$0 = AdapterWrapper.this;
                position = i;
                super();
            }
            }
);
            return view1;
        }
    }

    private View popHeader()
    {
        View view;
        if(mHeaderCache.size() > 0)
            view = (View)mHeaderCache.remove(0);
        else
            view = null;
        return view;
    }

    private boolean previousPositionHasSameHeader(int i)
    {
        boolean flag;
        if(i != 0 && mDelegate.getHeaderId(i) == mDelegate.getHeaderId(i - 1))
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void recycleHeaderIfExists(WrapperView wrapperview)
    {
        View view = wrapperview.mHeader;
        if(view != null)
            mHeaderCache.add(view);
    }

    public boolean areAllItemsEnabled()
    {
        return mDelegate.areAllItemsEnabled();
    }

    public boolean equals(Object obj)
    {
        return mDelegate.equals(obj);
    }

    public int getCount()
    {
        return mDelegate.getCount();
    }

    public View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        return ((BaseAdapter)mDelegate).getDropDownView(i, view, viewgroup);
    }

    public long getHeaderId(int i)
    {
        return mDelegate.getHeaderId(i);
    }

    public View getHeaderView(int i, View view, ViewGroup viewgroup)
    {
        return mDelegate.getHeaderView(i, view, viewgroup);
    }

    public Object getItem(int i)
    {
        return mDelegate.getItem(i);
    }

    public long getItemId(int i)
    {
        return mDelegate.getItemId(i);
    }

    public int getItemViewType(int i)
    {
        return mDelegate.getItemViewType(i);
    }

    public volatile View getView(int i, View view, ViewGroup viewgroup)
    {
        return getView(i, view, viewgroup);
    }

    public WrapperView getView(int i, View view, ViewGroup viewgroup)
    {
        Object obj;
        View view1;
        View view2;
        Drawable drawable;
        int j;
        boolean flag;
        if(view == null)
            obj = new WrapperView(mContext);
        else
            obj = (WrapperView)view;
        view1 = mDelegate.getView(i, ((WrapperView) (obj)).mItem, ((ViewGroup) (obj)));
        view2 = null;
        if(previousPositionHasSameHeader(i))
            recycleHeaderIfExists(((WrapperView) (obj)));
        else
            view2 = configureHeader(((WrapperView) (obj)), i);
        if((view1 instanceof Checkable) && !(obj instanceof CheckableWrapperView))
            obj = new CheckableWrapperView(mContext);
        else
        if(!(view1 instanceof Checkable) && (obj instanceof CheckableWrapperView))
            obj = new WrapperView(mContext);
        drawable = mDivider;
        j = mDividerHeight;
        if(i == -1 + getCount())
            flag = true;
        else
            flag = false;
        ((WrapperView) (obj)).update(view1, view2, drawable, j, flag);
        return ((WrapperView) (obj));
    }

    public int getViewTypeCount()
    {
        return mDelegate.getViewTypeCount();
    }

    public boolean hasStableIds()
    {
        return mDelegate.hasStableIds();
    }

    public int hashCode()
    {
        return mDelegate.hashCode();
    }

    public boolean isEmpty()
    {
        return mDelegate.isEmpty();
    }

    public boolean isEnabled(int i)
    {
        return mDelegate.isEnabled(i);
    }

    public void notifyDataSetChanged()
    {
        ((BaseAdapter)mDelegate).notifyDataSetChanged();
    }

    public void notifyDataSetInvalidated()
    {
        ((BaseAdapter)mDelegate).notifyDataSetInvalidated();
    }

    void setDivider(Drawable drawable)
    {
        mDivider = drawable;
    }

    void setDividerHeight(int i)
    {
        mDividerHeight = i;
    }

    public void setOnHeaderClickListener(OnHeaderClickListener onheaderclicklistener)
    {
        mOnHeaderClickListener = onheaderclicklistener;
    }

    public String toString()
    {
        return mDelegate.toString();
    }

    private final Context mContext;
    private DataSetObserver mDataSetObserver;
    final StickyListHeadersAdapter mDelegate;
    private Drawable mDivider;
    private int mDividerHeight;
    private final List mHeaderCache = new LinkedList();
    private OnHeaderClickListener mOnHeaderClickListener;




}

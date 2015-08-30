// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

public class ViewAdapter extends BaseAdapter
{

    public ViewAdapter(List list)
    {
        views = list;
    }

    public boolean areAllItemsEnabled()
    {
        return false;
    }

    protected View createView(int i, ViewGroup viewgroup)
    {
        throw new RuntimeException("You must override createView()!");
    }

    public int getCount()
    {
        return views.size();
    }

    public Object getItem(int i)
    {
        return views.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public int getItemViewType(int i)
    {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        View view1 = (View)views.get(i);
        if(view1 == null)
        {
            view1 = createView(i, viewgroup);
            views.set(i, view1);
        }
        return view1;
    }

    public int getViewTypeCount()
    {
        return getCount();
    }

    public boolean isEnabled(int i)
    {
        return false;
    }

    private static final String LOG_TAG = com/sonos/acr/util/ViewAdapter.getSimpleName();
    private List views;

}

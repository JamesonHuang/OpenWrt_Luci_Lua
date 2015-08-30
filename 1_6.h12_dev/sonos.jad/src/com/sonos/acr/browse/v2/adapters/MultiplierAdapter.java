// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.adapters;

import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package com.sonos.acr.browse.v2.adapters:
//            DelagateAdapter, IDataSourceAdapter

public class MultiplierAdapter extends DelagateAdapter
{

    public MultiplierAdapter(IDataSourceAdapter idatasourceadapter, int i)
    {
        DelagateAdapter(idatasourceadapter);
        multiplier = i;
    }

    public int getCount()
    {
        return getCount() * multiplier;
    }

    public Object getItem(int i)
    {
        return getItem(i / multiplier);
    }

    public long getItemId(int i)
    {
        return getItemId(i);
    }

    public int getItemViewType(int i)
    {
        return getItemViewType(i);
    }

    public int getPositionForSection(int i)
    {
        return getPositionForSection(i);
    }

    public int getSectionForPosition(int i)
    {
        return getSectionForPosition(i);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        return getView(i, view, viewgroup);
    }

    public boolean isSectioned(int i)
    {
        return isSectioned(i);
    }

    public int realCount()
    {
        return getCount();
    }

    int multiplier;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.emilsjolander.components.stickylistheaders.StickyListHeadersAdapter;
import com.sonos.acr.browse.BrowseSectionHeader;
import com.sonos.acr.browse.v2.view.BrowseItemCell;

// Referenced classes of package com.sonos.acr.browse.v2.adapters:
//            DelagateAdapter, IDataSourceAdapter

public class FixedSectionableListAdapter extends DelagateAdapter
    implements StickyListHeadersAdapter
{

    public FixedSectionableListAdapter(Context context1, IDataSourceAdapter idatasourceadapter)
    {
        DelagateAdapter(idatasourceadapter);
        context = context1;
    }

    public long getHeaderId(int i)
    {
        long l;
        if(!isSectioned(i))
            l = -1L;
        else
            l = getSectionForPosition(i);
        return l;
    }

    public View getHeaderView(int i, View view, ViewGroup viewgroup)
    {
        Object obj;
        if(isSectioned(i))
        {
            com.sonos.acr.browse.indexers.BrowseSection abrowsesection[];
            int j;
            if(view instanceof BrowseSectionHeader)
                obj = (BrowseSectionHeader)view;
            else
                obj = cellFactory.createHeaderView(i);
            abrowsesection = getSections();
            j = getSectionForPosition(i);
            if(abrowsesection != null && abrowsesection.length > j)
                ((BrowseSectionHeader) (obj)).updateSectionInfo(abrowsesection[j]);
            else
                ((BrowseSectionHeader) (obj)).setVisibility(8);
        } else
        {
            obj = new View(context);
        }
        return ((View) (obj));
    }

    public int getItemViewType(int i)
    {
        if(!hasSections()) goto _L2; else goto _L1
_L1:
        int k = delegateAdapter.getSectionForPosition(i);
        if(k < 0 || i != delegateAdapter.getPositionForSection(k)) goto _L2; else goto _L3
_L3:
        int j = 1;
_L5:
        return j;
_L2:
        j = 0;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        View view1 = delegateAdapter.getView(i, view, viewgroup);
        if(view1 instanceof BrowseItemCell)
            ((BrowseItemCell)view1).setHeaderPosition(getPositionForSection(getSectionForPosition(i)));
        return view1;
    }

    public void notifyDataSetChanged()
    {
        notifyDataSetChanged();
    }

    public void setCellFactory(IDataSourceAdapter.CellFactory cellfactory)
    {
        setCellFactory(cellfactory);
        cellFactory = cellfactory;
    }

    private static final int VIEWTYPE_BROWSE_HEADER_ITEM = 1;
    protected static final int VIEWTYPE_BROWSE_ITEM;
    IDataSourceAdapter.CellFactory cellFactory;
    private Context context;
}

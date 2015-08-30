// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.Context;
import android.widget.AbsListView;
import com.emilsjolander.components.stickylistheaders.StickyListHeadersListView;
import com.sonos.acr.browse.v2.adapters.FixedSectionableListAdapter;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.sclib.SCIBrowseDataSource;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourceSectionedListPageFragment

public class DataSourceFixedSectionedListPageFragment extends DataSourceSectionedListPageFragment
    implements com.sonos.acr.browse.v2.adapters.IDataSourceAdapter.CellFactory, com.emilsjolander.components.stickylistheaders.StickyListHeadersListView.OnHeaderClickListener
{

    public DataSourceFixedSectionedListPageFragment()
    {
    }

    public DataSourceFixedSectionedListPageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        DataSourceSectionedListPageFragment(scibrowsedatasource);
    }

    protected IDataSourceAdapter createDataSourceAdapter(SCIBrowseDataSource scibrowsedatasource, Context context)
    {
        return new FixedSectionableListAdapter(context, createDataSourceAdapter(scibrowsedatasource, context));
    }

    protected void setAdapterOnList(AbsListView abslistview, IDataSourceAdapter idatasourceadapter)
    {
        if(abslistview instanceof StickyListHeadersListView)
        {
            ((StickyListHeadersListView)adapterListView).setOnHeaderClickListener(this);
            ((StickyListHeadersListView)adapterListView).setAreHeadersSticky(false);
        }
        setAdapterOnList(abslistview, idatasourceadapter);
    }
}

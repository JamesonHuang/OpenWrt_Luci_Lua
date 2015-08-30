// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import com.emilsjolander.components.stickylistheaders.StickyListHeadersListView;
import com.sonos.acr.browse.BrowseSectionHeader;
import com.sonos.acr.browse.indexers.BrowseGroupSectionIndexer;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.acr.browse.v2.adapters.SectionableListAdapter;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.SCIBrowseDataSource;
import com.sonos.sclib.SCIBrowseGroupsInfo;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourceListPageFragment

public class DataSourceSectionedListPageFragment extends DataSourceListPageFragment
    implements com.sonos.acr.browse.v2.adapters.IDataSourceAdapter.CellFactory, com.emilsjolander.components.stickylistheaders.StickyListHeadersListView.OnHeaderClickListener
{
    public static interface OnHeaderClickListener
    {

        public abstract void onHeaderClicked(BrowseSectionHeader browsesectionheader);
    }


    public DataSourceSectionedListPageFragment()
    {
    }

    public DataSourceSectionedListPageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        DataSourceListPageFragment(scibrowsedatasource);
    }

    protected IDataSourceAdapter createDataSourceAdapter(SCIBrowseDataSource scibrowsedatasource, Context context)
    {
        return new SectionableListAdapter(context, createDataSourceAdapter(scibrowsedatasource, context));
    }

    protected BrowseGroupSectionIndexer createNewIndexer()
    {
        return new BrowseGroupSectionIndexer(browseDataSource);
    }

    protected int getLayoutId()
    {
        return 0x7f030051;
    }

    public void onDataSourceUpdated()
    {
        onDataSourceUpdated();
        if(LibraryUtils.cast(browseDataSource, com/sonos/sclib/SCIBrowseGroupsInfo) != null && dataSourceAdapter != null)
        {
            BrowseGroupSectionIndexer browsegroupsectionindexer = createNewIndexer();
            dataSourceAdapter.setSectionIndexer(browsegroupsectionindexer);
        }
    }

    public final void onHeaderClick(StickyListHeadersListView stickylistheaderslistview, View view, int i, long l, boolean flag)
    {
        if(headerClickListener != null && (view instanceof BrowseSectionHeader))
            headerClickListener.onHeaderClicked((BrowseSectionHeader)view);
    }

    protected void setAdapterOnList(AbsListView abslistview, IDataSourceAdapter idatasourceadapter)
    {
        if(abslistview instanceof StickyListHeadersListView)
            ((StickyListHeadersListView)adapterListView).setOnHeaderClickListener(this);
        setAdapterOnList(abslistview, idatasourceadapter);
    }

    public void setHeaderClickListener(OnHeaderClickListener onheaderclicklistener)
    {
        headerClickListener = onheaderclicklistener;
    }

    protected OnHeaderClickListener headerClickListener;
}

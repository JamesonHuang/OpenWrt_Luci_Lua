// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.os.Bundle;
import android.view.*;
import android.widget.AbsListView;
import android.widget.ListView;
import com.sonos.acr.browse.BrowseSectionHeader;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.browse.v2.view.BrowseItemListViewCell;
import com.sonos.sclib.SCIBrowseDataSource;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourceAbsListPageFragment

public class DataSourceListPageFragment extends DataSourceAbsListPageFragment
    implements com.sonos.acr.browse.v2.adapters.IDataSourceAdapter.CellFactory
{

    public DataSourceListPageFragment()
    {
    }

    public DataSourceListPageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        DataSourceAbsListPageFragment(scibrowsedatasource);
    }

    protected void addFooterSeparator()
    {
    }

    public BrowseItemCell createCellView(int i)
    {
        return new BrowseItemListViewCell(themedContext);
    }

    public BrowseSectionHeader createHeaderView(int i)
    {
        return new BrowseSectionHeader(themedContext);
    }

    protected int getLayoutId()
    {
        return 0x7f03004f;
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = onCreateThemedView(layoutinflater, viewgroup, bundle);
        addFooterSeparator();
        return view;
    }

    protected void setAdapterOnList(AbsListView abslistview, IDataSourceAdapter idatasourceadapter)
    {
        ((ListView)abslistview).setAdapter(idatasourceadapter);
    }

    public void updateCellView(BrowseItemCell browseitemcell, int i)
    {
    }
}

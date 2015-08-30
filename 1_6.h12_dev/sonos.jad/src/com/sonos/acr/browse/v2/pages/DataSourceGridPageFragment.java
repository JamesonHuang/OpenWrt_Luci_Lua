// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.widget.AbsListView;
import android.widget.GridView;
import com.sonos.acr.browse.BrowseSectionHeader;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.browse.v2.view.BrowseItemGridViewCell;
import com.sonos.sclib.SCIBrowseDataSource;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourceAbsListPageFragment

public class DataSourceGridPageFragment extends DataSourceAbsListPageFragment
{

    public DataSourceGridPageFragment()
    {
    }

    public DataSourceGridPageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        DataSourceAbsListPageFragment(scibrowsedatasource);
    }

    public BrowseItemCell createCellView(int i)
    {
        return new BrowseItemGridViewCell(themedContext);
    }

    public BrowseSectionHeader createHeaderView(int i)
    {
        return null;
    }

    protected int getLayoutId()
    {
        return 0x7f03004e;
    }

    protected void setAdapterOnList(AbsListView abslistview, IDataSourceAdapter idatasourceadapter)
    {
        if(abslistview instanceof GridView)
            ((GridView)abslistview).setAdapter(idatasourceadapter);
    }

    public void updateCellView(BrowseItemCell browseitemcell, int i)
    {
    }
}

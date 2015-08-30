// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.widget.AbsListView;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.acr.browse.v2.view.BrowseEditItemListViewCell;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.util.WeakHashSet;
import com.sonos.sclib.SCIBrowseDataSource;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourceGridPageFragment

public class DataSourceEditGridPageFragment extends DataSourceGridPageFragment
{

    public DataSourceEditGridPageFragment()
    {
    }

    public DataSourceEditGridPageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        DataSourceGridPageFragment(scibrowsedatasource);
    }

    protected boolean canEdit()
    {
        boolean flag;
        if(adapterListView == null || adapterListView.getCount() == 0)
            flag = false;
        else
            flag = true;
        return flag;
    }

    protected void onEditModeChange(boolean flag)
    {
        onEditModeChange(flag);
        AbsListView abslistview = adapterListView;
        boolean flag1;
        Iterator iterator;
        if(!flag)
            flag1 = true;
        else
            flag1 = false;
        abslistview.setFastScrollEnabled(flag1);
        iterator = dataSourceAdapter.getManagedCells().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            BrowseItemCell browseitemcell = (BrowseItemCell)iterator.next();
            if(browseitemcell instanceof BrowseEditItemListViewCell)
                ((BrowseEditItemListViewCell)browseitemcell).setEditState(flag);
        } while(true);
    }

    public void updateCellView(BrowseItemCell browseitemcell, int i)
    {
        if(browseitemcell instanceof BrowseEditItemListViewCell)
            ((BrowseEditItemListViewCell)browseitemcell).setEditState(inEditMode);
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.browse.v2.view.EditorialBrowseItemListViewCell;
import com.sonos.acr.util.DisplayController;
import com.sonos.sclib.SCIBrowseDataSource;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourceListPageFragment

public class DataSourceEditorialListPageFragment extends DataSourceListPageFragment
{

    public DataSourceEditorialListPageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        DataSourceListPageFragment(scibrowsedatasource);
    }

    public BrowseItemCell createCellView(int i)
    {
        return new EditorialBrowseItemListViewCell(themedContext);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        onViewCreated(view, bundle);
        if((adapterListView instanceof ListView) && DisplayController.getScreenType() == 0)
        {
            int i = ((ListView)adapterListView).getDividerHeight();
            ((ListView)adapterListView).setDivider(getResources().getDrawable(0x7f02000a));
            ((ListView)adapterListView).setDividerHeight(i);
        }
    }
}

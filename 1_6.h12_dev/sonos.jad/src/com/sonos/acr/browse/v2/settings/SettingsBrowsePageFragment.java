// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.settings;

import android.view.View;
import com.sonos.acr.browse.v2.actions.ActionSet;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.acr.browse.v2.pages.DataSourceListPageFragment;
import com.sonos.acr.browse.v2.view.*;
import com.sonos.sclib.SCIBrowseDataSource;

public class SettingsBrowsePageFragment extends DataSourceListPageFragment
{

    public SettingsBrowsePageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        super(scibrowsedatasource);
    }

    protected void addFooterSeparator()
    {
    }

    public BrowseItemCell createCellView(int i)
    {
        return new SettingsItemListViewCell(getThemedContext());
    }

    protected int getLayoutId()
    {
        return 0x7f030055;
    }

    public void onResume()
    {
        super.onResume();
        dataSourceAdapter.refresh();
    }

    public void updateActionMenu()
    {
        if(actionMenu != null)
        {
            ActionSet actionset = getDataSourceActions();
            if(!actionset.isEmpty())
            {
                actionMenu.setActions(actionset);
                ((View)actionMenu).setVisibility(0);
            } else
            {
                ((View)actionMenu).setVisibility(8);
            }
        }
    }
}

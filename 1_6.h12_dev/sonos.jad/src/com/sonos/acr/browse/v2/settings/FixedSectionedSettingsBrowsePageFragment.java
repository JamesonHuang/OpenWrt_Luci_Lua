// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.settings;

import android.view.View;
import com.sonos.acr.browse.BrowseSectionHeader;
import com.sonos.acr.browse.v2.SettingsBrowseSectionHeader;
import com.sonos.acr.browse.v2.actions.ActionSet;
import com.sonos.acr.browse.v2.pages.DataSourceFixedSectionedListPageFragment;
import com.sonos.acr.browse.v2.view.*;
import com.sonos.sclib.SCIBrowseDataSource;

public class FixedSectionedSettingsBrowsePageFragment extends DataSourceFixedSectionedListPageFragment
{

    public FixedSectionedSettingsBrowsePageFragment(SCIBrowseDataSource scibrowsedatasource)
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

    public BrowseSectionHeader createHeaderView(int i)
    {
        return new SettingsBrowseSectionHeader(themedContext);
    }

    protected int getLayoutId()
    {
        return 0x7f030056;
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

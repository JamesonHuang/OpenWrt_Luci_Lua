// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.settings;

import android.support.v4.app.FragmentActivity;
import com.sonos.acr.browse.v2.PickerFragment;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.sclib.sclib;
import java.util.Stack;

public class SettingsFlipperFragment extends PickerFragment
{

    public SettingsFlipperFragment()
    {
    }

    public boolean canGoBack()
    {
        boolean flag = true;
        if(pages.size() <= flag)
            flag = false;
        return flag;
    }

    public String getActionFilter(boolean flag)
    {
        return sclib.SC_ACTION_FILTERNAME_CONTEXTMENU;
    }

    public boolean onItemLongClick(BrowseItemCell browseitemcell)
    {
        return false;
    }

    protected void updateHeaderBar()
    {
        super.updateHeaderBar();
        getActivity().setTitle(getTitle());
    }
}

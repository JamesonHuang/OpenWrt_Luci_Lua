// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2;

import com.sonos.acr.browse.v2.pages.BrowseFlipperPageFragment;
import com.sonos.acr.browse.v2.view.BrowseItemCell;

public class PickerFragment extends BrowseFlipperPageFragment
{

    public PickerFragment()
    {
        this(0, 0x7f03000f);
    }

    public PickerFragment(int i)
    {
        this(i, 0x7f03000f);
    }

    public PickerFragment(int i, int j)
    {
        super(i, j);
    }

    public void dismissPicker()
    {
        clearStack();
        invalidatePage();
    }

    public volatile CharSequence getTitle()
    {
        return super.getTitle();
    }

    public boolean onItemLongClick(BrowseItemCell browseitemcell)
    {
        return true;
    }
}

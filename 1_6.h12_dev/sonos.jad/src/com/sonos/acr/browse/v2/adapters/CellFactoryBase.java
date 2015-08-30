// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.adapters;

import com.sonos.acr.browse.BrowseSectionHeader;
import com.sonos.acr.browse.v2.view.BrowseItemCell;

public abstract class CellFactoryBase
    implements IDataSourceAdapter.CellFactory
{

    public CellFactoryBase()
    {
    }

    public BrowseSectionHeader createHeaderView(int i)
    {
        return null;
    }

    public void updateCellView(BrowseItemCell browseitemcell, int i)
    {
    }
}

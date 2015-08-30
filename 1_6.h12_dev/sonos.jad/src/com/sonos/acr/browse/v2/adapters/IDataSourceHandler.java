// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.adapters;

import com.sonos.acr.browse.v2.view.BrowseItemCell;

public interface IDataSourceHandler
{
    public static interface OnItemClickListener
    {

        public abstract void onItemClick(BrowseItemCell browseitemcell);

        public abstract boolean onItemLongClick(BrowseItemCell browseitemcell);
    }


    public abstract void setOnItemClickListener(OnItemClickListener onitemclicklistener);
}

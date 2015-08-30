// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.adapters;

import android.widget.ListAdapter;
import com.sonos.acr.browse.BrowseSectionHeader;
import com.sonos.acr.browse.indexers.SonosSectionIndexer;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.util.WeakHashSet;
import com.sonos.sclib.SCIBrowseDataSource;

// Referenced classes of package com.sonos.acr.browse.v2.adapters:
//            IDataSourceHandler

public interface IDataSourceAdapter
    extends ListAdapter, SonosSectionIndexer, IDataSourceHandler
{
    public static interface CellFactory
    {

        public abstract BrowseItemCell createCellView(int i);

        public abstract BrowseSectionHeader createHeaderView(int i);

        public abstract void updateCellView(BrowseItemCell browseitemcell, int i);
    }

    public static interface AdapterListener
    {

        public abstract void onDataSourceGone();

        public abstract void onDataSourceInvalidated();

        public abstract void onDataSourceUpdated();
    }


    public abstract WeakHashSet getManagedCells();

    public abstract boolean isGone();

    public abstract void refresh();

    public abstract void releaseResources();

    public abstract void setAdapterListener(AdapterListener adapterlistener);

    public abstract void setBrowseDataSource(SCIBrowseDataSource scibrowsedatasource);

    public abstract void setCellFactory(CellFactory cellfactory);

    public abstract void setSectionIndexer(SonosSectionIndexer sonossectionindexer);

    public abstract void subscribe();

    public abstract void unsubscribe();
}

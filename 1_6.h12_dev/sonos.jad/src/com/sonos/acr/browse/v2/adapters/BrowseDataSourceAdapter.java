// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.sonos.acr.Loc;
import com.sonos.acr.browse.indexers.*;
import com.sonos.acr.browse.v2.BrowseDataSourceEventSink;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.WeakHashSet;
import com.sonos.sclib.*;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.browse.v2.adapters:
//            IDataSourceAdapter

public class BrowseDataSourceAdapter extends BaseAdapter
    implements IDataSourceAdapter, android.view.View.OnClickListener, android.view.View.OnLongClickListener
{
    private class AdapterEventSink extends BrowseDataSourceEventSink
    {

        public void onBrowseChanged(SCIBrowseDataSource scibrowsedatasource)
        {
            SLog.d(LOG_TAG, (new StringBuilder()).append("OnBrowseChanged Called: Valid").append(dataSource.isValid()).toString());
            BrowseDataSourceAdapter.this.onBrowseChanged(scibrowsedatasource);
        }

        public void onBrowseInvalidation(SCIBrowseDataSource scibrowsedatasource)
        {
            SLog.i(LOG_TAG, Loc.NOLOC((new StringBuilder()).append("On Browse Invalidation: ").append(scibrowsedatasource.getSCUri()).append(" Gone: ").append(scibrowsedatasource.isGone()).toString()));
            onBrowseInvalidated(scibrowsedatasource);
        }

        public void onPowerScrollInfo(SCIBrowseDataSource scibrowsedatasource, SCIPowerscrollDataSource scipowerscrolldatasource)
        {
            SLog.d(LOG_TAG, Loc.NOLOC("Browse power scroll information is available."));
            if(scipowerscrolldatasource.hasPrefixData())
                setSectionIndexer(new BrowsePowerScrollSectionIndexer(dataSource));
            else
                setSectionIndexer(null);
        }

        final BrowseDataSourceAdapter this$0;

        private AdapterEventSink()
        {
            this$0 = BrowseDataSourceAdapter.this;
            BrowseDataSourceEventSink();
        }

    }


    public BrowseDataSourceAdapter(Context context1)
    {
        managedCells = new WeakHashSet();
        if(context1 == null)
        {
            throw new NullPointerException("Null Context you can't do that.");
        } else
        {
            context = context1;
            return;
        }
    }

    protected BrowseDataSourceEventSink createEventSink()
    {
        return new AdapterEventSink();
    }

    public int getCount()
    {
        int i;
        if(dataSource != null)
            i = (int)dataSource.getNumItems();
        else
            i = 0;
        return i;
    }

    public SCIBrowseItem getItem(int i)
    {
        return dataSource.getItemAtIndex(i);
    }

    public volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public int getItemViewType(int i)
    {
        return 0;
    }

    public WeakHashSet getManagedCells()
    {
        return managedCells;
    }

    public int getPositionCount()
    {
        return getCount();
    }

    public int getPositionForSection(int i)
    {
        int j;
        if(sectionIndexer == null)
            j = 0;
        else
            j = sectionIndexer.getPositionForSection(i);
        return j;
    }

    public int getSectionForPosition(int i)
    {
        int j;
        if(sectionIndexer == null)
            j = 0;
        else
            j = sectionIndexer.getSectionForPosition(i);
        return j;
    }

    public BrowseSection[] getSections()
    {
        BrowseSection abrowsesection[];
        if(sectionIndexer == null || sectionIndexer.getPositionCount() == 0)
            abrowsesection = new BrowseSection[0];
        else
            abrowsesection = sectionIndexer.getSections();
        return abrowsesection;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        BrowseItemCell browseitemcell;
        if(view instanceof BrowseItemCell)
        {
            browseitemcell = (BrowseItemCell)view;
            browseitemcell.unsubscribe();
        } else
        {
            browseitemcell = cellFactory.createCellView(i);
            browseitemcell.setOnClickListener(this);
            browseitemcell.setOnLongClickListener(this);
            managedCells.add(browseitemcell);
        }
        browseitemcell.subscribe(getItem(i), i);
        cellFactory.updateCellView(browseitemcell, i);
        return browseitemcell;
    }

    public int getViewTypeCount()
    {
        return 1;
    }

    public boolean hasSections()
    {
        boolean flag;
        if(sectionIndexer != null && sectionIndexer.hasSections())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasStableIds()
    {
        return false;
    }

    public boolean isGone()
    {
        boolean flag;
        if(dataSource == null || dataSource.isGone())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isSectioned(int i)
    {
        boolean flag;
        if(sectionIndexer != null && sectionIndexer.isSectioned(i))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void notifyDataSetChanged()
    {
        SLog.d(LOG_TAG, "Dataset has changed");
        notifyDataSetChanged();
    }

    protected void onBrowseChanged(SCIBrowseDataSource scibrowsedatasource)
    {
        unsubscribeCells();
        notifyDataSetChanged();
        if(adapterListener != null)
            adapterListener.onDataSourceUpdated();
        SLog.d(LOG_TAG, (new StringBuilder()).append("Finished Browse Changed: [").append(scibrowsedatasource.getSCUri()).append("]:]").append(scibrowsedatasource.getNumItems()).append("]").toString());
    }

    protected void onBrowseInvalidated(SCIBrowseDataSource scibrowsedatasource)
    {
        unsubscribeCells();
        if(adapterListener != null)
            if(scibrowsedatasource.isGone())
            {
                adapterListener.onDataSourceGone();
            } else
            {
                adapterListener.onDataSourceInvalidated();
                scibrowsedatasource.refreshBrowse();
            }
    }

    public void onClick(View view)
    {
        if(clickListener == null) goto _L2; else goto _L1
_L1:
        if(view == null) goto _L2; else goto _L3
_L3:
        if(!(view instanceof BrowseItemCell)) goto _L5; else goto _L4
_L4:
        clickListener.onItemClick((BrowseItemCell)view);
_L2:
        return;
_L5:
        view = (View)view.getParent();
        if(true) goto _L1; else goto _L6
_L6:
    }

    public boolean onLongClick(View view)
    {
        boolean flag = false;
        if(!sclib.SCLibMatchesFixedSCUri(dataSource.getSCUri(), SCFixedSCUriID.SC_FIXEDSCURI_Root, false)) goto _L2; else goto _L1
_L1:
        flag = true;
_L4:
        return flag;
_L2:
        if(clickListener == null)
            continue; /* Loop/switch isn't completed */
_L5:
        if(view != null)
        {
label0:
            {
                if(!(view instanceof BrowseItemCell))
                    break label0;
                clickListener.onItemLongClick((BrowseItemCell)view);
                flag = true;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
        view = (View)view.getParent();
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    public void refresh()
    {
        if(dataSource != null)
            onBrowseChanged(dataSource);
    }

    public void releaseResources()
    {
        for(Iterator iterator = managedCells.iterator(); iterator.hasNext(); ((BrowseItemCell)iterator.next()).stop());
    }

    public void setAdapterListener(IDataSourceAdapter.AdapterListener adapterlistener)
    {
        adapterListener = adapterlistener;
    }

    public void setBrowseDataSource(SCIBrowseDataSource scibrowsedatasource)
    {
        if(dataSource == null || !dataSource.equals(scibrowsedatasource))
        {
            dataSource = scibrowsedatasource;
            if(dataSourceEventSink != null)
                subscribe();
        }
        if(adapterListener != null)
            adapterListener.onDataSourceUpdated();
    }

    public void setCellFactory(IDataSourceAdapter.CellFactory cellfactory)
    {
        cellFactory = cellfactory;
    }

    public void setOnItemClickListener(IDataSourceHandler.OnItemClickListener onitemclicklistener)
    {
        clickListener = onitemclicklistener;
    }

    public void setSectionIndexer(SonosSectionIndexer sonossectionindexer)
    {
        if(sectionIndexer != sonossectionindexer)
        {
            sectionIndexer = sonossectionindexer;
            notifyDataSetChanged();
        }
    }

    public void subscribe()
    {
        if(cellFactory == null)
            throw new RuntimeException("Cell Factory has not been set!");
        if(dataSourceEventSink == null)
            dataSourceEventSink = createEventSink();
        dataSourceEventSink.subscribe(dataSource);
        if(dataSource == null)
        {
            unsubscribeCells();
            notifyDataSetChanged();
        }
    }

    public void unsubscribe()
    {
        unsubscribeCells();
        if(dataSourceEventSink != null)
        {
            dataSourceEventSink.unsubscribe();
            dataSourceEventSink = null;
        }
    }

    public void unsubscribeCells()
    {
        for(Iterator iterator = managedCells.iterator(); iterator.hasNext(); ((BrowseItemCell)iterator.next()).unsubscribe());
    }

    protected static final int VIEWTYPE_BROWSE_ITEM;
    private final String LOG_TAG = (new StringBuilder()).append(com/sonos/acr/browse/v2/adapters/BrowseDataSourceAdapter.getSimpleName()).append(":").append(getClass().getName()).toString();
    protected IDataSourceAdapter.AdapterListener adapterListener;
    IDataSourceAdapter.CellFactory cellFactory;
    protected IDataSourceHandler.OnItemClickListener clickListener;
    protected Context context;
    protected SCIBrowseDataSource dataSource;
    protected BrowseDataSourceEventSink dataSourceEventSink;
    protected WeakHashSet managedCells;
    protected SonosSectionIndexer sectionIndexer;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.adapters;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.sonos.acr.browse.indexers.BrowseSection;
import com.sonos.acr.browse.indexers.SonosSectionIndexer;
import com.sonos.acr.util.WeakHashSet;
import com.sonos.sclib.SCIBrowseDataSource;

// Referenced classes of package com.sonos.acr.browse.v2.adapters:
//            IDataSourceAdapter

public abstract class DelagateAdapter extends BaseAdapter
    implements IDataSourceAdapter
{

    public DelagateAdapter(IDataSourceAdapter idatasourceadapter)
    {
        if(idatasourceadapter == null)
        {
            throw new RuntimeException("No delegate was specified");
        } else
        {
            setDelegateAdapter(idatasourceadapter);
            return;
        }
    }

    public int getCount()
    {
        return delegateAdapter.getCount();
    }

    public IDataSourceAdapter getDelegateAdapter()
    {
        return delegateAdapter;
    }

    public Object getItem(int i)
    {
        return delegateAdapter.getItem(i);
    }

    public long getItemId(int i)
    {
        return delegateAdapter.getItemId(i);
    }

    public int getItemViewType(int i)
    {
        return delegateAdapter.getItemViewType(i);
    }

    public WeakHashSet getManagedCells()
    {
        return delegateAdapter.getManagedCells();
    }

    public int getPositionCount()
    {
        return delegateAdapter.getPositionCount();
    }

    public int getPositionForSection(int i)
    {
        return delegateAdapter.getPositionForSection(i);
    }

    public int getSectionForPosition(int i)
    {
        return delegateAdapter.getSectionForPosition(i);
    }

    public BrowseSection[] getSections()
    {
        return delegateAdapter.getSections();
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        return delegateAdapter.getView(i, view, viewgroup);
    }

    public int getViewTypeCount()
    {
        return delegateAdapter.getViewTypeCount();
    }

    public boolean hasSections()
    {
        return delegateAdapter.hasSections();
    }

    public boolean isEmpty()
    {
        return delegateAdapter.isEmpty();
    }

    public boolean isGone()
    {
        return delegateAdapter.isGone();
    }

    public boolean isSectioned(int i)
    {
        return delegateAdapter.isSectioned(i);
    }

    public void refresh()
    {
        delegateAdapter.refresh();
    }

    public void releaseResources()
    {
        delegateAdapter.releaseResources();
    }

    public void setAdapterListener(IDataSourceAdapter.AdapterListener adapterlistener)
    {
        delegateAdapter.setAdapterListener(adapterlistener);
    }

    public void setBrowseDataSource(SCIBrowseDataSource scibrowsedatasource)
    {
        delegateAdapter.setBrowseDataSource(scibrowsedatasource);
    }

    public void setCellFactory(IDataSourceAdapter.CellFactory cellfactory)
    {
        delegateAdapter.setCellFactory(cellfactory);
    }

    public void setDelegateAdapter(IDataSourceAdapter idatasourceadapter)
    {
        if(delegateAdapter != null)
        {
            delegateAdapter.unsubscribe();
            delegateAdapter.releaseResources();
        }
        delegateAdapter = idatasourceadapter;
        delegateAdapter.registerDataSetObserver(new DataSetObserver() {

            public void onChanged()
            {
                onChanged();
                notifyDataSetChanged();
            }

            public void onInvalidated()
            {
                onInvalidated();
                notifyDataSetInvalidated();
            }

            final DelagateAdapter this$0;

            
            {
                this$0 = DelagateAdapter.this;
                DataSetObserver();
            }
        }
);
    }

    public void setOnItemClickListener(IDataSourceHandler.OnItemClickListener onitemclicklistener)
    {
        delegateAdapter.setOnItemClickListener(onitemclicklistener);
    }

    public void setSectionIndexer(SonosSectionIndexer sonossectionindexer)
    {
        delegateAdapter.setSectionIndexer(sonossectionindexer);
    }

    public void subscribe()
    {
        delegateAdapter.subscribe();
    }

    public void unsubscribe()
    {
        delegateAdapter.unsubscribe();
    }

    protected IDataSourceAdapter delegateAdapter;
}

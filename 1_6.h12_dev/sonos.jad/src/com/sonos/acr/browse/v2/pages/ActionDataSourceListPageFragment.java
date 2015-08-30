// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.*;
import com.sonos.acr.browse.BrowseSectionHeader;
import com.sonos.acr.browse.v2.adapters.FixedSectionableListAdapter;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.acr.browse.v2.view.ActionItemListViewCell;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.view.SonosLinearLayout;
import com.sonos.sclib.SCIBrowseDataSource;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourceFixedSectionedListPageFragment

public class ActionDataSourceListPageFragment extends DataSourceFixedSectionedListPageFragment
    implements com.sonos.acr.browse.v2.adapters.IDataSourceAdapter.CellFactory
{

    public ActionDataSourceListPageFragment()
    {
        width = -1;
        height = -1;
    }

    public ActionDataSourceListPageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        DataSourceFixedSectionedListPageFragment(scibrowsedatasource);
        width = -1;
        height = -1;
    }

    private void resetSize()
    {
        width = -1;
        height = -1;
        if(rootView != null && getResources() != null)
        {
            int l1 = getResources().getDimensionPixelSize(0x7f090050);
            rootView.setMinimumWidth(l1);
            rootView.setMinimumHeight(0);
        }
        if(!hasHeader && dataSourceAdapter != null)
        {
            int i = 0;
            for(int j = 0; j < dataSourceAdapter.getCount(); j++)
            {
                BrowseItemCell browseitemcell = createCellView(0);
                View view1 = dataSourceAdapter.getView(j, browseitemcell, adapterListView);
                view1.setLayoutParams(new LayoutParams(-2, -2));
                view1.measure(0, 0);
                browseitemcell.unsubscribe();
                int k1 = view1.getMeasuredWidth();
                if(k1 > i)
                    i = k1;
            }

            if(dataSourceAdapter.hasSections())
            {
                int l = dataSourceAdapter.getSections().length;
                for(int i1 = 0; i1 < l; i1++)
                {
                    BrowseSectionHeader browsesectionheader = createHeaderView(i1);
                    View view = ((FixedSectionableListAdapter)dataSourceAdapter).getHeaderView(i1, browsesectionheader, adapterListView);
                    view.setLayoutParams(new LayoutParams(-2, -2));
                    view.measure(0, 0);
                    int j1 = view.getMeasuredWidth();
                    if(j1 > i)
                        i = j1;
                }

            }
            int k = i + (listContainer.getPaddingLeft() + listContainer.getPaddingRight());
            listContainer.setMaxWidth(k);
        } else
        {
            listContainer.setMaxWidth(-1);
        }
        listContainer.requestLayout();
    }

    private void setNewSize()
    {
        if(rootView != null)
        {
            if(height != -1)
                rootView.setMinimumHeight(height);
            if(width != -1)
            {
                rootView.setMinimumWidth(width);
                listContainer.setMaxWidth(width);
            }
        }
    }

    public BrowseItemCell createCellView(int i)
    {
        return new ActionItemListViewCell(themedContext);
    }

    public BrowseSectionHeader createHeaderView(int i)
    {
        return new BrowseSectionHeader(themedContext) {

            protected int getLayoutId()
            {
                return 0x7f030037;
            }

            final ActionDataSourceListPageFragment this$0;

            
            {
                this$0 = ActionDataSourceListPageFragment.this;
                BrowseSectionHeader(context);
            }
        }
;
    }

    protected int getLayoutId()
    {
        return 0x7f030004;
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        rootView = onCreateThemedView(layoutinflater, viewgroup, bundle);
        listContainer = (SonosLinearLayout)rootView.findViewById(0x7f0a0033);
        if(getResources() != null)
            listContainer.setMaxWidth(getResources().getDimensionPixelSize(0x7f090050));
        return rootView;
    }

    public void onDataSourceUpdated()
    {
        onDataSourceUpdated();
        if(browseDataSource == null || browseDataSource.isValid())
            resetSize();
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        onViewCreated(view, bundle);
        setNewSize();
    }

    public void setHasHeader(boolean flag)
    {
        hasHeader = flag;
    }

    public void setStartSize(int i, int j)
    {
        height = j;
        width = i;
        if(getView() != null)
            setNewSize();
    }

    boolean hasHeader;
    private int height;
    SonosLinearLayout listContainer;
    View rootView;
    private int width;
}

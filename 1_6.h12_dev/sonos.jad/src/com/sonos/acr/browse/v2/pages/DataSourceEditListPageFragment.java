// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.widget.*;
import com.sonos.acr.browse.BrowseSectionHeader;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.acr.browse.v2.view.BrowseEditItemListViewCell;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;
import dslv.DragSortListView;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourceAbsListPageFragment

public class DataSourceEditListPageFragment extends DataSourceAbsListPageFragment
    implements dslv.DragSortListView.DropListener, com.sonos.acr.browse.v2.adapters.IDataSourceAdapter.CellFactory
{

    public DataSourceEditListPageFragment()
    {
    }

    public DataSourceEditListPageFragment(SCIBrowseDataSource scibrowsedatasource)
    {
        DataSourceAbsListPageFragment(scibrowsedatasource);
    }

    protected boolean canEdit()
    {
        boolean flag;
        if(adapterListView == null || adapterListView.getCount() == 0)
            flag = false;
        else
            flag = true;
        return flag;
    }

    public BrowseItemCell createCellView(int i)
    {
        return new BrowseEditItemListViewCell(themedContext);
    }

    public BrowseSectionHeader createHeaderView(int i)
    {
        return null;
    }

    public void drop(int i, int j)
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Dragging From: ").append(i).append(" to: ").append(j).toString());
        Object obj = ((ListAdapter)adapterListView.getAdapter()).getItem(i);
        if(obj instanceof SCIBrowseItem)
        {
            SCIActionWithIntDescriptor sciactionwithintdescriptor = ActionMenuUtils.getReorderAction((SCIBrowseItem)obj);
            if(sciactionwithintdescriptor != null)
            {
                SCIActionContext sciactioncontext = sciactionwithintdescriptor.getAction(j);
                if(sciactioncontext != null)
                    sciactioncontext.perform();
            }
        }
    }

    protected int getLayoutId()
    {
        return 0x7f03004d;
    }

    protected void onEditModeChange(boolean flag)
    {
        onEditModeChange(flag);
        setDragDropEnabled(flag);
        Iterator iterator = dataSourceAdapter.getManagedCells().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            BrowseItemCell browseitemcell = (BrowseItemCell)iterator.next();
            if(browseitemcell instanceof BrowseEditItemListViewCell)
                ((BrowseEditItemListViewCell)browseitemcell).setEditState(flag);
        } while(true);
        AbsListView abslistview = adapterListView;
        boolean flag1;
        ListView listview;
        int i;
        if(!flag)
            flag1 = true;
        else
            flag1 = false;
        abslistview.setFastScrollEnabled(flag1);
        updateFooterPadding(flag);
        listview = (ListView)adapterListView;
        i = listview.getDividerHeight();
        if(isAdded() && i > 0 && listview.getDivider() != null)
        {
            Resources resources = getResources();
            int j;
            if(flag)
                j = 0x7f020008;
            else
                j = 0x7f020016;
            listview.setDivider(resources.getDrawable(j));
            listview.setDividerHeight(i);
        }
    }

    protected void setAdapterOnList(AbsListView abslistview, IDataSourceAdapter idatasourceadapter)
    {
        ((DragSortListView)abslistview).setAdapter(idatasourceadapter);
    }

    public void setDragDropEnabled(boolean flag)
    {
        if(adapterListView instanceof DragSortListView)
        {
            DragSortListView dragsortlistview = (DragSortListView)adapterListView;
            if(flag)
            {
                dragsortlistview.setDropListener(this);
                dragsortlistview.setDragEnabled(true);
            } else
            {
                dragsortlistview.cancelDrag();
                dragsortlistview.setDragEnabled(false);
            }
        }
    }

    public void updateCellView(BrowseItemCell browseitemcell, int i)
    {
        if(browseitemcell instanceof BrowseEditItemListViewCell)
            ((BrowseEditItemListViewCell)browseitemcell).setEditState(inEditMode);
    }

    protected void updateFooterPadding(boolean flag)
    {
        Configuration configuration = getResources().getConfiguration();
        if(adapterListView != null && (0xf & configuration.screenLayout) == 4)
            if(!flag)
                adapterListView.setPadding(adapterListView.getPaddingLeft(), adapterListView.getPaddingTop(), adapterListView.getPaddingRight(), getResources().getDimensionPixelSize(0x7f09000e));
            else
                adapterListView.setPadding(adapterListView.getPaddingLeft(), adapterListView.getPaddingTop(), adapterListView.getPaddingRight(), 0);
    }
}

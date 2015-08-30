// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.*;
import android.widget.AbsListView;
import com.sonos.acr.browse.BrowseSectionHeader;
import com.sonos.acr.browse.v2.adapters.FixedSectionableListAdapter;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.acr.browse.v2.pages.DataSourceFixedSectionedListPageFragment;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.browse.v2.view.BrowseItemListViewCell;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;
import java.util.HashMap;

public class MusicMenuFragment extends DataSourceFixedSectionedListPageFragment
{
    private class RootBrowseSectionHeader extends BrowseSectionHeader
    {

        protected int getLayoutId()
        {
            return 0x7f030038;
        }

        final MusicMenuFragment this$0;

        public RootBrowseSectionHeader(Context context)
        {
            this$0 = MusicMenuFragment.this;
            super(context);
        }
    }


    public MusicMenuFragment()
    {
        super(LibraryUtils.createBrowseDataSource(sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Root), ""));
        setThemedAttributeId(0x7f010055);
    }

    private String lookupServiceDescriptorIdBySCUri(String s)
    {
        SCIServiceDescriptorManager sciservicedescriptormanager = getHousehold().getServiceDescriptorManager();
        if(sciservicedescriptormanager == null) goto _L2; else goto _L1
_L1:
        SCIServiceDescriptor sciservicedescriptor = sciservicedescriptormanager.lookupServiceDescriptorBySCUri(s);
        if(sciservicedescriptor == null) goto _L2; else goto _L3
_L3:
        String s1 = sciservicedescriptor.getID();
_L5:
        return s1;
_L2:
        s1 = null;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public boolean containsURIItem(String s)
    {
        long l;
        int i;
        if(browseDataSource == null || !browseDataSource.isValid() || !StringUtils.isNotEmptyOrNull(s))
            break MISSING_BLOCK_LABEL_73;
        l = browseDataSource.getNumItems();
        i = 0;
_L3:
        if((long)i >= l)
            break MISSING_BLOCK_LABEL_73;
        if(!browseDataSource.getItemAtIndex(i).getSCUri().equals(s)) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        i++;
          goto _L3
        flag = false;
          goto _L4
    }

    public BrowseItemCell createCellView(int i)
    {
        BrowseItemListViewCell browseitemlistviewcell = new BrowseItemListViewCell(themedContext) {

            protected int getLayoutId()
            {
                return 0x7f03000d;
            }

            final MusicMenuFragment this$0;

            
            {
                this$0 = MusicMenuFragment.this;
                super(context);
                int i = getResources().getDimensionPixelSize(0x7f09004a);
                setPadding(i, 0, i, 0);
                oddBackgroundDrawable = 0;
            }
        }
;
        browseitemlistviewcell.setBackgroundResource(0x7f02009f);
        return browseitemlistviewcell;
    }

    protected IDataSourceAdapter createDataSourceAdapter(SCIBrowseDataSource scibrowsedatasource, Context context)
    {
        return new FixedSectionableListAdapter(context, super.createDataSourceAdapter(scibrowsedatasource, context));
    }

    public BrowseSectionHeader createHeaderView(int i)
    {
        return new RootBrowseSectionHeader(getThemedContext());
    }

    protected int getLayoutId()
    {
        return 0x7f030052;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        updateMusicMenuPadding();
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = super.onCreateThemedView(layoutinflater, viewgroup, bundle);
        updateMusicMenuPadding();
        return view;
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        pageHeader.setVisibility(8);
    }

    public void setItemSelected(String s)
    {
        if(StringUtils.isNotEmptyOrNull(s) && !s.equals(selectedItemScUri))
        {
            selectedItemScUri = s;
            selectedItemServiceId = lookupServiceDescriptorIdBySCUri(s);
            if(dataSourceAdapter != null)
                dataSourceAdapter.refresh();
        }
    }

    public void updateCellView(BrowseItemCell browseitemcell, int i)
    {
        super.updateCellView(browseitemcell, i);
        boolean flag = false;
        String s = browseitemcell.getBrowseItem().getSCUri();
        Integer integer;
        int j;
        if(s.equals(selectedItemScUri))
            flag = true;
        else
        if(selectedItemServiceId != null)
        {
            String s1 = lookupServiceDescriptorIdBySCUri(s);
            if(s1 != null && s1.equals(selectedItemServiceId))
                flag = true;
        }
        browseitemcell.setSelected(flag);
        integer = (Integer)automatorIds.get(s);
        if(integer != null)
            j = integer.intValue();
        else
            j = 0;
        browseitemcell.setId(j);
    }

    public void updateMusicMenuPadding()
    {
        if(adapterListView != null)
        {
            int i = getResources().getDimensionPixelOffset(0x7f09000e);
            int j = getResources().getDimensionPixelOffset(0x7f09005c);
            AbsListView abslistview = adapterListView;
            int k = adapterListView.getPaddingLeft();
            int l = adapterListView.getPaddingTop();
            int i1 = adapterListView.getPaddingRight();
            if(getResources().getConfiguration().orientation != 1 || DisplayController.getScreenType() > 1)
                j += i;
            abslistview.setPadding(k, l, i1, j);
        }
    }

    private static HashMap automatorIds;
    private String selectedItemScUri;
    private String selectedItemServiceId;

    static 
    {
        automatorIds = new HashMap();
        automatorIds.put(sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Settings_Alarms), Integer.valueOf(0x7f0a000c));
        automatorIds.put(sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Favorites), Integer.valueOf(0x7f0a0003));
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2;

import android.os.Bundle;
import android.view.View;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.browse.v2.pages.DataSourcePageFragment;
import com.sonos.acr.browse.v2.pages.MusicPageFactory;
import com.sonos.acr.browse.v2.pages.TabbedSearchFragment;
import com.sonos.acr.search.SearchController;
import com.sonos.acr.util.DisplayController;
import com.sonos.sclib.SCIBrowseDataSource;
import java.util.ArrayList;
import java.util.Stack;

// Referenced classes of package com.sonos.acr.browse.v2:
//            BrowseMusicFragment, PageFragment

public class SearchBrowseFragment extends BrowseMusicFragment
    implements com.sonos.acr.search.SearchController.SearchListener
{
    public class StackedSearchFragment extends TabbedSearchFragment
    {

        public void onActiveChanged(boolean flag)
        {
            super.onActiveChanged(flag);
            if(flag)
                onEnterSearch();
        }

        public void onDestroy()
        {
            super.onDestroy();
            searchFragment = null;
            onExitSearch();
            searchController.changeToAggregateSearchable();
        }

        public void onHiddenChanged(boolean flag)
        {
            super.onHiddenChanged(flag);
            if(flag)
                onExitSearch();
        }

        public void onViewCreated(View view, Bundle bundle)
        {
            super.onViewCreated(view, bundle);
            view.findViewById(0x7f0a013c).setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view1)
                {
                    closeSearch();
                    if(searchFragment.getBackNagivation() == com.sonos.acr.util.NavigationUtils.BackNavigationRouting.BACKS_TO_NOWPLAYING || searchFragment.getBackNagivation() == com.sonos.acr.util.NavigationUtils.BackNavigationRouting.BACKS_TO_QUEUE)
                    {
                        getSonosActivity().showNowPlaying();
                        if(searchFragment.getBackNagivation() == com.sonos.acr.util.NavigationUtils.BackNavigationRouting.BACKS_TO_QUEUE)
                            getSonosActivity().showQueue();
                    }
                }

                final StackedSearchFragment this$1;

                
                {
                    this$1 = StackedSearchFragment.this;
                    super();
                }
            }
);
        }

        final SearchBrowseFragment this$0;

        public StackedSearchFragment()
        {
            this$0 = SearchBrowseFragment.this;
            super();
        }
    }


    public SearchBrowseFragment()
    {
        super(0x7f010055);
        setPageFactory(new MusicPageFactory() {

            protected DataSourcePageFragment getDataSourcePageFragment(SCIBrowseDataSource scibrowsedatasource, com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType sclistpresentationtype)
            {
                if(DisplayController.getScreenType() != 0 && 
// JavaClassFileOutputException: get_constant: invalid tag

            final SearchBrowseFragment this$0;

            
            {
                this$0 = SearchBrowseFragment.this;
                super();
            }
        }
);
    }

    private void popToSearch()
    {
        if(pages.contains(searchFragment))
            for(; pages.size() > 0 && searchFragment != pages.peek(); popPage(true));
    }

    public void closeSearch()
    {
        if(searchFragment != null && pages.contains(searchFragment))
        {
            popToSearch();
            if(!pages.isEmpty())
                popPage(true);
        }
        if(searchController != null)
            searchController.searchClosed(getTopSCUri());
    }

    public int getCurrentCategoryIndex()
    {
        return -1;
    }

    public boolean isSearching()
    {
        boolean flag;
        if(searchFragment != null && !searchFragment.isRemoving() && !pages.empty() && pages.peek() == searchFragment)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onCategoriesChanged(ArrayList arraylist, int i)
    {
    }

    public void onCurrentCategoryChanged(ArrayList arraylist, int i)
    {
    }

    protected void onEnterSearch()
    {
    }

    protected void onExitSearch()
    {
        searchController.setRestrictedSearchable(null);
    }

    public void onPageUpdated(PageFragment pagefragment)
    {
        onPageUpdated(pagefragment);
        if(searchFragment == null)
            searchController.updateTopAggregateProvider(getTopSCUri());
    }

    public void onSearchInputFocused(View view)
    {
    }

    public void onSearchTermChanged()
    {
        if(searchFragment != null)
            showSearch(false);
    }

    public void onServiceOrderChanged()
    {
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        onViewCreated(view, bundle);
        searchController = getSonosActivity().getSearchController();
        getSonosActivity().getSearchController().addListener(this);
    }

    public void showSearch(boolean flag)
    {
        showSearch(flag, com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE);
    }

    public void showSearch(boolean flag, com.sonos.acr.util.NavigationUtils.BackNavigationRouting backnavigationrouting)
    {
        if(searchFragment != null)
        {
            popToSearch();
        } else
        {
            searchFragment = new StackedSearchFragment();
            searchFragment.setOnItemClickListener(this);
            StackedSearchFragment stackedsearchfragment = searchFragment;
            boolean flag1;
            if(backnavigationrouting != com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE)
                flag1 = true;
            else
                flag1 = false;
            pushPage(stackedsearchfragment, flag1);
            searchFragment.setBackNavigation(backnavigationrouting);
        }
        if(backnavigationrouting != com.sonos.acr.util.NavigationUtils.BackNavigationRouting.NONE)
            searchFragment.setBackNavigation(backnavigationrouting);
        if(flag)
            searchFragment.focusSearch();
    }

    SearchController searchController;
    StackedSearchFragment searchFragment;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.*;
import android.util.Log;
import android.view.*;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.browse.BrowseSectionHeader;
import com.sonos.acr.browse.indexers.BrowseSection;
import com.sonos.acr.browse.v2.PageFragment;
import com.sonos.acr.search.SearchBarView;
import com.sonos.acr.search.SearchController;
import com.sonos.acr.view.SonosViewPager;
import com.sonos.acr.view.TabPageIndicator;
import com.sonos.sclib.SCIAppReporting;
import com.sonos.sclib.sclib;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            SearchDataSourceListPageFragment

public class TabbedSearchFragment extends PageFragment
    implements DataSourceSectionedListPageFragment.OnHeaderClickListener, com.sonos.acr.browse.v2.PageFragment.PageFragmentListener
{
    class SearchableAdapter extends FragmentPagerAdapter
        implements com.sonos.acr.search.SearchController.SearchListener
    {

        public int getCount()
        {
            return categoryCount;
        }

        public int getCurrentCategoryIndex()
        {
            return -1;
        }

        public Fragment getItem(int i)
        {
            return (Fragment)categoryFragments.get(i);
        }

        public CharSequence getPageTitle(int i)
        {
            String s = ((SearchDataSourceListPageFragment)categoryFragments.get(i)).getTitle();
            String s1;
            if(s == null)
                s1 = "";
            else
                s1 = s.toUpperCase();
            return s1;
        }

        public boolean isGone()
        {
            int i = scopePager.getCurrentItem();
            boolean flag;
            if(i < 0 || i >= categoryFragments.size() || ((SearchDataSourceListPageFragment)categoryFragments.get(i)).isGone())
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void onCategoriesChanged(ArrayList arraylist, int i)
        {
            categoryCount = arraylist.size();
            for(int j = categoryFragments.size(); j < categoryCount; j++)
            {
                SearchDataSourceListPageFragment searchdatasourcelistpagefragment = new SearchDataSourceListPageFragment(j, searchController);
                categoryFragments.add(searchdatasourcelistpagefragment);
                searchdatasourcelistpagefragment.setOnItemClickListener(clickListener);
                searchdatasourcelistpagefragment.setHeaderClickListener(TabbedSearchFragment.this);
                searchdatasourcelistpagefragment.addPageFragmentListener(TabbedSearchFragment.this);
            }

            Log.e(
// JavaClassFileOutputException: get_constant: invalid tag

        public void onCurrentCategoryChanged(ArrayList arraylist, int i)
        {
            scopePager.setCurrentItem(i);
        }

        public void onSearchInputFocused(View view)
        {
        }

        public void onSearchTermChanged()
        {
        }

        public void onServiceOrderChanged()
        {
        }

        void setClickListener(com.sonos.acr.browse.v2.adapters.IDataSourceHandler.OnItemClickListener onitemclicklistener)
        {
            clickListener = onitemclicklistener;
            for(Iterator iterator = categoryFragments.iterator(); iterator.hasNext(); ((SearchDataSourceListPageFragment)iterator.next()).setOnItemClickListener(onitemclicklistener));
        }

        private int categoryCount;
        private final ArrayList categoryFragments = new ArrayList();
        private com.sonos.acr.browse.v2.adapters.IDataSourceHandler.OnItemClickListener clickListener;
        private SearchController searchController;
        final TabbedSearchFragment this$0;

        SearchableAdapter(FragmentManager fragmentmanager, SearchController searchcontroller)
        {
            this$0 = TabbedSearchFragment.this;
            super(fragmentmanager);
            searchController = searchcontroller;
        }
    }


    public TabbedSearchFragment()
    {
        PageFragment(0x7f01005a);
        onPageChangeListener = new android.support.v4.view.ViewPager.OnPageChangeListener() {

            public void onPageScrollStateChanged(int i)
            {
            }

            public void onPageScrolled(int i, float f, int j)
            {
            }

            public void onPageSelected(int i)
            {
                searchController.changeCurrentCategory(i);
            }

            final TabbedSearchFragment this$0;

            
            {
                this$0 = TabbedSearchFragment.this;
                super();
            }
        }
;
    }

    public void focusSearch()
    {
        if(searchBarView != null)
            searchBarView.requestSearchFocus();
    }

    public boolean isGone()
    {
        boolean flag;
        if(searchableAdapter != null && searchableAdapter.isGone())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean onBackPressed()
    {
        boolean flag;
        if(!searchController.isRestrictedSearchModeEnabled() && !searchController.isCurrentAggregate())
        {
            searchController.changeToAggregateSearchable();
            flag = true;
        } else
        {
            flag = onBackPressed();
        }
        return flag;
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        searchController = getSonosActivity().getSearchController();
        View view = layoutinflater.inflate(0x7f0300a0, viewgroup, false);
        scopePager = (SonosViewPager)view.findViewById(0x7f0a01d8);
        TabPageIndicator tabpageindicator = (TabPageIndicator)view.findViewById(0x7f0a01d7);
        tabpageindicator.setPager(scopePager);
        int i = getResources().getDimensionPixelSize(0x7f090067);
        if(i > 0)
        {
            tabpageindicator.setHorizontalFadingEdgeEnabled(true);
            tabpageindicator.setFadingEdgeLength(i);
        }
        searchableAdapter = new SearchableAdapter(getChildFragmentManager(), searchController);
        searchableAdapter.setClickListener(clickListener);
        scopePager.setAdapter(searchableAdapter);
        scopePager.addOnPageChangeListener(onPageChangeListener);
        searchBarView = (SearchBarView)view.findViewById(0x7f0a01d6);
        searchBarView.setController(searchController);
        hasShownKeyboard = false;
        return view;
    }

    public void onDestroyView()
    {
        onDestroyView();
        scopePager.setOnClickListener(null);
        scopePager = null;
        searchableAdapter = null;
        clickListener = null;
    }

    public void onHeaderClicked(BrowseSectionHeader browsesectionheader)
    {
        if(!searchController.isRestrictedSearchModeEnabled())
            if(searchController.isCurrentAggregate())
            {
                BrowseSection browsesection = browsesectionheader.getBrowseSection();
                if(browsesection != null && browsesection.canPush())
                {
                    sclib.getAppReportingInstance().focus(com.sonos.sclib.SCIAppReporting.SCViewID.SEARCH_MORE);
                    searchController.changeCurrentSearchable(sclib.SCLibGetBrowseCPIDFromSCUri(browsesection.getBrowseSCUri()));
                }
            } else
            {
                searchController.changeToAggregateSearchable();
            }
    }

    public void onPageInvalidated(PageFragment pagefragment)
    {
        invalidatePage();
    }

    public void onPageUpdated(PageFragment pagefragment)
    {
    }

    public void onStart()
    {
        onStart();
        searchController.addListener(searchableAdapter);
        if(!hasShownKeyboard && searchBarView != null)
        {
            searchBarView.requestSearchFocus();
            hasShownKeyboard = true;
        }
    }

    public void onStop()
    {
        onStop();
        searchController.removeListener(searchableAdapter);
    }

    public void setOnItemClickListener(com.sonos.acr.browse.v2.adapters.IDataSourceHandler.OnItemClickListener onitemclicklistener)
    {
        clickListener = onitemclicklistener;
        if(searchableAdapter != null)
            searchableAdapter.setClickListener(clickListener);
    }

    protected com.sonos.acr.browse.v2.adapters.IDataSourceHandler.OnItemClickListener clickListener;
    private boolean hasShownKeyboard;
    protected android.support.v4.view.ViewPager.OnPageChangeListener onPageChangeListener;
    protected SonosViewPager scopePager;
    private SearchBarView searchBarView;
    protected SearchController searchController;
    private SearchableAdapter searchableAdapter;

}

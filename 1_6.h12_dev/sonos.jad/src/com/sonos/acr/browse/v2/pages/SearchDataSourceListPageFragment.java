// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.sonos.acr.browse.BrowseSectionHeader;
import com.sonos.acr.browse.indexers.BrowseGroupSectionIndexer;
import com.sonos.acr.browse.indexers.BrowseSection;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.browse.v2.view.SearchBrowseListItemCell;
import com.sonos.acr.search.SearchController;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;
import java.util.ArrayList;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourceSectionedListPageFragment

public class SearchDataSourceListPageFragment extends DataSourceSectionedListPageFragment
    implements com.sonos.acr.search.SearchController.SearchListener
{

    public SearchDataSourceListPageFragment(int i, SearchController searchcontroller)
    {
        isAggregate = false;
        categoryIndex = i;
        searchController = searchcontroller;
    }

    private Spannable createHighlightedString(String s, String s1)
    {
        SpannableString spannablestring = new SpannableString(s);
        int i = s.indexOf(s1);
        if(i != -1)
            spannablestring.setSpan(new ForegroundColorSpan(getResources().getColor(0x7f080024)), i, i + s1.length(), 0);
        return spannablestring;
    }

    private boolean isSectioned()
    {
        boolean flag;
        if(searchController == null || !searchController.isRestrictedSearchModeEnabled())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public BrowseItemCell createCellView(int i)
    {
        return new SearchBrowseListItemCell(themedContext);
    }

    public BrowseSectionHeader createHeaderView(int i)
    {
        return new BrowseSectionHeader(themedContext) {

            protected int getLayoutId()
            {
                return 0x7f030067;
            }

            public void updateSectionInfo(BrowseSection browsesection)
            {
                super.updateSectionInfo(browsesection);
                if(!searchController.isRestrictedSearchModeEnabled())
                {
                    sectionHeaderDrillDown.setVisibility(0);
                    if(isAggregate)
                    {
                        sectionHeaderDrillDown.setText(getText(0x7f0c0007));
                        arrowLeft.setVisibility(8);
                        arrowRight.setVisibility(0);
                    } else
                    {
                        sectionHeaderDrillDown.setText(getText(0x7f0c00c4));
                        arrowLeft.setVisibility(0);
                        arrowRight.setVisibility(8);
                    }
                } else
                {
                    arrowLeft.setVisibility(8);
                    arrowRight.setVisibility(8);
                    sectionHeaderDrillDown.setVisibility(8);
                }
            }

            View arrowLeft;
            View arrowRight;
            TextView sectionHeaderDrillDown;
            final SearchDataSourceListPageFragment this$0;

            
            {
                this$0 = SearchDataSourceListPageFragment.this;
                super(context);
                sectionHeaderDrillDown = (TextView)findViewById(0x7f0a0161);
                arrowLeft = findViewById(0x7f0a0160);
                arrowRight = findViewById(0x7f0a0162);
            }
        }
;
    }

    protected BrowseGroupSectionIndexer createNewIndexer()
    {
        BrowseGroupSectionIndexer browsegroupsectionindexer;
        if(isSectioned())
            browsegroupsectionindexer = super.createNewIndexer();
        else
            browsegroupsectionindexer = null;
        return browsegroupsectionindexer;
    }

    public int getCurrentCategoryIndex()
    {
        return categoryIndex;
    }

    protected int getLayoutId()
    {
        return 0x7f030050;
    }

    public volatile CharSequence getTitle()
    {
        return getTitle();
    }

    public String getTitle()
    {
        String s;
        if(categoryIndex < searchController.getCategoryCount() && categoryIndex >= 0)
            s = searchController.getTitleForCategory(categoryIndex);
        else
            s = "";
        return s;
    }

    public void onCategoriesChanged(ArrayList arraylist, int i)
    {
        updateSearch(true);
    }

    public void onCurrentCategoryChanged(ArrayList arraylist, int i)
    {
    }

    public void onSearchInputFocused(View view)
    {
    }

    public void onSearchTermChanged()
    {
        updateSearch(false);
    }

    public void onServiceOrderChanged()
    {
        updateSearch(true);
    }

    public void onStart()
    {
        super.onStart();
        searchController.addListener(this);
        updateSearch(false);
    }

    public void onStop()
    {
        super.onStop();
        searchController.removeListener(this);
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        browseLinkText = (TextView)view.findViewById(0x7f0a0120);
        adapterListView.setFastScrollEnabled(false);
        adapterListView.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view1, MotionEvent motionevent)
            {
                View view2 = getActivity().getCurrentFocus();
                if(view2 != null && view2.getId() == 0x7f0a0164)
                    view2.clearFocus();
                return false;
            }

            final SearchDataSourceListPageFragment this$0;

            
            {
                this$0 = SearchDataSourceListPageFragment.this;
                super();
            }
        }
);
        ListView listview = (ListView)adapterListView;
        Resources resources = getResources();
        int i;
        if(isSectioned())
            i = 0x7f020014;
        else
            i = 0x7f020016;
        listview.setDivider(resources.getDrawable(i));
        listview.setDividerHeight((int)getResources().getDimension(0x7f090061));
        super.onViewCreated(view, bundle);
        browseLinkText.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                searchController.changeToAggregateSearchable();
            }

            final SearchDataSourceListPageFragment this$0;

            
            {
                this$0 = SearchDataSourceListPageFragment.this;
                super();
            }
        }
);
    }

    public void setBrowseDataSource(SCIBrowseDataSource scibrowsedatasource)
    {
        if(scibrowsedatasource != null && isActive())
        {
            SCIAggregateBrowseDataSource sciaggregatebrowsedatasource = (SCIAggregateBrowseDataSource)LibraryUtils.cast(scibrowsedatasource, com/sonos/sclib/SCIAggregateBrowseDataSource);
            if(sciaggregatebrowsedatasource != null)
            {
                int i = 0;
                View view = getActivity().findViewById(0x7f0a002f);
                if(view != null)
                    i = view.getHeight() / (int)getResources().getDimension(0x7f09000f);
                sciaggregatebrowsedatasource.setNumRecommendedPresentationItems(i);
                SLog.d(LOG_TAG, (new StringBuilder()).append("setBrowseDataSource: recommended number of aggregated presentation items is ").append(i).toString());
            }
        }
        super.setBrowseDataSource(scibrowsedatasource);
        ((ListView)adapterListView).setAdapter(dataSourceAdapter);
    }

    protected void updateBrowseInfotext()
    {
        String s;
        String s1;
        super.updateBrowseInfotext();
        if(!isAggregate && browseDataSource != null && browseDataSource.isValid() && browseDataSource.getNumItems() == 0L && !StringUtils.isEmptyOrNull(searchController.getSearchTermForCategory(categoryIndex)) && !searchController.isRestrictedSearchModeEnabled())
        {
            browseLinkText.setText(0x7f0c00c5);
            browseLinkText.setVisibility(0);
        } else
        {
            browseLinkText.setVisibility(8);
        }
        s = searchController.getTitleForCategory(categoryIndex);
        s1 = browseInfoText.getText().toString();
        if(!StringUtils.isEmptyOrNull(searchController.getSearchTermForCategory(categoryIndex))) goto _L2; else goto _L1
_L1:
        browseInfoText.setVisibility(8);
_L4:
        if(browseInfoText.isShown() && StringUtils.isNotEmptyOrNull(s1))
            browseInfoText.setText(createHighlightedString(s1, s), android.widget.TextView.BufferType.SPANNABLE);
        return;
_L2:
        if(browseDataSource != null && !browseDataSource.isValid())
        {
            browseInfoText.setVisibility(0);
            if(searchController.getCurrentSearchable() != null)
            {
                int i;
                Object aobj[];
                if(isAggregate)
                    i = 0x7f0c00c7;
                else
                    i = 0x7f0c00c8;
                aobj = new Object[2];
                aobj[0] = s;
                aobj[1] = searchController.getCurrentSearchable().getTitle();
                s1 = String.format(getString(i, aobj), new Object[0]);
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void updateCellView(BrowseItemCell browseitemcell, int i)
    {
        ((SearchBrowseListItemCell)browseitemcell).setSectioned(isSectioned());
    }

    public void updateSearch(boolean flag)
    {
        if(categoryIndex < searchController.getCategoryCount() && categoryIndex >= 0)
        {
            String s = searchController.getSCUriForCategory(categoryIndex);
            isAggregate = searchController.isCurrentAggregate();
            if(browseDataSource == null || flag || !browseDataSource.getSCUri().equals(s))
            {
                searchController.getSearchTermForCategory(categoryIndex);
                setBrowseDataSource(LibraryUtils.createBrowseDataSource(s, searchController.getTitleForCategory(categoryIndex)));
            }
        } else
        {
            setBrowseDataSource(null);
        }
    }

    private TextView browseLinkText;
    private final int categoryIndex;
    boolean isAggregate;
    private final SearchController searchController;

}

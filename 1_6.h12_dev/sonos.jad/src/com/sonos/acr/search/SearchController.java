// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.search;

import android.view.View;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;
import java.util.*;

public class SearchController
    implements com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener
{
    public static interface SearchListener
    {

        public abstract int getCurrentCategoryIndex();

        public abstract void onCategoriesChanged(ArrayList arraylist, int i);

        public abstract void onCurrentCategoryChanged(ArrayList arraylist, int i);

        public abstract void onSearchInputFocused(View view);

        public abstract void onSearchTermChanged();

        public abstract void onServiceOrderChanged();
    }


    public SearchController()
    {
        searchables = new ArrayList();
        categories = new ArrayList();
        listeners = new WeakHashSet();
        currentSearchableIndex = -1;
        currentCategoryIndex = -1;
        currentSearchText = "";
        lastSelectedCurrentCategory = DEFAULT_CATEGORY_CNAME;
    }

    private int findSearchableIndexByCPID(String s)
    {
        int i;
        if(searchables == null)
            break MISSING_BLOCK_LABEL_49;
        i = 0;
_L3:
        if(i >= searchables.size())
            break MISSING_BLOCK_LABEL_49;
        if(!((SCISearchable)searchables.get(i)).getBrowseCPID().equals(s)) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        i++;
          goto _L3
        i = -1;
          goto _L1
    }

    private void fireOnSearchTermChanged()
    {
        SearchListener searchlistener;
label0:
        {
            searchlistener = null;
            if(currentCategoryIndex == -1)
                break label0;
            Iterator iterator1 = listeners.iterator();
            SearchListener searchlistener2;
            do
            {
                if(!iterator1.hasNext())
                    break label0;
                searchlistener2 = (SearchListener)iterator1.next();
            } while(searchlistener2.getCurrentCategoryIndex() != currentCategoryIndex);
            searchlistener2.onSearchTermChanged();
            searchlistener = searchlistener2;
        }
        Iterator iterator = listeners.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            SearchListener searchlistener1 = (SearchListener)iterator.next();
            if(searchlistener1 != searchlistener)
                searchlistener1.onSearchTermChanged();
        } while(true);
    }

    private static SCISearchableCategory getFirstAggregateCategoryWithTerm(SCIPropertyBag scipropertybag)
    {
        ArrayList arraylist = LibraryUtils.toList(LibraryUtils.getHousehold().getAggregatedSearchable().getCategories(), com/sonos/sclib/SCISearchableCategory);
        if(arraylist == null) goto _L2; else goto _L1
_L1:
        Iterator iterator = arraylist.iterator();
_L5:
        if(!iterator.hasNext()) goto _L2; else goto _L3
_L3:
        SCISearchableCategory scisearchablecategory = (SCISearchableCategory)iterator.next();
        if(!StringUtils.isNotEmptyOrNull(scipropertybag.getStrProp(scisearchablecategory.getCanonicalName()))) goto _L5; else goto _L4
_L4:
        return scisearchablecategory;
_L2:
        scisearchablecategory = null;
        if(true) goto _L4; else goto _L6
_L6:
    }

    private static int getSearchableIndex(ArrayList arraylist, String s)
    {
        int i;
        if(!StringUtils.isNotEmptyOrNull(s) || arraylist == null)
            break MISSING_BLOCK_LABEL_47;
        i = 0;
_L3:
        if(i >= arraylist.size())
            break MISSING_BLOCK_LABEL_47;
        if(!s.equals(((SCISearchable)arraylist.get(i)).getBrowseCPID())) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        i++;
          goto _L3
        i = -1;
          goto _L1
    }

    private void onCategoriesChanged()
    {
        int i = getCurrentCategoryIndex();
        for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((SearchListener)iterator.next()).onCategoriesChanged(categories, i));
    }

    private void onCurrentCategoryChanged()
    {
        int i = getCurrentCategoryIndex();
        for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((SearchListener)iterator.next()).onCurrentCategoryChanged(categories, i));
    }

    private void onSearchablesChanged()
    {
        currentCategoryIndex = -1;
        int i = getCurrentSearchableIndex();
        if(i != -1)
            categories = LibraryUtils.toList(((SCISearchable)searchables.get(i)).getCategories(), com/sonos/sclib/SCISearchableCategory);
        else
            SLog.e(LOG_TAG, "onSearchablesChanged has no current index");
        onCategoriesChanged();
    }

    private void updateCategoryForTopAggregateProvider(SCISearchable scisearchable)
    {
        if(StringUtils.isEmptyOrNull(currentSearchText) && aggregateSearchData == null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        ArrayList arraylist;
        int i;
        SCISearchable scisearchable1 = getCurrentSearchable();
        if(scisearchable1 == null || !scisearchable1.getIsAggregatedSearchable())
            continue; /* Loop/switch isn't completed */
        arraylist = LibraryUtils.toList(scisearchable.getCategories(), com/sonos/sclib/SCISearchableCategory);
        i = 0;
_L4:
        if(i < categories.size())
        {
label0:
            {
                SCISearchableCategory scisearchablecategory = (SCISearchableCategory)categories.get(i);
                Iterator iterator = arraylist.iterator();
                SCISearchableCategory scisearchablecategory1;
                do
                {
                    if(!iterator.hasNext())
                        break label0;
                    scisearchablecategory1 = (SCISearchableCategory)iterator.next();
                } while(!scisearchablecategory.getCanonicalName().equals(scisearchablecategory1.getCanonicalName()));
                changeCurrentCategory(i);
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
        i++;
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void addListener(SearchListener searchlistener)
    {
        if(listeners.add(searchlistener) && searchables != null && categories != null)
            searchlistener.onCategoriesChanged(categories, getCurrentCategoryIndex());
    }

    public void changeCurrentCategory(int i)
    {
        if(i != currentCategoryIndex && i != -1 && i < categories.size())
        {
            currentCategoryIndex = i;
            lastSelectedCurrentCategory = ((SCISearchableCategory)categories.get(currentCategoryIndex)).getCanonicalName();
            onCurrentCategoryChanged();
        }
    }

    public boolean changeCurrentSearchable(String s)
    {
        int i = getCurrentSearchableIndex();
        if(isRestrictedSearchMode || i != -1 && ((SCISearchable)searchables.get(i)).getBrowseCPID().equals(s)) goto _L2; else goto _L1
_L1:
        int j = getSearchableIndex(searchables, s);
        if(j == -1) goto _L2; else goto _L3
_L3:
        boolean flag;
        currentSearchableIndex = j;
        currentCategoryIndex = -1;
        onSearchablesChanged();
        flag = true;
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void changeToAggregateSearchable()
    {
        changeCurrentSearchable(sclibConstants.SC_SEARCH_AGGREGATED_CPUDN);
    }

    public int getCategoryCount()
    {
        return categories.size();
    }

    public int getCurrentCategoryIndex()
    {
        int i = -1;
        if(currentCategoryIndex != i || categories == null) goto _L2; else goto _L1
_L1:
        if(categories.size() != 0) goto _L4; else goto _L3
_L3:
        return i;
_L4:
        String s = lastSelectedCurrentCategory;
        if(s != null)
            for(i = 0; i < categories.size(); i++)
                if(s.equals(((SCISearchableCategory)categories.get(i)).getCanonicalName()))
                    continue; /* Loop/switch isn't completed */

        i = 0;
        continue; /* Loop/switch isn't completed */
_L2:
        i = currentCategoryIndex;
        if(true) goto _L3; else goto _L5
_L5:
    }

    public SCISearchable getCurrentSearchable()
    {
        int i = getCurrentSearchableIndex();
        SCISearchable scisearchable;
        if(i != -1 && searchables != null && searchables.size() > i)
            scisearchable = (SCISearchable)searchables.get(i);
        else
            scisearchable = null;
        return scisearchable;
    }

    public int getCurrentSearchableIndex()
    {
        int i = -1;
        if(currentSearchableIndex == i && searchables != null)
        {
            if(searchables.size() != 0)
                i = 0;
        } else
        {
            i = currentSearchableIndex;
        }
        return i;
    }

    public String getSCUriForCategory(int i)
    {
        if(i >= categories.size()) goto _L2; else goto _L1
_L1:
        SCISearchableCategory scisearchablecategory = (SCISearchableCategory)categories.get(i);
        if(scisearchablecategory == null) goto _L4; else goto _L3
_L3:
        String s = scisearchablecategory.getSCUriForSearchTerm(getSearchTermForCategory(i).trim());
_L5:
        return s;
_L4:
        SLog.e(LOG_TAG, "getSCUriForCategory found a null category");
_L6:
        s = "";
        if(true) goto _L5; else goto _L2
_L2:
        SLog.e(LOG_TAG, (new StringBuilder()).append("getSCUriForCategory index ").append(i).append(" is out of range:").append(categories.size()).toString());
          goto _L6
    }

    public String getSearchTermForCategory(int i)
    {
        String s;
        if(i < categories.size())
        {
            if(aggregateSearchData != null)
                s = aggregateSearchData.getProperty(((SCISearchableCategory)categories.get(i)).getCanonicalName(), "");
            else
                s = currentSearchText;
        } else
        {
            s = "";
        }
        return s;
    }

    public String getTitleForCategory(int i)
    {
        String s;
        if(i < categories.size())
            s = ((SCISearchableCategory)categories.get(i)).getName();
        else
            s = "";
        return s;
    }

    public boolean isCurrentAggregate()
    {
        SCISearchable scisearchable = getCurrentSearchable();
        boolean flag;
        if(scisearchable != null && scisearchable.getIsAggregatedSearchable())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isRestrictedSearchModeEnabled()
    {
        return isRestrictedSearchMode;
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnSearchablesListChanged)
        {
            ArrayList arraylist = household.getAllSearchables();
            SCISearchable scisearchable = getCurrentSearchable();
            int i;
            if(scisearchable != null)
                i = getSearchableIndex(arraylist, scisearchable.getBrowseCPID());
            else
                i = -1;
            if(!isRestrictedSearchMode || i == -1)
            {
                isRestrictedSearchMode = false;
                currentSearchableIndex = i;
                searchables = arraylist;
                onSearchablesChanged();
            }
        }
    }

    public void removeListener(SearchListener searchlistener)
    {
        listeners.remove(searchlistener);
    }

    public void resetSearchToDefaults()
    {
        if(StringUtils.isEmptyOrNull(currentSearchText) && aggregateSearchData == null)
            lastSelectedCurrentCategory = DEFAULT_CATEGORY_CNAME;
        if(!changeCurrentSearchable(DEFAULT_SEARCHABLE_CPID))
            onSearchablesChanged();
    }

    public void searchClosed(String s)
    {
        if(StringUtils.isEmptyOrNull(currentSearchText))
            updateTopAggregateProvider(s);
    }

    public void searchInputGotFocus(View view)
    {
        for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((SearchListener)iterator.next()).onSearchInputFocused(view));
    }

    public void setAggregateSearchData(SCIPropertyBag scipropertybag)
    {
        changeToAggregateSearchable();
        aggregateSearchData = LibraryUtils.createProperties(scipropertybag);
        if(scipropertybag != null)
        {
            SCISearchableCategory scisearchablecategory = getFirstAggregateCategoryWithTerm(scipropertybag);
            if(scisearchablecategory != null)
            {
                currentSearchableIndex = -1;
                lastSelectedCurrentCategory = scisearchablecategory.getCanonicalName();
            }
            if(!changeCurrentSearchable(DEFAULT_SEARCHABLE_CPID))
                onSearchablesChanged();
        } else
        {
            fireOnSearchTermChanged();
        }
    }

    public void setRestrictedSearchable(SCISearchable scisearchable)
    {
        boolean flag = false;
        if(scisearchable == null) goto _L2; else goto _L1
_L1:
        if(!isRestrictedSearchMode)
        {
            isRestrictedSearchMode = true;
            currentSearchableIndex = 0;
            SCISearchable ascisearchable[] = new SCISearchable[1];
            ascisearchable[0] = scisearchable;
            searchables = new ArrayList(Arrays.asList(ascisearchable));
            flag = true;
        }
_L4:
        if(flag)
        {
            setSearchTerm("");
            onSearchablesChanged();
        }
        return;
_L2:
        if(isRestrictedSearchMode)
        {
            isRestrictedSearchMode = false;
            currentSearchableIndex = -1;
            Household household = LibraryUtils.getHousehold();
            if(household != null)
            {
                searchables = household.getAllSearchables();
            } else
            {
                SLog.e(LOG_TAG, "Cannot reset searchables list: household is null!");
                searchables = new ArrayList();
            }
            flag = true;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setSearchTerm(String s)
    {
        if(aggregateSearchData != null)
        {
            int i = getCurrentCategoryIndex();
            if(i >= 0)
            {
                String s1 = ((SCISearchableCategory)categories.get(i)).getCanonicalName();
                String s2 = aggregateSearchData.getProperty(s1);
                boolean flag = aggregateSearchData.containsKey(s1);
                if(flag && !s2.equals(s) || !flag && StringUtils.isNotEmptyOrNull(s))
                    aggregateSearchData = null;
            }
        }
        if(aggregateSearchData == null && !currentSearchText.equals(s))
        {
            currentSearchText = s;
            fireOnSearchTermChanged();
        }
    }

    public void updateTopAggregateProvider(String s)
    {
        String s1 = LibraryUtils.getSearchableCPID(s);
        Household household = LibraryUtils.getHousehold();
        if(household != null)
            if(s1 != null)
            {
                int i = findSearchableIndexByCPID(s1);
                if(i >= 0)
                {
                    topSearchableCPID = s1;
                    SCISearchable scisearchable = (SCISearchable)searchables.get(i);
                    household.setTopSearchable(scisearchable);
                    updateCategoryForTopAggregateProvider(scisearchable);
                    SLog.i(LOG_TAG, "Dispatching Service Order Change!");
                    for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((SearchListener)iterator.next()).onServiceOrderChanged());
                }
            } else
            if(s != null && s1 == null)
            {
                household.setTopSearchable(null);
                topSearchableCPID = null;
            }
    }

    public static final int CURRENT_UNKNOWN = -1;
    private static final String DEFAULT_CATEGORY_CNAME;
    private static final String DEFAULT_SEARCHABLE_CPID;
    private static final String LOG_TAG = com/sonos/acr/search/SearchController.getSimpleName();
    private Properties aggregateSearchData;
    private ArrayList categories;
    private int currentCategoryIndex;
    private String currentSearchText;
    private int currentSearchableIndex;
    private boolean isRestrictedSearchMode;
    private String lastSelectedCurrentCategory;
    private WeakHashSet listeners;
    private ArrayList searchables;
    private String topSearchableCPID;

    static 
    {
        DEFAULT_CATEGORY_CNAME = sclibConstants.SC_SEARCH_CATEGORY_ARTISTS;
        DEFAULT_SEARCHABLE_CPID = sclibConstants.SC_SEARCH_AGGREGATED_CPUDN;
    }
}

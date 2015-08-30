// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import com.sonos.acr.browse.v2.MusicMenuFragment;
import com.sonos.acr.util.BrowseUtils;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            PageFactory, DataSourceEditListPageFragment, DataSourceEditGridPageFragment, DataSourceGridPageFragment, 
//            DataSourceHeroViewListPageFragment, DataSourceEditorialListPageFragment, DataSourceAlbumViewListPageFragment, DataSourceSectionedListPageFragment, 
//            DataSourceListPageFragment, DataSourcePageFragment

public class MusicPageFactory
    implements PageFactory
{

    public MusicPageFactory()
    {
    }

    public DataSourcePageFragment createBrowsePage(SCIBrowseDataSource scibrowsedatasource)
    {
        return getDataSourcePageFragment(scibrowsedatasource, BrowseUtils.getPresentationType(scibrowsedatasource));
    }

    protected DataSourcePageFragment getDataSourcePageFragment(SCIBrowseDataSource scibrowsedatasource, com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType sclistpresentationtype)
    {
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCIBrowseListPresentationMap$SCListPresentationType[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCIBrowseListPresentationMap$SCListPresentationType = new int[com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType.values().length];
                NoSuchFieldError nosuchfielderror4;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIBrowseListPresentationMap$SCListPresentationType[com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType.PRESENTATION_GRID.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIBrowseListPresentationMap$SCListPresentationType[com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType.PRESENTATION_HERO.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIBrowseListPresentationMap$SCListPresentationType[com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType.PRESENTATION_EDITORIAL.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCIBrowseListPresentationMap$SCListPresentationType[com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType.PRESENTATION_ALBUM.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                $SwitchMap$com$sonos$sclib$SCIBrowseListPresentationMap$SCListPresentationType[com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType.PRESENTATION_LIST.ordinal()] = 5;
_L2:
                return;
                nosuchfielderror4;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType[sclistpresentationtype.ordinal()];
        JVM INSTR tableswitch 1 4: default 40
    //                   1 61
    //                   2 95
    //                   3 107
    //                   4 119;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        Object obj;
        if(BrowseUtils.dataSourceHasActionCategoryType(scibrowsedatasource, sclibConstants.SC_ACTION_CATEGORY_EDIT))
            obj = new DataSourceEditListPageFragment(scibrowsedatasource);
        else
        if(LibraryUtils.cast(scibrowsedatasource, com/sonos/sclib/SCIPowerscrollDataSource) != null || LibraryUtils.cast(scibrowsedatasource, com/sonos/sclib/SCIBrowseGroupsInfo) != null)
        {
            if(scibrowsedatasource.getSCUri().equals(sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Root)))
                obj = new MusicMenuFragment();
            else
                obj = new DataSourceSectionedListPageFragment(scibrowsedatasource);
        } else
        {
            obj = new DataSourceListPageFragment(scibrowsedatasource);
        }
_L7:
        return ((DataSourcePageFragment) (obj));
_L2:
        if(BrowseUtils.dataSourceHasActionCategoryType(scibrowsedatasource, sclibConstants.SC_ACTION_CATEGORY_EDIT))
            obj = new DataSourceEditGridPageFragment(scibrowsedatasource);
        else
            obj = new DataSourceGridPageFragment(scibrowsedatasource);
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new DataSourceHeroViewListPageFragment(scibrowsedatasource);
        continue; /* Loop/switch isn't completed */
_L4:
        obj = new DataSourceEditorialListPageFragment(scibrowsedatasource);
        continue; /* Loop/switch isn't completed */
_L5:
        obj = new DataSourceAlbumViewListPageFragment(scibrowsedatasource);
        if(true) goto _L7; else goto _L6
_L6:
    }
}

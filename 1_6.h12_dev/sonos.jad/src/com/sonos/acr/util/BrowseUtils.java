// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import com.sonos.acr.browse.v2.actions.ActionItem;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.util:
//            LibraryUtils

public class BrowseUtils
{

    public BrowseUtils()
    {
    }

    public static String browseUri(String s, String s1)
    {
        return (new StringBuilder("x-sonos-scuri")).append("://asyncbrowse?oid=").append(s).append("&cpudn=").append(s1).toString();
    }

    public static boolean dataSourceHasActionCategoryType(SCIBrowseDataSource scibrowsedatasource, String s)
    {
        SCIStringArray scistringarray;
        int i;
        scistringarray = scibrowsedatasource.getSupportedActionCategories();
        if(scistringarray == null)
            break MISSING_BLOCK_LABEL_47;
        i = 0;
_L3:
        if((long)i >= scistringarray.size())
            break MISSING_BLOCK_LABEL_47;
        if(!s.equals(scistringarray.getAt(i))) goto _L2; else goto _L1
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

    public static com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType getPresentationType(SCIBrowseDataSource scibrowsedatasource)
    {
        Household household = LibraryUtils.getHousehold();
        if(scibrowsedatasource == null || household == null) goto _L2; else goto _L1
_L1:
        SCIBrowseListPresentationMap scibrowselistpresentationmap = household.getBrowseListPresentationMap();
        if(scibrowselistpresentationmap == null) goto _L2; else goto _L3
_L3:
        com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType sclistpresentationtype = scibrowselistpresentationmap.getPresentationForDataSource(scibrowsedatasource);
_L5:
        return sclistpresentationtype;
_L2:
        sclistpresentationtype = com.sonos.sclib.SCIBrowseListPresentationMap.SCListPresentationType.PRESENTATION_LIST;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static boolean isPlayAction(ActionItem actionitem)
    {
        boolean flag;
        flag = false;
        break MISSING_BLOCK_LABEL_2;
        if(actionitem != null && (actionitem.getActionID().equals(sclib.SC_ACTIONID_PLAY_NOW) || actionitem.getActionID().equals(sclib.SC_ACTIONID_PLAY_NOW_TV) || actionitem.getActionID().equals(sclib.SC_ACTIONID_PLAYMENU_PLAY_NOW) || actionitem.getActionID().equals(sclib.SC_ACTIONID_PLAYMENU_PLAY_NOW_TV)))
            flag = true;
        return flag;
    }

    private static final String LOG_TAG = com/sonos/acr/util/BrowseUtils.getSimpleName();
    public static final String MUSIC_LIBRARY_ROOT = "musicLibrary";
    public static final String URI_HOST = "x-sonos-scuri";

}

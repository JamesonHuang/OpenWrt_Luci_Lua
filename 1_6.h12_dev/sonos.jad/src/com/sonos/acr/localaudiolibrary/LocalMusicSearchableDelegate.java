// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.localaudiolibrary;

import android.content.Context;
import com.sonos.acr.localaudiolibrary.adapters.LocalMediaRootAdapter;
import com.sonos.acr.util.StringUtils;
import com.sonos.sclib.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

// Referenced classes of package com.sonos.acr.localaudiolibrary:
//            LocalMusicBrowseItem

public class LocalMusicSearchableDelegate extends SCILocalMusicSearchableDelegateSwigBase
{

    public LocalMusicSearchableDelegate(Context context1)
    {
        context = context1;
    }

    public SCIStringArray getCategoryIDs()
    {
        SCIStringArray scistringarray = sclib.createSCStringArray();
        for(Iterator iterator = categoryMap.keySet().iterator(); iterator.hasNext(); scistringarray.append((String)iterator.next()));
        return scistringarray;
    }

    public String getLogoURI()
    {
        return "ACR_LOCAL_LIBRARY";
    }

    public String getTitle()
    {
        return LocalMediaRootAdapter.createRootItem(context, "root").getTitle();
    }

    public String objectIdForSearch(String s, String s1)
    {
        if(!StringUtils.isNotEmptyOrNull(s1))
            break MISSING_BLOCK_LABEL_18;
        String s2 = URLEncoder.encode(s1, "UTF-8");
        s1 = s2;
_L2:
        return (new StringBuilder()).append(s).append(":").append("\"").append(s1).append("\"").toString();
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        unsupportedencodingexception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final String LOG_TAG = com/sonos/acr/localaudiolibrary/LocalMusicSearchableDelegate.getSimpleName();
    public static final HashMap categoryMap;
    Context context;

    static 
    {
        categoryMap = new HashMap();
        categoryMap.put(sclibConstants.SC_SEARCH_CATEGORY_ARTISTS, "artists");
        categoryMap.put(sclibConstants.SC_SEARCH_CATEGORY_TRACKS, "tracks");
        categoryMap.put(sclibConstants.SC_SEARCH_CATEGORY_ALBUMS, "albums");
        categoryMap.put(sclibConstants.SC_SEARCH_CATEGORY_GENRES, "genres");
        categoryMap.put(sclibConstants.SC_SEARCH_CATEGORY_PODCASTS, "podcasts");
    }
}

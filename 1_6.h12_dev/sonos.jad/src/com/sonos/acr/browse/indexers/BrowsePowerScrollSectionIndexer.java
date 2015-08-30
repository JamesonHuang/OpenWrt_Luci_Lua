// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.indexers;

import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;
import java.util.ArrayList;

// Referenced classes of package com.sonos.acr.browse.indexers:
//            BrowseSectionIndexer, BrowseSection

public class BrowsePowerScrollSectionIndexer extends BrowseSectionIndexer
{

    public BrowsePowerScrollSectionIndexer(SCIBrowseDataSource scibrowsedatasource)
    {
        super(scibrowsedatasource);
    }

    protected BrowseSection[] createBrowseSections(SCIBrowseDataSource scibrowsedatasource)
    {
        SCIPowerscrollDataSource scipowerscrolldatasource = (SCIPowerscrollDataSource)LibraryUtils.cast(scibrowsedatasource, com/sonos/sclib/SCIPowerscrollDataSource);
        ArrayList arraylist = new ArrayList(POWERSCROLL_SECTIONS.length);
        String as[] = POWERSCROLL_SECTIONS;
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            String s = as[j];
            if(scipowerscrolldatasource.getCountForPrefix(s) > 0L)
                arraylist.add(s);
        }

        BrowseSection abrowsesection[] = new BrowseSection[arraylist.size()];
        for(int k = 0; k < abrowsesection.length; k++)
            abrowsesection[k] = new BrowseSection(scipowerscrolldatasource.getStartIndexForPrefix((String)arraylist.get(k)), (String)arraylist.get(k));

        return abrowsesection;
    }

    public static final String POWERSCROLL_SECTIONS[];

    static 
    {
        String s = sclibConstants.SCIPOWERSCROLL_PREFIX_CHARS;
        POWERSCROLL_SECTIONS = new String[s.length()];
        for(int i = 0; i < s.length(); i++)
            POWERSCROLL_SECTIONS[i] = s.substring(i, i + 1);

    }
}

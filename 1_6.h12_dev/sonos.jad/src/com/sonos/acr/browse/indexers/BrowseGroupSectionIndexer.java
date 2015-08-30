// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.indexers;

import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.SCIBrowseDataSource;
import com.sonos.sclib.SCIBrowseGroupsInfo;
import java.util.ArrayList;

// Referenced classes of package com.sonos.acr.browse.indexers:
//            BrowseSectionIndexer, BrowseSection

public class BrowseGroupSectionIndexer extends BrowseSectionIndexer
{

    public BrowseGroupSectionIndexer(SCIBrowseDataSource scibrowsedatasource)
    {
        super(scibrowsedatasource);
    }

    protected BrowseSection[] createBrowseSections(SCIBrowseDataSource scibrowsedatasource)
    {
        SCIBrowseGroupsInfo scibrowsegroupsinfo = (SCIBrowseGroupsInfo)LibraryUtils.cast(scibrowsedatasource, com/sonos/sclib/SCIBrowseGroupsInfo);
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < (int)scibrowsegroupsinfo.getNumGroups(); i++)
            if(scibrowsegroupsinfo.shouldDisplayHeaderForGroup(i))
                arraylist.add(new BrowseSection((int)scibrowsegroupsinfo.getStartIndexForGroup(i), scibrowsegroupsinfo.getTitleForGroup(i), scibrowsegroupsinfo.getDescriptionForGroup(i), scibrowsegroupsinfo.getArtURLForGroup(i), scibrowsegroupsinfo.getArtTypeForGroup(i), scibrowsegroupsinfo.getBrowseableSCUriForGroup(i)));

        return (BrowseSection[])arraylist.toArray(new BrowseSection[arraylist.size()]);
    }
}

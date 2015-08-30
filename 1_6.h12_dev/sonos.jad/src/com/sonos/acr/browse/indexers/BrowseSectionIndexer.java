// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.indexers;

import com.sonos.sclib.SCIBrowseDataSource;
import java.util.Arrays;

// Referenced classes of package com.sonos.acr.browse.indexers:
//            SonosSectionIndexer, BrowseSection

public abstract class BrowseSectionIndexer
    implements SonosSectionIndexer
{

    protected BrowseSectionIndexer(SCIBrowseDataSource scibrowsedatasource)
    {
        groupInfos = createBrowseSections(scibrowsedatasource);
    }

    private int getPositionForSection_int(int i)
    {
        int j;
        if(i < groupInfos.length)
            j = groupInfos[i].startPosition;
        else
        if(groupInfos.length > 0)
            j = groupInfos[-1 + groupInfos.length].startPosition;
        else
            j = 0;
        return j;
    }

    private int getSectionForPosition_int(int i)
    {
        int k;
        if(groupInfos.length <= 0)
            break MISSING_BLOCK_LABEL_56;
        k = 1;
_L5:
        if(k >= groupInfos.length) goto _L2; else goto _L1
_L1:
        if(i >= groupInfos[k].startPosition) goto _L4; else goto _L3
_L3:
        int j = k - 1;
_L6:
        return j;
_L4:
        k++;
          goto _L5
_L2:
        j = -1 + groupInfos.length;
          goto _L6
        j = 0;
          goto _L6
    }

    protected abstract BrowseSection[] createBrowseSections(SCIBrowseDataSource scibrowsedatasource);

    public int getPositionCount()
    {
        return groupInfos.length;
    }

    public int getPositionForSection(int i)
    {
        return getPositionForSection_int(i);
    }

    public int getSectionForPosition(int i)
    {
        return getSectionForPosition_int(i);
    }

    public BrowseSection[] getSections()
    {
        return groupInfos;
    }

    public boolean hasSections()
    {
        boolean flag;
        if(groupInfos.length > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isSectioned(int i)
    {
        boolean flag = false;
        if(groupInfos.length > 0 && i >= groupInfos[0].startPosition)
            flag = true;
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("BrowseSectionIndexer{groupInfos=").append(Arrays.toString(groupInfos)).append('}').toString();
    }

    private BrowseSection groupInfos[];
}

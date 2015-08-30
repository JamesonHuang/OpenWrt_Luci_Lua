// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.indexers;


// Referenced classes of package com.sonos.acr.browse.indexers:
//            BrowseSection

public interface SonosSectionIndexer
{

    public abstract int getPositionCount();

    public abstract int getPositionForSection(int i);

    public abstract int getSectionForPosition(int i);

    public abstract BrowseSection[] getSections();

    public abstract boolean hasSections();

    public abstract boolean isSectioned(int i);
}

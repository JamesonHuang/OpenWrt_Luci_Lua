// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import com.sonos.sclib.SCIBrowseDataSource;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            DataSourcePageFragment

public interface PageFactory
{

    public abstract DataSourcePageFragment createBrowsePage(SCIBrowseDataSource scibrowsedatasource);
}

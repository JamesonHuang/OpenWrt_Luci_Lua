// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.actions;

import com.sonos.sclib.SCIBrowseDataSource;

// Referenced classes of package com.sonos.acr.browse.v2.actions:
//            ActionData

public class DataSourceActionMenuData
    implements ActionData
{

    protected DataSourceActionMenuData()
    {
        albumArtType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_VIRTUAL_URL;
    }

    public DataSourceActionMenuData(SCIBrowseDataSource scibrowsedatasource)
    {
        albumArtType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_VIRTUAL_URL;
        dataSource = scibrowsedatasource;
        albumArtType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_NONE;
    }

    public DataSourceActionMenuData(SCIBrowseDataSource scibrowsedatasource, String s)
    {
        albumArtType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_VIRTUAL_URL;
        dataSource = scibrowsedatasource;
        primaryText = s;
        albumArtType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_NONE;
    }

    public DataSourceActionMenuData(SCIBrowseDataSource scibrowsedatasource, String s, String s1, String s2, com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype)
    {
        albumArtType = com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_VIRTUAL_URL;
        dataSource = scibrowsedatasource;
        primaryText = s;
        secondaryText = s1;
        aaUri = s2;
        albumArtType = scalbumarttype;
    }

    public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getActionImageType()
    {
        return albumArtType;
    }

    public String getActionImageUrl()
    {
        return aaUri;
    }

    public SCIBrowseDataSource getDataSource()
    {
        return dataSource;
    }

    public ActionData getExtendedActionData()
    {
        return null;
    }

    public String getPrimaryText()
    {
        return primaryText;
    }

    public String getSecondaryText()
    {
        return secondaryText;
    }

    protected String aaUri;
    protected com.sonos.sclib.SCIBrowseItem.SCAlbumArtType albumArtType;
    protected SCIBrowseDataSource dataSource;
    protected String primaryText;
    protected String secondaryText;
}

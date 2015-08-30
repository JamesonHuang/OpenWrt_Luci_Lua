// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.actions;

import com.sonos.acr.util.AlbumArtSize;
import com.sonos.sclib.SCIActionFilter;
import com.sonos.sclib.SCIBrowseItem;

// Referenced classes of package com.sonos.acr.browse.v2.actions:
//            ActionSet, ActionData, DataSourceActionMenuData

public class BrowseItemActionData extends ActionSet
    implements ActionData
{

    public BrowseItemActionData(SCIBrowseItem scibrowseitem, SCIActionFilter sciactionfilter)
    {
        browseItem = scibrowseitem;
        com.sonos.sclib.SCIBrowseDataSource scibrowsedatasource;
        if(sciactionfilter != null)
            addActionItems(scibrowseitem.getFilteredActions(sciactionfilter));
        else
            addActionItems(scibrowseitem.getActions());
        scibrowsedatasource = scibrowseitem.getMoreMenuDataSource();
        if(scibrowsedatasource != null)
            extendedActionData = new DataSourceActionMenuData(scibrowsedatasource, getPrimaryText(), getSecondaryText(), getActionImageUrl(), getActionImageType());
    }

    public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getActionImageType()
    {
        return browseItem.getAlbumArtType();
    }

    public String getActionImageUrl()
    {
        return browseItem.getAlbumArtURL(AlbumArtSize.SIZE_BROWSE.getPixelWidth());
    }

    public ActionData getExtendedActionData()
    {
        return extendedActionData;
    }

    public String getPrimaryText()
    {
        return browseItem.getPrimaryTitle();
    }

    public String getSecondaryText()
    {
        return browseItem.getSecondaryTitle();
    }

    private SCIBrowseItem browseItem;
    private ActionData extendedActionData;
}

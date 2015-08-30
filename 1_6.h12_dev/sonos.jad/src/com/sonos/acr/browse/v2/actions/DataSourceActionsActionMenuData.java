// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.actions;

import com.sonos.sclib.SCIBrowseDataSource;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.browse.v2.actions:
//            ActionSet, ActionData, ActionItem, DataSourceActionMenuData

public class DataSourceActionsActionMenuData extends ActionSet
    implements ActionData
{

    public DataSourceActionsActionMenuData(SCIBrowseDataSource scibrowsedatasource, ActionSet actionset)
    {
        datasource = scibrowsedatasource;
        for(Iterator iterator = actionset.getActions().iterator(); iterator.hasNext(); addActionItem((ActionItem)iterator.next()));
        SCIBrowseDataSource scibrowsedatasource1 = scibrowsedatasource.getMoreMenuDataSource();
        if(scibrowsedatasource1 != null)
            extendedActionData = new DataSourceActionMenuData(scibrowsedatasource1, getPrimaryText(), getSecondaryText(), getActionImageUrl(), getActionImageType());
    }

    public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getActionImageType()
    {
        return com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_NONE;
    }

    public String getActionImageUrl()
    {
        return "";
    }

    public ActionData getExtendedActionData()
    {
        return extendedActionData;
    }

    public String getPrimaryText()
    {
        return datasource.getPresentationText(com.sonos.sclib.SCIBrowseDataSource.PresentationTextType.TITLE_DEFAULT);
    }

    public String getSecondaryText()
    {
        return "";
    }

    private SCIBrowseDataSource datasource;
    private ActionData extendedActionData;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.actions;

import com.sonos.sclib.SCIEnumerator;

// Referenced classes of package com.sonos.acr.browse.v2.actions:
//            ActionSet, ActionData

public class SimpleActionData extends ActionSet
    implements ActionData
{

    public SimpleActionData(SCIEnumerator scienumerator, String s)
    {
        ActionSet(scienumerator);
        primaryText = s;
    }

    public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getActionImageType()
    {
        return null;
    }

    public String getActionImageUrl()
    {
        return null;
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
        return null;
    }

    private String primaryText;
}

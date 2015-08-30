// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import com.sonos.acr.SonosActivity;
import com.sonos.acr.browse.Browseable;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class ShowBrowsePickerAction extends UIAction
{

    public ShowBrowsePickerAction(SonosActivity sonosactivity, SCIBrowseDataSource scibrowsedatasource, String s)
    {
        super(sonosactivity);
        rootDataSource = scibrowsedatasource;
        actionId = s;
    }

    public SCIBrowseDataSource getRootDataSource()
    {
        return rootDataSource;
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        if(currentContext instanceof Browseable)
            ((Browseable)currentContext).showPicker(rootDataSource, actionId);
        return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
    }

    String actionId;
    SCIBrowseDataSource rootDataSource;
}

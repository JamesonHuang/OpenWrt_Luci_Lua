// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import com.sonos.acr.SonosActivity;
import com.sonos.acr.browse.Browseable;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class ShowBrowseStackAction extends UIAction
{

    public ShowBrowseStackAction(SonosActivity sonosactivity, SCIBrowseStackManager scibrowsestackmanager)
    {
        super(sonosactivity);
        browseStackManager = scibrowsestackmanager;
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        if(currentContext instanceof Browseable)
            ((Browseable)currentContext).displayBrowseStack(browseStackManager);
        return SCActionCompletionStatus.DONE_WITH_ACTION;
    }

    SCIBrowseStackManager browseStackManager;
}

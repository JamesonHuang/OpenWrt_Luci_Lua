// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import com.sonos.acr.SonosActivity;
import com.sonos.acr.browse.Browseable;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.SCActionCompletionStatus;
import com.sonos.sclib.SCIActionContext;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class PopBrowseStackAction extends UIAction
{

    public PopBrowseStackAction(SonosActivity sonosactivity, int i)
    {
        super(sonosactivity);
        mNumPagesToPop = i;
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        if(currentContext instanceof Browseable)
            ((Browseable)currentContext).popPages(mNumPagesToPop);
        else
            SLog.e(LOG_TAG, (new StringBuilder()).append("Cannot pop browse!  Current context is not browseable: ").append(currentContext.getClass().getName()).toString());
        return SCActionCompletionStatus.DONE_WITH_ACTION;
    }

    private static final String LOG_TAG = com/sonos/acr/uiactions/PopBrowseStackAction.getSimpleName();
    int mNumPagesToPop;

}

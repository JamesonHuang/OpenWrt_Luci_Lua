// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.actions.sclib;

import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.browse.v2.actions.sclib:
//            SCLibActionItem

public class SCLibSelectableActionItem extends SCLibActionItem
{

    public SCLibSelectableActionItem(SCIActionSelectableDescriptor sciactionselectabledescriptor)
    {
        SCLibActionItem(sciactionselectabledescriptor);
    }

    public SCIActionContext getActionContext()
    {
        return ((SCIActionNoArgDescriptor)descriptor).getAction();
    }

    public boolean getIsCurrentSelection()
    {
        return ((SCIActionSelectableDescriptor)LibraryUtils.cast(descriptor, com/sonos/sclib/SCIActionSelectableDescriptor)).isCurrentSelection();
    }

    public String getLabel()
    {
        return ((SCIActionNoArgDescriptor)descriptor).getLabel();
    }
}

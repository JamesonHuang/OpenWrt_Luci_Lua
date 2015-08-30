// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.actions.sclib;

import com.sonos.sclib.SCIActionContext;
import com.sonos.sclib.SCIActionNoArgDescriptor;

// Referenced classes of package com.sonos.acr.browse.v2.actions.sclib:
//            SCLibActionItem

public class SCLibNoArgActionItem extends SCLibActionItem
{

    public SCLibNoArgActionItem(SCIActionNoArgDescriptor sciactionnoargdescriptor)
    {
        SCLibActionItem(sciactionnoargdescriptor);
    }

    public SCIActionContext getActionContext()
    {
        return ((SCIActionNoArgDescriptor)descriptor).getAction();
    }

    public String getLabel()
    {
        return ((SCIActionNoArgDescriptor)descriptor).getLabel();
    }
}

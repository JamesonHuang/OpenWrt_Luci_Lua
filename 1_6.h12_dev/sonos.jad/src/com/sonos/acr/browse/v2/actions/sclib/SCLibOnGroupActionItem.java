// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.actions.sclib;

import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.SCIActionContext;
import com.sonos.sclib.SCIActionOnGroupDescriptor;

// Referenced classes of package com.sonos.acr.browse.v2.actions.sclib:
//            SCLibActionItem

public class SCLibOnGroupActionItem extends SCLibActionItem
{

    public SCLibOnGroupActionItem(SCIActionOnGroupDescriptor sciactionongroupdescriptor)
    {
        SCLibActionItem(sciactionongroupdescriptor);
    }

    public SCIActionContext getActionContext()
    {
        ZoneGroup zonegroup = LibraryUtils.getCurrentZoneGroup();
        SCIActionContext sciactioncontext;
        if(zonegroup != null)
            sciactioncontext = ((SCIActionOnGroupDescriptor)descriptor).getAction(zonegroup.getID());
        else
            sciactioncontext = null;
        return sciactioncontext;
    }
}

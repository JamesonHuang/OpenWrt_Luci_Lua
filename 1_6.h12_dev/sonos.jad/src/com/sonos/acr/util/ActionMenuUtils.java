// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.content.DialogInterface;
import com.sonos.acr.sclib.EnumeratorAdapter;
import com.sonos.sclib.*;
import java.util.Iterator;

public class ActionMenuUtils
{
    public static interface OnActionMenuItemClickedListener
    {

        public abstract void onActionMenuCancelled(DialogInterface dialoginterface);

        public abstract void onActionMenuItemClicked(DialogInterface dialoginterface, SCIActionDescriptor sciactiondescriptor);
    }


    public ActionMenuUtils()
    {
    }

    public static boolean canActOn(SCIBrowseItem scibrowseitem, boolean flag)
    {
        boolean flag1;
        if(flag)
        {
            if(scibrowseitem.getFilteredActions(sclib.createDefaultSCIActionFilter(sclib.SC_ACTION_FILTERNAME_EDIT)).count() > 0)
                flag1 = true;
            else
                flag1 = false;
        } else
        {
            flag1 = scibrowseitem.canActOn();
        }
        return flag1;
    }

    public static boolean canPush(SCIBrowseItem scibrowseitem, boolean flag)
    {
        boolean flag1 = true;
        if(flag || scibrowseitem == null) goto _L2; else goto _L1
_L1:
        if(!scibrowseitem.canPush()) goto _L4; else goto _L3
_L3:
        return flag1;
_L4:
        if(!scibrowseitem.canActOn()) goto _L2; else goto _L5
_L5:
        Iterator iterator = (new EnumeratorAdapter(scibrowseitem.getActions(), sclib.SCIACTION_NOARG_DESCRIPTOR_INTERFACE)).iterator();
_L8:
        if(!iterator.hasNext()) goto _L2; else goto _L6
_L6:
        SCIActionNoArgDescriptor sciactionnoargdescriptor = (SCIActionNoArgDescriptor)iterator.next();
        if(sciactionnoargdescriptor == null || !sciactionnoargdescriptor.getCategory().equals(sclib.SC_ACTION_CATEGORY_PUSH)) goto _L8; else goto _L7
_L7:
        continue; /* Loop/switch isn't completed */
_L2:
        flag1 = false;
        if(true) goto _L3; else goto _L9
_L9:
    }

    public static SCIActionNoArgDescriptor getDeleteAction(SCIBrowseItem scibrowseitem)
    {
        Iterator iterator = (new EnumeratorAdapter(scibrowseitem.getActions(), sclib.SCIACTION_NOARG_DESCRIPTOR_INTERFACE)).iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        SCIActionNoArgDescriptor sciactionnoargdescriptor = (SCIActionNoArgDescriptor)iterator.next();
        if(sciactionnoargdescriptor == null || !sciactionnoargdescriptor.getActionID().equals(sclibConstants.SC_ACTIONID_DELETE_ITEM)) goto _L4; else goto _L3
_L3:
        return sciactionnoargdescriptor;
_L2:
        sciactionnoargdescriptor = null;
        if(true) goto _L3; else goto _L5
_L5:
    }

    public static SCIActionWithIntDescriptor getReorderAction(SCIBrowseDataSource scibrowsedatasource)
    {
        Iterator iterator = (new EnumeratorAdapter(scibrowsedatasource.getActionsOnSelectedItems(), sclib.SCIACTION_WITH_INT_DESCRIPTOR_INTERFACE)).iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        SCIActionWithIntDescriptor sciactionwithintdescriptor = (SCIActionWithIntDescriptor)iterator.next();
        if(sciactionwithintdescriptor == null || !sciactionwithintdescriptor.getActionID().equals(sclibConstants.SC_ACTIONID_MOVE_SELECTED_ITEMS)) goto _L4; else goto _L3
_L3:
        return sciactionwithintdescriptor;
_L2:
        sciactionwithintdescriptor = null;
        if(true) goto _L3; else goto _L5
_L5:
    }

    public static SCIActionWithIntDescriptor getReorderAction(SCIBrowseItem scibrowseitem)
    {
        Iterator iterator = (new EnumeratorAdapter(scibrowseitem.getActions(), sclib.SCIACTION_WITH_INT_DESCRIPTOR_INTERFACE)).iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        SCIActionWithIntDescriptor sciactionwithintdescriptor = (SCIActionWithIntDescriptor)iterator.next();
        if(sciactionwithintdescriptor == null || !sciactionwithintdescriptor.getActionID().equals(sclibConstants.SC_ACTIONID_MOVE_ITEM)) goto _L4; else goto _L3
_L3:
        return sciactionwithintdescriptor;
_L2:
        sciactionwithintdescriptor = null;
        if(true) goto _L3; else goto _L5
_L5:
    }

    private static final String LOG_TAG = com/sonos/acr/util/ActionMenuUtils.getSimpleName();

}

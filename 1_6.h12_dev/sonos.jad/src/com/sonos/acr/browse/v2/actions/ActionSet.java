// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.actions;

import com.sonos.acr.browse.v2.actions.sclib.SCLibActionItem;
import com.sonos.acr.sclib.EnumeratorAdapter;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.browse.v2.actions:
//            ActionItem, SimpleActionItem

public class ActionSet
{

    public ActionSet()
    {
        actionItems = new ArrayList();
    }

    public ActionSet(SimpleActionItem simpleactionitem)
    {
        actionItems = new ArrayList();
        addActionItem(simpleactionitem);
    }

    public ActionSet(SCIEnumerator scienumerator)
    {
        actionItems = new ArrayList();
        addActionItems(scienumerator);
    }

    public ActionSet(ArrayList arraylist)
    {
        actionItems = new ArrayList();
        if(arraylist != null)
        {
            for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); addActionItem((ActionItem)iterator.next()));
        }
    }

    public void addActionItem(ActionItem actionitem)
    {
        actionItems.add(actionitem);
    }

    public void addActionItems(SCIEnumerator scienumerator)
    {
        if(scienumerator != null)
        {
            Iterator iterator = (new EnumeratorAdapter(scienumerator, sclib.SCIACTION_DESCRIPTOR_INTERFACE)).iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                SCIActionDescriptor sciactiondescriptor = (SCIActionDescriptor)iterator.next();
                if(sciactiondescriptor.isEnabled())
                {
                    SCLibActionItem sclibactionitem = SCLibActionItem.createActionItem(sciactiondescriptor);
                    if(sclibactionitem != null)
                        addActionItem(sclibactionitem);
                }
            } while(true);
        }
    }

    public boolean containsCategory(String s)
    {
        Iterator iterator = actionItems.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        if(!s.equals(((ActionItem)iterator.next()).getCategory())) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        return flag;
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public ActionItem findActionById(String s)
    {
        Iterator iterator = actionItems.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        ActionItem actionitem = (ActionItem)iterator.next();
        if(!s.equals(actionitem.getActionID())) goto _L4; else goto _L3
_L3:
        return actionitem;
_L2:
        actionitem = null;
        if(true) goto _L3; else goto _L5
_L5:
    }

    public ActionItem getActionAt(int i)
    {
        return (ActionItem)actionItems.get(i);
    }

    public ArrayList getActions()
    {
        return new ArrayList(actionItems);
    }

    public ArrayList getCategory(String s)
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = actionItems.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ActionItem actionitem = (ActionItem)iterator.next();
            if(s.equals(actionitem.getCategory()))
                arraylist.add(actionitem);
        } while(true);
        return arraylist;
    }

    public boolean isAtLeastOneEnabled()
    {
        Iterator iterator = actionItems.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        if(!((ActionItem)iterator.next()).isEnabled()) goto _L4; else goto _L3
_L3:
        boolean flag = true;
_L6:
        return flag;
_L2:
        flag = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public boolean isEmpty()
    {
        return actionItems.isEmpty();
    }

    public void remoteItemAt(int i)
    {
        actionItems.remove(i);
    }

    public int size()
    {
        return actionItems.size();
    }

    private ArrayList actionItems;
}

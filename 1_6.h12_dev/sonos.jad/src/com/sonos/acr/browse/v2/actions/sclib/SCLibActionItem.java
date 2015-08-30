// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.actions.sclib;

import com.sonos.acr.browse.v2.actions.ActionItem;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.sclib.*;
import java.util.*;

// Referenced classes of package com.sonos.acr.browse.v2.actions.sclib:
//            SCLibOnGroupActionItem, SCLibSelectableActionItem, SCLibNoArgActionItem

public abstract class SCLibActionItem extends ActionItem
{

    public SCLibActionItem(SCIActionDescriptor sciactiondescriptor)
    {
        descriptor = sciactiondescriptor;
        propertyBag = new HashMap();
    }

    public static SCLibActionItem createActionItem(SCIActionDescriptor sciactiondescriptor)
    {
        SCIActionOnGroupDescriptor sciactionongroupdescriptor = (SCIActionOnGroupDescriptor)LibraryUtils.cast(sciactiondescriptor, com/sonos/sclib/SCIActionOnGroupDescriptor);
        Object obj;
        if(sciactionongroupdescriptor != null)
        {
            obj = new SCLibOnGroupActionItem(sciactionongroupdescriptor);
        } else
        {
            SCIActionSelectableDescriptor sciactionselectabledescriptor = (SCIActionSelectableDescriptor)LibraryUtils.cast(sciactiondescriptor, com/sonos/sclib/SCIActionSelectableDescriptor);
            if(sciactionselectabledescriptor != null)
            {
                obj = new SCLibSelectableActionItem(sciactionselectabledescriptor);
            } else
            {
                SCIActionNoArgDescriptor sciactionnoargdescriptor = (SCIActionNoArgDescriptor)LibraryUtils.cast(sciactiondescriptor, com/sonos/sclib/SCIActionNoArgDescriptor);
                if(sciactionnoargdescriptor != null)
                    obj = new SCLibNoArgActionItem(sciactionnoargdescriptor);
                else
                    obj = null;
            }
        }
        return ((SCLibActionItem) (obj));
    }

    private void setPropertiesOnContext(SCIActionContext sciactioncontext)
    {
        Iterator iterator = propertyBag.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if(entry.getValue() instanceof String)
                sciactioncontext.getPropertyBag().setStrProp((String)entry.getKey(), (String)entry.getValue());
            else
            if(entry.getValue() instanceof Integer)
                sciactioncontext.getPropertyBag().setIntProp((String)entry.getKey(), ((Integer)entry.getValue()).intValue());
            else
            if(entry.getValue() instanceof Boolean)
                sciactioncontext.getPropertyBag().setBoolProp((String)entry.getKey(), ((Boolean)entry.getValue()).booleanValue());
        } while(true);
    }

    public void addBooleanToPropertyBag(String s, Boolean boolean1)
    {
        propertyBag.put(s, boolean1);
    }

    public void addIntegerToPropertyBag(String s, Integer integer)
    {
        propertyBag.put(s, integer);
    }

    public void addStringToPropertyBag(String s, String s1)
    {
        propertyBag.put(s, s1);
    }

    public void clearPropertyBag()
    {
        propertyBag.clear();
    }

    public abstract SCIActionContext getActionContext();

    public String getActionID()
    {
        return descriptor.getActionID();
    }

    public String getCategory()
    {
        return descriptor.getCategory();
    }

    public String getLabel()
    {
        return descriptor.getLabel();
    }

    public boolean isEnabled()
    {
        return descriptor.isEnabled();
    }

    public final void perform()
    {
        SCIActionContext sciactioncontext = getActionContext();
        if(sciactioncontext != null)
        {
            setPropertiesOnContext(sciactioncontext);
            sciactioncontext.perform();
        }
    }

    SCIActionDescriptor descriptor;
    HashMap propertyBag;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.property;

import ch.qos.logback.core.PropertyDefinerBase;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.OptionHelper;

public class ResourceExistsPropertyDefiner extends PropertyDefinerBase
{

    public ResourceExistsPropertyDefiner()
    {
    }

    public String getPropertyValue()
    {
        String s;
        if(OptionHelper.isEmpty(resourceStr))
        {
            addError("The \"resource\" property must be set.");
            s = null;
        } else
        {
            boolean flag;
            if(Loader.getResourceBySelfClassLoader(resourceStr) != null)
                flag = true;
            else
                flag = false;
            s = booleanAsStr(flag);
        }
        return s;
    }

    public String getResource()
    {
        return resourceStr;
    }

    public void setResource(String s)
    {
        resourceStr = s;
    }

    String resourceStr;
}

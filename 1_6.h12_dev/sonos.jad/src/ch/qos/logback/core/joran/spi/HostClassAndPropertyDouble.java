// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.spi;


public class HostClassAndPropertyDouble
{

    public HostClassAndPropertyDouble(Class class1, String s)
    {
        hostClass = class1;
        propertyName = s;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(obj == null)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(getClass() != obj.getClass())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        HostClassAndPropertyDouble hostclassandpropertydouble = (HostClassAndPropertyDouble)obj;
        if(hostClass == null)
        {
            if(hostclassandpropertydouble.hostClass != null)
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
        } else
        if(!hostClass.equals(hostclassandpropertydouble.hostClass))
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(propertyName == null)
        {
            if(hostclassandpropertydouble.propertyName != null)
                flag = false;
        } else
        if(!propertyName.equals(hostclassandpropertydouble.propertyName))
            flag = false;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public Class getHostClass()
    {
        return hostClass;
    }

    public String getPropertyName()
    {
        return propertyName;
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        int k;
        if(hostClass == null)
            j = 0;
        else
            j = hostClass.hashCode();
        k = 31 * (j + 31);
        if(propertyName != null)
            i = propertyName.hashCode();
        return k + i;
    }

    final Class hostClass;
    final String propertyName;
}

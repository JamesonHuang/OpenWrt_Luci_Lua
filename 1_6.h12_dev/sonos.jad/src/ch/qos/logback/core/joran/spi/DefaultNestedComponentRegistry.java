// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.spi;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package ch.qos.logback.core.joran.spi:
//            HostClassAndPropertyDouble

public class DefaultNestedComponentRegistry
{

    public DefaultNestedComponentRegistry()
    {
        defaultComponentMap = new HashMap();
    }

    private Class oneShotFind(Class class1, String s)
    {
        HostClassAndPropertyDouble hostclassandpropertydouble = new HostClassAndPropertyDouble(class1, s);
        return (Class)defaultComponentMap.get(hostclassandpropertydouble);
    }

    public void add(Class class1, String s, Class class2)
    {
        HostClassAndPropertyDouble hostclassandpropertydouble = new HostClassAndPropertyDouble(class1, s.toLowerCase());
        defaultComponentMap.put(hostclassandpropertydouble, class2);
    }

    public Class findDefaultComponentType(Class class1, String s)
    {
        String s1 = s.toLowerCase();
_L3:
        Class class2;
        if(class1 == null)
            break MISSING_BLOCK_LABEL_33;
        class2 = oneShotFind(class1, s1);
        if(class2 == null) goto _L2; else goto _L1
_L1:
        return class2;
_L2:
        class1 = class1.getSuperclass();
          goto _L3
        class2 = null;
          goto _L1
    }

    Map defaultComponentMap;
}

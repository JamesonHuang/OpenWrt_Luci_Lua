// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.util;

import java.lang.reflect.Method;
import java.util.*;

// Referenced classes of package ch.qos.logback.core.joran.util:
//            MethodDescriptor, PropertyDescriptor

public class Introspector
{

    public Introspector()
    {
    }

    public static String decapitalize(String s)
    {
        if(s != null && s.length() != 0) goto _L2; else goto _L1
_L1:
        String s1 = s;
_L4:
        return s1;
_L2:
        s1 = s.substring(0, 1).toLowerCase();
        if(s.length() > 1)
            s1 = (new StringBuilder()).append(s1).append(s.substring(1)).toString();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static MethodDescriptor[] getMethodDescriptors(Class class1)
    {
        ArrayList arraylist = new ArrayList();
        Method amethod[] = class1.getMethods();
        int i = amethod.length;
        for(int j = 0; j < i; j++)
        {
            Method method = amethod[j];
            arraylist.add(new MethodDescriptor(method.getName(), method));
        }

        return (MethodDescriptor[])arraylist.toArray(new MethodDescriptor[0]);
    }

    public static PropertyDescriptor[] getPropertyDescriptors(Class class1)
    {
        int i = "set".length();
        HashMap hashmap = new HashMap();
        Method amethod[] = class1.getMethods();
        int j = amethod.length;
        int k = 0;
        while(k < j) 
        {
            Method method = amethod[k];
            String s = method.getName();
            boolean flag;
            boolean flag1;
            if(s.startsWith("get") && s.length() > i)
                flag = true;
            else
                flag = false;
            if(s.startsWith("set") && s.length() > i)
                flag1 = true;
            else
                flag1 = false;
            if(flag || flag1)
            {
                String s1 = decapitalize(s.substring(i));
                PropertyDescriptor propertydescriptor = (PropertyDescriptor)hashmap.get(s1);
                if(propertydescriptor == null)
                {
                    propertydescriptor = new PropertyDescriptor(s1);
                    hashmap.put(s1, propertydescriptor);
                }
                Class aclass[] = method.getParameterTypes();
                if(flag1)
                {
                    if(aclass.length == 1)
                    {
                        propertydescriptor.setWriteMethod(method);
                        propertydescriptor.setPropertyType(aclass[0]);
                    }
                } else
                if(flag && aclass.length == 0)
                {
                    propertydescriptor.setReadMethod(method);
                    if(propertydescriptor.getPropertyType() == null)
                        propertydescriptor.setPropertyType(method.getReturnType());
                }
            }
            k++;
        }
        return (PropertyDescriptor[])hashmap.values().toArray(new PropertyDescriptor[0]);
    }
}

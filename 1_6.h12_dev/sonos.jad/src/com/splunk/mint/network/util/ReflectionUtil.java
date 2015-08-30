// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network.util;

import java.lang.reflect.Constructor;
import java.util.*;

public final class ReflectionUtil
{

    public ReflectionUtil()
    {
    }

    public static boolean callingClassAnyOf(String as[])
    {
        StackTraceElement astacktraceelement[];
        int i;
        int j;
        astacktraceelement = (new Throwable()).getStackTrace();
        i = astacktraceelement.length;
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_46;
        if(!contains(astacktraceelement[j].getClassName(), as)) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        j++;
          goto _L3
        flag = false;
          goto _L4
    }

    public static final boolean contains(String s, String as[])
    {
        int i;
        int j;
        i = as.length;
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_32;
        if(!s.contains(as[j])) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        j++;
          goto _L3
        flag = false;
          goto _L4
    }

    public static String extractCallingMethod(String as[])
    {
        StackTraceElement astacktraceelement[];
        int i;
        int j;
        astacktraceelement = (new Throwable()).getStackTrace();
        i = astacktraceelement.length;
        j = 0;
_L3:
        StackTraceElement stacktraceelement;
        if(j >= i)
            break MISSING_BLOCK_LABEL_93;
        stacktraceelement = astacktraceelement[j];
        if(inExcluded(stacktraceelement.getClassName(), as)) goto _L2; else goto _L1
_L1:
        String s = (new StringBuilder()).append(stacktraceelement.getClassName()).append(".").append(stacktraceelement.getMethodName()).append(":").append(stacktraceelement.getLineNumber()).toString();
_L4:
        return s;
_L2:
        j++;
          goto _L3
        s = null;
          goto _L4
    }

    public static Constructor findConstructor(String s, Class aclass[])
        throws ClassNotFoundException
    {
        Constructor aconstructor[];
        int i;
        int j;
        aconstructor = Class.forName(s).getDeclaredConstructors();
        i = aconstructor.length;
        j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        Constructor constructor;
        constructor = aconstructor[j];
        Class aclass1[] = constructor.getParameterTypes();
        if(aclass1.length != aclass.length)
            continue; /* Loop/switch isn't completed */
        boolean flag = true;
        for(int k = 0; k < aclass1.length; k++)
            if(!aclass1[k].equals(aclass[k]))
                flag = false;

        if(!flag)
            continue; /* Loop/switch isn't completed */
_L4:
        return constructor;
        j++;
          goto _L3
_L2:
        constructor = null;
          goto _L4
    }

    public static Set getAllMethods(Class class1)
    {
        HashSet hashset = new HashSet();
        for(Iterator iterator = getAllSuperTypes(class1).iterator(); iterator.hasNext();)
        {
            java.lang.reflect.Method amethod[] = ((Class)iterator.next()).getDeclaredMethods();
            int i = amethod.length;
            int j = 0;
            while(j < i) 
            {
                hashset.add(amethod[j]);
                j++;
            }
        }

        return hashset;
    }

    public static Set getAllSuperTypes(Class class1)
    {
        HashSet hashset = new HashSet();
        if(class1 != null && !class1.equals(java/lang/Object))
        {
            hashset.add(class1);
            hashset.addAll(getAllSuperTypes(class1.getSuperclass()));
            Class aclass[] = class1.getInterfaces();
            int i = aclass.length;
            for(int j = 0; j < i; j++)
                hashset.addAll(getAllSuperTypes(aclass[j]));

        }
        return hashset;
    }

    private static final boolean inExcluded(String s, String as[])
    {
        int i;
        int j;
        i = as.length;
        j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_32;
        if(!s.startsWith(as[j])) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        j++;
          goto _L3
        flag = false;
          goto _L4
    }

    public static final boolean includeObject;
}

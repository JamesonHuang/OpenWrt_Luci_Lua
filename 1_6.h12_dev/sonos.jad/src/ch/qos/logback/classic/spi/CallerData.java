// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import ch.qos.logback.core.CoreConstants;
import java.util.Iterator;
import java.util.List;

public class CallerData
{

    public CallerData()
    {
    }

    public static StackTraceElement[] extract(Throwable throwable, String s, int i, List list)
    {
        int j = 0;
        if(throwable != null) goto _L2; else goto _L1
_L1:
        StackTraceElement astacktraceelement1[] = null;
_L10:
        return astacktraceelement1;
_L2:
        StackTraceElement astacktraceelement[];
        int k;
        int l;
        astacktraceelement = throwable.getStackTrace();
        k = 0;
        l = -1;
_L7:
        if(k >= astacktraceelement.length) goto _L4; else goto _L3
_L3:
        if(!isInFrameworkSpace(astacktraceelement[k].getClassName(), s, list)) goto _L6; else goto _L5
_L5:
        l = k + 1;
_L8:
        k++;
          goto _L7
_L6:
        if(l == -1) goto _L8; else goto _L4
_L4:
        if(l != -1)
            break; /* Loop/switch isn't completed */
        astacktraceelement1 = EMPTY_CALLER_DATA_ARRAY;
        if(true) goto _L10; else goto _L9
_L9:
        int i1 = astacktraceelement.length - l;
        if(i >= i1)
            i = i1;
        astacktraceelement1 = new StackTraceElement[i];
        while(j < i) 
        {
            astacktraceelement1[j] = astacktraceelement[l + j];
            j++;
        }
        if(true) goto _L10; else goto _L11
_L11:
    }

    static boolean isInFrameworkSpace(String s, String s1, List list)
    {
        boolean flag;
        if(s.equals(s1) || s.equals("org.apache.log4j.Category") || s.startsWith("org.slf4j.Logger") || isInFrameworkSpaceList(s, list))
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isInFrameworkSpaceList(String s, List list)
    {
        if(list != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
            if(s.startsWith((String)iterator.next()))
            {
                flag = true;
                continue; /* Loop/switch isn't completed */
            }

        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static StackTraceElement naInstance()
    {
        return new StackTraceElement("?", "?", "?", -1);
    }

    public static final String CALLER_DATA_NA;
    public static final StackTraceElement EMPTY_CALLER_DATA_ARRAY[] = new StackTraceElement[0];
    public static final int LINE_NA = -1;
    private static final String LOG4J_CATEGORY = "org.apache.log4j.Category";
    public static final String NA = "?";
    private static final String SLF4J_BOUNDARY = "org.slf4j.Logger";

    static 
    {
        CALLER_DATA_NA = (new StringBuilder()).append("?#?:?").append(CoreConstants.LINE_SEPARATOR).toString();
    }
}

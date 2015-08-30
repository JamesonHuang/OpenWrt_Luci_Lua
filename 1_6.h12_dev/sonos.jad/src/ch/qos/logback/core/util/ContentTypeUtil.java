// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;


public class ContentTypeUtil
{

    public ContentTypeUtil()
    {
    }

    public static String getSubType(String s)
    {
        String s1 = null;
        if(s != null) goto _L2; else goto _L1
_L1:
        return s1;
_L2:
        int i = s.indexOf('/');
        if(i != -1)
        {
            int j = i + 1;
            if(j < s.length())
                s1 = s.substring(j);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static boolean isTextual(String s)
    {
        boolean flag;
        if(s == null)
            flag = false;
        else
            flag = s.startsWith("text");
        return flag;
    }
}

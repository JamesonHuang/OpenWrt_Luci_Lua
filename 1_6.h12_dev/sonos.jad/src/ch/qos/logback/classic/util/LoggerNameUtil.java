// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.util;


public class LoggerNameUtil
{

    public LoggerNameUtil()
    {
    }

    public static int getFirstSeparatorIndexOf(String s)
    {
        return getSeparatorIndexOf(s, 0);
    }

    public static int getSeparatorIndexOf(String s, int i)
    {
        int j;
        int k;
        j = s.indexOf('.', i);
        k = s.indexOf('$', i);
        if(j != -1 || k != -1) goto _L2; else goto _L1
_L1:
        j = -1;
_L4:
        return j;
_L2:
        if(j == -1)
            j = k;
        else
        if(k != -1 && j >= k)
            j = k;
        if(true) goto _L4; else goto _L3
_L3:
    }
}

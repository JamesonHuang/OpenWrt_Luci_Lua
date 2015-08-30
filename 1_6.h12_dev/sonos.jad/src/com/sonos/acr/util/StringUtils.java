// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import java.util.Comparator;

public class StringUtils
{

    public StringUtils()
    {
    }

    public static String ensureNotNull(String s)
    {
        if(s == null)
            s = "";
        return s;
    }

    public static Comparator getSonosStringComparator()
    {
        if(sm_SonosStringComparator == null)
            sm_SonosStringComparator = new Comparator() {

                public volatile int compare(Object obj, Object obj1)
                {
                    return compare((String)obj, (String)obj1);
                }

                public int compare(String s, String s1)
                {
                    return StringUtils.sonosStringCompare(s, s1);
                }

            }
;
        return sm_SonosStringComparator;
    }

    public static boolean isEmptyOrNull(CharSequence charsequence)
    {
        boolean flag;
        if(charsequence == null || charsequence.length() == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isLong(String s)
    {
        Long.parseLong(s);
        boolean flag = true;
_L2:
        return flag;
        Exception exception;
        exception;
        flag = false;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static boolean isNotEmptyOrNull(CharSequence charsequence)
    {
        boolean flag;
        if(charsequence != null && charsequence.length() != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static int sonosStringCompare(String s, String s1)
    {
        int i = s.compareToIgnoreCase(s1);
        if(i == 0)
            i = s.compareTo(s1);
        return i;
    }

    private static Comparator sm_SonosStringComparator = null;

}

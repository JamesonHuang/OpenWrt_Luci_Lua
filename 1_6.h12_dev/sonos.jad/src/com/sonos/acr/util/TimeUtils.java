// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;


// Referenced classes of package com.sonos.acr.util:
//            SLog

public class TimeUtils
{

    public TimeUtils()
    {
    }

    public static long toMilliSeconds(String s)
    {
        long l = -1L;
        SLog.d("TimesUtil", (new StringBuilder()).append("Time: ").append(s).toString());
        if(s != null && s.matches("\\d+(\\:[0-5]\\d){2}"))
        {
            String as[] = s.split(":");
            l = 1000 * (60 * (60 * Integer.valueOf(as[0]).intValue())) + 1000 * (60 * Integer.valueOf(as[1]).intValue()) + 1000 * Integer.valueOf(as[2]).intValue();
        }
        return l;
    }

    public static String toShortTime(long l)
    {
        return toShortTime(l, false);
    }

    public static String toShortTime(long l, boolean flag)
    {
        String s;
        int i;
        int j;
        int k;
        StringBuilder stringbuilder;
        String s1;
        if(flag)
            s = "-";
        else
            s = "";
        if(l < 0L)
            l = 0L;
        i = (int)(l / 0x36ee80L);
        j = (int)((l % 0x36ee80L) / 60000L);
        k = (int)Math.floor((double)(l % 0x36ee80L % 60000L) / 1000D);
        stringbuilder = (new StringBuilder()).append(s);
        if(i > 0)
        {
            Object aobj1[] = new Object[3];
            aobj1[0] = Integer.valueOf(i);
            aobj1[1] = Integer.valueOf(j);
            aobj1[2] = Integer.valueOf(k);
            s1 = String.format("%d:%02d:%02d", aobj1);
        } else
        {
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(j);
            aobj[1] = Integer.valueOf(k);
            s1 = String.format("%d:%02d", aobj);
        }
        return stringbuilder.append(s1).toString();
    }

    public static String toTime(long l)
    {
        String s = "";
        if(l >= 0L)
        {
            int i = (int)(l / 0x36ee80L);
            int j = (int)((l % 0x36ee80L) / 60000L);
            int k = (int)((l % 0x36ee80L % 60000L) / 1000L);
            StringBuilder stringbuilder = new StringBuilder();
            String s1;
            StringBuilder stringbuilder1;
            String s2;
            StringBuilder stringbuilder2;
            String s3;
            if(i > 9)
                s1 = String.valueOf(i);
            else
                s1 = (new StringBuilder()).append("0").append(i).toString();
            stringbuilder1 = stringbuilder.append(s1).append(":");
            if(j > 9)
                s2 = String.valueOf(j);
            else
                s2 = (new StringBuilder()).append("0").append(j).toString();
            stringbuilder2 = stringbuilder1.append(s2).append(":");
            if(k > 9)
                s3 = String.valueOf(k);
            else
                s3 = (new StringBuilder()).append("0").append(k).toString();
            s = stringbuilder2.append(s3).toString();
        }
        return s;
    }
}

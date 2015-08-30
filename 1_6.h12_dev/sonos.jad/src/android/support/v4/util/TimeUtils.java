// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.util;

import java.io.PrintWriter;

public class TimeUtils
{

    public TimeUtils()
    {
    }

    private static int accumField(int i, int j, boolean flag, int k)
    {
        int l;
        if(i > 99 || flag && k >= 3)
            l = j + 3;
        else
        if(i > 9 || flag && k >= 2)
            l = j + 2;
        else
        if(flag || i > 0)
            l = j + 1;
        else
            l = 0;
        return l;
    }

    public static void formatDuration(long l, long l1, PrintWriter printwriter)
    {
        if(l == 0L)
            printwriter.print("--");
        else
            formatDuration(l - l1, printwriter, 0);
    }

    public static void formatDuration(long l, PrintWriter printwriter)
    {
        formatDuration(l, printwriter, 0);
    }

    public static void formatDuration(long l, PrintWriter printwriter, int i)
    {
        Object obj = sFormatSync;
        obj;
        JVM INSTR monitorenter ;
        int j = formatDurationLocked(l, i);
        printwriter.print(new String(sFormatStr, 0, j));
        return;
    }

    public static void formatDuration(long l, StringBuilder stringbuilder)
    {
        Object obj = sFormatSync;
        obj;
        JVM INSTR monitorenter ;
        int i = formatDurationLocked(l, 0);
        stringbuilder.append(sFormatStr, 0, i);
        return;
    }

    private static int formatDurationLocked(long l, int i)
    {
        if(sFormatStr.length < i)
            sFormatStr = new char[i];
        char ac[] = sFormatStr;
        int k3;
        if(l == 0L)
        {
            for(int i5 = i - 1; i5 < 0;)
                ac[0] = ' ';

            ac[0] = '0';
            k3 = 1;
        } else
        {
            char c;
            int j;
            int k;
            int i1;
            int j1;
            int k1;
            int l1;
            if(l > 0L)
            {
                c = '+';
            } else
            {
                c = '-';
                l = -l;
            }
            j = (int)(l % 1000L);
            k = (int)Math.floor(l / 1000L);
            i1 = 0;
            j1 = 0;
            k1 = 0;
            if(k > 0x15180)
            {
                i1 = k / 0x15180;
                k -= 0x15180 * i1;
            }
            if(k > 3600)
            {
                j1 = k / 3600;
                k -= j1 * 3600;
            }
            if(k > 60)
            {
                k1 = k / 60;
                k -= k1 * 60;
            }
            l1 = 0;
            if(i != 0)
            {
                int l3 = accumField(i1, 1, false, 0);
                boolean flag4;
                int i4;
                boolean flag5;
                int j4;
                boolean flag6;
                int k4;
                byte byte4;
                int l4;
                if(l3 > 0)
                    flag4 = true;
                else
                    flag4 = false;
                i4 = l3 + accumField(j1, 1, flag4, 2);
                if(i4 > 0)
                    flag5 = true;
                else
                    flag5 = false;
                j4 = i4 + accumField(k1, 1, flag5, 2);
                if(j4 > 0)
                    flag6 = true;
                else
                    flag6 = false;
                k4 = j4 + accumField(k, 1, flag6, 2);
                if(k4 > 0)
                    byte4 = 3;
                else
                    byte4 = 0;
                for(l4 = k4 + (1 + accumField(j, 2, true, byte4)); l4 < i; l4++)
                {
                    ac[l1] = ' ';
                    l1++;
                }

            }
            ac[l1] = c;
            int i2 = l1 + 1;
            boolean flag;
            int j2;
            boolean flag1;
            byte byte0;
            int k2;
            boolean flag2;
            byte byte1;
            int l2;
            boolean flag3;
            byte byte2;
            int i3;
            byte byte3;
            int j3;
            if(i != 0)
                flag = true;
            else
                flag = false;
            j2 = printField(ac, i1, 'd', i2, false, 0);
            if(j2 != i2)
                flag1 = true;
            else
                flag1 = false;
            if(flag)
                byte0 = 2;
            else
                byte0 = 0;
            k2 = printField(ac, j1, 'h', j2, flag1, byte0);
            if(k2 != i2)
                flag2 = true;
            else
                flag2 = false;
            if(flag)
                byte1 = 2;
            else
                byte1 = 0;
            l2 = printField(ac, k1, 'm', k2, flag2, byte1);
            if(l2 != i2)
                flag3 = true;
            else
                flag3 = false;
            if(flag)
                byte2 = 2;
            else
                byte2 = 0;
            i3 = printField(ac, k, 's', l2, flag3, byte2);
            if(flag && i3 != i2)
                byte3 = 3;
            else
                byte3 = 0;
            j3 = printField(ac, j, 'm', i3, true, byte3);
            ac[j3] = 's';
            k3 = j3 + 1;
        }
        return k3;
    }

    private static int printField(char ac[], int i, char c, int j, boolean flag, int k)
    {
        if(flag || i > 0)
        {
            int l = j;
            if(flag && k >= 3 || i > 99)
            {
                int k1 = i / 100;
                ac[j] = (char)(k1 + 48);
                j++;
                i -= k1 * 100;
            }
            if(flag && k >= 2 || i > 9 || l != j)
            {
                int i1 = i / 10;
                ac[j] = (char)(i1 + 48);
                j++;
                i -= i1 * 10;
            }
            ac[j] = (char)(i + 48);
            int j1 = j + 1;
            ac[j1] = c;
            j = j1 + 1;
        }
        return j;
    }

    public static final int HUNDRED_DAY_FIELD_LEN = 19;
    private static final int SECONDS_PER_DAY = 0x15180;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static char sFormatStr[] = new char[24];
    private static final Object sFormatSync = new Object();

}

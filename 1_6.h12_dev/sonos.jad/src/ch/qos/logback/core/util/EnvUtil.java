// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.util;

import java.util.*;

// Referenced classes of package ch.qos.logback.core.util:
//            OptionHelper

public class EnvUtil
{

    public EnvUtil()
    {
    }

    public static boolean isAndroidOS()
    {
        String s = OptionHelper.getSystemProperty("os.name");
        String s1 = OptionHelper.getEnv("ANDROID_ROOT");
        String s2 = OptionHelper.getEnv("ANDROID_DATA");
        boolean flag;
        if(s != null && s.contains("Linux") && s1 != null && s1.contains("/system") && s2 != null && s2.contains("/data"))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isJDK5()
    {
        return isJDK_N_OrHigher(5);
    }

    public static boolean isJDK6OrHigher()
    {
        return isJDK_N_OrHigher(6);
    }

    public static boolean isJDK7OrHigher()
    {
        return isJDK_N_OrHigher(7);
    }

    private static boolean isJDK_N_OrHigher(int i)
    {
        ArrayList arraylist;
        String s;
        arraylist = new ArrayList();
        for(int j = 0; j < 5; j++)
            arraylist.add((new StringBuilder()).append("1.").append(i + j).toString());

        s = System.getProperty("java.version");
        if(s != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        for(Iterator iterator = arraylist.iterator(); iterator.hasNext();)
            if(s.startsWith((String)iterator.next()))
            {
                flag = true;
                continue; /* Loop/switch isn't completed */
            }

        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean isWindows()
    {
        return System.getProperty("os.name").startsWith("Windows");
    }
}

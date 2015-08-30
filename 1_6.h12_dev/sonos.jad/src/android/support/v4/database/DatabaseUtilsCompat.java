// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.database;

import android.text.TextUtils;

public class DatabaseUtilsCompat
{

    private DatabaseUtilsCompat()
    {
    }

    public static String[] appendSelectionArgs(String as[], String as1[])
    {
        String as2[];
        if(as == null || as.length == 0)
        {
            as2 = as1;
        } else
        {
            as2 = new String[as.length + as1.length];
            System.arraycopy(as, 0, as2, 0, as.length);
            System.arraycopy(as1, 0, as2, as.length, as1.length);
        }
        return as2;
    }

    public static String concatenateWhere(String s, String s1)
    {
        if(!TextUtils.isEmpty(s))
            if(TextUtils.isEmpty(s1))
                s1 = s;
            else
                s1 = (new StringBuilder()).append("(").append(s).append(") AND (").append(s1).append(")").toString();
        return s1;
    }
}

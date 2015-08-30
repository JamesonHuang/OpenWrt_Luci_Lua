// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import java.util.ArrayList;

public class ExcludedUrls extends ArrayList
{

    public ExcludedUrls(String as[])
    {
        int i = as.length;
        for(int j = 0; j < i; j++)
            addValue(as[j]);

    }

    public void addValue(String s)
    {
        if(s != null && s.length() > 5)
            add(s);
    }

    private static final int MIN_URL_LENGTH = 5;
    private static final long serialVersionUID = 0xc46756db9d1d4e39L;
}

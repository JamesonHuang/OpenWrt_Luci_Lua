// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import java.util.concurrent.ExecutorService;

class BaseExecutor
{

    BaseExecutor()
    {
    }

    protected static volatile ExecutorService executor = null;

}

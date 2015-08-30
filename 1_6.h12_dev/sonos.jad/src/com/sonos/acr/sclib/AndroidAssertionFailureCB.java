// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib;

import com.sonos.acr.util.SLog;
import com.sonos.sclib.SCLibAssertionFailureCallback;

class AndroidAssertionFailureCB extends SCLibAssertionFailureCallback
{

    AndroidAssertionFailureCB()
    {
    }

    public void assertionFailed(String s, int i, String s1)
    {
        Object aobj[] = new Object[3];
        aobj[0] = s;
        aobj[1] = Integer.valueOf(i);
        aobj[2] = s1;
        SLog.d("ASSERT", String.format("%s(%d): %s", aobj));
        throw new RuntimeException("Assert Failed!!!!");
    }
}

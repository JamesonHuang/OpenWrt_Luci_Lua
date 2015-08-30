// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI, SCIEnumerator

public class SCIOpNetstartGetScanList extends SCIOp
{

    protected SCIOpNetstartGetScanList(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpNetstartGetScanListUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpNetstartGetScanList(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpNetstartGetScanList(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpNetstartGetScanList sciopnetstartgetscanlist)
    {
        long l;
        if(sciopnetstartgetscanlist == null)
            l = 0L;
        else
            l = sciopnetstartgetscanlist.swigCPtr;
        return l;
    }

    /**
     * @deprecated Method dispose is deprecated
     */

    public void dispose()
    {
        this;
        JVM INSTR monitorenter ;
        swigCPtr = 0L;
        dispose();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public SCIEnumerator getEntries()
    {
        long l = sclibJNI.SCIOpNetstartGetScanList_getEntries(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIEnumerator getEntriesWithBSSID(String s, String s1)
    {
        long l = sclibJNI.SCIOpNetstartGetScanList_getEntriesWithBSSID(swigCPtr, this, s, s1);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public int getNumEntries()
    {
        return sclibJNI.SCIOpNetstartGetScanList_getNumEntries(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpNetstartGetScanList");
    private long swigCPtr;

}

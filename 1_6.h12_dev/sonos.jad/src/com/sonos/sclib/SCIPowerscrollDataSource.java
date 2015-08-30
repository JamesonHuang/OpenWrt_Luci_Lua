// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIPowerscrollDataSource extends SCIObj
{

    protected SCIPowerscrollDataSource(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIPowerscrollDataSourceUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIPowerscrollDataSource(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIPowerscrollDataSource(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIPowerscrollDataSource scipowerscrolldatasource)
    {
        long l;
        if(scipowerscrolldatasource == null)
            l = 0L;
        else
            l = scipowerscrolldatasource.swigCPtr;
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

    public long getCountForPrefix(String s)
    {
        return sclibJNI.SCIPowerscrollDataSource_getCountForPrefix(swigCPtr, this, s);
    }

    public String getPowerscrollCSV()
    {
        return sclibJNI.SCIPowerscrollDataSource_getPowerscrollCSV(swigCPtr, this);
    }

    public String getPrefixForIndex(long l)
    {
        return sclibJNI.SCIPowerscrollDataSource_getPrefixForIndex(swigCPtr, this, l);
    }

    public int getStartIndexForPrefix(String s)
    {
        return sclibJNI.SCIPowerscrollDataSource_getStartIndexForPrefix(swigCPtr, this, s);
    }

    public boolean hasPrefixData()
    {
        return sclibJNI.SCIPowerscrollDataSource_hasPrefixData(swigCPtr, this);
    }

    public boolean isPowerscrollInfoReady()
    {
        return sclibJNI.SCIPowerscrollDataSource_isPowerscrollInfoReady(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIPowerscrollDataSource");
    private long swigCPtr;

}

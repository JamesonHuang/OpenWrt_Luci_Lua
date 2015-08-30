// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCILatentLoadingDataSource extends SCIObj
{

    protected SCILatentLoadingDataSource(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCILatentLoadingDataSourceUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCILatentLoadingDataSource(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCILatentLoadingDataSource(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCILatentLoadingDataSource scilatentloadingdatasource)
    {
        long l;
        if(scilatentloadingdatasource == null)
            l = 0L;
        else
            l = scilatentloadingdatasource.swigCPtr;
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

    public boolean isFetchingData()
    {
        return sclibJNI.SCILatentLoadingDataSource_isFetchingData(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCILatentLoadingDataSource");
    private long swigCPtr;

}

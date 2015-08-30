// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIAggregateBrowseDataSource extends SCIObj
{

    protected SCIAggregateBrowseDataSource(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIAggregateBrowseDataSourceUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIAggregateBrowseDataSource(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIAggregateBrowseDataSource(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIAggregateBrowseDataSource sciaggregatebrowsedatasource)
    {
        long l;
        if(sciaggregatebrowsedatasource == null)
            l = 0L;
        else
            l = sciaggregatebrowsedatasource.swigCPtr;
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

    public long getNumRecommendedPresentationItems()
    {
        return sclibJNI.SCIAggregateBrowseDataSource_getNumRecommendedPresentationItems(swigCPtr, this);
    }

    public void setNumRecommendedPresentationItems(long l)
    {
        sclibJNI.SCIAggregateBrowseDataSource_setNumRecommendedPresentationItems(swigCPtr, this, l);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIAggregateBrowseDataSource");
    private long swigCPtr;

}

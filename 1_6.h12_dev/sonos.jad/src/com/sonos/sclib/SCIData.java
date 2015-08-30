// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIData extends SCIObj
{

    protected SCIData(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIDataUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIData(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIData(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIData scidata)
    {
        long l;
        if(scidata == null)
            l = 0L;
        else
            l = scidata.swigCPtr;
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

    public void endReading()
    {
        sclibJNI.SCIData_endReading(swigCPtr, this);
    }

    public int getBytes(long l, String s, long l1)
    {
        return sclibJNI.SCIData_getBytes(swigCPtr, this, l, s, l1);
    }

    public String getMimeType()
    {
        return sclibJNI.SCIData_getMimeType(swigCPtr, this);
    }

    public boolean prepareForReading()
    {
        return sclibJNI.SCIData_prepareForReading(swigCPtr, this);
    }

    public int setImageBytes(byte abyte0[], long l)
    {
        return sclibJNI.SCIData_setImageBytes(swigCPtr, this, abyte0, l);
    }

    public long totalBytes()
    {
        return sclibJNI.SCIData_totalBytes(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIData");
    private long swigCPtr;

}

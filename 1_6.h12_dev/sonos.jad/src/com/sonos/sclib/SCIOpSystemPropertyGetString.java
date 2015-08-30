// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpSystemPropertyGetString extends SCIOp
{

    protected SCIOpSystemPropertyGetString(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpSystemPropertyGetStringUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpSystemPropertyGetString(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpSystemPropertyGetString(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpSystemPropertyGetString sciopsystempropertygetstring)
    {
        long l;
        if(sciopsystempropertygetstring == null)
            l = 0L;
        else
            l = sciopsystempropertygetstring.swigCPtr;
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

    public String getString()
    {
        return sclibJNI.SCIOpSystemPropertyGetString_getString(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpSystemPropertyGetString");
    private long swigCPtr;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIActionFilter, sclibJNI

public class SCIActionFilterSwigBase extends SCIActionFilter
{

    public SCIActionFilterSwigBase()
    {
        SCIActionFilterSwigBase(sclibJNI.new_SCIActionFilterSwigBase(), true);
        sclibJNI.SCIActionFilterSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCIActionFilterSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIActionFilter(sclibJNI.SWIGSCIActionFilterSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIActionFilterSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIActionFilterSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIActionFilterSwigBase sciactionfilterswigbase)
    {
        long l;
        if(sciactionfilterswigbase == null)
            l = 0L;
        else
            l = sciactionfilterswigbase.swigCPtr;
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

    public String dumpSCIObj()
    {
        String s;
        if(getClass() == com/sonos/sclib/SCIActionFilterSwigBase)
            s = sclibJNI.SCIActionFilterSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCIActionFilterSwigBase_dumpSCIObjSwigExplicitSCIActionFilterSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIActionFilterSwigBase");
    private long swigCPtr;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIBrowseItem, sclibJNI

public class SCIBrowseItemSwigBase extends SCIBrowseItem
{

    public SCIBrowseItemSwigBase()
    {
        SCIBrowseItemSwigBase(sclibJNI.new_SCIBrowseItemSwigBase(), true);
        sclibJNI.SCIBrowseItemSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCIBrowseItemSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIBrowseItem(sclibJNI.SWIGSCIBrowseItemSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIBrowseItemSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIBrowseItemSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        long l;
        if(scibrowseitemswigbase == null)
            l = 0L;
        else
            l = scibrowseitemswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCIBrowseItemSwigBase)
            s = sclibJNI.SCIBrowseItemSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCIBrowseItemSwigBase_dumpSCIObjSwigExplicitSCIBrowseItemSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIBrowseItemSwigBase");
    private long swigCPtr;

}

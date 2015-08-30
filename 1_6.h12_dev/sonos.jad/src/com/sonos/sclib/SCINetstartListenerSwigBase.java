// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCINetstartListener, sclibJNI

public class SCINetstartListenerSwigBase extends SCINetstartListener
{

    public SCINetstartListenerSwigBase()
    {
        SCINetstartListenerSwigBase(sclibJNI.new_SCINetstartListenerSwigBase(), true);
        sclibJNI.SCINetstartListenerSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCINetstartListenerSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCINetstartListener(sclibJNI.SWIGSCINetstartListenerSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCINetstartListenerSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCINetstartListenerSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCINetstartListenerSwigBase scinetstartlistenerswigbase)
    {
        long l;
        if(scinetstartlistenerswigbase == null)
            l = 0L;
        else
            l = scinetstartlistenerswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCINetstartListenerSwigBase)
            s = sclibJNI.SCINetstartListenerSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCINetstartListenerSwigBase_dumpSCIObjSwigExplicitSCINetstartListenerSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCINetstartListenerSwigBase");
    private long swigCPtr;

}

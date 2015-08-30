// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIMusicServerBrowseDelegate, sclibJNI

public class SCIMusicServerBrowseDelegateSwigBase extends SCIMusicServerBrowseDelegate
{

    public SCIMusicServerBrowseDelegateSwigBase()
    {
        SCIMusicServerBrowseDelegateSwigBase(sclibJNI.new_SCIMusicServerBrowseDelegateSwigBase(), true);
        sclibJNI.SCIMusicServerBrowseDelegateSwigBase_director_connect(this, swigCPtr, true, true);
    }

    protected SCIMusicServerBrowseDelegateSwigBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIMusicServerBrowseDelegate(sclibJNI.SWIGSCIMusicServerBrowseDelegateSwigBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIMusicServerBrowseDelegateSwigBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIMusicServerBrowseDelegateSwigBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIMusicServerBrowseDelegateSwigBase scimusicserverbrowsedelegateswigbase)
    {
        long l;
        if(scimusicserverbrowsedelegateswigbase == null)
            l = 0L;
        else
            l = scimusicserverbrowsedelegateswigbase.swigCPtr;
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
        if(getClass() == com/sonos/sclib/SCIMusicServerBrowseDelegateSwigBase)
            s = sclibJNI.SCIMusicServerBrowseDelegateSwigBase_dumpSCIObj(swigCPtr, this);
        else
            s = sclibJNI.SCIMusicServerBrowseDelegateSwigBase_dumpSCIObjSwigExplicitSCIMusicServerBrowseDelegateSwigBase(swigCPtr, this);
        return s;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIMusicServerBrowseDelegateSwigBase");
    private long swigCPtr;

}

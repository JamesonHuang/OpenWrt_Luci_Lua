// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCINetstartScanListEntry extends SCIObj
{

    protected SCINetstartScanListEntry(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCINetstartScanListEntryUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCINetstartScanListEntry(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCINetstartScanListEntry(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCINetstartScanListEntry scinetstartscanlistentry)
    {
        long l;
        if(scinetstartscanlistentry == null)
            l = 0L;
        else
            l = scinetstartscanlistentry.swigCPtr;
        return l;
    }

    public boolean canConnect()
    {
        return sclibJNI.SCINetstartScanListEntry_canConnect(swigCPtr, this);
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

    public int getChannel()
    {
        return sclibJNI.SCINetstartScanListEntry_getChannel(swigCPtr, this);
    }

    public String getSSID()
    {
        return sclibJNI.SCINetstartScanListEntry_getSSID(swigCPtr, this);
    }

    public int getSignalStrength()
    {
        return sclibJNI.SCINetstartScanListEntry_getSignalStrength(swigCPtr, this);
    }

    public boolean isHidden()
    {
        return sclibJNI.SCINetstartScanListEntry_isHidden(swigCPtr, this);
    }

    public boolean isOpen()
    {
        return sclibJNI.SCINetstartScanListEntry_isOpen(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCINetstartScanListEntry");
    private long swigCPtr;

}

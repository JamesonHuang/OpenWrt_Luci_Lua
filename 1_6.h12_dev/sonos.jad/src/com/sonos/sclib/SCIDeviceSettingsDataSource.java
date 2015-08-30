// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIDevice

public class SCIDeviceSettingsDataSource extends SCIObj
{

    protected SCIDeviceSettingsDataSource(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIDeviceSettingsDataSourceUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIDeviceSettingsDataSource(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIDeviceSettingsDataSource(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIDeviceSettingsDataSource scidevicesettingsdatasource)
    {
        long l;
        if(scidevicesettingsdatasource == null)
            l = 0L;
        else
            l = scidevicesettingsdatasource.swigCPtr;
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

    public SCIDevice getDevice()
    {
        long l = sclibJNI.SCIDeviceSettingsDataSource_getDevice(swigCPtr, this);
        SCIDevice scidevice;
        if(l == 0L)
            scidevice = null;
        else
            scidevice = new SCIDevice(l, true);
        return scidevice;
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIDeviceSettingsDataSource");
    private long swigCPtr;

}

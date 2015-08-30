// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIDeviceVolume, SCIEnumerator, 
//            SCIGroupVolumeMode, SCIEventSink

public class SCIGroupVolume extends SCIObj
{

    protected SCIGroupVolume(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIGroupVolumeUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIGroupVolume(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIGroupVolume(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIGroupVolume scigroupvolume)
    {
        long l;
        if(scigroupvolume == null)
            l = 0L;
        else
            l = scigroupvolume.swigCPtr;
        return l;
    }

    public boolean areAllGroupVolumesAdjustable()
    {
        return sclibJNI.SCIGroupVolume_areAllGroupVolumesAdjustable(swigCPtr, this);
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

    public SCIDeviceVolume getDeviceVolume(String s)
    {
        long l = sclibJNI.SCIGroupVolume_getDeviceVolume(swigCPtr, this, s);
        SCIDeviceVolume scidevicevolume;
        if(l == 0L)
            scidevicevolume = null;
        else
            scidevicevolume = new SCIDeviceVolume(l, true);
        return scidevicevolume;
    }

    public SCIEnumerator getDeviceVolumes()
    {
        long l = sclibJNI.SCIGroupVolume_getDeviceVolumes(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIEnumerator getDeviceVolumesEnum()
    {
        long l = sclibJNI.SCIGroupVolume_getDeviceVolumesEnum(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public String getGroupID()
    {
        return sclibJNI.SCIGroupVolume_getGroupID(swigCPtr, this);
    }

    public SCIGroupVolumeMode getGroupVolumeMode()
    {
        return SCIGroupVolumeMode.swigToEnum(sclibJNI.SCIGroupVolume_getGroupVolumeMode(swigCPtr, this));
    }

    public SCIDeviceVolume getMasterDeviceVolume()
    {
        long l = sclibJNI.SCIGroupVolume_getMasterDeviceVolume(swigCPtr, this);
        SCIDeviceVolume scidevicevolume;
        if(l == 0L)
            scidevicevolume = null;
        else
            scidevicevolume = new SCIDeviceVolume(l, true);
        return scidevicevolume;
    }

    public boolean isGroupVolumeAdjustable()
    {
        return sclibJNI.SCIGroupVolume_isGroupVolumeAdjustable(swigCPtr, this);
    }

    public boolean isValid()
    {
        return sclibJNI.SCIGroupVolume_isValid(swigCPtr, this);
    }

    public boolean isZoneGroupValid()
    {
        return sclibJNI.SCIGroupVolume_isZoneGroupValid(swigCPtr, this);
    }

    public void prepareForGroupVolumeAdjustments()
    {
        sclibJNI.SCIGroupVolume_prepareForGroupVolumeAdjustments(swigCPtr, this);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIGroupVolume_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIGroupVolume_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIGroupVolume");
    private long swigCPtr;

}

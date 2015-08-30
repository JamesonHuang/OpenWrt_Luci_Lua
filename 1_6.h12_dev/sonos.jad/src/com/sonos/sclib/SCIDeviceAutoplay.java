// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIOpDevicePropertiesGetAutoplayRoomUUID, SCIOpDevicePropertiesGetAutoplayLinkedZones, 
//            SCIOpDevicePropertiesGetAutoplayVolume, SCIOpDevicePropertiesGetUseAutoplayVolume, SCIOp

public class SCIDeviceAutoplay extends SCIObj
{

    protected SCIDeviceAutoplay(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIDeviceAutoplayUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIDeviceAutoplay(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIDeviceAutoplay(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIDeviceAutoplay scideviceautoplay)
    {
        long l;
        if(scideviceautoplay == null)
            l = 0L;
        else
            l = scideviceautoplay.swigCPtr;
        return l;
    }

    public SCIOpDevicePropertiesGetAutoplayRoomUUID createGetAutoplayDeviceOp()
    {
        long l = sclibJNI.SCIDeviceAutoplay_createGetAutoplayDeviceOp(swigCPtr, this);
        SCIOpDevicePropertiesGetAutoplayRoomUUID sciopdevicepropertiesgetautoplayroomuuid;
        if(l == 0L)
            sciopdevicepropertiesgetautoplayroomuuid = null;
        else
            sciopdevicepropertiesgetautoplayroomuuid = new SCIOpDevicePropertiesGetAutoplayRoomUUID(l, true);
        return sciopdevicepropertiesgetautoplayroomuuid;
    }

    public SCIOpDevicePropertiesGetAutoplayLinkedZones createGetAutoplayGroupedZoneOp()
    {
        long l = sclibJNI.SCIDeviceAutoplay_createGetAutoplayGroupedZoneOp(swigCPtr, this);
        SCIOpDevicePropertiesGetAutoplayLinkedZones sciopdevicepropertiesgetautoplaylinkedzones;
        if(l == 0L)
            sciopdevicepropertiesgetautoplaylinkedzones = null;
        else
            sciopdevicepropertiesgetautoplaylinkedzones = new SCIOpDevicePropertiesGetAutoplayLinkedZones(l, true);
        return sciopdevicepropertiesgetautoplaylinkedzones;
    }

    public SCIOpDevicePropertiesGetAutoplayVolume createGetAutoplayVolumeOp()
    {
        long l = sclibJNI.SCIDeviceAutoplay_createGetAutoplayVolumeOp(swigCPtr, this);
        SCIOpDevicePropertiesGetAutoplayVolume sciopdevicepropertiesgetautoplayvolume;
        if(l == 0L)
            sciopdevicepropertiesgetautoplayvolume = null;
        else
            sciopdevicepropertiesgetautoplayvolume = new SCIOpDevicePropertiesGetAutoplayVolume(l, true);
        return sciopdevicepropertiesgetautoplayvolume;
    }

    public SCIOpDevicePropertiesGetUseAutoplayVolume createGetUseAutoplayVolumeOp()
    {
        long l = sclibJNI.SCIDeviceAutoplay_createGetUseAutoplayVolumeOp(swigCPtr, this);
        SCIOpDevicePropertiesGetUseAutoplayVolume sciopdevicepropertiesgetuseautoplayvolume;
        if(l == 0L)
            sciopdevicepropertiesgetuseautoplayvolume = null;
        else
            sciopdevicepropertiesgetuseautoplayvolume = new SCIOpDevicePropertiesGetUseAutoplayVolume(l, true);
        return sciopdevicepropertiesgetuseautoplayvolume;
    }

    public SCIOp createSetAutoplayDeviceOp(String s)
    {
        long l = sclibJNI.SCIDeviceAutoplay_createSetAutoplayDeviceOp(swigCPtr, this, s);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetAutoplayGroupedZoneOp(boolean flag)
    {
        long l = sclibJNI.SCIDeviceAutoplay_createSetAutoplayGroupedZoneOp(swigCPtr, this, flag);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetAutoplayVolumeOp(long l)
    {
        long l1 = sclibJNI.SCIDeviceAutoplay_createSetAutoplayVolumeOp(swigCPtr, this, l);
        SCIOp sciop;
        if(l1 == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l1, true);
        return sciop;
    }

    public SCIOp createSetUseAutoplayVolumeOp(boolean flag)
    {
        long l = sclibJNI.SCIDeviceAutoplay_createSetUseAutoplayVolumeOp(swigCPtr, this, flag);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
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

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIDeviceAutoplay");
    private long swigCPtr;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIOpAudioInGetLineInLevel, SCIOpAudioInGetAudioInputAttributes, 
//            SCIconID, SCIOp

public class SCIDeviceLineIn extends SCIObj
{

    protected SCIDeviceLineIn(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIDeviceLineInUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIDeviceLineIn(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIDeviceLineIn(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIDeviceLineIn scidevicelinein)
    {
        long l;
        if(scidevicelinein == null)
            l = 0L;
        else
            l = scidevicelinein.swigCPtr;
        return l;
    }

    public SCIOpAudioInGetLineInLevel createGetLineInLevelOp()
    {
        long l = sclibJNI.SCIDeviceLineIn_createGetLineInLevelOp(swigCPtr, this);
        SCIOpAudioInGetLineInLevel sciopaudioingetlineinlevel;
        if(l == 0L)
            sciopaudioingetlineinlevel = null;
        else
            sciopaudioingetlineinlevel = new SCIOpAudioInGetLineInLevel(l, true);
        return sciopaudioingetlineinlevel;
    }

    public SCIOpAudioInGetAudioInputAttributes createGetLineInNameAndIconOp()
    {
        long l = sclibJNI.SCIDeviceLineIn_createGetLineInNameAndIconOp(swigCPtr, this);
        SCIOpAudioInGetAudioInputAttributes sciopaudioingetaudioinputattributes;
        if(l == 0L)
            sciopaudioingetaudioinputattributes = null;
        else
            sciopaudioingetaudioinputattributes = new SCIOpAudioInGetAudioInputAttributes(l, true);
        return sciopaudioingetaudioinputattributes;
    }

    public SCIOp createRenameLineInOp(String s, SCIconID sciconid)
    {
        long l = sclibJNI.SCIDeviceLineIn_createRenameLineInOp(swigCPtr, this, s, sciconid.swigValue());
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetLineInLevelOp(long l)
    {
        long l1 = sclibJNI.SCIDeviceLineIn_createSetLineInLevelOp(swigCPtr, this, l);
        SCIOp sciop;
        if(l1 == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l1, true);
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

    public String getLineInLevelStr(long l)
    {
        return sclibJNI.SCIDeviceLineIn_getLineInLevelStr(swigCPtr, this, l);
    }

    public int getNumLineInLevels()
    {
        return sclibJNI.SCIDeviceLineIn_getNumLineInLevels(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIDeviceLineIn");
    private long swigCPtr;

}

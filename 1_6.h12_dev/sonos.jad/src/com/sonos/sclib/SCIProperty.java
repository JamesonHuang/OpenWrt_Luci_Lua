// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIPropertyBag, SCPropertyType, 
//            SCIStringArray

public class SCIProperty extends SCIObj
{

    protected SCIProperty(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIPropertyUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIProperty(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIProperty(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIProperty sciproperty)
    {
        long l;
        if(sciproperty == null)
            l = 0L;
        else
            l = sciproperty.swigCPtr;
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

    public boolean getBoolValue()
    {
        return sclibJNI.SCIProperty_getBoolValue(swigCPtr, this);
    }

    public int getIntValue()
    {
        return sclibJNI.SCIProperty_getIntValue(swigCPtr, this);
    }

    public String getKey()
    {
        return sclibJNI.SCIProperty_getKey(swigCPtr, this);
    }

    public SCIPropertyBag getPropBagValue()
    {
        long l = sclibJNI.SCIProperty_getPropBagValue(swigCPtr, this);
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        return scipropertybag;
    }

    public SCPropertyType getPropType()
    {
        return SCPropertyType.swigToEnum(sclibJNI.SCIProperty_getPropType(swigCPtr, this));
    }

    public SCIStringArray getStrArrayValue()
    {
        long l = sclibJNI.SCIProperty_getStrArrayValue(swigCPtr, this);
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return scistringarray;
    }

    public String getStrValue()
    {
        return sclibJNI.SCIProperty_getStrValue(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIProperty");
    private long swigCPtr;

}

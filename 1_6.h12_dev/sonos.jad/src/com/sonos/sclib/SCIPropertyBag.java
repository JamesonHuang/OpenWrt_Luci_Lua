// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SWIGTYPE_p_bool, SWIGTYPE_p_int, 
//            SCIStringArray, SWIGTYPE_p_SCPtrT_SCIPropertyBag_t, SCPropertyType, SCIProperty, 
//            SWIGTYPE_p_SCPtrT_SCIStringArray_t

public class SCIPropertyBag extends SCIObj
{

    protected SCIPropertyBag(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIPropertyBagUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIPropertyBag(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIPropertyBag(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIPropertyBag scipropertybag)
    {
        long l;
        if(scipropertybag == null)
            l = 0L;
        else
            l = scipropertybag.swigCPtr;
        return l;
    }

    public void copyAllValuesTo(SCIPropertyBag scipropertybag)
    {
        sclibJNI.SCIPropertyBag_copyAllValuesTo(swigCPtr, this, getCPtr(scipropertybag), scipropertybag);
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

    public boolean doesPropExist(String s)
    {
        return sclibJNI.SCIPropertyBag_doesPropExist(swigCPtr, this, s);
    }

    public boolean getBoolProp(String s)
    {
        return sclibJNI.SCIPropertyBag_getBoolProp__SWIG_0(swigCPtr, this, s);
    }

    public boolean getBoolProp(String s, SWIGTYPE_p_bool swigtype_p_bool)
    {
        return sclibJNI.SCIPropertyBag_getBoolProp__SWIG_1(swigCPtr, this, s, SWIGTYPE_p_bool.getCPtr(swigtype_p_bool));
    }

    public int getIntProp(String s)
    {
        return sclibJNI.SCIPropertyBag_getIntProp__SWIG_0(swigCPtr, this, s);
    }

    public boolean getIntProp(String s, SWIGTYPE_p_int swigtype_p_int)
    {
        return sclibJNI.SCIPropertyBag_getIntProp__SWIG_1(swigCPtr, this, s, SWIGTYPE_p_int.getCPtr(swigtype_p_int));
    }

    public SCIStringArray getKeys()
    {
        long l = sclibJNI.SCIPropertyBag_getKeys(swigCPtr, this);
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return scistringarray;
    }

    public SCIPropertyBag getPropBagProp(String s)
    {
        long l = sclibJNI.SCIPropertyBag_getPropBagProp__SWIG_0(swigCPtr, this, s);
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        return scipropertybag;
    }

    public boolean getPropBagProp(String s, SWIGTYPE_p_SCPtrT_SCIPropertyBag_t swigtype_p_scptrt_scipropertybag_t)
    {
        return sclibJNI.SCIPropertyBag_getPropBagProp__SWIG_1(swigCPtr, this, s, SWIGTYPE_p_SCPtrT_SCIPropertyBag_t.getCPtr(swigtype_p_scptrt_scipropertybag_t));
    }

    public SCPropertyType getPropType(String s)
    {
        return SCPropertyType.swigToEnum(sclibJNI.SCIPropertyBag_getPropType(swigCPtr, this, s));
    }

    public SCIProperty getProperty(String s)
    {
        long l = sclibJNI.SCIPropertyBag_getProperty(swigCPtr, this, s);
        SCIProperty sciproperty;
        if(l == 0L)
            sciproperty = null;
        else
            sciproperty = new SCIProperty(l, true);
        return sciproperty;
    }

    public SCIStringArray getStrArrayProp(String s)
    {
        long l = sclibJNI.SCIPropertyBag_getStrArrayProp__SWIG_0(swigCPtr, this, s);
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return scistringarray;
    }

    public boolean getStrArrayProp(String s, SWIGTYPE_p_SCPtrT_SCIStringArray_t swigtype_p_scptrt_scistringarray_t)
    {
        return sclibJNI.SCIPropertyBag_getStrArrayProp__SWIG_1(swigCPtr, this, s, SWIGTYPE_p_SCPtrT_SCIStringArray_t.getCPtr(swigtype_p_scptrt_scistringarray_t));
    }

    public String getStrProp(String s)
    {
        return sclibJNI.SCIPropertyBag_getStrProp__SWIG_0(swigCPtr, this, s);
    }

    public boolean getStrProp(String s, Object aobj[])
    {
        return sclibJNI.SCIPropertyBag_getStrProp__SWIG_1(swigCPtr, this, s, aobj);
    }

    public void removeProp(String s)
    {
        sclibJNI.SCIPropertyBag_removeProp(swigCPtr, this, s);
    }

    public void setBoolProp(String s, boolean flag)
    {
        sclibJNI.SCIPropertyBag_setBoolProp(swigCPtr, this, s, flag);
    }

    public void setIntProp(String s, int i)
    {
        sclibJNI.SCIPropertyBag_setIntProp(swigCPtr, this, s, i);
    }

    public void setPropBagProp(String s, SCIPropertyBag scipropertybag)
    {
        sclibJNI.SCIPropertyBag_setPropBagProp(swigCPtr, this, s, getCPtr(scipropertybag), scipropertybag);
    }

    public void setStrArrayProp(String s, SCIStringArray scistringarray)
    {
        sclibJNI.SCIPropertyBag_setStrArrayProp(swigCPtr, this, s, SCIStringArray.getCPtr(scistringarray), scistringarray);
    }

    public void setStrProp(String s, String s1)
    {
        sclibJNI.SCIPropertyBag_setStrProp(swigCPtr, this, s, s1);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIPropertyBag");
    private long swigCPtr;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIEnumerator, SCISettingsProperty

public class SCISettingsBrowseItem extends SCIObj
{

    protected SCISettingsBrowseItem(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCISettingsBrowseItemUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCISettingsBrowseItem(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCISettingsBrowseItem(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCISettingsBrowseItem scisettingsbrowseitem)
    {
        long l;
        if(scisettingsbrowseitem == null)
            l = 0L;
        else
            l = scisettingsbrowseitem.swigCPtr;
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

    public SCIEnumerator getProperties()
    {
        long l = sclibJNI.SCISettingsBrowseItem_getProperties(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCISettingsProperty getProperty(long l)
    {
        long l1 = sclibJNI.SCISettingsBrowseItem_getProperty(swigCPtr, this, l);
        SCISettingsProperty scisettingsproperty;
        if(l1 == 0L)
            scisettingsproperty = null;
        else
            scisettingsproperty = new SCISettingsProperty(l1, true);
        return scisettingsproperty;
    }

    public String getValueString()
    {
        return sclibJNI.SCISettingsBrowseItem_getValueString(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCISettingsBrowseItem");
    private long swigCPtr;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIInfoViewHeaderItem extends SCIObj
{

    protected SCIInfoViewHeaderItem(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIInfoViewHeaderItemUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIInfoViewHeaderItem(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIInfoViewHeaderItem(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIInfoViewHeaderItem sciinfoviewheaderitem)
    {
        long l;
        if(sciinfoviewheaderitem == null)
            l = 0L;
        else
            l = sciinfoviewheaderitem.swigCPtr;
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

    public String getLabel()
    {
        return sclibJNI.SCIInfoViewHeaderItem_getLabel(swigCPtr, this);
    }

    public String getValue()
    {
        return sclibJNI.SCIInfoViewHeaderItem_getValue(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIInfoViewHeaderItem");
    private long swigCPtr;

}

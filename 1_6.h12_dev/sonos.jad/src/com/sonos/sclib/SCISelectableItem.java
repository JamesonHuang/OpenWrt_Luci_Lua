// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCISelectableItem extends SCIObj
{

    protected SCISelectableItem(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCISelectableItemUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCISelectableItem(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCISelectableItem(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCISelectableItem sciselectableitem)
    {
        long l;
        if(sciselectableitem == null)
            l = 0L;
        else
            l = sciselectableitem.swigCPtr;
        return l;
    }

    public boolean canSelect()
    {
        return sclibJNI.SCISelectableItem_canSelect(swigCPtr, this);
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

    public boolean isSelected()
    {
        return sclibJNI.SCISelectableItem_isSelected(swigCPtr, this);
    }

    public void setSelected(boolean flag)
    {
        sclibJNI.SCISelectableItem_setSelected(swigCPtr, this, flag);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCISelectableItem");
    private long swigCPtr;

}

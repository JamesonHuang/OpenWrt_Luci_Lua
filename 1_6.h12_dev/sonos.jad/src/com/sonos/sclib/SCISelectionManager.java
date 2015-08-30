// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIIntArray

public class SCISelectionManager extends SCIObj
{

    protected SCISelectionManager(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCISelectionManagerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCISelectionManager(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCISelectionManager(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCISelectionManager sciselectionmanager)
    {
        long l;
        if(sciselectionmanager == null)
            l = 0L;
        else
            l = sciselectionmanager.swigCPtr;
        return l;
    }

    public void deselectAll()
    {
        sclibJNI.SCISelectionManager_deselectAll(swigCPtr, this);
    }

    public void deselectRange(long l, long l1)
    {
        sclibJNI.SCISelectionManager_deselectRange(swigCPtr, this, l, l1);
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

    public int getNumOfSelectedItems()
    {
        return sclibJNI.SCISelectionManager_getNumOfSelectedItems(swigCPtr, this);
    }

    public SCIIntArray getSelectedItems()
    {
        long l = sclibJNI.SCISelectionManager_getSelectedItems(swigCPtr, this);
        SCIIntArray sciintarray;
        if(l == 0L)
            sciintarray = null;
        else
            sciintarray = new SCIIntArray(l, true);
        return sciintarray;
    }

    public void selectAll()
    {
        sclibJNI.SCISelectionManager_selectAll(swigCPtr, this);
    }

    public void selectRange(long l, long l1)
    {
        sclibJNI.SCISelectionManager_selectRange(swigCPtr, this, l, l1);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCISelectionManager");
    private long swigCPtr;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCISearchableCategory extends SCIObj
{

    protected SCISearchableCategory(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCISearchableCategoryUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCISearchableCategory(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCISearchableCategory(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCISearchableCategory scisearchablecategory)
    {
        long l;
        if(scisearchablecategory == null)
            l = 0L;
        else
            l = scisearchablecategory.swigCPtr;
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

    public String getCanonicalID()
    {
        return sclibJNI.SCISearchableCategory_getCanonicalID(swigCPtr, this);
    }

    public String getCanonicalName()
    {
        return sclibJNI.SCISearchableCategory_getCanonicalName(swigCPtr, this);
    }

    public String getCategoryId()
    {
        return sclibJNI.SCISearchableCategory_getCategoryId(swigCPtr, this);
    }

    public String getName()
    {
        return sclibJNI.SCISearchableCategory_getName(swigCPtr, this);
    }

    public String getSCUriForSearchTerm(String s)
    {
        return sclibJNI.SCISearchableCategory_getSCUriForSearchTerm(swigCPtr, this, s);
    }

    public boolean isIncremental()
    {
        return sclibJNI.SCISearchableCategory_isIncremental(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCISearchableCategory");
    private long swigCPtr;

}

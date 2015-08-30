// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIEnumerator, SCIServiceDescriptor

public class SCISearchable extends SCIObj
{

    protected SCISearchable(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCISearchableUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCISearchable(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCISearchable(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCISearchable scisearchable)
    {
        long l;
        if(scisearchable == null)
            l = 0L;
        else
            l = scisearchable.swigCPtr;
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

    public String getBrowseCPID()
    {
        return sclibJNI.SCISearchable_getBrowseCPID(swigCPtr, this);
    }

    public SCIEnumerator getCategories()
    {
        long l = sclibJNI.SCISearchable_getCategories(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public boolean getIsAggregatedSearchable()
    {
        return sclibJNI.SCISearchable_getIsAggregatedSearchable(swigCPtr, this);
    }

    public SCIBrowseItem.SCAlbumArtType getLogoType()
    {
        return SCIBrowseItem.SCAlbumArtType.swigToEnum(sclibJNI.SCISearchable_getLogoType(swigCPtr, this));
    }

    public String getLogoURL()
    {
        return sclibJNI.SCISearchable_getLogoURL(swigCPtr, this);
    }

    public String getPresentationTextForSearch()
    {
        return sclibJNI.SCISearchable_getPresentationTextForSearch(swigCPtr, this);
    }

    public SCIServiceDescriptor getServiceDescriptor()
    {
        long l = sclibJNI.SCISearchable_getServiceDescriptor(swigCPtr, this);
        SCIServiceDescriptor sciservicedescriptor;
        if(l == 0L)
            sciservicedescriptor = null;
        else
            sciservicedescriptor = new SCIServiceDescriptor(l, true);
        return sciservicedescriptor;
    }

    public String getShortTitle()
    {
        return sclibJNI.SCISearchable_getShortTitle(swigCPtr, this);
    }

    public String getTitle()
    {
        return sclibJNI.SCISearchable_getTitle(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCISearchable");
    private long swigCPtr;

}

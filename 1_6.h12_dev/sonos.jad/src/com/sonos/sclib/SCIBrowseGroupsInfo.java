// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIBrowseGroupsInfo extends SCIObj
{

    protected SCIBrowseGroupsInfo(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIBrowseGroupsInfoUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIBrowseGroupsInfo(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIBrowseGroupsInfo(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIBrowseGroupsInfo scibrowsegroupsinfo)
    {
        long l;
        if(scibrowsegroupsinfo == null)
            l = 0L;
        else
            l = scibrowsegroupsinfo.swigCPtr;
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

    public SCIBrowseItem.SCAlbumArtType getArtTypeForGroup(long l)
    {
        return SCIBrowseItem.SCAlbumArtType.swigToEnum(sclibJNI.SCIBrowseGroupsInfo_getArtTypeForGroup(swigCPtr, this, l));
    }

    public String getArtURLForGroup(long l)
    {
        return sclibJNI.SCIBrowseGroupsInfo_getArtURLForGroup__SWIG_0(swigCPtr, this, l);
    }

    public String getArtURLForGroup(long l, long l1)
    {
        return sclibJNI.SCIBrowseGroupsInfo_getArtURLForGroup__SWIG_1(swigCPtr, this, l, l1);
    }

    public String getBrowseableSCUriForGroup(long l)
    {
        return sclibJNI.SCIBrowseGroupsInfo_getBrowseableSCUriForGroup(swigCPtr, this, l);
    }

    public String getDescriptionForGroup(long l)
    {
        return sclibJNI.SCIBrowseGroupsInfo_getDescriptionForGroup(swigCPtr, this, l);
    }

    public long getNumGroups()
    {
        return sclibJNI.SCIBrowseGroupsInfo_getNumGroups(swigCPtr, this);
    }

    public long getStartIndexForGroup(long l)
    {
        return sclibJNI.SCIBrowseGroupsInfo_getStartIndexForGroup(swigCPtr, this, l);
    }

    public String getTitleForGroup(long l)
    {
        return sclibJNI.SCIBrowseGroupsInfo_getTitleForGroup(swigCPtr, this, l);
    }

    public boolean shouldDisplayHeaderForGroup(long l)
    {
        return sclibJNI.SCIBrowseGroupsInfo_shouldDisplayHeaderForGroup(swigCPtr, this, l);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIBrowseGroupsInfo");
    private long swigCPtr;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIInfoViewHeaderItem, SCIEventSink

public class SCIInfoViewHeaderDataSource extends SCIObj
{

    protected SCIInfoViewHeaderDataSource(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIInfoViewHeaderDataSourceUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIInfoViewHeaderDataSource(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIInfoViewHeaderDataSource(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIInfoViewHeaderDataSource sciinfoviewheaderdatasource)
    {
        long l;
        if(sciinfoviewheaderdatasource == null)
            l = 0L;
        else
            l = sciinfoviewheaderdatasource.swigCPtr;
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

    public SCIBrowseItem.SCAlbumArtType getAlbumArtType()
    {
        return SCIBrowseItem.SCAlbumArtType.swigToEnum(sclibJNI.SCIInfoViewHeaderDataSource_getAlbumArtType(swigCPtr, this));
    }

    public String getAlbumArtURL()
    {
        return sclibJNI.SCIInfoViewHeaderDataSource_getAlbumArtURL__SWIG_0(swigCPtr, this);
    }

    public String getAlbumArtURL(long l)
    {
        return sclibJNI.SCIInfoViewHeaderDataSource_getAlbumArtURL__SWIG_1(swigCPtr, this, l);
    }

    public SCIInfoViewHeaderItem getItemAtIndex(long l)
    {
        long l1 = sclibJNI.SCIInfoViewHeaderDataSource_getItemAtIndex(swigCPtr, this, l);
        SCIInfoViewHeaderItem sciinfoviewheaderitem;
        if(l1 == 0L)
            sciinfoviewheaderitem = null;
        else
            sciinfoviewheaderitem = new SCIInfoViewHeaderItem(l1, true);
        return sciinfoviewheaderitem;
    }

    public long getNumItems()
    {
        return sclibJNI.SCIInfoViewHeaderDataSource_getNumItems(swigCPtr, this);
    }

    public boolean isValid()
    {
        return sclibJNI.SCIInfoViewHeaderDataSource_isValid(swigCPtr, this);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIInfoViewHeaderDataSource_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIInfoViewHeaderDataSource_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIInfoViewHeaderDataSource");
    private long swigCPtr;

}

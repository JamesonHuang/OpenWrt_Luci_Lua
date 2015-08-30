// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIActionDescriptor, SCIBrowseDataSource, 
//            SCRet, SCNPAlbumArtIconID

public class SCINowPlayingSource extends SCIObj
{

    protected SCINowPlayingSource(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCINowPlayingSourceUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCINowPlayingSource(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCINowPlayingSource(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCINowPlayingSource scinowplayingsource)
    {
        long l;
        if(scinowplayingsource == null)
            l = 0L;
        else
            l = scinowplayingsource.swigCPtr;
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

    public String getAlbumArtURL()
    {
        return sclibJNI.SCINowPlayingSource_getAlbumArtURL__SWIG_0(swigCPtr, this);
    }

    public String getAlbumArtURL(long l)
    {
        return sclibJNI.SCINowPlayingSource_getAlbumArtURL__SWIG_1(swigCPtr, this, l);
    }

    public int getBase()
    {
        return sclibJNI.SCINowPlayingSource_getBase(swigCPtr, this);
    }

    public String getDefaultSharingString()
    {
        return sclibJNI.SCINowPlayingSource_getDefaultSharingString(swigCPtr, this);
    }

    public SCIActionDescriptor getInfoViewActionDescriptor()
    {
        long l = sclibJNI.SCINowPlayingSource_getInfoViewActionDescriptor(swigCPtr, this);
        SCIActionDescriptor sciactiondescriptor;
        if(l == 0L)
            sciactiondescriptor = null;
        else
            sciactiondescriptor = new SCIActionDescriptor(l, true);
        return sciactiondescriptor;
    }

    public SCIBrowseDataSource getMoreMenuDataSource()
    {
        long l = sclibJNI.SCINowPlayingSource_getMoreMenuDataSource(swigCPtr, this);
        SCIBrowseDataSource scibrowsedatasource;
        if(l == 0L)
            scibrowsedatasource = null;
        else
            scibrowsedatasource = new SCIBrowseDataSource(l, true);
        return scibrowsedatasource;
    }

    public String getNextTrackAlbumArtURL(long l)
    {
        return sclibJNI.SCINowPlayingSource_getNextTrackAlbumArtURL(swigCPtr, this, l);
    }

    public SCRet getNextTrackProperty(int i, Object aobj[])
    {
        return SCRet.swigToEnum(sclibJNI.SCINowPlayingSource_getNextTrackProperty(swigCPtr, this, i, aobj));
    }

    public boolean getNightMode()
    {
        return sclibJNI.SCINowPlayingSource_getNightMode(swigCPtr, this);
    }

    public SCRet getOneLineMetadata(Object aobj[])
    {
        return SCRet.swigToEnum(sclibJNI.SCINowPlayingSource_getOneLineMetadata(swigCPtr, this, aobj));
    }

    public SCNPAlbumArtIconID getPlaceholderAlbumArtIconID()
    {
        return SCNPAlbumArtIconID.swigToEnum(sclibJNI.SCINowPlayingSource_getPlaceholderAlbumArtIconID(swigCPtr, this));
    }

    public SCRet getProperty(int i, Object aobj[])
    {
        return SCRet.swigToEnum(sclibJNI.SCINowPlayingSource_getProperty(swigCPtr, this, i, aobj));
    }

    public String getPropertyLabel(int i)
    {
        return sclibJNI.SCINowPlayingSource_getPropertyLabel(swigCPtr, this, i);
    }

    public int getPropertyLabelID(int i)
    {
        return sclibJNI.SCINowPlayingSource_getPropertyLabelID(swigCPtr, this, i);
    }

    public String getSharingMenuTitle()
    {
        return sclibJNI.SCINowPlayingSource_getSharingMenuTitle(swigCPtr, this);
    }

    public boolean getTVDialogEnhancement()
    {
        return sclibJNI.SCINowPlayingSource_getTVDialogEnhancement(swigCPtr, this);
    }

    public SCRet getThreeLineMetadata(Object aobj[], Object aobj1[], Object aobj2[])
    {
        return SCRet.swigToEnum(sclibJNI.SCINowPlayingSource_getThreeLineMetadata(swigCPtr, this, aobj, aobj1, aobj2));
    }

    public SCRet getTwoLineMetadata(Object aobj[], Object aobj1[])
    {
        return SCRet.swigToEnum(sclibJNI.SCINowPlayingSource_getTwoLineMetadata(swigCPtr, this, aobj, aobj1));
    }

    public int getType()
    {
        return sclibJNI.SCINowPlayingSource_getType(swigCPtr, this);
    }

    public boolean isInQueue()
    {
        return sclibJNI.SCINowPlayingSource_isInQueue(swigCPtr, this);
    }

    public boolean isPlayNextAvailable()
    {
        return sclibJNI.SCINowPlayingSource_isPlayNextAvailable(swigCPtr, this);
    }

    public boolean isShareable()
    {
        return sclibJNI.SCINowPlayingSource_isShareable(swigCPtr, this);
    }

    public void setNightMode(boolean flag)
    {
        sclibJNI.SCINowPlayingSource_setNightMode(swigCPtr, this, flag);
    }

    public void setTVDialogEnhancement(boolean flag)
    {
        sclibJNI.SCINowPlayingSource_setTVDialogEnhancement(swigCPtr, this, flag);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCINowPlayingSource");
    private long swigCPtr;

}

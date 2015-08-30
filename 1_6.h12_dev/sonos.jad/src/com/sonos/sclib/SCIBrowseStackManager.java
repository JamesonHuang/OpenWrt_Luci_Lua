// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIStringArray, SCIEnumerator, 
//            SCIBrowseDataSource, SCRet, SCIEventSink

public class SCIBrowseStackManager extends SCIObj
{

    protected SCIBrowseStackManager(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIBrowseStackManagerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIBrowseStackManager(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIBrowseStackManager(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIBrowseStackManager scibrowsestackmanager)
    {
        long l;
        if(scibrowsestackmanager == null)
            l = 0L;
        else
            l = scibrowsestackmanager.swigCPtr;
        return l;
    }

    public void addExclusiveDataSourceFilter(String s, SCIStringArray scistringarray)
    {
        sclibJNI.SCIBrowseStackManager_addExclusiveDataSourceFilter(swigCPtr, this, s, SCIStringArray.getCPtr(scistringarray), scistringarray);
    }

    public boolean canPushExternalSources()
    {
        return sclibJNI.SCIBrowseStackManager_canPushExternalSources(swigCPtr, this);
    }

    public void clear()
    {
        sclibJNI.SCIBrowseStackManager_clear(swigCPtr, this);
    }

    public void clearAllDataSourceFilters()
    {
        sclibJNI.SCIBrowseStackManager_clearAllDataSourceFilters(swigCPtr, this);
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

    public SCIEnumerator getActions()
    {
        long l = sclibJNI.SCIBrowseStackManager_getActions(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public String getBrowseCPIDForTopUri()
    {
        return sclibJNI.SCIBrowseStackManager_getBrowseCPIDForTopUri(swigCPtr, this);
    }

    public SCIBrowseItem.SCAlbumArtType getHeaderAlbumArtType()
    {
        return SCIBrowseItem.SCAlbumArtType.swigToEnum(sclibJNI.SCIBrowseStackManager_getHeaderAlbumArtType(swigCPtr, this));
    }

    public String getHeaderAlbumArtURL()
    {
        return sclibJNI.SCIBrowseStackManager_getHeaderAlbumArtURL(swigCPtr, this);
    }

    public int getNumberOfStackLevelsInvalidated()
    {
        return sclibJNI.SCIBrowseStackManager_getNumberOfStackLevelsInvalidated(swigCPtr, this);
    }

    public String getServiceDescriptorIDForTopUri()
    {
        return sclibJNI.SCIBrowseStackManager_getServiceDescriptorIDForTopUri(swigCPtr, this);
    }

    public SCIBrowseDataSource getTopDataSource()
    {
        long l = sclibJNI.SCIBrowseStackManager_getTopDataSource(swigCPtr, this);
        SCIBrowseDataSource scibrowsedatasource;
        if(l == 0L)
            scibrowsedatasource = null;
        else
            scibrowsedatasource = new SCIBrowseDataSource(l, true);
        return scibrowsedatasource;
    }

    public String getTopTitle()
    {
        return sclibJNI.SCIBrowseStackManager_getTopTitle(swigCPtr, this);
    }

    public String getTopUri()
    {
        return sclibJNI.SCIBrowseStackManager_getTopUri(swigCPtr, this);
    }

    public boolean isTopUriASearch()
    {
        return sclibJNI.SCIBrowseStackManager_isTopUriASearch(swigCPtr, this);
    }

    public boolean pop()
    {
        return sclibJNI.SCIBrowseStackManager_pop(swigCPtr, this);
    }

    public int popToSCUri(String s)
    {
        return sclibJNI.SCIBrowseStackManager_popToSCUri(swigCPtr, this, s);
    }

    public SCRet push(String s)
    {
        return SCRet.swigToEnum(sclibJNI.SCIBrowseStackManager_push__SWIG_0(swigCPtr, this, s));
    }

    public SCRet push(String s, String s1)
    {
        return SCRet.swigToEnum(sclibJNI.SCIBrowseStackManager_push__SWIG_1(swigCPtr, this, s, s1));
    }

    public boolean setSearchTerm(String s)
    {
        return sclibJNI.SCIBrowseStackManager_setSearchTerm(swigCPtr, this, s);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIBrowseStackManager_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIBrowseStackManager_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIBrowseStackManager");
    private long swigCPtr;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIData, SCIMusicServerBrowseDelegate, 
//            SCIAudioData, SCIEnumerator

public class SCIMusicServer extends SCIObj
{

    protected SCIMusicServer(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIMusicServerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIMusicServer(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIMusicServer(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIMusicServer scimusicserver)
    {
        long l;
        if(scimusicserver == null)
            l = 0L;
        else
            l = scimusicserver.swigCPtr;
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

    public SCIData getImageData(String s)
    {
        long l = sclibJNI.SCIMusicServer_getImageData(swigCPtr, this, s);
        SCIData scidata;
        if(l == 0L)
            scidata = null;
        else
            scidata = new SCIData(l, true);
        return scidata;
    }

    public SCIMusicServerBrowseDelegate getMusicServerBrowseDelegate()
    {
        long l = sclibJNI.SCIMusicServer_getMusicServerBrowseDelegate(swigCPtr, this);
        SCIMusicServerBrowseDelegate scimusicserverbrowsedelegate;
        if(l == 0L)
            scimusicserverbrowsedelegate = null;
        else
            scimusicserverbrowsedelegate = new SCIMusicServerBrowseDelegate(l, true);
        return scimusicserverbrowsedelegate;
    }

    public SCIAudioData getTrackData(String s)
    {
        long l = sclibJNI.SCIMusicServer_getTrackData(swigCPtr, this, s);
        SCIAudioData sciaudiodata;
        if(l == 0L)
            sciaudiodata = null;
        else
            sciaudiodata = new SCIAudioData(l, true);
        return sciaudiodata;
    }

    public SCIEnumerator getTracksForPlaylistId(String s)
    {
        long l = sclibJNI.SCIMusicServer_getTracksForPlaylistId(swigCPtr, this, s);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIMusicServer");
    private long swigCPtr;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIData, SCIMusicServerBrowseDelegate

public class SCIMusicServerDelegate extends SCIObj
{

    protected SCIMusicServerDelegate(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIMusicServerDelegateUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIMusicServerDelegate(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIMusicServerDelegate(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIMusicServerDelegate scimusicserverdelegate)
    {
        long l;
        if(scimusicserverdelegate == null)
            l = 0L;
        else
            l = scimusicserverdelegate.swigCPtr;
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

    public void fillImageBytes(SCIData scidata, String s)
    {
        sclibJNI.SCIMusicServerDelegate_fillImageBytes(swigCPtr, this, SCIData.getCPtr(scidata), scidata, s);
    }

    public SCIMusicServerBrowseDelegate getMusicServerBrowseDelegate()
    {
        long l = sclibJNI.SCIMusicServerDelegate_getMusicServerBrowseDelegate(swigCPtr, this);
        SCIMusicServerBrowseDelegate scimusicserverbrowsedelegate;
        if(l == 0L)
            scimusicserverbrowsedelegate = null;
        else
            scimusicserverbrowsedelegate = new SCIMusicServerBrowseDelegate(l, true);
        return scimusicserverbrowsedelegate;
    }

    public String getPlayableTrackId(String s)
    {
        return sclibJNI.SCIMusicServerDelegate_getPlayableTrackId(swigCPtr, this, s);
    }

    public void onBeginStreaming()
    {
        sclibJNI.SCIMusicServerDelegate_onBeginStreaming(swigCPtr, this);
    }

    public void onEndStreaming()
    {
        sclibJNI.SCIMusicServerDelegate_onEndStreaming(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIMusicServerDelegate");
    private long swigCPtr;

}

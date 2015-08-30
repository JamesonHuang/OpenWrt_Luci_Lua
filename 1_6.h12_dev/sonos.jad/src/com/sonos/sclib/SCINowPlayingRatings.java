// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIActionContext

public class SCINowPlayingRatings extends SCIObj
{

    protected SCINowPlayingRatings(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCINowPlayingRatingsUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCINowPlayingRatings(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCINowPlayingRatings(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCINowPlayingRatings scinowplayingratings)
    {
        long l;
        if(scinowplayingratings == null)
            l = 0L;
        else
            l = scinowplayingratings.swigCPtr;
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

    public SCIActionContext getActionForRating(int i)
    {
        long l = sclibJNI.SCINowPlayingRatings_getActionForRating(swigCPtr, this, i);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    public String getRatingDisplayString(int i)
    {
        return sclibJNI.SCINowPlayingRatings_getRatingDisplayString(swigCPtr, this, i);
    }

    public SCIBrowseItem.SCAlbumArtType getRatingImageType(int i)
    {
        return SCIBrowseItem.SCAlbumArtType.swigToEnum(sclibJNI.SCINowPlayingRatings_getRatingImageType(swigCPtr, this, i));
    }

    public String getRatingImageURL(int i)
    {
        return sclibJNI.SCINowPlayingRatings_getRatingImageURL(swigCPtr, this, i);
    }

    public boolean isRatingVisible(int i)
    {
        return sclibJNI.SCINowPlayingRatings_isRatingVisible(swigCPtr, this, i);
    }

    public int numberOfRatings()
    {
        return sclibJNI.SCINowPlayingRatings_numberOfRatings(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCINowPlayingRatings");
    private long swigCPtr;

}

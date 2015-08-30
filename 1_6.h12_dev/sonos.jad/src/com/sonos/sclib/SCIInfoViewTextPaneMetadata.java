// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIInfoViewTextPaneMetadata extends SCIObj
{

    protected SCIInfoViewTextPaneMetadata(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIInfoViewTextPaneMetadataUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIInfoViewTextPaneMetadata(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIInfoViewTextPaneMetadata(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata)
    {
        long l;
        if(sciinfoviewtextpanemetadata == null)
            l = 0L;
        else
            l = sciinfoviewtextpanemetadata.swigCPtr;
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

    public String getAlbumArtURL(long l)
    {
        return sclibJNI.SCIInfoViewTextPaneMetadata_getAlbumArtURL(swigCPtr, this, l);
    }

    public String getArtist()
    {
        return sclibJNI.SCIInfoViewTextPaneMetadata_getArtist(swigCPtr, this);
    }

    public String getTitle()
    {
        return sclibJNI.SCIInfoViewTextPaneMetadata_getTitle(swigCPtr, this);
    }

    public boolean isAlbum()
    {
        return sclibJNI.SCIInfoViewTextPaneMetadata_isAlbum(swigCPtr, this);
    }

    public boolean isArtist()
    {
        return sclibJNI.SCIInfoViewTextPaneMetadata_isArtist(swigCPtr, this);
    }

    public boolean isTrack()
    {
        return sclibJNI.SCIInfoViewTextPaneMetadata_isTrack(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIInfoViewTextPaneMetadata");
    private long swigCPtr;

}

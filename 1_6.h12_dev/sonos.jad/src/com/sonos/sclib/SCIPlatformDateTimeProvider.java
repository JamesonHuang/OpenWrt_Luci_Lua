// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import com.sonos.jniutil.NativeWeakReferenceHelper;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            sclibJNI, SCITimeZone, SCISystemTime

public class SCIPlatformDateTimeProvider
{

    public SCIPlatformDateTimeProvider()
    {
        SCIPlatformDateTimeProvider(sclibJNI.new_SCIPlatformDateTimeProvider(), true);
        sclibJNI.SCIPlatformDateTimeProvider_director_connect(this, swigCPtr, true, true);
    }

    protected SCIPlatformDateTimeProvider(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        swigCPtr = l;
        nativeRef = new NativeWeakReferenceHelper(this, nativecleanupinvocation);
    }

    protected SCIPlatformDateTimeProvider(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIPlatformDateTimeProvider(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIPlatformDateTimeProvider sciplatformdatetimeprovider)
    {
        long l;
        if(sciplatformdatetimeprovider == null)
            l = 0L;
        else
            l = sciplatformdatetimeprovider.swigCPtr;
        return l;
    }

    public void dispose()
    {
        if(swigCPtr != 0L)
        {
            if(nativeRef != null)
            {
                NativeWeakReferenceHelper nativeweakreferencehelper = nativeRef;
                nativeRef = null;
                nativeweakreferencehelper.dispose();
            }
            swigCPtr = 0L;
        }
    }

    public boolean doesPlatformTimeZoneMatch(SCITimeZone scitimezone)
    {
        return sclibJNI.SCIPlatformDateTimeProvider_doesPlatformTimeZoneMatch(swigCPtr, this, SCITimeZone.getCPtr(scitimezone), scitimezone);
    }

    public SCISystemTime getPlatformDateTime()
    {
        long l = sclibJNI.SCIPlatformDateTimeProvider_getPlatformDateTime(swigCPtr, this);
        SCISystemTime scisystemtime;
        if(l == 0L)
            scisystemtime = null;
        else
            scisystemtime = new SCISystemTime(l, true);
        return scisystemtime;
    }

    public NativeWeakReferenceHelper getWeakRefHelper()
    {
        return nativeRef;
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIPlatformDateTimeProvider");
    protected NativeWeakReferenceHelper nativeRef;
    private long swigCPtr;

}

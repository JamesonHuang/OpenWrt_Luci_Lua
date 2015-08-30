// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import com.sonos.jniutil.NativeWeakReferenceHelper;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            sclibJNI, SCIPropertyBag, SCIWifiListener

public class SCIWifiSetupDelegate
{

    public SCIWifiSetupDelegate()
    {
        SCIWifiSetupDelegate(sclibJNI.new_SCIWifiSetupDelegate(), true);
        sclibJNI.SCIWifiSetupDelegate_director_connect(this, swigCPtr, true, true);
    }

    protected SCIWifiSetupDelegate(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        swigCPtr = l;
        nativeRef = new NativeWeakReferenceHelper(this, nativecleanupinvocation);
    }

    protected SCIWifiSetupDelegate(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIWifiSetupDelegate(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIWifiSetupDelegate sciwifisetupdelegate)
    {
        long l;
        if(sciwifisetupdelegate == null)
            l = 0L;
        else
            l = sciwifisetupdelegate.swigCPtr;
        return l;
    }

    public void cancelSSIDJoin(String s)
    {
        sclibJNI.SCIWifiSetupDelegate_cancelSSIDJoin(swigCPtr, this, s);
    }

    public void clearListener()
    {
        sclibJNI.SCIWifiSetupDelegate_clearListener(swigCPtr, this);
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

    public NativeWeakReferenceHelper getWeakRefHelper()
    {
        return nativeRef;
    }

    public void getWifiInfo(SCIPropertyBag scipropertybag)
    {
        sclibJNI.SCIWifiSetupDelegate_getWifiInfo(swigCPtr, this, SCIPropertyBag.getCPtr(scipropertybag), scipropertybag);
    }

    public boolean isWifiConnected()
    {
        return sclibJNI.SCIWifiSetupDelegate_isWifiConnected(swigCPtr, this);
    }

    public void removeSSID(String s)
    {
        sclibJNI.SCIWifiSetupDelegate_removeSSID(swigCPtr, this, s);
    }

    public void setSSID(String s, String s1, long l, SCIWifiListener sciwifilistener)
    {
        sclibJNI.SCIWifiSetupDelegate_setSSID(swigCPtr, this, s, s1, l, SCIWifiListener.getCPtr(sciwifilistener), sciwifilistener);
    }

    protected void swigDirectorDisconnect()
    {
        nativeRef = null;
        dispose();
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIWifiSetupDelegate");
    protected NativeWeakReferenceHelper nativeRef;
    private long swigCPtr;

}

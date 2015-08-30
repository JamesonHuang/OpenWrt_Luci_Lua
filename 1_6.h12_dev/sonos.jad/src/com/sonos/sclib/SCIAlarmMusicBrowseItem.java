// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIAlarmMusic

public class SCIAlarmMusicBrowseItem extends SCIObj
{

    protected SCIAlarmMusicBrowseItem(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIAlarmMusicBrowseItemUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIAlarmMusicBrowseItem(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIAlarmMusicBrowseItem(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIAlarmMusicBrowseItem scialarmmusicbrowseitem)
    {
        long l;
        if(scialarmmusicbrowseitem == null)
            l = 0L;
        else
            l = scialarmmusicbrowseitem.swigCPtr;
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

    public int getAlarmId()
    {
        return sclibJNI.SCIAlarmMusicBrowseItem_getAlarmId(swigCPtr, this);
    }

    public SCIAlarmMusic getAlarmMusic()
    {
        long l = sclibJNI.SCIAlarmMusicBrowseItem_getAlarmMusic(swigCPtr, this);
        SCIAlarmMusic scialarmmusic;
        if(l == 0L)
            scialarmmusic = null;
        else
            scialarmmusic = new SCIAlarmMusic(l, true);
        return scialarmmusic;
    }

    public String getMusicTitle()
    {
        return sclibJNI.SCIAlarmMusicBrowseItem_getMusicTitle(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIAlarmMusicBrowseItem");
    private long swigCPtr;

}

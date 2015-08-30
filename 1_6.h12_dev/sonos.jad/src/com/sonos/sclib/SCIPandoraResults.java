// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCISearchable, SCIBrowseItem

public class SCIPandoraResults extends SCIObj
{

    protected SCIPandoraResults(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIPandoraResultsUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIPandoraResults(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIPandoraResults(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIPandoraResults scipandoraresults)
    {
        long l;
        if(scipandoraresults == null)
            l = 0L;
        else
            l = scipandoraresults.swigCPtr;
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

    public SCISearchable getAddToStationSearchable()
    {
        long l = sclibJNI.SCIPandoraResults_getAddToStationSearchable(swigCPtr, this);
        SCISearchable scisearchable;
        if(l == 0L)
            scisearchable = null;
        else
            scisearchable = new SCISearchable(l, true);
        return scisearchable;
    }

    public SCIBrowseItem getDirectMatchItem()
    {
        long l = sclibJNI.SCIPandoraResults_getDirectMatchItem(swigCPtr, this);
        SCIBrowseItem scibrowseitem;
        if(l == 0L)
            scibrowseitem = null;
        else
            scibrowseitem = new SCIBrowseItem(l, true);
        return scibrowseitem;
    }

    public long getNumArtistResults()
    {
        return sclibJNI.SCIPandoraResults_getNumArtistResults(swigCPtr, this);
    }

    public long getNumGenreResults()
    {
        return sclibJNI.SCIPandoraResults_getNumGenreResults(swigCPtr, this);
    }

    public long getNumTrackResults()
    {
        return sclibJNI.SCIPandoraResults_getNumTrackResults(swigCPtr, this);
    }

    public boolean hasDirectMatch()
    {
        return sclibJNI.SCIPandoraResults_hasDirectMatch(swigCPtr, this);
    }

    public boolean hasMoreArtistResults()
    {
        return sclibJNI.SCIPandoraResults_hasMoreArtistResults(swigCPtr, this);
    }

    public boolean hasMoreGenreResults()
    {
        return sclibJNI.SCIPandoraResults_hasMoreGenreResults(swigCPtr, this);
    }

    public boolean hasMoreTrackResults()
    {
        return sclibJNI.SCIPandoraResults_hasMoreTrackResults(swigCPtr, this);
    }

    public boolean isAddToStationResult()
    {
        return sclibJNI.SCIPandoraResults_isAddToStationResult(swigCPtr, this);
    }

    public void setShowAllArtists(boolean flag)
    {
        sclibJNI.SCIPandoraResults_setShowAllArtists(swigCPtr, this, flag);
    }

    public void setShowAllGenres(boolean flag)
    {
        sclibJNI.SCIPandoraResults_setShowAllGenres(swigCPtr, this, flag);
    }

    public void setShowAllTracks(boolean flag)
    {
        sclibJNI.SCIPandoraResults_setShowAllTracks(swigCPtr, this, flag);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIPandoraResults");
    private long swigCPtr;

}

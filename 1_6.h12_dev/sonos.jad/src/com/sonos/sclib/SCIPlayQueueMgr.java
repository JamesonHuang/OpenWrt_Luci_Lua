// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIStringArray, SCIOpAddTracksToQueue, 
//            SCIOp, SCIOpGenericUpdateQueue, SCIOpQueueReplaceAllTracks, SCIStringInput, 
//            SCIGetSonosPlaylistsCB, SCIEnumerator

public class SCIPlayQueueMgr extends SCIObj
{

    protected SCIPlayQueueMgr(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIPlayQueueMgrUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIPlayQueueMgr(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIPlayQueueMgr(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIPlayQueueMgr sciplayqueuemgr)
    {
        long l;
        if(sciplayqueuemgr == null)
            l = 0L;
        else
            l = sciplayqueuemgr.swigCPtr;
        return l;
    }

    public SCIOpAddTracksToQueue createAppendItemsOp(SCIStringArray scistringarray, SCIStringArray scistringarray1, boolean flag, boolean flag1, long l)
    {
        long l1 = sclibJNI.SCIPlayQueueMgr_createAppendItemsOp(swigCPtr, this, SCIStringArray.getCPtr(scistringarray), scistringarray, SCIStringArray.getCPtr(scistringarray1), scistringarray1, flag, flag1, l);
        SCIOpAddTracksToQueue sciopaddtrackstoqueue;
        if(l1 == 0L)
            sciopaddtrackstoqueue = null;
        else
            sciopaddtrackstoqueue = new SCIOpAddTracksToQueue(l1, true);
        return sciopaddtrackstoqueue;
    }

    public SCIOpAddTracksToQueue createInsertItemsOp(SCIStringArray scistringarray, SCIStringArray scistringarray1, long l, boolean flag, boolean flag1, long l1)
    {
        long l2 = sclibJNI.SCIPlayQueueMgr_createInsertItemsOp(swigCPtr, this, SCIStringArray.getCPtr(scistringarray), scistringarray, SCIStringArray.getCPtr(scistringarray1), scistringarray1, l, flag, flag1, l1);
        SCIOpAddTracksToQueue sciopaddtrackstoqueue;
        if(l2 == 0L)
            sciopaddtrackstoqueue = null;
        else
            sciopaddtrackstoqueue = new SCIOpAddTracksToQueue(l2, true);
        return sciopaddtrackstoqueue;
    }

    public SCIOp createMoveSelectedItemsOp(long l)
    {
        long l1 = sclibJNI.SCIPlayQueueMgr_createMoveSelectedItemsOp(swigCPtr, this, l);
        SCIOp sciop;
        if(l1 == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l1, true);
        return sciop;
    }

    public SCIOpGenericUpdateQueue createRemoveAllItemsOp(long l)
    {
        long l1 = sclibJNI.SCIPlayQueueMgr_createRemoveAllItemsOp(swigCPtr, this, l);
        SCIOpGenericUpdateQueue sciopgenericupdatequeue;
        if(l1 == 0L)
            sciopgenericupdatequeue = null;
        else
            sciopgenericupdatequeue = new SCIOpGenericUpdateQueue(l1, true);
        return sciopgenericupdatequeue;
    }

    public SCIOpGenericUpdateQueue createRemoveItemsOp(long l, long l1, long l2)
    {
        long l3 = sclibJNI.SCIPlayQueueMgr_createRemoveItemsOp(swigCPtr, this, l, l1, l2);
        SCIOpGenericUpdateQueue sciopgenericupdatequeue;
        if(l3 == 0L)
            sciopgenericupdatequeue = null;
        else
            sciopgenericupdatequeue = new SCIOpGenericUpdateQueue(l3, true);
        return sciopgenericupdatequeue;
    }

    public SCIOp createRemoveSelectedItemsOp()
    {
        long l = sclibJNI.SCIPlayQueueMgr_createRemoveSelectedItemsOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOpGenericUpdateQueue createReorderItemsOp(long l, long l1, long l2, long l3)
    {
        long l4 = sclibJNI.SCIPlayQueueMgr_createReorderItemsOp(swigCPtr, this, l, l1, l2, l3);
        SCIOpGenericUpdateQueue sciopgenericupdatequeue;
        if(l4 == 0L)
            sciopgenericupdatequeue = null;
        else
            sciopgenericupdatequeue = new SCIOpGenericUpdateQueue(l4, true);
        return sciopgenericupdatequeue;
    }

    public SCIOpQueueReplaceAllTracks createReplaceAllTracksOp(SCIStringArray scistringarray, SCIStringArray scistringarray1, long l, String s, long l1)
    {
        long l2 = sclibJNI.SCIPlayQueueMgr_createReplaceAllTracksOp(swigCPtr, this, SCIStringArray.getCPtr(scistringarray), scistringarray, SCIStringArray.getCPtr(scistringarray1), scistringarray1, l, s, l1);
        SCIOpQueueReplaceAllTracks sciopqueuereplacealltracks;
        if(l2 == 0L)
            sciopqueuereplacealltracks = null;
        else
            sciopqueuereplacealltracks = new SCIOpQueueReplaceAllTracks(l2, true);
        return sciopqueuereplacealltracks;
    }

    public SCIOp createSaveToSonosPlaylistOp(String s, String s1)
    {
        long l = sclibJNI.SCIPlayQueueMgr_createSaveToSonosPlaylistOp(swigCPtr, this, s, s1);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
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

    public int getCurrentItemIndex()
    {
        return sclibJNI.SCIPlayQueueMgr_getCurrentItemIndex(swigCPtr, this);
    }

    public String getRecommendedTitle()
    {
        return sclibJNI.SCIPlayQueueMgr_getRecommendedTitle(swigCPtr, this);
    }

    public SCIStringInput getSonosPlaylistValidator()
    {
        long l = sclibJNI.SCIPlayQueueMgr_getSonosPlaylistValidator(swigCPtr, this);
        SCIStringInput scistringinput;
        if(l == 0L)
            scistringinput = null;
        else
            scistringinput = new SCIStringInput(l, true);
        return scistringinput;
    }

    public void getSonosPlaylists(SCIGetSonosPlaylistsCB scigetsonosplaylistscb)
    {
        sclibJNI.SCIPlayQueueMgr_getSonosPlaylists(swigCPtr, this, SCIGetSonosPlaylistsCB.getCPtr(scigetsonosplaylistscb), scigetsonosplaylistscb);
    }

    public SCIEnumerator getSonosPlaylistsEnum()
    {
        long l = sclibJNI.SCIPlayQueueMgr_getSonosPlaylistsEnum(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public String getTrackCountTitle()
    {
        return sclibJNI.SCIPlayQueueMgr_getTrackCountTitle(swigCPtr, this);
    }

    public boolean isInUse()
    {
        return sclibJNI.SCIPlayQueueMgr_isInUse(swigCPtr, this);
    }

    public boolean isInfiniteQueue()
    {
        return sclibJNI.SCIPlayQueueMgr_isInfiniteQueue(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIPlayQueueMgr");
    private long swigCPtr;

}

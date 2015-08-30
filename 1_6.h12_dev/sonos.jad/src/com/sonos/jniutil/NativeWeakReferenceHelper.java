// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.jniutil;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package com.sonos.jniutil:
//            NativeCleanupInvocation

public class NativeWeakReferenceHelper extends WeakReference
{

    public NativeWeakReferenceHelper(Object obj, NativeCleanupInvocation nativecleanupinvocation)
    {
        super(obj, sm_referenceQueue);
        m_deleteInvocation = nativecleanupinvocation;
        sm_weakReferenceList.add(this);
    }

    public static void cleanupAllRemainingObjectsExceptFor(List list)
    {
        for(Iterator iterator = list.iterator(); iterator.hasNext(); sm_weakReferenceList.remove(iterator.next()));
        NativeWeakReferenceHelper nativeweakreferencehelper;
        for(Iterator iterator1 = sm_weakReferenceList.iterator(); iterator1.hasNext(); nativeweakreferencehelper.dispose())
        {
            nativeweakreferencehelper = (NativeWeakReferenceHelper)iterator1.next();
            iterator1.remove();
        }

    }

    public static boolean cleanupObjects(int i)
    {
        long l = System.currentTimeMillis();
_L4:
        NativeWeakReferenceHelper nativeweakreferencehelper = (NativeWeakReferenceHelper)sm_referenceQueue.poll();
        if(nativeweakreferencehelper == null) goto _L2; else goto _L1
_L1:
        nativeweakreferencehelper.dispose();
        if(i <= 0 || System.currentTimeMillis() - l <= (long)i) goto _L4; else goto _L3
_L3:
        boolean flag = false;
_L6:
        return flag;
_L2:
        flag = true;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static void forgetWeakReference(NativeWeakReferenceHelper nativeweakreferencehelper)
    {
        sm_weakReferenceList.remove(nativeweakreferencehelper);
    }

    public void dispose()
    {
        if(m_deleteInvocation != null)
            m_deleteInvocation.run();
        sm_weakReferenceList.remove(this);
    }

    private static ReferenceQueue sm_referenceQueue = new ReferenceQueue();
    private static Set sm_weakReferenceList = new HashSet();
    private NativeCleanupInvocation m_deleteInvocation;

}

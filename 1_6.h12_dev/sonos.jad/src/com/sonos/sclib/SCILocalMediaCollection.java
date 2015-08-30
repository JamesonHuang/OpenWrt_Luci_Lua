// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCILocalMusicBrowseItemInfo, SCILocalMediaCollectionListener

public class SCILocalMediaCollection extends SCIObj
{
    public static final class AllNodeType extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$002(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$008()
            {
                int i = next;
                next = i + 1;
                return i;
            }

*/

            private SwigNext()
            {
            }
        }


        public static AllNodeType swigToEnum(int i)
        {
            AllNodeType aallnodetype[] = (AllNodeType[])com/sonos/sclib/SCILocalMediaCollection$AllNodeType.getEnumConstants();
            if(i >= aallnodetype.length || i < 0 || aallnodetype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            AllNodeType allnodetype1 = aallnodetype[i];
_L4:
            return allnodetype1;
_L2:
            int j = aallnodetype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                AllNodeType allnodetype = aallnodetype[k];
                if(allnodetype.swigValue == i)
                {
                    allnodetype1 = allnodetype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCILocalMediaCollection$AllNodeType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static AllNodeType valueOf(String s)
        {
            return (AllNodeType)Enum.valueOf(com/sonos/sclib/SCILocalMediaCollection$AllNodeType, s);
        }

        public static AllNodeType[] values()
        {
            return (AllNodeType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final AllNodeType $VALUES[];
        public static final AllNodeType LMBI_ALL_NODE_ALL_TRACKS;
        public static final AllNodeType LMBI_ALL_NODE_COMPLETE_ALBUM;
        public static final AllNodeType LMBI_ALL_NODE_NONE;
        public static final AllNodeType LMBI_ALL_NODE_PUSHABLE_ALL;
        private final int swigValue;

        static 
        {
            LMBI_ALL_NODE_NONE = new AllNodeType("LMBI_ALL_NODE_NONE", 0);
            LMBI_ALL_NODE_ALL_TRACKS = new AllNodeType("LMBI_ALL_NODE_ALL_TRACKS", 1);
            LMBI_ALL_NODE_COMPLETE_ALBUM = new AllNodeType("LMBI_ALL_NODE_COMPLETE_ALBUM", 2);
            LMBI_ALL_NODE_PUSHABLE_ALL = new AllNodeType("LMBI_ALL_NODE_PUSHABLE_ALL", 3);
            AllNodeType aallnodetype[] = new AllNodeType[4];
            aallnodetype[0] = LMBI_ALL_NODE_NONE;
            aallnodetype[1] = LMBI_ALL_NODE_ALL_TRACKS;
            aallnodetype[2] = LMBI_ALL_NODE_COMPLETE_ALBUM;
            aallnodetype[3] = LMBI_ALL_NODE_PUSHABLE_ALL;
            $VALUES = aallnodetype;
        }

        private AllNodeType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private AllNodeType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private AllNodeType(String s, int i, AllNodeType allnodetype)
        {
            Enum(s, i);
            swigValue = allnodetype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCILocalMediaCollection(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCILocalMediaCollectionUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCILocalMediaCollection(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCILocalMediaCollection(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCILocalMediaCollection scilocalmediacollection)
    {
        long l;
        if(scilocalmediacollection == null)
            l = 0L;
        else
            l = scilocalmediacollection.swigCPtr;
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

    public AllNodeType getAllNodeType()
    {
        return AllNodeType.swigToEnum(sclibJNI.SCILocalMediaCollection_getAllNodeType(swigCPtr, this));
    }

    public long getCount()
    {
        return sclibJNI.SCILocalMediaCollection_getCount(swigCPtr, this);
    }

    public SCILocalMusicBrowseItemInfo getItemAt(long l)
    {
        long l1 = sclibJNI.SCILocalMediaCollection_getItemAt(swigCPtr, this, l);
        SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo;
        if(l1 == 0L)
            scilocalmusicbrowseiteminfo = null;
        else
            scilocalmusicbrowseiteminfo = new SCILocalMusicBrowseItemInfo(l1, true);
        return scilocalmusicbrowseiteminfo;
    }

    public String getPowerscrollCSV()
    {
        return sclibJNI.SCILocalMediaCollection_getPowerscrollCSV(swigCPtr, this);
    }

    public SCIBrowseListPresentationMap.SCListPresentationType getPresentationType()
    {
        return SCIBrowseListPresentationMap.SCListPresentationType.swigToEnum(sclibJNI.SCILocalMediaCollection_getPresentationType(swigCPtr, this));
    }

    public void registerMediaCollectionListener(SCILocalMediaCollectionListener scilocalmediacollectionlistener)
    {
        sclibJNI.SCILocalMediaCollection_registerMediaCollectionListener(swigCPtr, this, SCILocalMediaCollectionListener.getCPtr(scilocalmediacollectionlistener), scilocalmediacollectionlistener);
    }

    public boolean showTrackNumber()
    {
        return sclibJNI.SCILocalMediaCollection_showTrackNumber(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCILocalMediaCollection");
    private long swigCPtr;

}

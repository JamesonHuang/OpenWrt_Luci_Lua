// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCILocalMediaCollection, SCILocalMusicBrowseItemInfo, 
//            SCILocalMusicSearchableDelegate

public class SCIMusicServerBrowseDelegate extends SCIObj
{
    public static final class AssociationType extends Enum
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


        public static AssociationType swigToEnum(int i)
        {
            AssociationType aassociationtype[] = (AssociationType[])com/sonos/sclib/SCIMusicServerBrowseDelegate$AssociationType.getEnumConstants();
            if(i >= aassociationtype.length || i < 0 || aassociationtype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            AssociationType associationtype1 = aassociationtype[i];
_L4:
            return associationtype1;
_L2:
            int j = aassociationtype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                AssociationType associationtype = aassociationtype[k];
                if(associationtype.swigValue == i)
                {
                    associationtype1 = associationtype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIMusicServerBrowseDelegate$AssociationType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static AssociationType valueOf(String s)
        {
            return (AssociationType)Enum.valueOf(com/sonos/sclib/SCIMusicServerBrowseDelegate$AssociationType, s);
        }

        public static AssociationType[] values()
        {
            return (AssociationType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final AssociationType $VALUES[];
        public static final AssociationType ALBUM_TRACKS;
        public static final AssociationType ARTIST_ALBUMS;
        public static final AssociationType ARTIST_TRACKS;
        private final int swigValue;

        static 
        {
            ARTIST_ALBUMS = new AssociationType("ARTIST_ALBUMS", 0);
            ARTIST_TRACKS = new AssociationType("ARTIST_TRACKS", 1);
            ALBUM_TRACKS = new AssociationType("ALBUM_TRACKS", 2);
            AssociationType aassociationtype[] = new AssociationType[3];
            aassociationtype[0] = ARTIST_ALBUMS;
            aassociationtype[1] = ARTIST_TRACKS;
            aassociationtype[2] = ALBUM_TRACKS;
            $VALUES = aassociationtype;
        }

        private AssociationType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private AssociationType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private AssociationType(String s, int i, AssociationType associationtype)
        {
            Enum(s, i);
            swigValue = associationtype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIMusicServerBrowseDelegate(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIMusicServerBrowseDelegateUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIMusicServerBrowseDelegate(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIMusicServerBrowseDelegate(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIMusicServerBrowseDelegate scimusicserverbrowsedelegate)
    {
        long l;
        if(scimusicserverbrowsedelegate == null)
            l = 0L;
        else
            l = scimusicserverbrowsedelegate.swigCPtr;
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

    public SCILocalMediaCollection getLocalMediaCollectionForId(String s)
    {
        long l = sclibJNI.SCIMusicServerBrowseDelegate_getLocalMediaCollectionForId(swigCPtr, this, s);
        SCILocalMediaCollection scilocalmediacollection;
        if(l == 0L)
            scilocalmediacollection = null;
        else
            scilocalmediacollection = new SCILocalMediaCollection(l, true);
        return scilocalmediacollection;
    }

    public SCILocalMusicBrowseItemInfo getLocalMusicItemInfoForId(String s)
    {
        long l = sclibJNI.SCIMusicServerBrowseDelegate_getLocalMusicItemInfoForId(swigCPtr, this, s);
        SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo;
        if(l == 0L)
            scilocalmusicbrowseiteminfo = null;
        else
            scilocalmusicbrowseiteminfo = new SCILocalMusicBrowseItemInfo(l, true);
        return scilocalmusicbrowseiteminfo;
    }

    public SCILocalMusicSearchableDelegate getLocalMusicSearchableDelegate()
    {
        long l = sclibJNI.SCIMusicServerBrowseDelegate_getLocalMusicSearchableDelegate(swigCPtr, this);
        SCILocalMusicSearchableDelegate scilocalmusicsearchabledelegate;
        if(l == 0L)
            scilocalmusicsearchabledelegate = null;
        else
            scilocalmusicsearchabledelegate = new SCILocalMusicSearchableDelegate(l, true);
        return scilocalmusicsearchabledelegate;
    }

    public String getObjectIdForAssociationType(String s, AssociationType associationtype)
    {
        return sclibJNI.SCIMusicServerBrowseDelegate_getObjectIdForAssociationType(swigCPtr, this, s, associationtype.swigValue());
    }

    public SCILocalMusicBrowseItemInfo getRootItem()
    {
        long l = sclibJNI.SCIMusicServerBrowseDelegate_getRootItem(swigCPtr, this);
        SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo;
        if(l == 0L)
            scilocalmusicbrowseiteminfo = null;
        else
            scilocalmusicbrowseiteminfo = new SCILocalMusicBrowseItemInfo(l, true);
        return scilocalmusicbrowseiteminfo;
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIMusicServerBrowseDelegate");
    private long swigCPtr;

}

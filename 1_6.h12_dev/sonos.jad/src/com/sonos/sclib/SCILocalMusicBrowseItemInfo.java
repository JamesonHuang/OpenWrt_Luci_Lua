// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCITrackInfo, sclibJNI

public class SCILocalMusicBrowseItemInfo extends SCITrackInfo
{
    public static final class DisplayStringId extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$102(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$108()
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


        public static DisplayStringId swigToEnum(int i)
        {
            DisplayStringId adisplaystringid[] = (DisplayStringId[])com/sonos/sclib/SCILocalMusicBrowseItemInfo$DisplayStringId.getEnumConstants();
            if(i >= adisplaystringid.length || i < 0 || adisplaystringid[i].swigValue != i) goto _L2; else goto _L1
_L1:
            DisplayStringId displaystringid1 = adisplaystringid[i];
_L4:
            return displaystringid1;
_L2:
            int j = adisplaystringid.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                DisplayStringId displaystringid = adisplaystringid[k];
                if(displaystringid.swigValue == i)
                {
                    displaystringid1 = displaystringid;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCILocalMusicBrowseItemInfo$DisplayStringId).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static DisplayStringId valueOf(String s)
        {
            return (DisplayStringId)Enum.valueOf(com/sonos/sclib/SCILocalMusicBrowseItemInfo$DisplayStringId, s);
        }

        public static DisplayStringId[] values()
        {
            return (DisplayStringId[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final DisplayStringId $VALUES[];
        public static final DisplayStringId LMBI_DISPLAY_STRING_LINE_2;
        public static final DisplayStringId LMBI_DISPLAY_STRING_MAIN;
        private final int swigValue;

        static 
        {
            LMBI_DISPLAY_STRING_MAIN = new DisplayStringId("LMBI_DISPLAY_STRING_MAIN", 0);
            LMBI_DISPLAY_STRING_LINE_2 = new DisplayStringId("LMBI_DISPLAY_STRING_LINE_2", 1);
            DisplayStringId adisplaystringid[] = new DisplayStringId[2];
            adisplaystringid[0] = LMBI_DISPLAY_STRING_MAIN;
            adisplaystringid[1] = LMBI_DISPLAY_STRING_LINE_2;
            $VALUES = adisplaystringid;
        }

        private DisplayStringId(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private DisplayStringId(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private DisplayStringId(String s, int i, DisplayStringId displaystringid)
        {
            Enum(s, i);
            swigValue = displaystringid.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class ItemType extends Enum
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


        public static ItemType swigToEnum(int i)
        {
            ItemType aitemtype[] = (ItemType[])com/sonos/sclib/SCILocalMusicBrowseItemInfo$ItemType.getEnumConstants();
            if(i >= aitemtype.length || i < 0 || aitemtype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            ItemType itemtype1 = aitemtype[i];
_L4:
            return itemtype1;
_L2:
            int j = aitemtype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                ItemType itemtype = aitemtype[k];
                if(itemtype.swigValue == i)
                {
                    itemtype1 = itemtype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCILocalMusicBrowseItemInfo$ItemType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static ItemType valueOf(String s)
        {
            return (ItemType)Enum.valueOf(com/sonos/sclib/SCILocalMusicBrowseItemInfo$ItemType, s);
        }

        public static ItemType[] values()
        {
            return (ItemType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final ItemType $VALUES[];
        public static final ItemType ALBUM;
        public static final ItemType ARTIST;
        public static final ItemType PODCAST;
        public static final ItemType TRACK;
        private final int swigValue;

        static 
        {
            ALBUM = new ItemType("ALBUM", 0);
            ARTIST = new ItemType("ARTIST", 1);
            TRACK = new ItemType("TRACK", 2);
            PODCAST = new ItemType("PODCAST", 3);
            ItemType aitemtype[] = new ItemType[4];
            aitemtype[0] = ALBUM;
            aitemtype[1] = ARTIST;
            aitemtype[2] = TRACK;
            aitemtype[3] = PODCAST;
            $VALUES = aitemtype;
        }

        private ItemType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private ItemType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private ItemType(String s, int i, ItemType itemtype)
        {
            Enum(s, i);
            swigValue = itemtype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCILocalMusicBrowseItemInfo(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCITrackInfo(sclibJNI.SWIGSCILocalMusicBrowseItemInfoUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCILocalMusicBrowseItemInfo(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCILocalMusicBrowseItemInfo(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo)
    {
        long l;
        if(scilocalmusicbrowseiteminfo == null)
            l = 0L;
        else
            l = scilocalmusicbrowseiteminfo.swigCPtr;
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

    public String getArtMimeType()
    {
        return sclibJNI.SCILocalMusicBrowseItemInfo_getArtMimeType(swigCPtr, this);
    }

    public SCIBrowseItem.SCAlbumArtType getArtType()
    {
        return SCIBrowseItem.SCAlbumArtType.swigToEnum(sclibJNI.SCILocalMusicBrowseItemInfo_getArtType(swigCPtr, this));
    }

    public String getArtUri()
    {
        return sclibJNI.SCILocalMusicBrowseItemInfo_getArtUri(swigCPtr, this);
    }

    public int getByteOffsetForTime(long l)
    {
        return sclibJNI.SCILocalMusicBrowseItemInfo_getByteOffsetForTime(swigCPtr, this, l);
    }

    public String getDisplayString(DisplayStringId displaystringid)
    {
        return sclibJNI.SCILocalMusicBrowseItemInfo_getDisplayString(swigCPtr, this, displaystringid.swigValue());
    }

    public ItemType getItemType()
    {
        return ItemType.swigToEnum(sclibJNI.SCILocalMusicBrowseItemInfo_getItemType(swigCPtr, this));
    }

    public String getMimeType()
    {
        return sclibJNI.SCILocalMusicBrowseItemInfo_getMimeType(swigCPtr, this);
    }

    public int getTrackNumber()
    {
        return sclibJNI.SCILocalMusicBrowseItemInfo_getTrackNumber(swigCPtr, this);
    }

    public boolean isContainer()
    {
        return sclibJNI.SCILocalMusicBrowseItemInfo_isContainer(swigCPtr, this);
    }

    public boolean isPlayable()
    {
        return sclibJNI.SCILocalMusicBrowseItemInfo_isPlayable(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCILocalMusicBrowseItemInfo");
    private long swigCPtr;

}

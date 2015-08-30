// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIEnumerator, SCIPropertyBag, 
//            SCIActionFilter, SCIBrowseDataSource, SCIEventSink

public class SCIBrowseItem extends SCIObj
{
    public static final class SCAlbumArtType extends Enum
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


        public static SCAlbumArtType swigToEnum(int i)
        {
            SCAlbumArtType ascalbumarttype[] = (SCAlbumArtType[])com/sonos/sclib/SCIBrowseItem$SCAlbumArtType.getEnumConstants();
            if(i >= ascalbumarttype.length || i < 0 || ascalbumarttype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SCAlbumArtType scalbumarttype1 = ascalbumarttype[i];
_L4:
            return scalbumarttype1;
_L2:
            int j = ascalbumarttype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SCAlbumArtType scalbumarttype = ascalbumarttype[k];
                if(scalbumarttype.swigValue == i)
                {
                    scalbumarttype1 = scalbumarttype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIBrowseItem$SCAlbumArtType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SCAlbumArtType valueOf(String s)
        {
            return (SCAlbumArtType)Enum.valueOf(com/sonos/sclib/SCIBrowseItem$SCAlbumArtType, s);
        }

        public static SCAlbumArtType[] values()
        {
            return (SCAlbumArtType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SCAlbumArtType $VALUES[];
        public static final SCAlbumArtType ART_LOCAL;
        public static final SCAlbumArtType ART_LOCAL_URL;
        public static final SCAlbumArtType ART_LOGO;
        public static final SCAlbumArtType ART_NONE;
        public static final SCAlbumArtType ART_RESTRICTED;
        public static final SCAlbumArtType ART_TRACKNUM;
        public static final SCAlbumArtType ART_URL;
        public static final SCAlbumArtType ART_VIRTUAL_URL;
        private final int swigValue;

        static 
        {
            ART_URL = new SCAlbumArtType("ART_URL", 0);
            ART_VIRTUAL_URL = new SCAlbumArtType("ART_VIRTUAL_URL", 1);
            ART_LOCAL_URL = new SCAlbumArtType("ART_LOCAL_URL", 2);
            ART_LOGO = new SCAlbumArtType("ART_LOGO", 3);
            ART_LOCAL = new SCAlbumArtType("ART_LOCAL", 4);
            ART_TRACKNUM = new SCAlbumArtType("ART_TRACKNUM", 5);
            ART_RESTRICTED = new SCAlbumArtType("ART_RESTRICTED", 6);
            ART_NONE = new SCAlbumArtType("ART_NONE", 7);
            SCAlbumArtType ascalbumarttype[] = new SCAlbumArtType[8];
            ascalbumarttype[0] = ART_URL;
            ascalbumarttype[1] = ART_VIRTUAL_URL;
            ascalbumarttype[2] = ART_LOCAL_URL;
            ascalbumarttype[3] = ART_LOGO;
            ascalbumarttype[4] = ART_LOCAL;
            ascalbumarttype[5] = ART_TRACKNUM;
            ascalbumarttype[6] = ART_RESTRICTED;
            ascalbumarttype[7] = ART_NONE;
            $VALUES = ascalbumarttype;
        }

        private SCAlbumArtType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SCAlbumArtType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SCAlbumArtType(String s, int i, SCAlbumArtType scalbumarttype)
        {
            Enum(s, i);
            swigValue = scalbumarttype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class SCBrowseItemText extends Enum
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


        public static SCBrowseItemText swigToEnum(int i)
        {
            SCBrowseItemText ascbrowseitemtext[] = (SCBrowseItemText[])com/sonos/sclib/SCIBrowseItem$SCBrowseItemText.getEnumConstants();
            if(i >= ascbrowseitemtext.length || i < 0 || ascbrowseitemtext[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SCBrowseItemText scbrowseitemtext1 = ascbrowseitemtext[i];
_L4:
            return scbrowseitemtext1;
_L2:
            int j = ascbrowseitemtext.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SCBrowseItemText scbrowseitemtext = ascbrowseitemtext[k];
                if(scbrowseitemtext.swigValue == i)
                {
                    scbrowseitemtext1 = scbrowseitemtext;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIBrowseItem$SCBrowseItemText).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SCBrowseItemText valueOf(String s)
        {
            return (SCBrowseItemText)Enum.valueOf(com/sonos/sclib/SCIBrowseItem$SCBrowseItemText, s);
        }

        public static SCBrowseItemText[] values()
        {
            return (SCBrowseItemText[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SCBrowseItemText $VALUES[];
        public static final SCBrowseItemText BIT_ADORNED_TITLE;
        public static final SCBrowseItemText BIT_LINE_1;
        public static final SCBrowseItemText BIT_LINE_2;
        public static final SCBrowseItemText BIT_LINE_3;
        public static final SCBrowseItemText BIT_SUBTITLE;
        public static final SCBrowseItemText BIT_SUMMARY;
        public static final SCBrowseItemText BIT_TITLE;
        private final int swigValue;

        static 
        {
            BIT_TITLE = new SCBrowseItemText("BIT_TITLE", 0);
            BIT_ADORNED_TITLE = new SCBrowseItemText("BIT_ADORNED_TITLE", 1);
            BIT_SUBTITLE = new SCBrowseItemText("BIT_SUBTITLE", 2);
            BIT_SUMMARY = new SCBrowseItemText("BIT_SUMMARY", 3);
            BIT_LINE_1 = new SCBrowseItemText("BIT_LINE_1", 4);
            BIT_LINE_2 = new SCBrowseItemText("BIT_LINE_2", 5);
            BIT_LINE_3 = new SCBrowseItemText("BIT_LINE_3", 6);
            SCBrowseItemText ascbrowseitemtext[] = new SCBrowseItemText[7];
            ascbrowseitemtext[0] = BIT_TITLE;
            ascbrowseitemtext[1] = BIT_ADORNED_TITLE;
            ascbrowseitemtext[2] = BIT_SUBTITLE;
            ascbrowseitemtext[3] = BIT_SUMMARY;
            ascbrowseitemtext[4] = BIT_LINE_1;
            ascbrowseitemtext[5] = BIT_LINE_2;
            ascbrowseitemtext[6] = BIT_LINE_3;
            $VALUES = ascbrowseitemtext;
        }

        private SCBrowseItemText(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SCBrowseItemText(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SCBrowseItemText(String s, int i, SCBrowseItemText scbrowseitemtext)
        {
            Enum(s, i);
            swigValue = scbrowseitemtext.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIBrowseItem(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIBrowseItemUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIBrowseItem(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIBrowseItem(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIBrowseItem scibrowseitem)
    {
        long l;
        if(scibrowseitem == null)
            l = 0L;
        else
            l = scibrowseitem.swigCPtr;
        return l;
    }

    public boolean canActOn()
    {
        return sclibJNI.SCIBrowseItem_canActOn(swigCPtr, this);
    }

    public boolean canPush()
    {
        return sclibJNI.SCIBrowseItem_canPush(swigCPtr, this);
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

    public SCIEnumerator getActions()
    {
        long l = sclibJNI.SCIBrowseItem_getActions(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCAlbumArtType getAlbumArtType()
    {
        return SCAlbumArtType.swigToEnum(sclibJNI.SCIBrowseItem_getAlbumArtType(swigCPtr, this));
    }

    public String getAlbumArtURL()
    {
        return sclibJNI.SCIBrowseItem_getAlbumArtURL__SWIG_0(swigCPtr, this);
    }

    public String getAlbumArtURL(long l)
    {
        return sclibJNI.SCIBrowseItem_getAlbumArtURL__SWIG_1(swigCPtr, this, l);
    }

    public SCIPropertyBag getAttributes()
    {
        long l = sclibJNI.SCIBrowseItem_getAttributes(swigCPtr, this);
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        return scipropertybag;
    }

    public String getBrowseItemText(SCBrowseItemText scbrowseitemtext)
    {
        return sclibJNI.SCIBrowseItem_getBrowseItemText(swigCPtr, this, scbrowseitemtext.swigValue());
    }

    public SCIEnumerator getFilteredActions(SCIActionFilter sciactionfilter)
    {
        long l = sclibJNI.SCIBrowseItem_getFilteredActions(swigCPtr, this, SCIActionFilter.getCPtr(sciactionfilter), sciactionfilter);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIBrowseDataSource getMoreMenuDataSource()
    {
        long l = sclibJNI.SCIBrowseItem_getMoreMenuDataSource(swigCPtr, this);
        SCIBrowseDataSource scibrowsedatasource;
        if(l == 0L)
            scibrowsedatasource = null;
        else
            scibrowsedatasource = new SCIBrowseDataSource(l, true);
        return scibrowsedatasource;
    }

    public String getOrdinal()
    {
        return sclibJNI.SCIBrowseItem_getOrdinal(swigCPtr, this);
    }

    public String getPrimaryAdornedTitle()
    {
        return sclibJNI.SCIBrowseItem_getPrimaryAdornedTitle(swigCPtr, this);
    }

    public String getPrimaryTitle()
    {
        return sclibJNI.SCIBrowseItem_getPrimaryTitle(swigCPtr, this);
    }

    public String getSCUri()
    {
        return sclibJNI.SCIBrowseItem_getSCUri(swigCPtr, this);
    }

    public String getSecondaryTitle()
    {
        return sclibJNI.SCIBrowseItem_getSecondaryTitle(swigCPtr, this);
    }

    public boolean hasOrdinal()
    {
        return sclibJNI.SCIBrowseItem_hasOrdinal(swigCPtr, this);
    }

    public boolean isBrowseItemTextAvailable(SCBrowseItemText scbrowseitemtext)
    {
        return sclibJNI.SCIBrowseItem_isBrowseItemTextAvailable(swigCPtr, this, scbrowseitemtext.swigValue());
    }

    public boolean isDataAvailable()
    {
        return sclibJNI.SCIBrowseItem_isDataAvailable(swigCPtr, this);
    }

    public boolean isParentOfSearch()
    {
        return sclibJNI.SCIBrowseItem_isParentOfSearch(swigCPtr, this);
    }

    public boolean isSecondaryTitleValid()
    {
        return sclibJNI.SCIBrowseItem_isSecondaryTitleValid(swigCPtr, this);
    }

    public void subscribe(SCIEventSink scieventsink, boolean flag)
    {
        sclibJNI.SCIBrowseItem_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink, flag);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIBrowseItem_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIBrowseItem");
    private long swigCPtr;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIEnumerator, SCIActionFilter, 
//            SCIBrowseItem, SCIActionContext, SCISelectionManager, SCIStringArray, 
//            SCIEventSink

public class SCIBrowseDataSource extends SCIObj
{
    public static final class PresentationArtworkType extends Enum
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


        public static PresentationArtworkType swigToEnum(int i)
        {
            PresentationArtworkType apresentationartworktype[] = (PresentationArtworkType[])com/sonos/sclib/SCIBrowseDataSource$PresentationArtworkType.getEnumConstants();
            if(i >= apresentationartworktype.length || i < 0 || apresentationartworktype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            PresentationArtworkType presentationartworktype1 = apresentationartworktype[i];
_L4:
            return presentationartworktype1;
_L2:
            int j = apresentationartworktype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                PresentationArtworkType presentationartworktype = apresentationartworktype[k];
                if(presentationartworktype.swigValue == i)
                {
                    presentationartworktype1 = presentationartworktype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIBrowseDataSource$PresentationArtworkType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static PresentationArtworkType valueOf(String s)
        {
            return (PresentationArtworkType)Enum.valueOf(com/sonos/sclib/SCIBrowseDataSource$PresentationArtworkType, s);
        }

        public static PresentationArtworkType[] values()
        {
            return (PresentationArtworkType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final PresentationArtworkType $VALUES[];
        public static final PresentationArtworkType ARTWORK_CONTENT_LOGO;
        public static final PresentationArtworkType ARTWORK_DEFAULT;
        public static final PresentationArtworkType ARTWORK_EMPTY;
        public static final PresentationArtworkType ARTWORK_ERROR;
        private final int swigValue;

        static 
        {
            ARTWORK_DEFAULT = new PresentationArtworkType("ARTWORK_DEFAULT", 0);
            ARTWORK_EMPTY = new PresentationArtworkType("ARTWORK_EMPTY", 1);
            ARTWORK_ERROR = new PresentationArtworkType("ARTWORK_ERROR", 2);
            ARTWORK_CONTENT_LOGO = new PresentationArtworkType("ARTWORK_CONTENT_LOGO", 3);
            PresentationArtworkType apresentationartworktype[] = new PresentationArtworkType[4];
            apresentationartworktype[0] = ARTWORK_DEFAULT;
            apresentationartworktype[1] = ARTWORK_EMPTY;
            apresentationartworktype[2] = ARTWORK_ERROR;
            apresentationartworktype[3] = ARTWORK_CONTENT_LOGO;
            $VALUES = apresentationartworktype;
        }

        private PresentationArtworkType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private PresentationArtworkType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private PresentationArtworkType(String s, int i, PresentationArtworkType presentationartworktype)
        {
            Enum(s, i);
            swigValue = presentationartworktype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class PresentationTextType extends Enum
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


        public static PresentationTextType swigToEnum(int i)
        {
            PresentationTextType apresentationtexttype[] = (PresentationTextType[])com/sonos/sclib/SCIBrowseDataSource$PresentationTextType.getEnumConstants();
            if(i >= apresentationtexttype.length || i < 0 || apresentationtexttype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            PresentationTextType presentationtexttype1 = apresentationtexttype[i];
_L4:
            return presentationtexttype1;
_L2:
            int j = apresentationtexttype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                PresentationTextType presentationtexttype = apresentationtexttype[k];
                if(presentationtexttype.swigValue == i)
                {
                    presentationtexttype1 = presentationtexttype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIBrowseDataSource$PresentationTextType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static PresentationTextType valueOf(String s)
        {
            return (PresentationTextType)Enum.valueOf(com/sonos/sclib/SCIBrowseDataSource$PresentationTextType, s);
        }

        public static PresentationTextType[] values()
        {
            return (PresentationTextType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final PresentationTextType $VALUES[];
        public static final PresentationTextType BUTTON_EDIT;
        public static final PresentationTextType MENU_EDIT;
        public static final PresentationTextType MESSAGE_EMPTY;
        public static final PresentationTextType MESSAGE_LAST_ERROR;
        public static final PresentationTextType SUBTITLE_DEFAULT;
        public static final PresentationTextType TEXT_TYPE_BUTTON_TITLE;
        public static final PresentationTextType TEXT_TYPE_EDIT_MENU_TITLE;
        public static final PresentationTextType TEXT_TYPE_EMPTY_MESSAGE;
        public static final PresentationTextType TEXT_TYPE_LAST_ERROR_MESSAGE;
        public static final PresentationTextType TEXT_TYPE_SUBTITLE;
        public static final PresentationTextType TEXT_TYPE_SUMMARY;
        public static final PresentationTextType TEXT_TYPE_TITLE;
        public static final PresentationTextType TEXT_TYPE_TITLE_EDITING;
        public static final PresentationTextType TITLE_DEFAULT;
        public static final PresentationTextType TITLE_EDIT;
        private final int swigValue;

        static 
        {
            TEXT_TYPE_TITLE = new PresentationTextType("TEXT_TYPE_TITLE", 0);
            TEXT_TYPE_TITLE_EDITING = new PresentationTextType("TEXT_TYPE_TITLE_EDITING", 1);
            TEXT_TYPE_SUBTITLE = new PresentationTextType("TEXT_TYPE_SUBTITLE", 2);
            TEXT_TYPE_BUTTON_TITLE = new PresentationTextType("TEXT_TYPE_BUTTON_TITLE", 3);
            TEXT_TYPE_EDIT_MENU_TITLE = new PresentationTextType("TEXT_TYPE_EDIT_MENU_TITLE", 4);
            TEXT_TYPE_EMPTY_MESSAGE = new PresentationTextType("TEXT_TYPE_EMPTY_MESSAGE", 5);
            TEXT_TYPE_LAST_ERROR_MESSAGE = new PresentationTextType("TEXT_TYPE_LAST_ERROR_MESSAGE", 6);
            TEXT_TYPE_SUMMARY = new PresentationTextType("TEXT_TYPE_SUMMARY", 7);
            TITLE_DEFAULT = new PresentationTextType("TITLE_DEFAULT", 8, sclibJNI.SCIBrowseDataSource_TITLE_DEFAULT_get());
            TITLE_EDIT = new PresentationTextType("TITLE_EDIT", 9, sclibJNI.SCIBrowseDataSource_TITLE_EDIT_get());
            BUTTON_EDIT = new PresentationTextType("BUTTON_EDIT", 10, sclibJNI.SCIBrowseDataSource_BUTTON_EDIT_get());
            MENU_EDIT = new PresentationTextType("MENU_EDIT", 11, sclibJNI.SCIBrowseDataSource_MENU_EDIT_get());
            MESSAGE_EMPTY = new PresentationTextType("MESSAGE_EMPTY", 12, sclibJNI.SCIBrowseDataSource_MESSAGE_EMPTY_get());
            MESSAGE_LAST_ERROR = new PresentationTextType("MESSAGE_LAST_ERROR", 13, sclibJNI.SCIBrowseDataSource_MESSAGE_LAST_ERROR_get());
            SUBTITLE_DEFAULT = new PresentationTextType("SUBTITLE_DEFAULT", 14, sclibJNI.SCIBrowseDataSource_SUBTITLE_DEFAULT_get());
            PresentationTextType apresentationtexttype[] = new PresentationTextType[15];
            apresentationtexttype[0] = TEXT_TYPE_TITLE;
            apresentationtexttype[1] = TEXT_TYPE_TITLE_EDITING;
            apresentationtexttype[2] = TEXT_TYPE_SUBTITLE;
            apresentationtexttype[3] = TEXT_TYPE_BUTTON_TITLE;
            apresentationtexttype[4] = TEXT_TYPE_EDIT_MENU_TITLE;
            apresentationtexttype[5] = TEXT_TYPE_EMPTY_MESSAGE;
            apresentationtexttype[6] = TEXT_TYPE_LAST_ERROR_MESSAGE;
            apresentationtexttype[7] = TEXT_TYPE_SUMMARY;
            apresentationtexttype[8] = TITLE_DEFAULT;
            apresentationtexttype[9] = TITLE_EDIT;
            apresentationtexttype[10] = BUTTON_EDIT;
            apresentationtexttype[11] = MENU_EDIT;
            apresentationtexttype[12] = MESSAGE_EMPTY;
            apresentationtexttype[13] = MESSAGE_LAST_ERROR;
            apresentationtexttype[14] = SUBTITLE_DEFAULT;
            $VALUES = apresentationtexttype;
        }

        private PresentationTextType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private PresentationTextType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private PresentationTextType(String s, int i, PresentationTextType presentationtexttype)
        {
            Enum(s, i);
            swigValue = presentationtexttype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIBrowseDataSource(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIBrowseDataSourceUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIBrowseDataSource(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIBrowseDataSource(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIBrowseDataSource scibrowsedatasource)
    {
        long l;
        if(scibrowsedatasource == null)
            l = 0L;
        else
            l = scibrowsedatasource.swigCPtr;
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

    public SCIEnumerator getActions()
    {
        long l = sclibJNI.SCIBrowseDataSource_getActions(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIEnumerator getActionsOnSelectedItems()
    {
        long l = sclibJNI.SCIBrowseDataSource_getActionsOnSelectedItems(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIEnumerator getFilteredActions(SCIActionFilter sciactionfilter)
    {
        long l = sclibJNI.SCIBrowseDataSource_getFilteredActions(swigCPtr, this, SCIActionFilter.getCPtr(sciactionfilter), sciactionfilter);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIBrowseItem getItemAtIndex(long l)
    {
        long l1 = sclibJNI.SCIBrowseDataSource_getItemAtIndex(swigCPtr, this, l);
        SCIBrowseItem scibrowseitem;
        if(l1 == 0L)
            scibrowseitem = null;
        else
            scibrowseitem = new SCIBrowseItem(l1, true);
        return scibrowseitem;
    }

    public int getLastBrowseResult()
    {
        return sclibJNI.SCIBrowseDataSource_getLastBrowseResult(swigCPtr, this);
    }

    public SCIActionContext getLastErrorAction()
    {
        long l = sclibJNI.SCIBrowseDataSource_getLastErrorAction(swigCPtr, this);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    public SCIBrowseDataSource getMoreMenuDataSource()
    {
        long l = sclibJNI.SCIBrowseDataSource_getMoreMenuDataSource(swigCPtr, this);
        SCIBrowseDataSource scibrowsedatasource;
        if(l == 0L)
            scibrowsedatasource = null;
        else
            scibrowsedatasource = new SCIBrowseDataSource(l, true);
        return scibrowsedatasource;
    }

    public long getNumItems()
    {
        return sclibJNI.SCIBrowseDataSource_getNumItems(swigCPtr, this);
    }

    public long getPostModificationIndex()
    {
        return sclibJNI.SCIBrowseDataSource_getPostModificationIndex(swigCPtr, this);
    }

    public SCIBrowseItem.SCAlbumArtType getPresentationArtworkArtType(PresentationArtworkType presentationartworktype)
    {
        return SCIBrowseItem.SCAlbumArtType.swigToEnum(sclibJNI.SCIBrowseDataSource_getPresentationArtworkArtType(swigCPtr, this, presentationartworktype.swigValue()));
    }

    public String getPresentationArtworkURL(PresentationArtworkType presentationartworktype, long l)
    {
        return sclibJNI.SCIBrowseDataSource_getPresentationArtworkURL(swigCPtr, this, presentationartworktype.swigValue(), l);
    }

    public String getPresentationText(PresentationTextType presentationtexttype)
    {
        return sclibJNI.SCIBrowseDataSource_getPresentationText(swigCPtr, this, presentationtexttype.swigValue());
    }

    public String getSCUri()
    {
        return sclibJNI.SCIBrowseDataSource_getSCUri(swigCPtr, this);
    }

    public SCISelectionManager getSelectionManager()
    {
        long l = sclibJNI.SCIBrowseDataSource_getSelectionManager(swigCPtr, this);
        SCISelectionManager sciselectionmanager;
        if(l == 0L)
            sciselectionmanager = null;
        else
            sciselectionmanager = new SCISelectionManager(l, true);
        return sciselectionmanager;
    }

    public SCIStringArray getSupportedActionCategories()
    {
        long l = sclibJNI.SCIBrowseDataSource_getSupportedActionCategories(swigCPtr, this);
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return scistringarray;
    }

    public boolean isGone()
    {
        return sclibJNI.SCIBrowseDataSource_isGone(swigCPtr, this);
    }

    public boolean isLastErrorPermanent()
    {
        return sclibJNI.SCIBrowseDataSource_isLastErrorPermanent(swigCPtr, this);
    }

    public boolean isLastItemBrowseResultFailure()
    {
        return sclibJNI.SCIBrowseDataSource_isLastItemBrowseResultFailure(swigCPtr, this);
    }

    public boolean isSearchResult()
    {
        return sclibJNI.SCIBrowseDataSource_isSearchResult(swigCPtr, this);
    }

    public boolean isValid()
    {
        return sclibJNI.SCIBrowseDataSource_isValid(swigCPtr, this);
    }

    public boolean refreshBrowse()
    {
        return sclibJNI.SCIBrowseDataSource_refreshBrowse(swigCPtr, this);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIBrowseDataSource_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIBrowseDataSource_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIBrowseDataSource");
    private long swigCPtr;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIBrowseDataSource, SCIBrowseItem

public class SCIBrowseListPresentationMap extends SCIObj
{
    public static final class SCListPresentationType extends Enum
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


        public static SCListPresentationType swigToEnum(int i)
        {
            SCListPresentationType asclistpresentationtype[] = (SCListPresentationType[])com/sonos/sclib/SCIBrowseListPresentationMap$SCListPresentationType.getEnumConstants();
            if(i >= asclistpresentationtype.length || i < 0 || asclistpresentationtype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SCListPresentationType sclistpresentationtype1 = asclistpresentationtype[i];
_L4:
            return sclistpresentationtype1;
_L2:
            int j = asclistpresentationtype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SCListPresentationType sclistpresentationtype = asclistpresentationtype[k];
                if(sclistpresentationtype.swigValue == i)
                {
                    sclistpresentationtype1 = sclistpresentationtype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIBrowseListPresentationMap$SCListPresentationType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SCListPresentationType valueOf(String s)
        {
            return (SCListPresentationType)Enum.valueOf(com/sonos/sclib/SCIBrowseListPresentationMap$SCListPresentationType, s);
        }

        public static SCListPresentationType[] values()
        {
            return (SCListPresentationType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SCListPresentationType $VALUES[];
        public static final SCListPresentationType PRESENTATION_ALBUM;
        public static final SCListPresentationType PRESENTATION_EDITORIAL;
        public static final SCListPresentationType PRESENTATION_GRID;
        public static final SCListPresentationType PRESENTATION_HERO;
        public static final SCListPresentationType PRESENTATION_LIST;
        private final int swigValue;

        static 
        {
            PRESENTATION_LIST = new SCListPresentationType("PRESENTATION_LIST", 0);
            PRESENTATION_GRID = new SCListPresentationType("PRESENTATION_GRID", 1);
            PRESENTATION_ALBUM = new SCListPresentationType("PRESENTATION_ALBUM", 2);
            PRESENTATION_HERO = new SCListPresentationType("PRESENTATION_HERO", 3);
            PRESENTATION_EDITORIAL = new SCListPresentationType("PRESENTATION_EDITORIAL", 4);
            SCListPresentationType asclistpresentationtype[] = new SCListPresentationType[5];
            asclistpresentationtype[0] = PRESENTATION_LIST;
            asclistpresentationtype[1] = PRESENTATION_GRID;
            asclistpresentationtype[2] = PRESENTATION_ALBUM;
            asclistpresentationtype[3] = PRESENTATION_HERO;
            asclistpresentationtype[4] = PRESENTATION_EDITORIAL;
            $VALUES = asclistpresentationtype;
        }

        private SCListPresentationType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SCListPresentationType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SCListPresentationType(String s, int i, SCListPresentationType sclistpresentationtype)
        {
            Enum(s, i);
            swigValue = sclistpresentationtype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIBrowseListPresentationMap(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIBrowseListPresentationMapUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIBrowseListPresentationMap(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIBrowseListPresentationMap(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIBrowseListPresentationMap scibrowselistpresentationmap)
    {
        long l;
        if(scibrowselistpresentationmap == null)
            l = 0L;
        else
            l = scibrowselistpresentationmap.swigCPtr;
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

    public SCListPresentationType getPresentationForDataSource(SCIBrowseDataSource scibrowsedatasource)
    {
        return SCListPresentationType.swigToEnum(sclibJNI.SCIBrowseListPresentationMap_getPresentationForDataSource(swigCPtr, this, SCIBrowseDataSource.getCPtr(scibrowsedatasource), scibrowsedatasource));
    }

    public SCListPresentationType getPresentationForPushedItem(SCIBrowseItem scibrowseitem)
    {
        return SCListPresentationType.swigToEnum(sclibJNI.SCIBrowseListPresentationMap_getPresentationForPushedItem(swigCPtr, this, SCIBrowseItem.getCPtr(scibrowseitem), scibrowseitem));
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIBrowseListPresentationMap");
    private long swigCPtr;

}

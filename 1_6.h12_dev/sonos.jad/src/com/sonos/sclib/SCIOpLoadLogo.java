// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIOp, sclibJNI

public class SCIOpLoadLogo extends SCIOp
{
    public static final class LoadLogoResult extends Enum
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


        public static LoadLogoResult swigToEnum(int i)
        {
            LoadLogoResult aloadlogoresult[] = (LoadLogoResult[])com/sonos/sclib/SCIOpLoadLogo$LoadLogoResult.getEnumConstants();
            if(i >= aloadlogoresult.length || i < 0 || aloadlogoresult[i].swigValue != i) goto _L2; else goto _L1
_L1:
            LoadLogoResult loadlogoresult1 = aloadlogoresult[i];
_L4:
            return loadlogoresult1;
_L2:
            int j = aloadlogoresult.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                LoadLogoResult loadlogoresult = aloadlogoresult[k];
                if(loadlogoresult.swigValue == i)
                {
                    loadlogoresult1 = loadlogoresult;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIOpLoadLogo$LoadLogoResult).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static LoadLogoResult valueOf(String s)
        {
            return (LoadLogoResult)Enum.valueOf(com/sonos/sclib/SCIOpLoadLogo$LoadLogoResult, s);
        }

        public static LoadLogoResult[] values()
        {
            return (LoadLogoResult[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final LoadLogoResult $VALUES[];
        public static final LoadLogoResult LLOP_RESULT_CACHED;
        public static final LoadLogoResult LLOP_RESULT_DOWNLOADED;
        public static final LoadLogoResult LLOP_RESULT_DOWNLOADING;
        public static final LoadLogoResult LLOP_RESULT_DOWNLOAD_FAILURE;
        public static final LoadLogoResult LLOP_RESULT_INVALID_RESOURCE_ID;
        public static final LoadLogoResult LLOP_RESULT_NOTSTARTED;
        private final int swigValue;

        static 
        {
            LLOP_RESULT_DOWNLOADED = new LoadLogoResult("LLOP_RESULT_DOWNLOADED", 0, sclibJNI.SCIOpLoadLogo_LLOP_RESULT_DOWNLOADED_get());
            LLOP_RESULT_NOTSTARTED = new LoadLogoResult("LLOP_RESULT_NOTSTARTED", 1);
            LLOP_RESULT_CACHED = new LoadLogoResult("LLOP_RESULT_CACHED", 2);
            LLOP_RESULT_DOWNLOADING = new LoadLogoResult("LLOP_RESULT_DOWNLOADING", 3);
            LLOP_RESULT_DOWNLOAD_FAILURE = new LoadLogoResult("LLOP_RESULT_DOWNLOAD_FAILURE", 4);
            LLOP_RESULT_INVALID_RESOURCE_ID = new LoadLogoResult("LLOP_RESULT_INVALID_RESOURCE_ID", 5);
            LoadLogoResult aloadlogoresult[] = new LoadLogoResult[6];
            aloadlogoresult[0] = LLOP_RESULT_DOWNLOADED;
            aloadlogoresult[1] = LLOP_RESULT_NOTSTARTED;
            aloadlogoresult[2] = LLOP_RESULT_CACHED;
            aloadlogoresult[3] = LLOP_RESULT_DOWNLOADING;
            aloadlogoresult[4] = LLOP_RESULT_DOWNLOAD_FAILURE;
            aloadlogoresult[5] = LLOP_RESULT_INVALID_RESOURCE_ID;
            $VALUES = aloadlogoresult;
        }

        private LoadLogoResult(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private LoadLogoResult(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private LoadLogoResult(String s, int i, LoadLogoResult loadlogoresult)
        {
            Enum(s, i);
            swigValue = loadlogoresult.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIOpLoadLogo(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIOp(sclibJNI.SWIGSCIOpLoadLogoUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIOpLoadLogo(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIOpLoadLogo(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIOpLoadLogo scioploadlogo)
    {
        long l;
        if(scioploadlogo == null)
            l = 0L;
        else
            l = scioploadlogo.swigCPtr;
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

    public String getLocalPath()
    {
        return sclibJNI.SCIOpLoadLogo_getLocalPath(swigCPtr, this);
    }

    public int getOpResult()
    {
        return sclibJNI.SCIOpLoadLogo_getOpResult(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIOpLoadLogo");
    private long swigCPtr;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCIWebRequestSpec extends SCIObj
{
    public static final class SCCachePolicy extends Enum
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


        public static SCCachePolicy swigToEnum(int i)
        {
            SCCachePolicy asccachepolicy[] = (SCCachePolicy[])com/sonos/sclib/SCIWebRequestSpec$SCCachePolicy.getEnumConstants();
            if(i >= asccachepolicy.length || i < 0 || asccachepolicy[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SCCachePolicy sccachepolicy1 = asccachepolicy[i];
_L4:
            return sccachepolicy1;
_L2:
            int j = asccachepolicy.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SCCachePolicy sccachepolicy = asccachepolicy[k];
                if(sccachepolicy.swigValue == i)
                {
                    sccachepolicy1 = sccachepolicy;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIWebRequestSpec$SCCachePolicy).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SCCachePolicy valueOf(String s)
        {
            return (SCCachePolicy)Enum.valueOf(com/sonos/sclib/SCIWebRequestSpec$SCCachePolicy, s);
        }

        public static SCCachePolicy[] values()
        {
            return (SCCachePolicy[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SCCachePolicy $VALUES[];
        public static final SCCachePolicy CACHE_DEFAULT;
        public static final SCCachePolicy CLEAR_CACHE_FORCE_LOAD;
        public static final SCCachePolicy FORCE_LOAD;
        public static final SCCachePolicy LOCAL_CACHE_ELSE_LOAD;
        public static final SCCachePolicy UNKNOWN;
        private final int swigValue;

        static 
        {
            UNKNOWN = new SCCachePolicy("UNKNOWN", 0, sclibJNI.SCIWebRequestSpec_UNKNOWN_get());
            LOCAL_CACHE_ELSE_LOAD = new SCCachePolicy("LOCAL_CACHE_ELSE_LOAD", 1);
            FORCE_LOAD = new SCCachePolicy("FORCE_LOAD", 2);
            CLEAR_CACHE_FORCE_LOAD = new SCCachePolicy("CLEAR_CACHE_FORCE_LOAD", 3);
            CACHE_DEFAULT = new SCCachePolicy("CACHE_DEFAULT", 4);
            SCCachePolicy asccachepolicy[] = new SCCachePolicy[5];
            asccachepolicy[0] = UNKNOWN;
            asccachepolicy[1] = LOCAL_CACHE_ELSE_LOAD;
            asccachepolicy[2] = FORCE_LOAD;
            asccachepolicy[3] = CLEAR_CACHE_FORCE_LOAD;
            asccachepolicy[4] = CACHE_DEFAULT;
            $VALUES = asccachepolicy;
        }

        private SCCachePolicy(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SCCachePolicy(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SCCachePolicy(String s, int i, SCCachePolicy sccachepolicy)
        {
            Enum(s, i);
            swigValue = sccachepolicy.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIWebRequestSpec(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIWebRequestSpecUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIWebRequestSpec(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIWebRequestSpec(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIWebRequestSpec sciwebrequestspec)
    {
        long l;
        if(sciwebrequestspec == null)
            l = 0L;
        else
            l = sciwebrequestspec.swigCPtr;
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

    public SCCachePolicy getCachePolicy()
    {
        return SCCachePolicy.swigToEnum(sclibJNI.SCIWebRequestSpec_getCachePolicy(swigCPtr, this));
    }

    public int getConnectionTimeOut()
    {
        return sclibJNI.SCIWebRequestSpec_getConnectionTimeOut(swigCPtr, this);
    }

    public String getUrl()
    {
        return sclibJNI.SCIWebRequestSpec_getUrl(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIWebRequestSpec");
    private long swigCPtr;

}

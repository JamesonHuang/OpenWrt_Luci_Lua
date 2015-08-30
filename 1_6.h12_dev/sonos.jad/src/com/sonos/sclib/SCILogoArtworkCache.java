// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIArtworkData

public class SCILogoArtworkCache extends SCIObj
{
    public static final class SCLogoArtSize extends Enum
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


        public static SCLogoArtSize swigToEnum(int i)
        {
            SCLogoArtSize asclogoartsize[] = (SCLogoArtSize[])com/sonos/sclib/SCILogoArtworkCache$SCLogoArtSize.getEnumConstants();
            if(i >= asclogoartsize.length || i < 0 || asclogoartsize[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SCLogoArtSize sclogoartsize1 = asclogoartsize[i];
_L4:
            return sclogoartsize1;
_L2:
            int j = asclogoartsize.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SCLogoArtSize sclogoartsize = asclogoartsize[k];
                if(sclogoartsize.swigValue == i)
                {
                    sclogoartsize1 = sclogoartsize;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCILogoArtworkCache$SCLogoArtSize).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SCLogoArtSize valueOf(String s)
        {
            return (SCLogoArtSize)Enum.valueOf(com/sonos/sclib/SCILogoArtworkCache$SCLogoArtSize, s);
        }

        public static SCLogoArtSize[] values()
        {
            return (SCLogoArtSize[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SCLogoArtSize $VALUES[];
        public static final SCLogoArtSize LOGO_SIZE_DEFAULT;
        public static final SCLogoArtSize LOGO_SIZE_LARGE;
        public static final SCLogoArtSize LOGO_SIZE_MEDIUM;
        public static final SCLogoArtSize LOGO_SIZE_SMALL;
        public static final SCLogoArtSize LOGO_SIZE_XLARGE;
        public static final SCLogoArtSize LOGO_SIZE_XSMALL;
        private final int swigValue;

        static 
        {
            LOGO_SIZE_XSMALL = new SCLogoArtSize("LOGO_SIZE_XSMALL", 0);
            LOGO_SIZE_SMALL = new SCLogoArtSize("LOGO_SIZE_SMALL", 1);
            LOGO_SIZE_MEDIUM = new SCLogoArtSize("LOGO_SIZE_MEDIUM", 2);
            LOGO_SIZE_LARGE = new SCLogoArtSize("LOGO_SIZE_LARGE", 3);
            LOGO_SIZE_XLARGE = new SCLogoArtSize("LOGO_SIZE_XLARGE", 4);
            LOGO_SIZE_DEFAULT = new SCLogoArtSize("LOGO_SIZE_DEFAULT", 5);
            SCLogoArtSize asclogoartsize[] = new SCLogoArtSize[6];
            asclogoartsize[0] = LOGO_SIZE_XSMALL;
            asclogoartsize[1] = LOGO_SIZE_SMALL;
            asclogoartsize[2] = LOGO_SIZE_MEDIUM;
            asclogoartsize[3] = LOGO_SIZE_LARGE;
            asclogoartsize[4] = LOGO_SIZE_XLARGE;
            asclogoartsize[5] = LOGO_SIZE_DEFAULT;
            $VALUES = asclogoartsize;
        }

        private SCLogoArtSize(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SCLogoArtSize(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SCLogoArtSize(String s, int i, SCLogoArtSize sclogoartsize)
        {
            Enum(s, i);
            swigValue = sclogoartsize.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCILogoArtworkCache(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCILogoArtworkCacheUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCILogoArtworkCache(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCILogoArtworkCache(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCILogoArtworkCache scilogoartworkcache)
    {
        long l;
        if(scilogoartworkcache == null)
            l = 0L;
        else
            l = scilogoartworkcache.swigCPtr;
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

    public String getCompleteAAUri(String s, SCLogoArtSize sclogoartsize)
    {
        return sclibJNI.SCILogoArtworkCache_getCompleteAAUri(swigCPtr, this, s, sclogoartsize.swigValue());
    }

    public SCIArtworkData requestArtwork(String s)
    {
        long l = sclibJNI.SCILogoArtworkCache_requestArtwork__SWIG_0(swigCPtr, this, s);
        SCIArtworkData sciartworkdata;
        if(l == 0L)
            sciartworkdata = null;
        else
            sciartworkdata = new SCIArtworkData(l, true);
        return sciartworkdata;
    }

    public SCIArtworkData requestArtwork(String s, SCLogoArtSize sclogoartsize)
    {
        long l = sclibJNI.SCILogoArtworkCache_requestArtwork__SWIG_1(swigCPtr, this, s, sclogoartsize.swigValue());
        SCIArtworkData sciartworkdata;
        if(l == 0L)
            sciartworkdata = null;
        else
            sciartworkdata = new SCIArtworkData(l, true);
        return sciartworkdata;
    }

    public void setDefaultLogoSize(SCLogoArtSize sclogoartsize)
    {
        sclibJNI.SCILogoArtworkCache_setDefaultLogoSize(swigCPtr, this, sclogoartsize.swigValue());
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCILogoArtworkCache");
    private long swigCPtr;

}

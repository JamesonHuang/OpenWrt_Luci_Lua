// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIArtworkCache, SCILogoArtworkCache

public class SCIArtworkCacheManager extends SCIObj
{
    public static final class LogoArtworkFormat extends Enum
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


        public static LogoArtworkFormat swigToEnum(int i)
        {
            LogoArtworkFormat alogoartworkformat[] = (LogoArtworkFormat[])com/sonos/sclib/SCIArtworkCacheManager$LogoArtworkFormat.getEnumConstants();
            if(i >= alogoartworkformat.length || i < 0 || alogoartworkformat[i].swigValue != i) goto _L2; else goto _L1
_L1:
            LogoArtworkFormat logoartworkformat1 = alogoartworkformat[i];
_L4:
            return logoartworkformat1;
_L2:
            int j = alogoartworkformat.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                LogoArtworkFormat logoartworkformat = alogoartworkformat[k];
                if(logoartworkformat.swigValue == i)
                {
                    logoartworkformat1 = logoartworkformat;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIArtworkCacheManager$LogoArtworkFormat).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static LogoArtworkFormat valueOf(String s)
        {
            return (LogoArtworkFormat)Enum.valueOf(com/sonos/sclib/SCIArtworkCacheManager$LogoArtworkFormat, s);
        }

        public static LogoArtworkFormat[] values()
        {
            return (LogoArtworkFormat[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final LogoArtworkFormat $VALUES[];
        public static final LogoArtworkFormat LOGO_FORMAT_CIRCULAR;
        public static final LogoArtworkFormat LOGO_FORMAT_SQUARE;
        private final int swigValue;

        static 
        {
            LOGO_FORMAT_CIRCULAR = new LogoArtworkFormat("LOGO_FORMAT_CIRCULAR", 0);
            LOGO_FORMAT_SQUARE = new LogoArtworkFormat("LOGO_FORMAT_SQUARE", 1);
            LogoArtworkFormat alogoartworkformat[] = new LogoArtworkFormat[2];
            alogoartworkformat[0] = LOGO_FORMAT_CIRCULAR;
            alogoartworkformat[1] = LOGO_FORMAT_SQUARE;
            $VALUES = alogoartworkformat;
        }

        private LogoArtworkFormat(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private LogoArtworkFormat(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private LogoArtworkFormat(String s, int i, LogoArtworkFormat logoartworkformat)
        {
            Enum(s, i);
            swigValue = logoartworkformat.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIArtworkCacheManager(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIArtworkCacheManagerUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIArtworkCacheManager(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIArtworkCacheManager(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIArtworkCacheManager sciartworkcachemanager)
    {
        long l;
        if(sciartworkcachemanager == null)
            l = 0L;
        else
            l = sciartworkcachemanager.swigCPtr;
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

    public void emptyAllCaches()
    {
        sclibJNI.SCIArtworkCacheManager_emptyAllCaches(swigCPtr, this);
    }

    public SCIArtworkCache getArtworkCache(long l)
    {
        long l1 = sclibJNI.SCIArtworkCacheManager_getArtworkCache(swigCPtr, this, l);
        SCIArtworkCache sciartworkcache;
        if(l1 == 0L)
            sciartworkcache = null;
        else
            sciartworkcache = new SCIArtworkCache(l1, true);
        return sciartworkcache;
    }

    public SCILogoArtworkCache getLogoArtworkCache()
    {
        long l = sclibJNI.SCIArtworkCacheManager_getLogoArtworkCache(swigCPtr, this);
        SCILogoArtworkCache scilogoartworkcache;
        if(l == 0L)
            scilogoartworkcache = null;
        else
            scilogoartworkcache = new SCILogoArtworkCache(l, true);
        return scilogoartworkcache;
    }

    public SCILogoArtworkCache getLogoArtworkCacheWithFormat(LogoArtworkFormat logoartworkformat)
    {
        long l = sclibJNI.SCIArtworkCacheManager_getLogoArtworkCacheWithFormat(swigCPtr, this, logoartworkformat.swigValue());
        SCILogoArtworkCache scilogoartworkcache;
        if(l == 0L)
            scilogoartworkcache = null;
        else
            scilogoartworkcache = new SCILogoArtworkCache(l, true);
        return scilogoartworkcache;
    }

    public SCIArtworkCache setUpNewArtworkCache(long l, long l1, long l2)
    {
        long l3 = sclibJNI.SCIArtworkCacheManager_setUpNewArtworkCache(swigCPtr, this, l, l1, l2);
        SCIArtworkCache sciartworkcache;
        if(l3 == 0L)
            sciartworkcache = null;
        else
            sciartworkcache = new SCIArtworkCache(l3, true);
        return sciartworkcache;
    }

    public SCIArtworkCache setUpNewCompressedArtworkCache(long l, long l1, long l2)
    {
        long l3 = sclibJNI.SCIArtworkCacheManager_setUpNewCompressedArtworkCache(swigCPtr, this, l, l1, l2);
        SCIArtworkCache sciartworkcache;
        if(l3 == 0L)
            sciartworkcache = null;
        else
            sciartworkcache = new SCIArtworkCache(l3, true);
        return sciartworkcache;
    }

    public void tearDownArtworkCache(long l)
    {
        sclibJNI.SCIArtworkCacheManager_tearDownArtworkCache(swigCPtr, this, l);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIArtworkCacheManager");
    private long swigCPtr;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIEventSink

public class SCIArtworkData extends SCIObj
{
    public static final class ArtworkDataFormat extends Enum
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


        public static ArtworkDataFormat swigToEnum(int i)
        {
            ArtworkDataFormat aartworkdataformat[] = (ArtworkDataFormat[])com/sonos/sclib/SCIArtworkData$ArtworkDataFormat.getEnumConstants();
            if(i >= aartworkdataformat.length || i < 0 || aartworkdataformat[i].swigValue != i) goto _L2; else goto _L1
_L1:
            ArtworkDataFormat artworkdataformat1 = aartworkdataformat[i];
_L4:
            return artworkdataformat1;
_L2:
            int j = aartworkdataformat.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                ArtworkDataFormat artworkdataformat = aartworkdataformat[k];
                if(artworkdataformat.swigValue == i)
                {
                    artworkdataformat1 = artworkdataformat;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIArtworkData$ArtworkDataFormat).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static ArtworkDataFormat valueOf(String s)
        {
            return (ArtworkDataFormat)Enum.valueOf(com/sonos/sclib/SCIArtworkData$ArtworkDataFormat, s);
        }

        public static ArtworkDataFormat[] values()
        {
            return (ArtworkDataFormat[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final ArtworkDataFormat $VALUES[];
        public static final ArtworkDataFormat ART_DATA_RASTER;
        public static final ArtworkDataFormat ART_DATA_UNKNOWN;
        public static final ArtworkDataFormat ART_DATA_VECTOR;
        private final int swigValue;

        static 
        {
            ART_DATA_RASTER = new ArtworkDataFormat("ART_DATA_RASTER", 0);
            ART_DATA_VECTOR = new ArtworkDataFormat("ART_DATA_VECTOR", 1);
            ART_DATA_UNKNOWN = new ArtworkDataFormat("ART_DATA_UNKNOWN", 2);
            ArtworkDataFormat aartworkdataformat[] = new ArtworkDataFormat[3];
            aartworkdataformat[0] = ART_DATA_RASTER;
            aartworkdataformat[1] = ART_DATA_VECTOR;
            aartworkdataformat[2] = ART_DATA_UNKNOWN;
            $VALUES = aartworkdataformat;
        }

        private ArtworkDataFormat(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private ArtworkDataFormat(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private ArtworkDataFormat(String s, int i, ArtworkDataFormat artworkdataformat)
        {
            Enum(s, i);
            swigValue = artworkdataformat.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIArtworkData(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIArtworkDataUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIArtworkData(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIArtworkData(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIArtworkData sciartworkdata)
    {
        long l;
        if(sciartworkdata == null)
            l = 0L;
        else
            l = sciartworkdata.swigCPtr;
        return l;
    }

    public void clearFileData()
    {
        sclibJNI.SCIArtworkData_clearFileData(swigCPtr, this);
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

    public void getData(byte abyte0[], long l)
    {
        sclibJNI.SCIArtworkData_getData(swigCPtr, this, abyte0, l);
    }

    public ArtworkDataFormat getDataFormat()
    {
        return ArtworkDataFormat.swigToEnum(sclibJNI.SCIArtworkData_getDataFormat(swigCPtr, this));
    }

    public int getFailureCode()
    {
        return sclibJNI.SCIArtworkData_getFailureCode(swigCPtr, this);
    }

    public long getSizeInBytes()
    {
        return sclibJNI.SCIArtworkData_getSizeInBytes(swigCPtr, this);
    }

    public long getSizeInPixels()
    {
        return sclibJNI.SCIArtworkData_getSizeInPixels(swigCPtr, this);
    }

    public SCIBrowseItem.SCAlbumArtType getType()
    {
        return SCIBrowseItem.SCAlbumArtType.swigToEnum(sclibJNI.SCIArtworkData_getType(swigCPtr, this));
    }

    public String getURL()
    {
        return sclibJNI.SCIArtworkData_getURL(swigCPtr, this);
    }

    public long getXPixels()
    {
        return sclibJNI.SCIArtworkData_getXPixels(swigCPtr, this);
    }

    public long getYPixels()
    {
        return sclibJNI.SCIArtworkData_getYPixels(swigCPtr, this);
    }

    public boolean hasFailed()
    {
        return sclibJNI.SCIArtworkData_hasFailed(swigCPtr, this);
    }

    public boolean isReady()
    {
        return sclibJNI.SCIArtworkData_isReady(swigCPtr, this);
    }

    public boolean isStillWorking()
    {
        return sclibJNI.SCIArtworkData_isStillWorking(swigCPtr, this);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIArtworkData_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIArtworkData_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIArtworkData");
    private long swigCPtr;

}

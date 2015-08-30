// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI

public class SCINetworkManagementDelegate extends SCIObj
{
    public static final class NetworkType extends Enum
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


        public static NetworkType swigToEnum(int i)
        {
            NetworkType anetworktype[] = (NetworkType[])com/sonos/sclib/SCINetworkManagementDelegate$NetworkType.getEnumConstants();
            if(i >= anetworktype.length || i < 0 || anetworktype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            NetworkType networktype1 = anetworktype[i];
_L4:
            return networktype1;
_L2:
            int j = anetworktype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                NetworkType networktype = anetworktype[k];
                if(networktype.swigValue == i)
                {
                    networktype1 = networktype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCINetworkManagementDelegate$NetworkType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static NetworkType valueOf(String s)
        {
            return (NetworkType)Enum.valueOf(com/sonos/sclib/SCINetworkManagementDelegate$NetworkType, s);
        }

        public static NetworkType[] values()
        {
            return (NetworkType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final NetworkType $VALUES[];
        public static final NetworkType NetworkType_CELL;
        public static final NetworkType NetworkType_CONNECTED_UNKNOWN;
        public static final NetworkType NetworkType_NONE;
        public static final NetworkType NetworkType_UNKNOWN;
        public static final NetworkType NetworkType_WIFI;
        public static final NetworkType NetworkType_WIRED;
        private final int swigValue;

        static 
        {
            NetworkType_UNKNOWN = new NetworkType("NetworkType_UNKNOWN", 0, sclibJNI.SCINetworkManagementDelegate_NetworkType_UNKNOWN_get());
            NetworkType_NONE = new NetworkType("NetworkType_NONE", 1);
            NetworkType_CONNECTED_UNKNOWN = new NetworkType("NetworkType_CONNECTED_UNKNOWN", 2);
            NetworkType_CELL = new NetworkType("NetworkType_CELL", 3);
            NetworkType_WIFI = new NetworkType("NetworkType_WIFI", 4);
            NetworkType_WIRED = new NetworkType("NetworkType_WIRED", 5);
            NetworkType anetworktype[] = new NetworkType[6];
            anetworktype[0] = NetworkType_UNKNOWN;
            anetworktype[1] = NetworkType_NONE;
            anetworktype[2] = NetworkType_CONNECTED_UNKNOWN;
            anetworktype[3] = NetworkType_CELL;
            anetworktype[4] = NetworkType_WIFI;
            anetworktype[5] = NetworkType_WIRED;
            $VALUES = anetworktype;
        }

        private NetworkType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private NetworkType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private NetworkType(String s, int i, NetworkType networktype)
        {
            Enum(s, i);
            swigValue = networktype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCINetworkManagementDelegate(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCINetworkManagementDelegateUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCINetworkManagementDelegate(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCINetworkManagementDelegate(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCINetworkManagementDelegate scinetworkmanagementdelegate)
    {
        long l;
        if(scinetworkmanagementdelegate == null)
            l = 0L;
        else
            l = scinetworkmanagementdelegate.swigCPtr;
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

    public NetworkType getNetworkType()
    {
        return NetworkType.swigToEnum(sclibJNI.SCINetworkManagementDelegate_getNetworkType(swigCPtr, this));
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCINetworkManagementDelegate");
    private long swigCPtr;

}

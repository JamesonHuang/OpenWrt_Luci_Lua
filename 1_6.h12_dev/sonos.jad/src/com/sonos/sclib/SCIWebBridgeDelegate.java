// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIWebMessage, SCRouteResultType

public class SCIWebBridgeDelegate extends SCIObj
{
    public static final class ePreRouteResult extends Enum
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


        public static ePreRouteResult swigToEnum(int i)
        {
            ePreRouteResult aeprerouteresult[] = (ePreRouteResult[])com/sonos/sclib/SCIWebBridgeDelegate$ePreRouteResult.getEnumConstants();
            if(i >= aeprerouteresult.length || i < 0 || aeprerouteresult[i].swigValue != i) goto _L2; else goto _L1
_L1:
            ePreRouteResult eprerouteresult1 = aeprerouteresult[i];
_L4:
            return eprerouteresult1;
_L2:
            int j = aeprerouteresult.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                ePreRouteResult eprerouteresult = aeprerouteresult[k];
                if(eprerouteresult.swigValue == i)
                {
                    eprerouteresult1 = eprerouteresult;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIWebBridgeDelegate$ePreRouteResult).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static ePreRouteResult valueOf(String s)
        {
            return (ePreRouteResult)Enum.valueOf(com/sonos/sclib/SCIWebBridgeDelegate$ePreRouteResult, s);
        }

        public static ePreRouteResult[] values()
        {
            return (ePreRouteResult[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final ePreRouteResult $VALUES[];
        public static final ePreRouteResult CONTINUE_ROUTING;
        public static final ePreRouteResult STOP_ROUTING;
        private final int swigValue;

        static 
        {
            CONTINUE_ROUTING = new ePreRouteResult("CONTINUE_ROUTING", 0);
            STOP_ROUTING = new ePreRouteResult("STOP_ROUTING", 1);
            ePreRouteResult aeprerouteresult[] = new ePreRouteResult[2];
            aeprerouteresult[0] = CONTINUE_ROUTING;
            aeprerouteresult[1] = STOP_ROUTING;
            $VALUES = aeprerouteresult;
        }

        private ePreRouteResult(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private ePreRouteResult(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private ePreRouteResult(String s, int i, ePreRouteResult eprerouteresult)
        {
            Enum(s, i);
            swigValue = eprerouteresult.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIWebBridgeDelegate(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIWebBridgeDelegateUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIWebBridgeDelegate(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIWebBridgeDelegate(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIWebBridgeDelegate sciwebbridgedelegate)
    {
        long l;
        if(sciwebbridgedelegate == null)
            l = 0L;
        else
            l = sciwebbridgedelegate.swigCPtr;
        return l;
    }

    public void bridgeStarted(String s, boolean flag)
    {
        sclibJNI.SCIWebBridgeDelegate_bridgeStarted(swigCPtr, this, s, flag);
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

    public void doPostRouteMessage(SCIWebMessage sciwebmessage, SCRouteResultType scrouteresulttype)
    {
        sclibJNI.SCIWebBridgeDelegate_doPostRouteMessage(swigCPtr, this, SCIWebMessage.getCPtr(sciwebmessage), sciwebmessage, scrouteresulttype.swigValue());
    }

    public ePreRouteResult doPreRouteMessage(SCIWebMessage sciwebmessage)
    {
        return ePreRouteResult.swigToEnum(sclibJNI.SCIWebBridgeDelegate_doPreRouteMessage(swigCPtr, this, SCIWebMessage.getCPtr(sciwebmessage), sciwebmessage));
    }

    public void publishNativeEvent(SCIWebMessage sciwebmessage, int i)
    {
        sclibJNI.SCIWebBridgeDelegate_publishNativeEvent(swigCPtr, this, SCIWebMessage.getCPtr(sciwebmessage), sciwebmessage, i);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIWebBridgeDelegate");
    private long swigCPtr;

}

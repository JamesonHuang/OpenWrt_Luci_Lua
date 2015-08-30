// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIPropertyBag, SCIWebRequestSpec, 
//            SCIWebMessage, SCRouteResultType

public class SCIWebBridge extends SCIObj
{
    public static final class SCRouteWebMessageType extends Enum
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


        public static SCRouteWebMessageType swigToEnum(int i)
        {
            SCRouteWebMessageType ascroutewebmessagetype[] = (SCRouteWebMessageType[])com/sonos/sclib/SCIWebBridge$SCRouteWebMessageType.getEnumConstants();
            if(i >= ascroutewebmessagetype.length || i < 0 || ascroutewebmessagetype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SCRouteWebMessageType scroutewebmessagetype1 = ascroutewebmessagetype[i];
_L4:
            return scroutewebmessagetype1;
_L2:
            int j = ascroutewebmessagetype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SCRouteWebMessageType scroutewebmessagetype = ascroutewebmessagetype[k];
                if(scroutewebmessagetype.swigValue == i)
                {
                    scroutewebmessagetype1 = scroutewebmessagetype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIWebBridge$SCRouteWebMessageType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SCRouteWebMessageType valueOf(String s)
        {
            return (SCRouteWebMessageType)Enum.valueOf(com/sonos/sclib/SCIWebBridge$SCRouteWebMessageType, s);
        }

        public static SCRouteWebMessageType[] values()
        {
            return (SCRouteWebMessageType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SCRouteWebMessageType $VALUES[];
        public static final SCRouteWebMessageType COMMAND;
        public static final SCRouteWebMessageType PUBLISH;
        public static final SCRouteWebMessageType SUBSCRIBE;
        public static final SCRouteWebMessageType UNSUBSCRIBEALL;
        private final int swigValue;

        static 
        {
            COMMAND = new SCRouteWebMessageType("COMMAND", 0, sclibJNI.SCIWebBridge_COMMAND_get());
            SUBSCRIBE = new SCRouteWebMessageType("SUBSCRIBE", 1);
            UNSUBSCRIBEALL = new SCRouteWebMessageType("UNSUBSCRIBEALL", 2);
            PUBLISH = new SCRouteWebMessageType("PUBLISH", 3);
            SCRouteWebMessageType ascroutewebmessagetype[] = new SCRouteWebMessageType[4];
            ascroutewebmessagetype[0] = COMMAND;
            ascroutewebmessagetype[1] = SUBSCRIBE;
            ascroutewebmessagetype[2] = UNSUBSCRIBEALL;
            ascroutewebmessagetype[3] = PUBLISH;
            $VALUES = ascroutewebmessagetype;
        }

        private SCRouteWebMessageType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SCRouteWebMessageType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SCRouteWebMessageType(String s, int i, SCRouteWebMessageType scroutewebmessagetype)
        {
            Enum(s, i);
            swigValue = scroutewebmessagetype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIWebBridge(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIWebBridgeUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIWebBridge(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIWebBridge(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIWebBridge sciwebbridge)
    {
        long l;
        if(sciwebbridge == null)
            l = 0L;
        else
            l = sciwebbridge.swigCPtr;
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

    public String getDataForObject(String s, String s1)
    {
        return sclibJNI.SCIWebBridge_getDataForObject(swigCPtr, this, s, s1);
    }

    public String getFailedToConnectErrorMessage(String s)
    {
        return sclibJNI.SCIWebBridge_getFailedToConnectErrorMessage(swigCPtr, this, s);
    }

    public SCIWebRequestSpec getHttpRequestSpec(String s, SCIPropertyBag scipropertybag)
    {
        long l = sclibJNI.SCIWebBridge_getHttpRequestSpec(swigCPtr, this, s, SCIPropertyBag.getCPtr(scipropertybag), scipropertybag);
        SCIWebRequestSpec sciwebrequestspec;
        if(l == 0L)
            sciwebrequestspec = null;
        else
            sciwebrequestspec = new SCIWebRequestSpec(l, true);
        return sciwebrequestspec;
    }

    public String getJavaScriptAppContextEvalExpression()
    {
        return sclibJNI.SCIWebBridge_getJavaScriptAppContextEvalExpression(swigCPtr, this);
    }

    public String getRequestFailedErrorMessage()
    {
        return sclibJNI.SCIWebBridge_getRequestFailedErrorMessage(swigCPtr, this);
    }

    public String getTimeoutErrorMessage()
    {
        return sclibJNI.SCIWebBridge_getTimeoutErrorMessage(swigCPtr, this);
    }

    public void init()
    {
        sclibJNI.SCIWebBridge_init(swigCPtr, this);
    }

    public SCRouteResultType routeMessage(SCIWebMessage sciwebmessage, SCRouteWebMessageType scroutewebmessagetype)
    {
        return SCRouteResultType.swigToEnum(sclibJNI.SCIWebBridge_routeMessage(swigCPtr, this, SCIWebMessage.getCPtr(sciwebmessage), sciwebmessage, scroutewebmessagetype.swigValue()));
    }

    public void term()
    {
        sclibJNI.SCIWebBridge_term(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIWebBridge");
    private long swigCPtr;

}

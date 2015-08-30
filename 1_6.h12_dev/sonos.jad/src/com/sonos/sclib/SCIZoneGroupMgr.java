// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIHousehold, SCIStringArray, 
//            SCIOp, SCIPropertyBag, SCIActionContext, SCIEnumerator

public class SCIZoneGroupMgr extends SCIObj
{
    public static final class ZMState extends Enum
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


        public static ZMState swigToEnum(int i)
        {
            ZMState azmstate[] = (ZMState[])com/sonos/sclib/SCIZoneGroupMgr$ZMState.getEnumConstants();
            if(i >= azmstate.length || i < 0 || azmstate[i].swigValue != i) goto _L2; else goto _L1
_L1:
            ZMState zmstate1 = azmstate[i];
_L4:
            return zmstate1;
_L2:
            int j = azmstate.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                ZMState zmstate = azmstate[k];
                if(zmstate.swigValue == i)
                {
                    zmstate1 = zmstate;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIZoneGroupMgr$ZMState).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static ZMState valueOf(String s)
        {
            return (ZMState)Enum.valueOf(com/sonos/sclib/SCIZoneGroupMgr$ZMState, s);
        }

        public static ZMState[] values()
        {
            return (ZMState[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final ZMState $VALUES[];
        public static final ZMState ZM_STATE_ALL_UNCONFIGURED;
        public static final ZMState ZM_STATE_ALL_ZONES_HIDDEN;
        public static final ZMState ZM_STATE_INCOMPATIBLE;
        public static final ZMState ZM_STATE_NORMAL;
        public static final ZMState ZM_STATE_NO_PLAYERS;
        public static final ZMState ZM_STATE_NO_ZONES_FOUND;
        public static final ZMState ZM_STATE_ORPHANED_PLAYERS;
        private final int swigValue;

        static 
        {
            ZM_STATE_NORMAL = new ZMState("ZM_STATE_NORMAL", 0, sclibJNI.SCIZoneGroupMgr_ZM_STATE_NORMAL_get());
            ZM_STATE_ALL_ZONES_HIDDEN = new ZMState("ZM_STATE_ALL_ZONES_HIDDEN", 1);
            ZM_STATE_ORPHANED_PLAYERS = new ZMState("ZM_STATE_ORPHANED_PLAYERS", 2);
            ZM_STATE_NO_PLAYERS = new ZMState("ZM_STATE_NO_PLAYERS", 3);
            ZM_STATE_NO_ZONES_FOUND = new ZMState("ZM_STATE_NO_ZONES_FOUND", 4);
            ZM_STATE_ALL_UNCONFIGURED = new ZMState("ZM_STATE_ALL_UNCONFIGURED", 5);
            ZM_STATE_INCOMPATIBLE = new ZMState("ZM_STATE_INCOMPATIBLE", 6);
            ZMState azmstate[] = new ZMState[7];
            azmstate[0] = ZM_STATE_NORMAL;
            azmstate[1] = ZM_STATE_ALL_ZONES_HIDDEN;
            azmstate[2] = ZM_STATE_ORPHANED_PLAYERS;
            azmstate[3] = ZM_STATE_NO_PLAYERS;
            azmstate[4] = ZM_STATE_NO_ZONES_FOUND;
            azmstate[5] = ZM_STATE_ALL_UNCONFIGURED;
            azmstate[6] = ZM_STATE_INCOMPATIBLE;
            $VALUES = azmstate;
        }

        private ZMState(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private ZMState(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private ZMState(String s, int i, ZMState zmstate)
        {
            Enum(s, i);
            swigValue = zmstate.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIZoneGroupMgr(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIZoneGroupMgrUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIZoneGroupMgr(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIZoneGroupMgr(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIZoneGroupMgr scizonegroupmgr)
    {
        long l;
        if(scizonegroupmgr == null)
            l = 0L;
        else
            l = scizonegroupmgr.swigCPtr;
        return l;
    }

    public static SCIZoneGroupMgr getInterface(SCIHousehold scihousehold)
    {
        long l = sclibJNI.SCIZoneGroupMgr_getInterface(SCIHousehold.getCPtr(scihousehold), scihousehold);
        SCIZoneGroupMgr scizonegroupmgr;
        if(l == 0L)
            scizonegroupmgr = null;
        else
            scizonegroupmgr = new SCIZoneGroupMgr(l, true);
        return scizonegroupmgr;
    }

    public SCIOp createAddAndDropDevicesOp(String s, SCIStringArray scistringarray, SCIStringArray scistringarray1)
    {
        long l = sclibJNI.SCIZoneGroupMgr_createAddAndDropDevicesOp(swigCPtr, this, s, SCIStringArray.getCPtr(scistringarray), scistringarray, SCIStringArray.getCPtr(scistringarray1), scistringarray1);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIPropertyBag createLimitedConnectivityPropertyBag()
    {
        long l = sclibJNI.SCIZoneGroupMgr_createLimitedConnectivityPropertyBag(swigCPtr, this);
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        return scipropertybag;
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

    public SCIActionContext getActionForZoneGroup(String s)
    {
        long l = sclibJNI.SCIZoneGroupMgr_getActionForZoneGroup(swigCPtr, this, s);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    public boolean getAllIncompatible()
    {
        return sclibJNI.SCIZoneGroupMgr_getAllIncompatible(swigCPtr, this);
    }

    public boolean getAnyIncompatible()
    {
        return sclibJNI.SCIZoneGroupMgr_getAnyIncompatible(swigCPtr, this);
    }

    public boolean getAnyVisibleAndCompatible()
    {
        return sclibJNI.SCIZoneGroupMgr_getAnyVisibleAndCompatible(swigCPtr, this);
    }

    public SCIEnumerator getLimitedConnectivityActions()
    {
        long l = sclibJNI.SCIZoneGroupMgr_getLimitedConnectivityActions(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public boolean getOlderDeviceExists()
    {
        return sclibJNI.SCIZoneGroupMgr_getOlderDeviceExists(swigCPtr, this);
    }

    public ZMState getState()
    {
        return ZMState.swigToEnum(sclibJNI.SCIZoneGroupMgr_getState(swigCPtr, this));
    }

    public boolean hasNoPlayers()
    {
        return sclibJNI.SCIZoneGroupMgr_hasNoPlayers(swigCPtr, this);
    }

    public boolean isFullyCompatible()
    {
        return sclibJNI.SCIZoneGroupMgr_isFullyCompatible(swigCPtr, this);
    }

    public boolean isInCrossVersionMode()
    {
        return sclibJNI.SCIZoneGroupMgr_isInCrossVersionMode(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIZoneGroupMgr");
    private long swigCPtr;

}

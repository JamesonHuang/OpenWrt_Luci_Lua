// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIAction, SCIStringArray

public class SCILocationServices extends SCIObj
{
    public static final class LocationServiceStatus extends Enum
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


        public static LocationServiceStatus swigToEnum(int i)
        {
            LocationServiceStatus alocationservicestatus[] = (LocationServiceStatus[])com/sonos/sclib/SCILocationServices$LocationServiceStatus.getEnumConstants();
            if(i >= alocationservicestatus.length || i < 0 || alocationservicestatus[i].swigValue != i) goto _L2; else goto _L1
_L1:
            LocationServiceStatus locationservicestatus1 = alocationservicestatus[i];
_L4:
            return locationservicestatus1;
_L2:
            int j = alocationservicestatus.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                LocationServiceStatus locationservicestatus = alocationservicestatus[k];
                if(locationservicestatus.swigValue == i)
                {
                    locationservicestatus1 = locationservicestatus;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCILocationServices$LocationServiceStatus).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static LocationServiceStatus valueOf(String s)
        {
            return (LocationServiceStatus)Enum.valueOf(com/sonos/sclib/SCILocationServices$LocationServiceStatus, s);
        }

        public static LocationServiceStatus[] values()
        {
            return (LocationServiceStatus[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final LocationServiceStatus $VALUES[];
        public static final LocationServiceStatus StatusDisabled;
        public static final LocationServiceStatus StatusEnabled;
        public static final LocationServiceStatus StatusUnknown;
        private final int swigValue;

        static 
        {
            StatusUnknown = new LocationServiceStatus("StatusUnknown", 0);
            StatusEnabled = new LocationServiceStatus("StatusEnabled", 1);
            StatusDisabled = new LocationServiceStatus("StatusDisabled", 2);
            LocationServiceStatus alocationservicestatus[] = new LocationServiceStatus[3];
            alocationservicestatus[0] = StatusUnknown;
            alocationservicestatus[1] = StatusEnabled;
            alocationservicestatus[2] = StatusDisabled;
            $VALUES = alocationservicestatus;
        }

        private LocationServiceStatus(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private LocationServiceStatus(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private LocationServiceStatus(String s, int i, LocationServiceStatus locationservicestatus)
        {
            Enum(s, i);
            swigValue = locationservicestatus.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCILocationServices(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCILocationServicesUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCILocationServices(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCILocationServices(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCILocationServices scilocationservices)
    {
        long l;
        if(scilocationservices == null)
            l = 0L;
        else
            l = scilocationservices.swigCPtr;
        return l;
    }

    public SCIAction createRequestLocationAuthorizationAction()
    {
        long l = sclibJNI.SCILocationServices_createRequestLocationAuthorizationAction(swigCPtr, this);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
    }

    public SCIAction createRequestLocationInformationAction(SCIStringArray scistringarray)
    {
        long l = sclibJNI.SCILocationServices_createRequestLocationInformationAction(swigCPtr, this, SCIStringArray.getCPtr(scistringarray), scistringarray);
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        return sciaction;
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

    public String getLocationUsageDescription()
    {
        return sclibJNI.SCILocationServices_getLocationUsageDescription(swigCPtr, this);
    }

    public String getLocationUsageExistingHHDescription()
    {
        return sclibJNI.SCILocationServices_getLocationUsageExistingHHDescription(swigCPtr, this);
    }

    public LocationServiceStatus locationStatus()
    {
        return LocationServiceStatus.swigToEnum(sclibJNI.SCILocationServices_locationStatus(swigCPtr, this));
    }

    public boolean shouldRequestLocationForExistingHH()
    {
        return sclibJNI.SCILocationServices_shouldRequestLocationForExistingHH(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCILocationServices");
    private long swigCPtr;

}

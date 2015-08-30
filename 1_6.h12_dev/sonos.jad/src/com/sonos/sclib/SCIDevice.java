// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIOp, SCIOpConnectionManagerGetProtocolInfo, 
//            SCIOpDevicePropertiesGetLEDState, SCIVersionRange, SCIVersion

public class SCIDevice extends SCIObj
{
    public static final class DeviceClass extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$102(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$108()
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


        public static DeviceClass swigToEnum(int i)
        {
            DeviceClass adeviceclass[] = (DeviceClass[])com/sonos/sclib/SCIDevice$DeviceClass.getEnumConstants();
            if(i >= adeviceclass.length || i < 0 || adeviceclass[i].swigValue != i) goto _L2; else goto _L1
_L1:
            DeviceClass deviceclass1 = adeviceclass[i];
_L4:
            return deviceclass1;
_L2:
            int j = adeviceclass.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                DeviceClass deviceclass = adeviceclass[k];
                if(deviceclass.swigValue == i)
                {
                    deviceclass1 = deviceclass;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIDevice$DeviceClass).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static DeviceClass valueOf(String s)
        {
            return (DeviceClass)Enum.valueOf(com/sonos/sclib/SCIDevice$DeviceClass, s);
        }

        public static DeviceClass[] values()
        {
            return (DeviceClass[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final DeviceClass $VALUES[];
        public static final DeviceClass DC_DOCK;
        public static final DeviceClass DC_SECONDARY_PLAYER;
        public static final DeviceClass DC_SUB_DEPRECATED;
        public static final DeviceClass DC_UNKNOWN;
        public static final DeviceClass DC_ZONEBRIDGE;
        public static final DeviceClass DC_ZONEPLAYER;
        private final int swigValue;

        static 
        {
            DC_UNKNOWN = new DeviceClass("DC_UNKNOWN", 0, sclibJNI.SCIDevice_DC_UNKNOWN_get());
            DC_ZONEPLAYER = new DeviceClass("DC_ZONEPLAYER", 1);
            DC_ZONEBRIDGE = new DeviceClass("DC_ZONEBRIDGE", 2);
            DC_DOCK = new DeviceClass("DC_DOCK", 3);
            DC_SUB_DEPRECATED = new DeviceClass("DC_SUB_DEPRECATED", 4);
            DC_SECONDARY_PLAYER = new DeviceClass("DC_SECONDARY_PLAYER", 5);
            DeviceClass adeviceclass[] = new DeviceClass[6];
            adeviceclass[0] = DC_UNKNOWN;
            adeviceclass[1] = DC_ZONEPLAYER;
            adeviceclass[2] = DC_ZONEBRIDGE;
            adeviceclass[3] = DC_DOCK;
            adeviceclass[4] = DC_SUB_DEPRECATED;
            adeviceclass[5] = DC_SECONDARY_PLAYER;
            $VALUES = adeviceclass;
        }

        private DeviceClass(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private DeviceClass(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private DeviceClass(String s, int i, DeviceClass deviceclass)
        {
            Enum(s, i);
            swigValue = deviceclass.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class DeviceModel extends Enum
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


        public static DeviceModel swigToEnum(int i)
        {
            DeviceModel adevicemodel[] = (DeviceModel[])com/sonos/sclib/SCIDevice$DeviceModel.getEnumConstants();
            if(i >= adevicemodel.length || i < 0 || adevicemodel[i].swigValue != i) goto _L2; else goto _L1
_L1:
            DeviceModel devicemodel1 = adevicemodel[i];
_L4:
            return devicemodel1;
_L2:
            int j = adevicemodel.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                DeviceModel devicemodel = adevicemodel[k];
                if(devicemodel.swigValue == i)
                {
                    devicemodel1 = devicemodel;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIDevice$DeviceModel).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static DeviceModel valueOf(String s)
        {
            return (DeviceModel)Enum.valueOf(com/sonos/sclib/SCIDevice$DeviceModel, s);
        }

        public static DeviceModel[] values()
        {
            return (DeviceModel[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final DeviceModel $VALUES[];
        public static final DeviceModel DM_BOOST;
        public static final DeviceModel DM_DOCK;
        public static final DeviceModel DM_SOUNDBAR;
        public static final DeviceModel DM_SUB;
        public static final DeviceModel DM_UNKNOWN;
        public static final DeviceModel DM_ZONEBRIDGE;
        public static final DeviceModel DM_ZP100;
        public static final DeviceModel DM_ZP120;
        public static final DeviceModel DM_ZP80;
        public static final DeviceModel DM_ZPS1;
        public static final DeviceModel DM_ZPS3;
        public static final DeviceModel DM_ZPS5;
        private final int swigValue;

        static 
        {
            DM_UNKNOWN = new DeviceModel("DM_UNKNOWN", 0, sclibJNI.SCIDevice_DM_UNKNOWN_get());
            DM_ZP80 = new DeviceModel("DM_ZP80", 1);
            DM_ZP100 = new DeviceModel("DM_ZP100", 2);
            DM_ZP120 = new DeviceModel("DM_ZP120", 3);
            DM_ZONEBRIDGE = new DeviceModel("DM_ZONEBRIDGE", 4);
            DM_ZPS5 = new DeviceModel("DM_ZPS5", 5);
            DM_DOCK = new DeviceModel("DM_DOCK", 6);
            DM_ZPS3 = new DeviceModel("DM_ZPS3", 7);
            DM_SUB = new DeviceModel("DM_SUB", 8);
            DM_SOUNDBAR = new DeviceModel("DM_SOUNDBAR", 9);
            DM_ZPS1 = new DeviceModel("DM_ZPS1", 10);
            DM_BOOST = new DeviceModel("DM_BOOST", 11);
            DeviceModel adevicemodel[] = new DeviceModel[12];
            adevicemodel[0] = DM_UNKNOWN;
            adevicemodel[1] = DM_ZP80;
            adevicemodel[2] = DM_ZP100;
            adevicemodel[3] = DM_ZP120;
            adevicemodel[4] = DM_ZONEBRIDGE;
            adevicemodel[5] = DM_ZPS5;
            adevicemodel[6] = DM_DOCK;
            adevicemodel[7] = DM_ZPS3;
            adevicemodel[8] = DM_SUB;
            adevicemodel[9] = DM_SOUNDBAR;
            adevicemodel[10] = DM_ZPS1;
            adevicemodel[11] = DM_BOOST;
            $VALUES = adevicemodel;
        }

        private DeviceModel(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private DeviceModel(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private DeviceModel(String s, int i, DeviceModel devicemodel)
        {
            Enum(s, i);
            swigValue = devicemodel.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIDevice(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIDeviceUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIDevice(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIDevice(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIDevice scidevice)
    {
        long l;
        if(scidevice == null)
            l = 0L;
        else
            l = scidevice.swigCPtr;
        return l;
    }

    public boolean canCreateStereoPairWithDevice(SCIDevice scidevice)
    {
        return sclibJNI.SCIDevice_canCreateStereoPairWithDevice(swigCPtr, this, getCPtr(scidevice), scidevice);
    }

    public SCIOp createConfigureOp(long l)
    {
        long l1 = sclibJNI.SCIDevice_createConfigureOp(swigCPtr, this, l);
        SCIOp sciop;
        if(l1 == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l1, true);
        return sciop;
    }

    public SCIOpConnectionManagerGetProtocolInfo createGetProtocolInfoOp()
    {
        long l = sclibJNI.SCIDevice_createGetProtocolInfoOp(swigCPtr, this);
        SCIOpConnectionManagerGetProtocolInfo sciopconnectionmanagergetprotocolinfo;
        if(l == 0L)
            sciopconnectionmanagergetprotocolinfo = null;
        else
            sciopconnectionmanagergetprotocolinfo = new SCIOpConnectionManagerGetProtocolInfo(l, true);
        return sciopconnectionmanagergetprotocolinfo;
    }

    public SCIOpDevicePropertiesGetLEDState createGetWhiteLEDOp()
    {
        long l = sclibJNI.SCIDevice_createGetWhiteLEDOp(swigCPtr, this);
        SCIOpDevicePropertiesGetLEDState sciopdevicepropertiesgetledstate;
        if(l == 0L)
            sciopdevicepropertiesgetledstate = null;
        else
            sciopdevicepropertiesgetledstate = new SCIOpDevicePropertiesGetLEDState(l, true);
        return sciopdevicepropertiesgetledstate;
    }

    public SCIOp createPauseOp()
    {
        long l = sclibJNI.SCIDevice_createPauseOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createRenameOp(String s, String s1)
    {
        long l = sclibJNI.SCIDevice_createRenameOp__SWIG_0(swigCPtr, this, s, s1);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createRenameOp(String s, String s1, long l)
    {
        long l1 = sclibJNI.SCIDevice_createRenameOp__SWIG_1(swigCPtr, this, s, s1, l);
        SCIOp sciop;
        if(l1 == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l1, true);
        return sciop;
    }

    public SCIOp createRenameToHiddenOp(Object aobj[])
    {
        long l = sclibJNI.SCIDevice_createRenameToHiddenOp(swigCPtr, this, aobj);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSeparateStereoPairOp()
    {
        long l = sclibJNI.SCIDevice_createSeparateStereoPairOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetWhiteLEDOp(boolean flag)
    {
        long l = sclibJNI.SCIDevice_createSetWhiteLEDOp(swigCPtr, this, flag);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createStopOp()
    {
        long l = sclibJNI.SCIDevice_createStopOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
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

    public SCIVersionRange getCompatibleVersionRange()
    {
        long l = sclibJNI.SCIDevice_getCompatibleVersionRange(swigCPtr, this);
        SCIVersionRange sciversionrange;
        if(l == 0L)
            sciversionrange = null;
        else
            sciversionrange = new SCIVersionRange(l, true);
        return sciversionrange;
    }

    public DeviceClass getDeviceClass()
    {
        return DeviceClass.swigToEnum(sclibJNI.SCIDevice_getDeviceClass(swigCPtr, this));
    }

    public DeviceModel getDeviceModel()
    {
        return DeviceModel.swigToEnum(sclibJNI.SCIDevice_getDeviceModel(swigCPtr, this));
    }

    public String getDeviceModelDisplayString()
    {
        return sclibJNI.SCIDevice_getDeviceModelDisplayString(swigCPtr, this);
    }

    public String getID()
    {
        return sclibJNI.SCIDevice_getID(swigCPtr, this);
    }

    public String getIconURI()
    {
        return sclibJNI.SCIDevice_getIconURI(swigCPtr, this);
    }

    public String getMinCompatibleVersionStr()
    {
        return sclibJNI.SCIDevice_getMinCompatibleVersionStr(swigCPtr, this);
    }

    public SCIVersion getSoftwareVersion()
    {
        long l = sclibJNI.SCIDevice_getSoftwareVersion(swigCPtr, this);
        SCIVersion sciversion;
        if(l == 0L)
            sciversion = null;
        else
            sciversion = new SCIVersion(l, true);
        return sciversion;
    }

    public String getSoftwareVersionStr()
    {
        return sclibJNI.SCIDevice_getSoftwareVersionStr(swigCPtr, this);
    }

    public String getTitle()
    {
        return sclibJNI.SCIDevice_getTitle(swigCPtr, this);
    }

    public String getTitleForAboutBox()
    {
        return sclibJNI.SCIDevice_getTitleForAboutBox(swigCPtr, this);
    }

    public String getTitleForDevice()
    {
        return sclibJNI.SCIDevice_getTitleForDevice(swigCPtr, this);
    }

    public String getTitleForZPSettingsMenu()
    {
        return sclibJNI.SCIDevice_getTitleForZPSettingsMenu(swigCPtr, this);
    }

    public boolean isCompatible()
    {
        return sclibJNI.SCIDevice_isCompatible(swigCPtr, this);
    }

    public boolean isConfigured()
    {
        return sclibJNI.SCIDevice_isConfigured(swigCPtr, this);
    }

    public boolean isVisible()
    {
        return sclibJNI.SCIDevice_isVisible(swigCPtr, this);
    }

    public void setTitle(String s)
    {
        sclibJNI.SCIDevice_setTitle(swigCPtr, this, s);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIDevice");
    private long swigCPtr;

}

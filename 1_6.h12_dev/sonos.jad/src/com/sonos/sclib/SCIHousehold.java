// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCISetupWizard, SCIActionContext, 
//            SCISoundbarWizard, SCIBrowseStackManager, SCIConfigureWirelessWizard, SCIOpGetAboutSonosString, 
//            SCIOpSystemPropertyGetString, SCIOpGetUsageDataShareOption, SCIOpZoneGroupTopologyGetZoneGroupState, SCIMusicServiceWizard, 
//            SCIPropertyBag, SCIOnlineUpdateWizard, SCIOp, SCISonosNetSetupWizard, 
//            SCISearchable, SCIAlarmManager, SCIEnumerator, SCIDevice, 
//            SCIBrowseListPresentationMap, SCIZoneGroup, SCIDateTimeManager, SCIIndexManager, 
//            SCIServiceDescriptorManager, SCIShareManager, SCIEventSink

public class SCIHousehold extends SCIObj
{
    public static final class DevFilterOpt extends Enum
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


        public static DevFilterOpt swigToEnum(int i)
        {
            DevFilterOpt adevfilteropt[] = (DevFilterOpt[])com/sonos/sclib/SCIHousehold$DevFilterOpt.getEnumConstants();
            if(i >= adevfilteropt.length || i < 0 || adevfilteropt[i].swigValue != i) goto _L2; else goto _L1
_L1:
            DevFilterOpt devfilteropt1 = adevfilteropt[i];
_L4:
            return devfilteropt1;
_L2:
            int j = adevfilteropt.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                DevFilterOpt devfilteropt = adevfilteropt[k];
                if(devfilteropt.swigValue == i)
                {
                    devfilteropt1 = devfilteropt;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIHousehold$DevFilterOpt).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static DevFilterOpt valueOf(String s)
        {
            return (DevFilterOpt)Enum.valueOf(com/sonos/sclib/SCIHousehold$DevFilterOpt, s);
        }

        public static DevFilterOpt[] values()
        {
            return (DevFilterOpt[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final DevFilterOpt $VALUES[];
        public static final DevFilterOpt FLT_DEV_ANY;
        public static final DevFilterOpt FLT_DEV_COMPATIBLE_AND_UNCONFIGURED;
        public static final DevFilterOpt FLT_DEV_COMPATIBLE_AND_VISIBLE;
        public static final DevFilterOpt FLT_DEV_LINE_IN_ZONEPLAYERS;
        public static final DevFilterOpt FLT_DEV_SETTING_MENU_DOCKS;
        public static final DevFilterOpt FLT_DEV_SETTING_MENU_ZONEBRIDGES;
        public static final DevFilterOpt FLT_DEV_SETTING_MENU_ZONEPLAYERS;
        public static final DevFilterOpt FLT_DEV_STEREO_PAIR_CANDIDATES;
        private final int swigValue;

        static 
        {
            FLT_DEV_COMPATIBLE_AND_VISIBLE = new DevFilterOpt("FLT_DEV_COMPATIBLE_AND_VISIBLE", 0);
            FLT_DEV_COMPATIBLE_AND_UNCONFIGURED = new DevFilterOpt("FLT_DEV_COMPATIBLE_AND_UNCONFIGURED", 1);
            FLT_DEV_SETTING_MENU_ZONEPLAYERS = new DevFilterOpt("FLT_DEV_SETTING_MENU_ZONEPLAYERS", 2);
            FLT_DEV_SETTING_MENU_ZONEBRIDGES = new DevFilterOpt("FLT_DEV_SETTING_MENU_ZONEBRIDGES", 3);
            FLT_DEV_SETTING_MENU_DOCKS = new DevFilterOpt("FLT_DEV_SETTING_MENU_DOCKS", 4);
            FLT_DEV_STEREO_PAIR_CANDIDATES = new DevFilterOpt("FLT_DEV_STEREO_PAIR_CANDIDATES", 5);
            FLT_DEV_LINE_IN_ZONEPLAYERS = new DevFilterOpt("FLT_DEV_LINE_IN_ZONEPLAYERS", 6);
            FLT_DEV_ANY = new DevFilterOpt("FLT_DEV_ANY", 7);
            DevFilterOpt adevfilteropt[] = new DevFilterOpt[8];
            adevfilteropt[0] = FLT_DEV_COMPATIBLE_AND_VISIBLE;
            adevfilteropt[1] = FLT_DEV_COMPATIBLE_AND_UNCONFIGURED;
            adevfilteropt[2] = FLT_DEV_SETTING_MENU_ZONEPLAYERS;
            adevfilteropt[3] = FLT_DEV_SETTING_MENU_ZONEBRIDGES;
            adevfilteropt[4] = FLT_DEV_SETTING_MENU_DOCKS;
            adevfilteropt[5] = FLT_DEV_STEREO_PAIR_CANDIDATES;
            adevfilteropt[6] = FLT_DEV_LINE_IN_ZONEPLAYERS;
            adevfilteropt[7] = FLT_DEV_ANY;
            $VALUES = adevfilteropt;
        }

        private DevFilterOpt(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private DevFilterOpt(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private DevFilterOpt(String s, int i, DevFilterOpt devfilteropt)
        {
            Enum(s, i);
            swigValue = devfilteropt.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class ZGFilterOpt extends Enum
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


        public static ZGFilterOpt swigToEnum(int i)
        {
            ZGFilterOpt azgfilteropt[] = (ZGFilterOpt[])com/sonos/sclib/SCIHousehold$ZGFilterOpt.getEnumConstants();
            if(i >= azgfilteropt.length || i < 0 || azgfilteropt[i].swigValue != i) goto _L2; else goto _L1
_L1:
            ZGFilterOpt zgfilteropt1 = azgfilteropt[i];
_L4:
            return zgfilteropt1;
_L2:
            int j = azgfilteropt.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                ZGFilterOpt zgfilteropt = azgfilteropt[k];
                if(zgfilteropt.swigValue == i)
                {
                    zgfilteropt1 = zgfilteropt;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIHousehold$ZGFilterOpt).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static ZGFilterOpt valueOf(String s)
        {
            return (ZGFilterOpt)Enum.valueOf(com/sonos/sclib/SCIHousehold$ZGFilterOpt, s);
        }

        public static ZGFilterOpt[] values()
        {
            return (ZGFilterOpt[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final ZGFilterOpt $VALUES[];
        public static final ZGFilterOpt FLT_ZG_ANY;
        public static final ZGFilterOpt FLT_ZG_COMPATIBLE;
        public static final ZGFilterOpt FLT_ZG_INCOMPATIBLE;
        public static final ZGFilterOpt FLT_ZG_ZONEMENU;
        private final int swigValue;

        static 
        {
            FLT_ZG_COMPATIBLE = new ZGFilterOpt("FLT_ZG_COMPATIBLE", 0);
            FLT_ZG_INCOMPATIBLE = new ZGFilterOpt("FLT_ZG_INCOMPATIBLE", 1);
            FLT_ZG_ANY = new ZGFilterOpt("FLT_ZG_ANY", 2);
            FLT_ZG_ZONEMENU = new ZGFilterOpt("FLT_ZG_ZONEMENU", 3);
            ZGFilterOpt azgfilteropt[] = new ZGFilterOpt[4];
            azgfilteropt[0] = FLT_ZG_COMPATIBLE;
            azgfilteropt[1] = FLT_ZG_INCOMPATIBLE;
            azgfilteropt[2] = FLT_ZG_ANY;
            azgfilteropt[3] = FLT_ZG_ZONEMENU;
            $VALUES = azgfilteropt;
        }

        private ZGFilterOpt(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private ZGFilterOpt(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private ZGFilterOpt(String s, int i, ZGFilterOpt zgfilteropt)
        {
            Enum(s, i);
            swigValue = zgfilteropt.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIHousehold(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIHouseholdUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIHousehold(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIHousehold(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIHousehold scihousehold)
    {
        long l;
        if(scihousehold == null)
            l = 0L;
        else
            l = scihousehold.swigCPtr;
        return l;
    }

    public boolean canAddAccounts()
    {
        return sclibJNI.SCIHousehold_canAddAccounts(swigCPtr, this);
    }

    public SCISetupWizard createAddBoostWizard()
    {
        long l = sclibJNI.SCIHousehold_createAddBoostWizard(swigCPtr, this);
        SCISetupWizard scisetupwizard;
        if(l == 0L)
            scisetupwizard = null;
        else
            scisetupwizard = new SCISetupWizard(l, true);
        return scisetupwizard;
    }

    public SCIActionContext createAddCustomRadioStationAction()
    {
        long l = sclibJNI.SCIHousehold_createAddCustomRadioStationAction(swigCPtr, this);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    public SCISetupWizard createAddPlayerOrSubWizard()
    {
        long l = sclibJNI.SCIHousehold_createAddPlayerOrSubWizard(swigCPtr, this);
        SCISetupWizard scisetupwizard;
        if(l == 0L)
            scisetupwizard = null;
        else
            scisetupwizard = new SCISetupWizard(l, true);
        return scisetupwizard;
    }

    public SCISoundbarWizard createAddSubSoundbarWizard(String s)
    {
        long l = sclibJNI.SCIHousehold_createAddSubSoundbarWizard(swigCPtr, this, s);
        SCISoundbarWizard scisoundbarwizard;
        if(l == 0L)
            scisoundbarwizard = null;
        else
            scisoundbarwizard = new SCISoundbarWizard(l, true);
        return scisoundbarwizard;
    }

    public SCIBrowseStackManager createBrowseStackWithRoot(String s)
    {
        long l = sclibJNI.SCIHousehold_createBrowseStackWithRoot__SWIG_0(swigCPtr, this, s);
        SCIBrowseStackManager scibrowsestackmanager;
        if(l == 0L)
            scibrowsestackmanager = null;
        else
            scibrowsestackmanager = new SCIBrowseStackManager(l, true);
        return scibrowsestackmanager;
    }

    public SCIBrowseStackManager createBrowseStackWithRoot(String s, String s1)
    {
        long l = sclibJNI.SCIHousehold_createBrowseStackWithRoot__SWIG_1(swigCPtr, this, s, s1);
        SCIBrowseStackManager scibrowsestackmanager;
        if(l == 0L)
            scibrowsestackmanager = null;
        else
            scibrowsestackmanager = new SCIBrowseStackManager(l, true);
        return scibrowsestackmanager;
    }

    public SCISetupWizard createChangeSpeakersWizard(String s)
    {
        long l = sclibJNI.SCIHousehold_createChangeSpeakersWizard(swigCPtr, this, s);
        SCISetupWizard scisetupwizard;
        if(l == 0L)
            scisetupwizard = null;
        else
            scisetupwizard = new SCISetupWizard(l, true);
        return scisetupwizard;
    }

    public SCISetupWizard createChooseConfigureWizard()
    {
        long l = sclibJNI.SCIHousehold_createChooseConfigureWizard(swigCPtr, this);
        SCISetupWizard scisetupwizard;
        if(l == 0L)
            scisetupwizard = null;
        else
            scisetupwizard = new SCISetupWizard(l, true);
        return scisetupwizard;
    }

    public SCISetupWizard createConfigureSonosComponentWizard(String s)
    {
        long l = sclibJNI.SCIHousehold_createConfigureSonosComponentWizard(swigCPtr, this, s);
        SCISetupWizard scisetupwizard;
        if(l == 0L)
            scisetupwizard = null;
        else
            scisetupwizard = new SCISetupWizard(l, true);
        return scisetupwizard;
    }

    public SCISetupWizard createConfigureSubWizard(String s)
    {
        long l = sclibJNI.SCIHousehold_createConfigureSubWizard(swigCPtr, this, s);
        SCISetupWizard scisetupwizard;
        if(l == 0L)
            scisetupwizard = null;
        else
            scisetupwizard = new SCISetupWizard(l, true);
        return scisetupwizard;
    }

    public SCIConfigureWirelessWizard createConfigureWirelessWizard()
    {
        long l = sclibJNI.SCIHousehold_createConfigureWirelessWizard(swigCPtr, this);
        SCIConfigureWirelessWizard sciconfigurewirelesswizard;
        if(l == 0L)
            sciconfigurewirelesswizard = null;
        else
            sciconfigurewirelesswizard = new SCIConfigureWirelessWizard(l, true);
        return sciconfigurewirelesswizard;
    }

    public SCIActionContext createFactoryResetAction()
    {
        long l = sclibJNI.SCIHousehold_createFactoryResetAction(swigCPtr, this);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    public SCIActionContext createForgetHouseholdAction()
    {
        long l = sclibJNI.SCIHousehold_createForgetHouseholdAction(swigCPtr, this);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    public SCIOpGetAboutSonosString createGetAboutSonosStrOp()
    {
        long l = sclibJNI.SCIHousehold_createGetAboutSonosStrOp(swigCPtr, this);
        SCIOpGetAboutSonosString sciopgetaboutsonosstring;
        if(l == 0L)
            sciopgetaboutsonosstring = null;
        else
            sciopgetaboutsonosstring = new SCIOpGetAboutSonosString(l, true);
        return sciopgetaboutsonosstring;
    }

    public SCIOpSystemPropertyGetString createGetPropertyOp(String s)
    {
        long l = sclibJNI.SCIHousehold_createGetPropertyOp(swigCPtr, this, s);
        SCIOpSystemPropertyGetString sciopsystempropertygetstring;
        if(l == 0L)
            sciopsystempropertygetstring = null;
        else
            sciopsystempropertygetstring = new SCIOpSystemPropertyGetString(l, true);
        return sciopsystempropertygetstring;
    }

    public SCIOpGetAboutSonosString createGetShortAboutSonosStrOp()
    {
        long l = sclibJNI.SCIHousehold_createGetShortAboutSonosStrOp(swigCPtr, this);
        SCIOpGetAboutSonosString sciopgetaboutsonosstring;
        if(l == 0L)
            sciopgetaboutsonosstring = null;
        else
            sciopgetaboutsonosstring = new SCIOpGetAboutSonosString(l, true);
        return sciopgetaboutsonosstring;
    }

    public SCIOpGetUsageDataShareOption createGetUsageDataShareOptionOp()
    {
        long l = sclibJNI.SCIHousehold_createGetUsageDataShareOptionOp(swigCPtr, this);
        SCIOpGetUsageDataShareOption sciopgetusagedatashareoption;
        if(l == 0L)
            sciopgetusagedatashareoption = null;
        else
            sciopgetusagedatashareoption = new SCIOpGetUsageDataShareOption(l, true);
        return sciopgetusagedatashareoption;
    }

    public SCIOpZoneGroupTopologyGetZoneGroupState createGetZoneGroupStateOp(String s)
    {
        long l = sclibJNI.SCIHousehold_createGetZoneGroupStateOp(swigCPtr, this, s);
        SCIOpZoneGroupTopologyGetZoneGroupState sciopzonegrouptopologygetzonegroupstate;
        if(l == 0L)
            sciopzonegrouptopologygetzonegroupstate = null;
        else
            sciopzonegrouptopologygetzonegroupstate = new SCIOpZoneGroupTopologyGetZoneGroupState(l, true);
        return sciopzonegrouptopologygetzonegroupstate;
    }

    public SCISetupWizard createMusicLibrarySetupWizard()
    {
        long l = sclibJNI.SCIHousehold_createMusicLibrarySetupWizard(swigCPtr, this);
        SCISetupWizard scisetupwizard;
        if(l == 0L)
            scisetupwizard = null;
        else
            scisetupwizard = new SCISetupWizard(l, true);
        return scisetupwizard;
    }

    public SCIMusicServiceWizard createMusicServiceAddAccountWizard()
    {
        long l = sclibJNI.SCIHousehold_createMusicServiceAddAccountWizard(swigCPtr, this);
        SCIMusicServiceWizard scimusicservicewizard;
        if(l == 0L)
            scimusicservicewizard = null;
        else
            scimusicservicewizard = new SCIMusicServiceWizard(l, true);
        return scimusicservicewizard;
    }

    public SCIMusicServiceWizard createMusicServiceAddSonosLabsAccountWizard()
    {
        long l = sclibJNI.SCIHousehold_createMusicServiceAddSonosLabsAccountWizard(swigCPtr, this);
        SCIMusicServiceWizard scimusicservicewizard;
        if(l == 0L)
            scimusicservicewizard = null;
        else
            scimusicservicewizard = new SCIMusicServiceWizard(l, true);
        return scimusicservicewizard;
    }

    public SCIMusicServiceWizard createMusicServiceChangeNicknameWizard(String s)
    {
        long l = sclibJNI.SCIHousehold_createMusicServiceChangeNicknameWizard(swigCPtr, this, s);
        SCIMusicServiceWizard scimusicservicewizard;
        if(l == 0L)
            scimusicservicewizard = null;
        else
            scimusicservicewizard = new SCIMusicServiceWizard(l, true);
        return scimusicservicewizard;
    }

    public SCIMusicServiceWizard createMusicServiceChangePasswordWizard(String s)
    {
        long l = sclibJNI.SCIHousehold_createMusicServiceChangePasswordWizard(swigCPtr, this, s);
        SCIMusicServiceWizard scimusicservicewizard;
        if(l == 0L)
            scimusicservicewizard = null;
        else
            scimusicservicewizard = new SCIMusicServiceWizard(l, true);
        return scimusicservicewizard;
    }

    public SCIMusicServiceWizard createMusicServiceReauthorizeAccountWizard(String s)
    {
        long l = sclibJNI.SCIHousehold_createMusicServiceReauthorizeAccountWizard(swigCPtr, this, s);
        SCIMusicServiceWizard scimusicservicewizard;
        if(l == 0L)
            scimusicservicewizard = null;
        else
            scimusicservicewizard = new SCIMusicServiceWizard(l, true);
        return scimusicservicewizard;
    }

    public SCIMusicServiceWizard createMusicServiceReplaceAccountWizard(String s)
    {
        long l = sclibJNI.SCIHousehold_createMusicServiceReplaceAccountWizard(swigCPtr, this, s);
        SCIMusicServiceWizard scimusicservicewizard;
        if(l == 0L)
            scimusicservicewizard = null;
        else
            scimusicservicewizard = new SCIMusicServiceWizard(l, true);
        return scimusicservicewizard;
    }

    public SCIMusicServiceWizard createMusicServiceSubscribeWizard(String s)
    {
        long l = sclibJNI.SCIHousehold_createMusicServiceSubscribeWizard(swigCPtr, this, s);
        SCIMusicServiceWizard scimusicservicewizard;
        if(l == 0L)
            scimusicservicewizard = null;
        else
            scimusicservicewizard = new SCIMusicServiceWizard(l, true);
        return scimusicservicewizard;
    }

    public SCIMusicServiceWizard createMusicServiceWizard(SCIPropertyBag scipropertybag)
    {
        long l = sclibJNI.SCIHousehold_createMusicServiceWizard(swigCPtr, this, SCIPropertyBag.getCPtr(scipropertybag), scipropertybag);
        SCIMusicServiceWizard scimusicservicewizard;
        if(l == 0L)
            scimusicservicewizard = null;
        else
            scimusicservicewizard = new SCIMusicServiceWizard(l, true);
        return scimusicservicewizard;
    }

    public SCIOnlineUpdateWizard createOnlineUpdateIntroOnlyWizard(SCIPropertyBag scipropertybag)
    {
        long l = sclibJNI.SCIHousehold_createOnlineUpdateIntroOnlyWizard(swigCPtr, this, SCIPropertyBag.getCPtr(scipropertybag), scipropertybag);
        SCIOnlineUpdateWizard scionlineupdatewizard;
        if(l == 0L)
            scionlineupdatewizard = null;
        else
            scionlineupdatewizard = new SCIOnlineUpdateWizard(l, true);
        return scionlineupdatewizard;
    }

    public SCIOnlineUpdateWizard createOnlineUpdateWizard(boolean flag)
    {
        long l = sclibJNI.SCIHousehold_createOnlineUpdateWizard(swigCPtr, this, flag);
        SCIOnlineUpdateWizard scionlineupdatewizard;
        if(l == 0L)
            scionlineupdatewizard = null;
        else
            scionlineupdatewizard = new SCIOnlineUpdateWizard(l, true);
        return scionlineupdatewizard;
    }

    public SCIOp createPauseOp()
    {
        long l = sclibJNI.SCIHousehold_createPauseOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIActionContext createPushSCUriAction(String s, String s1, boolean flag)
    {
        long l = sclibJNI.SCIHousehold_createPushSCUriAction(swigCPtr, this, s, s1, flag);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
    }

    public SCISoundbarWizard createRecalibrateAudioSoundbarWizard(String s)
    {
        long l = sclibJNI.SCIHousehold_createRecalibrateAudioSoundbarWizard(swigCPtr, this, s);
        SCISoundbarWizard scisoundbarwizard;
        if(l == 0L)
            scisoundbarwizard = null;
        else
            scisoundbarwizard = new SCISoundbarWizard(l, true);
        return scisoundbarwizard;
    }

    public SCISetupWizard createRecalibrateSubWizard(String s)
    {
        long l = sclibJNI.SCIHousehold_createRecalibrateSubWizard(swigCPtr, this, s);
        SCISetupWizard scisetupwizard;
        if(l == 0L)
            scisetupwizard = null;
        else
            scisetupwizard = new SCISetupWizard(l, true);
        return scisetupwizard;
    }

    public SCISetupWizard createRegistrationWizard()
    {
        long l = sclibJNI.SCIHousehold_createRegistrationWizard(swigCPtr, this);
        SCISetupWizard scisetupwizard;
        if(l == 0L)
            scisetupwizard = null;
        else
            scisetupwizard = new SCISetupWizard(l, true);
        return scisetupwizard;
    }

    public SCISoundbarWizard createRemoteSetupWizard(String s)
    {
        long l = sclibJNI.SCIHousehold_createRemoteSetupWizard(swigCPtr, this, s);
        SCISoundbarWizard scisoundbarwizard;
        if(l == 0L)
            scisoundbarwizard = null;
        else
            scisoundbarwizard = new SCISoundbarWizard(l, true);
        return scisoundbarwizard;
    }

    public SCIOnlineUpdateWizard createResumeUpdateWizard()
    {
        long l = sclibJNI.SCIHousehold_createResumeUpdateWizard(swigCPtr, this);
        SCIOnlineUpdateWizard scionlineupdatewizard;
        if(l == 0L)
            scionlineupdatewizard = null;
        else
            scionlineupdatewizard = new SCIOnlineUpdateWizard(l, true);
        return scionlineupdatewizard;
    }

    public SCIOp createSetPropertyOp(String s, String s1)
    {
        long l = sclibJNI.SCIHousehold_createSetPropertyOp(swigCPtr, this, s, s1);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIOp createSetUsageDataShareOptionOp(boolean flag)
    {
        long l = sclibJNI.SCIHousehold_createSetUsageDataShareOptionOp(swigCPtr, this, flag);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCISoundbarWizard createSetupSurroundSoundbarWizard(String s)
    {
        long l = sclibJNI.SCIHousehold_createSetupSurroundSoundbarWizard(swigCPtr, this, s);
        SCISoundbarWizard scisoundbarwizard;
        if(l == 0L)
            scisoundbarwizard = null;
        else
            scisoundbarwizard = new SCISoundbarWizard(l, true);
        return scisoundbarwizard;
    }

    public SCISetupWizard createShareUsageDataWizard()
    {
        long l = sclibJNI.SCIHousehold_createShareUsageDataWizard(swigCPtr, this);
        SCISetupWizard scisetupwizard;
        if(l == 0L)
            scisetupwizard = null;
        else
            scisetupwizard = new SCISetupWizard(l, true);
        return scisetupwizard;
    }

    public SCISonosNetSetupWizard createSonosNetSetupWizard()
    {
        long l = sclibJNI.SCIHousehold_createSonosNetSetupWizard(swigCPtr, this);
        SCISonosNetSetupWizard scisonosnetsetupwizard;
        if(l == 0L)
            scisonosnetsetupwizard = null;
        else
            scisonosnetsetupwizard = new SCISonosNetSetupWizard(l, true);
        return scisonosnetsetupwizard;
    }

    public SCISoundbarWizard createSoundbarWizard(String s)
    {
        long l = sclibJNI.SCIHousehold_createSoundbarWizard(swigCPtr, this, s);
        SCISoundbarWizard scisoundbarwizard;
        if(l == 0L)
            scisoundbarwizard = null;
        else
            scisoundbarwizard = new SCISoundbarWizard(l, true);
        return scisoundbarwizard;
    }

    public SCIOp createStopOp()
    {
        long l = sclibJNI.SCIHousehold_createStopOp(swigCPtr, this);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public SCIActionContext createSubmitDiagsWizardAction()
    {
        long l = sclibJNI.SCIHousehold_createSubmitDiagsWizardAction(swigCPtr, this);
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactioncontext;
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

    public SCISearchable getAggregatedSearchable()
    {
        long l = sclibJNI.SCIHousehold_getAggregatedSearchable(swigCPtr, this);
        SCISearchable scisearchable;
        if(l == 0L)
            scisearchable = null;
        else
            scisearchable = new SCISearchable(l, true);
        return scisearchable;
    }

    public SCIAlarmManager getAlarmManager()
    {
        long l = sclibJNI.SCIHousehold_getAlarmManager(swigCPtr, this);
        SCIAlarmManager scialarmmanager;
        if(l == 0L)
            scialarmmanager = null;
        else
            scialarmmanager = new SCIAlarmManager(l, true);
        return scialarmmanager;
    }

    public SCIEnumerator getAllSearchables()
    {
        long l = sclibJNI.SCIHousehold_getAllSearchables(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIDevice getAssociatedDevice()
    {
        long l = sclibJNI.SCIHousehold_getAssociatedDevice(swigCPtr, this);
        SCIDevice scidevice;
        if(l == 0L)
            scidevice = null;
        else
            scidevice = new SCIDevice(l, true);
        return scidevice;
    }

    public SCIBrowseListPresentationMap getBrowseListPresentationMap()
    {
        long l = sclibJNI.SCIHousehold_getBrowseListPresentationMap(swigCPtr, this);
        SCIBrowseListPresentationMap scibrowselistpresentationmap;
        if(l == 0L)
            scibrowselistpresentationmap = null;
        else
            scibrowselistpresentationmap = new SCIBrowseListPresentationMap(l, true);
        return scibrowselistpresentationmap;
    }

    public String getControllerUpdateURL()
    {
        return sclibJNI.SCIHousehold_getControllerUpdateURL(swigCPtr, this);
    }

    public SCIDevice getCurrentMasterDevice()
    {
        long l = sclibJNI.SCIHousehold_getCurrentMasterDevice(swigCPtr, this);
        SCIDevice scidevice;
        if(l == 0L)
            scidevice = null;
        else
            scidevice = new SCIDevice(l, true);
        return scidevice;
    }

    public SCIZoneGroup getCurrentZoneGroup()
    {
        long l = sclibJNI.SCIHousehold_getCurrentZoneGroup(swigCPtr, this);
        SCIZoneGroup scizonegroup;
        if(l == 0L)
            scizonegroup = null;
        else
            scizonegroup = new SCIZoneGroup(l, true);
        return scizonegroup;
    }

    public String getCustomerIDIfRegistered()
    {
        return sclibJNI.SCIHousehold_getCustomerIDIfRegistered(swigCPtr, this);
    }

    public SCIDateTimeManager getDateTimeManager()
    {
        long l = sclibJNI.SCIHousehold_getDateTimeManager(swigCPtr, this);
        SCIDateTimeManager scidatetimemanager;
        if(l == 0L)
            scidatetimemanager = null;
        else
            scidatetimemanager = new SCIDateTimeManager(l, true);
        return scidatetimemanager;
    }

    public SCIEnumerator getDevices(DevFilterOpt devfilteropt)
    {
        long l = sclibJNI.SCIHousehold_getDevices(swigCPtr, this, devfilteropt.swigValue());
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public String getID()
    {
        return sclibJNI.SCIHousehold_getID(swigCPtr, this);
    }

    public SCIIndexManager getIndexManager()
    {
        long l = sclibJNI.SCIHousehold_getIndexManager(swigCPtr, this);
        SCIIndexManager sciindexmanager;
        if(l == 0L)
            sciindexmanager = null;
        else
            sciindexmanager = new SCIIndexManager(l, true);
        return sciindexmanager;
    }

    public long getNumZoneGroups(ZGFilterOpt zgfilteropt)
    {
        return sclibJNI.SCIHousehold_getNumZoneGroups(swigCPtr, this, zgfilteropt.swigValue());
    }

    public SCIServiceDescriptorManager getServiceDescriptorManager()
    {
        long l = sclibJNI.SCIHousehold_getServiceDescriptorManager(swigCPtr, this);
        SCIServiceDescriptorManager sciservicedescriptormanager;
        if(l == 0L)
            sciservicedescriptormanager = null;
        else
            sciservicedescriptormanager = new SCIServiceDescriptorManager(l, true);
        return sciservicedescriptormanager;
    }

    public SCIShareManager getShareManager()
    {
        long l = sclibJNI.SCIHousehold_getShareManager(swigCPtr, this);
        SCIShareManager scisharemanager;
        if(l == 0L)
            scisharemanager = null;
        else
            scisharemanager = new SCIShareManager(l, true);
        return scisharemanager;
    }

    public SCIEnumerator getZoneGroups(ZGFilterOpt zgfilteropt)
    {
        long l = sclibJNI.SCIHousehold_getZoneGroups(swigCPtr, this, zgfilteropt.swigValue());
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public boolean hasTransientOrphanedZoneGroups()
    {
        return sclibJNI.SCIHousehold_hasTransientOrphanedZoneGroups(swigCPtr, this);
    }

    public boolean isConnectingToZPs()
    {
        return sclibJNI.SCIHousehold_isConnectingToZPs(swigCPtr, this);
    }

    public boolean isControllerUpdateAvailable()
    {
        return sclibJNI.SCIHousehold_isControllerUpdateAvailable(swigCPtr, this);
    }

    public boolean isValid()
    {
        return sclibJNI.SCIHousehold_isValid(swigCPtr, this);
    }

    public SCIDevice lookupDevice(String s)
    {
        long l = sclibJNI.SCIHousehold_lookupDevice(swigCPtr, this, s);
        SCIDevice scidevice;
        if(l == 0L)
            scidevice = null;
        else
            scidevice = new SCIDevice(l, true);
        return scidevice;
    }

    public SCISearchable lookupSearchableBySCUri(String s)
    {
        long l = sclibJNI.SCIHousehold_lookupSearchableBySCUri(swigCPtr, this, s);
        SCISearchable scisearchable;
        if(l == 0L)
            scisearchable = null;
        else
            scisearchable = new SCISearchable(l, true);
        return scisearchable;
    }

    public SCIZoneGroup lookupZoneGroup(String s)
    {
        long l = sclibJNI.SCIHousehold_lookupZoneGroup(swigCPtr, this, s);
        SCIZoneGroup scizonegroup;
        if(l == 0L)
            scizonegroup = null;
        else
            scizonegroup = new SCIZoneGroup(l, true);
        return scizonegroup;
    }

    public void saveCurrentZoneGroup()
    {
        sclibJNI.SCIHousehold_saveCurrentZoneGroup(swigCPtr, this);
    }

    public void setCurrentZoneGroup(String s)
    {
        sclibJNI.SCIHousehold_setCurrentZoneGroup(swigCPtr, this, s);
    }

    public void setTopSearchable(SCISearchable scisearchable)
    {
        sclibJNI.SCIHousehold_setTopSearchable(swigCPtr, this, SCISearchable.getCPtr(scisearchable), scisearchable);
    }

    public void setTopSearchableBySCUri(String s)
    {
        sclibJNI.SCIHousehold_setTopSearchableBySCUri(swigCPtr, this, s);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIHousehold_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIHousehold_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void updateAvailableServices()
    {
        sclibJNI.SCIHousehold_updateAvailableServices(swigCPtr, this);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIHousehold");
    private long swigCPtr;

}

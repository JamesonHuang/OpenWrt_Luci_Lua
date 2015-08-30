// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibConstants, SCIPropertyBag, sclibJNI, SCFixedSCUriID, 
//            SCFixedSCUriCategory, SCIStringArray, SCLibParameters, SCILibrary, 
//            SCIActionFilter, SCIActionFilterer, SCIOpNetstartGetScanList, SCIWebBridgeDelegate, 
//            SCIHousehold, SCIWebBridge, SCIWebMessage, SCIIntArray, 
//            SCIOp, SCIRecurrence, SCISystemTime, SCITime, 
//            SCServiceAccountFilterID, SCIServiceAccountFilter, SCServiceDescriptorFilterID, SCIServiceDescriptorFilter, 
//            SCIStringInput, SCIStringTemplate, SCIAppReporting, ResURIDataType

public class sclib
    implements sclibConstants
{

    public sclib()
    {
    }

    public static String SCLibConvertPropertyBagToJson(SCIPropertyBag scipropertybag, boolean flag)
    {
        return sclibJNI.SCLibConvertPropertyBagToJson(SCIPropertyBag.getCPtr(scipropertybag), scipropertybag, flag);
    }

    public static String SCLibGetBrowseCPIDFromSCUri(String s)
    {
        return sclibJNI.SCLibGetBrowseCPIDFromSCUri(s);
    }

    public static String SCLibGetCanonicalSearchCategory(String s)
    {
        return sclibJNI.SCLibGetCanonicalSearchCategory(s);
    }

    public static String SCLibGetDeviceIDFromDeviceSettingsSCUri(String s)
    {
        return sclibJNI.SCLibGetDeviceIDFromDeviceSettingsSCUri(s);
    }

    public static String SCLibGetFixedSCUri(SCFixedSCUriID scfixedscuriid)
    {
        return sclibJNI.SCLibGetFixedSCUri(scfixedscuriid.swigValue());
    }

    public static String SCLibGetFixedSCUriTitle(SCFixedSCUriID scfixedscuriid)
    {
        return sclibJNI.SCLibGetFixedSCUriTitle(scfixedscuriid.swigValue());
    }

    public static String SCLibGetMobileDeviceTrackIDFromSCUri(String s)
    {
        return sclibJNI.SCLibGetMobileDeviceTrackIDFromSCUri(s);
    }

    public static String SCLibGetServiceDescriptorIDFromSCUri(String s)
    {
        return sclibJNI.SCLibGetServiceDescriptorIDFromSCUri(s);
    }

    public static String SCLibGetSettingSCUri(SCFixedSCUriCategory scfixedscuricategory, String s)
    {
        return sclibJNI.SCLibGetSettingSCUri(scfixedscuricategory.swigValue(), s);
    }

    public static String SCLibGetStringFromModelType(SCIDevice.DeviceModel devicemodel)
    {
        return sclibJNI.SCLibGetStringFromModelType(devicemodel.swigValue());
    }

    public static SCIStringArray SCLibGetSupportedLanguageIDs()
    {
        long l = sclibJNI.SCLibGetSupportedLanguageIDs();
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return scistringarray;
    }

    public static SCILibrary SCLibInit(SCLibParameters sclibparameters)
    {
        long l = sclibJNI.SCLibInit(SCLibParameters.getCPtr(sclibparameters), sclibparameters);
        SCILibrary scilibrary;
        if(l == 0L)
            scilibrary = null;
        else
            scilibrary = new SCILibrary(l, true);
        return scilibrary;
    }

    public static boolean SCLibIsInfoViewSCUri(String s)
    {
        return sclibJNI.SCLibIsInfoViewSCUri(s);
    }

    public static boolean SCLibIsMobileDeviceSCUri(String s)
    {
        return sclibJNI.SCLibIsMobileDeviceSCUri(s);
    }

    public static boolean SCLibIsSearchSCUri(String s)
    {
        return sclibJNI.SCLibIsSearchSCUri(s);
    }

    public static boolean SCLibIsValidMACAddress(String s)
    {
        return sclibJNI.SCLibIsValidMACAddress(s);
    }

    public static boolean SCLibMatchesFixedSCUri(String s, SCFixedSCUriID scfixedscuriid, boolean flag)
    {
        return sclibJNI.SCLibMatchesFixedSCUri(s, scfixedscuriid.swigValue(), flag);
    }

    public static boolean SCLibMatchesFixedSCUriCategory(String s, SCFixedSCUriCategory scfixedscuricategory)
    {
        return sclibJNI.SCLibMatchesFixedSCUriCategory(s, scfixedscuricategory.swigValue());
    }

    public static void SCLibTerm(SCILibrary scilibrary)
    {
        sclibJNI.SCLibTerm(SCILibrary.getCPtr(scilibrary), scilibrary);
    }

    public static SCIActionFilter createDefaultSCIActionFilter(String s)
    {
        long l = sclibJNI.createDefaultSCIActionFilter(s);
        SCIActionFilter sciactionfilter;
        if(l == 0L)
            sciactionfilter = null;
        else
            sciactionfilter = new SCIActionFilter(l, true);
        return sciactionfilter;
    }

    public static SCIPropertyBag createPropertyBag()
    {
        long l = sclibJNI.createPropertyBag();
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        return scipropertybag;
    }

    public static SCIActionFilterer createSCActionFilterer()
    {
        long l = sclibJNI.createSCActionFilterer();
        SCIActionFilterer sciactionfilterer;
        if(l == 0L)
            sciactionfilterer = null;
        else
            sciactionfilterer = new SCIActionFilterer(l, true);
        return sciactionfilterer;
    }

    public static SCIOpNetstartGetScanList createSCINetstartGetScanListOp(String s)
    {
        long l = sclibJNI.createSCINetstartGetScanListOp(s);
        SCIOpNetstartGetScanList sciopnetstartgetscanlist;
        if(l == 0L)
            sciopnetstartgetscanlist = null;
        else
            sciopnetstartgetscanlist = new SCIOpNetstartGetScanList(l, true);
        return sciopnetstartgetscanlist;
    }

    public static SCIWebBridge createSCIWebBridge(SCIWebBridgeDelegate sciwebbridgedelegate, SCIHousehold scihousehold)
    {
        long l = sclibJNI.createSCIWebBridge(SCIWebBridgeDelegate.getCPtr(sciwebbridgedelegate), sciwebbridgedelegate, SCIHousehold.getCPtr(scihousehold), scihousehold);
        SCIWebBridge sciwebbridge;
        if(l == 0L)
            sciwebbridge = null;
        else
            sciwebbridge = new SCIWebBridge(l, true);
        return sciwebbridge;
    }

    public static SCIWebMessage createSCIWebMessage(String s, String s1)
    {
        long l = sclibJNI.createSCIWebMessage(s, s1);
        SCIWebMessage sciwebmessage;
        if(l == 0L)
            sciwebmessage = null;
        else
            sciwebmessage = new SCIWebMessage(l, true);
        return sciwebmessage;
    }

    public static SCIIntArray createSCIntArray()
    {
        long l = sclibJNI.createSCIntArray();
        SCIIntArray sciintarray;
        if(l == 0L)
            sciintarray = null;
        else
            sciintarray = new SCIIntArray(l, true);
        return sciintarray;
    }

    public static SCIOp createSCNullAsyncOperation(int i)
    {
        long l = sclibJNI.createSCNullAsyncOperation(i);
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return sciop;
    }

    public static SCIRecurrence createSCRecurrence()
    {
        long l = sclibJNI.createSCRecurrence();
        SCIRecurrence scirecurrence;
        if(l == 0L)
            scirecurrence = null;
        else
            scirecurrence = new SCIRecurrence(l, true);
        return scirecurrence;
    }

    public static SCIStringArray createSCStringArray()
    {
        long l = sclibJNI.createSCStringArray();
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return scistringarray;
    }

    public static SCISystemTime createSCSystemTime()
    {
        long l = sclibJNI.createSCSystemTime();
        SCISystemTime scisystemtime;
        if(l == 0L)
            scisystemtime = null;
        else
            scisystemtime = new SCISystemTime(l, true);
        return scisystemtime;
    }

    public static SCITime createSCTime()
    {
        long l = sclibJNI.createSCTime();
        SCITime scitime;
        if(l == 0L)
            scitime = null;
        else
            scitime = new SCITime(l, true);
        return scitime;
    }

    public static SCIStringArray createSCUriArray()
    {
        long l = sclibJNI.createSCUriArray();
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return scistringarray;
    }

    public static SCIServiceAccountFilter createServiceAccountFilter(SCServiceAccountFilterID scserviceaccountfilterid)
    {
        long l = sclibJNI.createServiceAccountFilter(scserviceaccountfilterid.swigValue());
        SCIServiceAccountFilter sciserviceaccountfilter;
        if(l == 0L)
            sciserviceaccountfilter = null;
        else
            sciserviceaccountfilter = new SCIServiceAccountFilter(l, true);
        return sciserviceaccountfilter;
    }

    public static SCIServiceAccountFilter createServiceAccountsByServiceFilter(String s)
    {
        long l = sclibJNI.createServiceAccountsByServiceFilter(s);
        SCIServiceAccountFilter sciserviceaccountfilter;
        if(l == 0L)
            sciserviceaccountfilter = null;
        else
            sciserviceaccountfilter = new SCIServiceAccountFilter(l, true);
        return sciserviceaccountfilter;
    }

    public static SCIServiceDescriptorFilter createServiceDescriptorFilter(SCServiceDescriptorFilterID scservicedescriptorfilterid)
    {
        long l = sclibJNI.createServiceDescriptorFilter(scservicedescriptorfilterid.swigValue());
        SCIServiceDescriptorFilter sciservicedescriptorfilter;
        if(l == 0L)
            sciservicedescriptorfilter = null;
        else
            sciservicedescriptorfilter = new SCIServiceDescriptorFilter(l, true);
        return sciservicedescriptorfilter;
    }

    public static SCIStringInput createShareNameInput()
    {
        long l = sclibJNI.createShareNameInput();
        SCIStringInput scistringinput;
        if(l == 0L)
            scistringinput = null;
        else
            scistringinput = new SCIStringInput(l, true);
        return scistringinput;
    }

    public static SCIStringTemplate createStringTemplate(String s)
    {
        long l = sclibJNI.createStringTemplate(s);
        SCIStringTemplate scistringtemplate;
        if(l == 0L)
            scistringtemplate = null;
        else
            scistringtemplate = new SCIStringTemplate(l, true);
        return scistringtemplate;
    }

    public static SCIAppReporting getAppReportingInstance()
    {
        long l = sclibJNI.getAppReportingInstance();
        SCIAppReporting sciappreporting;
        if(l == 0L)
            sciappreporting = null;
        else
            sciappreporting = new SCIAppReporting(l, true);
        return sciappreporting;
    }

    public static int getPortForMusicServer()
    {
        return sclibJNI.getPortForMusicServer();
    }

    public static String getStaticResUriForResourceId(ResURIDataType resuridatatype, String s, String s1, String s2)
    {
        return sclibJNI.getStaticResUriForResourceId(resuridatatype.swigValue(), s, s1, s2);
    }

    public static String getVirtualResUriForResourceId(ResURIDataType resuridatatype, String s, String s1)
    {
        return sclibJNI.getVirtualResUriForResourceId__SWIG_0(resuridatatype.swigValue(), s, s1);
    }

    public static String getVirtualResUriForResourceId(ResURIDataType resuridatatype, String s, String s1, boolean flag)
    {
        return sclibJNI.getVirtualResUriForResourceId__SWIG_1(resuridatatype.swigValue(), s, s1, flag);
    }
}

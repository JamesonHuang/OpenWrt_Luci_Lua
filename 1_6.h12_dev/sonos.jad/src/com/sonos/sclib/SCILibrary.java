// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIEventSink, SCIBrowseItem, 
//            SCIBrowseDataSource, SCIStringArray, SCIEnumerator, SCISetupWizard, 
//            SCIMusicServerDelegate, SCIBrowseManager, SCRoomID, SCIHousehold, 
//            SCIMusicServer, SCINetworkManagementDelegate, SCIVersion, SCILocationServices, 
//            SCIActionFactory, SWIGTYPE_p_RTimedJobManager, SCIStackTraceCaptureDelegate

public class SCILibrary extends SCIObj
{

    protected SCILibrary(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCILibraryUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCILibrary(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCILibrary(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCILibrary scilibrary)
    {
        long l;
        if(scilibrary == null)
            l = 0L;
        else
            l = scilibrary.swigCPtr;
        return l;
    }

    public void SCLibUIThreadCallback()
    {
        sclibJNI.SCILibrary_SCLibUIThreadCallback(swigCPtr, this);
    }

    public void addToEventSinkCache(SCIEventSink scieventsink)
    {
        sclibJNI.SCILibrary_addToEventSinkCache(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public SCIBrowseDataSource createBrowseDataSource(SCIBrowseItem scibrowseitem)
    {
        long l = sclibJNI.SCILibrary_createBrowseDataSource__SWIG_2(swigCPtr, this, SCIBrowseItem.getCPtr(scibrowseitem), scibrowseitem);
        SCIBrowseDataSource scibrowsedatasource;
        if(l == 0L)
            scibrowsedatasource = null;
        else
            scibrowsedatasource = new SCIBrowseDataSource(l, true);
        return scibrowsedatasource;
    }

    public SCIBrowseDataSource createBrowseDataSource(String s, String s1)
    {
        long l = sclibJNI.SCILibrary_createBrowseDataSource__SWIG_0(swigCPtr, this, s, s1);
        SCIBrowseDataSource scibrowsedatasource;
        if(l == 0L)
            scibrowsedatasource = null;
        else
            scibrowsedatasource = new SCIBrowseDataSource(l, true);
        return scibrowsedatasource;
    }

    public SCIBrowseDataSource createBrowseDataSource(String s, String s1, SCIStringArray scistringarray)
    {
        long l = sclibJNI.SCILibrary_createBrowseDataSource__SWIG_1(swigCPtr, this, s, s1, SCIStringArray.getCPtr(scistringarray), scistringarray);
        SCIBrowseDataSource scibrowsedatasource;
        if(l == 0L)
            scibrowsedatasource = null;
        else
            scibrowsedatasource = new SCIBrowseDataSource(l, true);
        return scibrowsedatasource;
    }

    public SCIEnumerator createCustomUIActionEnumerator(String s, String s1)
    {
        long l = sclibJNI.SCILibrary_createCustomUIActionEnumerator(swigCPtr, this, s, s1);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCISetupWizard createJoinAnotherHouseholdWizard()
    {
        long l = sclibJNI.SCILibrary_createJoinAnotherHouseholdWizard(swigCPtr, this);
        SCISetupWizard scisetupwizard;
        if(l == 0L)
            scisetupwizard = null;
        else
            scisetupwizard = new SCISetupWizard(l, true);
        return scisetupwizard;
    }

    public void createMusicServer(SCIMusicServerDelegate scimusicserverdelegate)
    {
        sclibJNI.SCILibrary_createMusicServer(swigCPtr, this, SCIMusicServerDelegate.getCPtr(scimusicserverdelegate), scimusicserverdelegate);
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

    public SCIEnumerator getAudioInputResources()
    {
        long l = sclibJNI.SCILibrary_getAudioInputResources(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIBrowseManager getBrowseManager()
    {
        long l = sclibJNI.SCILibrary_getBrowseManager(swigCPtr, this);
        SCIBrowseManager scibrowsemanager;
        if(l == 0L)
            scibrowsemanager = null;
        else
            scibrowsemanager = new SCIBrowseManager(l, true);
        return scibrowsemanager;
    }

    public String getClientOSVersion()
    {
        return sclibJNI.SCILibrary_getClientOSVersion(swigCPtr, this);
    }

    public SCIEnumerator getCountryList()
    {
        long l = sclibJNI.SCILibrary_getCountryList(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCRoomID getDefaultRoomIDForDeviceNaming()
    {
        return SCRoomID.swigToEnum(sclibJNI.SCILibrary_getDefaultRoomIDForDeviceNaming(swigCPtr, this));
    }

    public SCIEnumerator getDockResources()
    {
        long l = sclibJNI.SCILibrary_getDockResources(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIEnumerator getEventSinkCache()
    {
        long l = sclibJNI.SCILibrary_getEventSinkCache(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public String getHTSourceTypeText(int i)
    {
        return sclibJNI.SCILibrary_getHTSourceTypeText(swigCPtr, this, i);
    }

    public String getHostDeviceID()
    {
        return sclibJNI.SCILibrary_getHostDeviceID(swigCPtr, this);
    }

    public String getHostMACAddress()
    {
        return sclibJNI.SCILibrary_getHostMACAddress(swigCPtr, this);
    }

    public String getHostModel()
    {
        return sclibJNI.SCILibrary_getHostModel(swigCPtr, this);
    }

    public SCIHousehold getHousehold()
    {
        long l = sclibJNI.SCILibrary_getHousehold(swigCPtr, this);
        SCIHousehold scihousehold;
        if(l == 0L)
            scihousehold = null;
        else
            scihousehold = new SCIHousehold(l, true);
        return scihousehold;
    }

    public String getMarketingVersion()
    {
        return sclibJNI.SCILibrary_getMarketingVersion(swigCPtr, this);
    }

    public SCIMusicServer getMusicServer()
    {
        long l = sclibJNI.SCILibrary_getMusicServer(swigCPtr, this);
        SCIMusicServer scimusicserver;
        if(l == 0L)
            scimusicserver = null;
        else
            scimusicserver = new SCIMusicServer(l, true);
        return scimusicserver;
    }

    public SCINetworkManagementDelegate getNetworkManagementDelegate()
    {
        long l = sclibJNI.SCILibrary_getNetworkManagementDelegate(swigCPtr, this);
        SCINetworkManagementDelegate scinetworkmanagementdelegate;
        if(l == 0L)
            scinetworkmanagementdelegate = null;
        else
            scinetworkmanagementdelegate = new SCINetworkManagementDelegate(l, true);
        return scinetworkmanagementdelegate;
    }

    public String getRecommendedText(int i)
    {
        return sclibJNI.SCILibrary_getRecommendedText(swigCPtr, this, i);
    }

    public String getRecommendedURL(int i)
    {
        return sclibJNI.SCILibrary_getRecommendedURL(swigCPtr, this, i);
    }

    public SCIEnumerator getRoomResources()
    {
        long l = sclibJNI.SCILibrary_getRoomResources(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIEnumerator getRoomResourcesForDeviceNaming(String s)
    {
        long l = sclibJNI.SCILibrary_getRoomResourcesForDeviceNaming(swigCPtr, this, s);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIVersion getSCLIBVersion()
    {
        long l = sclibJNI.SCILibrary_getSCLIBVersion(swigCPtr, this);
        SCIVersion sciversion;
        if(l == 0L)
            sciversion = null;
        else
            sciversion = new SCIVersion(l, true);
        return sciversion;
    }

    public SCIVersion getSoftwareVersion()
    {
        long l = sclibJNI.SCILibrary_getSoftwareVersion(swigCPtr, this);
        SCIVersion sciversion;
        if(l == 0L)
            sciversion = null;
        else
            sciversion = new SCIVersion(l, true);
        return sciversion;
    }

    public int getSonosPlaylistMaxUTF8Length()
    {
        return sclibJNI.SCILibrary_getSonosPlaylistMaxUTF8Length(swigCPtr, this);
    }

    public SCIEnumerator getZoneBridgeResources()
    {
        long l = sclibJNI.SCILibrary_getZoneBridgeResources(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public SCIEnumerator getZoneExtenderResources()
    {
        long l = sclibJNI.SCILibrary_getZoneExtenderResources(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public void locationServicesStatusChanged()
    {
        sclibJNI.SCILibrary_locationServicesStatusChanged(swigCPtr, this);
    }

    public SCIObj queryInterface(String s)
    {
        return sclibJNI.SCILibrary_queryInterface(swigCPtr, this, s);
    }

    public void registerLocationServices(SCILocationServices scilocationservices)
    {
        sclibJNI.SCILibrary_registerLocationServices(swigCPtr, this, SCILocationServices.getCPtr(scilocationservices), scilocationservices);
    }

    public void registerMobileDevice(String s, String s1)
    {
        sclibJNI.SCILibrary_registerMobileDevice(swigCPtr, this, s, s1);
    }

    public void removeFromEventSinkCache(SCIEventSink scieventsink)
    {
        sclibJNI.SCILibrary_removeFromEventSinkCache(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public void setActionFactory(SCIActionFactory sciactionfactory)
    {
        sclibJNI.SCILibrary_setActionFactory(swigCPtr, this, SCIActionFactory.getCPtr(sciactionfactory), sciactionfactory);
    }

    public void setCR200LanguageID(String s)
    {
        sclibJNI.SCILibrary_setCR200LanguageID(swigCPtr, this, s);
    }

    public void setCR200TimedJobManager(SWIGTYPE_p_RTimedJobManager swigtype_p_rtimedjobmanager)
    {
        sclibJNI.SCILibrary_setCR200TimedJobManager(swigCPtr, this, SWIGTYPE_p_RTimedJobManager.getCPtr(swigtype_p_rtimedjobmanager));
    }

    public void setMusicServer(SCIMusicServer scimusicserver)
    {
        sclibJNI.SCILibrary_setMusicServer(swigCPtr, this, SCIMusicServer.getCPtr(scimusicserver), scimusicserver);
    }

    public void setNetworkManagementDelegate(SCINetworkManagementDelegate scinetworkmanagementdelegate)
    {
        sclibJNI.SCILibrary_setNetworkManagementDelegate(swigCPtr, this, SCINetworkManagementDelegate.getCPtr(scinetworkmanagementdelegate), scinetworkmanagementdelegate);
    }

    public void setStackTraceCaptureCallback(SCIStackTraceCaptureDelegate scistacktracecapturedelegate)
    {
        sclibJNI.SCILibrary_setStackTraceCaptureCallback(swigCPtr, this, SCIStackTraceCaptureDelegate.getCPtr(scistacktracecapturedelegate), scistacktracecapturedelegate);
    }

    public void setSupportEnabled(boolean flag)
    {
        sclibJNI.SCILibrary_setSupportEnabled(swigCPtr, this, flag);
    }

    public static final int SC_HTSI_DOLBY_DIGITAL_DUAL_MONO = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_DUAL_MONO_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_DUAL_MONO_WITH_LFE = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_DUAL_MONO_WITH_LFE_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_LCR = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_LCR_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_LCR_WITH_LFE = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_LCR_WITH_LFE_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_LCR_WITH_MONO_SURROUND = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_LCR_WITH_MONO_SURROUND_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_LCR_WITH_MONO_SURROUND_AND_LFE = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_LCR_WITH_MONO_SURROUND_AND_LFE_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_LCR_WITH_STEREO_SURROUND = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_LCR_WITH_STEREO_SURROUND_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_LCR_WITH_STEREO_SURROUND_AND_LFE = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_LCR_WITH_STEREO_SURROUND_AND_LFE_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_MONO = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_MONO_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_MONO_WITH_LFE = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_MONO_WITH_LFE_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_STEREO = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_STEREO_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_LFE = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_LFE_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_MONO_SURROUND = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_MONO_SURROUND_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_MONO_SURROUND_AND_LFE = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_MONO_SURROUND_AND_LFE_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_STEREO_SURROUND = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_STEREO_SURROUND_get();
    public static final int SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_STEREO_SURROUND_AND_LFE = sclibJNI.SCILibrary_SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_STEREO_SURROUND_AND_LFE_get();
    public static final int SC_HTSI_NO_SIGNAL = sclibJNI.SCILibrary_SC_HTSI_NO_SIGNAL_get();
    public static final int SC_HTSI_NULL_BURST = sclibJNI.SCILibrary_SC_HTSI_NULL_BURST_get();
    public static final int SC_HTSI_PAUSE_BURST = sclibJNI.SCILibrary_SC_HTSI_PAUSE_BURST_get();
    public static final int SC_HTSI_PCM = sclibJNI.SCILibrary_SC_HTSI_PCM_get();
    public static final int SC_HTSI_SILENCE = sclibJNI.SCILibrary_SC_HTSI_SILENCE_get();
    public static final int SC_HTSI_UNAVAILABLE = sclibJNI.SCILibrary_SC_HTSI_UNAVAILABLE_get();
    public static final int SC_HTSI_UNKNOWN = sclibJNI.SCILibrary_SC_HTSI_UNKNOWN_get();
    public static final int SC_STRID_ALARMMUSIC = sclibJNI.SCILibrary_SC_STRID_ALARMMUSIC_get();
    public static final int SC_STRID_HDR_IMPROVE_SONOS = sclibJNI.SCILibrary_SC_STRID_HDR_IMPROVE_SONOS_get();
    public static final int SC_STRID_NS_NFW_OPEN_SSID = sclibJNI.SCILibrary_SC_STRID_NS_NFW_OPEN_SSID_get();
    public static final int SC_STRID_PLAYBAR_INFORMATION = sclibJNI.SCILibrary_SC_STRID_PLAYBAR_INFORMATION_get();
    public static final int SC_STRID_PLAYBAR_INFO_TITLE = sclibJNI.SCILibrary_SC_STRID_PLAYBAR_INFO_TITLE_get();
    public static final int SC_STRID_PLAYBAR_TOOLTIP_NIGHTSOUND = sclibJNI.SCILibrary_SC_STRID_PLAYBAR_TOOLTIP_NIGHTSOUND_get();
    public static final int SC_STRID_PLAYBAR_TOOLTIP_SPEECHENCHANCEMENT = sclibJNI.SCILibrary_SC_STRID_PLAYBAR_TOOLTIP_SPEECHENCHANCEMENT_get();
    public static final int SC_STRID_RESETCONTROLLER_INSTRUCTIONS = sclibJNI.SCILibrary_SC_STRID_RESETCONTROLLER_INSTRUCTIONS_get();
    public static final int SC_STRID_RESETCONTROLLER_PROMPT = sclibJNI.SCILibrary_SC_STRID_RESETCONTROLLER_PROMPT_get();
    public static final int SC_STRID_SETUPWIZ_USAGEDATAOPTIN_BODY_1 = sclibJNI.SCILibrary_SC_STRID_SETUPWIZ_USAGEDATAOPTIN_BODY_1_get();
    public static final int SC_STRID_SETUPWIZ_USAGEDATAOPTIN_BODY_2_OPTINURL_FMT = sclibJNI.SCILibrary_SC_STRID_SETUPWIZ_USAGEDATAOPTIN_BODY_2_OPTINURL_FMT_get();
    public static final int SC_STRID_SETUPWIZ_USAGEDATAOPTIN_INPUT_1 = sclibJNI.SCILibrary_SC_STRID_SETUPWIZ_USAGEDATAOPTIN_INPUT_1_get();
    public static final int SC_STRID_SETUPWIZ_USAGEDATAOPTIN_TITLE = sclibJNI.SCILibrary_SC_STRID_SETUPWIZ_USAGEDATAOPTIN_TITLE_get();
    public static final int SC_STRID_TOOLTIP_CROSSFADE = sclibJNI.SCILibrary_SC_STRID_TOOLTIP_CROSSFADE_get();
    public static final int SC_STRID_TOOLTIP_INFOVIEW = sclibJNI.SCILibrary_SC_STRID_TOOLTIP_INFOVIEW_get();
    public static final int SC_STRID_TOOLTIP_NEXTTRACK = sclibJNI.SCILibrary_SC_STRID_TOOLTIP_NEXTTRACK_get();
    public static final int SC_STRID_TOOLTIP_PLAYPAUSE = sclibJNI.SCILibrary_SC_STRID_TOOLTIP_PLAYPAUSE_get();
    public static final int SC_STRID_TOOLTIP_PREVTRACK = sclibJNI.SCILibrary_SC_STRID_TOOLTIP_PREVTRACK_get();
    public static final int SC_STRID_TOOLTIP_REPEAT = sclibJNI.SCILibrary_SC_STRID_TOOLTIP_REPEAT_get();
    public static final int SC_STRID_TOOLTIP_SEEKTRACKPOSITION = sclibJNI.SCILibrary_SC_STRID_TOOLTIP_SEEKTRACKPOSITION_get();
    public static final int SC_STRID_TOOLTIP_SHUFFLE = sclibJNI.SCILibrary_SC_STRID_TOOLTIP_SHUFFLE_get();
    public static final int SC_URL_GETMUSIC = sclibJNI.SCILibrary_SC_URL_GETMUSIC_get();
    public static final int SC_URL_HELP_AUTOIP = sclibJNI.SCILibrary_SC_URL_HELP_AUTOIP_get();
    public static final int SC_URL_HELP_FACTORY_RESET = sclibJNI.SCILibrary_SC_URL_HELP_FACTORY_RESET_get();
    public static final int SC_URL_HELP_FIREWALL = sclibJNI.SCILibrary_SC_URL_HELP_FIREWALL_get();
    public static final int SC_URL_HELP_SEARCHING = sclibJNI.SCILibrary_SC_URL_HELP_SEARCHING_get();
    public static final int SC_URL_HELP_SETUP = sclibJNI.SCILibrary_SC_URL_HELP_SETUP_get();
    public static final int SC_URL_HELP_SONOSNET = sclibJNI.SCILibrary_SC_URL_HELP_SONOSNET_get();
    public static final int SC_URL_HELP_WMPSHARING = sclibJNI.SCILibrary_SC_URL_HELP_WMPSHARING_get();
    public static final int SC_URL_PLAYBAR_INFORMATION = sclibJNI.SCILibrary_SC_URL_PLAYBAR_INFORMATION_get();
    public static final int SC_URL_PRIVACY = sclibJNI.SCILibrary_SC_URL_PRIVACY_get();
    public static final int SC_URL_SHOP = sclibJNI.SCILibrary_SC_URL_SHOP_get();
    public static final int SC_URL_SONOS_ACCOUNT = sclibJNI.SCILibrary_SC_URL_SONOS_ACCOUNT_get();
    public static final int SC_URL_SONOS_DEMO = sclibJNI.SCILibrary_SC_URL_SONOS_DEMO_get();
    public static final int SC_URL_SUPPORT = sclibJNI.SCILibrary_SC_URL_SUPPORT_get();
    public static final int SC_URL_SUPPORT_VER_LANG = sclibJNI.SCILibrary_SC_URL_SUPPORT_VER_LANG_get();
    public static final int SC_URL_USAGE = sclibJNI.SCILibrary_SC_URL_USAGE_get();
    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCILibrary");
    private long swigCPtr;

}

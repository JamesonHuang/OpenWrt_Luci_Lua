// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib.wrappers;

import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.StringUtils;
import com.sonos.sclib.*;
import java.util.*;

// Referenced classes of package com.sonos.acr.sclib.wrappers:
//            ZoneGroup, ZoneDevice

public class Household
{
    public static interface HouseholdChangedListener
    {

        public abstract void onHouseholdChanged();
    }


    public Household(SCIHousehold scihousehold)
    {
        searchables = new ArrayList();
        listeners = new ArrayList();
        sciHousehold = scihousehold;
    }

    public SCISetupWizard createAddBoostWizard()
    {
        return sciHousehold.createAddBoostWizard();
    }

    public SCIActionContext createAddCustomRadioStationAction()
    {
        return sciHousehold.createAddCustomRadioStationAction();
    }

    public SCISetupWizard createAddPlayerOrSubWizard()
    {
        return sciHousehold.createAddPlayerOrSubWizard();
    }

    public SCISoundbarWizard createAddSubSoundbarWizard(String s)
    {
        return sciHousehold.createAddSubSoundbarWizard(s);
    }

    public SCIBrowseStackManager createBrowseStackWithRoot(String s)
    {
        return sciHousehold.createBrowseStackWithRoot(s);
    }

    public SCIBrowseStackManager createBrowseStackWithRoot(String s, String s1)
    {
        return sciHousehold.createBrowseStackWithRoot(s, s1);
    }

    public SCISetupWizard createChangeSpeakersWizard(String s)
    {
        return sciHousehold.createChangeSpeakersWizard(s);
    }

    public SCISetupWizard createChooseConfigurePage()
    {
        return sciHousehold.createChooseConfigureWizard();
    }

    public SCISetupWizard createChooseConfigureWizard()
    {
        return sciHousehold.createChooseConfigureWizard();
    }

    public SCISetupWizard createConfigureSonosComponentWizard(String s)
    {
        return sciHousehold.createConfigureSonosComponentWizard(s);
    }

    public SCISetupWizard createConfigureSubWizard(String s)
    {
        return sciHousehold.createConfigureSubWizard(s);
    }

    public SCIConfigureWirelessWizard createConfigureWirelessWizard()
    {
        return sciHousehold.createConfigureWirelessWizard();
    }

    public SCIActionContext createFactoryResetAction()
    {
        return sciHousehold.createFactoryResetAction();
    }

    public SCIActionContext createForgetHouseholdAction()
    {
        return sciHousehold.createForgetHouseholdAction();
    }

    public SCIOpGetAboutSonosString createGetAboutSonosStrOp()
    {
        return sciHousehold.createGetAboutSonosStrOp();
    }

    public SCIOpSystemPropertyGetString createGetPropertyOp(String s)
    {
        return sciHousehold.createGetPropertyOp(s);
    }

    public SCIOpGetAboutSonosString createGetShortAboutSonosStrOp()
    {
        return sciHousehold.createGetShortAboutSonosStrOp();
    }

    public SCIOpGetUsageDataShareOption createGetUsageDataShareOptionOp()
    {
        return sciHousehold.createGetUsageDataShareOptionOp();
    }

    public SCIOpZoneGroupTopologyGetZoneGroupState createGetZoneGroupStateOp(String s)
    {
        return sciHousehold.createGetZoneGroupStateOp(s);
    }

    public SCISetupWizard createMusicLibrarySetupWizard()
    {
        return sciHousehold.createMusicLibrarySetupWizard();
    }

    public SCIMusicServiceWizard createMusicServiceAddAccountWizard()
    {
        return sciHousehold.createMusicServiceAddAccountWizard();
    }

    public SCIMusicServiceWizard createMusicServiceAddSonosLabsAccountWizard()
    {
        return sciHousehold.createMusicServiceAddSonosLabsAccountWizard();
    }

    public SCIMusicServiceWizard createMusicServiceChangePasswordWizard(String s)
    {
        return sciHousehold.createMusicServiceChangePasswordWizard(s);
    }

    public SCIMusicServiceWizard createMusicServiceReauthorizeAccountWizard(String s)
    {
        return sciHousehold.createMusicServiceReauthorizeAccountWizard(s);
    }

    public SCIMusicServiceWizard createMusicServiceReplaceAccountWizard(String s)
    {
        return sciHousehold.createMusicServiceReplaceAccountWizard(s);
    }

    public SCIMusicServiceWizard createMusicServiceSubscribeWizard(String s)
    {
        return sciHousehold.createMusicServiceSubscribeWizard(s);
    }

    public SCIMusicServiceWizard createMusicServiceWizard(SCIPropertyBag scipropertybag)
    {
        return sciHousehold.createMusicServiceWizard(scipropertybag);
    }

    public SCIOnlineUpdateWizard createOnlineUpdateIntroOnlyWizard(SCIPropertyBag scipropertybag)
    {
        return sciHousehold.createOnlineUpdateIntroOnlyWizard(scipropertybag);
    }

    public SCIOnlineUpdateWizard createOnlineUpdateWizard(boolean flag)
    {
        return sciHousehold.createOnlineUpdateWizard(flag);
    }

    public SCIOp createPauseOp()
    {
        return sciHousehold.createPauseOp();
    }

    public SCIActionContext createPushSCUriAction(SCFixedSCUriID scfixedscuriid, boolean flag)
    {
        return sciHousehold.createPushSCUriAction(sclib.SCLibGetFixedSCUri(scfixedscuriid), sclib.SCLibGetFixedSCUriTitle(scfixedscuriid), flag);
    }

    public SCIActionContext createPushSCUriAction(String s, String s1, boolean flag)
    {
        return sciHousehold.createPushSCUriAction(s, s1, flag);
    }

    public SCISoundbarWizard createRecalibrateAudioSoundbarWizard(String s)
    {
        return sciHousehold.createRecalibrateAudioSoundbarWizard(s);
    }

    public SCISetupWizard createRecalibrateSubWizard(String s)
    {
        return sciHousehold.createRecalibrateSubWizard(s);
    }

    public SCISetupWizard createRegistrationWizard()
    {
        return sciHousehold.createRegistrationWizard();
    }

    public SCISoundbarWizard createRemoteSetupWizard(String s)
    {
        return sciHousehold.createRemoteSetupWizard(s);
    }

    public SCIOnlineUpdateWizard createResumeUpdateWizard()
    {
        return sciHousehold.createResumeUpdateWizard();
    }

    public SCIOp createSetPropertyOp(String s, String s1)
    {
        return sciHousehold.createSetPropertyOp(s, s1);
    }

    public SCIOp createSetUsageDataShareOptionOp(boolean flag)
    {
        return sciHousehold.createSetUsageDataShareOptionOp(flag);
    }

    public SCISoundbarWizard createSetupSurroundSoundbarWizard(String s)
    {
        return sciHousehold.createSetupSurroundSoundbarWizard(s);
    }

    public SCISetupWizard createShareUsageDataWizard()
    {
        return sciHousehold.createShareUsageDataWizard();
    }

    public SCISonosNetSetupWizard createSonosNetSetupWizard()
    {
        return sciHousehold.createSonosNetSetupWizard();
    }

    public SCISoundbarWizard createSoundbarWizard(String s)
    {
        return sciHousehold.createSoundbarWizard(s);
    }

    public SCIOp createStopOp()
    {
        return sciHousehold.createStopOp();
    }

    public SCIActionContext createSubmitDiagsWizardAction()
    {
        return sciHousehold.createSubmitDiagsWizardAction();
    }

    public SCISearchable getAggregatedSearchable()
    {
        return sciHousehold.getAggregatedSearchable();
    }

    public SCIAlarmManager getAlarmManager()
    {
        return sciHousehold.getAlarmManager();
    }

    public ArrayList getAllSearchables()
    {
        return (ArrayList)searchables.clone();
    }

    public SCIDevice getAssociatedDevice()
    {
        return sciHousehold.getAssociatedDevice();
    }

    public SCIBrowseListPresentationMap getBrowseListPresentationMap()
    {
        return sciHousehold.getBrowseListPresentationMap();
    }

    public ArrayList getCompatibleAndVisibleZoneGroups()
    {
        SCIEnumerator scienumerator = sciHousehold.getZoneGroups(com.sonos.sclib.SCIHousehold.ZGFilterOpt.FLT_ZG_ANY);
        ArrayList arraylist = new ArrayList();
        scienumerator.reset();
        do
        {
            if(!scienumerator.moveNext())
                break;
            SCIZoneGroup scizonegroup = (SCIZoneGroup)scienumerator.getCurrent(sclibConstants.SCIZONEGROUP_INTERFACE);
            if(scizonegroup.isCompatibleAndVisible())
                arraylist.add(new ZoneGroup(scizonegroup));
        } while(true);
        return arraylist;
    }

    public SCIDevice getCurrentMasterDevice()
    {
        return sciHousehold.getCurrentMasterDevice();
    }

    public ZoneGroup getCurrentZoneGroup()
    {
        SCIZoneGroup scizonegroup = sciHousehold.getCurrentZoneGroup();
        ZoneGroup zonegroup;
        if(scizonegroup != null)
            zonegroup = new ZoneGroup(scizonegroup);
        else
            zonegroup = null;
        return zonegroup;
    }

    public String getCustomerIDIfRegistered()
    {
        return sciHousehold.getCustomerIDIfRegistered();
    }

    public SCIDateTimeManager getDateTimeManager()
    {
        return sciHousehold.getDateTimeManager();
    }

    public ArrayList getDevices(com.sonos.sclib.SCIHousehold.DevFilterOpt devfilteropt)
    {
        return LibraryUtils.toList(sciHousehold.getDevices(devfilteropt), com/sonos/sclib/SCIDevice, com/sonos/acr/sclib/wrappers/ZoneDevice);
    }

    public String getID()
    {
        return sciHousehold.getID();
    }

    public SCIIndexManager getIndexManager()
    {
        return sciHousehold.getIndexManager();
    }

    public long getNumZoneGroups(com.sonos.sclib.SCIHousehold.ZGFilterOpt zgfilteropt)
    {
        return sciHousehold.getNumZoneGroups(zgfilteropt);
    }

    public SCIServiceDescriptorManager getServiceDescriptorManager()
    {
        return sciHousehold.getServiceDescriptorManager();
    }

    public SCIShareManager getShareManager()
    {
        return sciHousehold.getShareManager();
    }

    public ArrayList getSortedZoneGroups(com.sonos.sclib.SCIHousehold.ZGFilterOpt zgfilteropt, Comparator comparator)
    {
        ArrayList arraylist = getZoneGroups(zgfilteropt);
        Collections.sort(arraylist, comparator);
        return arraylist;
    }

    public SCIZoneGroupMgr getZoneGroupManager()
    {
        return SCIZoneGroupMgr.getInterface(sciHousehold);
    }

    public ArrayList getZoneGroups(com.sonos.sclib.SCIHousehold.ZGFilterOpt zgfilteropt)
    {
        SCIEnumerator scienumerator = sciHousehold.getZoneGroups(zgfilteropt);
        ArrayList arraylist = new ArrayList();
        scienumerator.reset();
        for(; scienumerator.moveNext(); arraylist.add(new ZoneGroup((SCIZoneGroup)scienumerator.getCurrent(sclibConstants.SCIZONEGROUP_INTERFACE))));
        return arraylist;
    }

    public boolean hasTransientOrphanedZoneGroups()
    {
        return sciHousehold.hasTransientOrphanedZoneGroups();
    }

    public boolean isValid()
    {
        return sciHousehold.isValid();
    }

    public ZoneDevice lookupDevice(String s)
    {
        SCIDevice scidevice = sciHousehold.lookupDevice(s);
        ZoneDevice zonedevice;
        if(scidevice != null)
            zonedevice = new ZoneDevice(scidevice);
        else
            zonedevice = null;
        return zonedevice;
    }

    public SCISearchable lookupSearchableBySCUri(String s)
    {
        return sciHousehold.lookupSearchableBySCUri(s);
    }

    public ZoneGroup lookupZoneGroup(String s)
    {
        SCIZoneGroup scizonegroup;
        ZoneGroup zonegroup;
        if("CURRENT_ZONEGROUP".equals(s))
            scizonegroup = sciHousehold.getCurrentZoneGroup();
        else
            scizonegroup = sciHousehold.lookupZoneGroup(s);
        if(scizonegroup != null)
            zonegroup = new ZoneGroup(scizonegroup);
        else
            zonegroup = null;
        return zonegroup;
    }

    public ZoneGroup lookupZoneGroupByDevice(String s)
    {
        if(!StringUtils.isEmptyOrNull(s)) goto _L2; else goto _L1
_L1:
        ZoneGroup zonegroup = getCurrentZoneGroup();
_L4:
        return zonegroup;
_L2:
        for(Iterator iterator = getZoneGroups(com.sonos.sclib.SCIHousehold.ZGFilterOpt.FLT_ZG_ANY).iterator(); iterator.hasNext();)
        {
            zonegroup = (ZoneGroup)iterator.next();
            if(zonegroup.getDevice(s) != null)
                continue; /* Loop/switch isn't completed */
        }

        zonegroup = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void saveCurrentZoneGroup()
    {
        sciHousehold.saveCurrentZoneGroup();
    }

    public void setCurrentZoneGroup(String s)
    {
        sciHousehold.setCurrentZoneGroup(s);
        saveCurrentZoneGroup();
    }

    public void setHousehold(SCIHousehold scihousehold)
    {
        sciHousehold = scihousehold;
        searchables.clear();
        for(Iterator iterator = listeners.iterator(); iterator.hasNext(); ((HouseholdChangedListener)iterator.next()).onHouseholdChanged());
    }

    public void setTopSearchable(SCISearchable scisearchable)
    {
        sciHousehold.setTopSearchable(scisearchable);
    }

    public void setTopSearchableBySCUri(String s)
    {
        sciHousehold.setTopSearchableBySCUri(s);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sciHousehold.subscribe(scieventsink);
        if(scieventsink instanceof HouseholdChangedListener)
        {
            HouseholdChangedListener householdchangedlistener = (HouseholdChangedListener)scieventsink;
            if(!listeners.contains(householdchangedlistener))
                listeners.add(householdchangedlistener);
        }
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sciHousehold.unsubscribe(scieventsink);
        if(scieventsink instanceof HouseholdChangedListener)
        {
            HouseholdChangedListener householdchangedlistener = (HouseholdChangedListener)scieventsink;
            listeners.remove(householdchangedlistener);
        }
    }

    public void updateAvailableServices()
    {
        sciHousehold.updateAvailableServices();
    }

    public void updateSearchables()
    {
        searchables.clear();
        SCIEnumerator scienumerator = sciHousehold.getAllSearchables();
        if(scienumerator != null)
        {
            scienumerator.reset();
            SCISearchable scisearchable;
            for(; scienumerator.moveNext(); searchables.add(scisearchable))
                scisearchable = (SCISearchable)(SCISearchable)scienumerator.getCurrent(sclibConstants.SCISEARCHABLE_INTERFACE);

        }
    }

    public static final String LOG_TAG = com/sonos/acr/sclib/wrappers/Household.getSimpleName();
    public static final String ZONEGROUP_ID_CURRENT = "CURRENT_ZONEGROUP";
    private ArrayList listeners;
    SCIHousehold sciHousehold;
    ArrayList searchables;

}

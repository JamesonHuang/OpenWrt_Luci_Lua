// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            SCIActionDelegateSwigBase, SCIAction, SCIActionContext, SCIActionFactorySwigBase, 
//            SCIBrowseDataSource, SCIBrowseStackManager, SCDisplayCustomControlActionType, SCISystemTime, 
//            SCIStringInput, SCIStringArray, SCIEnumerator, SCIResource, 
//            SCIInfoViewTextPaneMetadata, SCITime, SCIWizard, SCNavigationActionType, 
//            SCIPropertyBag, SCIOp, SCRunWizardActionType, SCIActionFilterSwigBase, 
//            SCIActionDescriptor, SCIActionSwigBase, SCActionCompletionStatus, SCIBrowseItemSwigBase, 
//            SCIActionFilter, SCIEventSink, SCICustomSubWizardSwigBase, SCIEventSinkSwigBase, 
//            SCIGetAboutSonosStringCBSwigBase, SCIGetSonosPlaylistsCBSwigBase, SCILocalMediaCollectionSwigBase, SCILocalMusicBrowseItemInfo, 
//            SCILocalMediaCollectionListener, SCILocalMusicBrowseItemInfoSwigBase, SCILocalMusicSearchableDelegateSwigBase, SCILocationServicesSwigBase, 
//            SCIMusicServerBrowseDelegateSwigBase, SCILocalMediaCollection, SCILocalMusicSearchableDelegate, SCIMusicServerDelegateSwigBase, 
//            SCIData, SCIMusicServerBrowseDelegate, SCINetstartListenerSwigBase, SCINetworkManagementDelegateSwigBase, 
//            SCIOpCBSwigBase, SCIPlatformDateTimeProvider, SCITimeZone, SCIStackTraceCaptureDelegateSwigBase, 
//            SCITrackInfoSwigBase, SCIWebBridgeDelegateSwigBase, SCRouteResultType, SCIWebMessage, 
//            SCIWifiSetupDelegate, SCIWifiListener, SCLibAssertionFailureCallback, SCLibCallUIThreadCallback, 
//            SCLibDiagnosticConsoleLogCallback, SCLibDiagnosticExtraInfoCallback, SCLibLogCallback, SCLibTruncatedStringsCallback, 
//            SCIActionDelegate, SCIActionFactory, SCIActionFilterer, SCIActionNoArgDescriptor, 
//            SCIActionOnAlarmDescriptor, SCIActionOnGroupDescriptor, SCIActionSelectableDescriptor, SCIActionWithBoolDescriptor, 
//            SCIActionWithIntDescriptor, SCIActionWithStringDescriptor, SCIAddToQueueAtNumberDescriptor, SCIAggregateBrowseDataSource, 
//            SCIAlarmManager, SCIAlarm, SCIAlarmMusicBrowseItem, SCIAlarmMusic, 
//            SCIRecurrence, SCIAppReporting, SCIArtworkCacheManager, SCIArtworkCache, 
//            SCIArtworkData, SCIAudibleManager, SCIAudioData, SCIAudioInputResource, 
//            SCIBooleanSettingsProperty, SCIBrowseGroupsInfo, SCIBrowseItem, SCIBrowseListPresentationMap, 
//            SCIBrowseManager, SCICommittable, SCIConfigureWirelessWizard, SCICountry, 
//            SCICustomSubWizard, SCIDateTimeManager, SCIDateTimeSettingsProperty, SCIDealerMode, 
//            SCIDebug, SCIDeviceAutoplay, SCIDeviceLineIn, SCIDeviceLineOut, 
//            SCIDeviceMusicEqualization, SCIDeviceSettingsDataSource, SCIDeviceVolume, SCIDevice, 
//            SCIEnumerable, SCIObj, SCIGetAboutSonosStringCB, SCIGetSonosPlaylistsCB, 
//            SCIGroupVolume, SCIHousehold, SCISearchable, SCIIndexManager, 
//            SCIInfoViewHeaderDataSource, SCIInfoViewHeaderItem, SCIInputValidationCB, SCIInput, 
//            SCIIntArray, SCIIntegerSettingsProperty, SCILatentLoadingDataSource, SCILibraryTests, 
//            SCILibrary, SCIMusicServerDelegate, SCILocationServices, SCIMusicServer, 
//            SCINetworkManagementDelegate, SCIStackTraceCaptureDelegate, SCILinkSettingsProperty, SCILogoArtworkCache, 
//            SCIMusicServiceWizard, SCINetstartListener, SCINetstartScanListEntry, SCINetworkManagement, 
//            SCINowPlayingRatings, SCINowPlayingSleepTimer, SCINowPlayingSource, SCINowPlayingTransport, 
//            SCINowPlaying, SCIZoneGroup, SCIOnlineUpdateWizard, SCIOpAVTransportGetPositionInfo, 
//            SCIOpAVTransportGetRemainingSleepTimerDuration, SCIOpAddServiceAccount, SCIOpAddTracksToQueue, SCIOpAlarmSave, 
//            SCIOpAttachPrivateQueue, SCIOpAudioInGetAudioInputAttributes, SCIOpAudioInGetLineInLevel, SCIOpCB, 
//            SCIOpConnectionManagerGetProtocolInfo, SCIOpDevicePropertiesGetAutoplayLinkedZones, SCIOpDevicePropertiesGetAutoplayRoomUUID, SCIOpDevicePropertiesGetAutoplayVolume, 
//            SCIOpDevicePropertiesGetLEDState, SCIOpDevicePropertiesGetUseAutoplayVolume, SCIOpFactory, SCIOpGenericUpdateQueue, 
//            SCIOpGetAboutSonosString, SCIOpGetTrackPositionInfo, SCIOpGetUsageDataShareOption, SCIOpLoadAlbumArt, 
//            SCIOpLoadLogo, SCIOpNetstartGetScanList, SCIOpNewPrivateQueue, SCIOpQueueAttachQueue, 
//            SCIOpQueueReplaceAllTracks, SCIOpRegEmailOptIn, SCIOpRenderingControlGetOutputFixed, SCIOpRenderingControlGetSupportsOutputFixed, 
//            SCIOpReplaceAccount, SCIOpSubmitDiagnostics, SCIOpSystemPropertyGetString, SCIOpValidateServiceCredentials, 
//            SCIOpZoneGroupTopologyGetZoneGroupState, SCIOperationProgress, SCIPandoraResults, SCIPlayQueueItemState, 
//            SCIPlayQueueMgr, SCIPlayQueue, SCIPowerscrollDataSource, SCIProperty, 
//            SCIRoomResource, SCIScrobblingService, SCISearchableCategory, SCISelectableItem, 
//            SCISelectionManager, SCIServiceAccountFilter, SCIServiceAccount, SCIServiceAccountManager, 
//            SCIServiceDescriptorFilter, SCIServiceDescriptor, SCIServiceDescriptorManager, SCISettingsBrowseItem, 
//            SCISettingsProperty, SCISetupWizard, SCIShareManager, SCIShare, 
//            SCISonosPlaylist, SCISoundbarWizard, SCISpinnerSettingsProperty, SCIStringFromCustomSettingsProperty, 
//            SCIStringFromListSettingsProperty, SCIStringInputBase, SCIStringInputWithAsyncValidation, SCIStringSettingsProperty, 
//            SCIStringTemplate, SCISubCalibrator, SCISystem, SCITimeSettingsProperty, 
//            SCITooltip, SCITrackInfo, SCIVersionRange, SCIVersion, 
//            SCIWebBridgeDelegate, SCIWebBridge, SCIWebRequestSpec, SCIZoneGroupMgr, 
//            SCLibParameters, SCUserInterfaceParameters

public class sclibJNI
{

    public sclibJNI()
    {
    }

    public static final native String LOCALMUSICBROWSE_CPUDN_get();

    public static final native String LS_PROP_AUTHORIZED_get();

    public static final native String LS_PROP_COUNTRY_CODE_get();

    public static final native String LS_PROP_COUNTRY_NAME_get();

    public static final native String LS_PROP_POSTAL_CODE_get();

    public static final native String ONALARMRUNNINGCHANGED_EVENT_get();

    public static final native String ONALARMSCHANGED_EVENT_get();

    public static final native String ONARTWORKDATACHANGED_EVENT_get();

    public static final native String ONASSOCIATEDDEVICECHANGED_EVENT_get();

    public static final native String ONBALANCELEVELCHANGED_EVENT_get();

    public static final native String ONBASSLEVELCHANGED_EVENT_get();

    public static final native String ONBROWSECHANGED_EVENT_get();

    public static final native String ONBROWSEINVALIDATION_EVENT_get();

    public static final native String ONCHANGED_EVENT_get();

    public static final native String ONCROSSOVERCHANGED_EVENT_get();

    public static final native String ONCURRENTZONEGROUPCHANGED_EVENT_get();

    public static final native String ONDATEFORMATCHANGED_EVENT_get();

    public static final native String ONDEALERLOCKCHANGED_EVENT_get();

    public static final native String ONDEVICEUPDATESTATUSCHANGED_EVENT_get();

    public static final native String ONFINISHEDCONNECTINGTOZPS_EVENT_get();

    public static final native String ONFIXEDOUTPUT_EVENT_get();

    public static final native String ONINDEXERROR_EVENT_get();

    public static final native String ONINDEXTIMECHANGED_EVENT_get();

    public static final native String ONINDEX_EVENT_get();

    public static final native String ONINVALIDATION_EVENT_get();

    public static final native String ONITEMCHANGED_EVENT_get();

    public static final native String ONLINEUPDATE_WIZPROP_ChoiceBody_get();

    public static final native String ONLINEUPDATE_WIZPROP_ChoiceCancelLabel_get();

    public static final native String ONLINEUPDATE_WIZPROP_ChoiceContinueLabel_get();

    public static final native String ONLINEUPDATE_WIZPROP_ChoiceTitle_get();

    public static final native String ONLINEUPDATE_WIZPROP_CurrentDeviceStatus_get();

    public static final native String ONLINEUPDATE_WIZPROP_CurrentDevicesPercent_get();

    public static final native String ONLINEUPDATE_WIZPROP_IntroBody_get();

    public static final native String ONLINEUPDATE_WIZPROP_IntroTitle_get();

    public static final native String ONLINEUPDATE_WIZPROP_IsRegistrationRequired_get();

    public static final native String ONLINEUPDATE_WIZPROP_IsReindexNeeded_get();

    public static final native String ONLINEUPDATE_WIZPROP_OnlineUpdateWizardMode_get();

    public static final native String ONLINEUPDATE_WIZPROP_ResultCodeBodyText_get();

    public static final native String ONLINEUPDATE_WIZPROP_ResultCodeHeaderText_get();

    public static final native String ONLINEUPDATE_WIZPROP_TimeoutInSecs_get();

    public static final native String ONLINEUPDATE_WIZPROP_UpdateResultCode_get();

    public static final native String ONLOUDNESSCHANGED_EVENT_get();

    public static final native String ONMUSICCHANGED_EVENT_get();

    public static final native String ONMUTECHANGED_EVENT_get();

    public static final native String ONNETWORKCHANGED_EVENT_get();

    public static final native String ONNIGHTMODECHANGED_EVENT_get();

    public static final native String ONOPRUNNINGCOUNTCHANGED_EVENT_get();

    public static final native String ONOUTPUTMODECHANGED_EVENT_get();

    public static final native String ONPOWERSCROLLINFO_EVENT_get();

    public static final native String ONPRELOADSERVICEDESCRIPTORSCHANGED_EVENT_get();

    public static final native String ONPRIVATEQUEUECHANGED_EVENT_get();

    public static final native String ONQUEUECURRENTITEMCHANGED_EVENT_get();

    public static final native String ONQUEUEDATASOURCECHANGED_EVENT_get();

    public static final native String ONQUEUEINUSECHANGED_EVENT_get();

    public static final native String ONQUEUEINVALIDATION_EVENT_get();

    public static final native String ONRUNALARM_EVENT_get();

    public static final native String ONSEARCHABLESLISTCHANGED_EVENT_get();

    public static final native String ONSERVICEACCOUNTSCHANGED_EVENT_get();

    public static final native String ONSERVICEDESCRIPTORSCHANGED_EVENT_get();

    public static final native String ONSHARESCHANGED_EVENT_get();

    public static final native String ONSLEEPTIMERGENERATIONCHANGED_EVENT_get();

    public static final native String ONSNOOZERUNNINGCHANGED_EVENT_get();

    public static final native String ONSOFTWAREUPDATEAVAILABLECHANGED_EVENT_get();

    public static final native String ONSTACKINVALIDATION_EVENT_get();

    public static final native String ONSTATECHANGED_EVENT_get();

    public static final native String ONSTATETRANSITIONSENABLED_EVENT_get();

    public static final native String ONSUBENABLEDCHANGED_EVENT_get();

    public static final native String ONSUBGAINCHANGED_EVENT_get();

    public static final native String ONSUBPOLARITYCHANGED_EVENT_get();

    public static final native String ONSURROUNDENABLEDCHANGED_EVENT_get();

    public static final native String ONSURROUNDLEVELCHANGED_EVENT_get();

    public static final native String ONSURROUNDMODECHANGED_EVENT_get();

    public static final native String ONTIMEFORMATCHANGED_EVENT_get();

    public static final native String ONTIMEGENERATIONCHANGED_EVENT_get();

    public static final native String ONTIMESERVERCHANGED_EVENT_get();

    public static final native String ONTIMESTATUSCHANGED_EVENT_get();

    public static final native String ONTIMEZONECHANGED_EVENT_get();

    public static final native String ONTRANSITIONSCHANGED_EVENT_get();

    public static final native String ONTREBLELEVELCHANGED_EVENT_get();

    public static final native String ONTVAUDIODELAYCHANGED_EVENT_get();

    public static final native String ONTVDIALOGLEVELCHANGED_EVENT_get();

    public static final native String ONTVEQUALIZATIONCHANGED_EVENT_get();

    public static final native String ONVOLUMECHANGED_EVENT_get();

    public static final native String ONZONEGROUPCHANGED_EVENT_get();

    public static final native String ONZONEGROUPSCHANGED_EVENT_get();

    public static final native String SCACTN_BAGPROP_URL_QUERYSTR_get();

    public static final native String SCACTN_BOOLPROP_ACTION_NOT_PERFORMED_get();

    public static final native String SCACTN_BOOLPROP_MENU_CANCEL_get();

    public static final native String SCACTN_BOOLPROP_SUPPRESS_PROMPTS_get();

    public static final native String SCACTN_BOOLPROP_UI_TRIM_WHITESPACE_get();

    public static final native String SCACTN_BOOL_SETTINGSUSERINPUT_IS_ID_get();

    public static final native String SCACTN_INTPROP_ALARMID_get();

    public static final native String SCACTN_INTPROP_DATE_DAY_get();

    public static final native String SCACTN_INTPROP_DATE_MONTH_get();

    public static final native String SCACTN_INTPROP_DATE_YEAR_get();

    public static final native String SCACTN_INTPROP_ICON_ID_INPUT_get();

    public static final native String SCACTN_INTPROP_INTEGER_INPUT_get();

    public static final native String SCACTN_INTPROP_MENU_SELECTED_INDEX_get();

    public static final native String SCACTN_INTPROP_TIME_HOURS_get();

    public static final native String SCACTN_INTPROP_TIME_MINUTES_get();

    public static final native String SCACTN_INTPROP_TIME_SECONDS_get();

    public static final native String SCACTN_INTPROP_WIZARDEXITCODE_get();

    public static final native String SCACTN_METADATA_AAURI_get();

    public static final native String SCACTN_METADATA_ALBUMARTIST_get();

    public static final native String SCACTN_METADATA_ALBUM_get();

    public static final native String SCACTN_METADATA_ARTIST_get();

    public static final native String SCACTN_METADATA_DISPLAYTITLE_get();

    public static final native String SCACTN_METADATA_GENRE_get();

    public static final native String SCACTN_METADATA_OBJECTID_get();

    public static final native String SCACTN_METADATA_PARENTID_get();

    public static final native String SCACTN_METADATA_RESPROTOCOLINFO_get();

    public static final native String SCACTN_METADATA_RESURI_get();

    public static final native String SCACTN_METADATA_TITLE_get();

    public static final native String SCACTN_METADATA_UDN_get();

    public static final native String SCACTN_METADATA_UPNPCLASS_get();

    public static final native String SCACTN_PROP_ACTION_CATEGORY_get();

    public static final native String SCACTN_PROP_ACTION_ID_get();

    public static final native String SCACTN_PROP_APP_REPORTING_VIEW_get();

    public static final native String SCACTN_PROP_SETTINGSUSERINPUT_EXTRA_get();

    public static final native String SCACTN_PROP_SETTINGSUSERINPUT_get();

    public static final native String SCACTN_STRPROP_ALBUM_get();

    public static final native String SCACTN_STRPROP_ARTIST_get();

    public static final native String SCACTN_STRPROP_DEVICEID_get();

    public static final native String SCACTN_STRPROP_HOUSEHOLD_ID_get();

    public static final native String SCACTN_STRPROP_PRIMARY_BUTTON_NAME_get();

    public static final native String SCACTN_STRPROP_SHARE_STRING_get();

    public static final native String SCACTN_STRPROP_TEXT_INPUT_2_get();

    public static final native String SCACTN_STRPROP_TEXT_INPUT_get();

    public static final native String SCACTN_STRPROP_TITLE_get();

    public static final native String SCACTN_STRPROP_URI_get();

    public static final native String SCALBUMART_ALARMS_get();

    public static final native String SCALBUMART_ALLMUSIC_get();

    public static final native String SCALBUMART_ALL_NODE_get();

    public static final native String SCALBUMART_AUDIBLE_get();

    public static final native String SCALBUMART_BROADCAST_get();

    public static final native String SCALBUMART_DEFAULT_get();

    public static final native String SCALBUMART_DOCK_get();

    public static final native String SCALBUMART_EMPTY_FAVORITES_get();

    public static final native String SCALBUMART_EMPTY_LINEIN_get();

    public static final native String SCALBUMART_EMPTY_PLAYLISTS_get();

    public static final native String SCALBUMART_FAVORITES_get();

    public static final native String SCALBUMART_FOLDER_get();

    public static final native String SCALBUMART_LASTFMBAN_get();

    public static final native String SCALBUMART_LASTFMLOVE_get();

    public static final native String SCALBUMART_LIBRARY_ALBUMS_get();

    public static final native String SCALBUMART_LIBRARY_ARTISTS_get();

    public static final native String SCALBUMART_LIBRARY_COMPILATIONS_get();

    public static final native String SCALBUMART_LIBRARY_COMPOSERS_get();

    public static final native String SCALBUMART_LIBRARY_GENRES_get();

    public static final native String SCALBUMART_LIBRARY_PLAYLISTS_get();

    public static final native String SCALBUMART_LIBRARY_PODCASTS_get();

    public static final native String SCALBUMART_LIBRARY_TRACKS_get();

    public static final native String SCALBUMART_LIBRARY_get();

    public static final native String SCALBUMART_LINEIN_get();

    public static final native String SCALBUMART_LOGO_TUNEIN_get();

    public static final native String SCALBUMART_MULTITRACK_get();

    public static final native String SCALBUMART_NONE_get();

    public static final native String SCALBUMART_PANDORATHUMBSDOWN_EMPTY_get();

    public static final native String SCALBUMART_PANDORATHUMBSDOWN_get();

    public static final native String SCALBUMART_PANDORATHUMBSUP_EMPTY_get();

    public static final native String SCALBUMART_PANDORATHUMBSUP_get();

    public static final native String SCALBUMART_PLAYLIST_ITEM_DISABLED_get();

    public static final native String SCALBUMART_PLAYLIST_ITEM_get();

    public static final native String SCALBUMART_PLAYLIST_get();

    public static final native String SCALBUMART_PLUS_get();

    public static final native String SCALBUMART_RADIO_get();

    public static final native String SCALBUMART_RESTRICTED_get();

    public static final native String SCALBUMART_SETTINGS_get();

    public static final native String SCALBUMART_SOUNDLAB_get();

    public static final native String SCALBUMART_TRACK_get();

    public static final native String SCALBUMART_TVAUDIO_get();

    public static final native String SCALBUMART_UPDATE_get();

    public static final native String SCALBUMART_WMSERVER_get();

    public static final native String SCATN_BOOLPROP_CANNOT_FORCE_CLOSE_WINDOW_ACTION_get();

    public static final native String SCATN_BOOLPROP_PERFORM_ONLY_WHEN_NO_MODALS_EXIST_get();

    public static final native String SCATN_STRPROP_SHORT_MESSAGE_get();

    public static final native String SCBROWSEITEM_ATTR_BOOL_ISACTIVEITEM_get();

    public static final native String SCBROWSEITEM_ATTR_BOOL_ISCHECKABLE_get();

    public static final native String SCBROWSEITEM_ATTR_BOOL_ISCHECKED_get();

    public static final native String SCBROWSEITEM_ATTR_INT_ALARMID_get();

    public static final native int SCDATEFORMAT_UNKNOWN_get();

    public static final native int SCDAY_SUNDAY_get();

    public static final native String SCIACTIONFACTORY_INTERFACE_get();

    public static final native String SCIACTION_ADD_TO_QUEUE_AT_NUMBER_DESCRIPTOR_INTERFACE_get();

    public static final native String SCIACTION_DESCRIPTOR_INTERFACE_get();

    public static final native String SCIACTION_NOARG_DESCRIPTOR_INTERFACE_get();

    public static final native String SCIACTION_ON_ALARM_DESCRIPTOR_INTERFACE_get();

    public static final native String SCIACTION_ON_GROUP_DESCRIPTOR_INTERFACE_get();

    public static final native String SCIACTION_SELECTABLE_DESCRIPTOR_INTERFACE_get();

    public static final native String SCIACTION_WITH_BOOL_DESCRIPTOR_INTERFACE_get();

    public static final native String SCIACTION_WITH_INT_DESCRIPTOR_INTERFACE_get();

    public static final native String SCIACTION_WITH_STRING_DESCRIPTOR_INTERFACE_get();

    public static final native String SCIAGGREGATEBROWSEDATASOURCE_INTERFACE_get();

    public static final native String SCIALARMMANAGER_INTERFACE_get();

    public static final native String SCIALARMMANAGER_ONALARMSCHANGED_EVENT_get();

    public static final native String SCIALARMMANAGER_ONRUNALARM_EVENT_get();

    public static final native String SCIALARMMUSICBROWSEITEM_INTERFACE_get();

    public static final native String SCIALARM_INTERFACE_get();

    public static final native String SCIALARM_ONITEMCHANGED_EVENT_get();

    public static final native String SCIARTWORKCACHEMANAGER_INTERFACE_get();

    public static final native String SCIARTWORKCACHE_INTERFACE_get();

    public static final native String SCIARTWORKDATA_INTERFACE_get();

    public static final native String SCIARTWORKDATA_ONARTWORKDATACHANGED_EVENT_get();

    public static final native String SCIAUDIBLEMANAGER_INTERFACE_get();

    public static final native String SCIAUDIODATA_INTERFACE_get();

    public static final native String SCIAUDIOINPUTRESOURCE_INTERFACE_get();

    public static final native void SCIActionContext_actionHasCompleted(long l, SCIActionContext sciactioncontext, long l1, SCIAction sciaction);

    public static final native long SCIActionContext_getPropertyBag(long l, SCIActionContext sciactioncontext);

    public static final native int SCIActionContext_perform(long l, SCIActionContext sciactioncontext);

    public static final native void SCIActionContext_setDelegate(long l, SCIActionContext sciactioncontext, long l1, SCIActionDelegate sciactiondelegate);

    public static final native void SCIActionDelegateSwigBase_change_ownership(SCIActionDelegateSwigBase sciactiondelegateswigbase, long l, boolean flag);

    public static final native void SCIActionDelegateSwigBase_director_connect(SCIActionDelegateSwigBase sciactiondelegateswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCIActionDelegateSwigBase_dumpSCIObj(long l, SCIActionDelegateSwigBase sciactiondelegateswigbase);

    public static final native String SCIActionDelegateSwigBase_dumpSCIObjSwigExplicitSCIActionDelegateSwigBase(long l, SCIActionDelegateSwigBase sciactiondelegateswigbase);

    public static final native void SCIActionDelegate_asyncActionHasCompleted(long l, SCIActionDelegate sciactiondelegate, long l1, SCIAction sciaction, long l2, 
            SCIActionContext sciactioncontext);

    public static final native String SCIActionDescriptor_getActionID(long l, SCIActionDescriptor sciactiondescriptor);

    public static final native String SCIActionDescriptor_getCategory(long l, SCIActionDescriptor sciactiondescriptor);

    public static final native String SCIActionDescriptor_getInterfaceID(long l, SCIActionDescriptor sciactiondescriptor);

    public static final native String SCIActionDescriptor_getLabel(long l, SCIActionDescriptor sciactiondescriptor);

    public static final native boolean SCIActionDescriptor_isEnabled(long l, SCIActionDescriptor sciactiondescriptor);

    public static final native void SCIActionFactorySwigBase_change_ownership(SCIActionFactorySwigBase sciactionfactoryswigbase, long l, boolean flag);

    public static final native void SCIActionFactorySwigBase_director_connect(SCIActionFactorySwigBase sciactionfactoryswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCIActionFactorySwigBase_dumpSCIObj(long l, SCIActionFactorySwigBase sciactionfactoryswigbase);

    public static final native String SCIActionFactorySwigBase_dumpSCIObjSwigExplicitSCIActionFactorySwigBase(long l, SCIActionFactorySwigBase sciactionfactoryswigbase);

    public static final native long SCIActionFactory_createBrowsePickerAction(long l, SCIActionFactory sciactionfactory, long l1, SCIBrowseDataSource scibrowsedatasource, String s);

    public static final native long SCIActionFactory_createCustomUIAction(long l, SCIActionFactory sciactionfactory, String s, String s1);

    public static final native long SCIActionFactory_createDisplayBrowseStackAction(long l, SCIActionFactory sciactionfactory, long l1, SCIBrowseStackManager scibrowsestackmanager);

    public static final native long SCIActionFactory_createDisplayCustomControlAction(long l, SCIActionFactory sciactionfactory, int i);

    public static final native long SCIActionFactory_createDisplayDatePickerAction(long l, SCIActionFactory sciactionfactory, String s, String s1, long l1, SCISystemTime scisystemtime);

    public static final native long SCIActionFactory_createDisplayDualTextInputAction(long l, SCIActionFactory sciactionfactory, String s, String s1, String s2, String s3, long l1, SCIStringInput scistringinput, String s4, String s5, long l2, SCIStringInput scistringinput1);

    public static final native long SCIActionFactory_createDisplayInfoViewAction(long l, SCIActionFactory sciactionfactory, String s, String s1);

    public static final native long SCIActionFactory_createDisplayIntegerInputAction(long l, SCIActionFactory sciactionfactory, String s, String s1, String s2, int i, int j, 
            int k);

    public static final native long SCIActionFactory_createDisplayMenuAction(long l, SCIActionFactory sciactionfactory, String s, String s1, String s2, long l1, 
            SCIStringArray scistringarray, int i);

    public static final native long SCIActionFactory_createDisplayMenuAndTextInputAction(long l, SCIActionFactory sciactionfactory, String s, String s1, String s2, boolean flag, long l1, SCIEnumerator scienumerator, int i, long l2, SCIResource sciresource, int j, 
            String s3);

    public static final native long SCIActionFactory_createDisplayMenuPopupAction(long l, SCIActionFactory sciactionfactory, String s, long l1, SCIStringArray scistringarray, int i, 
            String s1, boolean flag, boolean flag1);

    public static final native long SCIActionFactory_createDisplayMessagePopupAction(long l, SCIActionFactory sciactionfactory, String s, int i);

    public static final native long SCIActionFactory_createDisplayTextInputAction(long l, SCIActionFactory sciactionfactory, String s, String s1, String s2, long l1, 
            SCIStringInput scistringinput);

    public static final native long SCIActionFactory_createDisplayTextPaneAction(long l, SCIActionFactory sciactionfactory, String s, String s1, long l1, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata);

    public static final native long SCIActionFactory_createDisplayTimePickerAction(long l, SCIActionFactory sciactionfactory, String s, String s1, long l1, SCITime scitime);

    public static final native long SCIActionFactory_createDisplayWizardAction(long l, SCIActionFactory sciactionfactory, long l1, SCIWizard sciwizard);

    public static final native long SCIActionFactory_createNavigationAction(long l, SCIActionFactory sciactionfactory, int i, long l1, SCIPropertyBag scipropertybag);

    public static final native long SCIActionFactory_createPopBrowseAction(long l, SCIActionFactory sciactionfactory, int i);

    public static final native long SCIActionFactory_createPushSCUriAction(long l, SCIActionFactory sciactionfactory, String s, String s1, boolean flag);

    public static final native long SCIActionFactory_createRunAsyncIOOperationAction(long l, SCIActionFactory sciactionfactory, long l1, SCIOp sciop);

    public static final native long SCIActionFactory_createRunWizardAction(long l, SCIActionFactory sciactionfactory, int i);

    public static final native void SCIActionFilterSwigBase_change_ownership(SCIActionFilterSwigBase sciactionfilterswigbase, long l, boolean flag);

    public static final native void SCIActionFilterSwigBase_director_connect(SCIActionFilterSwigBase sciactionfilterswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCIActionFilterSwigBase_dumpSCIObj(long l, SCIActionFilterSwigBase sciactionfilterswigbase);

    public static final native String SCIActionFilterSwigBase_dumpSCIObjSwigExplicitSCIActionFilterSwigBase(long l, SCIActionFilterSwigBase sciactionfilterswigbase);

    public static final native boolean SCIActionFilter_acceptsAction(long l, SCIActionFilter sciactionfilter, long l1, SCIActionDescriptor sciactiondescriptor);

    public static final native long SCIActionFilterer_createFilteredEnumerator(long l, SCIActionFilterer sciactionfilterer, long l1, SCIEnumerator scienumerator, long l2, 
            SCIActionFilter sciactionfilter);

    public static final native long SCIActionNoArgDescriptor_getAction(long l, SCIActionNoArgDescriptor sciactionnoargdescriptor);

    public static final native long SCIActionOnAlarmDescriptor_getAction(long l, SCIActionOnAlarmDescriptor sciactiononalarmdescriptor, int i);

    public static final native long SCIActionOnGroupDescriptor_getAction(long l, SCIActionOnGroupDescriptor sciactionongroupdescriptor, String s);

    public static final native boolean SCIActionSelectableDescriptor_isCurrentSelection(long l, SCIActionSelectableDescriptor sciactionselectabledescriptor);

    public static final native void SCIActionSwigBase_change_ownership(SCIActionSwigBase sciactionswigbase, long l, boolean flag);

    public static final native void SCIActionSwigBase_director_connect(SCIActionSwigBase sciactionswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCIActionSwigBase_dumpSCIObj(long l, SCIActionSwigBase sciactionswigbase);

    public static final native String SCIActionSwigBase_dumpSCIObjSwigExplicitSCIActionSwigBase(long l, SCIActionSwigBase sciactionswigbase);

    public static final native long SCIActionWithBoolDescriptor_getAction(long l, SCIActionWithBoolDescriptor sciactionwithbooldescriptor, boolean flag);

    public static final native long SCIActionWithIntDescriptor_getAction(long l, SCIActionWithIntDescriptor sciactionwithintdescriptor, int i);

    public static final native long SCIActionWithStringDescriptor_getAction(long l, SCIActionWithStringDescriptor sciactionwithstringdescriptor, String s);

    public static final native int SCIAction_perform(long l, SCIAction sciaction, long l1, SCIActionContext sciactioncontext);

    public static final native long SCIAddToQueueAtNumberDescriptor_getAction(long l, SCIAddToQueueAtNumberDescriptor sciaddtoqueueatnumberdescriptor, String s, long l1);

    public static final native long SCIAggregateBrowseDataSource_getNumRecommendedPresentationItems(long l, SCIAggregateBrowseDataSource sciaggregatebrowsedatasource);

    public static final native void SCIAggregateBrowseDataSource_setNumRecommendedPresentationItems(long l, SCIAggregateBrowseDataSource sciaggregatebrowsedatasource, long l1);

    public static final native void SCIAlarmManager_clearEditableAlarm(long l, SCIAlarmManager scialarmmanager);

    public static final native long SCIAlarmManager_createDeleteAlarmAction(long l, SCIAlarmManager scialarmmanager, long l1, SCIAlarm scialarm);

    public static final native long SCIAlarmManager_createDeleteAlarmOp(long l, SCIAlarmManager scialarmmanager, long l1, SCIAlarm scialarm);

    public static final native long SCIAlarmManager_createNewAlarm(long l, SCIAlarmManager scialarmmanager);

    public static final native long SCIAlarmManager_getAlarms(long l, SCIAlarmManager scialarmmanager);

    public static final native long SCIAlarmManager_getEditableAlarm(long l, SCIAlarmManager scialarmmanager, int i);

    public static final native long SCIAlarmManager_getNewAlarmDataSource(long l, SCIAlarmManager scialarmmanager);

    public static final native long SCIAlarmManager_lookupAlarm(long l, SCIAlarmManager scialarmmanager, int i);

    public static final native void SCIAlarmManager_subscribe(long l, SCIAlarmManager scialarmmanager, long l1, SCIEventSink scieventsink);

    public static final native void SCIAlarmManager_unsubscribe(long l, SCIAlarmManager scialarmmanager, long l1, SCIEventSink scieventsink);

    public static final native int SCIAlarmMusicBrowseItem_getAlarmId(long l, SCIAlarmMusicBrowseItem scialarmmusicbrowseitem);

    public static final native long SCIAlarmMusicBrowseItem_getAlarmMusic(long l, SCIAlarmMusicBrowseItem scialarmmusicbrowseitem);

    public static final native String SCIAlarmMusicBrowseItem_getMusicTitle(long l, SCIAlarmMusicBrowseItem scialarmmusicbrowseitem);

    public static final native String SCIAlarmMusic_getMusicMetadata(long l, SCIAlarmMusic scialarmmusic);

    public static final native String SCIAlarmMusic_getMusicURI(long l, SCIAlarmMusic scialarmmusic);

    public static final native int SCIAlarm_RecurrenceLong_get();

    public static final native int SCIAlarm_RecurrenceNormal_get();

    public static final native int SCIAlarm_RecurrenceShort_get();

    public static final native long SCIAlarm_clone(long l, SCIAlarm scialarm);

    public static final native long SCIAlarm_createSaveAlarmAction(long l, SCIAlarm scialarm);

    public static final native long SCIAlarm_createSaveAlarmOp(long l, SCIAlarm scialarm);

    public static final native long SCIAlarm_getAlarmMusic(long l, SCIAlarm scialarm);

    public static final native String SCIAlarm_getDeviceID(long l, SCIAlarm scialarm);

    public static final native long SCIAlarm_getDuration(long l, SCIAlarm scialarm);

    public static final native boolean SCIAlarm_getEnabled(long l, SCIAlarm scialarm);

    public static final native String SCIAlarm_getFormattedTime(long l, SCIAlarm scialarm);

    public static final native int SCIAlarm_getID(long l, SCIAlarm scialarm);

    public static final native boolean SCIAlarm_getIncludeLinkedZones(long l, SCIAlarm scialarm);

    public static final native String SCIAlarm_getMusicTitle(long l, SCIAlarm scialarm);

    public static final native long SCIAlarm_getRecurrence(long l, SCIAlarm scialarm);

    public static final native String SCIAlarm_getRecurrenceText__SWIG_0(long l, SCIAlarm scialarm);

    public static final native String SCIAlarm_getRecurrenceText__SWIG_1(long l, SCIAlarm scialarm, int i);

    public static final native String SCIAlarm_getSCUriForAlarmMusicBrowse(long l, SCIAlarm scialarm);

    public static final native boolean SCIAlarm_getShuffleEnabled(long l, SCIAlarm scialarm);

    public static final native boolean SCIAlarm_getShuffleMode(long l, SCIAlarm scialarm);

    public static final native long SCIAlarm_getStartTime(long l, SCIAlarm scialarm);

    public static final native int SCIAlarm_getVolume(long l, SCIAlarm scialarm);

    public static final native boolean SCIAlarm_isValid(long l, SCIAlarm scialarm);

    public static final native void SCIAlarm_setAlarmMusic(long l, SCIAlarm scialarm, long l1, SCIAlarmMusic scialarmmusic);

    public static final native void SCIAlarm_setDeviceID(long l, SCIAlarm scialarm, String s);

    public static final native void SCIAlarm_setDuration(long l, SCIAlarm scialarm, long l1, SCITime scitime);

    public static final native void SCIAlarm_setEnabled(long l, SCIAlarm scialarm, boolean flag);

    public static final native void SCIAlarm_setIncludeLinkedZones(long l, SCIAlarm scialarm, boolean flag);

    public static final native void SCIAlarm_setRecurrence(long l, SCIAlarm scialarm, long l1, SCIRecurrence scirecurrence);

    public static final native void SCIAlarm_setShuffleMode(long l, SCIAlarm scialarm, boolean flag);

    public static final native void SCIAlarm_setStartTime(long l, SCIAlarm scialarm, long l1, SCITime scitime);

    public static final native void SCIAlarm_setVolume(long l, SCIAlarm scialarm, int i);

    public static final native void SCIAlarm_subscribe(long l, SCIAlarm scialarm, long l1, SCIEventSink scieventsink);

    public static final native void SCIAlarm_unsubscribe(long l, SCIAlarm scialarm, long l1, SCIEventSink scieventsink);

    public static final native void SCIAppReporting_background(long l, SCIAppReporting sciappreporting);

    public static final native void SCIAppReporting_focus(long l, SCIAppReporting sciappreporting, int i);

    public static final native void SCIAppReporting_foreground(long l, SCIAppReporting sciappreporting);

    public static final native void SCIAppReporting_interaction(long l, SCIAppReporting sciappreporting, int i, int j, int k);

    public static final native void SCIAppReporting_reportEventWithProps(long l, SCIAppReporting sciappreporting, String s, String s1, long l1, SCIPropertyBag scipropertybag);

    public static final native void SCIAppReporting_updateScreenResolution(long l, SCIAppReporting sciappreporting, int i, int j);

    public static final native void SCIAppReporting_viewClose(long l, SCIAppReporting sciappreporting, int i);

    public static final native void SCIAppReporting_viewOpen(long l, SCIAppReporting sciappreporting, int i);

    public static final native void SCIAppReporting_viewPropChange(long l, SCIAppReporting sciappreporting, int i, int j, int k, int i1, int j1);

    public static final native void SCIArtworkCacheManager_emptyAllCaches(long l, SCIArtworkCacheManager sciartworkcachemanager);

    public static final native long SCIArtworkCacheManager_getArtworkCache(long l, SCIArtworkCacheManager sciartworkcachemanager, long l1);

    public static final native long SCIArtworkCacheManager_getLogoArtworkCache(long l, SCIArtworkCacheManager sciartworkcachemanager);

    public static final native long SCIArtworkCacheManager_getLogoArtworkCacheWithFormat(long l, SCIArtworkCacheManager sciartworkcachemanager, int i);

    public static final native long SCIArtworkCacheManager_setUpNewArtworkCache(long l, SCIArtworkCacheManager sciartworkcachemanager, long l1, long l2, long l3);

    public static final native long SCIArtworkCacheManager_setUpNewCompressedArtworkCache(long l, SCIArtworkCacheManager sciartworkcachemanager, long l1, long l2, long l3);

    public static final native void SCIArtworkCacheManager_tearDownArtworkCache(long l, SCIArtworkCacheManager sciartworkcachemanager, long l1);

    public static final native void SCIArtworkCache_emptyCache(long l, SCIArtworkCache sciartworkcache);

    public static final native long SCIArtworkCache_getTotalSizeInBytes(long l, SCIArtworkCache sciartworkcache);

    public static final native long SCIArtworkCache_requestArtwork(long l, SCIArtworkCache sciartworkcache, String s, int i);

    public static final native void SCIArtworkData_clearFileData(long l, SCIArtworkData sciartworkdata);

    public static final native void SCIArtworkData_getData(long l, SCIArtworkData sciartworkdata, byte abyte0[], long l1);

    public static final native int SCIArtworkData_getDataFormat(long l, SCIArtworkData sciartworkdata);

    public static final native int SCIArtworkData_getFailureCode(long l, SCIArtworkData sciartworkdata);

    public static final native long SCIArtworkData_getSizeInBytes(long l, SCIArtworkData sciartworkdata);

    public static final native long SCIArtworkData_getSizeInPixels(long l, SCIArtworkData sciartworkdata);

    public static final native int SCIArtworkData_getType(long l, SCIArtworkData sciartworkdata);

    public static final native String SCIArtworkData_getURL(long l, SCIArtworkData sciartworkdata);

    public static final native long SCIArtworkData_getXPixels(long l, SCIArtworkData sciartworkdata);

    public static final native long SCIArtworkData_getYPixels(long l, SCIArtworkData sciartworkdata);

    public static final native boolean SCIArtworkData_hasFailed(long l, SCIArtworkData sciartworkdata);

    public static final native boolean SCIArtworkData_isReady(long l, SCIArtworkData sciartworkdata);

    public static final native boolean SCIArtworkData_isStillWorking(long l, SCIArtworkData sciartworkdata);

    public static final native void SCIArtworkData_subscribe(long l, SCIArtworkData sciartworkdata, long l1, SCIEventSink scieventsink);

    public static final native void SCIArtworkData_unsubscribe(long l, SCIArtworkData sciartworkdata, long l1, SCIEventSink scieventsink);

    public static final native long SCIAudibleManager_createAddAccountAction(long l, SCIAudibleManager sciaudiblemanager);

    public static final native String SCIAudibleManager_getServiceDescriptorID(long l, SCIAudibleManager sciaudiblemanager);

    public static final native boolean SCIAudibleManager_isActivatedDeviceInHousehold(long l, SCIAudibleManager sciaudiblemanager);

    public static final native void SCIAudibleManager_update(long l, SCIAudibleManager sciaudiblemanager);

    public static final native int SCIAudioData_getTimeSeekOffset(long l, SCIAudioData sciaudiodata, long l1);

    public static final native long SCIAudioData_getTotalTime(long l, SCIAudioData sciaudiodata);

    public static final native boolean SCIAudioInputResource_doesSetAutoPlayGroup(long l, SCIAudioInputResource sciaudioinputresource);

    public static final native int SCIAudioInputResource_getIconID(long l, SCIAudioInputResource sciaudioinputresource);

    public static final native String SCIAudioInputResource_getLineID(long l, SCIAudioInputResource sciaudioinputresource);

    public static final native String SCIAudioInputResource_getName(long l, SCIAudioInputResource sciaudioinputresource);

    public static final native int SCIAudioInputResource_getVolume(long l, SCIAudioInputResource sciaudioinputresource);

    public static final native String SCIBOOLEANSETTINGSPROPERTY_INTERFACE_get();

    public static final native String SCIBROWSEDATASOURCE_INTERFACE_get();

    public static final native String SCIBROWSEDATASOURCE_ONBROWSECHANGED_EVENT_get();

    public static final native String SCIBROWSEDATASOURCE_ONINVALIDATION_EVENT_get();

    public static final native String SCIBROWSEDATASOURCE_ONPOWERSCROLLINFO_EVENT_get();

    public static final native String SCIBROWSEDATASOURCE_ONQUEUECURRENTITEMCHANGED_EVENT_get();

    public static final native String SCIBROWSEDATASOURCE_ONQUEUEINUSECHANGED_EVENT_get();

    public static final native String SCIBROWSEGROUPSINFO_INTERFACE_get();

    public static final native String SCIBROWSEITEM_INTERFACE_get();

    public static final native String SCIBROWSEITEM_ONITEMCHANGED_EVENT_get();

    public static final native String SCIBROWSEMANAGER_INTERFACE_get();

    public static final native String SCIBROWSESERVICE_INTERFACE_get();

    public static final native String SCIBROWSESTACKMANAGER_INTERFACE_get();

    public static final native String SCIBROWSESTACKMANAGER_ONBROWSEINVALIDATION_EVENT_get();

    public static final native String SCIBROWSESTACKMANAGER_ONSTACKINVALIDATION_EVENT_get();

    public static final native boolean SCIBooleanSettingsProperty_getValue(long l, SCIBooleanSettingsProperty scibooleansettingsproperty);

    public static final native int SCIBrowseDataSource_BUTTON_EDIT_get();

    public static final native int SCIBrowseDataSource_MENU_EDIT_get();

    public static final native int SCIBrowseDataSource_MESSAGE_EMPTY_get();

    public static final native int SCIBrowseDataSource_MESSAGE_LAST_ERROR_get();

    public static final native int SCIBrowseDataSource_SUBTITLE_DEFAULT_get();

    public static final native int SCIBrowseDataSource_TITLE_DEFAULT_get();

    public static final native int SCIBrowseDataSource_TITLE_EDIT_get();

    public static final native long SCIBrowseDataSource_getActions(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native long SCIBrowseDataSource_getActionsOnSelectedItems(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native long SCIBrowseDataSource_getFilteredActions(long l, SCIBrowseDataSource scibrowsedatasource, long l1, SCIActionFilter sciactionfilter);

    public static final native long SCIBrowseDataSource_getItemAtIndex(long l, SCIBrowseDataSource scibrowsedatasource, long l1);

    public static final native int SCIBrowseDataSource_getLastBrowseResult(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native long SCIBrowseDataSource_getLastErrorAction(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native long SCIBrowseDataSource_getMoreMenuDataSource(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native long SCIBrowseDataSource_getNumItems(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native long SCIBrowseDataSource_getPostModificationIndex(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native int SCIBrowseDataSource_getPresentationArtworkArtType(long l, SCIBrowseDataSource scibrowsedatasource, int i);

    public static final native String SCIBrowseDataSource_getPresentationArtworkURL(long l, SCIBrowseDataSource scibrowsedatasource, int i, long l1);

    public static final native String SCIBrowseDataSource_getPresentationText(long l, SCIBrowseDataSource scibrowsedatasource, int i);

    public static final native String SCIBrowseDataSource_getSCUri(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native long SCIBrowseDataSource_getSelectionManager(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native long SCIBrowseDataSource_getSupportedActionCategories(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native boolean SCIBrowseDataSource_isGone(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native boolean SCIBrowseDataSource_isLastErrorPermanent(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native boolean SCIBrowseDataSource_isLastItemBrowseResultFailure(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native boolean SCIBrowseDataSource_isSearchResult(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native boolean SCIBrowseDataSource_isValid(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native boolean SCIBrowseDataSource_refreshBrowse(long l, SCIBrowseDataSource scibrowsedatasource);

    public static final native void SCIBrowseDataSource_subscribe(long l, SCIBrowseDataSource scibrowsedatasource, long l1, SCIEventSink scieventsink);

    public static final native void SCIBrowseDataSource_unsubscribe(long l, SCIBrowseDataSource scibrowsedatasource, long l1, SCIEventSink scieventsink);

    public static final native int SCIBrowseGroupsInfo_getArtTypeForGroup(long l, SCIBrowseGroupsInfo scibrowsegroupsinfo, long l1);

    public static final native String SCIBrowseGroupsInfo_getArtURLForGroup__SWIG_0(long l, SCIBrowseGroupsInfo scibrowsegroupsinfo, long l1);

    public static final native String SCIBrowseGroupsInfo_getArtURLForGroup__SWIG_1(long l, SCIBrowseGroupsInfo scibrowsegroupsinfo, long l1, long l2);

    public static final native String SCIBrowseGroupsInfo_getBrowseableSCUriForGroup(long l, SCIBrowseGroupsInfo scibrowsegroupsinfo, long l1);

    public static final native String SCIBrowseGroupsInfo_getDescriptionForGroup(long l, SCIBrowseGroupsInfo scibrowsegroupsinfo, long l1);

    public static final native long SCIBrowseGroupsInfo_getNumGroups(long l, SCIBrowseGroupsInfo scibrowsegroupsinfo);

    public static final native long SCIBrowseGroupsInfo_getStartIndexForGroup(long l, SCIBrowseGroupsInfo scibrowsegroupsinfo, long l1);

    public static final native String SCIBrowseGroupsInfo_getTitleForGroup(long l, SCIBrowseGroupsInfo scibrowsegroupsinfo, long l1);

    public static final native boolean SCIBrowseGroupsInfo_shouldDisplayHeaderForGroup(long l, SCIBrowseGroupsInfo scibrowsegroupsinfo, long l1);

    public static final native void SCIBrowseItemSwigBase_change_ownership(SCIBrowseItemSwigBase scibrowseitemswigbase, long l, boolean flag);

    public static final native void SCIBrowseItemSwigBase_director_connect(SCIBrowseItemSwigBase scibrowseitemswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCIBrowseItemSwigBase_dumpSCIObj(long l, SCIBrowseItemSwigBase scibrowseitemswigbase);

    public static final native String SCIBrowseItemSwigBase_dumpSCIObjSwigExplicitSCIBrowseItemSwigBase(long l, SCIBrowseItemSwigBase scibrowseitemswigbase);

    public static final native boolean SCIBrowseItem_canActOn(long l, SCIBrowseItem scibrowseitem);

    public static final native boolean SCIBrowseItem_canPush(long l, SCIBrowseItem scibrowseitem);

    public static final native long SCIBrowseItem_getActions(long l, SCIBrowseItem scibrowseitem);

    public static final native int SCIBrowseItem_getAlbumArtType(long l, SCIBrowseItem scibrowseitem);

    public static final native String SCIBrowseItem_getAlbumArtURL__SWIG_0(long l, SCIBrowseItem scibrowseitem);

    public static final native String SCIBrowseItem_getAlbumArtURL__SWIG_1(long l, SCIBrowseItem scibrowseitem, long l1);

    public static final native long SCIBrowseItem_getAttributes(long l, SCIBrowseItem scibrowseitem);

    public static final native String SCIBrowseItem_getBrowseItemText(long l, SCIBrowseItem scibrowseitem, int i);

    public static final native long SCIBrowseItem_getFilteredActions(long l, SCIBrowseItem scibrowseitem, long l1, SCIActionFilter sciactionfilter);

    public static final native long SCIBrowseItem_getMoreMenuDataSource(long l, SCIBrowseItem scibrowseitem);

    public static final native String SCIBrowseItem_getOrdinal(long l, SCIBrowseItem scibrowseitem);

    public static final native String SCIBrowseItem_getPrimaryAdornedTitle(long l, SCIBrowseItem scibrowseitem);

    public static final native String SCIBrowseItem_getPrimaryTitle(long l, SCIBrowseItem scibrowseitem);

    public static final native String SCIBrowseItem_getSCUri(long l, SCIBrowseItem scibrowseitem);

    public static final native String SCIBrowseItem_getSecondaryTitle(long l, SCIBrowseItem scibrowseitem);

    public static final native boolean SCIBrowseItem_hasOrdinal(long l, SCIBrowseItem scibrowseitem);

    public static final native boolean SCIBrowseItem_isBrowseItemTextAvailable(long l, SCIBrowseItem scibrowseitem, int i);

    public static final native boolean SCIBrowseItem_isDataAvailable(long l, SCIBrowseItem scibrowseitem);

    public static final native boolean SCIBrowseItem_isParentOfSearch(long l, SCIBrowseItem scibrowseitem);

    public static final native boolean SCIBrowseItem_isSecondaryTitleValid(long l, SCIBrowseItem scibrowseitem);

    public static final native void SCIBrowseItem_subscribe(long l, SCIBrowseItem scibrowseitem, long l1, SCIEventSink scieventsink, boolean flag);

    public static final native void SCIBrowseItem_unsubscribe(long l, SCIBrowseItem scibrowseitem, long l1, SCIEventSink scieventsink);

    public static final native int SCIBrowseListPresentationMap_getPresentationForDataSource(long l, SCIBrowseListPresentationMap scibrowselistpresentationmap, long l1, SCIBrowseDataSource scibrowsedatasource);

    public static final native int SCIBrowseListPresentationMap_getPresentationForPushedItem(long l, SCIBrowseListPresentationMap scibrowselistpresentationmap, long l1, SCIBrowseItem scibrowseitem);

    public static final native void SCIBrowseManager_resumePolling(long l, SCIBrowseManager scibrowsemanager);

    public static final native void SCIBrowseManager_suspendPolling(long l, SCIBrowseManager scibrowsemanager);

    public static final native void SCIBrowseStackManager_addExclusiveDataSourceFilter(long l, SCIBrowseStackManager scibrowsestackmanager, String s, long l1, SCIStringArray scistringarray);

    public static final native boolean SCIBrowseStackManager_canPushExternalSources(long l, SCIBrowseStackManager scibrowsestackmanager);

    public static final native void SCIBrowseStackManager_clear(long l, SCIBrowseStackManager scibrowsestackmanager);

    public static final native void SCIBrowseStackManager_clearAllDataSourceFilters(long l, SCIBrowseStackManager scibrowsestackmanager);

    public static final native long SCIBrowseStackManager_getActions(long l, SCIBrowseStackManager scibrowsestackmanager);

    public static final native String SCIBrowseStackManager_getBrowseCPIDForTopUri(long l, SCIBrowseStackManager scibrowsestackmanager);

    public static final native int SCIBrowseStackManager_getHeaderAlbumArtType(long l, SCIBrowseStackManager scibrowsestackmanager);

    public static final native String SCIBrowseStackManager_getHeaderAlbumArtURL(long l, SCIBrowseStackManager scibrowsestackmanager);

    public static final native int SCIBrowseStackManager_getNumberOfStackLevelsInvalidated(long l, SCIBrowseStackManager scibrowsestackmanager);

    public static final native String SCIBrowseStackManager_getServiceDescriptorIDForTopUri(long l, SCIBrowseStackManager scibrowsestackmanager);

    public static final native long SCIBrowseStackManager_getTopDataSource(long l, SCIBrowseStackManager scibrowsestackmanager);

    public static final native String SCIBrowseStackManager_getTopTitle(long l, SCIBrowseStackManager scibrowsestackmanager);

    public static final native String SCIBrowseStackManager_getTopUri(long l, SCIBrowseStackManager scibrowsestackmanager);

    public static final native boolean SCIBrowseStackManager_isTopUriASearch(long l, SCIBrowseStackManager scibrowsestackmanager);

    public static final native boolean SCIBrowseStackManager_pop(long l, SCIBrowseStackManager scibrowsestackmanager);

    public static final native int SCIBrowseStackManager_popToSCUri(long l, SCIBrowseStackManager scibrowsestackmanager, String s);

    public static final native int SCIBrowseStackManager_push__SWIG_0(long l, SCIBrowseStackManager scibrowsestackmanager, String s);

    public static final native int SCIBrowseStackManager_push__SWIG_1(long l, SCIBrowseStackManager scibrowsestackmanager, String s, String s1);

    public static final native boolean SCIBrowseStackManager_setSearchTerm(long l, SCIBrowseStackManager scibrowsestackmanager, String s);

    public static final native void SCIBrowseStackManager_subscribe(long l, SCIBrowseStackManager scibrowsestackmanager, long l1, SCIEventSink scieventsink);

    public static final native void SCIBrowseStackManager_unsubscribe(long l, SCIBrowseStackManager scibrowsestackmanager, long l1, SCIEventSink scieventsink);

    public static final native String SCICOMMITTABLE_INTERFACE_get();

    public static final native String SCICONFIGUREWIRELESSWIZARD_INTERFACE_get();

    public static final native String SCICONFIGUREWIRELESSWIZARD_ONSTATECHANGED_EVENT_get();

    public static final native String SCICOUNTRYLIST_INTERFACE_get();

    public static final native String SCICOUNTRY_INTERFACE_get();

    public static final native void SCICommittable_commit(long l, SCICommittable scicommittable);

    public static final native boolean SCICommittable_isCommitted(long l, SCICommittable scicommittable);

    public static final native int SCIConfigureWirelessWizard_CONFIGUREWIRELESS_STRID_BODY_get();

    public static final native int SCIConfigureWirelessWizard_CONFIGUREWIRELESS_STRID_TITLE_1_get();

    public static final native int SCIConfigureWirelessWizard_STATE_CONFIGUREWIRELESS_COMPLETE_get();

    public static final native int SCIConfigureWirelessWizard_STATE_CONFIGUREWIRELESS_INIT_get();

    public static final native int SCIConfigureWirelessWizard_STATE_CONFIGUREWIRELESS_UNKNOWN_get();

    public static final native long SCIConfigureWirelessWizard_createSCINetstartGetScanListOp(long l, SCIConfigureWirelessWizard sciconfigurewirelesswizard);

    public static final native long SCIConfigureWirelessWizard_getOriginalWifiInfo(long l, SCIConfigureWirelessWizard sciconfigurewirelesswizard);

    public static final native long SCIConfigureWirelessWizard_getPasswordInput(long l, SCIConfigureWirelessWizard sciconfigurewirelesswizard);

    public static final native String SCIConfigureWirelessWizard_getSSID(long l, SCIConfigureWirelessWizard sciconfigurewirelesswizard);

    public static final native long SCIConfigureWirelessWizard_getSSIDInput(long l, SCIConfigureWirelessWizard sciconfigurewirelesswizard);

    public static final native long SCIConfigureWirelessWizard_getScanListEntries(long l, SCIConfigureWirelessWizard sciconfigurewirelesswizard);

    public static final native int SCIConfigureWirelessWizard_getScanListNumEntries(long l, SCIConfigureWirelessWizard sciconfigurewirelesswizard);

    public static final native boolean SCIConfigureWirelessWizard_hasPassword(long l, SCIConfigureWirelessWizard sciconfigurewirelesswizard);

    public static final native boolean SCIConfigureWirelessWizard_isOpenNetwork(long l, SCIConfigureWirelessWizard sciconfigurewirelesswizard, String s);

    public static final native void SCIConfigureWirelessWizard_setPassword(long l, SCIConfigureWirelessWizard sciconfigurewirelesswizard, String s);

    public static final native void SCIConfigureWirelessWizard_setPropBagProp(long l, SCIConfigureWirelessWizard sciconfigurewirelesswizard, String s, long l1, SCIPropertyBag scipropertybag);

    public static final native void SCIConfigureWirelessWizard_setSSID(long l, SCIConfigureWirelessWizard sciconfigurewirelesswizard, String s);

    public static final native boolean SCIConfigureWirelessWizard_unexpectedDisconnectFromSonosAP(long l, SCIConfigureWirelessWizard sciconfigurewirelesswizard);

    public static final native String SCICountry_getCode(long l, SCICountry scicountry);

    public static final native String SCICountry_getName(long l, SCICountry scicountry);

    public static final native void SCICustomSubWizardSwigBase_change_ownership(SCICustomSubWizardSwigBase scicustomsubwizardswigbase, long l, boolean flag);

    public static final native void SCICustomSubWizardSwigBase_director_connect(SCICustomSubWizardSwigBase scicustomsubwizardswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCICustomSubWizardSwigBase_dumpSCIObj(long l, SCICustomSubWizardSwigBase scicustomsubwizardswigbase);

    public static final native String SCICustomSubWizardSwigBase_dumpSCIObjSwigExplicitSCICustomSubWizardSwigBase(long l, SCICustomSubWizardSwigBase scicustomsubwizardswigbase);

    public static final native boolean SCICustomSubWizard_canClientCancelWizard(long l, SCICustomSubWizard scicustomsubwizard);

    public static final native boolean SCICustomSubWizard_canClientTransitionToNextState(long l, SCICustomSubWizard scicustomsubwizard);

    public static final native boolean SCICustomSubWizard_canClientTransitionToPreviousState(long l, SCICustomSubWizard scicustomsubwizard);

    public static final native int SCICustomSubWizard_enter(long l, SCICustomSubWizard scicustomsubwizard, long l1, SCIWizard sciwizard);

    public static final native void SCICustomSubWizard_exit(long l, SCICustomSubWizard scicustomsubwizard);

    public static final native int SCICustomSubWizard_getNextStateID(long l, SCICustomSubWizard scicustomsubwizard);

    public static final native long SCICustomSubWizard_getPropertyBag(long l, SCICustomSubWizard scicustomsubwizard);

    public static final native String SCICustomSubWizard_getRecommendedLabelForNextState(long l, SCICustomSubWizard scicustomsubwizard);

    public static final native void SCICustomSubWizard_onSubWizardStateTransition(long l, SCICustomSubWizard scicustomsubwizard, int i);

    public static final native String SCIDATA_INTERFACE_get();

    public static final native String SCIDATETIMEMANAGER_INTERFACE_get();

    public static final native String SCIDATETIMEMANAGER_ONDATEFORMATCHANGED_EVENT_get();

    public static final native String SCIDATETIMEMANAGER_ONTIMEFORMATCHANGED_EVENT_get();

    public static final native String SCIDATETIMEMANAGER_ONTIMEGENERATIONCHANGED_EVENT_get();

    public static final native String SCIDATETIMEMANAGER_ONTIMESERVERCHANGED_EVENT_get();

    public static final native String SCIDATETIMEMANAGER_ONTIMESTATUSCHANGED_EVENT_get();

    public static final native String SCIDATETIMEMANAGER_ONTIMEZONECHANGED_EVENT_get();

    public static final native String SCIDATETIMESETTINGSPROPERTY_INTERFACE_get();

    public static final native String SCIDEALERMODE_INTERFACE_get();

    public static final native String SCIDEBUG_INTERFACE_get();

    public static final native String SCIDEVICEAUTOPLAY_INTERFACE_get();

    public static final native String SCIDEVICELINEIN_INTERFACE_get();

    public static final native String SCIDEVICELINEOUT_INTERFACE_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_INTERFACE_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONBALANCELEVELCHANGED_EVENT_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONBASSLEVELCHANGED_EVENT_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONCROSSOVERCHANGED_EVENT_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONFIXEDOUTPUT_EVENT_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONLOUDNESSCHANGED_EVENT_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONNIGHTMODECHANGED_EVENT_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONSUBENABLEDCHANGED_EVENT_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONSUBGAINCHANGED_EVENT_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONSUBPOLARITYCHANGED_EVENT_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONSURROUNDENABLEDCHANGED_EVENT_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONSURROUNDLEVELCHANGED_EVENT_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONSURROUNDMODECHANGED_EVENT_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONTREBLELEVELCHANGED_EVENT_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONTVAUDIODELAYCHANGED_EVENT_get();

    public static final native String SCIDEVICEMUSICEQUALIZATION_ONTVDIALOGLEVELCHANGED_EVENT_get();

    public static final native String SCIDEVICESETTINGSDATASOURCE_INTERFACE_get();

    public static final native String SCIDEVICEVOLUME_INTERFACE_get();

    public static final native String SCIDEVICE_INTERFACE_get();

    public static final native void SCIData_endReading(long l, SCIData scidata);

    public static final native int SCIData_getBytes(long l, SCIData scidata, long l1, String s, long l2);

    public static final native String SCIData_getMimeType(long l, SCIData scidata);

    public static final native boolean SCIData_prepareForReading(long l, SCIData scidata);

    public static final native int SCIData_setImageBytes(long l, SCIData scidata, byte abyte0[], long l1);

    public static final native long SCIData_totalBytes(long l, SCIData scidata);

    public static final native long SCIDateTimeManager_createSetCurrentLocalTimeOp(long l, SCIDateTimeManager scidatetimemanager, long l1, SCISystemTime scisystemtime);

    public static final native long SCIDateTimeManager_createSetDateFormatOp(long l, SCIDateTimeManager scidatetimemanager, int i);

    public static final native long SCIDateTimeManager_createSetTimeFormatOp(long l, SCIDateTimeManager scidatetimemanager, int i);

    public static final native long SCIDateTimeManager_createSetTimeZoneOp(long l, SCIDateTimeManager scidatetimemanager, long l1, SCITimeZone scitimezone);

    public static final native long SCIDateTimeManager_createSwitchToInternetTimeOp(long l, SCIDateTimeManager scidatetimemanager);

    public static final native long SCIDateTimeManager_createSwitchToManualTimeOp(long l, SCIDateTimeManager scidatetimemanager);

    public static final native long SCIDateTimeManager_getCurrentLocalTime(long l, SCIDateTimeManager scidatetimemanager);

    public static final native long SCIDateTimeManager_getCurrentTime(long l, SCIDateTimeManager scidatetimemanager);

    public static final native int SCIDateTimeManager_getDateFormat(long l, SCIDateTimeManager scidatetimemanager);

    public static final native String SCIDateTimeManager_getFormattedDate(long l, SCIDateTimeManager scidatetimemanager, long l1, SCISystemTime scisystemtime);

    public static final native String SCIDateTimeManager_getFormattedLocalDate__SWIG_0(long l, SCIDateTimeManager scidatetimemanager);

    public static final native String SCIDateTimeManager_getFormattedLocalDate__SWIG_1(long l, SCIDateTimeManager scidatetimemanager, boolean flag, boolean flag1);

    public static final native String SCIDateTimeManager_getFormattedLocalTime(long l, SCIDateTimeManager scidatetimemanager);

    public static final native String SCIDateTimeManager_getFormattedTime(long l, SCIDateTimeManager scidatetimemanager, long l1, SCITime scitime);

    public static final native long SCIDateTimeManager_getTimeAsLocalTime(long l, SCIDateTimeManager scidatetimemanager, long l1, SCISystemTime scisystemtime);

    public static final native int SCIDateTimeManager_getTimeFormat(long l, SCIDateTimeManager scidatetimemanager);

    public static final native int SCIDateTimeManager_getTimeStatus(long l, SCIDateTimeManager scidatetimemanager);

    public static final native long SCIDateTimeManager_getTimeZone(long l, SCIDateTimeManager scidatetimemanager);

    public static final native int SCIDateTimeManager_getTimeZoneIndex(long l, SCIDateTimeManager scidatetimemanager);

    public static final native long SCIDateTimeManager_getTimeZones(long l, SCIDateTimeManager scidatetimemanager);

    public static final native boolean SCIDateTimeManager_isTimeAvailable(long l, SCIDateTimeManager scidatetimemanager);

    public static final native void SCIDateTimeManager_subscribe(long l, SCIDateTimeManager scidatetimemanager, long l1, SCIEventSink scieventsink);

    public static final native void SCIDateTimeManager_unsubscribe(long l, SCIDateTimeManager scidatetimemanager, long l1, SCIEventSink scieventsink);

    public static final native String SCIDateTimeSettingsProperty_getFormattedValue(long l, SCIDateTimeSettingsProperty scidatetimesettingsproperty);

    public static final native long SCIDateTimeSettingsProperty_getValue(long l, SCIDateTimeSettingsProperty scidatetimesettingsproperty);

    public static final native boolean SCIDealerMode_checkStoresAlivePIN(long l, SCIDealerMode scidealermode, String s);

    public static final native void SCIDealerMode_controllerWokeUp(long l, SCIDealerMode scidealermode);

    public static final native void SCIDealerMode_deleteAllSonosPlaylists(long l, SCIDealerMode scidealermode);

    public static final native boolean SCIDealerMode_isDealerLock(long l, SCIDealerMode scidealermode);

    public static final native boolean SCIDealerMode_isStoresAliveEnabled__SWIG_0(long l, SCIDealerMode scidealermode, boolean flag);

    public static final native boolean SCIDealerMode_isStoresAliveEnabled__SWIG_1(long l, SCIDealerMode scidealermode);

    public static final native boolean SCIDealerMode_sendControllerAlive(long l, SCIDealerMode scidealermode);

    public static final native void SCIDealerMode_setDealerLock(long l, SCIDealerMode scidealermode, boolean flag);

    public static final native void SCIDealerMode_setStoresAlive(long l, SCIDealerMode scidealermode, boolean flag);

    public static final native void SCIDebug_forceCrash(long l, SCIDebug scidebug);

    public static final native int SCIDebug_getSCLibObjectCount(long l, SCIDebug scidebug);

    public static final native void SCIDebug_setDiagnosticLevel(long l, SCIDebug scidebug, String s, int i);

    public static final native void SCIDebug_setGlobalOpsDelay(long l, SCIDebug scidebug, int i);

    public static final native void SCIDebug_setMaxListenerCountThreshold(long l, SCIDebug scidebug, int i);

    public static final native void SCIDebug_setNetworkIOFailureCondition(long l, SCIDebug scidebug, int i, int j, String s);

    public static final native long SCIDeviceAutoplay_createGetAutoplayDeviceOp(long l, SCIDeviceAutoplay scideviceautoplay);

    public static final native long SCIDeviceAutoplay_createGetAutoplayGroupedZoneOp(long l, SCIDeviceAutoplay scideviceautoplay);

    public static final native long SCIDeviceAutoplay_createGetAutoplayVolumeOp(long l, SCIDeviceAutoplay scideviceautoplay);

    public static final native long SCIDeviceAutoplay_createGetUseAutoplayVolumeOp(long l, SCIDeviceAutoplay scideviceautoplay);

    public static final native long SCIDeviceAutoplay_createSetAutoplayDeviceOp(long l, SCIDeviceAutoplay scideviceautoplay, String s);

    public static final native long SCIDeviceAutoplay_createSetAutoplayGroupedZoneOp(long l, SCIDeviceAutoplay scideviceautoplay, boolean flag);

    public static final native long SCIDeviceAutoplay_createSetAutoplayVolumeOp(long l, SCIDeviceAutoplay scideviceautoplay, long l1);

    public static final native long SCIDeviceAutoplay_createSetUseAutoplayVolumeOp(long l, SCIDeviceAutoplay scideviceautoplay, boolean flag);

    public static final native long SCIDeviceLineIn_createGetLineInLevelOp(long l, SCIDeviceLineIn scidevicelinein);

    public static final native long SCIDeviceLineIn_createGetLineInNameAndIconOp(long l, SCIDeviceLineIn scidevicelinein);

    public static final native long SCIDeviceLineIn_createRenameLineInOp(long l, SCIDeviceLineIn scidevicelinein, String s, int i);

    public static final native long SCIDeviceLineIn_createSetLineInLevelOp(long l, SCIDeviceLineIn scidevicelinein, long l1);

    public static final native String SCIDeviceLineIn_getLineInLevelStr(long l, SCIDeviceLineIn scidevicelinein, long l1);

    public static final native int SCIDeviceLineIn_getNumLineInLevels(long l, SCIDeviceLineIn scidevicelinein);

    public static final native long SCIDeviceLineOut_createGetLineOutFixed(long l, SCIDeviceLineOut scidevicelineout);

    public static final native long SCIDeviceLineOut_createGetSupportsLineOutFixed(long l, SCIDeviceLineOut scidevicelineout);

    public static final native long SCIDeviceLineOut_createSetLineOutLevelOp(long l, SCIDeviceLineOut scidevicelineout, boolean flag);

    public static final native int SCIDeviceMusicEqualization_MAX_BALANCE_LEVEL_get();

    public static final native int SCIDeviceMusicEqualization_MAX_BASS_LEVEL_get();

    public static final native int SCIDeviceMusicEqualization_MAX_SUB_GAIN_get();

    public static final native int SCIDeviceMusicEqualization_MAX_SURROUND_LEVEL_get();

    public static final native int SCIDeviceMusicEqualization_MAX_TREBLE_LEVEL_get();

    public static final native int SCIDeviceMusicEqualization_MAX_TV_AUDIO_DELAY_get();

    public static final native int SCIDeviceMusicEqualization_MIN_BALANCE_LEVEL_get();

    public static final native int SCIDeviceMusicEqualization_MIN_BASS_LEVEL_get();

    public static final native int SCIDeviceMusicEqualization_MIN_SUB_GAIN_get();

    public static final native int SCIDeviceMusicEqualization_MIN_SURROUND_LEVEL_get();

    public static final native int SCIDeviceMusicEqualization_MIN_TREBLE_LEVEL_get();

    public static final native int SCIDeviceMusicEqualization_MIN_TV_AUDIO_DELAY_get();

    public static final native long SCIDeviceMusicEqualization_createRefreshCacheOp(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native long SCIDeviceMusicEqualization_createResetAllOp(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native long SCIDeviceMusicEqualization_createSetBassLevelOp(long l, SCIDeviceMusicEqualization scidevicemusicequalization, int i);

    public static final native long SCIDeviceMusicEqualization_createSetLeftRightBalanceLevelOp(long l, SCIDeviceMusicEqualization scidevicemusicequalization, int i);

    public static final native long SCIDeviceMusicEqualization_createSetLoudnessBoostOp(long l, SCIDeviceMusicEqualization scidevicemusicequalization, boolean flag);

    public static final native long SCIDeviceMusicEqualization_createSetTrebleLevelOp(long l, SCIDeviceMusicEqualization scidevicemusicequalization, int i);

    public static final native int SCIDeviceMusicEqualization_getBassLevel(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native int SCIDeviceMusicEqualization_getCrossover(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native int SCIDeviceMusicEqualization_getCrossoverIndex(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native long SCIDeviceMusicEqualization_getCrossoverIndexLabels(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native int SCIDeviceMusicEqualization_getLeftRightBalanceLevel(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native boolean SCIDeviceMusicEqualization_getLoudnessBoost(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native boolean SCIDeviceMusicEqualization_getNightMode(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native String SCIDeviceMusicEqualization_getRecommendedTitle(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native boolean SCIDeviceMusicEqualization_getSubEnabled(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native int SCIDeviceMusicEqualization_getSubGain(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native boolean SCIDeviceMusicEqualization_getSubPolarity(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native boolean SCIDeviceMusicEqualization_getSurroundEnabled(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native int SCIDeviceMusicEqualization_getSurroundLevel(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native int SCIDeviceMusicEqualization_getSurroundMode(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native int SCIDeviceMusicEqualization_getTVAudioDelay(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native boolean SCIDeviceMusicEqualization_getTVDialogEnhancement(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native int SCIDeviceMusicEqualization_getTrebleLevel(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native boolean SCIDeviceMusicEqualization_isValid(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native void SCIDeviceMusicEqualization_resetBasicEq(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native void SCIDeviceMusicEqualization_resetSubEq(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native void SCIDeviceMusicEqualization_resetSurround(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native void SCIDeviceMusicEqualization_resetTVDialog(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native void SCIDeviceMusicEqualization_setBassLevel(long l, SCIDeviceMusicEqualization scidevicemusicequalization, int i);

    public static final native void SCIDeviceMusicEqualization_setCrossover(long l, SCIDeviceMusicEqualization scidevicemusicequalization, int i);

    public static final native void SCIDeviceMusicEqualization_setCrossoverIndex(long l, SCIDeviceMusicEqualization scidevicemusicequalization, int i);

    public static final native void SCIDeviceMusicEqualization_setLeftRightBalanceLevel(long l, SCIDeviceMusicEqualization scidevicemusicequalization, int i);

    public static final native void SCIDeviceMusicEqualization_setLoudnessBoost(long l, SCIDeviceMusicEqualization scidevicemusicequalization, boolean flag);

    public static final native void SCIDeviceMusicEqualization_setNightMode(long l, SCIDeviceMusicEqualization scidevicemusicequalization, boolean flag);

    public static final native void SCIDeviceMusicEqualization_setSubEnabled(long l, SCIDeviceMusicEqualization scidevicemusicequalization, boolean flag);

    public static final native void SCIDeviceMusicEqualization_setSubGain(long l, SCIDeviceMusicEqualization scidevicemusicequalization, int i);

    public static final native void SCIDeviceMusicEqualization_setSubPolarity(long l, SCIDeviceMusicEqualization scidevicemusicequalization, boolean flag);

    public static final native void SCIDeviceMusicEqualization_setSurroundEnabled(long l, SCIDeviceMusicEqualization scidevicemusicequalization, boolean flag);

    public static final native void SCIDeviceMusicEqualization_setSurroundLevel(long l, SCIDeviceMusicEqualization scidevicemusicequalization, int i);

    public static final native void SCIDeviceMusicEqualization_setSurroundMode(long l, SCIDeviceMusicEqualization scidevicemusicequalization, int i);

    public static final native void SCIDeviceMusicEqualization_setTVAudioDelay(long l, SCIDeviceMusicEqualization scidevicemusicequalization, int i);

    public static final native void SCIDeviceMusicEqualization_setTVDialogEnhancement(long l, SCIDeviceMusicEqualization scidevicemusicequalization, boolean flag);

    public static final native void SCIDeviceMusicEqualization_setTrebleLevel(long l, SCIDeviceMusicEqualization scidevicemusicequalization, int i);

    public static final native boolean SCIDeviceMusicEqualization_shouldShowBalance(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native boolean SCIDeviceMusicEqualization_shouldShowCrossoverAdjust(long l, SCIDeviceMusicEqualization scidevicemusicequalization);

    public static final native void SCIDeviceMusicEqualization_subscribe(long l, SCIDeviceMusicEqualization scidevicemusicequalization, long l1, SCIEventSink scieventsink);

    public static final native void SCIDeviceMusicEqualization_unsubscribe(long l, SCIDeviceMusicEqualization scidevicemusicequalization, long l1, SCIEventSink scieventsink);

    public static final native long SCIDeviceSettingsDataSource_getDevice(long l, SCIDeviceSettingsDataSource scidevicesettingsdatasource);

    public static final native void SCIDeviceVolume_beginContinuousVolumeAdjustments(long l, SCIDeviceVolume scidevicevolume);

    public static final native void SCIDeviceVolume_endContinuousVolumeAdjustments(long l, SCIDeviceVolume scidevicevolume);

    public static final native String SCIDeviceVolume_getDeviceID(long l, SCIDeviceVolume scidevicevolume);

    public static final native String SCIDeviceVolume_getPresentationText(long l, SCIDeviceVolume scidevicevolume, int i);

    public static final native int SCIDeviceVolume_getVolume(long l, SCIDeviceVolume scidevicevolume);

    public static final native boolean SCIDeviceVolume_isMuted(long l, SCIDeviceVolume scidevicevolume);

    public static final native boolean SCIDeviceVolume_isVolumeAdjustable(long l, SCIDeviceVolume scidevicevolume);

    public static final native int SCIDeviceVolume_outputMode(long l, SCIDeviceVolume scidevicevolume);

    public static final native int SCIDeviceVolume_setAbsoluteVolume(long l, SCIDeviceVolume scidevicevolume, int i);

    public static final native int SCIDeviceVolume_setMute(long l, SCIDeviceVolume scidevicevolume, boolean flag);

    public static final native int SCIDeviceVolume_setRelativeVolume(long l, SCIDeviceVolume scidevicevolume, int i);

    public static final native int SCIDevice_DC_UNKNOWN_get();

    public static final native int SCIDevice_DM_UNKNOWN_get();

    public static final native boolean SCIDevice_canCreateStereoPairWithDevice(long l, SCIDevice scidevice, long l1, SCIDevice scidevice1);

    public static final native long SCIDevice_createConfigureOp(long l, SCIDevice scidevice, long l1);

    public static final native long SCIDevice_createGetProtocolInfoOp(long l, SCIDevice scidevice);

    public static final native long SCIDevice_createGetWhiteLEDOp(long l, SCIDevice scidevice);

    public static final native long SCIDevice_createPauseOp(long l, SCIDevice scidevice);

    public static final native long SCIDevice_createRenameOp__SWIG_0(long l, SCIDevice scidevice, String s, String s1);

    public static final native long SCIDevice_createRenameOp__SWIG_1(long l, SCIDevice scidevice, String s, String s1, long l1);

    public static final native long SCIDevice_createRenameToHiddenOp(long l, SCIDevice scidevice, Object aobj[]);

    public static final native long SCIDevice_createSeparateStereoPairOp(long l, SCIDevice scidevice);

    public static final native long SCIDevice_createSetWhiteLEDOp(long l, SCIDevice scidevice, boolean flag);

    public static final native long SCIDevice_createStopOp(long l, SCIDevice scidevice);

    public static final native long SCIDevice_getCompatibleVersionRange(long l, SCIDevice scidevice);

    public static final native int SCIDevice_getDeviceClass(long l, SCIDevice scidevice);

    public static final native int SCIDevice_getDeviceModel(long l, SCIDevice scidevice);

    public static final native String SCIDevice_getDeviceModelDisplayString(long l, SCIDevice scidevice);

    public static final native String SCIDevice_getID(long l, SCIDevice scidevice);

    public static final native String SCIDevice_getIconURI(long l, SCIDevice scidevice);

    public static final native String SCIDevice_getMinCompatibleVersionStr(long l, SCIDevice scidevice);

    public static final native long SCIDevice_getSoftwareVersion(long l, SCIDevice scidevice);

    public static final native String SCIDevice_getSoftwareVersionStr(long l, SCIDevice scidevice);

    public static final native String SCIDevice_getTitle(long l, SCIDevice scidevice);

    public static final native String SCIDevice_getTitleForAboutBox(long l, SCIDevice scidevice);

    public static final native String SCIDevice_getTitleForDevice(long l, SCIDevice scidevice);

    public static final native String SCIDevice_getTitleForZPSettingsMenu(long l, SCIDevice scidevice);

    public static final native boolean SCIDevice_isCompatible(long l, SCIDevice scidevice);

    public static final native boolean SCIDevice_isConfigured(long l, SCIDevice scidevice);

    public static final native boolean SCIDevice_isVisible(long l, SCIDevice scidevice);

    public static final native void SCIDevice_setTitle(long l, SCIDevice scidevice, String s);

    public static final native String SCIENUMERABLE_INTERFACE_get();

    public static final native String SCIENUMERATOR_INTERFACE_get();

    public static final native String SCIEVENTSINK_INTERFACE_get();

    public static final native long SCIEnumerable_getEnumerator(long l, SCIEnumerable scienumerable);

    public static final native int SCIEnumerator_count(long l, SCIEnumerator scienumerator);

    public static final native SCIObj SCIEnumerator_getCurrent(long l, SCIEnumerator scienumerator, String s);

    public static final native boolean SCIEnumerator_moveNext(long l, SCIEnumerator scienumerator);

    public static final native void SCIEnumerator_reset(long l, SCIEnumerator scienumerator);

    public static final native void SCIEventSinkSwigBase_change_ownership(SCIEventSinkSwigBase scieventsinkswigbase, long l, boolean flag);

    public static final native void SCIEventSinkSwigBase_director_connect(SCIEventSinkSwigBase scieventsinkswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCIEventSinkSwigBase_dumpSCIObj(long l, SCIEventSinkSwigBase scieventsinkswigbase);

    public static final native String SCIEventSinkSwigBase_dumpSCIObjSwigExplicitSCIEventSinkSwigBase(long l, SCIEventSinkSwigBase scieventsinkswigbase);

    public static final native void SCIEventSink_dispatchEvent(long l, SCIEventSink scieventsink, SCIObj sciobj, String s);

    public static final native boolean SCIEventSink_listenToEventType(long l, SCIEventSink scieventsink, String s);

    public static final native String SCIGETABOUTSONOSSTRINGCB_INTERFACE_get();

    public static final native String SCIGROUPVOLUME_GROUPID_get();

    public static final native String SCIGROUPVOLUME_INTERFACE_get();

    public static final native String SCIGROUPVOLUME_ONMUTECHANGED_EVENT_get();

    public static final native String SCIGROUPVOLUME_ONOUTPUTMODECHANGED_EVENT_get();

    public static final native String SCIGROUPVOLUME_ONVOLUMECHANGED_EVENT_get();

    public static final native String SCIGROUPVOLUME_ONZONEGROUPCHANGED_EVENT_get();

    public static final native void SCIGetAboutSonosStringCBSwigBase_change_ownership(SCIGetAboutSonosStringCBSwigBase scigetaboutsonosstringcbswigbase, long l, boolean flag);

    public static final native void SCIGetAboutSonosStringCBSwigBase_director_connect(SCIGetAboutSonosStringCBSwigBase scigetaboutsonosstringcbswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCIGetAboutSonosStringCBSwigBase_dumpSCIObj(long l, SCIGetAboutSonosStringCBSwigBase scigetaboutsonosstringcbswigbase);

    public static final native String SCIGetAboutSonosStringCBSwigBase_dumpSCIObjSwigExplicitSCIGetAboutSonosStringCBSwigBase(long l, SCIGetAboutSonosStringCBSwigBase scigetaboutsonosstringcbswigbase);

    public static final native void SCIGetAboutSonosStringCB_updateGetAboutSonosString(long l, SCIGetAboutSonosStringCB scigetaboutsonosstringcb, String s);

    public static final native void SCIGetSonosPlaylistsCBSwigBase_change_ownership(SCIGetSonosPlaylistsCBSwigBase scigetsonosplaylistscbswigbase, long l, boolean flag);

    public static final native void SCIGetSonosPlaylistsCBSwigBase_director_connect(SCIGetSonosPlaylistsCBSwigBase scigetsonosplaylistscbswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCIGetSonosPlaylistsCBSwigBase_dumpSCIObj(long l, SCIGetSonosPlaylistsCBSwigBase scigetsonosplaylistscbswigbase);

    public static final native String SCIGetSonosPlaylistsCBSwigBase_dumpSCIObjSwigExplicitSCIGetSonosPlaylistsCBSwigBase(long l, SCIGetSonosPlaylistsCBSwigBase scigetsonosplaylistscbswigbase);

    public static final native void SCIGetSonosPlaylistsCB_getSonosPlaylistsFailed(long l, SCIGetSonosPlaylistsCB scigetsonosplaylistscb);

    public static final native void SCIGetSonosPlaylistsCB_getSonosPlaylistsSucceeded(long l, SCIGetSonosPlaylistsCB scigetsonosplaylistscb, String s);

    public static final native boolean SCIGroupVolume_areAllGroupVolumesAdjustable(long l, SCIGroupVolume scigroupvolume);

    public static final native long SCIGroupVolume_getDeviceVolume(long l, SCIGroupVolume scigroupvolume, String s);

    public static final native long SCIGroupVolume_getDeviceVolumes(long l, SCIGroupVolume scigroupvolume);

    public static final native long SCIGroupVolume_getDeviceVolumesEnum(long l, SCIGroupVolume scigroupvolume);

    public static final native String SCIGroupVolume_getGroupID(long l, SCIGroupVolume scigroupvolume);

    public static final native int SCIGroupVolume_getGroupVolumeMode(long l, SCIGroupVolume scigroupvolume);

    public static final native long SCIGroupVolume_getMasterDeviceVolume(long l, SCIGroupVolume scigroupvolume);

    public static final native boolean SCIGroupVolume_isGroupVolumeAdjustable(long l, SCIGroupVolume scigroupvolume);

    public static final native boolean SCIGroupVolume_isValid(long l, SCIGroupVolume scigroupvolume);

    public static final native boolean SCIGroupVolume_isZoneGroupValid(long l, SCIGroupVolume scigroupvolume);

    public static final native void SCIGroupVolume_prepareForGroupVolumeAdjustments(long l, SCIGroupVolume scigroupvolume);

    public static final native void SCIGroupVolume_subscribe(long l, SCIGroupVolume scigroupvolume, long l1, SCIEventSink scieventsink);

    public static final native void SCIGroupVolume_unsubscribe(long l, SCIGroupVolume scigroupvolume, long l1, SCIEventSink scieventsink);

    public static final native String SCIHOUSEHOLD_INTERFACE_get();

    public static final native String SCIHOUSEHOLD_ONASSOCIATEDDEVICECHANGED_EVENT_get();

    public static final native String SCIHOUSEHOLD_ONCURRENTZONEGROUPCHANGED_EVENT_get();

    public static final native String SCIHOUSEHOLD_ONFINISHEDCONNECTINGTOZPS_EVENT_get();

    public static final native String SCIHOUSEHOLD_ONNETWORKCHANGED_EVENT_get();

    public static final native String SCIHOUSEHOLD_ONSEARCHABLESLISTCHANGED_EVENT_get();

    public static final native String SCIHOUSEHOLD_ONSOFTWAREUPDATEAVAILABLECHANGED_EVENT_get();

    public static final native String SCIHOUSEHOLD_ONZONEGROUPSCHANGED_EVENT_get();

    public static final native boolean SCIHousehold_canAddAccounts(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createAddBoostWizard(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createAddCustomRadioStationAction(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createAddPlayerOrSubWizard(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createAddSubSoundbarWizard(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createBrowseStackWithRoot__SWIG_0(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createBrowseStackWithRoot__SWIG_1(long l, SCIHousehold scihousehold, String s, String s1);

    public static final native long SCIHousehold_createChangeSpeakersWizard(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createChooseConfigureWizard(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createConfigureSonosComponentWizard(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createConfigureSubWizard(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createConfigureWirelessWizard(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createFactoryResetAction(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createForgetHouseholdAction(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createGetAboutSonosStrOp(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createGetPropertyOp(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createGetShortAboutSonosStrOp(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createGetUsageDataShareOptionOp(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createGetZoneGroupStateOp(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createMusicLibrarySetupWizard(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createMusicServiceAddAccountWizard(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createMusicServiceAddSonosLabsAccountWizard(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createMusicServiceChangeNicknameWizard(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createMusicServiceChangePasswordWizard(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createMusicServiceReauthorizeAccountWizard(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createMusicServiceReplaceAccountWizard(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createMusicServiceSubscribeWizard(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createMusicServiceWizard(long l, SCIHousehold scihousehold, long l1, SCIPropertyBag scipropertybag);

    public static final native long SCIHousehold_createOnlineUpdateIntroOnlyWizard(long l, SCIHousehold scihousehold, long l1, SCIPropertyBag scipropertybag);

    public static final native long SCIHousehold_createOnlineUpdateWizard(long l, SCIHousehold scihousehold, boolean flag);

    public static final native long SCIHousehold_createPauseOp(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createPushSCUriAction(long l, SCIHousehold scihousehold, String s, String s1, boolean flag);

    public static final native long SCIHousehold_createRecalibrateAudioSoundbarWizard(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createRecalibrateSubWizard(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createRegistrationWizard(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createRemoteSetupWizard(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createResumeUpdateWizard(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createSetPropertyOp(long l, SCIHousehold scihousehold, String s, String s1);

    public static final native long SCIHousehold_createSetUsageDataShareOptionOp(long l, SCIHousehold scihousehold, boolean flag);

    public static final native long SCIHousehold_createSetupSurroundSoundbarWizard(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createShareUsageDataWizard(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createSonosNetSetupWizard(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createSoundbarWizard(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_createStopOp(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_createSubmitDiagsWizardAction(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_getAggregatedSearchable(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_getAlarmManager(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_getAllSearchables(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_getAssociatedDevice(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_getBrowseListPresentationMap(long l, SCIHousehold scihousehold);

    public static final native String SCIHousehold_getControllerUpdateURL(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_getCurrentMasterDevice(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_getCurrentZoneGroup(long l, SCIHousehold scihousehold);

    public static final native String SCIHousehold_getCustomerIDIfRegistered(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_getDateTimeManager(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_getDevices(long l, SCIHousehold scihousehold, int i);

    public static final native String SCIHousehold_getID(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_getIndexManager(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_getNumZoneGroups(long l, SCIHousehold scihousehold, int i);

    public static final native long SCIHousehold_getServiceDescriptorManager(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_getShareManager(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_getZoneGroups(long l, SCIHousehold scihousehold, int i);

    public static final native boolean SCIHousehold_hasTransientOrphanedZoneGroups(long l, SCIHousehold scihousehold);

    public static final native boolean SCIHousehold_isConnectingToZPs(long l, SCIHousehold scihousehold);

    public static final native boolean SCIHousehold_isControllerUpdateAvailable(long l, SCIHousehold scihousehold);

    public static final native boolean SCIHousehold_isValid(long l, SCIHousehold scihousehold);

    public static final native long SCIHousehold_lookupDevice(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_lookupSearchableBySCUri(long l, SCIHousehold scihousehold, String s);

    public static final native long SCIHousehold_lookupZoneGroup(long l, SCIHousehold scihousehold, String s);

    public static final native void SCIHousehold_saveCurrentZoneGroup(long l, SCIHousehold scihousehold);

    public static final native void SCIHousehold_setCurrentZoneGroup(long l, SCIHousehold scihousehold, String s);

    public static final native void SCIHousehold_setTopSearchable(long l, SCIHousehold scihousehold, long l1, SCISearchable scisearchable);

    public static final native void SCIHousehold_setTopSearchableBySCUri(long l, SCIHousehold scihousehold, String s);

    public static final native void SCIHousehold_subscribe(long l, SCIHousehold scihousehold, long l1, SCIEventSink scieventsink);

    public static final native void SCIHousehold_unsubscribe(long l, SCIHousehold scihousehold, long l1, SCIEventSink scieventsink);

    public static final native void SCIHousehold_updateAvailableServices(long l, SCIHousehold scihousehold);

    public static final native String SCIINDEXMANAGER_INTERFACE_get();

    public static final native String SCIINDEXMANAGER_ONINDEXERROR_EVENT_get();

    public static final native String SCIINDEXMANAGER_ONINDEXTIMECHANGED_EVENT_get();

    public static final native String SCIINDEXMANAGER_ONINDEX_EVENT_get();

    public static final native String SCIINFOVIEWHEADERDATASOURCE_INTERFACE_get();

    public static final native String SCIINFOVIEWHEADERDATASOURCE_ONCHANGED_EVENT_get();

    public static final native String SCIINPUTVALIDATIONCB_INTERFACE_get();

    public static final native String SCIINPUTWITHASYNCVALIDATION_INTERFACE_get();

    public static final native String SCIINPUT_INTERFACE_get();

    public static final native String SCIINTEGERSETTINGSPROPERTY_INTERFACE_get();

    public static final native long SCIIndexManager_createSetDailyIndexRefreshTimeOp(long l, SCIIndexManager sciindexmanager, long l1, SCITime scitime);

    public static final native long SCIIndexManager_createUpdateMusicIndexOp(long l, SCIIndexManager sciindexmanager, String s);

    public static final native String SCIIndexManager_getErrorMessage(long l, SCIIndexManager sciindexmanager);

    public static final native long SCIIndexManager_getIndexingTime(long l, SCIIndexManager sciindexmanager);

    public static final native boolean SCIIndexManager_isIndexingInProgress(long l, SCIIndexManager sciindexmanager);

    public static final native boolean SCIIndexManager_isIndexingScheduled(long l, SCIIndexManager sciindexmanager);

    public static final native void SCIIndexManager_setPreparingForIndexing(long l, SCIIndexManager sciindexmanager);

    public static final native void SCIIndexManager_subscribe(long l, SCIIndexManager sciindexmanager, long l1, SCIEventSink scieventsink);

    public static final native void SCIIndexManager_unsubscribe(long l, SCIIndexManager sciindexmanager, long l1, SCIEventSink scieventsink);

    public static final native int SCIInfoViewHeaderDataSource_getAlbumArtType(long l, SCIInfoViewHeaderDataSource sciinfoviewheaderdatasource);

    public static final native String SCIInfoViewHeaderDataSource_getAlbumArtURL__SWIG_0(long l, SCIInfoViewHeaderDataSource sciinfoviewheaderdatasource);

    public static final native String SCIInfoViewHeaderDataSource_getAlbumArtURL__SWIG_1(long l, SCIInfoViewHeaderDataSource sciinfoviewheaderdatasource, long l1);

    public static final native long SCIInfoViewHeaderDataSource_getItemAtIndex(long l, SCIInfoViewHeaderDataSource sciinfoviewheaderdatasource, long l1);

    public static final native long SCIInfoViewHeaderDataSource_getNumItems(long l, SCIInfoViewHeaderDataSource sciinfoviewheaderdatasource);

    public static final native boolean SCIInfoViewHeaderDataSource_isValid(long l, SCIInfoViewHeaderDataSource sciinfoviewheaderdatasource);

    public static final native void SCIInfoViewHeaderDataSource_subscribe(long l, SCIInfoViewHeaderDataSource sciinfoviewheaderdatasource, long l1, SCIEventSink scieventsink);

    public static final native void SCIInfoViewHeaderDataSource_unsubscribe(long l, SCIInfoViewHeaderDataSource sciinfoviewheaderdatasource, long l1, SCIEventSink scieventsink);

    public static final native String SCIInfoViewHeaderItem_getLabel(long l, SCIInfoViewHeaderItem sciinfoviewheaderitem);

    public static final native String SCIInfoViewHeaderItem_getValue(long l, SCIInfoViewHeaderItem sciinfoviewheaderitem);

    public static final native String SCIInfoViewTextPaneMetadata_getAlbumArtURL(long l, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata, long l1);

    public static final native String SCIInfoViewTextPaneMetadata_getArtist(long l, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata);

    public static final native String SCIInfoViewTextPaneMetadata_getTitle(long l, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata);

    public static final native boolean SCIInfoViewTextPaneMetadata_isAlbum(long l, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata);

    public static final native boolean SCIInfoViewTextPaneMetadata_isArtist(long l, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata);

    public static final native boolean SCIInfoViewTextPaneMetadata_isTrack(long l, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata);

    public static final native void SCIInputValidationCB_handleInputValidationCB(long l, SCIInputValidationCB sciinputvalidationcb, String s, boolean flag);

    public static final native String SCIInput_getID(long l, SCIInput sciinput);

    public static final native String SCIInput_getInterfaceID(long l, SCIInput sciinput);

    public static final native void SCIIntArray_append(long l, SCIIntArray sciintarray, int i);

    public static final native void SCIIntArray_clear(long l, SCIIntArray sciintarray);

    public static final native int SCIIntArray_getAt(long l, SCIIntArray sciintarray, long l1);

    public static final native void SCIIntArray_remove(long l, SCIIntArray sciintarray, long l1);

    public static final native long SCIIntArray_size(long l, SCIIntArray sciintarray);

    public static final native int SCIIntegerSettingsProperty_getMaxValue(long l, SCIIntegerSettingsProperty sciintegersettingsproperty);

    public static final native int SCIIntegerSettingsProperty_getMinValue(long l, SCIIntegerSettingsProperty sciintegersettingsproperty);

    public static final native int SCIIntegerSettingsProperty_getValue(long l, SCIIntegerSettingsProperty sciintegersettingsproperty);

    public static final native String SCILATENTLOADINGDATASOURCE_INTERFACE_get();

    public static final native String SCILIBRARYTESTS_INTERFACE_get();

    public static final native String SCILIBRARY_INTERFACE_get();

    public static final native String SCILINKSETTINGSPROPERTY_INTERFACE_get();

    public static final native String SCILOCALMUSICSEARCHDELEGATE_INTERFACE_get();

    public static final native String SCILOCATIONSERVICES_INTERFACE_get();

    public static final native String SCILOGOARTWORKCACHE_INTERFACE_get();

    public static final native boolean SCILatentLoadingDataSource_isFetchingData(long l, SCILatentLoadingDataSource scilatentloadingdatasource);

    public static final native long SCILibraryTests_getWizardImageResourceIds(long l, SCILibraryTests scilibrarytests);

    public static final native void SCILibraryTests_testDisplayMenuPopupAction(long l, SCILibraryTests scilibrarytests);

    public static final native void SCILibraryTests_testDisplayMessagePopupAction(long l, SCILibraryTests scilibrarytests);

    public static final native void SCILibraryTests_testTextInputAction(long l, SCILibraryTests scilibrarytests);

    public static final native void SCILibrary_SCLibUIThreadCallback(long l, SCILibrary scilibrary);

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_DUAL_MONO_WITH_LFE_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_DUAL_MONO_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_LCR_WITH_LFE_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_LCR_WITH_MONO_SURROUND_AND_LFE_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_LCR_WITH_MONO_SURROUND_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_LCR_WITH_STEREO_SURROUND_AND_LFE_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_LCR_WITH_STEREO_SURROUND_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_LCR_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_MONO_WITH_LFE_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_MONO_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_LFE_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_MONO_SURROUND_AND_LFE_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_MONO_SURROUND_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_STEREO_SURROUND_AND_LFE_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_STEREO_WITH_STEREO_SURROUND_get();

    public static final native int SCILibrary_SC_HTSI_DOLBY_DIGITAL_STEREO_get();

    public static final native int SCILibrary_SC_HTSI_NO_SIGNAL_get();

    public static final native int SCILibrary_SC_HTSI_NULL_BURST_get();

    public static final native int SCILibrary_SC_HTSI_PAUSE_BURST_get();

    public static final native int SCILibrary_SC_HTSI_PCM_get();

    public static final native int SCILibrary_SC_HTSI_SILENCE_get();

    public static final native int SCILibrary_SC_HTSI_UNAVAILABLE_get();

    public static final native int SCILibrary_SC_HTSI_UNKNOWN_get();

    public static final native int SCILibrary_SC_STRID_ALARMMUSIC_get();

    public static final native int SCILibrary_SC_STRID_HDR_IMPROVE_SONOS_get();

    public static final native int SCILibrary_SC_STRID_NS_NFW_OPEN_SSID_get();

    public static final native int SCILibrary_SC_STRID_PLAYBAR_INFORMATION_get();

    public static final native int SCILibrary_SC_STRID_PLAYBAR_INFO_TITLE_get();

    public static final native int SCILibrary_SC_STRID_PLAYBAR_TOOLTIP_NIGHTSOUND_get();

    public static final native int SCILibrary_SC_STRID_PLAYBAR_TOOLTIP_SPEECHENCHANCEMENT_get();

    public static final native int SCILibrary_SC_STRID_RESETCONTROLLER_INSTRUCTIONS_get();

    public static final native int SCILibrary_SC_STRID_RESETCONTROLLER_PROMPT_get();

    public static final native int SCILibrary_SC_STRID_SETUPWIZ_USAGEDATAOPTIN_BODY_1_get();

    public static final native int SCILibrary_SC_STRID_SETUPWIZ_USAGEDATAOPTIN_BODY_2_OPTINURL_FMT_get();

    public static final native int SCILibrary_SC_STRID_SETUPWIZ_USAGEDATAOPTIN_INPUT_1_get();

    public static final native int SCILibrary_SC_STRID_SETUPWIZ_USAGEDATAOPTIN_TITLE_get();

    public static final native int SCILibrary_SC_STRID_TOOLTIP_CROSSFADE_get();

    public static final native int SCILibrary_SC_STRID_TOOLTIP_INFOVIEW_get();

    public static final native int SCILibrary_SC_STRID_TOOLTIP_NEXTTRACK_get();

    public static final native int SCILibrary_SC_STRID_TOOLTIP_PLAYPAUSE_get();

    public static final native int SCILibrary_SC_STRID_TOOLTIP_PREVTRACK_get();

    public static final native int SCILibrary_SC_STRID_TOOLTIP_REPEAT_get();

    public static final native int SCILibrary_SC_STRID_TOOLTIP_SEEKTRACKPOSITION_get();

    public static final native int SCILibrary_SC_STRID_TOOLTIP_SHUFFLE_get();

    public static final native int SCILibrary_SC_URL_GETMUSIC_get();

    public static final native int SCILibrary_SC_URL_HELP_AUTOIP_get();

    public static final native int SCILibrary_SC_URL_HELP_FACTORY_RESET_get();

    public static final native int SCILibrary_SC_URL_HELP_FIREWALL_get();

    public static final native int SCILibrary_SC_URL_HELP_SEARCHING_get();

    public static final native int SCILibrary_SC_URL_HELP_SETUP_get();

    public static final native int SCILibrary_SC_URL_HELP_SONOSNET_get();

    public static final native int SCILibrary_SC_URL_HELP_WMPSHARING_get();

    public static final native int SCILibrary_SC_URL_PLAYBAR_INFORMATION_get();

    public static final native int SCILibrary_SC_URL_PRIVACY_get();

    public static final native int SCILibrary_SC_URL_SHOP_get();

    public static final native int SCILibrary_SC_URL_SONOS_ACCOUNT_get();

    public static final native int SCILibrary_SC_URL_SONOS_DEMO_get();

    public static final native int SCILibrary_SC_URL_SUPPORT_VER_LANG_get();

    public static final native int SCILibrary_SC_URL_SUPPORT_get();

    public static final native int SCILibrary_SC_URL_USAGE_get();

    public static final native void SCILibrary_addToEventSinkCache(long l, SCILibrary scilibrary, long l1, SCIEventSink scieventsink);

    public static final native long SCILibrary_createBrowseDataSource__SWIG_0(long l, SCILibrary scilibrary, String s, String s1);

    public static final native long SCILibrary_createBrowseDataSource__SWIG_1(long l, SCILibrary scilibrary, String s, String s1, long l1, SCIStringArray scistringarray);

    public static final native long SCILibrary_createBrowseDataSource__SWIG_2(long l, SCILibrary scilibrary, long l1, SCIBrowseItem scibrowseitem);

    public static final native long SCILibrary_createCustomUIActionEnumerator(long l, SCILibrary scilibrary, String s, String s1);

    public static final native long SCILibrary_createJoinAnotherHouseholdWizard(long l, SCILibrary scilibrary);

    public static final native void SCILibrary_createMusicServer(long l, SCILibrary scilibrary, long l1, SCIMusicServerDelegate scimusicserverdelegate);

    public static final native long SCILibrary_getAudioInputResources(long l, SCILibrary scilibrary);

    public static final native long SCILibrary_getBrowseManager(long l, SCILibrary scilibrary);

    public static final native String SCILibrary_getClientOSVersion(long l, SCILibrary scilibrary);

    public static final native long SCILibrary_getCountryList(long l, SCILibrary scilibrary);

    public static final native int SCILibrary_getDefaultRoomIDForDeviceNaming(long l, SCILibrary scilibrary);

    public static final native long SCILibrary_getDockResources(long l, SCILibrary scilibrary);

    public static final native long SCILibrary_getEventSinkCache(long l, SCILibrary scilibrary);

    public static final native String SCILibrary_getHTSourceTypeText(long l, SCILibrary scilibrary, int i);

    public static final native String SCILibrary_getHostDeviceID(long l, SCILibrary scilibrary);

    public static final native String SCILibrary_getHostMACAddress(long l, SCILibrary scilibrary);

    public static final native String SCILibrary_getHostModel(long l, SCILibrary scilibrary);

    public static final native long SCILibrary_getHousehold(long l, SCILibrary scilibrary);

    public static final native String SCILibrary_getMarketingVersion(long l, SCILibrary scilibrary);

    public static final native long SCILibrary_getMusicServer(long l, SCILibrary scilibrary);

    public static final native long SCILibrary_getNetworkManagementDelegate(long l, SCILibrary scilibrary);

    public static final native String SCILibrary_getRecommendedText(long l, SCILibrary scilibrary, int i);

    public static final native String SCILibrary_getRecommendedURL(long l, SCILibrary scilibrary, int i);

    public static final native long SCILibrary_getRoomResources(long l, SCILibrary scilibrary);

    public static final native long SCILibrary_getRoomResourcesForDeviceNaming(long l, SCILibrary scilibrary, String s);

    public static final native long SCILibrary_getSCLIBVersion(long l, SCILibrary scilibrary);

    public static final native long SCILibrary_getSoftwareVersion(long l, SCILibrary scilibrary);

    public static final native int SCILibrary_getSonosPlaylistMaxUTF8Length(long l, SCILibrary scilibrary);

    public static final native long SCILibrary_getZoneBridgeResources(long l, SCILibrary scilibrary);

    public static final native long SCILibrary_getZoneExtenderResources(long l, SCILibrary scilibrary);

    public static final native void SCILibrary_locationServicesStatusChanged(long l, SCILibrary scilibrary);

    public static final native SCIObj SCILibrary_queryInterface(long l, SCILibrary scilibrary, String s);

    public static final native void SCILibrary_registerLocationServices(long l, SCILibrary scilibrary, long l1, SCILocationServices scilocationservices);

    public static final native void SCILibrary_registerMobileDevice(long l, SCILibrary scilibrary, String s, String s1);

    public static final native void SCILibrary_removeFromEventSinkCache(long l, SCILibrary scilibrary, long l1, SCIEventSink scieventsink);

    public static final native void SCILibrary_setActionFactory(long l, SCILibrary scilibrary, long l1, SCIActionFactory sciactionfactory);

    public static final native void SCILibrary_setCR200LanguageID(long l, SCILibrary scilibrary, String s);

    public static final native void SCILibrary_setCR200TimedJobManager(long l, SCILibrary scilibrary, long l1);

    public static final native void SCILibrary_setMusicServer(long l, SCILibrary scilibrary, long l1, SCIMusicServer scimusicserver);

    public static final native void SCILibrary_setNetworkManagementDelegate(long l, SCILibrary scilibrary, long l1, SCINetworkManagementDelegate scinetworkmanagementdelegate);

    public static final native void SCILibrary_setStackTraceCaptureCallback(long l, SCILibrary scilibrary, long l1, SCIStackTraceCaptureDelegate scistacktracecapturedelegate);

    public static final native void SCILibrary_setSupportEnabled(long l, SCILibrary scilibrary, boolean flag);

    public static final native String SCILinkSettingsProperty_getValue(long l, SCILinkSettingsProperty scilinksettingsproperty);

    public static final native void SCILocalMediaCollectionListener_onMediaCollectionChanged(long l, SCILocalMediaCollectionListener scilocalmediacollectionlistener);

    public static final native void SCILocalMediaCollectionSwigBase_change_ownership(SCILocalMediaCollectionSwigBase scilocalmediacollectionswigbase, long l, boolean flag);

    public static final native void SCILocalMediaCollectionSwigBase_director_connect(SCILocalMediaCollectionSwigBase scilocalmediacollectionswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCILocalMediaCollectionSwigBase_dumpSCIObj(long l, SCILocalMediaCollectionSwigBase scilocalmediacollectionswigbase);

    public static final native String SCILocalMediaCollectionSwigBase_dumpSCIObjSwigExplicitSCILocalMediaCollectionSwigBase(long l, SCILocalMediaCollectionSwigBase scilocalmediacollectionswigbase);

    public static final native int SCILocalMediaCollection_getAllNodeType(long l, SCILocalMediaCollection scilocalmediacollection);

    public static final native long SCILocalMediaCollection_getCount(long l, SCILocalMediaCollection scilocalmediacollection);

    public static final native long SCILocalMediaCollection_getItemAt(long l, SCILocalMediaCollection scilocalmediacollection, long l1);

    public static final native String SCILocalMediaCollection_getPowerscrollCSV(long l, SCILocalMediaCollection scilocalmediacollection);

    public static final native int SCILocalMediaCollection_getPresentationType(long l, SCILocalMediaCollection scilocalmediacollection);

    public static final native void SCILocalMediaCollection_registerMediaCollectionListener(long l, SCILocalMediaCollection scilocalmediacollection, long l1, SCILocalMediaCollectionListener scilocalmediacollectionlistener);

    public static final native boolean SCILocalMediaCollection_showTrackNumber(long l, SCILocalMediaCollection scilocalmediacollection);

    public static final native void SCILocalMusicBrowseItemInfoSwigBase_change_ownership(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase, long l, boolean flag);

    public static final native void SCILocalMusicBrowseItemInfoSwigBase_director_connect(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCILocalMusicBrowseItemInfoSwigBase_dumpSCIObj(long l, SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase);

    public static final native String SCILocalMusicBrowseItemInfoSwigBase_dumpSCIObjSwigExplicitSCILocalMusicBrowseItemInfoSwigBase(long l, SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase);

    public static final native String SCILocalMusicBrowseItemInfo_getArtMimeType(long l, SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo);

    public static final native int SCILocalMusicBrowseItemInfo_getArtType(long l, SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo);

    public static final native String SCILocalMusicBrowseItemInfo_getArtUri(long l, SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo);

    public static final native int SCILocalMusicBrowseItemInfo_getByteOffsetForTime(long l, SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo, long l1);

    public static final native String SCILocalMusicBrowseItemInfo_getDisplayString(long l, SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo, int i);

    public static final native int SCILocalMusicBrowseItemInfo_getItemType(long l, SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo);

    public static final native String SCILocalMusicBrowseItemInfo_getMimeType(long l, SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo);

    public static final native int SCILocalMusicBrowseItemInfo_getTrackNumber(long l, SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo);

    public static final native boolean SCILocalMusicBrowseItemInfo_isContainer(long l, SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo);

    public static final native boolean SCILocalMusicBrowseItemInfo_isPlayable(long l, SCILocalMusicBrowseItemInfo scilocalmusicbrowseiteminfo);

    public static final native void SCILocalMusicSearchableDelegateSwigBase_change_ownership(SCILocalMusicSearchableDelegateSwigBase scilocalmusicsearchabledelegateswigbase, long l, boolean flag);

    public static final native void SCILocalMusicSearchableDelegateSwigBase_director_connect(SCILocalMusicSearchableDelegateSwigBase scilocalmusicsearchabledelegateswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCILocalMusicSearchableDelegateSwigBase_dumpSCIObj(long l, SCILocalMusicSearchableDelegateSwigBase scilocalmusicsearchabledelegateswigbase);

    public static final native String SCILocalMusicSearchableDelegateSwigBase_dumpSCIObjSwigExplicitSCILocalMusicSearchableDelegateSwigBase(long l, SCILocalMusicSearchableDelegateSwigBase scilocalmusicsearchabledelegateswigbase);

    public static final native long SCILocalMusicSearchableDelegate_getCategoryIDs(long l, SCILocalMusicSearchableDelegate scilocalmusicsearchabledelegate);

    public static final native String SCILocalMusicSearchableDelegate_getLogoURI(long l, SCILocalMusicSearchableDelegate scilocalmusicsearchabledelegate);

    public static final native String SCILocalMusicSearchableDelegate_getTitle(long l, SCILocalMusicSearchableDelegate scilocalmusicsearchabledelegate);

    public static final native String SCILocalMusicSearchableDelegate_objectIdForSearch(long l, SCILocalMusicSearchableDelegate scilocalmusicsearchabledelegate, String s, String s1);

    public static final native void SCILocationServicesSwigBase_change_ownership(SCILocationServicesSwigBase scilocationservicesswigbase, long l, boolean flag);

    public static final native void SCILocationServicesSwigBase_director_connect(SCILocationServicesSwigBase scilocationservicesswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCILocationServicesSwigBase_dumpSCIObj(long l, SCILocationServicesSwigBase scilocationservicesswigbase);

    public static final native String SCILocationServicesSwigBase_dumpSCIObjSwigExplicitSCILocationServicesSwigBase(long l, SCILocationServicesSwigBase scilocationservicesswigbase);

    public static final native long SCILocationServices_createRequestLocationAuthorizationAction(long l, SCILocationServices scilocationservices);

    public static final native long SCILocationServices_createRequestLocationInformationAction(long l, SCILocationServices scilocationservices, long l1, SCIStringArray scistringarray);

    public static final native String SCILocationServices_getLocationUsageDescription(long l, SCILocationServices scilocationservices);

    public static final native String SCILocationServices_getLocationUsageExistingHHDescription(long l, SCILocationServices scilocationservices);

    public static final native int SCILocationServices_locationStatus(long l, SCILocationServices scilocationservices);

    public static final native boolean SCILocationServices_shouldRequestLocationForExistingHH(long l, SCILocationServices scilocationservices);

    public static final native String SCILogoArtworkCache_getCompleteAAUri(long l, SCILogoArtworkCache scilogoartworkcache, String s, int i);

    public static final native long SCILogoArtworkCache_requestArtwork__SWIG_0(long l, SCILogoArtworkCache scilogoartworkcache, String s);

    public static final native long SCILogoArtworkCache_requestArtwork__SWIG_1(long l, SCILogoArtworkCache scilogoartworkcache, String s, int i);

    public static final native void SCILogoArtworkCache_setDefaultLogoSize(long l, SCILogoArtworkCache scilogoartworkcache, int i);

    public static final native String SCIMUSICSERVERBROWSEDELEGATE_INTERFACE_get();

    public static final native String SCIMUSICSERVERDELEGATE_INTERFACE_get();

    public static final native String SCIMUSICSERVER_INTERFACE_get();

    public static final native String SCIMUSICSERVICEWIZARD_INTERFACE_get();

    public static final native String SCIMUSICSERVICEWIZARD_ONSERVICEDESCRIPTORLISTCHANGED_EVENT_get();

    public static final native String SCIMUSICSERVICEWIZARD_ONSTATECHANGED_EVENT_get();

    public static final native String SCIMUSICSERVICEWIZARD_ONSTATETRANSITIONSENABLED_EVENT_get();

    public static final native void SCIMusicServerBrowseDelegateSwigBase_change_ownership(SCIMusicServerBrowseDelegateSwigBase scimusicserverbrowsedelegateswigbase, long l, boolean flag);

    public static final native void SCIMusicServerBrowseDelegateSwigBase_director_connect(SCIMusicServerBrowseDelegateSwigBase scimusicserverbrowsedelegateswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCIMusicServerBrowseDelegateSwigBase_dumpSCIObj(long l, SCIMusicServerBrowseDelegateSwigBase scimusicserverbrowsedelegateswigbase);

    public static final native String SCIMusicServerBrowseDelegateSwigBase_dumpSCIObjSwigExplicitSCIMusicServerBrowseDelegateSwigBase(long l, SCIMusicServerBrowseDelegateSwigBase scimusicserverbrowsedelegateswigbase);

    public static final native long SCIMusicServerBrowseDelegate_getLocalMediaCollectionForId(long l, SCIMusicServerBrowseDelegate scimusicserverbrowsedelegate, String s);

    public static final native long SCIMusicServerBrowseDelegate_getLocalMusicItemInfoForId(long l, SCIMusicServerBrowseDelegate scimusicserverbrowsedelegate, String s);

    public static final native long SCIMusicServerBrowseDelegate_getLocalMusicSearchableDelegate(long l, SCIMusicServerBrowseDelegate scimusicserverbrowsedelegate);

    public static final native String SCIMusicServerBrowseDelegate_getObjectIdForAssociationType(long l, SCIMusicServerBrowseDelegate scimusicserverbrowsedelegate, String s, int i);

    public static final native long SCIMusicServerBrowseDelegate_getRootItem(long l, SCIMusicServerBrowseDelegate scimusicserverbrowsedelegate);

    public static final native void SCIMusicServerDelegateSwigBase_change_ownership(SCIMusicServerDelegateSwigBase scimusicserverdelegateswigbase, long l, boolean flag);

    public static final native void SCIMusicServerDelegateSwigBase_director_connect(SCIMusicServerDelegateSwigBase scimusicserverdelegateswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCIMusicServerDelegateSwigBase_dumpSCIObj(long l, SCIMusicServerDelegateSwigBase scimusicserverdelegateswigbase);

    public static final native String SCIMusicServerDelegateSwigBase_dumpSCIObjSwigExplicitSCIMusicServerDelegateSwigBase(long l, SCIMusicServerDelegateSwigBase scimusicserverdelegateswigbase);

    public static final native void SCIMusicServerDelegate_fillImageBytes(long l, SCIMusicServerDelegate scimusicserverdelegate, long l1, SCIData scidata, String s);

    public static final native long SCIMusicServerDelegate_getMusicServerBrowseDelegate(long l, SCIMusicServerDelegate scimusicserverdelegate);

    public static final native String SCIMusicServerDelegate_getPlayableTrackId(long l, SCIMusicServerDelegate scimusicserverdelegate, String s);

    public static final native void SCIMusicServerDelegate_onBeginStreaming(long l, SCIMusicServerDelegate scimusicserverdelegate);

    public static final native void SCIMusicServerDelegate_onEndStreaming(long l, SCIMusicServerDelegate scimusicserverdelegate);

    public static final native long SCIMusicServer_getImageData(long l, SCIMusicServer scimusicserver, String s);

    public static final native long SCIMusicServer_getMusicServerBrowseDelegate(long l, SCIMusicServer scimusicserver);

    public static final native long SCIMusicServer_getTrackData(long l, SCIMusicServer scimusicserver, String s);

    public static final native long SCIMusicServer_getTracksForPlaylistId(long l, SCIMusicServer scimusicserver, String s);

    public static final native int SCIMusicServiceWizard_MSWIZ_STRID_BODY_get();

    public static final native int SCIMusicServiceWizard_MSWIZ_STRID_TITLE_1_get();

    public static final native int SCIMusicServiceWizard_STATE_MUSICSERVICE_COMPLETE_get();

    public static final native int SCIMusicServiceWizard_STATE_MUSICSERVICE_INIT_get();

    public static final native int SCIMusicServiceWizard_STATE_MUSICSERVICE_UNKNOWN_get();

    public static final native String SCIMusicServiceWizard_getAccountNeededText(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native boolean SCIMusicServiceWizard_getAgreeToTerms(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native long SCIMusicServiceWizard_getAvailableServiceDescriptors(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native boolean SCIMusicServiceWizard_getHasAccountChoice(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native String SCIMusicServiceWizard_getHasAccountChoiceTitle(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native long SCIMusicServiceWizard_getLoginInput(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native String SCIMusicServiceWizard_getNewChoiceTitle(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native long SCIMusicServiceWizard_getNicknameInput(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native long SCIMusicServiceWizard_getPasswordInput(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native boolean SCIMusicServiceWizard_getRemovePromotedChoice(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native String SCIMusicServiceWizard_getResultText(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native int SCIMusicServiceWizard_getSelection(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native String SCIMusicServiceWizard_getServiceBlurb(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native int SCIMusicServiceWizard_getServiceLogoType(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native String SCIMusicServiceWizard_getServiceLogoUri(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native String SCIMusicServiceWizard_getServiceName(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native String SCIMusicServiceWizard_getServiceSubscribeText(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native boolean SCIMusicServiceWizard_getShareUsage(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native boolean SCIMusicServiceWizard_getShowLinkCode(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native String SCIMusicServiceWizard_getTermsText(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native String SCIMusicServiceWizard_getWorkingText(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native boolean SCIMusicServiceWizard_isResultSuccess(long l, SCIMusicServiceWizard scimusicservicewizard);

    public static final native void SCIMusicServiceWizard_setAgreeToTerms(long l, SCIMusicServiceWizard scimusicservicewizard, boolean flag);

    public static final native void SCIMusicServiceWizard_setHasAccountChoice(long l, SCIMusicServiceWizard scimusicservicewizard, boolean flag);

    public static final native void SCIMusicServiceWizard_setMergeFromTrial(long l, SCIMusicServiceWizard scimusicservicewizard, boolean flag);

    public static final native void SCIMusicServiceWizard_setRemovePromotedChoice(long l, SCIMusicServiceWizard scimusicservicewizard, boolean flag);

    public static final native void SCIMusicServiceWizard_setSelection(long l, SCIMusicServiceWizard scimusicservicewizard, int i);

    public static final native void SCIMusicServiceWizard_setServiceDescriptorID(long l, SCIMusicServiceWizard scimusicservicewizard, String s);

    public static final native void SCIMusicServiceWizard_setShareUsage(long l, SCIMusicServiceWizard scimusicservicewizard, boolean flag);

    public static final native String SCINETSTARTSCANLISTENTRY_INTERFACE_get();

    public static final native String SCINETWORKMANAGEMENT_INTERFACE_get();

    public static final native String SCINOWPLAYINGRATINGS_INTERFACE_get();

    public static final native String SCINOWPLAYINGSLEEPTIMER_INTERFACE_get();

    public static final native String SCINOWPLAYINGSOURCE_INTERFACE_get();

    public static final native String SCINOWPLAYINGTRANSPORT_INTERFACE_get();

    public static final native String SCINOWPLAYINGTRANSPORT_SEEK_RELATIVE_TIME_get();

    public static final native String SCINOWPLAYINGTRANSPORT_SEEK_SECTION_NUMBER_get();

    public static final native String SCINOWPLAYINGTRANSPORT_SEEK_TRACK_NUMBER_get();

    public static final native String SCINOWPLAYING_INTERFACE_get();

    public static final native String SCINOWPLAYING_ONALARMRUNNINGCHANGED_EVENT_get();

    public static final native String SCINOWPLAYING_ONMUSICCHANGED_EVENT_get();

    public static final native String SCINOWPLAYING_ONSLEEPTIMERGENERATIONCHANGED_EVENT_get();

    public static final native String SCINOWPLAYING_ONSNOOZERUNNINGCHANGED_EVENT_get();

    public static final native String SCINOWPLAYING_ONTVEQUALIZATIONCHANGED_EVENT_get();

    public static final native void SCINetstartListenerSwigBase_change_ownership(SCINetstartListenerSwigBase scinetstartlistenerswigbase, long l, boolean flag);

    public static final native void SCINetstartListenerSwigBase_director_connect(SCINetstartListenerSwigBase scinetstartlistenerswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCINetstartListenerSwigBase_dumpSCIObj(long l, SCINetstartListenerSwigBase scinetstartlistenerswigbase);

    public static final native String SCINetstartListenerSwigBase_dumpSCIObjSwigExplicitSCINetstartListenerSwigBase(long l, SCINetstartListenerSwigBase scinetstartlistenerswigbase);

    public static final native void SCINetstartListener_onDeviceDiscoveryWaiting(long l, SCINetstartListener scinetstartlistener);

    public static final native void SCINetstartListener_onJoinComplete(long l, SCINetstartListener scinetstartlistener);

    public static final native void SCINetstartListener_onJoinFail(long l, SCINetstartListener scinetstartlistener);

    public static final native void SCINetstartListener_onNetParamsAcquired(long l, SCINetstartListener scinetstartlistener, String s, String s1, int i);

    public static final native void SCINetstartListener_onSonosnetOptionChange(long l, SCINetstartListener scinetstartlistener, boolean flag);

    public static final native void SCINetstartListener_onSonosnetPasswordReset(long l, SCINetstartListener scinetstartlistener);

    public static final native boolean SCINetstartScanListEntry_canConnect(long l, SCINetstartScanListEntry scinetstartscanlistentry);

    public static final native int SCINetstartScanListEntry_getChannel(long l, SCINetstartScanListEntry scinetstartscanlistentry);

    public static final native String SCINetstartScanListEntry_getSSID(long l, SCINetstartScanListEntry scinetstartscanlistentry);

    public static final native int SCINetstartScanListEntry_getSignalStrength(long l, SCINetstartScanListEntry scinetstartscanlistentry);

    public static final native boolean SCINetstartScanListEntry_isHidden(long l, SCINetstartScanListEntry scinetstartscanlistentry);

    public static final native boolean SCINetstartScanListEntry_isOpen(long l, SCINetstartScanListEntry scinetstartscanlistentry);

    public static final native void SCINetworkManagementDelegateSwigBase_change_ownership(SCINetworkManagementDelegateSwigBase scinetworkmanagementdelegateswigbase, long l, boolean flag);

    public static final native void SCINetworkManagementDelegateSwigBase_director_connect(SCINetworkManagementDelegateSwigBase scinetworkmanagementdelegateswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCINetworkManagementDelegateSwigBase_dumpSCIObj(long l, SCINetworkManagementDelegateSwigBase scinetworkmanagementdelegateswigbase);

    public static final native String SCINetworkManagementDelegateSwigBase_dumpSCIObjSwigExplicitSCINetworkManagementDelegateSwigBase(long l, SCINetworkManagementDelegateSwigBase scinetworkmanagementdelegateswigbase);

    public static final native int SCINetworkManagementDelegate_NetworkType_UNKNOWN_get();

    public static final native int SCINetworkManagementDelegate_getNetworkType(long l, SCINetworkManagementDelegate scinetworkmanagementdelegate);

    public static final native int SCINetworkManagement_getNetworkState(long l, SCINetworkManagement scinetworkmanagement);

    public static final native void SCINetworkManagement_networkChanged(long l, SCINetworkManagement scinetworkmanagement);

    public static final native void SCINetworkManagement_rebindNetworkSockets(long l, SCINetworkManagement scinetworkmanagement);

    public static final native void SCINetworkManagement_refreshNetworking(long l, SCINetworkManagement scinetworkmanagement);

    public static final native void SCINetworkManagement_resumeDeviceExpiration(long l, SCINetworkManagement scinetworkmanagement);

    public static final native void SCINetworkManagement_resumeNetworking(long l, SCINetworkManagement scinetworkmanagement);

    public static final native void SCINetworkManagement_suspendDeviceExpiration(long l, SCINetworkManagement scinetworkmanagement);

    public static final native void SCINetworkManagement_suspendNetworking(long l, SCINetworkManagement scinetworkmanagement);

    public static final native long SCINowPlayingRatings_getActionForRating(long l, SCINowPlayingRatings scinowplayingratings, int i);

    public static final native String SCINowPlayingRatings_getRatingDisplayString(long l, SCINowPlayingRatings scinowplayingratings, int i);

    public static final native int SCINowPlayingRatings_getRatingImageType(long l, SCINowPlayingRatings scinowplayingratings, int i);

    public static final native String SCINowPlayingRatings_getRatingImageURL(long l, SCINowPlayingRatings scinowplayingratings, int i);

    public static final native boolean SCINowPlayingRatings_isRatingVisible(long l, SCINowPlayingRatings scinowplayingratings, int i);

    public static final native int SCINowPlayingRatings_numberOfRatings(long l, SCINowPlayingRatings scinowplayingratings);

    public static final native long SCINowPlayingSleepTimer_createConfigureSleepTimerOp(long l, SCINowPlayingSleepTimer scinowplayingsleeptimer, String s);

    public static final native long SCINowPlayingSleepTimer_createGetRemainingSleepTimerDurationOp(long l, SCINowPlayingSleepTimer scinowplayingsleeptimer);

    public static final native String SCINowPlayingSource_getAlbumArtURL__SWIG_0(long l, SCINowPlayingSource scinowplayingsource);

    public static final native String SCINowPlayingSource_getAlbumArtURL__SWIG_1(long l, SCINowPlayingSource scinowplayingsource, long l1);

    public static final native int SCINowPlayingSource_getBase(long l, SCINowPlayingSource scinowplayingsource);

    public static final native String SCINowPlayingSource_getDefaultSharingString(long l, SCINowPlayingSource scinowplayingsource);

    public static final native long SCINowPlayingSource_getInfoViewActionDescriptor(long l, SCINowPlayingSource scinowplayingsource);

    public static final native long SCINowPlayingSource_getMoreMenuDataSource(long l, SCINowPlayingSource scinowplayingsource);

    public static final native String SCINowPlayingSource_getNextTrackAlbumArtURL(long l, SCINowPlayingSource scinowplayingsource, long l1);

    public static final native int SCINowPlayingSource_getNextTrackProperty(long l, SCINowPlayingSource scinowplayingsource, int i, Object aobj[]);

    public static final native boolean SCINowPlayingSource_getNightMode(long l, SCINowPlayingSource scinowplayingsource);

    public static final native int SCINowPlayingSource_getOneLineMetadata(long l, SCINowPlayingSource scinowplayingsource, Object aobj[]);

    public static final native int SCINowPlayingSource_getPlaceholderAlbumArtIconID(long l, SCINowPlayingSource scinowplayingsource);

    public static final native int SCINowPlayingSource_getProperty(long l, SCINowPlayingSource scinowplayingsource, int i, Object aobj[]);

    public static final native String SCINowPlayingSource_getPropertyLabel(long l, SCINowPlayingSource scinowplayingsource, int i);

    public static final native int SCINowPlayingSource_getPropertyLabelID(long l, SCINowPlayingSource scinowplayingsource, int i);

    public static final native String SCINowPlayingSource_getSharingMenuTitle(long l, SCINowPlayingSource scinowplayingsource);

    public static final native boolean SCINowPlayingSource_getTVDialogEnhancement(long l, SCINowPlayingSource scinowplayingsource);

    public static final native int SCINowPlayingSource_getThreeLineMetadata(long l, SCINowPlayingSource scinowplayingsource, Object aobj[], Object aobj1[], Object aobj2[]);

    public static final native int SCINowPlayingSource_getTwoLineMetadata(long l, SCINowPlayingSource scinowplayingsource, Object aobj[], Object aobj1[]);

    public static final native int SCINowPlayingSource_getType(long l, SCINowPlayingSource scinowplayingsource);

    public static final native boolean SCINowPlayingSource_isInQueue(long l, SCINowPlayingSource scinowplayingsource);

    public static final native boolean SCINowPlayingSource_isPlayNextAvailable(long l, SCINowPlayingSource scinowplayingsource);

    public static final native boolean SCINowPlayingSource_isShareable(long l, SCINowPlayingSource scinowplayingsource);

    public static final native void SCINowPlayingSource_setNightMode(long l, SCINowPlayingSource scinowplayingsource, boolean flag);

    public static final native void SCINowPlayingSource_setTVDialogEnhancement(long l, SCINowPlayingSource scinowplayingsource, boolean flag);

    public static final native long SCINowPlayingTransport_createGetTrackPositionInfoOp(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native long SCINowPlayingTransport_createNextTrackOnRateOp(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native long SCINowPlayingTransport_createNextTrackOp(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native long SCINowPlayingTransport_createPauseOp(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native long SCINowPlayingTransport_createPlayOp(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native long SCINowPlayingTransport_createPrevTrackOp(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native long SCINowPlayingTransport_createRewindToStartOp(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native long SCINowPlayingTransport_createRewindToStartOrPrevTrackOp(long l, SCINowPlayingTransport scinowplayingtransport, long l1);

    public static final native long SCINowPlayingTransport_createSeekOp(long l, SCINowPlayingTransport scinowplayingtransport, String s, String s1);

    public static final native long SCINowPlayingTransport_createSeekToTimeWithinTrackOp(long l, SCINowPlayingTransport scinowplayingtransport, long l1);

    public static final native long SCINowPlayingTransport_createSetCrossfadeModeOp(long l, SCINowPlayingTransport scinowplayingtransport, boolean flag);

    public static final native long SCINowPlayingTransport_createSetRepeatModeOp(long l, SCINowPlayingTransport scinowplayingtransport, boolean flag);

    public static final native long SCINowPlayingTransport_createSetShuffleModeOp(long l, SCINowPlayingTransport scinowplayingtransport, boolean flag);

    public static final native long SCINowPlayingTransport_createSetTransportURIOp(long l, SCINowPlayingTransport scinowplayingtransport, String s, String s1);

    public static final native long SCINowPlayingTransport_createSnoozeAlarmOp(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native long SCINowPlayingTransport_createTogglePlayPauseOp(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native String SCINowPlayingTransport_getAsynchronousErrorString(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native int SCINowPlayingTransport_getAvtURI(long l, SCINowPlayingTransport scinowplayingtransport, Object aobj[]);

    public static final native boolean SCINowPlayingTransport_getCrossfadeMode(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native int SCINowPlayingTransport_getCurrentTrackDuration(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native void SCINowPlayingTransport_getErrorString(long l, SCINowPlayingTransport scinowplayingtransport, int i, Object aobj[]);

    public static final native void SCINowPlayingTransport_getErrorStringFromOpResultAndURI(long l, SCINowPlayingTransport scinowplayingtransport, int i, String s, Object aobj[]);

    public static final native int SCINowPlayingTransport_getPlayPauseDisplayState(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native int SCINowPlayingTransport_getPlaybackState(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native boolean SCINowPlayingTransport_getRepeatMode(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native boolean SCINowPlayingTransport_getShuffleMode(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native int SCINowPlayingTransport_getTrackURI(long l, SCINowPlayingTransport scinowplayingtransport, Object aobj[]);

    public static final native int SCINowPlayingTransport_getTransportErrorHttpCode(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native long SCINowPlayingTransport_getTransportErrorHttpHeaders(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native String SCINowPlayingTransport_getTransportErrorURI(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native boolean SCINowPlayingTransport_hasLocalMuseSession(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native boolean SCINowPlayingTransport_hasMusic(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native boolean SCINowPlayingTransport_isCrossfadeEnabled(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native boolean SCINowPlayingTransport_isFastForwardEnabled(long l, SCINowPlayingTransport scinowplayingtransport, long l1);

    public static final native boolean SCINowPlayingTransport_isNextTrackEnabled(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native boolean SCINowPlayingTransport_isPlayPauseEnabled(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native boolean SCINowPlayingTransport_isPreviousTrackEnabled(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native boolean SCINowPlayingTransport_isRepeatEnabled(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native boolean SCINowPlayingTransport_isRewindEnabled(long l, SCINowPlayingTransport scinowplayingtransport, long l1);

    public static final native boolean SCINowPlayingTransport_isSeekEnabled(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native boolean SCINowPlayingTransport_isShuffleEnabled(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native boolean SCINowPlayingTransport_isTrackPositionInfoAvailable(long l, SCINowPlayingTransport scinowplayingtransport);

    public static final native String SCINowPlaying_getGroupID(long l, SCINowPlaying scinowplaying);

    public static final native long SCINowPlaying_getInterface(long l, SCIZoneGroup scizonegroup);

    public static final native boolean SCINowPlaying_isValid(long l, SCINowPlaying scinowplaying);

    public static final native void SCINowPlaying_subscribe(long l, SCINowPlaying scinowplaying, long l1, SCIEventSink scieventsink);

    public static final native void SCINowPlaying_unsubscribe(long l, SCINowPlaying scinowplaying, long l1, SCIEventSink scieventsink);

    public static final native String SCIOBJ_INTERFACE_get();

    public static final native String SCIONLINEUPDATEWIZARD_INTERFACE_get();

    public static final native String SCIONLINEUPDATEWIZARD_ONDEVICEUPDATESTATUSCHANGED_EVENT_get();

    public static final native String SCIONLINEUPDATEWIZARD_ONSTATECHANGED_EVENT_get();

    public static final native String SCIONLINEUPDATEWIZARD_ONSTATETRANSITIONSENABLED_EVENT_get();

    public static final native String SCIOPADDSERVICEACCOUNT_INTERFACE_get();

    public static final native String SCIOPADDTRACKSTOQUEUE_INTERFACE_get();

    public static final native String SCIOPATTACHPRIVATEQUEUE_INTERFACE_get();

    public static final native String SCIOPAUDIOINGETAUDIOINPUTATTRIBUTES_INTERFACE_get();

    public static final native String SCIOPAUDIOINGETLINEINLEVEL_INTERFACE_get();

    public static final native String SCIOPAVTRANSPORTGETPOSITIONINFO_INTERFACE_get();

    public static final native String SCIOPAVTRANSPORTGETREMAININGSLEEPTIMERDURATION_INTERFACE_get();

    public static final native String SCIOPCB_INTERFACE_get();

    public static final native String SCIOPCONNECTIONMANAGERGETPROTOCOLINFO_INTERFACE_get();

    public static final native String SCIOPDEVICEPROPERTIESGETAUTOPLAYLINKEDZONES_INTERFACE_get();

    public static final native String SCIOPDEVICEPROPERTIESGETAUTOPLAYROOMUUID_INTERFACE_get();

    public static final native String SCIOPDEVICEPROPERTIESGETAUTOPLAYVOLUME_INTERFACE_get();

    public static final native String SCIOPDEVICEPROPERTIESGETLEDSTATE_INTERFACE_get();

    public static final native String SCIOPDEVICEPROPERTIESGETUSEAUTOPLAYVOLUME_INTERFACE_get();

    public static final native String SCIOPERATIONPROGRESS_INTERFACE_get();

    public static final native String SCIOPFACTORY_INTERFACE_get();

    public static final native String SCIOPGETABOUTSONOSSTRING_INTERFACE_get();

    public static final native String SCIOPGETTRACKPOSITIONINFO_INTERFACE_get();

    public static final native String SCIOPGETUSAGEDATASHAREOPTION_INTERFACE_get();

    public static final native String SCIOPLOADALBUMART_INTERFACE_get();

    public static final native String SCIOPLOADLOGO_INTERFACE_get();

    public static final native String SCIOPNETSTARTGETSCANLIST_INTERFACE_get();

    public static final native String SCIOPNEWPRIVATEQUEUE_INTERFACE_get();

    public static final native String SCIOPQUEUEATTACHQUEUE_INTERFACE_get();

    public static final native String SCIOPQUEUEREPLACEALLTRACKS_INTERFACE_get();

    public static final native String SCIOPREGEMAILOPTIN_INTERFACE_get();

    public static final native String SCIOPRENDERINGCONTROLGETOUTPUTFIXED_INTERFACE_get();

    public static final native String SCIOPRENDERINGCONTROLGETSUPPORTSOUTPUTFIXED_INTERFACE_get();

    public static final native String SCIOPREPLACEACCOUNT_INTERFACE_get();

    public static final native String SCIOPSUBMITDIAGNOSTICS_INTERFACE_get();

    public static final native String SCIOPSYSTEMPROPERTYGETSTRING_INTERFACE_get();

    public static final native String SCIOPZONEGROUPTOPOLOGYGETZONEGROUPSTATE_INTERFACE_get();

    public static final native String SCIOP_INTERFACE_get();

    public static final native SCIObj SCIObj_queryInterface(long l, SCIObj sciobj, String s);

    public static final native int SCIOnlineUpdateWizard_ONLINEUPDATE_DEVICE_STATUS_STARTING_get();

    public static final native int SCIOnlineUpdateWizard_ONLINEUPDATE_DEVICE_STATUS_UNKNOWN_get();

    public static final native int SCIOnlineUpdateWizard_OUWIZ_STRID_BODY_get();

    public static final native int SCIOnlineUpdateWizard_OUWIZ_STRID_TITLE_get();

    public static final native int SCIOnlineUpdateWizard_STATE_ONLINEUPDATE_COMPLETE_get();

    public static final native int SCIOnlineUpdateWizard_STATE_ONLINEUPDATE_INIT_get();

    public static final native int SCIOnlineUpdateWizard_STATE_ONLINEUPDATE_UNKNOWN_get();

    public static final native boolean SCIOnlineUpdateWizard_canSelfUpdate(long l, SCIOnlineUpdateWizard scionlineupdatewizard);

    public static final native boolean SCIOnlineUpdateWizard_doesControllerNeedUpdating(long l, SCIOnlineUpdateWizard scionlineupdatewizard);

    public static final native String SCIOnlineUpdateWizard_getChoiceCancelLabel(long l, SCIOnlineUpdateWizard scionlineupdatewizard);

    public static final native String SCIOnlineUpdateWizard_getChoiceContinueLabel(long l, SCIOnlineUpdateWizard scionlineupdatewizard);

    public static final native int SCIOnlineUpdateWizard_getCurrentDeviceStatusCode(long l, SCIOnlineUpdateWizard scionlineupdatewizard);

    public static final native int SCIOnlineUpdateWizard_getMode(long l, SCIOnlineUpdateWizard scionlineupdatewizard);

    public static final native String SCIOnlineUpdateWizard_getOnlineUpdateURL(long l, SCIOnlineUpdateWizard scionlineupdatewizard);

    public static final native int SCIOnlineUpdateWizard_getUpdateCompletedPercentage(long l, SCIOnlineUpdateWizard scionlineupdatewizard);

    public static final native void SCIOnlineUpdateWizard_saveUpdateState(long l, SCIOnlineUpdateWizard scionlineupdatewizard);

    public static final native void SCIOnlineUpdateWizard_setCanSelfUpdate(long l, SCIOnlineUpdateWizard scionlineupdatewizard, boolean flag);

    public static final native void SCIOnlineUpdateWizard_setConnectionTimeout(long l, SCIOnlineUpdateWizard scionlineupdatewizard, int i);

    public static final native void SCIOnlineUpdateWizard_setMode(long l, SCIOnlineUpdateWizard scionlineupdatewizard, int i);

    public static final native int SCIOpAVTransportGetPositionInfo_getAbsCount(long l, SCIOpAVTransportGetPositionInfo sciopavtransportgetpositioninfo);

    public static final native String SCIOpAVTransportGetPositionInfo_getAbsTime(long l, SCIOpAVTransportGetPositionInfo sciopavtransportgetpositioninfo);

    public static final native int SCIOpAVTransportGetPositionInfo_getRelCount(long l, SCIOpAVTransportGetPositionInfo sciopavtransportgetpositioninfo);

    public static final native String SCIOpAVTransportGetPositionInfo_getRelTime(long l, SCIOpAVTransportGetPositionInfo sciopavtransportgetpositioninfo);

    public static final native long SCIOpAVTransportGetPositionInfo_getTrack(long l, SCIOpAVTransportGetPositionInfo sciopavtransportgetpositioninfo);

    public static final native String SCIOpAVTransportGetPositionInfo_getTrackDuration(long l, SCIOpAVTransportGetPositionInfo sciopavtransportgetpositioninfo);

    public static final native String SCIOpAVTransportGetPositionInfo_getTrackMetaData(long l, SCIOpAVTransportGetPositionInfo sciopavtransportgetpositioninfo);

    public static final native String SCIOpAVTransportGetPositionInfo_getTrackURI(long l, SCIOpAVTransportGetPositionInfo sciopavtransportgetpositioninfo);

    public static final native long SCIOpAVTransportGetRemainingSleepTimerDuration_getCurrentSleepTimerGeneration(long l, SCIOpAVTransportGetRemainingSleepTimerDuration sciopavtransportgetremainingsleeptimerduration);

    public static final native String SCIOpAVTransportGetRemainingSleepTimerDuration_getRemainingSleepTimerDuration(long l, SCIOpAVTransportGetRemainingSleepTimerDuration sciopavtransportgetremainingsleeptimerduration);

    public static final native String SCIOpAddServiceAccount_getAccountUDN(long l, SCIOpAddServiceAccount sciopaddserviceaccount);

    public static final native long SCIOpAddTracksToQueue_getFirstTrackNumberAdded(long l, SCIOpAddTracksToQueue sciopaddtrackstoqueue);

    public static final native long SCIOpAddTracksToQueue_getNewUpdateID(long l, SCIOpAddTracksToQueue sciopaddtrackstoqueue);

    public static final native long SCIOpAddTracksToQueue_getNumTracksAdded(long l, SCIOpAddTracksToQueue sciopaddtrackstoqueue);

    public static final native int SCIOpAlarmSave_getAssignedID(long l, SCIOpAlarmSave sciopalarmsave);

    public static final native long SCIOpAttachPrivateQueue_getAttachedPrivateQueue(long l, SCIOpAttachPrivateQueue sciopattachprivatequeue);

    public static final native String SCIOpAudioInGetAudioInputAttributes_getCurrentIcon(long l, SCIOpAudioInGetAudioInputAttributes sciopaudioingetaudioinputattributes);

    public static final native String SCIOpAudioInGetAudioInputAttributes_getCurrentName(long l, SCIOpAudioInGetAudioInputAttributes sciopaudioingetaudioinputattributes);

    public static final native int SCIOpAudioInGetLineInLevel_getCurrentLeftLineInLevel(long l, SCIOpAudioInGetLineInLevel sciopaudioingetlineinlevel);

    public static final native int SCIOpAudioInGetLineInLevel_getCurrentRightLineInLevel(long l, SCIOpAudioInGetLineInLevel sciopaudioingetlineinlevel);

    public static final native void SCIOpCBSwigBase_change_ownership(SCIOpCBSwigBase sciopcbswigbase, long l, boolean flag);

    public static final native void SCIOpCBSwigBase_director_connect(SCIOpCBSwigBase sciopcbswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCIOpCBSwigBase_dumpSCIObj(long l, SCIOpCBSwigBase sciopcbswigbase);

    public static final native String SCIOpCBSwigBase_dumpSCIObjSwigExplicitSCIOpCBSwigBase(long l, SCIOpCBSwigBase sciopcbswigbase);

    public static final native void SCIOpCB__operationComplete(long l, SCIOpCB sciopcb, long l1, int i);

    public static final native String SCIOpConnectionManagerGetProtocolInfo_getSink(long l, SCIOpConnectionManagerGetProtocolInfo sciopconnectionmanagergetprotocolinfo);

    public static final native String SCIOpConnectionManagerGetProtocolInfo_getSource(long l, SCIOpConnectionManagerGetProtocolInfo sciopconnectionmanagergetprotocolinfo);

    public static final native boolean SCIOpDevicePropertiesGetAutoplayLinkedZones_getIncludeLinkedZones(long l, SCIOpDevicePropertiesGetAutoplayLinkedZones sciopdevicepropertiesgetautoplaylinkedzones);

    public static final native String SCIOpDevicePropertiesGetAutoplayRoomUUID_getRoomUUID(long l, SCIOpDevicePropertiesGetAutoplayRoomUUID sciopdevicepropertiesgetautoplayroomuuid);

    public static final native int SCIOpDevicePropertiesGetAutoplayVolume_getCurrentVolume(long l, SCIOpDevicePropertiesGetAutoplayVolume sciopdevicepropertiesgetautoplayvolume);

    public static final native String SCIOpDevicePropertiesGetLEDState_getCurrentLEDState(long l, SCIOpDevicePropertiesGetLEDState sciopdevicepropertiesgetledstate);

    public static final native boolean SCIOpDevicePropertiesGetUseAutoplayVolume_getUseVolume(long l, SCIOpDevicePropertiesGetUseAutoplayVolume sciopdevicepropertiesgetuseautoplayvolume);

    public static final native long SCIOpFactory_createLoadAlbumArtOp(long l, SCIOpFactory sciopfactory, String s, boolean flag, int i, int j);

    public static final native long SCIOpFactory_createLoadLogoOp(long l, SCIOpFactory sciopfactory, String s, long l1);

    public static final native long SCIOpFactory_createRegisterLeadOp(long l, SCIOpFactory sciopfactory, String s, String s1, String s2, int i);

    public static final native long SCIOpFactory_createSubmitDiagnosticsOp(long l, SCIOpFactory sciopfactory);

    public static final native long SCIOpGenericUpdateQueue_getNewUpdateID(long l, SCIOpGenericUpdateQueue sciopgenericupdatequeue);

    public static final native String SCIOpGetAboutSonosString_getAssociatedDeviceIP(long l, SCIOpGetAboutSonosString sciopgetaboutsonosstring, boolean flag);

    public static final native String SCIOpGetAboutSonosString_getCopyright(long l, SCIOpGetAboutSonosString sciopgetaboutsonosstring);

    public static final native String SCIOpGetAboutSonosString_getCopyrightLegalLink(long l, SCIOpGetAboutSonosString sciopgetaboutsonosstring);

    public static final native String SCIOpGetAboutSonosString_getDevicesString(long l, SCIOpGetAboutSonosString sciopgetaboutsonosstring);

    public static final native String SCIOpGetAboutSonosString_getInternalVersion(long l, SCIOpGetAboutSonosString sciopgetaboutsonosstring, boolean flag);

    public static final native String SCIOpGetAboutSonosString_getMarketingVersion(long l, SCIOpGetAboutSonosString sciopgetaboutsonosstring, boolean flag);

    public static final native String SCIOpGetAboutSonosString_getPartnerCopyrightString(long l, SCIOpGetAboutSonosString sciopgetaboutsonosstring);

    public static final native String SCIOpGetAboutSonosString_getSonosID(long l, SCIOpGetAboutSonosString sciopgetaboutsonosstring, boolean flag);

    public static final native String SCIOpGetAboutSonosString_getString(long l, SCIOpGetAboutSonosString sciopgetaboutsonosstring);

    public static final native void SCIOpGetAboutSonosString_setCallback(long l, SCIOpGetAboutSonosString sciopgetaboutsonosstring, long l1, SCIGetAboutSonosStringCB scigetaboutsonosstringcb);

    public static final native long SCIOpGetTrackPositionInfo_getTrackDurationInSecs(long l, SCIOpGetTrackPositionInfo sciopgettrackpositioninfo);

    public static final native long SCIOpGetTrackPositionInfo_getTrackIndex(long l, SCIOpGetTrackPositionInfo sciopgettrackpositioninfo);

    public static final native long SCIOpGetTrackPositionInfo_getTrackPositionInSecs(long l, SCIOpGetTrackPositionInfo sciopgettrackpositioninfo);

    public static final native String SCIOpGetTrackPositionInfo_getTrackURI(long l, SCIOpGetTrackPositionInfo sciopgettrackpositioninfo);

    public static final native boolean SCIOpGetUsageDataShareOption_isUsageDataShareEnabled(long l, SCIOpGetUsageDataShareOption sciopgetusagedatashareoption);

    public static final native boolean SCIOpLoadAlbumArt_dataLoaded(long l, SCIOpLoadAlbumArt scioploadalbumart);

    public static final native long SCIOpLoadAlbumArt_getData(long l, SCIOpLoadAlbumArt scioploadalbumart, byte abyte0[]);

    public static final native long SCIOpLoadAlbumArt_getDataSize(long l, SCIOpLoadAlbumArt scioploadalbumart);

    public static final native int SCIOpLoadLogo_LLOP_RESULT_DOWNLOADED_get();

    public static final native String SCIOpLoadLogo_getLocalPath(long l, SCIOpLoadLogo scioploadlogo);

    public static final native int SCIOpLoadLogo_getOpResult(long l, SCIOpLoadLogo scioploadlogo);

    public static final native long SCIOpNetstartGetScanList_getEntries(long l, SCIOpNetstartGetScanList sciopnetstartgetscanlist);

    public static final native long SCIOpNetstartGetScanList_getEntriesWithBSSID(long l, SCIOpNetstartGetScanList sciopnetstartgetscanlist, String s, String s1);

    public static final native int SCIOpNetstartGetScanList_getNumEntries(long l, SCIOpNetstartGetScanList sciopnetstartgetscanlist);

    public static final native long SCIOpNewPrivateQueue_getCreatedPrivateQueue(long l, SCIOpNewPrivateQueue sciopnewprivatequeue);

    public static final native long SCIOpQueueAttachQueue_getQueueID(long l, SCIOpQueueAttachQueue sciopqueueattachqueue);

    public static final native String SCIOpQueueAttachQueue_getQueueOwnerContext(long l, SCIOpQueueAttachQueue sciopqueueattachqueue);

    public static final native long SCIOpQueueReplaceAllTracks_getNewQueueLength(long l, SCIOpQueueReplaceAllTracks sciopqueuereplacealltracks);

    public static final native long SCIOpQueueReplaceAllTracks_getNewUpdateID(long l, SCIOpQueueReplaceAllTracks sciopqueuereplacealltracks);

    public static final native int SCIOpRegEmailOptIn_REOIOP_RESULT_SUCCESS_get();

    public static final native int SCIOpRegEmailOptIn_getOpResult(long l, SCIOpRegEmailOptIn sciopregemailoptin);

    public static final native boolean SCIOpRenderingControlGetOutputFixed_getCurrentFixed(long l, SCIOpRenderingControlGetOutputFixed scioprenderingcontrolgetoutputfixed);

    public static final native boolean SCIOpRenderingControlGetSupportsOutputFixed_getCurrentSupportsFixed(long l, SCIOpRenderingControlGetSupportsOutputFixed scioprenderingcontrolgetsupportsoutputfixed);

    public static final native String SCIOpReplaceAccount_getAccountUDN(long l, SCIOpReplaceAccount sciopreplaceaccount);

    public static final native long SCIOpSubmitDiagnostics_getConfirmationNumber(long l, SCIOpSubmitDiagnostics sciopsubmitdiagnostics);

    public static final native String SCIOpSystemPropertyGetString_getString(long l, SCIOpSystemPropertyGetString sciopsystempropertygetstring);

    public static final native String SCIOpValidateServiceCredentials_getErrorString(long l, SCIOpValidateServiceCredentials sciopvalidateservicecredentials);

    public static final native String SCIOpValidateServiceCredentials_getLogonString(long l, SCIOpValidateServiceCredentials sciopvalidateservicecredentials);

    public static final native boolean SCIOpValidateServiceCredentials_isExpired(long l, SCIOpValidateServiceCredentials sciopvalidateservicecredentials);

    public static final native boolean SCIOpValidateServiceCredentials_isFullAccount(long l, SCIOpValidateServiceCredentials sciopvalidateservicecredentials);

    public static final native String SCIOpZoneGroupTopologyGetZoneGroupState_getZoneGroupState(long l, SCIOpZoneGroupTopologyGetZoneGroupState sciopzonegrouptopologygetzonegroupstate);

    public static final native void SCIOp__cancel(long l, SCIOp sciop);

    public static final native long SCIOp__start(long l, SCIOp sciop, long l1, SCIOpCB sciopcb);

    public static final native int SCIOp_getOpResult(long l, SCIOp sciop);

    public static final native String SCIOp_getResultString(long l, SCIOp sciop);

    public static final native boolean SCIOp_isRunning(long l, SCIOp sciop);

    public static final native long SCIOp_serialNumber(long l, SCIOp sciop);

    public static final native int SCIOperationProgress_getPercentageCompleted(long l, SCIOperationProgress scioperationprogress);

    public static final native String SCIOperationProgress_getProgressText(long l, SCIOperationProgress scioperationprogress);

    public static final native String SCIPANDORARESULTS_INTERFACE_get();

    public static final native String SCIPLAYQUEUEITEMSTATE_INTERFACE_get();

    public static final native String SCIPLAYQUEUEMGR_INTERFACE_get();

    public static final native String SCIPLAYQUEUE_INTERFACE_get();

    public static final native String SCIPLAYQUEUE_ONPRIVATEQUEUECHANGED_EVENT_get();

    public static final native String SCIPLAYQUEUE_ONQUEUEDATASOURCECHANGED_EVENT_get();

    public static final native String SCIPLAYQUEUE_ONQUEUEINVALIDATION_EVENT_get();

    public static final native String SCIPOWERSCROLLDATASOURCE_INTERFACE_get();

    public static final native String SCIPOWERSCROLL_PREFIX_CHARS_get();

    public static final native String SCIPROPERTYBAG_INTERFACE_get();

    public static final native long SCIPandoraResults_getAddToStationSearchable(long l, SCIPandoraResults scipandoraresults);

    public static final native long SCIPandoraResults_getDirectMatchItem(long l, SCIPandoraResults scipandoraresults);

    public static final native long SCIPandoraResults_getNumArtistResults(long l, SCIPandoraResults scipandoraresults);

    public static final native long SCIPandoraResults_getNumGenreResults(long l, SCIPandoraResults scipandoraresults);

    public static final native long SCIPandoraResults_getNumTrackResults(long l, SCIPandoraResults scipandoraresults);

    public static final native boolean SCIPandoraResults_hasDirectMatch(long l, SCIPandoraResults scipandoraresults);

    public static final native boolean SCIPandoraResults_hasMoreArtistResults(long l, SCIPandoraResults scipandoraresults);

    public static final native boolean SCIPandoraResults_hasMoreGenreResults(long l, SCIPandoraResults scipandoraresults);

    public static final native boolean SCIPandoraResults_hasMoreTrackResults(long l, SCIPandoraResults scipandoraresults);

    public static final native boolean SCIPandoraResults_isAddToStationResult(long l, SCIPandoraResults scipandoraresults);

    public static final native void SCIPandoraResults_setShowAllArtists(long l, SCIPandoraResults scipandoraresults, boolean flag);

    public static final native void SCIPandoraResults_setShowAllGenres(long l, SCIPandoraResults scipandoraresults, boolean flag);

    public static final native void SCIPandoraResults_setShowAllTracks(long l, SCIPandoraResults scipandoraresults, boolean flag);

    public static final native void SCIPlatformDateTimeProvider_change_ownership(SCIPlatformDateTimeProvider sciplatformdatetimeprovider, long l, boolean flag);

    public static final native void SCIPlatformDateTimeProvider_director_connect(SCIPlatformDateTimeProvider sciplatformdatetimeprovider, long l, boolean flag, boolean flag1);

    public static final native boolean SCIPlatformDateTimeProvider_doesPlatformTimeZoneMatch(long l, SCIPlatformDateTimeProvider sciplatformdatetimeprovider, long l1, SCITimeZone scitimezone);

    public static final native long SCIPlatformDateTimeProvider_getPlatformDateTime(long l, SCIPlatformDateTimeProvider sciplatformdatetimeprovider);

    public static final native int SCIPlayQueueItemState_getState(long l, SCIPlayQueueItemState sciplayqueueitemstate);

    public static final native boolean SCIPlayQueueItemState_isCurrentItem(long l, SCIPlayQueueItemState sciplayqueueitemstate);

    public static final native long SCIPlayQueueMgr_createAppendItemsOp(long l, SCIPlayQueueMgr sciplayqueuemgr, long l1, SCIStringArray scistringarray, long l2, 
            SCIStringArray scistringarray1, boolean flag, boolean flag1, long l3);

    public static final native long SCIPlayQueueMgr_createInsertItemsOp(long l, SCIPlayQueueMgr sciplayqueuemgr, long l1, SCIStringArray scistringarray, long l2, 
            SCIStringArray scistringarray1, long l3, boolean flag, boolean flag1, long l4);

    public static final native long SCIPlayQueueMgr_createMoveSelectedItemsOp(long l, SCIPlayQueueMgr sciplayqueuemgr, long l1);

    public static final native long SCIPlayQueueMgr_createRemoveAllItemsOp(long l, SCIPlayQueueMgr sciplayqueuemgr, long l1);

    public static final native long SCIPlayQueueMgr_createRemoveItemsOp(long l, SCIPlayQueueMgr sciplayqueuemgr, long l1, long l2, long l3);

    public static final native long SCIPlayQueueMgr_createRemoveSelectedItemsOp(long l, SCIPlayQueueMgr sciplayqueuemgr);

    public static final native long SCIPlayQueueMgr_createReorderItemsOp(long l, SCIPlayQueueMgr sciplayqueuemgr, long l1, long l2, long l3, long l4);

    public static final native long SCIPlayQueueMgr_createReplaceAllTracksOp(long l, SCIPlayQueueMgr sciplayqueuemgr, long l1, SCIStringArray scistringarray, long l2, 
            SCIStringArray scistringarray1, long l3, String s, long l4);

    public static final native long SCIPlayQueueMgr_createSaveToSonosPlaylistOp(long l, SCIPlayQueueMgr sciplayqueuemgr, String s, String s1);

    public static final native int SCIPlayQueueMgr_getCurrentItemIndex(long l, SCIPlayQueueMgr sciplayqueuemgr);

    public static final native String SCIPlayQueueMgr_getRecommendedTitle(long l, SCIPlayQueueMgr sciplayqueuemgr);

    public static final native long SCIPlayQueueMgr_getSonosPlaylistValidator(long l, SCIPlayQueueMgr sciplayqueuemgr);

    public static final native void SCIPlayQueueMgr_getSonosPlaylists(long l, SCIPlayQueueMgr sciplayqueuemgr, long l1, SCIGetSonosPlaylistsCB scigetsonosplaylistscb);

    public static final native long SCIPlayQueueMgr_getSonosPlaylistsEnum(long l, SCIPlayQueueMgr sciplayqueuemgr);

    public static final native String SCIPlayQueueMgr_getTrackCountTitle(long l, SCIPlayQueueMgr sciplayqueuemgr);

    public static final native boolean SCIPlayQueueMgr_isInUse(long l, SCIPlayQueueMgr sciplayqueuemgr);

    public static final native boolean SCIPlayQueueMgr_isInfiniteQueue(long l, SCIPlayQueueMgr sciplayqueuemgr);

    public static final native long SCIPlayQueue_createDataSource(long l, SCIPlayQueue sciplayqueue);

    public static final native long SCIPlayQueue_getQueueID(long l, SCIPlayQueue sciplayqueue);

    public static final native String SCIPlayQueue_getQueueOwnerID(long l, SCIPlayQueue sciplayqueue);

    public static final native long SCIPlayQueue_getUpdateID(long l, SCIPlayQueue sciplayqueue);

    public static final native boolean SCIPlayQueue_isValid(long l, SCIPlayQueue sciplayqueue);

    public static final native void SCIPlayQueue_subscribe(long l, SCIPlayQueue sciplayqueue, long l1, SCIEventSink scieventsink);

    public static final native void SCIPlayQueue_unsubscribe(long l, SCIPlayQueue sciplayqueue, long l1, SCIEventSink scieventsink);

    public static final native long SCIPowerscrollDataSource_getCountForPrefix(long l, SCIPowerscrollDataSource scipowerscrolldatasource, String s);

    public static final native String SCIPowerscrollDataSource_getPowerscrollCSV(long l, SCIPowerscrollDataSource scipowerscrolldatasource);

    public static final native String SCIPowerscrollDataSource_getPrefixForIndex(long l, SCIPowerscrollDataSource scipowerscrolldatasource, long l1);

    public static final native int SCIPowerscrollDataSource_getStartIndexForPrefix(long l, SCIPowerscrollDataSource scipowerscrolldatasource, String s);

    public static final native boolean SCIPowerscrollDataSource_hasPrefixData(long l, SCIPowerscrollDataSource scipowerscrolldatasource);

    public static final native boolean SCIPowerscrollDataSource_isPowerscrollInfoReady(long l, SCIPowerscrollDataSource scipowerscrolldatasource);

    public static final native void SCIPropertyBag_copyAllValuesTo(long l, SCIPropertyBag scipropertybag, long l1, SCIPropertyBag scipropertybag1);

    public static final native boolean SCIPropertyBag_doesPropExist(long l, SCIPropertyBag scipropertybag, String s);

    public static final native boolean SCIPropertyBag_getBoolProp__SWIG_0(long l, SCIPropertyBag scipropertybag, String s);

    public static final native boolean SCIPropertyBag_getBoolProp__SWIG_1(long l, SCIPropertyBag scipropertybag, String s, long l1);

    public static final native int SCIPropertyBag_getIntProp__SWIG_0(long l, SCIPropertyBag scipropertybag, String s);

    public static final native boolean SCIPropertyBag_getIntProp__SWIG_1(long l, SCIPropertyBag scipropertybag, String s, long l1);

    public static final native long SCIPropertyBag_getKeys(long l, SCIPropertyBag scipropertybag);

    public static final native long SCIPropertyBag_getPropBagProp__SWIG_0(long l, SCIPropertyBag scipropertybag, String s);

    public static final native boolean SCIPropertyBag_getPropBagProp__SWIG_1(long l, SCIPropertyBag scipropertybag, String s, long l1);

    public static final native int SCIPropertyBag_getPropType(long l, SCIPropertyBag scipropertybag, String s);

    public static final native long SCIPropertyBag_getProperty(long l, SCIPropertyBag scipropertybag, String s);

    public static final native long SCIPropertyBag_getStrArrayProp__SWIG_0(long l, SCIPropertyBag scipropertybag, String s);

    public static final native boolean SCIPropertyBag_getStrArrayProp__SWIG_1(long l, SCIPropertyBag scipropertybag, String s, long l1);

    public static final native String SCIPropertyBag_getStrProp__SWIG_0(long l, SCIPropertyBag scipropertybag, String s);

    public static final native boolean SCIPropertyBag_getStrProp__SWIG_1(long l, SCIPropertyBag scipropertybag, String s, Object aobj[]);

    public static final native void SCIPropertyBag_removeProp(long l, SCIPropertyBag scipropertybag, String s);

    public static final native void SCIPropertyBag_setBoolProp(long l, SCIPropertyBag scipropertybag, String s, boolean flag);

    public static final native void SCIPropertyBag_setIntProp(long l, SCIPropertyBag scipropertybag, String s, int i);

    public static final native void SCIPropertyBag_setPropBagProp(long l, SCIPropertyBag scipropertybag, String s, long l1, SCIPropertyBag scipropertybag1);

    public static final native void SCIPropertyBag_setStrArrayProp(long l, SCIPropertyBag scipropertybag, String s, long l1, SCIStringArray scistringarray);

    public static final native void SCIPropertyBag_setStrProp(long l, SCIPropertyBag scipropertybag, String s, String s1);

    public static final native boolean SCIProperty_getBoolValue(long l, SCIProperty sciproperty);

    public static final native int SCIProperty_getIntValue(long l, SCIProperty sciproperty);

    public static final native String SCIProperty_getKey(long l, SCIProperty sciproperty);

    public static final native long SCIProperty_getPropBagValue(long l, SCIProperty sciproperty);

    public static final native int SCIProperty_getPropType(long l, SCIProperty sciproperty);

    public static final native long SCIProperty_getStrArrayValue(long l, SCIProperty sciproperty);

    public static final native String SCIProperty_getStrValue(long l, SCIProperty sciproperty);

    public static final native String SCIRECURRENCE_INTERFACE_get();

    public static final native String SCIRESOURCE_INTERFACE_get();

    public static final native String SCIROOMRESOURCE_INTERFACE_get();

    public static final native boolean SCIRecurrence_allDaysDisabled(long l, SCIRecurrence scirecurrence);

    public static final native boolean SCIRecurrence_allDaysEnabled(long l, SCIRecurrence scirecurrence);

    public static final native long SCIRecurrence_clone(long l, SCIRecurrence scirecurrence);

    public static final native void SCIRecurrence_disableAllDays(long l, SCIRecurrence scirecurrence);

    public static final native void SCIRecurrence_enableAllDays(long l, SCIRecurrence scirecurrence);

    public static final native void SCIRecurrence_enableForDayOfWeek(long l, SCIRecurrence scirecurrence, int i, boolean flag);

    public static final native int SCIRecurrence_getRecurrenceType(long l, SCIRecurrence scirecurrence);

    public static final native boolean SCIRecurrence_isEnabledForDayOfWeek(long l, SCIRecurrence scirecurrence, int i);

    public static final native void SCIRecurrence_setRecurrenceType(long l, SCIRecurrence scirecurrence, int i);

    public static final native String SCIResource_getID(long l, SCIResource sciresource);

    public static final native int SCIResource_getIconID(long l, SCIResource sciresource);

    public static final native String SCIResource_getName(long l, SCIResource sciresource);

    public static final native int SCIRoomResource_getIconID(long l, SCIRoomResource sciroomresource);

    public static final native String SCIRoomResource_getIconURI(long l, SCIRoomResource sciroomresource);

    public static final native String SCIRoomResource_getName(long l, SCIRoomResource sciroomresource);

    public static final native int SCIRoomResource_getRoomID(long l, SCIRoomResource sciroomresource);

    public static final native String SCISCROBBLINGSERVICE_INTERFACE_get();

    public static final native String SCISEARCHABLECATEGORY_INTERFACE_get();

    public static final native String SCISEARCHABLE_INTERFACE_get();

    public static final native String SCISELECTABLEITEM_INTERFACE_get();

    public static final native String SCISELECTIONMANAGER_INTERFACE_get();

    public static final native String SCISERVICEACCOUNTMANAGER_INTERFACE_get();

    public static final native String SCISERVICEACCOUNT_INTERFACE_get();

    public static final native String SCISERVICEDESCRIPTORMANAGER_INTERFACE_get();

    public static final native String SCISERVICEDESCRIPTOR_INTERFACE_get();

    public static final native String SCISETTINGSPROPERTY_INTERFACE_get();

    public static final native String SCISETTINGS_BROWSE_ITEM_INTERFACE_get();

    public static final native String SCISETUPWIZARD_INTERFACE_get();

    public static final native String SCISETUPWIZARD_ONSTATECHANGED_EVENT_get();

    public static final native String SCISETUPWIZARD_ONSTATETRANSITIONSENABLED_EVENT_get();

    public static final native String SCISHAREMANAGER_INTERFACE_get();

    public static final native String SCISHAREMANAGER_ONSHARESCHANGED_EVENT_get();

    public static final native String SCISIMPLEMESSAGINGSERVICE_INTERFACE_get();

    public static final native String SCISONOSNETSETUPWIZARD_INTERFACE_get();

    public static final native String SCISONOSNETSETUPWIZARD_ONSTATECHANGED_EVENT_get();

    public static final native String SCISONOSPLAYLIST_INTERFACE_get();

    public static final native String SCISOUNDBARWIZARD_INTERFACE_get();

    public static final native String SCISOUNDBARWIZARD_ONSTATECHANGED_EVENT_get();

    public static final native String SCISPINNERSETTINGSPROPERTY_INTERFACE_get();

    public static final native String SCISSOUNDBARWIZARD_INTERFACE_get();

    public static final native String SCISTRINGFROMCUSTOMSETTINGSPROPERTY_INTERFACE_get();

    public static final native String SCISTRINGFROMLISTSETTINGSPROPERTY_INTERFACE_get();

    public static final native String SCISTRINGINPUT_INTERFACE_get();

    public static final native String SCISTRINGSETTINGSPROPERTY_INTERFACE_get();

    public static final native String SCISUBCALIBRATOR_INTERFACE_get();

    public static final native String SCISUBCALIBRATOR_ONSTATECHANGED_EVENT_get();

    public static final native String SCISYSTEMTIME_INTERFACE_get();

    public static final native String SCISYSTEM_INTERFACE_get();

    public static final native String SCISYSTEM_ONDEALERLOCKCHANGED_EVENT_get();

    public static final native String SCISYSTEM_ONOPRUNNINGCOUNTCHANGED_EVENT_get();

    public static final native long SCIScrobblingService_createEnableScrobblingOp(long l, SCIScrobblingService sciscrobblingservice, boolean flag);

    public static final native boolean SCIScrobblingService_isLoggingEnabled(long l, SCIScrobblingService sciscrobblingservice);

    public static final native String SCISearchableCategory_getCanonicalID(long l, SCISearchableCategory scisearchablecategory);

    public static final native String SCISearchableCategory_getCanonicalName(long l, SCISearchableCategory scisearchablecategory);

    public static final native String SCISearchableCategory_getCategoryId(long l, SCISearchableCategory scisearchablecategory);

    public static final native String SCISearchableCategory_getName(long l, SCISearchableCategory scisearchablecategory);

    public static final native String SCISearchableCategory_getSCUriForSearchTerm(long l, SCISearchableCategory scisearchablecategory, String s);

    public static final native boolean SCISearchableCategory_isIncremental(long l, SCISearchableCategory scisearchablecategory);

    public static final native String SCISearchable_getBrowseCPID(long l, SCISearchable scisearchable);

    public static final native long SCISearchable_getCategories(long l, SCISearchable scisearchable);

    public static final native boolean SCISearchable_getIsAggregatedSearchable(long l, SCISearchable scisearchable);

    public static final native int SCISearchable_getLogoType(long l, SCISearchable scisearchable);

    public static final native String SCISearchable_getLogoURL(long l, SCISearchable scisearchable);

    public static final native String SCISearchable_getPresentationTextForSearch(long l, SCISearchable scisearchable);

    public static final native long SCISearchable_getServiceDescriptor(long l, SCISearchable scisearchable);

    public static final native String SCISearchable_getShortTitle(long l, SCISearchable scisearchable);

    public static final native String SCISearchable_getTitle(long l, SCISearchable scisearchable);

    public static final native boolean SCISelectableItem_canSelect(long l, SCISelectableItem sciselectableitem);

    public static final native boolean SCISelectableItem_isSelected(long l, SCISelectableItem sciselectableitem);

    public static final native void SCISelectableItem_setSelected(long l, SCISelectableItem sciselectableitem, boolean flag);

    public static final native void SCISelectionManager_deselectAll(long l, SCISelectionManager sciselectionmanager);

    public static final native void SCISelectionManager_deselectRange(long l, SCISelectionManager sciselectionmanager, long l1, long l2);

    public static final native int SCISelectionManager_getNumOfSelectedItems(long l, SCISelectionManager sciselectionmanager);

    public static final native long SCISelectionManager_getSelectedItems(long l, SCISelectionManager sciselectionmanager);

    public static final native void SCISelectionManager_selectAll(long l, SCISelectionManager sciselectionmanager);

    public static final native void SCISelectionManager_selectRange(long l, SCISelectionManager sciselectionmanager, long l1, long l2);

    public static final native boolean SCIServiceAccountFilter_acceptsServiceAccount(long l, SCIServiceAccountFilter sciserviceaccountfilter, long l1, SCIServiceAccount sciserviceaccount);

    public static final native boolean SCIServiceAccountManager_getDefaultAccount(long l, SCIServiceAccountManager sciserviceaccountmanager, String s, Object aobj[]);

    public static final native long SCIServiceAccountManager_getFilteredServiceAccounts(long l, SCIServiceAccountManager sciserviceaccountmanager, long l1, SCIServiceAccountFilter sciserviceaccountfilter);

    public static final native long SCIServiceAccountManager_getServiceDescriptorManager(long l, SCIServiceAccountManager sciserviceaccountmanager);

    public static final native boolean SCIServiceAccountManager_isAccountToDisplay(long l, SCIServiceAccountManager sciserviceaccountmanager, String s);

    public static final native boolean SCIServiceAccountManager_isDefaultAccount(long l, SCIServiceAccountManager sciserviceaccountmanager, String s);

    public static final native long SCIServiceAccountManager_lookupServiceAccount(long l, SCIServiceAccountManager sciserviceaccountmanager, String s);

    public static final native boolean SCIServiceAccountManager_setDefaultAccount(long l, SCIServiceAccountManager sciserviceaccountmanager, String s);

    public static final native void SCIServiceAccountManager_subscribe(long l, SCIServiceAccountManager sciserviceaccountmanager, long l1, SCIEventSink scieventsink);

    public static final native void SCIServiceAccountManager_unsubscribe(long l, SCIServiceAccountManager sciserviceaccountmanager, long l1, SCIEventSink scieventsink);

    public static final native boolean SCIServiceAccountManager_validateDefaultAccount(long l, SCIServiceAccountManager sciserviceaccountmanager, String s);

    public static final native boolean SCIServiceAccount_canChangePassword(long l, SCIServiceAccount sciserviceaccount);

    public static final native boolean SCIServiceAccount_canReauthorizeAccount(long l, SCIServiceAccount sciserviceaccount);

    public static final native boolean SCIServiceAccount_canReplaceAccount(long l, SCIServiceAccount sciserviceaccount);

    public static final native long SCIServiceAccount_createMigrateTrialAccountOp(long l, SCIServiceAccount sciserviceaccount, String s, String s1);

    public static final native long SCIServiceAccount_createRemoveAccountAction(long l, SCIServiceAccount sciserviceaccount);

    public static final native long SCIServiceAccount_createRemoveAccountOp(long l, SCIServiceAccount sciserviceaccount);

    public static final native long SCIServiceAccount_createReplaceAccountOp(long l, SCIServiceAccount sciserviceaccount, String s, String s1);

    public static final native long SCIServiceAccount_createReplaceOAuthAccountOp(long l, SCIServiceAccount sciserviceaccount, String s, String s1, String s2);

    public static final native long SCIServiceAccount_createSetAccountNicknameOp(long l, SCIServiceAccount sciserviceaccount, String s);

    public static final native long SCIServiceAccount_createUpdateAccountPasswordOp(long l, SCIServiceAccount sciserviceaccount, String s);

    public static final native long SCIServiceAccount_getAllActions(long l, SCIServiceAccount sciserviceaccount);

    public static final native String SCIServiceAccount_getID(long l, SCIServiceAccount sciserviceaccount);

    public static final native String SCIServiceAccount_getLearnMoreText(long l, SCIServiceAccount sciserviceaccount);

    public static final native String SCIServiceAccount_getLogoStr(long l, SCIServiceAccount sciserviceaccount);

    public static final native int SCIServiceAccount_getLogoType(long l, SCIServiceAccount sciserviceaccount);

    public static final native String SCIServiceAccount_getLogonString(long l, SCIServiceAccount sciserviceaccount);

    public static final native String SCIServiceAccount_getNickname(long l, SCIServiceAccount sciserviceaccount);

    public static final native String SCIServiceAccount_getServiceDescriptorID(long l, SCIServiceAccount sciserviceaccount);

    public static final native String SCIServiceAccount_getShortTitle(long l, SCIServiceAccount sciserviceaccount);

    public static final native String SCIServiceAccount_getTitle(long l, SCIServiceAccount sciserviceaccount);

    public static final native boolean SCIServiceAccount_isAnonymous(long l, SCIServiceAccount sciserviceaccount);

    public static final native boolean SCIServiceAccount_isTrialAccount(long l, SCIServiceAccount sciserviceaccount);

    public static final native boolean SCIServiceAccount_isValid(long l, SCIServiceAccount sciserviceaccount);

    public static final native boolean SCIServiceDescriptorFilter_acceptsServiceDescriptor(long l, SCIServiceDescriptorFilter sciservicedescriptorfilter, long l1, SCIServiceDescriptor sciservicedescriptor);

    public static final native long SCIServiceDescriptorManager_getAudibleManager(long l, SCIServiceDescriptorManager sciservicedescriptormanager);

    public static final native long SCIServiceDescriptorManager_getFilteredServiceDescriptors(long l, SCIServiceDescriptorManager sciservicedescriptormanager, long l1, SCIServiceDescriptorFilter sciservicedescriptorfilter);

    public static final native long SCIServiceDescriptorManager_getServiceAccountManager(long l, SCIServiceDescriptorManager sciservicedescriptormanager);

    public static final native long SCIServiceDescriptorManager_lookupServiceDescriptor(long l, SCIServiceDescriptorManager sciservicedescriptormanager, String s);

    public static final native long SCIServiceDescriptorManager_lookupServiceDescriptorBySCUri(long l, SCIServiceDescriptorManager sciservicedescriptormanager, String s);

    public static final native void SCIServiceDescriptorManager_subscribe(long l, SCIServiceDescriptorManager sciservicedescriptormanager, long l1, SCIEventSink scieventsink);

    public static final native void SCIServiceDescriptorManager_unsubscribe(long l, SCIServiceDescriptorManager sciservicedescriptormanager, long l1, SCIEventSink scieventsink);

    public static final native boolean SCIServiceDescriptor_canAddAccount(long l, SCIServiceDescriptor sciservicedescriptor);

    public static final native long SCIServiceDescriptor_createAddAccountAction(long l, SCIServiceDescriptor sciservicedescriptor, String s, String s1);

    public static final native long SCIServiceDescriptor_createAddAccountOp(long l, SCIServiceDescriptor sciservicedescriptor, String s, String s1);

    public static final native long SCIServiceDescriptor_createAddLinkCodeAccountOp(long l, SCIServiceDescriptor sciservicedescriptor, String s, String s1, String s2);

    public static final native long SCIServiceDescriptor_createProvisionCredentialedTrialAccountOp(long l, SCIServiceDescriptor sciservicedescriptor, String s, String s1);

    public static final native long SCIServiceDescriptor_createProvisionTrialAccountOp(long l, SCIServiceDescriptor sciservicedescriptor);

    public static final native long SCIServiceDescriptor_createValidateServiceCredentialsOp(long l, SCIServiceDescriptor sciservicedescriptor, String s, String s1);

    public static final native boolean SCIServiceDescriptor_doesServiceAccountSupportInterface(long l, SCIServiceDescriptor sciservicedescriptor, String s);

    public static final native int SCIServiceDescriptor_getCredentialsType(long l, SCIServiceDescriptor sciservicedescriptor);

    public static final native String SCIServiceDescriptor_getDescription(long l, SCIServiceDescriptor sciservicedescriptor);

    public static final native String SCIServiceDescriptor_getID(long l, SCIServiceDescriptor sciservicedescriptor);

    public static final native String SCIServiceDescriptor_getLogoStr(long l, SCIServiceDescriptor sciservicedescriptor);

    public static final native int SCIServiceDescriptor_getLogoType(long l, SCIServiceDescriptor sciservicedescriptor);

    public static final native String SCIServiceDescriptor_getShortTitle(long l, SCIServiceDescriptor sciservicedescriptor);

    public static final native String SCIServiceDescriptor_getTitle(long l, SCIServiceDescriptor sciservicedescriptor);

    public static final native boolean SCIServiceDescriptor_hasAccount(long l, SCIServiceDescriptor sciservicedescriptor);

    public static final native boolean SCIServiceDescriptor_isPreload(long l, SCIServiceDescriptor sciservicedescriptor);

    public static final native boolean SCIServiceDescriptor_isSonosSoundLab(long l, SCIServiceDescriptor sciservicedescriptor);

    public static final native boolean SCIServiceDescriptor_supportsMergableTrial(long l, SCIServiceDescriptor sciservicedescriptor);

    public static final native boolean SCIServiceDescriptor_supportsTrial(long l, SCIServiceDescriptor sciservicedescriptor);

    public static final native long SCISettingsBrowseItem_getProperties(long l, SCISettingsBrowseItem scisettingsbrowseitem);

    public static final native long SCISettingsBrowseItem_getProperty(long l, SCISettingsBrowseItem scisettingsbrowseitem, long l1);

    public static final native String SCISettingsBrowseItem_getValueString(long l, SCISettingsBrowseItem scisettingsbrowseitem);

    public static final native long SCISettingsProperty_getID(long l, SCISettingsProperty scisettingsproperty);

    public static final native String SCISettingsProperty_getInterfaceID(long l, SCISettingsProperty scisettingsproperty);

    public static final native String SCISettingsProperty_getName(long l, SCISettingsProperty scisettingsproperty);

    public static final native int SCISetupWizard_JOIN_BUTTON_UNKNOWN_BOOST_get();

    public static final native int SCISetupWizard_JOIN_BUTTON_UNKNOWN_get();

    public static final native int SCISetupWizard_SETUP_STRID_BODY_get();

    public static final native int SCISetupWizard_SETUP_STRID_TITLE_1_get();

    public static final native int SCISetupWizard_STATE_SETUP_COMPLETE_get();

    public static final native int SCISetupWizard_STATE_SETUP_INIT_get();

    public static final native int SCISetupWizard_STATE_SETUP_UNKNOWN_get();

    public static final native boolean SCISetupWizard_completedAndIsAssociatedToHousehold(long l, SCISetupWizard scisetupwizard);

    public static final native boolean SCISetupWizard_configuringWiredComponent(long l, SCISetupWizard scisetupwizard);

    public static final native long SCISetupWizard_createGetAutoplayGroupedZonesOp(long l, SCISetupWizard scisetupwizard);

    public static final native long SCISetupWizard_createGetAutoplayVolumeOp(long l, SCISetupWizard scisetupwizard);

    public static final native long SCISetupWizard_deviceExtraSetupWizard(long l, SCISetupWizard scisetupwizard);

    public static final native boolean SCISetupWizard_deviceRequiresExtraSetup(long l, SCISetupWizard scisetupwizard);

    public static final native String SCISetupWizard_getAutoplayDevice(long l, SCISetupWizard scisetupwizard);

    public static final native long SCISetupWizard_getCandidateDevices(long l, SCISetupWizard scisetupwizard);

    public static final native String SCISetupWizard_getConnectedDeviceID(long l, SCISetupWizard scisetupwizard);

    public static final native int SCISetupWizard_getDeviceModelFound(long l, SCISetupWizard scisetupwizard);

    public static final native String SCISetupWizard_getDeviceModelString(long l, SCISetupWizard scisetupwizard);

    public static final native String SCISetupWizard_getDeviceName(long l, SCISetupWizard scisetupwizard);

    public static final native long SCISetupWizard_getDeviceNameInput(long l, SCISetupWizard scisetupwizard);

    public static final native boolean SCISetupWizard_getDeviceSetupDidNotName(long l, SCISetupWizard scisetupwizard);

    public static final native boolean SCISetupWizard_getDeviceSetupFailed(long l, SCISetupWizard scisetupwizard);

    public static final native int SCISetupWizard_getDeviceType(long l, SCISetupWizard scisetupwizard);

    public static final native boolean SCISetupWizard_getExistingHousehold(long l, SCISetupWizard scisetupwizard);

    public static final native int SCISetupWizard_getJoinButtonType(long l, SCISetupWizard scisetupwizard);

    public static final native int SCISetupWizard_getMode(long l, SCISetupWizard scisetupwizard);

    public static final native long SCISetupWizard_getSubCalibrator(long l, SCISetupWizard scisetupwizard);

    public static final native int SCISetupWizard_getSubSpeakerSizeIndex(long l, SCISetupWizard scisetupwizard);

    public static final native long SCISetupWizard_getUnconfiguredDevices(long l, SCISetupWizard scisetupwizard);

    public static final native int SCISetupWizard_getWifiConnectionTimeout(long l, SCISetupWizard scisetupwizard);

    public static final native void SCISetupWizard_setAddAnotherDevice(long l, SCISetupWizard scisetupwizard, boolean flag);

    public static final native void SCISetupWizard_setAutoplayDevice(long l, SCISetupWizard scisetupwizard, String s);

    public static final native void SCISetupWizard_setAutoplayGroupedZones(long l, SCISetupWizard scisetupwizard, boolean flag);

    public static final native void SCISetupWizard_setAutoplayVolume(long l, SCISetupWizard scisetupwizard, long l1);

    public static final native void SCISetupWizard_setConnectionTimeout(long l, SCISetupWizard scisetupwizard, int i);

    public static final native void SCISetupWizard_setDeviceIcon(long l, SCISetupWizard scisetupwizard, String s);

    public static final native void SCISetupWizard_setDeviceToConfigure(long l, SCISetupWizard scisetupwizard, String s);

    public static final native void SCISetupWizard_setDoRegistration(long l, SCISetupWizard scisetupwizard, boolean flag);

    public static final native void SCISetupWizard_setRequestedSubMaster(long l, SCISetupWizard scisetupwizard, String s, String s1);

    public static final native void SCISetupWizard_setRetryConnecting(long l, SCISetupWizard scisetupwizard, boolean flag);

    public static final native void SCISetupWizard_setSkipInitialVersionCheck(long l, SCISetupWizard scisetupwizard, boolean flag);

    public static final native void SCISetupWizard_setSubChooseRoom(long l, SCISetupWizard scisetupwizard, boolean flag);

    public static final native void SCISetupWizard_setSubPhaseBIsLouder(long l, SCISetupWizard scisetupwizard, boolean flag);

    public static final native void SCISetupWizard_setSubRemove(long l, SCISetupWizard scisetupwizard, boolean flag);

    public static final native void SCISetupWizard_setSubSpeakerSizeIndex(long l, SCISetupWizard scisetupwizard, int i);

    public static final native void SCISetupWizard_setSubTryAgain(long l, SCISetupWizard scisetupwizard, boolean flag);

    public static final native boolean SCISetupWizard_shouldShowExistingHouseholdConfirmation(long l, SCISetupWizard scisetupwizard);

    public static final native long SCIShareManager_createAddShareOp(long l, SCIShareManager scisharemanager, String s, String s1, String s2);

    public static final native long SCIShareManager_createRemoveShareOp(long l, SCIShareManager scisharemanager, long l1, SCIShare scishare);

    public static final native long SCIShareManager_getShareDataSource(long l, SCIShareManager scisharemanager);

    public static final native long SCIShareManager_getShares(long l, SCIShareManager scisharemanager);

    public static final native boolean SCIShareManager_hasShares(long l, SCIShareManager scisharemanager);

    public static final native void SCIShareManager_subscribe(long l, SCIShareManager scisharemanager, long l1, SCIEventSink scieventsink);

    public static final native void SCIShareManager_unsubscribe(long l, SCIShareManager scisharemanager, long l1, SCIEventSink scieventsink);

    public static final native String SCIShare_getName(long l, SCIShare scishare);

    public static final native String SCIShare_getPath(long l, SCIShare scishare);

    public static final native int SCISonosNetSetupWizard_STATE_SONOSNETSETUP_COMPLETE_get();

    public static final native int SCISonosNetSetupWizard_STATE_SONOSNETSETUP_INIT_get();

    public static final native int SCISonosNetSetupWizard_STATE_SONOSNETSETUP_UNKNOWN_get();

    public static final native String SCISonosPlaylist_getID(long l, SCISonosPlaylist scisonosplaylist);

    public static final native String SCISonosPlaylist_getTitle(long l, SCISonosPlaylist scisonosplaylist);

    public static final native int SCISoundbarWizard_LEARN_BUTTON_INVALID_get();

    public static final native int SCISoundbarWizard_LEARN_BUTTON_VOLUP_get();

    public static final native int SCISoundbarWizard_SOUNDBAR_STRID_BODY_get();

    public static final native int SCISoundbarWizard_SOUNDBAR_STRID_TITLE_1_get();

    public static final native int SCISoundbarWizard_STATE_SOUNDBAR_COMPLETE_get();

    public static final native int SCISoundbarWizard_STATE_SOUNDBAR_INIT_get();

    public static final native int SCISoundbarWizard_STATE_SOUNDBAR_UNKNOWN_get();

    public static final native boolean SCISoundbarWizard_completedSuccessfully(long l, SCISoundbarWizard scisoundbarwizard);

    public static final native int SCISoundbarWizard_currentlyAttaching(long l, SCISoundbarWizard scisoundbarwizard);

    public static final native int SCISoundbarWizard_getCurrentRemoteButton(long l, SCISoundbarWizard scisoundbarwizard);

    public static final native int SCISoundbarWizard_getMode(long l, SCISoundbarWizard scisoundbarwizard);

    public static final native int SCISoundbarWizard_getSelection(long l, SCISoundbarWizard scisoundbarwizard);

    public static final native int SCISoundbarWizard_getSoundbarSurroundSetup(long l, SCISoundbarWizard scisoundbarwizard);

    public static final native long SCISoundbarWizard_getSubCalibrator(long l, SCISoundbarWizard scisoundbarwizard);

    public static final native boolean SCISoundbarWizard_isComponentAttached(long l, SCISoundbarWizard scisoundbarwizard, int i);

    public static final native void SCISoundbarWizard_setComponentDistance(long l, SCISoundbarWizard scisoundbarwizard, int i, int j);

    public static final native void SCISoundbarWizard_setComponentToAttach(long l, SCISoundbarWizard scisoundbarwizard, int i);

    public static final native void SCISoundbarWizard_setInputSelected(long l, SCISoundbarWizard scisoundbarwizard, boolean flag);

    public static final native void SCISoundbarWizard_setSelection(long l, SCISoundbarWizard scisoundbarwizard, int i);

    public static final native void SCISoundbarWizard_setSoundbarSubSetup(long l, SCISoundbarWizard scisoundbarwizard, boolean flag);

    public static final native void SCISoundbarWizard_setSoundbarSurroundSetup(long l, SCISoundbarWizard scisoundbarwizard, int i);

    public static final native void SCISoundbarWizard_setSubPhaseBIsLouder(long l, SCISoundbarWizard scisoundbarwizard, boolean flag);

    public static final native void SCISoundbarWizard_setTVBuiltInDisabled(long l, SCISoundbarWizard scisoundbarwizard, boolean flag);

    public static final native void SCISoundbarWizard_setTryRemoteListeningAgain(long l, SCISoundbarWizard scisoundbarwizard, boolean flag);

    public static final native void SCISoundbarWizard_setTryTVSignalDetectionAgain(long l, SCISoundbarWizard scisoundbarwizard, boolean flag);

    public static final native String SCISpinnerSettingsProperty_getValue(long l, SCISpinnerSettingsProperty scispinnersettingsproperty);

    public static final native void SCIStackTraceCaptureDelegateSwigBase_change_ownership(SCIStackTraceCaptureDelegateSwigBase scistacktracecapturedelegateswigbase, long l, boolean flag);

    public static final native void SCIStackTraceCaptureDelegateSwigBase_director_connect(SCIStackTraceCaptureDelegateSwigBase scistacktracecapturedelegateswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCIStackTraceCaptureDelegateSwigBase_dumpSCIObj(long l, SCIStackTraceCaptureDelegateSwigBase scistacktracecapturedelegateswigbase);

    public static final native String SCIStackTraceCaptureDelegateSwigBase_dumpSCIObjSwigExplicitSCIStackTraceCaptureDelegateSwigBase(long l, SCIStackTraceCaptureDelegateSwigBase scistacktracecapturedelegateswigbase);

    public static final native void SCIStackTraceCaptureDelegate_stackTraceCaptured(long l, SCIStackTraceCaptureDelegate scistacktracecapturedelegate, String s);

    public static final native void SCIStringArray_append(long l, SCIStringArray scistringarray, String s);

    public static final native void SCIStringArray_clear(long l, SCIStringArray scistringarray);

    public static final native String SCIStringArray_getAt(long l, SCIStringArray scistringarray, long l1);

    public static final native boolean SCIStringArray_isEmpty(long l, SCIStringArray scistringarray);

    public static final native void SCIStringArray_remove(long l, SCIStringArray scistringarray, long l1);

    public static final native long SCIStringArray_size(long l, SCIStringArray scistringarray);

    public static final native long SCIStringFromCustomSettingsProperty_getPredefinedValues(long l, SCIStringFromCustomSettingsProperty scistringfromcustomsettingsproperty);

    public static final native String SCIStringFromCustomSettingsProperty_getResourceID(long l, SCIStringFromCustomSettingsProperty scistringfromcustomsettingsproperty);

    public static final native String SCIStringFromCustomSettingsProperty_getValue(long l, SCIStringFromCustomSettingsProperty scistringfromcustomsettingsproperty);

    public static final native long SCIStringFromListSettingsProperty_getPredefinedValues(long l, SCIStringFromListSettingsProperty scistringfromlistsettingsproperty);

    public static final native String SCIStringFromListSettingsProperty_getPrompt(long l, SCIStringFromListSettingsProperty scistringfromlistsettingsproperty);

    public static final native String SCIStringFromListSettingsProperty_getResourceID(long l, SCIStringFromListSettingsProperty scistringfromlistsettingsproperty);

    public static final native long SCIStringFromListSettingsProperty_getValidator(long l, SCIStringFromListSettingsProperty scistringfromlistsettingsproperty);

    public static final native String SCIStringFromListSettingsProperty_getValue(long l, SCIStringFromListSettingsProperty scistringfromlistsettingsproperty);

    public static final native boolean SCIStringFromListSettingsProperty_predefinedValueRequired(long l, SCIStringFromListSettingsProperty scistringfromlistsettingsproperty);

    public static final native int SCIStringInputBase_getMaxNumChars(long l, SCIStringInputBase scistringinputbase);

    public static final native int SCIStringInputBase_getRecommendedInputMethodType(long l, SCIStringInputBase scistringinputbase);

    public static final native String SCIStringInputBase_getString(long l, SCIStringInputBase scistringinputbase);

    public static final native boolean SCIStringInputBase_isLocked(long l, SCIStringInputBase scistringinputbase);

    public static final native void SCIStringInputBase_setString(long l, SCIStringInputBase scistringinputbase, String s);

    public static final native boolean SCIStringInputWithAsyncValidation_isValid__SWIG_0(long l, SCIStringInputWithAsyncValidation scistringinputwithasyncvalidation, long l1, SCIInputValidationCB sciinputvalidationcb);

    public static final native boolean SCIStringInputWithAsyncValidation_isValid__SWIG_1(long l, SCIStringInputWithAsyncValidation scistringinputwithasyncvalidation, String s, long l1, SCIInputValidationCB sciinputvalidationcb);

    public static final native boolean SCIStringInput_isValid__SWIG_0(long l, SCIStringInput scistringinput);

    public static final native boolean SCIStringInput_isValid__SWIG_1(long l, SCIStringInput scistringinput, String s);

    public static final native long SCIStringSettingsProperty_getValidator(long l, SCIStringSettingsProperty scistringsettingsproperty);

    public static final native String SCIStringSettingsProperty_getValue(long l, SCIStringSettingsProperty scistringsettingsproperty);

    public static final native String SCIStringTemplate_buildString(long l, SCIStringTemplate scistringtemplate, long l1, SCIPropertyBag scipropertybag, long l2);

    public static final native int SCISubCalibrator_getPhase(long l, SCISubCalibrator scisubcalibrator);

    public static final native void SCISubCalibrator_play(long l, SCISubCalibrator scisubcalibrator);

    public static final native void SCISubCalibrator_setSubLevelIndex(long l, SCISubCalibrator scisubcalibrator, int i);

    public static final native void SCISubCalibrator_stop(long l, SCISubCalibrator scisubcalibrator);

    public static final native void SCISubCalibrator_subscribe(long l, SCISubCalibrator scisubcalibrator, long l1, SCIEventSink scieventsink);

    public static final native void SCISubCalibrator_unsubscribe(long l, SCISubCalibrator scisubcalibrator, long l1, SCIEventSink scieventsink);

    public static final native long SCISystemTime_clone(long l, SCISystemTime scisystemtime);

    public static final native int SCISystemTime_getDay(long l, SCISystemTime scisystemtime);

    public static final native int SCISystemTime_getDayOfWeek(long l, SCISystemTime scisystemtime);

    public static final native int SCISystemTime_getDaysInMonth(long l, SCISystemTime scisystemtime, int i, int j);

    public static final native int SCISystemTime_getHour(long l, SCISystemTime scisystemtime);

    public static final native int SCISystemTime_getMillisecond(long l, SCISystemTime scisystemtime);

    public static final native int SCISystemTime_getMinute(long l, SCISystemTime scisystemtime);

    public static final native int SCISystemTime_getMonth(long l, SCISystemTime scisystemtime);

    public static final native int SCISystemTime_getSecond(long l, SCISystemTime scisystemtime);

    public static final native int SCISystemTime_getYear(long l, SCISystemTime scisystemtime);

    public static final native boolean SCISystemTime_isValid(long l, SCISystemTime scisystemtime);

    public static final native void SCISystemTime_setDay(long l, SCISystemTime scisystemtime, int i);

    public static final native void SCISystemTime_setDayOfWeek(long l, SCISystemTime scisystemtime, int i);

    public static final native void SCISystemTime_setHour(long l, SCISystemTime scisystemtime, int i);

    public static final native void SCISystemTime_setMillisecond(long l, SCISystemTime scisystemtime, int i);

    public static final native void SCISystemTime_setMinute(long l, SCISystemTime scisystemtime, int i);

    public static final native void SCISystemTime_setMonth(long l, SCISystemTime scisystemtime, int i);

    public static final native void SCISystemTime_setSecond(long l, SCISystemTime scisystemtime, int i);

    public static final native void SCISystemTime_setYear(long l, SCISystemTime scisystemtime, int i);

    public static final native String SCISystem_blockingFetchSonosNetInfo(long l, SCISystem scisystem);

    public static final native void SCISystem_cleanupOnlineUpdateFiles(long l, SCISystem scisystem);

    public static final native long SCISystem_createSetupWizard(long l, SCISystem scisystem);

    public static final native boolean SCISystem_factoryResetConfigFiles(long l, SCISystem scisystem);

    public static final native long SCISystem_getArtworkCacheManager(long l, SCISystem scisystem);

    public static final native String SCISystem_getCopyright(long l, SCISystem scisystem);

    public static final native long SCISystem_getDebugWizardActions(long l, SCISystem scisystem);

    public static final native long SCISystem_getInterface(long l, SCILibrary scilibrary);

    public static final native long SCISystem_getNetstartListener(long l, SCISystem scisystem);

    public static final native boolean SCISystem_isFactoryReset(long l, SCISystem scisystem);

    public static final native boolean SCISystem_isRunningBackgroundOperations(long l, SCISystem scisystem);

    public static final native boolean SCISystem_isSonosNetAllowed(long l, SCISystem scisystem);

    public static final native boolean SCISystem_needToResumeOnlineUpdate(long l, SCISystem scisystem);

    public static final native void SCISystem_resetSonosNetPassword(long l, SCISystem scisystem);

    public static final native void SCISystem_setNetstartListener(long l, SCISystem scisystem, long l1, SCINetstartListener scinetstartlistener);

    public static final native void SCISystem_setSonosNetAllowed(long l, SCISystem scisystem, boolean flag);

    public static final native void SCISystem_subscribe(long l, SCISystem scisystem, long l1, SCIEventSink scieventsink);

    public static final native void SCISystem_unsubscribe(long l, SCISystem scisystem, long l1, SCIEventSink scieventsink);

    public static final native String SCITIMESETTINGSPROPERTY_INTERFACE_get();

    public static final native int SCITIMEZONE_GMT_IX_get();

    public static final native String SCITIMEZONE_INTERFACE_get();

    public static final native String SCITIME_INTERFACE_get();

    public static final native String SCITOOLTIP_INTERFACE_get();

    public static final native String SCITimeSettingsProperty_getFormattedValue(long l, SCITimeSettingsProperty scitimesettingsproperty);

    public static final native long SCITimeSettingsProperty_getValue(long l, SCITimeSettingsProperty scitimesettingsproperty);

    public static final native long SCITimeZone_clone(long l, SCITimeZone scitimezone);

    public static final native boolean SCITimeZone_getAutoAdjustForDST(long l, SCITimeZone scitimezone);

    public static final native long SCITimeZone_getDSTEndTime(long l, SCITimeZone scitimezone);

    public static final native int SCITimeZone_getDSTOffset(long l, SCITimeZone scitimezone);

    public static final native long SCITimeZone_getDSTStartTime(long l, SCITimeZone scitimezone);

    public static final native String SCITimeZone_getDisplayName(long l, SCITimeZone scitimezone);

    public static final native int SCITimeZone_getIndex(long l, SCITimeZone scitimezone);

    public static final native String SCITimeZone_getShortName(long l, SCITimeZone scitimezone);

    public static final native boolean SCITimeZone_getSupportsDST(long l, SCITimeZone scitimezone);

    public static final native int SCITimeZone_getUTCOffset(long l, SCITimeZone scitimezone);

    public static final native void SCITimeZone_setAutoAdjustForDST(long l, SCITimeZone scitimezone, boolean flag);

    public static final native long SCITime_clone(long l, SCITime scitime);

    public static final native boolean SCITime_equalsTo(long l, SCITime scitime, long l1);

    public static final native int SCITime_get12Hour(long l, SCITime scitime);

    public static final native int SCITime_getHour(long l, SCITime scitime);

    public static final native int SCITime_getMinute(long l, SCITime scitime);

    public static final native int SCITime_getSecond(long l, SCITime scitime);

    public static final native boolean SCITime_isLessThan(long l, SCITime scitime, long l1);

    public static final native boolean SCITime_isPm(long l, SCITime scitime);

    public static final native boolean SCITime_isValid(long l, SCITime scitime);

    public static final native void SCITime_setHour(long l, SCITime scitime, int i);

    public static final native void SCITime_setMinute(long l, SCITime scitime, int i);

    public static final native void SCITime_setPm(long l, SCITime scitime, boolean flag);

    public static final native void SCITime_setSecond(long l, SCITime scitime, int i);

    public static final native String SCITooltip_getTooltip(long l, SCITooltip scitooltip);

    public static final native void SCITrackInfoSwigBase_change_ownership(SCITrackInfoSwigBase scitrackinfoswigbase, long l, boolean flag);

    public static final native void SCITrackInfoSwigBase_director_connect(SCITrackInfoSwigBase scitrackinfoswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCITrackInfoSwigBase_dumpSCIObj(long l, SCITrackInfoSwigBase scitrackinfoswigbase);

    public static final native String SCITrackInfoSwigBase_dumpSCIObjSwigExplicitSCITrackInfoSwigBase(long l, SCITrackInfoSwigBase scitrackinfoswigbase);

    public static final native String SCITrackInfo_getAlbum(long l, SCITrackInfo scitrackinfo);

    public static final native String SCITrackInfo_getArtist(long l, SCITrackInfo scitrackinfo);

    public static final native long SCITrackInfo_getDuration(long l, SCITrackInfo scitrackinfo);

    public static final native String SCITrackInfo_getId(long l, SCITrackInfo scitrackinfo);

    public static final native String SCITrackInfo_getResUri(long l, SCITrackInfo scitrackinfo);

    public static final native String SCITrackInfo_getTitle(long l, SCITrackInfo scitrackinfo);

    public static final native String SCIVALIDATESERVICECREDENTIALS_INTERFACE_get();

    public static final native String SCIVERSIONRANGE_INTERFACE_get();

    public static final native String SCIVERSION_INTERFACE_get();

    public static final native boolean SCIVersionRange_containsVersion(long l, SCIVersionRange sciversionrange, long l1, SCIVersion sciversion);

    public static final native long SCIVersionRange_maxVersion(long l, SCIVersionRange sciversionrange);

    public static final native long SCIVersionRange_minVersion(long l, SCIVersionRange sciversionrange);

    public static final native boolean SCIVersionRange_overlapsWith(long l, SCIVersionRange sciversionrange, long l1, SCIVersionRange sciversionrange1);

    public static final native int SCIVersion_compare(long l, SCIVersion sciversion, long l1, SCIVersion sciversion1);

    public static final native int SCIVersion_getBuild(long l, SCIVersion sciversion);

    public static final native int SCIVersion_getMajor(long l, SCIVersion sciversion);

    public static final native int SCIVersion_getMinor(long l, SCIVersion sciversion);

    public static final native boolean SCIVersion_isBeta(long l, SCIVersion sciversion);

    public static final native boolean SCIVersion_isDiag(long l, SCIVersion sciversion);

    public static final native String SCIWIZARD_INTERFACE_get();

    public static final native String SCIWIZARD_ONSTATECHANGED_EVENT_get();

    public static final native String SCIWIZARD_ONSTATETRANSITIONSENABLED_EVENT_get();

    public static final native void SCIWebBridgeDelegateSwigBase_change_ownership(SCIWebBridgeDelegateSwigBase sciwebbridgedelegateswigbase, long l, boolean flag);

    public static final native void SCIWebBridgeDelegateSwigBase_director_connect(SCIWebBridgeDelegateSwigBase sciwebbridgedelegateswigbase, long l, boolean flag, boolean flag1);

    public static final native String SCIWebBridgeDelegateSwigBase_dumpSCIObj(long l, SCIWebBridgeDelegateSwigBase sciwebbridgedelegateswigbase);

    public static final native String SCIWebBridgeDelegateSwigBase_dumpSCIObjSwigExplicitSCIWebBridgeDelegateSwigBase(long l, SCIWebBridgeDelegateSwigBase sciwebbridgedelegateswigbase);

    public static final native void SCIWebBridgeDelegate_bridgeStarted(long l, SCIWebBridgeDelegate sciwebbridgedelegate, String s, boolean flag);

    public static final native void SCIWebBridgeDelegate_doPostRouteMessage(long l, SCIWebBridgeDelegate sciwebbridgedelegate, long l1, SCIWebMessage sciwebmessage, int i);

    public static final native int SCIWebBridgeDelegate_doPreRouteMessage(long l, SCIWebBridgeDelegate sciwebbridgedelegate, long l1, SCIWebMessage sciwebmessage);

    public static final native void SCIWebBridgeDelegate_publishNativeEvent(long l, SCIWebBridgeDelegate sciwebbridgedelegate, long l1, SCIWebMessage sciwebmessage, int i);

    public static final native int SCIWebBridge_COMMAND_get();

    public static final native String SCIWebBridge_getDataForObject(long l, SCIWebBridge sciwebbridge, String s, String s1);

    public static final native String SCIWebBridge_getFailedToConnectErrorMessage(long l, SCIWebBridge sciwebbridge, String s);

    public static final native long SCIWebBridge_getHttpRequestSpec(long l, SCIWebBridge sciwebbridge, String s, long l1, SCIPropertyBag scipropertybag);

    public static final native String SCIWebBridge_getJavaScriptAppContextEvalExpression(long l, SCIWebBridge sciwebbridge);

    public static final native String SCIWebBridge_getRequestFailedErrorMessage(long l, SCIWebBridge sciwebbridge);

    public static final native String SCIWebBridge_getTimeoutErrorMessage(long l, SCIWebBridge sciwebbridge);

    public static final native void SCIWebBridge_init(long l, SCIWebBridge sciwebbridge);

    public static final native int SCIWebBridge_routeMessage(long l, SCIWebBridge sciwebbridge, long l1, SCIWebMessage sciwebmessage, int i);

    public static final native void SCIWebBridge_term(long l, SCIWebBridge sciwebbridge);

    public static final native long SCIWebMessage_getProperties(long l, SCIWebMessage sciwebmessage);

    public static final native String SCIWebMessage_getSubject(long l, SCIWebMessage sciwebmessage);

    public static final native int SCIWebRequestSpec_UNKNOWN_get();

    public static final native int SCIWebRequestSpec_getCachePolicy(long l, SCIWebRequestSpec sciwebrequestspec);

    public static final native int SCIWebRequestSpec_getConnectionTimeOut(long l, SCIWebRequestSpec sciwebrequestspec);

    public static final native String SCIWebRequestSpec_getUrl(long l, SCIWebRequestSpec sciwebrequestspec);

    public static final native void SCIWifiListener_setSSIDResult(long l, SCIWifiListener sciwifilistener, String s, boolean flag);

    public static final native void SCIWifiListener_setSSIDResultExtended(long l, SCIWifiListener sciwifilistener, String s, int i);

    public static final native void SCIWifiSetupDelegate_cancelSSIDJoin(long l, SCIWifiSetupDelegate sciwifisetupdelegate, String s);

    public static final native void SCIWifiSetupDelegate_change_ownership(SCIWifiSetupDelegate sciwifisetupdelegate, long l, boolean flag);

    public static final native void SCIWifiSetupDelegate_clearListener(long l, SCIWifiSetupDelegate sciwifisetupdelegate);

    public static final native void SCIWifiSetupDelegate_director_connect(SCIWifiSetupDelegate sciwifisetupdelegate, long l, boolean flag, boolean flag1);

    public static final native void SCIWifiSetupDelegate_getWifiInfo(long l, SCIWifiSetupDelegate sciwifisetupdelegate, long l1, SCIPropertyBag scipropertybag);

    public static final native boolean SCIWifiSetupDelegate_isWifiConnected(long l, SCIWifiSetupDelegate sciwifisetupdelegate);

    public static final native void SCIWifiSetupDelegate_removeSSID(long l, SCIWifiSetupDelegate sciwifisetupdelegate, String s);

    public static final native void SCIWifiSetupDelegate_setSSID(long l, SCIWifiSetupDelegate sciwifisetupdelegate, String s, String s1, long l1, long l2, SCIWifiListener sciwifilistener);

    public static final native int SCIWizard_STATE_COMPLETE_get();

    public static final native int SCIWizard_STATE_INIT_get();

    public static final native int SCIWizard_STATE_UNKNOWN_get();

    public static final native int SCIWizard_STRID_PRESENTATION_BODY_1_get();

    public static final native int SCIWizard_STRID_PRESENTATION_BODY_2_get();

    public static final native int SCIWizard_STRID_PRESENTATION_BODY_3_get();

    public static final native int SCIWizard_STRID_PRESENTATION_BODY_4_get();

    public static final native int SCIWizard_STRID_PRESENTATION_BODY_5_get();

    public static final native int SCIWizard_STRID_PRESENTATION_BODY_get();

    public static final native int SCIWizard_STRID_PRESENTATION_ERROR_get();

    public static final native int SCIWizard_STRID_PRESENTATION_TITLE_get();

    public static final native int SCIWizard_WIZ_INPUT_1_get();

    public static final native int SCIWizard_WIZ_INPUT_NONE_get();

    public static final native void SCIWizard_abort(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_areInputsValid(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_canRun(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_cancel(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_canceled(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_completed(long l, SCIWizard sciwizard);

    public static final native long SCIWizard_dbgGetAllWizardStateIDs(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_dbgNextState(long l, SCIWizard sciwizard);

    public static final native void SCIWizard_dbgRunInUserFlowMode(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_dbgTransitionToState(long l, SCIWizard sciwizard, int i);

    public static final native boolean SCIWizard_getBoolProp(long l, SCIWizard sciwizard, String s);

    public static final native int SCIWizard_getExitCode(long l, SCIWizard sciwizard);

    public static final native int SCIWizard_getInputIndex(long l, SCIWizard sciwizard, int i);

    public static final native int SCIWizard_getIntProp(long l, SCIWizard sciwizard, String s);

    public static final native long SCIWizard_getNamedParameters(long l, SCIWizard sciwizard);

    public static final native String SCIWizard_getRecommendedLabelForNextState(long l, SCIWizard sciwizard);

    public static final native String SCIWizard_getRecommendedLabelForPreviousState(long l, SCIWizard sciwizard);

    public static final native String SCIWizard_getRecommendedPresentationText(long l, SCIWizard sciwizard, int i);

    public static final native String SCIWizard_getRecommendedTitle(long l, SCIWizard sciwizard);

    public static final native long SCIWizard_getReturnValues(long l, SCIWizard sciwizard);

    public static final native long SCIWizard_getRunningSubWizardPropertyBag(long l, SCIWizard sciwizard);

    public static final native int SCIWizard_getSelection(long l, SCIWizard sciwizard);

    public static final native int SCIWizard_getState(long l, SCIWizard sciwizard);

    public static final native int SCIWizard_getStateTransitionDirection(long l, SCIWizard sciwizard);

    public static final native String SCIWizard_getStrProp(long l, SCIWizard sciwizard, String s);

    public static final native long SCIWizard_getStringInput(long l, SCIWizard sciwizard, int i);

    public static final native long SCIWizard_getWizardComponentsForCurrentState(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_insertSubWizard(long l, SCIWizard sciwizard, int i, long l1, SCICustomSubWizard scicustomsubwizard);

    public static final native boolean SCIWizard_isBusy(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_isCancelEnabled(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_isNested(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_isNextStateEnabled(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_isPresentationRecommended(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_isPreviousStateEnabled(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_isStateDone(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_isStateOK(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_previousStateWillCancel(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_requiresInput(long l, SCIWizard sciwizard);

    public static final native void SCIWizard_reset(long l, SCIWizard sciwizard);

    public static final native void SCIWizard_run(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_running(long l, SCIWizard sciwizard);

    public static final native void SCIWizard_selectInput(long l, SCIWizard sciwizard, int i, int j);

    public static final native void SCIWizard_setBoolProp(long l, SCIWizard sciwizard, String s, boolean flag);

    public static final native void SCIWizard_setCustomSubwizardReturnValue(long l, SCIWizard sciwizard, int i);

    public static final native void SCIWizard_setIntProp(long l, SCIWizard sciwizard, String s, int i);

    public static final native void SCIWizard_setSelection(long l, SCIWizard sciwizard, int i);

    public static final native void SCIWizard_setStrProp(long l, SCIWizard sciwizard, String s, String s1);

    public static final native void SCIWizard_subscribe(long l, SCIWizard sciwizard, long l1, SCIEventSink scieventsink);

    public static final native boolean SCIWizard_transitionToNextState(long l, SCIWizard sciwizard);

    public static final native boolean SCIWizard_transitionToPreviousState(long l, SCIWizard sciwizard);

    public static final native void SCIWizard_unsubscribe(long l, SCIWizard sciwizard, long l1, SCIEventSink scieventsink);

    public static final native String SCIZONEGROUPMGR_INTERFACE_get();

    public static final native String SCIZONEGROUPMGR_LC_LABEL_1_get();

    public static final native String SCIZONEGROUPMGR_LC_LABEL_2_get();

    public static final native String SCIZONEGROUPMGR_LC_PENDING_get();

    public static final native String SCIZONEGROUPMGR_LC_STRING_1_get();

    public static final native String SCIZONEGROUPMGR_LC_STRING_2_get();

    public static final native String SCIZONEGROUPMGR_LC_STRING_3_get();

    public static final native String SCIZONEGROUP_INTERFACE_get();

    public static final native int SCIZoneGroupMgr_ZM_STATE_NORMAL_get();

    public static final native long SCIZoneGroupMgr_createAddAndDropDevicesOp(long l, SCIZoneGroupMgr scizonegroupmgr, String s, long l1, SCIStringArray scistringarray, long l2, SCIStringArray scistringarray1);

    public static final native long SCIZoneGroupMgr_createLimitedConnectivityPropertyBag(long l, SCIZoneGroupMgr scizonegroupmgr);

    public static final native long SCIZoneGroupMgr_getActionForZoneGroup(long l, SCIZoneGroupMgr scizonegroupmgr, String s);

    public static final native boolean SCIZoneGroupMgr_getAllIncompatible(long l, SCIZoneGroupMgr scizonegroupmgr);

    public static final native boolean SCIZoneGroupMgr_getAnyIncompatible(long l, SCIZoneGroupMgr scizonegroupmgr);

    public static final native boolean SCIZoneGroupMgr_getAnyVisibleAndCompatible(long l, SCIZoneGroupMgr scizonegroupmgr);

    public static final native long SCIZoneGroupMgr_getInterface(long l, SCIHousehold scihousehold);

    public static final native long SCIZoneGroupMgr_getLimitedConnectivityActions(long l, SCIZoneGroupMgr scizonegroupmgr);

    public static final native boolean SCIZoneGroupMgr_getOlderDeviceExists(long l, SCIZoneGroupMgr scizonegroupmgr);

    public static final native int SCIZoneGroupMgr_getState(long l, SCIZoneGroupMgr scizonegroupmgr);

    public static final native boolean SCIZoneGroupMgr_hasNoPlayers(long l, SCIZoneGroupMgr scizonegroupmgr);

    public static final native boolean SCIZoneGroupMgr_isFullyCompatible(long l, SCIZoneGroupMgr scizonegroupmgr);

    public static final native boolean SCIZoneGroupMgr_isInCrossVersionMode(long l, SCIZoneGroupMgr scizonegroupmgr);

    public static final native long SCIZoneGroup_createAttachPrivateQueueOp(long l, SCIZoneGroup scizonegroup, String s);

    public static final native long SCIZoneGroup_createNewPrivateQueueOp(long l, SCIZoneGroup scizonegroup, String s, String s1, String s2);

    public static final native long SCIZoneGroup_getDevice(long l, SCIZoneGroup scizonegroup, String s);

    public static final native long SCIZoneGroup_getDevices(long l, SCIZoneGroup scizonegroup);

    public static final native String SCIZoneGroup_getID(long l, SCIZoneGroup scizonegroup);

    public static final native long SCIZoneGroup_getMasterDevice(long l, SCIZoneGroup scizonegroup);

    public static final native boolean SCIZoneGroup_isCompatible(long l, SCIZoneGroup scizonegroup);

    public static final native boolean SCIZoneGroup_isCompatibleAndVisible(long l, SCIZoneGroup scizonegroup);

    public static final native boolean SCIZoneGroup_isUnbondedSUB(long l, SCIZoneGroup scizonegroup);

    public static final native boolean SCIZoneGroup_isUnconfigured(long l, SCIZoneGroup scizonegroup);

    public static final native int SCI_DEVICEVOLUME_OUTPUT_UNKNOWN_get();

    public static final native int SCI_GROUPVOLUMEMODE_DEFAULT_get();

    public static final native int SCI_STR_TMPL_RES_ILLEGAL_TYPE_get();

    public static final native int SCI_STR_TMPL_RES_MISSING_TOKEN_get();

    public static final native int SCI_STR_TMPL_RES_OK_get();

    public static final native int SCI_STR_TMPL_RES_OTHER_get();

    public static final native int SCI_STR_TMPL_RES_UNKOWN_NAMESPACE_get();

    public static final native int SCI_STR_TMPL_RES_UNSUPPORTED_TOKEN_get();

    public static final native String SCI_WEB_BRIDGE_ACTION_OPEN_EXTERNAL_URI_get();

    public static final native String SCI_WEB_BRIDGE_EVENT_SCLIB_READY_get();

    public static final native String SCI_WEB_BRIDGE_EVENT_SERVICEACCOUNTS_CHANGED_get();

    public static final native String SCI_WEB_BRIDGE_EVENT_SERVICEDESCRIPTORS_CHANGED_get();

    public static final native String SCI_WEB_BRIDGE_EVENT_SHOW_DETAIL_PAGE_get();

    public static final native String SCI_WEB_BRIDGE_EVENT_UPDATE_TITLE_get();

    public static final native String SCI_WEB_BRIDGE_EVENT_VIEW_FAIL_get();

    public static final native String SCI_WEB_BRIDGE_EVENT_VIEW_ISREADY_get();

    public static final native String SCI_WIFI_BSSID_get();

    public static final native String SCI_WIFI_OPEN_get();

    public static final native String SCI_WIFI_SSID_get();

    public static final native String SCLIB_BUILDTYPE_STRING_get();

    public static final native void SCLibAssertionFailureCallback_assertionFailed(long l, SCLibAssertionFailureCallback sclibassertionfailurecallback, String s, int i, String s1);

    public static final native void SCLibAssertionFailureCallback_change_ownership(SCLibAssertionFailureCallback sclibassertionfailurecallback, long l, boolean flag);

    public static final native void SCLibAssertionFailureCallback_director_connect(SCLibAssertionFailureCallback sclibassertionfailurecallback, long l, boolean flag, boolean flag1);

    public static final native void SCLibCallUIThreadCallback_callSCLibOnUIThread(long l, SCLibCallUIThreadCallback sclibcalluithreadcallback);

    public static final native void SCLibCallUIThreadCallback_change_ownership(SCLibCallUIThreadCallback sclibcalluithreadcallback, long l, boolean flag);

    public static final native void SCLibCallUIThreadCallback_director_connect(SCLibCallUIThreadCallback sclibcalluithreadcallback, long l, boolean flag, boolean flag1);

    public static final native String SCLibConvertPropertyBagToJson(long l, SCIPropertyBag scipropertybag, boolean flag);

    public static final native void SCLibDiagnosticConsoleLogCallback_change_ownership(SCLibDiagnosticConsoleLogCallback sclibdiagnosticconsolelogcallback, long l, boolean flag);

    public static final native void SCLibDiagnosticConsoleLogCallback_director_connect(SCLibDiagnosticConsoleLogCallback sclibdiagnosticconsolelogcallback, long l, boolean flag, boolean flag1);

    public static final native String SCLibDiagnosticConsoleLogCallback_getDiagnosticConsoleLog(long l, SCLibDiagnosticConsoleLogCallback sclibdiagnosticconsolelogcallback);

    public static final native void SCLibDiagnosticExtraInfoCallback_change_ownership(SCLibDiagnosticExtraInfoCallback sclibdiagnosticextrainfocallback, long l, boolean flag);

    public static final native void SCLibDiagnosticExtraInfoCallback_director_connect(SCLibDiagnosticExtraInfoCallback sclibdiagnosticextrainfocallback, long l, boolean flag, boolean flag1);

    public static final native String SCLibDiagnosticExtraInfoCallback_getDiagnosticExtraInfo(long l, SCLibDiagnosticExtraInfoCallback sclibdiagnosticextrainfocallback);

    public static final native String SCLibGetBrowseCPIDFromSCUri(String s);

    public static final native String SCLibGetCanonicalSearchCategory(String s);

    public static final native String SCLibGetDeviceIDFromDeviceSettingsSCUri(String s);

    public static final native String SCLibGetFixedSCUri(int i);

    public static final native String SCLibGetFixedSCUriTitle(int i);

    public static final native String SCLibGetMobileDeviceTrackIDFromSCUri(String s);

    public static final native String SCLibGetServiceDescriptorIDFromSCUri(String s);

    public static final native String SCLibGetSettingSCUri(int i, String s);

    public static final native String SCLibGetStringFromModelType(int i);

    public static final native long SCLibGetSupportedLanguageIDs();

    public static final native long SCLibInit(long l, SCLibParameters sclibparameters);

    public static final native boolean SCLibIsInfoViewSCUri(String s);

    public static final native boolean SCLibIsMobileDeviceSCUri(String s);

    public static final native boolean SCLibIsSearchSCUri(String s);

    public static final native boolean SCLibIsValidMACAddress(String s);

    public static final native void SCLibLogCallback_LogDebugMessage(long l, SCLibLogCallback scliblogcallback, String s, int i, String s1);

    public static final native void SCLibLogCallback_change_ownership(SCLibLogCallback scliblogcallback, long l, boolean flag);

    public static final native void SCLibLogCallback_director_connect(SCLibLogCallback scliblogcallback, long l, boolean flag, boolean flag1);

    public static final native boolean SCLibMatchesFixedSCUri(String s, int i, boolean flag);

    public static final native boolean SCLibMatchesFixedSCUriCategory(String s, int i);

    public static final native int SCLibParameters_AnacapaLogDefault_get();

    public static final native void SCLibParameters_addDeveloperOption(long l, SCLibParameters sclibparameters, String s);

    public static final native long SCLibParameters_getDiagnosticCommandNames(long l, SCLibParameters sclibparameters);

    public static final native long SCLibParameters_getDiagnosticCommands(long l, SCLibParameters sclibparameters);

    public static final native long SCLibParameters_getDiagnosticFiles(long l, SCLibParameters sclibparameters);

    public static final native boolean SCLibParameters_hasDeveloperOption(long l, SCLibParameters sclibparameters, String s);

    public static final native long SCLibParameters_m_UIParams_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_UIParams_set(long l, SCLibParameters sclibparameters, long l1, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native boolean SCLibParameters_m_bAnacapaUseConfFile_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_bAnacapaUseConfFile_set(long l, SCLibParameters sclibparameters, boolean flag);

    public static final native boolean SCLibParameters_m_bEnableAudibleManager_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_bEnableAudibleManager_set(long l, SCLibParameters sclibparameters, boolean flag);

    public static final native boolean SCLibParameters_m_bEnableSearchManager_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_bEnableSearchManager_set(long l, SCLibParameters sclibparameters, boolean flag);

    public static final native boolean SCLibParameters_m_bIsTestDevice_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_bIsTestDevice_set(long l, SCLibParameters sclibparameters, boolean flag);

    public static final native boolean SCLibParameters_m_bRequiresUnicastAlive_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_bRequiresUnicastAlive_set(long l, SCLibParameters sclibparameters, boolean flag);

    public static final native int SCLibParameters_m_eAnacapaLogEnable_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_eAnacapaLogEnable_set(long l, SCLibParameters sclibparameters, int i);

    public static final native int SCLibParameters_m_nAnacapaMaxConnections_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_nAnacapaMaxConnections_set(long l, SCLibParameters sclibparameters, int i);

    public static final native int SCLibParameters_m_nAnacapaPortSearchAttempts_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_nAnacapaPortSearchAttempts_set(long l, SCLibParameters sclibparameters, int i);

    public static final native int SCLibParameters_m_nBrowseCacheOverrideValue_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_nBrowseCacheOverrideValue_set(long l, SCLibParameters sclibparameters, int i);

    public static final native long SCLibParameters_m_pAssertionFailureCB_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_pAssertionFailureCB_set(long l, SCLibParameters sclibparameters, long l1, SCLibAssertionFailureCallback sclibassertionfailurecallback);

    public static final native long SCLibParameters_m_pCallUIThreadCB_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_pCallUIThreadCB_set(long l, SCLibParameters sclibparameters, long l1, SCLibCallUIThreadCallback sclibcalluithreadcallback);

    public static final native long SCLibParameters_m_pDiagnosticCallback_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_pDiagnosticCallback_set(long l, SCLibParameters sclibparameters, long l1, SCLibDiagnosticExtraInfoCallback sclibdiagnosticextrainfocallback);

    public static final native long SCLibParameters_m_pDiagnosticConsoleLogCallback_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_pDiagnosticConsoleLogCallback_set(long l, SCLibParameters sclibparameters, long l1, SCLibDiagnosticConsoleLogCallback sclibdiagnosticconsolelogcallback);

    public static final native long SCLibParameters_m_pLoggerCB_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_pLoggerCB_set(long l, SCLibParameters sclibparameters, long l1, SCLibLogCallback scliblogcallback);

    public static final native long SCLibParameters_m_pPlatformDateTimeProvider_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_pPlatformDateTimeProvider_set(long l, SCLibParameters sclibparameters, long l1, SCIPlatformDateTimeProvider sciplatformdatetimeprovider);

    public static final native long SCLibParameters_m_pTruncatedStringsCallback_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_pTruncatedStringsCallback_set(long l, SCLibParameters sclibparameters, long l1, SCLibTruncatedStringsCallback sclibtruncatedstringscallback);

    public static final native long SCLibParameters_m_pWifiSetupDelegate_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_pWifiSetupDelegate_set(long l, SCLibParameters sclibparameters, long l1, SCIWifiSetupDelegate sciwifisetupdelegate);

    public static final native String SCLibParameters_m_sAnacapaConfFilePath_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_sAnacapaConfFilePath_set(long l, SCLibParameters sclibparameters, String s);

    public static final native String SCLibParameters_m_sDefaultCountryCode_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_sDefaultCountryCode_set(long l, SCLibParameters sclibparameters, String s);

    public static final native String SCLibParameters_m_sDeveloperOptions_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_sDeveloperOptions_set(long l, SCLibParameters sclibparameters, String s);

    public static final native String SCLibParameters_m_sDownloadedResourcesPath_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_sDownloadedResourcesPath_set(long l, SCLibParameters sclibparameters, String s);

    public static final native String SCLibParameters_m_sHostDeviceID_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_sHostDeviceID_set(long l, SCLibParameters sclibparameters, String s);

    public static final native String SCLibParameters_m_sHostMACAddress_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_sHostMACAddress_set(long l, SCLibParameters sclibparameters, String s);

    public static final native String SCLibParameters_m_sHostModel_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_sHostModel_set(long l, SCLibParameters sclibparameters, String s);

    public static final native String SCLibParameters_m_sJFFSRoot_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_sJFFSRoot_set(long l, SCLibParameters sclibparameters, String s);

    public static final native String SCLibParameters_m_sLanguageID_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_sLanguageID_set(long l, SCLibParameters sclibparameters, String s);

    public static final native String SCLibParameters_m_sOSVersion_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_sOSVersion_set(long l, SCLibParameters sclibparameters, String s);

    public static final native String SCLibParameters_m_sResourcesPath_get(long l, SCLibParameters sclibparameters);

    public static final native void SCLibParameters_m_sResourcesPath_set(long l, SCLibParameters sclibparameters, String s);

    public static final native void SCLibParameters_removeDeveloperOption(long l, SCLibParameters sclibparameters, String s);

    public static final native void SCLibTerm(long l, SCILibrary scilibrary);

    public static final native void SCLibTruncatedStringsCallback_change_ownership(SCLibTruncatedStringsCallback sclibtruncatedstringscallback, long l, boolean flag);

    public static final native void SCLibTruncatedStringsCallback_clearTruncatedStrings(long l, SCLibTruncatedStringsCallback sclibtruncatedstringscallback);

    public static final native void SCLibTruncatedStringsCallback_director_connect(SCLibTruncatedStringsCallback sclibtruncatedstringscallback, long l, boolean flag, boolean flag1);

    public static final native String SCLibTruncatedStringsCallback_getTruncatedStrings(long l, SCLibTruncatedStringsCallback sclibtruncatedstringscallback);

    public static final native int SCOP_INVALID_SERIALNUM_get();

    public static final native String SCRUNWIZARDITEMPROP_WIZARD_DEVICE_UDN_get();

    public static final native String SCRUNWIZARDITEMPROP_WIZARD_URI_get();

    public static final native String SCSHARE_INTERFACE_get();

    public static final native int SCTIMEFORMAT_UNKNOWN_get();

    public static final native int SCUserInterfaceParameters_SCUI_BTS_SHORT_get();

    public static final native int SCUserInterfaceParameters_SCUI_DENSITY_HIGH_get();

    public static final native int SCUserInterfaceParameters_SCUI_FF_UNKNOWN_get();

    public static final native int SCUserInterfaceParameters_SCUI_PHONE_UNKNOWN_get();

    public static final native int SCUserInterfaceParameters_SCUI_USES_BROWSE_FOR_SEARCH_get();

    public static final native void SCUserInterfaceParameters_addScreenResolution(long l, SCUserInterfaceParameters scuserinterfaceparameters, String s, String s1);

    public static final native int SCUserInterfaceParameters_m_browseSearchMode_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_browseSearchMode_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, int i);

    public static final native int SCUserInterfaceParameters_m_browseTextStyle_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_browseTextStyle_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, int i);

    public static final native int SCUserInterfaceParameters_m_densityDpi_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_densityDpi_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, int i);

    public static final native int SCUserInterfaceParameters_m_formFactor_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_formFactor_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, int i);

    public static final native int SCUserInterfaceParameters_m_localSharingSupport_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_localSharingSupport_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, int i);

    public static final native int SCUserInterfaceParameters_m_phoneType_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_phoneType_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, int i);

    public static final native float SCUserInterfaceParameters_m_pixelRatio_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_pixelRatio_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, float f);

    public static final native float SCUserInterfaceParameters_m_scaledScreenDensity_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_scaledScreenDensity_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, float f);

    public static final native int SCUserInterfaceParameters_m_screenDensity_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_screenDensity_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, int i);

    public static final native long SCUserInterfaceParameters_m_screenHeightArray_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_screenHeightArray_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, long l1);

    public static final native int SCUserInterfaceParameters_m_screenHeight_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_screenHeight_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, int i);

    public static final native long SCUserInterfaceParameters_m_screenWidthArray_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_screenWidthArray_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, long l1);

    public static final native int SCUserInterfaceParameters_m_screenWidth_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_screenWidth_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, int i);

    public static final native float SCUserInterfaceParameters_m_xDpi_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_xDpi_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, float f);

    public static final native float SCUserInterfaceParameters_m_yDpi_get(long l, SCUserInterfaceParameters scuserinterfaceparameters);

    public static final native void SCUserInterfaceParameters_m_yDpi_set(long l, SCUserInterfaceParameters scuserinterfaceparameters, float f);

    public static final native String SC_ACTIONID_ACCOUNT_PICKER_get();

    public static final native String SC_ACTIONID_ADD_FAVORITE_get();

    public static final native String SC_ACTIONID_ADD_TO_GENERIC_get();

    public static final native String SC_ACTIONID_ADD_TO_PLAYLIST_get();

    public static final native String SC_ACTIONID_ADD_TO_QUEUE_AT_NUMBER_get();

    public static final native String SC_ACTIONID_BROWSE_IPOD_get();

    public static final native String SC_ACTIONID_CHICKEN_EXIT_get();

    public static final native String SC_ACTIONID_CUSTOMCTL_ABOUTSONOS_get();

    public static final native String SC_ACTIONID_CUSTOMCTL_ADDSHARE_get();

    public static final native String SC_ACTIONID_CUSTOMCTL_ALARMFREQUENCY_get();

    public static final native String SC_ACTIONID_CUSTOMCTL_FORGETHOUSEHOLD_get();

    public static final native String SC_ACTIONID_CUSTOMCTL_MOREINFO_get();

    public static final native String SC_ACTIONID_CUSTOMCTL_MOREMUSIC_get();

    public static final native String SC_ACTIONID_CUSTOMCTL_MUSICEQ_get();

    public static final native String SC_ACTIONID_CUSTOMCTL_RESETCONTROLLER_get();

    public static final native String SC_ACTIONID_CUSTOMCTL_SOCIAL_SHARING_get();

    public static final native String SC_ACTIONID_CUSTOMCTL_SUBEQ_get();

    public static final native String SC_ACTIONID_CUSTOMCTL_SURROUND_get();

    public static final native String SC_ACTIONID_CUSTOMCTL_TVDIALOG_get();

    public static final native String SC_ACTIONID_CUSTOM_UI_ACTION_get();

    public static final native String SC_ACTIONID_DELETE_ALARM_get();

    public static final native String SC_ACTIONID_DELETE_FAVORITE_get();

    public static final native String SC_ACTIONID_DELETE_ITEM_get();

    public static final native String SC_ACTIONID_DELETE_SELECTED_ITEMS_get();

    public static final native String SC_ACTIONID_DISPLAY_CUSTOM_CONTROL_get();

    public static final native String SC_ACTIONID_DISPLAY_TEXT_get();

    public static final native String SC_ACTIONID_DISPLAY_WIZARD_get();

    public static final native String SC_ACTIONID_DOCK_PLAY_NOW_get();

    public static final native String SC_ACTIONID_DONE_get();

    public static final native String SC_ACTIONID_EDIT_get();

    public static final native String SC_ACTIONID_FACTORY_RESET_get();

    public static final native String SC_ACTIONID_FORGET_HHID_get();

    public static final native String SC_ACTIONID_INFOVIEW_WRAPPER_get();

    public static final native String SC_ACTIONID_INVALIDATE_STACK_get();

    public static final native String SC_ACTIONID_MENU_SELECT_SETTING_get();

    public static final native String SC_ACTIONID_MOVE_ITEM_get();

    public static final native String SC_ACTIONID_MOVE_SELECTED_ITEMS_get();

    public static final native String SC_ACTIONID_NAVTO_ALARMS_get();

    public static final native String SC_ACTIONID_NAVTO_MUSICMENU_get();

    public static final native String SC_ACTIONID_NAVTO_NOWPLAYING_get();

    public static final native String SC_ACTIONID_NAVTO_ROOMSMENU_get();

    public static final native String SC_ACTIONID_NAVTO_SEARCH_get();

    public static final native String SC_ACTIONID_NAVTO_SETTINGS_get();

    public static final native String SC_ACTIONID_NEW_ALARM_get();

    public static final native String SC_ACTIONID_NEW_PLAYLIST_get();

    public static final native String SC_ACTIONID_PANDORA_ADD_MUSIC_SEARCH_get();

    public static final native String SC_ACTIONID_PANDORA_ADD_MUSIC_get();

    public static final native String SC_ACTIONID_PANDORA_CREATEPLAY_STATION_get();

    public static final native String SC_ACTIONID_PANDORA_CREATE_STATION_get();

    public static final native String SC_ACTIONID_PANDORA_DELETE_STATION_get();

    public static final native String SC_ACTIONID_PANDORA_NEW_STATION_SEARCH_get();

    public static final native String SC_ACTIONID_PLAYMENU_ADD_TO_QUEUE_get();

    public static final native String SC_ACTIONID_PLAYMENU_PLAY_NEXT_get();

    public static final native String SC_ACTIONID_PLAYMENU_PLAY_NOW_TV_get();

    public static final native String SC_ACTIONID_PLAYMENU_PLAY_NOW_get();

    public static final native String SC_ACTIONID_PLAYMENU_REPLACE_QUEUE_get();

    public static final native String SC_ACTIONID_PLAYMENU_get();

    public static final native String SC_ACTIONID_PLAY_NOW_TV_get();

    public static final native String SC_ACTIONID_PLAY_NOW_get();

    public static final native String SC_ACTIONID_QUEUE_MOVESELITEMS_get();

    public static final native String SC_ACTIONID_QUEUE_PLAYPAUSETOGGLE_get();

    public static final native String SC_ACTIONID_QUEUE_REMOVEITEM_get();

    public static final native String SC_ACTIONID_QUEUE_REMOVESELITEMS_get();

    public static final native String SC_ACTIONID_RADIO_ADD_CUSTOM_STATION_get();

    public static final native String SC_ACTIONID_RADIO_EDIT_CUSTOM_STATION_get();

    public static final native String SC_ACTIONID_RADIO_LOCATION_PUSH_get();

    public static final native String SC_ACTIONID_RADIO_LOCATION_SET_FROM_CITY_get();

    public static final native String SC_ACTIONID_RADIO_LOCATION_SET_FROM_USZIP_get();

    public static final native String SC_ACTIONID_REMOVE_SERVICE_get();

    public static final native String SC_ACTIONID_REMOVE_TRIAL_get();

    public static final native String SC_ACTIONID_RENAME_DEVICE_get();

    public static final native String SC_ACTIONID_RENAME_FAVORITE_get();

    public static final native String SC_ACTIONID_RENAME_ITEM_get();

    public static final native String SC_ACTIONID_RENAME_LINEIN_get();

    public static final native String SC_ACTIONID_RENAME_PANDORA_get();

    public static final native String SC_ACTIONID_RUNWIZ_ADDBOOST_get();

    public static final native String SC_ACTIONID_RUNWIZ_ADDPLAYERORSUB_get();

    public static final native String SC_ACTIONID_RUNWIZ_BETA_get();

    public static final native String SC_ACTIONID_RUNWIZ_MUSICSERVICE_ADD_get();

    public static final native String SC_ACTIONID_RUNWIZ_MUSICSERVICE_CHANGENICKNAME_get();

    public static final native String SC_ACTIONID_RUNWIZ_MUSICSERVICE_CHANGEPASSWORD_get();

    public static final native String SC_ACTIONID_RUNWIZ_MUSICSERVICE_REAUTHORIZE_get();

    public static final native String SC_ACTIONID_RUNWIZ_MUSICSERVICE_REPLACE_get();

    public static final native String SC_ACTIONID_RUNWIZ_MUSICSERVICE_SUBSCRIBE_get();

    public static final native String SC_ACTIONID_RUNWIZ_ONLINEUPDATE_get();

    public static final native String SC_ACTIONID_RUNWIZ_REGISTRATION_get();

    public static final native String SC_ACTIONID_RUNWIZ_SETUP_UNCONFIGURED_get();

    public static final native String SC_ACTIONID_RUNWIZ_SETUP_get();

    public static final native String SC_ACTIONID_RUNWIZ_SHAREUSAGEDATA_get();

    public static final native String SC_ACTIONID_RUNWIZ_SOUNDBAR_get();

    public static final native String SC_ACTIONID_RUNWIZ_WIFICONFIG_get();

    public static final native String SC_ACTIONID_RUN_WIZARD_get();

    public static final native String SC_ACTIONID_SAVE_ALARM_get();

    public static final native String SC_ACTIONID_SELITEMS_ADD_TO_QUEUE_AT_IDX_get();

    public static final native String SC_ACTIONID_SELITEMS_ADD_TO_QUEUE_get();

    public static final native String SC_ACTIONID_SELITEMS_PLAY_NEXT_get();

    public static final native String SC_ACTIONID_SELITEMS_PLAY_NOW_get();

    public static final native String SC_ACTIONID_SELITEMS_REPLACE_QUEUE_get();

    public static final native String SC_ACTIONID_SEPARATE_PAIR_get();

    public static final native String SC_ACTIONID_SET_AUTOPLAY_VOLUME_get();

    public static final native String SC_ACTIONID_SET_DATETIME_get();

    public static final native String SC_ACTIONID_SET_MUSIC_FOR_ALARM_get();

    public static final native String SC_ACTIONID_SHOW_INFOVIEW_get();

    public static final native String SC_ACTIONID_SLIDER_SELECT_SETTING_get();

    public static final native String SC_ACTIONID_SUFFIX_LEARNMORE_get();

    public static final native String SC_ACTIONID_TOGGLE_BOOL_SETTING_get();

    public static final native String SC_ACTIONID_TOGGLE_INTERNET_TIME_get();

    public static final native String SC_ACTIONID_TOGGLE_SCROBBLE_get();

    public static final native String SC_ACTIONID_UPDATE_MUSIC_INDEX_get();

    public static final native String SC_ACTION_CATEGORY_ACCOUNT_get();

    public static final native String SC_ACTION_CATEGORY_COLLECTION_get();

    public static final native String SC_ACTION_CATEGORY_DEFAULT_get();

    public static final native String SC_ACTION_CATEGORY_DISCOVERY_get();

    public static final native String SC_ACTION_CATEGORY_DRAGANDDROP_get();

    public static final native String SC_ACTION_CATEGORY_EDIT_get();

    public static final native String SC_ACTION_CATEGORY_LONGINPUT_get();

    public static final native String SC_ACTION_CATEGORY_PUSH_get();

    public static final native String SC_ACTION_CATEGORY_SETTINGS_get();

    public static final native String SC_ACTION_FILTERNAME_ACCOUNT_get();

    public static final native String SC_ACTION_FILTERNAME_ALL_get();

    public static final native String SC_ACTION_FILTERNAME_CONTEXTMENU_get();

    public static final native String SC_ACTION_FILTERNAME_DEFAULT_get();

    public static final native String SC_ACTION_FILTERNAME_EDIT_get();

    public static final native String SC_ACTION_FILTERNAME_PUSH_get();

    public static final native String SC_ACTION_FILTERNAME_SETTINGS_get();

    public static final native int SC_CONFWIFI_EXITCODE_CREDENTIALS_TIMEDOUT_get();

    public static final native int SC_CONFWIFI_EXITCODE_UNEXPECTED_DISCONNECT_get();

    public static final native int SC_CUSTOMWIFIWIZ_EXITCODE_DONTTRYJOINAGAIN_get();

    public static final native int SC_CUSTOMWIFIWIZ_EXITCODE_REPEAT_BUTTONS_get();

    public static final native int SC_CUSTOM_WIZ_EXITCODE_CANCELED_get();

    public static final native int SC_CUSTOM_WIZ_EXITCODE_DEFAULT_get();

    public static final native String SC_DEVICE_ICONURI_PREFIX_get();

    public static final native String SC_DEVOPT_BROWSE_PICKER_get();

    public static final native String SC_DEVOPT_CR200_MINIMAL_INIT_get();

    public static final native String SC_DEVOPT_CROSS_SERVICE_SEARCH_get();

    public static final native String SC_DEVOPT_FORCE_USAGE_get();

    public static final native String SC_DEVOPT_MORE_MUSIC_WEB_get();

    public static final native String SC_DEVOPT_SHOW_PLAY1_get();

    public static final native String SC_DEVOPT_TRAIN_ALARMS_SLIDER_get();

    public static final native String SC_DEVOPT_TRAIN_ALARMS_get();

    public static final native String SC_DEVOPT_TRAIN_MUSIC_MENU_SEARCH_get();

    public static final native String SC_DEVOPT_TRAIN_TAB_SONOS_MENU_get();

    public static final native String SC_DEVOPT_TRAIN_UX_get();

    public static final native String SC_DEVOPT_UI_AUTOMATION_get();

    public static final native int SC_FIXEDSCURI_Root_get();

    public static final native int SC_ICONID_BEDROOM_get();

    public static final native int SC_ICONID_INVALID_get();

    public static final native int SC_MSSWIZ_EXITCODE_LAUNCH_SHARESWIZARD_get();

    public static final native int SC_NP_AAICON_GENERIC_get();

    public static final native int SC_NP_BASE_NONE_get();

    public static final native int SC_NP_ERR_SKIP_BACK_get();

    public static final native int SC_NP_META_BROWSE_STRING_get();

    public static final native int SC_NP_META_COMPOUND_STRING_4_get();

    public static final native int SC_NP_META_SIMPLE_STRING_1_get();

    public static final native int SC_NP_META_SIMPLE_STRING_2_get();

    public static final native int SC_NP_META_SIMPLE_STRING_3_get();

    public static final native int SC_NP_META_SIMPLE_STRING_4_get();

    public static final native int SC_NP_META_SIMPLE_STRING_5_get();

    public static final native int SC_NP_META_SIMPLE_STRING_6_get();

    public static final native int SC_NP_META_SIMPLE_STRING_7_get();

    public static final native int SC_NP_META_SIMPLE_STRING_8_get();

    public static final native int SC_NP_META_SIMPLE_STRING_9_get();

    public static final native int SC_NP_META_TOOLTIP_1_get();

    public static final native int SC_NP_META_TOOLTIP_2_get();

    public static final native int SC_NP_META_TOOLTIP_3_get();

    public static final native int SC_NP_META_TOOLTIP_4_get();

    public static final native int SC_NP_PLAYBACK_PLAYING_get();

    public static final native int SC_NP_PLAYBACK_UNKNOWN_get();

    public static final native int SC_NP_TYPE_HLS_get();

    public static final native int SC_NP_TYPE_HT_AUDIO_SOURCE_get();

    public static final native int SC_NP_TYPE_INTERNET_RADIO_get();

    public static final native int SC_NP_TYPE_LASTFM_RADIO_get();

    public static final native int SC_NP_TYPE_NONE_get();

    public static final native int SC_NP_TYPE_NO_SOURCE_get();

    public static final native int SC_NP_TYPE_PANDORA_RADIO_get();

    public static final native int SC_NP_TYPE_QUEUE_get();

    public static final native int SC_NP_TYPE_RHAPSODY_RADIO_get();

    public static final native int SC_NP_TYPE_SONOS_ALARM_get();

    public static final native int SC_NP_TYPE_SONOS_DOCK_ZP_get();

    public static final native int SC_NP_TYPE_SONOS_DOCK_get();

    public static final native int SC_NP_TYPE_SONOS_IQRADIO_get();

    public static final native int SC_NP_TYPE_SONOS_LINE_IN_get();

    public static final native int SC_NP_TYPE_SONOS_PROGRADIO_get();

    public static final native int SC_OUWIZ_EXITCODE_CONTROLLER_UPDATE_REQUIRED_get();

    public static final native int SC_OUWIZ_EXITCODE_REGISTRATION_REQUIRED_get();

    public static final native int SC_PROP_TYPE_BOOL_get();

    public static final native int SC_PROP_TYPE_INVALID_get();

    public static final native int SC_QUEUEID_SHARED_get();

    public static final native int SC_RET_OK_get();

    public static final native int SC_ROOMID_BATHROOM_get();

    public static final native int SC_ROOMID_INVALID_get();

    public static final native int SC_SCURICATEGORY_Settings_Device_get();

    public static final native String SC_SEARCHPROP_CPUDN_get();

    public static final native String SC_SEARCHPROP_DEFAULT_CATEGORY_get();

    public static final native String SC_SEARCH_AGGREGATED_CPUDN_get();

    public static final native String SC_SEARCH_CATEGORY_ALBUMS_get();

    public static final native String SC_SEARCH_CATEGORY_ARTISTS_get();

    public static final native String SC_SEARCH_CATEGORY_COMPOSERS_get();

    public static final native String SC_SEARCH_CATEGORY_CUSTOM_PFX_get();

    public static final native String SC_SEARCH_CATEGORY_GENRES_get();

    public static final native String SC_SEARCH_CATEGORY_HOSTS_get();

    public static final native String SC_SEARCH_CATEGORY_PEOPLE_get();

    public static final native String SC_SEARCH_CATEGORY_PLAYLISTS_get();

    public static final native String SC_SEARCH_CATEGORY_PODCASTS_get();

    public static final native String SC_SEARCH_CATEGORY_STATIONS_get();

    public static final native String SC_SEARCH_CATEGORY_TAGS_get();

    public static final native String SC_SEARCH_CATEGORY_TRACKS_get();

    public static final native String SC_SERVICEACCOUNTMANAGER_ONSERVICEACCOUNTSCHANGED_EVENT_get();

    public static final native String SC_SERVICEDESCRIPTORMANAGER_ONPRELOADSERVICEDESCRIPTORSCHANGED_EVENT_get();

    public static final native String SC_SERVICEDESCRIPTORMANAGER_ONSERVICEDESCRIPTORSCHANGED_EVENT_get();

    public static final native int SC_SETUPWIZ_EXITCODE_LAUNCH_MORE_MUSIC_get();

    public static final native int SC_SETUPWIZ_EXITCODE_REGISTRATION_ERROR_get();

    public static final native int SC_SETUPWIZ_EXITCODE_REGISTRATION_SUCCESSFUL_get();

    public static final native int SC_STRID_PRESENTATION_BODY_get();

    public static final native int SC_STRID_PRESENTATION_ERROR_get();

    public static final native int SC_STRID_PRESENTATION_TITLE_get();

    public static final native String SC_SVCACCTPROP_ACCOUNTID_get();

    public static final native String SC_SVCACCTPROP_ACTIONS_LABEL_get();

    public static final native String SC_SVCACCTPROP_ACTIONS_get();

    public static final native String SC_SVCACCTPROP_IS_ANONYMOUS_get();

    public static final native String SC_SVCACCTPROP_IS_CUSTOMSD_get();

    public static final native String SC_SVCACCTPROP_IS_SMAPI_get();

    public static final native String SC_SVCACCTPROP_IS_TRIAL_get();

    public static final native String SC_SVCACCTPROP_NAME_get();

    public static final native String SC_SVCACCTPROP_NICKNAME_get();

    public static final native String SC_SVCACCTPROP_SERVICEID_get();

    public static final native String SC_SVCACCTPROP_SMAPI_SERVICEID_get();

    public static final native String SC_SVCACCTPROP_USERNAME_get();

    public static final native String SC_SVCDESCPROP_ACCOUNTS_get();

    public static final native String SC_SVCDESCPROP_ACTIONS_get();

    public static final native String SC_SVCDESCPROP_DESCRIPTION_get();

    public static final native String SC_SVCDESCPROP_FULLBASETYPE_get();

    public static final native String SC_SVCDESCPROP_IS_CUSTOMSD_get();

    public static final native String SC_SVCDESCPROP_IS_PRELOAD_get();

    public static final native String SC_SVCDESCPROP_IS_SMAPI_get();

    public static final native String SC_SVCDESCPROP_IS_SONOSLAB_get();

    public static final native String SC_SVCDESCPROP_NAME_get();

    public static final native String SC_SVCDESCPROP_SERVICEID_get();

    public static final native String SC_SVCDESCPROP_SMAPI_SERVICEID_get();

    public static final native String SC_SVCDESCPROP_SUPPORTS_MESSAGING_get();

    public static final native int SC_WIZSTATE_COMPLETE_get();

    public static final native int SC_WIZSTATE_INIT_get();

    public static final native int SC_WIZSTATE_SUBWIZ_COMPLETE_get();

    public static final native int SC_WIZSTATE_SUBWIZ_INIT_ENUM_get();

    public static final native int SC_WIZSTATE_SUBWIZ_INIT_get();

    public static final native int SC_WIZSTATE_UNKNOWN_get();

    public static final native int SC_WIZ_EXITCODE_CANCELED_get();

    public static final native int SC_WIZ_EXITCODE_DEFAULT_get();

    public static final native String SETUP_WIZPROP_requestedLocationAccess_get();

    public static final native String SONOS_FEATURES_get();

    public static final native long SWIGSCIActionContextUpcast(long l);

    public static final native long SWIGSCIActionDelegateSwigBaseUpcast(long l);

    public static final native long SWIGSCIActionDelegateUpcast(long l);

    public static final native long SWIGSCIActionDescriptorUpcast(long l);

    public static final native long SWIGSCIActionFactorySwigBaseUpcast(long l);

    public static final native long SWIGSCIActionFactoryUpcast(long l);

    public static final native long SWIGSCIActionFilterSwigBaseUpcast(long l);

    public static final native long SWIGSCIActionFilterUpcast(long l);

    public static final native long SWIGSCIActionFiltererUpcast(long l);

    public static final native long SWIGSCIActionNoArgDescriptorUpcast(long l);

    public static final native long SWIGSCIActionOnAlarmDescriptorUpcast(long l);

    public static final native long SWIGSCIActionOnGroupDescriptorUpcast(long l);

    public static final native long SWIGSCIActionSelectableDescriptorUpcast(long l);

    public static final native long SWIGSCIActionSwigBaseUpcast(long l);

    public static final native long SWIGSCIActionUpcast(long l);

    public static final native long SWIGSCIActionWithBoolDescriptorUpcast(long l);

    public static final native long SWIGSCIActionWithIntDescriptorUpcast(long l);

    public static final native long SWIGSCIActionWithStringDescriptorUpcast(long l);

    public static final native long SWIGSCIAddToQueueAtNumberDescriptorUpcast(long l);

    public static final native long SWIGSCIAggregateBrowseDataSourceUpcast(long l);

    public static final native long SWIGSCIAlarmManagerUpcast(long l);

    public static final native long SWIGSCIAlarmMusicBrowseItemUpcast(long l);

    public static final native long SWIGSCIAlarmMusicUpcast(long l);

    public static final native long SWIGSCIAlarmUpcast(long l);

    public static final native long SWIGSCIAppReportingUpcast(long l);

    public static final native long SWIGSCIArtworkCacheManagerUpcast(long l);

    public static final native long SWIGSCIArtworkCacheUpcast(long l);

    public static final native long SWIGSCIArtworkDataUpcast(long l);

    public static final native long SWIGSCIAudibleManagerUpcast(long l);

    public static final native long SWIGSCIAudioDataUpcast(long l);

    public static final native long SWIGSCIAudioInputResourceUpcast(long l);

    public static final native long SWIGSCIBooleanSettingsPropertyUpcast(long l);

    public static final native long SWIGSCIBrowseDataSourceUpcast(long l);

    public static final native long SWIGSCIBrowseGroupsInfoUpcast(long l);

    public static final native long SWIGSCIBrowseItemSwigBaseUpcast(long l);

    public static final native long SWIGSCIBrowseItemUpcast(long l);

    public static final native long SWIGSCIBrowseListPresentationMapUpcast(long l);

    public static final native long SWIGSCIBrowseManagerUpcast(long l);

    public static final native long SWIGSCIBrowseServiceUpcast(long l);

    public static final native long SWIGSCIBrowseStackManagerUpcast(long l);

    public static final native long SWIGSCICommittableUpcast(long l);

    public static final native long SWIGSCIConfigureWirelessWizardUpcast(long l);

    public static final native long SWIGSCICountryUpcast(long l);

    public static final native long SWIGSCICustomSubWizardSwigBaseUpcast(long l);

    public static final native long SWIGSCICustomSubWizardUpcast(long l);

    public static final native long SWIGSCIDataUpcast(long l);

    public static final native long SWIGSCIDateTimeManagerUpcast(long l);

    public static final native long SWIGSCIDateTimeSettingsPropertyUpcast(long l);

    public static final native long SWIGSCIDealerModeUpcast(long l);

    public static final native long SWIGSCIDebugUpcast(long l);

    public static final native long SWIGSCIDeviceAutoplayUpcast(long l);

    public static final native long SWIGSCIDeviceLineInUpcast(long l);

    public static final native long SWIGSCIDeviceLineOutUpcast(long l);

    public static final native long SWIGSCIDeviceMusicEqualizationUpcast(long l);

    public static final native long SWIGSCIDeviceSettingsDataSourceUpcast(long l);

    public static final native long SWIGSCIDeviceUpcast(long l);

    public static final native long SWIGSCIDeviceVolumeUpcast(long l);

    public static final native long SWIGSCIEnumerableUpcast(long l);

    public static final native long SWIGSCIEnumeratorUpcast(long l);

    public static final native long SWIGSCIEventSinkSwigBaseUpcast(long l);

    public static final native long SWIGSCIEventSinkUpcast(long l);

    public static final native long SWIGSCIGetAboutSonosStringCBSwigBaseUpcast(long l);

    public static final native long SWIGSCIGetAboutSonosStringCBUpcast(long l);

    public static final native long SWIGSCIGetSonosPlaylistsCBSwigBaseUpcast(long l);

    public static final native long SWIGSCIGetSonosPlaylistsCBUpcast(long l);

    public static final native long SWIGSCIGroupVolumeUpcast(long l);

    public static final native long SWIGSCIHouseholdUpcast(long l);

    public static final native long SWIGSCIIndexManagerUpcast(long l);

    public static final native long SWIGSCIInfoViewHeaderDataSourceUpcast(long l);

    public static final native long SWIGSCIInfoViewHeaderItemUpcast(long l);

    public static final native long SWIGSCIInfoViewTextPaneMetadataUpcast(long l);

    public static final native long SWIGSCIInputUpcast(long l);

    public static final native long SWIGSCIInputValidationCBUpcast(long l);

    public static final native long SWIGSCIIntArrayUpcast(long l);

    public static final native long SWIGSCIIntegerSettingsPropertyUpcast(long l);

    public static final native long SWIGSCILatentLoadingDataSourceUpcast(long l);

    public static final native long SWIGSCILibraryTestsUpcast(long l);

    public static final native long SWIGSCILibraryUpcast(long l);

    public static final native long SWIGSCILinkSettingsPropertyUpcast(long l);

    public static final native long SWIGSCILocalMediaCollectionListenerUpcast(long l);

    public static final native long SWIGSCILocalMediaCollectionSwigBaseUpcast(long l);

    public static final native long SWIGSCILocalMediaCollectionUpcast(long l);

    public static final native long SWIGSCILocalMusicBrowseItemInfoSwigBaseUpcast(long l);

    public static final native long SWIGSCILocalMusicBrowseItemInfoUpcast(long l);

    public static final native long SWIGSCILocalMusicSearchableDelegateSwigBaseUpcast(long l);

    public static final native long SWIGSCILocalMusicSearchableDelegateUpcast(long l);

    public static final native long SWIGSCILocationServicesSwigBaseUpcast(long l);

    public static final native long SWIGSCILocationServicesUpcast(long l);

    public static final native long SWIGSCILogoArtworkCacheUpcast(long l);

    public static final native long SWIGSCIMusicServerBrowseDelegateSwigBaseUpcast(long l);

    public static final native long SWIGSCIMusicServerBrowseDelegateUpcast(long l);

    public static final native long SWIGSCIMusicServerDelegateSwigBaseUpcast(long l);

    public static final native long SWIGSCIMusicServerDelegateUpcast(long l);

    public static final native long SWIGSCIMusicServerUpcast(long l);

    public static final native long SWIGSCIMusicServiceWizardUpcast(long l);

    public static final native long SWIGSCINetstartListenerSwigBaseUpcast(long l);

    public static final native long SWIGSCINetstartListenerUpcast(long l);

    public static final native long SWIGSCINetstartScanListEntryUpcast(long l);

    public static final native long SWIGSCINetworkManagementDelegateSwigBaseUpcast(long l);

    public static final native long SWIGSCINetworkManagementDelegateUpcast(long l);

    public static final native long SWIGSCINetworkManagementUpcast(long l);

    public static final native long SWIGSCINowPlayingRatingsUpcast(long l);

    public static final native long SWIGSCINowPlayingSleepTimerUpcast(long l);

    public static final native long SWIGSCINowPlayingSourceUpcast(long l);

    public static final native long SWIGSCINowPlayingTransportUpcast(long l);

    public static final native long SWIGSCINowPlayingUpcast(long l);

    public static final native long SWIGSCIOnlineUpdateWizardUpcast(long l);

    public static final native long SWIGSCIOpAVTransportGetPositionInfoUpcast(long l);

    public static final native long SWIGSCIOpAVTransportGetRemainingSleepTimerDurationUpcast(long l);

    public static final native long SWIGSCIOpAddServiceAccountUpcast(long l);

    public static final native long SWIGSCIOpAddTracksToQueueUpcast(long l);

    public static final native long SWIGSCIOpAlarmSaveUpcast(long l);

    public static final native long SWIGSCIOpAttachPrivateQueueUpcast(long l);

    public static final native long SWIGSCIOpAudioInGetAudioInputAttributesUpcast(long l);

    public static final native long SWIGSCIOpAudioInGetLineInLevelUpcast(long l);

    public static final native long SWIGSCIOpCBSwigBaseUpcast(long l);

    public static final native long SWIGSCIOpCBUpcast(long l);

    public static final native long SWIGSCIOpConnectionManagerGetProtocolInfoUpcast(long l);

    public static final native long SWIGSCIOpDevicePropertiesGetAutoplayLinkedZonesUpcast(long l);

    public static final native long SWIGSCIOpDevicePropertiesGetAutoplayRoomUUIDUpcast(long l);

    public static final native long SWIGSCIOpDevicePropertiesGetAutoplayVolumeUpcast(long l);

    public static final native long SWIGSCIOpDevicePropertiesGetLEDStateUpcast(long l);

    public static final native long SWIGSCIOpDevicePropertiesGetUseAutoplayVolumeUpcast(long l);

    public static final native long SWIGSCIOpFactoryUpcast(long l);

    public static final native long SWIGSCIOpGenericUpdateQueueUpcast(long l);

    public static final native long SWIGSCIOpGetAboutSonosStringUpcast(long l);

    public static final native long SWIGSCIOpGetTrackPositionInfoUpcast(long l);

    public static final native long SWIGSCIOpGetUsageDataShareOptionUpcast(long l);

    public static final native long SWIGSCIOpLoadAlbumArtUpcast(long l);

    public static final native long SWIGSCIOpLoadLogoUpcast(long l);

    public static final native long SWIGSCIOpNetstartGetScanListUpcast(long l);

    public static final native long SWIGSCIOpNewPrivateQueueUpcast(long l);

    public static final native long SWIGSCIOpQueueAttachQueueUpcast(long l);

    public static final native long SWIGSCIOpQueueReplaceAllTracksUpcast(long l);

    public static final native long SWIGSCIOpRegEmailOptInUpcast(long l);

    public static final native long SWIGSCIOpRenderingControlGetOutputFixedUpcast(long l);

    public static final native long SWIGSCIOpRenderingControlGetSupportsOutputFixedUpcast(long l);

    public static final native long SWIGSCIOpReplaceAccountUpcast(long l);

    public static final native long SWIGSCIOpSubmitDiagnosticsUpcast(long l);

    public static final native long SWIGSCIOpSystemPropertyGetStringUpcast(long l);

    public static final native long SWIGSCIOpUpcast(long l);

    public static final native long SWIGSCIOpValidateServiceCredentialsUpcast(long l);

    public static final native long SWIGSCIOpZoneGroupTopologyGetZoneGroupStateUpcast(long l);

    public static final native long SWIGSCIOperationProgressUpcast(long l);

    public static final native long SWIGSCIPandoraResultsUpcast(long l);

    public static final native long SWIGSCIPlayQueueItemStateUpcast(long l);

    public static final native long SWIGSCIPlayQueueMgrUpcast(long l);

    public static final native long SWIGSCIPlayQueueUpcast(long l);

    public static final native long SWIGSCIPowerscrollDataSourceUpcast(long l);

    public static final native long SWIGSCIPropertyBagUpcast(long l);

    public static final native long SWIGSCIPropertyUpcast(long l);

    public static final native long SWIGSCIRecurrenceUpcast(long l);

    public static final native long SWIGSCIResourceUpcast(long l);

    public static final native long SWIGSCIRoomResourceUpcast(long l);

    public static final native long SWIGSCIScrobblingServiceUpcast(long l);

    public static final native long SWIGSCISearchableCategoryUpcast(long l);

    public static final native long SWIGSCISearchableUpcast(long l);

    public static final native long SWIGSCISelectableItemUpcast(long l);

    public static final native long SWIGSCISelectionManagerUpcast(long l);

    public static final native long SWIGSCIServiceAccountFilterUpcast(long l);

    public static final native long SWIGSCIServiceAccountManagerUpcast(long l);

    public static final native long SWIGSCIServiceAccountUpcast(long l);

    public static final native long SWIGSCIServiceDescriptorFilterUpcast(long l);

    public static final native long SWIGSCIServiceDescriptorManagerUpcast(long l);

    public static final native long SWIGSCIServiceDescriptorUpcast(long l);

    public static final native long SWIGSCISettingsBrowseItemUpcast(long l);

    public static final native long SWIGSCISettingsPropertyUpcast(long l);

    public static final native long SWIGSCISetupWizardUpcast(long l);

    public static final native long SWIGSCIShareManagerUpcast(long l);

    public static final native long SWIGSCIShareUpcast(long l);

    public static final native long SWIGSCISimpleMessagingServiceUpcast(long l);

    public static final native long SWIGSCISonosNetSetupWizardUpcast(long l);

    public static final native long SWIGSCISonosPlaylistUpcast(long l);

    public static final native long SWIGSCISoundbarWizardUpcast(long l);

    public static final native long SWIGSCISpinnerSettingsPropertyUpcast(long l);

    public static final native long SWIGSCIStackTraceCaptureDelegateSwigBaseUpcast(long l);

    public static final native long SWIGSCIStackTraceCaptureDelegateUpcast(long l);

    public static final native long SWIGSCIStringArrayUpcast(long l);

    public static final native long SWIGSCIStringFromCustomSettingsPropertyUpcast(long l);

    public static final native long SWIGSCIStringFromListSettingsPropertyUpcast(long l);

    public static final native long SWIGSCIStringInputBaseUpcast(long l);

    public static final native long SWIGSCIStringInputUpcast(long l);

    public static final native long SWIGSCIStringInputWithAsyncValidationUpcast(long l);

    public static final native long SWIGSCIStringSettingsPropertyUpcast(long l);

    public static final native long SWIGSCIStringTemplateUpcast(long l);

    public static final native long SWIGSCISubCalibratorUpcast(long l);

    public static final native long SWIGSCISystemTimeUpcast(long l);

    public static final native long SWIGSCISystemUpcast(long l);

    public static final native long SWIGSCITimeSettingsPropertyUpcast(long l);

    public static final native long SWIGSCITimeUpcast(long l);

    public static final native long SWIGSCITimeZoneUpcast(long l);

    public static final native long SWIGSCITooltipUpcast(long l);

    public static final native long SWIGSCITrackInfoSwigBaseUpcast(long l);

    public static final native long SWIGSCITrackInfoUpcast(long l);

    public static final native long SWIGSCIVersionRangeUpcast(long l);

    public static final native long SWIGSCIVersionUpcast(long l);

    public static final native long SWIGSCIWebBridgeDelegateSwigBaseUpcast(long l);

    public static final native long SWIGSCIWebBridgeDelegateUpcast(long l);

    public static final native long SWIGSCIWebBridgeUpcast(long l);

    public static final native long SWIGSCIWebMessageUpcast(long l);

    public static final native long SWIGSCIWebRequestSpecUpcast(long l);

    public static final native long SWIGSCIWifiListenerUpcast(long l);

    public static final native long SWIGSCIWizardUpcast(long l);

    public static final native long SWIGSCIZoneGroupMgrUpcast(long l);

    public static final native long SWIGSCIZoneGroupUpcast(long l);

    public static void SwigDirector_SCIActionDelegateSwigBase_asyncActionHasCompleted(SCIActionDelegateSwigBase sciactiondelegateswigbase, long l, long l1)
    {
        SCIActionContext sciactioncontext = null;
        SCIAction sciaction;
        if(l == 0L)
            sciaction = null;
        else
            sciaction = new SCIAction(l, true);
        if(l1 != 0L)
            sciactioncontext = new SCIActionContext(l1, true);
        sciactiondelegateswigbase.asyncActionHasCompleted(sciaction, sciactioncontext);
    }

    public static String SwigDirector_SCIActionDelegateSwigBase_dumpSCIObj(SCIActionDelegateSwigBase sciactiondelegateswigbase)
    {
        return sciactiondelegateswigbase.dumpSCIObj();
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createBrowsePickerAction(SCIActionFactorySwigBase sciactionfactoryswigbase, long l, String s)
    {
        SCIBrowseDataSource scibrowsedatasource;
        if(l == 0L)
            scibrowsedatasource = null;
        else
            scibrowsedatasource = new SCIBrowseDataSource(l, true);
        return SCIAction.getCPtr(sciactionfactoryswigbase.createBrowsePickerAction(scibrowsedatasource, s));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createCustomUIAction(SCIActionFactorySwigBase sciactionfactoryswigbase, String s, String s1)
    {
        return SCIAction.getCPtr(sciactionfactoryswigbase.createCustomUIAction(s, s1));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createDisplayBrowseStackAction(SCIActionFactorySwigBase sciactionfactoryswigbase, long l)
    {
        SCIBrowseStackManager scibrowsestackmanager;
        if(l == 0L)
            scibrowsestackmanager = null;
        else
            scibrowsestackmanager = new SCIBrowseStackManager(l, true);
        return SCIAction.getCPtr(sciactionfactoryswigbase.createDisplayBrowseStackAction(scibrowsestackmanager));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createDisplayCustomControlAction(SCIActionFactorySwigBase sciactionfactoryswigbase, int i)
    {
        return SCIAction.getCPtr(sciactionfactoryswigbase.createDisplayCustomControlAction(SCDisplayCustomControlActionType.swigToEnum(i)));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createDisplayDatePickerAction(SCIActionFactorySwigBase sciactionfactoryswigbase, String s, String s1, long l)
    {
        SCISystemTime scisystemtime;
        if(l == 0L)
            scisystemtime = null;
        else
            scisystemtime = new SCISystemTime(l, true);
        return SCIAction.getCPtr(sciactionfactoryswigbase.createDisplayDatePickerAction(s, s1, scisystemtime));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createDisplayDualTextInputAction(SCIActionFactorySwigBase sciactionfactoryswigbase, String s, String s1, String s2, String s3, long l, String s4, 
            String s5, long l1)
    {
        SCIStringInput scistringinput;
        SCIStringInput scistringinput1;
        if(l == 0L)
            scistringinput = null;
        else
            scistringinput = new SCIStringInput(l, true);
        if(l1 == 0L)
            scistringinput1 = null;
        else
            scistringinput1 = new SCIStringInput(l1, true);
        return SCIAction.getCPtr(sciactionfactoryswigbase.createDisplayDualTextInputAction(s, s1, s2, s3, scistringinput, s4, s5, scistringinput1));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createDisplayInfoViewAction(SCIActionFactorySwigBase sciactionfactoryswigbase, String s, String s1)
    {
        return SCIAction.getCPtr(sciactionfactoryswigbase.createDisplayInfoViewAction(s, s1));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createDisplayIntegerInputAction(SCIActionFactorySwigBase sciactionfactoryswigbase, String s, String s1, String s2, int i, int j, int k)
    {
        return SCIAction.getCPtr(sciactionfactoryswigbase.createDisplayIntegerInputAction(s, s1, s2, i, j, k));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createDisplayMenuAction(SCIActionFactorySwigBase sciactionfactoryswigbase, String s, String s1, String s2, long l, int i)
    {
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return SCIAction.getCPtr(sciactionfactoryswigbase.createDisplayMenuAction(s, s1, s2, scistringarray, i));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createDisplayMenuAndTextInputAction(SCIActionFactorySwigBase sciactionfactoryswigbase, String s, String s1, String s2, boolean flag, long l, int i, 
            long l1, int j, String s3)
    {
        SCIEnumerator scienumerator;
        SCIResource sciresource;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        if(l1 == 0L)
            sciresource = null;
        else
            sciresource = new SCIResource(l1, true);
        return SCIAction.getCPtr(sciactionfactoryswigbase.createDisplayMenuAndTextInputAction(s, s1, s2, flag, scienumerator, i, sciresource, j, s3));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createDisplayMenuPopupAction(SCIActionFactorySwigBase sciactionfactoryswigbase, String s, long l, int i, String s1, boolean flag, boolean flag1)
    {
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return SCIAction.getCPtr(sciactionfactoryswigbase.createDisplayMenuPopupAction(s, scistringarray, i, s1, flag, flag1));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createDisplayMessagePopupAction(SCIActionFactorySwigBase sciactionfactoryswigbase, String s, int i)
    {
        return SCIAction.getCPtr(sciactionfactoryswigbase.createDisplayMessagePopupAction(s, i));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createDisplayTextInputAction(SCIActionFactorySwigBase sciactionfactoryswigbase, String s, String s1, String s2, long l)
    {
        SCIStringInput scistringinput;
        if(l == 0L)
            scistringinput = null;
        else
            scistringinput = new SCIStringInput(l, true);
        return SCIAction.getCPtr(sciactionfactoryswigbase.createDisplayTextInputAction(s, s1, s2, scistringinput));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createDisplayTextPaneAction(SCIActionFactorySwigBase sciactionfactoryswigbase, String s, String s1, long l)
    {
        SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata;
        if(l == 0L)
            sciinfoviewtextpanemetadata = null;
        else
            sciinfoviewtextpanemetadata = new SCIInfoViewTextPaneMetadata(l, true);
        return SCIAction.getCPtr(sciactionfactoryswigbase.createDisplayTextPaneAction(s, s1, sciinfoviewtextpanemetadata));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createDisplayTimePickerAction(SCIActionFactorySwigBase sciactionfactoryswigbase, String s, String s1, long l)
    {
        SCITime scitime;
        if(l == 0L)
            scitime = null;
        else
            scitime = new SCITime(l, true);
        return SCIAction.getCPtr(sciactionfactoryswigbase.createDisplayTimePickerAction(s, s1, scitime));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createDisplayWizardAction(SCIActionFactorySwigBase sciactionfactoryswigbase, long l)
    {
        SCIWizard sciwizard;
        if(l == 0L)
            sciwizard = null;
        else
            sciwizard = new SCIWizard(l, true);
        return SCIAction.getCPtr(sciactionfactoryswigbase.createDisplayWizardAction(sciwizard));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createNavigationAction(SCIActionFactorySwigBase sciactionfactoryswigbase, int i, long l)
    {
        SCNavigationActionType scnavigationactiontype = SCNavigationActionType.swigToEnum(i);
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        return SCIAction.getCPtr(sciactionfactoryswigbase.createNavigationAction(scnavigationactiontype, scipropertybag));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createPopBrowseAction(SCIActionFactorySwigBase sciactionfactoryswigbase, int i)
    {
        return SCIAction.getCPtr(sciactionfactoryswigbase.createPopBrowseAction(i));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createPushSCUriAction(SCIActionFactorySwigBase sciactionfactoryswigbase, String s, String s1, boolean flag)
    {
        return SCIAction.getCPtr(sciactionfactoryswigbase.createPushSCUriAction(s, s1, flag));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createRunAsyncIOOperationAction(SCIActionFactorySwigBase sciactionfactoryswigbase, long l)
    {
        SCIOp sciop;
        if(l == 0L)
            sciop = null;
        else
            sciop = new SCIOp(l, true);
        return SCIAction.getCPtr(sciactionfactoryswigbase.createRunAsyncIOOperationAction(sciop));
    }

    public static long SwigDirector_SCIActionFactorySwigBase_createRunWizardAction(SCIActionFactorySwigBase sciactionfactoryswigbase, int i)
    {
        return SCIAction.getCPtr(sciactionfactoryswigbase.createRunWizardAction(SCRunWizardActionType.swigToEnum(i)));
    }

    public static String SwigDirector_SCIActionFactorySwigBase_dumpSCIObj(SCIActionFactorySwigBase sciactionfactoryswigbase)
    {
        return sciactionfactoryswigbase.dumpSCIObj();
    }

    public static boolean SwigDirector_SCIActionFilterSwigBase_acceptsAction(SCIActionFilterSwigBase sciactionfilterswigbase, long l)
    {
        SCIActionDescriptor sciactiondescriptor;
        if(l == 0L)
            sciactiondescriptor = null;
        else
            sciactiondescriptor = new SCIActionDescriptor(l, true);
        return sciactionfilterswigbase.acceptsAction(sciactiondescriptor);
    }

    public static String SwigDirector_SCIActionFilterSwigBase_dumpSCIObj(SCIActionFilterSwigBase sciactionfilterswigbase)
    {
        return sciactionfilterswigbase.dumpSCIObj();
    }

    public static String SwigDirector_SCIActionSwigBase_dumpSCIObj(SCIActionSwigBase sciactionswigbase)
    {
        return sciactionswigbase.dumpSCIObj();
    }

    public static int SwigDirector_SCIActionSwigBase_perform(SCIActionSwigBase sciactionswigbase, long l)
    {
        SCIActionContext sciactioncontext;
        if(l == 0L)
            sciactioncontext = null;
        else
            sciactioncontext = new SCIActionContext(l, true);
        return sciactionswigbase.perform(sciactioncontext).swigValue();
    }

    public static boolean SwigDirector_SCIBrowseItemSwigBase_canActOn(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return scibrowseitemswigbase.canActOn();
    }

    public static boolean SwigDirector_SCIBrowseItemSwigBase_canPush(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return scibrowseitemswigbase.canPush();
    }

    public static String SwigDirector_SCIBrowseItemSwigBase_dumpSCIObj(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return scibrowseitemswigbase.dumpSCIObj();
    }

    public static long SwigDirector_SCIBrowseItemSwigBase_getActions(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return SCIEnumerator.getCPtr(scibrowseitemswigbase.getActions());
    }

    public static int SwigDirector_SCIBrowseItemSwigBase_getAlbumArtType(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return scibrowseitemswigbase.getAlbumArtType().swigValue();
    }

    public static String SwigDirector_SCIBrowseItemSwigBase_getAlbumArtURL__SWIG_0(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return scibrowseitemswigbase.getAlbumArtURL();
    }

    public static String SwigDirector_SCIBrowseItemSwigBase_getAlbumArtURL__SWIG_1(SCIBrowseItemSwigBase scibrowseitemswigbase, long l)
    {
        return scibrowseitemswigbase.getAlbumArtURL(l);
    }

    public static long SwigDirector_SCIBrowseItemSwigBase_getAttributes(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return SCIPropertyBag.getCPtr(scibrowseitemswigbase.getAttributes());
    }

    public static String SwigDirector_SCIBrowseItemSwigBase_getBrowseItemText(SCIBrowseItemSwigBase scibrowseitemswigbase, int i)
    {
        return scibrowseitemswigbase.getBrowseItemText(SCIBrowseItem.SCBrowseItemText.swigToEnum(i));
    }

    public static long SwigDirector_SCIBrowseItemSwigBase_getFilteredActions(SCIBrowseItemSwigBase scibrowseitemswigbase, long l)
    {
        SCIActionFilter sciactionfilter;
        if(l == 0L)
            sciactionfilter = null;
        else
            sciactionfilter = new SCIActionFilter(l, true);
        return SCIEnumerator.getCPtr(scibrowseitemswigbase.getFilteredActions(sciactionfilter));
    }

    public static long SwigDirector_SCIBrowseItemSwigBase_getMoreMenuDataSource(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return SCIBrowseDataSource.getCPtr(scibrowseitemswigbase.getMoreMenuDataSource());
    }

    public static String SwigDirector_SCIBrowseItemSwigBase_getOrdinal(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return scibrowseitemswigbase.getOrdinal();
    }

    public static String SwigDirector_SCIBrowseItemSwigBase_getPrimaryAdornedTitle(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return scibrowseitemswigbase.getPrimaryAdornedTitle();
    }

    public static String SwigDirector_SCIBrowseItemSwigBase_getPrimaryTitle(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return scibrowseitemswigbase.getPrimaryTitle();
    }

    public static String SwigDirector_SCIBrowseItemSwigBase_getSCUri(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return scibrowseitemswigbase.getSCUri();
    }

    public static String SwigDirector_SCIBrowseItemSwigBase_getSecondaryTitle(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return scibrowseitemswigbase.getSecondaryTitle();
    }

    public static boolean SwigDirector_SCIBrowseItemSwigBase_hasOrdinal(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return scibrowseitemswigbase.hasOrdinal();
    }

    public static boolean SwigDirector_SCIBrowseItemSwigBase_isBrowseItemTextAvailable(SCIBrowseItemSwigBase scibrowseitemswigbase, int i)
    {
        return scibrowseitemswigbase.isBrowseItemTextAvailable(SCIBrowseItem.SCBrowseItemText.swigToEnum(i));
    }

    public static boolean SwigDirector_SCIBrowseItemSwigBase_isDataAvailable(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return scibrowseitemswigbase.isDataAvailable();
    }

    public static boolean SwigDirector_SCIBrowseItemSwigBase_isParentOfSearch(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return scibrowseitemswigbase.isParentOfSearch();
    }

    public static boolean SwigDirector_SCIBrowseItemSwigBase_isSecondaryTitleValid(SCIBrowseItemSwigBase scibrowseitemswigbase)
    {
        return scibrowseitemswigbase.isSecondaryTitleValid();
    }

    public static void SwigDirector_SCIBrowseItemSwigBase_subscribe(SCIBrowseItemSwigBase scibrowseitemswigbase, long l, boolean flag)
    {
        SCIEventSink scieventsink;
        if(l == 0L)
            scieventsink = null;
        else
            scieventsink = new SCIEventSink(l, true);
        scibrowseitemswigbase.subscribe(scieventsink, flag);
    }

    public static void SwigDirector_SCIBrowseItemSwigBase_unsubscribe(SCIBrowseItemSwigBase scibrowseitemswigbase, long l)
    {
        SCIEventSink scieventsink;
        if(l == 0L)
            scieventsink = null;
        else
            scieventsink = new SCIEventSink(l, true);
        scibrowseitemswigbase.unsubscribe(scieventsink);
    }

    public static boolean SwigDirector_SCICustomSubWizardSwigBase_canClientCancelWizard(SCICustomSubWizardSwigBase scicustomsubwizardswigbase)
    {
        return scicustomsubwizardswigbase.canClientCancelWizard();
    }

    public static boolean SwigDirector_SCICustomSubWizardSwigBase_canClientTransitionToNextState(SCICustomSubWizardSwigBase scicustomsubwizardswigbase)
    {
        return scicustomsubwizardswigbase.canClientTransitionToNextState();
    }

    public static boolean SwigDirector_SCICustomSubWizardSwigBase_canClientTransitionToPreviousState(SCICustomSubWizardSwigBase scicustomsubwizardswigbase)
    {
        return scicustomsubwizardswigbase.canClientTransitionToPreviousState();
    }

    public static String SwigDirector_SCICustomSubWizardSwigBase_dumpSCIObj(SCICustomSubWizardSwigBase scicustomsubwizardswigbase)
    {
        return scicustomsubwizardswigbase.dumpSCIObj();
    }

    public static int SwigDirector_SCICustomSubWizardSwigBase_enter(SCICustomSubWizardSwigBase scicustomsubwizardswigbase, long l)
    {
        SCIWizard sciwizard;
        if(l == 0L)
            sciwizard = null;
        else
            sciwizard = new SCIWizard(l, true);
        return scicustomsubwizardswigbase.enter(sciwizard);
    }

    public static void SwigDirector_SCICustomSubWizardSwigBase_exit(SCICustomSubWizardSwigBase scicustomsubwizardswigbase)
    {
        scicustomsubwizardswigbase.exit();
    }

    public static int SwigDirector_SCICustomSubWizardSwigBase_getNextStateID(SCICustomSubWizardSwigBase scicustomsubwizardswigbase)
    {
        return scicustomsubwizardswigbase.getNextStateID();
    }

    public static long SwigDirector_SCICustomSubWizardSwigBase_getPropertyBag(SCICustomSubWizardSwigBase scicustomsubwizardswigbase)
    {
        return SCIPropertyBag.getCPtr(scicustomsubwizardswigbase.getPropertyBag());
    }

    public static String SwigDirector_SCICustomSubWizardSwigBase_getRecommendedLabelForNextState(SCICustomSubWizardSwigBase scicustomsubwizardswigbase)
    {
        return scicustomsubwizardswigbase.getRecommendedLabelForNextState();
    }

    public static void SwigDirector_SCICustomSubWizardSwigBase_onSubWizardStateTransition(SCICustomSubWizardSwigBase scicustomsubwizardswigbase, int i)
    {
        scicustomsubwizardswigbase.onSubWizardStateTransition(i);
    }

    public static void SwigDirector_SCIEventSinkSwigBase_dispatchEvent(SCIEventSinkSwigBase scieventsinkswigbase, SCIObj sciobj, String s)
    {
        scieventsinkswigbase.dispatchEvent(sciobj, s);
    }

    public static String SwigDirector_SCIEventSinkSwigBase_dumpSCIObj(SCIEventSinkSwigBase scieventsinkswigbase)
    {
        return scieventsinkswigbase.dumpSCIObj();
    }

    public static boolean SwigDirector_SCIEventSinkSwigBase_listenToEventType(SCIEventSinkSwigBase scieventsinkswigbase, String s)
    {
        return scieventsinkswigbase.listenToEventType(s);
    }

    public static String SwigDirector_SCIGetAboutSonosStringCBSwigBase_dumpSCIObj(SCIGetAboutSonosStringCBSwigBase scigetaboutsonosstringcbswigbase)
    {
        return scigetaboutsonosstringcbswigbase.dumpSCIObj();
    }

    public static void SwigDirector_SCIGetAboutSonosStringCBSwigBase_updateGetAboutSonosString(SCIGetAboutSonosStringCBSwigBase scigetaboutsonosstringcbswigbase, String s)
    {
        scigetaboutsonosstringcbswigbase.updateGetAboutSonosString(s);
    }

    public static String SwigDirector_SCIGetSonosPlaylistsCBSwigBase_dumpSCIObj(SCIGetSonosPlaylistsCBSwigBase scigetsonosplaylistscbswigbase)
    {
        return scigetsonosplaylistscbswigbase.dumpSCIObj();
    }

    public static void SwigDirector_SCIGetSonosPlaylistsCBSwigBase_getSonosPlaylistsFailed(SCIGetSonosPlaylistsCBSwigBase scigetsonosplaylistscbswigbase)
    {
        scigetsonosplaylistscbswigbase.getSonosPlaylistsFailed();
    }

    public static void SwigDirector_SCIGetSonosPlaylistsCBSwigBase_getSonosPlaylistsSucceeded(SCIGetSonosPlaylistsCBSwigBase scigetsonosplaylistscbswigbase, String s)
    {
        scigetsonosplaylistscbswigbase.getSonosPlaylistsSucceeded(s);
    }

    public static String SwigDirector_SCILocalMediaCollectionSwigBase_dumpSCIObj(SCILocalMediaCollectionSwigBase scilocalmediacollectionswigbase)
    {
        return scilocalmediacollectionswigbase.dumpSCIObj();
    }

    public static int SwigDirector_SCILocalMediaCollectionSwigBase_getAllNodeType(SCILocalMediaCollectionSwigBase scilocalmediacollectionswigbase)
    {
        return scilocalmediacollectionswigbase.getAllNodeType().swigValue();
    }

    public static long SwigDirector_SCILocalMediaCollectionSwigBase_getCount(SCILocalMediaCollectionSwigBase scilocalmediacollectionswigbase)
    {
        return scilocalmediacollectionswigbase.getCount();
    }

    public static long SwigDirector_SCILocalMediaCollectionSwigBase_getItemAt(SCILocalMediaCollectionSwigBase scilocalmediacollectionswigbase, long l)
    {
        return SCILocalMusicBrowseItemInfo.getCPtr(scilocalmediacollectionswigbase.getItemAt(l));
    }

    public static String SwigDirector_SCILocalMediaCollectionSwigBase_getPowerscrollCSV(SCILocalMediaCollectionSwigBase scilocalmediacollectionswigbase)
    {
        return scilocalmediacollectionswigbase.getPowerscrollCSV();
    }

    public static int SwigDirector_SCILocalMediaCollectionSwigBase_getPresentationType(SCILocalMediaCollectionSwigBase scilocalmediacollectionswigbase)
    {
        return scilocalmediacollectionswigbase.getPresentationType().swigValue();
    }

    public static void SwigDirector_SCILocalMediaCollectionSwigBase_registerMediaCollectionListener(SCILocalMediaCollectionSwigBase scilocalmediacollectionswigbase, long l)
    {
        SCILocalMediaCollectionListener scilocalmediacollectionlistener;
        if(l == 0L)
            scilocalmediacollectionlistener = null;
        else
            scilocalmediacollectionlistener = new SCILocalMediaCollectionListener(l, true);
        scilocalmediacollectionswigbase.registerMediaCollectionListener(scilocalmediacollectionlistener);
    }

    public static boolean SwigDirector_SCILocalMediaCollectionSwigBase_showTrackNumber(SCILocalMediaCollectionSwigBase scilocalmediacollectionswigbase)
    {
        return scilocalmediacollectionswigbase.showTrackNumber();
    }

    public static String SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_dumpSCIObj(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.dumpSCIObj();
    }

    public static String SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_getAlbum(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.getAlbum();
    }

    public static String SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_getArtMimeType(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.getArtMimeType();
    }

    public static int SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_getArtType(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.getArtType().swigValue();
    }

    public static String SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_getArtUri(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.getArtUri();
    }

    public static String SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_getArtist(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.getArtist();
    }

    public static int SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_getByteOffsetForTime(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase, long l)
    {
        return scilocalmusicbrowseiteminfoswigbase.getByteOffsetForTime(l);
    }

    public static String SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_getDisplayString(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase, int i)
    {
        return scilocalmusicbrowseiteminfoswigbase.getDisplayString(SCILocalMusicBrowseItemInfo.DisplayStringId.swigToEnum(i));
    }

    public static long SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_getDuration(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.getDuration();
    }

    public static String SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_getId(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.getId();
    }

    public static int SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_getItemType(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.getItemType().swigValue();
    }

    public static String SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_getMimeType(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.getMimeType();
    }

    public static String SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_getResUri(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.getResUri();
    }

    public static String SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_getTitle(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.getTitle();
    }

    public static int SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_getTrackNumber(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.getTrackNumber();
    }

    public static boolean SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_isContainer(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.isContainer();
    }

    public static boolean SwigDirector_SCILocalMusicBrowseItemInfoSwigBase_isPlayable(SCILocalMusicBrowseItemInfoSwigBase scilocalmusicbrowseiteminfoswigbase)
    {
        return scilocalmusicbrowseiteminfoswigbase.isPlayable();
    }

    public static String SwigDirector_SCILocalMusicSearchableDelegateSwigBase_dumpSCIObj(SCILocalMusicSearchableDelegateSwigBase scilocalmusicsearchabledelegateswigbase)
    {
        return scilocalmusicsearchabledelegateswigbase.dumpSCIObj();
    }

    public static long SwigDirector_SCILocalMusicSearchableDelegateSwigBase_getCategoryIDs(SCILocalMusicSearchableDelegateSwigBase scilocalmusicsearchabledelegateswigbase)
    {
        return SCIStringArray.getCPtr(scilocalmusicsearchabledelegateswigbase.getCategoryIDs());
    }

    public static String SwigDirector_SCILocalMusicSearchableDelegateSwigBase_getLogoURI(SCILocalMusicSearchableDelegateSwigBase scilocalmusicsearchabledelegateswigbase)
    {
        return scilocalmusicsearchabledelegateswigbase.getLogoURI();
    }

    public static String SwigDirector_SCILocalMusicSearchableDelegateSwigBase_getTitle(SCILocalMusicSearchableDelegateSwigBase scilocalmusicsearchabledelegateswigbase)
    {
        return scilocalmusicsearchabledelegateswigbase.getTitle();
    }

    public static String SwigDirector_SCILocalMusicSearchableDelegateSwigBase_objectIdForSearch(SCILocalMusicSearchableDelegateSwigBase scilocalmusicsearchabledelegateswigbase, String s, String s1)
    {
        return scilocalmusicsearchabledelegateswigbase.objectIdForSearch(s, s1);
    }

    public static long SwigDirector_SCILocationServicesSwigBase_createRequestLocationAuthorizationAction(SCILocationServicesSwigBase scilocationservicesswigbase)
    {
        return SCIAction.getCPtr(scilocationservicesswigbase.createRequestLocationAuthorizationAction());
    }

    public static long SwigDirector_SCILocationServicesSwigBase_createRequestLocationInformationAction(SCILocationServicesSwigBase scilocationservicesswigbase, long l)
    {
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return SCIAction.getCPtr(scilocationservicesswigbase.createRequestLocationInformationAction(scistringarray));
    }

    public static String SwigDirector_SCILocationServicesSwigBase_dumpSCIObj(SCILocationServicesSwigBase scilocationservicesswigbase)
    {
        return scilocationservicesswigbase.dumpSCIObj();
    }

    public static String SwigDirector_SCILocationServicesSwigBase_getLocationUsageDescription(SCILocationServicesSwigBase scilocationservicesswigbase)
    {
        return scilocationservicesswigbase.getLocationUsageDescription();
    }

    public static String SwigDirector_SCILocationServicesSwigBase_getLocationUsageExistingHHDescription(SCILocationServicesSwigBase scilocationservicesswigbase)
    {
        return scilocationservicesswigbase.getLocationUsageExistingHHDescription();
    }

    public static int SwigDirector_SCILocationServicesSwigBase_locationStatus(SCILocationServicesSwigBase scilocationservicesswigbase)
    {
        return scilocationservicesswigbase.locationStatus().swigValue();
    }

    public static boolean SwigDirector_SCILocationServicesSwigBase_shouldRequestLocationForExistingHH(SCILocationServicesSwigBase scilocationservicesswigbase)
    {
        return scilocationservicesswigbase.shouldRequestLocationForExistingHH();
    }

    public static String SwigDirector_SCIMusicServerBrowseDelegateSwigBase_dumpSCIObj(SCIMusicServerBrowseDelegateSwigBase scimusicserverbrowsedelegateswigbase)
    {
        return scimusicserverbrowsedelegateswigbase.dumpSCIObj();
    }

    public static long SwigDirector_SCIMusicServerBrowseDelegateSwigBase_getLocalMediaCollectionForId(SCIMusicServerBrowseDelegateSwigBase scimusicserverbrowsedelegateswigbase, String s)
    {
        return SCILocalMediaCollection.getCPtr(scimusicserverbrowsedelegateswigbase.getLocalMediaCollectionForId(s));
    }

    public static long SwigDirector_SCIMusicServerBrowseDelegateSwigBase_getLocalMusicItemInfoForId(SCIMusicServerBrowseDelegateSwigBase scimusicserverbrowsedelegateswigbase, String s)
    {
        return SCILocalMusicBrowseItemInfo.getCPtr(scimusicserverbrowsedelegateswigbase.getLocalMusicItemInfoForId(s));
    }

    public static long SwigDirector_SCIMusicServerBrowseDelegateSwigBase_getLocalMusicSearchableDelegate(SCIMusicServerBrowseDelegateSwigBase scimusicserverbrowsedelegateswigbase)
    {
        return SCILocalMusicSearchableDelegate.getCPtr(scimusicserverbrowsedelegateswigbase.getLocalMusicSearchableDelegate());
    }

    public static String SwigDirector_SCIMusicServerBrowseDelegateSwigBase_getObjectIdForAssociationType(SCIMusicServerBrowseDelegateSwigBase scimusicserverbrowsedelegateswigbase, String s, int i)
    {
        return scimusicserverbrowsedelegateswigbase.getObjectIdForAssociationType(s, SCIMusicServerBrowseDelegate.AssociationType.swigToEnum(i));
    }

    public static long SwigDirector_SCIMusicServerBrowseDelegateSwigBase_getRootItem(SCIMusicServerBrowseDelegateSwigBase scimusicserverbrowsedelegateswigbase)
    {
        return SCILocalMusicBrowseItemInfo.getCPtr(scimusicserverbrowsedelegateswigbase.getRootItem());
    }

    public static String SwigDirector_SCIMusicServerDelegateSwigBase_dumpSCIObj(SCIMusicServerDelegateSwigBase scimusicserverdelegateswigbase)
    {
        return scimusicserverdelegateswigbase.dumpSCIObj();
    }

    public static void SwigDirector_SCIMusicServerDelegateSwigBase_fillImageBytes(SCIMusicServerDelegateSwigBase scimusicserverdelegateswigbase, long l, String s)
    {
        SCIData scidata;
        if(l == 0L)
            scidata = null;
        else
            scidata = new SCIData(l, true);
        scimusicserverdelegateswigbase.fillImageBytes(scidata, s);
    }

    public static long SwigDirector_SCIMusicServerDelegateSwigBase_getMusicServerBrowseDelegate(SCIMusicServerDelegateSwigBase scimusicserverdelegateswigbase)
    {
        return SCIMusicServerBrowseDelegate.getCPtr(scimusicserverdelegateswigbase.getMusicServerBrowseDelegate());
    }

    public static String SwigDirector_SCIMusicServerDelegateSwigBase_getPlayableTrackId(SCIMusicServerDelegateSwigBase scimusicserverdelegateswigbase, String s)
    {
        return scimusicserverdelegateswigbase.getPlayableTrackId(s);
    }

    public static void SwigDirector_SCIMusicServerDelegateSwigBase_onBeginStreaming(SCIMusicServerDelegateSwigBase scimusicserverdelegateswigbase)
    {
        scimusicserverdelegateswigbase.onBeginStreaming();
    }

    public static void SwigDirector_SCIMusicServerDelegateSwigBase_onEndStreaming(SCIMusicServerDelegateSwigBase scimusicserverdelegateswigbase)
    {
        scimusicserverdelegateswigbase.onEndStreaming();
    }

    public static String SwigDirector_SCINetstartListenerSwigBase_dumpSCIObj(SCINetstartListenerSwigBase scinetstartlistenerswigbase)
    {
        return scinetstartlistenerswigbase.dumpSCIObj();
    }

    public static void SwigDirector_SCINetstartListenerSwigBase_onDeviceDiscoveryWaiting(SCINetstartListenerSwigBase scinetstartlistenerswigbase)
    {
        scinetstartlistenerswigbase.onDeviceDiscoveryWaiting();
    }

    public static void SwigDirector_SCINetstartListenerSwigBase_onJoinComplete(SCINetstartListenerSwigBase scinetstartlistenerswigbase)
    {
        scinetstartlistenerswigbase.onJoinComplete();
    }

    public static void SwigDirector_SCINetstartListenerSwigBase_onJoinFail(SCINetstartListenerSwigBase scinetstartlistenerswigbase)
    {
        scinetstartlistenerswigbase.onJoinFail();
    }

    public static void SwigDirector_SCINetstartListenerSwigBase_onNetParamsAcquired(SCINetstartListenerSwigBase scinetstartlistenerswigbase, String s, String s1, int i)
    {
        scinetstartlistenerswigbase.onNetParamsAcquired(s, s1, i);
    }

    public static void SwigDirector_SCINetstartListenerSwigBase_onSonosnetOptionChange(SCINetstartListenerSwigBase scinetstartlistenerswigbase, boolean flag)
    {
        scinetstartlistenerswigbase.onSonosnetOptionChange(flag);
    }

    public static void SwigDirector_SCINetstartListenerSwigBase_onSonosnetPasswordReset(SCINetstartListenerSwigBase scinetstartlistenerswigbase)
    {
        scinetstartlistenerswigbase.onSonosnetPasswordReset();
    }

    public static String SwigDirector_SCINetworkManagementDelegateSwigBase_dumpSCIObj(SCINetworkManagementDelegateSwigBase scinetworkmanagementdelegateswigbase)
    {
        return scinetworkmanagementdelegateswigbase.dumpSCIObj();
    }

    public static int SwigDirector_SCINetworkManagementDelegateSwigBase_getNetworkType(SCINetworkManagementDelegateSwigBase scinetworkmanagementdelegateswigbase)
    {
        return scinetworkmanagementdelegateswigbase.getNetworkType().swigValue();
    }

    public static void SwigDirector_SCIOpCBSwigBase__operationComplete(SCIOpCBSwigBase sciopcbswigbase, long l, int i)
    {
        sciopcbswigbase._operationComplete(l, i);
    }

    public static String SwigDirector_SCIOpCBSwigBase_dumpSCIObj(SCIOpCBSwigBase sciopcbswigbase)
    {
        return sciopcbswigbase.dumpSCIObj();
    }

    public static boolean SwigDirector_SCIPlatformDateTimeProvider_doesPlatformTimeZoneMatch(SCIPlatformDateTimeProvider sciplatformdatetimeprovider, long l)
    {
        SCITimeZone scitimezone;
        if(l == 0L)
            scitimezone = null;
        else
            scitimezone = new SCITimeZone(l, true);
        return sciplatformdatetimeprovider.doesPlatformTimeZoneMatch(scitimezone);
    }

    public static long SwigDirector_SCIPlatformDateTimeProvider_getPlatformDateTime(SCIPlatformDateTimeProvider sciplatformdatetimeprovider)
    {
        return SCISystemTime.getCPtr(sciplatformdatetimeprovider.getPlatformDateTime());
    }

    public static String SwigDirector_SCIStackTraceCaptureDelegateSwigBase_dumpSCIObj(SCIStackTraceCaptureDelegateSwigBase scistacktracecapturedelegateswigbase)
    {
        return scistacktracecapturedelegateswigbase.dumpSCIObj();
    }

    public static void SwigDirector_SCIStackTraceCaptureDelegateSwigBase_stackTraceCaptured(SCIStackTraceCaptureDelegateSwigBase scistacktracecapturedelegateswigbase, String s)
    {
        scistacktracecapturedelegateswigbase.stackTraceCaptured(s);
    }

    public static String SwigDirector_SCITrackInfoSwigBase_dumpSCIObj(SCITrackInfoSwigBase scitrackinfoswigbase)
    {
        return scitrackinfoswigbase.dumpSCIObj();
    }

    public static String SwigDirector_SCITrackInfoSwigBase_getAlbum(SCITrackInfoSwigBase scitrackinfoswigbase)
    {
        return scitrackinfoswigbase.getAlbum();
    }

    public static String SwigDirector_SCITrackInfoSwigBase_getArtist(SCITrackInfoSwigBase scitrackinfoswigbase)
    {
        return scitrackinfoswigbase.getArtist();
    }

    public static long SwigDirector_SCITrackInfoSwigBase_getDuration(SCITrackInfoSwigBase scitrackinfoswigbase)
    {
        return scitrackinfoswigbase.getDuration();
    }

    public static String SwigDirector_SCITrackInfoSwigBase_getId(SCITrackInfoSwigBase scitrackinfoswigbase)
    {
        return scitrackinfoswigbase.getId();
    }

    public static String SwigDirector_SCITrackInfoSwigBase_getResUri(SCITrackInfoSwigBase scitrackinfoswigbase)
    {
        return scitrackinfoswigbase.getResUri();
    }

    public static String SwigDirector_SCITrackInfoSwigBase_getTitle(SCITrackInfoSwigBase scitrackinfoswigbase)
    {
        return scitrackinfoswigbase.getTitle();
    }

    public static void SwigDirector_SCIWebBridgeDelegateSwigBase_bridgeStarted(SCIWebBridgeDelegateSwigBase sciwebbridgedelegateswigbase, String s, boolean flag)
    {
        sciwebbridgedelegateswigbase.bridgeStarted(s, flag);
    }

    public static void SwigDirector_SCIWebBridgeDelegateSwigBase_doPostRouteMessage(SCIWebBridgeDelegateSwigBase sciwebbridgedelegateswigbase, long l, int i)
    {
        SCIWebMessage sciwebmessage;
        if(l == 0L)
            sciwebmessage = null;
        else
            sciwebmessage = new SCIWebMessage(l, true);
        sciwebbridgedelegateswigbase.doPostRouteMessage(sciwebmessage, SCRouteResultType.swigToEnum(i));
    }

    public static int SwigDirector_SCIWebBridgeDelegateSwigBase_doPreRouteMessage(SCIWebBridgeDelegateSwigBase sciwebbridgedelegateswigbase, long l)
    {
        SCIWebMessage sciwebmessage;
        if(l == 0L)
            sciwebmessage = null;
        else
            sciwebmessage = new SCIWebMessage(l, true);
        return sciwebbridgedelegateswigbase.doPreRouteMessage(sciwebmessage).swigValue();
    }

    public static String SwigDirector_SCIWebBridgeDelegateSwigBase_dumpSCIObj(SCIWebBridgeDelegateSwigBase sciwebbridgedelegateswigbase)
    {
        return sciwebbridgedelegateswigbase.dumpSCIObj();
    }

    public static void SwigDirector_SCIWebBridgeDelegateSwigBase_publishNativeEvent(SCIWebBridgeDelegateSwigBase sciwebbridgedelegateswigbase, long l, int i)
    {
        SCIWebMessage sciwebmessage;
        if(l == 0L)
            sciwebmessage = null;
        else
            sciwebmessage = new SCIWebMessage(l, true);
        sciwebbridgedelegateswigbase.publishNativeEvent(sciwebmessage, i);
    }

    public static void SwigDirector_SCIWifiSetupDelegate_cancelSSIDJoin(SCIWifiSetupDelegate sciwifisetupdelegate, String s)
    {
        sciwifisetupdelegate.cancelSSIDJoin(s);
    }

    public static void SwigDirector_SCIWifiSetupDelegate_clearListener(SCIWifiSetupDelegate sciwifisetupdelegate)
    {
        sciwifisetupdelegate.clearListener();
    }

    public static void SwigDirector_SCIWifiSetupDelegate_getWifiInfo(SCIWifiSetupDelegate sciwifisetupdelegate, long l)
    {
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        sciwifisetupdelegate.getWifiInfo(scipropertybag);
    }

    public static boolean SwigDirector_SCIWifiSetupDelegate_isWifiConnected(SCIWifiSetupDelegate sciwifisetupdelegate)
    {
        return sciwifisetupdelegate.isWifiConnected();
    }

    public static void SwigDirector_SCIWifiSetupDelegate_removeSSID(SCIWifiSetupDelegate sciwifisetupdelegate, String s)
    {
        sciwifisetupdelegate.removeSSID(s);
    }

    public static void SwigDirector_SCIWifiSetupDelegate_setSSID(SCIWifiSetupDelegate sciwifisetupdelegate, String s, String s1, long l, long l1)
    {
        SCIWifiListener sciwifilistener;
        if(l1 == 0L)
            sciwifilistener = null;
        else
            sciwifilistener = new SCIWifiListener(l1, true);
        sciwifisetupdelegate.setSSID(s, s1, l, sciwifilistener);
    }

    public static void SwigDirector_SCLibAssertionFailureCallback_assertionFailed(SCLibAssertionFailureCallback sclibassertionfailurecallback, String s, int i, String s1)
    {
        sclibassertionfailurecallback.assertionFailed(s, i, s1);
    }

    public static void SwigDirector_SCLibCallUIThreadCallback_callSCLibOnUIThread(SCLibCallUIThreadCallback sclibcalluithreadcallback)
    {
        sclibcalluithreadcallback.callSCLibOnUIThread();
    }

    public static String SwigDirector_SCLibDiagnosticConsoleLogCallback_getDiagnosticConsoleLog(SCLibDiagnosticConsoleLogCallback sclibdiagnosticconsolelogcallback)
    {
        return sclibdiagnosticconsolelogcallback.getDiagnosticConsoleLog();
    }

    public static String SwigDirector_SCLibDiagnosticExtraInfoCallback_getDiagnosticExtraInfo(SCLibDiagnosticExtraInfoCallback sclibdiagnosticextrainfocallback)
    {
        return sclibdiagnosticextrainfocallback.getDiagnosticExtraInfo();
    }

    public static void SwigDirector_SCLibLogCallback_LogDebugMessage(SCLibLogCallback scliblogcallback, String s, int i, String s1)
    {
        scliblogcallback.LogDebugMessage(s, i, s1);
    }

    public static void SwigDirector_SCLibTruncatedStringsCallback_clearTruncatedStrings(SCLibTruncatedStringsCallback sclibtruncatedstringscallback)
    {
        sclibtruncatedstringscallback.clearTruncatedStrings();
    }

    public static String SwigDirector_SCLibTruncatedStringsCallback_getTruncatedStrings(SCLibTruncatedStringsCallback sclibtruncatedstringscallback)
    {
        return sclibtruncatedstringscallback.getTruncatedStrings();
    }

    public static final native int WIFI_RESULT_FAILURE_get();

    public static final native int WIFI_RESULT_SUCCESS_get();

    public static final native int WIFI_RESULT_WATCHDOG_get();

    public static final native int WIFI_UnknownFreq_get();

    public static final native String WIZARD_COMPONENT_KEY_BOLD_TEXT_get();

    public static final native String WIZARD_COMPONENT_KEY_DISABLE_SLEEP_get();

    public static final native String WIZARD_COMPONENT_KEY_HIDE_NEXT_get();

    public static final native String WIZARD_COMPONENT_KEY_HIDE_PREVIOUS_get();

    public static final native String WIZARD_COMPONENT_KEY_IMAGE_TYPE_KEY_get();

    public static final native String WIZARD_COMPONENT_KEY_IMAGE_URL_get();

    public static final native String WIZARD_COMPONENT_KEY_INPUT_get();

    public static final native String WIZARD_COMPONENT_KEY_INVISIBLE_get();

    public static final native String WIZARD_COMPONENT_KEY_LIST_KEY_get();

    public static final native String WIZARD_COMPONENT_KEY_NEXT_IS_COMPLETE_get();

    public static final native String WIZARD_COMPONENT_KEY_PREVIOUS_IS_CANCEL_get();

    public static final native String WIZARD_COMPONENT_KEY_SELECT_TEXT_get();

    public static final native String WIZARD_COMPONENT_KEY_SHOW_SECURE_TEXT_STRING_get();

    public static final native String WIZARD_COMPONENT_KEY_SHOW_SECURE_TEXT_get();

    public static final native String WIZARD_COMPONENT_KEY_STRING_get();

    public static final native String WIZARD_COMPONENT_KEY_TYPE_get();

    public static final native long createDefaultSCIActionFilter(String s);

    public static final native long createPropertyBag();

    public static final native long createSCActionFilterer();

    public static final native long createSCINetstartGetScanListOp(String s);

    public static final native long createSCIWebBridge(long l, SCIWebBridgeDelegate sciwebbridgedelegate, long l1, SCIHousehold scihousehold);

    public static final native long createSCIWebMessage(String s, String s1);

    public static final native long createSCIntArray();

    public static final native long createSCNullAsyncOperation(int i);

    public static final native long createSCRecurrence();

    public static final native long createSCStringArray();

    public static final native long createSCSystemTime();

    public static final native long createSCTime();

    public static final native long createSCUriArray();

    public static final native long createServiceAccountFilter(int i);

    public static final native long createServiceAccountsByServiceFilter(String s);

    public static final native long createServiceDescriptorFilter(int i);

    public static final native long createShareNameInput();

    public static final native long createStringTemplate(String s);

    public static final native void delete_SCIAction(long l);

    public static final native void delete_SCIActionContext(long l);

    public static final native void delete_SCIActionDelegate(long l);

    public static final native void delete_SCIActionDelegateSwigBase(long l);

    public static final native void delete_SCIActionDescriptor(long l);

    public static final native void delete_SCIActionFactory(long l);

    public static final native void delete_SCIActionFactorySwigBase(long l);

    public static final native void delete_SCIActionFilter(long l);

    public static final native void delete_SCIActionFilterSwigBase(long l);

    public static final native void delete_SCIActionFilterer(long l);

    public static final native void delete_SCIActionNoArgDescriptor(long l);

    public static final native void delete_SCIActionOnAlarmDescriptor(long l);

    public static final native void delete_SCIActionOnGroupDescriptor(long l);

    public static final native void delete_SCIActionSelectableDescriptor(long l);

    public static final native void delete_SCIActionSwigBase(long l);

    public static final native void delete_SCIActionWithBoolDescriptor(long l);

    public static final native void delete_SCIActionWithIntDescriptor(long l);

    public static final native void delete_SCIActionWithStringDescriptor(long l);

    public static final native void delete_SCIAddToQueueAtNumberDescriptor(long l);

    public static final native void delete_SCIAggregateBrowseDataSource(long l);

    public static final native void delete_SCIAlarm(long l);

    public static final native void delete_SCIAlarmManager(long l);

    public static final native void delete_SCIAlarmMusic(long l);

    public static final native void delete_SCIAlarmMusicBrowseItem(long l);

    public static final native void delete_SCIAppReporting(long l);

    public static final native void delete_SCIArtworkCache(long l);

    public static final native void delete_SCIArtworkCacheManager(long l);

    public static final native void delete_SCIArtworkData(long l);

    public static final native void delete_SCIAudibleManager(long l);

    public static final native void delete_SCIAudioData(long l);

    public static final native void delete_SCIAudioInputResource(long l);

    public static final native void delete_SCIBooleanSettingsProperty(long l);

    public static final native void delete_SCIBrowseDataSource(long l);

    public static final native void delete_SCIBrowseGroupsInfo(long l);

    public static final native void delete_SCIBrowseItem(long l);

    public static final native void delete_SCIBrowseItemSwigBase(long l);

    public static final native void delete_SCIBrowseListPresentationMap(long l);

    public static final native void delete_SCIBrowseManager(long l);

    public static final native void delete_SCIBrowseService(long l);

    public static final native void delete_SCIBrowseStackManager(long l);

    public static final native void delete_SCICommittable(long l);

    public static final native void delete_SCIConfigureWirelessWizard(long l);

    public static final native void delete_SCICountry(long l);

    public static final native void delete_SCICustomSubWizard(long l);

    public static final native void delete_SCICustomSubWizardSwigBase(long l);

    public static final native void delete_SCIData(long l);

    public static final native void delete_SCIDateTimeManager(long l);

    public static final native void delete_SCIDateTimeSettingsProperty(long l);

    public static final native void delete_SCIDealerMode(long l);

    public static final native void delete_SCIDebug(long l);

    public static final native void delete_SCIDevice(long l);

    public static final native void delete_SCIDeviceAutoplay(long l);

    public static final native void delete_SCIDeviceLineIn(long l);

    public static final native void delete_SCIDeviceLineOut(long l);

    public static final native void delete_SCIDeviceMusicEqualization(long l);

    public static final native void delete_SCIDeviceSettingsDataSource(long l);

    public static final native void delete_SCIDeviceVolume(long l);

    public static final native void delete_SCIEnumerable(long l);

    public static final native void delete_SCIEnumerator(long l);

    public static final native void delete_SCIEventSink(long l);

    public static final native void delete_SCIEventSinkSwigBase(long l);

    public static final native void delete_SCIGetAboutSonosStringCB(long l);

    public static final native void delete_SCIGetAboutSonosStringCBSwigBase(long l);

    public static final native void delete_SCIGetSonosPlaylistsCB(long l);

    public static final native void delete_SCIGetSonosPlaylistsCBSwigBase(long l);

    public static final native void delete_SCIGroupVolume(long l);

    public static final native void delete_SCIHousehold(long l);

    public static final native void delete_SCIIndexManager(long l);

    public static final native void delete_SCIInfoViewHeaderDataSource(long l);

    public static final native void delete_SCIInfoViewHeaderItem(long l);

    public static final native void delete_SCIInfoViewTextPaneMetadata(long l);

    public static final native void delete_SCIInput(long l);

    public static final native void delete_SCIInputValidationCB(long l);

    public static final native void delete_SCIIntArray(long l);

    public static final native void delete_SCIIntegerSettingsProperty(long l);

    public static final native void delete_SCILatentLoadingDataSource(long l);

    public static final native void delete_SCILibrary(long l);

    public static final native void delete_SCILibraryTests(long l);

    public static final native void delete_SCILinkSettingsProperty(long l);

    public static final native void delete_SCILocalMediaCollection(long l);

    public static final native void delete_SCILocalMediaCollectionListener(long l);

    public static final native void delete_SCILocalMediaCollectionSwigBase(long l);

    public static final native void delete_SCILocalMusicBrowseItemInfo(long l);

    public static final native void delete_SCILocalMusicBrowseItemInfoSwigBase(long l);

    public static final native void delete_SCILocalMusicSearchableDelegate(long l);

    public static final native void delete_SCILocalMusicSearchableDelegateSwigBase(long l);

    public static final native void delete_SCILocationServices(long l);

    public static final native void delete_SCILocationServicesSwigBase(long l);

    public static final native void delete_SCILogoArtworkCache(long l);

    public static final native void delete_SCIMusicServer(long l);

    public static final native void delete_SCIMusicServerBrowseDelegate(long l);

    public static final native void delete_SCIMusicServerBrowseDelegateSwigBase(long l);

    public static final native void delete_SCIMusicServerDelegate(long l);

    public static final native void delete_SCIMusicServerDelegateSwigBase(long l);

    public static final native void delete_SCIMusicServiceWizard(long l);

    public static final native void delete_SCINetstartListener(long l);

    public static final native void delete_SCINetstartListenerSwigBase(long l);

    public static final native void delete_SCINetstartScanListEntry(long l);

    public static final native void delete_SCINetworkManagement(long l);

    public static final native void delete_SCINetworkManagementDelegate(long l);

    public static final native void delete_SCINetworkManagementDelegateSwigBase(long l);

    public static final native void delete_SCINowPlaying(long l);

    public static final native void delete_SCINowPlayingRatings(long l);

    public static final native void delete_SCINowPlayingSleepTimer(long l);

    public static final native void delete_SCINowPlayingSource(long l);

    public static final native void delete_SCINowPlayingTransport(long l);

    public static final native void delete_SCIObj(long l);

    public static final native void delete_SCIOnlineUpdateWizard(long l);

    public static final native void delete_SCIOp(long l);

    public static final native void delete_SCIOpAVTransportGetPositionInfo(long l);

    public static final native void delete_SCIOpAVTransportGetRemainingSleepTimerDuration(long l);

    public static final native void delete_SCIOpAddServiceAccount(long l);

    public static final native void delete_SCIOpAddTracksToQueue(long l);

    public static final native void delete_SCIOpAlarmSave(long l);

    public static final native void delete_SCIOpAttachPrivateQueue(long l);

    public static final native void delete_SCIOpAudioInGetAudioInputAttributes(long l);

    public static final native void delete_SCIOpAudioInGetLineInLevel(long l);

    public static final native void delete_SCIOpCB(long l);

    public static final native void delete_SCIOpCBSwigBase(long l);

    public static final native void delete_SCIOpConnectionManagerGetProtocolInfo(long l);

    public static final native void delete_SCIOpDevicePropertiesGetAutoplayLinkedZones(long l);

    public static final native void delete_SCIOpDevicePropertiesGetAutoplayRoomUUID(long l);

    public static final native void delete_SCIOpDevicePropertiesGetAutoplayVolume(long l);

    public static final native void delete_SCIOpDevicePropertiesGetLEDState(long l);

    public static final native void delete_SCIOpDevicePropertiesGetUseAutoplayVolume(long l);

    public static final native void delete_SCIOpFactory(long l);

    public static final native void delete_SCIOpGenericUpdateQueue(long l);

    public static final native void delete_SCIOpGetAboutSonosString(long l);

    public static final native void delete_SCIOpGetTrackPositionInfo(long l);

    public static final native void delete_SCIOpGetUsageDataShareOption(long l);

    public static final native void delete_SCIOpLoadAlbumArt(long l);

    public static final native void delete_SCIOpLoadLogo(long l);

    public static final native void delete_SCIOpNetstartGetScanList(long l);

    public static final native void delete_SCIOpNewPrivateQueue(long l);

    public static final native void delete_SCIOpQueueAttachQueue(long l);

    public static final native void delete_SCIOpQueueReplaceAllTracks(long l);

    public static final native void delete_SCIOpRegEmailOptIn(long l);

    public static final native void delete_SCIOpRenderingControlGetOutputFixed(long l);

    public static final native void delete_SCIOpRenderingControlGetSupportsOutputFixed(long l);

    public static final native void delete_SCIOpReplaceAccount(long l);

    public static final native void delete_SCIOpSubmitDiagnostics(long l);

    public static final native void delete_SCIOpSystemPropertyGetString(long l);

    public static final native void delete_SCIOpValidateServiceCredentials(long l);

    public static final native void delete_SCIOpZoneGroupTopologyGetZoneGroupState(long l);

    public static final native void delete_SCIOperationProgress(long l);

    public static final native void delete_SCIPandoraResults(long l);

    public static final native void delete_SCIPlatformDateTimeProvider(long l);

    public static final native void delete_SCIPlayQueue(long l);

    public static final native void delete_SCIPlayQueueItemState(long l);

    public static final native void delete_SCIPlayQueueMgr(long l);

    public static final native void delete_SCIPowerscrollDataSource(long l);

    public static final native void delete_SCIProperty(long l);

    public static final native void delete_SCIPropertyBag(long l);

    public static final native void delete_SCIRecurrence(long l);

    public static final native void delete_SCIResource(long l);

    public static final native void delete_SCIRoomResource(long l);

    public static final native void delete_SCIScrobblingService(long l);

    public static final native void delete_SCISearchable(long l);

    public static final native void delete_SCISearchableCategory(long l);

    public static final native void delete_SCISelectableItem(long l);

    public static final native void delete_SCISelectionManager(long l);

    public static final native void delete_SCIServiceAccount(long l);

    public static final native void delete_SCIServiceAccountFilter(long l);

    public static final native void delete_SCIServiceAccountManager(long l);

    public static final native void delete_SCIServiceDescriptor(long l);

    public static final native void delete_SCIServiceDescriptorFilter(long l);

    public static final native void delete_SCIServiceDescriptorManager(long l);

    public static final native void delete_SCISettingsBrowseItem(long l);

    public static final native void delete_SCISettingsProperty(long l);

    public static final native void delete_SCISetupWizard(long l);

    public static final native void delete_SCIShare(long l);

    public static final native void delete_SCIShareManager(long l);

    public static final native void delete_SCISimpleMessagingService(long l);

    public static final native void delete_SCISonosNetSetupWizard(long l);

    public static final native void delete_SCISonosPlaylist(long l);

    public static final native void delete_SCISoundbarWizard(long l);

    public static final native void delete_SCISpinnerSettingsProperty(long l);

    public static final native void delete_SCIStackTraceCaptureDelegate(long l);

    public static final native void delete_SCIStackTraceCaptureDelegateSwigBase(long l);

    public static final native void delete_SCIStringArray(long l);

    public static final native void delete_SCIStringFromCustomSettingsProperty(long l);

    public static final native void delete_SCIStringFromListSettingsProperty(long l);

    public static final native void delete_SCIStringInput(long l);

    public static final native void delete_SCIStringInputBase(long l);

    public static final native void delete_SCIStringInputWithAsyncValidation(long l);

    public static final native void delete_SCIStringSettingsProperty(long l);

    public static final native void delete_SCIStringTemplate(long l);

    public static final native void delete_SCISubCalibrator(long l);

    public static final native void delete_SCISystem(long l);

    public static final native void delete_SCISystemTime(long l);

    public static final native void delete_SCITime(long l);

    public static final native void delete_SCITimeSettingsProperty(long l);

    public static final native void delete_SCITimeZone(long l);

    public static final native void delete_SCITooltip(long l);

    public static final native void delete_SCITrackInfo(long l);

    public static final native void delete_SCITrackInfoSwigBase(long l);

    public static final native void delete_SCIVersion(long l);

    public static final native void delete_SCIVersionRange(long l);

    public static final native void delete_SCIWebBridge(long l);

    public static final native void delete_SCIWebBridgeDelegate(long l);

    public static final native void delete_SCIWebBridgeDelegateSwigBase(long l);

    public static final native void delete_SCIWebMessage(long l);

    public static final native void delete_SCIWebRequestSpec(long l);

    public static final native void delete_SCIWifiListener(long l);

    public static final native void delete_SCIWifiSetupDelegate(long l);

    public static final native void delete_SCIWizard(long l);

    public static final native void delete_SCIZoneGroup(long l);

    public static final native void delete_SCIZoneGroupMgr(long l);

    public static final native void delete_SCLibAssertionFailureCallback(long l);

    public static final native void delete_SCLibCallUIThreadCallback(long l);

    public static final native void delete_SCLibDiagnosticConsoleLogCallback(long l);

    public static final native void delete_SCLibDiagnosticExtraInfoCallback(long l);

    public static final native void delete_SCLibLogCallback(long l);

    public static final native void delete_SCLibParameters(long l);

    public static final native void delete_SCLibTruncatedStringsCallback(long l);

    public static final native void delete_SCUserInterfaceParameters(long l);

    public static final native long getAppReportingInstance();

    public static final native int getPortForMusicServer();

    public static final native String getStaticResUriForResourceId(int i, String s, String s1, String s2);

    public static final native String getVirtualResUriForResourceId__SWIG_0(int i, String s, String s1);

    public static final native String getVirtualResUriForResourceId__SWIG_1(int i, String s, String s1, boolean flag);

    public static final native long new_SCIActionDelegateSwigBase();

    public static final native long new_SCIActionFactorySwigBase();

    public static final native long new_SCIActionFilterSwigBase();

    public static final native long new_SCIActionSwigBase();

    public static final native long new_SCIBrowseItemSwigBase();

    public static final native long new_SCICustomSubWizardSwigBase();

    public static final native long new_SCIEventSinkSwigBase();

    public static final native long new_SCIGetAboutSonosStringCBSwigBase();

    public static final native long new_SCIGetSonosPlaylistsCBSwigBase();

    public static final native long new_SCILocalMediaCollectionSwigBase();

    public static final native long new_SCILocalMusicBrowseItemInfoSwigBase();

    public static final native long new_SCILocalMusicSearchableDelegateSwigBase();

    public static final native long new_SCILocationServicesSwigBase();

    public static final native long new_SCIMusicServerBrowseDelegateSwigBase();

    public static final native long new_SCIMusicServerDelegateSwigBase();

    public static final native long new_SCINetstartListenerSwigBase();

    public static final native long new_SCINetworkManagementDelegateSwigBase();

    public static final native long new_SCIOpCBSwigBase();

    public static final native long new_SCIPlatformDateTimeProvider();

    public static final native long new_SCIStackTraceCaptureDelegateSwigBase();

    public static final native long new_SCITrackInfoSwigBase();

    public static final native long new_SCIWebBridgeDelegateSwigBase();

    public static final native long new_SCIWifiSetupDelegate();

    public static final native long new_SCLibAssertionFailureCallback();

    public static final native long new_SCLibCallUIThreadCallback();

    public static final native long new_SCLibDiagnosticConsoleLogCallback();

    public static final native long new_SCLibDiagnosticExtraInfoCallback();

    public static final native long new_SCLibLogCallback();

    public static final native long new_SCLibParameters();

    public static final native long new_SCLibTruncatedStringsCallback();

    public static final native long new_SCUserInterfaceParameters();

    private static final native void swig_module_init();

    static 
    {
        swig_module_init();
    }
}

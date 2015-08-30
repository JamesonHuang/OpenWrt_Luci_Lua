// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr;

import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import com.android.debug.hv.ViewServer;
import com.sonos.acr.application.ApplicationStateManager;
import com.sonos.acr.application.NetworkSearchStateManager;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.browse.v2.settings.SettingsActivity;
import com.sonos.acr.localaudiolibrary.LocalMusicService;
import com.sonos.acr.moremusic.MoreMusicActivity;
import com.sonos.acr.network.SonosNetworkManager;
import com.sonos.acr.nowplaying.SonosHomeLargeTabletActivity;
import com.sonos.acr.nowplaying.SonosHomePhoneActivity;
import com.sonos.acr.nowplaying.SonosHomeXLargeTabletActivity;
import com.sonos.acr.nowplaying.controllers.HouseholdController;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.sclib.SonosListener;
import com.sonos.acr.sclib.sinks.IndexManagerEventSink;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.search.SearchController;
import com.sonos.acr.services.notification.NotificationService;
import com.sonos.acr.uiactions.UIActionFactory;
import com.sonos.acr.util.DisplayController;
import com.sonos.acr.util.FeedbackHandler;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.MockSensorManager;
import com.sonos.acr.util.NotificationCenter;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.ShakeEventListener;
import com.sonos.acr.util.SonosToast;
import com.sonos.acr.util.TextViewLogger;
import com.sonos.acr.util.UserActivityMonitor;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr:
//            LimitedConnectivityActivity, SonosLaunchActivity

public class SonosActivity extends FragmentActivity
    implements com.sonos.acr.util.NotificationCenter.NotificationListener, com.sonos.acr.network.SonosNetworkManager.ConnectionListener, com.sonos.acr.sclib.sinks.IndexManagerEventSink.IndexManagerListener
{
    public static interface IOrientationChangedListener
    {

        public abstract void onOrientationChanged(int i);
    }


    public SonosActivity()
    {
        LOG_TAG = (new StringBuilder()).append(com/sonos/acr/SonosActivity.getSimpleName()).append(":").append(getClass().getSimpleName()).toString();
        mEditableUICount = 0;
        mHadEditableUI = false;
        sonosNetworkManager = SonosApplication.getInstance().getNetworkStatusMonitor();
        currentOrientation = 0;
        orientationListeners = new ArrayList();
    }

    public static Class getCurrentStateActivity()
    {
        Object obj;
        if(isLimitedConnectivity())
            obj = com/sonos/acr/LimitedConnectivityActivity;
        else
        if(DisplayController.getScreenType() == 1)
            obj = com/sonos/acr/nowplaying/SonosHomeLargeTabletActivity;
        else
        if(DisplayController.getScreenType() == 2)
            obj = com/sonos/acr/nowplaying/SonosHomeXLargeTabletActivity;
        else
            obj = com/sonos/acr/nowplaying/SonosHomePhoneActivity;
        return ((Class) (obj));
    }

    public static boolean isLimitedConnectivity()
    {
        boolean flag;
        if(!SonosApplication.getInstance().getListener().isConnectedAndPlayable())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void addOrientationListener(IOrientationChangedListener iorientationchangedlistener)
    {
        orientationListeners.add(iorientationchangedlistener);
    }

    public boolean canRunInLC()
    {
        return false;
    }

    protected boolean canRunWithoutWifi()
    {
        return false;
    }

    public void doExit()
    {
        SLog.i(LOG_TAG, "SonosActivity.doExit");
        if(android.os.Build.VERSION.SDK_INT < 14)
            startService((new Intent(this, com/sonos/acr/services/notification/NotificationService)).setAction("ACTION_END_NOTIFICATIONS"));
        finish();
    }

    protected void finalize()
        throws Throwable
    {
        super.finalize();
        SLog.i(LOG_TAG, "Activity being garbage collected!!!");
    }

    public void finish()
    {
        Log.i(LOG_TAG, "Activity being finished");
        super.finish();
    }

    public SCIActionFactory getActionFactory()
    {
        return uiActionFactory;
    }

    public volatile Context getApplicationContext()
    {
        return getApplicationContext();
    }

    public SonosApplication getApplicationContext()
    {
        return (SonosApplication)super.getApplicationContext();
    }

    public int getCurrentOrientation()
    {
        return currentOrientation;
    }

    public Household getHousehold()
    {
        return LibraryUtils.getHousehold();
    }

    public HouseholdController getHouseholdController()
    {
        return null;
    }

    public SCILibrary getLibrary()
    {
        return SonosApplication.getInstance().getSCLibManager().getLibrary();
    }

    public SCIDealerMode getSCDealerMode()
    {
        return getApplicationContext().getSCLibManager().getDealerMode();
    }

    public SCIOpFactory getSCOpFactory()
    {
        return getApplicationContext().getSCLibManager().getOpFactory();
    }

    public SCISystem getSCSystem()
    {
        return LibraryUtils.getSystem();
    }

    public SCIZoneGroupMgr getSCZoneGroupManager()
    {
        return getHousehold().getZoneGroupManager();
    }

    public SearchController getSearchController()
    {
        return null;
    }

    public SonosNetworkManager getSonosNetworkManager()
    {
        return sonosNetworkManager;
    }

    public boolean handleKeyDown(int i, KeyEvent keyevent)
    {
        return false;
    }

    public boolean handleKeyUp(int i, KeyEvent keyevent)
    {
        return false;
    }

    public void hideQueue()
    {
    }

    public void hideRoomGrouping()
    {
    }

    public boolean hideRooms()
    {
        return false;
    }

    public void jumpToLCScreen()
    {
        SLog.d(LOG_TAG, "jumpToLCScreen");
        if(!(this instanceof LimitedConnectivityActivity))
        {
            ApplicationStateManager.getInstance().closeAllActivities();
            startActivity((new Intent(this, com/sonos/acr/LimitedConnectivityActivity)).addFlags(0x34000000));
        }
    }

    public boolean moveTaskToBack(boolean flag)
    {
        Log.i(LOG_TAG, "Activity being moved to back");
        return super.moveTaskToBack(flag);
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        getWindow().setFormat(1);
    }

    public void onBackPressed()
    {
        SLog.i(LOG_TAG, "SonosActivity.onBackPressed");
        super.onBackPressed();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        boolean flag;
        flag = false;
        DisplayController.resolvePreferredDensities(getResources());
        super.onConfigurationChanged(configuration);
        if(DisplayController.getScreenType() != 0) goto _L2; else goto _L1
_L1:
        if(configuration.hardKeyboardHidden != 1) goto _L4; else goto _L3
_L3:
        boolean flag1;
        if(mEditableUICount > 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag1)
        {
            Iterator iterator;
            IOrientationChangedListener iorientationchangedlistener;
            boolean flag2;
            if(configuration.orientation == 2)
                flag2 = true;
            else
                flag2 = false;
            if(!flag2)
                setRequestedOrientation(0);
        }
_L6:
        if(currentOrientation != configuration.orientation)
        {
            currentOrientation = configuration.orientation;
            onContentViewUpdateRequested();
        }
_L2:
        iterator = orientationListeners.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            iorientationchangedlistener = (IOrientationChangedListener)iterator.next();
            if(iorientationchangedlistener != null)
                iorientationchangedlistener.onOrientationChanged(getResources().getConfiguration().orientation);
        } while(true);
        break; /* Loop/switch isn't completed */
_L4:
        if(configuration.hardKeyboardHidden == 2)
        {
            if(configuration.orientation == 1)
                flag = true;
            if(!flag)
                setRequestedOrientation(1);
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void onConnectionStatusChange(SonosNetworkManager sonosnetworkmanager)
    {
        if(shouldJumpToLC())
            jumpToLCScreen();
    }

    protected void onContentViewUpdateRequested()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        Log.i(LOG_TAG, "Activity is being created!!");
        if(!(this instanceof SonosLaunchActivity) && !LibraryUtils.getSCLibManager().isInitialized())
            SonosApplication.getInstance().startServices();
        DisplayController.resolvePreferredDensities(getResources());
        super.onCreate(bundle);
        if(DisplayController.getScreenType() == 0)
            setRequestedOrientation(1);
        ApplicationStateManager.getInstance().activityCreated(this);
        if(SonosApplication.isUserAMonkey())
        {
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
        }
        ViewServer.get(this).addWindow(this);
        textViewInflater = TextViewLogger.getLayoutInflater(this);
        String s = getResources().getString(0x7f0c0036);
        if(s.equals("ALPHA") || s.equals("BETA"))
        {
            mSensorManager = (SensorManager)getSystemService("sensor");
            mockSensorManager = new MockSensorManager();
            mSensorListener = new ShakeEventListener(new com.sonos.acr.util.ShakeEventListener.OnShakeListener() {

                public void onShake()
                {
                    if(mSensorListener.feedbackHandler == null)
                        mSensorListener.feedbackHandler = new FeedbackHandler(SonosActivity.this);
                    if(!mSensorListener.feedbackHandler.isShowing)
                        mSensorListener.feedbackHandler.submitDiagAndEmail(getWindow());
                }

                final SonosActivity this$0;

            
            {
                this$0 = SonosActivity.this;
                super();
            }
            }
);
        }
    }

    public View onCreateView(String s, Context context, AttributeSet attributeset)
    {
        View view = null;
        if(textViewInflater != null)
            view = textViewInflater.onCreateView(s, context, attributeset);
        if(view == null)
            view = super.onCreateView(s, context, attributeset);
        return view;
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(LOG_TAG, "Activity is being destroyed!!");
        ViewServer.get(this).removeWindow(this);
        ApplicationStateManager.getInstance().activityDestroyed(this);
    }

    public void onIndexingEvent(SCIIndexManager sciindexmanager, com.sonos.acr.sclib.sinks.IndexManagerEventSink.IndexManagerEvent indexmanagerevent)
    {
        if(indexmanagerevent == com.sonos.acr.sclib.sinks.IndexManagerEventSink.IndexManagerEvent.OnIndexingError && sciindexmanager != null)
            SonosToast.popupDialog(sciindexmanager.getErrorMessage(), "");
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i == 174 && mockSensorManager != null)
            mockSensorManager.doShake();
        return super.onKeyDown(i, keyevent);
    }

    public void onNotification(NotificationCenter notificationcenter, String s, Object obj)
    {
        Context context;
        context = null;
        if(obj != null && (obj instanceof View))
            for(context = ((View)obj).getContext(); (context instanceof ContextWrapper) && context != this; context = ((ContextWrapper)context).getBaseContext());
        if(context != this || DisplayController.getScreenType() != 0) goto _L2; else goto _L1
_L1:
        if(!s.equals(KEYBOARD_ENTRY_AVAILABLE)) goto _L4; else goto _L3
_L3:
        mHadEditableUI = true;
        mEditableUICount = 1 + mEditableUICount;
        boolean flag1;
        if(getResources().getConfiguration().hardKeyboardHidden == 1)
            flag1 = true;
        else
            flag1 = false;
        if(flag1 && mEditableUICount == 1)
            setRequestedOrientation(0);
_L2:
        return;
_L4:
        if(s.equals(KEYBOARD_ENTRY_UNAVAILABLE) && mHadEditableUI)
            if(mEditableUICount <= 0)
            {
                SLog.e(LOG_TAG, "'mEditableUICount' is already 0");
            } else
            {
                mEditableUICount = -1 + mEditableUICount;
                boolean flag;
                if(getResources().getConfiguration().hardKeyboardHidden == 1)
                    flag = true;
                else
                    flag = false;
                if(flag && mEditableUICount == 0)
                    setRequestedOrientation(1);
            }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        boolean flag = true;
        if(menuitem.getItemId() == 0x7f0a0207)
            showSettings();
        else
        if(menuitem.getItemId() == 0x7f0a0208)
            showAlarmSettings();
        else
            flag = super.onOptionsItemSelected(menuitem);
        return flag;
    }

    protected void onPause()
    {
        SonosApplication.getInstance().getNetworkSearchStateMonitor().suspendNetworkingIfNeeded();
        if(mSensorManager != null)
            mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
        Log.i(LOG_TAG, "Activity is being paused!!");
        ApplicationStateManager.getInstance().activityPaused(this);
        SonosApplication.getInstance().getNetworkStatusMonitor().unsubscribe(this);
        IndexManagerEventSink.getInstance().removeListener(this);
    }

    protected void onResume()
    {
        super.onResume();
        SonosApplication.getInstance().getNetworkSearchStateMonitor().resumeNetworkingIfNeeded();
        if(shouldJumpToLC())
            jumpToLCScreen();
        SonosApplication.getInstance().getNetworkStatusMonitor().subscribe(this);
        IndexManagerEventSink.getInstance().addListener(this);
        Log.i(LOG_TAG, "Activity is being resumed!!");
        registerUIActionFactory();
        ApplicationStateManager.getInstance().activityResumed(this);
        if(!(this instanceof SonosLaunchActivity))
        {
            NotificationService.init(SonosApplication.getInstance());
            LocalMusicService.init(SonosApplication.getInstance());
        }
        if(mSensorManager != null)
            mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(1), 2);
        if(mockSensorManager != null)
            mockSensorManager.registerListener(mSensorListener);
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
    }

    public boolean onSearchRequested()
    {
        return true;
    }

    protected void onSoftKeyboardShown(boolean flag)
    {
    }

    protected void onStart()
    {
        Log.i(LOG_TAG, "Activity is being started!!");
        super.onStart();
        NotificationCenter.defaultCenter().addObserver(KEYBOARD_ENTRY_AVAILABLE, this);
        NotificationCenter.defaultCenter().addObserver(KEYBOARD_ENTRY_UNAVAILABLE, this);
        SonosApplication.getInstance().onStartup();
    }

    protected void onStop()
    {
        super.onStop();
        Log.i(LOG_TAG, "Activity is being stopped!!");
        NotificationCenter.defaultCenter().removeObserver(KEYBOARD_ENTRY_AVAILABLE, this);
        NotificationCenter.defaultCenter().removeObserver(KEYBOARD_ENTRY_UNAVAILABLE, this);
        mEditableUICount = 0;
        mHadEditableUI = false;
        SonosApplication.getInstance().onShutdown();
        unregisterUIActionFactory();
    }

    public void onUserInteraction()
    {
        super.onUserInteraction();
        UserActivityMonitor useractivitymonitor = SonosApplication.getInstance().getUserActivityMonitor();
        if(useractivitymonitor != null)
            useractivitymonitor.onUserActivity();
    }

    public void onWindowFocusChanged(boolean flag)
    {
        super.onWindowFocusChanged(flag);
        if(flag)
            registerUIActionFactory();
    }

    protected void registerUIActionFactory()
    {
        SCLibManager sclibmanager = LibraryUtils.getSCLibManager();
        if(sclibmanager.isInitialized())
        {
            if(uiActionFactory == null)
                uiActionFactory = new UIActionFactory(this);
            sclibmanager.setActionFactory(uiActionFactory);
        }
    }

    public void removeOrientationListener(IOrientationChangedListener iorientationchangedlistener)
    {
        if(orientationListeners.contains(iorientationchangedlistener))
            orientationListeners.remove(iorientationchangedlistener);
    }

    protected boolean shouldJumpToLC()
    {
        boolean flag;
        if(!getSonosNetworkManager().isWifiConnected() && !canRunWithoutWifi() || isLimitedConnectivity() && !canRunInLC())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void showAlarmSettings()
    {
        startActivity((new Intent(this, com/sonos/acr/browse/v2/settings/SettingsActivity)).setAction("SHOW_FRAGMENT").addFlags(0x20020000).putExtra("BROWSE_THEME", 0x7f010053).putExtra("ROOT_URI", sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Settings_Alarms)).putExtra("ROOT_TITLE", 0x7f0c005a));
    }

    public void showBrowseMusic()
    {
        showBrowseMusic(false);
    }

    public void showBrowseMusic(boolean flag)
    {
        int i = 0x20020000;
        if(flag)
            i |= 0x10000000;
        startActivity((new Intent(this, getCurrentStateActivity())).addFlags(i).setAction("SHOW_MUSICBROWSE"));
    }

    public void showInfo(View view)
    {
    }

    public void showMoreMusic(SCIPropertyBag scipropertybag)
    {
        Household household = getHousehold();
        if(household != null)
            household.updateAvailableServices();
        Intent intent = new Intent(this, com/sonos/acr/moremusic/MoreMusicActivity);
        if(scipropertybag != null && scipropertybag.doesPropExist(sclibConstants.SCACTN_BAGPROP_URL_QUERYSTR))
        {
            SCIPropertyBag scipropertybag1 = scipropertybag.getPropBagProp(sclibConstants.SCACTN_BAGPROP_URL_QUERYSTR);
            SCIStringArray scistringarray = scipropertybag1.getKeys();
            long l = scistringarray.size();
            Bundle bundle = null;
            if(l > 0L)
            {
                bundle = new Bundle();
                intent.putExtra(sclibConstants.SCACTN_BAGPROP_URL_QUERYSTR, bundle);
            }
            for(long l1 = 0L; l1 < l; l1++)
            {
                String s = scistringarray.getAt(l1);
                SCIProperty sciproperty = scipropertybag1.getProperty(s);
                if(sciproperty.getPropType() == SCPropertyType.SC_PROP_TYPE_STR)
                    bundle.putString(s, sciproperty.getStrValue());
            }

        }
        intent.addFlags(0x30020000);
        startActivity(intent);
    }

    public void showNowPlaying()
    {
        showNowPlaying(false);
    }

    public void showNowPlaying(boolean flag)
    {
        int i = 0x24020000;
        if(flag)
            i |= 0x10000000;
        startActivity((new Intent(this, getCurrentStateActivity())).addFlags(i).setAction("SHOW_METADATA"));
    }

    public void showQueue()
    {
    }

    public void showRoomGrouping(String s)
    {
    }

    public void showRoomSettings()
    {
        startActivity((new Intent(this, com/sonos/acr/browse/v2/settings/SettingsActivity)).setAction("SHOW_FRAGMENT").addFlags(0x20020000).putExtra("BROWSE_THEME", 0x7f010053).putExtra("ROOT_URI", sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Settings_ZonePlayers)).putExtra("ROOT_TITLE", 0x7f0c00ac));
    }

    public void showRooms()
    {
        startActivity((new Intent(this, getCurrentStateActivity())).addFlags(0x20000000));
        overridePendingTransition(0x7f040001, 0x7f040002);
    }

    public void showSearch()
    {
    }

    public void showSearch(SCIPropertyBag scipropertybag)
    {
    }

    public void showSettings()
    {
        startActivity((new Intent(this, com/sonos/acr/browse/v2/settings/SettingsActivity)).setAction("SHOW_FRAGMENT").addFlags(0x20020000).putExtra("BROWSE_THEME", 0x7f010053).putExtra("ROOT_URI", sclib.SCLibGetFixedSCUri(SCFixedSCUriID.SC_FIXEDSCURI_Settings)).putExtra("ROOT_TITLE", 0x7f0c00ca));
    }

    public void showShareNowPlaying(String s)
    {
        Resources resources = getResources();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", s);
        intent.putExtra("android.intent.extra.SUBJECT", resources.getString(0x7f0c005e));
        boolean flag = false;
        int i;
        if(LibraryUtils.getCurrentZoneGroup() != null && LibraryUtils.getCurrentZoneGroup().nowPlaying != null)
        {
            SCNPSourceType scnpsourcetype = LibraryUtils.getCurrentZoneGroup().nowPlaying.getSourceType();
            if(scnpsourcetype.equals(SCNPSourceType.SC_NP_TYPE_INTERNET_RADIO) || scnpsourcetype.equals(SCNPSourceType.SC_NP_TYPE_RHAPSODY_RADIO) || scnpsourcetype.equals(SCNPSourceType.SC_NP_TYPE_HLS) || scnpsourcetype.equals(SCNPSourceType.SC_NP_TYPE_LASTFM_RADIO) || scnpsourcetype.equals(SCNPSourceType.SC_NP_TYPE_SONOS_PROGRADIO))
                flag = true;
            else
                flag = false;
        }
        if(flag)
            i = 0x7f0c005c;
        else
            i = 0x7f0c005d;
        startActivity(Intent.createChooser(intent, resources.getString(i)));
    }

    public void showTextPane(String s, String s1, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata, SCIActionContext sciactioncontext)
    {
    }

    public void startActivity(Intent intent)
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Starting new Activity: ").append(intent).toString());
        super.startActivity(intent);
    }

    public void startActivity(Intent intent, Bundle bundle)
    {
        SLog.i(LOG_TAG, (new StringBuilder()).append("Starting new Activity: ").append(intent).toString());
        super.startActivity(intent, bundle);
    }

    protected void unregisterUIActionFactory()
    {
        if(uiActionFactory != null)
        {
            SCLibManager sclibmanager = LibraryUtils.getSCLibManager();
            if(sclibmanager.isInitialized())
                sclibmanager.removeActionFactory(uiActionFactory);
        }
    }

    public static final String KEYBOARD_ENTRY_AVAILABLE = (new StringBuilder()).append(com/sonos/acr/SonosActivity.getSimpleName()).append(":KeyboardEntryAvailable").toString();
    public static final String KEYBOARD_ENTRY_UNAVAILABLE = (new StringBuilder()).append(com/sonos/acr/SonosActivity.getSimpleName()).append(":KeyboardEntryUnavailable").toString();
    public static final String TAG = "SONOS";
    public String LOG_TAG;
    protected int currentOrientation;
    protected int mEditableUICount;
    protected boolean mHadEditableUI;
    private ShakeEventListener mSensorListener;
    private SensorManager mSensorManager;
    private MockSensorManager mockSensorManager;
    private ArrayList orientationListeners;
    protected SonosNetworkManager sonosNetworkManager;
    private android.view.LayoutInflater.Factory textViewInflater;
    protected UIActionFactory uiActionFactory;


}

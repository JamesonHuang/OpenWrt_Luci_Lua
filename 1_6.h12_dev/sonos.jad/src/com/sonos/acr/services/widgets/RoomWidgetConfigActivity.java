// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.widgets;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.graphics.Rect;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.*;
import com.sonos.acr.SonosLaunchActivity;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.network.SonosNetworkManager;
import com.sonos.acr.nowplaying.controllers.HouseholdController;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.sclib.sinks.AllNowPlayingEventSink;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.util.*;
import com.sonos.acr.view.PlayIndicatorView;
import com.sonos.acr.view.RemoteImageView;
import com.sonos.sclib.SCIAppReporting;
import com.sonos.sclib.sclib;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.services.widgets:
//            RoomWidgetService

public class RoomWidgetConfigActivity extends Activity
    implements com.sonos.acr.sclib.sinks.AllNowPlayingEventSink.AllNowPlayingEventListener
{
    public class WidgetConfigCell extends RelativeLayout
    {

        private void updateDeviceViews(ZoneDevice zonedevice)
        {
            zoneName.setText(zonedevice.getTitle());
            if(originalZoneDeviceId == null)
                checkImage.setVisibility(8);
            else
            if(!originalZoneDeviceId.equals(zonedevice.getId()))
                checkImage.setVisibility(4);
            else
                checkImage.setVisibility(0);
        }

        public void initialize(Context context)
        {
            LayoutInflater.from(context).inflate(0x7f0300af, this, true);
            zmMetaDataText1 = (TextView)findViewById(0x7f0a0154);
            zmMetaDataText2 = (TextView)findViewById(0x7f0a0155);
            albumArt = (RemoteImageView)findViewById(0x7f0a002c);
            playIndicator = (PlayIndicatorView)findViewById(0x7f0a01f9);
            row1 = (TableRow)findViewById(0x7f0a01f8);
            row2 = (TableRow)findViewById(0x7f0a01fa);
            zoneName = (TextView)findViewById(0x7f0a0064);
            checkImage = (ImageView)findViewById(0x7f0a01fb);
        }

        public void setZone(ZoneGroup zonegroup, ZoneDevice zonedevice)
        {
            if(!deviceId.equals(zonedevice.getId()))
            {
                deviceId = zonedevice.getId();
                updateDeviceViews(zonedevice);
                updateNowPlayingView(zonegroup.nowPlaying);
            }
        }

        public void updateNowPlayingView(NowPlaying nowplaying)
        {
            String as[] = nowplaying.getDoubleLineMetaData();
            TextView textview = zmMetaDataText1;
            String s;
            TextView textview1;
            String s1;
            if(as != null)
                s = as[0];
            else
                s = "";
            textview.setText(s);
            textview1 = zmMetaDataText2;
            if(as != null)
                s1 = as[1];
            else
                s1 = "";
            textview1.setText(s1);
            albumArt.setSmallImageFromNowPlaying(nowplaying);
            if(nowplaying.isPlaying())
                playIndicator.setVisibility(0);
            else
                playIndicator.setVisibility(8);
            playIndicator.setController(householdController.getPlayIndicatorController(nowplaying.getZoneGroup().getID()));
            if(!StringUtils.isNotEmptyOrNull(zmMetaDataText2.getText())) goto _L2; else goto _L1
_L1:
            zmMetaDataText2.setVisibility(0);
            if(playIndicator.getParent() == row1)
            {
                row1.removeView(playIndicator);
                row2.addView(playIndicator);
            }
_L4:
            return;
_L2:
            zmMetaDataText2.setVisibility(8);
            if(playIndicator.getParent() == row2)
            {
                row2.removeView(playIndicator);
                row1.addView(playIndicator);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        protected RemoteImageView albumArt;
        protected ImageView checkImage;
        private String deviceId;
        protected PlayIndicatorView playIndicator;
        protected TableRow row1;
        protected TableRow row2;
        final RoomWidgetConfigActivity this$0;
        protected TextView zmMetaDataText1;
        protected TextView zmMetaDataText2;
        protected TextView zoneName;

        public WidgetConfigCell(Context context)
        {
            this$0 = RoomWidgetConfigActivity.this;
            super(context);
            deviceId = "";
            initialize(context);
        }
    }


    public RoomWidgetConfigActivity()
    {
        widgetId = 0;
        originalZoneDeviceId = null;
        searchingTimerHandler = new Handler();
        notInRangeDialog = null;
    }

    private void showConfigureNotSetUpDialog()
    {
        (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0c0118).setMessage(0x7f0c0117).setCancelable(true).setNegativeButton(0x7f0c0037, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                finish();
            }

            final RoomWidgetConfigActivity this$0;

            
            {
                this$0 = RoomWidgetConfigActivity.this;
                super();
            }
        }
).setPositiveButton(0x7f0c00a1, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                startActivity(new Intent(RoomWidgetConfigActivity.this, com/sonos/acr/SonosLaunchActivity));
                finish();
            }

            final RoomWidgetConfigActivity this$0;

            
            {
                this$0 = RoomWidgetConfigActivity.this;
                super();
            }
        }
).setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialoginterface)
            {
                finish();
            }

            final RoomWidgetConfigActivity this$0;

            
            {
                this$0 = RoomWidgetConfigActivity.this;
                super();
            }
        }
).show();
    }

    private void startSearchingTimer(boolean flag)
    {
        Handler handler = searchingTimerHandler;
        Runnable runnable = new Runnable() {

            public void run()
            {
                stopSearchingTimer();
                if(networkStatusMonitor.isWifiConnected())
                    showConfigureNotSetUpDialog();
                else
                    notInRangeDialog = (new android.app.AlertDialog.Builder(RoomWidgetConfigActivity.this)).setTitle(0x7f0c0121).setMessage(0x7f0c0120).setPositiveButton(0x7f0c0050, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            finish();
                        }

                        final _cls6 this$1;

                    
                    {
                        this$1 = _cls6.this;
                        super();
                    }
                    }
).show();
            }

            final RoomWidgetConfigActivity this$0;

            
            {
                this$0 = RoomWidgetConfigActivity.this;
                super();
            }
        }
;
        long l;
        if(flag)
            l = 20000L;
        else
            l = 10000L;
        handler.postDelayed(runnable, l);
    }

    private void stopSearchingTimer()
    {
        searchingTimerHandler.removeCallbacksAndMessages(null);
    }

    private void updateDialog()
    {
        stopSearchingTimer();
        if(notInRangeDialog != null && notInRangeDialog.isShowing())
            notInRangeDialog.dismiss();
        if(!networkStatusMonitor.isWifiConnected())
        {
            showMessage(0x7f0c00c9);
            startSearchingTimer(true);
        } else
        {
            Household household = LibraryUtils.getHousehold();
            ArrayList arraylist = household.getDevices(com.sonos.sclib.SCIHousehold.DevFilterOpt.FLT_DEV_COMPATIBLE_AND_VISIBLE);
            if(arraylist.size() == 0)
            {
                showMessage(0x7f0c0054);
                startSearchingTimer(false);
            } else
            {
                showZoneDevices(arraylist, household);
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        if(motionevent.getAction() == 0)
        {
            Rect rect = new Rect();
            Window window = getWindow();
            if(window != null && window.getDecorView() != null)
            {
                window.getDecorView().getHitRect(rect);
                if(!rect.contains((int)motionevent.getX(), (int)motionevent.getY()))
                    finish();
            }
        }
        return super.dispatchTouchEvent(motionevent);
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setResult(0);
        requestWindowFeature(1);
        setContentView(0x7f0300ae);
        progressBar = (ProgressBar)findViewById(0x7f0a01b0);
        progressMessage = (TextView)findViewById(0x7f0a01f7);
        zoneDeviceList = (LinearLayout)findViewById(0x7f0a01f6);
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null)
        {
            widgetId = bundle1.getInt("appWidgetId", 0);
            originalZoneDeviceId = bundle1.getString("com.sonos.intent.extra.ZDID");
        }
        if(widgetId == 0)
        {
            SLog.e(LOG_TAG, "onCreate, invalid widget ID!");
            finish();
        }
        if(!LibraryUtils.getSCLibManager().isInitialized())
            SonosApplication.getInstance().startServices();
        networkStatusMonitor = SonosApplication.getInstance().getNetworkStatusMonitor();
        householdController = new HouseholdController(this);
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged)
            updateDialog();
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent nowplayevent)
    {
        if(nowplayevent == com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent.OnMusicChanged)
            updateNowPlaying(nowplaying);
    }

    protected void onStart()
    {
        super.onStart();
        householdController.subscribe();
        if("".equals(LibraryUtils.getHousehold().getID()))
            showConfigureNotSetUpDialog();
        else
        if(((WifiManager)getSystemService("wifi")).isWifiEnabled())
            AllNowPlayingEventSink.getInstance().addListener(this);
        else
            (new android.app.AlertDialog.Builder(this)).setMessage(0x7f0c011e).setTitle(0x7f0c011f).setNegativeButton(0x7f0c0037, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    finish();
                }

                final RoomWidgetConfigActivity this$0;

            
            {
                this$0 = RoomWidgetConfigActivity.this;
                super();
            }
            }
).setPositiveButton(0x7f0c0051, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    AllNowPlayingEventSink.getInstance().addListener(RoomWidgetConfigActivity.this);
                    WifiManager wifimanager = (WifiManager)getSystemService("wifi");
                    try
                    {
                        wifimanager.setWifiEnabled(true);
                    }
                    catch(SecurityException securityexception) { }
                    updateDialog();
                }

                final RoomWidgetConfigActivity this$0;

            
            {
                this$0 = RoomWidgetConfigActivity.this;
                super();
            }
            }
).show();
    }

    protected void onStop()
    {
        super.onStop();
        stopSearchingTimer();
        AllNowPlayingEventSink.getInstance().removeListener(this);
        householdController.unsubscribe();
    }

    public void showMessage(int i)
    {
        progressMessage.setText(i);
        zoneDeviceList.setVisibility(8);
        progressBar.setVisibility(0);
        progressMessage.setVisibility(0);
    }

    public void showZoneDevices(ArrayList arraylist, Household household)
    {
        zoneDeviceList.setVisibility(0);
        progressBar.setVisibility(8);
        progressMessage.setVisibility(8);
        zoneDeviceList.removeAllViews();
        Iterator iterator = arraylist.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            final ZoneDevice device = (ZoneDevice)iterator.next();
            ZoneGroup zonegroup = household.lookupZoneGroupByDevice(device.getId());
            if(zonegroup != null)
            {
                WidgetConfigCell widgetconfigcell = new WidgetConfigCell(this);
                widgetconfigcell.setZone(zonegroup, device);
                widgetconfigcell.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        LibraryUtils.getHouseholdPreferences().edit().putString((new StringBuilder()).append("NOWPLAYING_WIDGET_DEVICE_ID").append(widgetId).toString(), device.getId()).commit();
                        setResult(-1, (new Intent()).putExtra("appWidgetId", widgetId));
                        startService((new Intent(RoomWidgetConfigActivity.this, com/sonos/acr/services/widgets/RoomWidgetService)).setAction("UPDATE_WIDGETS"));
                        sclib.getAppReportingInstance().interaction(com.sonos.sclib.SCIAppReporting.SCInteractionID.TAP, com.sonos.sclib.SCIAppReporting.SCViewID.WIDGET, com.sonos.sclib.SCIAppReporting.SCViewComponentID.VC_ROOM);
                        finish();
                    }

                    final RoomWidgetConfigActivity this$0;
                    final ZoneDevice val$device;

            
            {
                this$0 = RoomWidgetConfigActivity.this;
                device = zonedevice;
                super();
            }
                }
);
                widgetconfigcell.setTag(device.getId());
                zoneDeviceList.addView(widgetconfigcell);
            }
        } while(true);
    }

    public void updateNowPlaying(NowPlaying nowplaying)
    {
        Iterator iterator = nowplaying.getZoneGroup().getDevices().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ZoneDevice zonedevice = (ZoneDevice)iterator.next();
            WidgetConfigCell widgetconfigcell = (WidgetConfigCell)zoneDeviceList.findViewWithTag(zonedevice.getId());
            if(widgetconfigcell != null)
                widgetconfigcell.updateNowPlayingView(nowplaying);
        } while(true);
    }

    public static final String LOG_TAG = com/sonos/acr/services/widgets/RoomWidgetConfigActivity.getSimpleName();
    private HouseholdController householdController;
    private SonosNetworkManager networkStatusMonitor;
    AlertDialog notInRangeDialog;
    private String originalZoneDeviceId;
    private ProgressBar progressBar;
    private TextView progressMessage;
    private Handler searchingTimerHandler;
    private int widgetId;
    private LinearLayout zoneDeviceList;








}

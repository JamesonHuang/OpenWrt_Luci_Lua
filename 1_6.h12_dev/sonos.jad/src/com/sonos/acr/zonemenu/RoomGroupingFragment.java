// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.zonemenu;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.*;
import android.widget.*;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.SonosFragment;
import com.sonos.acr.nowplaying.controllers.HouseholdController;
import com.sonos.acr.sclib.sinks.AllNowPlayingEventSink;
import com.sonos.acr.sclib.sinks.HouseholdEventSink;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.uibusymanager.UiBusyManager;
import com.sonos.acr.util.*;
import com.sonos.acr.view.*;
import com.sonos.sclib.*;
import java.util.*;

public class RoomGroupingFragment extends SonosFragment
    implements android.view.View.OnClickListener, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener, com.sonos.acr.sclib.sinks.AllNowPlayingEventSink.AllNowPlayingEventListener, android.widget.AdapterView.OnItemClickListener
{

    public RoomGroupingFragment()
    {
        groupingZoneGroup = "";
        scrollFromLaunch = true;
    }

    private void fillGroupingWithData(String s)
    {
        ZoneGroup zonegroup = LibraryUtils.getHousehold().lookupZoneGroup(s);
        if(zonegroup != null)
        {
            NowPlaying nowplaying = zonegroup.nowPlaying;
            if(nowplaying != null)
            {
                String as[] = nowplaying.getDoubleLineMetaData();
                TextView textview = zmMetaDataText1;
                String s1;
                TextView textview1;
                String s2;
                if(as != null)
                    s1 = as[0];
                else
                    s1 = "";
                textview.setText(s1);
                textview1 = zmMetaDataText2;
                if(as != null)
                    s2 = as[1];
                else
                    s2 = "";
                textview1.setText(s2);
                if(albumArtBg != null)
                    albumArtBg.setImageFromNowPlaying(nowplaying);
                groupAlbumArt.setSmallImageFromNowPlaying(nowplaying);
                zmMetaDataText2.getId();
                if(as == null || StringUtils.isEmptyOrNull(as[0]))
                    zmMetaDataText1.setVisibility(8);
                else
                    zmMetaDataText1.setVisibility(0);
                if(as == null || StringUtils.isEmptyOrNull(as[1]))
                {
                    zmMetaDataText2.setVisibility(8);
                    zmMetaDataText1.getId();
                    playIndicatorSecond.setVisibility(8);
                    playIndicatorFirst.setVisibility(0);
                } else
                {
                    zmMetaDataText2.setVisibility(0);
                    playIndicatorSecond.setVisibility(0);
                    playIndicatorFirst.setVisibility(8);
                }
            }
        }
    }

    private int getCheckedCount()
    {
        int i = zonesList.getCount();
        int j = 0;
        for(int k = 0; k < i; k++)
            if(zonesList.isItemChecked(k))
                j++;

        return j;
    }

    private ArrayList getCheckedDevices(List list, ZoneGroup zonegroup)
    {
        ArrayList arraylist;
label0:
        {
            arraylist = new ArrayList();
            if(zonesAdapter != null && zonesAdapter.getCount() > 0)
            {
                SparseBooleanArray sparsebooleanarray = zonesList.getCheckedItemPositions();
                if(sparsebooleanarray != null)
                {
                    for(int i = 0; i < sparsebooleanarray.size(); i++)
                    {
                        ZoneDevice zonedevice1 = (ZoneDevice)zonesAdapter.getItem(i);
                        if(zonedevice1 != null)
                            arraylist.add(zonedevice1.getId());
                    }

                    break label0;
                }
            }
            ArrayList arraylist1 = zonegroup.getDevices();
            Iterator iterator = list.iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                ZoneDevice zonedevice = (ZoneDevice)iterator.next();
                if(arraylist1.contains(zonedevice))
                    arraylist.add(zonedevice.getId());
            } while(true);
        }
        return arraylist;
    }

    private void saveDevices()
    {
        Household household = LibraryUtils.getHousehold();
        if(household != null)
        {
            ZoneGroup zonegroup = household.lookupZoneGroup(groupingZoneGroup);
            if(zonegroup != null)
            {
                ArrayList arraylist = zonegroup.getDevices();
                SCIStringArray scistringarray = sclib.createSCStringArray();
                SCIStringArray scistringarray1 = sclib.createSCStringArray();
                int i = 0;
                while(i < zonesList.getCount()) 
                {
                    ZoneDevice zonedevice = (ZoneDevice)zonesList.getItemAtPosition(i);
                    if(zonesList.isItemChecked(i))
                    {
                        if(!arraylist.contains(zonedevice))
                            scistringarray.append(zonedevice.getId());
                    } else
                    if(arraylist.contains(zonedevice))
                        scistringarray1.append(zonedevice.getId());
                    i++;
                }
                if(!scistringarray.isEmpty() || !scistringarray1.isEmpty())
                {
                    SCIZoneGroupMgr scizonegroupmgr = household.getZoneGroupManager();
                    if(scizonegroupmgr != null)
                    {
                        com.sonos.sclib.SCIOp sciop = scizonegroupmgr.createAddAndDropDevicesOp(groupingZoneGroup, scistringarray, scistringarray1);
                        if(sciop != null)
                        {
                            SCIOpCBSwigBase sciopcbswigbase = new SCIOpCBSwigBase() {

                                public void _operationComplete(long l, int j)
                                {
                                    SLog.d(RoomGroupingFragment.LOG_TAG, (new StringBuilder()).append("addAndDropOp completed ").append(l).append(" ").append(j).toString());
                                    dismissFragment();
                                    isSavingDevices = false;
                                }

                                final RoomGroupingFragment this$0;

            
            {
                this$0 = RoomGroupingFragment.this;
                super();
            }
                            }
;
                            busyManager = new UiBusyManager(getActivity(), sciop, sciopcbswigbase);
                            busyManager.start();
                            isSavingDevices = true;
                        }
                    }
                } else
                {
                    dismissFragment();
                }
            }
        }
    }

    private void showNoZoneSelectedWarning()
    {
        (new android.app.AlertDialog.Builder(themedContext)).setIcon(0x1080027).setTitle(0x7f0c0124).setMessage(0x7f0c0123).setCancelable(false).setPositiveButton(0x1040013, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                saveDevices();
            }

            final RoomGroupingFragment this$0;

            
            {
                this$0 = RoomGroupingFragment.this;
                super();
            }
        }
).setNegativeButton(0x1040009, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            final RoomGroupingFragment this$0;

            
            {
                this$0 = RoomGroupingFragment.this;
                super();
            }
        }
).show();
    }

    private void updateSelectAllButton()
    {
        if(zonesList != null)
        {
            int i = 0;
            for(int j = 0; j < zonesList.getCheckedItemPositions().size(); j++)
            {
                int k = zonesList.getCheckedItemPositions().keyAt(j);
                if(zonesList.getCheckedItemPositions().get(k))
                    i++;
            }

            if(i < zonesList.getCount())
            {
                selectAllButton.setText(getResources().getString(0x7f0c0125));
                selectAllButton.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(0x7f02006f), null, null, null);
            } else
            {
                selectAllButton.setText(getResources().getString(0x7f0c0129));
                selectAllButton.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(0x7f02008c), null, null, null);
            }
        }
    }

    public void dismissFragment()
    {
        if(getSonosActivity() != null)
            getSonosActivity().hideRoomGrouping();
    }

    public void onClick(View view)
    {
        if(view == selectAllButtonFrame && zonesList != null)
        {
            int i = 0;
            for(int j = 0; j < zonesList.getCheckedItemPositions().size(); j++)
            {
                int l = zonesList.getCheckedItemPositions().keyAt(j);
                if(zonesList.getCheckedItemPositions().get(l))
                    i++;
            }

            boolean flag = false;
            if(i == zonesList.getCount())
                flag = true;
            int k = 0;
            while(k < zonesList.getCount()) 
            {
                ListView listview = zonesList;
                boolean flag1;
                if(!flag)
                    flag1 = true;
                else
                    flag1 = false;
                listview.setItemChecked(k, flag1);
                k++;
            }
            updateSelectAllButton();
        }
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = layoutinflater.inflate(0x7f030061, viewgroup, false);
        zmMetaDataText1 = (TextView)view.findViewById(0x7f0a0142);
        zmMetaDataText2 = (TextView)view.findViewById(0x7f0a0144);
        albumArtBg = (RemoteImageView)view.findViewById(0x7f0a007f);
        zonesList = (ListView)view.findViewById(0x7f0a0147);
        zonesList.setOnItemClickListener(this);
        groupAlbumArt = (RemoteImageView)view.findViewById(0x7f0a0140);
        playIndicatorFirst = (PlayIndicatorView)view.findViewById(0x7f0a0143);
        playIndicatorFirst.setController(getSonosActivity().getHouseholdController().getPlayIndicatorController(groupingZoneGroup));
        playIndicatorSecond = (PlayIndicatorView)view.findViewById(0x7f0a0145);
        playIndicatorSecond.setController(getSonosActivity().getHouseholdController().getPlayIndicatorController(groupingZoneGroup));
        playIndicatorSecond.setVisibility(8);
        playIndicatorFirst.setVisibility(0);
        selectAllButton = (SonosTextView)view.findViewById(0x7f0a0149);
        selectAllButtonFrame = view.findViewById(0x7f0a0148);
        if(selectAllButtonFrame != null)
            selectAllButtonFrame.setOnClickListener(this);
        View view1 = view.findViewById(0x7f0a013d);
        view.findViewById(0x7f0a013c).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view2)
            {
                dismissFragment();
            }

            final RoomGroupingFragment this$0;

            
            {
                this$0 = RoomGroupingFragment.this;
                super();
            }
        }
);
        view1.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view2)
            {
                if(zonesList != null)
                    if(getCheckedCount() == 0)
                        showNoZoneSelectedWarning();
                    else
                        saveDevices();
            }

            final RoomGroupingFragment this$0;

            
            {
                this$0 = RoomGroupingFragment.this;
                super();
            }
        }
);
        return view;
    }

    public void onDestroyView()
    {
        zmMetaDataText1 = null;
        zmMetaDataText2 = null;
        if(selectAllButton != null)
            selectAllButton = null;
        if(selectAllButtonFrame != null)
        {
            selectAllButtonFrame.setOnClickListener(null);
            selectAllButtonFrame = null;
        }
        zonesList = null;
        groupAlbumArt = null;
        groupingZoneGroup = "";
        zonesAdapter = null;
        super.onDestroyView();
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(household == null || !StringUtils.isNotEmptyOrNull(groupingZoneGroup)) goto _L2; else goto _L1
_L1:
        ZoneGroup zonegroup = household.lookupZoneGroup(groupingZoneGroup);
        if(zonegroup != null) goto _L4; else goto _L3
_L3:
        dismissFragment();
_L2:
        return;
_L4:
        if(householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged && StringUtils.isNotEmptyOrNull(groupingZoneGroup) && !isSavingDevices)
            updateItems(new ArrayList(household.getDevices(com.sonos.sclib.SCIHousehold.DevFilterOpt.FLT_DEV_COMPATIBLE_AND_VISIBLE)), zonegroup);
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        updateSelectAllButton();
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent nowplayevent)
    {
        if(nowplaying != null && nowplayevent == com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent.OnMusicChanged && nowplaying.getZoneGroup().getID().equals(groupingZoneGroup))
            fillGroupingWithData(groupingZoneGroup);
    }

    public void onResume()
    {
        super.onResume();
        scrollFromLaunch = true;
        busyManager = null;
        isSavingDevices = false;
    }

    public void onSubscribeEventSinks()
    {
        super.onSubscribeEventSinks();
        HouseholdEventSink.getInstance().addListener(this);
        AllNowPlayingEventSink.getInstance().addListener(this);
    }

    public void onUnsubscribeEventSinks()
    {
        super.onUnsubscribeEventSinks();
        HouseholdEventSink.getInstance().removeListener(this);
        AllNowPlayingEventSink.getInstance().removeListener(this);
    }

    public void setGroupingId(String s)
    {
        groupingZoneGroup = s;
    }

    public void updateItems(List list, ZoneGroup zonegroup)
    {
        if(zonesList == null) goto _L2; else goto _L1
_L1:
        Collections.sort(list, new com.sonos.acr.sclib.wrappers.ZoneDevice.DeviceSortByTitleComparator());
        ArrayList arraylist = getCheckedDevices(list, zonegroup);
        zonesAdapter = new ArrayAdapter(themedContext, 0x7f030020, list);
        zonesList.setAdapter(zonesAdapter);
        zonesList.setSelector(0x7f08001d);
        fillGroupingWithData(groupingZoneGroup);
        for(int i = 0; i < list.size(); i++)
            if(arraylist.contains(((ZoneDevice)list.get(i)).getId()))
                zonesList.setItemChecked(i, true);

        updateSelectAllButton();
        if(!scrollFromLaunch) goto _L2; else goto _L3
_L3:
        int j;
        int k;
        j = -1;
        k = 0;
_L9:
        if(k >= zonesList.getCheckedItemPositions().size()) goto _L5; else goto _L4
_L4:
        int l = zonesList.getCheckedItemPositions().keyAt(k);
        if(!zonesList.getCheckedItemPositions().get(l)) goto _L7; else goto _L6
_L6:
        j = l;
        scrollFromLaunch = false;
_L5:
        if(j != -1)
            zonesList.setSelection(j);
_L2:
        return;
_L7:
        k++;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public static final String LOG_TAG = com/sonos/acr/zonemenu/RoomGroupingFragment.getSimpleName();
    RemoteImageView albumArtBg;
    private UiBusyManager busyManager;
    protected RemoteImageView groupAlbumArt;
    private String groupingZoneGroup;
    private boolean isSavingDevices;
    private PlayIndicatorView playIndicatorFirst;
    private PlayIndicatorView playIndicatorSecond;
    private boolean scrollFromLaunch;
    protected SonosTextView selectAllButton;
    protected View selectAllButtonFrame;
    protected TextView zmMetaDataText1;
    protected TextView zmMetaDataText2;
    private ArrayAdapter zonesAdapter;
    protected ListView zonesList;






/*
    static boolean access$302(RoomGroupingFragment roomgroupingfragment, boolean flag)
    {
        roomgroupingfragment.isSavingDevices = flag;
        return flag;
    }

*/
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.zonemenu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.SonosFragment;
import com.sonos.acr.nowplaying.SonosHomeActivity;
import com.sonos.acr.nowplaying.SwitcherPopupWindow;
import com.sonos.acr.sclib.sinks.AllNowPlayingEventSink;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.acr.uibusymanager.UiBusyManager;
import com.sonos.acr.util.*;
import com.sonos.acr.view.RemoteImageView;
import com.sonos.sclib.SCIZoneGroupMgr;
import java.util.*;

// Referenced classes of package com.sonos.acr.zonemenu:
//            RoomsMenuCell

public class RoomsMenuFragment extends SonosFragment
    implements com.sonos.acr.sclib.sinks.AllNowPlayingEventSink.AllNowPlayingEventListener, android.view.View.OnClickListener
{
    public class ZoneMenuAdapter extends BaseAdapter
        implements RoomsMenuCell.ActionListener
    {

        private int getIndex(RoomsMenuCell roomsmenucell)
        {
            int i;
            Iterator iterator;
            i = 0;
            if(zoneMenuAdapter == null)
                break MISSING_BLOCK_LABEL_70;
            iterator = zoneMenuAdapter.zoneGroupList.iterator();
_L3:
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_70;
            if(!((ZoneGroup)iterator.next()).getID().equals(roomsmenucell.zoneGroupId)) goto _L2; else goto _L1
_L1:
            int j = i;
_L4:
            return j;
_L2:
            i++;
              goto _L3
            j = -1;
              goto _L4
        }

        public void checkForAsynchronousErrors(NowPlaying nowplaying)
        {
            String s = nowplaying.getAsynchronousErrorString();
            if(s.length() > 0)
            {
                ZoneGroup zonegroup = nowplaying.getZoneGroup();
                if(zonegroup != null)
                    SonosToast.popupDialog(s, zonegroup.createZoneGroupTitle(3, false));
            }
        }

        public int getCount()
        {
            return zoneGroupList.size();
        }

        public Object getItem(int i)
        {
            return zoneGroupList.get(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            RoomsMenuCell roomsmenucell;
            ZoneGroup zonegroup;
            if(view instanceof RoomsMenuCell)
            {
                roomsmenucell = (RoomsMenuCell)view;
            } else
            {
                roomsmenucell = new RoomsMenuCell(getActivity());
                roomsmenucell.setActionListener(this);
            }
            zonegroup = (ZoneGroup)zoneGroupList.get(i);
            if(zonegroup != null)
            {
                roomsmenucell.setZoneGroup(zonegroup);
                roomsmenucell.setTag(zonegroup.getID());
                roomsmenucell.setCurrent(zonegroup.getID().equals(currentZoneGroupId));
                if(zonegroup.getID().equals(currentZoneGroupId))
                {
                    int j = getIndex(roomsmenucell);
                    if(j >= 0 && scrollFromLaunch)
                    {
                        zoneGroupListView.setSelection(j);
                        scrollFromLaunch = false;
                    }
                }
            }
            return roomsmenucell;
        }

        public boolean isAnyGroupPlaying()
        {
            Iterator iterator = zoneGroupList.iterator();
_L4:
            if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
            ZoneGroup zonegroup = (ZoneGroup)iterator.next();
            if(!zonegroup.nowPlaying.isPlaying() || !zonegroup.nowPlaying.isPlayPauseEnabled()) goto _L4; else goto _L3
_L3:
            boolean flag = true;
_L6:
            return flag;
_L2:
            flag = false;
            if(true) goto _L6; else goto _L5
_L5:
        }

        public void onCurrentZoneGroupChanged(Household household)
        {
            int i = -1;
            RoomsMenuCell roomsmenucell = (RoomsMenuCell)zoneGroupListView.findViewWithTag(currentZoneGroupId);
            if(roomsmenucell != null)
                roomsmenucell.setCurrent(false);
            ZoneGroup zonegroup = household.getCurrentZoneGroup();
            if(zonegroup != null)
            {
                currentZoneGroupId = zonegroup.getID();
                RoomsMenuCell roomsmenucell1 = (RoomsMenuCell)zoneGroupListView.findViewWithTag(currentZoneGroupId);
                if(roomsmenucell1 != null)
                {
                    roomsmenucell1.setCurrent(true);
                    i = getIndex(roomsmenucell1);
                }
            }
            if(i != -1 && scrollFromLaunch)
            {
                zoneGroupListView.setSelection(i);
                scrollFromLaunch = false;
            }
        }

        public void onMainButtonLongPress(RoomsMenuCell roomsmenucell)
        {
        }

        public void onMainButtonPress(RoomsMenuCell roomsmenucell)
        {
            String s = (String)roomsmenucell.getTag();
            String s1 = currentZoneGroupId;
            Iterator iterator = 
// JavaClassFileOutputException: get_constant: invalid tag

        public void onZoneGroupButtonPress(RoomsMenuCell roomsmenucell)
        {
            if((SonosHomeActivity)getActivity() != null && StringUtils.isNotEmptyOrNull(roomsmenucell.zoneGroupId))
                getSonosActivity().showRoomGrouping(roomsmenucell.zoneGroupId);
        }

        public void onZoneGroupsChanged(Household household)
        {
            com.sonos.sclib.SCIZoneGroupMgr.ZMState zmstate = household.getZoneGroupManager().getState();
            if(zmstate == com.sonos.sclib.SCIZoneGroupMgr.ZMState.ZM_STATE_NORMAL || zmstate == com.sonos.sclib.SCIZoneGroupMgr.ZMState.ZM_STATE_ALL_UNCONFIGURED)
            {
                zoneGroupList = household.getZoneGroups(com.sonos.sclib.SCIHousehold.ZGFilterOpt.FLT_ZG_ZONEMENU);
                ZoneGroup zonegroup = household.getCurrentZoneGroup();
                String s;
                if(zonegroup != null)
                    s = zonegroup.getID();
                else
                    s = null;
                currentZoneGroupId = s;
            } else
            {
                zoneGroupList = new ArrayList();
            }
            notifyDataSetChanged();
        }

        public void updateNowPlayingAndPauseAll(NowPlaying nowplaying)
        {
            if(zoneGroupListView != null)
            {
                RoomsMenuCell roomsmenucell = (RoomsMenuCell)zoneGroupListView.findViewWithTag(nowplaying.getZoneGroup().getID());
                if(roomsmenucell != null)
                    roomsmenucell.updateNowPlayingView(nowplaying);
            }
            updatePauseAllButton();
        }

        SonosActivity activity;
        String currentZoneGroupId;
        final RoomsMenuFragment this$0;
        private ArrayList zoneGroupList;


        public ZoneMenuAdapter(SonosActivity sonosactivity)
        {
            this$0 = RoomsMenuFragment.this;
            super();
            zoneGroupList = new ArrayList(0);
            activity = sonosactivity;
        }
    }

    private class PauseAllOnClick
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            (new android.app.AlertDialog.Builder(getActivity())).setIcon(0x1080027).setTitle(0x7f0c012d).setMessage(0x7f0c012e).setPositiveButton(0x1040013, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    Household household = LibraryUtils.getHousehold();
                    if(household != null)
                    {
                        com.sonos.sclib.SCIOp sciop = household.createPauseOp();
                        if(sciop == null)
                            SLog.e(RoomsMenuFragment.LOG_TAG, "pauseAllOp is null!");
                        else
                        if(getActivity() != null)
                            (new UiBusyManager(getActivity(), sciop, null)).start();
                        else
                            SLog.e(RoomsMenuFragment.LOG_TAG, "Activity is null, can't pause!");
                    } else
                    {
                        SLog.e(RoomsMenuFragment.LOG_TAG, "Household does not exist, can't pause!");
                    }
                }

                final PauseAllOnClick this$1;

                
                {
                    this$1 = PauseAllOnClick.this;
                    super();
                }
            }
).setNegativeButton(0x1040009, null).setCancelable(false).create().show();
        }

        final RoomsMenuFragment this$0;

        private PauseAllOnClick()
        {
            this$0 = RoomsMenuFragment.this;
            super();
        }

    }

    public static interface RoomsListener
        extends com.sonos.acr.SonosFragment.FragmentStateListener
    {

        public abstract void onZoneGroupSelected(String s, String s1);
    }


    public RoomsMenuFragment()
    {
        super(0x7f010057);
    }

    private int getCurrentZG()
    {
        int i;
        Iterator iterator;
        i = 0;
        if(zoneMenuAdapter == null)
            break MISSING_BLOCK_LABEL_64;
        iterator = zoneMenuAdapter.zoneGroupList.iterator();
_L3:
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_64;
        if(!((ZoneGroup)iterator.next()).getID().equals(zoneMenuAdapter.currentZoneGroupId)) goto _L2; else goto _L1
_L1:
        int j = i;
_L4:
        return j;
_L2:
        i++;
          goto _L3
        j = -1;
          goto _L4
    }

    private void updatePauseAllButton()
    {
        if(pauseAllButton != null)
        {
            View view = pauseAllButton;
            boolean flag;
            if(zoneMenuAdapter != null && zoneMenuAdapter.isAnyGroupPlaying())
                flag = true;
            else
                flag = false;
            view.setEnabled(flag);
        }
    }

    public void hideRoomsHolder()
    {
        android.view.animation.Animation animation = AnimationUtils.loadAnimation(getActivity(), 0x7f040002);
        roomsHolder.startAnimation(animation);
        roomsHolder.setVisibility(8);
    }

    public void onClick(View view)
    {
        if(view == albumArt)
            getSonosActivity().hideRooms();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        if(switcherPopupWindow != null)
            switcherPopupWindow.dismiss();
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = layoutinflater.inflate(0x7f030063, null);
        zoneGroupListView = (ListView)view.findViewById(0x7f0a015a);
        zoneMenuAdapter = new ZoneMenuAdapter(getSonosActivity());
        zoneGroupListView.setAdapter(zoneMenuAdapter);
        pauseAllButton = view.findViewById(0x7f0a015b);
        roomsHolder = view.findViewById(0x7f0a0159);
        pauseAllButton.setOnClickListener(new PauseAllOnClick());
        albumArtBg = (RemoteImageView)view.findViewById(0x7f0a007f);
        albumArt = (RemoteImageView)view.findViewById(0x7f0a002c);
        closeButton = (ImageView)view.findViewById(0x7f0a00cc);
        if(closeButton != null)
            closeButton.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view1)
                {
                    getSonosActivity().hideRooms();
                }

                final RoomsMenuFragment this$0;

            
            {
                this$0 = RoomsMenuFragment.this;
                super();
            }
            }
);
        if(albumArt != null)
            albumArt.setOnClickListener(this);
        return view;
    }

    public void onDestroyView()
    {
        zoneGroupListView.setAdapter(null);
        zoneGroupListView = null;
        pauseAllButton.setOnClickListener(null);
        pauseAllButton = null;
        zoneMenuAdapter = null;
        albumArt = null;
        super.onDestroyView();
    }

    public void onHiddenChanged(boolean flag)
    {
        super.onHiddenChanged(flag);
        if(!flag)
        {
            scrollFromLaunch = true;
            if(getCurrentZG() >= 0)
                zoneGroupListView.setSelection(getCurrentZG());
            roomsHolder.requestFocus();
        }
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnZoneGroupChanged)
            zoneMenuAdapter.onZoneGroupsChanged(household);
        if(householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnCurrentZoneGroupChanged)
            zoneMenuAdapter.onCurrentZoneGroupChanged(household);
    }

    public void onNowPlayingEvent(NowPlaying nowplaying, com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent nowplayevent)
    {
        if(nowplayevent == com.sonos.acr.sclib.sinks.NowPlayingEventSink.NowPlayEvent.OnMusicChanged)
        {
            zoneMenuAdapter.updateNowPlayingAndPauseAll(nowplaying);
            zoneMenuAdapter.checkForAsynchronousErrors(nowplaying);
        }
    }

    public void onStart()
    {
        super.onStart();
        AllNowPlayingEventSink.getInstance().addListener(this);
        updatePauseAllButton();
    }

    public void onStop()
    {
        super.onStop();
        AllNowPlayingEventSink.getInstance().removeListener(this);
    }

    public void setDropdownSwitcherButtonVisibility(int i)
    {
        if(dropdownButton != null)
            dropdownButton.setVisibility(i);
    }

    public void showRoomsHolder()
    {
        roomsHolder.setVisibility(0);
        android.view.animation.Animation animation = AnimationUtils.loadAnimation(getActivity(), 0x7f040001);
        roomsHolder.startAnimation(animation);
    }

    protected void updateAlbumArtFromNowPlaying(NowPlaying nowplaying)
    {
        if(albumArt != null)
            albumArt.setImageFromNowPlaying(nowplaying);
    }

    private static final String LOG_TAG = com/sonos/acr/zonemenu/RoomsMenuFragment.getSimpleName();
    protected RemoteImageView albumArt;
    RemoteImageView albumArtBg;
    private ImageView closeButton;
    protected ImageButton dropdownButton;
    private View pauseAllButton;
    private View roomsHolder;
    private boolean scrollFromLaunch;
    protected SwitcherPopupWindow switcherPopupWindow;
    private ListView zoneGroupListView;
    private ZoneMenuAdapter zoneMenuAdapter;





/*
    static boolean access$302(RoomsMenuFragment roomsmenufragment, boolean flag)
    {
        roomsmenufragment.scrollFromLaunch = flag;
        return flag;
    }

*/




}

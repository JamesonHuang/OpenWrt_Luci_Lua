// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.queue;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.*;
import android.view.*;
import android.widget.*;
import com.sonos.acr.browse.v2.PageFragment;
import com.sonos.acr.nowplaying.SonosHomeActivity;
import com.sonos.acr.sclib.wrappers.Playlist;
import com.sonos.acr.util.*;
import com.sonos.acr.view.SonosEditText;
import com.sonos.acr.view.SonosImageView;
import com.sonos.sclib.*;
import java.util.ArrayList;

public class SaveQueueFragment extends PageFragment
    implements TextWatcher
{
    class SonosPlaylistCB extends SCIGetSonosPlaylistsCBSwigBase
    {

        public void getSonosPlaylistsFailed()
        {
            SLog.e(SaveQueueFragment.LOG_TAG, "Get Sonos Playlist failed...");
            browseList.setVisibility(8);
            playlistLabel.setVisibility(8);
            newNameText.requestFocus();
        }

        public void getSonosPlaylistsSucceeded(String s)
        {
            if(playQueueMgr != null)
            {
                SLog.d(SaveQueueFragment.LOG_TAG, "Get Sonos Playlist succeeded!!!");
                com.sonos.sclib.SCIEnumerator scienumerator = playQueueMgr.getSonosPlaylistsEnum();
                if(newNameText != null && StringUtils.isEmptyOrNull(newNameText.getText()))
                {
                    newNameText.setText(s);
                    newNameText.selectAll();
                }
                if(getActivity() == null)
                {
                    SLog.e(SaveQueueFragment.LOG_TAG, "Get Sonos Playlist succeeded, but we could not make an adapter...");
                } else
                {
                    playlists = LibraryUtils.toList(scienumerator, com/sonos/sclib/SCISonosPlaylist, com/sonos/acr/sclib/wrappers/Playlist);
                    ArrayAdapter arrayadapter = new ArrayAdapter(, 0x7f030064, playlists);
                    browseList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                        public void onItemClick(AdapterView adapterview, View view, int i, long l)
                        {
                            String s1 = ((Playlist)playlists.get(i)).getTitle();
                            newNameText.setText(s1);
                            newNameText.setSelection(s1.length());
                        }

                        final SonosPlaylistCB this$1;

                
                {
                    this$1 = SonosPlaylistCB.this;
                    Object();
                }
                    }
);
                    browseList.setAdapter(arrayadapter);
                    if(arrayadapter.getCount() > 0)
                    {
                        browseList.setVisibility(0);
                        playlistLabel.setVisibility(0);
                    } else
                    {
                        browseList.setVisibility(8);
                        playlistLabel.setVisibility(8);
                        newNameText.requestFocus();
                    }
                }
                if(newNameText != null)
                    reevaluateText(newNameText.getText());
            }
        }

        final SaveQueueFragment this$0;

        SonosPlaylistCB()
        {
            this$0 = SaveQueueFragment.this;
            SCIGetSonosPlaylistsCBSwigBase();
        }
    }


    public SaveQueueFragment(SCIPlayQueueMgr sciplayqueuemgr)
    {
        super(0x7f010056);
        sonosPlaylistCallback = new SonosPlaylistCB();
        playQueueMgr = sciplayqueuemgr;
    }

    private Playlist findPlaylistByName(String s)
    {
        int i = findPlaylistIndex(s);
        Playlist playlist;
        if(i >= 0)
            playlist = (Playlist)playlists.get(i);
        else
            playlist = new Playlist(s);
        return playlist;
    }

    private int findPlaylistIndex(String s)
    {
        int i;
        if(playlists == null)
            break MISSING_BLOCK_LABEL_49;
        i = 0;
_L3:
        if(i >= playlists.size())
            break MISSING_BLOCK_LABEL_49;
        if(!((Playlist)playlists.get(i)).getTitle().equals(s)) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        i++;
          goto _L3
        i = -1;
          goto _L1
    }

    private void finish()
    {
        playQueueMgr = null;
        invalidatePage();
    }

    private void performSave(Playlist playlist)
    {
        if(playQueueMgr != null)
        {
            playQueueMgr.createSaveToSonosPlaylistOp(playlist.getTitle(), playlist.getID())._start(((SonosHomeActivity)getSonosActivity()).getSaveCallBack());
            finish();
        }
    }

    private void reevaluateText(CharSequence charsequence)
    {
        boolean flag = true;
        int i = findPlaylistIndex(charsequence.toString());
        SonosImageView sonosimageview;
        if(i >= 0)
            browseList.setItemChecked(i, flag);
        else
            browseList.setItemChecked(browseList.getCheckedItemPosition(), false);
        sonosimageview = doneButton;
        if(charsequence.length() <= 0)
            flag = false;
        sonosimageview.setEnabled(flag);
    }

    private void validateSave(final Playlist newPlaylist)
    {
        if(newPlaylist.exists())
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
            String s = getResources().getString(0x7f0c00b4);
            Object aobj[] = new Object[1];
            aobj[0] = newPlaylist.getTitle();
            builder.setMessage(String.format(s, aobj)).setTitle(0x7f0c00b3).setIcon(0x1080027).setPositiveButton(0x7f0c00aa, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    performSave(newPlaylist);
                }

                final SaveQueueFragment this$0;
                final Playlist val$newPlaylist;

            
            {
                this$0 = SaveQueueFragment.this;
                newPlaylist = playlist;
                Object();
            }
            }
).setNegativeButton(0x7f0c0037, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

                final SaveQueueFragment this$0;

            
            {
                this$0 = SaveQueueFragment.this;
                Object();
            }
            }
).create().show();
        } else
        {
            performSave(newPlaylist);
        }
    }

    public void afterTextChanged(Editable editable)
    {
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public boolean isGone()
    {
        boolean flag;
        if(playQueueMgr == null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onActiveChanged(boolean flag)
    {
        super.onActiveChanged(flag);
        if(flag && DisplayController.getScreenType() == 0)
            getActivity().getWindow().setSoftInputMode(16);
        else
            getActivity().getWindow().setSoftInputMode(32);
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = layoutinflater.inflate(0x7f030065, null);
        newNameText = (SonosEditText)view.findViewById(0x7f0a015e);
        browseList = (ListView)view.findViewById(0x7f0a0031);
        playlistLabel = (TextView)view.findViewById(0x7f0a015f);
        InputFilter ainputfilter[] = new InputFilter[1];
        ainputfilter[0] = new android.text.InputFilter.LengthFilter(getLibrary().getSonosPlaylistMaxUTF8Length());
        newNameText.setFilters(ainputfilter);
        newNameText.addTextChangedListener(this);
        doneButton = (SonosImageView)view.findViewById(0x7f0a0099);
        doneButton.setEnabled(false);
        view.findViewById(0x7f0a0098).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                finish();
            }

            final SaveQueueFragment this$0;

            
            {
                this$0 = SaveQueueFragment.this;
                Object();
            }
        }
);
        doneButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                String s = newNameText.getText().toString();
                if(StringUtils.isNotEmptyOrNull(s))
                    validateSave(findPlaylistByName(s));
                else
                    SonosToast.popupDialog(0x7f0c00b5);
            }

            final SaveQueueFragment this$0;

            
            {
                this$0 = SaveQueueFragment.this;
                Object();
            }
        }
);
        return view;
    }

    public void onDestroyView()
    {
        if(newNameText != null)
            newNameText.removeTextChangedListener(this);
        super.onDestroyView();
    }

    public void onHiddenChanged(boolean flag)
    {
        super.onHiddenChanged(flag);
        if(flag)
            newNameText.hideKeyboard();
    }

    public void onStart()
    {
        super.onStart();
        playQueueMgr.getSonosPlaylists(sonosPlaylistCallback);
        newNameText.requestFocus();
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        reevaluateText(charsequence);
    }

    private static final String LOG_TAG = com/sonos/acr/browse/v2/queue/SaveQueueFragment.getSimpleName();
    private ListView browseList;
    private SonosImageView doneButton;
    private SonosEditText newNameText;
    SCIPlayQueueMgr playQueueMgr;
    private TextView playlistLabel;
    private ArrayList playlists;
    private SonosPlaylistCB sonosPlaylistCallback;













/*
    static ArrayList access$802(SaveQueueFragment savequeuefragment, ArrayList arraylist)
    {
        savequeuefragment.playlists = arraylist;
        return arraylist;
    }

*/

}

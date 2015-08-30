// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.pages;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.*;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.browse.v2.PageFragment;
import com.sonos.acr.browse.v2.actions.*;
import com.sonos.acr.browse.v2.actions.sclib.SCLibActionItem;
import com.sonos.acr.browse.v2.view.BrowseItemCell;
import com.sonos.acr.infoview.InfoviewHeaderDataSourceEventSink;
import com.sonos.acr.nowplaying.SonosHomeActivity;
import com.sonos.acr.sclib.wrappers.NowPlaying;
import com.sonos.acr.uibusymanager.ActionUiBusyManager;
import com.sonos.acr.util.*;
import com.sonos.acr.view.SonosImageView;
import com.sonos.sclib.*;
import java.util.ArrayList;

// Referenced classes of package com.sonos.acr.browse.v2.pages:
//            ActionListPageFragment, ActionDataSourceListPageFragment, ActionPopupFragment

public class ActionDialogFragment extends DialogFragment
    implements ActionListPageFragment.OnActionClickListener, com.sonos.acr.browse.v2.adapters.IDataSourceHandler.OnItemClickListener
{
    private class MoreMenuActionItem extends SimpleActionItem
    {

        public void perform()
        {
            int i = getView().findViewById(0x7f0a002f).getWidth();
            int j = getView().findViewById(0x7f0a002f).getHeight();
            getChildFragmentManager().beginTransaction().replace(0x7f0a002f, createActionFragment(actionData, i, j)).commit();
        }

        ActionData actionData;
        final ActionDialogFragment this$0;

        public MoreMenuActionItem(String s, ActionData actiondata)
        {
            this$0 = ActionDialogFragment.this;
            SimpleActionItem(s);
            actionData = actiondata;
        }
    }

    public static interface ISonosDialogActionPerformedListener
    {

        public abstract void onActionPerformed(SCIBrowseItem scibrowseitem, ActionItem actionitem);
    }

    public static interface ISonosDialogDismissListener
    {

        public abstract void onSonosDialogDismissed();
    }


    public ActionDialogFragment(ActionData actiondata)
    {
        albumArtSerialNumber = sclib.SCOP_INVALID_SERIALNUM;
        infoviewHeaderEventSink = null;
        headerSubscribed = false;
        actionData = actiondata;
        setExtendedData();
    }

    public ActionDialogFragment(ActionData actiondata, boolean flag)
    {
        albumArtSerialNumber = sclib.SCOP_INVALID_SERIALNUM;
        infoviewHeaderEventSink = null;
        headerSubscribed = false;
        actionData = actiondata;
        if(!flag)
            setExtendedData();
    }

    public ActionDialogFragment(NowPlaying nowplaying)
    {
        albumArtSerialNumber = sclib.SCOP_INVALID_SERIALNUM;
        infoviewHeaderEventSink = null;
        headerSubscribed = false;
        actionData = new NowPlayingActionData(nowplaying);
        setExtendedData();
    }

    public ActionDialogFragment(SCIBrowseItem scibrowseitem, SCIActionFilter sciactionfilter)
    {
        albumArtSerialNumber = sclib.SCOP_INVALID_SERIALNUM;
        infoviewHeaderEventSink = null;
        headerSubscribed = false;
        actionData = new BrowseItemActionData(scibrowseitem, sciactionfilter);
        setExtendedData();
    }

    private PageFragment createActionFragment(ActionData actiondata, int i, int j)
    {
        Object obj;
        if(actiondata instanceof ActionSet)
        {
            obj = new ActionListPageFragment((ActionSet)actiondata);
            ((ActionListPageFragment) (obj)).setClickListener(this);
        } else
        if(actiondata instanceof DataSourceActionMenuData)
        {
            ActionDataSourceListPageFragment actiondatasourcelistpagefragment = new ActionDataSourceListPageFragment(((DataSourceActionMenuData)actiondata).getDataSource());
            actiondatasourcelistpagefragment.setStartSize(i, j);
            boolean flag;
            if(dialogHeader != null)
                flag = true;
            else
                flag = false;
            actiondatasourcelistpagefragment.setHasHeader(flag);
            actiondatasourcelistpagefragment.setOnItemClickListener(this);
            obj = actiondatasourcelistpagefragment;
        } else
        {
            obj = null;
        }
        return ((PageFragment) (obj));
    }

    private void updateAlbumArt()
    {
        if(albumArt != null && albumArtFrame != null)
        {
            com.sonos.sclib.SCIBrowseItem.SCAlbumArtType scalbumarttype = actionData.getActionImageType();
            if(scalbumarttype != null && scalbumarttype != com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.ART_NONE)
            {
                albumArt.setVisibility(0);
                albumArtFrame.setVisibility(0);
                albumArtController.setImageURI(actionData.getActionImageUrl(), scalbumarttype);
                primaryText.setGravity(3);
                android.view.ViewGroup.MarginLayoutParams marginlayoutparams1 = (android.view.ViewGroup.MarginLayoutParams)primaryText.getLayoutParams();
                marginlayoutparams1.setMargins(0, 0, getActivity().getResources().getDimensionPixelSize(0x7f09001e), 0);
                primaryText.setLayoutParams(marginlayoutparams1);
            } else
            {
                albumArt.setVisibility(8);
                albumArtFrame.setVisibility(8);
                primaryText.setGravity(1);
                android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)primaryText.getLayoutParams();
                marginlayoutparams.setMargins(0, 0, 0, 0);
                primaryText.setLayoutParams(marginlayoutparams);
            }
        }
    }

    protected PageFragment createActionFragment(ActionData actiondata)
    {
        Object obj;
        if(actiondata instanceof ActionSet)
        {
            obj = new ActionListPageFragment((ActionSet)actiondata);
            ((ActionListPageFragment) (obj)).setClickListener(this);
        } else
        if(actiondata instanceof DataSourceActionMenuData)
        {
            headerDS = (SCIInfoViewHeaderDataSource)LibraryUtils.cast(((DataSourceActionMenuData)actiondata).getDataSource(), com/sonos/sclib/SCIInfoViewHeaderDataSource);
            infoviewHeaderEventSink = new InfoviewHeaderDataSourceEventSink() {

                public void onInfoviewHeaderChanged(SCIInfoViewHeaderDataSource sciinfoviewheaderdatasource)
                {
                    sciinfoviewheaderdatasource.unsubscribe(infoviewHeaderEventSink);
                    headerSubscribed = false;
                    String s = "";
                    String s1 = "";
                    if(sciinfoviewheaderdatasource.getNumItems() > 0L)
                        s = sciinfoviewheaderdatasource.getItemAtIndex(0L).getValue();
                    if(sciinfoviewheaderdatasource.getNumItems() > 1L)
                        s1 = sciinfoviewheaderdatasource.getItemAtIndex(1L).getValue();
                    DataSourceActionMenuData datasourceactionmenudata = new DataSourceActionMenuData(((DataSourceActionMenuData)actionData).getDataSource(), s, s1, sciinfoviewheaderdatasource.getAlbumArtURL(AlbumArtSize.SIZE_LARGE_BROWSE.getPixelWidth()), sciinfoviewheaderdatasource.getAlbumArtType());
                    actionData = datasourceactionmenudata;
                    updateHeaderView();
                    updateAlbumArt();
                }

                final ActionDialogFragment this$0;

            
            {
                this$0 = ActionDialogFragment.this;
                InfoviewHeaderDataSourceEventSink();
            }
            }
;
            headerSubscribed = true;
            headerDS.subscribe(infoviewHeaderEventSink);
            ActionDataSourceListPageFragment actiondatasourcelistpagefragment = new ActionDataSourceListPageFragment(((DataSourceActionMenuData)actiondata).getDataSource());
            if(getResources() != null)
                actiondatasourcelistpagefragment.setStartSize(getResources().getDimensionPixelSize(0x7f090050), getResources().getDimensionPixelSize(0x7f09004f));
            boolean flag;
            if(dialogHeader != null)
                flag = true;
            else
                flag = false;
            actiondatasourcelistpagefragment.setHasHeader(flag);
            actiondatasourcelistpagefragment.setOnItemClickListener(this);
            obj = actiondatasourcelistpagefragment;
        } else
        {
            obj = null;
        }
        return ((PageFragment) (obj));
    }

    protected int getLayoutId()
    {
        return 0x7f030000;
    }

    public void onActionClick(ActionItem actionitem)
    {
        String s = actionitem.getActionID();
        if(s.equals(sclib.SC_ACTIONID_ACCOUNT_PICKER) && (actionitem instanceof SCLibActionItem))
        {
            SCLibActionItem sclibactionitem = (SCLibActionItem)actionitem;
            (new ActionUiBusyManager(getActivity(), sclibactionitem.getActionContext())).start();
        } else
        {
            actionitem.perform();
        }
        if(!(actionitem instanceof MoreMenuActionItem))
            if(listener != null)
                listener.onSonosDialogDismissed();
            else
                dismiss();
        if((s.equals(sclib.SC_ACTIONID_PANDORA_CREATE_STATION) || s.equals(sclib.SC_ACTIONID_PANDORA_CREATEPLAY_STATION)) && (getActivity() instanceof SonosHomeActivity))
            ((SonosHomeActivity)getActivity()).closeSearch();
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        getChildFragmentManager().beginTransaction().add(0x7f0a002f, createActionFragment(actionData)).commit();
    }

    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        InputMethodManager inputmethodmanager = (InputMethodManager)getActivity().getSystemService("input_method");
        if(inputmethodmanager.isAcceptingText())
            inputmethodmanager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.getWindow().setLayout(getResources().getDimensionPixelSize(0x7f090051), -2);
        return dialog;
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        mainView = layoutinflater.inflate(getLayoutId(), viewgroup, false);
        if(android.os.Build.VERSION.SDK_INT >= 11)
        {
            LayoutTransition layouttransition = new LayoutTransition();
            layouttransition.setDuration(100L);
            if(android.os.Build.VERSION.SDK_INT > 15)
                layouttransition.enableTransitionType(4);
            ((ViewGroup)mainView).setLayoutTransition(layouttransition);
        }
        dialogHeader = (ViewGroup)mainView.findViewById(0x7f0a002a);
        primaryText = (TextView)mainView.findViewById(0x7f0a002d);
        secondaryText = (TextView)mainView.findViewById(0x7f0a002e);
        albumArt = (SonosImageView)mainView.findViewById(0x7f0a002c);
        albumArtFrame = (FrameLayout)mainView.findViewById(0x7f0a002b);
        if(albumArt != null)
        {
            albumArtController = new ImageViewAlbumArtController(AlbumArtSize.SIZE_BROWSE, albumArt);
            albumArtController.setDefaultResourceId(0x7f06001b);
        }
        return mainView;
    }

    public void onDetach()
    {
        super.onDetach();
        if(albumArtController != null)
            albumArtController.cancelDownload();
    }

    public void onDismiss(DialogInterface dialoginterface)
    {
        if(headerDS != null && headerSubscribed)
        {
            headerSubscribed = false;
            headerDS.unsubscribe(infoviewHeaderEventSink);
        }
        super.onDismiss(dialoginterface);
    }

    public void onItemClick(BrowseItemCell browseitemcell)
    {
        SCIBrowseItem scibrowseitem = browseitemcell.getBrowseItem();
        ActionItem actionitem = null;
        if(scibrowseitem != null && (scibrowseitem.canActOn() || scibrowseitem.canPush()))
        {
            actionitem = (ActionItem)(new ActionSet(scibrowseitem.getActions())).getActions().get(0);
            FragmentActivity fragmentactivity = getActivity();
            if((fragmentactivity instanceof SonosHomeActivity) && ((SonosHomeActivity)fragmentactivity).isQueueVisible() && (actionitem instanceof SCLibActionItem))
            {
                ((SCLibActionItem)actionitem).clearPropertyBag();
                ((SCLibActionItem)actionitem).addStringToPropertyBag("com.sonos.acr.util.backNavigationRoutingKey", com.sonos.acr.util.NavigationUtils.BackNavigationRouting.BACKS_TO_QUEUE.toString());
            }
            actionitem.perform();
        }
        if(actionListener != null)
            actionListener.onActionPerformed(scibrowseitem, actionitem);
        if(listener != null)
            listener.onSonosDialogDismissed();
        else
            dismiss();
    }

    public boolean onItemLongClick(BrowseItemCell browseitemcell)
    {
        return false;
    }

    public void onResume()
    {
        super.onResume();
        updateHeaderView();
        updateAlbumArt();
    }

    public void onStop()
    {
        super.onStop();
        if(getShowsDialog())
            dismissAllowingStateLoss();
    }

    public void setExtendedData()
    {
        if(actionData instanceof ActionSet)
        {
            ActionSet actionset = (ActionSet)actionData;
            ActionData actiondata = actionData.getExtendedActionData();
            if(actiondata != null)
                actionset.addActionItem(new MoreMenuActionItem(SonosApplication.getInstance().getResources().getString(0x7f0c001e), actiondata));
        }
    }

    public void setOnActionPerformedListener(ISonosDialogActionPerformedListener isonosdialogactionperformedlistener)
    {
        actionListener = isonosdialogactionperformedlistener;
    }

    public void setOnDismissListener(ISonosDialogDismissListener isonosdialogdismisslistener)
    {
        listener = isonosdialogdismisslistener;
    }

    public void show(FragmentManager fragmentmanager, String s, View view)
    {
        show(fragmentmanager, s, view, 83);
    }

    public void show(FragmentManager fragmentmanager, String s, View view, int i)
    {
        show(fragmentmanager, s, view, i, false, null);
    }

    public void show(FragmentManager fragmentmanager, String s, View view, int i, boolean flag, View view1)
    {
        if(view == null)
        {
            show(fragmentmanager, s);
        } else
        {
            ActionPopupFragment actionpopupfragment = new ActionPopupFragment(this);
            actionpopupfragment.setAnimateResize(true);
            actionpopupfragment.setGravity(i);
            actionpopupfragment.setCenterInsideAnchor(flag);
            actionpopupfragment.setSelectedView(view1);
            actionpopupfragment.show(fragmentmanager, s, view);
        }
    }

    protected void updateHeaderView()
    {
        if(primaryText != null && secondaryText != null)
        {
            String s = actionData.getPrimaryText();
            String s1 = actionData.getSecondaryText();
            if(StringUtils.isNotEmptyOrNull(s))
            {
                primaryText.setText(s);
                primaryText.setVisibility(0);
            } else
            {
                primaryText.setVisibility(8);
            }
            if(StringUtils.isNotEmptyOrNull(s1))
            {
                secondaryText.setText(s1);
                secondaryText.setVisibility(0);
            } else
            {
                secondaryText.setVisibility(8);
            }
        }
    }

    private static final String LOG_TAG = com/sonos/acr/browse/v2/pages/ActionDialogFragment.getSimpleName();
    ActionData actionData;
    ISonosDialogActionPerformedListener actionListener;
    protected SonosImageView albumArt;
    protected ImageViewAlbumArtController albumArtController;
    protected FrameLayout albumArtFrame;
    protected long albumArtSerialNumber;
    protected ViewGroup dialogHeader;
    SCIInfoViewHeaderDataSource headerDS;
    private boolean headerSubscribed;
    private InfoviewHeaderDataSourceEventSink infoviewHeaderEventSink;
    ISonosDialogDismissListener listener;
    protected View mainView;
    protected TextView primaryText;
    protected TextView secondaryText;




/*
    static boolean access$102(ActionDialogFragment actiondialogfragment, boolean flag)
    {
        actiondialogfragment.headerSubscribed = flag;
        return flag;
    }

*/


}

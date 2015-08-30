// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.browse.v2.queue;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.*;
import android.view.animation.Animation;
import android.widget.*;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.browse.v2.PageFragment;
import com.sonos.acr.browse.v2.actions.*;
import com.sonos.acr.browse.v2.adapters.IDataSourceAdapter;
import com.sonos.acr.browse.v2.pages.ActionDialogFragment;
import com.sonos.acr.browse.v2.pages.DataSourceEditListPageFragment;
import com.sonos.acr.browse.v2.view.*;
import com.sonos.acr.nowplaying.controllers.*;
import com.sonos.acr.sclib.sinks.HouseholdEventSink;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.sclib.wrappers.ZoneGroup;
import com.sonos.acr.util.*;
import com.sonos.acr.view.SonosTextView;
import com.sonos.sclib.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.browse.v2.queue:
//            SaveQueueFragment, QueueListItemCell, QueueAdapter

public class QueueFragment extends DataSourceEditListPageFragment
    implements com.sonos.acr.browse.v2.adapters.IDataSourceHandler.OnItemClickListener, android.widget.AbsListView.OnScrollListener, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdListener, com.sonos.acr.browse.v2.pages.ActionDialogFragment.ISonosDialogActionPerformedListener, com.sonos.acr.nowplaying.controllers.TransportViewController.ShuffleButtonClickListener
{
    public static interface QueueInUseListener
    {

        public abstract void onQueueInUseChanged(boolean flag);
    }

    class QueueActionSet extends ActionSet
        implements ActionData
    {

        public com.sonos.sclib.SCIBrowseItem.SCAlbumArtType getActionImageType()
        {
            return null;
        }

        public String getActionImageUrl()
        {
            return null;
        }

        public ActionData getExtendedActionData()
        {
            return null;
        }

        public String getPrimaryText()
        {
            return null;
        }

        public String getSecondaryText()
        {
            return null;
        }

        public void setEnabled(boolean flag)
        {
            Iterator iterator = getActions().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                ActionItem actionitem = (ActionItem)iterator.next();
                if(actionitem instanceof SimpleActionItem)
                    ((SimpleActionItem)actionitem).setEnabled(flag);
            } while(true);
        }

        final QueueFragment this$0;

        QueueActionSet()
        {
            this$0 = QueueFragment.this;
            super();
            addActionItem(new SimpleActionItem(QueueFragment.this) {

                public void perform()
                {
                    setInEditMode(true);
                }

                final QueueActionSet this$1;
                final QueueFragment val$this$0;

                
                {
                    this$1 = QueueActionSet.this;
                    this$0 = queuefragment;
                    super(final_s);
                }
            }
);
            addActionItem(new SimpleActionItem(QueueFragment.this) {

                public void perform()
                {
                    clearQueue();
                }

                final QueueActionSet this$1;
                final QueueFragment val$this$0;

                
                {
                    this$1 = QueueActionSet.this;
                    this$0 = queuefragment;
                    super(final_s);
                }
            }
);
            addActionItem(new SimpleActionItem(QueueFragment.this) {

                public void perform()
                {
                    showSaveQueue();
                }

                final QueueActionSet this$1;
                final QueueFragment val$this$0;

                
                {
                    this$1 = QueueActionSet.this;
                    this$0 = queuefragment;
                    super(final_s);
                }
            }
);
        }
    }


    public QueueFragment()
    {
        lastScrollItemPosition = -1;
        lastScrollOffset = 0;
        playQueueEventSink = new PlayQueueEventSink();
        queueInUse = false;
        queueInUseListener = null;
        idleTimer = new SonosTimer();
    }

    private void clearQueue()
    {
        android.app.AlertDialog.Builder builder = new Builder(getThemedContext());
        builder.setIcon(0x1080027);
        builder.setTitle(0x7f0c003b);
        builder.setMessage(0x7f0c003a);
        builder.setPositiveButton(0x7f0c0039, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                if(playQueueMgr != null)
                {
                    com.sonos.sclib.SCIOpGenericUpdateQueue sciopgenericupdatequeue = playQueueMgr.createRemoveAllItemsOp(0L);
                    if(sciopgenericupdatequeue != null)
                        sciopgenericupdatequeue._start(new SCIOpCBSwigBase() {

                            public void _operationComplete(long l, int j)
                            {
                                dismissQueue();
                            }

                            final _cls3 this$1;

                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                        }
);
                }
            }

            final QueueFragment this$0;

            
            {
                this$0 = QueueFragment.this;
                super();
            }
        }
);
        builder.setNegativeButton(0x1040000, null);
        builder.create().show();
    }

    private boolean isXLarge()
    {
        boolean flag;
        if(DisplayController.getScreenType() == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void queueInUseChanged(boolean flag)
    {
        if(queueInUseListener != null && queueInUse != flag)
        {
            queueInUse = flag;
            queueInUseListener.onQueueInUseChanged(flag);
        }
    }

    private void resetLastScrollPos()
    {
        lastScrollItemPosition = -1;
        lastScrollOffset = 0;
    }

    private void unsubscribePlayQueue()
    {
        if(playQueue != null)
        {
            playQueue.unsubscribe(playQueueEventSink);
            playQueue = null;
            playQueueMgr = null;
        }
        setBrowseDataSource(null);
    }

    private boolean updatePlayQueueDataSource()
    {
        SCIBrowseDataSource scibrowsedatasource = playQueue.createDataSource();
        if(scibrowsedatasource == null) goto _L2; else goto _L1
_L1:
        playQueueMgr = (SCIPlayQueueMgr)LibraryUtils.cast(scibrowsedatasource, com/sonos/sclib/SCIPlayQueueMgr);
        if(playQueueMgr == null) goto _L2; else goto _L3
_L3:
        boolean flag;
        playQueue.subscribe(playQueueEventSink);
        setBrowseDataSource(scibrowsedatasource);
        flag = true;
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void updateQueueTitle(SCIBrowseDataSource scibrowsedatasource, SCIPlayQueueMgr sciplayqueuemgr)
    {
        byte byte0 = 0;
        if(queueTitle != null)
            if(isInEditMode())
            {
                queueTitle.setText(getResources().getString(0x7f0c0047));
            } else
            {
                SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
                spannablestringbuilder.append(new SpannableString(getResources().getString(0x7f0c00a6)));
                if(scibrowsedatasource != null)
                {
                    SpannableString spannablestring;
                    if(sciplayqueuemgr.isInUse())
                        spannablestring = new SpannableString((new StringBuilder()).append(" ").append(sciplayqueuemgr.getTrackCountTitle()).toString());
                    else
                        spannablestring = new SpannableString(getResources().getString(0x7f0c00a5));
                    spannablestring.setSpan(new ForegroundColorSpan(getResources().getColor(0x7f080071)), 0, spannablestring.length(), 0);
                    spannablestringbuilder.append(spannablestring);
                }
                ((TextView)queueTitle.getNextView()).setText(spannablestringbuilder, android.widget.TextView.BufferType.SPANNABLE);
                queueTitle.showNext();
            }
        if(queueNotInUseTextView != null && sciplayqueuemgr != null)
        {
            SonosTextView sonostextview = queueNotInUseTextView;
            if(sciplayqueuemgr.isInUse())
                byte0 = 8;
            sonostextview.setVisibility(byte0);
        }
    }

    public void close()
    {
        if(saveQueueFragment != null)
        {
            saveQueueFragment.onBackPressed();
            dismissSaveQueue();
        }
        if(isInEditMode())
            setInEditMode(false);
    }

    public BrowseItemCell createCellView(int i)
    {
        QueueListItemCell queuelistitemcell = new QueueListItemCell(themedContext);
        queuelistitemcell.setPlayViewIndicatorController(getSonosActivity().getHouseholdController().getCurrentPlayIndicatorController());
        return queuelistitemcell;
    }

    protected IDataSourceAdapter createDataSourceAdapter(SCIBrowseDataSource scibrowsedatasource, Context context)
    {
        return new QueueAdapter(context) ;
    }

    public void dismissQueue()
    {
        getSonosActivity().hideQueue();
        invalidatePage();
    }

    protected void dismissSaveQueue()
    {
        if(saveQueueFragment != null)
        {
            getChildFragmentManager().popBackStack();
            saveQueueFragment = null;
        }
    }

    public ActionSet getDataSourceActions()
    {
        Object obj;
        if(!isInEditMode())
        {
            QueueActionSet queueactionset = queueActions;
            boolean flag;
            if(dataSourceAdapter != null && dataSourceAdapter.getCount() > 0)
                flag = true;
            else
                flag = false;
            queueactionset.setEnabled(flag);
            obj = queueActions;
        } else
        {
            obj = getDataSourceActions();
        }
        return ((ActionSet) (obj));
    }

    protected int getLayoutId()
    {
        return 0x7f03005f;
    }

    public SCIPlayQueueMgr getPlayQueueManager()
    {
        return playQueueMgr;
    }

    protected boolean isCurrentObstructed(int i)
    {
        boolean flag;
        int j;
        flag = true;
        j = adapterListView.getFirstVisiblePosition();
        if(j < i && adapterListView.getLastVisiblePosition() > i) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        View view = adapterListView.getChildAt(i - j);
        if(view != null)
        {
            int ai[] = new int[2];
            Rect rect = new Rect();
            adapterListView.getHitRect(rect);
            view.getLocationOnScreen(ai);
            if(ai[flag] >= rect.bottom)
                continue; /* Loop/switch isn't completed */
        }
        flag = false;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean isInUse()
    {
        boolean flag;
        if(playQueueMgr != null && playQueueMgr.isInUse())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onActionPerformed(SCIBrowseItem scibrowseitem, ActionItem actionitem)
    {
        if(!sclib.SC_ACTION_CATEGORY_DEFAULT.equals(actionitem.getCategory()))
            dismissQueue();
    }

    protected void onActiveChanged(boolean flag)
    {
        Log.i(LOG_TAG, "onActiveStateChanged: ");
    }

    protected void onAnimationEnd(Animation animation)
    {
        onAnimationEnd(animation);
        updateScrollPosition(true);
    }

    public boolean onBackPressed()
    {
        boolean flag = true;
        if(saveQueueFragment != null && !saveQueueFragment.onBackPressed())
            dismissSaveQueue();
        else
        if(isInEditMode())
            setInEditMode(false);
        else
            flag = false;
        return flag;
    }

    public View onCreateThemedView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = onCreateThemedView(layoutinflater, viewgroup, bundle);
        queueContentLayout = view.findViewById(0x7f0a0134);
        queueTitle = (TextSwitcher)queueContentLayout.findViewById(0x7f0a009a);
        queueNotInUseTextView = (SonosTextView)view.findViewById(0x7f0a0137);
        updateQueueTitle(null, null);
        setOnItemClickListener(this);
        queueActions = new QueueActionSet();
        if(actionMenu instanceof GlowButtonOverflowActionMenu)
            ((GlowButtonOverflowActionMenu)actionMenu).setParentFragment(this);
        adapterListView.setOnScrollListener(this);
        View view1 = queueContentLayout.findViewById(0x7f0a00b8);
        if(view1 != null)
            view1.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view2)
                {
                    dismissQueue();
                }

                final QueueFragment this$0;

            
            {
                this$0 = QueueFragment.this;
                super();
            }
            }
);
        TransportViewController transportviewcontroller = getSonosActivity().getHouseholdController().getCurrentZoneGroupController().getTransportViewController();
        shuffleButton = view.findViewById(0x7f0a0135);
        if(shuffleButton != null)
        {
            transportviewcontroller.observeView(shuffleButton);
            transportviewcontroller.shuffleListener = this;
        }
        repeatButton = view.findViewById(0x7f0a0136);
        if(repeatButton != null)
            transportviewcontroller.observeView(repeatButton);
        return view;
    }

    public void onCurrentZoneGroupChanged(ZoneGroup zonegroup)
    {
        SLog.i(LOG_TAG, "Subscribing the Queue");
        unsubscribePlayQueue();
        if(zonegroup == null) goto _L2; else goto _L1
_L1:
        playQueue = zonegroup.getPlayQueue();
        if(playQueue == null || !updatePlayQueueDataSource()) goto _L2; else goto _L3
_L3:
        return;
_L2:
        SLog.e(LOG_TAG, "Subscribing the Queue Failed!");
        unsubscribePlayQueue();
        dismissQueue();
        if(true) goto _L3; else goto _L4
_L4:
    }

    public void onDataSourceUpdated()
    {
        onDataSourceUpdated();
        updateViews(browseDataSource, playQueueMgr);
        updateScrollPosition(false);
    }

    public void onDestroy()
    {
        onDestroy();
        onUnsubscribeEventSinks();
        idleTimer.cancel();
    }

    protected void onEditModeChange(boolean flag)
    {
        byte byte0 = 8;
        onEditModeChange(flag);
        updateQueueTitle(browseDataSource, playQueueMgr);
        View view = shuffleButton;
        byte byte1;
        View view1;
        if(flag)
            byte1 = byte0;
        else
            byte1 = 0;
        view.setVisibility(byte1);
        view1 = repeatButton;
        if(!flag)
            byte0 = 0;
        view1.setVisibility(byte0);
        if(actionMenu instanceof GlowButtonOverflowActionMenu)
            ((GlowButtonOverflowActionMenu)actionMenu).setEditState(flag);
    }

    public void onHiddenChanged(boolean flag)
    {
        onHiddenChanged(flag);
        if(!flag)
            updateScrollPosition(true);
    }

    public void onHouseholdEvent(Household household, com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent householdevent)
    {
        if(householdevent == com.sonos.acr.sclib.sinks.HouseholdEventSink.HouseholdEvent.OnCurrentZoneGroupChanged)
            onCurrentZoneGroupChanged(household.getCurrentZoneGroup());
        updateViews(browseDataSource, playQueueMgr);
    }

    public void onItemClick(BrowseItemCell browseitemcell)
    {
        SCIBrowseItem scibrowseitem = browseitemcell.getBrowseItem();
        if(scibrowseitem != null)
        {
            BrowseItemActionData browseitemactiondata = new BrowseItemActionData(scibrowseitem, sclib.createDefaultSCIActionFilter(getActionFilter(false)));
            boolean flag = browseitemactiondata.containsCategory(sclibConstants.SC_ACTION_CATEGORY_EDIT);
            if(browseitemactiondata.size() == 1 && !flag)
            {
                ActionItem actionitem = (ActionItem)browseitemactiondata.getActions().get(0);
                SLog.e(LOG_TAG, (new StringBuilder()).append("Executing Item: ").append(actionitem).toString());
                actionitem.perform();
            } else
            {
                (new ActionDialogFragment(browseitemactiondata)).show(getChildFragmentManager(), "", browseitemcell, 17);
            }
        }
    }

    public boolean onItemLongClick(BrowseItemCell browseitemcell)
    {
        boolean flag;
        SCIBrowseItem scibrowseitem;
        flag = true;
        scibrowseitem = browseitemcell.getBrowseItem();
        if(scibrowseitem == null) goto _L2; else goto _L1
_L1:
        BrowseItemActionData browseitemactiondata = new BrowseItemActionData(scibrowseitem, sclib.createDefaultSCIActionFilter(getActionFilter(flag)));
        if(browseitemactiondata.size() <= 0) goto _L2; else goto _L3
_L3:
        ActionDialogFragment actiondialogfragment = new ActionDialogFragment(browseitemactiondata);
        BrowseItemCell browseitemcell1 = browseitemcell;
        if(DisplayController.getScreenType() == 0)
        {
            actiondialogfragment.setOnActionPerformedListener(this);
            browseitemcell1 = null;
        }
        actiondialogfragment.show(getChildFragmentManager(), "", browseitemcell1, 17);
_L5:
        return flag;
_L2:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void onScroll(AbsListView abslistview, int i, int j, int k)
    {
    }

    public void onScrollStateChanged(AbsListView abslistview, int i)
    {
        if(i != 0)
            idleTimer.start(10000L);
    }

    public void onShuffleClicked()
    {
        idleTimer.cancel();
    }

    public void onStart()
    {
        onSubscribeEventSinks();
        onStart();
    }

    public void onStop()
    {
        onStop();
        onUnsubscribeEventSinks();
        setBrowseDataSource(null);
    }

    public void onSubscribeEventSinks()
    {
        onSubscribeEventSinks();
        HouseholdEventSink.getInstance().addListener(this);
    }

    public void onUnsubscribeEventSinks()
    {
        onUnsubscribeEventSinks();
        idleTimer.cancel();
        unsubscribePlayQueue();
        HouseholdEventSink.getInstance().removeListener(this);
    }

    public void saveScrollPos()
    {
        int i = 0;
        if(adapterListView != null)
        {
            lastScrollItemPosition = adapterListView.getFirstVisiblePosition();
            if(adapterListView.getChildAt(0) != null)
                i = adapterListView.getChildAt(0).getTop();
            lastScrollOffset = i;
        }
    }

    public void setQueueInUseListener(QueueInUseListener queueinuselistener)
    {
        queueInUseListener = queueinuselistener;
    }

    protected void showSaveQueue()
    {
        if(saveQueueFragment == null)
        {
            saveQueueFragment = new SaveQueueFragment(playQueueMgr);
            saveQueueFragment.addPageFragmentListener(new com.sonos.acr.browse.v2.PageFragment.PageFragmentListener() {

                public void onPageInvalidated(PageFragment pagefragment)
                {
                    dismissSaveQueue();
                }

                public void onPageUpdated(PageFragment pagefragment)
                {
                }

                final QueueFragment this$0;

            
            {
                this$0 = QueueFragment.this;
                super();
            }
            }
);
            getChildFragmentManager().beginTransaction().setCustomAnimations(0x7f040001, 0, 0, 0x7f040002).add(0x7f0a0134, saveQueueFragment).addToBackStack("").commit();
        }
    }

    public void updateActionMenu()
    {
        if(actionMenu != null)
        {
            ActionSet actionset = getDataSourceActions();
            if(!actionset.isEmpty())
            {
                actionMenu.setActions(actionset);
                ((View)actionMenu).setVisibility(0);
            } else
            {
                ((View)actionMenu).setVisibility(8);
            }
        }
    }

    public void updateCellView(BrowseItemCell browseitemcell, int i)
    {
        updateCellView(browseitemcell, i);
        if(dataSourceAdapter != null && (browseitemcell instanceof QueueListItemCell))
        {
            QueueListItemCell queuelistitemcell = (QueueListItemCell)browseitemcell;
            float f = 1.0F;
            boolean flag;
            if(playQueueMgr != null && playQueueMgr.isInfiniteQueue())
                flag = true;
            else
                flag = false;
            if(flag)
            {
                int j = dataSourceAdapter.getCount() - i;
                if(j <= 4)
                    f = 0.01F * ((70F * (float)j) / 4F);
            }
            queuelistitemcell.setContentAlpha(f);
        }
    }

    protected void updateFooterPadding(boolean flag)
    {
    }

    public void updateScrollPosition(boolean flag)
    {
        boolean flag1;
        if(!isInEditMode() && !idleTimer.isRunning() || flag)
            flag1 = true;
        else
            flag1 = false;
        if(browseDataSource == null || !browseDataSource.isValid()) goto _L2; else goto _L1
_L1:
        if(lastScrollItemPosition < 0 || flag1) goto _L4; else goto _L3
_L3:
        resetLastScrollPos();
_L2:
        return;
_L4:
        if(flag1)
        {
            int i = -1;
            if(playQueueMgr != null)
                i = playQueueMgr.getCurrentItemIndex();
            if(i >= 0)
            {
                if(i > 0)
                    i--;
                if(isCurrentObstructed(i))
                    adapterListView.setSelection(i);
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    protected void updateViews(SCIBrowseDataSource scibrowsedatasource, SCIPlayQueueMgr sciplayqueuemgr)
    {
        updateQueueTitle(scibrowsedatasource, sciplayqueuemgr);
        if(isInEditMode() && scibrowsedatasource.getNumItems() == 0L)
            setInEditMode(false);
    }

    SonosTimer idleTimer;
    protected int lastScrollItemPosition;
    protected int lastScrollOffset;
    protected SCIPlayQueue playQueue;
    protected PlayQueueEventSink playQueueEventSink;
    protected SCIPlayQueueMgr playQueueMgr;
    protected QueueActionSet queueActions;
    protected View queueContentLayout;
    boolean queueInUse;
    private QueueInUseListener queueInUseListener;
    SonosTextView queueNotInUseTextView;
    protected TextSwitcher queueTitle;
    protected View repeatButton;
    protected SaveQueueFragment saveQueueFragment;
    protected View shuffleButton;







}

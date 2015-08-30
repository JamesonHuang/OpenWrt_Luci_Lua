// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services;

import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;
import java.util.Iterator;

// Referenced classes of package com.sonos.acr.services:
//            NoOpActionFactory

public class ActionFactoryDelegate extends SCIActionFactorySwigBase
{
    private static class ActionOpCB extends SCIActionSwigBase
    {

        public SCActionCompletionStatus perform(final SCIActionContext actionContext)
        {
            op._start(new SCIOpCBSwigBase() {

                public void _operationComplete(long l, int i)
                {
                    actionContext.actionHasCompleted(ActionOpCB.this);
                }

                final ActionOpCB this$0;
                final SCIActionContext val$actionContext;

                
                {
                    this$0 = ActionOpCB.this;
                    actionContext = sciactioncontext;
                    super();
                }
            }
);
            return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
        }

        SCIOp op;

        ActionOpCB(SCIOp sciop)
        {
            op = sciop;
        }
    }


    public ActionFactoryDelegate()
    {
        messageListeners = new WeakHashSet();
        currentActionFactory = noOpActionFactory;
    }

    public void addMessageListener(com.sonos.acr.sclib.SCLibManager.MessageListener messagelistener)
    {
        messageListeners.add(messagelistener);
    }

    public SCIAction createBrowsePickerAction(SCIBrowseDataSource scibrowsedatasource, String s)
    {
        return currentActionFactory.createBrowsePickerAction(scibrowsedatasource, s);
    }

    public SCIAction createCustomUIAction(String s, String s1)
    {
        return currentActionFactory.createCustomUIAction(s, s1);
    }

    public SCIAction createDisplayBrowseStackAction(SCIBrowseStackManager scibrowsestackmanager)
    {
        return currentActionFactory.createDisplayBrowseStackAction(scibrowsestackmanager);
    }

    public SCIAction createDisplayCustomControlAction(SCDisplayCustomControlActionType scdisplaycustomcontrolactiontype)
    {
        return currentActionFactory.createDisplayCustomControlAction(scdisplaycustomcontrolactiontype);
    }

    public SCIAction createDisplayDatePickerAction(String s, String s1, SCISystemTime scisystemtime)
    {
        return currentActionFactory.createDisplayDatePickerAction(s, s1, scisystemtime);
    }

    public SCIAction createDisplayDualTextInputAction(String s, String s1, String s2, String s3, SCIStringInput scistringinput, String s4, String s5, 
            SCIStringInput scistringinput1)
    {
        return currentActionFactory.createDisplayDualTextInputAction(s, s1, s2, s3, scistringinput, s4, s5, scistringinput1);
    }

    public SCIAction createDisplayInfoViewAction(String s, String s1)
    {
        return currentActionFactory.createDisplayInfoViewAction(s, s1);
    }

    public SCIAction createDisplayIntegerInputAction(String s, String s1, String s2, int i, int j, int k)
    {
        return currentActionFactory.createDisplayIntegerInputAction(s, s1, s2, i, j, k);
    }

    public SCIAction createDisplayMenuAction(String s, String s1, String s2, SCIStringArray scistringarray, int i)
    {
        return currentActionFactory.createDisplayMenuAction(s, s1, s2, scistringarray, i);
    }

    public SCIAction createDisplayMenuAndTextInputAction(String s, String s1, String s2, boolean flag, SCIEnumerator scienumerator, int i, SCIResource sciresource, 
            int j, String s3)
    {
        return currentActionFactory.createDisplayMenuAndTextInputAction(s, s1, s2, flag, scienumerator, i, sciresource, j, s3);
    }

    public SCIAction createDisplayMenuPopupAction(String s, SCIStringArray scistringarray, int i, String s1, boolean flag, boolean flag1)
    {
        return currentActionFactory.createDisplayMenuPopupAction(s, scistringarray, i, s1, flag, flag1);
    }

    public SCIAction createDisplayMessagePopupAction(final String s, final int i)
    {
        Object obj;
        if(currentActionFactory == noOpActionFactory)
            obj = new SCIActionSwigBase() {

                public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
                {
                    String s1 = sciactioncontext.getPropertyBag().getStrProp("ZONEGROUP_ID");
                    String s2 = sciactioncontext.getPropertyBag().getStrProp(sclibConstants.SCATN_STRPROP_SHORT_MESSAGE);
                    com.sonos.acr.sclib.wrappers.ZoneGroup zonegroup = LibraryUtils.getHousehold().lookupZoneGroup(s1);
                    Iterator iterator = messageListeners.iterator();
                    do
                    {
                        if(!iterator.hasNext())
                            break;
                        com.sonos.acr.sclib.SCLibManager.MessageListener messagelistener = (com.sonos.acr.sclib.SCLibManager.MessageListener)iterator.next();
                        if(messagelistener != null)
                        {
                            SLog.e(ActionFactoryDelegate.LOG_TAG, (new StringBuilder()).append("Sending Message to: ").append(messagelistener.getClass()).append(" message: ").append(s).toString());
                            messagelistener.onZoneGroupMessage(zonegroup, s, s2, i);
                        }
                    } while(true);
                    return SCActionCompletionStatus.DONE_WITH_ACTION;
                }

                final ActionFactoryDelegate this$0;
                final int val$i;
                final String val$s;

            
            {
                this$0 = ActionFactoryDelegate.this;
                s = s1;
                i = j;
                super();
            }
            }
;
        else
            obj = currentActionFactory.createDisplayMessagePopupAction(s, i);
        return ((SCIAction) (obj));
    }

    public SCIAction createDisplayTextInputAction(String s, String s1, String s2, SCIStringInput scistringinput)
    {
        return currentActionFactory.createDisplayTextInputAction(s, s1, s2, scistringinput);
    }

    public SCIAction createDisplayTextPaneAction(String s, String s1, SCIInfoViewTextPaneMetadata sciinfoviewtextpanemetadata)
    {
        return currentActionFactory.createDisplayTextPaneAction(s, s1, sciinfoviewtextpanemetadata);
    }

    public SCIAction createDisplayTimePickerAction(String s, String s1, SCITime scitime)
    {
        return currentActionFactory.createDisplayTimePickerAction(s, s1, scitime);
    }

    public SCIAction createDisplayWizardAction(SCIWizard sciwizard)
    {
        return currentActionFactory.createDisplayWizardAction(sciwizard);
    }

    public SCIAction createNavigationAction(SCNavigationActionType scnavigationactiontype, SCIPropertyBag scipropertybag)
    {
        return currentActionFactory.createNavigationAction(scnavigationactiontype, scipropertybag);
    }

    public SCIAction createPopBrowseAction(int i)
    {
        return currentActionFactory.createPopBrowseAction(i);
    }

    public SCIAction createPushSCUriAction(String s, String s1, boolean flag)
    {
        return currentActionFactory.createPushSCUriAction(s, s1, flag);
    }

    public SCIAction createRunAsyncIOOperationAction(SCIOp sciop)
    {
        Object obj;
        if(currentActionFactory == noOpActionFactory)
            obj = new ActionOpCB(sciop);
        else
            obj = currentActionFactory.createRunAsyncIOOperationAction(sciop);
        return ((SCIAction) (obj));
    }

    public SCIAction createRunWizardAction(SCRunWizardActionType scrunwizardactiontype)
    {
        return currentActionFactory.createRunWizardAction(scrunwizardactiontype);
    }

    public void removeCurrentActionFactory(SCIActionFactory sciactionfactory)
    {
        if(currentActionFactory == sciactionfactory)
            currentActionFactory = noOpActionFactory;
    }

    public void setCurrentActionFactory(SCIActionFactory sciactionfactory)
    {
        currentActionFactory = sciactionfactory;
    }

    private static final String LOG_TAG = com/sonos/acr/services/ActionFactoryDelegate.getSimpleName();
    protected SCIActionFactory currentActionFactory;
    WeakHashSet messageListeners;
    private final NoOpActionFactory noOpActionFactory = new NoOpActionFactory();


}

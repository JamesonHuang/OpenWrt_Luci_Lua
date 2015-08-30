// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uibusymanager;

import android.app.Activity;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.uibusymanager:
//            AbsUiBusyManager

public class ActionUiBusyManager extends AbsUiBusyManager
{

    public ActionUiBusyManager(Activity activity, SCIActionContext sciactioncontext)
    {
        super(activity);
        actionContext = sciactioncontext;
    }

    public void start()
    {
        if(actionContext != null)
        {
            actionContext.setDelegate(new SCIActionDelegateSwigBase() {

                public void asyncActionHasCompleted(SCIAction sciaction, SCIActionContext sciactioncontext)
                {
                    unlockUI();
                    actionContext.setDelegate(null);
                }

                final ActionUiBusyManager this$0;

            
            {
                this$0 = ActionUiBusyManager.this;
                super();
            }
            }
);
            if(actionContext.perform() == SCActionCompletionStatus.WAIT_FOR_CALLBACK)
                lockUI();
        }
    }

    SCIActionContext actionContext;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import com.sonos.acr.SonosActivity;
import com.sonos.acr.util.SonosToast;
import com.sonos.sclib.SCActionCompletionStatus;
import com.sonos.sclib.SCIActionContext;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class DisplayMessagePopupAction extends UIAction
{

    public DisplayMessagePopupAction(SonosActivity sonosactivity, String s, int i)
    {
        super(sonosactivity);
        m_message = s;
    }

    public SCActionCompletionStatus perform(final SCIActionContext actionContextRef)
    {
        com.sonos.acr.util.SonosToast.CompletionCallback completioncallback = new com.sonos.acr.util.SonosToast.CompletionCallback() {

            public void completed()
            {
                actionContextRef.actionHasCompleted(DisplayMessagePopupAction.this);
            }

            final DisplayMessagePopupAction this$0;
            final SCIActionContext val$actionContextRef;

            
            {
                this$0 = DisplayMessagePopupAction.this;
                actionContextRef = sciactioncontext;
                super();
            }
        }
;
        SonosToast.popupDialog(m_message, null, completioncallback);
        return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
    }

    String m_message;
}

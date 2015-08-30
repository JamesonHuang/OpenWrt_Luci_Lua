// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import android.os.Handler;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.application.SonosApplication;
import com.sonos.sclib.SCActionCompletionStatus;
import com.sonos.sclib.SCIActionContext;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class CrashAction extends UIAction
{

    public CrashAction(SonosActivity sonosactivity, String s)
    {
        super(sonosactivity);
        exceptionText = s;
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        SonosApplication.getInstance().getHandler().post(new Runnable() {

            public void run()
            {
                throw new RuntimeException(exceptionText);
            }

            final CrashAction this$0;

            
            {
                this$0 = CrashAction.this;
                super();
            }
        }
);
        return SCActionCompletionStatus.DONE_WITH_ACTION;
    }

    String exceptionText;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uibusymanager;

import android.app.Activity;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.uibusymanager:
//            AbsUiBusyManager

public class UiBusyManager extends AbsUiBusyManager
{

    public UiBusyManager(Activity activity, SCIOp sciop, SCIOpCB sciopcb)
    {
        super(activity);
        operation = sciop;
        operationCallback = sciopcb;
    }

    protected void operationComplete(long l, int i)
    {
        if(operationCallback != null)
            operationCallback._operationComplete(l, i);
        unlockUI();
    }

    public void start()
    {
        if(operation != null && operation._start(new SCIOpCBSwigBase() {

        public void _operationComplete(long l, int i)
        {
            operationComplete(l, i);
        }

        final UiBusyManager this$0;

            
            {
                this$0 = UiBusyManager.this;
                super();
            }
    }
) != (long)sclibConstants.SCOP_INVALID_SERIALNUM)
            lockUI();
    }

    SCIOp operation;
    SCIOpCB operationCallback;
}

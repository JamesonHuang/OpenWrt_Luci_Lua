// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import com.sonos.acr.SonosActivity;
import com.sonos.acr.uibusymanager.UiBusyManager;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class RunAsyncIOOperationAction extends UIAction
{

    public RunAsyncIOOperationAction(SonosActivity sonosactivity, SCIOp sciop)
    {
        super(sonosactivity);
        m_CompletionCallback = new SCIOpCBSwigBase() {

            public void _operationComplete(long l, int i)
            {
                SLog.e(RunAsyncIOOperationAction.LOG_TAG, (new StringBuilder()).append("Async operation finished with result: ").append(i).append(m_Op.getOpResult()).toString());
                if(!$assertionsDisabled && l != m_Op.serialNumber())
                    throw new AssertionError();
                if(l != m_Op.serialNumber())
                    SLog.e(RunAsyncIOOperationAction.LOG_TAG, "Incorrect ASyncIOOp serial number");
                m_ActionContext.actionHasCompleted(RunAsyncIOOperationAction.this);
            }

            static final boolean $assertionsDisabled;
            final RunAsyncIOOperationAction this$0;

            static 
            {
                boolean flag;
                if(!com/sonos/acr/uiactions/RunAsyncIOOperationAction.desiredAssertionStatus())
                    flag = true;
                else
                    flag = false;
                $assertionsDisabled = flag;
            }

            
            {
                this$0 = RunAsyncIOOperationAction.this;
                super();
            }
        }
;
        m_Op = sciop;
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        m_ActionContext = sciactioncontext;
        (new UiBusyManager(currentContext, m_Op, m_CompletionCallback)).start();
        return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
    }

    private static final String LOG_TAG = com/sonos/acr/uiactions/RunAsyncIOOperationAction.getSimpleName();
    SCIActionContext m_ActionContext;
    private SCIOpCBSwigBase m_CompletionCallback;
    SCIOp m_Op;


}

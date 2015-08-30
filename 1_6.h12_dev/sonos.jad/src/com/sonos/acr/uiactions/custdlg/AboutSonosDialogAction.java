// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions.custdlg;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.uiactions.DisplayCustomControlAction;
import com.sonos.sclib.*;

public class AboutSonosDialogAction extends DisplayCustomControlAction
{

    public AboutSonosDialogAction(SonosActivity sonosactivity)
    {
        super(sonosactivity);
        aboutSonosStringCallBack = new SCIGetAboutSonosStringCBSwigBase() {

            public void updateGetAboutSonosString(String s)
            {
                setMessage.setMessage(s);
                setMessage.show();
            }

            final AboutSonosDialogAction this$0;

            
            {
                this$0 = AboutSonosDialogAction.this;
                super();
            }
        }
;
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        SCIOpGetAboutSonosString sciopgetaboutsonosstring = currentContext.getHousehold().createGetAboutSonosStrOp();
        sciopgetaboutsonosstring.setCallback(aboutSonosStringCallBack);
        sciopgetaboutsonosstring._start(noopOperationCallback);
        m_alertDialog = m_alertDialogBuilder.setIcon(0x108009b).setTitle(0x7f0c0000).setPositiveButton(0x7f0c0050, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
            }

            final AboutSonosDialogAction this$0;

            
            {
                this$0 = AboutSonosDialogAction.this;
                super();
            }
        }
).create();
        currentContext.getLibrary().setSupportEnabled(true);
        m_alertDialog.setOnDismissListener(new android.content.DialogInterface.OnDismissListener() {

            public void onDismiss(DialogInterface dialoginterface)
            {
                currentContext.getLibrary().setSupportEnabled(false);
            }

            final AboutSonosDialogAction this$0;

            
            {
                this$0 = AboutSonosDialogAction.this;
                super();
            }
        }
);
        return SCActionCompletionStatus.DONE_WITH_ACTION;
    }

    private SCIGetAboutSonosStringCBSwigBase aboutSonosStringCallBack;


}

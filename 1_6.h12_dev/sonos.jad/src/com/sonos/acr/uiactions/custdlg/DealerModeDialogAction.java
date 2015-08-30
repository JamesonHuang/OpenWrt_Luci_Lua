// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions.custdlg;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.uiactions.DisplayCustomControlAction;
import com.sonos.acr.util.SonosToast;
import com.sonos.acr.view.SonosButton;
import com.sonos.sclib.*;

public class DealerModeDialogAction extends DisplayCustomControlAction
{

    public DealerModeDialogAction(SonosActivity sonosactivity)
    {
        DisplayCustomControlAction(sonosactivity);
        m_dealerMode = sonosactivity.getSCDealerMode();
        buildView();
    }

    private void buildView()
    {
        m_dealerModeView = LayoutInflater.from(currentContext).inflate(0x7f03006b, null);
        m_DMToggleButton = (ToggleButton)m_dealerModeView.findViewById(0x7f0a0169);
        m_SAToggleButton = (ToggleButton)m_dealerModeView.findViewById(0x7f0a016c);
        if(m_dealerMode != null)
        {
            m_DMToggleButton.setChecked(m_dealerMode.isDealerLock());
            m_SAToggleButton.setChecked(m_dealerMode.isStoresAliveEnabled());
        }
        m_SAToggleButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(!m_SAToggleButton.isChecked())
                {
                    if(m_dealerMode.isStoresAliveEnabled())
                        SonosToast.popupDialog(0x7f0c00ec);
                } else
                {
                    final EditText input = new EditText(currentContext);
                    (new Builder(currentContext)).setTitle(0x7f0c003e).setMessage(0x7f0c003d).setView(input).setPositiveButton(0x7f0c0050, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            String s = input.getText().toString();
                            if(m_dealerMode.checkStoresAlivePIN(s))
                            {
                                m_DMToggleButton.setChecked(true);
                            } else
                            {
                                m_SAToggleButton.setChecked(false);
                                SonosToast.popupDialog(0x7f0c00eb);
                            }
                        }

                        final _cls1 this$1;
                        final EditText val$input;

                    
                    {
                        this$1 = _cls1.this;
                        input = edittext;
                        Object();
                    }
                    }
).setNegativeButton(0x1040000, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            m_SAToggleButton.setChecked(false);
                        }

                        final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        Object();
                    }
                    }
).show();
                }
            }

            final DealerModeDialogAction this$0;

            
            {
                this$0 = DealerModeDialogAction.this;
                Object();
            }
        }
);
        ((SonosButton)m_dealerModeView.findViewById(0x7f0a016d)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                currentContext.startActivity(new Intent("android.settings.SETTINGS"));
            }

            final DealerModeDialogAction this$0;

            
            {
                this$0 = DealerModeDialogAction.this;
                Object();
            }
        }
);
    }

    public SCActionCompletionStatus perform(final SCIActionContext pActionCtxt)
    {
        if(m_dealerMode != null)
            m_DMToggleButton.setChecked(m_dealerMode.isDealerLock());
        m_alertDialogBuilder.setView(m_dealerModeView);
        m_alertDialog = m_alertDialogBuilder.setTitle(0x7f0c0042).setPositiveButton(0x7f0c0050, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                if(m_dealerMode != null)
                {
                    boolean flag = m_SAToggleButton.isChecked();
                    m_dealerMode.setStoresAlive(flag);
                    boolean flag1 = m_DMToggleButton.isChecked();
                    m_dealerMode.setDealerLock(flag1);
                }
                pActionCtxt.actionHasCompleted(DealerModeDialogAction.this);
                terminate(dialoginterface);
            }

            final DealerModeDialogAction this$0;
            final SCIActionContext val$pActionCtxt;

            
            {
                this$0 = DealerModeDialogAction.this;
                pActionCtxt = sciactioncontext;
                Object();
            }
        }
).create();
        m_alertDialog.show();
        return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
    }

    private ToggleButton m_DMToggleButton;
    private ToggleButton m_SAToggleButton;
    private SCIDealerMode m_dealerMode;
    private View m_dealerModeView;




}

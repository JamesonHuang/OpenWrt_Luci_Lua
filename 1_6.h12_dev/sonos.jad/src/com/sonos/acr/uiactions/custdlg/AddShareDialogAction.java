// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions.custdlg;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.uiactions.DisplayCustomControlAction;
import com.sonos.acr.uibusymanager.UiBusyManager;
import com.sonos.acr.util.*;
import com.sonos.sclib.*;

public class AddShareDialogAction extends DisplayCustomControlAction
    implements android.content.DialogInterface.OnClickListener, TextWatcher
{

    public AddShareDialogAction(SonosActivity sonosactivity)
    {
        super(sonosactivity);
        addShareView = LayoutInflater.from(sonosactivity).inflate(0x7f030006, null);
        usernameView = (EditText)addShareView.findViewById(0x7f0a0038);
        passwordView = (EditText)addShareView.findViewById(0x7f0a0039);
        sharePathView = (EditText)addShareView.findViewById(0x7f0a0037);
        passwordView.setSingleLine();
        passwordView.setTypeface(ViewUtils.SONOS_REGULAR);
        passwordView.setTransformationMethod(new PasswordTransformationMethod());
        sharePathView.setInputType(0x80000);
        usernameView.setInputType(0x80000);
        m_alertDialogBuilder.setView(addShareView);
    }

    public void afterTextChanged(Editable editable)
    {
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        SLog.e(LOG_TAG, "onClickSingle called");
        if(i == -1)
        {
            final SCIOp sciOp = currentContext.getHousehold().getShareManager().createAddShareOp(sharePathView.getText().toString(), usernameView.getText().toString(), passwordView.getText().toString());
            (new UiBusyManager(currentContext, sciOp, new SCIOpCBSwigBase() )).start();
            SonosToast.popupDialog(0x7f0c0087);
        }
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        Button button = okButton;
        boolean flag;
        if(charsequence != null && charsequence.length() > 0)
            flag = true;
        else
            flag = false;
        button.setEnabled(flag);
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        actionContext = sciactioncontext;
        m_alertDialogBuilder.setPositiveButton(0x104000a, this);
        m_alertDialogBuilder.setNegativeButton(0x1040000, null);
        m_alertDialogBuilder.setTitle(0x7f0c008c);
        m_alertDialog = m_alertDialogBuilder.create();
        m_alertDialog.show();
        okButton = m_alertDialog.getButton(-1);
        okButton.setEnabled(false);
        sharePathView.addTextChangedListener(this);
        return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
    }

    SCIActionContext actionContext;
    View addShareView;
    Button okButton;
    EditText passwordView;
    EditText sharePathView;
    EditText usernameView;

}

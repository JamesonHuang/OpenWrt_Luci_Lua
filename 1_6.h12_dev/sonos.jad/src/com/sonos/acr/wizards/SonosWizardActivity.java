// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards;

import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Window;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.application.AppDataStore;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;
import java.util.Stack;

// Referenced classes of package com.sonos.acr.wizards:
//            Wizard, WizardView

public class SonosWizardActivity extends SonosActivity
{

    public SonosWizardActivity()
    {
        wizardStack = new Stack();
    }

    public boolean canRunInLC()
    {
        return true;
    }

    protected boolean canRunWithoutWifi()
    {
        boolean flag;
        if(!wizardStack.isEmpty() && ((Wizard)wizardStack.peek()).canRunWithoutWifi())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isDebugWizard()
    {
        boolean flag;
        if(!wizardStack.isEmpty())
            flag = ((Wizard)wizardStack.peek()).isDebugMode();
        else
            flag = false;
        return flag;
    }

    public void onBackPressed()
    {
        int i = wizardStack.size();
        if(i <= 0 || !((Wizard)wizardStack.peek()).onBackPressed() && i == 1)
            super.onBackPressed();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        SLog.e(LOG_TAG, "On Create Called!!!!");
        setContentView(0x7f0300bf);
        getWindow().addFlags(128);
        wizardView = (WizardView)findViewById(0x7f0a0204);
        if(LibraryUtils.getSCLibManager().isInitialized())
            onNewIntent(getIntent());
        else
            jumpToLCScreen();
        PowerManager _tmp = (PowerManager)SonosApplication.getInstance().getSystemService("power");
    }

    protected void onDestroy()
    {
        super.onDestroy();
        unregisterUIActionFactory();
    }

    public void onNewIntent(Intent intent)
    {
        SLog.e(LOG_TAG, "onNewIntent Called!!!!");
        if(!wizardStack.isEmpty())
            ((Wizard)wizardStack.peek()).pause();
        Long long1 = (Long)intent.getExtras().get("WIZARD_OBJECT");
        Wizard wizard = (Wizard)SonosApplication.getAppDataStore().get(long1.longValue());
        if(wizard != null)
        {
            wizardView.setDebugWizard(wizard.isDebugMode());
            wizard.init(wizardView, this);
            wizardStack.push(wizard);
            wizard.start();
            wizard.resume();
        } else
        {
            SLog.e(LOG_TAG, (new StringBuilder()).append("The wizard object was nowhere to be found! Intent:").append(intent).toString());
        }
    }

    protected void onPause()
    {
        super.onPause();
        if(!wizardStack.isEmpty())
            ((Wizard)wizardStack.peek()).pause();
    }

    protected void onResume()
    {
        super.onResume();
        if(!wizardStack.isEmpty())
            ((Wizard)wizardStack.peek()).resume();
    }

    public void onWizardCompleted(Wizard wizard)
    {
        SLog.e(LOG_TAG, "On Wizard Completed");
        if(wizard == wizardStack.peek())
        {
            wizard.stop();
            wizardStack.pop();
            if(wizardStack.isEmpty())
            {
                wizardView.setDebugWizard(false);
                finish();
            } else
            {
                wizardView.setDebugWizard(((Wizard)wizardStack.peek()).isDebugMode());
                ((Wizard)wizardStack.peek()).resume();
            }
            return;
        } else
        {
            throw new RuntimeException("Wizard that completed does not match current Wizard");
        }
    }

    protected void unregisterUIActionFactory()
    {
        if(isFinishing())
            super.unregisterUIActionFactory();
    }

    public static final String DEBUG_WIZARD = "DEBUG_WIZARD";
    public static final String EXTRA_WIZARD_OBJECT = "WIZARD_OBJECT";
    private Stack wizardStack;
    protected WizardView wizardView;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uibusymanager;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.sonos.acr.application.SonosApplication;

public abstract class AbsUiBusyManager
{

    public AbsUiBusyManager(Activity activity)
    {
        context = activity;
        handler = SonosApplication.getInstance().getHandler();
    }

    public boolean isBusy()
    {
        boolean flag;
        if(dialog != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected void lockUI()
    {
        if(dialog == null)
        {
            dialog = new Dialog(context);
            dialog.setCancelable(false);
            dialog.requestWindowFeature(1);
            dialog.setContentView(0x7f030012);
            Window window = dialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.clearFlags(2);
            android.view.WindowManager.LayoutParams layoutparams = window.getAttributes();
            layoutparams.height = -1;
            layoutparams.width = -1;
            layoutparams.flags = 128;
            layoutparams.dimAmount = 0.5F;
            layoutparams.type = 1003;
            layoutparams.windowAnimations = 0x1030004;
            layoutparams.format = -3;
            window.setAttributes(layoutparams);
            if(android.os.Build.VERSION.SDK_INT >= 19)
            {
                window.setFlags(0x8000000, 0x8000000);
                window.setFlags(0x4000000, 0x4000000);
            }
            dialog.show();
            final ProgressBar progressBar = (ProgressBar)dialog.findViewById(0x7f0a0050);
            final FrameLayout back = (FrameLayout)dialog.findViewById(0x7f0a004f);
            if(progressBar != null)
            {
                progressStart = new Runnable() {

                    public void run()
                    {
                        android.view.animation.Animation animation = AnimationUtils.loadAnimation(context, 0x7f040001);
                        progressBar.setVisibility(0);
                        back.setVisibility(0);
                        progressBar.startAnimation(animation);
                        back.startAnimation(animation);
                    }

                    final AbsUiBusyManager this$0;
                    final FrameLayout val$back;
                    final ProgressBar val$progressBar;

            
            {
                this$0 = AbsUiBusyManager.this;
                progressBar = progressbar;
                back = framelayout;
                super();
            }
                }
;
                handler.postDelayed(progressStart, 2000L);
            }
        }
    }

    public abstract void start();

    protected void unlockUI()
    {
        if(dialog != null && !context.isFinishing())
        {
            dialog.dismiss();
            dialog = null;
        }
        handler.removeCallbacks(progressStart);
    }

    Activity context;
    private Dialog dialog;
    private Handler handler;
    Runnable progressStart;
}

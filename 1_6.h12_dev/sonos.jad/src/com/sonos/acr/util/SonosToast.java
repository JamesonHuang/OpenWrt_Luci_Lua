// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Handler;
import android.widget.Toast;
import com.sonos.acr.application.SonosApplication;

// Referenced classes of package com.sonos.acr.util:
//            StringUtils

public class SonosToast
{
    public static interface CompletionCallback
    {

        public abstract void completed();
    }


    public SonosToast(Context context)
    {
        mAndroidHandler = SonosApplication.getInstance().getHandler();
        mWrappedToast = Toast.makeText(context, "", 0);
    }

    public static SonosToast getInstance()
    {
        if(instance == null)
            instance = new SonosToast(SonosApplication.getInstance().getApplicationContext());
        return instance;
    }

    public static void okDialog(int i, Context context)
    {
        (new android.app.AlertDialog.Builder(context)).setMessage(SonosApplication.getInstance().getResources().getString(i)).setPositiveButton(0x7f0c0050, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                dialoginterface.dismiss();
            }

        }
).create().show();
    }

    public static void popup(String s)
    {
        popup(s, 1);
    }

    public static void popup(String s, int i)
    {
        Toast.makeText(SonosApplication.getInstance(), s, i).show();
    }

    public static void popupDialog(int i)
    {
        popupDialog(SonosApplication.getInstance().getApplicationContext().getResources().getString(i), ((String) (null)));
    }

    public static void popupDialog(int i, int j)
    {
        Resources resources = SonosApplication.getInstance().getApplicationContext().getResources();
        popupDialog(resources.getString(i), resources.getString(j));
    }

    public static void popupDialog(String s, String s1)
    {
        if(s != null && !"".equals(s))
            popupDialog(s, s1, null);
    }

    public static void popupDialog(final String msg, final String title, final CompletionCallback callback)
    {
        SonosApplication.getInstance().getHandler().post(new Runnable() {

            public void run()
            {
                SonosToast.getInstance().show(msg, title, callback);
            }

            final CompletionCallback val$callback;
            final String val$msg;
            final String val$title;

            
            {
                msg = s;
                title = s1;
                callback = completioncallback;
                super();
            }
        }
);
    }

    /**
     * @deprecated Method show is deprecated
     */

    private void show(String s, String s1, CompletionCallback completioncallback)
    {
        this;
        JVM INSTR monitorenter ;
        if(s == null)
            break MISSING_BLOCK_LABEL_114;
        if(s.length() > 0)
        {
            if(!StringUtils.isEmptyOrNull(s1))
                s = (new StringBuilder()).append(s1).append(": ").append(s).toString();
            mAndroidHandler.removeCallbacks(mDismissDialogRunnable);
            if(mSonosCompletionCallback != null)
                mSonosCompletionCallback.completed();
            mSonosCompletionCallback = completioncallback;
            mWrappedToast.setText(s);
            mWrappedToast.setDuration(0);
            mWrappedToast.show();
            mAndroidHandler.postDelayed(mDismissDialogRunnable, 2000L);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void dismiss()
    {
        mAndroidHandler.removeCallbacks(mDismissDialogRunnable);
        if(instance != null)
        {
            if(mSonosCompletionCallback != null)
            {
                mSonosCompletionCallback.completed();
                mSonosCompletionCallback = null;
            }
            mWrappedToast.cancel();
            instance = null;
        }
    }

    private static final int SHORT_DELAY = 2000;
    private static SonosToast instance;
    private Handler mAndroidHandler;
    private final Runnable mDismissDialogRunnable = new Runnable() {

        public void run()
        {
            dismiss();
        }

        final SonosToast this$0;

            
            {
                this$0 = SonosToast.this;
                super();
            }
    }
;
    private CompletionCallback mSonosCompletionCallback;
    private Toast mWrappedToast;

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.app.ProgressDialog;
import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.sclib.*;
import java.io.*;

// Referenced classes of package com.sonos.acr.util:
//            SLog, LibraryUtils, StringUtils, ImageUtils

public class FeedbackHandler extends ContextWrapper
{

    public FeedbackHandler(Context context)
    {
        super(context);
        isShowing = false;
        isSubmitting = false;
        backedOut = false;
        bitmap = null;
        diagOp = null;
    }

    private void doSubmit(Bitmap bitmap1)
    {
        String s = getResources().getString(0x7f0c0036);
        SLog.v(LOG_TAG, "Feedback diag submitted!");
        String s1 = (new StringBuilder()).append(Environment.getExternalStorageDirectory().toString()).append("/SONOS").toString();
        if(s.equals("BETA") || s.equals("ALPHA"))
            s1 = (new StringBuilder()).append(Environment.getExternalStorageDirectory().toString()).append("/Pictures/Screenshots").toString();
        String s2 = (new StringBuilder()).append(s1).append("/FeedbackScreen.jpg").toString();
        long l = 0L;
        if(diagOp != null)
        {
            l = diagOp.getConfirmationNumber();
            diagOp = null;
        }
        Uri uri = null;
        if(bitmap1 != null)
        {
            SLog.v(LOG_TAG, "Writing feedback bitmap to file...");
            (new File(s1)).mkdirs();
            File file = new File(s2);
            if(file.exists())
                file.delete();
            String s3;
            String s4;
            Household household;
            String s5;
            String s6;
            Intent intent;
            String as[];
            String s7;
            Object aobj[];
            String s8;
            Resources resources;
            Object aobj1[];
            try
            {
                FileOutputStream fileoutputstream = new FileOutputStream(file);
                bitmap1.compress(android.graphics.Bitmap.CompressFormat.JPEG, 40, fileoutputstream);
                fileoutputstream.flush();
                fileoutputstream.close();
                SLog.v(LOG_TAG, "Feedback bitmap written!");
            }
            catch(FileNotFoundException filenotfoundexception) { }
            catch(IOException ioexception) { }
            uri = Uri.fromFile(file);
        }
        if(s.equals("BETA") || s.equals("ALPHA"))
        {
            if(uri != null)
                saveMediaEntry(uri.getPath(), "screenshot", System.currentTimeMillis(), getResources().getConfiguration().orientation);
            s3 = "http://beta.sonos.com/feedback/";
            s4 = "";
            household = LibraryUtils.getHousehold();
            if(household != null)
                s4 = household.getCustomerIDIfRegistered();
            if(StringUtils.isNotEmptyOrNull(s4) || l > 0L)
            {
                s3 = s3.concat("?");
                if(StringUtils.isNotEmptyOrNull(s4))
                {
                    s6 = (new StringBuilder()).append("sonosid=").append(s4).toString();
                    s3 = s3.concat(s6);
                    if(l > 0L)
                        s3 = s3.concat("&");
                }
                if(l > 0L)
                {
                    s5 = (new StringBuilder()).append("diagnostic=").append(l).toString();
                    s3 = s3.concat(s5);
                }
            }
            if(progressDialog != null)
                progressDialog.dismiss();
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s3)));
        } else
        {
            intent = new Intent("android.intent.action.SEND");
            intent.setType("text/html");
            as = new String[1];
            as[0] = "beta@sonos.com";
            intent.putExtra("android.intent.extra.EMAIL", as);
            s7 = getResources().getString(0x7f0c004c);
            aobj = new Object[1];
            aobj[0] = getResources().getString(0x7f0c0034);
            intent.putExtra("android.intent.extra.SUBJECT", String.format(s7, aobj));
            if(l == 0L)
                s8 = getResources().getString(0x7f0c004e);
            else
                s8 = Long.toString(l);
            resources = getResources();
            aobj1 = new Object[3];
            aobj1[0] = s8;
            aobj1[1] = getDeviceName();
            aobj1[2] = android.os.Build.VERSION.RELEASE;
            intent.putExtra("android.intent.extra.TEXT", resources.getString(0x7f0c004b, aobj1));
            if(bitmap1 != null && uri != null)
                intent.putExtra("android.intent.extra.STREAM", uri);
            if(progressDialog != null)
                progressDialog.dismiss();
            startActivity(intent);
        }
        isShowing = false;
        isSubmitting = false;
    }

    private Uri saveMediaEntry(String s, String s1, long l, int i)
    {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("title", s1);
        contentvalues.put("_display_name", s1);
        contentvalues.put("date_added", Long.valueOf(l));
        contentvalues.put("datetaken", Long.valueOf(l));
        contentvalues.put("date_modified", Long.valueOf(l));
        contentvalues.put("mime_type", "image/jpeg");
        contentvalues.put("orientation", Integer.valueOf(i));
        File file = new File(s);
        File file1 = file.getParentFile();
        String s2 = file1.toString().toLowerCase();
        String s3 = file1.getName().toLowerCase();
        contentvalues.put("bucket_id", Integer.valueOf(s2.hashCode()));
        contentvalues.put("bucket_display_name", s3);
        contentvalues.put("_size", Long.valueOf(file.length()));
        contentvalues.put("_data", s);
        return getContentResolver().insert(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentvalues);
    }

    public String getDeviceName()
    {
        String s = Build.MANUFACTURER;
        String s1 = Build.MODEL;
        String s2;
        if(s1.startsWith(s))
            s2 = s1.toUpperCase();
        else
            s2 = (new StringBuilder()).append(s.toUpperCase()).append(" ").append(s1).toString();
        return s2;
    }

    public void submitDiagAndEmail(Window window)
    {
        if(!isSubmitting)
        {
            isSubmitting = true;
            backedOut = false;
            if(window != null && window.getDecorView() != null)
            {
                View view = window.getDecorView().findViewById(0x1020002);
                if(view != null)
                {
                    view.setDrawingCacheEnabled(true);
                    Bitmap bitmap1 = view.getDrawingCache();
                    if(bitmap1 != null)
                    {
                        bitmap = ImageUtils.createBitmap(bitmap1, 0.4F);
                        view.setDrawingCacheEnabled(false);
                        if(bitmap != null)
                            bitmap.setHasAlpha(false);
                    }
                }
            }
            isShowing = true;
            (new android.app.AlertDialog.Builder(this)).setMessage(getResources().getString(0x7f0c004d)).setPositiveButton(0x1040013, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    diagOp = LibraryUtils.getSCLibManager().getOpFactory().createSubmitDiagnosticsOp();
                    progressDialog = ProgressDialog.show(FeedbackHandler.this, "", getResources().getString(0x7f0c00a2), true, true, new android.content.DialogInterface.OnCancelListener() {

                        public void onCancel(DialogInterface dialoginterface1)
                        {
                            backedOut = true;
                            if(diagOp.isRunning())
                                diagOp._cancel();
                            FeedbackHandler feedbackhandler = _fld0;
                            isShowing = false;
                            feedbackhandler.isSubmitting = false;
                        }

                        final _cls3 this$1;

                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                    }
);
                    progressDialog.setProgressStyle(0);
                    SLog.v(FeedbackHandler.LOG_TAG, "Submitting feedback diag...");
                    if(diagOp != null)
                        diagOp._start(new SCIOpCBSwigBase() {

                            public void _operationComplete(long l, int j)
                            {
                                if(!backedOut)
                                    doSubmit(bitmap);
                            }

                            final _cls3 this$1;

                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                        }
);
                    else
                        doSubmit(bitmap);
                }

                final FeedbackHandler this$0;

            
            {
                this$0 = FeedbackHandler.this;
                super();
            }
            }
).setNegativeButton(getResources().getString(0x1040009), new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    FeedbackHandler feedbackhandler = FeedbackHandler.this;
                    isShowing = false;
                    feedbackhandler.isSubmitting = false;
                }

                final FeedbackHandler this$0;

            
            {
                this$0 = FeedbackHandler.this;
                super();
            }
            }
).setCancelable(true).setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

                public void onCancel(DialogInterface dialoginterface)
                {
                    FeedbackHandler feedbackhandler = FeedbackHandler.this;
                    isShowing = false;
                    feedbackhandler.isSubmitting = false;
                }

                final FeedbackHandler this$0;

            
            {
                this$0 = FeedbackHandler.this;
                super();
            }
            }
).show();
        }
    }

    public static final String LOG_TAG = com/sonos/acr/util/FeedbackHandler.getSimpleName();
    private boolean backedOut;
    Bitmap bitmap;
    SCIOpSubmitDiagnostics diagOp;
    public boolean isShowing;
    private boolean isSubmitting;
    private ProgressDialog progressDialog;



/*
    static boolean access$002(FeedbackHandler feedbackhandler, boolean flag)
    {
        feedbackhandler.isSubmitting = flag;
        return flag;
    }

*/



/*
    static ProgressDialog access$102(FeedbackHandler feedbackhandler, ProgressDialog progressdialog)
    {
        feedbackhandler.progressDialog = progressdialog;
        return progressdialog;
    }

*/



/*
    static boolean access$202(FeedbackHandler feedbackhandler, boolean flag)
    {
        feedbackhandler.backedOut = flag;
        return flag;
    }

*/

}

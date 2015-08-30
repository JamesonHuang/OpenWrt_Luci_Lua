// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.onlineupdate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.*;
import android.widget.TextView;
import com.sonos.acr.Loc;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.util.SLog;
import com.sonos.acr.util.SonosToast;
import com.sonos.acr.wizards.SonosWizardActivity;
import com.sonos.sclib.SCIOnlineUpdateWizard;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

// Referenced classes of package com.sonos.acr.wizards.onlineupdate:
//            OnlineUpdateWizardState, OnlineUpdateWizard

public class StateOUNeedsUpdating extends OnlineUpdateWizardState
{

    public StateOUNeedsUpdating(OnlineUpdateWizard onlineupdatewizard)
    {
        super(onlineupdatewizard, com.sonos.sclib.SCIOnlineUpdateWizard.OnlineUpdateWizardState.STATE_ONLINEUPDATE_CONTROLLER_NEEDS_UPDATING, 0x7f030049);
    }

    private void performUpdate(String s)
    {
        final SonosWizardActivity activity = ((OnlineUpdateWizard)sonosWizard).getActivity();
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(getString(0x7f0c009b));
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(1);
        AsyncTask asynctask = new AsyncTask() {

            private void showErrorMessage(Exception exception)
            {
                activity.runOnUiThread(new Runnable() {

                    public void run()
                    {
                        (new android.app.AlertDialog.Builder(activity)).setTitle(0x7f0c009d).setMessage(0x7f0c009c).setPositiveButton(0x104000a, null).show();
                    }

                    final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
                }
);
                SLog.e(
// JavaClassFileOutputException: get_constant: invalid tag

            protected volatile Object doInBackground(Object aobj[])
            {
                return doInBackground((String[])aobj);
            }

            protected transient String doInBackground(String as1[])
            {
                URL url;
                String s1;
                HttpURLConnection httpurlconnection;
                url = new URL(as1[0]);
                httpurlconnection = (HttpURLConnection)url.openConnection();
                SLog.d(
// JavaClassFileOutputException: get_constant: invalid tag

            protected volatile void onPostExecute(Object obj)
            {
                onPostExecute((String)obj);
            }

            protected void onPostExecute(String s1)
            {
                onPostExecute(s1);
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
            }

            protected void onPreExecute()
            {
                onPreExecute();
                progressDialog.show();
            }

            protected transient void onProgressUpdate(Integer ainteger[])
            {
                onProgressUpdate(ainteger);
                progressDialog.setProgress(ainteger[0].intValue());
            }

            protected volatile void onProgressUpdate(Object aobj[])
            {
                onProgressUpdate((Integer[])aobj);
            }

            final StateOUNeedsUpdating this$0;
            final SonosWizardActivity val$activity;

            
            {
                this$0 = StateOUNeedsUpdating.this;
                activity = sonoswizardactivity;
                super();
            }
        }
;
        String as[] = new String[1];
        as[0] = s;
        asynctask.execute(as);
    }

    protected void launchUri(Uri uri)
    {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        ((OnlineUpdateWizard)sonosWizard).getActivity().startActivity(intent);
_L1:
        return;
        Exception exception;
        exception;
        SonosToast.popupDialog(0x7f0c00a0);
          goto _L1
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup)
    {
        View view = onCreateView(layoutinflater, viewgroup);
        ((TextView)view.findViewById(0x7f0a0053)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                String s = getWizard().getOnlineUpdateURL();
                if(s == null || s.length() == 0 || s.contains("^"))
                {
                    SLog.e(, Loc.NOLOC("GetString OnlineUpdateBaseURL failed."));
                    SonosApplication sonosapplication = SonosApplication.getInstance();
                    Uri uri = Uri.parse((new StringBuilder()).append("market://details?id=").append(sonosapplication.getPackageName()).toString());
                    launchUri(uri);
                } else
                {
                    Uri uri1 = Uri.parse(s);
                    String s1 = uri1.getPath();
                    if(s1 != null)
                        if(s1.endsWith(".apk"))
                            performUpdate(s);
                        else
                            launchUri(uri1);
                    SLog.d(, (new StringBuilder()).append(Loc.NOLOC("Got OnlineUpdateBaseURL: ")).append(s).toString());
                }
            }

            final StateOUNeedsUpdating this$0;

            
            {
                this$0 = StateOUNeedsUpdating.this;
                super();
            }
        }
);
        return view;
    }

    ProgressDialog progressDialog;





}

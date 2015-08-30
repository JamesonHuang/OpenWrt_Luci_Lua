// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions.custdlg;

import android.content.DialogInterface;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.sclib.wrappers.Household;
import com.sonos.acr.uiactions.DisplayCustomControlAction;
import com.sonos.acr.util.SLog;
import com.sonos.acr.view.MusicEqDialog;
import com.sonos.sclib.*;

public class MusicEQDialogAction extends DisplayCustomControlAction
    implements com.sonos.acr.view.MusicEqDialog.ViewCompletedListener
{

    public MusicEQDialogAction(SonosActivity sonosactivity)
    {
        super(sonosactivity);
    }

    public void onViewCompleted()
    {
        pActionCtxt.actionHasCompleted(this);
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        pActionCtxt = sciactioncontext;
        SCIPropertyBag scipropertybag = sciactioncontext.getPropertyBag();
        if(scipropertybag.doesPropExist(sclibConstants.SCACTN_STRPROP_DEVICEID))
            SLog.d(LOG_TAG, (new StringBuilder()).append("DeviceID = ").append(scipropertybag.getStrProp(sclibConstants.SCACTN_STRPROP_DEVICEID)).toString());
        String s = scipropertybag.getStrProp(sclibConstants.SCACTN_STRPROP_DEVICEID);
        com.sonos.acr.sclib.wrappers.ZoneDevice zonedevice = currentContext.getHousehold().lookupDevice(s);
        musicEqDialog = new MusicEqDialog(currentContext);
        SCActionCompletionStatus scactioncompletionstatus;
        if(musicEqDialog.showEqView(zonedevice, this))
            scactioncompletionstatus = SCActionCompletionStatus.WAIT_FOR_CALLBACK;
        else
            scactioncompletionstatus = SCActionCompletionStatus.DONE_WITH_ACTION;
        return scactioncompletionstatus;
    }

    protected void terminate(DialogInterface dialoginterface)
    {
        musicEqDialog.dismiss();
    }

    private MusicEqDialog musicEqDialog;
    private SCIActionContext pActionCtxt;
}

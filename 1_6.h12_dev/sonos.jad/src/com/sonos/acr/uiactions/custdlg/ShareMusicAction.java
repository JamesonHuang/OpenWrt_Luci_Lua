// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions.custdlg;

import com.sonos.acr.SonosActivity;
import com.sonos.acr.uiactions.DisplayCustomControlAction;
import com.sonos.sclib.*;

public class ShareMusicAction extends DisplayCustomControlAction
{

    public ShareMusicAction(SonosActivity sonosactivity)
    {
        super(sonosactivity);
        m_sonosActivity = null;
        m_sonosActivity = sonosactivity;
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        SCActionCompletionStatus scactioncompletionstatus;
        if(sciactioncontext == null)
        {
            scactioncompletionstatus = SCActionCompletionStatus.DONE_WITH_ACTION;
        } else
        {
            String s = sciactioncontext.getPropertyBag().getStrProp(sclibConstants.SCACTN_STRPROP_SHARE_STRING);
            m_sonosActivity.showShareNowPlaying(s);
            scactioncompletionstatus = SCActionCompletionStatus.DONE_WITH_ACTION;
        }
        return scactioncompletionstatus;
    }

    private SonosActivity m_sonosActivity;
}

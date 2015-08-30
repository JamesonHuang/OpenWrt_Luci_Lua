// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.uiactions.custdlg.AboutSonosDialogAction;
import com.sonos.acr.uiactions.custdlg.AddShareDialogAction;
import com.sonos.acr.uiactions.custdlg.AlarmFrequencySelectAction;
import com.sonos.acr.uiactions.custdlg.DealerModeDialogAction;
import com.sonos.acr.uiactions.custdlg.MoreMusicAction;
import com.sonos.acr.uiactions.custdlg.MusicEQDialogAction;
import com.sonos.acr.uiactions.custdlg.ResetDialogAction;
import com.sonos.acr.uiactions.custdlg.RoomSettingsAction;
import com.sonos.acr.uiactions.custdlg.SUBEQDialogAction;
import com.sonos.acr.uiactions.custdlg.ShareMusicAction;
import com.sonos.acr.uiactions.custdlg.SurroundEQDialogAction;
import com.sonos.acr.uiactions.custdlg.TVDialogSettingsAction;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction, DisplayTextPaneAction

public abstract class DisplayCustomControlAction extends UIAction
{

    protected DisplayCustomControlAction(SonosActivity sonosactivity)
    {
        super(sonosactivity);
        m_alertDialogBuilder = new android.app.AlertDialog.Builder(sonosactivity);
    }

    public static SCIAction createAction(SonosActivity sonosactivity, SCDisplayCustomControlActionType scdisplaycustomcontrolactiontype)
    {
        class _cls2
        {

            static final int $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType = new int[SCDisplayCustomControlActionType.values().length];
                NoSuchFieldError nosuchfielderror12;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType[SCDisplayCustomControlActionType.SCACTN_CUSTOMCTL_ABOUTSONOS.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType[SCDisplayCustomControlActionType.SCACTN_CUSTOMCTL_DEALERMODE.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType[SCDisplayCustomControlActionType.SCACTN_CUSTOMCTL_MUSICEQ.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType[SCDisplayCustomControlActionType.SCACTN_CUSTOMCTL_SUBEQ.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType[SCDisplayCustomControlActionType.SCACTN_CUSTOMCTL_RESETCONTROLLER.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType[SCDisplayCustomControlActionType.SCACTN_CUSTOMCTL_ALARMFREQUENCYSELECT.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType[SCDisplayCustomControlActionType.SCACTN_CUSTOMCTL_ADDSHARE.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType[SCDisplayCustomControlActionType.SCACTN_CUSTOMCTL_TVDIALOGSETTINGS.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType[SCDisplayCustomControlActionType.SCACTN_CUSTOMCTL_SURROUNDSETTINGS.ordinal()] = 9;
                }
                catch(NoSuchFieldError nosuchfielderror8) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType[SCDisplayCustomControlActionType.SCACTN_CUSTOMCTL_MOREMUSIC.ordinal()] = 10;
                }
                catch(NoSuchFieldError nosuchfielderror9) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType[SCDisplayCustomControlActionType.SCACTN_CUSTOMCTL_MOREINFO.ordinal()] = 11;
                }
                catch(NoSuchFieldError nosuchfielderror10) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType[SCDisplayCustomControlActionType.SCACTN_CUSTOMCTL_ROOMSETTINGS.ordinal()] = 12;
                }
                catch(NoSuchFieldError nosuchfielderror11) { }
                $SwitchMap$com$sonos$sclib$SCDisplayCustomControlActionType[SCDisplayCustomControlActionType.SCACTN_CUSTOMCTL_SOCIAL_SHARING.ordinal()] = 13;
_L2:
                return;
                nosuchfielderror12;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls2..SwitchMap.com.sonos.sclib.SCDisplayCustomControlActionType[scdisplaycustomcontrolactiontype.ordinal()];
        JVM INSTR tableswitch 1 13: default 76
    //                   1 80
    //                   2 92
    //                   3 104
    //                   4 116
    //                   5 128
    //                   6 140
    //                   7 152
    //                   8 164
    //                   9 176
    //                   10 188
    //                   11 200
    //                   12 263
    //                   13 275;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L1:
        Object obj = null;
_L16:
        return ((SCIAction) (obj));
_L2:
        obj = new AboutSonosDialogAction(sonosactivity);
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new DealerModeDialogAction(sonosactivity);
        continue; /* Loop/switch isn't completed */
_L4:
        obj = new MusicEQDialogAction(sonosactivity);
        continue; /* Loop/switch isn't completed */
_L5:
        obj = new SUBEQDialogAction(sonosactivity);
        continue; /* Loop/switch isn't completed */
_L6:
        obj = new ResetDialogAction(sonosactivity);
        continue; /* Loop/switch isn't completed */
_L7:
        obj = new AlarmFrequencySelectAction(sonosactivity);
        continue; /* Loop/switch isn't completed */
_L8:
        obj = new AddShareDialogAction(sonosactivity);
        continue; /* Loop/switch isn't completed */
_L9:
        obj = new TVDialogSettingsAction(sonosactivity);
        continue; /* Loop/switch isn't completed */
_L10:
        obj = new SurroundEQDialogAction(sonosactivity);
        continue; /* Loop/switch isn't completed */
_L11:
        obj = new MoreMusicAction(sonosactivity);
        continue; /* Loop/switch isn't completed */
_L12:
        obj = new DisplayTextPaneAction(sonosactivity, sonosactivity.getResources().getString(0x7f0c006c), (new StringBuilder()).append(sonosactivity.getLibrary().getRecommendedText(SCILibrary.SC_STRID_PLAYBAR_INFORMATION)).append("\n\n").append(sonosactivity.getLibrary().getRecommendedURL(SCILibrary.SC_URL_PLAYBAR_INFORMATION)).toString(), null);
        continue; /* Loop/switch isn't completed */
_L13:
        obj = new RoomSettingsAction(sonosactivity);
        continue; /* Loop/switch isn't completed */
_L14:
        obj = new ShareMusicAction(sonosactivity);
        if(true) goto _L16; else goto _L15
_L15:
    }

    public abstract SCActionCompletionStatus perform(SCIActionContext sciactioncontext);

    protected void terminate(DialogInterface dialoginterface)
    {
        dialoginterface.dismiss();
    }

    protected final String LOG_TAG = getClass().getSimpleName();
    protected AlertDialog m_alertDialog;
    protected android.app.AlertDialog.Builder m_alertDialogBuilder;
    protected final SCIOpCBSwigBase noopOperationCallback = new SCIOpCBSwigBase() {

        public void _operationComplete(long l, int i)
        {
        }

        final DisplayCustomControlAction this$0;

            
            {
                this$0 = DisplayCustomControlAction.this;
                super();
            }
    }
;
}

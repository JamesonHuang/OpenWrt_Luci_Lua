// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.services.notification;

import android.content.*;
import android.os.Bundle;
import android.view.KeyEvent;
import com.sonos.acr.util.LibraryUtils;
import com.sonos.acr.util.SLog;

public class MusicIntentReceiver extends BroadcastReceiver
{

    public MusicIntentReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        KeyEvent keyevent;
        String s = intent.getAction();
        SLog.i(LOG_TAG, (new StringBuilder()).append("Got receieved a media button action: ").append(s).append(" Extras: ").append(intent.getExtras()).toString());
        if(s.equals("android.intent.action.MEDIA_BUTTON"))
        {
            keyevent = (KeyEvent)intent.getExtras().get("android.intent.extra.KEY_EVENT");
            break MISSING_BLOCK_LABEL_65;
        }
_L6:
        Intent intent1;
        do
            return;
        while(keyevent.getAction() != 0 || !sharedPreferences.getBoolean("EXTERNAL_CONTROLS", false) && (8 & keyevent.getFlags()) == 8);
        intent1 = null;
        keyevent.getKeyCode();
        JVM INSTR lookupswitch 6: default 168
    //                   85: 204
    //                   86: 204
    //                   87: 218
    //                   88: 232
    //                   126: 204
    //                   127: 204;
           goto _L1 _L2 _L2 _L3 _L4 _L2 _L2
_L4:
        break MISSING_BLOCK_LABEL_232;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L7:
        if(intent1 != null)
        {
            intent1.putExtra("com.sonos.intent.extra.ZGID", "CURRENT_ZONEGROUP");
            intent1.putExtra("com.sonos.intent.extra.VIEW_ID", com.sonos.sclib.SCIAppReporting.SCViewID.LOCKSCREEN);
            context.startService(intent1);
        }
        if(true) goto _L6; else goto _L5
_L5:
        intent1 = new Intent("com.sonos.intent.action.TRANSPORT_PLAYPAUSE");
          goto _L7
_L3:
        intent1 = new Intent("com.sonos.intent.action.TRANSPORT_NEXT");
          goto _L7
        intent1 = new Intent("com.sonos.intent.action.TRANSPORT_PREV");
          goto _L7
    }

    public static final String EXTERNAL_CONTROLS = "EXTERNAL_CONTROLS";
    private static final String LOG_TAG = com/sonos/acr/services/notification/MusicIntentReceiver.getSimpleName();
    private static final SharedPreferences sharedPreferences = LibraryUtils.getSharedPreferences();

}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import com.sonos.acr.SonosActivity;
import com.sonos.acr.util.SLog;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class NavigationAction extends UIAction
{

    public NavigationAction(SonosActivity sonosactivity, SCNavigationActionType scnavigationactiontype, SCIPropertyBag scipropertybag)
    {
        super(sonosactivity);
        navActionType = scnavigationactiontype;
        propertyBag = scipropertybag;
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        if(propertyBag == null)
            propertyBag = sciactioncontext.getPropertyBag();
        SLog.i(LOG_TAG, "Dumping the property bag");
        SCIStringArray scistringarray = propertyBag.getKeys();
        for(int i = 0; (long)i < scistringarray.size(); i++)
        {
            String s = scistringarray.getAt(i);
            SLog.i(LOG_TAG, (new StringBuilder()).append("Property Found: ").append(s).append(" value: ").append(propertyBag.getStrProp(s)).toString());
        }

        SLog.i(LOG_TAG, "Finished dumping the property bag");
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$SCNavigationActionType[];

            static 
            {
                $SwitchMap$com$sonos$sclib$SCNavigationActionType = new int[SCNavigationActionType.values().length];
                NoSuchFieldError nosuchfielderror4;
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNavigationActionType[SCNavigationActionType.SCACTN_NAVTO_MUSICMENU.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNavigationActionType[SCNavigationActionType.SCACTN_NAVTO_NOWPLAYING.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNavigationActionType[SCNavigationActionType.SCACTN_NAVTO_SETTINGS.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$SCNavigationActionType[SCNavigationActionType.SCACTN_NAVTO_ALARMS.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                $SwitchMap$com$sonos$sclib$SCNavigationActionType[SCNavigationActionType.SCACTN_NAVTO_SEARCH.ordinal()] = 5;
_L2:
                return;
                nosuchfielderror4;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.SCNavigationActionType[navActionType.ordinal()];
        JVM INSTR tableswitch 1 5: default 152
    //                   1 156
    //                   2 178
    //                   3 200
    //                   4 210
    //                   5 220;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return SCActionCompletionStatus.DONE_WITH_ACTION;
_L2:
        if(!propertyBag.doesPropExist("fromRoomsMenu"))
            currentContext.showBrowseMusic();
        continue; /* Loop/switch isn't completed */
_L3:
        if(!propertyBag.doesPropExist("fromRoomsMenu"))
            currentContext.showNowPlaying();
        continue; /* Loop/switch isn't completed */
_L4:
        currentContext.showSettings();
        continue; /* Loop/switch isn't completed */
_L5:
        currentContext.showAlarmSettings();
        continue; /* Loop/switch isn't completed */
_L6:
        currentContext.showSearch(propertyBag);
        if(true) goto _L1; else goto _L7
_L7:
    }

    public static final String FROM_ROOMS_MENU = "fromRoomsMenu";
    private static final String LOG_TAG = com/sonos/acr/uiactions/NavigationAction.getSimpleName();
    private SCNavigationActionType navActionType;
    private SCIPropertyBag propertyBag;

}

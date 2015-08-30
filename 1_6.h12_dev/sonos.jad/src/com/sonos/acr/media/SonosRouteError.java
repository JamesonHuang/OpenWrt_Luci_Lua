// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.media;

import android.content.Context;
import android.content.res.Resources;

public final class SonosRouteError extends Enum
{

    private SonosRouteError(String s, int i, int j)
    {
        super(s, i);
        code = j;
    }

    public static SonosRouteError valueOf(String s)
    {
        return (SonosRouteError)Enum.valueOf(com/sonos/acr/media/SonosRouteError, s);
    }

    public static SonosRouteError[] values()
    {
        return (SonosRouteError[])$VALUES.clone();
    }

    public int getCode()
    {
        return code;
    }

    public transient String getLocString(Context context, String as[])
    {
        Resources resources = context.getResources();
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(code);
        String s = String.format(resources.getString(0x7f0c0070, aobj), new Object[0]);
        String s1;
        if(code == UNSUPPORTED_INTENT_CATEGORY.getCode())
            s1 = context.getResources().getString(0x7f0c0076);
        else
        if(code == SONOS_GROUP_NOT_FOUND_FOR_ROUTE.getCode())
        {
            Resources resources7 = context.getResources();
            Object aobj7[] = new Object[1];
            aobj7[0] = as[0];
            s1 = String.format(resources7.getString(0x7f0c0071, aobj7), new Object[0]);
        } else
        if(code == INVALID_SESSION_ID.getCode())
        {
            Resources resources6 = context.getResources();
            Object aobj6[] = new Object[1];
            aobj6[0] = Integer.valueOf(code);
            s1 = String.format(resources6.getString(0x7f0c0072, aobj6), new Object[0]);
        } else
        if(code == MISSING_PENDING_INTENT.getCode())
        {
            Resources resources5 = context.getResources();
            Object aobj5[] = new Object[1];
            aobj5[0] = Integer.valueOf(code);
            s1 = String.format(resources5.getString(0x7f0c006e, aobj5), new Object[0]);
        } else
        if(code == NO_ITEM_SPECIFIED_IN_REMOVE_ACTION.getCode())
        {
            Resources resources4 = context.getResources();
            Object aobj4[] = new Object[1];
            aobj4[0] = Integer.valueOf(code);
            s1 = String.format(resources4.getString(0x7f0c0073, aobj4), new Object[0]);
        } else
        if(code == CANNOT_SEEK_IN_ITEM.getCode())
        {
            Resources resources3 = context.getResources();
            Object aobj3[] = new Object[1];
            aobj3[0] = Integer.valueOf(code);
            s1 = String.format(resources3.getString(0x7f0c006f, aobj3), new Object[0]);
        } else
        {
            if(code != CLIENT_APP_REJECTED.getCode())
                continue;
            Resources resources2 = context.getResources();
            Object aobj2[] = new Object[1];
            aobj2[0] = Integer.valueOf(code);
            s1 = String.format(resources2.getString(0x7f0c006e, aobj2), new Object[0]);
        }
        do
        {
            s = (new StringBuilder()).append(s1).append(" (").append(s).append(")").toString();
            do
                return s;
            while(code != CANNOT_CREATE_SESSION.getCode());
            Resources resources1 = context.getResources();
            Object aobj1[] = new Object[1];
            aobj1[0] = Integer.valueOf(code);
            s1 = String.format(resources1.getString(0x7f0c006e, aobj1), new Object[0]);
        } while(true);
    }

    private static final SonosRouteError $VALUES[];
    public static final SonosRouteError CANNOT_CREATE_SESSION;
    public static final SonosRouteError CANNOT_SEEK_IN_ITEM;
    public static final SonosRouteError CLIENT_APP_REJECTED;
    public static final SonosRouteError INVALID_SESSION_ID;
    public static final SonosRouteError MISSING_PENDING_INTENT;
    public static final SonosRouteError NO_ITEM_SPECIFIED_IN_REMOVE_ACTION;
    public static final SonosRouteError SONOS_GROUP_NOT_FOUND_FOR_ROUTE;
    public static final SonosRouteError UNSUPPORTED_INTENT_CATEGORY;
    private int code;

    static 
    {
        UNSUPPORTED_INTENT_CATEGORY = new SonosRouteError("UNSUPPORTED_INTENT_CATEGORY", 0, 1);
        SONOS_GROUP_NOT_FOUND_FOR_ROUTE = new SonosRouteError("SONOS_GROUP_NOT_FOUND_FOR_ROUTE", 1, 2);
        INVALID_SESSION_ID = new SonosRouteError("INVALID_SESSION_ID", 2, 3);
        MISSING_PENDING_INTENT = new SonosRouteError("MISSING_PENDING_INTENT", 3, 4);
        NO_ITEM_SPECIFIED_IN_REMOVE_ACTION = new SonosRouteError("NO_ITEM_SPECIFIED_IN_REMOVE_ACTION", 4, 5);
        CANNOT_SEEK_IN_ITEM = new SonosRouteError("CANNOT_SEEK_IN_ITEM", 5, 6);
        CLIENT_APP_REJECTED = new SonosRouteError("CLIENT_APP_REJECTED", 6, 7);
        CANNOT_CREATE_SESSION = new SonosRouteError("CANNOT_CREATE_SESSION", 7, 8);
        SonosRouteError asonosrouteerror[] = new SonosRouteError[8];
        asonosrouteerror[0] = UNSUPPORTED_INTENT_CATEGORY;
        asonosrouteerror[1] = SONOS_GROUP_NOT_FOUND_FOR_ROUTE;
        asonosrouteerror[2] = INVALID_SESSION_ID;
        asonosrouteerror[3] = MISSING_PENDING_INTENT;
        asonosrouteerror[4] = NO_ITEM_SPECIFIED_IN_REMOVE_ACTION;
        asonosrouteerror[5] = CANNOT_SEEK_IN_ITEM;
        asonosrouteerror[6] = CLIENT_APP_REJECTED;
        asonosrouteerror[7] = CANNOT_CREATE_SESSION;
        $VALUES = asonosrouteerror;
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.sclib;

import com.sonos.sclib.*;
import java.util.Calendar;
import java.util.TimeZone;

public class PlatformDateTimeProvider extends SCIPlatformDateTimeProvider
{

    public PlatformDateTimeProvider()
    {
    }

    public boolean doesPlatformTimeZoneMatch(SCITimeZone scitimezone)
    {
        TimeZone timezone;
        boolean flag;
        int i;
        timezone = TimeZone.getDefault();
        flag = scitimezone.getAutoAdjustForDST();
        i = scitimezone.getUTCOffset();
        if(!flag || !timezone.useDaylightTime()) goto _L2; else goto _L1
_L1:
        if(timezone.getRawOffset() != 1000 * (i * 60)) goto _L4; else goto _L3
_L3:
        boolean flag1;
        SCISystemTime scisystemtime = scitimezone.getDSTStartTime();
        SCISystemTime scisystemtime1 = scitimezone.getDSTEndTime();
        int j = scitimezone.getDSTOffset();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2, -1 + scisystemtime.getMonth());
        calendar.set(7, 1 + scisystemtime.getDayOfWeek());
        calendar.set(8, scisystemtime.getDay());
        calendar.set(11, scisystemtime.getHour());
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        long l = calendar.getTimeInMillis();
        int k = timezone.getOffset(l);
        int i1;
        int j1;
        long l1;
        int k1;
        int i2;
        if(j < 0)
            i1 = 1000 + 1000 * (j * 60);
        else
            i1 = 1000;
        j1 = timezone.getOffset(l - (long)i1);
        calendar.set(2, -1 + scisystemtime1.getMonth());
        calendar.set(7, 1 + scisystemtime1.getDayOfWeek());
        calendar.set(8, scisystemtime1.getDay());
        calendar.set(11, scisystemtime1.getHour());
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        l1 = calendar.getTimeInMillis();
        k1 = timezone.getOffset(l1);
        if(j > 0)
            i2 = 1000 + 1000 * (j * 60);
        else
            i2 = 1000;
        if(timezone.getOffset(l1 - (long)i2) != k || k != 1000 * (60 * (i + j)) || j1 != k1 || k1 != 1000 * (i * 60)) goto _L4; else goto _L5
_L5:
        flag1 = true;
_L7:
        return flag1;
_L2:
        if(!flag && !timezone.useDaylightTime())
        {
            if(timezone.getRawOffset() == 1000 * (i * 60))
                flag1 = true;
            else
                flag1 = false;
            continue; /* Loop/switch isn't completed */
        }
_L4:
        flag1 = false;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public SCISystemTime getPlatformDateTime()
    {
        Calendar calendar = Calendar.getInstance();
        SCISystemTime scisystemtime = sclib.createSCSystemTime();
        scisystemtime.setYear(calendar.get(1));
        scisystemtime.setMonth(1 + calendar.get(2));
        scisystemtime.setDay(calendar.get(5));
        scisystemtime.setDayOfWeek(-1 + calendar.get(7));
        scisystemtime.setHour(calendar.get(11));
        scisystemtime.setMinute(calendar.get(12));
        scisystemtime.setSecond(calendar.get(13));
        return scisystemtime;
    }
}

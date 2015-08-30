// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.sonos.acr.application.SonosApplication;
import com.sonos.acr.sclib.EnumeratorAdapter;
import com.sonos.acr.sclib.SCLibManager;
import com.sonos.acr.sclib.wrappers.*;
import com.sonos.sclib.*;
import java.lang.reflect.Constructor;
import java.util.*;

// Referenced classes of package com.sonos.acr.util:
//            SLog, StringUtils

public class LibraryUtils
{

    public LibraryUtils()
    {
    }

    public static SCIObj cast(SCIObj sciobj, Class class1)
    {
        SCIObj sciobj1;
        if(sciobj != null)
        {
            if(class1.isAssignableFrom(sciobj.getClass()))
                sciobj1 = sciobj;
            else
                sciobj1 = sciobj.queryInterface(class1.getSimpleName());
        } else
        {
            sciobj1 = null;
        }
        return sciobj1;
    }

    public static SCIBrowseDataSource createBrowseDataSource(SCIBrowseItem scibrowseitem)
    {
        return getSCLibManager().getLibrary().createBrowseDataSource(scibrowseitem);
    }

    public static SCIBrowseDataSource createBrowseDataSource(String s, String s1)
    {
        return getSCLibManager().getLibrary().createBrowseDataSource(s, s1);
    }

    public static SCIBrowseDataSource createBrowseDataSource(String s, String s1, SCIStringArray scistringarray)
    {
        return getSCLibManager().getLibrary().createBrowseDataSource(s, s1, scistringarray);
    }

    public static Properties createProperties(SCIPropertyBag scipropertybag)
    {
        Properties properties = new Properties();
        SCIStringArray scistringarray = scipropertybag.getKeys();
        for(int i = 0; (long)i < scistringarray.size(); i++)
        {
            String s = scistringarray.getAt(i);
            properties.put(s, scipropertybag.getStrProp(s).intern());
        }

        return properties;
    }

    public static ZoneGroup getCurrentZoneGroup()
    {
        Household household = getHousehold();
        ZoneGroup zonegroup;
        if(household != null)
        {
            zonegroup = household.getCurrentZoneGroup();
        } else
        {
            SLog.e("LibraryUtils", "getCurrentZoneGroup failed: household is null");
            zonegroup = null;
        }
        return zonegroup;
    }

    public static Household getHousehold()
    {
        SCLibManager sclibmanager = getSCLibManager();
        Household household;
        if(sclibmanager != null)
        {
            household = sclibmanager.getHousehold();
        } else
        {
            SLog.e("LibraryUtils", "getHousehold failed: sclibmanager is null");
            household = null;
        }
        return household;
    }

    public static SharedPreferences getHouseholdPreferences()
    {
        Household household = getHousehold();
        SharedPreferences sharedpreferences = getSharedPreferences();
        SharedPreferences sharedpreferences1;
        if(household != null && StringUtils.isNotEmptyOrNull(household.getID()))
        {
            String s = (new StringBuilder()).append("SonosPreferences_").append(household.getID()).toString();
            sharedpreferences1 = SonosApplication.getInstance().getApplicationContext().getSharedPreferences(s, 0);
            if(!sharedpreferences1.contains("COPIED_FLAG"))
            {
                android.content.SharedPreferences.Editor editor = sharedpreferences1.edit();
                Map map = sharedpreferences.getAll();
                Iterator iterator = map.keySet().iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    String s1 = (String)iterator.next();
                    Object obj = map.get(s1);
                    if(obj instanceof String)
                        editor.putString(s1, (String)obj);
                    else
                    if(obj instanceof Long)
                        editor.putLong(s1, ((Long)obj).longValue());
                    else
                    if(obj instanceof Integer)
                        editor.putInt(s1, ((Integer)obj).intValue());
                    else
                    if(obj instanceof Boolean)
                        editor.putBoolean(s1, ((Boolean)obj).booleanValue());
                    else
                    if(obj instanceof Float)
                        editor.putFloat(s1, ((Float)obj).floatValue());
                } while(true);
                editor.putBoolean("COPIED_FLAG", true).commit();
            }
        } else
        {
            SLog.e("LibraryUtils", "getCurrentZoneGroup failed: household is null");
            SLog.e("LibraryUtils", "Requesting Default Preference");
            sharedpreferences1 = sharedpreferences;
        }
        return sharedpreferences1;
    }

    public static SCILogoArtworkCache getLogoCache()
    {
        SCLibManager sclibmanager = getSCLibManager();
        SCILogoArtworkCache scilogoartworkcache;
        if(sclibmanager != null)
        {
            scilogoartworkcache = sclibmanager.getLogoCache();
        } else
        {
            SLog.e("LibraryUtils", "getLogoCache failed: sclibmanager is null");
            scilogoartworkcache = null;
        }
        return scilogoartworkcache;
    }

    public static SCINowPlayingSleepTimer getNowPlayingSleepTimer()
    {
        ZoneGroup zonegroup = getCurrentZoneGroup();
        SCINowPlayingSleepTimer scinowplayingsleeptimer;
        if(zonegroup != null)
        {
            scinowplayingsleeptimer = zonegroup.nowPlaying.getNowPlayingSleepTimer();
        } else
        {
            SLog.e("LibraryUtils", "getNowPlayingSleepTimer failed: zonegroup is null");
            scinowplayingsleeptimer = null;
        }
        return scinowplayingsleeptimer;
    }

    public static SCLibManager getSCLibManager()
    {
        return SonosApplication.getInstance().getSCLibManager();
    }

    public static String getSearchableCPID(String s)
    {
        Household household = getHousehold();
        if(household == null || !StringUtils.isNotEmptyOrNull(s)) goto _L2; else goto _L1
_L1:
        ArrayList arraylist = household.getAllSearchables();
        if(arraylist == null) goto _L2; else goto _L3
_L3:
        String s1;
        Iterator iterator;
        s1 = sclib.SCLibGetBrowseCPIDFromSCUri(s);
        iterator = arraylist.iterator();
_L6:
        if(!iterator.hasNext()) goto _L2; else goto _L4
_L4:
        if(!((SCISearchable)iterator.next()).getBrowseCPID().equals(s1)) goto _L6; else goto _L5
_L5:
        return s1;
_L2:
        s1 = null;
        if(true) goto _L5; else goto _L7
_L7:
    }

    public static SharedPreferences getSharedPreferences()
    {
        return sharedPreferences;
    }

    public static SCISystem getSystem()
    {
        SCLibManager sclibmanager = getSCLibManager();
        SCISystem scisystem;
        if(sclibmanager != null)
        {
            scisystem = sclibmanager.getSystem();
        } else
        {
            SLog.e("LibraryUtils", "getSystem failed: sclibmanager is null");
            scisystem = null;
        }
        return scisystem;
    }

    public static SCIZoneGroupMgr getZoneGroupMgr()
    {
        Household household = getHousehold();
        SCIZoneGroupMgr scizonegroupmgr;
        if(household != null)
            scizonegroupmgr = household.getZoneGroupManager();
        else
            scizonegroupmgr = null;
        return scizonegroupmgr;
    }

    public static boolean householdHasTransientOrphanedGroups()
    {
        return householdHasTransientOrphanedGroups(getHousehold());
    }

    public static boolean householdHasTransientOrphanedGroups(Household household)
    {
        return household.hasTransientOrphanedZoneGroups();
    }

    public static boolean isAssociatedToHousehold()
    {
        SCLibManager sclibmanager = getSCLibManager();
        boolean flag;
        if(sclibmanager != null)
            flag = sclibmanager.isAssociatedToHousehold();
        else
            flag = false;
        return flag;
    }

    public static EnumeratorAdapter iter(SCIEnumerator scienumerator, Class class1)
    {
        return new EnumeratorAdapter(scienumerator, class1.getSimpleName());
    }

    public static void showLinkErrors()
    {
        if(System.getProperty("os.arch").toUpperCase().startsWith("ARM"))
            Toast.makeText(SonosApplication.getInstance(), 0x7f0c0107, 1).show();
        else
            Toast.makeText(SonosApplication.getInstance(), 0x7f0c0108, 1).show();
    }

    public static ArrayList toList(SCIEnumerator scienumerator, Class class1)
    {
        ArrayList arraylist = new ArrayList(scienumerator.count());
        for(Iterator iterator = (new EnumeratorAdapter(scienumerator, class1.getSimpleName())).iterator(); iterator.hasNext(); arraylist.add((SCIObj)iterator.next()));
        return arraylist;
    }

    public static ArrayList toList(SCIEnumerator scienumerator, Class class1, Class class2)
    {
        long l = System.currentTimeMillis();
        ArrayList arraylist = new ArrayList(scienumerator.count());
        try
        {
            Constructor constructor;
            Object aobj[];
            for(Iterator iterator = (new EnumeratorAdapter(scienumerator, class1.getSimpleName())).iterator(); iterator.hasNext(); arraylist.add(constructor.newInstance(aobj)))
            {
                SCIObj sciobj = (SCIObj)iterator.next();
                Class aclass[] = new Class[1];
                aclass[0] = class1;
                constructor = class2.getDeclaredConstructor(aclass);
                aobj = new Object[1];
                aobj[0] = sciobj;
            }

        }
        catch(Exception exception)
        {
            SLog.e("LibraryUtils", "Error building list from enumerator!", exception);
        }
        SLog.i("LibraryUtils", (new StringBuilder()).append("SCIEnumerator To ArrayList took: ").append(System.currentTimeMillis() - l).append("ms").append(" for ").append(arraylist.size()).append(" items").toString());
        return arraylist;
    }

    public static final String COPIED_FLAG = "COPIED_FLAG";
    private static final String LOG_TAG = "LibraryUtils";
    private static final SharedPreferences sharedPreferences = SonosApplication.getInstance().getApplicationContext().getSharedPreferences("SonosPreferences", 0);

}

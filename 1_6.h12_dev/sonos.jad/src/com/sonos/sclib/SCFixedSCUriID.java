// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCFixedSCUriID extends Enum
{
    private static class SwigNext
    {

        private static int next = 0;



/*
        static int access$002(int i)
        {
            next = i;
            return i;
        }

*/


/*
        static int access$008()
        {
            int i = next;
            next = i + 1;
            return i;
        }

*/

        private SwigNext()
        {
        }
    }


    private SCFixedSCUriID(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCFixedSCUriID(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCFixedSCUriID(String s, int i, SCFixedSCUriID scfixedscuriid)
    {
        Enum(s, i);
        swigValue = scfixedscuriid.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCFixedSCUriID swigToEnum(int i)
    {
        SCFixedSCUriID ascfixedscuriid[] = (SCFixedSCUriID[])com/sonos/sclib/SCFixedSCUriID.getEnumConstants();
        if(i >= ascfixedscuriid.length || i < 0 || ascfixedscuriid[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCFixedSCUriID scfixedscuriid1 = ascfixedscuriid[i];
_L4:
        return scfixedscuriid1;
_L2:
        int j = ascfixedscuriid.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCFixedSCUriID scfixedscuriid = ascfixedscuriid[k];
            if(scfixedscuriid.swigValue == i)
            {
                scfixedscuriid1 = scfixedscuriid;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCFixedSCUriID).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCFixedSCUriID valueOf(String s)
    {
        return (SCFixedSCUriID)Enum.valueOf(com/sonos/sclib/SCFixedSCUriID, s);
    }

    public static SCFixedSCUriID[] values()
    {
        return (SCFixedSCUriID[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCFixedSCUriID $VALUES[];
    public static final SCFixedSCUriID SC_FIXEDSCURI_AdvancedSettings_AudioCompression;
    public static final SCFixedSCUriID SC_FIXEDSCURI_AdvancedSettings_AutoCheck4Updates;
    public static final SCFixedSCUriID SC_FIXEDSCURI_AdvancedSettings_Beta;
    public static final SCFixedSCUriID SC_FIXEDSCURI_AdvancedSettings_Registration;
    public static final SCFixedSCUriID SC_FIXEDSCURI_AdvancedSettings_ResetController;
    public static final SCFixedSCUriID SC_FIXEDSCURI_AdvancedSettings_ShowMediaServers;
    public static final SCFixedSCUriID SC_FIXEDSCURI_AdvancedSettings_ShowRhapsodyUPnP;
    public static final SCFixedSCUriID SC_FIXEDSCURI_AdvancedSettings_SubmitDiags;
    public static final SCFixedSCUriID SC_FIXEDSCURI_AdvancedSettings_UpdateIndex;
    public static final SCFixedSCUriID SC_FIXEDSCURI_AdvancedSettings_UsageData;
    public static final SCFixedSCUriID SC_FIXEDSCURI_AdvancedSettings_WirelessChannel;
    public static final SCFixedSCUriID SC_FIXEDSCURI_DateAndTime_Date;
    public static final SCFixedSCUriID SC_FIXEDSCURI_DateAndTime_InternetTime;
    public static final SCFixedSCUriID SC_FIXEDSCURI_DateAndTime_Time;
    public static final SCFixedSCUriID SC_FIXEDSCURI_DateAndTime_TimeZone;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Favorites;
    public static final SCFixedSCUriID SC_FIXEDSCURI_HT_Sources;
    public static final SCFixedSCUriID SC_FIXEDSCURI_MusicLibrary;
    public static final SCFixedSCUriID SC_FIXEDSCURI_MusicLibrary_CompilationAlbums;
    public static final SCFixedSCUriID SC_FIXEDSCURI_MusicLibrary_SchedUpdateIndex;
    public static final SCFixedSCUriID SC_FIXEDSCURI_MusicLibrary_SchedUpdateIndexTime;
    public static final SCFixedSCUriID SC_FIXEDSCURI_MusicLibrary_SortFolderBy;
    public static final SCFixedSCUriID SC_FIXEDSCURI_MusicLibrary_UpdateIndex;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Playlists;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Root;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Services;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Services_MusicServiceCatalog;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Services_MyMusicServices;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings_AboutSonos;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings_AddBoost;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings_AddPlayerOrSub;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings_Advanced;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings_AlarmMusic;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings_Alarms;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings_Controller;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings_DateAndTime;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings_Docks;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings_MusicLibraryManagement;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings_MusicShares;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings_OnlineUpdate;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings_ZoneBridges;
    public static final SCFixedSCUriID SC_FIXEDSCURI_Settings_ZonePlayers;
    private final int swigValue;

    static 
    {
        SC_FIXEDSCURI_Root = new SCFixedSCUriID("SC_FIXEDSCURI_Root", 0, sclibJNI.SC_FIXEDSCURI_Root_get());
        SC_FIXEDSCURI_MusicLibrary = new SCFixedSCUriID("SC_FIXEDSCURI_MusicLibrary", 1);
        SC_FIXEDSCURI_Favorites = new SCFixedSCUriID("SC_FIXEDSCURI_Favorites", 2);
        SC_FIXEDSCURI_Playlists = new SCFixedSCUriID("SC_FIXEDSCURI_Playlists", 3);
        SC_FIXEDSCURI_HT_Sources = new SCFixedSCUriID("SC_FIXEDSCURI_HT_Sources", 4);
        SC_FIXEDSCURI_Services = new SCFixedSCUriID("SC_FIXEDSCURI_Services", 5);
        SC_FIXEDSCURI_Services_MyMusicServices = new SCFixedSCUriID("SC_FIXEDSCURI_Services_MyMusicServices", 6);
        SC_FIXEDSCURI_Services_MusicServiceCatalog = new SCFixedSCUriID("SC_FIXEDSCURI_Services_MusicServiceCatalog", 7);
        SC_FIXEDSCURI_Settings = new SCFixedSCUriID("SC_FIXEDSCURI_Settings", 8);
        SC_FIXEDSCURI_Settings_MusicLibraryManagement = new SCFixedSCUriID("SC_FIXEDSCURI_Settings_MusicLibraryManagement", 9);
        SC_FIXEDSCURI_Settings_AddPlayerOrSub = new SCFixedSCUriID("SC_FIXEDSCURI_Settings_AddPlayerOrSub", 10);
        SC_FIXEDSCURI_Settings_AddBoost = new SCFixedSCUriID("SC_FIXEDSCURI_Settings_AddBoost", 11);
        SC_FIXEDSCURI_Settings_ZonePlayers = new SCFixedSCUriID("SC_FIXEDSCURI_Settings_ZonePlayers", 12);
        SC_FIXEDSCURI_Settings_ZoneBridges = new SCFixedSCUriID("SC_FIXEDSCURI_Settings_ZoneBridges", 13);
        SC_FIXEDSCURI_Settings_Docks = new SCFixedSCUriID("SC_FIXEDSCURI_Settings_Docks", 14);
        SC_FIXEDSCURI_Settings_Controller = new SCFixedSCUriID("SC_FIXEDSCURI_Settings_Controller", 15);
        SC_FIXEDSCURI_Settings_DateAndTime = new SCFixedSCUriID("SC_FIXEDSCURI_Settings_DateAndTime", 16);
        SC_FIXEDSCURI_Settings_Advanced = new SCFixedSCUriID("SC_FIXEDSCURI_Settings_Advanced", 17);
        SC_FIXEDSCURI_Settings_OnlineUpdate = new SCFixedSCUriID("SC_FIXEDSCURI_Settings_OnlineUpdate", 18);
        SC_FIXEDSCURI_Settings_AboutSonos = new SCFixedSCUriID("SC_FIXEDSCURI_Settings_AboutSonos", 19);
        SC_FIXEDSCURI_Settings_Alarms = new SCFixedSCUriID("SC_FIXEDSCURI_Settings_Alarms", 20);
        SC_FIXEDSCURI_Settings_AlarmMusic = new SCFixedSCUriID("SC_FIXEDSCURI_Settings_AlarmMusic", 21);
        SC_FIXEDSCURI_Settings_MusicShares = new SCFixedSCUriID("SC_FIXEDSCURI_Settings_MusicShares", 22);
        SC_FIXEDSCURI_MusicLibrary_UpdateIndex = new SCFixedSCUriID("SC_FIXEDSCURI_MusicLibrary_UpdateIndex", 23);
        SC_FIXEDSCURI_MusicLibrary_SchedUpdateIndex = new SCFixedSCUriID("SC_FIXEDSCURI_MusicLibrary_SchedUpdateIndex", 24);
        SC_FIXEDSCURI_MusicLibrary_SchedUpdateIndexTime = new SCFixedSCUriID("SC_FIXEDSCURI_MusicLibrary_SchedUpdateIndexTime", 25);
        SC_FIXEDSCURI_MusicLibrary_CompilationAlbums = new SCFixedSCUriID("SC_FIXEDSCURI_MusicLibrary_CompilationAlbums", 26);
        SC_FIXEDSCURI_MusicLibrary_SortFolderBy = new SCFixedSCUriID("SC_FIXEDSCURI_MusicLibrary_SortFolderBy", 27);
        SC_FIXEDSCURI_DateAndTime_TimeZone = new SCFixedSCUriID("SC_FIXEDSCURI_DateAndTime_TimeZone", 28);
        SC_FIXEDSCURI_DateAndTime_InternetTime = new SCFixedSCUriID("SC_FIXEDSCURI_DateAndTime_InternetTime", 29);
        SC_FIXEDSCURI_DateAndTime_Date = new SCFixedSCUriID("SC_FIXEDSCURI_DateAndTime_Date", 30);
        SC_FIXEDSCURI_DateAndTime_Time = new SCFixedSCUriID("SC_FIXEDSCURI_DateAndTime_Time", 31);
        SC_FIXEDSCURI_AdvancedSettings_Registration = new SCFixedSCUriID("SC_FIXEDSCURI_AdvancedSettings_Registration", 32);
        SC_FIXEDSCURI_AdvancedSettings_SubmitDiags = new SCFixedSCUriID("SC_FIXEDSCURI_AdvancedSettings_SubmitDiags", 33);
        SC_FIXEDSCURI_AdvancedSettings_ResetController = new SCFixedSCUriID("SC_FIXEDSCURI_AdvancedSettings_ResetController", 34);
        SC_FIXEDSCURI_AdvancedSettings_ShowMediaServers = new SCFixedSCUriID("SC_FIXEDSCURI_AdvancedSettings_ShowMediaServers", 35);
        SC_FIXEDSCURI_AdvancedSettings_ShowRhapsodyUPnP = new SCFixedSCUriID("SC_FIXEDSCURI_AdvancedSettings_ShowRhapsodyUPnP", 36);
        SC_FIXEDSCURI_AdvancedSettings_AutoCheck4Updates = new SCFixedSCUriID("SC_FIXEDSCURI_AdvancedSettings_AutoCheck4Updates", 37);
        SC_FIXEDSCURI_AdvancedSettings_WirelessChannel = new SCFixedSCUriID("SC_FIXEDSCURI_AdvancedSettings_WirelessChannel", 38);
        SC_FIXEDSCURI_AdvancedSettings_AudioCompression = new SCFixedSCUriID("SC_FIXEDSCURI_AdvancedSettings_AudioCompression", 39);
        SC_FIXEDSCURI_AdvancedSettings_UpdateIndex = new SCFixedSCUriID("SC_FIXEDSCURI_AdvancedSettings_UpdateIndex", 40);
        SC_FIXEDSCURI_AdvancedSettings_Beta = new SCFixedSCUriID("SC_FIXEDSCURI_AdvancedSettings_Beta", 41);
        SC_FIXEDSCURI_AdvancedSettings_UsageData = new SCFixedSCUriID("SC_FIXEDSCURI_AdvancedSettings_UsageData", 42);
        SCFixedSCUriID ascfixedscuriid[] = new SCFixedSCUriID[43];
        ascfixedscuriid[0] = SC_FIXEDSCURI_Root;
        ascfixedscuriid[1] = SC_FIXEDSCURI_MusicLibrary;
        ascfixedscuriid[2] = SC_FIXEDSCURI_Favorites;
        ascfixedscuriid[3] = SC_FIXEDSCURI_Playlists;
        ascfixedscuriid[4] = SC_FIXEDSCURI_HT_Sources;
        ascfixedscuriid[5] = SC_FIXEDSCURI_Services;
        ascfixedscuriid[6] = SC_FIXEDSCURI_Services_MyMusicServices;
        ascfixedscuriid[7] = SC_FIXEDSCURI_Services_MusicServiceCatalog;
        ascfixedscuriid[8] = SC_FIXEDSCURI_Settings;
        ascfixedscuriid[9] = SC_FIXEDSCURI_Settings_MusicLibraryManagement;
        ascfixedscuriid[10] = SC_FIXEDSCURI_Settings_AddPlayerOrSub;
        ascfixedscuriid[11] = SC_FIXEDSCURI_Settings_AddBoost;
        ascfixedscuriid[12] = SC_FIXEDSCURI_Settings_ZonePlayers;
        ascfixedscuriid[13] = SC_FIXEDSCURI_Settings_ZoneBridges;
        ascfixedscuriid[14] = SC_FIXEDSCURI_Settings_Docks;
        ascfixedscuriid[15] = SC_FIXEDSCURI_Settings_Controller;
        ascfixedscuriid[16] = SC_FIXEDSCURI_Settings_DateAndTime;
        ascfixedscuriid[17] = SC_FIXEDSCURI_Settings_Advanced;
        ascfixedscuriid[18] = SC_FIXEDSCURI_Settings_OnlineUpdate;
        ascfixedscuriid[19] = SC_FIXEDSCURI_Settings_AboutSonos;
        ascfixedscuriid[20] = SC_FIXEDSCURI_Settings_Alarms;
        ascfixedscuriid[21] = SC_FIXEDSCURI_Settings_AlarmMusic;
        ascfixedscuriid[22] = SC_FIXEDSCURI_Settings_MusicShares;
        ascfixedscuriid[23] = SC_FIXEDSCURI_MusicLibrary_UpdateIndex;
        ascfixedscuriid[24] = SC_FIXEDSCURI_MusicLibrary_SchedUpdateIndex;
        ascfixedscuriid[25] = SC_FIXEDSCURI_MusicLibrary_SchedUpdateIndexTime;
        ascfixedscuriid[26] = SC_FIXEDSCURI_MusicLibrary_CompilationAlbums;
        ascfixedscuriid[27] = SC_FIXEDSCURI_MusicLibrary_SortFolderBy;
        ascfixedscuriid[28] = SC_FIXEDSCURI_DateAndTime_TimeZone;
        ascfixedscuriid[29] = SC_FIXEDSCURI_DateAndTime_InternetTime;
        ascfixedscuriid[30] = SC_FIXEDSCURI_DateAndTime_Date;
        ascfixedscuriid[31] = SC_FIXEDSCURI_DateAndTime_Time;
        ascfixedscuriid[32] = SC_FIXEDSCURI_AdvancedSettings_Registration;
        ascfixedscuriid[33] = SC_FIXEDSCURI_AdvancedSettings_SubmitDiags;
        ascfixedscuriid[34] = SC_FIXEDSCURI_AdvancedSettings_ResetController;
        ascfixedscuriid[35] = SC_FIXEDSCURI_AdvancedSettings_ShowMediaServers;
        ascfixedscuriid[36] = SC_FIXEDSCURI_AdvancedSettings_ShowRhapsodyUPnP;
        ascfixedscuriid[37] = SC_FIXEDSCURI_AdvancedSettings_AutoCheck4Updates;
        ascfixedscuriid[38] = SC_FIXEDSCURI_AdvancedSettings_WirelessChannel;
        ascfixedscuriid[39] = SC_FIXEDSCURI_AdvancedSettings_AudioCompression;
        ascfixedscuriid[40] = SC_FIXEDSCURI_AdvancedSettings_UpdateIndex;
        ascfixedscuriid[41] = SC_FIXEDSCURI_AdvancedSettings_Beta;
        ascfixedscuriid[42] = SC_FIXEDSCURI_AdvancedSettings_UsageData;
        $VALUES = ascfixedscuriid;
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCFixedSCUriCategory extends Enum
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


    private SCFixedSCUriCategory(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCFixedSCUriCategory(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCFixedSCUriCategory(String s, int i, SCFixedSCUriCategory scfixedscuricategory)
    {
        Enum(s, i);
        swigValue = scfixedscuricategory.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCFixedSCUriCategory swigToEnum(int i)
    {
        SCFixedSCUriCategory ascfixedscuricategory[] = (SCFixedSCUriCategory[])com/sonos/sclib/SCFixedSCUriCategory.getEnumConstants();
        if(i >= ascfixedscuricategory.length || i < 0 || ascfixedscuricategory[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCFixedSCUriCategory scfixedscuricategory1 = ascfixedscuricategory[i];
_L4:
        return scfixedscuricategory1;
_L2:
        int j = ascfixedscuricategory.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCFixedSCUriCategory scfixedscuricategory = ascfixedscuricategory[k];
            if(scfixedscuricategory.swigValue == i)
            {
                scfixedscuricategory1 = scfixedscuricategory;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCFixedSCUriCategory).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCFixedSCUriCategory valueOf(String s)
    {
        return (SCFixedSCUriCategory)Enum.valueOf(com/sonos/sclib/SCFixedSCUriCategory, s);
    }

    public static SCFixedSCUriCategory[] values()
    {
        return (SCFixedSCUriCategory[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCFixedSCUriCategory $VALUES[];
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Music_Pandora;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Music_Pandora_AddToStation;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Music_Services;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_Advanced;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_Device;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_Docks_AutoPlayVolume;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_Docks_AutoPlayZone;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_Docks_IncludeGroupedZones;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_Docks_Naming;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_Docks_WhiteLED;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_WhiteLED;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZB_Naming;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZB_WhiteLED;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_AddRemSUB;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_AddRemSurround;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_AudioSettings;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_ChangeSpeakers;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_ConfigureSUB;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_CreateStereoPair;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_EQ;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_HTSetup;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_LineInAutoplayVolume;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_LineInAutoplayZone;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_LineInIncludeGroupedZones;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_LineInSettings;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_LineInSourceLevel;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_LineInSourceName;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_LineInUseAutoplayVolume;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_LineOutLevel;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_Naming;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_RecalibrateSUB;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_SUBEQ;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_SeparateStereoPair;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_SurroundEQ;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_TVDialogEQ;
    public static final SCFixedSCUriCategory SC_SCURICATEGORY_Settings_ZP_WhiteLED;
    private final int swigValue;

    static 
    {
        SC_SCURICATEGORY_Settings_Device = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_Device", 0, sclibJNI.SC_SCURICATEGORY_Settings_Device_get());
        SC_SCURICATEGORY_Settings_ZP_EQ = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_EQ", 1);
        SC_SCURICATEGORY_Settings_ZP_AudioSettings = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_AudioSettings", 2);
        SC_SCURICATEGORY_Settings_ZP_Naming = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_Naming", 3);
        SC_SCURICATEGORY_Settings_ZP_LineInSettings = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_LineInSettings", 4);
        SC_SCURICATEGORY_Settings_ZP_LineInSourceName = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_LineInSourceName", 5);
        SC_SCURICATEGORY_Settings_ZP_LineInSourceLevel = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_LineInSourceLevel", 6);
        SC_SCURICATEGORY_Settings_ZP_LineInAutoplayZone = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_LineInAutoplayZone", 7);
        SC_SCURICATEGORY_Settings_ZP_LineInIncludeGroupedZones = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_LineInIncludeGroupedZones", 8);
        SC_SCURICATEGORY_Settings_ZP_LineInUseAutoplayVolume = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_LineInUseAutoplayVolume", 9);
        SC_SCURICATEGORY_Settings_ZP_LineInAutoplayVolume = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_LineInAutoplayVolume", 10);
        SC_SCURICATEGORY_Settings_ZP_LineOutLevel = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_LineOutLevel", 11);
        SC_SCURICATEGORY_Settings_ZP_WhiteLED = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_WhiteLED", 12);
        SC_SCURICATEGORY_Settings_ZP_CreateStereoPair = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_CreateStereoPair", 13);
        SC_SCURICATEGORY_Settings_ZP_SeparateStereoPair = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_SeparateStereoPair", 14);
        SC_SCURICATEGORY_Settings_ZB_Naming = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZB_Naming", 15);
        SC_SCURICATEGORY_Settings_ZB_WhiteLED = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZB_WhiteLED", 16);
        SC_SCURICATEGORY_Settings_Docks_Naming = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_Docks_Naming", 17);
        SC_SCURICATEGORY_Settings_Docks_AutoPlayZone = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_Docks_AutoPlayZone", 18);
        SC_SCURICATEGORY_Settings_Docks_IncludeGroupedZones = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_Docks_IncludeGroupedZones", 19);
        SC_SCURICATEGORY_Settings_Docks_AutoPlayVolume = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_Docks_AutoPlayVolume", 20);
        SC_SCURICATEGORY_Settings_Docks_WhiteLED = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_Docks_WhiteLED", 21);
        SC_SCURICATEGORY_Settings_WhiteLED = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_WhiteLED", 22);
        SC_SCURICATEGORY_Music_Pandora = new SCFixedSCUriCategory("SC_SCURICATEGORY_Music_Pandora", 23);
        SC_SCURICATEGORY_Music_Pandora_AddToStation = new SCFixedSCUriCategory("SC_SCURICATEGORY_Music_Pandora_AddToStation", 24);
        SC_SCURICATEGORY_Music_Services = new SCFixedSCUriCategory("SC_SCURICATEGORY_Music_Services", 25);
        SC_SCURICATEGORY_Settings_ZP_ChangeSpeakers = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_ChangeSpeakers", 26);
        SC_SCURICATEGORY_Settings_ZP_ConfigureSUB = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_ConfigureSUB", 27);
        SC_SCURICATEGORY_Settings_ZP_RecalibrateSUB = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_RecalibrateSUB", 28);
        SC_SCURICATEGORY_Settings_ZP_SUBEQ = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_SUBEQ", 29);
        SC_SCURICATEGORY_Settings_ZP_AddRemSUB = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_AddRemSUB", 30);
        SC_SCURICATEGORY_Settings_ZP_AddRemSurround = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_AddRemSurround", 31);
        SC_SCURICATEGORY_Settings_ZP_HTSetup = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_HTSetup", 32);
        SC_SCURICATEGORY_Settings_ZP_TVDialogEQ = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_TVDialogEQ", 33);
        SC_SCURICATEGORY_Settings_ZP_SurroundEQ = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_ZP_SurroundEQ", 34);
        SC_SCURICATEGORY_Settings_Advanced = new SCFixedSCUriCategory("SC_SCURICATEGORY_Settings_Advanced", 35);
        SCFixedSCUriCategory ascfixedscuricategory[] = new SCFixedSCUriCategory[36];
        ascfixedscuricategory[0] = SC_SCURICATEGORY_Settings_Device;
        ascfixedscuricategory[1] = SC_SCURICATEGORY_Settings_ZP_EQ;
        ascfixedscuricategory[2] = SC_SCURICATEGORY_Settings_ZP_AudioSettings;
        ascfixedscuricategory[3] = SC_SCURICATEGORY_Settings_ZP_Naming;
        ascfixedscuricategory[4] = SC_SCURICATEGORY_Settings_ZP_LineInSettings;
        ascfixedscuricategory[5] = SC_SCURICATEGORY_Settings_ZP_LineInSourceName;
        ascfixedscuricategory[6] = SC_SCURICATEGORY_Settings_ZP_LineInSourceLevel;
        ascfixedscuricategory[7] = SC_SCURICATEGORY_Settings_ZP_LineInAutoplayZone;
        ascfixedscuricategory[8] = SC_SCURICATEGORY_Settings_ZP_LineInIncludeGroupedZones;
        ascfixedscuricategory[9] = SC_SCURICATEGORY_Settings_ZP_LineInUseAutoplayVolume;
        ascfixedscuricategory[10] = SC_SCURICATEGORY_Settings_ZP_LineInAutoplayVolume;
        ascfixedscuricategory[11] = SC_SCURICATEGORY_Settings_ZP_LineOutLevel;
        ascfixedscuricategory[12] = SC_SCURICATEGORY_Settings_ZP_WhiteLED;
        ascfixedscuricategory[13] = SC_SCURICATEGORY_Settings_ZP_CreateStereoPair;
        ascfixedscuricategory[14] = SC_SCURICATEGORY_Settings_ZP_SeparateStereoPair;
        ascfixedscuricategory[15] = SC_SCURICATEGORY_Settings_ZB_Naming;
        ascfixedscuricategory[16] = SC_SCURICATEGORY_Settings_ZB_WhiteLED;
        ascfixedscuricategory[17] = SC_SCURICATEGORY_Settings_Docks_Naming;
        ascfixedscuricategory[18] = SC_SCURICATEGORY_Settings_Docks_AutoPlayZone;
        ascfixedscuricategory[19] = SC_SCURICATEGORY_Settings_Docks_IncludeGroupedZones;
        ascfixedscuricategory[20] = SC_SCURICATEGORY_Settings_Docks_AutoPlayVolume;
        ascfixedscuricategory[21] = SC_SCURICATEGORY_Settings_Docks_WhiteLED;
        ascfixedscuricategory[22] = SC_SCURICATEGORY_Settings_WhiteLED;
        ascfixedscuricategory[23] = SC_SCURICATEGORY_Music_Pandora;
        ascfixedscuricategory[24] = SC_SCURICATEGORY_Music_Pandora_AddToStation;
        ascfixedscuricategory[25] = SC_SCURICATEGORY_Music_Services;
        ascfixedscuricategory[26] = SC_SCURICATEGORY_Settings_ZP_ChangeSpeakers;
        ascfixedscuricategory[27] = SC_SCURICATEGORY_Settings_ZP_ConfigureSUB;
        ascfixedscuricategory[28] = SC_SCURICATEGORY_Settings_ZP_RecalibrateSUB;
        ascfixedscuricategory[29] = SC_SCURICATEGORY_Settings_ZP_SUBEQ;
        ascfixedscuricategory[30] = SC_SCURICATEGORY_Settings_ZP_AddRemSUB;
        ascfixedscuricategory[31] = SC_SCURICATEGORY_Settings_ZP_AddRemSurround;
        ascfixedscuricategory[32] = SC_SCURICATEGORY_Settings_ZP_HTSetup;
        ascfixedscuricategory[33] = SC_SCURICATEGORY_Settings_ZP_TVDialogEQ;
        ascfixedscuricategory[34] = SC_SCURICATEGORY_Settings_ZP_SurroundEQ;
        ascfixedscuricategory[35] = SC_SCURICATEGORY_Settings_Advanced;
        $VALUES = ascfixedscuricategory;
    }
}

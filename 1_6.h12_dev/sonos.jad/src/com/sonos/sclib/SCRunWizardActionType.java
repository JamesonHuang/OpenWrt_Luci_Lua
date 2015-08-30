// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class SCRunWizardActionType extends Enum
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


    private SCRunWizardActionType(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCRunWizardActionType(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCRunWizardActionType(String s, int i, SCRunWizardActionType scrunwizardactiontype)
    {
        Enum(s, i);
        swigValue = scrunwizardactiontype.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCRunWizardActionType swigToEnum(int i)
    {
        SCRunWizardActionType ascrunwizardactiontype[] = (SCRunWizardActionType[])com/sonos/sclib/SCRunWizardActionType.getEnumConstants();
        if(i >= ascrunwizardactiontype.length || i < 0 || ascrunwizardactiontype[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCRunWizardActionType scrunwizardactiontype1 = ascrunwizardactiontype[i];
_L4:
        return scrunwizardactiontype1;
_L2:
        int j = ascrunwizardactiontype.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCRunWizardActionType scrunwizardactiontype = ascrunwizardactiontype[k];
            if(scrunwizardactiontype.swigValue == i)
            {
                scrunwizardactiontype1 = scrunwizardactiontype;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCRunWizardActionType).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCRunWizardActionType valueOf(String s)
    {
        return (SCRunWizardActionType)Enum.valueOf(com/sonos/sclib/SCRunWizardActionType, s);
    }

    public static SCRunWizardActionType[] values()
    {
        return (SCRunWizardActionType[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCRunWizardActionType $VALUES[];
    public static final SCRunWizardActionType SCACTN_RUNWIZ_ADDBOOST;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_ADDPLAYERORSUB;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_CHANGE_SPEAKERS;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_CHOOSE_CONFIGURE_DEVICE;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_CONFIGURE_SUB;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_INVALID;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_JOINANOTHERHOUSEHOLD;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_MUSICSERVICE_ADD;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_MUSICSERVICE_CHANGENICKNAME;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_MUSICSERVICE_CHANGEPASSWORD;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_MUSICSERVICE_REAUTHORIZE;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_MUSICSERVICE_REPLACE;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_MUSICSERVICE_SUBSCRIBE;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_ONLINEUPDATE;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_ONLINEUPDATE_FROM_ZONEMENU;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_ONLINEUPDATE_INTRODUCTION_ONLY;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_RECALIBRATE_SUB;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_REGISTRATION;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_SETUP;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_SETUP_UNCONFIGURED;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_SHAREUSAGEDATA;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_SONOSNET_SETUP;
    public static final SCRunWizardActionType SCACTN_RUNWIZ_SOUNDBAR;
    private final int swigValue;

    static 
    {
        SCACTN_RUNWIZ_INVALID = new SCRunWizardActionType("SCACTN_RUNWIZ_INVALID", 0);
        SCACTN_RUNWIZ_ONLINEUPDATE = new SCRunWizardActionType("SCACTN_RUNWIZ_ONLINEUPDATE", 1);
        SCACTN_RUNWIZ_ONLINEUPDATE_FROM_ZONEMENU = new SCRunWizardActionType("SCACTN_RUNWIZ_ONLINEUPDATE_FROM_ZONEMENU", 2);
        SCACTN_RUNWIZ_ONLINEUPDATE_INTRODUCTION_ONLY = new SCRunWizardActionType("SCACTN_RUNWIZ_ONLINEUPDATE_INTRODUCTION_ONLY", 3);
        SCACTN_RUNWIZ_SETUP = new SCRunWizardActionType("SCACTN_RUNWIZ_SETUP", 4);
        SCACTN_RUNWIZ_ADDPLAYERORSUB = new SCRunWizardActionType("SCACTN_RUNWIZ_ADDPLAYERORSUB", 5);
        SCACTN_RUNWIZ_ADDBOOST = new SCRunWizardActionType("SCACTN_RUNWIZ_ADDBOOST", 6);
        SCACTN_RUNWIZ_REGISTRATION = new SCRunWizardActionType("SCACTN_RUNWIZ_REGISTRATION", 7);
        SCACTN_RUNWIZ_SHAREUSAGEDATA = new SCRunWizardActionType("SCACTN_RUNWIZ_SHAREUSAGEDATA", 8);
        SCACTN_RUNWIZ_MUSICSERVICE_ADD = new SCRunWizardActionType("SCACTN_RUNWIZ_MUSICSERVICE_ADD", 9);
        SCACTN_RUNWIZ_MUSICSERVICE_SUBSCRIBE = new SCRunWizardActionType("SCACTN_RUNWIZ_MUSICSERVICE_SUBSCRIBE", 10);
        SCACTN_RUNWIZ_MUSICSERVICE_CHANGEPASSWORD = new SCRunWizardActionType("SCACTN_RUNWIZ_MUSICSERVICE_CHANGEPASSWORD", 11);
        SCACTN_RUNWIZ_MUSICSERVICE_CHANGENICKNAME = new SCRunWizardActionType("SCACTN_RUNWIZ_MUSICSERVICE_CHANGENICKNAME", 12);
        SCACTN_RUNWIZ_MUSICSERVICE_REPLACE = new SCRunWizardActionType("SCACTN_RUNWIZ_MUSICSERVICE_REPLACE", 13);
        SCACTN_RUNWIZ_MUSICSERVICE_REAUTHORIZE = new SCRunWizardActionType("SCACTN_RUNWIZ_MUSICSERVICE_REAUTHORIZE", 14);
        SCACTN_RUNWIZ_SONOSNET_SETUP = new SCRunWizardActionType("SCACTN_RUNWIZ_SONOSNET_SETUP", 15);
        SCACTN_RUNWIZ_CONFIGURE_SUB = new SCRunWizardActionType("SCACTN_RUNWIZ_CONFIGURE_SUB", 16);
        SCACTN_RUNWIZ_CHOOSE_CONFIGURE_DEVICE = new SCRunWizardActionType("SCACTN_RUNWIZ_CHOOSE_CONFIGURE_DEVICE", 17);
        SCACTN_RUNWIZ_RECALIBRATE_SUB = new SCRunWizardActionType("SCACTN_RUNWIZ_RECALIBRATE_SUB", 18);
        SCACTN_RUNWIZ_CHANGE_SPEAKERS = new SCRunWizardActionType("SCACTN_RUNWIZ_CHANGE_SPEAKERS", 19);
        SCACTN_RUNWIZ_SOUNDBAR = new SCRunWizardActionType("SCACTN_RUNWIZ_SOUNDBAR", 20);
        SCACTN_RUNWIZ_SETUP_UNCONFIGURED = new SCRunWizardActionType("SCACTN_RUNWIZ_SETUP_UNCONFIGURED", 21);
        SCACTN_RUNWIZ_JOINANOTHERHOUSEHOLD = new SCRunWizardActionType("SCACTN_RUNWIZ_JOINANOTHERHOUSEHOLD", 22);
        SCRunWizardActionType ascrunwizardactiontype[] = new SCRunWizardActionType[23];
        ascrunwizardactiontype[0] = SCACTN_RUNWIZ_INVALID;
        ascrunwizardactiontype[1] = SCACTN_RUNWIZ_ONLINEUPDATE;
        ascrunwizardactiontype[2] = SCACTN_RUNWIZ_ONLINEUPDATE_FROM_ZONEMENU;
        ascrunwizardactiontype[3] = SCACTN_RUNWIZ_ONLINEUPDATE_INTRODUCTION_ONLY;
        ascrunwizardactiontype[4] = SCACTN_RUNWIZ_SETUP;
        ascrunwizardactiontype[5] = SCACTN_RUNWIZ_ADDPLAYERORSUB;
        ascrunwizardactiontype[6] = SCACTN_RUNWIZ_ADDBOOST;
        ascrunwizardactiontype[7] = SCACTN_RUNWIZ_REGISTRATION;
        ascrunwizardactiontype[8] = SCACTN_RUNWIZ_SHAREUSAGEDATA;
        ascrunwizardactiontype[9] = SCACTN_RUNWIZ_MUSICSERVICE_ADD;
        ascrunwizardactiontype[10] = SCACTN_RUNWIZ_MUSICSERVICE_SUBSCRIBE;
        ascrunwizardactiontype[11] = SCACTN_RUNWIZ_MUSICSERVICE_CHANGEPASSWORD;
        ascrunwizardactiontype[12] = SCACTN_RUNWIZ_MUSICSERVICE_CHANGENICKNAME;
        ascrunwizardactiontype[13] = SCACTN_RUNWIZ_MUSICSERVICE_REPLACE;
        ascrunwizardactiontype[14] = SCACTN_RUNWIZ_MUSICSERVICE_REAUTHORIZE;
        ascrunwizardactiontype[15] = SCACTN_RUNWIZ_SONOSNET_SETUP;
        ascrunwizardactiontype[16] = SCACTN_RUNWIZ_CONFIGURE_SUB;
        ascrunwizardactiontype[17] = SCACTN_RUNWIZ_CHOOSE_CONFIGURE_DEVICE;
        ascrunwizardactiontype[18] = SCACTN_RUNWIZ_RECALIBRATE_SUB;
        ascrunwizardactiontype[19] = SCACTN_RUNWIZ_CHANGE_SPEAKERS;
        ascrunwizardactiontype[20] = SCACTN_RUNWIZ_SOUNDBAR;
        ascrunwizardactiontype[21] = SCACTN_RUNWIZ_SETUP_UNCONFIGURED;
        ascrunwizardactiontype[22] = SCACTN_RUNWIZ_JOINANOTHERHOUSEHOLD;
        $VALUES = ascrunwizardactiontype;
    }
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class WizardComponentType extends Enum
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


    private WizardComponentType(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private WizardComponentType(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private WizardComponentType(String s, int i, WizardComponentType wizardcomponenttype)
    {
        Enum(s, i);
        swigValue = wizardcomponenttype.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static WizardComponentType swigToEnum(int i)
    {
        WizardComponentType awizardcomponenttype[] = (WizardComponentType[])com/sonos/sclib/WizardComponentType.getEnumConstants();
        if(i >= awizardcomponenttype.length || i < 0 || awizardcomponenttype[i].swigValue != i) goto _L2; else goto _L1
_L1:
        WizardComponentType wizardcomponenttype1 = awizardcomponenttype[i];
_L4:
        return wizardcomponenttype1;
_L2:
        int j = awizardcomponenttype.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            WizardComponentType wizardcomponenttype = awizardcomponenttype[k];
            if(wizardcomponenttype.swigValue == i)
            {
                wizardcomponenttype1 = wizardcomponenttype;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/WizardComponentType).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static WizardComponentType valueOf(String s)
    {
        return (WizardComponentType)Enum.valueOf(com/sonos/sclib/WizardComponentType, s);
    }

    public static WizardComponentType[] values()
    {
        return (WizardComponentType[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final WizardComponentType $VALUES[];
    public static final WizardComponentType WIZARD_COMPONENT_TYPE_BUTTON;
    public static final WizardComponentType WIZARD_COMPONENT_TYPE_CHECKBOX;
    public static final WizardComponentType WIZARD_COMPONENT_TYPE_IMAGE;
    public static final WizardComponentType WIZARD_COMPONENT_TYPE_LIST;
    public static final WizardComponentType WIZARD_COMPONENT_TYPE_PAGE;
    public static final WizardComponentType WIZARD_COMPONENT_TYPE_PROGRESS;
    public static final WizardComponentType WIZARD_COMPONENT_TYPE_SECURE_TEXTFIELD;
    public static final WizardComponentType WIZARD_COMPONENT_TYPE_SPINNER;
    public static final WizardComponentType WIZARD_COMPONENT_TYPE_TEXT;
    public static final WizardComponentType WIZARD_COMPONENT_TYPE_TEXTFIELD;
    private final int swigValue;

    static 
    {
        WIZARD_COMPONENT_TYPE_PAGE = new WizardComponentType("WIZARD_COMPONENT_TYPE_PAGE", 0);
        WIZARD_COMPONENT_TYPE_TEXT = new WizardComponentType("WIZARD_COMPONENT_TYPE_TEXT", 1);
        WIZARD_COMPONENT_TYPE_IMAGE = new WizardComponentType("WIZARD_COMPONENT_TYPE_IMAGE", 2);
        WIZARD_COMPONENT_TYPE_BUTTON = new WizardComponentType("WIZARD_COMPONENT_TYPE_BUTTON", 3);
        WIZARD_COMPONENT_TYPE_SPINNER = new WizardComponentType("WIZARD_COMPONENT_TYPE_SPINNER", 4);
        WIZARD_COMPONENT_TYPE_LIST = new WizardComponentType("WIZARD_COMPONENT_TYPE_LIST", 5);
        WIZARD_COMPONENT_TYPE_TEXTFIELD = new WizardComponentType("WIZARD_COMPONENT_TYPE_TEXTFIELD", 6);
        WIZARD_COMPONENT_TYPE_SECURE_TEXTFIELD = new WizardComponentType("WIZARD_COMPONENT_TYPE_SECURE_TEXTFIELD", 7);
        WIZARD_COMPONENT_TYPE_CHECKBOX = new WizardComponentType("WIZARD_COMPONENT_TYPE_CHECKBOX", 8);
        WIZARD_COMPONENT_TYPE_PROGRESS = new WizardComponentType("WIZARD_COMPONENT_TYPE_PROGRESS", 9);
        WizardComponentType awizardcomponenttype[] = new WizardComponentType[10];
        awizardcomponenttype[0] = WIZARD_COMPONENT_TYPE_PAGE;
        awizardcomponenttype[1] = WIZARD_COMPONENT_TYPE_TEXT;
        awizardcomponenttype[2] = WIZARD_COMPONENT_TYPE_IMAGE;
        awizardcomponenttype[3] = WIZARD_COMPONENT_TYPE_BUTTON;
        awizardcomponenttype[4] = WIZARD_COMPONENT_TYPE_SPINNER;
        awizardcomponenttype[5] = WIZARD_COMPONENT_TYPE_LIST;
        awizardcomponenttype[6] = WIZARD_COMPONENT_TYPE_TEXTFIELD;
        awizardcomponenttype[7] = WIZARD_COMPONENT_TYPE_SECURE_TEXTFIELD;
        awizardcomponenttype[8] = WIZARD_COMPONENT_TYPE_CHECKBOX;
        awizardcomponenttype[9] = WIZARD_COMPONENT_TYPE_PROGRESS;
        $VALUES = awizardcomponenttype;
    }
}

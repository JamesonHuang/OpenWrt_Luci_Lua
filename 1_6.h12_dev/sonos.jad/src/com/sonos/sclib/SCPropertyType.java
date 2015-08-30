// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCPropertyType extends Enum
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


    private SCPropertyType(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCPropertyType(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCPropertyType(String s, int i, SCPropertyType scpropertytype)
    {
        Enum(s, i);
        swigValue = scpropertytype.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCPropertyType swigToEnum(int i)
    {
        SCPropertyType ascpropertytype[] = (SCPropertyType[])com/sonos/sclib/SCPropertyType.getEnumConstants();
        if(i >= ascpropertytype.length || i < 0 || ascpropertytype[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCPropertyType scpropertytype1 = ascpropertytype[i];
_L4:
        return scpropertytype1;
_L2:
        int j = ascpropertytype.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCPropertyType scpropertytype = ascpropertytype[k];
            if(scpropertytype.swigValue == i)
            {
                scpropertytype1 = scpropertytype;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCPropertyType).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCPropertyType valueOf(String s)
    {
        return (SCPropertyType)Enum.valueOf(com/sonos/sclib/SCPropertyType, s);
    }

    public static SCPropertyType[] values()
    {
        return (SCPropertyType[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCPropertyType $VALUES[];
    public static final SCPropertyType SC_PROP_TYPE_BOOL;
    public static final SCPropertyType SC_PROP_TYPE_INT;
    public static final SCPropertyType SC_PROP_TYPE_INVALID;
    public static final SCPropertyType SC_PROP_TYPE_PBAG;
    public static final SCPropertyType SC_PROP_TYPE_STR;
    public static final SCPropertyType SC_PROP_TYPE_STR_ARRAY;
    private final int swigValue;

    static 
    {
        SC_PROP_TYPE_INVALID = new SCPropertyType("SC_PROP_TYPE_INVALID", 0, sclibJNI.SC_PROP_TYPE_INVALID_get());
        SC_PROP_TYPE_BOOL = new SCPropertyType("SC_PROP_TYPE_BOOL", 1, sclibJNI.SC_PROP_TYPE_BOOL_get());
        SC_PROP_TYPE_INT = new SCPropertyType("SC_PROP_TYPE_INT", 2);
        SC_PROP_TYPE_STR = new SCPropertyType("SC_PROP_TYPE_STR", 3);
        SC_PROP_TYPE_PBAG = new SCPropertyType("SC_PROP_TYPE_PBAG", 4);
        SC_PROP_TYPE_STR_ARRAY = new SCPropertyType("SC_PROP_TYPE_STR_ARRAY", 5);
        SCPropertyType ascpropertytype[] = new SCPropertyType[6];
        ascpropertytype[0] = SC_PROP_TYPE_INVALID;
        ascpropertytype[1] = SC_PROP_TYPE_BOOL;
        ascpropertytype[2] = SC_PROP_TYPE_INT;
        ascpropertytype[3] = SC_PROP_TYPE_STR;
        ascpropertytype[4] = SC_PROP_TYPE_PBAG;
        ascpropertytype[5] = SC_PROP_TYPE_STR_ARRAY;
        $VALUES = ascpropertytype;
    }
}

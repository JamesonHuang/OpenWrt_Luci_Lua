// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class ResURIDataType extends Enum
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


    private ResURIDataType(String s, int i)
    {
        super(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private ResURIDataType(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private ResURIDataType(String s, int i, ResURIDataType resuridatatype)
    {
        Enum(s, i);
        swigValue = resuridatatype.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static ResURIDataType swigToEnum(int i)
    {
        ResURIDataType aresuridatatype[] = (ResURIDataType[])com/sonos/sclib/ResURIDataType.getEnumConstants();
        if(i >= aresuridatatype.length || i < 0 || aresuridatatype[i].swigValue != i) goto _L2; else goto _L1
_L1:
        ResURIDataType resuridatatype1 = aresuridatatype[i];
_L4:
        return resuridatatype1;
_L2:
        int j = aresuridatatype.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            ResURIDataType resuridatatype = aresuridatatype[k];
            if(resuridatatype.swigValue == i)
            {
                resuridatatype1 = resuridatatype;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/ResURIDataType).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static ResURIDataType valueOf(String s)
    {
        return (ResURIDataType)Enum.valueOf(com/sonos/sclib/ResURIDataType, s);
    }

    public static ResURIDataType[] values()
    {
        return (ResURIDataType[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final ResURIDataType $VALUES[];
    public static final ResURIDataType TYPE_IMAGE;
    public static final ResURIDataType TYPE_PLAYLIST;
    public static final ResURIDataType TYPE_TRACK;
    private final int swigValue;

    static 
    {
        TYPE_TRACK = new ResURIDataType("TYPE_TRACK", 0);
        TYPE_IMAGE = new ResURIDataType("TYPE_IMAGE", 1);
        TYPE_PLAYLIST = new ResURIDataType("TYPE_PLAYLIST", 2);
        ResURIDataType aresuridatatype[] = new ResURIDataType[3];
        aresuridatatype[0] = TYPE_TRACK;
        aresuridatatype[1] = TYPE_IMAGE;
        aresuridatatype[2] = TYPE_PLAYLIST;
        $VALUES = aresuridatatype;
    }
}

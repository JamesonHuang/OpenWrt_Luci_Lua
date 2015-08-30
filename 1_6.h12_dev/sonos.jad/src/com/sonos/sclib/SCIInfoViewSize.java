// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class SCIInfoViewSize extends Enum
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


    private SCIInfoViewSize(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCIInfoViewSize(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCIInfoViewSize(String s, int i, SCIInfoViewSize sciinfoviewsize)
    {
        Enum(s, i);
        swigValue = sciinfoviewsize.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCIInfoViewSize swigToEnum(int i)
    {
        SCIInfoViewSize asciinfoviewsize[] = (SCIInfoViewSize[])com/sonos/sclib/SCIInfoViewSize.getEnumConstants();
        if(i >= asciinfoviewsize.length || i < 0 || asciinfoviewsize[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCIInfoViewSize sciinfoviewsize1 = asciinfoviewsize[i];
_L4:
        return sciinfoviewsize1;
_L2:
        int j = asciinfoviewsize.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCIInfoViewSize sciinfoviewsize = asciinfoviewsize[k];
            if(sciinfoviewsize.swigValue == i)
            {
                sciinfoviewsize1 = sciinfoviewsize;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIInfoViewSize).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCIInfoViewSize valueOf(String s)
    {
        return (SCIInfoViewSize)Enum.valueOf(com/sonos/sclib/SCIInfoViewSize, s);
    }

    public static SCIInfoViewSize[] values()
    {
        return (SCIInfoViewSize[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCIInfoViewSize $VALUES[];
    public static final SCIInfoViewSize SCIINFOVIEW_SIZE_FULL;
    public static final SCIInfoViewSize SCIINFOVIEW_SIZE_MINI;
    public static final SCIInfoViewSize SCIINFOVIEW_SIZE_NONE;
    private final int swigValue;

    static 
    {
        SCIINFOVIEW_SIZE_NONE = new SCIInfoViewSize("SCIINFOVIEW_SIZE_NONE", 0);
        SCIINFOVIEW_SIZE_FULL = new SCIInfoViewSize("SCIINFOVIEW_SIZE_FULL", 1);
        SCIINFOVIEW_SIZE_MINI = new SCIInfoViewSize("SCIINFOVIEW_SIZE_MINI", 2);
        SCIInfoViewSize asciinfoviewsize[] = new SCIInfoViewSize[3];
        asciinfoviewsize[0] = SCIINFOVIEW_SIZE_NONE;
        asciinfoviewsize[1] = SCIINFOVIEW_SIZE_FULL;
        asciinfoviewsize[2] = SCIINFOVIEW_SIZE_MINI;
        $VALUES = asciinfoviewsize;
    }
}

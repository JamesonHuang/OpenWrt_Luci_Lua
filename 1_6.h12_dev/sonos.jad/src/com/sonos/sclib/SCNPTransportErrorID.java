// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


// Referenced classes of package com.sonos.sclib:
//            sclibJNI

public final class SCNPTransportErrorID extends Enum
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


    private SCNPTransportErrorID(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCNPTransportErrorID(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCNPTransportErrorID(String s, int i, SCNPTransportErrorID scnptransporterrorid)
    {
        Enum(s, i);
        swigValue = scnptransporterrorid.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCNPTransportErrorID swigToEnum(int i)
    {
        SCNPTransportErrorID ascnptransporterrorid[] = (SCNPTransportErrorID[])com/sonos/sclib/SCNPTransportErrorID.getEnumConstants();
        if(i >= ascnptransporterrorid.length || i < 0 || ascnptransporterrorid[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCNPTransportErrorID scnptransporterrorid1 = ascnptransporterrorid[i];
_L4:
        return scnptransporterrorid1;
_L2:
        int j = ascnptransporterrorid.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCNPTransportErrorID scnptransporterrorid = ascnptransporterrorid[k];
            if(scnptransporterrorid.swigValue == i)
            {
                scnptransporterrorid1 = scnptransporterrorid;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCNPTransportErrorID).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCNPTransportErrorID valueOf(String s)
    {
        return (SCNPTransportErrorID)Enum.valueOf(com/sonos/sclib/SCNPTransportErrorID, s);
    }

    public static SCNPTransportErrorID[] values()
    {
        return (SCNPTransportErrorID[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCNPTransportErrorID $VALUES[];
    public static final SCNPTransportErrorID SC_NP_ERR_MAX;
    public static final SCNPTransportErrorID SC_NP_ERR_NEXT_DOWN;
    public static final SCNPTransportErrorID SC_NP_ERR_PREV_DOWN;
    public static final SCNPTransportErrorID SC_NP_ERR_SEEK_BACK;
    public static final SCNPTransportErrorID SC_NP_ERR_SEEK_FORWARD;
    public static final SCNPTransportErrorID SC_NP_ERR_SKIP_BACK;
    public static final SCNPTransportErrorID SC_NP_ERR_SKIP_FORWARD;
    public static final SCNPTransportErrorID SC_NP_ERR_SKIP_LIMIT;
    private final int swigValue;

    static 
    {
        SC_NP_ERR_SKIP_BACK = new SCNPTransportErrorID("SC_NP_ERR_SKIP_BACK", 0, sclibJNI.SC_NP_ERR_SKIP_BACK_get());
        SC_NP_ERR_SKIP_FORWARD = new SCNPTransportErrorID("SC_NP_ERR_SKIP_FORWARD", 1);
        SC_NP_ERR_SEEK_BACK = new SCNPTransportErrorID("SC_NP_ERR_SEEK_BACK", 2);
        SC_NP_ERR_SEEK_FORWARD = new SCNPTransportErrorID("SC_NP_ERR_SEEK_FORWARD", 3);
        SC_NP_ERR_PREV_DOWN = new SCNPTransportErrorID("SC_NP_ERR_PREV_DOWN", 4);
        SC_NP_ERR_NEXT_DOWN = new SCNPTransportErrorID("SC_NP_ERR_NEXT_DOWN", 5);
        SC_NP_ERR_SKIP_LIMIT = new SCNPTransportErrorID("SC_NP_ERR_SKIP_LIMIT", 6);
        SC_NP_ERR_MAX = new SCNPTransportErrorID("SC_NP_ERR_MAX", 7);
        SCNPTransportErrorID ascnptransporterrorid[] = new SCNPTransportErrorID[8];
        ascnptransporterrorid[0] = SC_NP_ERR_SKIP_BACK;
        ascnptransporterrorid[1] = SC_NP_ERR_SKIP_FORWARD;
        ascnptransporterrorid[2] = SC_NP_ERR_SEEK_BACK;
        ascnptransporterrorid[3] = SC_NP_ERR_SEEK_FORWARD;
        ascnptransporterrorid[4] = SC_NP_ERR_PREV_DOWN;
        ascnptransporterrorid[5] = SC_NP_ERR_NEXT_DOWN;
        ascnptransporterrorid[6] = SC_NP_ERR_SKIP_LIMIT;
        ascnptransporterrorid[7] = SC_NP_ERR_MAX;
        $VALUES = ascnptransporterrorid;
    }
}

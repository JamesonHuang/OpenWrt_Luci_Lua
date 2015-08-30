// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;


public final class SCCredentialsType extends Enum
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


    private SCCredentialsType(String s, int i)
    {
        Enum(s, i);
        swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

    private SCCredentialsType(String s, int i, int j)
    {
        Enum(s, i);
        swigValue = j;
        SwigNext.next = j + 1;
    }

    private SCCredentialsType(String s, int i, SCCredentialsType sccredentialstype)
    {
        Enum(s, i);
        swigValue = sccredentialstype.swigValue;
        SwigNext.next = 1 + swigValue;
    }

    public static SCCredentialsType swigToEnum(int i)
    {
        SCCredentialsType asccredentialstype[] = (SCCredentialsType[])com/sonos/sclib/SCCredentialsType.getEnumConstants();
        if(i >= asccredentialstype.length || i < 0 || asccredentialstype[i].swigValue != i) goto _L2; else goto _L1
_L1:
        SCCredentialsType sccredentialstype1 = asccredentialstype[i];
_L4:
        return sccredentialstype1;
_L2:
        int j = asccredentialstype.length;
        int k = 0;
        do
        {
            if(k >= j)
                break;
            SCCredentialsType sccredentialstype = asccredentialstype[k];
            if(sccredentialstype.swigValue == i)
            {
                sccredentialstype1 = sccredentialstype;
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCCredentialsType).append(" with value ").append(i).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static SCCredentialsType valueOf(String s)
    {
        return (SCCredentialsType)Enum.valueOf(com/sonos/sclib/SCCredentialsType, s);
    }

    public static SCCredentialsType[] values()
    {
        return (SCCredentialsType[])$VALUES.clone();
    }

    public final int swigValue()
    {
        return swigValue;
    }

    private static final SCCredentialsType $VALUES[];
    public static final SCCredentialsType SC_CREDENTIALSTYPE_ANONYMOUS;
    public static final SCCredentialsType SC_CREDENTIALSTYPE_LINKCODEANDTOKEN;
    public static final SCCredentialsType SC_CREDENTIALSTYPE_USERNAMEANDPASSWORD;
    private final int swigValue;

    static 
    {
        SC_CREDENTIALSTYPE_ANONYMOUS = new SCCredentialsType("SC_CREDENTIALSTYPE_ANONYMOUS", 0);
        SC_CREDENTIALSTYPE_USERNAMEANDPASSWORD = new SCCredentialsType("SC_CREDENTIALSTYPE_USERNAMEANDPASSWORD", 1);
        SC_CREDENTIALSTYPE_LINKCODEANDTOKEN = new SCCredentialsType("SC_CREDENTIALSTYPE_LINKCODEANDTOKEN", 2);
        SCCredentialsType asccredentialstype[] = new SCCredentialsType[3];
        asccredentialstype[0] = SC_CREDENTIALSTYPE_ANONYMOUS;
        asccredentialstype[1] = SC_CREDENTIALSTYPE_USERNAMEANDPASSWORD;
        asccredentialstype[2] = SC_CREDENTIALSTYPE_LINKCODEANDTOKEN;
        $VALUES = asccredentialstype;
    }
}

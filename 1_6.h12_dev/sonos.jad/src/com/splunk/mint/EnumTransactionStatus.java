// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;


final class EnumTransactionStatus extends Enum
{

    private EnumTransactionStatus(String s, int i)
    {
        super(s, i);
    }

    public static EnumTransactionStatus valueOf(String s)
    {
        return (EnumTransactionStatus)Enum.valueOf(com/splunk/mint/EnumTransactionStatus, s);
    }

    public static EnumTransactionStatus[] values()
    {
        return (EnumTransactionStatus[])$VALUES.clone();
    }

    private static final EnumTransactionStatus $VALUES[];
    public static final EnumTransactionStatus CANCEL;
    public static final EnumTransactionStatus FAIL;
    public static final EnumTransactionStatus SUCCESS;

    static 
    {
        SUCCESS = new EnumTransactionStatus("SUCCESS", 0);
        FAIL = new EnumTransactionStatus("FAIL", 1);
        CANCEL = new EnumTransactionStatus("CANCEL", 2);
        EnumTransactionStatus aenumtransactionstatus[] = new EnumTransactionStatus[3];
        aenumtransactionstatus[0] = SUCCESS;
        aenumtransactionstatus[1] = FAIL;
        aenumtransactionstatus[2] = CANCEL;
        $VALUES = aenumtransactionstatus;
    }
}

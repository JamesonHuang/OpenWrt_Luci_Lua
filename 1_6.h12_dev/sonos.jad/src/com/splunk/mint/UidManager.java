// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

// Referenced classes of package com.splunk.mint:
//            Properties

class UidManager
{

    UidManager()
    {
    }

    private static final char[] encodeHex(byte abyte0[])
    {
        int i = abyte0.length;
        char ac[] = new char[i << 1];
        int j = 0;
        int k = 0;
        for(; j < i; j++)
        {
            int l = k + 1;
            ac[k] = DIGITS[(0xf0 & abyte0[j]) >>> 4];
            k = l + 1;
            ac[l] = DIGITS[0xf & abyte0[j]];
        }

        return ac;
    }

    private static final String generateUid()
    {
        String s4;
        byte abyte0[];
        String s = Long.valueOf((new Date()).getTime()).toString();
        String s1 = (new Object()).toString();
        String s2 = String.valueOf(System.nanoTime());
        String s3 = Integer.toString((int)(65535D * (new Random(System.currentTimeMillis())).nextDouble()));
        s4 = (new StringBuilder()).append(s).append(s1).append(s2).append(s3).append("android_id").toString();
        abyte0 = null;
        byte abyte2[];
        byte abyte1[] = s4.getBytes("UTF-8");
        abyte2 = MessageDigest.getInstance("MD5").digest(abyte1);
        abyte0 = abyte2;
_L2:
        return new String(encodeHex(abyte0));
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        unsupportedencodingexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        NoSuchAlgorithmException nosuchalgorithmexception;
        nosuchalgorithmexception;
        nosuchalgorithmexception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected static final String getUid(Context context)
    {
        if(Properties.UID == null || Properties.UID.length() != 32) goto _L2; else goto _L1
_L1:
        String s = Properties.UID;
_L4:
        return s;
_L2:
        s = "12345678901234567890123456789012";
        if(context != null)
        {
            SharedPreferences sharedpreferences = context.getSharedPreferences("UIDPREFERENCES", 0);
            if(sharedpreferences != null)
                s = sharedpreferences.getString("UID_FIELD", "UID_NOSAVED");
        }
        if(s.equals("UID_NOSAVED") || s.equals("12345678901234567890123456789012"))
        {
            s = generateUid();
            saveUid(context, s);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected static boolean saveUid(Context context, String s)
    {
        boolean flag = false;
        if(s != null && s.length() == 32)
        {
            Properties.UID = s;
            SharedPreferences sharedpreferences = context.getSharedPreferences("UIDPREFERENCES", 0);
            if(sharedpreferences != null)
                flag = sharedpreferences.edit().putString("UID_FIELD", s).commit();
        }
        return flag;
    }

    private static final char DIGITS[];
    private static final int UID_CORRECT_LENTH = 32;
    private static final String UID_ERROR_ID = "12345678901234567890123456789012";
    private static final String UID_FIELD = "UID_FIELD";
    private static final String UID_NOSAVED = "UID_NOSAVED";
    private static final String UID_PREFERENCES = "UIDPREFERENCES";

    static 
    {
        char ac[] = new char[16];
        ac[0] = '0';
        ac[1] = '1';
        ac[2] = '2';
        ac[3] = '3';
        ac[4] = '4';
        ac[5] = '5';
        ac[6] = '6';
        ac[7] = '7';
        ac[8] = '8';
        ac[9] = '9';
        ac[10] = 'a';
        ac[11] = 'b';
        ac[12] = 'c';
        ac[13] = 'd';
        ac[14] = 'e';
        ac[15] = 'f';
        DIGITS = ac;
    }
}

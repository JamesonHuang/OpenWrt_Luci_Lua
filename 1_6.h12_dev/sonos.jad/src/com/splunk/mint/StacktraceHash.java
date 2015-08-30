// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StacktraceHash
{

    StacktraceHash()
    {
    }

    public static String getMD5ForJavascriptError(String s)
    {
        return md5(s.replaceAll("@\\w+", "").replaceAll(hexRegex, "").replaceAll(guidRegex, "").replaceAll("$\\w+", ""));
    }

    public static final HashMap manipulateStacktrace(String s, String s1)
    {
        if(s != null && s1 != null) goto _L2; else goto _L1
_L1:
        HashMap hashmap = null;
_L5:
        return hashmap;
_L2:
        String s2;
        String as[];
        String s3;
        hashmap = new HashMap();
        s2 = null;
        as = s1.split("\n\t");
        if(as.length == 1)
            as = s1.split("\n");
        s3 = removeFirstDate(as[0]);
        String s12 = s3.substring(0, s3.indexOf(":"));
        String s4 = s12;
_L3:
        StringBuilder stringbuilder;
        hashmap.put("message", s3.replaceAll("\n", " ").replaceAll("Caused by:", ""));
        hashmap.put("klass", s4);
        if(s.contains("."))
        {
            String as4[] = s.split("\\.");
            Exception exception;
            String as1[];
            int i;
            int j;
            String s11;
            if(as4[0].length() > 3)
                s = as4[0];
            else
                s = as4[1];
        }
        stringbuilder = new StringBuilder();
        as1 = as;
        i = as1.length;
        for(j = 0; j < i; j++)
        {
            s11 = as1[j];
            if(s11.indexOf(s) != -1 && s11.indexOf(s) <= 20)
            {
                stringbuilder.append(s11);
                stringbuilder.append("\n");
            }
        }

        break MISSING_BLOCK_LABEL_234;
        exception;
        s4 = s3;
          goto _L3
        if(stringbuilder.length() == 0)
        {
            String as3[] = as;
            int i1 = as3.length;
            for(int j1 = 0; j1 < i1; j1++)
            {
                String s10 = as3[j1];
                if(s10.length() > 10 && s10.trim().startsWith("at ") && !s10.contains("...") && !s10.contains(".java.") && !s10.substring(0, 10).contains("android") && !s10.contains("org.apache") && !s10.contains("org.acra") && !s10.contains("dalvik") && !s10.contains(".acra."))
                {
                    stringbuilder.append(s10);
                    stringbuilder.append("\n");
                }
            }

        }
        if(stringbuilder.length() == 0)
        {
            String as2[] = as;
            int k = as2.length;
            for(int l = 0; l < k; l++)
            {
                String s9 = as2[l];
                if(s9.length() > 10 && s9.trim().startsWith("at ") && (s9.contains(".java") || s9.contains("Unknown")))
                {
                    stringbuilder.append(s9);
                    stringbuilder.append("\n");
                }
            }

        }
        String s5 = stringbuilder.toString().split("\n")[0];
        if(!s5.contains(s))
            s2 = s5.split("\\.")[1];
        hashmap.put("library", s2);
        String s6;
        String s7;
        if(s5.contains("("))
            s6 = s5.substring(1 + s5.indexOf("("), s5.indexOf(")"));
        else
            s6 = "Unknown";
        hashmap.put("where", s6);
        s7 = stringbuilder.toString().replaceAll("@\\w+", "").replaceAll(hexRegex, "").replaceAll(guidRegex, "").replaceAll("$\\w+", "");
        if(!s5.contains(".java:"))
        {
            String s8 = s5.replaceAll("\\d+", "");
            s7 = s7.replace(s5, s8);
        }
        hashmap.put("errorHash", md5(s7));
        if(true) goto _L5; else goto _L4
_L4:
    }

    private static String md5(String s)
    {
        String s1;
        MessageDigest messagedigest = MessageDigest.getInstance("MD5");
        messagedigest.reset();
        messagedigest.update(s.getBytes());
        s1 = (new BigInteger(1, messagedigest.digest())).toString(16);
_L1:
        String s2;
        if(s1.length() >= 32)
            break MISSING_BLOCK_LABEL_80;
        s2 = (new StringBuilder()).append("0").append(s1).toString();
        s1 = s2;
          goto _L1
        NoSuchAlgorithmException nosuchalgorithmexception;
        nosuchalgorithmexception;
        nosuchalgorithmexception.printStackTrace();
        s1 = "";
        return s1;
    }

    private static String removeFirstDate(String s)
    {
        if(s.contains("\n"))
        {
            String as[] = s.split("\\n");
            if(Pattern.compile("[\\d]{2}:[\\d]{2}:[\\d]{2}").matcher(s).find())
                s = as[1];
        }
        return s;
    }

    private static String guidRegex = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}";
    private static String hexRegex = "0[xX][0-9a-fA-F]+";

}

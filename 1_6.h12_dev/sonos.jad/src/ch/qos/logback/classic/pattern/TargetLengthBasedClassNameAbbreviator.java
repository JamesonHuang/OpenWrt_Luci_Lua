// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.pattern;

import java.io.PrintStream;

// Referenced classes of package ch.qos.logback.classic.pattern:
//            Abbreviator

public class TargetLengthBasedClassNameAbbreviator
    implements Abbreviator
{

    public TargetLengthBasedClassNameAbbreviator(int i)
    {
        targetLength = i;
    }

    static int computeDotIndexes(String s, int ai[])
    {
        int i = 0;
        int j = 0;
        do
        {
            int k = s.indexOf('.', i);
            if(k != -1 && j < 16)
            {
                ai[j] = k;
                j++;
                i = k + 1;
            } else
            {
                return j;
            }
        } while(true);
    }

    static void printArray(String s, int ai[])
    {
        System.out.print(s);
        int i = 0;
        while(i < ai.length) 
        {
            if(i == 0)
                System.out.print(ai[i]);
            else
                System.out.print((new StringBuilder()).append(", ").append(ai[i]).toString());
            i++;
        }
        System.out.println();
    }

    public String abbreviate(String s)
    {
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder(targetLength);
        if(s == null)
            throw new IllegalArgumentException("Class name may not be null");
        if(s.length() >= targetLength) goto _L2; else goto _L1
_L1:
        return s;
_L2:
        int ai[] = new int[16];
        int ai1[] = new int[17];
        int i = computeDotIndexes(s, ai);
        if(i != 0)
        {
            computeLengthArray(s, ai, ai1, i);
            int j = 0;
            while(j <= i) 
            {
                if(j == 0)
                    stringbuilder.append(s.substring(0, -1 + ai1[j]));
                else
                    stringbuilder.append(s.substring(ai[j - 1], ai[j - 1] + ai1[j]));
                j++;
            }
            s = stringbuilder.toString();
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    void computeLengthArray(String s, int ai[], int ai1[], int i)
    {
        int j = s.length() - targetLength;
        int k = 0;
        int l = j;
        while(k < i) 
        {
            int j1 = -1;
            if(k > 0)
                j1 = ai[k - 1];
            int k1 = -1 + (ai[k] - j1);
            if(k1 >= 1);
            int l1;
            int i2;
            if(l > 0)
            {
                if(k1 < 1)
                    l1 = k1;
                else
                    l1 = 1;
            } else
            {
                l1 = k1;
            }
            i2 = l - (k1 - l1);
            ai1[k] = l1 + 1;
            k++;
            l = i2;
        }
        int i1 = i - 1;
        ai1[i] = s.length() - ai[i1];
    }

    final int targetLength;
}

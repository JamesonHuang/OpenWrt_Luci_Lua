// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern;


public class FormatInfo
{

    public FormatInfo()
    {
        min = 0x80000000;
        max = 0x7fffffff;
        leftPad = true;
        leftTruncate = true;
    }

    public FormatInfo(int i, int j)
    {
        min = 0x80000000;
        max = 0x7fffffff;
        leftPad = true;
        leftTruncate = true;
        min = i;
        max = j;
    }

    public FormatInfo(int i, int j, boolean flag, boolean flag1)
    {
        min = 0x80000000;
        max = 0x7fffffff;
        leftPad = true;
        leftTruncate = true;
        min = i;
        max = j;
        leftPad = flag;
        leftTruncate = flag1;
    }

    public static FormatInfo valueOf(String s)
        throws IllegalArgumentException
    {
        if(s == null)
            throw new NullPointerException("Argument cannot be null");
        FormatInfo formatinfo = new FormatInfo();
        int i = s.indexOf('.');
        String s1 = null;
        if(i != -1)
        {
            String s2 = s.substring(0, i);
            if(i + 1 == s.length())
                throw new IllegalArgumentException((new StringBuilder()).append("Formatting string [").append(s).append("] should not end with '.'").toString());
            s1 = s.substring(i + 1);
            s = s2;
        }
        if(s != null && s.length() > 0)
        {
            int k = Integer.parseInt(s);
            if(k >= 0)
            {
                formatinfo.min = k;
            } else
            {
                formatinfo.min = -k;
                formatinfo.leftPad = false;
            }
        }
        if(s1 != null && s1.length() > 0)
        {
            int j = Integer.parseInt(s1);
            if(j >= 0)
            {
                formatinfo.max = j;
            } else
            {
                formatinfo.max = -j;
                formatinfo.leftTruncate = false;
            }
        }
        return formatinfo;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(!(obj instanceof FormatInfo))
        {
            flag = false;
        } else
        {
            FormatInfo formatinfo = (FormatInfo)obj;
            if(min != formatinfo.min || max != formatinfo.max || leftPad != formatinfo.leftPad || leftTruncate != formatinfo.leftTruncate)
                flag = false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public int getMax()
    {
        return max;
    }

    public int getMin()
    {
        return min;
    }

    public int hashCode()
    {
        int i = 1;
        int j = 31 * (31 * min + max);
        int k;
        int l;
        if(leftPad)
            k = i;
        else
            k = 0;
        l = 31 * (k + j);
        if(!leftTruncate)
            i = 0;
        return l + i;
    }

    public boolean isLeftPad()
    {
        return leftPad;
    }

    public boolean isLeftTruncate()
    {
        return leftTruncate;
    }

    public void setLeftPad(boolean flag)
    {
        leftPad = flag;
    }

    public void setLeftTruncate(boolean flag)
    {
        leftTruncate = flag;
    }

    public void setMax(int i)
    {
        max = i;
    }

    public void setMin(int i)
    {
        min = i;
    }

    public String toString()
    {
        return (new StringBuilder()).append("FormatInfo(").append(min).append(", ").append(max).append(", ").append(leftPad).append(", ").append(leftTruncate).append(")").toString();
    }

    private boolean leftPad;
    private boolean leftTruncate;
    private int max;
    private int min;
}

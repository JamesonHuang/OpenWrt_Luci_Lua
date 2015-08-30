// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern;


// Referenced classes of package ch.qos.logback.core.pattern:
//            Converter, FormatInfo, SpacePadder

public abstract class FormattingConverter extends Converter
{

    public FormattingConverter()
    {
    }

    public final FormatInfo getFormattingInfo()
    {
        return formattingInfo;
    }

    public final void setFormattingInfo(FormatInfo formatinfo)
    {
        if(formattingInfo != null)
        {
            throw new IllegalStateException("FormattingInfo has been already set");
        } else
        {
            formattingInfo = formatinfo;
            return;
        }
    }

    public final void write(StringBuilder stringbuilder, Object obj)
    {
        String s = convert(obj);
        if(formattingInfo != null) goto _L2; else goto _L1
_L1:
        stringbuilder.append(s);
_L4:
        return;
_L2:
        int i = formattingInfo.getMin();
        int j = formattingInfo.getMax();
        if(s == null)
        {
            if(i > 0)
                SpacePadder.spacePad(stringbuilder, i);
        } else
        {
            int k = s.length();
            if(k > j)
            {
                if(formattingInfo.isLeftTruncate())
                    stringbuilder.append(s.substring(k - j));
                else
                    stringbuilder.append(s.substring(0, j));
            } else
            if(k < i)
            {
                if(formattingInfo.isLeftPad())
                    SpacePadder.leftPad(stringbuilder, s, i);
                else
                    SpacePadder.rightPad(stringbuilder, s, i);
            } else
            {
                stringbuilder.append(s);
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    static final int INITIAL_BUF_SIZE = 256;
    static final int MAX_CAPACITY = 1024;
    FormatInfo formattingInfo;
}

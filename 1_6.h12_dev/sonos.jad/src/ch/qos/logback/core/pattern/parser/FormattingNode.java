// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern.parser;

import ch.qos.logback.core.pattern.FormatInfo;

// Referenced classes of package ch.qos.logback.core.pattern.parser:
//            Node

public class FormattingNode extends Node
{

    FormattingNode(int i)
    {
        super(i);
    }

    FormattingNode(int i, Object obj)
    {
        super(i, obj);
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        flag = false;
        break MISSING_BLOCK_LABEL_2;
        while(true) 
        {
            do
                return flag;
            while(!super.equals(obj) || !(obj instanceof FormattingNode));
            FormattingNode formattingnode = (FormattingNode)obj;
            if(formatInfo != null)
                flag = formatInfo.equals(formattingnode.formatInfo);
            else
            if(formattingnode.formatInfo == null)
                flag = true;
        }
    }

    public FormatInfo getFormatInfo()
    {
        return formatInfo;
    }

    public int hashCode()
    {
        int i = 31 * super.hashCode();
        int j;
        if(formatInfo != null)
            j = formatInfo.hashCode();
        else
            j = 0;
        return j + i;
    }

    public void setFormatInfo(FormatInfo formatinfo)
    {
        formatInfo = formatinfo;
    }

    FormatInfo formatInfo;
}

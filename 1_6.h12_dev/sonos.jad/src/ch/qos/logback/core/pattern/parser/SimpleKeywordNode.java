// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern.parser;

import java.util.List;

// Referenced classes of package ch.qos.logback.core.pattern.parser:
//            FormattingNode

public class SimpleKeywordNode extends FormattingNode
{

    protected SimpleKeywordNode(int i, Object obj)
    {
        super(i, obj);
    }

    SimpleKeywordNode(Object obj)
    {
        super(1, obj);
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
            while(!super.equals(obj) || !(obj instanceof SimpleKeywordNode));
            SimpleKeywordNode simplekeywordnode = (SimpleKeywordNode)obj;
            if(optionList != null)
                flag = optionList.equals(simplekeywordnode.optionList);
            else
            if(simplekeywordnode.optionList == null)
                flag = true;
        }
    }

    public List getOptions()
    {
        return optionList;
    }

    public int hashCode()
    {
        return super.hashCode();
    }

    public void setOptions(List list)
    {
        optionList = list;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(optionList == null)
            stringbuffer.append((new StringBuilder()).append("KeyWord(").append(value).append(",").append(formatInfo).append(")").toString());
        else
            stringbuffer.append((new StringBuilder()).append("KeyWord(").append(value).append(", ").append(formatInfo).append(",").append(optionList).append(")").toString());
        stringbuffer.append(printNext());
        return stringbuffer.toString();
    }

    List optionList;
}

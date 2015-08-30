// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern;

import ch.qos.logback.core.Layout;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;

public class PatternLayoutEncoderBase extends LayoutWrappingEncoder
{

    public PatternLayoutEncoderBase()
    {
        outputPatternAsHeader = false;
    }

    public String getPattern()
    {
        return pattern;
    }

    public boolean isOutputPatternAsHeader()
    {
        return outputPatternAsHeader;
    }

    public boolean isOutputPatternAsPresentationHeader()
    {
        return outputPatternAsHeader;
    }

    public void setLayout(Layout layout)
    {
        throw new UnsupportedOperationException((new StringBuilder()).append("one cannot set the layout of ").append(getClass().getName()).toString());
    }

    public void setOutputPatternAsHeader(boolean flag)
    {
        outputPatternAsHeader = flag;
    }

    public void setOutputPatternAsPresentationHeader(boolean flag)
    {
        addWarn("[outputPatternAsPresentationHeader] property is deprecated. Please use [outputPatternAsHeader] option instead.");
        outputPatternAsHeader = flag;
    }

    public void setPattern(String s)
    {
        pattern = s;
    }

    protected boolean outputPatternAsHeader;
    String pattern;
}

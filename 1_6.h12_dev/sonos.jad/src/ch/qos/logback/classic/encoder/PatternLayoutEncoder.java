// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.encoder;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.core.pattern.PatternLayoutEncoderBase;

public class PatternLayoutEncoder extends PatternLayoutEncoderBase
{

    public PatternLayoutEncoder()
    {
    }

    public void start()
    {
        PatternLayout patternlayout = new PatternLayout();
        patternlayout.setContext(context);
        patternlayout.setPattern(getPattern());
        patternlayout.setOutputPatternAsHeader(outputPatternAsHeader);
        patternlayout.start();
        layout = patternlayout;
        super.start();
    }
}

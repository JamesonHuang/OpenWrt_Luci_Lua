// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic;

import ch.qos.logback.classic.pattern.CallerDataConverter;
import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import ch.qos.logback.classic.pattern.ContextNameConverter;
import ch.qos.logback.classic.pattern.DateConverter;
import ch.qos.logback.classic.pattern.EnsureExceptionHandling;
import ch.qos.logback.classic.pattern.ExtendedThrowableProxyConverter;
import ch.qos.logback.classic.pattern.FileOfCallerConverter;
import ch.qos.logback.classic.pattern.LevelConverter;
import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import ch.qos.logback.classic.pattern.LocalSequenceNumberConverter;
import ch.qos.logback.classic.pattern.LoggerConverter;
import ch.qos.logback.classic.pattern.MDCConverter;
import ch.qos.logback.classic.pattern.MarkerConverter;
import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.pattern.MethodOfCallerConverter;
import ch.qos.logback.classic.pattern.NopThrowableInformationConverter;
import ch.qos.logback.classic.pattern.PropertyConverter;
import ch.qos.logback.classic.pattern.RelativeTimeConverter;
import ch.qos.logback.classic.pattern.RootCauseFirstThrowableProxyConverter;
import ch.qos.logback.classic.pattern.ThreadConverter;
import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.pattern.color.HighlightingCompositeConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.PatternLayoutBase;
import ch.qos.logback.core.pattern.color.*;
import ch.qos.logback.core.pattern.parser.Parser;
import java.util.HashMap;
import java.util.Map;

public class PatternLayout extends PatternLayoutBase
{

    public PatternLayout()
    {
        postCompileProcessor = new EnsureExceptionHandling();
    }

    public String doLayout(ILoggingEvent iloggingevent)
    {
        String s;
        if(!isStarted())
            s = "";
        else
            s = writeLoopOnConverters(iloggingevent);
        return s;
    }

    public volatile String doLayout(Object obj)
    {
        return doLayout((ILoggingEvent)obj);
    }

    public Map getDefaultConverterMap()
    {
        return defaultConverterMap;
    }

    public static final Map defaultConverterMap;

    static 
    {
        defaultConverterMap = new HashMap();
        defaultConverterMap.putAll(Parser.DEFAULT_COMPOSITE_CONVERTER_MAP);
        defaultConverterMap.put("d", ch/qos/logback/classic/pattern/DateConverter.getName());
        defaultConverterMap.put("date", ch/qos/logback/classic/pattern/DateConverter.getName());
        defaultConverterMap.put("r", ch/qos/logback/classic/pattern/RelativeTimeConverter.getName());
        defaultConverterMap.put("relative", ch/qos/logback/classic/pattern/RelativeTimeConverter.getName());
        defaultConverterMap.put("level", ch/qos/logback/classic/pattern/LevelConverter.getName());
        defaultConverterMap.put("le", ch/qos/logback/classic/pattern/LevelConverter.getName());
        defaultConverterMap.put("p", ch/qos/logback/classic/pattern/LevelConverter.getName());
        defaultConverterMap.put("t", ch/qos/logback/classic/pattern/ThreadConverter.getName());
        defaultConverterMap.put("thread", ch/qos/logback/classic/pattern/ThreadConverter.getName());
        defaultConverterMap.put("lo", ch/qos/logback/classic/pattern/LoggerConverter.getName());
        defaultConverterMap.put("logger", ch/qos/logback/classic/pattern/LoggerConverter.getName());
        defaultConverterMap.put("c", ch/qos/logback/classic/pattern/LoggerConverter.getName());
        defaultConverterMap.put("m", ch/qos/logback/classic/pattern/MessageConverter.getName());
        defaultConverterMap.put("msg", ch/qos/logback/classic/pattern/MessageConverter.getName());
        defaultConverterMap.put("message", ch/qos/logback/classic/pattern/MessageConverter.getName());
        defaultConverterMap.put("C", ch/qos/logback/classic/pattern/ClassOfCallerConverter.getName());
        defaultConverterMap.put("class", ch/qos/logback/classic/pattern/ClassOfCallerConverter.getName());
        defaultConverterMap.put("M", ch/qos/logback/classic/pattern/MethodOfCallerConverter.getName());
        defaultConverterMap.put("method", ch/qos/logback/classic/pattern/MethodOfCallerConverter.getName());
        defaultConverterMap.put("L", ch/qos/logback/classic/pattern/LineOfCallerConverter.getName());
        defaultConverterMap.put("line", ch/qos/logback/classic/pattern/LineOfCallerConverter.getName());
        defaultConverterMap.put("F", ch/qos/logback/classic/pattern/FileOfCallerConverter.getName());
        defaultConverterMap.put("file", ch/qos/logback/classic/pattern/FileOfCallerConverter.getName());
        defaultConverterMap.put("X", ch/qos/logback/classic/pattern/MDCConverter.getName());
        defaultConverterMap.put("mdc", ch/qos/logback/classic/pattern/MDCConverter.getName());
        defaultConverterMap.put("ex", ch/qos/logback/classic/pattern/ThrowableProxyConverter.getName());
        defaultConverterMap.put("exception", ch/qos/logback/classic/pattern/ThrowableProxyConverter.getName());
        defaultConverterMap.put("rEx", ch/qos/logback/classic/pattern/RootCauseFirstThrowableProxyConverter.getName());
        defaultConverterMap.put("rootException", ch/qos/logback/classic/pattern/RootCauseFirstThrowableProxyConverter.getName());
        defaultConverterMap.put("throwable", ch/qos/logback/classic/pattern/ThrowableProxyConverter.getName());
        defaultConverterMap.put("xEx", ch/qos/logback/classic/pattern/ExtendedThrowableProxyConverter.getName());
        defaultConverterMap.put("xException", ch/qos/logback/classic/pattern/ExtendedThrowableProxyConverter.getName());
        defaultConverterMap.put("xThrowable", ch/qos/logback/classic/pattern/ExtendedThrowableProxyConverter.getName());
        defaultConverterMap.put("nopex", ch/qos/logback/classic/pattern/NopThrowableInformationConverter.getName());
        defaultConverterMap.put("nopexception", ch/qos/logback/classic/pattern/NopThrowableInformationConverter.getName());
        defaultConverterMap.put("cn", ch/qos/logback/classic/pattern/ContextNameConverter.getName());
        defaultConverterMap.put("contextName", ch/qos/logback/classic/pattern/ContextNameConverter.getName());
        defaultConverterMap.put("caller", ch/qos/logback/classic/pattern/CallerDataConverter.getName());
        defaultConverterMap.put("marker", ch/qos/logback/classic/pattern/MarkerConverter.getName());
        defaultConverterMap.put("property", ch/qos/logback/classic/pattern/PropertyConverter.getName());
        defaultConverterMap.put("n", ch/qos/logback/classic/pattern/LineSeparatorConverter.getName());
        defaultConverterMap.put("black", ch/qos/logback/core/pattern/color/BlackCompositeConverter.getName());
        defaultConverterMap.put("red", ch/qos/logback/core/pattern/color/RedCompositeConverter.getName());
        defaultConverterMap.put("green", ch/qos/logback/core/pattern/color/GreenCompositeConverter.getName());
        defaultConverterMap.put("yellow", ch/qos/logback/core/pattern/color/YellowCompositeConverter.getName());
        defaultConverterMap.put("blue", ch/qos/logback/core/pattern/color/BlueCompositeConverter.getName());
        defaultConverterMap.put("magenta", ch/qos/logback/core/pattern/color/MagentaCompositeConverter.getName());
        defaultConverterMap.put("cyan", ch/qos/logback/core/pattern/color/CyanCompositeConverter.getName());
        defaultConverterMap.put("white", ch/qos/logback/core/pattern/color/WhiteCompositeConverter.getName());
        defaultConverterMap.put("gray", ch/qos/logback/core/pattern/color/GrayCompositeConverter.getName());
        defaultConverterMap.put("boldRed", ch/qos/logback/core/pattern/color/BoldRedCompositeConverter.getName());
        defaultConverterMap.put("boldGreen", ch/qos/logback/core/pattern/color/BoldGreenCompositeConverter.getName());
        defaultConverterMap.put("boldYellow", ch/qos/logback/core/pattern/color/BoldYellowCompositeConverter.getName());
        defaultConverterMap.put("boldBlue", ch/qos/logback/core/pattern/color/BoldBlueCompositeConverter.getName());
        defaultConverterMap.put("boldMagenta", ch/qos/logback/core/pattern/color/BoldMagentaCompositeConverter.getName());
        defaultConverterMap.put("boldCyan", ch/qos/logback/core/pattern/color/BoldCyanCompositeConverter.getName());
        defaultConverterMap.put("boldWhite", ch/qos/logback/core/pattern/color/BoldWhiteCompositeConverter.getName());
        defaultConverterMap.put("highlight", ch/qos/logback/classic/pattern/color/HighlightingCompositeConverter.getName());
        defaultConverterMap.put("lsn", ch/qos/logback/classic/pattern/LocalSequenceNumberConverter.getName());
    }
}

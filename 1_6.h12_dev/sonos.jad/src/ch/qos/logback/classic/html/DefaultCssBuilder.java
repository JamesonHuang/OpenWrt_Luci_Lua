// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.html;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.html.CssBuilder;

public class DefaultCssBuilder
    implements CssBuilder
{

    public DefaultCssBuilder()
    {
    }

    public void addCss(StringBuilder stringbuilder)
    {
        stringbuilder.append("<style  type=\"text/css\">");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("table { margin-left: 2em; margin-right: 2em; border-left: 2px solid #AAA; }");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("TR.even { background: #FFFFFF; }");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("TR.odd { background: #EAEAEA; }");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("TR.warn TD.Level, TR.error TD.Level, TR.fatal TD.Level {font-weight: bold; color: #FF4040 }");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("TD { padding-right: 1ex; padding-left: 1ex; border-right: 2px solid #AAA; }");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("TD.Time, TD.Date { text-align: right; font-family: courier, monospace; font-size: smaller; }");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("TD.Thread { text-align: left; }");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("TD.Level { text-align: right; }");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("TD.Logger { text-align: left; }");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("TR.header { background: #596ED5; color: #FFF; font-weight: bold; font-size: larger; }");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("TD.Exception { background: #A2AEE8; font-family: courier, monospace;}");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
        stringbuilder.append("</style>");
        stringbuilder.append(CoreConstants.LINE_SEPARATOR);
    }
}

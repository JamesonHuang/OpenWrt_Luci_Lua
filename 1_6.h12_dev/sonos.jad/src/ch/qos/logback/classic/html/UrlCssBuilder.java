// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.html;

import ch.qos.logback.core.html.CssBuilder;

public class UrlCssBuilder
    implements CssBuilder
{

    public UrlCssBuilder()
    {
        url = "http://logback.qos.ch/css/classic.css";
    }

    public void addCss(StringBuilder stringbuilder)
    {
        stringbuilder.append("<link REL=StyleSheet HREF=\"");
        stringbuilder.append(url);
        stringbuilder.append("\" TITLE=\"Basic\" />");
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String s)
    {
        url = s;
    }

    String url;
}

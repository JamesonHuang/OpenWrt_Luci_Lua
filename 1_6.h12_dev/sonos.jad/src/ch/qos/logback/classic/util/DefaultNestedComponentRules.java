// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.util;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.joran.spi.DefaultNestedComponentRegistry;
import ch.qos.logback.core.net.ssl.SSLNestedComponentRegistryRules;

public class DefaultNestedComponentRules
{

    public DefaultNestedComponentRules()
    {
    }

    public static void addDefaultNestedComponentRegistryRules(DefaultNestedComponentRegistry defaultnestedcomponentregistry)
    {
        defaultnestedcomponentregistry.add(ch/qos/logback/core/AppenderBase, "layout", ch/qos/logback/classic/PatternLayout);
        defaultnestedcomponentregistry.add(ch/qos/logback/core/UnsynchronizedAppenderBase, "layout", ch/qos/logback/classic/PatternLayout);
        defaultnestedcomponentregistry.add(ch/qos/logback/core/AppenderBase, "encoder", ch/qos/logback/classic/encoder/PatternLayoutEncoder);
        defaultnestedcomponentregistry.add(ch/qos/logback/core/UnsynchronizedAppenderBase, "encoder", ch/qos/logback/classic/encoder/PatternLayoutEncoder);
        SSLNestedComponentRegistryRules.addDefaultNestedComponentRegistryRules(defaultnestedcomponentregistry);
    }
}

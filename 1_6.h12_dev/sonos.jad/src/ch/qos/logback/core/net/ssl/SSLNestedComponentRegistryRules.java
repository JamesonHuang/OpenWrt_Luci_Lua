// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.ssl;

import ch.qos.logback.core.joran.spi.DefaultNestedComponentRegistry;

// Referenced classes of package ch.qos.logback.core.net.ssl:
//            SSLComponent, SSLConfiguration, SSLParametersConfiguration, KeyStoreFactoryBean, 
//            KeyManagerFactoryFactoryBean, TrustManagerFactoryFactoryBean, SecureRandomFactoryBean

public class SSLNestedComponentRegistryRules
{

    public SSLNestedComponentRegistryRules()
    {
    }

    public static void addDefaultNestedComponentRegistryRules(DefaultNestedComponentRegistry defaultnestedcomponentregistry)
    {
        defaultnestedcomponentregistry.add(ch/qos/logback/core/net/ssl/SSLComponent, "ssl", ch/qos/logback/core/net/ssl/SSLConfiguration);
        defaultnestedcomponentregistry.add(ch/qos/logback/core/net/ssl/SSLConfiguration, "parameters", ch/qos/logback/core/net/ssl/SSLParametersConfiguration);
        defaultnestedcomponentregistry.add(ch/qos/logback/core/net/ssl/SSLConfiguration, "keyStore", ch/qos/logback/core/net/ssl/KeyStoreFactoryBean);
        defaultnestedcomponentregistry.add(ch/qos/logback/core/net/ssl/SSLConfiguration, "trustStore", ch/qos/logback/core/net/ssl/KeyStoreFactoryBean);
        defaultnestedcomponentregistry.add(ch/qos/logback/core/net/ssl/SSLConfiguration, "keyManagerFactory", ch/qos/logback/core/net/ssl/KeyManagerFactoryFactoryBean);
        defaultnestedcomponentregistry.add(ch/qos/logback/core/net/ssl/SSLConfiguration, "trustManagerFactory", ch/qos/logback/core/net/ssl/TrustManagerFactoryFactoryBean);
        defaultnestedcomponentregistry.add(ch/qos/logback/core/net/ssl/SSLConfiguration, "secureRandom", ch/qos/logback/core/net/ssl/SecureRandomFactoryBean);
    }
}

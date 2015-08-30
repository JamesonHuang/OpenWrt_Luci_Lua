// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.ssl;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.net.ssl.TrustManagerFactory;

public class TrustManagerFactoryFactoryBean
{

    public TrustManagerFactoryFactoryBean()
    {
    }

    public TrustManagerFactory createTrustManagerFactory()
        throws NoSuchProviderException, NoSuchAlgorithmException
    {
        TrustManagerFactory trustmanagerfactory;
        if(getProvider() != null)
            trustmanagerfactory = TrustManagerFactory.getInstance(getAlgorithm(), getProvider());
        else
            trustmanagerfactory = TrustManagerFactory.getInstance(getAlgorithm());
        return trustmanagerfactory;
    }

    public String getAlgorithm()
    {
        String s;
        if(algorithm == null)
            s = TrustManagerFactory.getDefaultAlgorithm();
        else
            s = algorithm;
        return s;
    }

    public String getProvider()
    {
        return provider;
    }

    public void setAlgorithm(String s)
    {
        algorithm = s;
    }

    public void setProvider(String s)
    {
        provider = s;
    }

    private String algorithm;
    private String provider;
}

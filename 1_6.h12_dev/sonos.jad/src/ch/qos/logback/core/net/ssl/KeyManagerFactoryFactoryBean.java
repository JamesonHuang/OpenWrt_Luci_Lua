// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.ssl;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.net.ssl.KeyManagerFactory;

public class KeyManagerFactoryFactoryBean
{

    public KeyManagerFactoryFactoryBean()
    {
    }

    public KeyManagerFactory createKeyManagerFactory()
        throws NoSuchProviderException, NoSuchAlgorithmException
    {
        KeyManagerFactory keymanagerfactory;
        if(getProvider() != null)
            keymanagerfactory = KeyManagerFactory.getInstance(getAlgorithm(), getProvider());
        else
            keymanagerfactory = KeyManagerFactory.getInstance(getAlgorithm());
        return keymanagerfactory;
    }

    public String getAlgorithm()
    {
        String s;
        if(algorithm == null)
            s = KeyManagerFactory.getDefaultAlgorithm();
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

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.ssl;

import java.security.*;

public class SecureRandomFactoryBean
{

    public SecureRandomFactoryBean()
    {
    }

    public SecureRandom createSecureRandom()
        throws NoSuchProviderException, NoSuchAlgorithmException
    {
        SecureRandom securerandom1;
label0:
        {
            SecureRandom securerandom;
            try
            {
                if(getProvider() != null)
                {
                    securerandom1 = SecureRandom.getInstance(getAlgorithm(), getProvider());
                    break label0;
                }
                securerandom = SecureRandom.getInstance(getAlgorithm());
            }
            catch(NoSuchProviderException nosuchproviderexception)
            {
                throw new NoSuchProviderException((new StringBuilder()).append("no such secure random provider: ").append(getProvider()).toString());
            }
            catch(NoSuchAlgorithmException nosuchalgorithmexception)
            {
                throw new NoSuchAlgorithmException((new StringBuilder()).append("no such secure random algorithm: ").append(getAlgorithm()).toString());
            }
            securerandom1 = securerandom;
        }
        return securerandom1;
    }

    public String getAlgorithm()
    {
        String s;
        if(algorithm == null)
            s = "SHA1PRNG";
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

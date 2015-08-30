// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.ssl;

import ch.qos.logback.core.spi.ContextAware;
import java.security.*;
import java.security.cert.CertificateException;
import javax.net.ssl.*;

// Referenced classes of package ch.qos.logback.core.net.ssl:
//            KeyStoreFactoryBean, KeyManagerFactoryFactoryBean, SecureRandomFactoryBean, TrustManagerFactoryFactoryBean

public class SSLContextFactoryBean
{

    public SSLContextFactoryBean()
    {
    }

    private KeyManager[] createKeyManagers(ContextAware contextaware)
        throws NoSuchProviderException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException
    {
        KeyManager akeymanager[];
        if(getKeyStore() == null)
        {
            akeymanager = null;
        } else
        {
            KeyStore keystore = getKeyStore().createKeyStore();
            contextaware.addInfo((new StringBuilder()).append("key store of type '").append(keystore.getType()).append("' provider '").append(keystore.getProvider()).append("': ").append(getKeyStore().getLocation()).toString());
            KeyManagerFactory keymanagerfactory = getKeyManagerFactory().createKeyManagerFactory();
            contextaware.addInfo((new StringBuilder()).append("key manager algorithm '").append(keymanagerfactory.getAlgorithm()).append("' provider '").append(keymanagerfactory.getProvider()).append("'").toString());
            keymanagerfactory.init(keystore, getKeyStore().getPassword().toCharArray());
            akeymanager = keymanagerfactory.getKeyManagers();
        }
        return akeymanager;
    }

    private SecureRandom createSecureRandom(ContextAware contextaware)
        throws NoSuchProviderException, NoSuchAlgorithmException
    {
        SecureRandom securerandom = getSecureRandom().createSecureRandom();
        contextaware.addInfo((new StringBuilder()).append("secure random algorithm '").append(securerandom.getAlgorithm()).append("' provider '").append(securerandom.getProvider()).append("'").toString());
        return securerandom;
    }

    private TrustManager[] createTrustManagers(ContextAware contextaware)
        throws NoSuchProviderException, NoSuchAlgorithmException, KeyStoreException
    {
        TrustManager atrustmanager[];
        if(getTrustStore() == null)
        {
            atrustmanager = null;
        } else
        {
            KeyStore keystore = getTrustStore().createKeyStore();
            contextaware.addInfo((new StringBuilder()).append("trust store of type '").append(keystore.getType()).append("' provider '").append(keystore.getProvider()).append("': ").append(getTrustStore().getLocation()).toString());
            TrustManagerFactory trustmanagerfactory = getTrustManagerFactory().createTrustManagerFactory();
            contextaware.addInfo((new StringBuilder()).append("trust manager algorithm '").append(trustmanagerfactory.getAlgorithm()).append("' provider '").append(trustmanagerfactory.getProvider()).append("'").toString());
            trustmanagerfactory.init(keystore);
            atrustmanager = trustmanagerfactory.getTrustManagers();
        }
        return atrustmanager;
    }

    private KeyStoreFactoryBean keyStoreFromSystemProperties(String s)
    {
        KeyStoreFactoryBean keystorefactorybean;
        if(System.getProperty(s) == null)
        {
            keystorefactorybean = null;
        } else
        {
            keystorefactorybean = new KeyStoreFactoryBean();
            keystorefactorybean.setLocation(locationFromSystemProperty(s));
            keystorefactorybean.setProvider(System.getProperty((new StringBuilder()).append(s).append("Provider").toString()));
            keystorefactorybean.setPassword(System.getProperty((new StringBuilder()).append(s).append("Password").toString()));
            keystorefactorybean.setType(System.getProperty((new StringBuilder()).append(s).append("Type").toString()));
        }
        return keystorefactorybean;
    }

    private String locationFromSystemProperty(String s)
    {
        String s1 = System.getProperty(s);
        if(s1 != null && !s1.startsWith("file:"))
            s1 = (new StringBuilder()).append("file:").append(s1).toString();
        return s1;
    }

    public SSLContext createContext(ContextAware contextaware)
        throws NoSuchProviderException, NoSuchAlgorithmException, KeyManagementException, UnrecoverableKeyException, KeyStoreException, CertificateException
    {
        SSLContext sslcontext;
        if(getProvider() != null)
            sslcontext = SSLContext.getInstance(getProtocol(), getProvider());
        else
            sslcontext = SSLContext.getInstance(getProtocol());
        contextaware.addInfo((new StringBuilder()).append("SSL protocol '").append(sslcontext.getProtocol()).append("' provider '").append(sslcontext.getProvider()).append("'").toString());
        sslcontext.init(createKeyManagers(contextaware), createTrustManagers(contextaware), createSecureRandom(contextaware));
        return sslcontext;
    }

    public KeyManagerFactoryFactoryBean getKeyManagerFactory()
    {
        KeyManagerFactoryFactoryBean keymanagerfactoryfactorybean;
        if(keyManagerFactory == null)
            keymanagerfactoryfactorybean = new KeyManagerFactoryFactoryBean();
        else
            keymanagerfactoryfactorybean = keyManagerFactory;
        return keymanagerfactoryfactorybean;
    }

    public KeyStoreFactoryBean getKeyStore()
    {
        if(keyStore == null)
            keyStore = keyStoreFromSystemProperties("javax.net.ssl.keyStore");
        return keyStore;
    }

    public String getProtocol()
    {
        String s;
        if(protocol == null)
            s = "SSL";
        else
            s = protocol;
        return s;
    }

    public String getProvider()
    {
        return provider;
    }

    public SecureRandomFactoryBean getSecureRandom()
    {
        SecureRandomFactoryBean securerandomfactorybean;
        if(secureRandom == null)
            securerandomfactorybean = new SecureRandomFactoryBean();
        else
            securerandomfactorybean = secureRandom;
        return securerandomfactorybean;
    }

    public TrustManagerFactoryFactoryBean getTrustManagerFactory()
    {
        TrustManagerFactoryFactoryBean trustmanagerfactoryfactorybean;
        if(trustManagerFactory == null)
            trustmanagerfactoryfactorybean = new TrustManagerFactoryFactoryBean();
        else
            trustmanagerfactoryfactorybean = trustManagerFactory;
        return trustmanagerfactoryfactorybean;
    }

    public KeyStoreFactoryBean getTrustStore()
    {
        if(trustStore == null)
            trustStore = keyStoreFromSystemProperties("javax.net.ssl.trustStore");
        return trustStore;
    }

    public void setKeyManagerFactory(KeyManagerFactoryFactoryBean keymanagerfactoryfactorybean)
    {
        keyManagerFactory = keymanagerfactoryfactorybean;
    }

    public void setKeyStore(KeyStoreFactoryBean keystorefactorybean)
    {
        keyStore = keystorefactorybean;
    }

    public void setProtocol(String s)
    {
        protocol = s;
    }

    public void setProvider(String s)
    {
        provider = s;
    }

    public void setSecureRandom(SecureRandomFactoryBean securerandomfactorybean)
    {
        secureRandom = securerandomfactorybean;
    }

    public void setTrustManagerFactory(TrustManagerFactoryFactoryBean trustmanagerfactoryfactorybean)
    {
        trustManagerFactory = trustmanagerfactoryfactorybean;
    }

    public void setTrustStore(KeyStoreFactoryBean keystorefactorybean)
    {
        trustStore = keystorefactorybean;
    }

    private static final String JSSE_KEY_STORE_PROPERTY = "javax.net.ssl.keyStore";
    private static final String JSSE_TRUST_STORE_PROPERTY = "javax.net.ssl.trustStore";
    private KeyManagerFactoryFactoryBean keyManagerFactory;
    private KeyStoreFactoryBean keyStore;
    private String protocol;
    private String provider;
    private SecureRandomFactoryBean secureRandom;
    private TrustManagerFactoryFactoryBean trustManagerFactory;
    private KeyStoreFactoryBean trustStore;
}

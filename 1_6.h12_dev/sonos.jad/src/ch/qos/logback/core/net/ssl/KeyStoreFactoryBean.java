// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.ssl;

import ch.qos.logback.core.util.LocationUtil;
import java.io.*;
import java.net.URL;
import java.security.*;

public class KeyStoreFactoryBean
{

    public KeyStoreFactoryBean()
    {
    }

    private KeyStore newKeyStore()
        throws NoSuchAlgorithmException, NoSuchProviderException, KeyStoreException
    {
        KeyStore keystore;
        if(getProvider() != null)
            keystore = KeyStore.getInstance(getType(), getProvider());
        else
            keystore = KeyStore.getInstance(getType());
        return keystore;
    }

    public KeyStore createKeyStore()
        throws NoSuchProviderException, NoSuchAlgorithmException, KeyStoreException
    {
        InputStream inputstream;
        if(getLocation() == null)
            throw new IllegalArgumentException("location is required");
        inputstream = null;
        InputStream inputstream2 = LocationUtil.urlForResource(getLocation()).openStream();
        inputstream = inputstream2;
        KeyStore keystore;
        keystore = newKeyStore();
        keystore.load(inputstream, getPassword().toCharArray());
        if(inputstream != null)
            try
            {
                inputstream.close();
            }
            catch(IOException ioexception1)
            {
                ioexception1.printStackTrace(System.err);
            }
        return keystore;
        NoSuchProviderException nosuchproviderexception;
        nosuchproviderexception;
        throw new NoSuchProviderException((new StringBuilder()).append("no such keystore provider: ").append(getProvider()).toString());
        Exception exception4;
        exception4;
        InputStream inputstream1;
        Exception exception1;
        inputstream1 = inputstream;
        exception1 = exception4;
_L1:
        Exception exception3;
        Exception exception2;
        FileNotFoundException filenotfoundexception;
        NoSuchAlgorithmException nosuchalgorithmexception;
        if(inputstream1 != null)
            try
            {
                inputstream1.close();
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace(System.err);
            }
        throw exception1;
        nosuchalgorithmexception;
        throw new NoSuchAlgorithmException((new StringBuilder()).append("no such keystore type: ").append(getType()).toString());
        filenotfoundexception;
        throw new KeyStoreException((new StringBuilder()).append(getLocation()).append(": file not found").toString());
        exception2;
        inputstream1 = null;
        exception3 = exception2;
_L2:
        throw new KeyStoreException((new StringBuilder()).append(getLocation()).append(": ").append(exception3.getMessage()).toString(), exception3);
        exception1;
          goto _L1
        Exception exception;
        exception;
        inputstream1 = null;
        exception1 = exception;
          goto _L1
        Exception exception5;
        exception5;
        inputstream1 = inputstream;
        exception3 = exception5;
          goto _L2
    }

    public String getLocation()
    {
        return location;
    }

    public String getPassword()
    {
        String s;
        if(password == null)
            s = "changeit";
        else
            s = password;
        return s;
    }

    public String getProvider()
    {
        return provider;
    }

    public String getType()
    {
        String s;
        if(type == null)
            s = "JKS";
        else
            s = type;
        return s;
    }

    public void setLocation(String s)
    {
        location = s;
    }

    public void setPassword(String s)
    {
        password = s;
    }

    public void setProvider(String s)
    {
        provider = s;
    }

    public void setType(String s)
    {
        type = s;
    }

    private String location;
    private String password;
    private String provider;
    private String type;
}

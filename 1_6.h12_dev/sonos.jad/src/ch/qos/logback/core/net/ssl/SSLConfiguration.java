// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.ssl;


// Referenced classes of package ch.qos.logback.core.net.ssl:
//            SSLContextFactoryBean, SSLParametersConfiguration

public class SSLConfiguration extends SSLContextFactoryBean
{

    public SSLConfiguration()
    {
    }

    public SSLParametersConfiguration getParameters()
    {
        if(parameters == null)
            parameters = new SSLParametersConfiguration();
        return parameters;
    }

    public void setParameters(SSLParametersConfiguration sslparametersconfiguration)
    {
        parameters = sslparametersconfiguration;
    }

    private SSLParametersConfiguration parameters;
}

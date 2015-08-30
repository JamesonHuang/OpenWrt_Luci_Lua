// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.net.ssl;

import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.util.OptionHelper;
import ch.qos.logback.core.util.StringCollectionUtil;
import java.util.*;

// Referenced classes of package ch.qos.logback.core.net.ssl:
//            SSLConfigurable

public class SSLParametersConfiguration extends ContextAwareBase
{

    public SSLParametersConfiguration()
    {
    }

    private String[] enabledCipherSuites(String as[], String as1[])
    {
        if(enabledCipherSuites == null)
        {
            String as2[];
            int i;
            if(OptionHelper.isEmpty(getIncludedCipherSuites()) && OptionHelper.isEmpty(getExcludedCipherSuites()))
                enabledCipherSuites = (String[])Arrays.copyOf(as1, as1.length);
            else
                enabledCipherSuites = includedStrings(as, getIncludedCipherSuites(), getExcludedCipherSuites());
            as2 = enabledCipherSuites;
            i = as2.length;
            for(int j = 0; j < i; j++)
            {
                String s = as2[j];
                addInfo((new StringBuilder()).append("enabled cipher suite: ").append(s).toString());
            }

        }
        return enabledCipherSuites;
    }

    private String[] enabledProtocols(String as[], String as1[])
    {
        if(enabledProtocols == null)
        {
            String as2[];
            int i;
            if(OptionHelper.isEmpty(getIncludedProtocols()) && OptionHelper.isEmpty(getExcludedProtocols()))
                enabledProtocols = (String[])Arrays.copyOf(as1, as1.length);
            else
                enabledProtocols = includedStrings(as, getIncludedProtocols(), getExcludedProtocols());
            as2 = enabledProtocols;
            i = as2.length;
            for(int j = 0; j < i; j++)
            {
                String s = as2[j];
                addInfo((new StringBuilder()).append("enabled protocol: ").append(s).toString());
            }

        }
        return enabledProtocols;
    }

    private String[] includedStrings(String as[], String s, String s1)
    {
        ArrayList arraylist = new ArrayList(as.length);
        arraylist.addAll(Arrays.asList(as));
        if(s != null)
            StringCollectionUtil.retainMatching(arraylist, stringToArray(s));
        if(s1 != null)
            StringCollectionUtil.removeMatching(arraylist, stringToArray(s1));
        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    private String[] stringToArray(String s)
    {
        return s.split("\\s*,\\s*");
    }

    public void configure(SSLConfigurable sslconfigurable)
    {
        sslconfigurable.setEnabledProtocols(enabledProtocols(sslconfigurable.getSupportedProtocols(), sslconfigurable.getDefaultProtocols()));
        sslconfigurable.setEnabledCipherSuites(enabledCipherSuites(sslconfigurable.getSupportedCipherSuites(), sslconfigurable.getDefaultCipherSuites()));
        if(isNeedClientAuth() != null)
            sslconfigurable.setNeedClientAuth(isNeedClientAuth().booleanValue());
        if(isWantClientAuth() != null)
            sslconfigurable.setWantClientAuth(isWantClientAuth().booleanValue());
    }

    public String getExcludedCipherSuites()
    {
        return excludedCipherSuites;
    }

    public String getExcludedProtocols()
    {
        return excludedProtocols;
    }

    public String getIncludedCipherSuites()
    {
        return includedCipherSuites;
    }

    public String getIncludedProtocols()
    {
        return includedProtocols;
    }

    public Boolean isNeedClientAuth()
    {
        return needClientAuth;
    }

    public Boolean isWantClientAuth()
    {
        return wantClientAuth;
    }

    public void setExcludedCipherSuites(String s)
    {
        excludedCipherSuites = s;
    }

    public void setExcludedProtocols(String s)
    {
        excludedProtocols = s;
    }

    public void setIncludedCipherSuites(String s)
    {
        includedCipherSuites = s;
    }

    public void setIncludedProtocols(String s)
    {
        includedProtocols = s;
    }

    public void setNeedClientAuth(Boolean boolean1)
    {
        needClientAuth = boolean1;
    }

    public void setWantClientAuth(Boolean boolean1)
    {
        wantClientAuth = boolean1;
    }

    private String enabledCipherSuites[];
    private String enabledProtocols[];
    private String excludedCipherSuites;
    private String excludedProtocols;
    private String includedCipherSuites;
    private String includedProtocols;
    private Boolean needClientAuth;
    private Boolean wantClientAuth;
}

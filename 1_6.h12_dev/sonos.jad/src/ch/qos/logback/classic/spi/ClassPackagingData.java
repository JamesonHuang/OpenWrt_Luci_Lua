// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic.spi;

import java.io.Serializable;

public class ClassPackagingData
    implements Serializable
{

    public ClassPackagingData(String s, String s1)
    {
        codeLocation = s;
        version = s1;
        exact = true;
    }

    public ClassPackagingData(String s, String s1, boolean flag)
    {
        codeLocation = s;
        version = s1;
        exact = flag;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this != obj) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        if(obj == null)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(getClass() != obj.getClass())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        ClassPackagingData classpackagingdata = (ClassPackagingData)obj;
        if(codeLocation == null)
        {
            if(classpackagingdata.codeLocation != null)
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
        } else
        if(!codeLocation.equals(classpackagingdata.codeLocation))
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(exact != classpackagingdata.exact)
            flag = false;
        else
        if(version == null)
        {
            if(classpackagingdata.version != null)
                flag = false;
        } else
        if(!version.equals(classpackagingdata.version))
            flag = false;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public String getCodeLocation()
    {
        return codeLocation;
    }

    public String getVersion()
    {
        return version;
    }

    public int hashCode()
    {
        int i;
        if(codeLocation == null)
            i = 0;
        else
            i = codeLocation.hashCode();
        return i + 31;
    }

    public boolean isExact()
    {
        return exact;
    }

    private static final long serialVersionUID = 0xf4d55532ec138717L;
    final String codeLocation;
    private final boolean exact;
    final String version;
}

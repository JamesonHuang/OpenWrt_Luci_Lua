// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.sift;


// Referenced classes of package ch.qos.logback.core.sift:
//            AbstractDiscriminator

public class DefaultDiscriminator extends AbstractDiscriminator
{

    public DefaultDiscriminator()
    {
    }

    public String getDiscriminatingValue(Object obj)
    {
        return "default";
    }

    public String getKey()
    {
        return "default";
    }

    public static final String DEFAULT = "default";
}

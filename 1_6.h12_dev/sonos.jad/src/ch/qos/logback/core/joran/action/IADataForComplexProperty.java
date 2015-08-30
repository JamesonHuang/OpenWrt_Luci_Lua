// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.util.PropertySetter;
import ch.qos.logback.core.util.AggregationType;

public class IADataForComplexProperty
{

    public IADataForComplexProperty(PropertySetter propertysetter, AggregationType aggregationtype, String s)
    {
        parentBean = propertysetter;
        aggregationType = aggregationtype;
        complexPropertyName = s;
    }

    public AggregationType getAggregationType()
    {
        return aggregationType;
    }

    public String getComplexPropertyName()
    {
        return complexPropertyName;
    }

    public Object getNestedComplexProperty()
    {
        return nestedComplexProperty;
    }

    public void setNestedComplexProperty(Object obj)
    {
        nestedComplexProperty = obj;
    }

    final AggregationType aggregationType;
    final String complexPropertyName;
    boolean inError;
    private Object nestedComplexProperty;
    final PropertySetter parentBean;
}

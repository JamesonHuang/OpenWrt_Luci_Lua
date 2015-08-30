// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.util.PropertySetter;
import ch.qos.logback.core.util.AggregationType;

class IADataForBasicProperty
{

    IADataForBasicProperty(PropertySetter propertysetter, AggregationType aggregationtype, String s)
    {
        parentBean = propertysetter;
        aggregationType = aggregationtype;
        propertyName = s;
    }

    final AggregationType aggregationType;
    boolean inError;
    final PropertySetter parentBean;
    final String propertyName;
}

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.util;

import ch.qos.logback.core.joran.spi.DefaultClass;
import ch.qos.logback.core.joran.spi.DefaultNestedComponentRegistry;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.util.AggregationType;
import ch.qos.logback.core.util.PropertySetterException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

// Referenced classes of package ch.qos.logback.core.joran.util:
//            StringToObjectConverter, Introspector, PropertyDescriptor, MethodDescriptor, 
//            IntrospectionException

public class PropertySetter extends ContextAwareBase
{

    public PropertySetter(Object obj1)
    {
        obj = obj1;
        objClass = obj1.getClass();
    }

    private String capitalizeFirstLetter(String s)
    {
        return (new StringBuilder()).append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).toString();
    }

    private AggregationType computeRawAggregationType(Method method)
    {
        Class class1 = getParameterClassForMethod(method);
        AggregationType aggregationtype;
        if(class1 == null)
            aggregationtype = AggregationType.NOT_FOUND;
        else
        if(StringToObjectConverter.canBeBuiltFromSimpleString(class1))
            aggregationtype = AggregationType.AS_BASIC_PROPERTY;
        else
            aggregationtype = AggregationType.AS_COMPLEX_PROPERTY;
        return aggregationtype;
    }

    private Method findAdderMethod(String s)
    {
        String s1 = capitalizeFirstLetter(s);
        return getMethod((new StringBuilder()).append("add").append(s1).toString());
    }

    private Method findSetterMethod(String s)
    {
        PropertyDescriptor propertydescriptor = getPropertyDescriptor(Introspector.decapitalize(s));
        Method method;
        if(propertydescriptor != null)
            method = propertydescriptor.getWriteMethod();
        else
            method = null;
        return method;
    }

    private Class getParameterClassForMethod(Method method)
    {
        Class class1 = null;
        if(method != null) goto _L2; else goto _L1
_L1:
        return class1;
_L2:
        Class aclass[] = method.getParameterTypes();
        if(aclass.length == 1)
            class1 = aclass[0];
        if(true) goto _L1; else goto _L3
_L3:
    }

    private boolean isSanityCheckSuccessful(String s, Method method, Class aclass[], Object obj1)
    {
        boolean flag = false;
        Class class1 = obj1.getClass();
        if(aclass.length != 1)
            addError((new StringBuilder()).append("Wrong number of parameters in setter method for property [").append(s).append("] in ").append(obj.getClass().getName()).toString());
        else
        if(!aclass[0].isAssignableFrom(obj1.getClass()))
        {
            addError((new StringBuilder()).append("A \"").append(class1.getName()).append("\" object is not assignable to a \"").append(aclass[0].getName()).append("\" variable.").toString());
            addError((new StringBuilder()).append("The class \"").append(aclass[0].getName()).append("\" was loaded by ").toString());
            addError((new StringBuilder()).append("[").append(aclass[0].getClassLoader()).append("] whereas object of type ").toString());
            addError((new StringBuilder()).append("\"").append(class1.getName()).append("\" was loaded by [").append(class1.getClassLoader()).append("].").toString());
        } else
        {
            flag = true;
        }
        return flag;
    }

    private boolean isUnequivocallyInstantiable(Class class1)
    {
        boolean flag = false;
        if(!class1.isInterface()) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        Object obj1 = class1.newInstance();
        if(obj1 != null)
            flag = true;
        continue; /* Loop/switch isn't completed */
        InstantiationException instantiationexception;
        instantiationexception;
        continue; /* Loop/switch isn't completed */
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void addBasicProperty(String s, String s1)
    {
        if(s1 != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Method method;
        Class aclass[];
        String s2 = capitalizeFirstLetter(s);
        method = findAdderMethod(s2);
        if(method == null)
        {
            addError((new StringBuilder()).append("No adder for property [").append(s2).append("].").toString());
            continue; /* Loop/switch isn't completed */
        }
        aclass = method.getParameterTypes();
        isSanityCheckSuccessful(s2, method, aclass, s1);
        Object obj1 = StringToObjectConverter.convertArg(this, s1, aclass[0]);
        if(obj1 != null)
            invokeMethodWithSingleParameterOnThisObject(method, s1);
        continue; /* Loop/switch isn't completed */
        Throwable throwable;
        throwable;
        addError((new StringBuilder()).append("Conversion to type [").append(aclass[0]).append("] failed. ").toString(), throwable);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void addComplexProperty(String s, Object obj1)
    {
        Method method = findAdderMethod(s);
        if(method != null)
        {
            if(isSanityCheckSuccessful(s, method, method.getParameterTypes(), obj1))
                invokeMethodWithSingleParameterOnThisObject(method, obj1);
        } else
        {
            addError((new StringBuilder()).append("Could not find method [add").append(s).append("] in class [").append(objClass.getName()).append("].").toString());
        }
    }

    public AggregationType computeAggregationType(String s)
    {
        Method method = findAdderMethod(s);
        if(method == null) goto _L2; else goto _L1
_L1:
        AggregationType aggregationtype1 = computeRawAggregationType(method);
        class _cls1
        {

            static final int $SwitchMap$ch$qos$logback$core$util$AggregationType[];

            static 
            {
                $SwitchMap$ch$qos$logback$core$util$AggregationType = new int[AggregationType.values().length];
                NoSuchFieldError nosuchfielderror2;
                try
                {
                    $SwitchMap$ch$qos$logback$core$util$AggregationType[AggregationType.NOT_FOUND.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$util$AggregationType[AggregationType.AS_BASIC_PROPERTY.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$ch$qos$logback$core$util$AggregationType[AggregationType.AS_COMPLEX_PROPERTY.ordinal()] = 3;
_L2:
                return;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.ch.qos.logback.core.util.AggregationType[aggregationtype1.ordinal()];
        JVM INSTR tableswitch 1 3: default 52
    //                   1 72
    //                   2 80
    //                   3 88;
           goto _L2 _L3 _L4 _L5
_L2:
        Method method1 = findSetterMethod(s);
        AggregationType aggregationtype;
        if(method1 != null)
            aggregationtype = computeRawAggregationType(method1);
        else
            aggregationtype = AggregationType.NOT_FOUND;
_L7:
        return aggregationtype;
_L3:
        aggregationtype = AggregationType.NOT_FOUND;
        continue; /* Loop/switch isn't completed */
_L4:
        aggregationtype = AggregationType.AS_BASIC_PROPERTY_COLLECTION;
        continue; /* Loop/switch isn't completed */
_L5:
        aggregationtype = AggregationType.AS_COMPLEX_PROPERTY_COLLECTION;
        if(true) goto _L7; else goto _L6
_L6:
    }

    Annotation getAnnotation(String s, Class class1, Method method)
    {
        Annotation annotation;
        if(method != null)
            annotation = method.getAnnotation(class1);
        else
            annotation = null;
        return annotation;
    }

    Class getByConcreteType(String s, Method method)
    {
        Class class1;
        Class class2;
        class1 = null;
        class2 = getParameterClassForMethod(method);
        break MISSING_BLOCK_LABEL_9;
        if(class2 != null && isUnequivocallyInstantiable(class2))
            class1 = class2;
        return class1;
    }

    public Class getClassNameViaImplicitRules(String s, AggregationType aggregationtype, DefaultNestedComponentRegistry defaultnestedcomponentregistry)
    {
        Class class1 = defaultnestedcomponentregistry.findDefaultComponentType(obj.getClass(), s);
        if(class1 == null) goto _L2; else goto _L1
_L1:
        return class1;
_L2:
        Method method = getRelevantMethod(s, aggregationtype);
        if(method == null)
        {
            class1 = null;
        } else
        {
            class1 = getDefaultClassNameByAnnonation(s, method);
            if(class1 == null)
                class1 = getByConcreteType(s, method);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    Class getDefaultClassNameByAnnonation(String s, Method method)
    {
        DefaultClass defaultclass = (DefaultClass)getAnnotation(s, ch/qos/logback/core/joran/spi/DefaultClass, method);
        Class class1;
        if(defaultclass != null)
            class1 = defaultclass.value();
        else
            class1 = null;
        return class1;
    }

    protected Method getMethod(String s)
    {
        int i;
        if(methodDescriptors == null)
            introspect();
        i = 0;
_L3:
        if(i >= methodDescriptors.length)
            break MISSING_BLOCK_LABEL_56;
        if(!s.equals(methodDescriptors[i].getName())) goto _L2; else goto _L1
_L1:
        Method method = methodDescriptors[i].getMethod();
_L4:
        return method;
_L2:
        i++;
          goto _L3
        method = null;
          goto _L4
    }

    public Object getObj()
    {
        return obj;
    }

    public Class getObjClass()
    {
        return objClass;
    }

    protected PropertyDescriptor getPropertyDescriptor(String s)
    {
        int i;
        if(propertyDescriptors == null)
            introspect();
        i = 0;
_L3:
        if(i >= propertyDescriptors.length)
            break MISSING_BLOCK_LABEL_53;
        if(!s.equals(propertyDescriptors[i].getName())) goto _L2; else goto _L1
_L1:
        PropertyDescriptor propertydescriptor = propertyDescriptors[i];
_L4:
        return propertydescriptor;
_L2:
        i++;
          goto _L3
        propertydescriptor = null;
          goto _L4
    }

    Method getRelevantMethod(String s, AggregationType aggregationtype)
    {
        String s1 = capitalizeFirstLetter(s);
        Method method;
        if(aggregationtype == AggregationType.AS_COMPLEX_PROPERTY_COLLECTION)
            method = findAdderMethod(s1);
        else
        if(aggregationtype == AggregationType.AS_COMPLEX_PROPERTY)
            method = findSetterMethod(s1);
        else
            throw new IllegalStateException((new StringBuilder()).append(aggregationtype).append(" not allowed here").toString());
        return method;
    }

    protected void introspect()
    {
        propertyDescriptors = Introspector.getPropertyDescriptors(objClass);
        methodDescriptors = Introspector.getMethodDescriptors(objClass);
_L1:
        return;
        IntrospectionException introspectionexception;
        introspectionexception;
        addError((new StringBuilder()).append("Failed to introspect ").append(obj).append(": ").append(introspectionexception.getMessage()).toString());
        propertyDescriptors = new PropertyDescriptor[0];
        methodDescriptors = new MethodDescriptor[0];
          goto _L1
    }

    void invokeMethodWithSingleParameterOnThisObject(Method method, Object obj1)
    {
        Class class1 = obj1.getClass();
        Object obj2 = obj;
        Object aobj[] = new Object[1];
        aobj[0] = obj1;
        method.invoke(obj2, aobj);
_L1:
        return;
        Exception exception;
        exception;
        addError((new StringBuilder()).append("Could not invoke method ").append(method.getName()).append(" in class ").append(obj.getClass().getName()).append(" with parameter of type ").append(class1.getName()).toString(), exception);
          goto _L1
    }

    public void setComplexProperty(String s, Object obj1)
    {
        PropertyDescriptor propertydescriptor = getPropertyDescriptor(Introspector.decapitalize(s));
        if(propertydescriptor != null) goto _L2; else goto _L1
_L1:
        addWarn((new StringBuilder()).append("Could not find PropertyDescriptor for [").append(s).append("] in ").append(objClass.getName()).toString());
_L4:
        return;
_L2:
        Method method = propertydescriptor.getWriteMethod();
        if(method == null)
            addWarn((new StringBuilder()).append("Not setter method for property [").append(s).append("] in ").append(obj.getClass().getName()).toString());
        else
        if(isSanityCheckSuccessful(s, method, method.getParameterTypes(), obj1))
            try
            {
                invokeMethodWithSingleParameterOnThisObject(method, obj1);
            }
            catch(Exception exception)
            {
                addError((new StringBuilder()).append("Could not set component ").append(obj).append(" for parent component ").append(obj).toString(), exception);
            }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setProperty(PropertyDescriptor propertydescriptor, String s, String s1)
        throws PropertySetterException
    {
        Method method = propertydescriptor.getWriteMethod();
        if(method == null)
            throw new PropertySetterException((new StringBuilder()).append("No setter for property [").append(s).append("].").toString());
        Class aclass[] = method.getParameterTypes();
        if(aclass.length != 1)
            throw new PropertySetterException("#params for setter != 1");
        Object obj1;
        try
        {
            obj1 = StringToObjectConverter.convertArg(this, s1, aclass[0]);
        }
        catch(Throwable throwable)
        {
            throw new PropertySetterException((new StringBuilder()).append("Conversion to type [").append(aclass[0]).append("] failed. ").toString(), throwable);
        }
        if(obj1 == null)
            throw new PropertySetterException((new StringBuilder()).append("Conversion to type [").append(aclass[0]).append("] failed.").toString());
        try
        {
            Object obj2 = obj;
            Object aobj[] = new Object[1];
            aobj[0] = obj1;
            method.invoke(obj2, aobj);
            return;
        }
        catch(Exception exception)
        {
            throw new PropertySetterException(exception);
        }
    }

    public void setProperty(String s, String s1)
    {
        if(s1 != null)
        {
            String s2 = Introspector.decapitalize(s);
            PropertyDescriptor propertydescriptor = getPropertyDescriptor(s2);
            if(propertydescriptor == null)
                addWarn((new StringBuilder()).append("No such property [").append(s2).append("] in ").append(objClass.getName()).append(".").toString());
            else
                try
                {
                    setProperty(propertydescriptor, s2, s1);
                }
                catch(PropertySetterException propertysetterexception)
                {
                    addWarn((new StringBuilder()).append("Failed to set property [").append(s2).append("] to value \"").append(s1).append("\". ").toString(), propertysetterexception);
                }
        }
    }

    protected MethodDescriptor methodDescriptors[];
    protected Object obj;
    protected Class objClass;
    protected PropertyDescriptor propertyDescriptors[];
}

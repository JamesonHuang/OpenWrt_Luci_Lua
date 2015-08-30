// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.splunk.mint.network.util;

import java.lang.reflect.*;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.splunk.mint.network.util:
//            DelegationException, ReflectionUtil

public class Delegator
{
    public class DelegatorMethodFinder
    {

        public transient Object invoke(Object aobj[])
        {
            return invoke0(method, aobj);
        }

        private final Method method;
        final Delegator this$0;

        public transient DelegatorMethodFinder(String s, Class aclass[])
        {
            this$0 = Delegator.this;
            super();
            try
            {
                method = superclass.getDeclaredMethod(s, aclass);
                return;
            }
            catch(RuntimeException runtimeexception)
            {
                throw runtimeexception;
            }
            catch(Exception exception)
            {
                throw new DelegationException(exception);
            }
        }
    }


    public Delegator(Object obj, Class class1, Object obj1)
    {
        source = obj;
        superclass = class1;
        _flddelegate = obj1;
    }

    public Delegator(Object obj, Class class1, String s)
    {
        try
        {
            source = obj;
            superclass = class1;
            Constructor constructor = Class.forName(s).getDeclaredConstructor(new Class[0]);
            constructor.setAccessible(true);
            _flddelegate = constructor.newInstance(new Object[0]);
            return;
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(Exception exception)
        {
            throw new DelegationException("Could not make delegate object", exception);
        }
    }

    private Class convertPrimitiveClass(Class class1)
    {
        if(!class1.isPrimitive()) goto _L2; else goto _L1
_L1:
        if(class1 != Integer.TYPE) goto _L4; else goto _L3
_L3:
        class1 = java/lang/Integer;
_L2:
        return class1;
_L4:
        if(class1 == Boolean.TYPE)
            class1 = java/lang/Boolean;
        else
        if(class1 == Float.TYPE)
            class1 = java/lang/Float;
        else
        if(class1 == Long.TYPE)
            class1 = java/lang/Long;
        else
        if(class1 == Double.TYPE)
            class1 = java/lang/Double;
        else
        if(class1 == Short.TYPE)
            class1 = java/lang/Short;
        else
        if(class1 == Byte.TYPE)
            class1 = java/lang/Byte;
        else
        if(class1 == Character.TYPE)
            class1 = java/lang/Character;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private String extractMethodName()
    {
        return (new Throwable()).getStackTrace()[2].getMethodName();
    }

    private Method findMethod(String s, Object aobj[])
        throws NoSuchMethodException
    {
        Class class1 = superclass;
        Method method;
        if(aobj.length == 0)
        {
            method = class1.getDeclaredMethod(s, new Class[0]);
        } else
        {
            method = null;
            Iterator iterator = ReflectionUtil.getAllMethods(class1).iterator();
label0:
            do
            {
                if(!iterator.hasNext())
                    break;
                Method method1 = (Method)iterator.next();
                if(!method1.getName().equals(s))
                    continue;
                Class aclass[] = method1.getParameterTypes();
                if(aclass.length != aobj.length)
                    continue;
                for(int i = 0; i < aclass.length; i++)
                    if(!convertPrimitiveClass(aclass[i]).isInstance(aobj[i]))
                        continue label0;

                if(method == null)
                    method = method1;
                else
                    throw new DelegationException("Duplicate matches");
            } while(true);
            if(method == null)
                throw new DelegationException((new StringBuilder()).append("Could not find method: ").append(s).toString());
        }
        return method;
    }

    private Object invoke0(Method method, Object aobj[])
    {
        Object obj;
        try
        {
            writeFields(superclass, source, _flddelegate);
            method.setAccessible(true);
            obj = method.invoke(_flddelegate, aobj);
            writeFields(superclass, _flddelegate, source);
        }
        catch(RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new DelegationException(invocationtargetexception.getCause());
        }
        catch(Exception exception)
        {
            throw new DelegationException(exception);
        }
        return obj;
    }

    private void writeFields(Class class1, Object obj, Object obj1)
        throws Exception
    {
        Field afield[] = class1.getDeclaredFields();
        int i = afield.length;
        for(int j = 0; j < i; j++)
        {
            Field field = afield[j];
            field.setAccessible(true);
            field.set(obj1, field.get(obj));
        }

    }

    public transient DelegatorMethodFinder delegateTo(String s, Class aclass[])
    {
        return new DelegatorMethodFinder(s, aclass);
    }

    public final transient Object invoke(Object aobj[])
    {
        Object obj;
        try
        {
            obj = invoke0(findMethod(extractMethodName(), aobj), aobj);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            throw new DelegationException(nosuchmethodexception);
        }
        return obj;
    }

    private final Object _flddelegate;
    private final Object source;
    private final Class superclass;


}

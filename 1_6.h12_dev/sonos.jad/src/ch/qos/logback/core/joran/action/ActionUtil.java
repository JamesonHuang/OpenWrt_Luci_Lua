// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.ContextUtil;
import ch.qos.logback.core.util.OptionHelper;
import java.util.Properties;

public class ActionUtil
{
    public static final class Scope extends Enum
    {

        public static Scope valueOf(String s)
        {
            return (Scope)Enum.valueOf(ch/qos/logback/core/joran/action/ActionUtil$Scope, s);
        }

        public static Scope[] values()
        {
            return (Scope[])$VALUES.clone();
        }

        private static final Scope $VALUES[];
        public static final Scope CONTEXT;
        public static final Scope LOCAL;
        public static final Scope SYSTEM;

        static 
        {
            LOCAL = new Scope("LOCAL", 0);
            CONTEXT = new Scope("CONTEXT", 1);
            SYSTEM = new Scope("SYSTEM", 2);
            Scope ascope[] = new Scope[3];
            ascope[0] = LOCAL;
            ascope[1] = CONTEXT;
            ascope[2] = SYSTEM;
            $VALUES = ascope;
        }

        private Scope(String s, int i)
        {
            super(s, i);
        }
    }


    public ActionUtil()
    {
    }

    public static void setProperties(InterpretationContext interpretationcontext, Properties properties, Scope scope)
    {
        class _cls1
        {

            static final int $SwitchMap$ch$qos$logback$core$joran$action$ActionUtil$Scope[];

            static 
            {
                $SwitchMap$ch$qos$logback$core$joran$action$ActionUtil$Scope = new int[Scope.values().length];
                NoSuchFieldError nosuchfielderror2;
                try
                {
                    $SwitchMap$ch$qos$logback$core$joran$action$ActionUtil$Scope[Scope.LOCAL.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$ch$qos$logback$core$joran$action$ActionUtil$Scope[Scope.CONTEXT.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                $SwitchMap$ch$qos$logback$core$joran$action$ActionUtil$Scope[Scope.SYSTEM.ordinal()] = 3;
_L2:
                return;
                nosuchfielderror2;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.ch.qos.logback.core.joran.action.ActionUtil.Scope[scope.ordinal()];
        JVM INSTR tableswitch 1 3: default 36
    //                   1 37
    //                   2 45
    //                   3 63;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        interpretationcontext.addSubstitutionProperties(properties);
        continue; /* Loop/switch isn't completed */
_L3:
        (new ContextUtil(interpretationcontext.getContext())).addProperties(properties);
        continue; /* Loop/switch isn't completed */
_L4:
        OptionHelper.setSystemProperties(interpretationcontext, properties);
        if(true) goto _L1; else goto _L5
_L5:
    }

    public static void setProperty(InterpretationContext interpretationcontext, String s, String s1, Scope scope)
    {
        _cls1..SwitchMap.ch.qos.logback.core.joran.action.ActionUtil.Scope[scope.ordinal()];
        JVM INSTR tableswitch 1 3: default 36
    //                   1 37
    //                   2 46
    //                   3 60;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        interpretationcontext.addSubstitutionProperty(s, s1);
        continue; /* Loop/switch isn't completed */
_L3:
        interpretationcontext.getContext().putProperty(s, s1);
        continue; /* Loop/switch isn't completed */
_L4:
        OptionHelper.setSystemProperty(interpretationcontext, s, s1);
        if(true) goto _L1; else goto _L5
_L5:
    }

    public static Scope stringToScope(String s)
    {
        Scope scope;
        if(Scope.SYSTEM.toString().equalsIgnoreCase(s))
            scope = Scope.SYSTEM;
        else
        if(Scope.CONTEXT.toString().equalsIgnoreCase(s))
            scope = Scope.CONTEXT;
        else
            scope = Scope.LOCAL;
        return scope;
    }
}

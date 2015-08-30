// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIInput, sclibJNI

public class SCIStringInputBase extends SCIInput
{
    public static final class InputMethodType extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$002(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$008()
            {
                int i = next;
                next = i + 1;
                return i;
            }

*/

            private SwigNext()
            {
            }
        }


        public static InputMethodType swigToEnum(int i)
        {
            InputMethodType ainputmethodtype[] = (InputMethodType[])com/sonos/sclib/SCIStringInputBase$InputMethodType.getEnumConstants();
            if(i >= ainputmethodtype.length || i < 0 || ainputmethodtype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            InputMethodType inputmethodtype1 = ainputmethodtype[i];
_L4:
            return inputmethodtype1;
_L2:
            int j = ainputmethodtype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                InputMethodType inputmethodtype = ainputmethodtype[k];
                if(inputmethodtype.swigValue == i)
                {
                    inputmethodtype1 = inputmethodtype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIStringInputBase$InputMethodType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static InputMethodType valueOf(String s)
        {
            return (InputMethodType)Enum.valueOf(com/sonos/sclib/SCIStringInputBase$InputMethodType, s);
        }

        public static InputMethodType[] values()
        {
            return (InputMethodType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final InputMethodType $VALUES[];
        public static final InputMethodType INPUTMETHOD_TYPE_ALPHA;
        public static final InputMethodType INPUTMETHOD_TYPE_ALPHANUMERIC;
        public static final InputMethodType INPUTMETHOD_TYPE_EMAIL;
        public static final InputMethodType INPUTMETHOD_TYPE_NONE;
        public static final InputMethodType INPUTMETHOD_TYPE_NUMERIC;
        public static final InputMethodType INPUTMETHOD_TYPE_PASSWORD;
        public static final InputMethodType INPUTMETHOD_TYPE_SHARES;
        public static final InputMethodType INPUTMETHOD_TYPE_URL;
        private final int swigValue;

        static 
        {
            INPUTMETHOD_TYPE_NONE = new InputMethodType("INPUTMETHOD_TYPE_NONE", 0);
            INPUTMETHOD_TYPE_ALPHA = new InputMethodType("INPUTMETHOD_TYPE_ALPHA", 1);
            INPUTMETHOD_TYPE_NUMERIC = new InputMethodType("INPUTMETHOD_TYPE_NUMERIC", 2);
            INPUTMETHOD_TYPE_ALPHANUMERIC = new InputMethodType("INPUTMETHOD_TYPE_ALPHANUMERIC", 3);
            INPUTMETHOD_TYPE_EMAIL = new InputMethodType("INPUTMETHOD_TYPE_EMAIL", 4);
            INPUTMETHOD_TYPE_SHARES = new InputMethodType("INPUTMETHOD_TYPE_SHARES", 5);
            INPUTMETHOD_TYPE_PASSWORD = new InputMethodType("INPUTMETHOD_TYPE_PASSWORD", 6);
            INPUTMETHOD_TYPE_URL = new InputMethodType("INPUTMETHOD_TYPE_URL", 7);
            InputMethodType ainputmethodtype[] = new InputMethodType[8];
            ainputmethodtype[0] = INPUTMETHOD_TYPE_NONE;
            ainputmethodtype[1] = INPUTMETHOD_TYPE_ALPHA;
            ainputmethodtype[2] = INPUTMETHOD_TYPE_NUMERIC;
            ainputmethodtype[3] = INPUTMETHOD_TYPE_ALPHANUMERIC;
            ainputmethodtype[4] = INPUTMETHOD_TYPE_EMAIL;
            ainputmethodtype[5] = INPUTMETHOD_TYPE_SHARES;
            ainputmethodtype[6] = INPUTMETHOD_TYPE_PASSWORD;
            ainputmethodtype[7] = INPUTMETHOD_TYPE_URL;
            $VALUES = ainputmethodtype;
        }

        private InputMethodType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private InputMethodType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private InputMethodType(String s, int i, InputMethodType inputmethodtype)
        {
            Enum(s, i);
            swigValue = inputmethodtype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIStringInputBase(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIInput(sclibJNI.SWIGSCIStringInputBaseUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIStringInputBase(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIStringInputBase(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIStringInputBase scistringinputbase)
    {
        long l;
        if(scistringinputbase == null)
            l = 0L;
        else
            l = scistringinputbase.swigCPtr;
        return l;
    }

    /**
     * @deprecated Method dispose is deprecated
     */

    public void dispose()
    {
        this;
        JVM INSTR monitorenter ;
        swigCPtr = 0L;
        dispose();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int getMaxNumChars()
    {
        return sclibJNI.SCIStringInputBase_getMaxNumChars(swigCPtr, this);
    }

    public InputMethodType getRecommendedInputMethodType()
    {
        return InputMethodType.swigToEnum(sclibJNI.SCIStringInputBase_getRecommendedInputMethodType(swigCPtr, this));
    }

    public String getString()
    {
        return sclibJNI.SCIStringInputBase_getString(swigCPtr, this);
    }

    public boolean isLocked()
    {
        return sclibJNI.SCIStringInputBase_isLocked(swigCPtr, this);
    }

    public void setString(String s)
    {
        sclibJNI.SCIStringInputBase_setString(swigCPtr, this, s);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIStringInputBase");
    private long swigCPtr;

}

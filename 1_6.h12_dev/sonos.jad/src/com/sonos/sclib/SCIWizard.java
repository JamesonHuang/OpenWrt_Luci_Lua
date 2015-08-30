// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIObj, sclibJNI, SCIIntArray, SCIPropertyBag, 
//            SCIStringInput, SCIEnumerator, SCICustomSubWizard, SCIEventSink

public class SCIWizard extends SCIObj
{
    public static final class WizInputSelection extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$102(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$108()
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


        public static WizInputSelection swigToEnum(int i)
        {
            WizInputSelection awizinputselection[] = (WizInputSelection[])com/sonos/sclib/SCIWizard$WizInputSelection.getEnumConstants();
            if(i >= awizinputselection.length || i < 0 || awizinputselection[i].swigValue != i) goto _L2; else goto _L1
_L1:
            WizInputSelection wizinputselection1 = awizinputselection[i];
_L4:
            return wizinputselection1;
_L2:
            int j = awizinputselection.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                WizInputSelection wizinputselection = awizinputselection[k];
                if(wizinputselection.swigValue == i)
                {
                    wizinputselection1 = wizinputselection;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIWizard$WizInputSelection).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static WizInputSelection valueOf(String s)
        {
            return (WizInputSelection)Enum.valueOf(com/sonos/sclib/SCIWizard$WizInputSelection, s);
        }

        public static WizInputSelection[] values()
        {
            return (WizInputSelection[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final WizInputSelection $VALUES[];
        public static final WizInputSelection SETUP_WIZ_INPUT_MAX;
        public static final WizInputSelection WIZ_INPUT_1;
        public static final WizInputSelection WIZ_INPUT_2;
        public static final WizInputSelection WIZ_INPUT_3;
        public static final WizInputSelection WIZ_INPUT_4;
        public static final WizInputSelection WIZ_INPUT_5;
        public static final WizInputSelection WIZ_INPUT_6;
        public static final WizInputSelection WIZ_INPUT_7;
        public static final WizInputSelection WIZ_INPUT_8;
        public static final WizInputSelection WIZ_INPUT_NONE;
        private final int swigValue;

        static 
        {
            WIZ_INPUT_NONE = new WizInputSelection("WIZ_INPUT_NONE", 0, sclibJNI.SCIWizard_WIZ_INPUT_NONE_get());
            WIZ_INPUT_1 = new WizInputSelection("WIZ_INPUT_1", 1, sclibJNI.SCIWizard_WIZ_INPUT_1_get());
            WIZ_INPUT_2 = new WizInputSelection("WIZ_INPUT_2", 2);
            WIZ_INPUT_3 = new WizInputSelection("WIZ_INPUT_3", 3);
            WIZ_INPUT_4 = new WizInputSelection("WIZ_INPUT_4", 4);
            WIZ_INPUT_5 = new WizInputSelection("WIZ_INPUT_5", 5);
            WIZ_INPUT_6 = new WizInputSelection("WIZ_INPUT_6", 6);
            WIZ_INPUT_7 = new WizInputSelection("WIZ_INPUT_7", 7);
            WIZ_INPUT_8 = new WizInputSelection("WIZ_INPUT_8", 8);
            SETUP_WIZ_INPUT_MAX = new WizInputSelection("SETUP_WIZ_INPUT_MAX", 9);
            WizInputSelection awizinputselection[] = new WizInputSelection[10];
            awizinputselection[0] = WIZ_INPUT_NONE;
            awizinputselection[1] = WIZ_INPUT_1;
            awizinputselection[2] = WIZ_INPUT_2;
            awizinputselection[3] = WIZ_INPUT_3;
            awizinputselection[4] = WIZ_INPUT_4;
            awizinputselection[5] = WIZ_INPUT_5;
            awizinputselection[6] = WIZ_INPUT_6;
            awizinputselection[7] = WIZ_INPUT_7;
            awizinputselection[8] = WIZ_INPUT_8;
            awizinputselection[9] = SETUP_WIZ_INPUT_MAX;
            $VALUES = awizinputselection;
        }

        private WizInputSelection(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private WizInputSelection(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private WizInputSelection(String s, int i, WizInputSelection wizinputselection)
        {
            Enum(s, i);
            swigValue = wizinputselection.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class StateTransitionType extends Enum
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


        public static StateTransitionType swigToEnum(int i)
        {
            StateTransitionType astatetransitiontype[] = (StateTransitionType[])com/sonos/sclib/SCIWizard$StateTransitionType.getEnumConstants();
            if(i >= astatetransitiontype.length || i < 0 || astatetransitiontype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            StateTransitionType statetransitiontype1 = astatetransitiontype[i];
_L4:
            return statetransitiontype1;
_L2:
            int j = astatetransitiontype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                StateTransitionType statetransitiontype = astatetransitiontype[k];
                if(statetransitiontype.swigValue == i)
                {
                    statetransitiontype1 = statetransitiontype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIWizard$StateTransitionType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static StateTransitionType valueOf(String s)
        {
            return (StateTransitionType)Enum.valueOf(com/sonos/sclib/SCIWizard$StateTransitionType, s);
        }

        public static StateTransitionType[] values()
        {
            return (StateTransitionType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final StateTransitionType $VALUES[];
        public static final StateTransitionType STATE_TRANS_TYPE_BACKWARD;
        public static final StateTransitionType STATE_TRANS_TYPE_FORWARD;
        public static final StateTransitionType STATE_TRANS_TYPE_UNKNOWN;
        private final int swigValue;

        static 
        {
            STATE_TRANS_TYPE_UNKNOWN = new StateTransitionType("STATE_TRANS_TYPE_UNKNOWN", 0);
            STATE_TRANS_TYPE_FORWARD = new StateTransitionType("STATE_TRANS_TYPE_FORWARD", 1);
            STATE_TRANS_TYPE_BACKWARD = new StateTransitionType("STATE_TRANS_TYPE_BACKWARD", 2);
            StateTransitionType astatetransitiontype[] = new StateTransitionType[3];
            astatetransitiontype[0] = STATE_TRANS_TYPE_UNKNOWN;
            astatetransitiontype[1] = STATE_TRANS_TYPE_FORWARD;
            astatetransitiontype[2] = STATE_TRANS_TYPE_BACKWARD;
            $VALUES = astatetransitiontype;
        }

        private StateTransitionType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private StateTransitionType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private StateTransitionType(String s, int i, StateTransitionType statetransitiontype)
        {
            Enum(s, i);
            swigValue = statetransitiontype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIWizard(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIObj(sclibJNI.SWIGSCIWizardUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIWizard(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIWizard(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIWizard sciwizard)
    {
        long l;
        if(sciwizard == null)
            l = 0L;
        else
            l = sciwizard.swigCPtr;
        return l;
    }

    public void abort()
    {
        sclibJNI.SCIWizard_abort(swigCPtr, this);
    }

    public boolean areInputsValid()
    {
        return sclibJNI.SCIWizard_areInputsValid(swigCPtr, this);
    }

    public boolean canRun()
    {
        return sclibJNI.SCIWizard_canRun(swigCPtr, this);
    }

    public boolean cancel()
    {
        return sclibJNI.SCIWizard_cancel(swigCPtr, this);
    }

    public boolean canceled()
    {
        return sclibJNI.SCIWizard_canceled(swigCPtr, this);
    }

    public boolean completed()
    {
        return sclibJNI.SCIWizard_completed(swigCPtr, this);
    }

    public SCIIntArray dbgGetAllWizardStateIDs()
    {
        long l = sclibJNI.SCIWizard_dbgGetAllWizardStateIDs(swigCPtr, this);
        SCIIntArray sciintarray;
        if(l == 0L)
            sciintarray = null;
        else
            sciintarray = new SCIIntArray(l, true);
        return sciintarray;
    }

    public boolean dbgNextState()
    {
        return sclibJNI.SCIWizard_dbgNextState(swigCPtr, this);
    }

    public void dbgRunInUserFlowMode()
    {
        sclibJNI.SCIWizard_dbgRunInUserFlowMode(swigCPtr, this);
    }

    public boolean dbgTransitionToState(int i)
    {
        return sclibJNI.SCIWizard_dbgTransitionToState(swigCPtr, this, i);
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

    public boolean getBoolProp(String s)
    {
        return sclibJNI.SCIWizard_getBoolProp(swigCPtr, this, s);
    }

    public int getExitCode()
    {
        return sclibJNI.SCIWizard_getExitCode(swigCPtr, this);
    }

    public int getInputIndex(WizInputSelection wizinputselection)
    {
        return sclibJNI.SCIWizard_getInputIndex(swigCPtr, this, wizinputselection.swigValue());
    }

    public int getIntProp(String s)
    {
        return sclibJNI.SCIWizard_getIntProp(swigCPtr, this, s);
    }

    public SCIPropertyBag getNamedParameters()
    {
        long l = sclibJNI.SCIWizard_getNamedParameters(swigCPtr, this);
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        return scipropertybag;
    }

    public String getRecommendedLabelForNextState()
    {
        return sclibJNI.SCIWizard_getRecommendedLabelForNextState(swigCPtr, this);
    }

    public String getRecommendedLabelForPreviousState()
    {
        return sclibJNI.SCIWizard_getRecommendedLabelForPreviousState(swigCPtr, this);
    }

    public String getRecommendedPresentationText(int i)
    {
        return sclibJNI.SCIWizard_getRecommendedPresentationText(swigCPtr, this, i);
    }

    public String getRecommendedTitle()
    {
        return sclibJNI.SCIWizard_getRecommendedTitle(swigCPtr, this);
    }

    public SCIPropertyBag getReturnValues()
    {
        long l = sclibJNI.SCIWizard_getReturnValues(swigCPtr, this);
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        return scipropertybag;
    }

    public SCIPropertyBag getRunningSubWizardPropertyBag()
    {
        long l = sclibJNI.SCIWizard_getRunningSubWizardPropertyBag(swigCPtr, this);
        SCIPropertyBag scipropertybag;
        if(l == 0L)
            scipropertybag = null;
        else
            scipropertybag = new SCIPropertyBag(l, true);
        return scipropertybag;
    }

    public WizInputSelection getSelection()
    {
        return WizInputSelection.swigToEnum(sclibJNI.SCIWizard_getSelection(swigCPtr, this));
    }

    public int getState()
    {
        return sclibJNI.SCIWizard_getState(swigCPtr, this);
    }

    public StateTransitionType getStateTransitionDirection()
    {
        return StateTransitionType.swigToEnum(sclibJNI.SCIWizard_getStateTransitionDirection(swigCPtr, this));
    }

    public String getStrProp(String s)
    {
        return sclibJNI.SCIWizard_getStrProp(swigCPtr, this, s);
    }

    public SCIStringInput getStringInput(WizInputSelection wizinputselection)
    {
        long l = sclibJNI.SCIWizard_getStringInput(swigCPtr, this, wizinputselection.swigValue());
        SCIStringInput scistringinput;
        if(l == 0L)
            scistringinput = null;
        else
            scistringinput = new SCIStringInput(l, true);
        return scistringinput;
    }

    public SCIEnumerator getWizardComponentsForCurrentState()
    {
        long l = sclibJNI.SCIWizard_getWizardComponentsForCurrentState(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public boolean insertSubWizard(int i, SCICustomSubWizard scicustomsubwizard)
    {
        return sclibJNI.SCIWizard_insertSubWizard(swigCPtr, this, i, SCICustomSubWizard.getCPtr(scicustomsubwizard), scicustomsubwizard);
    }

    public boolean isBusy()
    {
        return sclibJNI.SCIWizard_isBusy(swigCPtr, this);
    }

    public boolean isCancelEnabled()
    {
        return sclibJNI.SCIWizard_isCancelEnabled(swigCPtr, this);
    }

    public boolean isNested()
    {
        return sclibJNI.SCIWizard_isNested(swigCPtr, this);
    }

    public boolean isNextStateEnabled()
    {
        return sclibJNI.SCIWizard_isNextStateEnabled(swigCPtr, this);
    }

    public boolean isPresentationRecommended()
    {
        return sclibJNI.SCIWizard_isPresentationRecommended(swigCPtr, this);
    }

    public boolean isPreviousStateEnabled()
    {
        return sclibJNI.SCIWizard_isPreviousStateEnabled(swigCPtr, this);
    }

    public boolean isStateDone()
    {
        return sclibJNI.SCIWizard_isStateDone(swigCPtr, this);
    }

    public boolean isStateOK()
    {
        return sclibJNI.SCIWizard_isStateOK(swigCPtr, this);
    }

    public boolean previousStateWillCancel()
    {
        return sclibJNI.SCIWizard_previousStateWillCancel(swigCPtr, this);
    }

    public boolean requiresInput()
    {
        return sclibJNI.SCIWizard_requiresInput(swigCPtr, this);
    }

    public void reset()
    {
        sclibJNI.SCIWizard_reset(swigCPtr, this);
    }

    public void run()
    {
        sclibJNI.SCIWizard_run(swigCPtr, this);
    }

    public boolean running()
    {
        return sclibJNI.SCIWizard_running(swigCPtr, this);
    }

    public void selectInput(WizInputSelection wizinputselection, int i)
    {
        sclibJNI.SCIWizard_selectInput(swigCPtr, this, wizinputselection.swigValue(), i);
    }

    public void setBoolProp(String s, boolean flag)
    {
        sclibJNI.SCIWizard_setBoolProp(swigCPtr, this, s, flag);
    }

    public void setCustomSubwizardReturnValue(int i)
    {
        sclibJNI.SCIWizard_setCustomSubwizardReturnValue(swigCPtr, this, i);
    }

    public void setIntProp(String s, int i)
    {
        sclibJNI.SCIWizard_setIntProp(swigCPtr, this, s, i);
    }

    public void setSelection(WizInputSelection wizinputselection)
    {
        sclibJNI.SCIWizard_setSelection(swigCPtr, this, wizinputselection.swigValue());
    }

    public void setStrProp(String s, String s1)
    {
        sclibJNI.SCIWizard_setStrProp(swigCPtr, this, s, s1);
    }

    public void subscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIWizard_subscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public boolean transitionToNextState()
    {
        return sclibJNI.SCIWizard_transitionToNextState(swigCPtr, this);
    }

    public boolean transitionToPreviousState()
    {
        return sclibJNI.SCIWizard_transitionToPreviousState(swigCPtr, this);
    }

    public void unsubscribe(SCIEventSink scieventsink)
    {
        sclibJNI.SCIWizard_unsubscribe(swigCPtr, this, SCIEventSink.getCPtr(scieventsink), scieventsink);
    }

    public static final int STATE_COMPLETE = sclibJNI.SCIWizard_STATE_COMPLETE_get();
    public static final int STATE_INIT = sclibJNI.SCIWizard_STATE_INIT_get();
    public static final int STATE_UNKNOWN = sclibJNI.SCIWizard_STATE_UNKNOWN_get();
    public static final int STRID_PRESENTATION_BODY = sclibJNI.SCIWizard_STRID_PRESENTATION_BODY_get();
    public static final int STRID_PRESENTATION_BODY_1 = sclibJNI.SCIWizard_STRID_PRESENTATION_BODY_1_get();
    public static final int STRID_PRESENTATION_BODY_2 = sclibJNI.SCIWizard_STRID_PRESENTATION_BODY_2_get();
    public static final int STRID_PRESENTATION_BODY_3 = sclibJNI.SCIWizard_STRID_PRESENTATION_BODY_3_get();
    public static final int STRID_PRESENTATION_BODY_4 = sclibJNI.SCIWizard_STRID_PRESENTATION_BODY_4_get();
    public static final int STRID_PRESENTATION_BODY_5 = sclibJNI.SCIWizard_STRID_PRESENTATION_BODY_5_get();
    public static final int STRID_PRESENTATION_ERROR = sclibJNI.SCIWizard_STRID_PRESENTATION_ERROR_get();
    public static final int STRID_PRESENTATION_TITLE = sclibJNI.SCIWizard_STRID_PRESENTATION_TITLE_get();
    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIWizard");
    private long swigCPtr;

}

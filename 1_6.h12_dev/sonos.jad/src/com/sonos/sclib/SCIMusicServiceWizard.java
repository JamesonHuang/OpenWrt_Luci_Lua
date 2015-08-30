// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            SCIWizard, sclibJNI, SCIEnumerator, SCIStringInput

public class SCIMusicServiceWizard extends SCIWizard
{
    public static final class MusicServiceWizardState extends Enum
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


        public static MusicServiceWizardState swigToEnum(int i)
        {
            MusicServiceWizardState amusicservicewizardstate[] = (MusicServiceWizardState[])com/sonos/sclib/SCIMusicServiceWizard$MusicServiceWizardState.getEnumConstants();
            if(i >= amusicservicewizardstate.length || i < 0 || amusicservicewizardstate[i].swigValue != i) goto _L2; else goto _L1
_L1:
            MusicServiceWizardState musicservicewizardstate1 = amusicservicewizardstate[i];
_L4:
            return musicservicewizardstate1;
_L2:
            int j = amusicservicewizardstate.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                MusicServiceWizardState musicservicewizardstate = amusicservicewizardstate[k];
                if(musicservicewizardstate.swigValue == i)
                {
                    musicservicewizardstate1 = musicservicewizardstate;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIMusicServiceWizard$MusicServiceWizardState).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static MusicServiceWizardState valueOf(String s)
        {
            return (MusicServiceWizardState)Enum.valueOf(com/sonos/sclib/SCIMusicServiceWizard$MusicServiceWizardState, s);
        }

        public static MusicServiceWizardState[] values()
        {
            return (MusicServiceWizardState[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final MusicServiceWizardState $VALUES[];
        public static final MusicServiceWizardState STATE_MUSICSERVICE_ACCOUNTNEEDED;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_COMPLETE;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_GET_AUDIBLE_DATA;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_GET_LINK_CODE;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_GET_SHARE_USAGE;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_INIT;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_INTRO;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_LINK_CODE;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_LIST;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_LOGINPASSWORD;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_MAX;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_MERGE;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_MULTIPLE_ACCOUNTS_ADDED;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_PASSWORD;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_PROMOTED_INTRO;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_RESULT;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_SET_NICKNAME;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_SET_SHARE_USAGE;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_SUBSCRIBEINTRO;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_TERMS;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_UNKNOWN;
        public static final MusicServiceWizardState STATE_MUSICSERVICE_WORKING;
        private final int swigValue;

        static 
        {
            STATE_MUSICSERVICE_UNKNOWN = new MusicServiceWizardState("STATE_MUSICSERVICE_UNKNOWN", 0, sclibJNI.SCIMusicServiceWizard_STATE_MUSICSERVICE_UNKNOWN_get());
            STATE_MUSICSERVICE_INIT = new MusicServiceWizardState("STATE_MUSICSERVICE_INIT", 1, sclibJNI.SCIMusicServiceWizard_STATE_MUSICSERVICE_INIT_get());
            STATE_MUSICSERVICE_COMPLETE = new MusicServiceWizardState("STATE_MUSICSERVICE_COMPLETE", 2, sclibJNI.SCIMusicServiceWizard_STATE_MUSICSERVICE_COMPLETE_get());
            STATE_MUSICSERVICE_GET_AUDIBLE_DATA = new MusicServiceWizardState("STATE_MUSICSERVICE_GET_AUDIBLE_DATA", 3);
            STATE_MUSICSERVICE_LIST = new MusicServiceWizardState("STATE_MUSICSERVICE_LIST", 4);
            STATE_MUSICSERVICE_GET_SHARE_USAGE = new MusicServiceWizardState("STATE_MUSICSERVICE_GET_SHARE_USAGE", 5);
            STATE_MUSICSERVICE_SET_SHARE_USAGE = new MusicServiceWizardState("STATE_MUSICSERVICE_SET_SHARE_USAGE", 6);
            STATE_MUSICSERVICE_PROMOTED_INTRO = new MusicServiceWizardState("STATE_MUSICSERVICE_PROMOTED_INTRO", 7);
            STATE_MUSICSERVICE_INTRO = new MusicServiceWizardState("STATE_MUSICSERVICE_INTRO", 8);
            STATE_MUSICSERVICE_TERMS = new MusicServiceWizardState("STATE_MUSICSERVICE_TERMS", 9);
            STATE_MUSICSERVICE_ACCOUNTNEEDED = new MusicServiceWizardState("STATE_MUSICSERVICE_ACCOUNTNEEDED", 10);
            STATE_MUSICSERVICE_LOGINPASSWORD = new MusicServiceWizardState("STATE_MUSICSERVICE_LOGINPASSWORD", 11);
            STATE_MUSICSERVICE_PASSWORD = new MusicServiceWizardState("STATE_MUSICSERVICE_PASSWORD", 12);
            STATE_MUSICSERVICE_WORKING = new MusicServiceWizardState("STATE_MUSICSERVICE_WORKING", 13);
            STATE_MUSICSERVICE_RESULT = new MusicServiceWizardState("STATE_MUSICSERVICE_RESULT", 14);
            STATE_MUSICSERVICE_SET_NICKNAME = new MusicServiceWizardState("STATE_MUSICSERVICE_SET_NICKNAME", 15);
            STATE_MUSICSERVICE_MULTIPLE_ACCOUNTS_ADDED = new MusicServiceWizardState("STATE_MUSICSERVICE_MULTIPLE_ACCOUNTS_ADDED", 16);
            STATE_MUSICSERVICE_SUBSCRIBEINTRO = new MusicServiceWizardState("STATE_MUSICSERVICE_SUBSCRIBEINTRO", 17);
            STATE_MUSICSERVICE_MERGE = new MusicServiceWizardState("STATE_MUSICSERVICE_MERGE", 18);
            STATE_MUSICSERVICE_GET_LINK_CODE = new MusicServiceWizardState("STATE_MUSICSERVICE_GET_LINK_CODE", 19);
            STATE_MUSICSERVICE_LINK_CODE = new MusicServiceWizardState("STATE_MUSICSERVICE_LINK_CODE", 20);
            STATE_MUSICSERVICE_MAX = new MusicServiceWizardState("STATE_MUSICSERVICE_MAX", 21);
            MusicServiceWizardState amusicservicewizardstate[] = new MusicServiceWizardState[22];
            amusicservicewizardstate[0] = STATE_MUSICSERVICE_UNKNOWN;
            amusicservicewizardstate[1] = STATE_MUSICSERVICE_INIT;
            amusicservicewizardstate[2] = STATE_MUSICSERVICE_COMPLETE;
            amusicservicewizardstate[3] = STATE_MUSICSERVICE_GET_AUDIBLE_DATA;
            amusicservicewizardstate[4] = STATE_MUSICSERVICE_LIST;
            amusicservicewizardstate[5] = STATE_MUSICSERVICE_GET_SHARE_USAGE;
            amusicservicewizardstate[6] = STATE_MUSICSERVICE_SET_SHARE_USAGE;
            amusicservicewizardstate[7] = STATE_MUSICSERVICE_PROMOTED_INTRO;
            amusicservicewizardstate[8] = STATE_MUSICSERVICE_INTRO;
            amusicservicewizardstate[9] = STATE_MUSICSERVICE_TERMS;
            amusicservicewizardstate[10] = STATE_MUSICSERVICE_ACCOUNTNEEDED;
            amusicservicewizardstate[11] = STATE_MUSICSERVICE_LOGINPASSWORD;
            amusicservicewizardstate[12] = STATE_MUSICSERVICE_PASSWORD;
            amusicservicewizardstate[13] = STATE_MUSICSERVICE_WORKING;
            amusicservicewizardstate[14] = STATE_MUSICSERVICE_RESULT;
            amusicservicewizardstate[15] = STATE_MUSICSERVICE_SET_NICKNAME;
            amusicservicewizardstate[16] = STATE_MUSICSERVICE_MULTIPLE_ACCOUNTS_ADDED;
            amusicservicewizardstate[17] = STATE_MUSICSERVICE_SUBSCRIBEINTRO;
            amusicservicewizardstate[18] = STATE_MUSICSERVICE_MERGE;
            amusicservicewizardstate[19] = STATE_MUSICSERVICE_GET_LINK_CODE;
            amusicservicewizardstate[20] = STATE_MUSICSERVICE_LINK_CODE;
            amusicservicewizardstate[21] = STATE_MUSICSERVICE_MAX;
            $VALUES = amusicservicewizardstate;
        }

        private MusicServiceWizardState(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private MusicServiceWizardState(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private MusicServiceWizardState(String s, int i, MusicServiceWizardState musicservicewizardstate)
        {
            Enum(s, i);
            swigValue = musicservicewizardstate.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class MSWizStringID extends Enum
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


        public static MSWizStringID swigToEnum(int i)
        {
            MSWizStringID amswizstringid[] = (MSWizStringID[])com/sonos/sclib/SCIMusicServiceWizard$MSWizStringID.getEnumConstants();
            if(i >= amswizstringid.length || i < 0 || amswizstringid[i].swigValue != i) goto _L2; else goto _L1
_L1:
            MSWizStringID mswizstringid1 = amswizstringid[i];
_L4:
            return mswizstringid1;
_L2:
            int j = amswizstringid.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                MSWizStringID mswizstringid = amswizstringid[k];
                if(mswizstringid.swigValue == i)
                {
                    mswizstringid1 = mswizstringid;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCIMusicServiceWizard$MSWizStringID).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static MSWizStringID valueOf(String s)
        {
            return (MSWizStringID)Enum.valueOf(com/sonos/sclib/SCIMusicServiceWizard$MSWizStringID, s);
        }

        public static MSWizStringID[] values()
        {
            return (MSWizStringID[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final MSWizStringID $VALUES[];
        public static final MSWizStringID MSWIZ_STRID_BODY;
        public static final MSWizStringID MSWIZ_STRID_BODY_1;
        public static final MSWizStringID MSWIZ_STRID_BODY_2;
        public static final MSWizStringID MSWIZ_STRID_BODY_3;
        public static final MSWizStringID MSWIZ_STRID_BODY_4;
        public static final MSWizStringID MSWIZ_STRID_INPUT_1;
        public static final MSWizStringID MSWIZ_STRID_INPUT_2;
        public static final MSWizStringID MSWIZ_STRID_INSTRUCTIONS;
        public static final MSWizStringID MSWIZ_STRID_MAX;
        public static final MSWizStringID MSWIZ_STRID_TITLE_1;
        private final int swigValue;

        static 
        {
            MSWIZ_STRID_TITLE_1 = new MSWizStringID("MSWIZ_STRID_TITLE_1", 0, sclibJNI.SCIMusicServiceWizard_MSWIZ_STRID_TITLE_1_get());
            MSWIZ_STRID_BODY = new MSWizStringID("MSWIZ_STRID_BODY", 1, sclibJNI.SCIMusicServiceWizard_MSWIZ_STRID_BODY_get());
            MSWIZ_STRID_BODY_1 = new MSWizStringID("MSWIZ_STRID_BODY_1", 2);
            MSWIZ_STRID_BODY_2 = new MSWizStringID("MSWIZ_STRID_BODY_2", 3);
            MSWIZ_STRID_BODY_3 = new MSWizStringID("MSWIZ_STRID_BODY_3", 4);
            MSWIZ_STRID_BODY_4 = new MSWizStringID("MSWIZ_STRID_BODY_4", 5);
            MSWIZ_STRID_INPUT_1 = new MSWizStringID("MSWIZ_STRID_INPUT_1", 6);
            MSWIZ_STRID_INPUT_2 = new MSWizStringID("MSWIZ_STRID_INPUT_2", 7);
            MSWIZ_STRID_INSTRUCTIONS = new MSWizStringID("MSWIZ_STRID_INSTRUCTIONS", 8);
            MSWIZ_STRID_MAX = new MSWizStringID("MSWIZ_STRID_MAX", 9);
            MSWizStringID amswizstringid[] = new MSWizStringID[10];
            amswizstringid[0] = MSWIZ_STRID_TITLE_1;
            amswizstringid[1] = MSWIZ_STRID_BODY;
            amswizstringid[2] = MSWIZ_STRID_BODY_1;
            amswizstringid[3] = MSWIZ_STRID_BODY_2;
            amswizstringid[4] = MSWIZ_STRID_BODY_3;
            amswizstringid[5] = MSWIZ_STRID_BODY_4;
            amswizstringid[6] = MSWIZ_STRID_INPUT_1;
            amswizstringid[7] = MSWIZ_STRID_INPUT_2;
            amswizstringid[8] = MSWIZ_STRID_INSTRUCTIONS;
            amswizstringid[9] = MSWIZ_STRID_MAX;
            $VALUES = amswizstringid;
        }

        private MSWizStringID(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private MSWizStringID(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private MSWizStringID(String s, int i, MSWizStringID mswizstringid)
        {
            Enum(s, i);
            swigValue = mswizstringid.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    protected SCIMusicServiceWizard(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        SCIWizard(sclibJNI.SWIGSCIMusicServiceWizardUpcast(l), nativecleanupinvocation);
        swigCPtr = l;
    }

    protected SCIMusicServiceWizard(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCIMusicServiceWizard(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCIMusicServiceWizard scimusicservicewizard)
    {
        long l;
        if(scimusicservicewizard == null)
            l = 0L;
        else
            l = scimusicservicewizard.swigCPtr;
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

    public String getAccountNeededText()
    {
        return sclibJNI.SCIMusicServiceWizard_getAccountNeededText(swigCPtr, this);
    }

    public boolean getAgreeToTerms()
    {
        return sclibJNI.SCIMusicServiceWizard_getAgreeToTerms(swigCPtr, this);
    }

    public SCIEnumerator getAvailableServiceDescriptors()
    {
        long l = sclibJNI.SCIMusicServiceWizard_getAvailableServiceDescriptors(swigCPtr, this);
        SCIEnumerator scienumerator;
        if(l == 0L)
            scienumerator = null;
        else
            scienumerator = new SCIEnumerator(l, true);
        return scienumerator;
    }

    public boolean getHasAccountChoice()
    {
        return sclibJNI.SCIMusicServiceWizard_getHasAccountChoice(swigCPtr, this);
    }

    public String getHasAccountChoiceTitle()
    {
        return sclibJNI.SCIMusicServiceWizard_getHasAccountChoiceTitle(swigCPtr, this);
    }

    public SCIStringInput getLoginInput()
    {
        long l = sclibJNI.SCIMusicServiceWizard_getLoginInput(swigCPtr, this);
        SCIStringInput scistringinput;
        if(l == 0L)
            scistringinput = null;
        else
            scistringinput = new SCIStringInput(l, true);
        return scistringinput;
    }

    public String getNewChoiceTitle()
    {
        return sclibJNI.SCIMusicServiceWizard_getNewChoiceTitle(swigCPtr, this);
    }

    public SCIStringInput getNicknameInput()
    {
        long l = sclibJNI.SCIMusicServiceWizard_getNicknameInput(swigCPtr, this);
        SCIStringInput scistringinput;
        if(l == 0L)
            scistringinput = null;
        else
            scistringinput = new SCIStringInput(l, true);
        return scistringinput;
    }

    public SCIStringInput getPasswordInput()
    {
        long l = sclibJNI.SCIMusicServiceWizard_getPasswordInput(swigCPtr, this);
        SCIStringInput scistringinput;
        if(l == 0L)
            scistringinput = null;
        else
            scistringinput = new SCIStringInput(l, true);
        return scistringinput;
    }

    public boolean getRemovePromotedChoice()
    {
        return sclibJNI.SCIMusicServiceWizard_getRemovePromotedChoice(swigCPtr, this);
    }

    public String getResultText()
    {
        return sclibJNI.SCIMusicServiceWizard_getResultText(swigCPtr, this);
    }

    public SCIWizard.WizInputSelection getSelection()
    {
        return SCIWizard.WizInputSelection.swigToEnum(sclibJNI.SCIMusicServiceWizard_getSelection(swigCPtr, this));
    }

    public String getServiceBlurb()
    {
        return sclibJNI.SCIMusicServiceWizard_getServiceBlurb(swigCPtr, this);
    }

    public SCIBrowseItem.SCAlbumArtType getServiceLogoType()
    {
        return SCIBrowseItem.SCAlbumArtType.swigToEnum(sclibJNI.SCIMusicServiceWizard_getServiceLogoType(swigCPtr, this));
    }

    public String getServiceLogoUri()
    {
        return sclibJNI.SCIMusicServiceWizard_getServiceLogoUri(swigCPtr, this);
    }

    public String getServiceName()
    {
        return sclibJNI.SCIMusicServiceWizard_getServiceName(swigCPtr, this);
    }

    public String getServiceSubscribeText()
    {
        return sclibJNI.SCIMusicServiceWizard_getServiceSubscribeText(swigCPtr, this);
    }

    public boolean getShareUsage()
    {
        return sclibJNI.SCIMusicServiceWizard_getShareUsage(swigCPtr, this);
    }

    public boolean getShowLinkCode()
    {
        return sclibJNI.SCIMusicServiceWizard_getShowLinkCode(swigCPtr, this);
    }

    public String getTermsText()
    {
        return sclibJNI.SCIMusicServiceWizard_getTermsText(swigCPtr, this);
    }

    public String getWorkingText()
    {
        return sclibJNI.SCIMusicServiceWizard_getWorkingText(swigCPtr, this);
    }

    public boolean isResultSuccess()
    {
        return sclibJNI.SCIMusicServiceWizard_isResultSuccess(swigCPtr, this);
    }

    public void setAgreeToTerms(boolean flag)
    {
        sclibJNI.SCIMusicServiceWizard_setAgreeToTerms(swigCPtr, this, flag);
    }

    public void setHasAccountChoice(boolean flag)
    {
        sclibJNI.SCIMusicServiceWizard_setHasAccountChoice(swigCPtr, this, flag);
    }

    public void setMergeFromTrial(boolean flag)
    {
        sclibJNI.SCIMusicServiceWizard_setMergeFromTrial(swigCPtr, this, flag);
    }

    public void setRemovePromotedChoice(boolean flag)
    {
        sclibJNI.SCIMusicServiceWizard_setRemovePromotedChoice(swigCPtr, this, flag);
    }

    public void setSelection(SCIWizard.WizInputSelection wizinputselection)
    {
        sclibJNI.SCIMusicServiceWizard_setSelection(swigCPtr, this, wizinputselection.swigValue());
    }

    public void setServiceDescriptorID(String s)
    {
        sclibJNI.SCIMusicServiceWizard_setServiceDescriptorID(swigCPtr, this, s);
    }

    public void setShareUsage(boolean flag)
    {
        sclibJNI.SCIMusicServiceWizard_setShareUsage(swigCPtr, this, flag);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCIMusicServiceWizard");
    private long swigCPtr;

}

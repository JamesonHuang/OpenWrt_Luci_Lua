// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import com.sonos.jniutil.NativeWeakReferenceHelper;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            sclibJNI, SCIStringArray, SCUserInterfaceParameters, SCLibAssertionFailureCallback, 
//            SCLibCallUIThreadCallback, SCLibDiagnosticExtraInfoCallback, SCLibDiagnosticConsoleLogCallback, SCLibLogCallback, 
//            SCIPlatformDateTimeProvider, SCLibTruncatedStringsCallback, SCIWifiSetupDelegate

public class SCLibParameters
{
    public static final class AnacapaStdLogEnable extends Enum
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


        public static AnacapaStdLogEnable swigToEnum(int i)
        {
            AnacapaStdLogEnable aanacapastdlogenable[] = (AnacapaStdLogEnable[])com/sonos/sclib/SCLibParameters$AnacapaStdLogEnable.getEnumConstants();
            if(i >= aanacapastdlogenable.length || i < 0 || aanacapastdlogenable[i].swigValue != i) goto _L2; else goto _L1
_L1:
            AnacapaStdLogEnable anacapastdlogenable1 = aanacapastdlogenable[i];
_L4:
            return anacapastdlogenable1;
_L2:
            int j = aanacapastdlogenable.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                AnacapaStdLogEnable anacapastdlogenable = aanacapastdlogenable[k];
                if(anacapastdlogenable.swigValue == i)
                {
                    anacapastdlogenable1 = anacapastdlogenable;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCLibParameters$AnacapaStdLogEnable).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static AnacapaStdLogEnable valueOf(String s)
        {
            return (AnacapaStdLogEnable)Enum.valueOf(com/sonos/sclib/SCLibParameters$AnacapaStdLogEnable, s);
        }

        public static AnacapaStdLogEnable[] values()
        {
            return (AnacapaStdLogEnable[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final AnacapaStdLogEnable $VALUES[];
        public static final AnacapaStdLogEnable AnacapaLogDefault;
        public static final AnacapaStdLogEnable AnacapaLogDisable;
        public static final AnacapaStdLogEnable AnacapaLogEnable;
        private final int swigValue;

        static 
        {
            AnacapaLogDefault = new AnacapaStdLogEnable("AnacapaLogDefault", 0, sclibJNI.SCLibParameters_AnacapaLogDefault_get());
            AnacapaLogEnable = new AnacapaStdLogEnable("AnacapaLogEnable", 1);
            AnacapaLogDisable = new AnacapaStdLogEnable("AnacapaLogDisable", 2);
            AnacapaStdLogEnable aanacapastdlogenable[] = new AnacapaStdLogEnable[3];
            aanacapastdlogenable[0] = AnacapaLogDefault;
            aanacapastdlogenable[1] = AnacapaLogEnable;
            aanacapastdlogenable[2] = AnacapaLogDisable;
            $VALUES = aanacapastdlogenable;
        }

        private AnacapaStdLogEnable(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private AnacapaStdLogEnable(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private AnacapaStdLogEnable(String s, int i, AnacapaStdLogEnable anacapastdlogenable)
        {
            Enum(s, i);
            swigValue = anacapastdlogenable.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    public SCLibParameters()
    {
        SCLibParameters(sclibJNI.new_SCLibParameters(), true);
    }

    protected SCLibParameters(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        swigCPtr = l;
        nativeRef = new NativeWeakReferenceHelper(this, nativecleanupinvocation);
    }

    protected SCLibParameters(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCLibParameters(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCLibParameters sclibparameters)
    {
        long l;
        if(sclibparameters == null)
            l = 0L;
        else
            l = sclibparameters.swigCPtr;
        return l;
    }

    public void addDeveloperOption(String s)
    {
        sclibJNI.SCLibParameters_addDeveloperOption(swigCPtr, this, s);
    }

    public void dispose()
    {
        if(swigCPtr != 0L)
        {
            if(nativeRef != null)
            {
                NativeWeakReferenceHelper nativeweakreferencehelper = nativeRef;
                nativeRef = null;
                nativeweakreferencehelper.dispose();
            }
            swigCPtr = 0L;
        }
    }

    public SCIStringArray getDiagnosticCommandNames()
    {
        long l = sclibJNI.SCLibParameters_getDiagnosticCommandNames(swigCPtr, this);
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return scistringarray;
    }

    public SCIStringArray getDiagnosticCommands()
    {
        long l = sclibJNI.SCLibParameters_getDiagnosticCommands(swigCPtr, this);
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return scistringarray;
    }

    public SCIStringArray getDiagnosticFiles()
    {
        long l = sclibJNI.SCLibParameters_getDiagnosticFiles(swigCPtr, this);
        SCIStringArray scistringarray;
        if(l == 0L)
            scistringarray = null;
        else
            scistringarray = new SCIStringArray(l, true);
        return scistringarray;
    }

    public SCUserInterfaceParameters getM_UIParams()
    {
        long l = sclibJNI.SCLibParameters_m_UIParams_get(swigCPtr, this);
        SCUserInterfaceParameters scuserinterfaceparameters;
        if(l == 0L)
            scuserinterfaceparameters = null;
        else
            scuserinterfaceparameters = new SCUserInterfaceParameters(l, false);
        return scuserinterfaceparameters;
    }

    public boolean getM_bAnacapaUseConfFile()
    {
        return sclibJNI.SCLibParameters_m_bAnacapaUseConfFile_get(swigCPtr, this);
    }

    public boolean getM_bEnableAudibleManager()
    {
        return sclibJNI.SCLibParameters_m_bEnableAudibleManager_get(swigCPtr, this);
    }

    public boolean getM_bEnableSearchManager()
    {
        return sclibJNI.SCLibParameters_m_bEnableSearchManager_get(swigCPtr, this);
    }

    public boolean getM_bIsTestDevice()
    {
        return sclibJNI.SCLibParameters_m_bIsTestDevice_get(swigCPtr, this);
    }

    public boolean getM_bRequiresUnicastAlive()
    {
        return sclibJNI.SCLibParameters_m_bRequiresUnicastAlive_get(swigCPtr, this);
    }

    public AnacapaStdLogEnable getM_eAnacapaLogEnable()
    {
        return AnacapaStdLogEnable.swigToEnum(sclibJNI.SCLibParameters_m_eAnacapaLogEnable_get(swigCPtr, this));
    }

    public int getM_nAnacapaMaxConnections()
    {
        return sclibJNI.SCLibParameters_m_nAnacapaMaxConnections_get(swigCPtr, this);
    }

    public int getM_nAnacapaPortSearchAttempts()
    {
        return sclibJNI.SCLibParameters_m_nAnacapaPortSearchAttempts_get(swigCPtr, this);
    }

    public int getM_nBrowseCacheOverrideValue()
    {
        return sclibJNI.SCLibParameters_m_nBrowseCacheOverrideValue_get(swigCPtr, this);
    }

    public SCLibAssertionFailureCallback getM_pAssertionFailureCB()
    {
        long l = sclibJNI.SCLibParameters_m_pAssertionFailureCB_get(swigCPtr, this);
        SCLibAssertionFailureCallback sclibassertionfailurecallback;
        if(l == 0L)
            sclibassertionfailurecallback = null;
        else
            sclibassertionfailurecallback = new SCLibAssertionFailureCallback(l, false);
        return sclibassertionfailurecallback;
    }

    public SCLibCallUIThreadCallback getM_pCallUIThreadCB()
    {
        long l = sclibJNI.SCLibParameters_m_pCallUIThreadCB_get(swigCPtr, this);
        SCLibCallUIThreadCallback sclibcalluithreadcallback;
        if(l == 0L)
            sclibcalluithreadcallback = null;
        else
            sclibcalluithreadcallback = new SCLibCallUIThreadCallback(l, false);
        return sclibcalluithreadcallback;
    }

    public SCLibDiagnosticExtraInfoCallback getM_pDiagnosticCallback()
    {
        long l = sclibJNI.SCLibParameters_m_pDiagnosticCallback_get(swigCPtr, this);
        SCLibDiagnosticExtraInfoCallback sclibdiagnosticextrainfocallback;
        if(l == 0L)
            sclibdiagnosticextrainfocallback = null;
        else
            sclibdiagnosticextrainfocallback = new SCLibDiagnosticExtraInfoCallback(l, false);
        return sclibdiagnosticextrainfocallback;
    }

    public SCLibDiagnosticConsoleLogCallback getM_pDiagnosticConsoleLogCallback()
    {
        long l = sclibJNI.SCLibParameters_m_pDiagnosticConsoleLogCallback_get(swigCPtr, this);
        SCLibDiagnosticConsoleLogCallback sclibdiagnosticconsolelogcallback;
        if(l == 0L)
            sclibdiagnosticconsolelogcallback = null;
        else
            sclibdiagnosticconsolelogcallback = new SCLibDiagnosticConsoleLogCallback(l, false);
        return sclibdiagnosticconsolelogcallback;
    }

    public SCLibLogCallback getM_pLoggerCB()
    {
        long l = sclibJNI.SCLibParameters_m_pLoggerCB_get(swigCPtr, this);
        SCLibLogCallback scliblogcallback;
        if(l == 0L)
            scliblogcallback = null;
        else
            scliblogcallback = new SCLibLogCallback(l, false);
        return scliblogcallback;
    }

    public SCIPlatformDateTimeProvider getM_pPlatformDateTimeProvider()
    {
        long l = sclibJNI.SCLibParameters_m_pPlatformDateTimeProvider_get(swigCPtr, this);
        SCIPlatformDateTimeProvider sciplatformdatetimeprovider;
        if(l == 0L)
            sciplatformdatetimeprovider = null;
        else
            sciplatformdatetimeprovider = new SCIPlatformDateTimeProvider(l, false);
        return sciplatformdatetimeprovider;
    }

    public SCLibTruncatedStringsCallback getM_pTruncatedStringsCallback()
    {
        long l = sclibJNI.SCLibParameters_m_pTruncatedStringsCallback_get(swigCPtr, this);
        SCLibTruncatedStringsCallback sclibtruncatedstringscallback;
        if(l == 0L)
            sclibtruncatedstringscallback = null;
        else
            sclibtruncatedstringscallback = new SCLibTruncatedStringsCallback(l, false);
        return sclibtruncatedstringscallback;
    }

    public SCIWifiSetupDelegate getM_pWifiSetupDelegate()
    {
        long l = sclibJNI.SCLibParameters_m_pWifiSetupDelegate_get(swigCPtr, this);
        SCIWifiSetupDelegate sciwifisetupdelegate;
        if(l == 0L)
            sciwifisetupdelegate = null;
        else
            sciwifisetupdelegate = new SCIWifiSetupDelegate(l, false);
        return sciwifisetupdelegate;
    }

    public String getM_sAnacapaConfFilePath()
    {
        return sclibJNI.SCLibParameters_m_sAnacapaConfFilePath_get(swigCPtr, this);
    }

    public String getM_sDefaultCountryCode()
    {
        return sclibJNI.SCLibParameters_m_sDefaultCountryCode_get(swigCPtr, this);
    }

    public String getM_sDeveloperOptions()
    {
        return sclibJNI.SCLibParameters_m_sDeveloperOptions_get(swigCPtr, this);
    }

    public String getM_sDownloadedResourcesPath()
    {
        return sclibJNI.SCLibParameters_m_sDownloadedResourcesPath_get(swigCPtr, this);
    }

    public String getM_sHostDeviceID()
    {
        return sclibJNI.SCLibParameters_m_sHostDeviceID_get(swigCPtr, this);
    }

    public String getM_sHostMACAddress()
    {
        return sclibJNI.SCLibParameters_m_sHostMACAddress_get(swigCPtr, this);
    }

    public String getM_sHostModel()
    {
        return sclibJNI.SCLibParameters_m_sHostModel_get(swigCPtr, this);
    }

    public String getM_sJFFSRoot()
    {
        return sclibJNI.SCLibParameters_m_sJFFSRoot_get(swigCPtr, this);
    }

    public String getM_sLanguageID()
    {
        return sclibJNI.SCLibParameters_m_sLanguageID_get(swigCPtr, this);
    }

    public String getM_sOSVersion()
    {
        return sclibJNI.SCLibParameters_m_sOSVersion_get(swigCPtr, this);
    }

    public String getM_sResourcesPath()
    {
        return sclibJNI.SCLibParameters_m_sResourcesPath_get(swigCPtr, this);
    }

    public NativeWeakReferenceHelper getWeakRefHelper()
    {
        return nativeRef;
    }

    public boolean hasDeveloperOption(String s)
    {
        return sclibJNI.SCLibParameters_hasDeveloperOption(swigCPtr, this, s);
    }

    public void removeDeveloperOption(String s)
    {
        sclibJNI.SCLibParameters_removeDeveloperOption(swigCPtr, this, s);
    }

    public void setM_UIParams(SCUserInterfaceParameters scuserinterfaceparameters)
    {
        sclibJNI.SCLibParameters_m_UIParams_set(swigCPtr, this, SCUserInterfaceParameters.getCPtr(scuserinterfaceparameters), scuserinterfaceparameters);
    }

    public void setM_bAnacapaUseConfFile(boolean flag)
    {
        sclibJNI.SCLibParameters_m_bAnacapaUseConfFile_set(swigCPtr, this, flag);
    }

    public void setM_bEnableAudibleManager(boolean flag)
    {
        sclibJNI.SCLibParameters_m_bEnableAudibleManager_set(swigCPtr, this, flag);
    }

    public void setM_bEnableSearchManager(boolean flag)
    {
        sclibJNI.SCLibParameters_m_bEnableSearchManager_set(swigCPtr, this, flag);
    }

    public void setM_bIsTestDevice(boolean flag)
    {
        sclibJNI.SCLibParameters_m_bIsTestDevice_set(swigCPtr, this, flag);
    }

    public void setM_bRequiresUnicastAlive(boolean flag)
    {
        sclibJNI.SCLibParameters_m_bRequiresUnicastAlive_set(swigCPtr, this, flag);
    }

    public void setM_eAnacapaLogEnable(AnacapaStdLogEnable anacapastdlogenable)
    {
        sclibJNI.SCLibParameters_m_eAnacapaLogEnable_set(swigCPtr, this, anacapastdlogenable.swigValue());
    }

    public void setM_nAnacapaMaxConnections(int i)
    {
        sclibJNI.SCLibParameters_m_nAnacapaMaxConnections_set(swigCPtr, this, i);
    }

    public void setM_nAnacapaPortSearchAttempts(int i)
    {
        sclibJNI.SCLibParameters_m_nAnacapaPortSearchAttempts_set(swigCPtr, this, i);
    }

    public void setM_nBrowseCacheOverrideValue(int i)
    {
        sclibJNI.SCLibParameters_m_nBrowseCacheOverrideValue_set(swigCPtr, this, i);
    }

    public void setM_pAssertionFailureCB(SCLibAssertionFailureCallback sclibassertionfailurecallback)
    {
        sclibJNI.SCLibParameters_m_pAssertionFailureCB_set(swigCPtr, this, SCLibAssertionFailureCallback.getCPtr(sclibassertionfailurecallback), sclibassertionfailurecallback);
    }

    public void setM_pCallUIThreadCB(SCLibCallUIThreadCallback sclibcalluithreadcallback)
    {
        sclibJNI.SCLibParameters_m_pCallUIThreadCB_set(swigCPtr, this, SCLibCallUIThreadCallback.getCPtr(sclibcalluithreadcallback), sclibcalluithreadcallback);
    }

    public void setM_pDiagnosticCallback(SCLibDiagnosticExtraInfoCallback sclibdiagnosticextrainfocallback)
    {
        sclibJNI.SCLibParameters_m_pDiagnosticCallback_set(swigCPtr, this, SCLibDiagnosticExtraInfoCallback.getCPtr(sclibdiagnosticextrainfocallback), sclibdiagnosticextrainfocallback);
    }

    public void setM_pDiagnosticConsoleLogCallback(SCLibDiagnosticConsoleLogCallback sclibdiagnosticconsolelogcallback)
    {
        sclibJNI.SCLibParameters_m_pDiagnosticConsoleLogCallback_set(swigCPtr, this, SCLibDiagnosticConsoleLogCallback.getCPtr(sclibdiagnosticconsolelogcallback), sclibdiagnosticconsolelogcallback);
    }

    public void setM_pLoggerCB(SCLibLogCallback scliblogcallback)
    {
        sclibJNI.SCLibParameters_m_pLoggerCB_set(swigCPtr, this, SCLibLogCallback.getCPtr(scliblogcallback), scliblogcallback);
    }

    public void setM_pPlatformDateTimeProvider(SCIPlatformDateTimeProvider sciplatformdatetimeprovider)
    {
        sclibJNI.SCLibParameters_m_pPlatformDateTimeProvider_set(swigCPtr, this, SCIPlatformDateTimeProvider.getCPtr(sciplatformdatetimeprovider), sciplatformdatetimeprovider);
    }

    public void setM_pTruncatedStringsCallback(SCLibTruncatedStringsCallback sclibtruncatedstringscallback)
    {
        sclibJNI.SCLibParameters_m_pTruncatedStringsCallback_set(swigCPtr, this, SCLibTruncatedStringsCallback.getCPtr(sclibtruncatedstringscallback), sclibtruncatedstringscallback);
    }

    public void setM_pWifiSetupDelegate(SCIWifiSetupDelegate sciwifisetupdelegate)
    {
        sclibJNI.SCLibParameters_m_pWifiSetupDelegate_set(swigCPtr, this, SCIWifiSetupDelegate.getCPtr(sciwifisetupdelegate), sciwifisetupdelegate);
    }

    public void setM_sAnacapaConfFilePath(String s)
    {
        sclibJNI.SCLibParameters_m_sAnacapaConfFilePath_set(swigCPtr, this, s);
    }

    public void setM_sDefaultCountryCode(String s)
    {
        sclibJNI.SCLibParameters_m_sDefaultCountryCode_set(swigCPtr, this, s);
    }

    public void setM_sDeveloperOptions(String s)
    {
        sclibJNI.SCLibParameters_m_sDeveloperOptions_set(swigCPtr, this, s);
    }

    public void setM_sDownloadedResourcesPath(String s)
    {
        sclibJNI.SCLibParameters_m_sDownloadedResourcesPath_set(swigCPtr, this, s);
    }

    public void setM_sHostDeviceID(String s)
    {
        sclibJNI.SCLibParameters_m_sHostDeviceID_set(swigCPtr, this, s);
    }

    public void setM_sHostMACAddress(String s)
    {
        sclibJNI.SCLibParameters_m_sHostMACAddress_set(swigCPtr, this, s);
    }

    public void setM_sHostModel(String s)
    {
        sclibJNI.SCLibParameters_m_sHostModel_set(swigCPtr, this, s);
    }

    public void setM_sJFFSRoot(String s)
    {
        sclibJNI.SCLibParameters_m_sJFFSRoot_set(swigCPtr, this, s);
    }

    public void setM_sLanguageID(String s)
    {
        sclibJNI.SCLibParameters_m_sLanguageID_set(swigCPtr, this, s);
    }

    public void setM_sOSVersion(String s)
    {
        sclibJNI.SCLibParameters_m_sOSVersion_set(swigCPtr, this, s);
    }

    public void setM_sResourcesPath(String s)
    {
        sclibJNI.SCLibParameters_m_sResourcesPath_set(swigCPtr, this, s);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCLibParameters");
    protected NativeWeakReferenceHelper nativeRef;
    private long swigCPtr;

}

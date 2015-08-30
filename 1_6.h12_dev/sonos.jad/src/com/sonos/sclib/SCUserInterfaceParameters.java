// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.sclib;

import com.sonos.jniutil.NativeCleanupInvocation;
import com.sonos.jniutil.NativeWeakReferenceHelper;
import java.lang.reflect.Method;

// Referenced classes of package com.sonos.sclib:
//            sclibJNI, SWIGTYPE_p_SCPtrT_SCIStringArray_t

public class SCUserInterfaceParameters
{
    public static final class SupportsLocalSocialSharing extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$502(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$508()
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


        public static SupportsLocalSocialSharing swigToEnum(int i)
        {
            SupportsLocalSocialSharing asupportslocalsocialsharing[] = (SupportsLocalSocialSharing[])com/sonos/sclib/SCUserInterfaceParameters$SupportsLocalSocialSharing.getEnumConstants();
            if(i >= asupportslocalsocialsharing.length || i < 0 || asupportslocalsocialsharing[i].swigValue != i) goto _L2; else goto _L1
_L1:
            SupportsLocalSocialSharing supportslocalsocialsharing1 = asupportslocalsocialsharing[i];
_L4:
            return supportslocalsocialsharing1;
_L2:
            int j = asupportslocalsocialsharing.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                SupportsLocalSocialSharing supportslocalsocialsharing = asupportslocalsocialsharing[k];
                if(supportslocalsocialsharing.swigValue == i)
                {
                    supportslocalsocialsharing1 = supportslocalsocialsharing;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCUserInterfaceParameters$SupportsLocalSocialSharing).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static SupportsLocalSocialSharing valueOf(String s)
        {
            return (SupportsLocalSocialSharing)Enum.valueOf(com/sonos/sclib/SCUserInterfaceParameters$SupportsLocalSocialSharing, s);
        }

        public static SupportsLocalSocialSharing[] values()
        {
            return (SupportsLocalSocialSharing[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final SupportsLocalSocialSharing $VALUES[];
        public static final SupportsLocalSocialSharing SCUI_NO_SHARING_SUPPORT;
        public static final SupportsLocalSocialSharing SCUI_SHARING_SUPPORT;
        private final int swigValue;

        static 
        {
            SCUI_NO_SHARING_SUPPORT = new SupportsLocalSocialSharing("SCUI_NO_SHARING_SUPPORT", 0);
            SCUI_SHARING_SUPPORT = new SupportsLocalSocialSharing("SCUI_SHARING_SUPPORT", 1);
            SupportsLocalSocialSharing asupportslocalsocialsharing[] = new SupportsLocalSocialSharing[2];
            asupportslocalsocialsharing[0] = SCUI_NO_SHARING_SUPPORT;
            asupportslocalsocialsharing[1] = SCUI_SHARING_SUPPORT;
            $VALUES = asupportslocalsocialsharing;
        }

        private SupportsLocalSocialSharing(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private SupportsLocalSocialSharing(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private SupportsLocalSocialSharing(String s, int i, SupportsLocalSocialSharing supportslocalsocialsharing)
        {
            Enum(s, i);
            swigValue = supportslocalsocialsharing.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class BrowseSearchMode extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$402(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$408()
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


        public static BrowseSearchMode swigToEnum(int i)
        {
            BrowseSearchMode abrowsesearchmode[] = (BrowseSearchMode[])com/sonos/sclib/SCUserInterfaceParameters$BrowseSearchMode.getEnumConstants();
            if(i >= abrowsesearchmode.length || i < 0 || abrowsesearchmode[i].swigValue != i) goto _L2; else goto _L1
_L1:
            BrowseSearchMode browsesearchmode1 = abrowsesearchmode[i];
_L4:
            return browsesearchmode1;
_L2:
            int j = abrowsesearchmode.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                BrowseSearchMode browsesearchmode = abrowsesearchmode[k];
                if(browsesearchmode.swigValue == i)
                {
                    browsesearchmode1 = browsesearchmode;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCUserInterfaceParameters$BrowseSearchMode).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static BrowseSearchMode valueOf(String s)
        {
            return (BrowseSearchMode)Enum.valueOf(com/sonos/sclib/SCUserInterfaceParameters$BrowseSearchMode, s);
        }

        public static BrowseSearchMode[] values()
        {
            return (BrowseSearchMode[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final BrowseSearchMode $VALUES[];
        public static final BrowseSearchMode SCUI_HAS_OWN_SEARCH_UI;
        public static final BrowseSearchMode SCUI_USES_BROWSE_FOR_SEARCH;
        private final int swigValue;

        static 
        {
            SCUI_USES_BROWSE_FOR_SEARCH = new BrowseSearchMode("SCUI_USES_BROWSE_FOR_SEARCH", 0, sclibJNI.SCUserInterfaceParameters_SCUI_USES_BROWSE_FOR_SEARCH_get());
            SCUI_HAS_OWN_SEARCH_UI = new BrowseSearchMode("SCUI_HAS_OWN_SEARCH_UI", 1);
            BrowseSearchMode abrowsesearchmode[] = new BrowseSearchMode[2];
            abrowsesearchmode[0] = SCUI_USES_BROWSE_FOR_SEARCH;
            abrowsesearchmode[1] = SCUI_HAS_OWN_SEARCH_UI;
            $VALUES = abrowsesearchmode;
        }

        private BrowseSearchMode(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private BrowseSearchMode(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private BrowseSearchMode(String s, int i, BrowseSearchMode browsesearchmode)
        {
            Enum(s, i);
            swigValue = browsesearchmode.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class ScreenDensityType extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$302(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$308()
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


        public static ScreenDensityType swigToEnum(int i)
        {
            ScreenDensityType ascreendensitytype[] = (ScreenDensityType[])com/sonos/sclib/SCUserInterfaceParameters$ScreenDensityType.getEnumConstants();
            if(i >= ascreendensitytype.length || i < 0 || ascreendensitytype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            ScreenDensityType screendensitytype1 = ascreendensitytype[i];
_L4:
            return screendensitytype1;
_L2:
            int j = ascreendensitytype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                ScreenDensityType screendensitytype = ascreendensitytype[k];
                if(screendensitytype.swigValue == i)
                {
                    screendensitytype1 = screendensitytype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCUserInterfaceParameters$ScreenDensityType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static ScreenDensityType valueOf(String s)
        {
            return (ScreenDensityType)Enum.valueOf(com/sonos/sclib/SCUserInterfaceParameters$ScreenDensityType, s);
        }

        public static ScreenDensityType[] values()
        {
            return (ScreenDensityType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final ScreenDensityType $VALUES[];
        public static final ScreenDensityType SCUI_DENSITY_HIGH;
        public static final ScreenDensityType SCUI_DENSITY_LOW;
        public static final ScreenDensityType SCUI_DENSITY_MEDIUM;
        private final int swigValue;

        static 
        {
            SCUI_DENSITY_HIGH = new ScreenDensityType("SCUI_DENSITY_HIGH", 0, sclibJNI.SCUserInterfaceParameters_SCUI_DENSITY_HIGH_get());
            SCUI_DENSITY_MEDIUM = new ScreenDensityType("SCUI_DENSITY_MEDIUM", 1);
            SCUI_DENSITY_LOW = new ScreenDensityType("SCUI_DENSITY_LOW", 2);
            ScreenDensityType ascreendensitytype[] = new ScreenDensityType[3];
            ascreendensitytype[0] = SCUI_DENSITY_HIGH;
            ascreendensitytype[1] = SCUI_DENSITY_MEDIUM;
            ascreendensitytype[2] = SCUI_DENSITY_LOW;
            $VALUES = ascreendensitytype;
        }

        private ScreenDensityType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private ScreenDensityType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private ScreenDensityType(String s, int i, ScreenDensityType screendensitytype)
        {
            Enum(s, i);
            swigValue = screendensitytype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class BrowseTextStyle extends Enum
    {
        private static class SwigNext
        {

            private static int next = 0;



/*
            static int access$202(int i)
            {
                next = i;
                return i;
            }

*/


/*
            static int access$208()
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


        public static BrowseTextStyle swigToEnum(int i)
        {
            BrowseTextStyle abrowsetextstyle[] = (BrowseTextStyle[])com/sonos/sclib/SCUserInterfaceParameters$BrowseTextStyle.getEnumConstants();
            if(i >= abrowsetextstyle.length || i < 0 || abrowsetextstyle[i].swigValue != i) goto _L2; else goto _L1
_L1:
            BrowseTextStyle browsetextstyle1 = abrowsetextstyle[i];
_L4:
            return browsetextstyle1;
_L2:
            int j = abrowsetextstyle.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                BrowseTextStyle browsetextstyle = abrowsetextstyle[k];
                if(browsetextstyle.swigValue == i)
                {
                    browsetextstyle1 = browsetextstyle;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCUserInterfaceParameters$BrowseTextStyle).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static BrowseTextStyle valueOf(String s)
        {
            return (BrowseTextStyle)Enum.valueOf(com/sonos/sclib/SCUserInterfaceParameters$BrowseTextStyle, s);
        }

        public static BrowseTextStyle[] values()
        {
            return (BrowseTextStyle[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final BrowseTextStyle $VALUES[];
        public static final BrowseTextStyle SCUI_BTS_LONG;
        public static final BrowseTextStyle SCUI_BTS_SHORT;
        private final int swigValue;

        static 
        {
            SCUI_BTS_SHORT = new BrowseTextStyle("SCUI_BTS_SHORT", 0, sclibJNI.SCUserInterfaceParameters_SCUI_BTS_SHORT_get());
            SCUI_BTS_LONG = new BrowseTextStyle("SCUI_BTS_LONG", 1);
            BrowseTextStyle abrowsetextstyle[] = new BrowseTextStyle[2];
            abrowsetextstyle[0] = SCUI_BTS_SHORT;
            abrowsetextstyle[1] = SCUI_BTS_LONG;
            $VALUES = abrowsetextstyle;
        }

        private BrowseTextStyle(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private BrowseTextStyle(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private BrowseTextStyle(String s, int i, BrowseTextStyle browsetextstyle)
        {
            Enum(s, i);
            swigValue = browsetextstyle.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class PhoneType extends Enum
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


        public static PhoneType swigToEnum(int i)
        {
            PhoneType aphonetype[] = (PhoneType[])com/sonos/sclib/SCUserInterfaceParameters$PhoneType.getEnumConstants();
            if(i >= aphonetype.length || i < 0 || aphonetype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            PhoneType phonetype1 = aphonetype[i];
_L4:
            return phonetype1;
_L2:
            int j = aphonetype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                PhoneType phonetype = aphonetype[k];
                if(phonetype.swigValue == i)
                {
                    phonetype1 = phonetype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCUserInterfaceParameters$PhoneType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static PhoneType valueOf(String s)
        {
            return (PhoneType)Enum.valueOf(com/sonos/sclib/SCUserInterfaceParameters$PhoneType, s);
        }

        public static PhoneType[] values()
        {
            return (PhoneType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final PhoneType $VALUES[];
        public static final PhoneType SCUI_PHONE_LARGE;
        public static final PhoneType SCUI_PHONE_NORMAL;
        public static final PhoneType SCUI_PHONE_SHORT;
        public static final PhoneType SCUI_PHONE_UNKNOWN;
        private final int swigValue;

        static 
        {
            SCUI_PHONE_UNKNOWN = new PhoneType("SCUI_PHONE_UNKNOWN", 0, sclibJNI.SCUserInterfaceParameters_SCUI_PHONE_UNKNOWN_get());
            SCUI_PHONE_SHORT = new PhoneType("SCUI_PHONE_SHORT", 1);
            SCUI_PHONE_NORMAL = new PhoneType("SCUI_PHONE_NORMAL", 2);
            SCUI_PHONE_LARGE = new PhoneType("SCUI_PHONE_LARGE", 3);
            PhoneType aphonetype[] = new PhoneType[4];
            aphonetype[0] = SCUI_PHONE_UNKNOWN;
            aphonetype[1] = SCUI_PHONE_SHORT;
            aphonetype[2] = SCUI_PHONE_NORMAL;
            aphonetype[3] = SCUI_PHONE_LARGE;
            $VALUES = aphonetype;
        }

        private PhoneType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private PhoneType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private PhoneType(String s, int i, PhoneType phonetype)
        {
            Enum(s, i);
            swigValue = phonetype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }

    public static final class FormFactorType extends Enum
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


        public static FormFactorType swigToEnum(int i)
        {
            FormFactorType aformfactortype[] = (FormFactorType[])com/sonos/sclib/SCUserInterfaceParameters$FormFactorType.getEnumConstants();
            if(i >= aformfactortype.length || i < 0 || aformfactortype[i].swigValue != i) goto _L2; else goto _L1
_L1:
            FormFactorType formfactortype1 = aformfactortype[i];
_L4:
            return formfactortype1;
_L2:
            int j = aformfactortype.length;
            int k = 0;
            do
            {
                if(k >= j)
                    break;
                FormFactorType formfactortype = aformfactortype[k];
                if(formfactortype.swigValue == i)
                {
                    formfactortype1 = formfactortype;
                    continue; /* Loop/switch isn't completed */
                }
                k++;
            } while(true);
            throw new IllegalArgumentException((new StringBuilder()).append("No enum ").append(com/sonos/sclib/SCUserInterfaceParameters$FormFactorType).append(" with value ").append(i).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static FormFactorType valueOf(String s)
        {
            return (FormFactorType)Enum.valueOf(com/sonos/sclib/SCUserInterfaceParameters$FormFactorType, s);
        }

        public static FormFactorType[] values()
        {
            return (FormFactorType[])$VALUES.clone();
        }

        public final int swigValue()
        {
            return swigValue;
        }

        private static final FormFactorType $VALUES[];
        public static final FormFactorType SCUI_FF_DESKTOP;
        public static final FormFactorType SCUI_FF_HANDHELD;
        public static final FormFactorType SCUI_FF_TABLET;
        public static final FormFactorType SCUI_FF_TEXTCONSOLE;
        public static final FormFactorType SCUI_FF_UNKNOWN;
        private final int swigValue;

        static 
        {
            SCUI_FF_UNKNOWN = new FormFactorType("SCUI_FF_UNKNOWN", 0, sclibJNI.SCUserInterfaceParameters_SCUI_FF_UNKNOWN_get());
            SCUI_FF_TEXTCONSOLE = new FormFactorType("SCUI_FF_TEXTCONSOLE", 1);
            SCUI_FF_HANDHELD = new FormFactorType("SCUI_FF_HANDHELD", 2);
            SCUI_FF_DESKTOP = new FormFactorType("SCUI_FF_DESKTOP", 3);
            SCUI_FF_TABLET = new FormFactorType("SCUI_FF_TABLET", 4);
            FormFactorType aformfactortype[] = new FormFactorType[5];
            aformfactortype[0] = SCUI_FF_UNKNOWN;
            aformfactortype[1] = SCUI_FF_TEXTCONSOLE;
            aformfactortype[2] = SCUI_FF_HANDHELD;
            aformfactortype[3] = SCUI_FF_DESKTOP;
            aformfactortype[4] = SCUI_FF_TABLET;
            $VALUES = aformfactortype;
        }

        private FormFactorType(String s, int i)
        {
            Enum(s, i);
            swigValue = int i = 
// JavaClassFileOutputException: get_constant: invalid tag

        private FormFactorType(String s, int i, int j)
        {
            Enum(s, i);
            swigValue = j;
            SwigNext.next = j + 1;
        }

        private FormFactorType(String s, int i, FormFactorType formfactortype)
        {
            Enum(s, i);
            swigValue = formfactortype.swigValue;
            SwigNext.next = 1 + swigValue;
        }
    }


    public SCUserInterfaceParameters()
    {
        SCUserInterfaceParameters(sclibJNI.new_SCUserInterfaceParameters(), true);
    }

    protected SCUserInterfaceParameters(long l, NativeCleanupInvocation nativecleanupinvocation)
    {
        swigCPtr = l;
        nativeRef = new NativeWeakReferenceHelper(this, nativecleanupinvocation);
    }

    protected SCUserInterfaceParameters(long l, boolean flag)
    {
        NativeCleanupInvocation nativecleanupinvocation;
        if(flag)
            nativecleanupinvocation = new NativeCleanupInvocation(dtorMethod, l);
        else
            nativecleanupinvocation = null;
        SCUserInterfaceParameters(l, nativecleanupinvocation);
    }

    protected static long getCPtr(SCUserInterfaceParameters scuserinterfaceparameters)
    {
        long l;
        if(scuserinterfaceparameters == null)
            l = 0L;
        else
            l = scuserinterfaceparameters.swigCPtr;
        return l;
    }

    public void addScreenResolution(String s, String s1)
    {
        sclibJNI.SCUserInterfaceParameters_addScreenResolution(swigCPtr, this, s, s1);
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

    public BrowseSearchMode getM_browseSearchMode()
    {
        return BrowseSearchMode.swigToEnum(sclibJNI.SCUserInterfaceParameters_m_browseSearchMode_get(swigCPtr, this));
    }

    public BrowseTextStyle getM_browseTextStyle()
    {
        return BrowseTextStyle.swigToEnum(sclibJNI.SCUserInterfaceParameters_m_browseTextStyle_get(swigCPtr, this));
    }

    public int getM_densityDpi()
    {
        return sclibJNI.SCUserInterfaceParameters_m_densityDpi_get(swigCPtr, this);
    }

    public FormFactorType getM_formFactor()
    {
        return FormFactorType.swigToEnum(sclibJNI.SCUserInterfaceParameters_m_formFactor_get(swigCPtr, this));
    }

    public SupportsLocalSocialSharing getM_localSharingSupport()
    {
        return SupportsLocalSocialSharing.swigToEnum(sclibJNI.SCUserInterfaceParameters_m_localSharingSupport_get(swigCPtr, this));
    }

    public PhoneType getM_phoneType()
    {
        return PhoneType.swigToEnum(sclibJNI.SCUserInterfaceParameters_m_phoneType_get(swigCPtr, this));
    }

    public float getM_pixelRatio()
    {
        return sclibJNI.SCUserInterfaceParameters_m_pixelRatio_get(swigCPtr, this);
    }

    public float getM_scaledScreenDensity()
    {
        return sclibJNI.SCUserInterfaceParameters_m_scaledScreenDensity_get(swigCPtr, this);
    }

    public ScreenDensityType getM_screenDensity()
    {
        return ScreenDensityType.swigToEnum(sclibJNI.SCUserInterfaceParameters_m_screenDensity_get(swigCPtr, this));
    }

    public int getM_screenHeight()
    {
        return sclibJNI.SCUserInterfaceParameters_m_screenHeight_get(swigCPtr, this);
    }

    public SWIGTYPE_p_SCPtrT_SCIStringArray_t getM_screenHeightArray()
    {
        long l = sclibJNI.SCUserInterfaceParameters_m_screenHeightArray_get(swigCPtr, this);
        SWIGTYPE_p_SCPtrT_SCIStringArray_t swigtype_p_scptrt_scistringarray_t;
        if(l == 0L)
            swigtype_p_scptrt_scistringarray_t = null;
        else
            swigtype_p_scptrt_scistringarray_t = new SWIGTYPE_p_SCPtrT_SCIStringArray_t(l, false);
        return swigtype_p_scptrt_scistringarray_t;
    }

    public int getM_screenWidth()
    {
        return sclibJNI.SCUserInterfaceParameters_m_screenWidth_get(swigCPtr, this);
    }

    public SWIGTYPE_p_SCPtrT_SCIStringArray_t getM_screenWidthArray()
    {
        long l = sclibJNI.SCUserInterfaceParameters_m_screenWidthArray_get(swigCPtr, this);
        SWIGTYPE_p_SCPtrT_SCIStringArray_t swigtype_p_scptrt_scistringarray_t;
        if(l == 0L)
            swigtype_p_scptrt_scistringarray_t = null;
        else
            swigtype_p_scptrt_scistringarray_t = new SWIGTYPE_p_SCPtrT_SCIStringArray_t(l, false);
        return swigtype_p_scptrt_scistringarray_t;
    }

    public float getM_xDpi()
    {
        return sclibJNI.SCUserInterfaceParameters_m_xDpi_get(swigCPtr, this);
    }

    public float getM_yDpi()
    {
        return sclibJNI.SCUserInterfaceParameters_m_yDpi_get(swigCPtr, this);
    }

    public NativeWeakReferenceHelper getWeakRefHelper()
    {
        return nativeRef;
    }

    public void setM_browseSearchMode(BrowseSearchMode browsesearchmode)
    {
        sclibJNI.SCUserInterfaceParameters_m_browseSearchMode_set(swigCPtr, this, browsesearchmode.swigValue());
    }

    public void setM_browseTextStyle(BrowseTextStyle browsetextstyle)
    {
        sclibJNI.SCUserInterfaceParameters_m_browseTextStyle_set(swigCPtr, this, browsetextstyle.swigValue());
    }

    public void setM_densityDpi(int i)
    {
        sclibJNI.SCUserInterfaceParameters_m_densityDpi_set(swigCPtr, this, i);
    }

    public void setM_formFactor(FormFactorType formfactortype)
    {
        sclibJNI.SCUserInterfaceParameters_m_formFactor_set(swigCPtr, this, formfactortype.swigValue());
    }

    public void setM_localSharingSupport(SupportsLocalSocialSharing supportslocalsocialsharing)
    {
        sclibJNI.SCUserInterfaceParameters_m_localSharingSupport_set(swigCPtr, this, supportslocalsocialsharing.swigValue());
    }

    public void setM_phoneType(PhoneType phonetype)
    {
        sclibJNI.SCUserInterfaceParameters_m_phoneType_set(swigCPtr, this, phonetype.swigValue());
    }

    public void setM_pixelRatio(float f)
    {
        sclibJNI.SCUserInterfaceParameters_m_pixelRatio_set(swigCPtr, this, f);
    }

    public void setM_scaledScreenDensity(float f)
    {
        sclibJNI.SCUserInterfaceParameters_m_scaledScreenDensity_set(swigCPtr, this, f);
    }

    public void setM_screenDensity(ScreenDensityType screendensitytype)
    {
        sclibJNI.SCUserInterfaceParameters_m_screenDensity_set(swigCPtr, this, screendensitytype.swigValue());
    }

    public void setM_screenHeight(int i)
    {
        sclibJNI.SCUserInterfaceParameters_m_screenHeight_set(swigCPtr, this, i);
    }

    public void setM_screenHeightArray(SWIGTYPE_p_SCPtrT_SCIStringArray_t swigtype_p_scptrt_scistringarray_t)
    {
        sclibJNI.SCUserInterfaceParameters_m_screenHeightArray_set(swigCPtr, this, SWIGTYPE_p_SCPtrT_SCIStringArray_t.getCPtr(swigtype_p_scptrt_scistringarray_t));
    }

    public void setM_screenWidth(int i)
    {
        sclibJNI.SCUserInterfaceParameters_m_screenWidth_set(swigCPtr, this, i);
    }

    public void setM_screenWidthArray(SWIGTYPE_p_SCPtrT_SCIStringArray_t swigtype_p_scptrt_scistringarray_t)
    {
        sclibJNI.SCUserInterfaceParameters_m_screenWidthArray_set(swigCPtr, this, SWIGTYPE_p_SCPtrT_SCIStringArray_t.getCPtr(swigtype_p_scptrt_scistringarray_t));
    }

    public void setM_xDpi(float f)
    {
        sclibJNI.SCUserInterfaceParameters_m_xDpi_set(swigCPtr, this, f);
    }

    public void setM_yDpi(float f)
    {
        sclibJNI.SCUserInterfaceParameters_m_yDpi_set(swigCPtr, this, f);
    }

    private static final Method dtorMethod = NativeCleanupInvocation.lookupMethod(com/sonos/sclib/sclibJNI, "delete_SCUserInterfaceParameters");
    protected NativeWeakReferenceHelper nativeRef;
    private long swigCPtr;

}

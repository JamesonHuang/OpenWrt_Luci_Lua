// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.wizards.anonymous.components;

import android.view.View;
import android.view.ViewGroup;
import com.sonos.acr.wizards.Wizard;
import com.sonos.sclib.*;
import java.util.ArrayList;

// Referenced classes of package com.sonos.acr.wizards.anonymous.components:
//            WizardTitleComponent, WizardTextComponent, WizardImageComponent, WizardButtonComponent, 
//            WizardCheckBoxComponent, WizardInputComponent, WizardSecureInputComponent, WizardSpinnerComponent, 
//            WizardListComponent

public abstract class WizardComponent
{

    public WizardComponent()
    {
    }

    public static WizardComponent WizardComponentWithDescriptor(SCIPropertyBag scipropertybag, Wizard wizard)
    {
        class _cls1
        {

            static final int $SwitchMap$com$sonos$sclib$WizardComponentType[];

            static 
            {
                $SwitchMap$com$sonos$sclib$WizardComponentType = new int[WizardComponentType.values().length];
                NoSuchFieldError nosuchfielderror8;
                try
                {
                    $SwitchMap$com$sonos$sclib$WizardComponentType[WizardComponentType.WIZARD_COMPONENT_TYPE_PAGE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$WizardComponentType[WizardComponentType.WIZARD_COMPONENT_TYPE_TEXT.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$WizardComponentType[WizardComponentType.WIZARD_COMPONENT_TYPE_IMAGE.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$WizardComponentType[WizardComponentType.WIZARD_COMPONENT_TYPE_BUTTON.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$WizardComponentType[WizardComponentType.WIZARD_COMPONENT_TYPE_CHECKBOX.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$WizardComponentType[WizardComponentType.WIZARD_COMPONENT_TYPE_TEXTFIELD.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$WizardComponentType[WizardComponentType.WIZARD_COMPONENT_TYPE_SECURE_TEXTFIELD.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$sonos$sclib$WizardComponentType[WizardComponentType.WIZARD_COMPONENT_TYPE_SPINNER.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7) { }
                $SwitchMap$com$sonos$sclib$WizardComponentType[WizardComponentType.WIZARD_COMPONENT_TYPE_LIST.ordinal()] = 9;
_L2:
                return;
                nosuchfielderror8;
                if(true) goto _L2; else goto _L1
_L1:
            }
        }

        _cls1..SwitchMap.com.sonos.sclib.WizardComponentType[WizardComponentType.swigToEnum(scipropertybag.getIntProp(sclibConstants.WIZARD_COMPONENT_KEY_TYPE)).ordinal()];
        JVM INSTR tableswitch 1 9: default 68
    //                   1 72
    //                   2 105
    //                   3 166
    //                   4 217
    //                   5 266
    //                   6 315
    //                   7 378
    //                   8 447
    //                   9 458;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
        Object obj = null;
_L12:
        return ((WizardComponent) (obj));
_L2:
        obj = new WizardTitleComponent();
        ((WizardTitleComponent) (obj)).setTitle(scipropertybag.getStrProp(sclibConstants.WIZARD_COMPONENT_KEY_STRING));
        obj.isInvisible = scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_INVISIBLE);
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new WizardTextComponent();
        ((WizardTextComponent) (obj)).setText(scipropertybag.getStrProp(sclibConstants.WIZARD_COMPONENT_KEY_STRING));
        if(scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_BOLD_TEXT))
            ((WizardTextComponent) (obj)).setBold();
        if(scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_SELECT_TEXT))
            ((WizardTextComponent) (obj)).setSelectable();
        obj.isInvisible = scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_INVISIBLE);
        continue; /* Loop/switch isn't completed */
_L4:
        obj = new WizardImageComponent();
        ((WizardImageComponent) (obj)).setImageURL(scipropertybag.getStrProp(sclibConstants.WIZARD_COMPONENT_KEY_IMAGE_URL));
        ((WizardImageComponent) (obj)).setImageType(com.sonos.sclib.SCIBrowseItem.SCAlbumArtType.swigToEnum(scipropertybag.getIntProp(sclibConstants.WIZARD_COMPONENT_KEY_IMAGE_TYPE_KEY)));
        ((WizardImageComponent) (obj)).fetchImage();
        obj.isInvisible = scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_INVISIBLE);
        continue; /* Loop/switch isn't completed */
_L5:
        obj = new WizardButtonComponent();
        ((WizardButtonComponent) (obj)).setWizard(wizard);
        ((WizardButtonComponent) (obj)).setText(scipropertybag.getStrProp(sclibConstants.WIZARD_COMPONENT_KEY_STRING));
        ((WizardButtonComponent) (obj)).setInput(scipropertybag.getIntProp(sclibConstants.WIZARD_COMPONENT_KEY_INPUT));
        obj.isInvisible = scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_INVISIBLE);
        continue; /* Loop/switch isn't completed */
_L6:
        obj = new WizardCheckBoxComponent();
        ((WizardCheckBoxComponent) (obj)).setWizard(wizard);
        ((WizardCheckBoxComponent) (obj)).setText(scipropertybag.getStrProp(sclibConstants.WIZARD_COMPONENT_KEY_STRING));
        ((WizardCheckBoxComponent) (obj)).setInput(scipropertybag.getIntProp(sclibConstants.WIZARD_COMPONENT_KEY_INPUT));
        obj.isInvisible = scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_INVISIBLE);
        continue; /* Loop/switch isn't completed */
_L7:
        obj = new WizardInputComponent();
        ((WizardInputComponent) (obj)).setWizard(wizard);
        com.sonos.sclib.SCIWizard.WizInputSelection wizinputselection1 = com.sonos.sclib.SCIWizard.WizInputSelection.swigToEnum(scipropertybag.getIntProp(sclibConstants.WIZARD_COMPONENT_KEY_INPUT));
        ((WizardInputComponent) (obj)).setStringInput(wizard.getWizard().getStringInput(wizinputselection1));
        ((WizardInputComponent) (obj)).setHint(scipropertybag.getStrProp(sclibConstants.WIZARD_COMPONENT_KEY_STRING));
        obj.isInvisible = scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_INVISIBLE);
        continue; /* Loop/switch isn't completed */
_L8:
        obj = new WizardSecureInputComponent();
        com.sonos.sclib.SCIWizard.WizInputSelection wizinputselection = com.sonos.sclib.SCIWizard.WizInputSelection.swigToEnum(scipropertybag.getIntProp(sclibConstants.WIZARD_COMPONENT_KEY_INPUT));
        ((WizardSecureInputComponent) (obj)).setStringInput(wizard.getWizard().getStringInput(wizinputselection));
        ((WizardSecureInputComponent) (obj)).setShowPasswordCheckBoxVisible(scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_SHOW_SECURE_TEXT));
        ((WizardSecureInputComponent) (obj)).setCheckboxText(scipropertybag.getStrProp(sclibConstants.WIZARD_COMPONENT_KEY_SHOW_SECURE_TEXT_STRING));
        obj.isInvisible = scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_INVISIBLE);
        continue; /* Loop/switch isn't completed */
_L9:
        obj = new WizardSpinnerComponent();
        continue; /* Loop/switch isn't completed */
_L10:
        obj = new WizardListComponent();
        SCIStringArray scistringarray = scipropertybag.getStrArrayProp(sclibConstants.WIZARD_COMPONENT_KEY_LIST_KEY);
        ArrayList arraylist = new ArrayList();
        for(int i = 0; (long)i < scistringarray.size(); i++)
            arraylist.add(scistringarray.getAt(i));

        ((WizardListComponent) (obj)).setItems(arraylist);
        if(scipropertybag.doesPropExist(sclibConstants.WIZARD_COMPONENT_KEY_INPUT))
            ((WizardListComponent) (obj)).setInput(com.sonos.sclib.SCIWizard.WizInputSelection.swigToEnum(scipropertybag.getIntProp(sclibConstants.WIZARD_COMPONENT_KEY_INPUT)));
        else
            ((WizardListComponent) (obj)).setInput(com.sonos.sclib.SCIWizard.WizInputSelection.WIZ_INPUT_1);
        ((WizardListComponent) (obj)).setInitialSelection(wizard.getWizard().getInputIndex(((WizardListComponent) (obj)).getInput()));
        obj.isInvisible = scipropertybag.getBoolProp(sclibConstants.WIZARD_COMPONENT_KEY_INVISIBLE);
        if(true) goto _L12; else goto _L11
_L11:
    }

    public abstract View getComponentView(ViewGroup viewgroup);

    public boolean isInvisible()
    {
        return isInvisible;
    }

    public abstract String logString();

    protected boolean isInvisible;
}

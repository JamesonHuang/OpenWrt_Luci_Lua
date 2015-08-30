// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import android.content.DialogInterface;
import com.sonos.acr.SonosActivity;
import com.sonos.acr.wizards.SonosWizardActivity;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class DisplayMenuPopupAction extends UIAction
    implements android.content.DialogInterface.OnClickListener
{

    public DisplayMenuPopupAction(SonosActivity sonosactivity, String s, SCIStringArray scistringarray, int i, String s1, boolean flag)
    {
        super(sonosactivity);
        m_MenuTitle = s;
        m_Instructions = s1;
        m_ForceVerticalLayout = flag;
        int j = (int)scistringarray.size();
        m_MenuItems = new String[j];
        for(int k = 0; k < j; k++)
            m_MenuItems[k] = scistringarray.getAt(k);

        if(!$assertionsDisabled && i >= j)
        {
            throw new AssertionError();
        } else
        {
            m_CancelItemIndex = i;
            return;
        }
    }

    private void setItemSelected(int i)
    {
        SCIPropertyBag scipropertybag = m_ActionContext.getPropertyBag();
        if(!$assertionsDisabled && scipropertybag == null)
        {
            throw new AssertionError();
        } else
        {
            scipropertybag.setIntProp(sclib.SCACTN_INTPROP_MENU_SELECTED_INDEX, i);
            return;
        }
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        int j = -1;
        if(m_MenuItems.length != 2 || m_ForceVerticalLayout) goto _L2; else goto _L1
_L1:
        if(-1 != i) goto _L4; else goto _L3
_L3:
        j = (1 + m_CancelItemIndex) % 2;
_L6:
        setItemSelected(j);
        m_ActionContext.actionHasCompleted(this);
        return;
_L4:
        if(-2 == i)
            j = m_CancelItemIndex;
        continue; /* Loop/switch isn't completed */
_L2:
        if(m_MenuItems.length == 3 && !m_ForceVerticalLayout)
        {
            if(-1 == i)
            {
                if(m_CancelItemIndex == 0)
                    j = 1;
                else
                    j = 0;
            } else
            if(-3 == i)
                if(m_CancelItemIndex < 2)
                    j = 2;
                else
                    j = 1;
        } else
        {
            j = i;
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public SCActionCompletionStatus perform(SCIActionContext sciactioncontext)
    {
        SCIPropertyBag scipropertybag;
        m_ActionContext = sciactioncontext;
        scipropertybag = sciactioncontext.getPropertyBag();
        if(!(currentContext instanceof SonosWizardActivity) || ((SonosWizardActivity)currentContext).isDebugWizard()) goto _L2; else goto _L1
_L1:
        SCActionCompletionStatus scactioncompletionstatus;
        if(scipropertybag != null)
            scipropertybag.setBoolProp(sclib.SCACTN_BOOLPROP_ACTION_NOT_PERFORMED, true);
        scactioncompletionstatus = SCActionCompletionStatus.DONE_WITH_ACTION;
_L4:
        return scactioncompletionstatus;
_L2:
        android.app.AlertDialog.Builder builder;
        builder = new android.app.AlertDialog.Builder(currentContext);
        builder.setCancelable(true);
        if(scipropertybag != null && scipropertybag.doesPropExist("iconId"))
            builder.setIcon(scipropertybag.getIntProp("iconId"));
        if(m_MenuItems.length != 2 || m_ForceVerticalLayout)
            break; /* Loop/switch isn't completed */
        if(m_CancelItemIndex == 0)
        {
            if(m_MenuItems[0].length() == 0)
                builder.setNegativeButton(0x7f0c004f, this);
            else
                builder.setNegativeButton(m_MenuItems[0], this);
            if(m_MenuItems[1].length() == 0)
                builder.setPositiveButton(0x7f0c0051, this);
            else
                builder.setPositiveButton(m_MenuItems[1], this);
        } else
        {
            if(m_MenuItems[1].length() == 0)
                builder.setPositiveButton(0x7f0c0051, this);
            else
                builder.setPositiveButton(m_MenuItems[0], this);
            if(m_MenuItems[0].length() == 0)
                builder.setNegativeButton(0x7f0c004f, this);
            else
                builder.setNegativeButton(m_MenuItems[1], this);
        }
        if(m_Instructions != null && m_Instructions.length() > 0)
        {
            builder.setTitle(m_MenuTitle);
            builder.setMessage(m_Instructions);
        } else
        {
            builder.setMessage(m_MenuTitle);
        }
_L5:
        builder.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialoginterface)
            {
                setItemSelected(m_CancelItemIndex);
                m_ActionContext.actionHasCompleted(DisplayMenuPopupAction.this);
            }

            final DisplayMenuPopupAction this$0;

            
            {
                this$0 = DisplayMenuPopupAction.this;
                super();
            }
        }
);
        builder.show();
        scactioncompletionstatus = SCActionCompletionStatus.WAIT_FOR_CALLBACK;
        if(true) goto _L4; else goto _L3
_L3:
        if(m_MenuItems.length == 1 && m_CancelItemIndex == -1)
        {
            if(m_Instructions != null && m_Instructions.length() > 0)
            {
                builder.setTitle(m_MenuTitle);
                builder.setMessage(m_Instructions);
            } else
            {
                builder.setMessage(m_MenuTitle);
            }
            builder.setPositiveButton(m_MenuItems[0], this);
        } else
        {
            String as[] = new String[-1 + m_MenuItems.length];
            for(int i = 0; i < m_CancelItemIndex; i++)
                as[i] = m_MenuItems[i];

            for(int j = m_CancelItemIndex; j < -1 + m_MenuItems.length; j++)
                as[j] = m_MenuItems[j + 1];

            if(as.length == 2 && !m_ForceVerticalLayout)
            {
                builder.setPositiveButton(as[0], this);
                builder.setNeutralButton(as[1], this);
                if(m_Instructions != null && m_Instructions.length() > 0)
                {
                    builder.setTitle(m_MenuTitle);
                    builder.setMessage(m_Instructions);
                } else
                {
                    builder.setMessage(m_MenuTitle);
                }
            } else
            {
                builder.setItems(as, this);
                builder.setTitle(m_MenuTitle);
            }
        }
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    static final boolean $assertionsDisabled;
    SCIActionContext m_ActionContext;
    int m_CancelItemIndex;
    boolean m_ForceVerticalLayout;
    String m_Instructions;
    String m_MenuItems[];
    String m_MenuTitle;

    static 
    {
        boolean flag;
        if(!com/sonos/acr/uiactions/DisplayMenuPopupAction.desiredAssertionStatus())
            flag = true;
        else
            flag = false;
        $assertionsDisabled = flag;
    }

}

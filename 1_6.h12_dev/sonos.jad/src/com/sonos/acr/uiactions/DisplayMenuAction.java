// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.sonos.acr.uiactions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.sonos.acr.SonosActivity;
import com.sonos.sclib.*;

// Referenced classes of package com.sonos.acr.uiactions:
//            UIAction

public class DisplayMenuAction extends UIAction
{

    public DisplayMenuAction(SonosActivity sonosactivity, String s, String s1, String s2, SCIStringArray scistringarray, int i)
    {
        super(sonosactivity);
        m_title = s1;
        m_initialSelection = i;
        m_items = new String[(int)scistringarray.size()];
        for(int j = 0; (long)j < scistringarray.size(); j++)
            m_items[j] = scistringarray.getAt(j);

    }

    public SCActionCompletionStatus perform(final SCIActionContext pActionCtxt)
    {
        final SCIPropertyBag propertyBag = pActionCtxt.getPropertyBag();
        (new android.app.AlertDialog.Builder(currentContext)).setTitle(m_title).setSingleChoiceItems(m_items, m_initialSelection, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                m_initialSelection = i;
            }

            final DisplayMenuAction this$0;

            
            {
                this$0 = DisplayMenuAction.this;
                super();
            }
        }
).setNegativeButton(0x1040000, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                propertyBag.setBoolProp(sclibConstants.SCACTN_BOOLPROP_MENU_CANCEL, true);
                pActionCtxt.actionHasCompleted(DisplayMenuAction.this);
            }

            final DisplayMenuAction this$0;
            final SCIActionContext val$pActionCtxt;
            final SCIPropertyBag val$propertyBag;

            
            {
                this$0 = DisplayMenuAction.this;
                propertyBag = scipropertybag;
                pActionCtxt = sciactioncontext;
                super();
            }
        }
).setPositiveButton(0x104000a, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                propertyBag.setIntProp(sclibConstants.SCACTN_INTPROP_MENU_SELECTED_INDEX, m_initialSelection);
                pActionCtxt.actionHasCompleted(DisplayMenuAction.this);
            }

            final DisplayMenuAction this$0;
            final SCIActionContext val$pActionCtxt;
            final SCIPropertyBag val$propertyBag;

            
            {
                this$0 = DisplayMenuAction.this;
                propertyBag = scipropertybag;
                pActionCtxt = sciactioncontext;
                super();
            }
        }
).setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialoginterface)
            {
                propertyBag.setBoolProp(sclibConstants.SCACTN_BOOLPROP_MENU_CANCEL, true);
                pActionCtxt.actionHasCompleted(DisplayMenuAction.this);
            }

            final DisplayMenuAction this$0;
            final SCIActionContext val$pActionCtxt;
            final SCIPropertyBag val$propertyBag;

            
            {
                this$0 = DisplayMenuAction.this;
                propertyBag = scipropertybag;
                pActionCtxt = sciactioncontext;
                super();
            }
        }
).create().show();
        return SCActionCompletionStatus.WAIT_FOR_CALLBACK;
    }

    private int m_initialSelection;
    private String m_items[];
    private String m_title;



/*
    static int access$002(DisplayMenuAction displaymenuaction, int i)
    {
        displaymenuaction.m_initialSelection = i;
        return i;
    }

*/
}

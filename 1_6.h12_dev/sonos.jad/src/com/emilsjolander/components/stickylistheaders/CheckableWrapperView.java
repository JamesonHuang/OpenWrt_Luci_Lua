// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.emilsjolander.components.stickylistheaders;

import android.content.Context;
import android.widget.Checkable;

// Referenced classes of package com.emilsjolander.components.stickylistheaders:
//            WrapperView

class CheckableWrapperView extends WrapperView
    implements Checkable
{

    public CheckableWrapperView(Context context)
    {
        super(context);
    }

    public boolean isChecked()
    {
        return ((Checkable)mItem).isChecked();
    }

    public void setChecked(boolean flag)
    {
        ((Checkable)mItem).setChecked(flag);
    }

    public void toggle()
    {
        boolean flag;
        if(!isChecked())
            flag = true;
        else
            flag = false;
        setChecked(flag);
    }
}

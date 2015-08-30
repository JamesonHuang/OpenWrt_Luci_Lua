// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.joran.spi;

import ch.qos.logback.core.joran.event.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package ch.qos.logback.core.joran.spi:
//            Interpreter, InterpretationContext

public class EventPlayer
{

    public EventPlayer(Interpreter interpreter1)
    {
        interpreter = interpreter1;
    }

    public void addEventsDynamically(List list, int i)
    {
        eventList.addAll(i + currentIndex, list);
    }

    public List getCopyOfPlayerEventList()
    {
        return new ArrayList(eventList);
    }

    public void play(List list)
    {
        eventList = list;
        for(currentIndex = 0; currentIndex < eventList.size(); currentIndex = 1 + currentIndex)
        {
            SaxEvent saxevent = (SaxEvent)eventList.get(currentIndex);
            if(saxevent instanceof StartEvent)
            {
                interpreter.startElement((StartEvent)saxevent);
                interpreter.getInterpretationContext().fireInPlay(saxevent);
            }
            if(saxevent instanceof BodyEvent)
            {
                interpreter.getInterpretationContext().fireInPlay(saxevent);
                interpreter.characters((BodyEvent)saxevent);
            }
            if(saxevent instanceof EndEvent)
            {
                interpreter.getInterpretationContext().fireInPlay(saxevent);
                interpreter.endElement((EndEvent)saxevent);
            }
        }

    }

    int currentIndex;
    List eventList;
    final Interpreter interpreter;
}

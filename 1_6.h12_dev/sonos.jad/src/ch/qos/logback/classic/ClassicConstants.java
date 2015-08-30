// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.classic;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class ClassicConstants
{

    public ClassicConstants()
    {
    }

    public static final int DEFAULT_MAX_CALLEDER_DATA_DEPTH = 8;
    public static final String FINALIZE_SESSION = "FINALIZE_SESSION";
    public static final Marker FINALIZE_SESSION_MARKER = MarkerFactory.getMarker("FINALIZE_SESSION");
    public static final String LOGBACK_CONTEXT_SELECTOR = "logback.ContextSelector";
    public static final int MAX_DOTS = 16;

}

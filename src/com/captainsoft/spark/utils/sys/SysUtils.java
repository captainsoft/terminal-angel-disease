/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.utils.sys;

import java.util.ArrayList;
import java.util.List;

/**
 * System utilities.
 *
 * @author mathias fringes
 */
public final class SysUtils {

    // constructors

    private SysUtils() {
        super();
    }

    // public

    public static String getInfoString() {
        StringBuilder sb = new StringBuilder();
        for (String s : props()) {
            sb.append(prop(s)).append("\r\n");
        }
        //
        return sb.toString();
    }

    public static String[] getParameters() {
        List<String> props = new ArrayList<String>();
        //
        for (String s : props()) {
            props.add(s + "=" + prop(s));
        }
        //
        return props.toArray(new String[1]);
    }

    // private

    private static String prop(String name) {
        return System.getProperty(name);
    }

    private static String[] props() {
        return new String[]{
                "java.runtime.name",
                "java.runtime.version",
                "java.vm.vendor",
                "java.vm.name",
                "os.name",
                "os.arch",
                "sun.desktop",
                "user.country",
                "user.language"
        };
    }

}

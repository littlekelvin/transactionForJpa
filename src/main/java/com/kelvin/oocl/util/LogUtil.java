package com.kelvin.oocl.util;

public class LogUtil {
    public static void info(Class clazz, String msg){
        System.out.println("[info] "+ clazz.getSimpleName()+ " - " + msg);
    }
}

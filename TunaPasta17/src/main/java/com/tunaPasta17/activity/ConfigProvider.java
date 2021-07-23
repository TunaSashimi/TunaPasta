package com.tunaPasta17.activity;

import android.app.Application;
import android.content.Context;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ConfigProvider {
    private static Context sContext;

    private static String packageName;

    /**
     * 通过反射获取ApplicationContext
     *
     * @return
     */
    public static Context getContext() {
        if (sContext == null) {
            try {
                final Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
                final Method currentActivityThread = activityThreadClass.getDeclaredMethod("currentActivityThread");
                final Object activityThread = currentActivityThread.invoke(null);
                final Method getApplication = activityThreadClass.getDeclaredMethod("getApplication");
                final Application application = (Application) getApplication.invoke(activityThread);
                sContext = application.getApplicationContext();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sContext;
    }

    /**
     * 通过反射获取包名
     *
     * @return
     */
    private static String getPackageName() {
        if (packageName == null) {
            try {
                final Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
                final Method currentPackageName = activityThreadClass.getDeclaredMethod("currentPackageName");
                packageName = (String) currentPackageName.invoke(null);
            } catch (Exception e) {
                packageName = getContext().getPackageName();
            }
        }

        return packageName;
    }

    /**
     * 获取具体的域值
     *
     * @return
     */
    public static String getBuildConfigStringValue(String fieldName) {
        try {
            Class<?> clazz = Class.forName(getPackageName() + ".BuildConfig");
            Field field = clazz.getField(fieldName);
            return (String) field.get(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean getBuildConfigBooleanValue(String fieldName) {
        try {
            Class<?> clazz = Class.forName(getPackageName() + ".BuildConfig");
            Field field = clazz.getField(fieldName);
            return (boolean) field.get(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Integer getBuildConfigIntegerValue(String fieldName) {
        try {
            Class<?> clazz = Class.forName(getPackageName() + ".BuildConfig");
            Field field = clazz.getField(fieldName);
            return (Integer) field.get(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Double getBuildConfigDoubleValue(String fieldName) {
        try {
            Class<?> clazz = Class.forName(getPackageName() + ".BuildConfig");
            Field field = clazz.getField(fieldName);
            return (Double) field.get(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return 0d;
    }

}


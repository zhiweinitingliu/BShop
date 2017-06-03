package com.jybd.bshop.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/*
 * 应用所有的偏好设置管理类
 */
public class SharedPreferencesUtil {

    /**
     * 添加一个偏好
     *
     * @param context context
     * @param key     key
     * @param object  String,int,boolean,float,long,and so on
     */
    public static void put(Context context, String key, Object object) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    /**
     * 获取一个偏好
     *
     * @param context       context
     * @param key           SharedPreferences key
     * @param defaultObject 默认值，注意：类型必须和该偏好的类型一致，
     * @param <T>           偏好类型
     * @return 偏好的值，如果没有记录过，返回defaultObject。
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(Context context, String key, T defaultObject) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Object value = defaultObject;
        if (defaultObject == null || defaultObject instanceof String) {
            value = sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            value = sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            value = sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            value = sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            value = sp.getLong(key, (Long) defaultObject);
        }
        T result;
        try {
            result = (T) value;
        } catch (Exception e) {
            result = defaultObject;
        }
        return result;
    }

    //删除一个偏好
    public static void remove(Context context, String key) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }
}

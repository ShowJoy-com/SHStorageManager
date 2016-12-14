package com.showjoy.android.storage.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * SharedPreferences 操作类
 * Created by lufei on 4/12/16.
 */
public class SharedPreferencesManager {

    SharedPreferences sharedPreferences;

    static Context sAppContext;

    static Map<String, SharedPreferencesManager> sharedPreferencesManagerMap;

    public static void init(Context appContext) {
        sAppContext = appContext;
        sharedPreferencesManagerMap = new HashMap<>();
    }

    public static synchronized SharedPreferencesManager getInstance(String filename) {
        if (sharedPreferencesManagerMap.containsKey(filename)) {
            return sharedPreferencesManagerMap.get(filename);
        }
        SharedPreferencesManager instance = new SharedPreferencesManager(sAppContext, filename);
        sharedPreferencesManagerMap.put(filename, instance);
        return instance;
    }

    private SharedPreferencesManager(Context appContext, String filename) {
        sharedPreferences = appContext.getSharedPreferences(filename, Activity.MODE_PRIVATE);
    }

    /**
     * 清除数据
     */
    public void clear() {
        sharedPreferences.edit().clear().commit();
    }

    public void remove(String key) {
        sharedPreferences.edit().remove(key).commit();
    }

    /**
     * 异步操作，一般调用这个即可
     *
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String defaultValue) {
        try {
            return sharedPreferences.getString(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public String getString(String key) {
        try {
            return sharedPreferences.getString(key, "");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 异步操作，一般调用这个即可
     *
     * @param key
     * @param value
     */
    public void put(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        try {
            return sharedPreferences.getBoolean(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key) {
        try {
            return sharedPreferences.getBoolean(key, false);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 异步操作，一般调用这个即可
     *
     * @param key
     * @param value
     */
    public void put(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key, int defaultValue) {
        try {
            return sharedPreferences.getInt(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public int getInt(String key) {
        try {
            return sharedPreferences.getInt(key, 0);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 异步操作，一般调用这个即可
     *
     * @param key
     * @param value
     */
    public void put(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key, long defaultValue) {
        try {
            return sharedPreferences.getLong(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public long getLong(String key) {
        try {
            return sharedPreferences.getLong(key, 0l);
        } catch (Exception e) {
            return 0l;
        }
    }

    /**
     * 异步操作，一般调用这个即可
     *
     * @param key
     * @param value
     */
    public void put(String key, float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public float getFloat(String key, float defaultValue) {
        try {
            return sharedPreferences.getFloat(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public float getFloat(String key) {
        try {
            return sharedPreferences.getFloat(key, 0f);
        } catch (Exception e) {
            return 0f;
        }
    }

    /**
     * 同步操作
     *
     * @param key
     * @param value
     */
    public boolean commitString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }
}

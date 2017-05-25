package com.showjoy.android.storage;

import android.content.Context;
import android.text.TextUtils;
import android.util.LruCache;

import com.showjoy.android.storage.util.JsonUtils;
import com.showjoy.android.storage.util.SharedPreferencesManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存储管理
 * Created by lufei on 6/24/16.
 */
public class SHStorageManager {

    private static Context appContext;

    private static Map<String, LruCache<String, Object>> lruCacheMap;

    private static int sMaxSize = 10;

    public static void init(Context context, int maxSize) {
        appContext = context;
        SharedPreferencesManager.init(appContext);
        lruCacheMap = new HashMap<>();
        sMaxSize = maxSize;
    }

    public static void init(Context context) {
        appContext = context;
        SharedPreferencesManager.init(appContext);
        lruCacheMap = new HashMap<>();
    }

    /**
     * 清除缓存
     */
    public static void clearCache() {
        for (LruCache<String, Object> objectLruCache : lruCacheMap.values()) {
            objectLruCache.evictAll();
        }
    }

    /**
     * 清除某个模块的缓存
     */
    public static void clearCache(String module) {
        if (lruCacheMap.containsKey(module)) {
            lruCacheMap.get(module).evictAll();
        }
    }

    public static void removeFromDisk(String module) {
        SharedPreferencesManager.getInstance(module).clear();
    }

    public static void removeFromCache(String module, String key) {
        if (lruCacheMap.containsKey(module)) {
            lruCacheMap.get(module).remove(key);
        }
    }

    public static void removeFromDisk(String module, String key) {
        SharedPreferencesManager.getInstance(module).remove(key);
    }

    /**
     * 存储
     * @param module
     * @param key
     * @param value
     */
    public static void putToCache(String module, String key, Object value) {
        if (!lruCacheMap.containsKey(module)) {
            if (null == lruCacheMap.get(module)) {
                lruCacheMap.put(module, new LruCache<String, Object>(sMaxSize));
            }
        }
        if (null != value) {
            lruCacheMap.get(module).put(key, value);
        }
    }

    /**
     * 存储
     * @param module
     * @param key
     * @param value
     */
    public static void putToCache(String module, String key, int value) {
        if (!lruCacheMap.containsKey(module)) {
            if (null == lruCacheMap.get(module)) {
                lruCacheMap.put(module, new LruCache<String, Object>(sMaxSize));
            }
        }
        lruCacheMap.get(module).put(key, value);

    }
    /**
     * 存储
     * @param module
     * @param key
     * @param value
     */
    public static void putToCache(String module, String key, long value) {
        if (!lruCacheMap.containsKey(module)) {
            if (null == lruCacheMap.get(module)) {
                lruCacheMap.put(module, new LruCache<String, Object>(sMaxSize));
            }
        }
        lruCacheMap.get(module).put(key, value);

    }
    /**
     * 存储
     * @param module
     * @param key
     * @param value
     */
    public static void putToCache(String module, String key, boolean value) {
        if (!lruCacheMap.containsKey(module)) {
            if (null == lruCacheMap.get(module)) {
                lruCacheMap.put(module, new LruCache<String, Object>(sMaxSize));
            }
        }
        lruCacheMap.get(module).put(key, value);

    }

    /**
     * 存储
     * @param module
     * @param key
     * @param value
     */
    public static void putToCache(String module, String key, float value) {
        if (!lruCacheMap.containsKey(module)) {
            if (null == lruCacheMap.get(module)) {
                lruCacheMap.put(module, new LruCache<String, Object>(sMaxSize));
            }
        }
        lruCacheMap.get(module).put(key, value);

    }

    //////Disk store


    /**
     * 存储
     * @param module
     * @param key
     * @param value
     */
    public static void putToDisk(String module, String key, String value) {
        putToCache(module, key, value);
        SharedPreferencesManager.getInstance(module).put(key, value);
    }

    /**
     * 存储
     * @param module
     * @param key
     * @param value
     */
    public static void putToDisk(String module, String key, int value) {
        putToCache(module, key, value);
        SharedPreferencesManager.getInstance(module).put(key, value);
    }
    /**
     * 存储
     * @param module
     * @param key
     * @param value
     */
    public static void putToDisk(String module, String key, long value) {
        putToCache(module, key, value);
        SharedPreferencesManager.getInstance(module).put(key, value);
    }
    /**
     * 存储
     * @param module
     * @param key
     * @param value
     */
    public static void putToDisk(String module, String key, boolean value) {
        putToCache(module, key, value);
        SharedPreferencesManager.getInstance(module).put(key, value);
    }

    /**
     * 存储
     * @param module
     * @param key
     * @param value
     */
    public static void putToDisk(String module, String key, float value) {
        putToCache(module, key, value);
        SharedPreferencesManager.getInstance(module).put(key, value);
    }

    /**
     * 存储
     * @param module
     * @param key
     * @param value
     */
    public static void putToDisk(String module, String key, Object value) {
        putToCache(module, key, value);
        SharedPreferencesManager.getInstance(module).put(key, JsonUtils.toJson(value));
    }

    /////Get

    public static List<String> getAllKeys(String module) {
        return SharedPreferencesManager.getInstance(module).getAllKeys();
    }

    /**
     * 获取
     * @param module
     * @param key
     * @param tClass
     */
    public static <T> T get(String module, String key, Class<T> tClass) {
        if (TextUtils.isEmpty(module)) {
            return null;
        }if (TextUtils.isEmpty(key)) {
            return null;
        }
        Object v = getFromCache(module, key);
        if (null == v) {
            String s = SharedPreferencesManager.getInstance(module).getString(key, "");
            if (TextUtils.isEmpty(s)) {
                return null;
            }
            T value = JsonUtils.parseObject(s, tClass);
            putToCache(module, key, value);
            return value;
        }
        try {
            return (T) v;
        }catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取
     * @param module
     * @param key
     * @param defaultValue
     */
    public static String get(String module, String key, String defaultValue) {
        if (TextUtils.isEmpty(module)) {
            return defaultValue;
        }if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        Object v = getFromCache(module, key);
        if (null == v) {
            if (null == defaultValue) {
                defaultValue = "";
            }
            String s = SharedPreferencesManager.getInstance(module).getString(key, defaultValue);
            putToCache(module, key, s);
            return s;
        }
        if (v instanceof String) {
            return (String) v;
        }
        return defaultValue;
    }

    /**
     * 获取
     * @param module
     * @param key
     * @param defaultValue
     */
    public static int get(String module, String key, int defaultValue) {
        if (TextUtils.isEmpty(module)) {
            return defaultValue;
        }if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        Object v = getFromCache(module, key);
        if (null == v) {
            int i = SharedPreferencesManager.getInstance(module).getInt(key, defaultValue);
            putToCache(module, key, i);
            return i;
        }
        if (v instanceof Integer) {
            return (int) v;
        }
        return defaultValue;
    }
    /**
     * 存储
     * @param module
     * @param key
     * @param defaultValue
     */
    public static long get(String module, String key, long defaultValue) {
        if (TextUtils.isEmpty(module)) {
            return defaultValue;
        }if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        Object v = getFromCache(module, key);
        if (null == v) {
            long l = SharedPreferencesManager.getInstance(module).getLong(key, defaultValue);
            putToCache(module, key, l);
            return l;
        }
        if (v instanceof Long) {
            return (long) v;
        }
        return defaultValue;
    }
    /**
     * 获取
     * @param module
     * @param key
     * @param defaultValue
     */
    public static boolean get(String module, String key, boolean defaultValue) {
        if (TextUtils.isEmpty(module)) {
            return defaultValue;
        }if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        Object v = getFromCache(module, key);
        if (null == v) {
            boolean b = SharedPreferencesManager.getInstance(module).getBoolean(key, defaultValue);
            putToCache(module, key, b);
            return b;
        }
        if (v instanceof Boolean) {
            return (boolean) v;
        }
        return defaultValue;
    }

    /**
     * 获取
     * @param module
     * @param key
     * @param defaultValue
     */
    public static float get(String module, String key, float defaultValue) {
        if (TextUtils.isEmpty(module)) {
            return defaultValue;
        }if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        Object v = getFromCache(module, key);
        if (null == v) {
            Float f = SharedPreferencesManager.getInstance(module).getFloat(key, defaultValue);
            putToCache(module, key, f);
            return f;
        }
        if (v instanceof Float) {
            return (float) v;
        }
        return defaultValue;
    }

    private static Object getFromCache(String module, String key) {
        if (null == lruCacheMap) {
            return null;
        }
        if (!lruCacheMap.containsKey(module)) {
            return null;
        }
        if (null == lruCacheMap.get(module)) {
            return null;
        }
        return lruCacheMap.get(module).get(key);
    }
}

package com.showjoy.android.storage.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * Json解析类
 */
public class JsonUtils {

    /**
     * 解析对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            return null;
        } 
    }

    /**
     * 解析数组
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        try {
            return JSON.parseArray(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解析数组
     * @param data
     * @return
     */
    public static String toJson(Object data) {
        try {
            return JSONArray.toJSONString(data);
        } catch (Exception e) {
            return null;
        }
    }
}

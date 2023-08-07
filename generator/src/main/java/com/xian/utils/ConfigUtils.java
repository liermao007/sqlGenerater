package com.xian.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lgx
 */
public class ConfigUtils {

    public static Map<String, String> configs = new HashMap<>();

    public static boolean save(String key, String config) {
        configs.put(key, config);
        return true;
    }

    public static String getConfigByKey(String key) {
        return configs.getOrDefault(key, "");
    }
}

package com.michael.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 用于读取 db.properties 配置文件
 */
public class ConfigManager {
    private static Properties props = null;

    static {
        InputStream is = null;
        is = ConfigManager.class.getClassLoader().getResourceAsStream(
                "config/db.properties");
        if (is == null)
            throw new RuntimeException("找不到数据库参数配置文件!");
        props = new Properties();
        try {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("数据库配置参数加载错误!", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}
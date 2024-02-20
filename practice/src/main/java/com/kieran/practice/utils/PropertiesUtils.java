package com.kieran.practice.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * 配置文件 工具类
 */
public final class PropertiesUtils {

    /**
     * 私有化
     */
    private PropertiesUtils() {
    }

    /**
     * 获取任意路径的配置文件
     *
     * @param fileName 配置文件名称
     * @return 配置文件
     */
    public static Properties getProperties(String fileName) {
        try {
            // 根据当前文件的位置，获取相对路径
            ClassLoader classLoader = PropertiesUtils.class.getClassLoader();
            URL resource = classLoader.getResource(fileName);
            if (null == resource) {
                return null;
            } else {
                String path = resource.getPath();
                Properties properties = new Properties();
                BufferedReader br = new BufferedReader(new FileReader(path));
                properties.load(br);
                return properties;
            }
        } catch (IOException e) {
            return null;
        }

    }

    /**
     * 读取配置项
     *
     * @param properties 配置文件
     * @param key 配置项
     * @return 值
     */
    public static String getProperty(Properties properties, String key) {
        return properties.getProperty(key);
    }
}

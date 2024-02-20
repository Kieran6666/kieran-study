package com.kieran.study.redis.jedis;

import com.kieran.study.utils.PropertiesUtils;
import java.util.Properties;

/**
 * Jedis Base类
 */
public class JedisBase {

    /**
     * 获取配置文件
     */
    private static final Properties properties = PropertiesUtils.getProperties("application-dev.properties");


    public static String getHost() {
        return properties.getProperty("spring.redis.host");
    }

    public static int getPort() {
        return Integer.parseInt(properties.getProperty("spring.redis.port"));
    }
}

package com.kieran.study.redis.jedis;

import redis.clients.jedis.Jedis;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 重复数据不会被添加
 */
public class TestHash extends JedisBase {

    public static void main(String[] args) {
        Jedis jedis = new Jedis(getHost(), getPort());
        Map<String, String> map = new LinkedHashMap<>();
        map.put("two", "2");
        map.put("one", "1");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");

        jedis.hset("j:hash", map);
        Map<String, String> stringStringMap = jedis.hgetAll("j:hash");
        System.err.println(stringStringMap.toString());

        jedis.close();
    }

}

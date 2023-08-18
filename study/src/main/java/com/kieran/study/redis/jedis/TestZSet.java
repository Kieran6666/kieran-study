package com.kieran.study.redis.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * 重复数据无法添加，且会根据score进行排序添加，从小到大
 */
public class TestZSet extends JedisBase {
    public static void main(String[] args) {
        Jedis jedis = new Jedis(getHost(), getPort());

        jedis.zadd("j:zset", 100d, "50");
        jedis.zadd("j:zset", 90d, "90");
        jedis.zadd("j:zset", 80d, "100");

        List<String> zrange = jedis.zrange("j:zset", 0, -1);
        System.err.println(zrange.toString());

        List<String> zrevRange = jedis.zrevrange("j:zset", 0, -1);
        System.err.println(zrevRange.toString());

        jedis.close();
    }
}

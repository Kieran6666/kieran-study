package com.kieran.study.redis.jedis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 对于数字会自动排序
 */
public class TestSet extends JedisBase {

    public static void main(String[] args) {
        Jedis jedis = new Jedis(getHost(), getPort());
        jedis.sadd("j:set", "1");
        jedis.sadd("j:set", "3");
        jedis.sadd("j:set", "2");

        Set<String> smembers = jedis.smembers("j:set");
        System.err.println(smembers.toString());

        jedis.close();
    }
}

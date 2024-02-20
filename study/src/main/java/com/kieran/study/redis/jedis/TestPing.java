package com.kieran.study.redis.jedis;

import redis.clients.jedis.Jedis;

/**
 * Ping
 */
public class TestPing {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        System.err.println(jedis.ping());
        jedis.close();
    }
}

package com.kieran.study.redis.jedis;

import redis.clients.jedis.Jedis;

/**
 * 只有 0 1
 * 场景: 某人的365上班打卡
 */
public class TestBitMap extends JedisBase {

    public static void main(String[] args) {
        Jedis jedis = new Jedis(getHost(), getPort());

        // 重复添加，值会覆盖
        jedis.setbit("j:bit", 20230101L, true);
        jedis.setbit("j:bit", 20230102L, false);
        jedis.setbit("j:bit", 20230103L, false);
        jedis.setbit("j:bit", 20230104L, false);

        // 查询不到会给false
        boolean getbit = jedis.getbit("j:bit", 20230104L);
        System.err.println(getbit);

        // 只统计 true的数量
        long bitcount = jedis.bitcount("j:bit");
        System.err.println(bitcount);

        jedis.close();
    }
}

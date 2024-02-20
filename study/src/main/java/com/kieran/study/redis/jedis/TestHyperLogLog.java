package com.kieran.study.redis.jedis;

import redis.clients.jedis.Jedis;

/**
 * 场景：统计页面的UV， 本质上是一种算法，不是数据类型，且无法查询具体有哪些元素
 */
public class TestHyperLogLog extends JedisBase {
    public static void main(String[] args) {
        Jedis jedis = new Jedis(getHost(), getPort());

        jedis.pfadd("j:hyperloglog", "1", "2");
        jedis.pfadd("j:hyperloglog", "2", "3");
        jedis.pfadd("j:hyperloglog", "4", "3-");

        long pfcount = jedis.pfcount("j:hyperloglog");
        System.err.println(pfcount);

        jedis.close();
    }
}

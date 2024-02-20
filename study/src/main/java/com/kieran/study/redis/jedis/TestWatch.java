package com.kieran.study.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * watch正常流程，使用乐观锁，不上锁，只有在更新的时候会检测watch的项目版本是否一致，不一致则放弃当前更新
 */
public class TestWatch extends JedisBase {

    public static void main(String[] args) {
        Jedis jedis = new Jedis(getHost(), getPort());

        jedis.set("money", "1000");
        jedis.set("out", "0");

        jedis.watch("money");
        Transaction multi = jedis.multi();
        multi.decrBy("money", 100);
        multi.incrBy("out", 100);

        multi.exec();


        System.err.println(jedis.get("money"));
        System.err.println(jedis.get("out"));

        jedis.close();
    }
}

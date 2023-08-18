package com.kieran.study.redis.jedis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@Slf4j
public class TestString extends JedisBase {

    public static void main(String[] args) {
        Jedis jedis = new Jedis(getHost(), getPort());
        String msg = jedis.set("j:name", "kieran");
        log.info("结果: {}", msg);

        String name = jedis.get("j:name");
        System.err.println(name);

        jedis.close();
    }
}

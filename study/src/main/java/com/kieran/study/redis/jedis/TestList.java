package com.kieran.study.redis.jedis;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class TestList extends JedisBase {

    public static void main(String[] args) {
        Jedis jedis = new Jedis(getHost(), getPort());

        List<Object> list = new ArrayList<>();

        list.add("1");
        list.add(2);
        list.add(true);

        for (Object object : list) {
            jedis.lpush("j:list", object.toString());
        }

        List<String> lRange = jedis.lrange("j:list", 0, -1);
        System.err.println(lRange.toString());

        jedis.close();
    }



}

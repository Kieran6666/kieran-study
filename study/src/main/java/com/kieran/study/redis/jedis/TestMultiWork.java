package com.kieran.study.redis.jedis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 事物
 */
public class TestMultiWork extends JedisBase {

    public static void main(String[] args) {
        Jedis jedis = new Jedis(getHost(), getPort());
        JSONObject result = new JSONObject();
        result.put("name", "kieran");
        result.put("age", "10");
        String data = result.toJSONString();

        Transaction multi = null;
        try {
            multi = jedis.multi();
            multi.set("user1", data);
            multi.set("user2", data);
            multi.exec();
        } catch (Exception e) {
            e.printStackTrace();
            if (null != multi) {
                multi.discard();
            }
        } finally {
            System.err.println(jedis.get("user1"));
            System.err.println(jedis.get("user2"));
            jedis.close();
        }
    }
}

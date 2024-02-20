package com.kieran.practice.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Redis Service
 */
@Service
public class RedisService {

    /**
     * Resource与Qualifier不能配合使用
     * Autowired与Qualifier可以配合使用
     */
//    @Autowired
//    @Qualifier("myRedisTemplate")
    @Resource(name = "myRedisTemplate")
    private RedisTemplate<Object, Object> redisTemplate;

    public void addKV(String name) {
        redisTemplate.opsForValue().set(name, "success");
        System.err.println(redisTemplate.opsForValue().get(name));
    }

    public int push() {

        int count = 0;
        int times = 0;
        int month = 1;
        for (int i = 100001; i <= 200000; i++) {
            if (times == 10000) {
                times = 0;
                month++;
            }
            // date必须放在第一个字段，其余根据顺序排列
            TreeMap<String, String> keywordMap = new TreeMap<>();
            keywordMap.put("uid", "98");
            keywordMap.put("os", "AS");
            keywordMap.put("om", "PAY");
            keywordMap.put("ot", "QUERY");
            keywordMap.put("oc", String.valueOf(i));
            keywordMap.put("uuid", "3c34073d-bde2-4636-a8b2-e9cbde100623");

            String date = "2023" + (month < 10 ? "0" + month : month) + new Random().nextInt(28) + "120000";

            // 数据存入redis
            StringBuilder value = new StringBuilder();
            value.append("date").append(":").append(date);
            for (Map.Entry<String, String> entry : keywordMap.entrySet()) {
                value.append("|").append(entry.getKey()).append(":").append(entry.getValue());
            }
            redisTemplate.opsForZSet().add("as_ops1", value, Double.parseDouble(String.valueOf(i)));
            count++;
            times++;

            System.err.println(value);
        }


        return count;
//        redisTemplate.opsForZSet().add("api", )
    }



}

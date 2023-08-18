package com.kieran.practice.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    private RedisTemplate<String, Object> redisTemplate;

    public void addKV(String name) {
        redisTemplate.opsForValue().set(name, "success");
        System.err.println(redisTemplate.opsForValue().get(name));
    }

}

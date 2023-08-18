package com.kieran.study.redis.redisTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class MyRedisTemplateTest {

    @Autowired
    @Qualifier("myRedisTemplate")
    private RedisTemplate redisTemplate;

}

package com.kieran.study.redis.redisTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class MyRedisTemplateTest {

    @Autowired
    @Qualifier("myRedisTemplate")
    private RedisTemplate redisTemplate;

}

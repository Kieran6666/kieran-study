package com.kieran.practice.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.StandardCharsets;

/**
 * 自定义RedisTemplate
 */
@Configuration
public class RedisTemplateConfig {
    /**
     *  redisTemplate是spring框架提供的redis客户端
     *  .gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-autoconfigure/2.7.6/4ad7e89accd25c69bfe84f52202deaf09430be2/spring-boot-autoconfigure-2.7.6.jar!/org/springframework/boot/autoconfigure/data/redis/RedisAutoConfiguration.class
     *  上一条是spring默认的redisTemplate，其中序列化的方式是 JDKSerializable，编码方式是ISO-8859-1
     *  这种序列化方式会导致存入redis时，使字符串变成编码的形式，可读性很差，因此需要自定义序列化方式
     *  推荐使用 JSON的序列化/反序列化方式，因此需要重写RedisTemplate
     *
     * @param redisConnectionFactory factory
     * @return redisTemplate
     */
    @Bean(name = "myRedisTemplate")
    @ConditionalOnMissingBean
//    @ConditionalOnSingleCandidate 判断指定类在 BeanFactory 中是否只有一个实例
//    由于Spring提供的RedisTemplate已经拥有BeanFactory的实例，因此开启此注解会导致本方法不生效
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 设置redis的String/Value的默认序列化方式
        DefaultStrSerializer stringRedisSerializer = new DefaultStrSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);

        template.afterPropertiesSet();

        return template;
    }
}

package com.kieran.practice.config;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.nio.charset.Charset;

/**
 * Redis字符序列化（如果不做此类，lettuce会把类直接存入Redis，导致会有类的前置信息被存入Redis中）
 */
public class DefaultStrSerializer implements RedisSerializer<Object> {
    /**
     * Charset
     */
    private final Charset charset;

    /**
     * DefaultStrSerializer
     */
    public DefaultStrSerializer() {
        this(Charset.forName("UTF8"));
    }

    /**
     * DefaultStrSerializer
     * @param charset charset
     */
    public DefaultStrSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    /**
     * serialize
     * @param o object
     * @return byte[]
     * @throws SerializationException SerializationException
     */
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return o == null ? null : String.valueOf(o).getBytes(charset);
    }

    /**
     * deserialize
     * @param bytes bytes
     * @return Object
     * @throws SerializationException SerializationException
     */
    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return bytes == null ? null : new String(bytes, charset);

    }
}

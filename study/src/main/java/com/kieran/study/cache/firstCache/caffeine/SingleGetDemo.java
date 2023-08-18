package com.kieran.study.cache.firstCache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class SingleGetDemo {

    public static void main(String[] args) throws InterruptedException {
        Cache<String, Object> sizeCache = Caffeine.newBuilder()
                .maximumSize(100) // 最多一百个数据
                .expireAfterAccess(1L, TimeUnit.SECONDS) // 缓存没有被访问，3秒后过期
                .build();
        sizeCache.put("age", 20);
        sizeCache.put("name", "kieran");

        log.info("未过期前获取：{} -> {}", "age", sizeCache.getIfPresent("age"));
        log.info("未过期前获取：{} -> {}", "name", sizeCache.getIfPresent("name"));

        Thread.sleep(2000);

        log.info("已过期后获取：{} -> {}", "age", sizeCache.getIfPresent("age"));
        log.info("已过期后获取：{} -> {}", "name", sizeCache.getIfPresent("name"));
        // 单个KEY的已过期后处理，使用的很少
        Object name = sizeCache.get("name", key -> {
            log.info(key + "已过期开始处理");
            try {
                Thread.sleep(5000); // 同步的加载操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 这一步会返回定义value, TODO:: 会写回缓存？
            return "invalid value!!";
        });
        log.info("ss " + name.toString());

        Object n1 = sizeCache.getIfPresent("name");
        System.err.println(n1);
        // 过期不会立刻失效，过期的一瞬间内仍然可以获取到值
        TimeUnit.MILLISECONDS.sleep(1000);
        Object invalidName = sizeCache.getIfPresent("name");
        System.err.println(invalidName);
    }
}

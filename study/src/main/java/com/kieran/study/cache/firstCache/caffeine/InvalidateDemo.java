package com.kieran.study.cache.firstCache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.TimeUnit;

/**
 * 缓存删除
 */
@Slf4j(topic = "invalidate")
public class InvalidateDemo {

    public static void main(String[] args) throws InterruptedException {
        Cache<String, String> c1 = Caffeine
                .newBuilder()
                .maximumSize(100)
                .expireAfterAccess(2L, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(@Nullable String key, @Nullable String value, @NonNull RemovalCause cause) {
                        log.info("删除监听器，key = {}、 value = {}、cause = {}", key, value, cause);
                    }
                })
                .build();

        c1.put("name", "kieran");
        c1.put("age", "10");
        log.info("缓存删除前，key = {}、 value = {}", "name", c1.getIfPresent("name"));
        log.info("缓存删除前，key = {}、 value = {}", "age", c1.getIfPresent("age"));
        c1.invalidate("name"); // 删除是立刻删除，过期是有一个过期删除的短暂时间
        log.info("缓存删除后，key = {}、 value = {}", "name", c1.getIfPresent("name"));
        TimeUnit.SECONDS.sleep(3);
        log.info("缓存删除后，key = {}、 value = {}", "age", c1.getIfPresent("age"));
        // 如果不加这句话，主线程就执行结束了，删除监听器的子线程就无法捕捉到
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
    }
}

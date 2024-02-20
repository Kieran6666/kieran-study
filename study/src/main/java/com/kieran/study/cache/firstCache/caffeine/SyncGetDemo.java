package com.kieran.study.cache.firstCache.caffeine;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.sun.tools.javac.util.List;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 咖啡因，多个key同步加载
 */
@Slf4j
public class SyncGetDemo {

    public static void main(String[] args) throws InterruptedException {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(3L, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public @Nullable String load(@NonNull String key) throws Exception {
                        // 只有key不存在或者key过期了，才会加载这里

                        log.info("缓存加载=>{}", key);
                        TimeUnit.SECONDS.sleep(3);
                        // 如果key过期，或者根据key无法搜索到，可以return指定的value，反之return真实的value
                        return "[LoadingCache] => " + key;
                    }
                });

        cache.put("name", "Kieran");

        log.info("未过期前获取：{} -> {}", "name", cache.getIfPresent("name"));
        TimeUnit.SECONDS.sleep(5);
        log.info("已过期后获取：{} -> {}", "name", cache.getIfPresent("name"));

        // getAll，可以触发.build()里的CacheLoader函数，此处可以区分出哪些key过期了
        Map<@NonNull String, @NonNull String> cacheMap = cache.getAll(List.of("name", "age"));
        for (Map.Entry<String, String> entry : cacheMap.entrySet()) {
            log.info("遍历Key:{}, value: {}", entry.getKey(), entry.getValue());
        }


    }
}

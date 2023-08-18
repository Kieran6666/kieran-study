package com.kieran.study.cache.firstCache.caffeine;

import com.github.benmanes.caffeine.cache.AsyncCacheLoader;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.kieran.study.juc.executor.ThreadPoolConfig;
import com.sun.tools.javac.util.List;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步缓存
 */
@Slf4j
public class AsyncDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = ThreadPoolConfig.threadPoolExecutor();

        AsyncLoadingCache<String, String> cache = Caffeine
                .newBuilder()
                .maximumSize(100)
                .expireAfterAccess(3L, TimeUnit.SECONDS)
                .executor(executor) // 默认使用的是ForkJoinPool.commonPool()
                .buildAsync(new AsyncCacheLoader<String, String>() {
                    @Override
                    public @NonNull CompletableFuture<String> asyncLoad(@NonNull String key, @NonNull Executor executor)
                    {
                        return CompletableFuture.supplyAsync(() -> {
                            // 只有key不存在或者key过期了，才会加载这里
                            log.info("缓存加载=>{}", key);
                            try {
                                TimeUnit.SECONDS.sleep(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // 如果key过期，或者根据key无法搜索到，可以return指定的value，反之return真实的value
                            return "[LoadingCache] => " + key;
                        });
                    }
                });

        cache.put("name", CompletableFuture.completedFuture("kieran"));
        cache.put("like", CompletableFuture.completedFuture("you"));

        log.info("未过期前获取：{} -> {}", "name", cache.getIfPresent("name").get());
        log.info("已过期后获取：{} -> {}", "name", cache.getIfPresent("name").get());

        // getAll，可以触发.build()里的CacheLoader函数，此处可以区分出哪些key过期了
        Map<@NonNull String, @NonNull String> cacheMap = cache.getAll(List.of("name", "kk", "age", "like")).get();
        for (Map.Entry<String, String> entry : cacheMap.entrySet()) {
            log.info("遍历Key:{}, value: {}", entry.getKey(), entry.getValue());
        }

        executor.shutdown();
    }


}

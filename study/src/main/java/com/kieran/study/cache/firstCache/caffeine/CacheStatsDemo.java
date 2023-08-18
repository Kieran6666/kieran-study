package com.kieran.study.cache.firstCache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.kieran.study.juc.executor.ThreadPoolConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 缓存统计数据
 */
@Slf4j
public class CacheStatsDemo {

    public static void main(String[] args) throws InterruptedException {
        Cache<String, String> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(1L, TimeUnit.SECONDS)
                .recordStats()
                .build();

        String[] keys = {"A", "B", "C", "D", "E"};

        cache.put("A", "is me, A");
        cache.put("B", "is me, B");
        cache.put("C", "is me, C");

        Random r = new Random();

        List<String> hitKeys = new ArrayList<>();

        int taskCount = 1000;
        CountDownLatch latch = new CountDownLatch(taskCount);
        ExecutorService executorService = ThreadPoolConfig.threadPoolExecutor();
        for (int i = 0; i < taskCount; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500); // 让cache失效
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String key = keys[r.nextInt(keys.length)];
                String value = cache.getIfPresent(key);
                log.info("当前线程 = 【{}】、key = {} 、 value = {}", Thread.currentThread().getName()+ ":" + finalI, key, value);
                if (null != value) {
                    hitKeys.add(key);
                }
                latch.countDown();
            });
        }

        latch.await();
        executorService.shutdown();

        // 缓存统计数据
        // 先说一下什么是“加载”，当查询缓存时，缓存未命中，那就需要去第三方数据库中查询，然后将查询出的数据先存入缓存，再返回给查询者，这个过程就是加载。
        CacheStats stats = cache.stats();
        log.info("加载次数 = {}", stats.loadCount());
        log.info("加载成功次数 = {}", stats.loadSuccessCount());
        log.info("加载失败次数 = {}", stats.loadFailureCount());
        log.info("加载失败率 = {}", stats.loadFailureRate());
        log.info("总共加载时间 = {}", stats.totalLoadTime());
        log.info("平均加载时间 = {}", stats.averageLoadPenalty());
        log.info("命中次数 = {}", stats.hitCount());
        log.info("命中率 = {}", stats.hitRate());
        log.info("丢失次数 = {}", stats.missCount());
        log.info("丢失率 = {}", stats.missRate());
        log.info("被淘汰出缓存的数据总个数 = {}", stats.evictionCount());
        log.info("被淘汰出缓存的那些数据的总权重 = {}", stats.evictionWeight());
        log.info("用户请求查询总次数 = {}", stats.requestCount());

        // 统计数据的记录方式可以重写，implement StatsCounter

        // 查看命中的key个数
        Map<String, List<String>> collect = hitKeys.stream().collect(Collectors.groupingBy(Function.identity()));
        log.info("A = {}", null != collect.get("A") ? collect.get("A").size() : 0);
        log.info("B = {}", null != collect.get("B") ? collect.get("B").size() : 0);
        log.info("C = {}", null != collect.get("C") ? collect.get("C").size() : 0);
        log.info("D = {}", null != collect.get("D") ? collect.get("D").size() : 0);
        log.info("E = {}", null != collect.get("E") ? collect.get("E").size() : 0);
    }
}

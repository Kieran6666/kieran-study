package com.kieran.study.cache.firstCache.caffeine;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.concurrent.TimeUnit;

/**
 * 创建咖啡因实例及驱逐策略
 * 注意：
 * 1.最大权重和最大容量的算法不一样，只能二选一
 * 2.缓存的清除会花费一点时间，因此缓存刚过期时就获取，也可能获取到
 */
@Slf4j
public class BuildDemo {

    public static void main(String[] args) throws InterruptedException {
        // 驱逐策略 - 最大容量
        Cache<String, Object> c1 = Caffeine.newBuilder()
                .maximumSize(3) // 最多一个数据
                .build();
        c1.put("age", 20);
        c1.put("name", "kieran");
        c1.put("val", "ff");
        // 缓存需要花费一点时间来清除
        TimeUnit.MILLISECONDS.sleep(100);
        log.info("超过最大容量：{} -> {}", "age", c1.getIfPresent("age"));
        log.info("超过最大容量：{} -> {}", "name", c1.getIfPresent("name"));
        log.info("超过最大容量：{} -> {}", "val", c1.getIfPresent("val"));

        // 驱逐策略 - 最大权重
        Cache<String, String> c2 = Caffeine.newBuilder()
                .maximumWeight(100) // TODO:: 如果超过权重，会导致cache整个直接被删除
                .weigher((key, value) -> {
                    log.info("[权重计算器]key：{}、value：{}", key, value);
                    return 50;
                })
                .build();

        c2.put("A", "isA");
        c2.put("B", "isB");
        c2.put("C", "isC");
        TimeUnit.MILLISECONDS.sleep(100);
        log.info("未超权重获取：{} -> {}", "A", c2.getIfPresent("A"));
        log.info("未超权重获取：{} -> {}", "B", c2.getIfPresent("B"));
        log.info("未超权重获取：{} -> {}", "C", c2.getIfPresent("C"));

        // 驱逐策略 - 写完时间驱逐
        Cache<String, String> c3 = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(2L, TimeUnit.SECONDS)
                .build();
        c3.put("name", "kieran1");
        for (int i = 0; i < 3; i++) {
            TimeUnit.MILLISECONDS.sleep(1500);
            log.info("时间驱逐策略，key：{}、value：{}", "name", c3.getIfPresent("name"));
        }

        // 自定义驱逐策略
        Cache<String, String> c4 = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfter(new Expiry<String, String>() {
                    @Override
                    public long expireAfterCreate(@NonNull String key, @NonNull String value, long currentTime) {
                        log.info("创建后，key = {}、value = {}", key, value);
                        return TimeUnit.NANOSECONDS.convert(365, TimeUnit.DAYS);
                    }

                    @Override
                    public long expireAfterUpdate(@NonNull String key, @NonNull String value, long currentTime, @NonNegative long currentDuration) {
                        log.info("更新后，key = {}、value = {}", key, value);
                        return TimeUnit.NANOSECONDS.convert(365, TimeUnit.DAYS);
                    }

                    @Override
                    public long expireAfterRead(@NonNull String key, @NonNull String value, long currentTime, @NonNegative long currentDuration) {
                        log.info("读取后，key = {}、value = {}", key, value);
                        return TimeUnit.NANOSECONDS.convert(2, TimeUnit.SECONDS);
                    }
                })
                .build();
        c4.put("K", "V");
        TimeUnit.MILLISECONDS.sleep(1900);
        log.info("读后过期驱逐策略，key = {}、value = {}", "K", c4.getIfPresent("K"));
        TimeUnit.MILLISECONDS.sleep(1900);
        c4.put("K", "V1"); // 更新缓存，可以重置读取的过期时间
        TimeUnit.MILLISECONDS.sleep(1900);
        log.info("读后过期驱逐策略，key = {}、value = {}", "K", c4.getIfPresent("K"));
        TimeUnit.MILLISECONDS.sleep(2100);
        log.info("读后过期驱逐策略，key = {}、value = {}", "K", c4.getIfPresent("K"));


        // 异步缓存不能使用 弱引用或者软引用的驱逐策略
        // 驱逐策略 - 弱引用驱逐 ,
        Cache<String, String> c5 = Caffeine
                .newBuilder()
                .weakKeys() // 弱引用，GC执行时，被GC回收
                .weakValues()
                .build();
        String key = new String("weak name"); // 强引用
        String value = new String("weak value");
        c5.put(key, value);

        log.info("弱引用驱逐策略，key = {}、value = {}", key, c5.getIfPresent(key));
        value = null; // 清空引用
        Runtime.getRuntime().gc(); // 此处示例强制调用GC，工作中不要这样使用，这是在玩火！
        TimeUnit.MILLISECONDS.sleep(100);
        log.info("弱引用驱逐策略，key = {}、value = {}", key, c5.getIfPresent(key));

        // 驱逐策略 - 软引用驱逐  内存不足时调用 不推荐使用
        Cache<String, String> c6 = Caffeine
                .newBuilder()
                .softValues()
                .build();


    }
}

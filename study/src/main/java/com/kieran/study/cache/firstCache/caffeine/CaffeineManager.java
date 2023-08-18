package com.kieran.study.cache.firstCache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CaffeineManager {


    public static void main(String[] args) throws InterruptedException {

    }

    static void simpleCache() throws InterruptedException {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();

        Set<Cache> cacheSet = new HashSet<>();
        cacheSet.add(new CaffeineCache("s1", Caffeine.newBuilder().expireAfterWrite(10L, TimeUnit.SECONDS).build()));
        cacheSet.add(new CaffeineCache("s2", Caffeine.newBuilder().expireAfterWrite(10L, TimeUnit.SECONDS).build()));
        simpleCacheManager.setCaches(cacheSet);

        Cache s1 = simpleCacheManager.getCache("s1");
        Cache s2 = simpleCacheManager.getCache("s2");

        s1.put("k1", "v1");
        s2.put("k2", "v2");

        System.err.println(s1.get("k1").get());
        System.err.println(s2.get("k2").get());

        TimeUnit.SECONDS.sleep(8);

        System.err.println(s1.get("k1").get());
        System.err.println(s2.get("k2").get());


        TimeUnit.SECONDS.sleep(3);

        System.err.println(s1.get("k1").get());
        System.err.println(s2.get("k2").get());


    }

    static void caffeineCache() throws InterruptedException {

        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder().maximumSize(10)
                .expireAfterWrite(10L, TimeUnit.SECONDS));


        Cache c1 = caffeineCacheManager.getCache("C1");
        Cache c2 = caffeineCacheManager.getCache("C2");


        c1.put("k1", "v1");
        c2.put("k2", "v2");

        System.err.println(c1.get("k1").get());
        System.err.println(c2.get("k2").get());

        TimeUnit.SECONDS.sleep(8);

        System.err.println(c1.get("k1").get());
        System.err.println(c2.get("k2").get());


        TimeUnit.SECONDS.sleep(3);

        System.err.println(c1.get("k1").get());
        System.err.println(c2.get("k2").get());
    }
}

package com.kieran.study.juc.executor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadPoolConfig {
    /**
     * 获取当前机器的核数
     */
    private static final int CPU_NUM = Runtime.getRuntime().availableProcessors();
    /**
     * 缓冲队列数
     * 默认的缓冲队列数是Integer.MAX_VALUE
     */
    private static final int QUEUE_CAPACITY = 100;

    /**
     * 允许线程空闲时间
     * 默认的线程空闲时间为60秒
     */
    private static final int KEEP_ALIVE_SECONDS = 30;

    /**
     * 线程池前缀名
     */
    private static final String THREAD_NAME_PREFIX = "Task_Service_Async_";

    /**
     * allowCoreThreadTimeOut为true则线程池数量最后销毁到0个
     * allowCoreThreadTimeOut为false
     * 销毁机制：超过核心线程数时，而且（超过最大值或者timeout过），就会销毁。
     * 默认是false
     */
    private boolean allowCoreThreadTimeOut = false;


    public static ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(
                CPU_NUM, CPU_NUM * 2,
                KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(QUEUE_CAPACITY),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = threadPoolExecutor();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            list.add(i);
        }

        int batchNum = 50;
        int times = new BigDecimal(String.valueOf(list.size()))
                .divide(new BigDecimal(String.valueOf(batchNum)), 1, RoundingMode.UP)
                .setScale(0, RoundingMode.UP)
                .intValue();

        List<List<Integer>> batches = new ArrayList<>();
        Stream.iterate(0, n -> n + batchNum).limit(times).forEach(a -> batches
                .add(list.stream().skip(a).limit(batchNum).collect(Collectors.toList())));

        List<Integer> dataList = new ArrayList<>();
        List<String> error = new ArrayList<>();

        Stream<CompletableFuture<Object>> completableFutureStream = batches.stream()
                .map(item -> CompletableFuture.supplyAsync(() -> {
                                    System.err.println(Thread.currentThread().getName());
                                    return item;
                                }
                              , threadPoolExecutor)
                        .handle((result, throwable) -> {
                    if (throwable == null) {
                        dataList.addAll(result);
                    } else {
                        System.err.println(throwable);
                        error.add("1");
                    }
                    return null;
                }));

        CompletableFuture[] completableFutures = completableFutureStream.toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures).whenComplete((x, y) -> System.err.println("库存推移补全完成")).join();

        if (error.size() > 0) {
            throw new Exception(error.toString());
        }


        // 必须shutdown
        threadPoolExecutor.shutdown();


        System.err.println(dataList);
        System.err.println(dataList.size());

        System.err.println(111);
    }

}

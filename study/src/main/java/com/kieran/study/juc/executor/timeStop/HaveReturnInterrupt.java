package com.kieran.study.juc.executor.timeStop;

import com.kieran.study.juc.executor.ThreadPoolConfig;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 有参返回的子线程
 */
@Slf4j
public class HaveReturnInterrupt implements Callable<Pair<String, Integer>> {

    private static AtomicBoolean run = new AtomicBoolean(true);
    private static int count = 0;
    @Override
    public Pair<String, Integer> call() throws Exception {
        while (run.get() && count <= 30) {
            TimeUnit.SECONDS.sleep(1);
            log.info("callable, count = {}, run = {}", count, run.get());
            count++;
        }
        return new Pair<>("my count", count);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = ThreadPoolConfig.threadPoolExecutor();
        Future<Pair<String, Integer>> submit = executorService.submit(new HaveReturnInterrupt());
        executorService.shutdown();

        TimeUnit.SECONDS.sleep(10);
//        TimeUnit.SECONDS.sleep(30);
        log.info("主线程打断前：{}", run.get());
        run.set(false);
        log.info("主线程打断后：{}", run.get());

        Pair<String, Integer> pair = submit.get();
        log.info("left = {}、right = {}", pair.getKey(), pair.getValue());
    }


}

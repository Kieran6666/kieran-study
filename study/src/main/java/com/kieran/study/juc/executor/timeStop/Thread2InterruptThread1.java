package com.kieran.study.juc.executor.timeStop;

import com.kieran.study.juc.executor.ThreadPoolConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 子线程2打断子线程1
 */
@Slf4j
public class Thread2InterruptThread1 {

    public static void main(String[] args) {
        AtomicBoolean run = new AtomicBoolean(true);

        Runnable r1 = () -> {
            int i = 0;
            while (run.get()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.info("异步线程：{}，{}", i++, run.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable r2 = () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                log.info("监视器触发前：{}", run.get());
                run.set(false);
                log.info("监视器触发后：{}", run.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(r1).start();
        new Thread(r2).start();
//        ExecutorService executor = ThreadPoolConfig.threadPoolExecutor();
//        executor.submit(r1);
//        executor.submit(r2);
//        executor.shutdown();
    }
}

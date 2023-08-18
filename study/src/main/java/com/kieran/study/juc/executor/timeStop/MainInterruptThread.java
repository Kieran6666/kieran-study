package com.kieran.study.juc.executor.timeStop;

import com.kieran.study.juc.executor.ThreadPoolConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 主线程打断子线程
 */
@Slf4j
public class MainInterruptThread {

    public static void main(String[] args) throws InterruptedException {
        // 原子操作+指令重排
        AtomicBoolean run = new AtomicBoolean(true);

        // 子线程
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

        Thread t1 = new Thread(r1);
        t1.start();
//        ExecutorService executor = ThreadPoolConfig.threadPoolExecutor();
//        executor.submit(r1);
//        executor.shutdown();

        TimeUnit.SECONDS.sleep(10);
        run.set(false);
    }
}

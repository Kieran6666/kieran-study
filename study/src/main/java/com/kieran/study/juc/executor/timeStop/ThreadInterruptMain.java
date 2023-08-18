package com.kieran.study.juc.executor.timeStop;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 子线程打断主线程
 */
@Slf4j
public class ThreadInterruptMain {

    public static void main(String[] args) {
        AtomicBoolean run = new AtomicBoolean(true);

        Runnable r1 = () -> {
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

        int i = 0;
        while (run.get()) {
            try {
                TimeUnit.SECONDS.sleep(1);
                log.info("主线程：{}，{}", i++, run.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

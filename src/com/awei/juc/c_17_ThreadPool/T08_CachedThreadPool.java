package com.awei.juc.c_17_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 没有核心线程，最大线程数为Integer的maxValue，线程队列为Synchronous，线程存活时间为60S
 * 有任务进来时，如果没有空闲的线程，则新起一个线程去处理
 */
public class T08_CachedThreadPool {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        System.out.println(service);
        
        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);
        
        service.shutdown();
    }
}

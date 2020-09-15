package com.awei.juc.c_17_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 为什么要有一个线程的线程池？
 *  1、线程池中有线程队列
 *  2、有完整的生命周期
 */
public class T07_SingleThreadPool {

    public static void main(String[] args) {
        //只有一个线程的线程池
        ExecutorService service = Executors.newSingleThreadExecutor();
        
        //任务会顺序执行
        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.execute(() -> {
                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }

        service.shutdown();
    }
}

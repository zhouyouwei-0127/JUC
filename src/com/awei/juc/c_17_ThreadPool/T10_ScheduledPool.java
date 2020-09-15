package com.awei.juc.c_17_ThreadPool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时执行任务的线程池
 * 需要指定核心的线程数，最大线程数为Integer的maxValue
 * 底层用的是DelayQueue
 */
public class T10_ScheduledPool {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        /**
         * scheduleAtFixedRate四个参数
         * 1、Runnable接口
         * 2、第一个任务在延迟多久之后执行
         * 3、每隔多久执行下一个任务
         * 4、延迟的时间单位
         */
        service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()); 
        },0,500,TimeUnit.MILLISECONDS);
    }
}

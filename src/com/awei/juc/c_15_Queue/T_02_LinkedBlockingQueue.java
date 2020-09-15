package com.awei.juc.c_15_Queue;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 如果容器满了，put方法会阻塞住线程
 * 如果队列为空了，则take方法会阻塞线程
 * 底层调用的是ReentrantLock的condition-最底层也是用的LockSupport的park
 */
public class T_02_LinkedBlockingQueue {

    static BlockingDeque<String> strs = new LinkedBlockingDeque<>();

    static Random r = new Random();
    
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    //如果容器满了，则线程会阻塞
                    strs.put("a" + i);
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        //take方法在队列中为空时会阻塞
                        System.out.println(Thread.currentThread().getName() + " take " + strs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

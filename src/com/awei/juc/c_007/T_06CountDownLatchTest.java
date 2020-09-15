package com.awei.juc.c_007;

import java.util.concurrent.CountDownLatch;

public class T_06CountDownLatchTest {

    public static void main(String[] args) {
        useCountDownLatch();
    }

    static void useCountDownLatch() {
        Thread[] threads = new Thread[100];
        //创建了一个门栓，门栓上记了个数是100
        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int num = 0;
                for (int j = 0; j < 100; j++) {
                    num += j;
                }
                //每一个线程结束的时候让门栓上的数减一
                latch.countDown();
                System.out.println(latch.getCount());
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            //阻塞住线程，在门栓上的数为0时再继续往下执行。
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("countDownLatch end");
    }
}

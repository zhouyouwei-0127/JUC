package com.awei.juc.c_008_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class T05_CountDownLatch {

    static List list = new ArrayList();

    void add(Object o) { list.add(o); }

    int size() { return list.size(); }

    public static void main(String[] args) {
        T02_NotifyHoldingLock t = new T02_NotifyHoldingLock();
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);

        new Thread(() -> {
            if (list.size() != 5) {
                try {
                    //阻塞住t2
                    latch1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 end");
            //让t1执行
            latch2.countDown();
        },"t2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.add(new Object());
                System.out.println(Thread.currentThread().getName() + " add " + i);
                if (list.size() == 5) {
                    //让t2执行
                    latch1.countDown();

                    try {
                        //阻塞住t1
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t1 end");
        },"t1").start();
    }
}

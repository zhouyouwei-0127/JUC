package com.awei.juc.c_008_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T02_NotifyHoldingLock {

    static List list = new ArrayList();

    void add(Object o) { list.add(o); }

    int size() { return list.size(); }

    public static void main(String[] args) {
        T02_NotifyHoldingLock t = new T02_NotifyHoldingLock();
        final Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2启动");
                if(t.size() != 5) {
                    try {
                        //调用wait t1线程释放锁进入等待队列
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
            }
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1启动");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    list.add(new Object());
                    System.out.println("add " + i);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (t.size() == 5) {
                        //调用此方法唤醒t2线程，但是并未释放锁，所以t1继续执行，执行完了之后t2再执行
                        lock.notify();
                    }
                }
            }
        }).start();
    }
}

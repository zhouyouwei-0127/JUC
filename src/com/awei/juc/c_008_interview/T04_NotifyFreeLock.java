package com.awei.juc.c_008_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，该怎么做呢？
 *
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 *
 * 阅读下面的程序，并分析输出结果
 * 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才接收到通知而退出
 * 想想这是为什么？
 *
 * notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行
 * 整个通信过程比较繁琐
 * @author mashibing
 */
public class T04_NotifyFreeLock {

    static List list = new ArrayList();

    void add(Object o) { list.add(o); }

    int size() { return list.size(); }

    public static void main(String[] args) {
        T02_NotifyHoldingLock t = new T02_NotifyHoldingLock();
        final Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                if (list.size() != 5) {
                    try {
                        //阻塞住t2，释放锁让t1执行
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 end");
                //唤醒t1线程
                lock.notify();
            }
        },"t2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    list.add(new Object());
                    System.out.println(Thread.currentThread().getName() + " add " + i);

                    if (list.size() == 5) {
                        //唤醒t2线程
                        lock.notify();
                        try {
                            //阻塞住t1，释放锁让t2执行
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            System.out.println("t1 end");
        },"t1").start();
    }
}

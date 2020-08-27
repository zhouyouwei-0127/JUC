package com.awei.juc.c_004;

import java.util.concurrent.TimeUnit;

/**
 * t1线程抛出异常后，会导致其它线程的乱入（t2开始执行）
 */
public class T {

    int count = 0;

    public synchronized void m() {
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 5) {
                int i = 1/0;
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m,"t1").start();
        new Thread(t::m,"t2").start();
    }
}

package com.awei.juc.c_007;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T_05ReentrantLock5 {

    /**
     * 默认为非公平锁
     * 参数为true表示为公平锁,为公平锁时线程的交替会比较频繁，因为线程在释放锁之后会进入等待队列等待其它线程执行
     * 而非公平锁线程会直接抢占cpu资源，不会等待其它线程，所以一个线程获取cpu的时间会比较长，线程交替不会那么频繁
     */
    Lock lock = new ReentrantLock(true);

    void m() {
        for (int i = 0; i < 100; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T_05ReentrantLock5 t = new T_05ReentrantLock5();
        new Thread(t::m,"t1").start();
        new Thread(t::m,"t2").start();
    }
}

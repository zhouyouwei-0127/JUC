package com.awei.juc.c_007;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T_02ReentrantLock2 {

    Lock lock = new ReentrantLock();

    void m1() {
        try {
            //加锁
            lock.lock();
            System.out.println("m1 start");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("m1 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //解锁，最好写在finally中
            lock.unlock();
        }
    }

    void m2() {
        try {
            lock.lock();
            System.out.println("m2");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T_02ReentrantLock2 t = new T_02ReentrantLock2();
        //第一个线程释放锁之后第二个线程才能拿到锁
        new Thread(t::m1).start();
        new Thread(t::m2).start();
    }
}

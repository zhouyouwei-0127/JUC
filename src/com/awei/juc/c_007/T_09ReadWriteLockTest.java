package com.awei.juc.c_007;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T_09ReadWriteLockTest {
    static Lock lock = new ReentrantLock();
    //读写锁
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //将读写锁一分为二，读锁和写锁是同一把锁
    //获得读锁
    static Lock readLock = readWriteLock.readLock();
    //获得写锁
    static Lock writeLock = readWriteLock.writeLock();

    static void read(Lock lock) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("read over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    static void write(Lock lock) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("write over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 如果传的是Lock则一个线程执行完之后另一个线程才能执行
     * 如果传的是ReadWriteLock，则读可以同时进行，因为读锁是共享锁，写线程需要一个个进行，因为写锁是排他锁
     * @param args
     */
    public static void main(String[] args) {
        //for (int i = 0; i < 18; i++) new Thread(() -> {read(lock);}).start();
        //for (int i = 0; i < 2; i++) new Thread(() -> {write(lock);}).start();
        for (int i = 0; i < 18; i++) new Thread(() -> {read(readLock);}).start();
        for (int i = 0; i < 2; i++) new Thread(() -> {write(writeLock);}).start();
    }

}

package com.awei.juc.c_007;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T_03ReentrantLock3 {

    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用tryLock进行尝试锁定,可以指定尝试的时间，不管锁定与否，方法都将继续执行
     * 可以根据tryLock的返回值来判断锁定是否成功
     */
    void m2() {
        boolean locked = true;
        try {
            locked = this.lock.tryLock(5, TimeUnit.SECONDS);
            if (locked) {
                System.out.println("锁定成功");
            } else {
                System.out.println("锁定失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T_03ReentrantLock3 t = new T_03ReentrantLock3();
        new Thread(t::m1).start();
        new Thread(t::m2).start();
    }
}

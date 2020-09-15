package com.awei.juc.c_007;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T_04ReentrantLock4 {

    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        try {
            //lock.lock();
            //可以对interrupt()方法做出响应,如果
            lock.lockInterruptibly();
            System.out.println("m2 start");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("m2 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //lock.unlock();
        }
    }

    public static void main(String[] args) {
        T_04ReentrantLock4 t = new T_04ReentrantLock4();

        new Thread(t::m1).start();
        Thread thread2 = new Thread(t::m2);
        //第一个线程锁定了之后，一直sleep没有释放锁，thread2就无法拿到锁执行后面的代码
        thread2.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //打断thread2的等待，让线程可以继续往下执行
        thread2.interrupt();
    }
}

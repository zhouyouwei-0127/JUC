package com.awei.juc.c_002;

/**
 * 加锁方法和不加锁方法可以同时执行
 */
public class T {

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }

    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " m2 start");
    }

    //如果不能同时调用，则会在m1执行完之后再执行m2
    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m1).start();
        new Thread(t::m2).start();
    }
}

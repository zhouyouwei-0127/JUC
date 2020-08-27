package com.awei.juc.c_001;

/**
 * 基本概念
 */
public class T {

    private int count = 100;
    private static int c = 100;
    private Object o = new Object();

    public void m() {
        //可以锁任意对象，表示必须拿到这把锁，才能执行下面的代码。
        synchronized (o) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    //跟m()是等值的，这里锁的是this对象
    public synchronized void m1() {
        count--;
    }

    //如果是静态的则表示锁的是T这个类对应的字节码文件的对象
    public synchronized static void m3() {
        c--;
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                t.m();
            }).start();
        }
    }
}

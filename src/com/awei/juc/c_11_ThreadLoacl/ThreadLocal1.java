package com.awei.juc.c_11_ThreadLoacl;

import java.util.concurrent.TimeUnit;

/**
 * 一个线程在睡一秒之后set了一个Person对象，另一个线程在睡了两秒之后再get 拿到的是null
 * 这是因为ThreadLocal将对象set进了线程本地中的map，其它线程是无法访问的
 */
public class ThreadLocal1 {
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();
    }
}

class Person {
    String name;
}

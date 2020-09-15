package com.awei.juc.c_005;

import java.util.concurrent.TimeUnit;

/**
 * 不加volatile程序在1S后不会输出end
 */
public class T {

    volatile boolean flag = true;

    void m() {
        System.out.println("start");
        while (flag) {

        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        T t = new T();

        new Thread(t::m).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.flag = false;
    }
}

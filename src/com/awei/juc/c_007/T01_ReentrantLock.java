package com.awei.juc.c_007;

import java.util.concurrent.TimeUnit;

public class T01_ReentrantLock {

    synchronized void m1() {
        System.out.println("m1......");
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);

            if (i ==2) {
                m2();
            }
        }
    }

    synchronized void m2() {
        System.out.println("m2...........");
    }

    public static void main(String[] args) {
        T01_ReentrantLock t = new T01_ReentrantLock();
        new Thread(t::m1).start();
    }
}

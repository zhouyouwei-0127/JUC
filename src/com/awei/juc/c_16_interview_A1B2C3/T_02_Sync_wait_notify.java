package com.awei.juc.c_16_interview_A1B2C3;

public class T_02_Sync_wait_notify {
    
    static Thread t1 = null, t2 = null;
    
    public static void main(String[] args) {
        Object lock = new Object();
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        
        t1 = new Thread(() -> {
            synchronized (lock) {
                for (char c : aI) {
                    System.out.println(c);
                    try {
                        //唤醒其它线程
                        lock.notify();
                        //释放锁
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                //必须加上，否则程序无法停止
                lock.notify();
            }
        },"t1");
        
        t2 = new Thread(() -> {
            synchronized (lock) {
                for (char c : aC) {
                    System.out.println(c);
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        },"t2");
        
        t1.start();
        t2.start();
    }
}

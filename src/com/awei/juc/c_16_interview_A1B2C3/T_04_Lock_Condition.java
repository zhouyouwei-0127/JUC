package com.awei.juc.c_16_interview_A1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T_04_Lock_Condition {

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        
        new Thread(() -> {
            try {
                lock.lock();
                for (char c : aI) {
                    condition.signal();
                    condition.await();
                    System.out.println(c);
                }

                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
        
        new Thread(() -> {
            try {
                lock.lock();
                for (char c : aC) {
                    System.out.println(c);
                    condition.signal();
                    condition.await();
                }

                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}

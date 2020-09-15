package com.awei.juc.c_16_interview_A1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T_05_Lock_Condition_02 {

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
       
        Lock lock = new ReentrantLock();
        Condition consumer = lock.newCondition();
        Condition provider = lock.newCondition();
        
        new Thread(() -> {
            try {
                lock.lock();
                for (char c : aI) {
                    provider.signal();
                    consumer.await();
                    System.out.println(c);
                }
                provider.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
        
        new Thread(() -> {
            try {
                lock.lock();
                for (char c : aC) {
                    consumer.signal();
                    System.out.println(c);
                    provider.await();
                }
                consumer.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}

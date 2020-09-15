package com.awei.juc.c_16_interview_A1B2C3;

import java.util.concurrent.locks.LockSupport;

/**
 * 要求用线程顺序打印A1B2C3....Z26
 */
public class T_01_LockSupport {
    
    static Thread t1 = null, t2 = null;
    
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        
        t1 = new Thread(() -> {
            for (char c : aI) {
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(t2);
            }
        },"t1");
        
        t2 = new Thread(() -> {
            for (char c : aC) {
                System.out.println(c);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        },"t2");

        t1.start();
        t2.start();
    }
}

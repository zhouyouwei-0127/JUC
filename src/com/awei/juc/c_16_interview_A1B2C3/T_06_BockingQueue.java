package com.awei.juc.c_16_interview_A1B2C3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class T_06_BockingQueue {
    static BlockingQueue<String> q1 = new ArrayBlockingQueue(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue(1);
   
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        
        new Thread(() -> {
            for (char c : aI) {
                try {
                    q2.put("ok");
                    q1.take();
                    System.out.println(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();  
        
        new Thread(() -> {
            for (char c : aC) {
                try {
                    q2.take();
                    System.out.println(c);
                    q1.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

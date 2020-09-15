package com.awei.juc.c_16_interview_A1B2C3;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class T_08_TransferQueue {

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        TransferQueue t = new LinkedTransferQueue();
        
        new Thread(() -> {
            for (char c : aI) {
                try {
                    System.out.println(t.take());
                    t.transfer(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        new Thread(() -> {
            for (char c : aC) {
                try {
                    t.transfer(c);
                    System.out.println(t.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

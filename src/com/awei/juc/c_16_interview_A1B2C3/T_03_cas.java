package com.awei.juc.c_16_interview_A1B2C3;

public class T_03_cas {
    
    enum RunThread {T1,T2}

    static volatile RunThread r = RunThread.T2;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        
        new Thread(() -> {
            for (char c : aI) {
                while (r != RunThread.T1) {}
                System.out.println(c);
                r = RunThread.T2;
            }
        }).start();

        new Thread(() -> {
            for (char c : aC) {
                while (r != RunThread.T2) {}
                System.out.println(c);
                r = RunThread.T1;
            }
        }).start();
    }
}

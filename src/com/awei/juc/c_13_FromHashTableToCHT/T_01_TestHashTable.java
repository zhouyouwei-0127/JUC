package com.awei.juc.c_13_FromHashTableToCHT;

import java.util.Hashtable;
import java.util.UUID;

public class T_01_TestHashTable {

    static Hashtable<UUID, UUID> m = new Hashtable<>();
    
    //每个线程执行的次数
    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    //线程数量
    static final int THREAD_COUNT = Constants.THREAD_COUNT;

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }
    
    static class MyThread extends Thread {
        int start;
        int gap = count/THREAD_COUNT;
        
        public MyThread(int start) {
            this.start = start;
        }
        
        @Override
        public void run() {
            //每个线程执行不同段的操作
            for(int i=start; i<start+gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];

        for(int i=0; i<threads.length; i++) {
            threads[i] =
                    new MyThread(i * (count/THREAD_COUNT));
        }

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("写入时间:\t" + (end - start));

        System.out.println(m.size());
    }
}

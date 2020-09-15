package com.awei.juc.c_006_01_AtomicSynchronizedLongAdder;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Stream;

public class CompareEfficiency {

    static long count1 = 0l;
    static AtomicLong count2 = new AtomicLong(0l);
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        //synchronized
        Object lock = new Object();
        Thread[] threads1 = new Thread[1000];

        for (int i = 0; i < threads1.length; i++) {
            threads1[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    synchronized (lock) {
                        count1++;
                    }
                }
            });
        }

        long start1 = System.currentTimeMillis();
        /*for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }*/
        Stream.of(threads1).forEach((x) -> x.start());
        Stream.of(threads1).forEach((x) -> {
            try {
                x.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end1 = System.currentTimeMillis();
        System.out.println("synchronized: " + count1 + " time " + (end1 - start1));

        //*******************************************************************//
        Thread[] threads2 = new Thread[1000];

        for (int i = 0; i < threads2.length; i++) {
            threads2[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    count2.incrementAndGet();
                }
            });
        }

        long start2 = System.currentTimeMillis();

        Stream.of(threads2).forEach((x) -> x.start());
        Stream.of(threads2).forEach((x) -> {
            try {
                x.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end2 = System.currentTimeMillis();
        System.out.println("AtomicLong: " + count2 + " time " + (end2 - start2));

        //*******************************************************************//
        Thread[] threads3 = new Thread[1000];
        for (int i = 0; i < threads3.length; i++) {
            threads3[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    count3.increment();
                }
            });
        }

        long start3 = System.currentTimeMillis();

        Stream.of(threads3).forEach((x) -> x.start());
        Stream.of(threads3).forEach((x) -> {
            try {
                x.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end3 = System.currentTimeMillis();
        System.out.println("LongAdder: " + count3 + " time " + (end3 - start3));
    }

}

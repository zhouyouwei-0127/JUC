package com.awei.juc.c_006_00_AtomicXX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T01_AtomicInteger {

    AtomicInteger count = new AtomicInteger(0);

    //int count = 0;

    void m() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();
            //count++;
        }
    }

    public static void main(String[] args) {
        T01_AtomicInteger t = new T01_AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m));
        }

        threads.forEach((e) -> e.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}

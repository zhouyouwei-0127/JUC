package com.awei.juc.c_007;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport
 */
public class T_14TestLockSupport {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(i);
                    TimeUnit.SECONDS.sleep(1);

                    if (i == 5) {
                        /**
                         *调用此方法，线程将被阻塞
                         * 内部调用的时unsafe类的park()
                         */
                        LockSupport.park();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        try {
            TimeUnit.SECONDS.sleep(8);
            //唤醒t线程
            LockSupport.unpark(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

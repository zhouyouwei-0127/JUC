package com.awei.juc.c_007;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯
 */
public class T_10SemaphoreTest {

    public static void main(String[] args) {
        //创建一个信号灯，内部设置两个信号,可以设置为公平锁
        Semaphore s = new Semaphore(2,true);

        new Thread(() -> {
            try {
                //获得信号灯中的信号，获得之后信号量减一，如果没有信号量，线程将阻塞在这里
                s.acquire();

                System.out.println("t1 running");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("t1 running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放信号量，信号灯中的信号量加一
                s.release();
            }
        }).start();

        new Thread(() -> {
            try {
                //获得信号灯中的信号，获得之后信号量减一，如果没有信号量，线程将阻塞在这里
                s.acquire();

                System.out.println("t2 running");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("t2 running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放信号量，信号灯中的信号量加一
                s.release();
            }
        }).start();
    }
}

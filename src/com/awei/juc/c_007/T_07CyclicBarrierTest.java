package com.awei.juc.c_007;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T_07CyclicBarrierTest {

    public static void main(String[] args) {

        /**
         * 创建一个栅栏，第一个参数为等待的线程数，表示有多少个线程之后再执行操作
         * 第二个参数是一个Runnable接口，在线程数满了之后执行这个接口中的代码
         */
        CyclicBarrier barrier = new CyclicBarrier(50, () -> System.out.println("满人，发车"));

        //循环了100次，所以输出5次
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

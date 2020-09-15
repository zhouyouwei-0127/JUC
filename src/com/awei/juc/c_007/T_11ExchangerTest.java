package com.awei.juc.c_007;

import java.util.concurrent.Exchanger;

/**
 * 交换器
 */
public class T_11ExchangerTest {

    //相当于一个容器，内部有两个格子，用来交换数据
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String s = "t1";
            try {
                //阻塞方法，当另一个线程调用此方法时，交换数据后两个线程再继续往后执行
                exchanger.exchange(s);
                System.out.println(Thread.currentThread().getName() + " " + s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(() -> {
            String s = "t2";
            try {
                exchanger.exchange(s);
                System.out.println(Thread.currentThread().getName() + " " + s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();
    }
}

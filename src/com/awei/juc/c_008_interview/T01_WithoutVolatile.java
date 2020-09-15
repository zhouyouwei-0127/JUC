package com.awei.juc.c_008_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T01_WithoutVolatile {

    /**
     * 不加volatile的时候，t2线程得不到通知，会一直进行while循环
     * 加上volatile之后t2也不一定能得到通知，
     * 因为volatile修饰的是引用类型，但是改变的是对象中的属性，引用并没有发生改变
     */
    volatile List list = new ArrayList();

    void add(Object o) { list.add(o); }

    int size() { return list.size(); }

    public static void main(String[] args) {
        T01_WithoutVolatile t = new T01_WithoutVolatile();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t.add(new Object());
                System.out.println("add " + i);

                //此处加上sleep,t2就会得到通知。
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(() -> {
            while (true) {
                if (t.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 end");
        },"t2").start();
    }
}

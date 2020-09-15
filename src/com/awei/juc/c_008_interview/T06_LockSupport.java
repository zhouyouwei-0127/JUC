package com.awei.juc.c_008_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class T06_LockSupport {

    static List list = new ArrayList();

    void add(Object o) { list.add(o); }

    int size() { return list.size(); }

    static Thread t2 = null, t1 = null;

    public static void main(String[] args) {
        T06_LockSupport t = new T06_LockSupport();

        t2 = new Thread(() -> {
            System.out.println("t2 启动");
            //阻塞t2
            LockSupport.park();
            System.out.println("t2 结束");
            //唤醒t1
            LockSupport.unpark(t1);
        },"t2");

        t1 = new Thread(() -> {
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                t.add(new Object());
                System.out.println("add " + i);
                if (t.size() == 5) {
                    //唤醒t2
                    LockSupport.unpark(t2);
                    //阻塞t1
                    LockSupport.park();
                }
            }
        },"t1");

        t2.start();
        t1.start();
    }
}

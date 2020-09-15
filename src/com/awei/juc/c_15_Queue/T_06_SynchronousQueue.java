package com.awei.juc.c_15_Queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 是一个不存在的queue，用于线程间交换数据
 * 跟exchanger效果一样，但是比exchanger更方便
 */
public class T_06_SynchronousQueue {
    
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();
        
        new Thread(() -> {
            try {
                //阻塞的取值方法
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        
        //两次输出长度都是0
        System.out.println(strs.size());

        /**
         * 由于queue的容量为0.所以在put的时候就会阻塞，直到另一个线程取走这个数
         * 相当于线程拿着一个值等着另一个线程来取
         */
        strs.put("aaa");
        System.out.println(strs.size());
    }
}

package com.awei.juc.c_15_Queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class T_03_ArrayBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        //必须给定长度
        BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }
        
        //队列满了之后会阻塞
        strs.put("a");
        //满了之后add会抛异常
//        strs.add("a");
        //满了之后不会抛异常，也不会阻塞程序，会有返回值判断是否成功
//       strs.offer("a");
    }
}

package com.awei.juc.c_15_Queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T_01_ConcurrentQueue {
    
    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            queue.offer("a" + i);
        }
        
        System.out.println(queue);
        System.out.println(queue.size());
        
        //queue.poll()取出第一个值并从队列中移除
        System.out.println(queue.poll());
        System.out.println(queue.size());
        
        //queue.peek()取出第一个值不移除
        System.out.println(queue.peek());
        System.out.println(queue.size());
    }
}

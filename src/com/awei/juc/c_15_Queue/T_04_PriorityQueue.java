package com.awei.juc.c_15_Queue;

import java.util.PriorityQueue;

/**
 * 内部用树形结构做了排序
 */
public class T_04_PriorityQueue {

    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue();

        q.add("c");
        q.add("e");
        q.add("a");
        q.add("d");
        q.add("z");

        for (int i = 0; i < 5; i++) {
            System.out.println(q.poll());
        }
    }
}

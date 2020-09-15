package com.awei.juc.c_17_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T11_WorkStealingPool {
    
    public static void main(String[] args) {
        ExecutorService service = Executors.newWorkStealingPool();
    }
}

package com.awei.juc.c_17_ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T04_FutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> ft = new FutureTask(() ->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });
        
        new Thread(ft).start();

        System.out.println(ft.get());
    }
}


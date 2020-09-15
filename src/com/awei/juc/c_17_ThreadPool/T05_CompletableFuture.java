package com.awei.juc.c_17_ThreadPool;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class T05_CompletableFuture {

    public static void main(String[] args) throws Exception {
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() -> priceOfTM());
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(() -> priceOfTB());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() -> priceOfJD());
        
        //allOf-将多个CompletableFuture进行统一管理，等待全部放回数据
        CompletableFuture.allOf(futureTM, futureTB, futureJD).join();
        //anyOf-任意一个CompletableFuture返回数据就可以
        CompletableFuture.anyOf(futureTM, futureTB, futureJD).join();

        CompletableFuture.supplyAsync(() -> priceOfJD())
                //返回结果之后进行其它的操作
                .thenApply(String::valueOf)
                .thenApply(str -> "price : " + str)
                .thenAccept(System.out::println);
        
        //thenApply为异步方法，阻塞住线程等待其执行完成
        System.in.read();
    }

    private static double priceOfTM() {
        delay();
        return 1.00;
    }

    private static double priceOfTB() {
        delay();
        return 2.00;
    }

    private static double priceOfJD() {
        delay();
        return 3.00;
    }

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}

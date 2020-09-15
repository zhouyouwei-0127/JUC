package com.awei.juc.c_17_ThreadPool;

import java.util.concurrent.*;

public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello callable";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        //将此任务交给线程池去执行-submit为异步方法
        Future<String> future = service.submit(c);
        
        //get为阻塞方法
        System.out.println(future.get());
        
        service.shutdown();
    }
}

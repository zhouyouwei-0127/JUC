package com.awei.juc.c_17_ThreadPool;

import java.io.IOException;
import java.util.concurrent.*;

public class T06_HelloThreadPool {
    
    static class Task implements Runnable {
        
        private int i;

        public Task(int i) {
            this.i = i;
        }
        
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task " + i);
            try {
                //每个线程都阻塞
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
        
    }

    public static void main(String[] args) {
        /**
         * 线程池的七个参数：
         * 1、核心线程数
         * 2、最大线程数
         * 3、线程存活时间 4、线程存活时间单位
         * 5、存放等待线程的队列
         * 6、生产线程的工厂
         * 7、拒绝策略
         */
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2,4,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 8; i++) {
            tpe.execute(new Task(i));
        }
        
        //tpe.getQueue()-获取该线程池的等待队列
        System.out.println(tpe.getQueue());
        
        //将该任务交给线程池执行
        tpe.execute(new Task(100));

        System.out.println(tpe.getQueue());
        
        //关闭线程池
        tpe.shutdown();
    }
}

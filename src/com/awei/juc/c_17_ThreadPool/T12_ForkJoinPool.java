package com.awei.juc.c_17_ThreadPool;

import com.awei.juc.c_001.T;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class T12_ForkJoinPool {

    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for(int i=0; i<nums.length; i++) {
            nums[i] = r.nextInt(100);
        }

        System.out.println("---" + Arrays.stream(nums).sum()); //stream api
    }
    
    //定义没有返回值的任务-RecursiveAction继承
    static class Task extends RecursiveAction {
        
        int start, end;

        public Task(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            long sum = 0L;
            if (end - start <= MAX_NUM) {
                for (int i = 0; i < nums.length; i++) sum += nums[i];
                System.out.println("from:" + start + " to:" + end + " = " + sum);
            } else {
                //任务拆分
                int middle = start + (end - start) / 2;
                Task task1 = new Task(start, middle);
                Task task2 = new Task(middle, end);

                task1.fork();
                task2.fork();
            }
        }
    }
    
    //定义有返回值的任务
    static class TaskRer extends RecursiveTask<Long> {

        int start, end;

        public TaskRer(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if(end-start <= MAX_NUM) {
                long sum = 0L;
                for(int i=start; i<end; i++) sum += nums[i];
                return sum;
            }

            int middle = start + (end-start)/2;

            TaskRer subTask1 = new TaskRer(start, middle);
            TaskRer subTask2 = new TaskRer(middle, end);
            subTask1.fork();
            subTask2.fork();
            
            //join是将结果汇总
            return subTask1.join() + subTask2.join();
        }
    }
    
    public static void main(String[] args) throws IOException {
        /*ForkJoinPool fjp = new ForkJoinPool();
        Task task = new Task(0, nums.length);
        fjp.execute(task);

        System.in.read();*/

        ForkJoinPool fjp = new ForkJoinPool();
        TaskRer taskRer = new TaskRer(0, nums.length);
        fjp.execute(taskRer);
        Long join = taskRer.join();
        System.out.println(join);
    }
}

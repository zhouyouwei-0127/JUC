package com.awei.juc.c_15_Queue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 内部用PriorityQueue实现的
 */
public class T_05_DelayQueue implements Delayed {

    String name;
    long runningTime;

    public T_05_DelayQueue(String name, long runningTime) {
        this.name = name;
        this.runningTime = runningTime;
    }

    static BlockingQueue<T_05_DelayQueue> tasks = new DelayQueue<>();

    //拿到delay的时间
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }
    
    //自定义排序规则-任务在队列中的先后顺序
    @Override
    public int compareTo(Delayed o) {
        if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
            return -1;
        else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "T_05_DelayQueue{" +
                "name='" + name + '\'' +
                ", runningTime=" + runningTime +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        T_05_DelayQueue t1 = new T_05_DelayQueue("t1", now + 1000);
        T_05_DelayQueue t2 = new T_05_DelayQueue("t2", now + 2000);
        T_05_DelayQueue t3 = new T_05_DelayQueue("t3", now + 1500);
        T_05_DelayQueue t4 = new T_05_DelayQueue("t4", now + 2500);
        T_05_DelayQueue t5 = new T_05_DelayQueue("t5", now + 500);

        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);

        System.out.println(tasks);
        
        //会按照时间排序输出
        for(int i=0; i<5; i++) {
            System.out.println(tasks.take());
        }
    }
}

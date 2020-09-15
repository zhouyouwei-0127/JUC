package com.awei.juc.c_009_interview;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用reentrantLock的condition 可以指定唤醒某个类型的线程
 * 底层是两个等待队列，生产者和消费者分别位于不同的等待队列中等待，唤醒的时候唤醒执行的等待队列中的线程即可
 * @param <T>
 */
public class MyContainer2<T> {

    final private LinkedList<T> list = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    Lock lock = new ReentrantLock();
    Condition consumer = lock.newCondition();
    Condition producer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while (list.size() == MAX) {
                //阻塞住所有的消费者线程--进入consumer队列中等待
                consumer.await();
            }
            list.add(t);
            ++count;
            //唤醒所有的生产者线程--producer队列中的所有线程
            producer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (list.size() == 0) {
                //所有的线程进入producer队列中等待
                producer.await();
            }
            --count;
            t = list.removeFirst();
            //唤醒consumer队列中的所有等待的线程
            consumer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        MyContainer2<String> c = new MyContainer2();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println("get " + c.get());
                }
            },"c" + i).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            },"p" + i).start();
        }
    }
}

package com.awei.juc.c_003;

import java.util.concurrent.TimeUnit;

/**
 * 加锁方法和不加锁方法可以同时执行
 */
public class Account {

    String name;
    double balance;

    public synchronized void setBalance() {
        name = "张三";
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balance = 100;
    }

    public void getBalance() {
        System.out.println(name + "    " + balance);
    }

    public static void main(String[] args) {
        Account a = new Account();
        new Thread(a::setBalance).start();
        new Thread(a::getBalance).start();
    }
}

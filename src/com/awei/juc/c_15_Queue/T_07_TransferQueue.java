package com.awei.juc.c_15_Queue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class T_07_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        
        TransferQueue<String> strs = new LinkedTransferQueue<String>();
        
        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        
        //一个线程往queue中装了东西后阻塞住，当有其它线程取走此数据之后再继续运行
        strs.transfer("aaa");
    }
}

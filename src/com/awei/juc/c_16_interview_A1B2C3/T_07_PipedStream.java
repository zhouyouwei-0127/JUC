package com.awei.juc.c_16_interview_A1B2C3;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 用于线程间通讯、一般不用
 */
public class T_07_PipedStream {

    public static void main(String[] args) throws IOException {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        /**
         * 每个线程有一个读的流一个写的流
         */
        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();

        /**
         * 线程读的流和另一个线程写的流需要连接
         */
        input1.connect(output2);
        input2.connect(output1);
        
        String msg = "Your Turn";
        
        new Thread(() -> {
            byte[] buffer = new byte[9];

            try {
                for (char c : aI) {
                    input1.read(buffer);

                    System.out.println(c);
                    
                    output1.write(msg.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        
        new Thread(() -> {
            byte[] buffer = new byte[9];

            try {
                for (char c : aC) {
                    System.out.println(c);
                    output2.write(msg.getBytes());
                    input2.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

package com.awei.juc.c_12_RefTypeAndThreadLocal;

import java.io.IOException;

/**
 * 普通的引用就是强引用，强引用指向的对象是不会被gc回收的
 */
public class T_01_NormalReference {

    public static void main(String[] args) throws IOException {
        M m = new M();
        //m设为空之后会被gc回收
        //m = null;
        System.gc();

        System.in.read();
    }
}

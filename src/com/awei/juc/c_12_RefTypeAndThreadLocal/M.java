package com.awei.juc.c_12_RefTypeAndThreadLocal;

public class M {

    /**
     * 在此对象被gc回收时会调用此方法
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}

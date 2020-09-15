package com.awei.juc.test;

public class A {

    public synchronized void m() {
        System.out.println(this);
    }
}

class B extends A {
    @Override
    public synchronized void m() {
        super.m();
        System.out.println(this);
        System.out.println(this);
    }

    public static void main(String[] args) {
        new B().m();
    }
}

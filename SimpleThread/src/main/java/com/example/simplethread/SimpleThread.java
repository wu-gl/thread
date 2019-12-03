package com.example.simplethread;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleThread {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    static int a = 0;

    public void add1() {
        System.out.println("a value is :" + ++a);
    }

    public synchronized void add2() {
        System.out.println("a value is :" + ++a);
    }

    public void add3() {
        System.out.println("a value is :" + atomicInteger.addAndGet(1));
    }

    public synchronized void add4() {
        try {
            System.out.println(Thread.currentThread().getName() + " a value is :" + ++a);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " complete :" + a);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    public static synchronized void add5() {
        try {
            System.out.println(Thread.currentThread().getName() + " a value is :" + ++a);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " complete :" + a);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public synchronized void add6() {
        try {
            System.out.println(Thread.currentThread().getName() + " a value is :" + ++a);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " complete :" + a);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void add7() {
        synchronized (lock1) {
            try {
                System.out.println(Thread.currentThread().getName() + " a value is :" + ++a);
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " complete :" + a);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public void add8() {
        synchronized (lock2) {
            try {
                System.out.println(Thread.currentThread().getName() + " a value is :" + ++a);
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " complete :" + a);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
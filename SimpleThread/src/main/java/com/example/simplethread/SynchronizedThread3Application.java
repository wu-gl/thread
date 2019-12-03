package com.example.simplethread;

public class SynchronizedThread3Application {


    public static void main(String[] args) {
        new Thread(() -> SimpleThread.add5(), "Thread1").start();
        new Thread(() -> SimpleThread.add5(), "Thread2").start();

    }
}


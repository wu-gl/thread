package com.example.simplethread;

public class SynchronizedThread1Application {


    public static void main(String[] args) {
        SimpleThread simpleThread = new SimpleThread();
        new Thread(() -> simpleThread.add7(), "Thread1").start();
        new Thread(() -> simpleThread.add8(), "Thread2").start();
    }
}


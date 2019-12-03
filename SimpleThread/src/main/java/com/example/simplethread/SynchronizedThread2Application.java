package com.example.simplethread;

public class SynchronizedThread2Application {


    public static void main(String[] args) {
        SimpleThread simpleThread = new SimpleThread();
        SimpleThread simpleThread1 = new SimpleThread();
        new Thread(() -> simpleThread.add4(), "Thread1").start();
        new Thread(() -> simpleThread1.add4(), "Thread2").start();
    }
}
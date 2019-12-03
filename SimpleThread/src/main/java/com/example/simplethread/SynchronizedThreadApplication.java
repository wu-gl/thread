package com.example.simplethread;

public class SynchronizedThreadApplication {


    public static void main(String[] args) {
        SimpleThread simpleThread = new SimpleThread();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    simpleThread.add2();
                }
            });
            thread.start();
        }

    }


}


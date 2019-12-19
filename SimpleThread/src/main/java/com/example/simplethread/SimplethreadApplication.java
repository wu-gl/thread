package com.example.simplethread;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

public class SimplethreadApplication {


    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
            final CountDownLatch countDownLatch = new CountDownLatch(10000);
            SimpleThread simpleThread = new SimpleThread();
            for (int i = 0; i < 10000; i++) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        simpleThread.add2();
                        countDownLatch.countDown();
                    }
                });
                thread.start();
            }
            countDownLatch.await();
            System.out.println(String.format("SimplethreadApplication total use : %s毫秒", (System.currentTimeMillis() - startTime)));
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }



}


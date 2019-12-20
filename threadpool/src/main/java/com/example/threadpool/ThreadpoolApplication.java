package com.example.threadpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.nio.ch.ThreadPool;

import java.util.Date;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class ThreadpoolApplication {

    public static void main(String[] args) {

        final FixedSizeThreadPool fixedSizeThreadPool = new FixedSizeThreadPool(3, 5);

        for (int i = 0; i < 20; i++) {
            try {
                fixedSizeThreadPool.submit(() -> {
                    try {
                        System.out.println(System.currentTimeMillis());
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        fixedSizeThreadPool.shutDown();
        // 用于计数线程是否执行完成
        try {
            //threadPool.scheduleAtFixedRate(,10,1,)
            //ExecutorService threadPool = new ThreadPoolExecutor(1, 5, 0, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(3), Executors.privilegedThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

            CountDownLatch countDownLatch = new CountDownLatch(20);
            ExecutorService threadPool = Executors.newWorkStealingPool();

            for (int i = 0; i < 20; i++) {
                try {
                    threadPool.execute(() -> {
                        try {
                            countDownLatch.countDown();
                            System.out.println(System.currentTimeMillis());
                            Thread.sleep(1000);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            countDownLatch.await();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2);
//
//        threadPool.scheduleAtFixedRate(new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println(new Date().getSeconds());
//
//            }
//        }, 10, 3, TimeUnit.SECONDS);

        //     ExecutorService cachedThreadPool = newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            try {
//                Thread.sleep(index * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        //cachedThreadPool.execute(new Runnable() {
//
//                @Override
//                public void run() {
//                    // TODO Auto-generated method stub
//                    System.out.println(index+":"+new Date().getSeconds());
//                }
//            });
//        }


    }

}

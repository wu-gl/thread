package com.example.threadpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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


    }

}

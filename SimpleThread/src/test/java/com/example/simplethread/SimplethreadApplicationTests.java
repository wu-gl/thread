package com.example.simplethread;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import java.util.concurrent.CountDownLatch;

//@SpringBootTest
@TestComponent
class SimplethreadApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void main() throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        SimpleThread simpleThread = new SimpleThread();
        new Thread(() -> {
            simpleThread.add4();
            countDownLatch.countDown();
        }, "Thread1").start();
        new Thread(() -> {
            simpleThread.add4();
            countDownLatch.countDown();
        }, "Thread2").start();
        countDownLatch.await();
    }

    @Test
    public void main1() {
        SimpleThread simpleThread = new SimpleThread();
        SimpleThread simpleThread1 = new SimpleThread();
        new Thread(() -> simpleThread.add4(), "Thread1").start();
        new Thread(() -> simpleThread1.add4(), "Thread2").start();
    }

    @Test
    public void main2() {
        new Thread(() -> SimpleThread.add5(), "Thread1").start();
        new Thread(() -> SimpleThread.add5(), "Thread2").start();
    }
}

package com.example.forkjoinpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ForkjoinpoolApplication {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        PrintAction printAction = new PrintAction(1, 100);
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(printAction);
        //线程阻塞，等待所有任务完成
        pool.awaitTermination(2, TimeUnit.SECONDS);
        pool.shutdown();

//        PrintTask printTask = new PrintTask(1, 100);
//        ForkJoinPool pool = new ForkJoinPool();
//        pool.submit(printTask);
//        System.out.println( printTask.get());
        //线程阻塞，等待所有任务完成
//        pool.awaitTermination(2, TimeUnit.SECONDS);
//        pool.shutdown();

    }
}

class PrintAction extends RecursiveAction {
    private final int THRESHOLD = 50; //最多只能打印50个数
    private int start;
    private int end;
    public AtomicInteger atomicInteger = new AtomicInteger(0);

    public PrintAction(int start, int end) {
        super();
        this.start = start;
        this.end = end;
    }


    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                atomicInteger.addAndGet(i);
                System.out.println(Thread.currentThread().getName() + "的i值：" + i);
                System.out.println(Thread.currentThread().getName() + "的atomicInteger值：" + atomicInteger);
            }
        } else {
            int middle = (start + end) / 2;
            PrintAction left = new PrintAction(start, middle);
            PrintAction right = new PrintAction(middle, end);
            //并行执行两个“小任务”
            left.fork();
            right.fork();
        }

    }

}

class PrintTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 50; //最多只能打印50个数
    private int start;
    private int end;


    public PrintTask(int start, int end) {
        super();
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += i;
                System.out.println(Thread.currentThread().getName() + "的i值：" + i);
            }
            return sum;
        } else {
            int middle = (start + end) / 2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            //并行执行两个“小任务”
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }
}


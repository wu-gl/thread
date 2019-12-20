package com.example.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Description FixedSizeThreadPool
 * @Author 吴桂林
 * @Date 2019/12/19 17:17
 * @Version 1.0
 */
public class FixedSizeThreadPool {

    /**
     * 创建线程池
     *
     * @param poolsize 核心线程数
     * @param taskSize 最大可排队的任务数
     */
    public FixedSizeThreadPool(int poolsize, int taskSize) {
        this.blockingQueue = new LinkedBlockingDeque<>(taskSize);
        workers = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < poolsize; i++) {
            Worker worker = new Worker(this);
            workers.add(worker);
            worker.start();
        }
    }

    public boolean submit(Runnable runnable) throws Exception {
        if (isWorking) {
            return this.blockingQueue.offer(runnable);
        }
        throw new Exception("下班了");
    }

    public void shutDown() {
        isWorking = false;
        //当然除了添加isWorking停止标志位，还可以强制关闭线程
        //if (force) {
        //    workers.forEach(d -> d.stop());
        //}
    }

    //是否工作，用于shutDown。
    public volatile boolean isWorking = true;

    //待执行任务队列，所有的执行请求都进入队列
    private BlockingQueue<Runnable> blockingQueue;
    //根据poolsize创建核心线程，不会释放
    private List<Thread> workers;

    public static class Worker extends Thread {
        private FixedSizeThreadPool pool;

        public Worker(FixedSizeThreadPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            while (pool.isWorking || this.pool.blockingQueue.size() > 0) {
                Runnable task = null;
                try {
                    if (pool.isWorking) {
                        task = this.pool.blockingQueue.take(); //阻塞的模式拿取数据
                    } else {
                        task = this.pool.blockingQueue.poll(); //非阻塞的模式拿取数据
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (task != null) {
                    task.run();
                }
            }
        }
    }
}

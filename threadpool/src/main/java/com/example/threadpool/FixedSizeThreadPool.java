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

    }

    public volatile boolean isWorking = true;

    private BlockingQueue<Runnable> blockingQueue;
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

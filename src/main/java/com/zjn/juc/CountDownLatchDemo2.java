package com.zjn.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 设置 CountDownLatch 等待线程数为 1
 * 开启 10 个线程，每个线程都会调用 CountDownLatch 的 await() 方法，这样每个线程都会被阻塞
 * 主线程休眠 1 秒后，调用 CountDownLatch 的 countDown() 方法，调用后就会唤醒所有等待的线程，所有等待的线程就会同时执行
 */
public class CountDownLatchDemo2 {

    static class TaskThread extends Thread {

        CountDownLatch latch;

        public TaskThread(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {

            try {
                latch.await();
                System.out.println(getName() + " start " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {

        int threadNum = 10;
        CountDownLatch latch = new CountDownLatch(1);

        for(int i = 0; i < threadNum; i++) {
            TaskThread task = new TaskThread(latch);
            task.start();
        }

        Thread.sleep(1000);
        latch.countDown();

    }

}

package com.zjn.juc;

import java.util.concurrent.CountDownLatch;


/**
 * 设置 CountDownLatch 的等待线程数为 10
 * 开启 10 个线程，每个线程都会睡眠 1 秒，睡眠结束后就会调用 CountDownLatch 的 countDown() 方法
 * 主线程调用 CountDownLatch 的 await() 方法，所以会开始阻塞，直到 CountDownLatch 的 count 为 0 才继续执行
 */
public class CountDownLatchDemo {

    static class TaskThread extends Thread {

        CountDownLatch latch;

        public TaskThread(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(getName() + " Task is Done");
                latch.countDown();
            }


        }

    }

    public static void main(String[] args) throws InterruptedException {

        int threadNum = 10;
        CountDownLatch latch = new CountDownLatch(threadNum);

        for(int i = 0; i < threadNum; i++) {
            TaskThread task = new TaskThread(latch);
            task.start();
        }

        System.out.println("Task Start!");

        latch.await();

        System.out.println("All Task is Done!");
    }

}

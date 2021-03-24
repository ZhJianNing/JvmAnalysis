package com.zjn.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * AqsTest
 *
 * @author zjn
 * @date 2021/3/24
 **/
public class AqsTest {

    public static int number = 1;
    static ReentrantLock lock = new ReentrantLock(false);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    lockTest();
                }
                System.out.println(Thread.currentThread() + "结束！");
            }
        }, "Thread-1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    lockTest();
                }
                System.out.println(Thread.currentThread() + "结束！");
            }
        }, "Thread-2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    lockTest();
                }
                System.out.println(Thread.currentThread() + "结束！");
            }
        }, "Thread-3").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    lockTest();
                }
                System.out.println(Thread.currentThread() + "结束！");
            }
        }, "Thread-4").start();

//        Thread.sleep(3000);
//        System.out.println(number);


    }

    public static void lockTest() {

        lock.lock();
        number++;
        lock.unlock();
    }
}

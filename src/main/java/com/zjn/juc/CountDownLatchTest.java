package com.zjn.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch f = new CountDownLatch(5);
        f.countDown();

        f.await();
    }
}

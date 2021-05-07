package com.zjn.juc;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        Semaphore h = new Semaphore(4);
        h.acquire();
        h.release();
    }
}

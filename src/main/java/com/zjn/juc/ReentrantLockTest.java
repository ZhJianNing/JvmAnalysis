package com.zjn.juc;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        ReentrantLock lock1 = new ReentrantLock(true);
        lock.lock();
        lock.unlock();
    }
}

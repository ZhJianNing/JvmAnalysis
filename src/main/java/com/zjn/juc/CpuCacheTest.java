package com.zjn.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CpuCacheTest  验证主存修改后，修改对其他线程的可见性
 *
 * 常见一级缓存、二级缓存、三级缓存，这些缓存的数据获取访问速度如下：
 * 从CPU到	        大约需要的CPU 周期	    大约需要的时间
 * =======================================================
 * 主存	 	        约60-80纳秒
 * QPI 总线传输     约20ns
 * L3 cache	        约40-45 cycles           约15ns
 * L2 cache	        约10 cycles              约3ns
 * L1 cache	        约3-4 cycles             约1ns
 * 寄存器	        1 cycle
 *=========================================================
 * @author zjn
 * @date 2021/3/24
 **/
public class CpuCacheTest {

//    private static volatile boolean ready;
    private static boolean ready;

    private static java.util.concurrent.atomic.AtomicInteger number = new AtomicInteger(0);

    public static void main(String[] args) {
        int availProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("cpu核数: " + availProcessors);
        for (int i = 0; i < 20; i++) {
            new ReaderThread("Thread-" + i).start();
        }
        ready = true;
    }

    public static class ReaderThread extends Thread {

        public ReaderThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (!ready) {
                //Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + "：number =" + number.incrementAndGet());
        }
    }
}
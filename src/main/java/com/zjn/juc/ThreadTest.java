package com.zjn.juc;

public class ThreadTest {
    public static void main(String[] args) throws Exception{

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        }).start();

    }
}

package com.zjn.juc;

import java.util.concurrent.Exchanger;

public class ExchangerTest {

    public static void main(String[] args) throws InterruptedException {

        Exchanger<String> i = new Exchanger<String>();
        i.exchange("");
    }
}

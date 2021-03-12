package com.zjn.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMapAnalysis
 *
 * @author zjn
 * @date 2020/8/12
 **/
public class HashMapAnalysis {
    public static void main(String[] args) {
        Map<String, String> test = new HashMap<>(3);
        test.put("1", "1");
        test.put("2", "2");
        test.put("3", "3");
        test.put("4", "4");
        test.put("5", "5");
        test.put("6", "6");
        test.put("7", "7");
        test.put("8", "8");
        test.put("9", "9");
        test.put("10", "10");
    }
}

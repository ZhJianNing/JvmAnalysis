package com.zjn.jvm;

/**
 * StringUtil
 *
 * @author zjn
 * @date 2020/7/1
 **/
public class StringUtil {
    public static void main(String[] args) {
        String prefixTid = String.format("%08x", 16);
        System.out.println(prefixTid);
    }

}

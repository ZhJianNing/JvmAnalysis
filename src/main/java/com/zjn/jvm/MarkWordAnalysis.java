package com.zjn.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * MarkWordAnalysis
 *
 * 观察对象头在上锁和不上锁的情况对比
 *
 * @author zjn
 * @date 2020/8/18
 **/
public class MarkWordAnalysis {
    public static void main(String[] args) {
        Object o = new Object();
        String os = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(os);

        //只有调用了hashCode方法，markword中才会真正存储hashCode
        o.hashCode();
        String osh = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(osh);

        synchronized (o){
            String oss = ClassLayout.parseInstance(o).toPrintable();
            System.out.println(oss);
        }
    }
}

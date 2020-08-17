package com.zjn.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * ObjectMemory  分析对象占用内存大小
 *
 * @author zjn
 * @date 2020/8/17
 **/
public class ObjectMemory {
    String test;
    String test2;

    public static void main(String[] args) {
        System.out.println("不带成员变量对象");
        Object o = new Object();
        String os = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(os);

        System.out.println("带成员变量对象");
        ObjectMemory objectMemory = new ObjectMemory();
        String oobjectMemory = ClassLayout.parseInstance(objectMemory).toPrintable();
        System.out.println(oobjectMemory);

        System.out.println("数组对象【0】");
        String[] a0 = new String[0];
        String a0s = ClassLayout.parseInstance(a0).toPrintable();
        System.out.println(a0s);

        System.out.println("数组对象【1】");
        String[] a1 = new String[1];
        String a1s = ClassLayout.parseInstance(a1).toPrintable();
        System.out.println(a1s);

        System.out.println("数组对象【2】");
        String[] a2 = new String[2];
        String a2s = ClassLayout.parseInstance(a2).toPrintable();
        System.out.println(a2s);

        System.out.println("数组对象【2】带元素");
        String[] a2son = new String[5];
        //5个元素指针占20个字节（无论是否存储值）
//        a2son[0] = "1";
//        a2son[1] = "2";
//        a2son[2] = "3";
//        a2son[3] = "4";
//        a2son[4] = "5";
        String a2sson = ClassLayout.parseInstance(a2son).toPrintable();
        System.out.println(a2sson);
    }

}

package com.zjn.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * ObjectMemory  分析对象占用内存大小
 *
 * 对象头在32位系统上占用8bytes，64位系统上占用16bytes，【开启（-XX:+UseCompressedOops）对象头大小为12bytes（64位机器）】，
 * 数组对象的对象头占用24个字节，启用压缩之后占用16个字节
 * 在idea启动jvm里配置-XX:+UseCompressedOops或-XX:-UseCompressedOops来测试
 *
 * 对象头由三部分组成：
 *          1、MarkWord（标记字段）：存储了哈希值、GC分代年龄、锁状态标志、线程持有锁、偏向线程ID、偏向时间戳；
 *          2、Class Pointer：存储了实例所属类的元数据指针；
 *          3、数组长度：【数组对象特有】，所以数组对象头比一般对象头长。
 *
 * 当关闭指针压缩时：
 *          会多出一部分(alignment/padding gap)  ，这部分是原生类型的8字节对齐（内部对齐）；
 *          关闭指针压缩，会出现内部8字节对齐【对象头八字节对齐（数组类型出现）、实例数据八字节对齐（前两部分对齐后，其实就不需要padding外部对齐了，所以外部对齐都是0）】【测试结论，待理论证实】
 *
 *
 *
 * 原生类型(primitive type)的内存占用如下：
 * Primitive Type	Memory Required(bytes)
 * boolean                      	1
 * byte                            	1
 * short                           	2
 * char                            	2
 * int                              4
 * float                            4
 * long                            	8
 * double    	                    8
 * reference类型(引用类型)在32位系统上每个占用4bytes, 在64位系统上每个占用8bytes。
 *
 *
 *
 * HotSpot的对齐方式为8字节对齐：
 * （对象头 + 实例数据 + padding） % 8等于0且0 <= padding < 8
 *
 *
 *
 * @author zjn
 * @date 2020/8/17
 **/
public class ObjectMemory {

//    boolean a;
    String test;
//    String test2;

    public static void main(String[] args) {
        System.out.println("不带成员变量对象");
        Object o = new Object();
        String os = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(os);

        System.out.println("带成员变量对象");
        ObjectMemory objectMemory = new ObjectMemory();
        String oobjectMemory = ClassLayout.parseInstance(objectMemory).toPrintable();
        System.out.println(oobjectMemory);

        System.out.println("数组对象，【0】个元素");
        String[] a0 = new String[0];
        String a0s = ClassLayout.parseInstance(a0).toPrintable();
        System.out.println(a0s);

        System.out.println("数组对象，【1】个元素");
        String[] a1 = new String[1];
        String a1s = ClassLayout.parseInstance(a1).toPrintable();
        System.out.println(a1s);

        System.out.println("数组对象，【2】元素");
        String[] a2 = new String[2];
        String a2s = ClassLayout.parseInstance(a2).toPrintable();
        System.out.println(a2s);

        System.out.println("数组对象，【5】个元素");
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

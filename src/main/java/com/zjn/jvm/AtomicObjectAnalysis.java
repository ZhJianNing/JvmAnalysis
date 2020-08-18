package com.zjn.jvm;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicObjectAnalysis
 *
 * @author zjn
 * @date 2020/8/12
 **/
public class AtomicObjectAnalysis {

    public static void main(String[] args) {
        String str1 = "aaa";
        String str2 = "bbb";
        //初始版本
        AtomicStampedReference<String> reference = new AtomicStampedReference<String>(str1, 1);
        System.out.println("reference.getStamp() = " + reference.getStamp());
        System.out.println("reference.getReference() = " + reference.getReference());

        //第一次赋值
        boolean b =  reference.compareAndSet(str1, str2, reference.getStamp(), reference.getStamp() + 1);
        System.out.println("b: " + b);
        System.out.println("reference.getStamp() = " + reference.getStamp());
        System.out.println("reference.getReference() = " + reference.getReference());

        //给新值设置版本号（第二次赋值）
        boolean bb = reference.attemptStamp(str2, reference.getStamp() + 1);
        System.out.println("bb: " + bb);
        System.out.println("reference.getStamp() = " + reference.getStamp());
        System.out.println("reference.getReference() = " + reference.getReference());

        //第三次赋值
        boolean c = reference.weakCompareAndSet(str2, "ccc", 3, reference.getStamp() + 1);
        System.out.println("c: " + c);
        System.out.println("reference.getStamp() = " + reference.getStamp());
        System.out.println("reference.getReference() = " + reference.getReference());

        //第四次赋值
        boolean d = reference.weakCompareAndSet(str2, "ddd", 5, reference.getStamp() + 1);
        System.out.println("d: " + d);
        System.out.println("reference.getStamp() = " + reference.getStamp());
        System.out.println("reference.getReference() = " + reference.getReference());


//        AtomicInteger aa = new AtomicInteger();
//        aa.incrementAndGet();
//        CompareObject test2 = new CompareObject();
//        CompareObject test3 = new CompareObject();
//        AtomicStampedReference<CompareObject> bb = new AtomicStampedReference(test2, 1);
//        System.out.println(bb.compareAndSet(test2, test3, 1, 2));
    }
}

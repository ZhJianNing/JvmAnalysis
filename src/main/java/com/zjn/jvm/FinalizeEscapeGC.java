package com.zjn.jvm;

public class FinalizeEscapeGC {

    public static FinalizeEscapeGC save_self = null;

    public void isAlive() {
        System.out.println("是de :我还活着");
    }

    @Override
    protected void finalize() throws Throwable {
        //执行Object的finalize方法
        super.finalize();
        System.out.println(Thread.currentThread().getName());

        System.out.println(System.currentTimeMillis() + "Object的finalize方法第一次被执行了");
        // 将这个对象赋值给静态属性对象？？？？？
        FinalizeEscapeGC.save_self = this;
    }

    public static void main(String[] args) throws InterruptedException {

        save_self = new FinalizeEscapeGC();
        System.out.println(save_self);
        //  解除引用
        save_self = null;

        //  调用回收机制
        System.out.println(System.currentTimeMillis());
        System.gc();
        // 停一阵等待gc回收
        Thread.sleep(500l);

        if (save_self == null) {
            System.out.println(save_self);
            System.out.println("死了");
        } else {
            System.out.println(save_self);
            System.out.println("还活着");
        }

        //  第二次
        //  解除引用
        save_self = null;

        //  调用回收机制
        System.out.println(System.currentTimeMillis());
        System.gc();
        // 停一阵等待gc回收
        Thread.sleep(500l);

        if (save_self == null) {
            System.out.println(save_self);
            System.out.println("死了");
        } else {
            System.out.println(save_self);
            System.out.println("还活着");
        }


    }
}

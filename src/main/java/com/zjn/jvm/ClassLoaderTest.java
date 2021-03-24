package com.zjn.jvm;

/**
 * ClassLoaderTest
 * 这个类放到项目里，会被sun.misc.Launcher$AppClassLoader类加载器发现，不能实现在定义类加载，测试时需要把这个类先编译
 * 的class文件放到某个特定目录下，用自定义类加载器去该路径下加载类
 *
 * @author zjn
 * @date 2020/8/18
 **/
public class ClassLoaderTest {
    public static void main(String[] args) {
        new ClassLoaderTest().say();
    }
    public void say() {

        System.out.println("Hello MyClassLoader");
        System.out.println("我的类加载器是" + this.getClass().getClassLoader());
    }
}

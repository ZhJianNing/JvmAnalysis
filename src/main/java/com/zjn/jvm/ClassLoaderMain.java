package com.zjn.jvm;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassLoaderMain
 * <p>
 * 类加载机制-双亲委托机制
 * <p>
 * 　　 例如：当jvm要加载Test.class的时候，
 * 　　（1）首先会到自定义加载器中查找，看是否已经加载过，如果已经加载过，则返回字节码。
 * 　　（2）如果自定义加载器没有加载过，则询问上一层加载器(即AppClassLoader)是否已经加载过Test.class。
 * 　　（3）如果没有加载过，则询问上一层加载器（ExtClassLoader）是否已经加载过。
 * 　　（4）如果没有加载过，则继续询问上一层加载（BoopStrap ClassLoader）是否已经加载过。
 * 　　（5）如果BoopStrap ClassLoader依然没有加载过，则到自己指定类加载路径下（"sun.boot.class.path"）查看是否有Test.class字节码，有则返回，没有通
 * 知下一层加载器ExtClassLoader到自己指定的类加载路径下（java.ext.dirs）查看。
 * 　　（6）依次类推，最后到自定义类加载器指定的路径还没有找到Test.class字节码，则抛出异常ClassNotFoundException。
 *
 * @author zjn
 * @date 2020/7/24
 **/
public class ClassLoaderMain {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InstantiationException, NoSuchMethodException, SecurityException, InvocationTargetException {

        //BoopStrap ClassLoder是由C/C++编写的，它本身是虚拟机的一部分，并不是一个java类，以null来显示
        System.out.println("BootStrap ClassLoader 加载范围");
        System.out.println(System.getProperty("sun.boot.class.path"));

        System.out.println("Extention ClassLoader 加载范围");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println("AppClassLoader 加载范围");
        System.out.println("主要加载当前应用下的classpath路径下的类。之前我们在环境变量中配置的classpath就是指定AppClassLoader的类加载路径");

        System.out.println("自定义类加载器 加载范围");
        System.out.println("排除以上三个范围的范围");


        //这个类class的路径
        String myClassPath = "D:\\githubWorkspace\\JvmAnalysis\\src\\other";
        //自定义类加载器的加载路径
        MyClassLoader myClassLoader = new MyClassLoader(myClassPath);//静态内部类使用方法
//        MyClassLoader myClassLoader = new ClassLoaderMain().new MyClassLoader(myClassPath); //非静态内部类使用方法
        //包名+类名
        Class c = myClassLoader.loadClass("com.zjn.jvm.ClassLoaderTest");

        if (c != null) {
            Object obj = c.newInstance();
            Method method = c.getMethod("say", null);
            method.invoke(obj, null);
            System.out.println(c.getClassLoader().toString());
        }
    }

    //自定义类加载器
    //内部类：public class MyClassLoader （依赖外部类，不能在越过外部类直接在静态方法中使用）
    //静态内部类：public static class MyClassLoader （静态内部类的创建不需要依赖外部类）
    public static class MyClassLoader extends ClassLoader {
        //指定路径
        private String classpath;


        public MyClassLoader(String classPath) {
            classpath = classPath;
        }

        /**
         * 重写findClass方法
         *
         * @param name 是我们这个类的全路径
         * @return
         * @throws ClassNotFoundException
         */
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            Class aa = null;
            try {
                // 获取该class文件字节码数组
                byte[] classData = getData(name);

                if (classData != null) {
                    // 将class的字节码数组转换成Class类的实例
                    return defineClass(name, classData, 0, classData.length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return super.findClass(name);
        }


//      （如果希望用自己的类加载器加载类，可以在重新该方法时不去委派到父类，即去掉return super.loadClass(name)逻辑）,一般都要遵循委派父类加载器逻辑
//        @Override
//        public Class<?> loadClass(String name) throws ClassNotFoundException {
//            try {
//                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
//                InputStream is = getClass().getResourceAsStream(fileName);
//                if (is == null) {
//                    return super.loadClass(name);//此步骤是用来委派父类加载器的
//                }
//                byte[] b = new byte[is.available()];
//                is.read(b);
//                return defineClass(name, b, 0, b.length);
//            } catch (IOException e) {
//                throw new ClassNotFoundException(name);
//            }
//        }

        /**
         * 将class文件转化为字节码数组
         * 可以再getData里面做很多事情 ，比如加密解密之类的 都是可以的
         *
         * @return
         */
        private byte[] getData(String className) throws IOException {
            InputStream in = null;
            ByteArrayOutputStream out = null;
            String path = classpath + File.separatorChar +
                    className.replace('.', File.separatorChar) + ".class";
            try {
                in = new FileInputStream(path);
                out = new ByteArrayOutputStream();
                byte[] buffer = new byte[2048];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                return out.toByteArray();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
            return null;
        }
    }

}

package com.zjn.jvm;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//测试静态导包【导入内部静态类】
import static java.lang.System.out;


/**
 * ClassLoaderMain
 * <p>
 * 类加载机制-双亲委托机制
 * 双亲委派模型：如果一个类加载器收到了类加载的请求，它首先不会自己去加载这个类，而是把这个请求委派给父类加载器去完成，
 * 每一层的类加载器都是如此，这样所有的加载请求都会被传送到顶层的启动类加载器中，只有当父加载无法完成加载请求（它的搜
 * 索范围中没找到所需的类）时，子加载器才会尝试去加载类。
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

        out.println("测试静态导包");
        //BoopStrap ClassLoder是由C/C++编写的，它本身是虚拟机的一部分，并不是一个java类，以null来显示
        //留了一个自定义class的目录接口【C:\Program Files\Java\jdk1.8.0_131\jre\classes】，可以在该目录下定义某些class类，加载优先权高于其他类加载器
        System.out.println("BootStrap ClassLoader【启动类加载器】 加载范围");
        String[] bootStrapClasspath = System.getProperty("sun.boot.class.path").split(";");
        for (int i = 0; i < bootStrapClasspath.length; i++) {
            System.out.println(bootStrapClasspath[i] + existFile(bootStrapClasspath[i]));
        }
        System.out.println("");
        System.out.println("====================================================================================");
        System.out.println("");

        System.out.println("Extention ClassLoader【扩展类加载器】加载范围");
        String[] extClasspaths = System.getProperty("java.ext.dirs").split(";");
        for (int i = 0; i < extClasspaths.length; i++) {
            System.out.println(extClasspaths[i] + existFile(extClasspaths[i]));
        }
        System.out.println("也可以使用  -Xbootclasspath=xx  启动参数可以添加范围");
        System.out.println("");
        System.out.println("====================================================================================");
        System.out.println("");


        System.out.println("AppClassLoader【应用类加载器】加载范围");
        System.out.println("【注：】主要加载当前应用下的classpath路径下的类。之前我们在环境变量中配置的classpath就是指定AppClassLoader的类加载路径");
        //System.getProperty("java.class.path")这个属性是所有的类加载路径，需要排除前面两个范围，前面两个范围有可能有不存在路径，这个范围应该是去除了前两个不存在的路径
        String[] appClasspaths = System.getProperty("java.class.path").split(";");
        for (int i = 0; i < appClasspaths.length; i++) {
            //过滤掉boot和ext
            if (isExist(bootStrapClasspath, extClasspaths, appClasspaths[i])) {
                continue;
            }
            System.out.println(appClasspaths[i] + existFile(appClasspaths[i]));
        }
        System.out.println("");
        System.out.println("====================================================================================");
        System.out.println("");


        System.out.println("MyClassLoader【自定义类加载器】加载范围");
        System.out.println("排除以上三个范围的范围，创建自定义类加载器时指定的路径");
        //设置自定义类加载器，加载路径
        String myClassPath = "D:\\githubWorkspace\\JvmAnalysis\\src\\other";
        System.out.println(myClassPath);
        System.out.println("");
        System.out.println("====================================================================================");
        System.out.println("");

        //自定义类加载器的加载路径
        MyClassLoader myClassLoader = new MyClassLoader(myClassPath);//静态内部类使用方法
//        MyClassLoader myClassLoader = new ClassLoaderMain().new MyClassLoader(myClassPath); //非静态内部类使用方法
        //包名+类名
        Class c = myClassLoader.loadClass("com.zjn.jvm.ClassLoaderTest");

        if (c != null) {
            Object obj = c.newInstance();
            Method method = c.getMethod("say", null);
            method.invoke(obj, null);
            System.out.println(c.getClassLoader());
        }
    }


    private static boolean isExist(String[] bootStrapClasspath, String[] extClasspaths, String classpath) {
        for (int j = 0; j < bootStrapClasspath.length; j++) {
            if (bootStrapClasspath[j].equals(classpath)) {
                return true;
            }
        }
//        for (int j = 0; j < extClasspaths.length; j++) {
//            if (extClasspaths[j].equals(classpath)) {
//                return true;
//            }
//        }
        //C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\
        if (classpath.startsWith(System.getProperty("java.home") + File.separator + "lib" + File.separator + "ext")) {
            return true;
        }
        return false;
    }

    private static String existFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            return "";
        } else {
            return "【不存在】";
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

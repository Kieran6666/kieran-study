package com.kieran.study.reflection;

/**
 * 获取类加载器
 */
public class Demo6 {
    public static void main(String[] args) throws ClassNotFoundException {
        // AppClassLoader和SystemClassLoader是一样的
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.err.println(systemClassLoader);

        // 获取系统类加载器的父类加载器 -> 扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.err.println(extClassLoader);

        // 获取扩展类加载的父类加载起 -> 根加载器(c/c++编写的，因此获取不到)
        ClassLoader rootClassLoader = extClassLoader.getParent();
        System.err.println(rootClassLoader);

        // 测试当前类是由哪个类加载器加载的
        ClassLoader cl1 = Class.forName("com.kieran.study.reflection.Demo6").getClassLoader();
        System.err.println(cl1);

        // 测试JDK是由哪个类加载器加载的(由根加载器加载的)
        ClassLoader cl2 = Class.forName("java.lang.Object").getClassLoader();
        System.err.println(cl2);

        // 如何获取系统类加载器可以加载的路径
        String property = System.getProperty("java.class.path");
        System.err.println(property);

        // 双亲委派机制
        // 比如你现在定义好了一个java.lang.String，系统会保证安全性，先到系统类加载器中找类String，再到扩展类加载起中找，再到引导类加载起中找
        // 如果找到了那么自己定义的类String并不会被启用，而是会选择找到的类
        // 优点：1.避免了类的重复加载 2.保护了程序的安全性，防止核心API被修改

    }
}

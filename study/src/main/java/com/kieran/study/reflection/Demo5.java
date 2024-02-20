package com.kieran.study.reflection;

/**
 * 测试类什么时候会初始化
 */
public class Demo5 {
    static {
        System.err.println("main类被加载");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // 1.主动引用
        // 输出 => main类被加载 父类被加载 子类被加载
//        Son son = new Son();

        // 1.主动引用 - 反射也会产生主动引用
        // 输出 => main类被加载 父类被加载 子类被加载
//        Class<?> son = Class.forName("com.kieran.study.reflection.Son");


        // 2.被动引用 - 不会发生类的初始化 调用父类的静态变量
        // 输出 => main类被加载 父类被加载 2
//        System.err.println(Son.b);

        // 2.被动引用 - 初始化数组也不会发生类的初始化 - 此处也不会加载父类，只是给空间命名了，并不会加载一个类
        // 输出 => main类被加载
//        Son[] son = new Son[5];

        // 2.被动引用 - 常量，也不会发生类的初始化
        // 输出 => main类被加载 1
        System.err.println(Son.M);
    }
}

class Father {
    static int b = 2;
    static {
        System.err.println("父类被加载");
    }
}

class Son extends Father {
    static {
        System.err.println("子类被加载");
        index = 300;
    }

    static int index = 100;

    static final int M = 1;
}

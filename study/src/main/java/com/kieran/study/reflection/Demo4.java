package com.kieran.study.reflection;

/**
 * 类的加载顺序
 */
public class Demo4 {
    public static void main(String[] args) {
        new A();
        System.err.println(A.index);
    }
}

class A {
    /**
     * 代码按照顺序执行 该顺序的结果是 index = 100
     *
     * 赋值顺序
     * 1. index = 0
     * 2. index = 300
     * 3. index = 100
     *
     * 初始化时，JVM会执行类构造器<clinit>() 方法，过程如下：
     * <clinit>() {
     *       System.err.println("类A静态代码块初始化");
     *       index = 300;
     *       index = 100;
     * }
     */
    static {
        System.err.println("类A静态代码块初始化");
        index = 300;
    }
    static int index = 100;

    /**
     * 代码按照顺序执行 该顺序的结果是 index = 300
     *
     * 赋值顺序
     * 1. index = 0
     * 2. index = 100
     * 3. index = 300
     *
     * 初始化时，JVM会执行类构造器<clinit>() 方法，过程如下：
     * <clinit>() {
     *       index = 100;
     *       System.err.println(index);
     *       System.err.println("类A静态代码块初始化");
     *       index = 300;
     * }
     */
//    static int index = 100;
//    static {
//        System.err.println(index);
//        System.err.println("类A静态代码块初始化");
//        index = 300;
//    }



    A() {
        System.err.println("类A无参构造初始化");
    }
}

package com.kieran.study.reflection;

/**
 * 什么叫反射
 */
public class Demo1 {

    public static void main(String[] args) throws ClassNotFoundException {
        // 一个类在内存中只有一个class对象
        // 一个类被加载后，类的整个结构都会被封装在Class对象中
        Class<?> c1 = Class.forName("com.kieran.study.dto.CarDTO");
        Class<?> c2 = Class.forName("com.kieran.study.dto.CarDTO");
        Class<?> c3 = Class.forName("com.kieran.study.dto.CarDTO");

        // hashCode是相同的
        System.err.println(c1.hashCode());
        System.err.println(c2.hashCode());
        System.err.println(c3.hashCode());
    }
}

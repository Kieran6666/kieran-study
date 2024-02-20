package com.kieran.study.reflection;

import com.kieran.study.utils.TestUtils;

import java.io.Serializable;
import java.lang.annotation.ElementType;

/**
 * 获取所有类型的Class
 */
public class Demo3 {
    public static void main(String[] args) {
        Class<Object> c1 = Object.class; // 类对象
        Class<Override> c2 = Override.class;  // 注解
        Class<Serializable> c3 = Serializable.class;// 接口
        Class<Integer> c4 = Integer.class; // 基本数据类型
        Class<? extends int[]> c5 = int[].class; // 一维数组
        Class<int[][]> c6 = int[][].class; // 二维数组
        Class<ElementType> c7 = ElementType.class; // 枚举
        Class<?> c8 = Class.class; // class
        Class<Void> c9 = void.class;// void

        System.err.println(c1);
        System.err.println(c2);
        System.err.println(c3);
        System.err.println(c4);
        System.err.println(c5);
        System.err.println(c6);
        System.err.println(c7);
        System.err.println(c8);
        System.err.println(c9);

        TestUtils.println();

        // 另只要元素的类型与维度相同，就是同一个class
        int[] i1 = new int[10];
        int[] i2 = new int[100];
        System.err.println(i1.getClass().hashCode());
        System.err.println(i2.getClass().hashCode());

    }
}

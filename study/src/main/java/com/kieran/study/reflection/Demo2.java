package com.kieran.study.reflection;

import com.kieran.study.dto.CarDTO;
import com.kieran.study.dto.CarFactoryDTO;
import com.kieran.study.utils.TestUtils;

/**
 * 获取类的几种方式
 */
public class Demo2 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 1. 通过实例获取
        CarDTO carDTO = new CarDTO();
        Class<? extends CarDTO> c1 = carDTO.getClass();

        // 2. 通过类名获取
        Class<?> c2 = Class.forName("com.kieran.study.dto.CarDTO");

        // 3. 直接通过类获取
        Class<CarDTO> c3 = CarDTO.class;
        System.err.println(c1.hashCode());
        System.err.println(c2.hashCode());
        System.err.println(c3.hashCode());

        TestUtils.println();

        // 4. 基本内置类型的包装类都有一个TYPE属性
        Class<Integer> c4 = Integer.TYPE;
        System.err.println(c4.hashCode());

        TestUtils.println();

        // 5. 获取父类
        Class<?> c5 = c1.getSuperclass();
        System.err.println(c5.getName());
        System.err.println(c5.hashCode());

        Class<?> c6 = c2.getSuperclass();
        System.err.println(c6.getName());
        System.err.println(c6.hashCode());

        Class<?> c7 = c3.getSuperclass();
        System.err.println(c7.getName());
        System.err.println(c7.hashCode());

        // 6.getSuperClass和new出来的父类不是同一个父类
        CarFactoryDTO c8 = new CarFactoryDTO();
        System.err.println("----- new出来的父类");
        System.err.println(c8.hashCode());


    }
}

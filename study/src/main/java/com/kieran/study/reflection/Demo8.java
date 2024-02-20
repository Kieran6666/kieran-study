package com.kieran.study.reflection;

import com.kieran.study.dto.CarDTO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射，动态创建对象
 *
 * 通过反射，操作方法
 */
public class Demo8 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> c1 = Class.forName("com.kieran.study.dto.CarDTO");

        CarDTO dto1 = (CarDTO) c1.newInstance();
        System.err.println(dto1);
        System.err.println(dto1.hashCode());

        CarDTO dto2 = (CarDTO) c1.getConstructor(String.class, String.class, boolean.class, long.class)
                .newInstance("A", "B", false, 1);
        System.err.println(dto2);
        System.err.println(dto2.hashCode());

        CarDTO dto3 = (CarDTO) c1.getDeclaredConstructor(null).newInstance(null);
        System.err.println(dto3);
        System.err.println(dto3.hashCode());

        Method m1 = c1.getMethod("setColor", String.class);
        m1.invoke(dto3, "blue");
        System.err.println(dto3);
        System.err.println(dto3.hashCode());

        Field f1 = c1.getDeclaredField("brand");
        f1.setAccessible(true);
        f1.set(dto3, "Porsche");
        System.err.println(dto3);
        System.err.println(dto3.hashCode());

    }
}

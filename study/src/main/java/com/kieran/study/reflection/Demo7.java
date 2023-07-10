package com.kieran.study.reflection;

import com.kieran.study.utils.TestUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Demo7 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class<?> c1 = Class.forName("com.kieran.study.dto.CarDTO");

        // 获取类的名字
        System.err.println(c1.getName()); // 包名 + 类名
        System.err.println(c1.getSimpleName()); // 类名

        TestUtils.println();

        // 获取类的属性
        Field[] fields = c1.getFields(); // 只能找到public属性
        for (Field field : fields) {
            System.err.println(field);
        }

        TestUtils.println();

        fields = c1.getDeclaredFields(); // 可以找到全部的属性
        for (Field field : fields) {
            System.err.println(field);
        }

//        Field name = c1.getDeclaredField("name"); // 找不到
//        System.err.println(name);

//        Field color1 = c1.getField("color"); // 找不到，因为是private属性
//        System.err.println(color1);

        Field color2 = c1.getDeclaredField("color"); // 找不到，因为是private属性
        System.err.println(color2);

        TestUtils.println();

        // 获取类的方法
        Method[] methods = c1.getMethods(); // 获取本类及其父类的全部public方法
        for (Method method : methods) {
            System.err.println(method);
        }

        TestUtils.println();

        Method[] declaredMethods = c1.getDeclaredMethods(); // 获取本类的全部方法
        for (Method method : declaredMethods) {
            System.err.println(method);
        }

        TestUtils.println();

        // 获取指定的方法
        Method getName = c1.getMethod("getColor");
        Method setName = c1.getMethod("setColor", String.class);
        System.err.println(getName);
        System.err.println(setName);

        TestUtils.println();

        // 获取构造器
        Constructor<?> constructor = c1.getConstructor(); // 获取默认的无参构造器
        System.err.println(constructor);

        TestUtils.println();

        // 获取全部的构造器
        Constructor<?>[] constructors = c1.getConstructors();
        for (Constructor<?> c : constructors) {
            System.err.println("constructors : "  + c);
        }

        TestUtils.println();

        constructors = c1.getDeclaredConstructors();
        for (Constructor<?> c : constructors) {
            System.err.println("DeclaredConstructors : "  + c);
        }

        TestUtils.println();

        // 获取指定的构造器，如果类中没有这个构造方法，是无法获取到的
        Constructor<?> target = c1.getConstructor(String.class, String.class, boolean.class, long.class);
        System.err.println(target);
    }
}

package com.kieran.study.reflection;

import com.kieran.study.dto.CarDTO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 性能对比  结果：普通方法 > 反射关闭检查 > 反射
 * setAccessible 作用于 方法 / 属性 上
 * 由于JDK的安全检查耗时较多.所以通过setAccessible(true)的方式关闭安全检查就可以达到提升反射速度的目的
 * 在Java中可以通过反射进行获取实体类中的字段值，当未设置Field的setAccessible方法为true时，会在调用的时候进行访问安全检查，会抛出IllegalAccessException异常。
 * 使用 setAccessible() 可以临时改变访问权限，就可以获取私有成员变量的值。
 *
 */
public class Demo9 {
    public static void test1() {
        CarDTO user = new CarDTO();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            user.getColor();
        }

        long endTime = System.currentTimeMillis();

        System.err.println("普通方法执行时间:" + (endTime - startTime) + "ms");
    }

    public static void test2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class c1 = Class.forName("com.kieran.study.dto.CarDTO");
        CarDTO user = (CarDTO) c1.getDeclaredConstructor(String.class, String.class, boolean.class, long.class)
                .newInstance("A", "B", false, 1);

        Method getColor = c1.getDeclaredMethod("getColor");

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            getColor.invoke(user, null);
        }

        long endTime = System.currentTimeMillis();

        System.err.println("反射方法执行时间:" + (endTime - startTime) + "ms");
    }

    public static void test3() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class c1 = Class.forName("com.kieran.study.dto.CarDTO");
        CarDTO user = (CarDTO) c1.getDeclaredConstructor(String.class, String.class, boolean.class, long.class)
                .newInstance("A", "B", false, 1);

        Method getColor = c1.getDeclaredMethod("getColor", null);
        getColor.setAccessible(true);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            getColor.invoke(user, null);
        }

        long endTime = System.currentTimeMillis();

        System.err.println("反射方法关闭检测执行时间:" + (endTime - startTime) + "ms");
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        test1();
        test2();
        test3();
    }
}

package com.kieran.study.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * 定义一个复杂的注解
 */
public class Demo3 {
    @Anno2("good")
    void test1() {}

    @Anno3(name = "kieran", nums = {3,4,5})
    void test2() {}

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = Demo3.class.getDeclaredMethod("test2", null);
        method.setAccessible(true);

        Anno3 annotation = method.getAnnotation(Anno3.class);
        System.err.println(annotation);

    }
}

/**
 * 当注解参数只有一个时，推荐使用value作为参数名，这样可以直接使用@Anno2("val") 而不需要@Anno2(value = "val")
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@interface Anno2 {
    String value();
}

/**
 * 这个注解已经属于比较复杂的了，一般不会定义这么多参数的注解
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@interface Anno3 {
    // 不是一个方法，是这个注解的参数(参数类型 + 参数名 + 可选默认值)
    String name();
    String color() default "blue";
    int age() default 10;
    int id() default -1; // 如果默认值为-1，代表不存在
    int[] nums() default {1,2,3};
}

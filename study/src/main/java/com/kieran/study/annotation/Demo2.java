package com.kieran.study.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元注解
 */
@Anno1
public class Demo2 {
    @Anno1
    public void test() {}

    @Anno1
    public static void main(String[] args) {
    }
}

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD}) // 表示允许在什么地方使用这个注解
@Retention(RetentionPolicy.RUNTIME) // 表示该注解在什么地方有效 runtime > class > source(源码) 比如 设定runtime，那么class source都有效
@Documented // 表示是否将我们的注解生成在Java doc中
@Inherited // 子类可以继承父类的注解
@interface Anno1 {

}

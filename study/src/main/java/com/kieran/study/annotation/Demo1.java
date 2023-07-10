package com.kieran.study.annotation;

/**
 * 内置注解
 */
public class Demo1 {

    /**
     * 重写
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * 告诉程序员不推荐使用该方法的注解，但是可以使用，或者存在更好的方式
     */
    @Deprecated
    public static void test() {
        System.err.println("不推荐使用该方法");
    }

    public static void main(String[] args) {
        // 在一些代码管理工具上会在test()上划横线
        test();
    }

    /**
     * 镇压警告，例如test3，不使用这个注解的话会报警告
     */
    @SuppressWarnings("all")
    public void test2() {

    }

    /**
     * 警告信息：Method 'test3()' is never used
     */
    public void test3() {

    }
}

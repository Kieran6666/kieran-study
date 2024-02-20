package com.kieran.study.lambda;

/**
 * lambda的演变过程
 */
public class Demo1 {
    static class ILike2 implements ILike {

        @Override
        public void lambda(int i, String s) {
            System.err.println(i + " " + s);
        }
    }

    public static void main(String[] args) {
        // 1.普通类
        ILike iLike = new Like1();
        iLike.lambda(1, "A");

        // 2.静态成员类
        iLike = new ILike2();
        iLike.lambda(2, "B");

        // 3.局部内部类
        class ILike3 implements ILike {

            @Override
            public void lambda(int i, String s) {
                System.err.println(i + " " + s);
            }
        }
        iLike = new ILike3();
        iLike.lambda(3, "C");

        // 4.匿名内部类
        iLike = new ILike() {
            @Override
            public void lambda(int i, String s) {
                System.err.println(i + " " + s);
            }
        };
        iLike.lambda(4, "D");

        // 5.lambda
        iLike = (int i, String s) -> {
            System.err.println(i + " " + s);
        };
        iLike.lambda(5, "E");

        // 6. lambda的内容体只有一行时，可以简化括号
        iLike = (int i, String s) -> System.err.println(i + " " + s);
        iLike.lambda(6, "F");

        // 7. 简化参数类型，要么全部有类型，要么全部没有
        iLike = (i, s)  -> System.err.println(i + " " + s);
        iLike.lambda(7, "G");
    }
}

/**
 * lambda的必须要求：只有一个抽象方法的接口
 */
interface ILike {
    void lambda(int i, String s);
}

class Like1 implements ILike {

    @Override
    public void lambda(int i, String s) {
        System.err.println(i + " " + s);
    }
}

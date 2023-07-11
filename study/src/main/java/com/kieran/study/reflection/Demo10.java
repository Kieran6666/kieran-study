package com.kieran.study.reflection;

import com.kieran.study.dto.CarDTO;
import com.kieran.study.utils.TestUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 通过反射获取泛型
 */
public class Demo10 {

    private void test1(Map<String, CarDTO> map, List<CarDTO> list) {
        System.err.println("test1");
    }

    private Map<String, CarDTO> test2() {
        System.err.println("test");
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
        Method test1 = Demo10.class.getDeclaredMethod("test1", Map.class, List.class);
        test1.setAccessible(true);

        Type[] genericParameterTypes = test1.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            System.err.println(genericParameterType);
            if (genericParameterType instanceof ParameterizedType) {
                for (Type actualTypeArgument : ((ParameterizedType) genericParameterType).getActualTypeArguments()) {
                    System.err.println(actualTypeArgument);
                }
            }
        }

        TestUtils.println();

        // 获取返回值
        Method test2 = Demo10.class.getDeclaredMethod("test2", (Class<?>) null);
        test2.setAccessible(true);

        Class<?> returnType = test2.getReturnType();
        System.err.println(returnType);

        Type genericReturnType = test2.getGenericReturnType();
        System.err.println(genericReturnType);
        if (genericReturnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                // 获取对应的类
                Class<?> c1 = Class.forName(String.valueOf(actualTypeArgument.getTypeName()));
                System.err.println(c1);
            }
        }


    }
}

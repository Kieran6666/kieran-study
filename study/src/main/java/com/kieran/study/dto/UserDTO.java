package com.kieran.study.dto;

import lombok.Getter;

/**
 * 链式编程+泛型
 *
 * FIXME:: 泛型是为了约束后面的子类？
 *
 * @param <K>
 * @param <V>
 */
@Getter
public class UserDTO<K, V> {

    private int age;
    private String name;

    private UserDTO() {
    }

    public static UserDTO<Object, Object> builder() {
        return new UserDTO<>();
    }

    public UserDTO<K, V> age(int age) {
        this.age = age;
        return this; // 链式的关键
    }

    public UserDTO<K, V> name(String name) {
        this.name = name;
        return this;
    }

    public <K1 extends K, V1 extends V> UserDTO<K1, V1> build() {
        return (UserDTO<K1, V1>) this;
    }


}

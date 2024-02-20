package com.kieran.study.reflection;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * 通过反射获取注解
 */
public class Demo11 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> c1 = Class.forName("com.kieran.study.reflection.Air");
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation a : annotations) {
            System.err.println(a);
        }

        AirAnno annotation = c1.getAnnotation(AirAnno.class);
        System.err.println(annotation);

        Class<?> c2 = Class.forName("com.kieran.study.reflection.AirField");
        System.err.println(c2);

        Field[] declaredFields = c1.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);

            Annotation[] annotations1 = field.getAnnotations();
            for (Annotation a1 : annotations1) {
                System.err.println(a1);
                AirField a11 = (AirField) a1;
                System.err.println(a11.columnName() + " - " + a11.type() + " - " + a11.length());
            }

            AirField annotation1 = field.getAnnotation(AirField.class);
            System.err.println(annotation1.columnName() + " - " + annotation1.type() + " - " + annotation1.length());

        }
    }
}


@AirAnno(value = "sweet")
class Air {
    @AirField(columnName = "id", type = "integer", length = 10)
    private int id;

    @AirField(columnName = "age", type = "integer", length = 5)
    private int age;

    @AirField(columnName = "name", type = "varchar", length = 20)
    private String name;

    public Air() {

    }

    public Air(int id, int age, String name) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Air{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@interface AirAnno {
    String value();
}

@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@interface AirField {
    String columnName();
    String type();
    int length();
}

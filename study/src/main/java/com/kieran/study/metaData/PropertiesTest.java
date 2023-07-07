package com.kieran.study.metaData;

import org.springframework.beans.factory.annotation.Value;

public class PropertiesTest {

    public static void main(String[] args) {
        KieranProperties kieranProperties = new KieranProperties();
        System.err.println(kieranProperties.getName());
        System.err.println(kieranProperties.getAge());
    }
}

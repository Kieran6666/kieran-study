package com.kieran.study.metaData;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * TODO:: 没弄明白，记得找时间弄明白 spring-configuration-process
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "kieran")
@PropertySource("application-dev.properties")
public class KieranProperties {


    private String name;

    private int age;
}

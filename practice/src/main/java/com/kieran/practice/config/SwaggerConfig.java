package com.kieran.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Swagger2 配置
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Bean(value = "swagger2")
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()        // 配置网站的基本信息
                        .title("Kieran4j-API文档")   // 网站标题
                        .version("v1.0")            // 标题后面的版本号
                        .description("学习后的实践API文档")
                        //联系人信息
                        .contact(new Contact("Kieran", "", ""))
                        .build())
                .select()
                // 指定带有某个注解的接口
                // 在不指定的情况下则会默认扫描@Controller注解，即便@RestController中包含@Controller，@RestController也不会生效
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

}

package com.kieran.study.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    // XML中拦截器的设置方式，在spring-mvc-config.xml中设置
//	<!-- 对mvc注解支持 -->
//	<mvc:annotation-driven  >
//	</mvc:annotation-driven>
//
//	<bean class="com.shinefriends.dc.document.MySwaggerConfig"/>
//
//	<mvc:interceptors>
//		<mvc:interceptor>
//			<mvc:mapping path="/**/"/>
//			<bean class="com.shinefriends.dc.interceptor.SignatureInterceptor" />
//		</mvc:interceptor>
//	</mvc:interceptors>


    /**
     * 添加拦截器，使拦截起生效
     *
     * @param registry 拦截器登记处
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAnnotationInterceptor());
    }

    /**
     * 把拦截起注入到Spring容器中
     */
    @Bean
    public AnnotationInterceptor getAnnotationInterceptor() {
        return new AnnotationInterceptor();
    }


//    /**
//     * 添加拦截器，使拦截起生效，不需要注入到spring容器中，用这种方式
//     *
//     * @param registry 拦截器登记处
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AnnotationInterceptor());
//    }
}

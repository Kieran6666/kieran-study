package com.kieran.study.annotation;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 拦截方法，判断是否有注解
 * extends HandlerInterceptorAdapter 被废弃了？
 */
public class AnnotationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;

                Anno3 anno3 = handlerMethod.getMethodAnnotation(Anno3.class);
                if (null != anno3) {
                    // 做相应的业务
                    System.err.println("执行业务");
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            // 自定义response
            PrintWriter writer = response.getWriter();
            JSONObject obj = new JSONObject();
            obj.put("code", "1");
            obj.put("msg", e.toString());
            writer.append(obj.toJSONString());
            return false;
        }
    }
}

package com.kieran.study.proxy.activeProxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib动态代理
 */
public class CglibProxy implements MethodInterceptor {
    /**
     * 被代理的类
     */
    private Object target;

    /**
     * 外部决定需要代理的类
     *
     * @param target 被代理的类
     */
    public CglibProxy(Object target) {
        this.target = target;
    }

    /**
     * 获取类代理
     */
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * 方法拦截
     *
     * @param obj 被代理的类
     * @param method 方法
     * @param args 参数
     * @param proxy 代理器
     * @return 结果
     * @throws Throwable 异常
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.err.println("cglib proxy => before method run..." + method.getName());
        Object result = proxy.invoke(target, args);
        System.err.println("cglib proxy => after method run..." + method.getName());
        return result;
    }

}

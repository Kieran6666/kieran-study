package com.kieran.study.proxy.activeProxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class ProxyInvocationHandler implements InvocationHandler {
    /**
     * 被代理的接口
     */
    private Object target;

    /**
     * 外部设置该接口
     *
     * @param target 被代理的接口
     */
    public void setTarget(Object target) {
        this.target = target;
    }

    /**
     * Target Proxy
     *
     * @return 代理类
     */
    public Object getProxy() {
        // 会克隆target里的所有接口
        // 代理的核心点是会生成一个$Proxy0，这个类会克隆接口中的方法
        // 由于java中不支持多继承，而每个$Proxy0都克隆了一个接口，因此jdkProxy只能代理接口
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 执行接口中的方法
     *
     * @param proxy 代理类
     * @param method 被代理的接口的方法
     * @param args 参数
     * @return 结果
     * @throws Throwable 异常信息
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log(method.getName());
        return method.invoke(target, args);
    }

    /**
     * Proxy自己的方法
     * @param msg 消息
     */
    private void log(String msg) {
        System.err.println("正在执行:" + msg + "方法");
    }
}

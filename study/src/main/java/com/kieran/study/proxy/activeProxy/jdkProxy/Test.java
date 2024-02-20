package com.kieran.study.proxy.activeProxy.jdkProxy;

import com.kieran.study.myInterface.crud.CrudService;
import com.kieran.study.myInterface.crud.impl.CrudServiceImpl;
import com.kieran.study.myInterface.crud.impl.CrudServiceImpl2;
import com.kieran.study.utils.TestUtils;

/**
 * jdk动态代理 测试类
 */
public class Test {

    public static void main(String[] args) {
        // 生成$Proxy0的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setTarget(new CrudServiceImpl());
        CrudService proxy = (CrudService) pih.getProxy();
        proxy.add();
        proxy.read();
        proxy.update();
        proxy.delete();

        TestUtils.println();

        ProxyInvocationHandler pih2 = new ProxyInvocationHandler();
        pih2.setTarget(new CrudServiceImpl2());
        CrudService proxy2 = (CrudService) pih2.getProxy();
        proxy2.add();
        proxy2.read();
        proxy2.update();
        proxy2.delete();

    }
}

package com.kieran.study.proxy.activeProxy.cglib;

import com.kieran.study.dto.CarDTO;
import com.kieran.study.utils.TestUtils;

/**
 * Cglib代理 测试类
 */
public class Test {

    public static void main(String[] args) {
        // 代理目标类
        CglibProxy cglibProxy = new CglibProxy(new CarDTO());
        CarDTO proxy = (CarDTO) cglibProxy.getProxy();
        System.err.println(proxy);
        proxy.setColor("blue");
        System.err.println(proxy);

        TestUtils.println();

        CglibProxyT<CarDTO> cglibProxy1 = new CglibProxyT<>();
        CarDTO proxy1 = cglibProxy1.getProxy(new CarDTO());
        System.err.println(proxy1);
        proxy1.setColor("green");
        System.err.println(proxy1);
    }
}

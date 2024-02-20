package com.kieran.study.proxy.staticProxy.demo2;

import com.kieran.study.myInterface.crud.CrudService;
import com.kieran.study.myInterface.crud.impl.CrudServiceImpl;
import com.kieran.study.myInterface.crud.impl.CrudServiceImpl2;
import com.kieran.study.utils.TestUtils;

/**
 * 需求：在不改动原有代码下，新增每个操作的日志打印
 * 解析：即代码增强
 * 解答：使用代理类
 */
public class CrudClient {

    /**
     * 实施代理行为
     *
     * @param args args
     */
    public static void main(String[] args) {
        CrudService crudService = new CrudServiceImpl();
        CrudServiceProxy proxy = new CrudServiceProxy(crudService);
        proxy.add();
        proxy.read();
        proxy.update();
        proxy.delete();

        TestUtils.println();

        CrudService crudService2 = new CrudServiceImpl2();
        CrudServiceProxy proxy2 = new CrudServiceProxy(crudService2);
        proxy2.add();
        proxy2.read();
        proxy2.update();
        proxy2.delete();
    }
}

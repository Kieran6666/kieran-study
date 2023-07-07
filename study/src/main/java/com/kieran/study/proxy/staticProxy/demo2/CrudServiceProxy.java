package com.kieran.study.proxy.staticProxy.demo2;

import com.kieran.study.myInterface.crud.CrudService;

/**
 * Crud代理类(增强类)
 */
public class CrudServiceProxy implements CrudService {
    /**
     * crudService
     */
    private CrudService crudService;

    /**
     * CrudServiceProxy
     *
     * @param crudService crudService
     */
    public CrudServiceProxy(CrudService crudService) {
        this.crudService = crudService;
    }

    /**
     * add
     */
    @Override
    public void add() {
        System.err.println("add start");
        crudService.add();
    }

    /**
     * read
     */
    @Override
    public void read() {
        System.err.println("read start");
        crudService.read();
    }

    /**
     * update
     */
    @Override
    public void update() {
        System.err.println("update start");
        crudService.update();
    }

    /**
     * delete
     */
    @Override
    public void delete() {
        System.err.println("delete start");
        crudService.delete();
    }
}

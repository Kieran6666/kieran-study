package com.kieran.study.myInterface.crud.impl;

import com.kieran.study.myInterface.crud.CrudService;

/**
 * Crud实现类2
 */
public class CrudServiceImpl2 implements CrudService {
    /**
     * 新增
     */
    @Override
    public void add() {
        System.err.println("add 2");
    }

    /**
     * 查询
     */
    @Override
    public void read() {
        System.err.println("read 2");
    }

    /**
     * 更新
     */
    @Override
    public void update() {
        System.err.println("update 2");
    }

    /**
     * 删除
     */
    @Override
    public void delete() {
        System.err.println("delete 2");
    }
}

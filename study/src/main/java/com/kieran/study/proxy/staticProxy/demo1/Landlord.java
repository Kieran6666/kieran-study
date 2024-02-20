package com.kieran.study.proxy.staticProxy.demo1;

/**
 * 被代理的角色：房东把房子租出去，需要实现租这个动作，但是是转给中介租
 */
public class Landlord implements Rent {
    @Override
    public void rent() {
        System.err.println("房东把房子租出去");
    }
}

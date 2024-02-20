package com.kieran.study.proxy.staticProxy.demo1;

/**
 * 真实角色：客户租房
 */
public class Client {

    /**
     * 客户找到中介租房，这个时候是知道大房东是谁的
     *
     * @param args args
     */
    public static void main(String[] args) {
        Proxy proxy = new Proxy(new Landlord());
        proxy.rent();
    }
}

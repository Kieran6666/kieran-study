package com.kieran.study.proxy.staticProxy.demo1;

/**
 * 代理角色：中介房子租出去，在房东的租的基础上加自己的方法，需要实现租这个动作
 */
public class Proxy implements Rent {
    /**
     * 获取房东的行为
     */
    private Landlord landlord;

    /**
     * 继承房东的行为，让自己成为二房东
     * @param landlord 房东
     */
    public Proxy(Landlord landlord) {
        this.landlord = landlord;
    }

    /**
     * 对租的行为升级
     */
    @Override
    public void rent() {
        lookHouse();
        landlord.rent();
        fare();
        giveHouse();
    }

    /**
     * 看房子
     */
    private void lookHouse() {
        System.err.println("看房子");
    }

    /**
     * 中介费
     */
    private void fare() {
        System.err.println("中介费");
    }

    /**
     * 给房子
     */
    private void giveHouse() {
        System.err.println("给房子");
    }

}

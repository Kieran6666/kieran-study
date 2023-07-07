package com.kieran.study.dto;

public class CarDTO {
    private String brand;
    private String color;
    private boolean ifNow;
    private long price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isIfNow() {
        return ifNow;
    }

    public void setIfNow(boolean ifNow) {
        this.ifNow = ifNow;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", ifNow=" + ifNow +
                ", price=" + price +
                '}';
    }
}

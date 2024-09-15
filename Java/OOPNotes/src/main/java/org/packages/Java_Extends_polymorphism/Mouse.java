package org.packages.Java_Extends_polymorphism;

public class Mouse implements USB {
    String name;

    public Mouse(String name) {
        this.name = name;
    }

    void click() {
        System.out.println("鼠标准备就绪, " + this.name +
                "可以开始使用");
    }

    public void connect() {
        System.out.println("USB设备 " + this.name + " [插入]了电脑");
    }

    public void unconnect() {
        System.out.println("USB设备 " + this.name + " [拔出]了电脑");
    }
}

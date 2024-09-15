package org.packages.Java_Extends_polymorphism;

public class Keyboard implements USB {
    private String name;

    public Keyboard(String name) {
        this.name = name;
    }

    void words() {
        System.out.println("键盘" + this.name +
                "准备就绪, 可以开始使用");
    }

    public void connect() {
        System.out.println("USB设备 " + this.name + " [插入]了电脑");
    }

    public void unconnect() {
        System.out.println("USB设备 " + this.name + " [拔出]了电脑");
    }
}

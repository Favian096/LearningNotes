package org.packages.Java_Extends_polymorphism;

public class Cat extends Animals {
    @Override
    void call() {
        System.out.println("===猫叫===");
    }

    void catch_mouse() {
        System.out.println("猫抓老鼠");
    }
}

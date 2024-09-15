package org.packages.Java_Extends_polymorphism;

public class Dog extends Animals {
    @Override
    void call() {
        System.out.println("===狗叫===");
    }

    void lookdoor() {
        System.out.println("狗在看门");
    }
}

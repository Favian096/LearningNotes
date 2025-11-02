package org.example.annotation;

/**
 * 以供注解展示
 */

@SimpleAnnotation(name = "Tom")
public class App {
    @ValueAnnotation("hw")
    String hello = "Hello World";


    public static void main(String[] args) {

    }

    @SimpleAnnotation(name = "AMD", age = 11)
    public void fun1() {
        System.out.println("AMD YES");

    }

    @ValueAnnotation("Inter")
    public void fun2() {
        System.out.println("Inter YES");
    }
}

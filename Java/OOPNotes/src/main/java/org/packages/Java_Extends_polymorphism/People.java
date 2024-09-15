package org.packages.Java_Extends_polymorphism;

public class People {
    private String name;
    private int age;

    public People() {
        System.out.println("people父类 无参 构造器被调用");
    }

    public People(String name, int age) {
        System.out.println("people父类 有参 构造器被调用");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void study() {
        System.out.println(name + "在学习...");
    }

    public void exam() {
        System.out.println(name + "进行了一次测试");
    }
}


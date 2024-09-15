package org.packages.Java_Extends_polymorphism;

public class Students extends People {
    public Students() {
//        super();这一行默认写不写都有
        super();
        System.out.println("students子类 无参 构造器被调用");
    }

    public Students(String name, int age) {
        super(name, age);
        System.out.println("students子类 有参 构造器被调用");
    }

    @Override
    public void exam() {
        super.exam();
        System.out.println("并且要准备考试了");
    }
}

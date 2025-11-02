package org.example.reflection;

public class Student {
    public String name;
    private int age;

    public Student() {
        System.out.println("non-args constructor");
    }

    private Student(String name, int age) {
        System.out.println("args constructor");
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String sleep() {
        return this.name + " sleep ~";
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

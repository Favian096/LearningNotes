package org.packages.Test;

public class Test1 {
    //        提前创建对象
    public static Test1 instance = new Test1();

    //        私有构造器
    private Test1() {
        System.out.println("饿汉设计模式");
    }
}

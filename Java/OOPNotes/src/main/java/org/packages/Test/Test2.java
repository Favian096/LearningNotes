package org.packages.Test;

public class Test2 {
    //        不使用  new  只有真正需要时才 new   ，私有变量
    private static Test2 instance;

    //          构造器私有
    private Test2() {
        System.out.println("懒汉设计模式");
    }

    public static Test2 getInstance() {
        if (instance == null) {
//                只有需要对象时才会创建一个
            instance = new Test2();
        }
        return instance;
    }
}

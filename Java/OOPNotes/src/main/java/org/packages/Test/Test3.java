package org.packages.Test;

public abstract class Test3 {
    //              抽象类只有方法签名, 不能声明方法体(只有方法名称,没有具体代码)
    //              定义了抽象方法就必须定义抽象类
    public abstract String method();

    //    定义模版方法(加final 防止重写)
    public final void fun() {
        System.out.println("===调用继承的抽象方法===");
        System.out.println(method());
        System.out.println("====== 完成调用 ======");
    }
}

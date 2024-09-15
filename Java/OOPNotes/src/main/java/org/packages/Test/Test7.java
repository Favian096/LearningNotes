package org.packages.Test;

public class Test7 {
    //    可变参数
    public void sum(int... num) {
        System.out.println("调用了可变参数方法:" + num.length);
    }

}

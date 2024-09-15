package org.packages.Test;

//函数式接口(一旦加上这个, 接口内就只能有一个抽象方法)
@FunctionalInterface
public interface Test5 {
    void run();

    default void competition() {
        eat();
        System.out.println("比赛");
    }

    static void sleep() {
        System.out.println("睡觉");
    }

    private void eat() {
        System.out.println("吃饭");
    }
}

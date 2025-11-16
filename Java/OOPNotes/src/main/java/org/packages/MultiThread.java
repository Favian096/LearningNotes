package org.packages;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MultiThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        多线程

//            多线程的创建
        /*  方式1: 继承Thread
         *          重写run方法
         *          创建线程类的对象
         *          调用start启用线程(再调用run方法)*/
        /*方法1 不可以继承其他类实现丰富的功能(拓展性), 也不能返回值(run为void)*/

        Thread td1 = new ThreadDemo1();
        td1.setName("1号线程->");
        td1.start();
        /*  如果直接调用run()来跑, 就相当于只是调用方法, 只有一个线程(main线程)*/


        /*  方式2: 实现Runnable接口
         *          重写run方法
         *          创建线程类的对象
         *         *把对象交给Thread进行处理
         *          调用start()执行
         * */
        /*  方法2 还可以继承其他类实现丰富的功能(拓展性), 但不能返回值(run为void)*/

//        Thread() 可以接收线程名称, 也可以接收Runnable类型线程对象
        Runnable ra = new ThreadDemo2();
        Thread td2 = new Thread(ra, "2号线程->");
        td2.start();

        /*  除此之外, 还可以使用匿名类的方式(lambda)  */
        new Thread(() -> System.out.println("使用匿名类的方法创建")).start();



        /*  方法3: 实现Callable接口,重写call方法(封装要做的事)
         *         用FutureTask把Callable对象 封装成线程对象(方便Thread接收, FutureTask是Runnable子类)
         *         把线程对象交给Thread处理
         *         调用start方法执行完
         *         通过FutureTask的get方法即可获取结果*/

        Callable<String> c = new ThreadDemo3("继承Callable的线程-执行完成-");
        FutureTask<String> ft = new FutureTask<>(c);
        Thread td3 = new Thread(ft, "3号线程->");
        td3.start();
        System.out.println(ft.get());
        /*  如果到这里线程没有执行完, 代码会自动等待  */



        /*  通常主线程的代码块放在子线程后面  */
        for (int i = 0; i < 3; i++) {
            System.out.println("Main主线程开始执行");
        }


    }

}

class ThreadDemo1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "ThreadDemo1执行(继承Thread)");
        }

    }
}

class ThreadDemo2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "休眠了3s");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + "ThreadDemo2执行(实现Runnable)");
        }
    }
}

class ThreadDemo3 implements Callable<String> {
    private String str;

    public ThreadDemo3(String str) {
        this.str = str;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "开始执行继承Callable的线程,即将返回字符串->");
        return this.str;
    }
}
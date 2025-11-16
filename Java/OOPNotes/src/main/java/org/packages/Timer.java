package org.packages;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Timer {
    public static void main(String[] args) {
//        定时器(任务延时调用或周期调用技术)
        /*  实现方式:
                方式1 Timer(单线程, 创建多个可能会死亡会时间有出入)
                        创建对象 调用方法
                            t.schedule(方法, 延迟时间, 周期时间)
                方式2 ScheduledExecutorService(线程池原理, 弥补了Timer的缺陷)
                                        创建对象 调用方法
*/
        java.util.Timer t1 = new java.util.Timer();
        t1.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer单线程执行~~~~~~~~~~");
            }
        }, 3000, 1000);

        /*  方法2                                                              线程数*/
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
        ses.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "使用ScheduledExecutorService");
            }
        }, 0, 2, TimeUnit.SECONDS);
//             延迟时间       间隔时间      时间单位







        /*  并发和并行:
         *       并发: CPU分时轮询的执行线程
         *       并行: 同一个时刻同时在执行  */


    }
}

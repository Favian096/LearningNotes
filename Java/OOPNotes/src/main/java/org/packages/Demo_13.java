package org.packages;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo_13 {
    public Lock lock = new ReentrantLock();

    public static void main(String[] args) {
//        线程安全问题(多个线程同时访问一个共享资源并且存在数据修改(ATM机取钱))
        /*  解决: 线程同步  */
        /*  线程同步核心思想: 加锁, 把共享资源上锁,
                             每次只能进入一个线程, 该线程访问完毕后才可以解锁
                             然后其他线程才可以进来        */
        /*  实现方法: 1.同步代码块
         *               synchronized(锁对象){共享资源代码}
         *                            锁对象规范要求:通常使用共享资源作为锁对象
         *                                          实例对象使用this作为锁对象
         *                                          静态方法使用字节码(类名.class)作为锁对象
         *
         *            2.同步方法(原理类似同步代码块)
         *                  将核心方法进行上锁
         *                  修饰符 synchronized 返回值类型 方法签名(){核心代码}
         *            3.Lock锁(功能更强大)
         *                  要用实行类ReetrantLock()
         *                  定义Lock对象 使用try...catch 和finally{lock.unlock();}
         *  */

        Thread t1 = new ThreadDemo4();
        t1.start();
        Thread t2 = new ThreadDemo4();
        t2.start();

        //        线程池(复用线程技术)
        /*  ExecutorService 代表线程池的接口
         *       创建线程池对象:
         *          1.使用ExecutorService的实现类ThreadPoolExecutor自创建线程池对象
         *          2.使用Executor(线程池工具类)调用方法返回不同线程池对象   */
        /*  方式1:*/
    /*        public ThreadPoolExecutor( int corePoolSize (核心线程数)
                                         int maximunPoolSize (最大线程数)
                                         Long KeepAliveTime(线程存活时间)
                                         TimeUnit unit(时间单位)
                                         BlockingQueue<Runnable> workQueue(任务队列)
                                         ThreadFactory threadFactory(指定那个线程工厂创建线程)
                                         RejectedExecutionHandler handler(线程慢,来新线程怎么办)

                )*/


    }


    public synchronized void divNum() {
//        synchronized (new Thread()) {
        lock.lock();
        try {
            if (Num.num == 100) {
                System.out.println(Thread.currentThread().getName() + " OK");
                Num.num -= 100;
                System.out.println(Num.num);
            } else {
                System.out.println("NumIsNULL!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
//        }
    }

}

class Num {
    public static int num = 100;
}

class ThreadDemo4 extends Thread {
    @Override
    public void run() {
        Demo_13 d = new Demo_13();
        d.divNum();
    }
}


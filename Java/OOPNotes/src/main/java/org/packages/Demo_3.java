package org.packages;

import org.packages.Java_Extends_polymorphism.Students;
import org.packages.Test.Test4;
import org.packages.Test.Test5;
import org.packages.Test.Test6;

import java.math.BigDecimal;
import java.util.Arrays;

public class Demo_3 {
    public static void main(String[] args) {
//        Java包
        /*  相同类下的包可以直接访问,不同包下的类需要导包:
         *               import 包名.类名           */
        Students s = new Students("JLY", 20);


        {
//        权限修饰符(控制访问范围)
            /*      private < 缺省 < protected < public     */

            /*  修饰符     同一个类        同一个包其他类     不同包下的子类     不同包下无关类*/
//          private       √
//          缺省          √                 √
//          protected     √                 √                  √
//          public        √                 √                  √                  √
        }

        {
//        final 关键字
            /*  修饰类: 表示该类是最终类, 不可继承
             *  修饰方法: 表示该方法是最终方法, 不可重写
             *  修饰变量: 表示该变量第一次赋值后, 不能再次被赋值(一次赋值, 不可修改)*/

            /*  如果final修饰的是 基本类型 那么数据值不能发生改变
             *  如果final修饰的是 引用类型 那么地址值不能发生改变*/

//         public static final String name = "123";
//        由 public static final 修饰的成员变量为 常量(必须初始化,不可修改)
//        (编译后,常量会被自动 宏替换为 字面量)
        }

        {
//        枚举(特殊类)
            /*  枚举类的第一行必须写枚举类的对象名称,建议全部大写*/
/*        public enum Season {
            SPRING, SUMMER, AUTUMN, WINTER
        }*/

        /*  枚举类反编译后, 内部所有的对象都是 枚举类型常量,
            枚举类是最终类,不可继承,
            枚举构造器私有, 不可对外创建对象*/
/*        public final class Season extends java.lang.Enum<Season> {
              public static final Season SPRING;
              public static final Season SUMMER;
              public static final Season AUTUMN;
              public static final Season WINTER;
              public static Season[] values();
              public static Season valueOf(java.lang.String);
              static {};
           }*/

            /*  枚举用作信息分类    switch 支持 枚举  */
        }

        {
//        抽象类(abstract)
            /*  修饰 类 方法  --->  抽象类 抽象方法*/
            /*      抽象类只有方法签名, 不能声明方法体(只有方法名称,没有具体代码)
             *      定义了抽象方法就必须定义抽象类
             *      抽象类无法实例化对象(因为没有方法体)*/
//        public abstract letter{
//            public abstract void word();
//    }

        /*  抽象类一般是作为父类(不完整的设计图), 抽象子类的行为,
                                               继承后再由子类具体完成方法
            即: 派生子类且 子类必须全部重写父类的抽象方法(约束子类完成方法)
                          (不重写(全部)的话, 子类也要定义为 抽象类)

             由此 abstract(   继承) 和 final(不可继承) 互斥*/


//        ---> 模版方法
            /*  将通用功能定义为一个模版方法, 放在抽象类中(的普通方法), 子类继承后再具体实现
             *           见 test3 ---- test4*/

            Test4 t = new Test4();
            t.fun();
        }
        {
//            接口(interface)
            /*  接口中只写 常量 和 抽象方法*/
            /*  接口 体现规范思想, 默认都是公开的, 因此方法可以不写 public 和 abstract
             *       接口的功能是用来实现(implements)的, 要重写, 接口没有对象
             *       类可以 多实现接口
             *       test implements test5, test*, ... {}*/
//            见  test5 和 test 6
            Test6 t = new Test6();
            t.run();
            t.competition();

            /*  类与类: 单继承(extends)
             *   类与接口: 多实现(implements)
             *   接口与接口: 多继承(extends)
             *       接口多继承的作用是规范合并为一个接口,方便类继承
             *
             *   ********接口中可以有方法体***********
             *   并且该方法体必须使用default修饰(test5)     ----> 类实现用
             *   或者接口中设置私有方法(只能接口内部调用)    ----> 接口内部用
             *   或者接口中的方法使用static修饰, 只能用接口名称调用    ----> 接口名调用
             * */
            Test5.sleep();

            /*  注: 一个类实现多个接口方法, 多个接口中有重名的 静态方法 不存在冲突
             *      (因为静态方法不会被类实现, 要用接口名调用, 相当于是不同方法)
             *
             *      一个类继承了父类, 又实现了接口, 父类和接口中有同名方法,
             *                                   默认优先使用父类中的方法(先继承后实现)
             *                                (class 子类 extends 父类 implements 接口{})
             *      一个类实现多个接口方法, 多个接口中有重名的 默认方法
             *                            要避免冲突 , 直接重写该方法
             *      一个类实现多个接口方法, 如果接口中存在规范冲突, 就无法实现
             *              比如存在一个 int run(); 和一个 void run();*/
        }
        {
//            内部类(定义在一个类(宿主)里面的类(寄生))
            /*   内部类可以方便的访问外部类成员, 包括私有成员
             *   内部类可以使用private protect 修饰, 更具封装性   */

            /*   静态内部类
                    由static修饰, 归属于外部类本身,
                    可以直接访问外部类的静态成员, 不可以 直接访问外部类的实例成员(可以间接 外部类.成员变量)

             *   成员内部类
                    无static修饰, 属于外部类的对象,
                    创建对象: 外部类名.内部类名 对象名 = new 外部类构造器().内部类构造器();
                    可以直接访问外部类的静态成员, 也可以直接访问外部类的实例成员
                    (注: 访问外部类同名变量可以使用  外部类.this.变量名 )

             *   局部内部类
                    通常放在方法 代码块 构造器...(少用)
             *   匿名内部类
                     没有名字的局部内部类, 为了创建子类对象, 简化代码↓:
                     new 类名|抽象类名|接口名(){
                            重写方法;
                     }*/
            System.out.println("-----------------------------");
            Test6 ni = new Test6() {
                @Override
                public void run() {
                    System.out.println("====定义和使用匿名内部类===");
                }
            };
            ni.run();
            /*  匿名类整体可以作为一个参数进行使用*/
            (new Test6() {
                @Override
                public void run() {
                    System.out.println("====作为一个参数使用匿名内部类===");
                }
            }).run();
            System.out.println("-----------------------------");

        }
        {
//            API---------object 和 objects详见JDK文档
            /*  object方法--- toString()    默认返回当前对象在堆内存中的地址(类全名@内存地址)
             *                               该方法目的是为了让子类重写, 以此返回对象的内容
             *                toString()    默认比较两个对象在堆内存中的地址(返回boolean)
             *                               该方法目的是为了让子类重写, 以此比较两个对象的内容
             *              重写可以直接 右键 生成即可
             *
             *  objects是object的继承类, 但比object安全
             * */


//            StringBuilder详见JDK文档
            /*  StringBuilder 是一个可变字符串类, 可提高字符串的操作效率(拼接 修改)
             *   由于字符串增删改减每次都会在内存新增一个对象, 而StringBuilder只会产生一个对象*/
            StringBuilder str = new StringBuilder("字符串构造器");
            System.out.println(str);
            str.append("===").append("123").append("456");
            System.out.println(str);
            str.reverse();
            System.out.println(str);
            System.out.println("-----------------------------");

//            Math详见JDK文档
//            Math.abs();         绝对值
//            Math.ceil();        向上取整
//            Math.floor();       向下取整
//            Math.pow();         次方
//            Math.round();       四舍五入
//            Math.random()       产生0.0 ~ 1.0 的随机数(不包括1)

//            System详见JDK文档
//            System.exit(0); 立即结束虚拟机运行
//            System.currentTimeMillis();返回1970 .1 .1 .0 .00 到现在总得毫秒值
            double start = System.currentTimeMillis();
            double end = System.currentTimeMillis();
            System.out.println(end - start);
            /*    数组拷贝    */
            int[] arr_1 = {12, 24, 36, 48, 60, 72, 84, 96};
            int[] arr_2 = new int[8];
            System.arraycopy(arr_1, 2, arr_2, 5, 2);
            System.out.println(Arrays.toString(arr_2));
            System.out.println("---------------------------------");

//            BigDecimal(用于解决浮点型运算精度失真的问题)
            /*  使用优先创建对象↓*/
            double a = 0.09;
            double b = 0.01;
            System.out.println(a + b);

            BigDecimal a_new = BigDecimal.valueOf(a);
            BigDecimal b_new = BigDecimal.valueOf(b);
            System.out.println(a_new.add(b_new));
            System.out.println(a_new.subtract(b_new));
            System.out.println(a_new.multiply(b_new));
            System.out.println(a_new.divide(b_new));
            /* 传值时候优先 建立 double temp = b_new.doubleValue(); 转化为double*/
//            除不尽时
//            可以在divide内设置取值位数
        }

    }

}

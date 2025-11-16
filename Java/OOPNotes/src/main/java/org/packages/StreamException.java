package org.packages;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamException {
    public static void main(String[] args) {
        {
//            不可变集合
            /*  不可修改的集合(使用 :List.of(), Set.of(), ) */
            List<Integer> l = List.of(132, 45, 132);
            Set<String> s = Set.of("sss", "j", "s");
            Map<String, String> m = Map.of("J", "l");
            System.out.println(l);
            System.out.println(s);
            System.out.println(m);
            System.out.println("--------------------------------");
        }
        {
//            Stream流(类似流水线传送带进行链式操作, 用于简化集合和数组操作的API )
        /*      Stream流思想: 先得到集合或者是数组的Stream流(集合/数组.stream()),
                                从而获取元素, 再调用Stream流简化的API进行操作*/
            /*  例子: 集合所有张开头并且字段长度为3的*/
            List<String> list = new ArrayList<>();
            List<String> list1 = new ArrayList<>();
            list.add("张老三");
            list.add("张小三");
            list.add("李四");
            list.add("张六");
            list.add("王八");
            list.add("王八");
            list1.add("JLY");
            list.stream().filter(s -> s.startsWith("张")).filter(s -> s.length() == 3).forEach(s -> System.out.println(s));
/*            list.stream().filter(new Predicate<String>() {
                                     @Override
                                     public boolean test(String s) {
                                         return false;
                                     }
                                 }
            );*/
            /*-----------Collection集合获取Stream流----------*/
            Collection<String> c = new ArrayList<>();
            Stream<String> sc = c.stream();
            /*或者*/
            Stream<Collection<String>> sc2 = Stream.of(c);

            /*--------------Map集合获取Stream流--------------*/
            /*没有直接的map Stream流 可以依托于分离键值*/
            Map<String, String> m = new HashMap<>();

            Stream<String> smk = m.keySet().stream();
            Stream<String> smv = m.values().stream();
            Stream<Map.Entry<String, String>> sme = m.entrySet().stream();
            /*或者*/
            Stream<Set<String>> smk2 = Stream.of(m.keySet());
            Stream<Collection<String>> smv2 = Stream.of(m.values());
            /*-----------数组集合获取Stream流----------*/
            Integer[] i = new Integer[1];
            Stream<Integer> si1 = Arrays.stream(i);
            /*或者*/
            Stream<Integer> si2 = Stream.of(i);

            System.out.println("---------------------------");
            /*          stream流常用API        */
            /*  filter()    对流中的数据进行 过滤
             *  count()     统计个数
             *  limit()     获取前几个元素
             *  skip()      跳过前几个元素
             *  distinct()  去除流中重复的元素(依赖hashcode和equals)
             *  map()       加工方法( 原结果 -> 加工结果 )
             *  concat()    合并流
             * */
            System.out.println(list.stream().count());
            list.stream().limit(2);
            list.stream().skip(2);
            list.stream().distinct();
            list.stream().map(s -> "使用了map加工:" + s).forEach(System.out::println);
            /* forEach(System.out::println)中原代码为(a -> System.out.println(a))
             *  因为前后两个a相同 因此省略且在方法名前该 :: */
            Stream<String> list2 = Stream.concat(list.stream(), list1.stream());
            list2.forEach(System.out::print);

            /*  收集stream流(将Stream流操作完成的结果转回到集合或数组中)*/
            /*      使用collect方法->  List使用Collectors.toList()
                                      Set使用Collectors.toSet()
                                      数组使用Collectors.toArrays()
             *                          .....
                        注: 流只能使用一次, 要再次使用需要重新创建一个流(cs只能用一次)*/
            Stream<String> cs = list.stream().filter(s -> s.startsWith("张"));
            List<String> csl = cs.collect(Collectors.toList());
            /*  或者JOK16以后可以简写如下, 但是简写的cls2是不可修改的list  */
//            List<String> csl2 = cs.toList();
            System.out.println("\n----------------------------");

        }
        {
//            异常处理
            /*  throwable   Error(系统错误)
             *              Exception   RuntimeException(编译无错, 运行出错(包括其子类))
             *                          处RuntimeException之外的错误*/

            /*  异常的处理方式:
             *       1直接抛给方法调用者;
             *       2使用try..catch...进行捕获处理;(直接ctrl alt T)
             *              try{...
             *              }catch(Exception e){
             *                  e.printStackTrace();
             *              }
             *        3前两种的结合:抛出异常给方法调用者, 再由调用者捕获
             *        */
            try {
                int[] arr = {1, 2, 3};
                System.out.println(arr[3]);
            } catch (Exception e) {
                /*      通常不会像下面这样写, 应该要写 e.printStackTrace();
                 *                          打印出异常栈信息(规范)*/
                System.out.println("此位置不存在元素");

            }

            /*  自定义异常错误:
                    自定义一个一个异常类继承Exception,再重写构造器
                    在方法体内 throw new 自定义的异常名字Exception("异常提示");
                         然后在 方法名之后throw 自定义的异常名字Exception
                         此方法会在编译过程中就给出提示
                     如果要自定义运行错误, 需要将以上方法的Exception改为RuntimeException
                        运行错误的不会再编译过程中提示(提示不强)*/

        }
    }
}

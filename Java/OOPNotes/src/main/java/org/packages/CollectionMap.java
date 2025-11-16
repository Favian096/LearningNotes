package org.packages;

import org.packages.Test.Test5;
import org.packages.Test.Test7;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CollectionMap {
    public static void main(String[] args) {
        {
            //        Lambda表达式(简化匿名内部类)
//        只能用于简化 函数式接口(有且只有一个抽象方法的接口)
            /*  格式: */
//        (匿名类形参刘表) -> {
//            重写方法
//        }
            Test5 t_Lambda = () -> {
                System.out.println("===使用Lambda表达式简化 函数式借口 实现匿名类简写===");
            };
            t_Lambda.run();

            /*  再简化:
             *           lambda表达式的参数类型可以省略
             *           如果只有一个参数, 可以省略小括号 ()
             *           如果重写的方法体只有一行代码可以不写大括号 {...};
             *           如果重写的方法体只有一行代码而且是return *** ;
             *                                      可以直接省略 return 和 ;   直接写 ****    */
        }
        {
//            自定义泛型
            /*  在定义类 接口后方添加<E> 表示泛型类 接口
             * public class test<E> {
             *   public void fun(E e){
             *       ...
             *   }
             *   ...
             * }*/
            /*  在方法前添加<泛型变量>即可实行接收任意数据类型的泛型方法
             *   public <T> void fun(T t){
             *       ...
             *   }*/

            /*  泛型的通配符: ?       只有在使用泛型时才会用 ?*/
//            public void run (Arraylist < ? > car){}

            /*  泛型上下限 :
             *             上限  ?extends Car   必须是Car或Car的子类
             *             下限  ?super Car     必须是Car或Car的父类*/
//            public void run (Arraylist < ? extends Car > car){}
//            public void run (Arraylist < ? super Car > car){}

        }
        {
//            集合Collection
            /*  单列集合Collection  每个元素有一个值
             *  双列集合Map  每个元素有两个值(即:键值对)*/

//            单列集合Collection是一个接口, 分类如下:
/*            Collection    Set     HashSet     LinkedHashSet
                                    TreeSet
                            List    LinkedList
                                    ArrayList

                    Set系类集合: 添加的元素 无序 不重复 无索引
                            HashSet 无序 不重复 无索引(LinkedHashSet 有序 不重复 无索引)
                            TreeSet 默认升序(数值按大小, 字符串按首字母, 其他对象需要自定义排序) 不重复 无索引
                    List系类集合: 添加的元素 有序 可重复 有索引
                            ArrayList基于动态数组实现的非线程安全的集合(查询快)；
                            LinkedList基于链表实现的非线程安全的集合(增删快)。
*/
            Collection list_0 = new ArrayList();
            list_0.add("JLY");
            list_0.add("JLY");
            list_0.add("147258");
            list_0.add(123);
            System.out.println("有序LIST:" + list_0);

            Collection list_1 = new HashSet();
            list_1.add("JLY");
            list_1.add("JLY");
            list_1.add("147258");
            list_1.add(123);
            list_1.add(789);
            list_1.add(456);
            list_1.add("啊啊");
            list_1.add("abc");

            System.out.println("无序Hash:" + list_1);
            /*      可以使用 <引用类型> 设定约束,
             *       注: 集合和泛型都只支持引用数据类型(每个元素都认为是对象) */

//            在使用Collection的Add添加元素时如果添加成功(或者是可以添加) 就会返回true
            System.out.println(list_1.add("JLY"));
            System.out.println(list_1.add("Ly"));

//            使用clear方法可以快速清空集合
            list_0.clear();
            System.out.println(list_0);

//            使用isEmpty方法判断集合是否为空(空返回true, 非空返回false)
            System.out.println(list_0.isEmpty());
            System.out.println(list_1.isEmpty());

//            使用size方法判断集合大小
            System.out.println(list_1.size());

//            使用contains方法判断集合是否包含某个元素(包含返回true)
            System.out.println(list_1.contains("JLY"));

//            使用remove方法删除集合中某个元素(成功返回true)
//            如果有多个重复元素 默认删除第一个
            System.out.println(list_1.remove("147258"));

//            集合转换为数组
            Object[] object_arr = list_1.toArray();

//            使用addAll可以实现数组合并
            list_0.addAll(list_1);
            System.out.println(list_0);
            System.out.println("------------------------------");


            /*          集合的3种遍历         */
//            1使用迭代器(iterator<E> it), 这是集合的专用遍历方式
            /*  定义一个迭代器对象it, 默认it会指向集合的第一个元素
             *      调用一次it.next()之后, it.next()会指向集合的下一个元素
             *  it.hasNext()会判断是否存在下一个元素, 返回一个boolean值*/
            System.out.println("======使用迭代器======");
            Iterator it = list_0.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
            /*  使用迭代器进行遍历删除时, 如果使用list_0.remove会造成索引后移
             *       跳过元素, 因此可以使用it.remove(此方法会自动索引减一,不会造成元素跳过)
             *  使用增强for和foreach也会造成同样的情况, 但这二者没有设置相应的处理方法,
             *      由此进行遍历删除时, 推荐使用迭代器 + it.remove
             *          (或者使用单纯的for循环进行删除, 可以设置索引减一或者倒着遍历删除)*/
            System.out.println("------------------------------");

//            2使用增强for进行遍历
            /*  注: 在增强for内进行修改集合元素值无意义, 因为修改的是创建的新对象(obj)的值*/
            System.out.println("======使用增强for======");
            for (Object obj : list_0) {
                System.out.println(obj);
            }
            System.out.println("------------------------------");
//            3 使用lambda表达式遍历(API: foreach)
            System.out.println("======使用foreach======");
            list_0.forEach(new Consumer() {
                @Override
                public void accept(Object o) {
                    System.out.println(o);
                }
            });
//            简化为lambda表达式
            list_1.forEach(o -> System.out.println("简化:" + o));

            System.out.println("----------------------------------");

//            Set系列HashSet LinkHashSet TreeSet

            /*  哈希值: JDK根据对象的地址从而计算出的int型数值(内存地址转换为int数值)*/
            /*  API : public int hashCode()        返回对象hash值*/
            /*      同一个对象多次调用返回的hash值相同, 默认不同对象的哈希值不同*/
            StringBuilder str = new StringBuilder();
            str.append("132456789");
            int hashValue = str.hashCode();
            System.out.println("字符串的hash值:" + hashValue);

            /*    TreeSet自定义排序的方法
             *           1让自定义的类(比如students)实现Comparable接口
             *                  并且在自定义类中重写compareTo方法
             *                  重写的方法可以直接
             *                  return this.student.getAge() - o.student.getAge >= 0? 1 : -1;(升序)
             *                  (对于浮点型数据建议使用Double.compare(参数1, 参数2))
             *           2利用Comparator接口实现(即: lambda表达式)
             *                  Set<Students> s = TreeSet<>((o1, o2) -> return this.student.getAge() - o.student.getAge >= 0? 1 : -1;)*/


            /*  可变参数   实现接受0个至多个参数 (数据类型...参数名称)
             *   (本质在其内部就是一个数组)*/
            Test7 t7 = new Test7();
            t7.sum();
            t7.sum(1, 2);
            t7.sum(123, 465, 789);
            /*  注: 方法的形参列表中, 可变参数只能有一个, 并且可变参数只能在形参列表最后面*/
            System.out.println("---------------------------");
        }
        {
//            Collection的工具类: Collections.方法
            /*  详见Java文档*/
//            批量加元素
            List<String> names = new ArrayList<>();
            Collections.addAll(names, "J", "L", "Y", "l", "y");
            System.out.println(names);
//            打乱集合顺序
            Collections.shuffle(names);
            System.out.println(names);
//            进行排序
            /*  使用sort结合匿名类*/
            System.out.println("---------------------------");

        }
        {
//                      Map集合(键值对集合)
            /*  即: 双列集合, 每个元素包含两个数据(key value)*/
            /*  Map     HashMap     LinkedHashMap
             *          HashTable   LinkedHashTable
             *          ......   TreeMap
             *          */
            /*  HashMap: 无序, 不重复, 无索引, 值不做要求
             *  LinkedHashMap:  有序, 不重复, 无索引, 值不做要求
             *  TreeMap: 排序, 不重复, 无索引, 值不做要求*/


            /*  Map体系特点由键决定,
             *               键是 无序 不重复 无索引  , 值可以重复
             *               (Map重复的键对应的值会覆盖前面重复键的值)
             *               键值对都可以是null*/

            Map<String, Integer> maps = new HashMap<>();
            maps.put("Java", 15);
            maps.put("HTML", 30);
            maps.put("HTML", 20);
            maps.put("SQL", 10);
            maps.put(null, null);
            System.out.println(maps);

            /*  Map体系常用API:
             *      maps.put(K,V)  添加元素
             *      maps.remove(K)  根据键删除键值对(返回值)
             *      maps.clear()    清空集合
             *      maps.containsKey(K)  判断是否包含指定Key(boolean)
             *      maps.containsValue(V)  判断是否包含指定Value(boolean)
             *      maps.isEmpty()  判断集合是否为空(boolean)
             *      maps.size()  返回集合大小
             *
             *      maps.get(K)  根据键返回值(V)
             *      Set<String> keys = maps.keySet()    获取全部键的集合
             *      Collection<String> values = maps.value()    获取全部键的集合
             *
             *      maps.putAll(maps_other) 将其他集合拷贝到maps*/

            /*      遍历3种方法      */
//            分离键, 再找值
            System.out.println("遍历方法1, 键找值:");
            Set<String> keys = maps.keySet();
            for (String key : keys) {
                System.out.println(key + "->" + maps.get(key));
            }


//            键值作为条目(Set<Map.Entry<K, V>> e = maps.entrySet())
            /*  e.getKey() 和 e.getValue() 获取 键 值*/
            System.out.println("遍历方法2, 键值对:");
            Set<Map.Entry<String, Integer>> entries = maps.entrySet();
            for (Map.Entry<String, Integer> e : entries) {
                System.out.println(e.getKey() + "->" + e.getValue());
            }

//            使用lambda表达式遍历
            System.out.println("遍历方法, lambda表达式:");
            maps.forEach(new BiConsumer<String, Integer>() {
                @Override
                public void accept(String s, Integer integer) {
                    System.out.println(s + "->" + integer);

                }
            });

            maps.forEach((k, v) -> System.out.println(k + "->" + v));
        }
        {
//            集合的嵌套
            Map<String, List<Integer>> a = new HashMap<>();
            List<Integer> l = new ArrayList<Integer>();
            l.add(1);
            l.add(2);
            l.add(3);
            a.put("J", l);
            System.out.println(a);

        }

    }
}

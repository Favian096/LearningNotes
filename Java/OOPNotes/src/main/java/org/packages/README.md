# Java Grammer



## Fundamental

> Java 的基础语法

### [Static-静态语法](./Static.java)

- static 修饰符
- 静态代码块

### [继承和多态](./Java_Extends_polymorphism/ExtendsAndPolymorphism.java)

- 继承的规则
- 多态的访问

### [面向对象基础](./OOPBasic.java)

- 权限修饰符
- final关键字
- 枚举类
- 抽象类
- 接口
- 内部类, 静态内部类, 成员内部类, 匿名内部类
- BigDecimal精确运算

### [时间和日期](./DataTime.java)

- 格式化转换

### [正则表达式](./RegularExpress.java)

- 表达式规则

### [泛型和集合](./CollectionMap.java)

- Lambda表达式
- 泛型
- Collection集合概述
- Map集合概述

### [Stream和异常](./StreamException.java)

- 不可变集合
- Stream流的使用
- 异常处理

### [基础日志](./LogBasic.java)

- 日志打印, 日志级别

### [文件操作](./FileOperate.java)

- 文件属性获取

- 文件目录遍历

### [文件读写](./FileReadWrite.java)

- 文件编码解码
- 内容读写

### [文件流](./IOStream.java)

- 字节流
- 字符类
- 缓冲流
- 转化流

### [多线程](./MultiThread.java)

- 3 种实现方式

### [线程安全](./ThreadSafe.java)

- synchronized
- lock
- 线程池技术概述

### [定时器](./Timer.java)

- 基础定时器
- 多线程定时器

### [网络基础](./Network.java)

- IP获取
- 动态代理



## Advanced

### 



## Expansion

### 方法引用

> 用于简化 Lambda 表达式

- **使用条件:** 

    - **Lambda表达式的主体仅包含一个表达式，且Lambda表达式只调用了一个已经存在的方法；**

    - **被引用的方法的参数列表和返回值与Lambda表达式的输入输出一致**

```java
// 引用示例
new Random().ints(10)
	.map(Math::abs)
	.forEach(System.out::println);

// 静态方法引用
Function<String, Integer> f = Integer::parseInt;

// 构造器引用
Supplier<List<String>> s = ArrayList::new;
List<String> list = s.get();

// 类的实例方法引用
List<String> list = Arrays.asList("a", "bb", "ccc");
list.sort(String::compareToIgnoreCase);

// 实例对象的方法引用
String str = "hello";
Supplier<Integer> s = str::length;
```

- 引用的 4 种常见形式

|      引用方式      |              说明               |
| :----------------: | :-----------------------------: |
|    静态方法引用    |  `ClassName::staticMethodName`  |
|     构造器引用     |        `ClassName::new`         |
|  类的实例方法引用  | `ClassName::instanceMethodName` |
| 实例对象的方法引用 |  `instance::StaticMethodName`   |


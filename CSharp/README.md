# C# Notes
## Basic
### 基本语法

- C # .Net 框架的一部分

- 与 Java 不同的是，文件名可以不同于类的名称(而使用命名空间来实现访问)

- *在VS中输入 cw 回车(tab) 即可快速输入 Console.WriteLine();*

- C#引入了var关键字用于推断类型(类似C++11的auto)

  ```c#
  // 示例代码展示
  
  using System;  //用于声明程序中包含 System 命名空间,程序中一般有多个 using 语句
  
  namespace HelloWorldApplicatioin{  //自定义命名空间, 包含了一系列的类
      public class HelloWorld{   // 类名
          public void Main(string[] args){   // Main方法(M大写)
              const string str = "瑠璃璃啊";   // const 即java的final关键字
              Class1 c = new Class1();
              c.printStr(str);
              Console.ReadKey(); 
          }
      }
      
      public class Class1{
          public void printStr(String str){
              Console.WriteLine(str);
          }
      }
      
  }
  ```



### 数据类型

- **值类型(基本类型)**

  | 类型    | 描述                                 | 范围                                                    | 默认值 |
  | ------- | ------------------------------------ | ------------------------------------------------------- | ------ |
  | byte    | 8 位无符号整数                       | 0 到 255                                                | 0      |
  | short   | 16 位有符号整数类型                  | -32,768 到 32,767                                       | 0      |
  | int     | 32 位有符号整数类型                  | -2,147,483,648 到 2,147,483,647                         | 0      |
  | long    | 64 位有符号整数类型                  | -9,223,372,036,854,775,808 到 9,223,372,036,854,775,807 | 0L     |
  | decimal | 128 位精确的十进制值，28-29 有效位数 | (-7.9 x 1028 到 7.9 x 1028)  / 100 到 28                | 0.0M   |
  | float   | 32 位单精度浮点型                    | -3.4 x 1038 到 + 3.4 x 1038                             | 0.0F   |
  | double  | 64 位双精度浮点型                    | (+/-)5.0 x 10-324 到 (+/-)1.7 x 10308                   | 0.0D   |
  | char    | 16 位 Unicode 字符                   | U +0000 到 U +ffff                                      | '\0'   |
  | bool    | 布尔值                               | True 或 False                                           | False  |
  | sbyte   | 8 位有符号整数类型                   | -128 到 127                                             | 0      |
  | uint    | 32 位无符号整数类型                  | 0 到 4,294,967,295                                      | 0      |
  | ulong   | 64 位无符号整数类型                  | 0 到 18,446,744,073,709,551,615                         | 0      |
  | ushort  | 16 位无符号整数类型                  | 0 到 65,535                                             | 0      |

- **引用类型**

  - **Object**类型:

    Object是数据类型的终极基类(同java)

    object 是 System.Object 类的别名(大小写都一样)

    ```c#
    Object == object
    //当一个值类型转换为对象类型时，则被称为 装箱；另一方面，当一个对象类型转换为值类型时，则被称为 拆箱
    ```

  - **dynamic**类型

    存储任何类型的值在动态数据类型变量中。这些变量的类型检查是在运行时发生的。

    声明动态类型的语法：

    ```c#
    dynamic <variable_name> = value;  // 类似于auto
    dynamic d = 20;
    ```

    动态类型与对象类型相似，但是对象类型变量的类型检查是在编译时发生的，而动态类型变量的类型检查是在运行时发生的。

  - **string**类型

    字符串（String）类型是 System.String 类的别名。它是从对象（Object）类型派生的

    ```c#
    String == string
    ```

    C# string 字符串的前面可以加 @（称作"逐字字符串"）将转义字符（\）当作普通字符对待，比如：

    ```c#
    string str = @"C:\Windows";
    
    string str = "C:\\Windows"; // 等价于上方
    ```

    @ 字符串中可以任意换行，换行符及缩进空格都计算在字符串长度之内。

    ```
    string str = @"<script type=""text/javascript"">
        <!--
        -->
    </script>";
    ```

    用户自定义引用类型有：class、interface 或 delegate。

- **指针类型**

  指针类型变量存储另一种类型的内存地址。C# 中的指针与 C 或 C++ 中的指针有相同的功能。
  
  声明指针类型的语法：
  
  ```c#
  type* identifier;
  //例如
  char* cptr;
  int* iptr;
  ```



### 类型转换

- **隐式类型转换**

  ```c#
  byte b = 10;
  int i = b; // 隐式转换，不需要显式转换
  ```

- **显式类型转换，即强制类型转换。**

  ```c#
  int i = 10;
  byte b = (byte)i; // 显式转换，需要使用强制类型转换符号
  
  int intValue = 123;
  string stringValue = intValue.ToString(); // 将 int 转换为字符串
  ```

  C# 提供了下列内置的类型转换方法：

  | 序号 | 方法 & 描述                                                  |
  | ---- | ------------------------------------------------------------ |
  | 12   | **ToString** 把类型转换为字符串类型。                        |
  | 2    | **ToByte** 把类型转换为字节类型。                            |
  | 7    | **ToInt16** 把类型转换为 16 位整数类型。Convert.ToInt16(num); |
  | 8    | **ToInt32** 把类型转换为 32 位整数类型。                     |
  | 9    | **ToInt64** 把类型转换为 64 位整数类型。                     |
  | 1    | **ToBoolean** 如果可能的话，把类型转换为布尔型。             |
  | 3    | **ToChar** 如果可能的话，把类型转换为单个 Unicode 字符类型。 |
  | 4    | **ToDateTime** 把类型（整数或字符串类型）转换为 日期-时间 结构。 |
  | 5    | **ToDecimal** 把浮点型或整数类型转换为十进制类型。           |
  | 6    | **ToDouble** 把类型转换为双精度浮点型。                      |
  | 10   | **ToSbyte** 把类型转换为有符号字节类型。                     |
  | 11   | **ToSingle** 把类型转换为小浮点数类型。                      |
  | 13   | **ToType** 把类型转换为指定类型。                            |
  | 14   | **ToUInt16** 把类型转换为 16 位无符号整数类型。              |
  | 15   | **ToUInt32** 把类型转换为 32 位无符号整数类型。              |
  | 16   | **ToUInt64** 把类型转换为 64 位无符号整数类型。              |



### 语法补充

- 枚举

  ```c#
  enum <enum_name>{ 
      enumeration list 
  };
  ```
  
- 运算符补充

  | 运算符   | 描述                                   | 实例                                                         |
  | -------- | -------------------------------------- | ------------------------------------------------------------ |
  | sizeof() | 返回数据类型的大小。                   | sizeof(int)，将返回 4.                                       |
  | typeof() | 返回 class 的类型。                    | typeof(StreamReader);                                        |
  | &        | 返回变量的地址。                       | &a; 将得到变量的实际地址。                                   |
  | *        | 变量的指针。                           | *a; 将指向一个变量。                                         |
  | ? :      | 条件表达式                             | 如果条件为真 ? 则为 X : 否则为 Y                             |
  | is       | 判断对象是否为某一类型。               | If( Ford is Car)  // 检查 Ford 是否是 Car 类的一个对象。     |
  | as       | 强制转换，即使转换失败也不会抛出异常。 | Object obj = new StringReader("Hello");      StringReader r = obj as StringReader; |

- **方法的引用传值和输出传值**

  | 方式     | 描述                                                         |
  | -------- | ------------------------------------------------------------ |
  | 引用参数 | 这种方式复制参数的内存位置的引用给形式参数。这意味着，当形参的值发生改变时，同时也改变实参的值。**(用于改变传递的值)** |
  | 输出参数 | 这种方式可以返回多个值。**(用于接收要变更的值)**             |

  ```c#
  //使用引用改变|交换值
  public void swap(ref int a, ref int b) {
      a = a ^ b;
      b = a ^ b;
      a = a ^ b;  
  }
  
  //使用输出来改变值
  public void change(out int x, out int y) {
      x = 123;
      y = 456;
  }
  
  //调用
  int a = 1, b = 2, c = 1, d = 1;
  swap(ref a, ref b);
  change(out x, out y);
  ```

- 可空类型

  一个特殊的数据类型，**Nullable** 类型（可空类型）

  Nullable< Int32 >，读作"可空的 Int32"，可以被赋值为 -2,147,483,648 到  2,147,483,647 之间的任意值，也可以被赋值为 null 值。类似的，Nullable< bool > 变量可以被赋值为  true 或 false 或 null。

  在处理可能未赋值的数据类型时，用 null 赋值特别有用。

  ```c#
  //声明语法
  <data_type> ? <variable_name> = null;
  NUllable<date_type> <varable_name> = new Nullable<int>(null);
  
  int? a = 3;
  double? b = new double?();
  
  //相当于
  Nullable<int> a = 3;
  ```

  合并运算符（ ?? ）

  作用是判断??左边的对象是否为 null，如果不为返回左边，如果为则返回右边

  *相当于三元表达式的简写*

  ```C#
  num2 = num1 ?? 5.34;      // num1 如果为空值则返回 5.34
  //也就是 num2 = num1 == null? 5.34:num1;
  ```

- 可变参数->参数数组

  ```c#
  // public 返回类型 方法名称( params 类型名称[] 数组名称 )
  //示例
  public int AddElements(params int[] arr) {
      int sum = 0;
      foreach (int i in arr)    {
         sum += i;
      }
      return sum;
   }
  ```
  
- Array类

  类属性

  | 序号 | 属性 & 描述                                                  |
  | ---- | ------------------------------------------------------------ |
  | 1    | **IsFixedSize** 获取一个值，该值指示数组是否带有固定大小。   |
  | 2    | **IsReadOnly** 获取一个值，该值指示数组是否只读。            |
  | 3    | **Length** 获取一个 32 位整数，该值表示所有维度的数组中的元素总数。 |
  | 4    | **LongLength** 获取一个 64 位整数，该值表示所有维度的数组中的元素总数。 |
  | 5    | **Rank** 获取数组的秩（维度）。                              |

  下表列出了 Array 类中一些最常用的方法：

  | 序号 | 方法 & 描述                                                  |
  | ---- | ------------------------------------------------------------ |
  | 1    | **Clear** 根据元素的类型，设置数组中某个范围的元素为零、为 false 或者为 null。 |
  | 2    | **Copy(Array, Array, Int32)** 从数组的第一个元素开始复制某个范围的元素到另一个数组的第一个元素位置。长度由一个 32 位整数指定。 |
  | 3    | **CopyTo(Array, Int32)** 从当前的一维数组中复制所有的元素到一个指定的一维数组的指定索引位置。索引由一个 32 位整数指定。 |
  | 4    | **GetLength**  获取一个 32 位整数，该值表示指定维度的数组中的元素总数。 |
  | 5    | **GetLongLength** 获取一个 64 位整数，该值表示指定维度的数组中的元素总数。 |
  | 6    | **GetLowerBound** 获取数组中指定维度的下界。                 |
  | 7    | **GetType** 获取当前实例的类型。从对象（Object）继承。       |
  | 8    | **GetUpperBound** 获取数组中指定维度的上界。                 |
  | 9    | **GetValue(Int32)** 获取一维数组中指定位置的值。索引由一个 32 位整数指定。 |
  | 10   | **IndexOf(Array, Object)** 搜索指定的对象，返回整个一维数组中第一次出现的索引。 |
  | 11   | **Reverse(Array)** 逆转整个一维数组中元素的顺序。            |
  | 12   | **SetValue(Object, Int32)** 给一维数组中指定位置的元素设置值。索引由一个 32 位整数指定。 |
  | 13   | **Sort(Array)** 使用数组的每个元素的 IComparable 实现来排序整个一维数组中的元素。 |
  | 14   | **ToString** 返回一个表示当前对象的字符串。从对象（Object）继承。 |

- **结构体struct**

  C# 中的结构有以下特点：

  - 结构可带有方法、字段、索引、属性、运算符方法和事件。
  - 结构可定义构造函数，但不能定义析构函数。不能为结构定义无参构造函数。无参构造函数(默认)是自动定义的，且不能被改变。
  - 结构不能继承其他的结构或类。
  - 结构不能作为其他结构或类的基础结构。
  - 结构可实现一个或多个接口。
  - 结构成员不能指定为 abstract、virtual 或 protected。
  - 当您使用 **new** 操作符创建一个结构对象时，会调用适当的构造函数来创建结构。结构可以不使用new即可被实例化。
  - 如果不使用new操作符，只有在所有的字段都被初始化之后，字段才被赋值，对象才被使用。

  ```c#
  struct Books{
     public string title;
     public string author;
     public string subject;
     public int book_id;
  };  
  
  //使用
  Books Book1;        /* 声明 Book1，类型为 Books */
  Book1.title = "C Programming";
  Book1.author = "Nuha Ali";
  Book1.subject = "C Programming Tutorial";
  Book1.book_id = 6495407;
  ```
  
  类和结构有以下几个基本的不同点：
  
  **值类型 vs 引用类型：**
  
  - **结构是值类型（Value Type）：** 结构是值类型，它们在栈上分配内存，而不是在堆上。**当将结构实例传递给方法或赋值给另一个变量时，将复制整个结构的内容**。
  - **类是引用类型（Reference Type）：** 类是引用类型，它们在堆上分配内存。**当将类实例传递给方法或赋值给另一个变量时，实际上是传递引用（内存地址）而不是整个对象的副本**。
  
  **继承：**
  
  - **结构不能继承：** 结构不能继承其他结构或类，也不能作为其他结构或类的基类。
  - **类支持继承：** 类支持单继承，一个类可以继承另一个类的成员，并且可以实现多个接口。
  
  **默认构造函数：**
  
  - **结构不能有无参数的构造函数：** 结构可以没有但不能包含无参数的构造函数。
  - **类可以有无参数的构造函数：** 类可以包含无参数的构造函数，如果没有提供构造函数，系统会提供默认的无参数构造函数。
  
  **可空性：**
  
  - **结构可以是可空的：** 结构可以被声明为可空，即可以赋予 `null` 值。
  - **类默认可为null：** 类的实例默认可以为 `null`，因为它们是引用类型。
  
  **性能和内存分配：**
  
  - **结构通常更轻量：** 由于结构是值类型且在栈上分配内存，它们通常比类更轻量，适用于简单的数据表示。
  - **类可能有更多开销：** 由于类是引用类型，可能涉及更多的内存开销和管理。



## OOP

### 类

- **类的默认访问标识符是internal，成员的默认访问标识符是 private**
- 和java相同, 定义了有参构造函数后, 默认的无参构造函数会自动消失

- **C#的析构函数同C++, 即 ~ClassName(){...}   无返回值, 不能被重载和继承**
- **C#的静态成员同java, 无论有多少个类的对象被创建，只会有一个该静态成员的副本**




### 封装

- 修饰符范围比较

  ```c#
  // internal 访问修饰符的任何成员可以被定义在该成员所定义的应用程序内的任何类或方法访问
  public > protected internal > internal > protected > private
  //其中protected和internal可以连用, 表示符合任意一条都可以访问
  ```



### 继承

- 基本语法

  ```c#
  class <派生类> : <基类>{...}
  
  class <派生类> : <基类>, <接口1>, <接口2>... {...}  
  
  //示例
  public class Base{};
  
  public interface Iface1{};
  public interface Iface2{};
  
  public class Derived : Base, Iface1, Iface2{...}
  ```

- 同样, **C#不支持多继承, 支持多实现, 但写法相同**

  ```c#
  //重写成员
  
  public class Animal{
      public virtual float speed = 1.0f;
      
      public virtual fun(){
          Console.WriteLine(speed);
      }
  }
  
  //继承并重写方法
  public class cat : Animal{
      public override fun(){
          this.speed = 10.0f;
          base.fun();
      }
  }
  ```

  



### 多态

#### 静态多态

- 函数重载同java

- 运算符重载同C++

  ```c#
  //通过关键字 operator 后跟运算符的符号来定义
  
  public static Box operator+ (Box b, Box c){
     Box box = new Box();
     box.length = b.length + c.length;
     box.breadth = b.breadth + c.breadth;
     box.height = b.height + c.height;
     return box;
  }
  
  //使用
  Box3 = Box1 + Box2;
  ```
  
  | 支持重载的运算符                      | 描述                                         |
  | ------------------------------------- | -------------------------------------------- |
  | +, -, !, ~, ++, --                    | 这些一元运算符只有一个操作数，且可以被重载。 |
  | +, -, *, /, %                         | 这些二元运算符带有两个操作数，且可以被重载。 |
  | ==, !=, <, >, <=, >=                  | 这些比较运算符可以被重载。                   |
  | &&, \|\|                              | 这些条件逻辑运算符不能被直接重载。           |
  | +=, -=, *=, /=, %=                    | 这些赋值运算符不能被重载。                   |
  | =, ., ?:, ->, new, is, sizeof, typeof | 这些运算符不能被重载。                       |



#### 动态多态

- ***抽象类***: 关键字 **abstract** 创建抽象类，用于提供接口的部分类的实现(方法体)

  - 有关抽象类的一些规则：
    - 您不能创建一个抽象类的实例。
    - 您不能在一个抽象类外部声明一个抽象方法。
    - 通过在类定义前面放置关键字 **sealed**，可以将类声明为**密封类**。当一个类被声明为 **sealed** 时，它不能被继承。抽象类不能被声明为 sealed。

  ```c#
  //示例
  abstract class Shape   {
         abstract public int area();
  }
  
  class Rectangle : Shape{
         //松override关键字重新
        public override int area (){
           Console.WriteLine("Rectangle 类的面积：");
           return 999;
      }
  }
  ```
  
  

- 当有一个定义在类中的函数需要在继承类中实现时，可以使用**虚方法**

  使用关键字 **virtual** 声明, 定义虚方法时, 必须实现虚方法

- 动态多态性是通过 **抽象类** 和 **虚方法** 实现的

  ```c#
  //示例
  public class Shape{
      // 虚方法
      public virtual void Draw(){
          Console.WriteLine("执行基类的画图任务");
      }
  }
  
  class Circle : Shape{
      //使用override关键字类重新方法
      public override void Draw(){
          base.Draw();        // 可以使用base来运行父类的方法, 类似java的super();
          Console.WriteLine("画一个圆形");
      }
  }
  ```
  
  
  
- **抽象方法(abstract)和虚方法(virtual)的区别**(覆盖时都使用**override**关键字)

  - 抽象方法定义时不能写方法体, 虚方法定义时必须写方法体;
  - 抽象方法必须包含在抽象类中, 虚方法可以在普通类|抽象类中;
  - 抽象方法必须被实现, 虚方法可以不被实现;



### 接口

- 接口定义了派生类应遵循的语法合同, 提供了派生类应遵循的标准结构

  接口定义了属性、方法和事件，这些都是接口的成员。

  接口只包含了成员的声明。成员的定义是派生类的责任。

- **接口声明默认是 public 的**



### 命名空间

- 命名空间可以嵌套

  ```c#
  namespace namespace_name1{
     // 代码声明
     namespace namespace_name2{
       // 代码声明
     }
  }
  
  //使用可以这样
  using namespace_1.namespace_2;
  ```
  
  

- 为了调用命名空间内的函数或变量，会把命名空间的名称置于前面

  ```c#
  //示例 namespace_name.item_name;
  
  namespace first_space{
     class namespace_cl{
        public void func(){
           Console.WriteLine("Inside first_space");
        }
     }
  }
  
  class TestClass{
     static void Main(string[] args){
         //以 . 形式调用
        first_space.namespace_cl fc = new first_space.namespace_cl();
        fc.func();
     }
  }
  ```
  
- **using** 关键字表明程序使用的是给定命名空间中的名称



### 预处理器指令

- 预处理器指令指导编译器在实际编译开始之前对信息进行预处理。

  所有的预处理器指令都是以 # 开始。且在一行上，只有空白字符可以出现在预处理器指令之前。预处理器指令不是语句，所以它们不以分号（;）结束。

  C# 编译器没有一个单独的预处理器，但是，指令被处理时就像是有一个单独的预处理器一样。在 C# 中，预处理器指令用于在条件编译中起作用。与 C 和 C++ 不同的是，它们不是用来创建宏。一个预处理器指令必须是该行上的唯一指令。

  | 预处理器指令 | 描述                                                         |
  | ------------ | ------------------------------------------------------------ |
  | #define      | 它用于定义一系列成为符号的字符。                             |
  | #undef       | 它用于取消定义符号。                                         |
  | #if          | 它用于测试符号是否为真。                                     |
  | #else        | 它用于创建复合条件指令，与 #if 一起使用。                    |
  | #elif        | 它用于创建复合条件指令。                                     |
  | #endif       | 指定一个条件指令的结束。                                     |
  | #line        | 它可以让您修改编译器的行数以及（可选地）输出错误和警告的文件名。 |
  | #error       | 它允许从代码的指定位置生成一个错误。                         |
  | #warning     | 它允许从代码的指定位置生成一级警告。                         |
  | #region      | 它可以让您在使用 Visual Studio Code Editor 的大纲特性时，指定一个可展开或折叠的代码块。 |
  | #endregion   | 它标识着 #region 块的结束。                                  |



### 异常处理

- C# 异常处理时建立在四个关键词之上的：**try**、**catch**、**finally** 和 **throw**。(同java)
  - **try**：一个 try 块标识了一个将被激活的特定的异常的代码块。后跟一个或多个 catch 块。
  - **catch**：程序通过异常处理程序捕获异常。catch 关键字表示异常的捕获。
  - **finally**：finally 块用于执行给定的语句，不管异常是否被抛出都会执行。例如，如果您打开一个文件，不管是否出现异常文件都要被关闭。
  - **throw**：当问题出现时，程序抛出一个异常。使用 throw 关键字来完成。

- C# 中的异常类主要是直接或间接地派生于 **System.Exception** 类

  ```c#
  //示例
  try{
     // 引起异常的语句  如 throw new Exception("瑠璃璃啊");
  }catch( ExceptionName e1 ){
     // 错误处理代码
  }catch( ExceptionName e2 ){
     // 错误处理代码
  }finally{
     // 要执行的语句
  }
  ```
  
  

### 输入与输出

- System.IO 命名空间中的 **FileStream** 类有助于文件的读写与关闭。该类派生自抽象类 Stream。

  创建一个 FileStream 对象 **F** 来读取名为 **sample.txt** 的文件：

  ```c#
  FileStream F = new FileStream("sample.txt", FileMode.Open, FileAccess.Read, FileShare.Read);
  ```


- | 参数       | 描述                                                         |
  | ---------- | ------------------------------------------------------------ |
  | FileMode   | **FileMode** 枚举定义了各种打开文件的方法。FileMode 枚举的成员有： **Append**：打开一个已有的文件，并将光标放置在文件的末尾。如果文件不存在，则创建文件。 **Create**：创建一个新的文件。如果文件已存在，则删除旧文件，然后创建新文件。 **CreateNew**：指定操作系统应创建一个新的文件。如果文件已存在，则抛出异常。 **Open**：打开一个已有的文件。如果文件不存在，则抛出异常。 **OpenOrCreate**：指定操作系统应打开一个已有的文件。如果文件不存在，则用指定的名称创建一个新的文件打开。 **Truncate**：打开一个已有的文件，文件一旦打开，就将被截断为零字节大小。然后我们可以向文件写入全新的数据，但是保留文件的初始创建日期。如果文件不存在，则抛出异常。 |
  | FileAccess | **FileAccess** 枚举的成员有：**Read**、**ReadWrite** 和 **Write**。 |
  | FileShare  | **FileShare** 枚举的成员有： **Inheritable**：允许文件句柄可由子进程继承。Win32 不直接支持此功能。 **None**：谢绝共享当前文件。文件关闭前，打开该文件的任何请求（由此进程或另一进程发出的请求）都将失败。  **Read**：允许随后打开文件读取。如果未指定此标志，则文件关闭前，任何打开该文件以进行读取的请求（由此进程或另一进程发出的请求）都将失败。但是，即使指定了此标志，仍可能需要附加权限才能够访问该文件。 **ReadWrite**：允许随后打开文件读取或写入。如果未指定此标志，则文件关闭前，任何打开该文件以进行读取或写入的请求（由此进程或另一进程发出）都将失败。但是，即使指定了此标志，仍可能需要附加权限才能够访问该文件。  **Write**：允许随后打开文件写入。如果未指定此标志，则文件关闭前，任何打开该文件以进行写入的请求（由此进程或另一进过程发出的请求）都将失败。但是，即使指定了此标志，仍可能需要附加权限才能够访问该文件。  **Delete**：允许随后删除文件。 |



## Advanced

### 特性（Attribute）

- **特性（Attribute）**是用于在运行时传递程序中各种元素（比如类、方法、结构、枚举、组件等）的行为信息的声明性标签。(java的注解)

  - 预定义特性
  - 自定义特性

- **预定义特性** : .Net 框架提供了三种预定义特性：

  - AttributeUsage

    描述了如何使用一个自定义特性类。它规定了特性可应用到的项目的类型。

  - Conditional

    标记了一个条件方法，其执行依赖于指定的预处理标识符。

  - Obsolete

    标记了不应被使用的程序实体	

- **自定义特性**

  - 创建并使用自定义特性包含四个步骤：
    - 声明自定义特性(派生自 **System.Attribute**)
    - 构建自定义特性
    - 在目标程序元素上应用自定义特性
    - 通过反射访问特性



### 反射

- 指程序可以访问、检测和修改它本身状态或行为的一种能力。

- 反射（Reflection）有下列用途：
  - 它允许在运行时查看特性（attribute）信息。
  - 它允许审查集合中的各种类型，以及实例化这些类型。
  - 它允许延迟绑定的方法和属性（property）。
  - 它允许在运行时创建新类型，然后使用这些类型执行一些任务。



### 属性语法糖

- 即: get和set方法, 使用 **访问器（accessors）** 让私有域的值可被读写或操作

  ```c#
  private string code; // 小写
  // 比如 声明类型为 string 的 Code 属性
  public string Code   // 大写
  {
     get{
        return code;
     }
     set{
        code = value;
     }
  }
  
  //这样的实现就相当于java的getter和setter方法, 这是C#的语法糖
  //使用时, 无需调用get和set, 直接用Code即可自动判定是get还是set
  classInstance.code;  // 外部无法调用
  classInstance.Code = "瑠璃璃";  // 自动调用set
  Console.WriteLine(classInstance.Code);  // 自动调用get
  ```
  
  ```c#
  // 代码示例
  class Student{
        //私有变量
        private string name = "not known";
        private int age = 0;
     
        // 声明类型为 string 的 Name 属性
        public string Name{
           get{
              return name;
           }
           set{
              name = value;
           }
        }
  
        // 声明类型为 int 的 Age 属性
        public int Age{
           get{
              return age;
           }
           set{
              age = value;
           }
        }
        public override string ToString(){
           return "Name = " + Name + ", Age = " + Age;
        }
      }
      class ExampleDemo{
        public static void Main(){
           // 创建一个新的 Student 对象
           Student s = new Student();
           s.Name = "Zara";
           s.Age = 9;
           Console.WriteLine("Student Info: {0}", s);
           //Student Info: Name = Zara, Age = 9
           // 增加年龄
           s.Age += 1;
           Console.WriteLine("Student Info: {0}", s);
           //Student Info: Name = Zara, Age = 10
           Console.ReadKey();
         }
     }
  ```
  
- 抽象属性示例

  ```c#
  using System;
  namespace runoob{
     public abstract class Person{
        public abstract string Name{
           get;
           set;
        }
        public abstract int Age{
           get;
           set;
        }
     }
     class Student : Person{
        private string code = "N.A";
        private string name = "N.A";
        private int age = 0;
  
        // 声明类型为 string 的 Code 属性
        public string Code{
           get{
              return code;
           }
           set{
              code = value;
           }
        }
     
        // 声明类型为 string 的 Name 属性
        public override string Name{
           get{
              return name;
           }
           set{
              name = value;
           }
        }
  
        // 声明类型为 int 的 Age 属性
        public override int Age{
           get{
              return age;
           }
           set{
              age = value;
           }
        }
        public override string ToString(){
           return "Code = " + Code +", Name = " + Name + ", Age = " + Age;
        }
     }
      
     class ExampleDemo{
        public static void Main(){
           // 创建一个新的 Student 对象
           var s = new Student(){
                  Code = "001",
                  Name = "Zara",
                  Age = 10
              };
           Console.WriteLine("Student Info:- {0}", s);
           //Student Info: Code = 001, Name = Zara, Age = 9
           // 增加年龄
           s.Age += 1;
           Console.WriteLine("Student Info:- {0}", s);
           //Student Info: Code = 001, Name = Zara, Age = 10
           Console.ReadKey();
         }
     }
  }
  ```
  
  

### 索引器语法糖

- 把类或结构的示例像数组一样索引, 索引器是通过get和set访问数据的

  不需要显示指定类型和指定成员就可以设置或检索索引值;

  ```c#
  //语法格式
  
  <访问修饰符> <元素数据类型> this[参数] {
      get;set;
  }
  
  //比如通过传入手机号返回用户地址
  ```

- 一个类可以有多个索引器

- 索引器可以被重载

  ```C#
  //代码示例
  
  public class IndexerTest{
      int[] temps = new int[10];
      
      //定义索引器
      public int this[int index]{
          get => temps[index];    //通常还用进行数据检验(索引范围)
          set => temps[index] = value;
      }
  }
  
  //使用
  var test = new IndexerText();
  test[1] = 12;
  Console.WriterLine(test[9])
  ```

  

### 委托语法糖

- 所有的委托（Delegate）都派生自 **System.Delegate** 类

  ```c#
  //声明语法
  delegate <return type> <delegate-name> <parameter list>
      
  //示例 public delegate int MyDelegate (string s);
  ```

- 实例化委托（Delegate）

  一旦声明了委托类型，委托对象必须使用 **new** 关键字来创建，且与一个特定的方法有关。

  传递到 **new** 语句的参数就像方法调用一样书写，但是不带有参数。例如：

  ```c#
  namespace DelegateAppl{
     class TestDelegate   {
        delegate int NumberChanger(int n);
         
        static int num = 10;
        // 返回值和参数与委托相同
        public static int AddNum(int p){
           num += p;
           return num;
        }
        // 返回值和参数与委托相同
        public static int MultNum(int q){
           num *= q;
           return num;
        }
        public static int getNum(){
           return num;
        }
  
        static void Main(string[] args){
           // 创建委托实例, 传入方法名称, 实现委托实例化
           NumberChanger nc1 = new NumberChanger(AddNum);
           NumberChanger nc2 = new NumberChanger(MultNum);
           // 使用委托对象调用方法
           nc1(25);
           Console.WriteLine("Value of Num: {0}", getNum());
           nc2(5);
           Console.WriteLine("Value of Num: {0}", getNum());
           Console.ReadKey();
        }
     }
  }
  ```
  
- 委托的多播

   使用 "+" 运算符进行合并, 一个合并委托调用它所合并的两个委托。

  只有相同类型的委托可被合并。

  "-" 运算符可用于从合并的委托中移除组件委托。 

  ```c#
  //同上代码
  NumberChanger nc;
  NumberChanger nc1 = new NumberChanger(AddNum);
  NumberChanger nc2 = new NumberChanger(MultNum);
  nc = nc1;
  nc += nc2;  // 合并委托, 会一次调用nc1 nc2
  // 调用多播
  nc(5);
  Console.WriteLine("Value of Num: {0}", getNum());  // 输出75
  ```

  

### 事件

- 基本上说是一个用户操作, C# 中使用事件机制实现线程间的通信

  包含事件的类用于发布事件。这被称为 **发布器（publisher）** 类。

  其他接受该事件的类被称为 **订阅器（subscriber）** 类。

- 声明事件

  ```c#
  //在类的内部声明事件，首先必须声明该事件的委托类型。例如：
  public delegate void BoilerLogHandler(string status);
  
  //然后，声明事件本身，使用 event 关键字：
  // 基于上面的委托定义事件
  public event BoilerLogHandler BoilerEventLog;
  ```

  ```c#
  //示例代码
  using System;
  namespace SimpleEvent{
    using System;
    /***********发布器类***********/
    public class EventTest{
      private int value;
  
      public delegate void NumManipulationHandler();
      public event NumManipulationHandler ChangeNum;
        
      protected virtual void OnNumChanged(){
        if ( ChangeNum != null ){
          ChangeNum(); /* 事件被触发 */
        }else {
          Console.WriteLine( "event not fire" );
          Console.ReadKey(); /* 回车继续 */
        }
      }
  
      public void SetValue( int n ){
        if ( value != n )
        {
          value = n;
          OnNumChanged();
        }
      }
    }
  
    /***********订阅器类***********/
    public class subscribEvent{
      public void printf() {
        Console.WriteLine( "event fire" );
        Console.ReadKey(); /* 回车继续 */
      }
    }
  
    /***********触发***********/
    public class MainClass{
      public static void Main(){
        EventTest e = new EventTest(); /* 实例化对象,第一次没有触发事件 */
        subscribEvent v = new subscribEvent(); /* 实例化对象 */
        
        //通过 += 订阅事件 , 或者说给事件添加外部的处理方法
        e.ChangeNum += new EventTest.NumManipulationHandler( v.printf ); 
        
        e.SetValue( 7 );   // event fire
      }
    }
  }
  ```

  

### 集合

- 用于数据存储和检索的类, 大多数集合类实现了相同的接口。

  - 常用的List语法

    ```C#
    var list=new List<int>();
    
    //如
    var a=new List<int>();
    a.Add(12);
    a.Add(10);
    Console.WriteLine(a[0]);
    ```

    

  - | 其他集合类                                                   | 描述和用法                                                   |
    | ------------------------------------------------------------ | ------------------------------------------------------------ |
    | [动态数组（ArrayList）](https://www.runoob.com/csharp/csharp-arraylist.html) | 它代表了可被单独**索引**的对象的有序集合。它基本上可以替代一个数组。但是，与数组不同的是，您可以使用**索引**在指定的位置添加和移除项目，动态数组会自动重新调整它的大小。它也允许在列表中进行动态内存分配、增加、搜索、排序各项。 |
    | [哈希表（Hashtable）](https://www.runoob.com/csharp/csharp-hashtable.html) | 它使用**键**来访问集合中的元素。当您使用键访问元素时，则使用哈希表，而且您可以识别一个有用的键值。哈希表中的每一项都有一个**键/值**对。键用于访问集合中的项目。 |
    | [排序列表（SortedList）](https://www.runoob.com/csharp/csharp-sortedlist.html) | 它可以使用**键**和**索引**来访问列表中的项。 排序列表是数组和哈希表的组合。它包含一个可使用键或索引访问各项的列表。如果您使用索引访问各项，则它是一个动态数组（ArrayList），如果您使用键访问各项，则它是一个哈希表（Hashtable）。集合中的各项总是按键值排序。 |
    | [堆栈（Stack）](https://www.runoob.com/csharp/csharp-stack.html) | 它代表了一个**后进先出**的对象集合。当您需要对各项进行后进先出的访问时，则使用堆栈。当您在列表中添加一项，称为**推入**元素，当您从列表中移除一项时，称为**弹出**元素。 |
    | [队列（Queue）](https://www.runoob.com/csharp/csharp-queue.html) | 它代表了一个**先进先出**的对象集合。当您需要对各项进行先进先出的访问时，则使用队列。当您在列表中添加一项，称为**入队**，当您从列表中移除一项时，称为**出队**。 |
    | [点阵列（BitArray）](https://www.runoob.com/csharp/csharp-bitarray.html) | 它代表了一个使用值 1 和 0 来表示的**二进制**数组。当您需要存储位，但是事先不知道位数时，则使用点阵列。您可以使用**整型索引**从点阵列集合中访问各项，索引从零开始。 |



### 泛型

- 泛型类|接口|抽象类基本用法

  ```C#
  public class MyGenericArray<T>{
      
          private T[] array;
      
          public MyGenericArray(int size){
              array = new T[size + 1];
          }
      
          public T getItem(int index){
              return array[index];
          }
      
          public void setItem(int index, T value){
              array[index] = value;
          }
      }
  
  // 用法
  MyGenericArray<int> intArray = new MyGenericArray<int>(5);
  ```

- 泛型委托的基本用法

  ```c#
  static void Swap<T>(ref T lhs, ref T rhs){
              T temp;
              temp = lhs;
              lhs = rhs;
              rhs = temp;
          }
  //示例
  Swap<int>(ref a, ref b);
  Swap<char>(ref c, ref d);
  ```
  
- 泛型委托

  ```c#
  //定义
  delegate T NumberChanger<T>(T n);
  
  //示例
  delegate T NumberChanger<T>(T n);
  
  static int num = 10;
  public static int AddNum(int p){
      num += p;
      return num;
  }
  
  // 创建委托实例
  NumberChanger<int> nc1 = new NumberChanger<int>(AddNum);
  // 使用委托对象调用方法
  nc1(25);
  ```
  


### 匿名方法

- 使用委托对象调用可由委托引用的方法(**推荐lambda表达式**)

  ```c#
  //示例
  delegate void NumberChanger(int n);
  ...
  // 即直接等于|实例化一个具体方法体
  NumberChanger nc = delegate(int x){
      Console.WriteLine("Anonymous Method: {0}", x);
  };  // 需要加 ;
  
  // 或者直接使用lambda表达式
  NumberChanger nc = x => Console.WriteLine($"Lambda Expression: {x}");
  
  ```
  


### 多线程

- 使用方法同java

  ```c#
  new Thread(()=>{
      Console.WriterLine("线程开始执行!")
  }).Start();
  ```

  

- 线程生命周期中的各种状态：

  - **未启动状态**：当线程实例被创建但 Start 方法未被调用时的状况。

  - **就绪状态**：当线程准备好运行并等待 CPU 周期时的状况。

  - **不可运行状态 : **

    ：下面的几种情况下线程是不可运行的：

    - 已经调用 Sleep 方法
    - 已经调用 Wait 方法
    - 通过 I/O 操作阻塞

  - **死亡状态**：当线程已完成执行或已中止时的状况。

- 下表列出了 **Thread** 类的一些常用的 **属性**：

  | 属性               | 描述                                                         |
  | ------------------ | ------------------------------------------------------------ |
  | CurrentContext     | 获取线程正在其中执行的当前上下文。                           |
  | CurrentCulture     | 获取或设置当前线程的区域性。                                 |
  | CurrentPrincipal   | 获取或设置线程的当前负责人（对基于角色的安全性而言）。       |
  | CurrentThread      | 获取当前正在运行的线程。                                     |
  | CurrentUICulture   | 获取或设置资源管理器使用的当前区域性以便在运行时查找区域性特定的资源。 |
  | ExecutionContext   | 获取一个 ExecutionContext 对象，该对象包含有关当前线程的各种上下文的信息。 |
  | IsAlive            | 获取一个值，该值指示当前线程的执行状态。                     |
  | IsBackground       | 获取或设置一个值，该值指示某个线程是否为后台线程。           |
  | IsThreadPoolThread | 获取一个值，该值指示线程是否属于托管线程池。                 |
  | ManagedThreadId    | 获取当前托管线程的唯一标识符。                               |
  | Name               | 获取或设置线程的名称。                                       |
  | Priority           | 获取或设置一个值，该值指示线程的调度优先级。                 |
  | ThreadState        | 获取一个值，该值包含当前线程的状态。                         |

- 下表列出了 **Thread** 类的一些常用的 **方法**：

  | 序号 | 方法名 & 描述                                                |
  | ---- | ------------------------------------------------------------ |
  | 1    | **public void Abort()** 在调用此方法的线程上引发 ThreadAbortException，以开始终止此线程的过程。调用此方法通常会终止线程。 |
  | 2    | **public static LocalDataStoreSlot AllocateDataSlot()** 在所有的线程上分配未命名的数据槽。为了获得更好的性能，请改用以 ThreadStaticAttribute 属性标记的字段。 |
  | 3    | **public static LocalDataStoreSlot AllocateNamedDataSlot( string name)**  在所有线程上分配已命名的数据槽。为了获得更好的性能，请改用以 ThreadStaticAttribute 属性标记的字段。 |
  | 4    | **public static void BeginCriticalRegion()** 通知主机执行将要进入一个代码区域，在该代码区域内线程中止或未经处理的异常的影响可能会危害应用程序域中的其他任务。 |
  | 5    | **public static void BeginThreadAffinity()** 通知主机托管代码将要执行依赖于当前物理操作系统线程的标识的指令。 |
  | 6    | **public static void EndCriticalRegion()** 通知主机执行将要进入一个代码区域，在该代码区域内线程中止或未经处理的异常仅影响当前任务。 |
  | 7    | **public static void EndThreadAffinity()** 通知主机托管代码已执行完依赖于当前物理操作系统线程的标识的指令。 |
  | 8    | **public static void FreeNamedDataSlot(string name)** 为进程中的所有线程消除名称与槽之间的关联。为了获得更好的性能，请改用以 ThreadStaticAttribute 属性标记的字段。 |
  | 9    | **public static Object GetData( LocalDataStoreSlot slot )**  在当前线程的当前域中从当前线程上指定的槽中检索值。为了获得更好的性能，请改用以 ThreadStaticAttribute 属性标记的字段。 |
  | 10   | **public static AppDomain GetDomain()** 返回当前线程正在其中运行的当前域。 |
  | 11   | **public static AppDomain GetDomainID()** 返回唯一的应用程序域标识符。 |
  | 12   | **public static LocalDataStoreSlot GetNamedDataSlot( string name )**  查找已命名的数据槽。为了获得更好的性能，请改用以 ThreadStaticAttribute 属性标记的字段。 |
  | 13   | **public void Interrupt()** 中断处于 WaitSleepJoin 线程状态的线程。 |
  | 14   | **public void Join()** 在继续执行标准的 COM 和 SendMessage 消息泵处理期间，阻塞调用线程，直到某个线程终止为止。此方法有不同的重载形式。 |
  | 15   | **public static void MemoryBarrier()** 按如下方式同步内存存取：执行当前线程的处理器在对指令重新排序时，不能采用先执行 MemoryBarrier 调用之后的内存存取，再执行 MemoryBarrier 调用之前的内存存取的方式。 |
  | 16   | **public static void ResetAbort()** 取消为当前线程请求的 Abort。 |
  | 17   | **public static void SetData( LocalDataStoreSlot slot, Object data )**  在当前正在运行的线程上为此线程的当前域在指定槽中设置数据。为了获得更好的性能，请改用以 ThreadStaticAttribute 属性标记的字段。 |
  | 18   | **public void Start()** 开始一个线程。                       |
  | 19   | **public static void Sleep( int millisecondsTimeout )**  让线程暂停一段时间。 |
  | 20   | **public static void SpinWait( int iterations )**  导致线程等待由 iterations 参数定义的时间量。 |
  | 21   | **public static byte VolatileRead( ref byte address )  public static double VolatileRead( ref double address )  public static int VolatileRead( ref int address )  public static Object VolatileRead( ref Object address )**  读取字段值。无论处理器的数目或处理器缓存的状态如何，该值都是由计算机的任何处理器写入的最新值。此方法有不同的重载形式。这里只给出了一些形式。 |
  | 22   | **public static void VolatileWrite( ref byte address, byte value )  public static void VolatileWrite( ref double address, double value )  public static void VolatileWrite( ref int address, int value )  public static void VolatileWrite( ref Object address, Object value )**  立即向字段写入一个值，以使该值对计算机中的所有处理器都可见。此方法有不同的重载形式。这里只给出了一些形式。 |
  | 23   | **public static bool Yield()** 导致调用线程执行准备好在当前处理器上运行的另一个线程。由操作系统选择要执行的线程。 |



- **使用Task**

  ```c#
  //Task底层也是使用thread, 但性能更好, 使用简易
  Task.Run(()=>{
      Console.WriterLine("线程开始执行!")
      Thread.Sleep(1000);
  })
  ```

- **异步方法**

  async和await, 即 :

  在async方法中, 可以为子线程添加await关键字来等待子线程执行完毕, 在执行后面的代码

  ```c#
  public async void func(){
      await Task.Run(()=>{
      Console.WriterLine("子线程开始执行!")
      Thread.Sleep(1000);
      });
      Console.WriterLine("子线程执行完毕!");
  }
  ```

- 多个子线程 : 同步执行:

  ```c#
  public void func(){
      List<Task> ts = new List<Task>();
      ts.add(Task.Run(()=>{
      Console.WriterLine("子线程1开始执行!")
      Thread.Sleep(1000);
      }));
      ts.add(Task.Run(()=>{
      Console.WriterLine("子线程2开始执行!")
      Thread.Sleep(1000);
      }));
      //类似于ES6的promise.All(...).then(...)
      Task.WhenAll(ts).ContinueWith(res=>{
              Console.WriterLine("子线程执行完毕!");
      })
  
  }
  ```

- 线程安全

  - **使用锁 (Locking)**
  - **使用 Monitor(与lock类似) **
  - **使用 Mutex**
  - **使用 Interlocked 类: **Interlocked 类提供了一系列的原子操作，可用于对共享变量进行操作，确保这些操作是原子的，不会被中断。

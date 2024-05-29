# CPP Notes

## Basic

### 数据类型

- #### 基本数据类型

```c++
  布尔型      bool    1字节
  字符型      char    1字节
  整型        int     4字节
  浮点型      float   4字节
  双浮点型    double   8字节
  无类型void  缺失
  宽字符型wchar_t  2或4字节(可以存储 Unicode 字符)
```

- ####  类型修饰符


```C++
  signed
  unsigned
  short
  long
  //例如
  unsigned int //可以将int正数范围扩大一倍
  short int    //可以简写为 short
  long int     //可以简写为 long
  long long    //比long更大
```

-  #### typedef 声明

  -  **typedef {type} {newTypeName}**

     ```c++
    typedef short int wchar_t  //其实wchar_t就是short int
    ```

- #### 枚举类型


```c++
 enum {enumName} {
     name[=num],
     name[=num],
     name[=num],
     ...
 }
```

示例

```c++
enum color { red, green=5, blue };
...
color r = red;
color b = blur;   //其中 r = 0, b = 6
```

- 
  ####  其他

  - 指针类型 *

  - 数组类型 []

  - 结构体类型 struct

  - 类 类型 class

  - 共用体类型 union




- ####  类型转换

  - 静态转换
    即**强转类型转换**, 不进行运行时类型检查

    ```c++
    int i = 10;
    float f = static_cast<float>(i);
    ```


  - 动态转换
    用于**类继承中的基类和派生类之间指针或引用的转换**

    ```c++
    class Base{};
    class Derived : public Base{};
    Base* p_base = new Derived;
    Derived* p_derived = dynamic_cast<Derived>(p_base);
    ```


  - 常量转换
    用于**将const类型对象转为非const类型**

    ```c++
    const int i = 10;
    int& r = const_cast<int&>(i);
    ```


  - 重新解释转换
    用于**将一个数据类型的值重新解释为另一个数据类型的值**, 不进行任何类型检

    ```c++
    int i = 10;
    float f = reinterpret_cast<float&>(i);
    ```




### 变量作用域

- 局部变量
- 全局变量
- 类作用域
- 块作用域
  - 即代码中 使用{...} 包裹的作用域



### 常量const

- 又叫 *字面量*

- 有两种定义方式

  - 使用 #define 进行定义(即宏替换)
  - 使用 const 关键字

  ```c++
  const int I = 100; // 表示I为常量100
  ```

- **const本质上就是固定被修饰的变量**

  ```c++
  int a = 8;
  int * const p = &a;  // 主要用来防止指针地址被修改
  *p = 10;
  cout << *p; //输出的结果为 10 因为const固定的是a的地址, 而不是a的值
  /*
  同时固定值和地址
  const int * const p = &a;
  */
  ```

- 修饰

  - 修饰普通变量
  - 修饰指针变量
  - 修饰函数形参和函数返回值

  ```c++
  int fun(const int a); //没什么用
  const func(int b);    //没什么用
  ```

  - 修饰成员函数

- 其他类型限定符

  - volatile    表变量可能因为程序外的因素改变
  - restrict    表背修饰的指针是唯一访问它所指对象的方式
  - mutable     表类中的成员变量可以在const成员函数中被修改
  - static      定义静态变量
  - register    定义寄存器变量

### 存储类

- 用于定义变量|函数的范围可见性和声明周期
- auto      用于声明变量时推断类型, 函数声明时作返回值占位符
- register  用来定义存储在寄存器中的变量
- static    用来修饰变量的生命周期
- extern    用来提供一个全局变量的引用, 对所有程序文件均可见(多文件共享一个变量或函数)
- mutable   用于对象成员替代常量, mutable成员可以通过const成员函数修改
- thread_local  声明的变量仅可在所属的线程上访问, 随线程创建和销毁



### 函数使用

- 基本同C, 同java

- !在源文件中定义函数给另一个文件使用时, **必须使用函数声明在函数顶部**

- 函数参数

  - 传值调用 fun(int a, int b = 3)
  - 指针调用 fun(int \*a, int \*b)
  - 引用调用 fun(int &a, int &b)

  ```c++
  //示例
  void fun(int &a, int *b){
      // &a表示参数a的地址, a表示地址处存储的值
      // b表示参数b的地址, *b表示地址处存储的值
      cout << (&a == b);
      cout << (a == *b);
      //以上结果均为true
  }
  ```

- 可变参数

  - 使用C语言的方法
  
    ```c++
    #include <stdarg.h>
    
    void fun(int num, ...){   //使用 ... 实现接收, 第一个参数 num表传递参数的个数
     //首先需要声明一个va_list类型的指针
    //va_list类型是在cstdarg头文件里面定义的，该指针用来依次指向各个参数
    //va_start是一个宏，用来初始化arg_ptr，使其指向列表的第一个参数，这个宏的第二个参数是sum函数参数列表省略号前得固定参数的名称，用来确定第一个参数的位置 
       va_list arg_ptr;
       va_start(arg_ptr, count);
        
        int CountSum = 0;
        //va_arg是一个宏，返回arg_ptr指向的
    	//参数位置，并使arg_ptr递增来指向下
    	//一个参数值
    	//va_arg宏的第二个参数是需要统计的第
    	//一个参数的类型，如果类型不正确，
    	//程序也可能会执行，但得到的是无用的
    	//数据，arg_ptr将被错误地递增
    	for (int i = 0; i < count; ++i){
    		CountSum += va_arg(arg_ptr, int);
    	}
    	//将va_list类型的指针复位成空值
    	//就是清空可变参数列表
    	va_end(arg_ptr);
    
    	return CountSum;
    }
    
    sum(5,1,2,3,4,5);//return 15
    ```
    
  - **模版可变参数**
  
    ```c++
    template<typename T, typename... Args>
    void foo(const T& t, const Args&... rest) {
    	std::cout << sizeof(T) << endl;
    	cout << sizeof...(Args) << endl;//打印可变参数数量
    	cout << sizeof...(rest) << endl;//打印可变参数数量
    }
    
    //代码示例使用(递归)
    template <class T1, class ...T2>
    void printArgs(T1 p, T2 ...args){
        cout << "param: " << p << endl;
        printArgs(...args);
     }
    
    void printArgs(){
    	cout << "params end" << endl;   // 用于终止
    }
    
    //代码示例使用(逗号表达式)
    template <class ...C>
    void printParams(C...args){
        //逗号表达式解包(实质就是Initializer初始化数组)
        int a[] = { (cout << args << endl, 0)... }
    }
    ```
  
  - **可变参数initializer_list**
  
    ```c++
    //initializer_list相对vector更轻量化,用法跟传vector参数类似,列表里只能同类型，而且元素是常量。
    
    void error_msg(initializer_list<string> params) {
    	for (auto p = params.begin(); p != params.end(); ++p)
    		cout << *p << " ";
    	cout << endl;
    }
    
    error_msg({"im","da","gong"});//传递参数时, 需要以列表形式传递
    ```
  
    
  
- **lambda函数**

  - \[capture\](parameters)->return-type{body}
  
  - \[captrue\](parameters)->{body}
  
    ```c++
    //基本语法形式(无返回值)
    auto f = [](int x)->{
        cout << x << endl;
    }
    
    //基本语法形式(有返回值)
    auto f = [](auto x, auto y)-> auto {
    	return x + y;
    }
    
    //其中[]的内部用于访问一些外围的变量
    //示例
    int M = 10, N = 20;
    auto fun = [M, &N](int x )->auto{
        N = 100;
        return N * M * x;
    }
    
    //对外围变量的高级写法
    [&]		//按引用访问封闭范围内的所有变量
    [=}		//按值访问封闭范围内的所有变量
    [&, =N] // 按值访问N, 其他变量按引用访问(还可以[=N, &], [&M,=]等)
    [this] //在类中访问this, 还可以[*this]
     
    ```
  
    

### 基本补充

- c++的rand()函数默认会产生0到数据范围的整数, 不是0到1的小数

- c++中支持多维数组:

  ```c++
  type name[num][num][num] = ... //三维数组
  ```

- 字符串: 
  c++提供了两种风格的字符串

  - C 风格字符串(以'\0'作为结束标志)
  - string类 类型(不会以'\0'结束字符串, 面向对象的用)
    支持 str = str1 + str2;

- C++指针

  - 基本同C语言
  - C++中增加了空指针的定义, 即:

  ```C++
  int *ptr = NULL;    // 其实NULL 就是 0
  int *ptr = nullptr; //推荐使用这种
  ```

- C++的引用

  - 简介: **引用变量就是已存在变量的另一个名字**
  - !注: 
    - 不存在空引用(存在空指针), 引用必须连接到合法内存
    - 一旦引用被初始化为一个对象, 就不能指向另一个对象
    - 引用必须创建时初始化
  - 在函数中使用引用作为参数比一般的参数更安全
  - 在函数中返回引用

- 时间与日期

  - C++标准库没有提供日期类型需要引入\<ctime\>

- 输入和输出

  - \<iostream\> 下属对象: 
    - cin 标准输入流
    - cout 标准输出流
    - cerr 非缓冲标准错误流 (控制台显示为红色)
    - clog 缓冲标准错误流/标准日志流(控制台显示红色)
    
  - \<iomanip\> 下属对象: 

    - std::setw(n)    

      n 表示宽度，用数字表示。

      setw() 函数只对紧接着的输出产生作用。

      可以使用setfill('*)配合进行填充

      ` cout << setfill('*')  << setw(14) << "aaaa" << endl;`

  - \<fstream\>

- C++的struct基本同C(注意结构体指针要用->访问成员)

- C++的**范围解析运算符** ::



## OOP

### 基本概念

- C++增加了面向对象的概念, 类是C++的核心特性: 

  - 类中的数据: **成员变量**;
  - 类中的函数: **成员函数**;
  - 类似一种模版, 可以创建多个具有相同属性和行为的对象

- C++的类和对象的基本定义:

  ``` c++
  class {className} {
      {public|private|protect}:   // 访问修饰符
      	type variableName;
      	type functionName(){};
      	...
  }
  
  className class1;
  className class2;
  ```

#### 成员函数

- C++ 的成员函数可以在内部直接定义, 也可以在内部声明后, 在外部使用**范围解析运算符定义**

  ```c++
  class Box {
  public:
      double length;
      double width;
      double height;
  
      double getWidth() {
          return this->width;
      }
  
      double getHeight(void);
  };
  
  //重写|覆盖|实现
  double Box::getHeight() {
      return this->height;
  }
  ```

  

#### 访问修饰符

- C++的类访问修饰符在类结束或或下一个访问修饰符之前有效

- 基本使用: **成员和类的默认访问修饰符是 private**。

  ```c++
  class Base{
      double width; //默认为private类型
      public:
      ...
      protected:  // 与private相似, 但protect修饰的成员在派生类|子类中是可以访问的
      ...
      private:  //只有类和友缘函数可以访问私有成员
      ...
  }
  ```

  

#### 构造&析构函数

##### 构造函数

- 函数名称与类名完全相同

- 用于初始化成员变量, 没有返回值(也不会返回void)

- 每次创建新对象的时候运行

  ```c++
  class Line{
      double length;
      double width;
      public:
      	Line();   // 构造函数
      	Line(double len);  // 重载构造函数
      	Line(double len, double width); // 重载构造函数
  }
  
  //没有返回值
  Line::Line(){
      cout << "Object is being created !" << endl;
  }
  
  /* 使用初始化列表初始化length, (更简洁)
     相当于在方法内部写  this.length = len;*/
  Line::Line(double len) : length(len){ 
      cout << "Object is being created ! length init ->" << len << endl; 
  }
  
  //同上
  Line::Line(double length, double width): length(len), width(width){
      cout << "Object is being created ! length width init ->" << endl; 
  }
  ```



##### 析构函数

- 析构函数名与类名相同, 只是在前面添加了**~**作为前缀

- 析构函数不能带有任何参数, 没有返回值, 用于**程序前关闭资源, 释放内存**

  ```c++
  class Line{
      public:
      	Line();   // 构造函数
      	~Line(){   //析构函数
              cout << "Object is delete !" << endl;
          }
  }
  
  // 当然也可以这样: 
  //Line::~Line(){
  //	cout << "Object is delete !" << endl;
  //}
  ```

  

#### 拷贝构造函数

- 简言之, 就是*用已创建的对象来初始化新的对象(同类型)*

- **如果在类中没有定义拷贝构造函数，编译器会自行定义一个默认的**

- **默认的拷贝构造函数会逐个成员地复制源对象的所有成员变量**

- **如果类带有指针变量，并有动态内存分配，则它必须有一个拷贝构造函数。**

  ```c++
  class Line{
      public:
      	Line(const Line &line); // 自定义拷贝构造函数
  }
  ```

- 通常用于:

  - 初始化新的对象

    ```c++
    Line line2(line1);  // 使用拷贝构造函数, 构建一个与line1相同的对象
    Line line2 = line1; // 与上行代码相同的意思, 会自动调用拷贝构造函数
    ```

  - 将对象作为参数传递

    ```c++
    void display(Line obj)// 即obj会调用拷贝构造函数, 自身初始化为传参的line
    {...}  
    ```

  - 函数返回对象, 接收这个对象

    ```c++
    Line line2 = func(); // func()函数返回一个Line对象 初始化line2
    ```



#### 友元函数和友元类

- 类的友元函数定义在外部, 但有权访问类的所有private和protected成员

- **友元函数不是成员函数, 没有this指针, 外部定义不加 className :: **

- 友元函数基本用法:  (**friend 关键字**)

  ```c++
  class Line {
  private:
      double width;
  
  public:
      void setWidth(double width) {
          this->width = width;
      };
  
  	// 友元函数(参数就是这个对象, 由此可以访问对象的所有成员)
      friend void printWidth(Line line) {
          cout << line.width << endl;
      }
  
  };
  
  // 也可以这样, 不需要加 Line:: 因为友元函数不是成员函数
  //void printWidth(Line line) {
  //    cout << line.width << endl;
  //}
  
  int main() {
      Line line;
      line.setWidth(3.5);
      // 直接就可以调用, 因为友元函数声明就相当于已经在外部定义了
      printWidth(line);  // 输出 3.5
      return 0;
  }
  ```

- 友元类: **友元类的所有成员函数都是源类的友元函数**

  ```c++
  class Line {
  private:
      int width;
      double height;
  
  public:
      // 声明类A为友元类
      friend class A;
  
      void printWidth() {
          cout << width << endl;
      }
  };
  
  class A {
  public:
      // A中的所有函数都是Line的友元函数(需要传递对象做参数)
      void setWidth(Line &line, int width) {
          line.width = width;
      }
  };
  
  int main() {
      Line line;
      A a;
      a.setWidth(line, 5);
      line.printWidth();
      return 0;
  }
  ```

- **!注: 友元函数访问非static成员需要在对象作为参数, 访问static成员或全局变量则不需要**



#### 内联函数

- 内联函数是为了解决函数调用的效率问题, 编译后会用函数体替换调用的位置

- **!注: 内联函数一般是只有几行的函数, 通常只用于精短的函数**

  - 一般不允许出现循环和判断语句
  - 在类中定义的(有函数体)的函数都是内敛函数(写inline为显示内联函数, 不写算隐式内联函数)

  ```c++
  //函数声明前加上 inline 限定符
  inline void println(msg){
      cout << msg << endl;
  }
  ```

  

#### this指针和类指针

- this是一个特殊的指针, 指向当前对象的实例

- 只有类中的成员才有this指针

- 简言之, **this就是实例化对象的地址**(下方代码两处输出结果相同)

  ```c++
  class Line{
      public: 
      	void printThis(){
              cout << this << endl;   //结果相同
          }
  }
  
  int main(){
      Line line;
      line.printThis();
      cout << &line << endl;    //结果相同
  }
  ```

- 类的指针(同结构体):  **使用 -> 进行访问**

  ```c++
  class Line{
      public: 
      	void printThis(){
              cout << this << endl;   //结果相同
          }
  }
  
  int main(){
      Line *line = new Line;    //动态内存分配(可以new Line(@param))
      line->printThis();
      cout << line << endl;    //结果相同
  }
  
  //可以这样
  {
      Line *obj = new Line(123);
      Line &line = *obj;
      line.printThis();		 //结果相同
      cout << &line << endl;	 //结果相同
      cout << *obj << endl;	 //结果相同
  }
  ```

  

#### 静态成员

- **类的静态成员在所有对象中是共享的(不创建对象即可使用)**

- 如果不存在初始化语句, 在创建第一个对象时, 类中所有的静态数据都应初始化为0

- 用 :: 对静态成员变量进行初始化和使用,

  **静态变量不属于对象, 是属于类的(实例化不分配内存)**

  **不能在类中初始化静态变量(除非用const), 必须在类外部初始化**

- **外部初始化静态变量要加上类型,**

  **创建对象时不会为静态成员变量分配内存(因为静态变量不属于对象),**

  **所以单独分配内存以便多对象共用**

- **类的静态函数不需要创建对象即可直接使用**

- 可以使用 :: 直接使用类的静态函数

- !注 : 类的静态函数没有this指针, 只能访问静态成员

  ```c++
  class Line {
  public:
  
      Line() {
          ObjCount++;
      }
  	
      //需要在类的外部分配内存, 以便多对象共用
      static int ObjCount;
      
      //除非这样, 才会在类的对象创建时分配内存
  	//static const int ObjCount;
      
      static int getObjCount() {
          return Line::ObjCount;
      }
  };
  
  // 注意: 初始化需要加上类型(int)
  int Line::ObjCount = 0;
  
  int main() {
      Line line1;  // 所有对象共用静态变量
      Line line2;  // 所有对象共用静态变量
      cout << Line::ObjCount << endl;  //输出为2
      cout << Line::getObjCount() << endl;  //输出为2
      return 0;
  }
  ```

  

### 继承

#### 基本概念

- 已有的类为**基类**, 继承的类为**派生类**

- **C++支持多继承**

- 基本格式:

  ```c++
  //访问修饰符不写默认为 private
  class DerivedClass : [public|protected|private] BaseClass
  ```

- 对象的构造析构顺序:

  **创建对象会先调用父类构造函数再调用子类的构造函数(先构造父类再构造子类)**

  **对象销毁会先调用子类析构函数再调用父类的析构函数(先析构子类再析构父类)**

  

#### 访问控制

| 对基类的成员访问 | public | protected | private |
| ---------------- | ------ | --------- | ------- |
| 在类中           | yes    | yes       | yes     |
| 派生类对基类     | yes    | yes       | no      |
| 外部的类         | yes    | no        | no      |



#### 继承类型

- 使用 public | protected | private 继承后的访问属性

| 派生类继承方式    | 基类的public          | 基类的protect         | 基类的private      |
| ----------------- | --------------------- | --------------------- | ------------------ |
| public继承        | 变成派生类的public    | 变成派生类的protected | 不可直接访问[^!注] |
| protected继承     | 变成派生类的protected | 变成派生类的protected | 不可直接访问       |
| private继承(默认) | 变成派生类的private   | 变成派生类的private   | 不可直接访问       |

[^!注]: 基类的**私有**成员不能直接被派生类访问，但是可以通过调用基类的**公有**和**保护**成员来访问。



#### 	多继承

- 基本用法:

  ```c++
  class <派生类名> : <继承方式1> <基类1>,  <继承方式2> <基类2>, ...
  ```

- 同名问题: 

  **对于子类, 继承后的同名成员调用遵循就近原则**(覆盖)

  *可以使用作用域限定符指定访问的成员*

  **使用子类对象初始化父类指针时, 调用的同名方法是父类的(非virtual继承):**

  **也就是说, 不用virtual时, 子类的方法即使重写了, 也不影响父类**

  ```c++
  class Base{}
  class Derived : public Base{}
  //如有同名方法, 则调用父类的, 因为创建的对象时父类对象
  Base *base = new Derived;
  
  //基类使用virtual修饰函数后, 函数成为虚函数(会被子类重写而完全覆盖)
  //virtual type function(){...}
  ```

- 虚继承 | 虚函数 | 纯虚函数(没有函数体的, 必须要子类重写的虚函数)

  ```c++
  //虚继承用于多继承中
  class Derived : virtual public Base{...}
  
  //虚函数
  virtual int function(){...}
  
  //纯虚函数(函数体需要子类实现), 语法是在函数声明后加 = 0;
  virtual int funtion() = 0;
  ```

  

### 重载

- ##### 函数重载

  在作用域内声明多个功能相似的同名函数(形参不同)

  ```c++
  class printData
  {
     public:
        void print(int i) {
          cout << "整数为: " << i << endl;
        }
   
        void print(double  f) {
          cout << "浮点数为: " << f << endl;
        }
   
        void print(char c[]) {
          cout << "字符串为: " << c << endl;
        }
  };
  ```

- ##### 运算符重载(operator关键字)

  重载的运算符是带有特殊名称的函数，函数名是由关键字 operator 和其后要重载的运算符符号构成的。

  ```c++
  className operator {符号} (param){...}
  ```

  与其他函数一样，重载运算符有一个返回类型和一个参数列表。

  例如重载 + 运算符实现两个对象相加;

```c++
class Box {
public:
    Box(int width) : width(width) {}

    int width;

    int getWidth() {
        return this->width;
    };

    // 重载 + 运算符，实现把两个 Box 对象相加
    Box operator+(const Box &b) {
        Box box(0);
        box.width = this->width + b.width;
        return box;
    }
};

int main() {
    Box box1(1);
    Box box2(2);
    //使用重载 + 运算符
    Box box3 = box1 + box2;
    cout << box1.getWidth() << endl;   // 1
    cout << box2.getWidth() << endl;   // 2
    cout << box3.getWidth() << endl;   // 3
    return 0;
}
```

- 可重载的运算符列表(大部分都行)

  ```c++
  双目运算符 	+ (加)，-(减)，*(乘)，/(除)，% (取模)
  关系运算符 	==(等于)，!= (不等于)，< (小于)，> (大于)，<=(小于等于)，>=(大于等于)
  逻辑运算符 	||(逻辑或)，&&(逻辑与)，!(逻辑非)
  单目运算符 	+ (正)，-(负)，*(指针)，&(取地址)
  自增减运算符 	++(自增)，--(自减)
  位运算符 	| (按位或)，& (按位与)，~(按位取反)，^(按位异或),，<< (左移)，>>(右移)
  赋值运算符 	=, +=, -=, *=, /= , % = , &=, |=, ^=, <<=, >>=
  空间申请与释放 	new, delete, new[ ] , delete[]
  其他运算符 	()(函数调用)，->(成员访问)，,(逗号)，[](下标)
  ```

  可以通过重载输入输出运算符实现对象的toString:

  ```c++
  class Distance {
  private:
      int feet;             // 0 到无穷
      int inches;           // 0 到 12
  public:
      // 所需的构造函数
      Distance() {
          feet = 0;
          inches = 0;
      }
  
      Distance(int f, int i) {
          feet = f;
          inches = i;
      }
  
    //重载 输出运算符
      friend ostream &operator<<(ostream &output,
                                 const Distance &D) {
          output << "F : " << D.feet << " I : " << D.inches;
          return output;
      }
    //重载 输入运算符
      friend istream &operator>>(istream &input, Distance &D) {
          input >> D.feet >> D.inches;
          return input;
      }
  };
  
  int main() {
      Distance D1(11, 10), D2(5, 11), D3;
  
      cout << "Enter the value of object : " << endl;
      cin >> D3;
      cout << "First Distance : " << D1 << endl;
      cout << "Second Distance :" << D2 << endl;
      cout << "Third Distance :" << D3 << endl;
  
      return 0;
  }
  ```

  

- 不可重载

  ```c++
  .：      成员访问运算符
  .*, ->*：成员指针访问运算符
  ::：     作用域域运算符
  sizeof： 长度运算符
  ?:：     条件运算符
  #：      预处理符号
  ```

  

### 多态

- 即同种方式的不同呈现

  ```c++
  class Device {
  public:
      int width;
      int height;
      int length;;
  
      int getWidth();
  
  //    定义虚函数
      virtual int getHeight() {
          cout << "Device getHeight" << endl;
          return this->height;
      }
  
  //    定义纯虚函数
      virtual int getLength() = 0;
  
  };
  
  int Device::getWidth() {
      cout << "Device getWidth" << endl;
      return this->width;
  }
  
  class Phone : public Device {
  public:
      int getWidth();
  
  //    重载虚函数
      int getHeight() {
          cout << "Phone getHeight" << endl;
          return this->height;
      }
  
  //    实现纯虚函数
      int getLength() {
          cout << "Phone getLength" << endl;
          return this->length;
      }
  
  };
  
  int Phone::getWidth() {
      cout << "Phone getWidth" << endl;
      return this->width;
  }
  
  int main() {
      Device *device = new Phone;
      device->getWidth();    //Device getWidth   静态多态
      device->getHeight();   //Phone getHeight   动态多态
      device->getLength();   //Phone getLength   动态多态
      return 0;
  }
  ```

  **默认会根据创建的对象类型(Device)来确定调用的方法, 也就是静态多态或静态链接**

  **使用virtual修饰函数后, 即可根据所调用的对象类型来选择调用的函数，这种操作被称为动态链接，或后期绑定**。



### 数据抽象和封装

- 数据抽象: 向外界提供关键信息，隐藏其后台的实现细节，只表现必要的信息而不呈现细节

- 数据封装: 把数据和操作数据的函数绑定在一起的一个概念，这样能避免受到外界的干扰和误用

- **数据封装是一种把数据和操作数据的函数捆绑在一起的机制，**
- **数据抽象是一种仅向用户暴露接口而把具体的实现细节隐藏起来的机制**



### 接口(抽象类)

- **接口使用抽象类来实现, 如果类中至少有一个函数被声明为纯虚函数，则这个类就是抽象类。**

- 设计抽象类是为了提供一个适当的、通用的、标准化的接口

- *抽象类不能被用于实例化对象, 继承的派生类必须实现每个纯虚函数*

  - 定义一个函数为虚函数，不代表函数为不被实现的函数

  - 定义他为虚函数是为了允许用基类的指针来调用子类的这个函数

  - 定义一个函数为纯虚函数，才代表函数没有被实现

  - 定义纯虚函数是为了实现这个接口(继承抽象类)

    

## Advanced

### 文件和流

| 数据类型 | 描述                               |
| -------- | ---------------------------------- |
| ofstream | 文件输出流, 用于创建文件并写入信息 |
| ifstream | 文件输入流, 用于读取文件信息       |
| fstream  | 文件读写流                         |



### 异常处理

- 关键字 **try,  catch,  throw**

  ```c++
  //基本使用
  try{
     // 保护代码
  }catch( ExceptionName e1 ){
     // catch 块
  }catch( ExceptionName e2 ){
     // catch 块
  }catch( ExceptionName eN ){
     // catch 块
  }
  ```
  
- 抛出异常

  ```c++
  //throw 语句的操作数可以是任意的表达式，表达式的结果的类型决定了抛出的异常的类型。
  throw "Division by zero condition!";
  ```

- 捕获异常

  ```c++
  try{
     // 保护代码
  }catch( ExceptionName e ){
    // 处理 ExceptionName 异常的代码
  }
  ```
  
- 捕获任意异常

  ```c++
  //catch 块能够处理 try 块抛出的任何类型的异常，则必须在异常声明的括号内使用省略号 ...
  try{
     // 保护代码
  }catch(...){
    // 能处理任何异常的代码
  }
  ```
  
- 示例: 

  ```c++
  #include <iostream>
  using namespace std;
   
  double division(int a, int b){
     if( b == 0 )   {
        throw "Division by zero condition!";
     }
     return (a/b);
  }
   
  int main (){
     int x = 50;
     int y = 0;
     double z = 0;
   
     try {
       z = division(x, y);
       cout << z << endl;
     }catch (const char* msg) {
       cerr << msg << endl;       // Division by zero condition!
     }
   
     return 0;
  }
  ```
  
- 可以通过继承和重载exception类定义新的异常

  ```c++
  struct MyException : public exception{
    const char * what () const throw ()  {
      return "C++ Exception";
    }
  };
  ```



### 动态内存

- C++程序中的内存分成了两个部分: 

  - **栈** : 函数内部声明的所有变量都将占用栈内存;
  - **堆** : 程序中未使用的内存, 可用于动态内存分配

- **new**关键字: 可以为任意类型的动态分配内存

- **delete**关键字: 可以释放动态分配的内存

  ```c++
  //为基本类型分配
  double * d = new double;
  //动态释放内存
  delete d;
  
  //注: 如果自由存储区已被用完，可能无法成功分配内存, 可用如下检查new 运算符是否返回NULL
  double* pvalue  = NULL;
  if( !(pvalue  = new double )){
     cout << "Error: out of memory." <<endl;
     exit(1);
   
  }
  ```
  
  malloc()函数在c++中依旧存在, 但new关键字不管分配了内存, 还创建了对象
  
  ```c++
      int *p = new int(1);    //分配内存并初始化值
      cout << *p << endl;    // 输出: 1
  
      delete p;              // 释放掉分配的内存
      cout << p << endl;     // 输出内存地址
      cout << *p << endl;    // 输出可能数随机数字
  
  ```
  
- 数组的动态内存分配

  ```c++
  //一维数组
  // 动态分配,数组长度为 m
  int *array=new int [m];
   
  //释放内存
  delete [] array;
  ```

  ```c++
  //二维数组
  int **array;
  // 假定数组第一维长度为 m， 第二维长度为 n
  // 动态分配空间
  array = new int *[m];         //分配一维动态内存
  for( int i=0; i<m; i++ ){
      array[i] = new int [n];    //分配二维动态内存
  }
  //释放
  for( int i=0; i<m; i++ ){
      delete [] array[i];        //释放二维动态内存
  }
  delete [] array;                //释放一维动态内存
  ```
  
  ```c++
  //三维数组
  int ***array;
  // 假定数组第一维为 m， 第二维为 n， 第三维为h
  // 动态分配空间
  array = new int **[m];      //分配一维
  for( int i=0; i<m; i++ ){
      array[i] = new int *[n];   //分配二维
      for( int j=0; j<n; j++ )    {
          array[i][j] = new int [h];   //分配三维
      }
  }
  //释放
  for( int i=0; i<m; i++ ){
      for( int j=0; j<n; j++ )    {
          delete[] array[i][j];
      }
      delete[] array[i];
  }
  delete[] array;
  ```
  
- 对象的动态内存分配

  ```c++
  #include <iostream>
  using namespace std;
   
  class Box{
     public:
        Box() { 
           cout << "调用构造函数！" <<endl; 
        }
        ~Box() { 
           cout << "调用析构函数！" <<endl; 
        }
  };
   
  int main( ){
     Box* myBoxArray = new Box[4];
   
     delete [] myBoxArray; // 删除数组
     return 0;
  }
  
  //输出结果
  /*
  调用构造函数！
  调用构造函数！
  调用构造函数！
  调用构造函数！
  调用析构函数！
  调用析构函数！
  调用析构函数！
  调用析构函数！
  */
  ```



### 命名空间

- 用于通过附加信息来区分不同库中相同名称的函数、类、变量等。

- 基本使用: **namespace**

  ```c++
  namespace {name} {
     // 代码声明
      void fun(){...}
      int a;
  }
  
  //调用
  name::a;
  name::fun();
  ```

- **using指令**

  **使用 using namespace指令，这样在使用命名空间时就可以不用在前面加上命名空间的名称。**

  ```c++
  using 指令也可以用来指定命名空间中的特定项目。例如只打算使用 std 命名空间中的 cout 部分，您可以使用如下的语句：
  
  using std::cout;
  
  //using指令 还可以起到typedef的作用
  typedef int T
  using T = int    //都是重新定义了一个T类型替代int类型
      
  //using指令可以在private继承中使用基类的publuc和protect成员
      using Base::<成员名> 
  ```

- 命名空间的嵌套和不连续

  ```c++
  namespace ns1{
      
      namespace ns2{
          int a;
      }
  }
  
  //使用
  using namespace ns1::ns2;
  //或者
  ns1::ns2::a;
  ```

  命名空间可以定义在几个不同的部分中，因此命名空间是由几个单独定义的部分组成的。一个命名空间的各个组成部分可以分散在多个文件中。

  所以，如果命名空间中的某个组成部分需要请求定义在另一个文件中的名称，则仍然需要声明该名称。定义同名的命名空间, 可以为已有的命名空间增加新的元素



### 模版

- 模板是泛型编程的基础，泛型编程即以一种独立于任何特定类型的方式编写代码。

- **函数模版**:

  ```c++
  template <typename TYPE> 
  TYPE returnType funcName(param...){
     // 函数的主体
  }
  ```
  
      其中， TYPE是函数所使用的数据类型的占位符名称(通常一个大写字母)

```c++
//使用T作为返回值占位符, 可以接收不同类型的数据进行比较
template<typename T>
T const &getMax(T const &a, T const &b) {
    return a > b ? a : b;
}

template<class G>
void printYes(){    //没有返回值则不需要G作为占位符
    cout << "yes" << endl;
}
//判断负数
template<typename C>
C bool NegativeNumber(C number){
    return number < 0;
}

int main() {
    int a = 1, b = 2;
    double x = 1.5, y = 6.5;
    cout << getMax(a, b) << endl;
    cout << getMax(x, y) << endl;
    
    return 0;
}
```

- 函数模版同样支持重载

  ```c++
  template<class T1, class T2>
  void print(T1 arg1, T2 arg2){
    cout<<arg1<<" "<<arg2<<endl; 
  }
  template<class T>
  void print(T arg1, T arg2){
    cout<< arg1<< " "<< arg2<< endl;
  }
  ```
  
  

- **类模版**:

  ```c++
  template <class T> 
  class className {
  .
  .
  .
  }
  ```

  **type** 是占位符类型名称，可以在类被实例化的时候进行指定。

  可以使用一个逗号分隔的列表来定义多个泛型数据类型。

  ```c++
  #include <iostream>
  #include <vector>
  #include <string>
  #include <stdexcept>
  
  using namespace std;
  
  //在此定义一个需要初始化的模版
  template<class T>
  class Stack {
  private:
      vector<T> elems;     // 泛型元素
  
  public:
      void push(T const &);  // 入栈
      void pop();               // 出栈
      T top() const;            // 返回栈顶元素
      bool empty() const {       // 如果为空则返回真。
          return elems.empty();
      }
  };
  
  //初始化的函数也需要对应泛型模版在头部
  template<class T>
  void Stack<T>::push(T const &elem) {
      // 追加传入元素的副本
      elems.push_back(elem);
  }
  
  //初始化的函数也需要对应泛型模版在头部
  template<class T>
  void Stack<T>::pop() {
      if (elems.empty()) {
          throw out_of_range("Stack<>::pop(): empty stack");
      }
      // 删除最后一个元素
      elems.pop_back();
  }
  
  //初始化的函数也需要对应泛型模版在头部
  template<class T>
  T Stack<T>::top() const {   //泛型返回值
      if (elems.empty()) {
          throw out_of_range("Stack<>::top(): empty stack");
      }
      // 返回最后一个元素的副本
      return elems.back();
  }
  
  int main() {
      try {
          Stack<int> intStack;  // int 类型的栈
          Stack<string> stringStack;    // string 类型的栈
  
          // 操作 int 类型的栈
          intStack.push(7);
          cout << intStack.top() << endl;
          intStack.pop();
  
          // 操作 string 类型的栈
          stringStack.push("hello");
          cout << stringStack.top() << std::endl;
          stringStack.pop();
          stringStack.pop();  // 异常
  
          return 0;
      }
      catch (exception const &ex) {
          cerr << "Exception: " << ex.what() << endl;
          return -1;
      }
  }
  ```

- 在模板定义语法中关键字 class 与 typename 的作用完全一样。可以替换



### 预处理器

- 预处理器是一些指令，指示编译器在实际编译之前所需完成的预处理。

  所有的预处理器指令都是以井号（#）开头，只有空格字符可以出现在预处理指令之前。预处理指令不是 C++ 语句，所以它们不会以分号（;）结尾。

- 所有的实例中都有 **#include** 指令。这个宏用于把头文件包含到源文件中。

  C++ 还支持很多预处理指令，比如 #include、#define、#if、#else、#line 等

  ```c++
  // 符号常量通常称为宏，后续出现的所有宏都会编译之前替换为replacement-text
  #define macro-name replacement-text 
  
  //只在调试时进行编译，调试开关可以使用一个宏来实现
  #ifdef NULL
     #define NULL 0
  #endif
  
  #if 0
     不进行编译的代码
  #endif
  
  //# 和 ## 预处理运算符在 C++ 和 ANSI/ISO C 中都是可用的。# 运算符会把 replacement-text 令牌转换为用引号引起来的字符串。
  #define MKSTR( x ) #x
   
  int main (){
         //编译前会被转化为cout << "HELLO C++" << endl;
      cout << MKSTR(HELLO C++) << endl;  //输出HELLO C++
      return 0;
  }
  ```
  
- 预定义宏

  ```c++
  __LINE__ 	程序编译时包含当前行号。
  __FILE__ 	程序编译时包含当前文件名。
  __DATE__ 	一个形式为 month/day/year 的字符串，表示把源文件转换为目标代码的日期。
  __TIME__ 	一个形式为 hour:minute:second 的字符串，它表示程序被编译的时间。
      
  //示例代码
  cout << "Value of __LINE__ : " << __LINE__ << endl;
  cout << "Value of __FILE__ : " << __FILE__ << endl;
  cout << "Value of __DATE__ : " << __DATE__ << endl;
  cout << "Value of __TIME__ : " << __TIME__ << endl;
  //输出结果
  //Value of __LINE__ : 6
  //Value of __FILE__ : test.cpp
  //Value of __DATE__ : Feb 28 2011
  //Value of __TIME__ : 18:52:48
  
  ```

  

### 信号处理

- 信号是由操作系统传给进程的中断，会提早终止一个程序。在 UNIX、LINUX、Mac OS X 或 Windows 系统上，可以通过按 Ctrl+C 产生中断。

  有些信号不能被程序捕获，但是下表所列信号可以在程序中捕获，并可以基于信号采取适当的动作。这些信号是定义在 C++ 头文件 <csignal> 中。

  ```c++
  SIGABRT 	程序的异常终止，如调用 abort。
  SIGFPE 	错误的算术运算，比如除以零或导致溢出的操作。
  SIGILL 	检测非法指令。
  SIGINT 	程序终止(interrupt)信号。
  SIGSEGV 	非法访问内存。
  SIGTERM 	发送到程序的终止请求。
  ```



### 多线程

#### 线程基本使用

- C++11引入了标准线程库std::thread

  **需要引入头文件\<thread\>**

- 基本使用: 

  std::thread 默认构造函数，创建一个空的 **std::thread** 执行对象。

  **基本格式也就是 thread t(\<调用对象\>, \<参数\>)**

  | 函数                                                         | 类别           | 作业                                       |
  | ------------------------------------------------------------ | -------------- | ------------------------------------------ |
  | thread() noexcept                                            | 默认构造函数   | 创建一个线程， 什么也不做                  |
  | template <class Fn, class… Args> explicit thread(Fn&& fn, Args&&… args) | 初始化构造函数 | 创建一个线程， 以`args`为参数 执行`fn`函数 |

  ```c++
  #include<thread>
  
  //使用方法
  std::thread thread_object(callable)
      
  //或者
  using namespace std;
  thread t({params});
  
  //其中可调用对象可以是以下三个中的任何一个：
      函数指针
      函数对象
      lambda 表达式
  ```

- 示例代码: 

  ```c++
  // 使用三个不同的可调用对象
  #include <iostream>
  #include <thread>
  using namespace std;
    
  // 函数
  void foo(int Z){
      for (int i = 0; i < Z; i++) {
          cout << "线程使用函数指针作为可调用参数\n";
      }
  }
    
  // 可调用对象
  class thread_obj {
  public:
      //重载 () 
      void operator()(int x)    {
          for (int i = 0; i < x; i++)
              cout << "线程使用函数对象作为可调用参数\n";
      }
  };
    
  int main(){
      cout << "线程 1 、2 、3 "<< "独立运行" << endl;
    
      // 传递函数指针作为参数
      thread th1(foo, 3);
    
      // 传递对象(构造函数)作为调用
      thread th2(thread_obj(), 3);
    
      // 定义 Lambda 表达式
      auto f = [](int x) {
          for (int i = 0; i < x; i++)
              cout << "线程使用 lambda 表达式作为可调用参数\n";
      };
    
      // 线程通过使用 lambda 表达式(也就是foo而已)作为可调用的参数
      thread th3(f, 3);
    
      // 等待线程完成
      // 等待线程 t1 完成
      th1.join();
    
      // 等待线程 t2 完成
      th2.join();
    
      // 等待线程 t3 完成
      th3.join();
    
      return 0;
  }
  ```
  
- 引用传递问题

  ```c++
  using namespace std;
  
  template<typename T>
  void change(T &a, T b) {
      a = b;
  }
  
  int main() {
      int a = 10;
  //    thread t(change<int>, a, 5);   这样会报错, 因为Args&&... args
  //std::ref 可以包装按引用传递的值。
  //std::cref 可以包装按const引用传递的值。
      //可以这样
  	thread t(change<int>, ref(a), 5);
      t.join();
      cout << a << endl;  // 5
      return 0;
  }
  ```

  

- thread对象的常用函数

  | 函数                            | 作用                                                         |
  | ------------------------------- | ------------------------------------------------------------ |
  | void join()                     | 等待线程结束并清理资源（会阻塞）                             |
  | bool joinable()                 | 返回线程是否可以执行join函数                                 |
  | void detach()                   | 将线程与调用其的线程分离，彼此独立执行（必须在线程创建时立即调用，且调用此函数会使其不能被join） |
  | std::thread::id get_id()        | 获取线程id                                                   |
  | thread& operator=(thread &&rhs) | 见移动构造函数                                               |

- 注意事项

  - 线程是在thread对象被定义的时候开始执行的，join函数只是阻塞等待线程结束并回收资源。

  - 分离的线程（执行过detach的线程）会在调用它的线程结束或自己结束时释放资源。

    ```c++
        thread t(change<int>, ref(a), 5);
        t.detach(); //与main分离开, 自行释放资源
    //    t.join();
        cout << a << endl;
    ```

  - 线程会在函数运行完毕后自动释放，不推荐利用其他方法强制结束线程，可能会因资源未释放而导致内存泄漏。

  - 没有执行join或detach的线程在程序结束时会引发异常

#### 资源锁

- 无锁代码

  ```c++
  #include <iostream>
  #include <thread>
  using namespace std;
  int n = 0;          //定义全局变量
  void count10000() {  //让n自增到10000
  	for (int i = 1; i <= 10000; i++)
  		n++;            
  }
  int main() {
  	thread th[100];
  	for (thread &x : th)
  		x = thread(count10000);  //每个线程都去运行, 让n自增到10000
  	for (thread &x : th)
  		x.join();
  	cout << n << endl;  //最终的结果可能是991164, 而不是10000
  	return 0;
  }
  ```

  问题的原因是多个线程同时操作同一个对象, 因此引入了std::mutex和std::atomic

- **互斥锁 mutex**    #include \<mutex\>

  std::mutex是最基本的互斥量，一个线程将mutex锁住时，其它的线程就不能操作mutex，直到这个线程将mutex解锁。

  ```c++
  //代码更改
  
  #include <mutex>
  
  mutex mtx;   // 引入互斥锁
  
  void count10000() {
  	for (int i = 1; i <= 10000; i++) {
  		mtx.lock();       // 资源加锁
  		n++;
  		mtx.unlock();      //资源解锁   最终输出10000
  	}
  }
  ```

  | 函数            | 作用                                                         |
  | --------------- | ------------------------------------------------------------ |
  | void lock()     | 将mutex上锁。 如果mutex已经被其它线程上锁， 那么会阻塞，直到解锁； 如果mutex已经被同一个线程锁住， 那么会产生死锁 |
  | void unlock()   | 解锁mutex，释放其所有权。 如果有线程因为调用lock()不能上锁而被阻塞，则调用此函数会将mutex的主动权随机交给其中一个线程； 如果mutex不是被此线程上锁，那么会引发未定义的异常 |
  | bool try_lock() | 尝试将mutex上锁。 如果mutex未被上锁，则将其上锁并返回true； 如果mutex已被锁则返回false |

  **mutex很好的解决了问题, 但每次都要加锁解锁, 太慢了 , 由此引入了atomic**

- **原子变量atomic **   

  ```c++
  //代码更改
  #include <atomic>
  //int n = 0;          //更改此行
  atomic_int n = 0;     //改为原子变量, 即可实现
  //std::atomic_int只是std::atomic<int>的别名
  ```

  **原子操作是最小的且不可并行化的操作, 意味着即使是多线程，也要像同步进行一样同步操作atomic对象，从而省去了mutex上锁、解锁的时间消耗。**

  | 构造函数                         | 作用                                                        |
  | -------------------------------- | ----------------------------------------------------------- |
  | atomic() noexcept = default      | 构造一个atomic对象（未初始化，可通过atomic_init进行初始化） |
  | constexpr atomic(T val) noexcept | 构造一个atomic对象，用`val`的值来初始化                     |

  [atomic方法参考](https://cplusplus.com/reference/atomic/atomic/)

#### std::async和std::future

- #include \<future\>

- async是一个函数，所以没有成员函数。

  | 重载版本                                                     | 作用                                                         |
  | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | template <class Fn, class… Args>  future<typename result_of<Fn(Args…)>::type>    async (Fn&& fn, Args&&… args) | 异步或同步（根据操作系统而定）以args为参数执行fn   同样地，传递引用参数需要std::ref或std::cref |
  | template <class Fn, class… Args>  future<typename result_of<Fn(Args…)>::type> | async (launch policy, Fn&& fn, Args&&… args);	异步或同步（根据policy参数而定（见下文））以args为参数执行fn，引用参数同上 |

  std::launch有2个枚举值和1个特殊值：

  | 标识符(模式)                              | 作用                                      |
  | ----------------------------------------- | ----------------------------------------- |
  | 枚举值：launch::async                     | 异步启动                                  |
  | 枚举值：launch::deferred                  | 在调用future::get、future::wait时同步启动 |
  | 特殊值：launch::async \| launch::defereed | 同步或异步，根据操作系统而定              |

  异步运行代码示例:

  ```c++
  #include <iostream>
  #include <future>
  using namespace std;
  int main() {
      //使用async函数, 传输标识符(模式), 运行函数, 函数参数
  	async(launch::async, [](const char *message){
  		cout << message << flush;
  	}, "Hello, ");       
  	cout << "World!" << endl;
  	return 0;
  }
  ```

- **std::futrure用于接收async的异步返回值**

  ```c++
  #include <iostream>
  #include <future> // std::async std::future
  using namespace std;
  
  template<class ... Args> decltype(auto) sum(Args&&... args) {
  	// "0 +"避免空参数包错误
  	return (0 + ... + args);
  }
  
  int main() {
  	// 注：这里不能只写函数名sum，必须带模板参数
  	future<int> val = async(launch::async, sum<int, int, int>, 1, 10, 100);
  	// future::get() 进行阻塞, 以等待线程结束并获得返回值
  	cout << val.get() << endl;       // 输出 111
  	return 0;
  }
  ```

  future的常用函数

  - 一般：T get()	阻塞等待线程结束并获取返回值。
  - 当类型为引用：R& future<R&>::get()
  - 当类型为void：void future::get()   等价与使用future的wait函数

  | 函数                                                         | 作用                                                         |
  | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | 一般：T get()<br/>当类型为引用：R& future<R&>::get()<br/>当类型为void：void future::get() | 阻塞等待线程结束并获取返回值。<br/>若类型为void，则与`future::wait()`相同。<br/>**只能调用一次。** |
  | void wait() const                                            | 阻塞等待线程结束                                             |
  | template <class Rep, class Period><br/> future_status wait_for(const chrono::duration<Rep,Period>& rel_time) const; | 阻塞等待rel_time（rel_time是一段时间），<br/>若在这段时间内线程结束则返回future_status::ready<br/>若没结束则返回future_status::timeout<br/>若async是以launch::deferred启动的，则不会阻塞并立即返回future_status::deferred |

  对于void类型

  ```c++
  #include <iostream>
  #include <future>
  using namespace std;
  void count_big_number() {
  	for (int i = 0; i <= 10'0000'0000; i++);
  }
  int main() {
  	future<void> fut = async(launch::async, count_big_number);
  	cout << "Please wait" << flush;
  	// 使用wait_for每次等待1秒
  	while (fut.wait_for(chrono::seconds(1)) != future_status::ready)
  		cout << '.' << flush;  //实现加载动画
  	cout << endl << "Finished!" << endl;
  	return 0;
  }
  ```



#### std::promise

- 引入promise是为了获取到thread的返回值, 本质是通过引用获取

  promise实际上是std::future的一个包装

  ```c++
  #include <iostream>
  #include <thread>
  #include <future> // std::promise std::future
  using namespace std;
  
  template<class ... Args> decltype(auto) sum(Args&&... args) {
  	return (0 + ... + args);
  }
  
  template<class ... Args> void sum_thread(promise<long long> &val, Args&&... args) {
  	val.set_value(sum(args...));
  }
  
  int main() {
  	promise<long long> sum_value;
  	thread get_sum(sum_thread<int, int, int>, ref(sum_value), 1, 10, 100);
  	cout << sum_value.get_future().get() << endl;
  	get_sum.join(); 
  	return 0;
  }
  ```



#### std::this_thread	

- 常用函数

  | 函数                                                         | 作用                                               |
  | ------------------------------------------------------------ | -------------------------------------------------- |
  | std::thread::id get_id() noexcept                            | 获取当前线程id                                     |
  | template<class Rep, class Period><br/>void sleep_for( const std::chrono::duration<Rep, Period>& sleep_duration ) | 等待`sleep_duration`（`sleep_duration`是一段时间） |
  | void yield() noexcept                                        | **暂时**放弃线程的执行，将主动权交给其他线程       |

  ```c++
  //示例
  #include <iostream>
  #include <thread>
  #include <atomic>
  using namespace std;
  atomic_bool ready = 0;
  // uintmax_t ==> unsigned long long
  void sleep(uintmax_t ms) {
  	this_thread::sleep_for(chrono::milliseconds(ms));
  }
  void count() {
  	while (!ready) this_thread::yield();
  	for (int i = 0; i <= 20'0000'0000; i++);
  	cout << "Thread " << this_thread::get_id() << " finished!" << endl;
  	return;
  }
  int main() {
  	thread th[10];
  	for (int i = 0; i < 10; i++)
  		th[i] = thread(::count);
  	sleep(5000);
  	ready = true;
  	cout << "Start!" << endl;
  	for (int i = 0; i < 10; i++)
  		th[i].join();
  	return 0;
  }
  ```

  

## Resources

### 标准模板库STL

- C++ STL（标准模板库）是一套功能强大的 C++ 模板类，提供了通用的模板类和函数，这些模板类和函数可以实现多种流行和常用的算法和数据结构，如向量、链表、队列、栈。

- 标准模板库的核心包括以下三个组件：

  | 组件                | 描述                                                         |
  | ------------------- | ------------------------------------------------------------ |
  | 容器（Containers）  | 容器是用来管理某一类对象的集合。C++ 提供了各种不同类型的容器，比如 deque、list、vector、map 等。 |
  | 算法（Algorithms）  | 算法作用于容器。它们提供了执行各种操作的方式，包括对容器内容执行初始化、排序、搜索和转换等操作。 |
  | 迭代器（iterators） | 迭代器用于遍历对象集合的元素。这些集合可能是容器，也可能是容器的子集。 |

  ```c++
  //示例代码: 
  // vector向量容器与数组十分相似，
  //不同的是，向量在需要扩展大小的时候，会自动处理它自己的存储需求
  #include <iostream>
  #include <vector>
  using namespace std;
   
  int main(){
     // 创建一个向量存储 int
     vector<int> vec; 
     int i;
   
     // 显示 vec 的原始大小
     cout << "vector size = " << vec.size() << endl;
   
     // 推入 5 个值到向量中
     for(i = 0; i < 5; i++){
        vec.push_back(i);
     }
   
     // 显示 vec 扩展后的大小
     cout << "extended vector size = " << vec.size() << endl;
  
     // 使用迭代器 iterator 访问值
     vector<int>::iterator v = vec.begin();
     while( v != vec.end()) {
        cout << "value of v = " << *v << endl;
        v++;
     }
  
     return 0;
  }
  ```




### 标准库

- C++ 标准库可以分为两部分：

  - **标准函数库：** 是由通用的、独立的、不属于任何类的函数组成, 继承自 C 语言, 做了一定的添加和修改。
  - **面向对象类库：** 这个库是类及其相关函数的集合。

- 标准函数库分为以下几类：

  - 输入/输出 I/O
  - 字符串和字符处理
  - 数学
  - 时间、日期和本地化
  - 动态分配
  - 其他
  - 宽字符函数

- 面向对象类库

  标准的 C++ 面向对象类库定义了大量支持一些常见操作的类，比如输入/输出 I/O、字符串处理、数值处理。面向对象类库包含以下内容：

  - 标准的 C++ I/O 类
  - String 类
  - 数值类
  - STL 容器类
  - STL 算法
  - STL 函数对象
  - STL 迭代器
  - STL 分配器
  - 本地化库
  - 异常处理类
  - 杂项支持库

  

### STL容器

- **vector**, 即可变数组

  - push_back()		在vector后面添加一个元素item
  - pop_back()          在vector后面删除一个元素item
  - size()                     返回大小(个数)
  - clear()                   一键清空vector中的所有元素
  - insert()                 根据指定位置在vector中插入元素
  - erase()                 删除指定位置的元素
- **set**, 一个内部自动有序且不含重复元素的容器

  - begin()   　　    返回set容器的第一个元素

  - end() 　　　　 返回set容器的最后一个元素

  - clear()  　　      删除set容器中的所有的元素

  - empty() 　　　判断set容器是否为空

  - max_size() 　   返回set容器可能包含的元素最大个数

  - size() 　　　　 返回当前set容器中的元素个数

  - rbegin　　　　返回的值和end()相同

  - rend()　　　　 返回的值和rbegin()相同
- **map**, 一种键值对的映射关系容器
  - insert()
  - erase()
  - find()
  - ......
- **string**, 即字符串类型
- **stack**, 栈
- **queue**, 队列



#### pair

- 其标准库类型#include <utility\>头文件中，定义如下：

  类模板：`template<class T1,class T2> struct pair`

  参数：T1是第一个值的数据类型，T2是第二个值的数据类型。

  功能：pair将一对值(T1和T2)组合成一个值，

  这一对值可以具有不同的数据类型（T1和T2），两个值可以分别用pair的两个公有函数first和second访问。


- 基本使用:

  ```c++
  #include <utility>
  
  pair<string, int> word_count; 
  pair<string, string> author("James","Joy");
  
  //访问两个元素操作可以通过first和second访问
  author.first = 1;
  std::cout << author.second;
  ```

  

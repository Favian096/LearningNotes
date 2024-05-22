## TypeScript Notes

- TypeScript 是 JavaScript 的一个超集，支持 ECMAScript 6 标准

- ```js
  npm install -g typescript
  ```

- 增加的功能包括：
  - 类型批注和编译时类型检查
  - 类型推断
  - 类型擦除
  - 接口
  - 枚举
  - Mixin
  - 泛型编程
  - 命名空间
  - 元组
  - Await

### 基本语法

- 模块
- 函数
- 变量
- 语句和表达式
- 注释

***



#### 基础类型

| 任意类型   | any       | 声明为 any 的变量可以赋予任意类型的值。                      |
| ---------- | --------- | ------------------------------------------------------------ |
| 数字类型   | number    | 双精度 64 位浮点值。它可以用来表示整数和分数。               |
| 字符串类型 | string    | 一个字符系列, 反引号`来定义多行文本和内嵌表达式。 `          |
| 布尔类型   | boolean   | 表示逻辑值：true 和 false。                                  |
| 数组类型   | 无        | 声明变量为数组。                                             |
| 元组       | 无        | 元组类型用来表示已知元素数量和类型的数组，各元素的类型不必相同，对应位置的类型需要相同。 let x: [string, number]; x = ['Runoob', 1]; |
| 枚举       | enum      | 枚举类型用于定义数值集合。                                   |
| void       | void      | 用于标识方法返回值的类型，表示该方法没有返回值。             |
| null       | null      | 表示对象值缺失。                                             |
| undefined  | undefined | 用于初始化变量为一个未定义的值                               |
| never      | never     | never 是其它类型（包括 null 和 undefined）的子类型，代表从不会出现的值。 |

**注意：**TypeScript 和 JavaScript 没有整数类型。

---

- **any类型**

  任意值是 TypeScript 针对编程时类型不明确的变量使用的一种数据类型

- **null和undefined**

  在 JavaScript 中 null 表示 "什么都没有"。

  null是一个只有一个值的特殊类型。表示一个空对象引用。

  用 typeof 检测 null 返回是 object。

  在 JavaScript 中, undefined 是一个没有设置值的变量。

  typeof 一个没有值的变量会返回 undefined。

  TypeScript中启用严格的空校验（--strictNullChecks）特性，就可以使得null 和 undefined 只能被赋值给 void 或本身对应的类型

  ```ts
  let x : number = 1;
  x = 1;  //正确
  x = null; //错误
  x = undefined; //错误
  ```

  ***注意: 可以用 | 来支持多种类型***

  ```typescript
  let x : number | null | undefined;
  x = 1;  //正确
  x = null; //正确
  x = undefined; //正确
  ```

- **never**

  never 是其它类型（包括 null 和 undefined）的子类型，代表从不会出现的值。

  意味着声明为 never 类型的变量只能被 never 类型所赋值，在函数中它通常表现为抛出异常或无法执行到终止点（例如无限循环）

  ```ts
  let x: never;
  let y: number;
  
  // 编译错误，数字类型不能转为 never 类型
  x = 123;
  
  // 运行正确，never 类型可以赋值给 never类型
  x = (()=>{ throw new Error('exception')})();
  
  // 运行正确，never 类型可以赋值给 数字类型
  y = (()=>{ throw new Error('exception')})();
  
  // 返回值为 never 的函数可以是抛出异常的情况
  function error(message: string): never {
      throw new Error(message);
  }
  
  // 返回值为 never 的函数可以是无法被执行到的终止点的情况
  function loop(): never {
      while (true) {}
  }
  ```



#### 变量声明

---

- ```ts
  var [变量名] : [类型] = 值;
  ```

- ```ts
  //基础声明
  let a : number;
  let b : string;
  let c : boolean;
  let d : void;
  let e : null;
  let f : undefined;
  let g : never;
  let h : any;
  
  //多类型声明
  let i : number | string | boolean;
  ```

- 类型推断

  ```ts
  //没有为变量设置类型时, ts会自动推断
  var x = 1;  // 推断为 number 类型
  x = "hello" // 报错, 因为x 是number类型
  ```

- 类型断言  `<类型> 值` 或者`值 as 类型`

  ```ts
  //用来手动指定一个值的类型，即允许变量从一种类型更改为另一种类型。
  
  var str = '1'  //类型推断为string
  var str2 : number = <number> <any> str; //先断言为any类型, 再断言number类型
  console.log(str2)
  ```

- 存在判断

  ```ts
  /*当使用A对象属性A.B时，如果无法确定A是否为空，则需要用A?.B，表示当A有值的时候才去访问B属性，没有值的时候就不去访问*/
  
  console.log(a?.b)  // 表仅当a存在时才会返回a.b的值, 否则输出undefined
  ```

- 变量作用域

  - 全局作用域 − 全局变量定义在程序结构的外部，它可以在你代码的任何位置使用。
  - 类作用域 − 这个变量也可以称为 **字段**。类变量声明在一个类里头，但在类的方法外面。 该变量可以通过类的对象来访问。类变量也可以是静态的，静态的变量可以通过类名直接访问。
  - 局部作用域 − 局部变量，局部变量只能在声明它的一个代码块（如：方法）中使用。



#### 循环语句

***

- for...in 循环用于一组值的集合或列表进行迭代输出。

- for...of 语句创建一个循环来迭代可迭代的对象用, 以替代 for...in 和 forEach(), for...of 允许你遍历 Arrays（数组）, Strings（字符串）, Maps（映射）, Sets（集合）等可迭代的数据结构等。

- forEach循环:  [无法返回]

  ```ts
  list.forEach((val, idx, array) => {
      // val: 当前值 
      // idx：当前index  
      // array: Array 
  });
  ```

- every循环: [可以返回]

  ```ts
  //若有一个不满足条件，则返回false，后面的元素都不会再执行。
  list.every((val, idx, array) => {
      // val: 当前值
      // idx：当前index
      // array: Array
      return val !== 2; // 有一个不满足条件则返回false, 不再执行
      // Return false will quit the iteration
  });
  ```

- some循环: [满足条件返回]

  ```ts
  //若有一个元素符合条件，则返回true，且后面的元素不会再检测。
  list.some((val, idx, array) => {
      // val: 当前值
      // idx：当前index
      // array: Array
      return val === 2; // 满足条件返回true, 不再执行
      // Return false will quit the iteration
  });
  ```



#### 函数类型

----

- ```ts
  //设置 参数类型 和 返回值类型
  function buildName(firstName: string, lastName: string) : string{
      return firstName + " " + lastName;
  }
  ```

- ```ts
  //参数默认值
  function buildName(firstName : string, lastName : string = "瑠璃璃"){...}
  ```

- ```ts
  //可变参数, 即使用 ...variableName : type[]  实现
  function buildName(firstName: string, ...restOfName: string[]) {
      return firstName + " " + restOfName.join(" ");
  }
    
  let employeeName = buildName("Joseph", "Samuel", "Lucas", "MacKinzie");
  ```



### 面向对象

---

#### 接口类型

- 注: 接口是TypeScript的一部分, 不能被转化为JavaScript

  ```ts
  interface interface_name { 
      ...
  }
      
  //示例
  interface IPerson { 
      firstName:string, 
      lastName:string, 
      sayHi: ()=>string 
  } 
   
  var customer:IPerson = {         // 定义customer变量
      firstName:"Tom",
      lastName:"Hanks", 
      sayHi: ():string =>{return "Hi there"} 
  } 
   
  console.log("Customer 对象 ") 
  console.log(customer.firstName) 
  console.log(customer.lastName) 
  console.log(customer.sayHi())  
  ```

- 联合类型和接口

  ```ts
  interface RunOptions { 
      program:string; 
      commandline:string[]|string|(()=>string); 
  } 
   
  // commandline 是字符串
  var options:RunOptions = {program:"test1",commandline:"Hello"}; 
  console.log(options.commandline)  
   
  // commandline 是字符串数组
  options = {program:"test1",commandline:["Hello","World"]}; 
  console.log(options.commandline[0]); 
  console.log(options.commandline[1]);  
   
  // commandline 是一个函数表达式
  options = {program:"test1",commandline:()=>{return "**Hello World**";}}; 
   
  var fn:any = options.commandline; 
  console.log(fn());
  ```

- 接口和数组

  ```ts
  //接口中我们可以将数组的索引值和元素设置为不同类型，索引值可以是数字或字符串。
  interface namelist { 
     [index:number]:string 
  } 
   
  // 类型一致，正确
  var list2:namelist = ["Google","Runoob","Taobao"]
  // 错误元素 1 不是 string 类型
  // var list2:namelist = ["Runoob",1,"Taobao"]
  
  //用字符串做索引
  interface ages { 
     [index:string]:number 
  } 
   
  var agelist:ages; 
   // 类型正确 
  agelist["runoob"] = 15  
   
  // 类型错误，输出  error TS2322: Type '"google"' is not assignable to type 'number'.
  // agelist[2] = "google"
  ```

- 接口继承

  ```ts
  //ts中允许多继承接口来拓展
  interface IParent1 { 
      v1:number 
  } 
   
  interface IParent2 { 
      v2:number 
  } 
   
  interface Child extends IParent1, IParent2 { } 
  var Iobj:Child = { v1:12, v2:23} 
  console.log("value 1: "+Iobj.v1+" value 2: "+Iobj.v2)
  ```

  

#### 类和对象

---

- ```ts
  class Car { 
      // 字段 
      engine:string; 
   
      // 构造函数 
      constructor(engine:string) { 
          this.engine = engine 
      }  
   
      // 方法 
      disp():void { 
          console.log("发动机为 :   "+this.engine) 
      } 
  }
  ```

- 继承

  ```ts
  //ts使用extends进行单继承, 不支持多继承, 私有成员不可被继承
  
  class PrinterClass { 
     private device : string; // 私有变量, 不可被继承
     static num:number;   // 静态变量
     doPrint():void {
        console.log("父类的 doPrint() 方法。") 
     } 
  } 
   
  //继承并重写方法
  class StringPrinter extends PrinterClass { 
     doPrint():void { 
        super.doPrint() // 调用父类的函数
        console.log("子类的 doPrint()方法。")
     } 
  }
  
  StringPrinter.num = 21;
  ```

- 访问修饰符

  - **public（默认）** : 公有，可以在任何地方被访问。
  - **protected** : 受保护，可以被其自身以及其子类访问。
  - **private** : 私有，只能被其定义所在的类访问。

- 实现接口

  ```ts
  //使用implements
  interface ILoan { 
     interest:number 
  } 
   
  class AgriLoan implements ILoan { 
     interest:number 
     rebate:number 
     
     constructor(interest:number,rebate:number) { 
        this.interest = interest 
        this.rebate = rebate 
     } 
  } 
   
  var obj = new AgriLoan(10,1) 
  console.log("利润为 : "+obj.interest+"，抽成为 : "+obj.rebate )
  //利润为 : 10，抽成为 : 1
  ```



#### 泛型

---

- 泛型标识符

  在泛型中，通常使用一些约定俗成的标识符，比如常见的 `T`（表示 Type）、`U`、`V` 等，但实际上你可以使用任何标识符。

  T: 代表 "Type"，是最常见的泛型类型参数名。

  ```ts
  function identity<T>(arg: T): T {
      return arg;
  }
  ```

  K, V: 用于表示键（Key）和值（Value）的泛型类型参数。

  ```ts
  interface KeyValuePair<K, V> {
      key: K;
      value: V;
  }
  ```

  E: 用于表示数组元素的泛型类型参数。

  ```ts
  function printArray<E>(arr: E[]): void {
      arr.forEach(item => console.log(item));
  }
  ```

  R: 用于表示函数返回值的泛型类型参数。

  ```ts
  function getResult<R>(value: R): R {
      return value;
  }
  ```

  U, V: 通常用于表示第二、第三个泛型类型参数。

  ```ts
  function combine<U, V>(first: U, second: V): string {
      return `${first} ${second}`;
  }
  ```

- 泛型约束

  ```ts
  // 基本语法
  interface Lengthwise {
      length: number;
  }
  
  
  // T 必须实现 Lengthwise 接口，该接口要求有 length 属性。
  function logLength<T extends Lengthwise>(arg: T): void {
      console.log(arg.length);
  }
  
  // 正确的使用, 字符串类型有length属性且为number类型
  logLength("hello"); // 输出: 5
  
  // 错误的使用，因为数字没有 length 属性
  logLength(42); // 错误
  ```

- 泛型默认值

  ```ts
  // 基本语法
  function defaultValue<T = string>(arg: T): T {
      return arg;
  }
  
  // 使用带默认值的泛型函数
  let result1 = defaultValue("hello"); // 推断为 string 类型
  let result2 = defaultValue(42);      // 推断为 number 类型
  ```

  

#### 命名空间

---

- TypeScript 中命名空间使用 namespace 来定义，语法格式如下：

  ```ts
  namespace SomeNameSpaceName {  
      //添加export以便外界访问
  	export interface ISomeInterfaceName {}     
  	export class SomeClassName {}  
  }
  
  //命名空间支持嵌套
  namespace namespace_name1 { 
      export namespace namespace_name2 {
          export class class_name {    } 
      } 
  }
  ```

  以上定义了一个命名空间 SomeNameSpaceName，如果我们需要在外部可以调用 SomeNameSpaceName 中的类和接口，则需要在类和接口添加 export 关键字。

  要在另外一个命名空间调用语法格式为：

  ```ts
  SomeNameSpaceName.SomeClassName;
  ```

  如果一个命名空间在一个单独的 TypeScript 文件中，则应使用三斜杠 /// 引用它，语法格式如下：

  ```ts
  /// <reference path = "SomeFileName.ts" />
  ```



#### 模块

---

- 两个模块之间的关系是通过在文件级别上使用 import 和 export 建立的。

  ```ts
  //示例
  
  // 文件名 : SomeInterface.ts 
  export interface SomeInterface { 
     // 代码部分
  }
  
  //另一个文件中使用
  import someInterfaceRef = require("./SomeInterface");
  ```

- ```ts
  //实例
  //IShape.ts 文件代码：
  /// <reference path = "IShape.ts" /> 
  export interface IShape { 
     draw(); 
  }
  
  //Circle.ts 文件代码：
  import shape = require("./IShape"); 
  export class Circle implements shape.IShape { 
     public draw() { 
        console.log("Cirlce is drawn (external module)"); 
     } 
  }
  ```







## JS Notes

---

#### 数组filter

- filter() 方法创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素。

  filter() 不会对空数组进行检测。

  filter() 不会改变原始数组。

- 基本用法`array.filter((item,index,arr)=>{...}, thisValue)`

  | 参数    | 描述                         |
  | ------- | ---------------------------- |
  | *item*  | 必须。当前元素的值           |
  | *index* | 可选。当前元素的索引值       |
  | *arr*   | 可选。当前元素属于的数组对象 |

- 返回: item满足条件返回这个item, 如`return item >= 4`





#### 数组find

- find() 方法返回通过测试（函数内判断）的数组的第一个元素的值。

  find() 方法为数组中的每个元素都调用一次函数执行：

  - 当数组中的元素在测试条件时返回 *true* 时, find() 返回符合条件的元素，之后的值不会再调用执行函数。 
  - 如果没有符合条件的元素返回 undefined 

   find() 对于空数组，函数是不会执行的。

   find() 并没有改变数组的原始值。

- 基本用法`array.find(function(currentValue, index, arr),thisValue)`

  | 参数           | 描述                         |
  | -------------- | ---------------------------- |
  | *currentValue* | 必需。当前元素               |
  | *index*        | 可选。当前元素的索引值       |
  | *arr*          | 可选。当前元素所属的数组对象 |

- 返回符合测试条件的第一个数组元素值，如果没有符合条件的则返回 undefined。



#### 数组some

- some() 方法用于检测数组中的元素是否满足指定条件（函数提供）。

  some() 方法会依次执行数组的每个元素：

  - 如果有一个元素满足条件，则表达式返回*true*   , 剩余的元素不会再执行检测。
  - 如果没有满足条件的元素，则返回false。

  some() 不会对空数组进行检测。

  some() 不会改变原始数组。

- 基本用法`array.some(function(currentValue,index,arr),thisValue)`

  | 参数           | 描述                         |
  | -------------- | ---------------------------- |
  | *currentValue* | 必须。当前元素的值           |
  | *index*        | 可选。当前元素的索引值       |
  | *arr*          | 可选。当前元素属于的数组对象 |

- 如果数组中有元素满足条件返回 true，否则返回 false。

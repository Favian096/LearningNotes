# TypeScript Notes

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
  - 名字空间
  - 元组
  - Await

## Basic

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

- 

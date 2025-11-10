# SpringBoot

> The Basic, Application and Princeple sections are all based on SpringBoot2.



## Fundamental

> - SpringBoot设计目的是用来简化Spring应用的初始搭建以及开发过程
>
>     以`@SrpingBootApplicatoin` 作为引导, 是程序入口
>
> - 依赖中 starter 依赖定义项目使用所有依赖坐标, 内含许多子依赖, 以达到减少配置的目的 



### REST简介

> Representational State Transfer, 表现状态转换 => 描述资源的一种访问形式(风格), 即**RESTful**
>
> 传统风格: 
>
> `http://1oca1host/user/getById?id=1`
>
> `http://1oca1host/user/saveUse`
>
> REST风格: 
>
> `http://localhost/user/1` 书写简化
>
> `http://localhost/user` 隐藏资源访问行为



#### [RESTful接口](./Fundamental/src/main/java/org/fundamental/controller/restful/RestfulController.java)

- SpringMVC支持 8 种请求方式, 常用GET, POST, PUT, DELETE四种:

|          方法           |         主要用途          |
| :---------------------: | :-----------------------: |
|   `RequestMethod.GET`   |         查询资源          |
|  `RequestMethod.POST`   |         创建资源          |
|   `RequestMethod.PUT`   |   替换资源（整体更新）    |
| `RequestMethod.DELETE`  |         删除资源          |
|  `RequestMethod.PATCH`  |       部分更新资源        |
|  `RequestMethod.HEAD`   | 获取响应头（不返回 body） |
| `RequestMethod.OPTIONS` |    查询允许的请求方法     |
|  `RequestMethod.TRACE`  |    回环测试（调试用）     |

- 参数接收

|     注解      |              用途              |
| :-----------: | :----------------------------: |
| @PathVariable | 接收路径参数，使用{参数名}描述 |
| @RequestBody  |   用于接收 json 数据(应用广)   |
| @RequestParam |    用于接收止传参或表单传参    |



#### [接口简化](./Fundamental/src/main/java/org/fundamental/controller/restful/RestfulSimpleController.java)

- 类注解: `@RestController`, 等同`@Controller` 与`@ResponseBody`两个注解组合功能

- 方法注解: `@GetMapping`,  `@PostMapping`, `@PutMapping` , `@DeleteMapping`

    需要补充的路径参数填写在注解中: `@GetMapping("/{id}")`



### [基础配置](https://springdoc.cn/spring-boot/application-properties.html#appendix.application-properties)

> SpringBoot 默认配置文件 application.properties, 通过键值对配置对应属性, 参考官方文档



#### [配置文件](./Fundamental/src/main/resources/application.yml)

- SpringBoot提供了 3 种属性配置的方式, 主要使用 yml 文件: 

    **application.properties**

    ```properties
    server.port=8080
    ```

    **application.yml**

    ```yml
    server:
        port: 8080
    ```

    **application.yaml**

    ```yaml
    server: 
        port: 8080
    ```

    相同属性解析优先级: **properties > yml > yaml**, 若存在不同的属性则保留下来(**共存叠加**)

- yml和yaml都使用yaml语法: 

    - 大小写敏感
    - 属性层级关系使用多行描述，每行结尾使用冒号结束
    - 使用缩进表示层级关系，同层级左侧对齐，只允许使用空格（**不允许使用 Tab 键**）
    - 属性值前面添加空格（属性名与属性值之间使用冒号+空格作为分隔）
    - `#`表示注释

    ```yaml
    boolean: TRUE           # TRUE, True, true, FALSE, False, false均可   
    float: 3.14             # 3.1445646e+5 支持科学计数法
    int: 123                # 支持二进制, 八进制, 十进制, 十六进制
    null: ~                 # 用 ~ 表示null
    string: HelloWorld      # 字符串可以直接写
    string2: "Hello World"  # 也可以用双引号包裹特殊字符
    date: 2025-09-01        # 日期必须用 yyyy-MM-dd 格式
    datetime: 2025-08-24T13:02:34+08:00 # 时间和日期间使用 T 连接, 最后用 + 连接时区    
    ```

    ```yaml
    #数组
    subject:
        - C
        - Python
        - Java
    user:
        name: liulili
        age: 16
        subject: [C, Java, Python]
    #对象
    users: [{name: Favian096, age: 22}, {name: liulili, age: 16}]
    ```

    ```yaml
    #动态解析
    windowsPath: C:\user
    linuxPath: /home/user
    
    logPath: ${windowsPath}\temp   # \t不会解析
    #使用引号包裹的转义字符可以生效
    logPath2: "${windowsPath}\temp"   # \t 会解析为制表符
    ```

    

#### [配置读取]()

- `@Value`读取单个数据，属性名引用方式:

    ```Java
    @Value("${一级属性名·二级属性名...}")
    private String attr;
    ```

-  使用`Environment`对象读取全部属性数据

- 使用`@ConfigurationProperties("属性名")`加载指定数据

    1. 依据yml文件中的属性定义bean
    2. 定义为Spring管控的bean(`@Component`)
    3. 指定加载的数据, **注: 需要在bean内有setter方法, 否则数据将为null**



### 常用框架

#### [Junit](https://www.runoob.com/java/java-junit-lib.html)

> Java程序最小的功能单元是方法, 单元测试就是针对最小的功能单元编写测试代码

##### 使用Fixture

> 在测试中, 常遇到一个对象需要初始化，测试完可能还需要清理的情况
>
> JUnit提供了编写测试前准备、测试后清理的固定代码, 称之为Fixture

- **`@BeforeEach`和`@AfterEach`**

    > 由`@BeforeEach`标记的方法, 会在每个`@Test`方法执行前执行一次
    >
    > 由`@AfterEach`标记的方法, 会在每个`@Test`方法执行后执行一次
    >
    > 二者通常用来初始化和处理**实例变量**
    >
    > 由于每个`@Test`都执行, 因此在各个`@Test`方法中互不影响, 因为是不同的实例

- **`@BeforeAll`和`@AfterAll`**

    > 由`@BeforeAll`标记的方法, 在所有`@Test`方法运行前仅运行一次
    >
    > 由`@AfterAll`标记的方法, 在所有`@Test`方法运行后仅运行一次
    >
    > `@BeforeAll`和`@AfterAll`只能标注在静态方法上, 用于初始化和处理**静态变量**
    >
    > 由于只执行一次, 因此在各个`@Test`方法中均是唯一实例，会影响各个`@Test`方法



##### 异常测试

> 对于可能抛出的异常进行测试，本身就是测试的重要环节

- JUnit提供`assertThrows()`来期望捕获一个指定的异常

    `assertThrows()`在捕获到指定异常时表示通过测试，

    未捕获到异常，或者捕获到的异常类型不对，均表示测试失败: 

```java
@Test
void testException() {
    assertThrows(IllegalArgumentException.class, new Executable() {
        @Override
        public void execute() throws Throwable {
            //...
        }
    });
}

// lambda
@Test
void testIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, new Executable() {
        @Override
        public void execute() throws Throwable {
            //...
        }
    });
}
```



##### 条件测试

> JUnit根据不同的条件注解，决定是否运行当前的`@Test`方法

- **`@Disable`**

    如果注释掉`@Test`，JUnit就不知道这是个测试方法，

    而加上`@Disabled`，JUnit仍然识别出这是个测试方法，只是暂时不运行

```java
@Disabled
@Test
void testBug101() {
    // 这个测试不会运行
}

//针对两个系统的测试方法，其中一个只能在Windows上跑，另一个只能在Mac/Linux上跑：
@Test
@EnabledOnOs(OS.WINDOWS)
void testWindows() {
    assertEquals("C:\\test.ini", config.getConfigFile("test.ini"));
}
@Test
@EnabledOnOs({ OS.LINUX, OS.MAC })
void testLinuxAndMac() {
    assertEquals("/usr/local/test.cfg", config.getConfigFile("test.cfg"));
}
//只在64位系统运行: @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
```



#### [Lombok](https://www.quanxiaoha.com/lombok/lombok-annotations/)



#### [Mybatis](https://www.cainiaoplus.com/mybatis/mybatis-tutorial.html)



#### [MyBatis-plus](https://baomidou.com/getting-started/)



#### [Druid](https://druid.isharkfly.com/)



#### 



































































## Application









## Principle

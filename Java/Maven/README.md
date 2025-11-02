# Maven

## Maven Intro

- Maven 中的 GAVP 是指 GroupId、ArtifactId、Version、Packaging 等四个属性的缩写，其中前三个是必要的，而 Packaging 属性为可选项


> **Packaging定义规则：**
>
> ​	指示将项目打包为什么类型的文件，idea根据packaging值，识别maven项目类型！
>
> ​	packaging 属性为 jar（默认值），代表普通的Java工程，打包以后是.jar结尾的文件。
>
> ​	packaging 属性为 war，代表Java的web工程，打包以后.war结尾的文件。
>
> ​	packaging 属性为 pom，代表不会打包，用来做继承的父工程。

### Project Struction

Maven Web 程序的文件结构及每个文件的作用：

```xml
|-- pom.xml                               # Maven 项目管理文件 
|-- src
    |-- main                              # 项目主要代码
    |   |-- java                          # Java 源代码目录
    |   |   `-- com/example/myapp         # 开发者代码主目录
    |   |       |-- controller            # 存放 Controller 层代码的目录
    |   |       |-- service               # 存放 Service 层代码的目录
    |   |       |-- dao                   # 存放 DAO 层代码的目录
    |   |       `-- model                 # 存放数据模型的目录
    |   |-- resources                     # 资源目录，存放配置文件、静态资源等
    |   |   |-- log4j.properties          # 日志配置文件
    |   |   |-- spring-mybatis.xml        # Spring Mybatis 配置文件
    |   |   `-- static                    # 存放静态资源的目录
    |   |       |-- css                   # 存放 CSS 文件的目录
    |   |       |-- js                    # 存放 JavaScript 文件的目录
    |   |       `-- images                # 存放图片资源的目录
    |   `-- webapp                        # 存放 WEB 相关配置和资源
    |       |-- WEB-INF                   # 存放 WEB 应用配置文件
    |       |   |-- web.xml               # Web 应用的部署描述文件
    |       |   `-- classes               # 存放编译后的 class 文件
    |       `-- index.html                # Web 应用入口页面
    `-- test                              # 项目测试代码
        |-- java                          # 单元测试目录
        `-- resources                     # 测试资源目录
```

-   pom.xml：Maven 项目管理文件，用于描述项目的依赖和构建配置等信息。
-   src/main/java：存放项目的 Java 源代码。
-   src/main/resources：存放项目的资源文件，如配置文件、静态资源等。
-   src/main/webapp/WEB-INF：存放 Web 应用的配置文件。
-   src/main/webapp/index.jsp：Web 应用的入口页面。
-   src/test/java：存放项目的测试代码。
-   src/test/resources：存放测试相关的资源文件，如测试配置文件等。



### Command Build

```mermaid
graph LR
A["清理"]-->B["编译"]-->C["测试"]-->D["报告"]-->E["打包"]-->F["部署"]

```

> 需要在pox.xml目录下执行

| 命令        | 描述                        |
| ----------- | --------------------------- |
| mvn compile | 编译项目，生成target文件    |
| mvn package | 打包项目，生成jar或war文件  |
| mvn clean   | 清理编译或打包后的项目结构  |
| mvn install | 打包后上传到maven本地仓库   |
| mvn deploy  | 只打包，上传到maven私服仓库 |
| mvn site    | 生成站点                    |
| mvn test    | 执行测试源码                |



## Dependency Manage

### POM Configure

```xml
<!-- 模型版本 -->
<modelVersion>4.0.0</modelVersion>
<!-- 公司或者组织的唯一标志，并且配置时生成的路径也是由此生成， 如com.companyname.project-group，maven会将该项目打成的jar包放本地路径：/com/companyname/project-group -->
<groupId>com.companyname.project-group</groupId>
<!-- 项目的唯一ID，一个groupId下面可能多个项目，就是靠artifactId来区分的 -->
<artifactId>project</artifactId>
<!-- 版本号 -->
<version>1.0.0</version>

<!--打包方式
    默认：jar
    jar指的是普通的java项目打包方式！ 项目打成jar包！
    war指的是web项目打包方式！项目打成war包！
    pom不会讲项目打包！这个项目作为父工程，被其他工程聚合或者继承！后面会讲解两个概念
-->
<packaging>jar/pom/war</packaging>
```

### Dependency Property

- 依赖管理和依赖添加


```xml
<!-- 
   通过编写依赖jar包的gav必要属性，引入第三方依赖！
   scope属性是可选的，可以指定依赖生效范围！
   依赖信息查询方式：
      1. maven仓库信息官网 https://mvnrepository.com/
      2. mavensearch插件搜索
 -->
<dependencies>
    <!-- 引入具体的依赖包 -->
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
        <!-- 依赖范围 -->
        <scope>runtime</scope>
    </dependency>

</dependencies>
```

- 依赖版本统一提取和维护


```xml
<!--声明版本-->
<properties>
  <!--命名随便,内部制定版本号即可！-->
  <junit.version>4.12</junit.version>
  <!-- 也可以通过 maven规定的固定的key，配置maven的参数！如下配置编码格式！-->
  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
</properties>

<dependencies>
  <dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <!--引用properties声明版本 -->
    <version>${junit.version}</version>
  </dependency>
</dependencies>
```

### Dependency Scpoe

- 对应jar包的作用范围：**编译环境、测试环境、运行环境**


| 依赖范围     | 描述                                                         |
| ------------ | ------------------------------------------------------------ |
| **compile**  | 编译依赖范围，scope 元素的缺省值, 对三种环境均有效           |
| **test**     | 测试依赖范围, 只对测试环境有效                               |
| **provided** | 已提供依赖范围, 只对编译和测试环境有效, 运行时由外部提供     |
| runtime      | 运行时依赖范围, 仅运行环境有效                               |
| system       | 系统依赖范围，其效果与 provided 的依赖范围一致               |
| import       | 导入依赖范围，该依赖范围只能与 dependencyManagement 元素配合使用 |



### Build Configure

> 项目构建是指将源代码、依赖库和资源文件等转换成可执行或可部署的应用程序的过程，在这个过程中包括编译源代码、链接依赖库、打包和部署等多个步骤, 可以在pom.xml定制一些配置

- **指定打包命名**

```xml
<!-- 默认的打包名称：artifactid+verson.打包方式 -->
<build>
  <finalName>定义打包名称</finalName>
</build>
```

- **指定打包文件**

    如果在java文件夹中添加java类，会自动打包编译到classes文件夹

    但是在java文件夹中添加xml文件，默认不会被打包

    默认情况下，按照maven工程结构放置的文件会默认被编译和打包

    使用resources标签，指定要打包资源的文件夹要把哪些静态资源打包到 classes根目录

    应用场景：mybatis中有时会将用于编写SQL语句的映射文件和mapper接口都写在src/main/java下的某个包中，此时映射文件就不会被打包, 解决如下: 

```xml
<build>
    <!--设置要打包的资源位置-->
    <resources>
        <resource>
            <!--设置资源所在目录-->
            <directory>src/main/java</directory>
            <includes>
                <!--设置包含的资源类型-->
                <include>**/*.xml</include>
            </includes>
        </resource>
    </resources>
</build>
```

- **配置依赖插件**

    dependencies标签下引入开发需要的jar包, 我们可以在build/plugins/plugin标签引入插件, 常用的插件：修改jdk版本、tomcat插件、mybatis分页插件、mybatis逆向工程插件等


```xml
<build>
  <plugins>
      <!-- java编译插件，配jdk的编译版本 -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
          <encoding>UTF-8</encoding>
        </configuration>
      </plugin>
      <!-- tomcat插件 -->
      <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
         <version>2.2</version>
          <configuration>
          <port>8090</port>
          <path>/</path>
          <uriEncoding>UTF-8</uriEncoding>
          <server>tomcat7</server>
        </configuration>
      </plugin>
    </plugins>
</build>
```



### Dependency Transitivity

> 假如有Maven项目A依赖B，项目B依赖C。那么我们可以说 A依赖C

- **传递的原则**

在 A 依赖 B，B 依赖 C 的前提下，C 是否能够传递到 A

> - B 依赖 C 时使用 compile 范围：可以传递
>
> - B 依赖 C 时使用 test 或 provided 范围：不能传递
>
> - B 依赖 C 时，若配置了以下标签，则不能传递
>
>     ```xml
>     <dependency>
>         <groupId>com.alibaba</groupId>
>         <artifactId>druid</artifactId>
>         <version>1.2.15</version>
>         <optional>true</optional> <!--可阻止传递-->
>     </dependency>
>     ```
>



### Dependency Conflict

- **解决依赖冲突（如何选择重复依赖）方式：**

1. **自动选择原则**

    -   **短路优先原则**（第一原则）

        A—>B—>C—>D—>E—>X(version 0.0.1)

        A—>F—>X(version 0.0.2)

        则A依赖于X(version 0.0.2)
    -   依赖路径长度相同情况下，则“**先声明优先**”（第二原则）

        A—>E—>X(version 0.0.1)

        A—>F—>X(version 0.0.2)

        在\<depencies>\</depencies>中，先声明的，路径相同，会优先选择

2. **手动排除**

    ```xml
    <dependency>
      <groupId>com.atguigu.maven</groupId>
      <artifactId>pro01-maven-java</artifactId>
      <version>1.0-SNAPSHOT</version>
      <scope>compile</scope>
      <!-- 使用excludes标签配置依赖的排除, 该版本的依赖将不会被引入本模块中  -->
      <exclusions>
        <!-- 在exclude标签中配置一个具体的排除 -->
        <exclusion>
          <!-- 指定要排除的依赖的坐标（不需要写version） -->
          <groupId>commons-logging</groupId>
          <artifactId>commons-logging</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
    ```



### Inheritance Aggregation

- **继承语法**

    -   父工程
        ```xml
          <groupId>com.atguigu.maven</groupId>
          <artifactId>pro03-maven-parent</artifactId>
          <version>1.0-SNAPSHOT</version>
          <!-- 当前工程作为父工程，它要去管理子工程，所以打包方式必须是 pom -->
          <packaging>pom</packaging>
        
        ```
    -   子工程
        ```xml
        <!-- 使用parent标签指定当前工程的父工程 -->
        <parent>
          <!-- 父工程的坐标 -->
          <groupId>com.atguigu.maven</groupId>
          <artifactId>pro03-maven-parent</artifactId>
          <version>1.0-SNAPSHOT</version>
        </parent>
        
        <!-- 子工程的坐标 -->
        <!-- 如果子工程坐标中的groupId和version与父工程一致，那么可以省略 -->
        <!-- <groupId>com.atguigu.maven</groupId> -->
        <artifactId>pro04-maven-module</artifactId>
        <!-- <version>1.0-SNAPSHOT</version> -->
        ```

- **父工程依赖统一管理**

    -   父工程声明版本
        ```xml
        <!-- 使用dependencyManagement标签配置对依赖的管理 -->
        <!-- 被管理的依赖并没有真正被引入到工程 -->
        <dependencyManagement>
          <dependencies>
            <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-core</artifactId>
              <version>6.0.10</version>
            </dependency>
            <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-beans</artifactId>
              <version>6.0.10</version>
            </dependency>
            <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-context</artifactId>
              <version>6.0.10</version>
            </dependency>
            <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-expression</artifactId>
              <version>6.0.10</version>
            </dependency>
            <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-aop</artifactId>
              <version>6.0.10</version>
            </dependency>
          </dependencies>
        </dependencyManagement>
        ```
    -   子工程引用版本
        ```xml
        <!-- 子工程引用父工程中的依赖信息时，可以把版本号去掉。  -->
        <!-- 把版本号去掉就表示子工程中这个依赖的版本由父工程决定。 -->
        <!-- 具体来说是由父工程的dependencyManagement来决定。 -->
        <dependencies>
          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
          </dependency>
          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
          </dependency>
          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
          </dependency>
          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-expression</artifactId>
          </dependency>
          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
          </dependency>
        </dependencies>
        ```

- **聚合语法**

> 将多个项目组织到一个父级项目中，以便一起构建和管理的机制

```xml
<project>
  <groupId>com.example</groupId>
  <artifactId>parent-project</artifactId>
  <packaging>pom</packaging>
  <version>1.0.0</version>
  <!--使用modules-->
  <modules>
    <module>child-project1</module>
    <module>child-project2</module>
  </modules>
</project>
```

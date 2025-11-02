# Reflection and Annotation



## Reflection

> 反射, 即 加载类, 并允许以编程的方式解剖类中的成员变量, 方法, 构造器等



### [获取Class对象](./src/main/java/org/example/reflection/GetClassObject.java)

> 获取类的Class对象有三种方式:

- **类名.class**
- **调用Class提供的方法**
- **调用Object提供的方法**



### [获取类的构造器](./src/main/java/org/example/reflection/GetConstructor.java)

> 获取目的是 初始化对象返回

- **获取构造器**

| 方法                                                         | 备注                                         |
| ------------------------------------------------------------ | -------------------------------------------- |
| Constructor<?>[] getConstructs()                             | 获取全部的构造器(仅public修饰的)             |
| Constructor<?>[] getDeclaredConstructors()                   | 获取全部存在的构造器                         |
| Constructor<T> getConstructor(Class<?> ... ParamTypes)       | 获取某个构造器(仅public修饰的, 对应参数类型) |
| Constructor<T> getDeclaredConstructor(Class<?>... ParamTypes) | 获取某个存在的构造器, 对应参数类型           |

- **利用构造器初始化对象返回**

| Constructor提供的方法                   | 备注                                             |
| --------------------------------------- | ------------------------------------------------ |
| T newInstance(Object... initargs)       | 调用创建实例的方法, 传入参数, 获取初始化的对象   |
| public void setAccessible(boolean flag) | 设置为true, 可以访问私有构造器(禁用检查访问控制) |



### [获取类的成员变量](./src/main/java/org/example/reflection/GetVariable.java)

> 目的是为了 赋值和取值

- **获取方法**

| 方法                                       | 备注                                 |
| ------------------------------------------ | ------------------------------------ |
| public Field[] getFields()                 | 获取全部的成员变量(仅public修饰的)   |
| public Field[] getDeclaredFields()         | 获取存在的全部成员变量               |
| public Field getField(String name)         | 获取类的某个成员变量(仅public修饰的) |
| public Field getDeclaredField(String name) | 获取类存在的某个成员变量             |

- **赋值和取值**

| 方法                                    | 备注                                               |
| --------------------------------------- | -------------------------------------------------- |
| void set(Object obj, Object value)      | 赋值                                               |
| Object get(Object obj)                  | 取值                                               |
| public void setAccessible(boolean flag) | 设置为true, 可以访问私有成员变量(禁用检查访问控制) |



### [获取类的成员方法](./src/main/java/org/example/reflection/GetMethod.java)

> 目的是为了 执行

- **获取方法**

| 方法                                                         | 备注                                 |
| ------------------------------------------------------------ | ------------------------------------ |
| Method[] getMethods()                                        | 获取全部的成员方法(仅public修饰的)   |
| public Field[] getDeclaredMethods()                          | 获取存在的全部成员方法               |
| public Field getMethod(String name, Class<?>... paramTypes)  | 获取类的某个成员方法(仅public修饰的) |
| public Field getDeclaredMethod(String name, Class<?>... paramTypes) | 获取类存在的某个成员方法             |

- **执行方法**

| Method提供的方法                                 | 备注                                               |
| ------------------------------------------------ | -------------------------------------------------- |
| public Object invoke(Object obj, Object... args) | 触发方法的执行                                     |
| public void setAccessible(boolean flag)          | 设置为true, 可以访问私有成员方法(禁用检查访问控制) |

---



## Annotation

> 注解, 本质是一个接口, 目的是标记: 让其他程序依据注解信息来执行该程序



### 自定义注解

> Java 注解的属性类型只能是以下几种：
>
> - 基本数据类型（如 *int*, *float*, *boolean* 等）
> - *String*
> - 枚举类型
> - 注解类型
> - 一维数组（以上类型的数组）
>
> **注解的Value属性:**
>
> > 如果注解中只有一个value属性(或其他属性用默认值), 使用该注解时, value名称可以不写

```java
public @interface <annotationName>{
    public <Type> Param() default <defaultValue>;
}
```





### 元注解

> 修饰注解的注解

#### @Target

> 声明被修饰的注解只能在那些位置使用
>
> ```java
> @Target(ElementType.TYPE) //单个
> @Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD}) //多个
> ```

| 类型           | 使用位置 |
| -------------- | -------- |
| TYPE           | 类, 接口 |
| FIELD          | 成员变量 |
| METHOD         | 成员方法 |
| PARAMETER      | 方法参数 |
| CONSTRUCTOR    | 构造器   |
| LOCAL_VARIABLE | 局部变量 |



#### @Retention

> 声明注解的保留周期
>
> ```java
> @Retention(RetentionPolicy.RUNTIME)
> ```

| 类型    | 保留周期                                   |
| ------- | ------------------------------------------ |
| SOURCE  | 只作用在源码阶段, 字节码中不存在           |
| CLASS   | 默认值, 保留到字节码阶段, 但运行阶段不存在 |
| RUNTIME | 保留到运行阶段                             |





### 解析注解

> 判断类上, 方法上, 成员变量上是否存在注解, 并把注解里的内容给解析出来

- **解析方法**

> - 要解析谁上面的注解就要先拿到谁
> - 解析类上的注解就要先拿到类的Class对象, 在通过Class对象解析上面的注解
> - 解析变量和方法上的注解就要获取到Field或Method对象, 然后调用方法解析
>
> Class, Method, Field, Constructor都实现了AnnotatedElement接口, 方法如下:

| AnnotatedElement注解接口提供的解析方法                       | 备注                       |
| ------------------------------------------------------------ | -------------------------- |
| public Annotation[] getDeclaredAnnotations()                 | 获得当前对象上面的注解     |
| public T getDeclaredAnnotation(Class<T> annotationClass)     | 根据注解类获取指定注解对象 |
| public boolean isAnnotationPresent(Class<T> annotationClass) | 判断该注解是否存在         |


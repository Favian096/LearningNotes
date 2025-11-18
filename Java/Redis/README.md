# Redis



## Fundamental

> Redis 是一个 key-value 的数据库， key— 般是 String 类型,
>
> value 有多种类型, 基本类型有 5 中: String, Hash, List, Set, Zset



### 通用命令

> 在redis-cli中, 通过 **`help [command]`** 可以查看一个命令的具体用法

- **`KEYS`** 查看符合模板的所有 key, 返回符合条件的 key 

    ```bash
    KEYS * 查找所有的key
    KEYS *a 查找所有a开头的key
    ```

- **`DEL`** 删除指定的 key , **DEL key [key...]** 删除多个 key , 返回删除的个数

    ```bash
    DEL name 删除name这个key
    DEL k1 k2 k3 删除多个key
    ```

- **`EXISTS`** 判断 key 是否存在, **`EXIST key [key...]`** 判断多个 key 是否存在, 返回存在的个数

    ```bash
    EXISTS name
    ```

- **`EXPIRE`** 给一个 key 设置有效期(单位是秒)，有效期到期时该 key 会被自动删除

    ```bash
    EXPIRE name 36
    ```

- **`TTL`** 查看一个 key 的剩余有效期, 返回剩余秒数, 其中 **-1 表示永久有效, -2表示key已经被移除**

    ```bash
    TTL name
    ```

    

### 基本类型

#### String

> 字符串类型，是 Redis 中最简单的存储类型, 最大不超过512MB
>
> 根据字符串的格式不同, 又可以分为 3 类: 
>
> -  string: 普通字符串
> - int: 整数类型, 可以做自增、自减操作
> - float: 浮点类型, 可以做自增、自减操作

- **`SET`** 添加或者修改已经存在的一个 String 类型的键值对, 可同时设置TTL, 如: `SET age 22 ex 10`
- **`GET`** 根据 key 获取 String 类型的 value
- **`MSET`** 批量添加多个 String 类型的键值对
- **`MGET`** 根据多个 key 获取多个 string 类型的 value
- **`INCR`** 一个整型的 key 自增 1
- **`INCRBY`** 让一个整型的 key 自增并指定步长, 如: `INCRBY num 2` 让 num 自增2
- **`INCRBYFLOAT`** 让一个浮点类型的数字自增并指定步长
- **`SETNX`** 添加一个 string 类型的键值对，前提是这个 key 不存在，否则不执行
- **`SETEX`** 添加一个 String 类型的键值对，并且指定有效期, 如: `SETEX name 10 Favian096`


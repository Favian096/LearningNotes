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



#### Hash

> Hash 类型, 也叫散列, 其 value 是一个无序字典(`field: value`)
>
> | KEY  |    VALUE     |
> | :--: | :----------: |
> | user | name: Favian |

- **`HSET key field value`** 添加或修改 hash 类型 key 的 field 的值
- **`HGET key field`** 获取一个 hash 类型 key 的 field 的 value 的值
- **`HMSET`** 批量添加多个 hash 类型 key 的 field 的 value 的值
- **`HMGET`** 批量获取多个 hash 类型 key 的 field 的 value 的值
- **`HGETALL`** 获取一个 hash 类型的 key 中的所有的 field 和 value
- **`HKEYS`** 获取一个 hash 类型的 key 中的所有的 field
- **`HVALS`** 获取一个 hash 类型的 key 中的所有的 value
- **`HINCRBY`** 让一个 hash 类型 key 的字段值自增并指定步长
- **`HSETNX`** 添加一个 hash 类型的 key 的field 的值, 前提是这个 field 存在, 否则不执行



#### List

> 类似 LinkedList 双向链表, 支持正向反向检索
>
> - 有序 , 元素可重复, 插入快, 查询慢
>
> **基于命令原理, 可以用 List 模拟实现栈和队列 **

- **`LPUSH key element...`** 向列表左侧插入一个或多个元素, 如`LPUSH names t1 t2 t3`
- **`LPOP key`** 移除并返回列表左侧的第一个元素
- **`RPUSH key element...`** 向列表右侧插入一个或多个元素
- **`RPOP key`** 移除并返回列表右侧的第一个元素
- **`LRANGE key start end`** 返回一段角标范围内的所有元素
- **`BLPOP`** 和 **`BRPOP`** 命令 **`LPOP`** 和**`RPOP`** 类似, 在没有元素时等待指定时间(**阻塞式获取**), 不直接返回nil



#### Set

> 类似 HashSet 的结构
>
> - 无序, 元素不可重复, 查找快, 支持交集, 并集, 差集

- **`SADD key member...`** 向 set 中添加一个或多个元素(member)
- **`SREM key member...`** 移除 set 中的指定元素
- **`SCARD key`** 返回 set 中的元素的个数
- **`SISMEMBER key member`** 判读一个元素是否存在于 set 中
- **`SMEMBERS`** 获取 set 中的所有元素

- **`SINTER key1 key2...`** 获取 key1 和 key2 的交集
- **`SDIFF key1 key2...`** 获取 key1 和 key2 的差集(即 key1 - key2 后获得的元素)
- **`SUNION key1 key2...`** 获取 key1 和 key2 的并集



#### ZSet

> 可排序的 set 集合, 底层由跳表(skipList)实现, 每个元素都带有一个 score 属性, 用于实现排序
>
> - 可排序, 元素不重复, 查询快
>
> **默认 ZSet 为升序, 可将命令的 Z 改为 ZREV 即可降序, 如 `ZREVADD key score member...`**

- **`ZADD key score member...`** 添加一个或多个元素到 ZSet, 存在则更新
- **`ZREM key member`** 移除 Zset 中的指定元素
- **`ZSCORE key member`** 获取 ZSet 中指定元素的 score 值
- **`ZRANK key member`** 获取 ZSet 中指定元素的排名(序号, 从0开始)

- **`ZCARD key`** 获取 ZSet 中元素的个数
- **`ZCOUNT key min max`** 统计 score 值在给定范围内的所有元素的个数
- **`ZINCRBY key increment member`** 让 ZSet 中的指定元素自增, 步长为指定的 increment 值
- **`ZRANGE key min max`** 按照 score 排序后, 获得指定排名范围内的元素
- **`ZDIFF, ZINTER, ZUNION`** 同上, 求差集, 交集, 并集




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



### 客户端

#### [Jedis](https://github.com/redis/jedis)

> 以 Redis 命令作为方法名称/学习成本低/简单实用, 但 Jedis 实例是线程不安全的, 
>
> 多线程环境下需要基于**连接池**来使用
>
> ```java
> pblic class JedisConnectionFactory {
>  private static final JedisPool jedisPool;
>  static {
>      JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
>      //最大连接数
>      jedisPoolConfig.setMaxTotal(8);
>      //最大空闲连接
>      jedisPoolConfig.setMaxIdle(4);
>      //最小空闲连接
>      jedisPoolConfig.setMinIdle(2);
>      //最长等待时间 ms
>      jedisPoolConfig.setMaxWaitMillis(200);
>      jedisPool = new JedisPool(jedisPoolConfig, 
>                               "localhost", 6379, 1000, "password");
>  }
> 
>  public static Jedis getJedis() {
>      return jedisPool.getResource();
>  }
> }
> ```
>
> 

- **示例**

```java
public class JedisTest{
    private Jedis jedis;
    
    @BeforeEach
    void setUp(){
        jedis = new Jedis("localhost", 6379);
        //jedis.auth("passowrd");
        jedis.select(0);  
        
        //使用线程池对象
        //jedis = JedisConnectionFactory.getJedis();
    }
    
    @Test
    void getSetTest(){
        jedis.set("name", "Favian096");
        System.out.println(
            jedis.get("name");
        );
    }
    
    @AfterEach
    void close(){
        if(jedis != null){
            jedis.close();
        }
    }
}
```



#### SpringDataRedis

> SpringData 是 Spring 中数据操作的模块，包含对各种数据库的集成
>
> 其中对 Redis 的集成模块就叫做 SpringDataRedis
>
> - 提供了对不同 Redis 客户端的整合 (Lettuce 和 Jedis)
> - 提供了 RedisTemplate 统 —API 来操作 Redis
> - 支持 Redis 的发布订阅模型
> - 支持 Redis 哨兵和 Redis 集群
> - 支持基于 Lettuce 的响应式编程
> - 支持基于JDK 、JSON 、字符串、 Spring 对象的数据序列化及反序列化
> - 支持基于 Redis 的 JDKCoIIection 实现

- **RedisTemplate API**

|             API             |     返回值      |        备注        |
| :-------------------------: | :-------------: | :----------------: |
| redisTemplate.opsForValue() | ValueOperations | 操作String类型数据 |
| redisTemplate.opsForHash()  | HashOperations  |  操作Hash类型数据  |
| redisTemplate.opsForList()  | ListOperations  |  操作List类型数据  |
|  redisTemplate.opsForSet()  |  SetOperations  |  操作Set类型数据   |
| redisTemplate.opsForZSet()  | ZSetOperations  |  操作ZSet类型数据  |

- **示例**

```java
@SpringbootTest
public class RedisTemplateTest {
    @Resource
    private RedisTemplate redisTemplate;
    
    @Test
    void getSetTest() {
        redisTemplate.opsForValue().set("name", "Favian096");
        System.out.println(
            redisTemplate.opsForValue().get("name");
        );
    }
}
```

- **序列化**

> 默认使用 Redistemplate 时, 对 key 和 value 都会使用 jdk 的序列化工具处理(可读性差, 内存占用大)
>
> 可以对 RedisTemplate 设置序列化方法, 实现对 key 和 value 的自定义序列化

- **示例**

    > 实现 key 序列化为 String, Value 序列化为 JSON
    >
    > **示例代码的实现, 为了反序列化时知道对象的类型会将类的 class 类型存入 JSON 中, 带来开销**
    >
    > **为节省空间, 当需要存 ava 对象时, 通常手动完成对象的序列化和反序列化**

```java
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(
        RedisConnectionFactory connectionFactor){
        //创建RedisTemplate对象
        RedisTemplate<String, Object> template = new RedisTemplate();
        //设置连接工厂
        template.setConnectionFactory(connectionFactory);
        //创建json序列化工具
        GenericJackson2JsonRedisSerializer jsonResisSerializer = 
            new GenericJackson2JsonRedisSerializer();
        //设置key的序列化为String类型
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        //设置Value的序列化类型为Json类型
        template.setValueSerializer(jsonResisSerializer);
        template.setHashValueSerializer(jsonResisSerializer);
        
        return template;
    }
}
```



- **StringRedisTemplate**

> StringRedisTemplate 是官方的字符串序列化实现, 将 key 和 value 序列化为字符串
>
> 存入时需要手动转为字符串, 获取时需要手动解析字符串(JSON)

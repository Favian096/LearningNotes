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

    

#### 

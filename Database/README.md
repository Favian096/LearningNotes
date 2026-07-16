# Database



## TDengine

> 笔记参考智能电表数据库

### Basic

> [!Note]
>
> 时序数据, 即时间序列数据(Time-Series Data)
>
> 它们是一组按照时间发生先后顺序进行排列的序列数据, 数据表的第一列必须是时间戳
>
> 使用命令行`taosBenchMark`建立一个智能电表的测试数据库, [参考文档](https://docs.taosdata.com/3.4.1/reference/tools/taosbenchmark/)
> > ```bash
> > # 创建一个名为db的数据库, 建立默认超表meters,并创建100张子表, 每张子表写入1000条数据
> > taosBenchmark -d db -t 100 -n 1000 -T 4 -I stmt -y
> > ```

#### 数据模型

- **超级表**

  超级表是一种数据结构, 它能将某一特定类型的数据采集点聚集在一起, 形成一张逻辑上的统一表

  这些数据采集点具有相同的表结构, 但各自的静态属性可能不同, 创建超级表时, 除了定义采集量的结构之外, 还需定义超级表的标签, 一张超级表至少包含一个时间戳列, 一个或多个采集量列以及一个或多个标签列, 超级表的标签可以灵活地进行增加、修改或删除操作

- **子表**

  隶属于某张超级表的具体表, 可以将超级表的定义作为模板, 并通过指定子表的标签值来创建子表

  > - 一张超级表包含多张子表, 这些子表具有相同的表结构, 但标签值各异
  > - 子表的表结构不能直接修改, 但可以修改超级表的列和标签, 且修改对所有子表立即生效。
  > - 超级表定义了一个模板, 自身并不存储任何数据或标签

- **虚拟表**

  一种不存储实际数据而可以用于分析计算的表, 表的数据是每次查询计算时动态生成的(将各个原始表的不同列的数据按照时间戳排序、对齐、合并的方式来生成虚拟表)

  虚拟表不能写入和删除数据, 虚拟表也可以分为虚拟超级表、虚拟子表、虚拟普通表, 因此可以灵活地根据业务需要进行定义

  > - 列选择与拼接: 可从多个原始表中选择指定的列, 按需组合到一张虚拟表中, 形成统一的数据视图
  > - 基于时间戳对齐: 以时间戳为依据对数据进行对齐, 如果多个表在相同时间戳下存在数据, 则对应列的值组合成同一行; 若部分表在该时间戳下无数据, 则对应列填充为 NULL
  > - 动态更新: 虚拟表根据原始表的数据变化自动更新, 确保数据的实时性

- 普通表

  > 子表在普通表的基础上增加了静态标签(标签可增删改查)
  >
  > 子表总是隶属于某张超级表, 它们是超级表的一部分, 而普通表则独立存在, 不属于任何超级表
  >
  > 普通表无法直接转换为子表, 子表也无法转换为普通表

- 创建数据库

  ```sql
  CREATE DATABASE power PRECISION 'ms' KEEP 3650 DURATION 10 BUFFER 16;
  ```

  > - `PRECISION 'ms'`: 这个数据库的时序数据使用毫秒精度的时间戳
  > - `KEEP 3650`: 这个库的数据将保留 3650 天, 超过 3650 天的数据将被自动删除
  > - `DURATION 10` : 每 10 天的数据放在一个数据文件中
  > - `BUFFER 16` : 写入使用大小为 16MB 的内存池

- 创建超级表

  ```sql
  CREATE STABLE meters (
      ts timestamp, 
      current float, 
      voltage int, 
      phase float
  ) TAGS (
      location varchar(64), 
      group_id int
  );
  ```

- 创建子表

  ```sql
  CREATE TABLE d1001 
  USING meters (
      location,
      group_id
  ) TAGS (
      "California.SanFrancisco", 
      2
  );
  ```

  > 对超级表进行写入或查询操作时, 可以使用伪列 tbname 来指定或输出对应操作的子表名

- 插入数据时自动建表

  ```sql
  INSERT INTO d1002 
  USING meters 
  TAGS (
      "California.SanFrancisco", 
      2
  ) VALUES (
      NOW, 
      10.2, 
      219, 
      0.32
  );
  ```

  > 当子表d1002存在时, 写入数据; 不存在时, 基于meters为超表创建子表d1002并写入数据

- 创建虚拟表

  ```sql
  -- 原始超表表
  CREATE STABLE current_stb (
      ts timestamp, 
      current float
  ) TAGS (
      device_id varchar(64),
      location varchar(64), 
      group_id int
  );
  
  CREATE STABLE voltage_stb (
      ts timestamp, 
      voltage int
  ) TAGS (
      device_id varchar(64),
      location varchar(64), 
      group_id int
  );
   
  CREATE STABLE phase_stb (
      ts timestamp, 
      phase float
  ) TAGS (
      device_id varchar(64),
      location varchar(64), 
      group_id int
  );
  
  -- 创建子表并写入数据
  create table current_d1001 using current_stb(device_id, location, group_id) tags("d1001", "California.SanFrancisco", 2);
  create table voltage_d1001 using voltage_stb(device_id, location, group_id) tags("d1001", "California.SanFrancisco", 2);
  create table phase_d1001 using phase_stb(device_id, location, group_id) tags("d1001", "California.SanFrancisco", 2);
  
  -- 创建虚拟超表(后缀加 VIRTUAL 1)
  CREATE STABLE meters_v (
      ts timestamp, 
      current float, 
      voltage int, 
      phase float
  ) TAGS (
      location varchar(64), 
      group_id int
  ) VIRTUAL 1;
  
  -- 创建虚拟子表(VTABLE标识, 字段不标注类型, 后用from标注来源)
  CREATE VTABLE d1001_v (
      current from current_d1001.current,
      voltage from voltage_d1001.voltage, 
      phase from phase_d1001.phase
  ) 
  USING meters_v 
  TAGS (
      "California.SanFrancisco", 
      2
  );
  
  -- 创建虚拟表(VTABLE标识, 字段标注类型, 后用from标注来源)
  CREATE VTABLE current_v (
      ts timestamp,
      d1001_current float from current_d1001.current,
      d1002_current float from current_d1002.current, 
      d1003_current float from current_d1003.current,
      d1004_current float from current_d1004.current
  );
  ```

  